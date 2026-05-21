import configuration.ARPS10X0X5__master_cfg_mfh.Groups;
spec Wvt_Wavetbl_exp_SPI {
  setup digInOut P_ALL_EXP_SPI-SS_DPS128-FAULT_PU-EN0_DPS128-OFF_DPS128-FCB_DPS128-VBIAS_DPS128-ITRIP_DPS128-ISEN_DPS128-VOUT1-VOUT2_RS1-VOUT2_RS2-VOUT3_RS1-VOUT3_RS2-VOUT4-VOUT5-VOUT6-VIN1-VIN2-VIN3-VIN4_RS1-VIN4_RS2-VIN5_RS1-VIN5_RS2-VIN6-VDD-U5_VDD {
    wavetable Wavetbl_exp_SPI {
      xModes = 1;
      X: r1:X;
      Q: d1:N;
    }
  }
  setup digInOut FAULTB_PS1600+EN0_PS1600+OFF_PS1600+FCB_PS1600 {
    wavetable Wavetbl_exp_SPI {
      xModes = 1;
      Q: d1:N;
    }
  }
}
