import timing.ARPS10X0X5__master_tim_mfh.EQNSET8.Tim_variables_eqn8;
import configuration.ARPS10X0X5__master_cfg_mfh.Groups;
// EQNSET8 => "eqn_8_Exp_spi";
// TIMSET1 => "timingset_1_exp_spi";
spec Tim_eqn8_set1 {
  set timingSet_1_EQNSET8;
  setup digInOut P_ALL_EXP_SPI-SS_DPS128-FAULT_PU-EN0_DPS128-OFF_DPS128-FCB_DPS128-VBIAS_DPS128-ITRIP_DPS128-ISEN_DPS128-VOUT1-VOUT2_RS1-VOUT2_RS2-VOUT3_RS1-VOUT3_RS2-VOUT4-VOUT5-VOUT6-VIN1-VIN2-VIN3-VIN4_RS1-VIN4_RS2-VIN5_RS1-VIN5_RS2-VIN6-VDD-U5_VDD {
    set timing timingSet_1_EQNSET8 {
      period = 30 ns;
      // Please set "periodDeviation" to adjust the period manually for CTIM.
      periodDeviation = 2 ns;
      r1 = 0 ns;
    }
  }
}
