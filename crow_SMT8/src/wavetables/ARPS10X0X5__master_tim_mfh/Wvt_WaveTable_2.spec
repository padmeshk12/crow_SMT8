import configuration.ARPS10X0X5__master_cfg_mfh.Groups;
spec Wvt_WaveTable_2 {
  setup digInOut SPI_PINS {
    result.capture.enabled = false;
    wavetable WaveTable_2 {
      xModes = 1;
      L: r1:L;
      H: r1:H;
      C: r1:C;
      0: d1:0;
      1: d1:1;
      Z: d1:Z;
      X: d1:Z r1:X;
      a: d1:1 d2:0;
      b: d1:0 d2:1;
      Q: d1:N;
    }
  }
  setup digInOut SS_PS1600+GND_PS1600+VBIAS_PS1600+ITRIP_PS1600+ISEN_PS1600+U5_IC_IN+U5_IC_EN {
    wavetable WaveTable_2 {
      xModes = 1;
      Q: d1:N;
    }
  }
}
