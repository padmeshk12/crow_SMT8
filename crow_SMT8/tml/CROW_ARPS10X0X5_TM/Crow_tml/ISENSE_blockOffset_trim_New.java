package CROW_ARPS10X0X5_TM.Crow_tml;

import xoc.dta.TestMethod;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.testdescriptor.IFunctionalTestDescriptor;


public class ISENSE_blockOffset_trim_New extends TestMethod {

    public IMeasurement measurement;

    public IFunctionalTestDescriptor ptd_BEST_MEAS_10mAIOUT;



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
