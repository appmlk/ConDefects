import java.util.Scanner;
 
public class Main {
 
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int num = scan.nextInt();
		
		if(num >= 42) num += 1;
		
		System.out.printf("AGC%03d", num);
		
	}
}