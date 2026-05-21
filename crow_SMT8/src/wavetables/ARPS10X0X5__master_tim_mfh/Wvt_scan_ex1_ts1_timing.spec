import configuration.ARPS10X0X5__master_cfg_mfh.Groups;
spec Wvt_scan_ex1_ts1_timing {
  setup digInOut EN0_PS1600 {
    wavetable scan_ex1_ts1_timing {
      xModes = 1;
      0: d1:0;
      1: d1:1;
      Z: d1:Z;
      L: d1:Z r1:L;
      H: d1:Z r1:H;
      T: d1:Z r1:M;
      Q: d1:N;
      brk Q;
    }
  }
  setup digInOut FAULTB_PS1600 {
    wavetable scan_ex1_ts1_timing {
      xModes = 1;
      0: d1:0;
      1: d1:1;
      Z: d1:Z;
      L: d1:Z r1:L;
      H: d1:Z r1:H;
      T: d1:Z r1:M;
      Q: d1:N;
      brk Q;
    }
  }
  setup digInOut FCB_PS1600 {
    wavetable scan_ex1_ts1_timing {
      xModes = 1;
      0: d1:0 d2:0;
      1: d1:1 d2:1;
      Z: d1:Z d2:Z;
      P: d1:1 d2:0;
      L: d1:Z r1:L;
      H: d1:Z r1:H;
      T: d1:Z r1:M;
      Q: d1:N;
      brk Q;
    }
  }
  setup digInOut OFF_PS1600 {
    wavetable scan_ex1_ts1_timing {
      xModes = 1;
      0: d1:0;
      1: d1:1;
      Z: d1:Z;
      L: d1:Z r1:L;
      H: d1:Z r1:H;
      T: d1:Z r1:M;
      Q: d1:N;
      brk Q;
    }
  }
  setup digInOut SS_PS1600+GND_PS1600+VBIAS_PS1600+ITRIP_PS1600+ISEN_PS1600+U5_IC_IN+U5_IC_EN {
    wavetable scan_ex1_ts1_timing {
      xModes = 1;
      Q: d1:N;
    }
  }
}
