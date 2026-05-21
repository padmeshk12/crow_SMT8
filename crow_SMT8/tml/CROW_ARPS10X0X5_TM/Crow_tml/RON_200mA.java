package CROW_ARPS10X0X5_TM.Crow_tml;

import java.util.Map;

import xoc.dsa.DeviceSetupFactory;
import xoc.dsa.IDeviceSetup;
import xoc.dsa.ISetupDcVI;
import xoc.dsa.ISetupDcVI.IIforce;
import xoc.dsa.ISetupDcVI.IVmeas;
import xoc.dsa.ISetupPattern;
import xoc.dsa.ISetupUtility;
import xoc.dta.TestMethod;
import xoc.dta.datatypes.MultiSiteDouble;
import xoc.dta.datatypes.MultiSiteDoubleArray;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.resultaccess.IDcVIResults;
import xoc.dta.testdescriptor.IParametricTestDescriptor;


public class RON_200mA extends TestMethod {

    public IMeasurement measurement;

    public IParametricTestDescriptor ptd_RON_200mAVDD_5_VIN_5_20V_Part;

    private static final double THREE_MS = 3e-3; // 3 milliseconds
    private static final double TEN_MS = 10e-3;  // l10 milliseconds


    @Override
    public void setup() {
        messageLogLevel = 8;

        IDeviceSetup ds = DeviceSetupFactory.createInstance();

        ds.importSpec("configuration.ARPS10X0X5__master_cfg_mfh.Groups");
//        ds.importSpec("mainSpecs.ARPS1020A5_tf_REV02.Spc_Lev_eqn3_spec1_set1_Tim_eqn5_spec1_set1");







        ISetupDcVI dcVI_VDD = ds.addDcVI("VDD");
        ISetupDcVI dcVI_VIN = ds.addDcVI("VIN1+VIN2+VIN3+VIN4_RS1+VIN5_RS1+VIN6");
        ISetupDcVI dcVI_VOUT = ds.addDcVI("VOUT1+VOUT2_RS2+VOUT3_RS2+VOUT4+VOUT5+VOUT6");
        ISetupDcVI dcVI_ISEN_ITRIP = ds.addDcVI("ISEN_PS1600+ITRIP_PS1600+ALL_Digital_Pin_EXP_VBIAS");

        ISetupDcVI dcVI_SS = ds.addDcVI("SS_PS1600")/*.setConnect(true).setDisconnect(true).setConnectModeHighImpedance().setDisconnectModeHiz()*/;
   //     ISetupDigInOut digInOut_SS = ds.addDigInOut("SS_PS1600");
        ISetupDcVI dcVI_EN0_FCB = ds.addDcVI("EN0_PS1600+FCB_PS1600");

        ISetupDcVI Off_PPMU = ds.addDcVI("OFF_PS1600+VBIAS_PS1600");
        Off_PPMU.setDisconnect(true);


        ISetupUtility hwRelay = ds.addUtility( "GND_K9_TGND + VIN6_K28_DIODE_VOUT1");


        dcVI_VDD.level().setVrange(10.0).setIrange(50e-3);
        dcVI_VIN.level().setVrange(8.0).setIrange(50e-3);
        dcVI_VOUT.level().setVrange(10.0).setIrange(50e-3);
        dcVI_ISEN_ITRIP.level().setVrange(2.0).setIrange(40e-3);
        dcVI_SS.level().setVrange(2.0).setIrange(40e-3);
        dcVI_EN0_FCB.level().setVrange(2.0).setIrange(40e-3);

        ISetupDcVI.IVforce vForce_VDD_0V = dcVI_VDD.vforce().setVrange(10.0).setForceValue(0).setIclamp(40e-3).setIrange(50e-3);
        ISetupDcVI.IVforce vForce_VIN_0V = dcVI_VIN.vforce().setVrange(5.0).setForceValue(0).setIclamp(40e-3).setIrange(50e-3);
        ISetupDcVI.IIforce vForce_VOUT_0A = dcVI_VOUT.iforce().setIrange(0.1).setForceValue(0).setVclampLow(-1).setVclampHigh(1).setIrange(50e-3);
        ISetupDcVI.IVforce vForce_ISEN_ITRIP_0V = dcVI_ISEN_ITRIP.vforce().setVrange(3.0).setForceValue(1).setIclamp(40e-3).setIrange(40e-3);
        ISetupDcVI.IVforce vForce_VDD_5V = dcVI_VDD.vforce().setVrange(10.0).setForceValue(5).setIclamp(40e-3).setIrange(50e-3);
        ISetupDcVI.IVforce vForce_VIN_5V = dcVI_VIN.vforce().setVrange(5.0).setForceValue(5).setIclamp(40e-3).setIrange(50e-3);

        ISetupDcVI.IVforce vForce_SS_1_8V = dcVI_SS.vforce().setVrange(5.0).setForceValue(1.8).setIclamp(40e-3).setIrange(40e-3);
        ISetupDcVI.IVforce vForce_EN0_FCB_0V = dcVI_EN0_FCB.vforce().setVrange(2).setForceValue(2).setIclamp(40e-3).setIrange(40e-3);

        IIforce actIF = dcVI_VOUT.iforce()
                .setForceValue(0.033333) // Force 0.0 A
                .setVclampLow(0)
                .setVclampHigh(10);

        IVmeas actVM = dcVI_VOUT.vmeas()
                .setWaitTime(THREE_MS)
                .setAverages(100)/* .setNeg("VIN6+VIN5_RS1+VIN4_RS1+VIN3+VIN2+VIN1").setVdiffRange(5) */;



        ISetupDcVI.IDisconnect disconnect = dcVI_SS.disconnect();


       String signalGroup = String.join("+", context.inspection().initialSpec("configuration.ARPS10X0X5__master_cfg_mfh.Groups").getSignalGroup("SS_PS1600"));

        ISetupPattern pat = ds.createPattern(1, signalGroup);

        pat.addVector("0");
        pat.addVector("1");
        pat.addVector("0");

        ds.sequentialBegin();
        {


            hwRelay.setValue(1);

            ds.waitCall(THREE_MS);


            ds.actionCall(vForce_VDD_0V);
            ds.actionCall(vForce_VIN_0V);
            ds.actionCall(vForce_VOUT_0A);
            ds.actionCall(vForce_ISEN_ITRIP_0V);
            ds.actionCall(Off_PPMU.disconnect());
            ds.actionCall(vForce_VDD_5V);
            ds.actionCall(vForce_VIN_5V);

            ds.actionCall(vForce_SS_1_8V);
            ds.actionCall(vForce_EN0_FCB_0V);



            ds.actionCall(actIF);
            ds.actionCall(actVM);


            ds.actionCall(disconnect);
//            ds.patternCall(pat);


        }
        ds.sequentialEnd();


measurement.setSetups(ds);





    }

