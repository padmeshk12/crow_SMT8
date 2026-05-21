import timing.ARPS10X0X5__master_tim_mfh.EQNSET4.Tim_variables_eqn4;
import configuration.ARPS10X0X5__master_cfg_mfh.Groups;
// EQNSET4 => "scan_ex1_ts1_timing_1";
// TIMSET2 => "scan_cycle_TM_1";
spec Tim_eqn4_set2 {
  set timingSet_2_EQNSET4;
  setup digInOut EN0_PS1600 {
    set timing timingSet_2_EQNSET4 {
      period = 500.00000000000006 ns;
      // Please set "periodDeviation" to adjust the period manually for CTIM.
      // periodDeviation = xxx ns;
      d1 = 0.0 ns;
      r1 = 0.0 ns;
    }
  }
  setup digInOut FAULTB_PS1600 {
    set timing timingSet_2_EQNSET4 {
      period = 500.00000000000006 ns;
      // Please set "periodDeviation" to adjust the period manually for CTIM.
      // periodDeviation = xxx ns;
      d1 = 0.0 ns;
      r1 = 0.0 ns;
    }
  }
  setup digInOut FCB_PS1600 {
    set timing timingSet_2_EQNSET4 {
      period = 500.00000000000006 ns;
      // Please set "periodDeviation" to adjust the period manually for CTIM.
      // periodDeviation = xxx ns;
      d1 = 200.0 ns;
      d2 = 400.0 ns;
      r1 = 0.0 ns;
    }
  }
  setup digInOut OFF_PS1600 {
    set timing timingSet_2_EQNSET4 {
      period = 500.00000000000006 ns;
      // Please set "periodDeviation" to adjust the period manually for CTIM.
      // periodDeviation = xxx ns;
      d1 = 0.0 ns;
      r1 = 0.0 ns;
    }
  }
}
