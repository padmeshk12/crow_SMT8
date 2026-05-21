import configuration.ARPS10X0X5__master_cfg_mfh.Groups;
spec Wvt_SS_DELay_dummy {
  setup digInOut P_PS1600_EXP_SS_EN_FCB {
    result.capture.enabled = false;
    wavetable SS_DELay_dummy {
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
  setup digInOut SS_PS1600+EN0_PS1600+FCB_PS1600+U5_IC_IN+U5_IC_EN {
    wavetable SS_DELay_dummy {
      xModes = 1;
      Q: d1:N;
    }
  }
}
