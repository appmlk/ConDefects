
import java.util.Scanner;

public class Main {

	public static void main(String[] args)  {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		int d = sc.nextInt();
		
		if(Math.abs(b - c) >= 2 || ((b == 0) && (c == 0) && (a != 0) && (b != 0))) {
			System.out.println("No");
		}
		else {
			System.out.println("Yes");
		}
	}
}