package CROW_ARPS10X0X5_TM;

import xoc.dta.TestMethod;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.testdescriptor.IFunctionalTestDescriptor;


public class Nominal_Input_leakage extends TestMethod {

    public IMeasurement measurement;

    public IFunctionalTestDescriptor ptd_DELTA_OFF_PS1600_Leakage_2V;



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
