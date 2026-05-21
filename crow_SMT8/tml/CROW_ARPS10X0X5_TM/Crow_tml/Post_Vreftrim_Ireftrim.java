package CROW_ARPS10X0X5_TM.Crow_tml;

import xoc.dta.TestMethod;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.testdescriptor.IFunctionalTestDescriptor;


public class Post_Vreftrim_Ireftrim extends TestMethod {

    public IMeasurement measurement;

    public IFunctionalTestDescriptor ptd_POST_IBIAS_1U_PP;



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
