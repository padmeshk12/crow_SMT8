import timing.ARPS10X0X5__master_tim_mfh.EQNSET5.Tim_variables_eqn5;
import configuration.ARPS10X0X5__master_cfg_mfh.Groups;
// EQNSET5 => "SPI_TIMING_EQN";
// TIMSET1 => "SPI_TIMING_1MHz";
spec Tim_eqn5_set1 {
  set timingSet_1_EQNSET5;
  setup digInOut SPI_PINS {
       settings.timing = timingSet_1_EQNSET5;
    set timing timingSet_1_EQNSET5 {
      period = 1000 ns;
      // Please set "periodDeviation" to adjust the period manually for CTIM.
      // periodDeviation = xxx ns;
      d1 = 0.0 ns;
      r1 = 0 ns;
    }
  }
  // For offset pins which used in pattern but not defined in setup
  setup digInOut SS_PS1600 +GND_PS1600+VBIAS_PS1600+ITRIP_PS1600+ISEN_PS1600+U5_IC_IN+U5_IC_EN {
       settings.timing = timingSet_1_EQNSET5;
    set timing timingSet_1_EQNSET5 {
      period = 1000 ns;
      // Please set "periodDeviation" to adjust the period manually for CTIM.
      // periodDeviation = xxx ns;
      d1 = 0 ns;
    }
  }
}
