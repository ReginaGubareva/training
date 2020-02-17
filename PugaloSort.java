import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PugaloSort {

    public static void main(String[] args) {
        try(Scanner sc = new Scanner(new FileReader("/home/ren/IdeaProjects/training/src/input.txt"));
            FileWriter fw = new FileWriter("/home/ren/IdeaProjects/training/src/output.txt")){

            int n = sc.nextInt();
            int k = sc.nextInt();
            int[] a = new int[n];

            for(int i = 0; i < n; i++){
                a[i] = sc.nextInt();
            }

            pugaloSort(a, n, k);

            if( isSorted(a)){
                System.out.println("NO");
                fw.write("NO");
            } else {
                System.out.println("YES");
                fw.write("YES");
            }


        } catch (IOException e){
            e.getMessage();
        }
    }

    public static boolean isSorted(int[] a){
        for (int i = 1; i < a.length; ++i) {
            if (a[i] < a[i - 1])
                return true;
        }
        return false;
    }

    public static void pugaloSort(int[] a, int n, int k) {
        for (int i = 0; i < k; i++) {
            if ((i + k) >= n) break;
            int finEl = ((n - 1) / k) * k + i;
            if (finEl >= n)
                finEl -= k;
            System.out.println(finEl);
            quickSort (a, i, finEl, k);

        }
    }

    public static void quickSort(int[] a, int l, int r, int k){

        int key = a[l];

        int i = l;
        int j = r;

        while(i <= j){
            while (a[i] < key) { i+=k;}
            while (key < a[j]) { j-=k;}

            if(i <= j){
                swap(a, i, j);
                i += k;
                j -= k;
            }
        }
        
        if( l < j) {
            quickSort(a, l, j, k);
        }
        if( i < r){
            quickSort(a, i, r, k);
        }

    }

    public static void swap(int[] a, int i, int j){
        int buf = a[i];
        a[i] = a[j];
        a[j] = buf;
    }

    public static String arrayAsString(int[] a){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < a.length; i++){
            sb.append(a[i] + " ");
        }
        return sb.toString();
    }
}
