import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		
		long a = Integer.parseInt(sc.next());
		long b = Integer.parseInt(sc.next());
		long x = Integer.parseInt(sc.next());
		
		int count = 0;
		while(b > a) {
			b /= x;
			count++;
		}
		System.out.println(count);
	}
}