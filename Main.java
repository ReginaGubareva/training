import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(new FileReader("input.txt"));
            FileWriter fw = new FileWriter("output.txt")){

            int n = sc.nextInt();
            int k = sc.nextInt();
            int[] a = new int[n];

            for(int i = 0; i < n; i++){
                a[i] = sc.nextInt();
            }

            System.out.println(isSortPossible(a, k));
            fw.write(isSortPossible(a, k));

        } catch (IOException e){
            e.getMessage();
        }
    }

    public static String isSortPossible(int[] a, int k){
        if(k == 1 || a.length == 1){
            return "YES";
        } else {
            int count = 0;

            for (int i = 0; i < a.length - 1; i++) {
                for (int j = i + 1; j < a.length; j++) {
                    if ((j - count - k) == 0 || (j - count - k) % k == 0) {
                        count++;
                        break;
                    }
                }
            }

            if(count == 0){
                return "NO";
            } else {
                return "YES";
            }
        }
    }



    /*public static String isSortPossible(int[] a, int k){
        if(k == 1 || a.length == 1){
            return "YES";
        } else {
            boolean flag = true;

            for (int i = 0; i < a.length - 1; i++) {
                int min = i;
                for (int j = i + 1; j < a.length; j++) {
                    if (a[j] < a[min]) {
                        min = j;
                    }
                }

                if ((min - i) % k != 0) {
                    flag = false;
                    break;
                }

                int tmp = a[i];
                a[i] = a[min];
                a[min] = tmp;

            }

            if(flag == true){
                return "YES";
            } else {
                return "NO";
            }
        }
    }*/


}