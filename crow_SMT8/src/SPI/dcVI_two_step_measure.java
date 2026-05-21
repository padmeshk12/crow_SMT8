package SPI;

import java.util.Map;

import xoc.dsa.DeviceSetupFactory;
import xoc.dsa.IDeviceSetup;
import xoc.dsa.ISetupDcVI;
import xoc.dsa.ISetupDcVI.IIforce;
import xoc.dsa.ISetupDcVI.IVmeas;
import xoc.dta.TestMethod;
import xoc.dta.datatypes.MultiSiteDouble;
import xoc.dta.datatypes.MultiSiteDoubleArray;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.resultaccess.IDcVIResults;



public class dcVI_two_step_measure extends TestMethod {

    public IMeasurement measurement;
//    public IMeasurement measurement1;

    @Override
    public void setup() {
        messageLogLevel = 8;


        IDeviceSetup ds = DeviceSetupFactory.createInstance();
//        IDeviceSetup ds1 = DeviceSetupFactory.createInstance();

        ISetupDcVI dcVI_SS = ds.addDcVI("SS_PS1600");
        dcVI_SS.level().setVrange(2.0).setIrange(40e-3);

//        ISetupDcVI dcVI_OFF = ds.addDcVI("OFF_PS1600").setConnect(true);
//        dcVI_OFF.level().setVrange(2.0).setIrange(40e-3);

        IIforce actIF = dcVI_SS.iforce()
                .setForceValue(0.033333) // Force 0.0 A
                .setVclampLow(0)
                .setVclampHigh(5);

        IVmeas actVM = dcVI_SS.vmeas()
                .setWaitTime(0)
                .setAverages(100);

//        IVforce vforce1 = dcVI_OFF.vforce("vforce_meas").setForceValue(1).setIclamp(40e-3).setIrange(40e-3);

        ds.actionCall(actIF);
        ds.actionCall(actVM);
//        ds.actionCall(vforce1);


        measurement.setSetups(ds);
//        IIforce actIF1 = dcVI_OFF.iforce()
//                .setForceValue(0.033333) // Force 0.0 A
//                .setVclampLow(0)
//                .setVclampHigh(5);
//
//        IVmeas actVM1 = dcVI_OFF.vmeas()
//                .setWaitTime(0)
//                .setAverages(100);
//
//        ds1.actionCall(actIF1);
//        ds1.actionCall(actVM1);
//
//       measurement1.setSetups(ds1);


    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        measurement.execute();

         IDcVIResults results = measurement.dcVI("SS_PS1600").preserveResults();

        Map<String, MultiSiteDoubleArray> voltage = results.vmeas("").getVoltage();
        MultiSiteDouble vmeas = voltage.get("SS_PS1600").getElement(0);
//        measurement.dcVI("OFF_PS1600").vforce("vforce_meas").setForceValue(vmeas).setIclamp(40e-3).setIrange(40e-3);

//        context.spec(measurement.getSpecificationName()).dcVI("OFF_PS1600").setConnect(true).vforce("vforce_meas").setForceValue(vmeas).setIclamp(40e-3).setIrange(40e-3);

        message(1, voltage.toString());


//        measurement1.execute();

    }

}
