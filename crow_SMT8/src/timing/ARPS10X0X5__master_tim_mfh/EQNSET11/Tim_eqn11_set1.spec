import timing.ARPS10X0X5__master_tim_mfh.EQNSET11.Tim_variables_eqn11;
import configuration.ARPS10X0X5__master_cfg_mfh.Groups;
// EQNSET11 => "SS_Delay_1";
// TIMSET1 => "SS_Delay_Cap_1";
spec Tim_eqn11_set1 {
  set timingSet_1_EQNSET11;
  setup digInOut P_PS1600_EXP_SS_EN_FCB {
    set timing timingSet_1_EQNSET11 {
      period = 20 ns;
      // Please set "periodDeviation" to adjust the period manually for CTIM.
      periodDeviation = 2 ns;
      d1 = 0.0 ns;
    }
  }
}
