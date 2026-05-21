package SPI;

import java.util.Map;

import xoc.dsa.DeviceSetupFactory;
import xoc.dsa.IDeviceSetup;
import xoc.dsa.ISetupDcVI;
import xoc.dsa.ISetupDcVI.IIforce;
import xoc.dsa.ISetupDcVI.IVmeas;
import xoc.dsa.ISetupUtility;
import xoc.dta.TestMethod;
import xoc.dta.datatypes.MultiSiteDoubleArray;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.resultaccess.IDcVIResults;

public class conti extends TestMethod {

    public IMeasurement measurement;

    @Override
    public void setup() {
        messageLogLevel = 8;

        IDeviceSetup ds = DeviceSetupFactory.createInstance();
        ds.importSpec("configuration.ARPS10X0X5__master_cfg_mfh.Groups");

        ISetupUtility hwRelay = ds.addUtility( "GND_K9_TGND + VIN6_K28_DIODE_VOUT1");
        ISetupUtility SS_K23_CAP = ds.addUtility( "SS_K23_CAP");

        ISetupDcVI ALL_DC_PINS = ds.addDcVI("ALL_DC_PINS").setConnect(true);
        ISetupDcVI dcVI_VDD = ds.addDcVI("VDD").setConnect(true);
        ISetupDcVI ALL_DIGITAL_EXP_GND = ds.addDcVI("ALL_DIGITAL_EXP_GND").setConnect(true);

        ISetupDcVI.IVforce ALL_DC_PINS_0V = ALL_DC_PINS.vforce("vForce_VDD_0V").setVrange(10.0).setForceValue(0).setIclamp(40e-3).setIrange(50e-3);
        ISetupDcVI.IVforce vForce_VDD_0V = dcVI_VDD.vforce("vForce_VDD_0V").setVrange(10.0).setForceValue(0).setIclamp(40e-3).setIrange(50e-3);
        ISetupDcVI.IIforce ALL_DIGITAL_EXP_GND_0A = ALL_DIGITAL_EXP_GND.iforce().setIrange(0.025).setForceValue(0).setVclampLow(-1).setVclampHigh(1).setIrange(40e-3);
        ISetupDcVI.IIforce ALL_DC_PINS_0A = ALL_DC_PINS.iforce().setIrange(0.025).setForceValue(0).setVclampLow(-1).setVclampHigh(1).setIrange(40e-3);


        IIforce actIF = ALL_DIGITAL_EXP_GND.iforce()
                .setForceValue(-100e-6) // Force 0.0 A
                .setVclampLow(0)
                .setVclampHigh(5);

        IVmeas actVM = ALL_DIGITAL_EXP_GND.vmeas()
                .setWaitTime(3e-3)
                .setAverages(100);




        hwRelay.setValue(1);
        SS_K23_CAP.setValue(1);

        ds.actionCall(ALL_DC_PINS_0V);
        ds.actionCall(vForce_VDD_0V);
        ds.actionCall(ALL_DIGITAL_EXP_GND_0A);
        ds.actionCall(ALL_DC_PINS_0A);

        ds.actionCall(actIF);
        ds.actionCall(actVM);

        measurement.setSetups(ds);

    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        measurement.execute();

        IDcVIResults results = measurement.dcVI("ALL_DIGITAL_EXP_GND").preserveResults();

        Map<String, MultiSiteDoubleArray> voltage = results.vmeas("").getVoltage();


        message(1, voltage.toString());

    }

}
