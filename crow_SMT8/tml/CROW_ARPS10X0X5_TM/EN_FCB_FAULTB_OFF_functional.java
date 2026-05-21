package CROW_ARPS10X0X5_TM;

import xoc.dta.TestMethod;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.testdescriptor.IFunctionalTestDescriptor;


public class EN_FCB_FAULTB_OFF_functional extends TestMethod {

    public IMeasurement measurement;

    public IFunctionalTestDescriptor ptd_FAULT_RESET_OFF_LOW;



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
