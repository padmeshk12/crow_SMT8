import configuration.ARPS10X0X5__master_cfg_mfh.Groups;
spec Wvt_Multiport_Timing_Spec {
  setup digInOut ALL_Digital_Pin+U5_IC_IN+U5_IC_EN {
    wavetable WaveTable_1 {
      xModes = 1;
      0: d1:F00;
      1: d1:F10;
      c: d1:F10 d2:F00;
      H: r1:H;
      L: r1:L;
      X: r1:X;
      Q: d1:N;
    }
  }
}