spec Lev_testdata_vout1 {
  action _dcs_fm_VIN1_cfm_VIN2_cfm_VIN3_cfm_VIN4_RS1_cfm_VIN4_RS2_cfm_VIN5_RS1_cfm__md5_f1bb8a0f85bacf10d8ff75df6b4e0806___EV_CFM_VOUT1__VOUT1;
  action _dcs_fm_VIN1_vfm_VOUT1_vfm_P_ALLPINS____EV_VFM_VOUT1__VOUT1;
  action _dcs_fm_VOUT1_vfm_VOUT2_RS1_vfm_VOUT2_RS2_vfm_VOUT3_RS1_vfm_VOUT3_RS2_vfm_VOUT4_vfm_VOUT5_vfm_VOUT6_vfm____EV_VFM_VOUT1__VOUT1;
  action _dcs_if_ALL_DC_PINS_cf0____EV_CF__ALL_DC_PINS_0__VOUT1;
  action _dcs_if_ALL_VIN_ALL_VOUT_l__0_1_h_0_1____EV_IF__ALL_VIN_ALL_VOUT__0_1_0_1__md5_b4441abcc3f6f3c9bfebc07b973541bc_VOUT1;
  action _dcs_if_ALL_VOUT_l__0_1_h_0_1____EV_IF__ALL_VOUT__0_1_0_1__VOUT1;
  action _dcs_if_VOUT1_cf_200u____EV_CF__VOUT1__200u__VOUT1;
  action _dcs_if_VOUT1_cf0____EV_CF__VOUT1_0__VOUT1;
  action _dcs_m_ALL_VOUT_im_avg100____EV_IM_ALL_VOUT_AV_100_VOUT1;
  action _dcs_m_CONTY_ODD_AVI_im_avg16_P_ALLPINS____EV_IM_CONTY_ODD_AVI_AV_16_VOUT1;
  action _dcs_m_VOUT1_vm_avg16____EV_VM_VOUT1_AV_16_VOUT1;
  action _dcs_mr_CONTY_ODD_AVI_imr_2u_P_ALLPINS____EV_IMR_CONTY_ODD_AVI_2u__VOUT1;
  action _dcs_vc_ALL_DC_PINS_vcl__2_vch_2____EV_VC__ALL_DC_PINS_vc_2_2__VOUT1;
  action _dcs_vc_VOUT1_vcl__2_vch_2____EV_VC__VOUT1_vc_2_2__VOUT1;
  action _dcs_vf_ALL_VIN_ALL_VOUT_vf0____EV_VF__ALL_VIN_ALL_VOUT_0__md5_dcf90f34246a9019e310a9e667cb4fc9_VOUT1;
  action _dcs_vf_ALL_VOUT_vf0____EV_VF__ALL_VOUT_0__VOUT1;
  action _dcs_vf_ALL_VOUT_vf1____EV_VF__ALL_VOUT_1__VOUT1;
  action _dcs_vf_CONTY_ODD_AVI_vf_0_1_P_ALLPINS____EV_VF__CONTY_ODD_AVI__0_1__VOUT1;
  action _dcs_vf_CONTY_ODD_AVI_vf0_1_P_ALLPINS____EV_VF__CONTY_ODD_AVI_0_1__VOUT1;
  action _dcs_vf_CONTY_ODD_AVI_vf0_P_ALLPINS____EV_VF__CONTY_ODD_AVI_0__VOUT1;
  setup dcVI VOUT1 {
    action iforce _dcs_if_ALL_DC_PINS_cf0____EV_CF__ALL_DC_PINS_0__VOUT1 {
      forceValue = 0 A;
    }
    action iforce _dcs_if_ALL_VIN_ALL_VOUT_l__0_1_h_0_1____EV_IF__ALL_VIN_ALL_VOUT__0_1_0_1__md5_b4441abcc3f6f3c9bfebc07b973541bc_VOUT1 { }
    action iforce _dcs_if_ALL_VOUT_l__0_1_h_0_1____EV_IF__ALL_VOUT__0_1_0_1__VOUT1 { }
    action iforce _dcs_if_VOUT1_cf_200u____EV_CF__VOUT1__200u__VOUT1 {
      forceValue = -0.0002 A;
    }
    action iforce _dcs_if_VOUT1_cf0____EV_CF__VOUT1_0__VOUT1 {
      forceValue = 0 A;
    }
    action imeas _dcs_m_ALL_VOUT_im_avg100____EV_IM_ALL_VOUT_AV_100_VOUT1 {
      averages = 100;
      irange = 1 A; // No range set in STM7, thus set 1A as default by TACO, Please modify it manually if need!;
      restoreIrange = true;
    }
    action imeas _dcs_m_CONTY_ODD_AVI_im_avg16_P_ALLPINS____EV_IM_CONTY_ODD_AVI_AV_16_VOUT1 {
      averages = 16;
      irange = 1 A; // No range set in STM7, thus set 1A as default by TACO, Please modify it manually if need!;
      restoreIrange = true;
    }
    action vmeas _dcs_m_VOUT1_vm_avg16____EV_VM_VOUT1_AV_16_VOUT1 {
      averages = 16;
    }
    action iforce _dcs_vc_ALL_DC_PINS_vcl__2_vch_2____EV_VC__ALL_DC_PINS_vc_2_2__VOUT1 { }
    action iforce _dcs_vc_VOUT1_vcl__2_vch_2____EV_VC__VOUT1_vc_2_2__VOUT1 { }
    action vforce _dcs_vf_ALL_VIN_ALL_VOUT_vf0____EV_VF__ALL_VIN_ALL_VOUT_0__md5_dcf90f34246a9019e310a9e667cb4fc9_VOUT1 {
      forceValue = 0 V;
      iclamp = 0.02 A; // Default value set by Taco, Please modify it manually!;
    }
    action vforce _dcs_vf_ALL_VOUT_vf0____EV_VF__ALL_VOUT_0__VOUT1 {
      forceValue = 0 V;
      iclamp = 0.02 A; // Default value set by Taco, Please modify it manually!;
    }
    action vforce _dcs_vf_ALL_VOUT_vf1____EV_VF__ALL_VOUT_1__VOUT1 {
      forceValue = 1 V;
      iclamp = 0.02 A; // Default value set by Taco, Please modify it manually!;
    }
    action vforce _dcs_vf_CONTY_ODD_AVI_vf_0_1_P_ALLPINS____EV_VF__CONTY_ODD_AVI__0_1__VOUT1 {
      forceValue = -0.1 V;
      iclamp = 0.02 A; // Default value set by Taco, Please modify it manually!;
    }
    action vforce _dcs_vf_CONTY_ODD_AVI_vf0_1_P_ALLPINS____EV_VF__CONTY_ODD_AVI_0_1__VOUT1 {
      forceValue = 0.1 V;
      iclamp = 0.02 A; // Default value set by Taco, Please modify it manually!;
    }
    action vforce _dcs_vf_CONTY_ODD_AVI_vf0_P_ALLPINS____EV_VF__CONTY_ODD_AVI_0__VOUT1 {
      forceValue = 0 V;
      iclamp = 0.02 A; // Default value set by Taco, Please modify it manually!;
    }
  }
}
