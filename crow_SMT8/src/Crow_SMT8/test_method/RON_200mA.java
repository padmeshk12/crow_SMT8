package Crow_SMT8.test_method;

import java.util.Map;

import xoc.dsa.DeviceSetupFactory;
import xoc.dsa.IDeviceSetup.DSAGenerationMode;
import xoc.dta.datatypes.MultiSiteDouble;
import xoc.dta.datatypes.MultiSiteDoubleArray;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.resultaccess.IDcVIResults;
import xoc.dta.testdescriptor.IParametricTestDescriptor;

public class RON_200mA extends common_Actions {

    public IMeasurement measurement;

    private static final String[] VIN_PINS = {"VIN1", "VIN2", "VIN3", "VIN4_RS1", "VIN5_RS1", "VIN6"};//= getPins("ALL_VIN");
    private static final String[] VOUT_PINS = {"VOUT6", "VOUT5", "VOUT4", "VOUT3_RS2", "VOUT2_RS2", "VOUT1"};//= getPins("ALL_VOUT");

    public IParametricTestDescriptor RON_200mA;

    @Override
    public void setup() {
        messageLogLevel = 8;

        ds = DeviceSetupFactory.createInstance(DSAGenerationMode.GENERATE_IN_FILE);
        ds.importSpec("configuration.ARPS10X0X5__master_cfg_mfh.Groups");

        Utility("GND_K9_TGND",1);
        Utility("VIN6_K28_DIODE_VOUT1",1);

        vForce("VDD", 0, 50e-3, 40e-3);
        vForce("VIN1+VIN2+VIN3+VIN4_RS1+VIN5_RS1+VIN6", 0, 50e-3, 40e-3);
        iForce("VOUT1+VOUT2_RS2+VOUT3_RS2+VOUT4+VOUT5+VOUT6", 0, 50e-3, 0, 10);
        vForce("ISEN_PS1600", 0, 40e-3, 40e-3);
        vForce("ITRIP_PS1600", 0, 40e-3, 40e-3);
        vForce("ALL_Digital_Pin_EXP_VBIAS", 0, 40e-3, 40e-3);
        Disconnect("OFF_PS1600+VBIAS_PS1600");
        vForce("VDD", 5, 50e-3, 40e-3);
        vForce("VIN1+VIN2+VIN3+VIN4_RS1+VIN5_RS1+VIN6", 5, 50e-3, 40e-3);
        vForce("SS_PS1600", 1.8, 40e-3, 40e-3);
        vForce("EN0_PS1600+FCB_PS1600", 2, 40e-3, 40e-3);
        Wait_ms(3);

        for (int i=0;i<VOUT_PINS.length;i++) {
              IFVM(VOUT_PINS[i],VIN_PINS[i],-0.033333,50e-3, 0, 10,3,100,2);
        }

        measurement.setSetups(ds);

    }

    @Override
    public void update() {

        // add code here
    }

    @Override
    public void execute() {

        measurement.execute();

        String voutGroup = String.join("+", VOUT_PINS);
        IDcVIResults results = measurement.dcVI(voutGroup).preserveResults();

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

//        MultiSiteDouble ronVolt1 =
//                voltage.get("VOUT1").getElement(0);
//
//        MultiSiteDouble ronVolt2 =
//                voltage.get("VOUT2_RS2").getElement(0);
//
//        MultiSiteDouble ronVolt3 =
//                voltage.get("VOUT3_RS2").getElement(0);
//
//        MultiSiteDouble ronVolt4 =
//                voltage.get("VOUT4").getElement(0);
//
//        MultiSiteDouble ronVolt5 =
//                voltage.get("VOUT5").getElement(0);
//
//        MultiSiteDouble ronVolt6 =
//                voltage.get("VOUT6").getElement(0);
//
//        MultiSiteDouble ron =
//                ronVolt1.add(ronVolt2)
//                        .add(ronVolt3)
//                        .add(ronVolt4)
//                        .add(ronVolt5)
//                        .add(ronVolt6)
//                        .divide(6.0)
//                        .divide(-0.2);

        MultiSiteDouble sumVoltages = null;
        for (String pin : VOUT_PINS) {
            MultiSiteDouble v = voltage.get(pin).getElement(0);
            if (sumVoltages == null) {
                sumVoltages = v;
            } else {
                sumVoltages = sumVoltages.add(v);
            }
        }

        MultiSiteDouble ron = sumVoltages.divide(6.0).divide(-0.2);

        RON_200mA.evaluate(ron, measurement);
    }

}
