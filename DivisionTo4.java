import java.util.Scanner;

public class DivisionTo4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int max = 0;

        while (sc.hasNextInt()){
            int n = sc.nextInt();
            if( n % 4 == 0 & n > max){
                max = n;
            }
        }

        System.out.println("max: " + max);

    }

}
