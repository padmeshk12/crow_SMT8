package CROW_ARPS10X0X5_TM;

import xoc.dta.TestMethod;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.testdescriptor.IFunctionalTestDescriptor;


public class FAULTB_VOL_VOH extends TestMethod {

    public IMeasurement measurement;

    public IFunctionalTestDescriptor ptd_FAULTB_VOH_LEVEL;



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
