import configuration.ARPS10X0X5__master_cfg_mfh.Groups;
// -------- For configuration --------;
// Uncomment the following line to switch on cycelPassFail result configuration;
// import configuration.ARPS10X0X5__master_cfg_mfh.Instrument_ARPS10X0X5__master_cfg_mfh;
// -------- For Level --------;
import levels.ARPS10X0X5__master_lev_mfh.Lev_eqn3_spec1;
import levels.ARPS10X0X5__master_lev_mfh.EQNSET3.Lev_eqn3_set1;
// -------- For Timing --------;
import timing.ARPS10X0X5__master_tim_mfh.Tim_multiportspec_logic_spec;
import timing.ARPS10X0X5__master_tim_mfh.EQNSET3.Tim_eqn3_set1_using_pALL_PINS_;
import timing.ARPS10X0X5__master_tim_mfh.EQNSET8.Tim_eqn8_set1_using_P_ALL_EXP_SPI;
// -------- For Wavetable --------;
import wavetables.ARPS10X0X5__master_tim_mfh.Wvt_Multiport_logic_spec;
spec Spc_Lev_eqn3_spec1_set1_Tim_logic_spec_set1_set1 {
  // For timing primary set
  setup digInOut pALL_PINS_ {
    settings.timing = timingSet_1_EQNSET3;
  }
  setup digInOut P_ALL_EXP_SPI - SS_DPS128 - FAULT_PU - EN0_DPS128 - OFF_DPS128 - FCB_DPS128 - VBIAS_DPS128 - ITRIP_DPS128 - ISEN_DPS128 - VOUT1 - VOUT2_RS1 - VOUT2_RS2 - VOUT3_RS1 - VOUT3_RS2 - VOUT4 - VOUT5 - VOUT6 - VIN1 - VIN2 - VIN3 - VIN4_RS1 - VIN4_RS2 - VIN5_RS1 - VIN5_RS2 - VIN6 - VDD - U5_VDD {
    settings.timing = timingSet_1_EQNSET8;
  }
  // For digInOut instruments
  using pALL_PINS_ + P_ALL_EXP_SPI;
}
