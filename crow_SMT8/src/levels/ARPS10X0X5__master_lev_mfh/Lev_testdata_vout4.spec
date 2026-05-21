spec Lev_testdata_vout4 {
  action _dcs_fm_VIN1_cfm_VIN2_cfm_VIN3_cfm_VIN4_RS1_cfm_VIN4_RS2_cfm_VIN5_RS1_cfm__md5_f1bb8a0f85bacf10d8ff75df6b4e0806___EV_CFM_VOUT4__VOUT4;
  action _dcs_fm_VOUT1_vfm_VOUT2_RS1_vfm_VOUT2_RS2_vfm_VOUT3_RS1_vfm_VOUT3_RS2_vfm_VOUT4_vfm_VOUT5_vfm_VOUT6_vfm____EV_VFM_VOUT4__VOUT4;
  action _dcs_fm_VOUT4_cfm_P_ALLPINS____EV_CFM_VOUT4__VOUT4;
  action _dcs_fm_VOUT4_vfm_P_ALLPINS____EV_VFM_VOUT4__VOUT4;
  action _dcs_fr_VOUT4_ifr_250u_P_ALLPINS____EV_IFR_VOUT4_250u__VOUT4;
  action _dcs_fr_VOUT4_vfr_80_P_ALLPINS____EV_VFR_VOUT4_80__VOUT4;
  action _dcs_if_ALL_DC_PINS_cf0____EV_CF__ALL_DC_PINS_0__VOUT4;
  action _dcs_if_ALL_VIN_ALL_VOUT_l__0_1_h_0_1____EV_IF__ALL_VIN_ALL_VOUT__0_1_0_1__md5_b4441abcc3f6f3c9bfebc07b973541bc_VOUT4;
  action _dcs_if_ALL_VOUT_l__0_1_h_0_1____EV_IF__ALL_VOUT__0_1_0_1__VOUT4;
  action _dcs_if_VOUT4_cf_200u____EV_CF__VOUT4__200u__VOUT4;
  action _dcs_if_VOUT4_cf0____EV_CF__VOUT4_0__VOUT4;
  action _dcs_if_VOUT4_cf0_P_ALLPINS____EV_CF__VOUT4_0__VOUT4;
  action _dcs_if_VOUT4_l__250u_h_250u_P_ALLPINS____EV_IF__VOUT4__250u_250u__VOUT4;
  action _dcs_if_VOUT4_l__5u_h_5u_P_ALLPINS____EV_IF__VOUT4__5u_5u__VOUT4;
  action _dcs_m_ALL_VOUT_im_avg100____EV_IM_ALL_VOUT_AV_100_VOUT4;
  action _dcs_m_VOUT4_im_avg100_P_ALLPINS____EV_IM_VOUT4_AV_100_VOUT4;
  action _dcs_m_VOUT4_vm_avg16____EV_VM_VOUT4_AV_16_VOUT4;
  action _dcs_m_VOUT4_vm_avg50_P_ALLPINS____EV_VM_VOUT4_AV_50_VOUT4;
  action _dcs_m_VOUT4_vm_avg50_P_ALL_EXP_ITRIP____EV_VM_VOUT4_AV_50_VOUT4;
  action _dcs_mr_VOUT4_imr_5u_P_ALLPINS____EV_IMR_VOUT4_5u__VOUT4;
  action _dcs_vc_ALL_DC_PINS_vcl__2_vch_2____EV_VC__ALL_DC_PINS_vc_2_2__VOUT4;
  action _dcs_vc_VOUT4_vcl__2_vch_2____EV_VC__VOUT4_vc_2_2__VOUT4;
  action _dcs_vc_VOUT4_vcl_0_vch_30_P_ALLPINS____EV_VC__VOUT4_vc0_30__VOUT4;
  action _dcs_vf_ALL_VIN_ALL_VOUT_vf0____EV_VF__ALL_VIN_ALL_VOUT_0__md5_dcf90f34246a9019e310a9e667cb4fc9_VOUT4;
  action _dcs_vf_ALL_VOUT_vf0____EV_VF__ALL_VOUT_0__VOUT4;
  action _dcs_vf_ALL_VOUT_vf1____EV_VF__ALL_VOUT_1__VOUT4;
  action _dcs_vf_VOUT4_vf0_P_ALLPINS____EV_VF__VOUT4_0__VOUT4;
  setup dcVI VOUT4 {
    action iforce _dcs_if_ALL_DC_PINS_cf0____EV_CF__ALL_DC_PINS_0__VOUT4 {
      forceValue = 0 A;
    }
    action iforce _dcs_if_ALL_VIN_ALL_VOUT_l__0_1_h_0_1____EV_IF__ALL_VIN_ALL_VOUT__0_1_0_1__md5_b4441abcc3f6f3c9bfebc07b973541bc_VOUT4 { }
    action iforce _dcs_if_ALL_VOUT_l__0_1_h_0_1____EV_IF__ALL_VOUT__0_1_0_1__VOUT4 { }
    action iforce _dcs_if_VOUT4_cf_200u____EV_CF__VOUT4__200u__VOUT4 {
      forceValue = -0.0002 A;
    }
    action iforce _dcs_if_VOUT4_cf0____EV_CF__VOUT4_0__VOUT4 {
      forceValue = 0 A;
    }
    action iforce _dcs_if_VOUT4_cf0_P_ALLPINS____EV_CF__VOUT4_0__VOUT4 {
      forceValue = 0 A;
    }
    action iforce _dcs_if_VOUT4_l__250u_h_250u_P_ALLPINS____EV_IF__VOUT4__250u_250u__VOUT4 { }
    action iforce _dcs_if_VOUT4_l__5u_h_5u_P_ALLPINS____EV_IF__VOUT4__5u_5u__VOUT4 { }
    action imeas _dcs_m_ALL_VOUT_im_avg100____EV_IM_ALL_VOUT_AV_100_VOUT4 {
      averages = 100;
      irange = 1 A; // No range set in STM7, thus set 1A as default by TACO, Please modify it manually if need!;
      restoreIrange = true;
    }
    action imeas _dcs_m_VOUT4_im_avg100_P_ALLPINS____EV_IM_VOUT4_AV_100_VOUT4 {
      averages = 100;
      irange = 1 A; // No range set in STM7, thus set 1A as default by TACO, Please modify it manually if need!;
      restoreIrange = true;
    }
    action vmeas _dcs_m_VOUT4_vm_avg16____EV_VM_VOUT4_AV_16_VOUT4 {
      averages = 16;
    }
    action vmeas _dcs_m_VOUT4_vm_avg50_P_ALLPINS____EV_VM_VOUT4_AV_50_VOUT4 {
      averages = 50;
    }
    action vmeas _dcs_m_VOUT4_vm_avg50_P_ALL_EXP_ITRIP____EV_VM_VOUT4_AV_50_VOUT4 {
      averages = 50;
    }
    action iforce _dcs_vc_ALL_DC_PINS_vcl__2_vch_2____EV_VC__ALL_DC_PINS_vc_2_2__VOUT4 { }
    action iforce _dcs_vc_VOUT4_vcl__2_vch_2____EV_VC__VOUT4_vc_2_2__VOUT4 { }
    action iforce _dcs_vc_VOUT4_vcl_0_vch_30_P_ALLPINS____EV_VC__VOUT4_vc0_30__VOUT4 { }
    action vforce _dcs_vf_ALL_VIN_ALL_VOUT_vf0____EV_VF__ALL_VIN_ALL_VOUT_0__md5_dcf90f34246a9019e310a9e667cb4fc9_VOUT4 {
      forceValue = 0 V;
      iclamp = 0.02 A; // Default value set by Taco, Please modify it manually!;
    }
    action vforce _dcs_vf_ALL_VOUT_vf0____EV_VF__ALL_VOUT_0__VOUT4 {
      forceValue = 0 V;
      iclamp = 0.02 A; // Default value set by Taco, Please modify it manually!;
    }
    action vforce _dcs_vf_ALL_VOUT_vf1____EV_VF__ALL_VOUT_1__VOUT4 {
      forceValue = 1 V;
      iclamp = 0.02 A; // Default value set by Taco, Please modify it manually!;
    }
    action vforce _dcs_vf_VOUT4_vf0_P_ALLPINS____EV_VF__VOUT4_0__VOUT4 {
      forceValue = 0 V;
      iclamp = 0.02 A; // Default value set by Taco, Please modify it manually!;
    }
  }
}
