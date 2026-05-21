package SPI;

import java.util.Map;

import xoc.dsa.DeviceSetupFactory;
import xoc.dsa.IDeviceSetup;
import xoc.dsa.IDeviceSetup.DSAGenerationMode;
import xoc.dsa.ISetupDcVI;
import xoc.dsa.ISetupDcVI.IMeasureWaveform;
import xoc.dsa.ISetupUtility;
import xoc.dsa.ISetupWaveformRamp;
import xoc.dta.TestMethod;
import xoc.dta.datatypes.dsp.MultiSiteWaveDouble;
import xoc.dta.datatypes.dsp.MultiSiteWaveDoubleArray;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.resultaccess.IDcVIResults;

public class UVLO extends TestMethod {

    public IMeasurement measurement;

    @Override
    public void setup() {
        messageLogLevel = 8;

        IDeviceSetup ds = DeviceSetupFactory.createInstance(DSAGenerationMode.GENERATE_IN_FILE);




        ISetupDcVI dcVI_ITRIP = ds.addDcVI("ITRIP_PS1600").setConnect(true);
        ISetupDcVI dcVI_ISEN = ds.addDcVI("ISEN_PS1600").setConnect(true);
        ISetupDcVI dcVI_VIN1 = ds.addDcVI("VIN1").setConnect(true);
        ISetupDcVI dcVI_EN0_FCB = ds.addDcVI("EN0_PS1600+FCB_PS1600");
        ISetupDcVI dcVI_VDD = ds.addDcVI("VDD").setConnect(true);
        ISetupDcVI Off_PPMU_Dis = ds.addDcVI("OFF_PS1600").setDisconnect(true);
        ISetupDcVI Off_PPMU = ds.addDcVI("OFF_PS1600").setDisconnect(true);
        ISetupDcVI dcVI_VOUT4 = ds.addDcVI("VOUT4").setConnect(true);

        ISetupUtility hwRelay = ds.addUtility( "GND_K9_TGND + VIN6_K28_DIODE_VOUT1");
        ISetupUtility SS_K23_CAP = ds.addUtility( "SS_K23_CAP");


        ISetupDcVI.IVforce vForce_ITRIP_0V = dcVI_ITRIP.vforce("vForce_ITRIP_0V").setVrange(3.0).setForceValue(0).setIclamp(40e-3).setIrange(40e-3);
        ISetupDcVI.IVforce vForce_ISEN_0V = dcVI_ISEN.vforce("vForce_ISEN_0V").setVrange(3.0).setForceValue(0).setIclamp(40e-3).setIrange(40e-3);
        ISetupDcVI.IVforce vForce_EN0_FCB_0V = dcVI_EN0_FCB.vforce("vForce_EN0_FCB_0V").setVrange(2).setForceValue(0).setIclamp(40e-3).setIrange(40e-3);
        ISetupDcVI.IVforce vForce_VIN1_0V = dcVI_VIN1.vforce("vForce_VIN1_0V").setVrange(5.0).setForceValue(0).setIclamp(40e-3).setIrange(50e-3);
        ISetupDcVI.IVforce vForce_VDD_0V = dcVI_VDD.vforce("vForce_VDD_0V").setVrange(10.0).setForceValue(0).setIclamp(40e-3).setIrange(50e-3);
        ISetupDcVI.IVforce vForce_VIN1_5V = dcVI_VIN1.vforce("vForce_VIN1_5V").setVrange(5.0).setForceValue(5).setIclamp(40e-3).setIrange(50e-3);
        ISetupDcVI.IIforce vForce_VOUT4_0A = dcVI_VOUT4.iforce("vForce_VOUT4_0A").setIrange(0.1).setForceValue(0).setVclampLow(-1).setVclampHigh(1).setIrange(50e-3);
        ISetupDcVI.IVforce vForce_EN0_FCB_3V = dcVI_EN0_FCB.vforce("vForce_EN0_FCB_3V").setVrange(4).setForceValue(3).setIclamp(40e-3).setIrange(40e-3);


        ISetupWaveformRamp Ramp_Rise_VIN_VDD_2p5 = ds.addWaveformRamp().setStartValue(0).setStopValue(2.9).setSamples(500).setSampleRate(1500);
        ISetupDcVI.ISourceWaveform Ramp_Rise_VIN_VDD_2p5_source = dcVI_VDD.sourceWaveform().setForceTypeVoltage().setWaveform(Ramp_Rise_VIN_VDD_2p5).setIclamp(0.2);

        ISetupWaveformRamp Ramp_Fall_VIN_VDD_2p5 = ds.addWaveformRamp().setStartValue(2.9).setStopValue(0).setSamples(500).setSampleRate(1500);
        ISetupDcVI.ISourceWaveform Ramp_Fall_VIN_VDD_2p5_source = dcVI_VDD.sourceWaveform().setForceTypeVoltage().setWaveform(Ramp_Fall_VIN_VDD_2p5).setIclamp(0.2);

        ISetupDcVI.IIforce Off_PPMU_0A = Off_PPMU.iforce().setForceValue(0).setVclampLow(-1).setVclampHigh(1);
        IMeasureWaveform measureWaveform = Off_PPMU.measureWaveform("Off_PPMU_MEAS_ACTION");
        measureWaveform.setHighAccuracy(true).setSampleRate(10).setSamples(128).setMeasTypeVoltage();

        ISetupDcVI.IIforce Vout4_0A = dcVI_VOUT4.iforce().setForceValue(0).setVclampLow(-1).setVclampHigh(1);
        IMeasureWaveform measureWaveform_Vout4 = dcVI_VOUT4.measureWaveform("VOUT4_MEAS_ACTION");
        measureWaveform_Vout4.setHighAccuracy(true).setSampleRate(10).setSamples(128).setMeasTypeVoltage();


        hwRelay.setValue(1);
        ds.actionCall(vForce_ITRIP_0V);
        ds.actionCall(vForce_ISEN_0V);
        ds.actionCall(Off_PPMU_Dis.disconnect());
        ds.actionCall(vForce_VIN1_0V);
        ds.actionCall(vForce_EN0_FCB_0V);
        SS_K23_CAP.setValue(1);
        ds.actionCall(vForce_VDD_0V);
        ds.actionCall(vForce_VIN1_5V);
        ds.actionCall(vForce_VOUT4_0A);
        ds.actionCall(vForce_EN0_FCB_3V);

        ds.parallelBegin();
        {

            ds.actionCall(Ramp_Rise_VIN_VDD_2p5_source);
        //    ds.waitCall(0.001);

            ds.sequentialBegin();
            {
                ds.actionCall(Off_PPMU_0A);
                ds.actionCall(measureWaveform);
            }
            ds.sequentialEnd();
            ds.sequentialBegin();
            {

                ds.actionCall(Vout4_0A);
                ds.actionCall(measureWaveform_Vout4);
            }
            ds.sequentialEnd();
        }
        ds.parallelEnd();

        ds.actionCall(Ramp_Fall_VIN_VDD_2p5_source);

        ds.actionCall(Off_PPMU_0A);
        ds.actionCall(measureWaveform);

        ds.actionCall(Vout4_0A);
        ds.actionCall(measureWaveform_Vout4);


        measurement.setSetups(ds);


    }



