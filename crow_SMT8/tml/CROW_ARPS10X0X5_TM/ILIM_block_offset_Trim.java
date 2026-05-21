package CROW_ARPS10X0X5_TM;

import xoc.dta.TestMethod;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.testdescriptor.IFunctionalTestDescriptor;


public class ILIM_block_offset_Trim extends TestMethod {

    public IMeasurement measurement;

    public IFunctionalTestDescriptor ptd_ILIM_MEAS_50PIMAX_CODE15;



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
