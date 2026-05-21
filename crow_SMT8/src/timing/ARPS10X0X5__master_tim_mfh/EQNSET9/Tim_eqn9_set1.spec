import timing.ARPS10X0X5__master_tim_mfh.EQNSET9.Tim_variables_eqn9;
import configuration.ARPS10X0X5__master_cfg_mfh.Groups;
// EQNSET9 => "Post_ILIM";
// TIMSET1 => "POST_ILIM_TIMINGSET";
spec Tim_eqn9_set1 {
  set timingSet_1_EQNSET9;
  setup digInOut SPI_PINS {
    set timing timingSet_1_EQNSET9 {
      period = 300 ns;
      // Please set "periodDeviation" to adjust the period manually for CTIM.
      // periodDeviation = xxx ns;
      d1 = 0.0 ns;
      r1 = 0 ns;
    }
  }
  // For offset pins which used in pattern but not defined in setup
  setup digInOut SS_PS1600+GND_PS1600+VBIAS_PS1600+ITRIP_PS1600+ISEN_PS1600+U5_IC_IN+U5_IC_EN {
    set timing timingSet_1_EQNSET9 {
      period = 300 ns;
      // Please set "periodDeviation" to adjust the period manually for CTIM.
      // periodDeviation = xxx ns;
      d1 = 0 ns;
    }
  }
}
