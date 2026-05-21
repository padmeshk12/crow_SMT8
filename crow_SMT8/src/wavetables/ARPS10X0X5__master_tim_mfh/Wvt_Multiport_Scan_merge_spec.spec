import configuration.ARPS10X0X5__master_cfg_mfh.Groups;
spec Wvt_Multiport_Scan_merge_spec {
  setup digInOut EN0_PS1600 {
    wavetable scan_merge {
      xModes = 1;
      0: d2:0;
      1: d2:1;
      Z: d2:Z;
      a: d2:Z r2:L;
      b: d2:Z r2:H;
      c: d2:Z r2:M;
      d: d1:0;
      e: d1:1;
      f: d1:Z;
      L: d1:Z r1:L;
      H: d1:Z r1:H;
      T: d1:Z r1:M;
      Q: d1:N;
      brk Q;
    }
  }
  setup digInOut FAULTB_PS1600 {
    wavetable scan_merge {
      xModes = 1;
      0: d2:0;
      1: d2:1;
      Z: d2:Z;
      a: d2:Z r2:L;
      b: d2:Z r2:H;
      c: d2:Z r2:M;
      d: d1:0;
      e: d1:1;
      f: d1:Z;
      L: d1:Z r1:L;
      H: d1:Z r1:H;
      T: d1:Z r1:M;
      Q: d1:N;
      brk Q;
    }
  }
  setup digInOut FCB_PS1600 {
    wavetable scan_merge {
      xModes = 1;
      0: d3:0 d4:0;
      1: d3:1 d4:1;
      Z: d3:Z d4:Z;
      a: d3:1 d4:0;
      b: d3:Z r2:L;
      c: d3:Z r2:H;
      d: d3:Z r2:M;
      e: d1:0 d2:0;
      f: d1:1 d2:1;
      g: d1:Z d2:Z;
      P: d1:1 d2:0;
      L: d1:Z r1:L;
      H: d1:Z r1:H;
      T: d1:Z r1:M;
      Q: d1:N;
      brk Q;
    }
  }
  setup digInOut OFF_PS1600 {
    wavetable scan_merge {
      xModes = 1;
      0: d2:0;
      1: d2:1;
      Z: d2:Z;
      a: d2:Z r2:L;
      b: d2:Z r2:H;
      c: d2:Z r2:M;
      d: d1:0;
      e: d1:1;
      f: d1:Z;
      L: d1:Z r1:L;
      H: d1:Z r1:H;
      T: d1:Z r1:M;
      Q: d1:N;
      brk Q;
    }
  }
  setup digInOut P_ALL_EXP_SPI-SS_DPS128-FAULT_PU-EN0_DPS128-OFF_DPS128-FCB_DPS128-VBIAS_DPS128-ITRIP_DPS128-ISEN_DPS128-VOUT1-VOUT2_RS1-VOUT2_RS2-VOUT3_RS1-VOUT3_RS2-VOUT4-VOUT5-VOUT6-VIN1-VIN2-VIN3-VIN4_RS1-VIN4_RS2-VIN5_RS1-VIN5_RS2-VIN6-VDD-U5_VDD {
    wavetable Wavetbl_exp_SPI {
      xModes = 1;
      X: r1:X;
      Q: d1:N;
    }
  }
}