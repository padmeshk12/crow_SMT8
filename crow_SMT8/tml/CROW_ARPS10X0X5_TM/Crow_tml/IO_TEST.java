package CROW_ARPS10X0X5_TM.Crow_tml;

import xoc.dta.TestMethod;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.testdescriptor.IFunctionalTestDescriptor;


public class IO_TEST extends TestMethod {

    public IMeasurement measurement;

    public IFunctionalTestDescriptor ptd_OFF_VOLTAGE_HIGH_FCB_1P8V_IO_TM_4;



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
