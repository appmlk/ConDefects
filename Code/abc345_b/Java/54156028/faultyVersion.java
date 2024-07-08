import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long x = sc.nextLong();
		sc.close();
		long y = 0;
		if (x % 10 == 0) {
			y = x / 10;
		} else {
			y = x / 10 + 1;
		}

		System.out.println(y);
	}
}