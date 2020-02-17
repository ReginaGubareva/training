import java.util.ArrayList;

public class TwoArrays {
    public static void main(String[] args) {
        int p = 0;
        int i = 0;
        int[] A = {5, 7, 4, 8};
        int[] B = new int[5];
        int N = 5;

        while(i<=3){
            B[i] = (A[i]*N +p)%9;
            p = (A[i]*N + p)/9;
            i++;
        }
        B[4] = p;

        System.out.println(arrayAsString(B));

    }

    public static String arrayAsString(int[] a){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < a.length; i++){
            sb.append(a[i] + " ");
        }
        return sb.toString();
    }
}
