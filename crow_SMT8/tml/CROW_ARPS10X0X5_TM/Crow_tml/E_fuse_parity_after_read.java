package CROW_ARPS10X0X5_TM.Crow_tml;

import xoc.dta.TestMethod;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.testdescriptor.IFunctionalTestDescriptor;


public class E_fuse_parity_after_read extends TestMethod {

    public IMeasurement measurement;

    public IFunctionalTestDescriptor ptd_Expect_1_Final_bit_47;



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
