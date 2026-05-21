package CROW_ARPS10X0X5_TM.Crow_tml;

import xoc.dta.TestMethod;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.testdescriptor.IFunctionalTestDescriptor;


public class UVLO_OTS_POR_PORsu_TMode extends TestMethod {

    public IMeasurement measurement;

    public IFunctionalTestDescriptor ptd_Low_Temp_Voltage_bank1_reset;



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
