package Crow_SMT8.test_method;

import java.util.List;
import java.util.Map;

import xoc.dsa.DeviceSetupFactory;
import xoc.dsa.IDeviceSetup.DSAGenerationMode;
import xoc.dta.datatypes.MultiSiteDouble;
import xoc.dta.datatypes.MultiSiteDoubleArray;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.resultaccess.IDcVIResults;
import xoc.dta.testdescriptor.IParametricTestDescriptor;

public class Continuity extends common_Actions {

    private static final String[] pin_grp_dc = {"VOUT1","VOUT2_RS1","VOUT2_RS2","VOUT3_RS1","VOUT3_RS2","VOUT4","VOUT5","VOUT6","VIN1","VIN2","VIN3","VIN4_RS1","VIN4_RS2","VIN5_RS1","VIN5_RS2","VIN6"};
    public IMeasurement measurement;

    public IParametricTestDescriptor Continuity_Digital,Continuity_DC,DC_Mold_Res;

    @Override
    public void setup() {
        messageLogLevel = 8;

        ds = DeviceSetupFactory.createInstance(DSAGenerationMode.GENERATE_IN_FILE);
        ds.importSpec("configuration.ARPS10X0X5__master_cfg_mfh.Groups");

        Utility("GND_K9_TGND + VIN6_K28_DIODE_VOUT1",1);
        Utility("SS_K23_CAP",1);

        vForce("ALL_DC_PINS", 0, 40e-3, 5e-3);
        vForce("VDD", 0, 50e-3, 5e-3);

        for (int i=0;i<pin_grp_dc.length;i++) {
            IFVM(pin_grp_dc[i],1e-3,40e-3,0,10,1,16);
            iForce(pin_grp_dc[i],0,1e-3,0,10);
            vForce(pin_grp_dc[i],0,5e-3,0.5e-3);
        }

        iForce("ALL_DIGITAL_EXP_GND", 0, 1e-3, -1.5,1.5);
        Wait_ms(2);
        iForce("ALL_DC_PINS",0,1e-3,0,10);

        IFVM("ALL_DIGITAL_EXP_GND",-100e-6,40e-3,0,5,1,16);
        iForce("ALL_DIGITAL_EXP_GND", 0, 1e-3, -1.5,1.5);
        vForce("ALL_DIGITAL_EXP_GND", 0, 40e-3, 5e-3);

        for (int i=0;i<pin_grp_dc.length;i++) {
            IFVM(pin_grp_dc[i],-200e-6,40e-3,0,10,1,16);
            iForce(pin_grp_dc[i],0,1e-3,0,10);
            vForce(pin_grp_dc[i],0,5e-3,0.5e-3);
        }

        Utility("GND_K9_TGND",0);
        Utility("GND_K10_PS1600",1);
        Wait_ms(2);
        IFVM("GND_PS1600",-100e-6,40e-3,0,5,1,16);
        iForce("ALL_DIGITAL_EXP_GND", 0, 1e-3, -1.5,1.5);

        List<String> signalList = context.inspection().initialSpec("configuration.ARPS10X0X5__master_cfg_mfh.Groups").resolveSignalExpression("ALL_DIGITAL_EXP_GND");
        println(signalList);

        measurement.setSetups(ds);

    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        measurement.execute();

     // 1. Digital & GND Continuity Datalog
        String voutGroup = "ALL_DIGITAL_EXP_GND+GND_PS1600";
        IDcVIResults results = measurement.dcVI(voutGroup).preserveResults();

        Map<String, MultiSiteDoubleArray> voltage = results.vmeas("").getVoltage();
        message(1, voltage.toString());
        Continuity_Digital.evaluate(voltage,0,measurement);

        List<String> signalList = context.inspection().initialSpec(measurement.getSpecificationName()).resolveSignalExpression(voutGroup);
        for(int site : context.getActiveSites()) {
            for(String signal : signalList) {
              println(signal + " : " + voltage.get(signal).getElement(site, 0) + "V");
          }
        }


     // 2. DC Pins Continuity & Mould Resistance Datalog
        voutGroup = "ALL_DC_PINS";
        results = measurement.dcVI(voutGroup).preserveResults();

        Map<String, MultiSiteDoubleArray> allVoltageResults = results.vmeas("").getVoltage();
        message(5, allVoltageResults.toString());

        Map<String, MultiSiteDouble> firstActionResults = getActionResults(allVoltageResults, 0);
        message(5, firstActionResults.toString());

        // Optimized: helper scales voltages by 10000.0 (V * 1e6 / 100) to compute Mould Resistance
        Map<String, MultiSiteDouble> calculatedRes = scaleResults(firstActionResults, 10000.0);
        message(5, calculatedRes.toString());
        DC_Mold_Res.evaluate(calculatedRes, measurement);

        // Optimized: helper extracts second action results (DC Continuity) with CORRECTED action index 1
        Map<String, MultiSiteDouble> secondActionResults = getActionResults(allVoltageResults, 1);
        message(5, secondActionResults.toString());
        Continuity_DC.evaluate(secondActionResults, measurement);

//        if (messageLogLevel >= 5) {
//            for (Map.Entry<String, MultiSiteDoubleArray> entry : voltage.entrySet()) {
//                String signal = entry.getKey();
//                MultiSiteDoubleArray values = entry.getValue();
//
//                for (int action = 0; action < values.length(); action++) {
//                    MultiSiteDouble actionValues = values.getElement(action);
//                    for (int site : context.getActiveSites()) {
//                        String output = String.format(
//                            "Action %d, Signal '%s', Site %d, Voltage: %6.4f V",
//                            action + 1, signal, site, actionValues.get(site)
//                        );
//                        message(5, output);
//                    }
//                }
//            }
//        }


//        voltage = results.vmeas("").getVoltage();
//
//     //   message(1, voltage.toString());
//
//        if (messageLogLevel >= 5) {
//            for (Map.Entry<String, MultiSiteDoubleArray> entry : voltage.entrySet()) {
//                String signal = entry.getKey();
//                MultiSiteDoubleArray values = entry.getValue();
//
//                for (int action = 0; action < values.length(); action++) {
//                    MultiSiteDouble actionValues = values.getElement(action);
//                    for (int site : context.getActiveSites()) {
//                        String output = String.format(
//                            "Action %d, Signal '%s', Site %d, Voltage: %6.4f V",
//                            action + 1, signal, site, actionValues.get(site)
//                        );
//                        message(5, output);
//                    }
//                }
//            }
//        }


//        MultiSiteDoubleArray voltageArray = allVoltageResults.get("VOUT1");
//        message(5, voltageArray.toString());
//        MultiSiteDouble firstActionResults = voltageArray.getElement(0);
//        message(5, firstActionResults.toString());
//      for (int site : context.getActiveSites()) {
//      double voltageValue = firstActionResults.get(site);
//      message(6, "Site " + site + " - First vmeas action voltage: " + voltageValue + " V");
//  }

//        Map<String, MultiSiteDouble> firstActionResults =
//                allVoltageResults.entrySet().stream()
//                        .collect(Collectors.toMap(
//                                Map.Entry::getKey,
//                                e -> e.getValue().getElement(0)));
//
//        message(5, firstActionResults.toString());
//
//        Map<String, MultiSiteDouble> calculatedRes =
//                firstActionResults.entrySet().stream()
//                        .collect(Collectors.toMap(
//                                Map.Entry::getKey,
//                                e -> e.getValue().multiply(1e6).divide(100)));
//
//        message(5, calculatedRes.toString());
//        DC_Mold_Res.evaluate(calculatedRes,measurement);
//
//
//
//        Map<String, MultiSiteDouble> secondActionResults =
//                allVoltageResults.entrySet().stream()
//                        .collect(Collectors.toMap(
//                                Map.Entry::getKey,
//                                e -> e.getValue().getElement(0)));
//
//        message(5, secondActionResults.toString());
//
//        Continuity_DC.evaluate(secondActionResults,measurement);

    }



}
