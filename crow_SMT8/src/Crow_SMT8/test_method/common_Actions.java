package Crow_SMT8.test_method;


import java.util.Map;
import java.util.stream.Collectors;

import xoc.dsa.IDeviceSetup;
import xoc.dsa.ISetupAction;
import xoc.dsa.ISetupDcVI;
import xoc.dsa.ISetupDcVI.IMeasureWaveform;
import xoc.dsa.ISetupDigInOut;
import xoc.dsa.ISetupUtility;
import xoc.dta.TestMethod;
import xoc.dta.datatypes.MultiSiteDouble;
import xoc.dta.datatypes.MultiSiteDoubleArray;


 public abstract class common_Actions extends TestMethod {

     protected IDeviceSetup ds;

     protected  void vForce(String Signals, double Voltage, double iRange, double iClamp) {
         vForce_Main(Signals, Voltage, iRange, iClamp,false,null);
     }
     protected  void vForce(String Signals, double Voltage, double iRange, double iClamp, boolean isHighCurrent, String RefPin) {
         vForce_Main(Signals, Voltage, iRange, iClamp,isHighCurrent,RefPin);
     }

     protected String makeActionName(String prefix, String signals, double value, String unit) {
         return (prefix + "_" + signals + "_" + value + unit)
                 .replace("+", "_")
                 .replace(".", "_")
                 .replace("-", "");
     }

     protected  void vForce_Main(String Signals, double Voltage, double iRange, double iClamp,
             boolean isHighCurrent, String RefPin) {
         Signals = (Signals).replace(",", "+");
         String prefix = isHighCurrent ? "VF_HCU_" : "VF_";
         String Action = (prefix + Signals +"_"+ Voltage+"V").replace("+", "_").replace(".", "_").replace("-", "");

         ISetupDcVI VForce = ds.addDcVI(Signals);
         if (isHighCurrent) {
            VForce.setConfigOptionHighCurrent().setConnectModeStandard().setDisconnect(false);
        } else {
            VForce.setConnect(true).setDisconnect(false);
        }

         ISetupDcVI.IVforce IVForce = VForce.vforce(Action).setVrange(Voltage + 0.5).setForceValue(Voltage)
                 .setIclamp(iClamp).setIrange(iRange);

         if (RefPin != null && !RefPin.isEmpty()) {
            IVForce.setNeg(RefPin);
        }
         ds.actionCall(IVForce);
     }

     protected  void iForce(String Signals, double Current, double iRange, double VClampLow,
             double VClampHigh) {
         String Action = ("IF_" + Signals +"_"+ Current+"A").replace("+", "_").replace(".", "_").replace("-", "");
         ISetupDcVI IForce = ds.addDcVI(Signals).setConnect(true);
         ISetupDcVI.IIforce IIForce = IForce.iforce(Action)
                 .setVrange(Math.max(Math.abs(VClampHigh), Math.abs(VClampLow)))
                 .setForceValue(Current).setVclampLow(VClampLow).setVclampHigh(VClampHigh)
                 .setIrange(iRange);

         ds.actionCall(IIForce);
     }

     protected  void IFVM(String Signals, double Current, double iRange, double VClampLow, double VClampHigh, double wait, int samples) {
         String Action = ("IF_" + Signals +"_"+ Current+"A").replace("+", "_").replace(".", "_").replace("-", "");
         Signals = (Signals).replace(",", "+");
         ISetupDcVI IForce = ds.addDcVI(Signals).setConnect(true);

         ISetupDcVI.IIforce IIForce = IForce.iforce(Action)
                 .setVrange(Math.max(Math.abs(VClampHigh), Math.abs(VClampLow)))
                 .setForceValue(Current).setVclampLow(VClampLow).setVclampHigh(VClampHigh)
                 .setIrange(iRange);

        Action = ("VM_" + Signals).replace("+", "_").replace(".", "_").replace("-", "");
        ISetupDcVI.IVmeas actVM = IForce.vmeas(Action)
                 .setWaitTime(wait/1000)
                 .setAverages(samples);

         ds.actionCall(IIForce);
         ds.actionCall(actVM);
     }

     protected  void IFVM(String Signals, String Ref_pin, double Current, double iRange, double VClampLow, double VClampHigh, double wait, int samples, int Vdiff) {
         String Action = ("IF_" + Signals +"_"+ Current+"A").replace("+", "_").replace(".", "_").replace("-", "");
         Signals = (Signals).replace(",", "+");
         ISetupDcVI IForce = ds.addDcVI(Signals).setConnect(true);
         ISetupDcVI ref_pin = ds.addDcVI(Ref_pin).setConnect(true);

         ref_pin.level().setVrange(Math.max(Math.abs(VClampHigh), Math.abs(VClampLow))).setIrange(iRange);

         ISetupDcVI.IIforce IIForce = IForce.iforce(Action)
                 .setVrange(Math.max(Math.abs(VClampHigh), Math.abs(VClampLow)))
                 .setForceValue(Current).setVclampLow(VClampLow).setVclampHigh(VClampHigh)
                 .setIrange(iRange);

        Action = ("VM_" + Signals).replace("+", "_").replace(".", "_").replace("-", "");
        ISetupDcVI.IVmeas actVM = IForce.vmeas(Action)
                 .setWaitTime(wait/1000)
                 .setAverages(samples).setNeg(Ref_pin).setVdiffRange(Vdiff);

         ds.actionCall(IIForce);
         ds.actionCall(actVM);
     }

     protected  void Disconnect(String Signals) {
         String Action = ("Disconnect_" + Signals).replace("+", "_").replace(".", "_");
         ISetupDcVI Off_PPMU = ds.addDcVI(Signals);
         Off_PPMU.setDisconnect(true);
         ds.actionCall(Off_PPMU.disconnect(Action));
     }

     protected  void Wait_ms(double wait) {
         ds.waitCall(wait / 1000);
     }

     protected  void Utility(String Ubit_Signal, int value) {
         Ubit_Signal = (Ubit_Signal).replace(",", "+");
         ISetupUtility Util_Relay = ds.addUtility(Ubit_Signal);
         Util_Relay.setValue(value);
     }

     protected Map<String, MultiSiteDouble> getActionResults(Map<String, MultiSiteDoubleArray> allVoltageResults, int actionIndex) {
         return allVoltageResults.entrySet().stream()
                 .collect(Collectors.toMap(
                         Map.Entry::getKey,
                         e -> e.getValue().getElement(actionIndex)
                 ));
     }

     protected Map<String, MultiSiteDouble> scaleResults(Map<String, MultiSiteDouble> results, double factor) {
         return results.entrySet().stream()
                 .collect(Collectors.toMap(
                         Map.Entry::getKey,
                         e -> e.getValue().multiply(factor)
                 ));
     }

     protected void  iFVMW(String Signals, double Current, double iRange, double VClampLow, double VClampHigh, int Samples, double SampleRate, String ID) {
         ISetupDcVI iFVMW = ds.addDcVI(Signals).setConnect(true);


         String forceAction = makeActionName("IF", Signals, Current, "A");

         ISetupDcVI.IIforce IForce = iFVMW.iforce(forceAction)
                 .setForceValue(Current)
                 .setVclampLow(VClampLow)
                 .setVclampHigh(VClampHigh)
                 .setIrange(iRange)
                 .setVrange(Math.max(Math.abs(VClampLow), Math.abs(VClampHigh)));

     //    String waveAction = makeActionName("VMW", Signals, Samples, "S_" + SampleRate + "Hz");
         IMeasureWaveform measureWaveform = iFVMW.measureWaveform(ID);
         measureWaveform.setHighAccuracy(true)
                 .setSampleRate(SampleRate)
                 .setSamples(Samples)
                 .setMeasTypeVoltage();

         ds.actionCall(IForce);
         ds.actionCall(measureWaveform);

     }

     protected void Ramp_Voltage(String Signals, double Start, double Stop, int Samples, double SampleRate) {

         ISetupDcVI dcviPin = ds.addDcVI(Signals).setConnect(true).setDisconnect(false);
         dcviPin.level().setIrange(40e-3).setVrange(5.0);

         String rampAction = ("Ramp_WF_V" + Signals).replace("+", "_").replace(".", "_").replace("-", "");
         String srcAction = ("Src_WF_V" + Signals).replace("+", "_").replace(".", "_").replace("-", "");

         xoc.dsa.ISetupWaveformRamp rampWaveform = ds.addWaveformRamp(rampAction)
                 .setStartValue(Start)
                 .setStopValue(Stop)
                 .setSamples(Samples)
                 .setSampleRate(SampleRate);
         ISetupDcVI.ISourceWaveform sourceAction = dcviPin.sourceWaveform(srcAction)
                 .setForceTypeVoltage()
                 .setWaveform(rampWaveform)
                 .setIclamp(40e-3);
         ds.actionCall(sourceAction);
     }

     protected  void VFIM(String Signals, double Voltage, double iRange, double IClamp, double wait, int samples) {
         String Action = ("VF_" + Signals +"_"+ Voltage+"V").replace("+", "_").replace(".", "_").replace("-", "");
         Signals = (Signals).replace(",", "+");
         ISetupDcVI VForce = ds.addDcVI(Signals).setConnect(true);

         ISetupDcVI.IVforce IVForce = VForce.vforce(Action)
                 .setVrange(Voltage+0.5)
                 .setForceValue(Voltage).setIclamp(IClamp)
                 .setIrange(iRange);

        Action = ("IM_" + Signals).replace("+", "_").replace(".", "_").replace("-", "");
        ISetupDcVI.IImeas actIM = VForce.imeas(Action)
                 .setWaitTime(wait/1000)
                 .setAverages(samples).setIrange(iRange);

         ds.actionCall(IVForce);
         ds.actionCall(actIM);
     }


     protected  void vForce_ID(String Signals, double Voltage, double iRange, double iClamp)
     {
         Signals = (Signals).replace(",", "+");
         String Action = ("VF_ID_" + Signals +"_"+ Voltage+"V").replace("+", "_").replace(".", "_").replace("-", "");

         ISetupDigInOut VForce = ds.addDigInOut(Signals);
         ISetupAction IVForce = VForce.vforce(Action)
                 .setForceValue(Voltage)
                 .setIrange(iRange)
                 .setIclamp(iClamp);
         ds.actionCall(IVForce);

     }

     protected  void Disconnect_ID(String Signals) {
         String Action = ("Disconnect_ID_" + Signals).replace("+", "_").replace(".", "_");
         ISetupDigInOut Off_PPMU = ds.addDigInOut(Signals);
         ISetupDigInOut.IFrontendConnection act = Off_PPMU.frontendConnection(Action).setDigitalFrontendDisable();
         ds.actionCall(act);
     }

     protected  void IFVM_ID(String Signals, double Current, double iRange, double VClampLow, double VClampHigh, double wait, int samples) {
         String Action = ("IF_ID_" + Signals +"_"+ Current+"A").replace("+", "_").replace(".", "_").replace("-", "");
         Signals = (Signals).replace(",", "+");
         ISetupDigInOut IForce = ds.addDigInOut(Signals).setConnect(true);

         ISetupDigInOut.IIforce IIForce = IForce.iforce(Action)
                 .setVrange(Math.max(Math.abs(VClampHigh), Math.abs(VClampLow)))
                 .setForceValue(Current).setVclampLow(VClampLow).setVclampHigh(VClampHigh)
                 .setIrange(iRange);

        Action = ("VM_ID_" + Signals).replace("+", "_").replace(".", "_").replace("-", "");
        ISetupDigInOut.IVmeas actVM = IForce.vmeas(Action)
                 .setWaitTime(wait/1000)
                 .setAverages(samples);

         ds.actionCall(IIForce);
         ds.actionCall(actVM);
     }


}
