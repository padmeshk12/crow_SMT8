import timing.ARPS10X0X5__master_tim_mfh.EQNSET6.Tim_variables_eqn6;
import configuration.ARPS10X0X5__master_cfg_mfh.Groups;
// EQNSET6 => "scan_merge";
// TIMSET1 => "test_cycle";
spec Tim_eqn6_set1 {
  set timingSet_1_EQNSET6;
  setup digInOut EN0_PS1600 {
    set timing timingSet_1_EQNSET6 {
      period = 1000.0000000000001 ns;
      // Please set "periodDeviation" to adjust the period manually for CTIM.
      // periodDeviation = xxx ns;
      d2 = 0.0 ns;
      r2 = 900.0 ns;
      d1 = 0.0 ns;
      r1 = 0.0 ns;
    }
  }
  setup digInOut FAULTB_PS1600 {
    set timing timingSet_1_EQNSET6 {
      period = 1000.0000000000001 ns;
      // Please set "periodDeviation" to adjust the period manually for CTIM.
      // periodDeviation = xxx ns;
      d2 = 0.0 ns;
      r2 = 900.0 ns;
      d1 = 0.0 ns;
      r1 = 0.0 ns;
    }
  }
  setup digInOut FCB_PS1600 {
    set timing timingSet_1_EQNSET6 {
      period = 1000.0000000000001 ns;
      // Please set "periodDeviation" to adjust the period manually for CTIM.
      // periodDeviation = xxx ns;
      d3 = 200.0 ns;
      d4 = 300.0 ns;
      r2 = 900.0 ns;
      d1 = 200.0 ns;
      d2 = 400.0 ns;
      r1 = 0.0 ns;
    }
  }
  setup digInOut OFF_PS1600 {
    set timing timingSet_1_EQNSET6 {
      period = 1000.0000000000001 ns;
      // Please set "periodDeviation" to adjust the period manually for CTIM.
      // periodDeviation = xxx ns;
      d2 = 0.0 ns;
      r2 = 900.0 ns;
      d1 = 0.0 ns;
      r1 = 0.0 ns;
    }
  }
}
