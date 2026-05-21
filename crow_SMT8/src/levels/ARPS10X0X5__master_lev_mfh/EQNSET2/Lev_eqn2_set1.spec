import levels.ARPS10X0X5__master_lev_mfh.EQNSET2.Lev_variables_eqn2;
import configuration.ARPS10X0X5__master_cfg_mfh.Groups;
// EQNSET2 => "Equation_Set2";
// LEVSET1 => "OFF_Pin_Levels";
spec Lev_eqn2_set1 {
  set levelSet_1;
  setup digInOut OFF_PS1600 {
    set level levelSet_1 {
      vol = 1.5 V;
      voh = 2 V;
      term = hiz;
    }
  }
  setup digInOut DIGITAL_EXP_OFF {
    set level levelSet_1 {
      vol = 0 V;
      voh = 0 V;
      term = hiz;
    }
  }
}
