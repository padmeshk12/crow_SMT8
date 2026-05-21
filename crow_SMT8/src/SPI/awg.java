package SPI;

import xoc.dsa.DeviceSetupFactory;
import xoc.dsa.IDeviceSetup;
import xoc.dsa.IDeviceSetup.DSAGenerationMode;
import xoc.dsa.ISetupDcVI;
import xoc.dsa.ISetupWaveformRamp;
import xoc.dta.TestMethod;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.resultaccess.IDcVIResults;

public class awg extends TestMethod {

    public IMeasurement measurement;

    public int Ramp_Samples = 1000;
    public double FS = 1000.0; // 1 KHz
    public double iForceRange = 0.05; // 100 mA
    public double vForceRange = 3.0; // 5 V
    public double iClampLow = -0.02; // -100 mA
    public double iClampHigh = 0.02;  // +100 mA

    public String pinName = "OFF_PS1600";

    public double vMin = 0.0;   // 0 V
    public double vMax = 2.9;   // 2.9 V

    @Override
    public void setup() {
        IDeviceSetup ds = DeviceSetupFactory.createInstance(DSAGenerationMode.GENERATE_IN_FILE);

     // Add the DCVI pin (VIN1) and configure it
        ISetupDcVI dcviPin = ds.addDcVI(pinName).setConnect(true).setDisconnect(false);

     // Set the level properties: current and voltage ranges
        dcviPin.level().setIrange(iForceRange).setVrange(vForceRange);

     // Create the ramp waveform
        ISetupWaveformRamp rampWaveform = ds.addWaveformRamp()
                .setStartValue(vMax).setStopValue(vMin).setSamples(Ramp_Samples).setSampleRate(FS); // This sets the sample rate (FS)

     // Create the source waveform action: force voltage ramp
        ISetupDcVI.ISourceWaveform sourceAction = dcviPin.sourceWaveform()
                .setForceTypeVoltage() // Force voltage
                .setWaveform(rampWaveform)
                .setIclamp(iClampHigh); // Clamp current to ±100 mA
                //  .setSamples(Ramp_Samples); // Match number of samples

     // Add the action to the setup sequence
        ds.actionCall(sourceAction);

        measurement.setSetups(ds);


    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub

        measurement.execute();

     // Preserve the results (optional, for post-processing)
        IDcVIResults results = measurement.dcVI(pinName).preserveResults();

        // Optional: Debug output or plotting
        // Example: Plot the waveform in Signal Analyzer
//        Map<String, MultiSiteWaveDoubleArray> waveforms = results.sourceMeasureWaveform("").getWaveform();
//
//        // Optional: Plot the waveform in the Signal Analyzer
//        for (Map.Entry<String, MultiSiteWaveDoubleArray> entry : waveforms.entrySet()) {
//            String signalName = entry.getKey();
//            MultiSiteWaveDoubleArray waveArray = entry.getValue();
//            for (int i = 0; i < waveArray.getElements().length; i++) {
//                MultiSiteWaveDouble wave = waveArray.getElements()[i];
//                wave.plot(signalName + "_voltage_ramp_" + i);
//            }
//
//    }
    }

}
