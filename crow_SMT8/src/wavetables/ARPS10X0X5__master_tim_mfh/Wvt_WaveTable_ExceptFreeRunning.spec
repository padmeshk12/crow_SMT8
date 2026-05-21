import configuration.ARPS10X0X5__master_cfg_mfh.Groups;
spec Wvt_WaveTable_ExceptFreeRunning {
  setup digInOut SPI_PINS {
    wavetable WaveTable_ExceptFreeRunning {
      xModes = 1;
      0: d1:0;
      1: d1:1;
      Z: d1:Z;
      L: d1:Z r1:L;
      H: d1:Z r1:H;
      X: d1:Z r1:X;
      a: d1:1 d2:0;
      b: d1:0 d2:1;
      Q: d1:N;
      brk Q;
    }
  }
  setup digInOut SS_PS1600+GND_PS1600+VBIAS_PS1600+ITRIP_PS1600+ISEN_PS1600+U5_IC_IN+U5_IC_EN {
    wavetable WaveTable_ExceptFreeRunning {
      xModes = 1;
      Q: d1:N;
    }
  }
}
