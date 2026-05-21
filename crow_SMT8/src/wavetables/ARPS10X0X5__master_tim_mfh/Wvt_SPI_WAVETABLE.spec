import configuration.ARPS10X0X5__master_cfg_mfh.Groups;
spec Wvt_SPI_WAVETABLE {
  setup digInOut SPI_PINS  {
    result.capture.enabled = false;
    wavetable SPI_WAVETABLE1  {
      xModes = 1;
      0: d1:F00;
      1: d1:F10;
      H: d1:Z r1:H;
      L: d1:Z r1:L;
      X: d1:Z r1:X;
      C: r1:C;
      Q: d1:N;
      brk Q;
    }
  }
  setup digInOut SS_PS1600 + GND_PS1600+VBIAS_PS1600+ITRIP_PS1600+U5_IC_IN+U5_IC_EN{
    wavetable SPI_WAVETABLE {
      xModes = 1;
      0: d1:F00;
      1: d1:F10;
      Q: d1:N;
    }
  }
   setup digInOut ISEN_PS1600{
    wavetable SPI_WAVETABLE {
      xModes = 1;
      0: d1:F00;
      1: d1:F10;
      Q: d1:N;
    }
  }
}
