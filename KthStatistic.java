import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class KthStatistic {

    public static void main(String[] args) {
        try(Scanner sc = new Scanner(new FileReader("input.txt"));
            FileWriter fw = new FileWriter("output.txt")){

            int n = sc.nextInt();
            int[] a = new int[n];

            int k1 = sc.nextInt()-1;
            int k2 = sc.nextInt()-1;
            int A = sc.nextInt();
            int B = sc.nextInt();
            int C = sc.nextInt();
            a[0] = sc.nextInt();
            a[1] = sc.nextInt();

            for(int i = 2; i < n; i++){
                a[i] = A*a[i-2] + B*a[i-1] + C;
            }

            System.out.println("array1: " + arrayAsString(a));

            modifiedQSort(a, 0, a.length-1, k1, k2);
            System.out.println("array2: " + arrayAsString(a));

            StringBuilder stringBuilder = new StringBuilder();
            if(k1 == k2){
                stringBuilder.append(a[k1]).append(" ");
            } else {
                for (int i = k1; i <= k2; i++) {
                    stringBuilder.append(a[i]).append(" ");
                }
            }


            System.out.println(stringBuilder.toString());
            fw.write(stringBuilder.toString());

        } catch (IOException e){
            e.getMessage();
        }
    }

    public static void modifiedQSort(int[] a, int left, int right, int k1, int k2){
        int i = left;
        int j = right;
        int key = a [(left + right) / 2];

        while (i <= j) {
            while (a[i] < key) { ++i; }
            while (a[j] > key) { --j; }

            if (i <= j) {
                swap(a, i, j);
                ++i;
                --j;
            }
        }

        if (left < j && j >= k1) {
            modifiedQSort(a, left, j, k1, k2);
        }

        if (right > i && i <= k2) {
            modifiedQSort(a, i, right, k1, k2);
        }
    }

    public static String arrayAsString(int[] a){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < a.length; i++){
            sb.append(a[i] + " ");
        }
        return sb.toString();
    }

    public static void swap(int[] a, int i, int j){
        int buf = a[i];
        a[i] = a[j];
        a[j] = buf;
    }
}




//    public static int kthOrderStatistic(int[] a, int l, int r, int k){
//        int key = a[((l+r)/2)];
//
//        int i = l;
//        int j = r;
//
//        while(i <= j){
//
//            while (a[i] < key) { i++; }
//            while (key < a[j]) { j--;}
//
//            if(i <= j){
//                swap(a, i, j);
//                i++;
//                j--;
//            }
//        }
//
//        if( l <= k && k <= j) {
//            return kthOrderStatistic(a, l, j, k);
//        } else if( i <= k && k <= r){
//            return kthOrderStatistic(a, i, r, k);
//        }
//
//        return a[k];
//    }
