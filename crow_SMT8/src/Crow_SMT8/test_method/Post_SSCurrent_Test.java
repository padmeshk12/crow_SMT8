package Crow_SMT8.test_method;

import xoc.dsa.DeviceSetupFactory;
import xoc.dsa.IDeviceSetup.DSAGenerationMode;
import xoc.dsa.ISetupDcVI;
import xoc.dta.measurement.IMeasurement;

public class Post_SSCurrent_Test extends common_Actions {

    public IMeasurement measurement;

    @Override
    public void setup() {


        messageLogLevel = 8;

        ds = DeviceSetupFactory.createInstance(DSAGenerationMode.GENERATE_IN_FILE);
        ds.importSpec("configuration.ARPS10X0X5__master_cfg_mfh.Groups");

        vForce("EN0_PS1600,FCB_PS1600", 0, 40e-3, 10e-3);
        Utility("GND_K9_TGND,ISEN_K30_PS1600_DPS128",1);
        Wait_ms(3);
        vForce("ITRIP_PS1600", 0, 40e-3, 10e-3);
        vForce("ISEN_PS1600", 0, 40e-3, 10e-3);
        Disconnect("OFF_PS1600");
        Utility("SS_K23_CAP",0);
        vForce("SS_PS1600", 0, 40e-3, 40e-3);
        Global.devVariantInit();

        vForce("VDD", Global.VCC, 100e-3, 100e-3);
        vForce("VIN1", 7.5/*Global.VCC*/, 100e-3, 100e-3,true,"VOUT6");
        vForce("ISEN_PS1600", 3.3, 40e-3, 10e-3);
        vForce("EN0_PS1600,FCB_PS1600", 5, 40e-3, 10e-3);
        VFIM("SS_PS1600",0.4,100e-6,100e-6,10,150);

        vForce("ALL_VOUT", 1, 200e-3, 200e-3);
        ds.parallelBegin();
        {
            vForce("VIN1", 0, 200e-3, 200e-3,true,"VOUT6");
            vForce("VIN2", 0, 200e-3, 200e-3,true,"VOUT5");
            vForce("VIN3", 0, 200e-3, 200e-3,true,"VOUT4");
            vForce("VIN4_RS2", 0, 200e-3, 200e-3,true,"VOUT3_RS1");
            vForce("VIN5_RS2", 0, 200e-3, 200e-3,true,"VOUT2_RS1");
            vForce("VIN6", 0, 200e-3, 200e-3,true,"VOUT1");
        }
        ds.parallelEnd();
        Disconnect("VIN1");

        vForce("SS_PS1600", 0.4, 40e-3, 40e-3);

        String pinGroup = "VIN1";
        ISetupDcVI dcvi = ds.addDcVI(pinGroup).setConfigOptionHighCurrent().setConnect(true);

        ISetupDcVI.IVforce pulseHigh = dcvi.vforce("Pulse_High")
                .setForceValue(4.0)
                .setVrange(8.0)
                .setIclamp(100e-3)
                .setIrange(1.0);
            // Step 2: Measure current during the pulse
            ISetupDcVI.IImeas measAction = dcvi.imeas("IMeas_During_Pulse")
                .setAverages(30)
                .setWaitTime(500e-6);       // 500 usec settling time before measurement
            // Step 3: Force the BASE voltage back (0V)
            ISetupDcVI.IVforce pulseBase = dcvi.vforce("Pulse_Base")
                .setForceValue(0.0)
                .setVrange(8.0)
                .setIclamp(100e-3)
                .setIrange(1.0);
            // Queue in sequence: Force 4V ? Wait 1ms ? Measure ? Force 0V
            ds.actionCall(pulseHigh);
            ds.waitCall(1000e-6);           // 1000 usec pulse duration
            ds.actionCall(measAction);
            ds.actionCall(pulseBase);

        measurement.setSetups(ds);

    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub

        measurement.execute();


    }



}
