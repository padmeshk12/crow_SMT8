import timing.ARPS10X0X5__master_tim_mfh.EQNSET2.Tim_variables_eqn2;
import configuration.ARPS10X0X5__master_cfg_mfh.Groups;
// EQNSET2 => "eqn_2";
// TIMSET1 => "OFF_PIN_Timing";
spec Tim_eqn2_set1 {
  set timingSet_1_EQNSET2;
  setup digInOut OFF_PS1600 {
    set timing timingSet_1_EQNSET2 {
      period = 100 ns;
      // Please set "periodDeviation" to adjust the period manually for CTIM.
      // periodDeviation = xxx ns;
      r1 = 50 ns;
      d1 = 0 ns;
      d2 = 0 ns;
    }
  }
  setup digInOut EN0_PS1600 {
    set timing timingSet_1_EQNSET2 {
      period = 100 ns;
      // Please set "periodDeviation" to adjust the period manually for CTIM.
      // periodDeviation = xxx ns;
      r1 = 0 ns;
      d1 = 0 ns;
      d2 = 0 ns;
    }
  }
  setup digInOut FAULTB_PS1600 {
    set timing timingSet_1_EQNSET2 {
      period = 100 ns;
      // Please set "periodDeviation" to adjust the period manually for CTIM.
      // periodDeviation = xxx ns;
      r1 = 0 ns;
      d1 = 0 ns;
      d2 = 0 ns;
    }
  }
  setup digInOut FCB_PS1600 {
    set timing timingSet_1_EQNSET2 {
      period = 100 ns;
      // Please set "periodDeviation" to adjust the period manually for CTIM.
      // periodDeviation = xxx ns;
      r1 = 0.0 ns;
      d1 = 0 ns;
      d2 = 0 ns;
    }
  }
  // For offset pins which used in pattern but not defined in setup
  setup digInOut SS_PS1600+GND_PS1600+VBIAS_PS1600+ITRIP_PS1600+ISEN_PS1600+U5_IC_IN+U5_IC_EN {
    set timing timingSet_1_EQNSET2 {
      period = 100 ns;
      // Please set "periodDeviation" to adjust the period manually for CTIM.
      // periodDeviation = xxx ns;
      d1 = 0 ns;
    }
  }
}
