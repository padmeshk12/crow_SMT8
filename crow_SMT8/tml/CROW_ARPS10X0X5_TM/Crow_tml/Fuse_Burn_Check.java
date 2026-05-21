package CROW_ARPS10X0X5_TM.Crow_tml;

import test_methods.common;
import xoc.dsa.DeviceSetupFactory;
import xoc.dsa.IDeviceSetup;
import xoc.dsa.ISetupDcVI;
import xoc.dsa.ISetupDigInOut;
import xoc.dsa.ISetupPattern;
import xoc.dsa.ISetupUtility;
import xoc.dta.TestMethod;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.testdescriptor.IFunctionalTestDescriptor;


public class Fuse_Burn_Check extends TestMethod {

    public IMeasurement measurement;

    public IFunctionalTestDescriptor ptd_BANKA_BIT5_BLOWN_TRUE;



    @Override
    public void setup()
    {
        messageLogLevel = 8;

        IDeviceSetup ds = DeviceSetupFactory.createInstance();

        ds.importSpec("mainSpecs.ARPS1020A5_tf_REV02.Spc_Lev_eqn3_spec1_set1_Tim_eqn5_spec1_set1");


        //Pattern setup init seq
        String signalGroup = String.join("+", context.inspection().initialSpec("configuration.ARPS10X0X5__master_cfg_mfh.Groups").getSignalGroup("P_ALL_SPI"));
        ISetupPattern pat = ds.createPattern(1,signalGroup);


        // util setup
        ISetupUtility con_GND = ds.addUtility( "GND_K9_TGND + VIN6_K28_DIODE_VOUT1");
        ISetupUtility SS_cap = ds.addUtility( "SS_K23_CAP");

        // Dcvi setup
        ISetupDigInOut ITRIP_PS1600 = ds.addDigInOut("ITRIP_PS1600");
        ISetupDcVI VDD_VIN1 = ds.addDcVI("VDD+VIN1");
        ISetupDigInOut ISEN_PS1600 = ds.addDigInOut("ISEN_PS1600");
        ISetupDigInOut EN0_FCB = ds.addDigInOut("EN0_PS1600+FCB_PS1600");
        ISetupDigInOut VBIAS_PS1600 = ds.addDigInOut("VBIAS_PS1600");
        ISetupDigInOut OFF_PS1600 = ds.addDigInOut("OFF_PS1600");


        // dcvi level setup
//        ITRIP_PS1600.level().setVil(0).setVih(3).setVol(1).setVoh(2.5);
//        ITRIP_PS1600.timing().setPeriod("10ns").setD1("0ns").setR1("5ns");
//        ISEN_PS1600.level().setVil(0).setVih(3).setVol(1).setVoh(2.5);
//        ISEN_PS1600.timing().setPeriod("10ns").setD1("0ns").setR1("5ns");
//        EN0_FCB.level().setVil(0).setVih(3).setVol(1).setVoh(2.5);
//        EN0_FCB.timing().setPeriod("10ns").setD1("0ns").setR1("5ns");
//        VBIAS_PS1600.level().setVil(0).setVih(3).setVol(1).setVoh(2.5);
//        VBIAS_PS1600.timing().setPeriod("10ns").setD1("0ns").setR1("5ns");
//        OFF_PS1600.level().setVil(0).setVih(3).setVol(1).setVoh(2.5);
//        OFF_PS1600.timing().setPeriod("10ns").setD1("0ns").setR1("5ns");

        VDD_VIN1.level().setVrange(2.0).setIrange(40e-3);

        // dcvi action setup
        ISetupDigInOut.IVforce ITRIP_PS1600_0V = ITRIP_PS1600.vforce().setVrange(3.0).setForceValue(0).setIclamp(40e-3).setIrange(40e-3);
        ISetupDcVI.IVforce VDD_VIN1_5V = VDD_VIN1.vforce().setVrange(6.0).setForceValue(5).setIclamp(40e-3).setIrange(50e-3);
        ISetupDigInOut.IVforce ISEN_PS1600_3_3V = ISEN_PS1600.vforce().setVrange(4.0).setForceValue(3.3).setIclamp(40e-3).setIrange(40e-3);
        ISetupDigInOut.IVforce EN0_FCB_0V = ISEN_PS1600.vforce().setVrange(4.0).setForceValue(0).setIclamp(40e-3).setIrange(40e-3);

        ISetupDigInOut.IIforce VBIAS_0A = VBIAS_PS1600.iforce().setVrange(1.0).setVclampLow(-1).setVclampHigh(1).setForceValue(0).setIrange(40e-3);



       ISetupDigInOut.IIforce OFF_actIF = OFF_PS1600.iforce().setForceValue(0).setVclampLow(0).setVclampHigh(1);
       ISetupDigInOut.IVmeas OFF_actVM = OFF_PS1600.vmeas().setWaitTime(0).setAverages(100);


        ISetupDigInOut.IVforce OFF_Dynamic_V = OFF_PS1600.vforce().setVrange(4.0).setForceValue(0).setIclamp(40e-3).setIrange(40e-3);


        ISetupPattern pat1 = common.createBasicPattern(ds, signalGroup);
        ISetupPattern pat2 = common.createBasicPattern(ds, signalGroup);


        // action call
        con_GND.setValue(1);
        ds.waitCall(3e-3);
        ds.actionCall(ITRIP_PS1600_0V);

        SS_cap.setValue(1);
        ds.actionCall(VDD_VIN1_5V);
        ds.actionCall(ISEN_PS1600_3_3V);
        ds.actionCall(EN0_FCB_0V);
        ds.patternCall(pat1);

        ds.actionCall(VBIAS_0A);
        ds.actionCall(OFF_actIF);
        ds.actionCall(OFF_actVM);
        ds.actionCall(OFF_Dynamic_V);
        ds.patternCall(pat2);

        measurement.setSetups(ds);




    }


    @Override
    public void execute ()
    {

        measurement.execute();
    }

}
