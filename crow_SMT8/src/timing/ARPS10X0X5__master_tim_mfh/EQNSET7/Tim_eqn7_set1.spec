import timing.ARPS10X0X5__master_tim_mfh.EQNSET7.Tim_variables_eqn7;
import configuration.ARPS10X0X5__master_cfg_mfh.Groups;
// EQNSET7 => "eqn_7";
// TIMSET1 => "timingset_1";
spec Tim_eqn7_set1 {
  set timingSet_1_EQNSET7;
  setup digInOut U5_IC_IN+U5_IC_EN+ALL_Digital_Pin {
    set timing timingSet_1_EQNSET7 {
      period = tper_eqn7_ALL_DIGITAL_GND_ALSO;
      // Please set "periodDeviation" to adjust the period manually for CTIM.
      periodDeviation = 2 ns;
      d1 = d1_spec_eqn7_ALL_DIGITAL_GND_ALSO;
      r1 = 0.8 * tper_eqn7_ALL_DIGITAL_GND_ALSO;
      d2 = 0 ns;
    }
  }
}
