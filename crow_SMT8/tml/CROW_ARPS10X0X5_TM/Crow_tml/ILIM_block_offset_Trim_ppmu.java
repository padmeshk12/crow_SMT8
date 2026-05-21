package CROW_ARPS10X0X5_TM.Crow_tml;

import xoc.dta.TestMethod;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.testdescriptor.IFunctionalTestDescriptor;


public class ILIM_block_offset_Trim_ppmu extends TestMethod {

    public IMeasurement measurement;

    public IFunctionalTestDescriptor ptd_calculated_Offset;



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
