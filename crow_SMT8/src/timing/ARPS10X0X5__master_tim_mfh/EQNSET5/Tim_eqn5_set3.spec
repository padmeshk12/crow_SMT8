import timing.ARPS10X0X5__master_tim_mfh.EQNSET5.Tim_variables_eqn5;
import configuration.ARPS10X0X5__master_cfg_mfh.Groups;
// EQNSET5 => "SPI_TIMING_EQN";
// TIMSET3 => "FAST_TRIP_CAPTURE";
spec Tim_eqn5_set3 {
  set timingSet_3_EQNSET5;
  setup digInOut OFF_PS1600 {
    set timing timingSet_3_EQNSET5 {
      period = 25 ns;
      // Please set "periodDeviation" to adjust the period manually for CTIM.
      // periodDeviation = xxx ns;
      d1 = 0.0 ns;
      r1 = 0.0 ns;
    }
  }
  // For offset pins which used in pattern but not defined in setup
  setup digInOut SS_PS1600+FAULTB_PS1600+EN0_PS1600+FCB_PS1600+GND_PS1600+VBIAS_PS1600+ITRIP_PS1600+ISEN_PS1600+U5_IC_IN+U5_IC_EN {
    set timing timingSet_3_EQNSET5 {
      period = 25 ns;
      // Please set "periodDeviation" to adjust the period manually for CTIM.
      // periodDeviation = xxx ns;
      d1 = 0 ns;
      r1 = 0 ns;
    }
  }
}
