spec awg {
    action Source_Waveform;

    signal LINE_MIC1_P = EN0_PS1600;
    signal LINE_MIC2_P = FCB_PS1600;

    waveform sine inputWaveform {
        frequency = 996.0275801418871Hz;
        sampleRate = 10MHz;
        samples = 2000000;
    }

    setup awg LINE_MIC1_P + LINE_MIC2_P {
        config.options = differential;
        connect = true;
        disconnect = false;
        amplitudeRange = 1.2V;

        action sourceWaveform Source_Waveform {
            waveform = inputWaveform;
            amplitude = 1.185V;
            offset = 0V;
            bandwidth = 1MHz;
            repeatCount = 1;
        }
    keepAlive = true;



}
}
