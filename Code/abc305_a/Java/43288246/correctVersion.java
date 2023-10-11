import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		double n = Integer.parseInt(sc.next());
		if(n % 5 == 0) {
			System.out.println((int)n);
			return;
		} else {
			System.out.println(Math.round(n / 5.0) * 5);
		}
	}
}