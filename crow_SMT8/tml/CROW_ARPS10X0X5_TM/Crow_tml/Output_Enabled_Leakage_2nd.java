package CROW_ARPS10X0X5_TM.Crow_tml;

import xoc.dta.TestMethod;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.testdescriptor.IFunctionalTestDescriptor;


public class Output_Enabled_Leakage_2nd extends TestMethod {

    public IMeasurement measurement;

    public IFunctionalTestDescriptor ptd_LOAD_CURRENT_READBACK_VDD_50V_VIN_50V_LOAD_8A;



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
