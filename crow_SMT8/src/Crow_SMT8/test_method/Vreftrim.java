package Crow_SMT8.test_method;

import java.util.Map;

import CROW_ARPS10X0X5_TM.lib_common.Global;
import CROW_ARPS10X0X5_TM.lib_common.NVM_register_bank;
import CROW_ARPS10X0X5_TM.lib_common.SPI_Protocol;
import CROW_ARPS10X0X5_TM.lib_common.register_bank;
import xoc.dsa.DeviceSetupFactory;
import xoc.dta.datatypes.MultiSiteDouble;
import xoc.dta.datatypes.MultiSiteDoubleArray;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.resultaccess.IDigInOutActionResults;
import xoc.dta.testdescriptor.IParametricTestDescriptor;

public class Vreftrim extends common_Actions {

    public IMeasurement measurement;
    public IParametricTestDescriptor VBIAS_Value,VREF_BEST_TRIM_CODE;
    private static final String SPI_SETUP_SPEC =
            "mainSpecs.ARPS1020A5_tf_REV02.Spc_Lev_eqn3_spec1_set1_Tim_eqn5_spec1_set3";
    @Override
    public void setup() {
        messageLogLevel = 8;

        Global.defineSpiInterface();
        ds = DeviceSetupFactory.createInstance();
        ds.importSpec("configuration.ARPS10X0X5__master_cfg_mfh.Groups");
        ds.importSpec(SPI_SETUP_SPEC);
        Global.setup(ds, measurement);

        Utility("GND_K9_TGND,ISEN_K30_PS1600_DPS128",1);
        Wait_ms(3);
        vForce_ID("ITRIP_PS1600", 0, 40e-3, 40e-3);
        Disconnect_ID("OFF_PS1600");
        Utility("SS_K23_CAP",1);
        Wait_ms(3);
        vForce("VDD+VIN1", 28, 100e-3, 100e-3);
        vForce_ID("ISEN_PS1600", 3.3, 40e-3, 40e-3);
        vForce_ID("EN0_PS1600+FCB_PS1600", 0, 10e-3, 10e-3);

        register_bank.setField("TM_ACTIVE_0", 1);
        register_bank.setField("TM_ACTIVE_1", 1);
        SPI_Protocol.spiWrite("MISO_READ_DATA");

        NVM_register_bank.registersReset();
        NVM_register_bank.setField("SRTCTrim", 0);

        for(int code=0;code<32;code++)
        {
            NVM_register_bank.setField("VrefTrim", code);
            SPI_Protocol.spiWriteNvm("1");
            IFVM_ID("VBIAS_PS1600",0,40e-3,0,5,1,16);
            NVM_register_bank.setField("VrefTrim", 31);
            SPI_Protocol.spiWriteNvmClearMem("1");
        }

        measurement.setSetups(ds);

    }

    @Override
    public void execute() {

        measurement.execute();

   //     IDcVIResults results = measurement.dcVI("VBIAS_PS1600").preserveResults();
        IDigInOutActionResults results = measurement.digInOut("VBIAS_PS1600").preserveActionResults();

        Map<String, MultiSiteDoubleArray> allVoltageResults = results.vmeas("").getVoltage();
        message(1, allVoltageResults.toString());

        Map<String, MultiSiteDouble> firstActionResults = getActionResults(allVoltageResults, 0);
        message(1, firstActionResults.toString());

       MultiSiteDoubleArray allVoltageResults1 = results.vmeas("").getVoltage("VBIAS_PS1600");
       message(1, allVoltageResults1.toString());

       MultiSiteDouble minDiff = allVoltageResults1.getElement(0).subtract(1.8).abs();
        println("min_diff: "+minDiff);

       double target_vbias =1.8;
       MultiSiteDouble closestTrimCode = new MultiSiteDouble(0.0);

       for(int code=0;code<32;code++)
       {
           MultiSiteDouble Vbias_value = allVoltageResults1.getElement(code);
           MultiSiteDouble diff = Vbias_value.subtract(target_vbias).abs();
           message(1, "Code: " + code + "Vbias_value "+ Vbias_value + "Diff = " + diff);

           for (int site : context.getActiveSites()) {
               double diffVal = diff.get(site);

               if (diffVal < minDiff.get(site)) {
                   minDiff.set(site, diffVal);
                   closestTrimCode.set(site, code);
               }
           }
           String qualifiedName = "code_" + code;

           VBIAS_Value.evaluate(Vbias_value,measurement);
           VBIAS_Value.setTestName(qualifiedName).setTestNumber(code).setTestText(qualifiedName);


       }

       for (int site : context.getActiveSites()) {
           message(1, "Site " + site + " -> Best trim code: " + closestTrimCode.get(site));
       }

       VREF_BEST_TRIM_CODE.evaluate(closestTrimCode, measurement);



    }


}

