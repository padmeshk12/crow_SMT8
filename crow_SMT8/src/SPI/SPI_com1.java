package SPI;

import xoc.dsa.DeviceSetupFactory;
import xoc.dsa.IDeviceSetup;
import xoc.dsa.ISetupDigInOut;
import xoc.dsa.ISetupPattern;
import xoc.dsa.ISetupWavetable;
import xoc.dta.TestMethod;
import xoc.dta.measurement.IMeasurement;

public class SPI_com1 extends TestMethod {
    private IMeasurement measurement;

    @Override
    public void setup() {
        // Create a new DeviceSetup instance
        IDeviceSetup ds = DeviceSetupFactory.createInstance();

        // Import specifications (as in original code)
        ds.importSpec("configuration.ARPS10X0X5__master_cfg_mfh.Groups");
        ds.importSpec("mainSpecs.ARPS1020A5_tf_REV02.Spc_Lev_eqn3_spec1_set1_Tim_eqn5_spec1_set1");

        // Define the signal group (P_ALL_SPI)
        String signals = "P_ALL_SPI";

        // Create a pattern with pmode = 4 (xMode = 4)
        ISetupPattern pat = ds.createPattern(1, signals);



        // Optional: Set up a wavetable with '0' as default drive action
        // This ensures that '0' is the default bit (0V) when not overridden
        ISetupDigInOut setupSignals = ds.addDigInOut(signals);
        setupSignals.addWavetable(1)
            .addStateCharDescription('0', ISetupWavetable.DriveAction.F00)  // Drive 0
            .addStateCharDescription('1', ISetupWavetable.DriveAction.F10)  // Drive 1
            .addStateCharDescription('X', ISetupWavetable.DriveAction.FNZ)  // High-Z
            .addStateCharDescription('N', ISetupWavetable.DriveAction.N);   // No drive

        // Set timing and level (optional, but recommended for proper operation)
        setupSignals.timing().setPeriod(100E-9).setD1(0.0);  // 100ns period, 0 delay
        setupSignals.level().setVil(0.0).setVih(3.3);       // 0V to 3.3V

        // Add vector: 01110
        // Ensure the vector length matches the number of signals in P_ALL_SPI
        // Example: if P_ALL_SPI has 5 signals, "01110" is valid
        pat.addVector("0");


        // Optional: Add more vectors
        // pat.addVector("1001");
        // pat.addVector("1100");

        // Call the pattern in the operating sequence
        ds.patternCall(pat);

        // Assign the setup to the measurement
        measurement.setSetups(ds);
    }

    @Override
    public void execute() {
        measurement.execute();
        println("Pattern sequence generated successfully with full control over vectors and instructions!");


    }

}
