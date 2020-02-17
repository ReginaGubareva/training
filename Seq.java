import java.util.ArrayList;

public class Seq {

    public static void main(String[] args) {
        ArrayList<Integer> seq = new ArrayList<>();

        int N = 10;
        seq.add(1);
        seq.add(2);

        for(int i = 2; i < 1200; i++){
            int n = seq.get(i-1) + 3;
            if(n < N){
                seq.add(n);
            } else {
                seq.add(n-N);
            }
        }

        for(int i = 0; i < 10; i ++){
            System.out.print(seq.get(i) + " ");
        }

        System.out.println();
        for(int i = 0; i < 50; i++){
            System.out.print(seq.get(i) + " ");
        }
        System.out.println();
        System.out.println("Element 1000: " + seq.get(1000));
        System.out.println("Element 1111: " + seq.get(1111));
    }
}
