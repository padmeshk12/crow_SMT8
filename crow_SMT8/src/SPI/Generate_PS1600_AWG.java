package SPI;

import xoc.dsa.DeviceSetupFactory;
import xoc.dsa.IDeviceSetup;
import xoc.dsa.ISetupAwg;
import xoc.dsa.ISetupAwg.ISourceWaveform;
import xoc.dsa.ISetupWaveformDc;
import xoc.dsa.ISetupWaveformRamp;
import xoc.dsa.ISetupWaveformSine;
import xoc.dta.TestMethod;
import xoc.dta.annotations.In;
import xoc.dta.measurement.IMeasurement;

/**
 * Test method to generate AWG waveforms for PS1600 digital pin using Device Setup API.
 * Supports DC, Ramp, and Sine waveforms on PS1600 pin.
 */
public class Generate_PS1600_AWG extends TestMethod {

    // Input parameters for waveform configuration
    @In
    public String pinName = "VIN1"; // PS1600 pin name (e.g., ps1600_1, ps1600_2)

    @In
    public String waveformType = "sine"; // Options: "dc", "ramp", "sine"

    @In
    public double amplitude = 1.0; // Amplitude in volts (0.0 to 5.0)

    @In
    public double offset = 0.0; // DC offset in volts

    @In
    public double frequency = 1e6; // Frequency in Hz (for sine wave)

    @In
    public double rampStart = 0.0; // Start voltage for ramp
    @In
    public double rampStop = 3.3; // Stop voltage for ramp
    @In
    public int rampSamples = 1000; // Number of samples for ramp

    @In
    public double sampleRate = 100e6; // Sample rate in Hz

    @In
    public int repeatCount = 1; // Number of repetitions

    @In
    public boolean differentialMode = true; // Use differential mode

    // Internal variables
    private String awgName;
    private String waveformName;
    private String sourceActionName;

    public IMeasurement measurement;

    @Override
    public void setup() {
        // Create the DSA object
        IDeviceSetup ds = DeviceSetupFactory.createInstance();

        // Import common signal group definitions
        ds.importSpec("configuration.ARPS10X0X5__master_cfg_mfh.Groups");

        // Determine the AWG name based on pin
        awgName = "VIN1";

        // Create the AWG instrument
        ISetupAwg awgSetup = ds.addAwg(awgName);

        // Configure AWG settings
        if (differentialMode) {
            awgSetup.setConfigOptionDifferential();
        }

        // Create waveform based on type
        switch (waveformType.toLowerCase()) {
            case "dc":
                // Create DC waveform
                ISetupWaveformDc dcWaveform = ds.addWaveformDc("dc_waveform");
                dcWaveform.setDcValue(offset)
                         .setSamples(1000)
                         .setSampleRate(sampleRate);
                waveformName = "dc_waveform";
                break;

            case "ramp":
                // Create ramp waveform
                ISetupWaveformRamp rampWaveform = ds.addWaveformRamp("ramp_waveform");
                rampWaveform.setStartValue(rampStart)
                           .setStopValue(rampStop)
                           .setSamples(rampSamples)
                           .setSampleRate(sampleRate);
                waveformName = "ramp_waveform";
                break;

            case "sine":
                // Create sine waveform
                ISetupWaveformSine sineWaveform = ds.addWaveformSine("sine_waveform");
                sineWaveform.setFrequency(frequency)
                           .setAmplitude(amplitude)
                           .setSampleRate(sampleRate)
                           .setSamples(1000);
                waveformName = "sine_waveform";
                break;

            default:
                throw new IllegalArgumentException("Unsupported waveform type: " + waveformType);
        }

        // Create source action for the waveform
        sourceActionName = "source_" + waveformType + "_action";
        ISourceWaveform sourceWaveform = awgSetup.sourceWaveform(sourceActionName)
                .setBandwidth("100MHz")
                .setAmplitude(amplitude)
                .setOffset(offset)
                .setRepeatCount(repeatCount)
                .setWaveform(waveformName);

        // Set up the operating sequence
        ds.parallelBegin("AWG_Group");
        {
            ds.sequentialBegin("AWG_Sequence");
            {
                ds.actionCall(sourceWaveform);
            }
            ds.sequentialEnd();
        }
        ds.parallelEnd();

        // Associate the device setup with the measurement
        measurement.setSetups(ds);
    }

    @Override
    public void execute() {
        // Execute the measurement
        measurement.execute();
        println("AWG waveform generated successfully for PS1600 pin: " + pinName);
        println("Waveform type: " + waveformType);
        println("Amplitude: " + amplitude + "V");
        println("Offset: " + offset + "V");
        println("Sample rate: " + sampleRate + "Hz");
        println("Repeat count: " + repeatCount);
    }
}
