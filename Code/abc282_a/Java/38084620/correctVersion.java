import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
      	int k = sc.nextInt();
      	
      	for(int i = 0; i < k; i++) {
        	System.out.print((char)('A' + i));
        }
    }
}