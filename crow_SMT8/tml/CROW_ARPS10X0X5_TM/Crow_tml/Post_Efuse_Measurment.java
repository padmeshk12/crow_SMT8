package CROW_ARPS10X0X5_TM.Crow_tml;

import xoc.dta.TestMethod;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.testdescriptor.IFunctionalTestDescriptor;


public class Post_Efuse_Measurment extends TestMethod {

    public IMeasurement measurement;

    public IFunctionalTestDescriptor ptd_POST_BANKB_EFUSE_RESISTANCE_BLOWN_BIT0;



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
