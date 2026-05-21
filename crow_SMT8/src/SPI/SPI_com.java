package SPI;

import test_methods.common;
import xoc.dsa.DeviceSetupFactory;
import xoc.dsa.IDeviceSetup;
import xoc.dsa.ISetupPattern;
import xoc.dta.TestMethod;
import xoc.dta.measurement.IMeasurement;

public class SPI_com extends TestMethod {
    private IMeasurement measurement;

    @Override
    public void setup() {
        IDeviceSetup ds = DeviceSetupFactory.createInstance();

        ds.importSpec("configuration.ARPS10X0X5__master_cfg_mfh.Groups");
        ds.importSpec("mainSpecs.ARPS1020A5_tf_REV02.Spc_Lev_eqn3_spec1_set1_Tim_eqn5_spec1_set1");

        String signalGroup = String.join("+", context.inspection().initialSpec("configuration.ARPS10X0X5__master_cfg_mfh.Groups").getSignalGroup("P_ALL_SPI"));

        ISetupPattern pat = ds.createPattern(1,signalGroup);

     //   pat.setDefaultValue('0');


        pat.addVector("1010","Start SPI transaction");
        pat.addVector("1001");
        pat.addVector("1100","End transaction");
        pat.addVector("1100","End transaction");



        ds.patternCall(pat);

        ISetupPattern pat1 = common.createBasicPattern(ds, signalGroup);
        ds.patternCall(pat1);

        common.printMessage("Pattern created using common function");

        ISetupPattern pat2 = common.createBasicPattern(ds, signalGroup);
        ds.patternCall(pat2);

        common.printMessage("Pattern created using common function");



        measurement.setSetups(ds);

    }

    @Override
    public void execute() {
        measurement.execute();
        println("Pattern sequence generated successfully with full control over vectors and instructions!");


    }

}
