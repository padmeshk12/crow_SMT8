import configuration.ARPS10X0X5__master_cfg_mfh.Groups;
spec Wvt_Multiport_EFUSE_PULSE {
  setup digInOut ALL_Digital_Pin+U5_IC_IN+U5_IC_EN-GND_PS1600 {
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
  setup digInOut P_ALL_EXP_SPI-SS_PS1600-VBIAS_PS1600-ITRIP_PS1600-ISEN_PS1600-U5_IC_IN-U5_IC_EN-SS_DPS128-FAULT_PU-EN0_DPS128-OFF_DPS128-FCB_DPS128-VBIAS_DPS128-ITRIP_DPS128-ISEN_DPS128-VOUT1-VOUT2_RS1-VOUT2_RS2-VOUT3_RS1-VOUT3_RS2-VOUT4-VOUT5-VOUT6-VIN1-VIN2-VIN3-VIN4_RS1-VIN4_RS2-VIN5_RS1-VIN5_RS2-VIN6-VDD-U5_VDD {
    wavetable Wavetbl_exp_SPI {
      xModes = 1;
      X: r1:X;
      Q: d1:N;
    }
  }
}