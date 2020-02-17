import java.util.*;
import java.io.*;

class AntiQuickSort{
	public static void main(String[] args){
		try(Scanner sc = new Scanner(new FileReader("input.txt"));
		   FileWriter fw = new FileWriter("output.txt")){
			int n = sc.nextInt();
			int[] a = new int[n];
		    
			/*if (n > 0) a[0] = 1;
			if (n > 1) a[1] = 2;
 
     
			for (int i = 3; i <= n; i++) {
				
				 int key = (i - 1) / 2;         
				 int t = a[key];         
				 a[key] = i;         
				 a[i - 1] = t;
			}*/
			
			for(int i = 0; i < n; i++){
				a[i] = i + 1;
			}
			
			for(int i = n; i > 2; i--){
				int key = (i)/2;
				
				int buf = a[key];
				a[key] = i;
				a[key-1] = buf;
			}

		   
		    
		    fw.write(arrayAsString(a));
			System.out.println("killer array: " + arrayAsString(a));
			
		} catch (IOException e){
			e.getMessage();
		}
		
	
	}
	
	
	public static String arrayAsString(int[] a){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < a.length; i++){
			sb.append(a[i] + " ");
		}
		return sb.toString();
	}
}
