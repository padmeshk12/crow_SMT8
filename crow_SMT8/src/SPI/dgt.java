package SPI;

import java.util.Map;

import xoc.dsa.DeviceSetupFactory;
import xoc.dsa.IDeviceSetup;
import xoc.dsa.ISetupDcVI;
import xoc.dta.TestMethod;
import xoc.dta.annotations.In;
import xoc.dta.datatypes.dsp.MultiSiteWaveDouble;
import xoc.dta.datatypes.dsp.MultiSiteWaveDoubleArray;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.resultaccess.IDcVIResults;

public class dgt extends TestMethod {
    public IMeasurement measurement;

    @In public String pinName = "OFF_PS1600";
    @In public double forceCurrent = 0.0; // 0 mA
    @In public double iRange = 10e-6;     // 10 µA
    @In public int samples = 100;         // Assuming Ramp_Samples = 100 (example value)
    @In public double fs = 1000.0;        // 1 KHz
    @In public double measWait = 2.0;     // 2 ms

    @Override
    public void setup() {
        // Create the device setup instance
        IDeviceSetup ds = DeviceSetupFactory.createInstance();

        // Add DCVI setup for the specified pin
        ISetupDcVI dcvi = ds.addDcVI(pinName);

        // Set the current range (iRange) and voltage range (vRange)
        // vRange is typically set based on expected voltage swing; here we assume a safe value
        double vRange = 5.0; // Example: 5V max expected; adjust based on DUT
        dcvi.level().setIrange(iRange).setVrange(vRange);

     // Configure safe connect mode (recommended for PS1600)
        dcvi.setSafeConnectOn();

        // Create the current force (iForce) action
        ISetupDcVI.IIforce iforceAction = dcvi.iforce()
                .setForceValue(forceCurrent)  // 0 mA
                .setVclampLow(-5.0)           // Clamp low (adjust as needed)
                .setVclampHigh(5.0);          // Clamp high (adjust as needed)

        // Create the voltage measure (vMeas) action with ramp sampling
        ISetupDcVI.IVmeas vmeasAction = dcvi.vmeas()
                .setSamples(samples)          // Number of samples (Ramp_Samples)
                .setFs(fs)                    // Sampling frequency (1 KHz)
                .setWaitTime(measWait / 1000.0); // Wait time in seconds (2 ms ? 0.002 s)

        // Define the operating sequence: call iForce first, then vMeas
        ds.actionCall(iforceAction);
        ds.actionCall(vmeasAction);

        // Assign the setup to the measurement
        measurement.setSetups(ds);
    }


    @Override
    public void execute() {
        // TODO Auto-generated method stub

        measurement.execute();


        IDcVIResults results = measurement.dcVI(signals).preserveResults();

        // Retrieve the voltage waveform from the sourceMeasureWaveform action
        Map<String, MultiSiteWaveDoubleArray> voltageWaveform = results.sourceMeasureWaveform("").getWaveform();

        // Debug: Print the result
        message(1, "Voltage waveform results: " + voltageWaveform.toString());

        // Optional: Plot the waveform in the Signal Analyzer
        for (Map.Entry<String, MultiSiteWaveDoubleArray> entry : voltageWaveform.entrySet()) {
            String signalName = entry.getKey();
            MultiSiteWaveDoubleArray waveArray = entry.getValue();
            for (int i = 0; i < waveArray.getElements().length; i++) {
                MultiSiteWaveDouble wave = waveArray.getElements()[i];
                wave.plot(signalName + "_voltage_ramp_" + i);
            }

    }

}
