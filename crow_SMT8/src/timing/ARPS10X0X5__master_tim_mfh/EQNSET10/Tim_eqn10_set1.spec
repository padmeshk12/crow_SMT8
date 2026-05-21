import timing.ARPS10X0X5__master_tim_mfh.EQNSET10.Tim_variables_eqn10;
import configuration.ARPS10X0X5__master_cfg_mfh.Groups;
// EQNSET10 => "SS_Delay";
// TIMSET1 => "SS_Delay_Cap";
spec Tim_eqn10_set1 {
  set timingSet_1_EQNSET10;
  setup digInOut FCB_PS1600+SS_PS1600-FCB_PS1600 {
    set timing timingSet_1_EQNSET10 {
      period = 20 ns;
      // Please set "periodDeviation" to adjust the period manually for CTIM.
      // periodDeviation = xxx ns;
      d1 = 0.0 ns;
      r1 = 0 ns;
    }
  }
  setup digInOut EN0_PS1600+FCB_PS1600 {
    set timing timingSet_1_EQNSET10 {
      period = 20 ns;
      // Please set "periodDeviation" to adjust the period manually for CTIM.
      periodDeviation = 2 ns;
      d1 = 0.0 ns;
      r1 = 0 ns;
    }
  }
  // For offset pins which used in pattern but not defined in setup
  setup digInOut FAULTB_PS1600+OFF_PS1600+GND_PS1600+VBIAS_PS1600+ITRIP_PS1600+ISEN_PS1600+U5_IC_IN+U5_IC_EN {
    set timing timingSet_1_EQNSET10 {
      period = 20 ns;
      // Please set "periodDeviation" to adjust the period manually for CTIM.
      periodDeviation = 2 ns;
      d1 = 0 ns;
    }
  }
}
