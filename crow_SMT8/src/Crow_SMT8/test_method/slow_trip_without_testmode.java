package Crow_SMT8.test_method;

import java.util.Map;

import xoc.dsa.DeviceSetupFactory;
import xoc.dsa.IDeviceSetup.DSAGenerationMode;
import xoc.dta.datatypes.dsp.MultiSiteWaveDouble;
import xoc.dta.datatypes.dsp.MultiSiteWaveDoubleArray;
import xoc.dta.datatypes.dsp.WaveDouble;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.resultaccess.IDcVIResults;

public class slow_trip_without_testmode extends common_Actions {

    public IMeasurement measurement;


    @Override
    public void setup() {
        messageLogLevel = 8;

        ds = DeviceSetupFactory.createInstance(DSAGenerationMode.GENERATE_IN_FILE);
        ds.importSpec("configuration.ARPS10X0X5__master_cfg_mfh.Groups");

        Utility("GND_K9_TGND,ISEN_K30_PS1600_DPS128",1);
        Wait_ms(3);
        vForce("ITRIP_PS1600", 0, 40e-3, 40e-3);
        Disconnect("OFF_PS1600");
        Utility("SS_K23_CAP",1);
        Wait_ms(3);
        vForce("VDD", 28, 100e-3, 100e-3);
        vForce("ALL_VIN", 28, 100e-3, 100e-3);
        iForce("VOUT1+VOUT2_RS2+VOUT3_RS2+VOUT4+VOUT5+VOUT6", 0, 200e-3, -10, 10);
        vForce("ISEN_PS1600", 3.3, 40e-3, 40e-3);
        vForce("EN0_PS1600+FCB_PS1600", 0, 10e-3, 10e-3);
        vForce("EN0_PS1600+FCB_PS1600", 2, 10e-3, 10e-3);
        iForce("OFF_PS1600", 0, 10e-3, 0, 5);
        iForce("VOUT1+VOUT2_RS2+VOUT3_RS2+VOUT4+VOUT5+VOUT6", 100*0.125e-3, 200e-3, -10, 10);
        Ramp_Voltage("ITRIP_PS1600",0.9,1.2,300,10e3);

        iFVMW("OFF_PS1600", 0, 40e-3, 0, 5,300,10e3,"ID");


        ds.parallelBegin();
        {



            ds.sequentialBegin();
            {
                iForce("OFF_PS1600", 0, 10e-3, 0, 5);
            }
            ds.sequentialEnd();
            ds.sequentialBegin();
            {

                vForce("ISEN_PS1600", 3.3, 40e-3, 40e-3);
            }
            ds.sequentialEnd();
        }
        ds.parallelEnd();

        measurement.setSetups(ds);




    }


    @Override
    public void execute() {
        // TODO Auto-generated method stub
        measurement.execute();
        IDcVIResults results = measurement.dcVI("OFF_PS1600").preserveResults();
        IDcVIResults.IMeasureWaveformResults measResults = results.measureWaveform("ID");
        MultiSiteWaveDoubleArray waveforms = measResults.getWaveform("OFF_PS1600");

        message(1, waveforms.toString());

        Map<String, MultiSiteWaveDoubleArray> waveformMap = results.measureWaveform("").getWaveform();
        MultiSiteWaveDoubleArray source = waveformMap.get("OFF_PS1600");
        message(1, source.toString());

        WaveDouble[] measuredVoltagesdc = source.get(1);
        message(1, measuredVoltagesdc.toString());
  MultiSiteWaveDouble[] elements = source.getElements();
  message(1, elements.toString());



    }



}
