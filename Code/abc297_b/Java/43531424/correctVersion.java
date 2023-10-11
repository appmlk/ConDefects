import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String input = sc.next();

		int b = input.indexOf("B");
		int b2 = input.indexOf("B", b + 1);
		int r = input.indexOf("R");
		int r2 = input.indexOf("R", r + 1);
		int k = input.indexOf("K");

		if (b < b2 && (b % 2) != (b2 % 2)) {
			if (r < r2 ) {
				if (r < k && k < r2) {
					System.out.println("Yes");
					return;
				}
			}
		}

		System.out.println("No");
	}

}