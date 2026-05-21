import configuration.ARPS10X0X5__master_cfg_mfh.Groups;
spec Wvt_SS_DELAY_WVT {
  setup digInOut P_EN_FCB+P_SS_PS1600 {
    result.capture.enabled = false;
    wavetable SS_DELAY_WVT {
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
  setup digInOut FAULTB_PS1600+OFF_PS1600+GND_PS1600+VBIAS_PS1600+ITRIP_PS1600+ISEN_PS1600+U5_IC_IN+U5_IC_EN {
    wavetable SS_DELAY_WVT {
      xModes = 1;
      Q: d1:N;
    }
  }
}
