package SPI;

import xoc.dsa.DeviceSetupFactory;
import xoc.dsa.IDeviceSetup;
import xoc.dta.TestMethod;
import xoc.dta.measurement.IMeasurement;

public class PS1600_static_awg extends TestMethod {

    public IMeasurement measurement;

    @Override
    public void setup() {
        // TODO Auto-generated method stub
        messageLogLevel = 8;

        IDeviceSetup ds = DeviceSetupFactory.createInstance();

        measurement.setSetups(ds);

    }


    @Override
    public void execute() {
        // TODO Auto-generated method stub
        measurement.execute(); //added



    }

}
