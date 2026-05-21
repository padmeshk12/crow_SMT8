import timing.ARPS10X0X5__master_tim_mfh.EQNSET2.Tim_variables_eqn2;
import configuration.ARPS10X0X5__master_cfg_mfh.Groups;
// EQNSET2 => "eqn_2";
// TIMSET2 => "SPI_TIMING_1MHz";
spec Tim_eqn2_set2 {
  set timingSet_2_EQNSET2;
  setup digInOut SPI_PINS {
    set timing timingSet_2_EQNSET2 {
      period = 25 ns;
      // Please set "periodDeviation" to adjust the period manually for CTIM.
      // periodDeviation = xxx ns;
      d1 = 0.0 ns;
      r1 = 0 ns;
      d2 = 0 ns;
    }
  }
  // For offset pins which used in pattern but not defined in setup
  setup digInOut SS_PS1600+GND_PS1600+VBIAS_PS1600+ITRIP_PS1600+ISEN_PS1600+U5_IC_IN+U5_IC_EN {
    set timing timingSet_2_EQNSET2 {
      period = 25 ns;
      // Please set "periodDeviation" to adjust the period manually for CTIM.
      // periodDeviation = xxx ns;
      d1 = 0 ns;
    }
  }
}
