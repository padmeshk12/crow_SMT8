package CROW_ARPS10X0X5_TM;

import xoc.dta.TestMethod;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.testdescriptor.IFunctionalTestDescriptor;


public class FCB_threshold_Test extends TestMethod {

    public IMeasurement measurement;

    public IFunctionalTestDescriptor ptd_FCB_HYSTERISIS;



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
