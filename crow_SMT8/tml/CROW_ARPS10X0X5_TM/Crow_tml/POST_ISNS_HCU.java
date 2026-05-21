package CROW_ARPS10X0X5_TM.Crow_tml;

import xoc.dta.TestMethod;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.testdescriptor.IFunctionalTestDescriptor;


public class POST_ISNS_HCU extends TestMethod {

    public IMeasurement measurement;

    public IFunctionalTestDescriptor ptd_Forced_Current_20V_Part;



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
