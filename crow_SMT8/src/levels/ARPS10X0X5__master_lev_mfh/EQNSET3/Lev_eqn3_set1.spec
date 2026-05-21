import levels.ARPS10X0X5__master_lev_mfh.EQNSET3.Lev_variables_eqn3;
import configuration.ARPS10X0X5__master_cfg_mfh.Groups;
// EQNSET3 => "SPI_LEVEL_EQN";
// LEVSET1 => "SPI_LEVELS";
spec Lev_eqn3_set1 {
  set levelSet_1;
  setup digInOut SPI_PINS {
      settings.level = levelSet_1;
    set level levelSet_1 {
      vil = 0.1 V;
      vih = 3 V;
      vol = 0.8 V;
      voh = 1 V;
      term = hiz;
    }
  }
  setup digInOut ISEN_PS1600+U5_IC_EN+U5_IC_IN {
       settings.level = levelSet_1;
    set level levelSet_1 {
      vil = 0.0 V;
      vih = 4.5 V;
      term = hiz;
    }
  }
  setup digInOut DIGITAL_EXP_SPI_ISEN_U5IN_EN {
       settings.level = levelSet_1;
    set level levelSet_1 {
      vil = 0 V;
      vih = 0 V;
      term = hiz;
    }
  }
}
