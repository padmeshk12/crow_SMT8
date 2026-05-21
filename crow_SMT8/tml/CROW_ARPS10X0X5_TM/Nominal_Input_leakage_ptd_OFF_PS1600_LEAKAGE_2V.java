package CROW_ARPS10X0X5_TM;

import xoc.dta.TestMethod;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.testdescriptor.IFunctionalTestDescriptor;


public class Nominal_Input_leakage_ptd_OFF_PS1600_LEAKAGE_2V extends TestMethod {

    public IMeasurement measurement;

    public IFunctionalTestDescriptor ptd_OFF_PS1600_LEAKAGE_2V;



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
