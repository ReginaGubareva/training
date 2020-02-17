public class Ifmo {
    public static void main(String[] args) {
        int K = 60;
        int N = 9;
        int i = 0;



        while(i < K ){

            int Z = N;
            int sum = 0;

            if (Z > 0) {
                sum = sum + (Z%10);
                Z = Z/10;
            }

            N = N+sum;
            i++;

        }


        System.out.println("N = " + N);
    }
}
