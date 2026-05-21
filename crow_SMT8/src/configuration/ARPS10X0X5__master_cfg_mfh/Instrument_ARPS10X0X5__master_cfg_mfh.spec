import configuration.ARPS10X0X5__master_cfg_mfh.Groups;
spec Instrument_ARPS10X0X5__master_cfg_mfh {
  setup digInOut SS_PS1600 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut FAULTB_PS1600 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut EN0_PS1600 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut OFF_PS1600 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut FCB_PS1600 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut GND_PS1600 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut VBIAS_PS1600 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut ITRIP_PS1600 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut ISEN_PS1600 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut U5_IC_IN {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut U5_IC_EN {
    result.cyclePassFail.enabled = true;
  }
  setup dcVI SS_DPS128 { }
  setup dcVI FAULT_PU { }
  setup dcVI EN0_DPS128 { }
  setup dcVI OFF_DPS128 { }
  setup dcVI FCB_DPS128 { }
  setup dcVI VBIAS_DPS128 { }
  setup dcVI ITRIP_DPS128 { }
  setup dcVI ISEN_DPS128 { }
  setup dcVI VOUT1 { }
  setup dcVI VOUT2_RS1 { }
  setup dcVI VOUT2_RS2 { }
  setup dcVI VOUT3_RS1 { }
  setup dcVI VOUT3_RS2 { }
  setup dcVI VOUT4 { }
  setup dcVI VOUT5 { }
  setup dcVI VOUT6 { }
  setup dcVI VIN1 { }
  setup dcVI VIN2 { }
  setup dcVI VIN3 { }
  setup dcVI VIN4_RS1 { }
  setup dcVI VIN4_RS2 { }
  setup dcVI VIN5_RS1 { }
  setup dcVI VIN5_RS2 { }
  setup dcVI VIN6 { }
  setup dcVI VDD { }
  setup dcVI U5_VDD { }
}