    @Override
    public void execute() {
        // TODO Auto-generated method stub

        measurement.execute();

        IDcVIResults results = measurement.dcVI("OFF_PS1600+VOUT4").preserveResults();
        IDcVIResults VOUT4 = measurement.dcVI("VOUT4").preserveResults();


        IDcVIResults.IMeasureWaveformResults measResults = results.measureWaveform("Off_PPMU_MEAS_ACTION");
        MultiSiteWaveDoubleArray waveforms = measResults.getWaveform("OFF_PS1600");
        message(1, waveforms.toString());

        IDcVIResults.IMeasureWaveformResults measResults1 = VOUT4.measureWaveform("VOUT4_MEAS_ACTION");
        MultiSiteWaveDoubleArray waveforms1 = measResults1.getWaveform("VOUT4");
        message(1, waveforms1.toString());

        Map<String, MultiSiteWaveDoubleArray> allWaveforms = results.measureWaveform("").getWaveform();

     // Iterate over all actions and signals
     for (Map.Entry<String, MultiSiteWaveDoubleArray> entry : allWaveforms.entrySet()) {

         String signalName = entry.getKey();
         println("signalName: "+signalName);

         MultiSiteWaveDoubleArray waveArray = entry.getValue();
         for (int site = 0; site < waveArray.getElements().length; site++) {
             println("Action: "+(site+1));
             MultiSiteWaveDouble siteWave = waveArray.getElements()[site];

             println("Array: "+siteWave);
             if (siteWave != null) {
                 siteWave.plot("All_Waveforms_" + signalName + "_Site_" + site);
             }
         }
     }

//        for (int site : context.getActiveSites()) {
//            MultiSiteWaveDouble waveform = waveforms.get(site);
//            if (waveform != null) {
//                waveform.plot("Measured Voltage Waveform - Site " + site);
//            }
//        }

//      for (Map.Entry<String, MultiSiteWaveDoubleArray> entry : waveforms.entrySet()) {
//      String signalName = entry.getKey();
//      MultiSiteWaveDoubleArray waveArray = entry.getValue();
//      for (int i = 0; i < waveArray.getElements().length; i++) {
//          MultiSiteWaveDouble wave = waveArray.getElements()[i];
//          wave.plot(signalName + "_voltage_ramp_" + i);
//      }

    }

}
