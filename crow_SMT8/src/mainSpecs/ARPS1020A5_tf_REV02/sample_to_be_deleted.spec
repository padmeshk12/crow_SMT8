
spec sample_to_be_deleted {
    action vforce_meas;

    setup dcVI OFF_PS1600{
        connect =  true;
        connectMode =  standard;
        disconnectMode =  safe;
        protection.disconnectPulldownState =  true;
        level.vrange =  2V;
        level.irange =  40e-3A;
        action vforce vforce_meas{
            forceValue = 1V;
            iclamp = 40e-3A;
            irange = 40e-3A;
        }

    }


}
