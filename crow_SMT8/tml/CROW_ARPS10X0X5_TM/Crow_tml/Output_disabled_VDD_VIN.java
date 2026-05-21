package CROW_ARPS10X0X5_TM.Crow_tml;

import xoc.dta.TestMethod;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.testdescriptor.IFunctionalTestDescriptor;


public class Output_disabled_VDD_VIN extends TestMethod {

    public IMeasurement measurement;

    public IFunctionalTestDescriptor ptd_DELTA_IOFFVDD0_VIN_50;



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
