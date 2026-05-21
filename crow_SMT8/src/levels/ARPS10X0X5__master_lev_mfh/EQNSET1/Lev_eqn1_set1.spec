import levels.ARPS10X0X5__master_lev_mfh.EQNSET1.Lev_variables_eqn1;
import configuration.ARPS10X0X5__master_cfg_mfh.Groups;
// EQNSET1 => "Equation_Set1";
// LEVSET1 => "ALLDCZero";
spec Lev_eqn1_set1 {
  set levelSet_1;
  setup digInOut ALL_Digital_Pin {
    set level levelSet_1 {
      vil = ip_vil;
      vih = ip_vih;
      vol = ip_vol;
      voh = ip_voh;
      term = hiz;
    }
  }
  setup digInOut U5_IC_EN_IN {
    set level levelSet_1 {
      vil = 0 V;
      vih = 0 V;
      term = hiz;
    }
  }
}
