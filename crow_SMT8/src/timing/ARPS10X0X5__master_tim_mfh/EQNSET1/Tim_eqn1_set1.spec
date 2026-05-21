import timing.ARPS10X0X5__master_tim_mfh.EQNSET1.Tim_variables_eqn1;
import configuration.ARPS10X0X5__master_cfg_mfh.Groups;
// EQNSET1 => "eqn_1";
// TIMSET1 => "timingset_1";
spec Tim_eqn1_set1 {
  set timingSet_1_EQNSET1;
  setup digInOut ALL_Digital_Pin+U5_IC_IN+U5_IC_EN {
    set timing timingSet_1_EQNSET1 {
      period = 1000 ns;
      // Please set "periodDeviation" to adjust the period manually for CTIM.
      // periodDeviation = xxx ns;
      d1 = 0.25 * tper_eqn1_P_ALL_DIG_U5IC_IN_VIN;
      d2 = 0.35 * tper_eqn1_P_ALL_DIG_U5IC_IN_VIN;
      r1 = 0.8 * tper_eqn1_P_ALL_DIG_U5IC_IN_VIN;
    }
  }
}
