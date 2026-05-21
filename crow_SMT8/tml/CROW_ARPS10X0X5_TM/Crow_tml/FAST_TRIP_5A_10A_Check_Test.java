package CROW_ARPS10X0X5_TM.Crow_tml;

import xoc.dta.TestMethod;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.testdescriptor.IFunctionalTestDescriptor;


public class FAST_TRIP_5A_10A_Check_Test extends TestMethod {

    public IMeasurement measurement;

    public IFunctionalTestDescriptor ptd_Fast_Trip_5A_Check;



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
