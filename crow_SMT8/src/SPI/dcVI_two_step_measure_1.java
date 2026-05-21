package SPI;

import java.util.Map;

import xoc.dsa.DeviceSetupFactory;
import xoc.dsa.IDeviceSetup;
import xoc.dsa.IDeviceSetup.DSAGenerationMode;
import xoc.dsa.ISetupDcVI;
import xoc.dsa.ISetupDcVI.IIforce;
import xoc.dsa.ISetupDcVI.IImeas;
import xoc.dsa.ISetupDcVI.IVforce;
import xoc.dsa.ISetupDcVI.IVmeas;
import xoc.dta.TestMethod;
import xoc.dta.datatypes.MultiSiteDoubleArray;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.resultaccess.IDcVIResults;


public class dcVI_two_step_measure_1 extends TestMethod {

    public IMeasurement measurement;
    public IMeasurement measurement1;

    @Override
    public void setup() {
        messageLogLevel = 8;


        IDeviceSetup ds = DeviceSetupFactory.createInstance(DSAGenerationMode.GENERATE_IN_FILE);
       IDeviceSetup ds1 = DeviceSetupFactory.createInstance();

        ISetupDcVI dcVI_SS = ds.addDcVI("SS_PS1600");
        dcVI_SS.level().setVrange(2.0).setIrange(40e-3);

        IIforce actIF = dcVI_SS.iforce()
                .setForceValue(0.033333) // Force 0.0 A
                .setVclampLow(0)
                .setVclampHigh(5);

        IVmeas actVM = dcVI_SS.vmeas()
                .setWaitTime(0)
                .setAverages(100);

        ds.actionCall(actIF);
        ds.actionCall(actVM);

        measurement.setSetups(ds);

      ISetupDcVI dcVI_OFF = ds1.addDcVI("OFF_PS1600");
      dcVI_OFF.level().setVrange(2.0).setIrange(40e-3);

      IVforce actVF1 = dcVI_OFF.vforce("actVF1")
              .setForceValue(3.0).setIclamp(0.0040);   // Force 0.0 A
//              .setVclampLow(0)
//              .setVclampHigh(5);

      IImeas actVM1 = dcVI_OFF.imeas("actVM1")
              .setWaitTime(0)
              .setAverages(100).setIrange(0.0040);

      ds1.actionCall(actVF1);
      ds1.actionCall(actVM1);

     measurement1.setSetups(ds1);


    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        measurement.execute();

         IDcVIResults results = measurement.dcVI("SS_PS1600").preserveResults();

        Map<String, MultiSiteDoubleArray> voltage = results.vmeas("").getVoltage();
        message(1, voltage.toString());
        // retrive result and store in variable
        MultiSiteDoubleArray voltageResults = results.vmeas("").getVoltage("SS_PS1600");

     // Retrieve the voltage value for site 1 as a Double
     Double voltageSite1 = 5.0;//voltageResults.getElement(1,0);

       measurement1.dcVI("OFF_PS1600").vforce("actVF1").setForceValue(voltageSite1);
//       measurement1.dcVI(PIN_B).vforce(ACT_VF).setForceValue(measuredVoltage);

        measurement1.execute();

    }

}
