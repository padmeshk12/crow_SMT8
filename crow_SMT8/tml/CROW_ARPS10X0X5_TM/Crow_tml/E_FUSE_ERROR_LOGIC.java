package CROW_ARPS10X0X5_TM.Crow_tml;

import xoc.dta.TestMethod;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.testdescriptor.IFunctionalTestDescriptor;


public class E_FUSE_ERROR_LOGIC extends TestMethod {

    public IMeasurement measurement;

    public IFunctionalTestDescriptor ptd_DICE_PARITY1_OFF_PAD_0V_EN;



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
