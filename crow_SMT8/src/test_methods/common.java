package test_methods;   // same package as your test methods

import xoc.dsa.IDeviceSetup;
import xoc.dsa.ISetupPattern;

public class common {

    // ?? Common pattern creation
    public static ISetupPattern createBasicPattern(IDeviceSetup ds, String signalGroup) {



        ISetupPattern pat = ds.createPattern(1, signalGroup);

        pat.addVector("1111");
        pat.addVector("0111");
        pat.addVector("1100");

        return pat;
    }


    public static void printMessage(String msg) {
        System.out.println("[COMMON] " + msg);
    }
}
