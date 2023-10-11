import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		double a = sc.nextInt();
		double b = sc.nextInt();
		double c = sc.nextInt();
		int x = sc.nextInt();
		
		if (x <= a) {
			out.println(1);
		} else if (x > a && x <= b) {
			out.println(c/(b-a));
		} else {
			out.println(0);
		}
		
		out.flush();
	}

}