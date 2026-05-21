package CROW_ARPS10X0X5_TM;

import xoc.dta.TestMethod;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.testdescriptor.IFunctionalTestDescriptor;


public class Leakage_Test extends TestMethod {

    public IMeasurement measurement;

    public IFunctionalTestDescriptor ptd_DELTA_IOFF_REVERSE_VOUT_VIN_LEAKAGE_100uA;



    @Override
    public void update ()
    {

        // add code here
    }


    @Override
    public void execute ()
    {

        measurement.execute();
    }

}
