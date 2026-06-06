package Crow_SMT8.test_method;

public class Global {

    public static double VCC = 0;
    public static double Max_Curr = 0;


    public static void devVariantInit() {

 //       double devvaar = TestSetup.getUserDouble("var");
//        double devCur = TestSetup.getUserDouble("Curr_Varient");
        int devvar=50;
        if (devvar == 20) {
            Max_Curr = 8;
            VCC = 12;
        } else {
            Max_Curr = 4;
            VCC = 28;
        }

    }

}