    @Override
    public void update ()
    {

        // add code here
    }


    @Override
    public void execute ()
    {

        measurement.execute();

        IDcVIResults results = measurement.dcVI("VOUT1+VOUT2_RS2+VOUT3_RS2+VOUT4+VOUT5+VOUT6").preserveResults();

        Map<String, MultiSiteDoubleArray> voltage = results.vmeas("").getVoltage();


        message(1, voltage.toString());

        if (messageLogLevel >= 5) {
            for (Map.Entry<String, MultiSiteDoubleArray> entry : voltage.entrySet()) {
                String signal = entry.getKey();
                MultiSiteDoubleArray values = entry.getValue();

                for (int action = 0; action < values.length(); action++) {
                    MultiSiteDouble actionValues = values.getElement(action);
                    for (int site : context.getActiveSites()) {
                        String output = String.format(
                            "Action %d, Signal '%s', Site %d, Voltage: %6.4f V",
                            action + 1, signal, site, actionValues.get(site)
                        );
                        message(5, output);
                    }
                }
            }
        }

        double currentForce = -0.2; // -200mA = -0.2A

        MultiSiteDouble ronVolt1 =
                voltage.get("VOUT1").getElement(0);

        MultiSiteDouble ronVolt2 =
                voltage.get("VOUT2_RS2").getElement(0);

        MultiSiteDouble ronVolt3 =
                voltage.get("VOUT3_RS2").getElement(0);

        MultiSiteDouble ronVolt4 =
                voltage.get("VOUT4").getElement(0);

        MultiSiteDouble ronVolt5 =
                voltage.get("VOUT5").getElement(0);

        MultiSiteDouble ronVolt6 =
                voltage.get("VOUT6").getElement(0);


        MultiSiteDouble ron =
                ronVolt1.add(ronVolt2)
                        .add(ronVolt3)
                        .add(ronVolt4)
                        .add(ronVolt5)
                        .add(ronVolt6)
                        .divide(6.0)
                        .divide(-0.2);


        ptd_RON_200mAVDD_5_VIN_5_20V_Part.evaluate(ron, measurement);





        releaseTester();
    }

}
