import configuration.ARPS10X0X5__master_cfg_mfh.Groups;
// -------- For configuration --------;
// Uncomment the following line to switch on cycelPassFail result configuration;
// import configuration.ARPS10X0X5__master_cfg_mfh.Instrument_ARPS10X0X5__master_cfg_mfh;
// -------- For Timing --------;
import timing.ARPS10X0X5__master_tim_mfh.Tim_multiportspec_timing_spec;
import timing.ARPS10X0X5__master_tim_mfh.EQNSET1.Tim_eqn1_set1_using_P_ALL_DIG_U5IC_IN_VIN;
// -------- For Wavetable --------;
import wavetables.ARPS10X0X5__master_tim_mfh.Wvt_Multiport_Timing_Spec;
spec Spc_Tim_timing_spec_set1 {
  // For digInOut instruments
  using P_ALL_DIG_U5IC_IN_VIN;
}
