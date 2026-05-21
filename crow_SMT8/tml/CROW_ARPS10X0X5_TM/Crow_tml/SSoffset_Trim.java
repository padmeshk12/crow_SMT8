package CROW_ARPS10X0X5_TM.Crow_tml;

import xoc.dta.TestMethod;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.testdescriptor.IFunctionalTestDescriptor;


public class SSoffset_Trim extends TestMethod {

    public IMeasurement measurement;

    public IFunctionalTestDescriptor ptd_BEST_Curr_SS_0p04V;



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
