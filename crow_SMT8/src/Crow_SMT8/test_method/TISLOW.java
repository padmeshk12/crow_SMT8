package Crow_SMT8.test_method;

import xoc.dsa.DeviceSetupFactory;
import xoc.dsa.IDeviceSetup.DSAGenerationMode;
import xoc.dta.measurement.IMeasurement;

public class TISLOW extends common_Actions {

    private static final String SPI_SETUP_SPEC =
            "mainSpecs.ARPS1020A5_tf_REV02.Spc_Lev_eqn3_spec1_set1_Tim_eqn5_spec1_set3";
    public IMeasurement measurement;


    @Override
    public void setup() {
        messageLogLevel = 8;

        ds = DeviceSetupFactory.createInstance(DSAGenerationMode.GENERATE_IN_FILE);
        ds.importSpec("configuration.ARPS10X0X5__master_cfg_mfh.Groups");
 //       ds.importSpec(SPI_SETUP_SPEC);

//        Utility("GND_K9_TGND,ISEN_K30_PS1600_DPS128",1);
//        Wait_ms(3);
//        vForce_ID("ITRIP_PS1600", 0, 40e-3, 40e-3);
//      //  Disconnect("OFF_PS1600");
//        Utility("SS_K23_CAP",1);
//        Wait_ms(3);
//        vForce("VDD", 28, 100e-3, 100e-3);
//        vForce("ALL_VIN", 28, 100e-3, 100e-3);
//        iForce("VOUT1+VOUT2_RS2+VOUT3_RS2+VOUT4+VOUT5+VOUT6", 0, 200e-3, -10, 10);
//        vForce_ID("ISEN_PS1600", 0, 40e-3, 40e-3);
//        vForce_ID("EN0_PS1600+FCB_PS1600", 3, 10e-3, 10e-3);
//
//        ISetupDigInOut dig = ds.addDigInOut("OFF_PS1600");
//        dig.level()
//        .setVil(0.0)
//        .setVih(1.8)
//        .setVol(0.5)
//        .setVoh(1.2);
//
//        dig.timing()
//        .setPeriod(100e-9);
//
//        vForce("ALL_VIN", 28, 100e-3, 100e-3);
//
//
//        vForce("ALL_VIN", 28, 100e-3, 100e-3);
//
//        dig.timing()
//        .setPeriod(30e-9);

        vForce("ISEN_PS1600", 0, 40e-3, 40e-3);
        Disconnect("ISEN_PS1600");
        Disconnect_ID("ISEN_PS1600");
        vForce_ID("ISEN_PS1600", 0, 40e-3, 40e-3);
      //  Ramp_Voltage("ITRIP_PS1600",1.5,1.5,150,100e3);


        measurement.setSetups(ds);
    }


    @Override
    public void execute() {
        // TODO Auto-generated method stub
        measurement.execute();
    }

}
