import java.util.Scanner;

public class SumMatrixes {
    public static void main(String[] args){
        System.out.println("1. Add matrices");
        System.out.println("2. Multiply matrix to a constant");
        System.out.println("3. Multiply matrices");
        System.out.println("0. Exit");

        Scanner sc = new Scanner(System.in);
        int operation = sc.nextInt();
        switch (operation){
            case (1) :
                System.out.println("Your choice: 1");
                System.out.println("Enter size of first matrix: ");
                int n1 = sc.nextInt();
                int m1 = sc.nextInt();
                double[][] matrix1 = new double[n1][m1];
                System.out.println("Enter first matrix: ");
                readMatrix(matrix1, sc);
                System.out.println("Enter size of second matrix:");
                int n2 = sc.nextInt();
                int m2 = sc.nextInt();
                if(n1 != n2 & m1 != m2){
                    System.out.println();
                    System.out.println("ERROR");
                } else {
                    double[][] matrix2 = new double[n2][m2];
                    System.out.println("Enter second matrix: ");
                    readMatrix(matrix2, sc);
                    printArray(sum(matrix1, matrix2));
                }
                break;
            case (2) :
                System.out.println("Your choice: 2");
                System.out.println("Enter size of matrix: ");
                int n = sc.nextInt();
                int m = sc.nextInt();
                double[][] matrix = new double[n][m];
                System.out.println("Enter matrix: ");
                readMatrix(matrix, sc);
                System.out.println("Enter number:");
                double C = sc.nextDouble();
                printArray(muxToNumber(matrix, C));
                break;
            case (3) :
                System.out.println("Your choice: 3");
                System.out.println("Enter size of first matrix: ");
                int x1 = sc.nextInt();
                int y1 = sc.nextInt();
                matrix1 = new double[x1][y1];
                System.out.println("Enter first matrix: ");
                readMatrix(matrix1, sc);
                System.out.println("Enter size of second matrix:");
                int x2 = sc.nextInt();
                int y2 = sc.nextInt();
                if(y1 != x2){
                    System.out.println();
                    System.out.println("ERROR");
                } else {
                    double[][] matrix2 = new double[x2][y2];
                    System.out.println("Enter second matrix: ");
                    readMatrix(matrix2, sc);
                    printArray(mux(matrix1, matrix2));
                }
                break;
            case (0) :
                System.out.println("Your choice: 0");
                break;
            default:
                break;
        }
    }

    public static void readMatrix(double[][] matrix, Scanner sc){
        for(int row = 0; row<matrix.length; row++){
            for(int col = 0; col < matrix[0].length; col++){
                matrix[row][col] = sc.nextDouble();
            }
        }
    }

    public static double[][] mux(double[][] matrix1, double[][] matrix2){

        double[][] mux = new double[matrix1.length][matrix2[0].length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix2[0].length; j++) {
                for (int k = 0; k < matrix1[0].length; k++) {
                    mux[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return mux;
    }

    public static double[][] muxToNumber(double[][] matrix, double C){
        for(int row = 0; row<matrix.length; row++){
            for(int col = 0; col < matrix[0].length; col++){
                matrix[row][col] = matrix[row][col] * C;
            }
        }
        return matrix;
    }

    public static double[][] sum(double[][] matrix1, double[][] matrix2){
            double[][] sum = new double[matrix1.length][matrix1[0].length];
            for(int row = 0; row<matrix1.length; row++){
                for(int col = 0; col < matrix1[0].length; col++){
                    sum[row][col] = matrix1[row][col]+ matrix2[row][col];
                }
            }
            return sum;
    }


    public static void printArray(double[][] arr){
        for(int row = 0; row<arr.length; row++){
            System.out.println();
            for(int col = 0; col < arr[0].length; col++){
                System.out.printf("%.2f", arr[row][col]);
                System.out.print(" ");
            }
        }
    }
}
/*
        0.1 2.5 3.6
        4.97 0.67 -5.48
        6.32 4.11 -2.05

        2.1 3.2 5.6
        -1.25 0.12 3.14


1 2 4
-3 2 -1
5 0 -1

 0.65 0.67 76.4 23.2
-0.7 -13.1 -7.2 9.2
-0.7 -5.5 -1.5 0.4
-1.0 12.6 0.8 -0.4

5.5 -0.3 -1.2 10.2
-1.0 0.8 0.8 -9.5
-45.5 45.5 56.5 13.7
-10.7 11.9 2.2 1.2

-3724.7525	3752.7965	4368.098	1068.818
238.41	-228.39	-396.2	29.71
65.62	-67.68	-87.43	25.04
-50.22	42.02	55.6	-119.42


-3728.69 3752.62 4367.40 1074.79
246.11 -228.39 -396.20 29.71
73.32 -67.68 -87.43 25.04
-39.22 42.02 55.60 -119.42


        */
