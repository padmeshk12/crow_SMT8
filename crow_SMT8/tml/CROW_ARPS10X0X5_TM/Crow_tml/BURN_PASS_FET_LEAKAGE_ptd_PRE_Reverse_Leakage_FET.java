package CROW_ARPS10X0X5_TM.Crow_tml;

import xoc.dta.TestMethod;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.testdescriptor.IFunctionalTestDescriptor;


public class BURN_PASS_FET_LEAKAGE_ptd_PRE_Reverse_Leakage_FET extends TestMethod {

    public IMeasurement measurement;

    public IFunctionalTestDescriptor ptd_PRE_Reverse_Leakage_FET;



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
