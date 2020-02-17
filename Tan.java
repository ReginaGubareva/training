import static java.lang.Math.abs;
import static java.lang.Math.log10;

public class Tan {
    public static void main(String[] args) {
        double sum = 0;
        double mult = 1;
        double[] arr = {0.0524, 0.0874, 0.1227, 0.1583, 0.1943, 0.2308, 0.2679, 0.3057, 0.3443, 0.3838, 0.4244, 0.4663,
                        0.5095, 0.5543, 0.6008, 0.6494, 0.7002, 0.7535, 0.8097, 0.8692, 0.9325, 1, 1.0723, 1.1503,
                        1.2348, 1.3270, 1.4281, 1.5398, 1.6642, 1.8040, 1.9626, 1.1445, 2.3558, 2.6050, 2.9042, 3.2708,
                        3.7320, 4.3314, 5.1445, 6.3137, 8.1443, 11.4300, 19.0811};

        for(int i = 0; i<arr.length; i++){
            mult *= arr[i];
            sum += arr[i];
        }

//        for(int i = 3; i <= 87; i=i+2){
//
//            //System.out.println("lg(tan( " + i + ")): " + Math.tan(i)));
//            double tan = Math.tan(i);
//
//            //System.out.println("tan( " + i + "): " + tan);
//            sum += Math.log10(tan);
//        }

        double result = -Math.log10(mult);
        System.out.println("sum: " + sum);
        System.out.println("mult: " + mult);

        System.out.println("result: " + result);
    }
}
