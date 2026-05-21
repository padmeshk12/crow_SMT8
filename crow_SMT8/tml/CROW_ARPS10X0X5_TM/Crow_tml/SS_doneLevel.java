package CROW_ARPS10X0X5_TM.Crow_tml;

import xoc.dta.TestMethod;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.testdescriptor.IFunctionalTestDescriptor;


public class SS_doneLevel extends TestMethod {

    public IMeasurement measurement;

    public IFunctionalTestDescriptor ptd_Post_threshold_DRVTM_12_switchoff_OFFPIN_Voltage;



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
