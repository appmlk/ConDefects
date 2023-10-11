import java.util.Scanner;

public class Main {
	public static void main(final String[] args) {
		try (final Scanner sc = new Scanner(System.in)) {
			final int H = Integer.parseInt(sc.next());
			final int M = Integer.parseInt(sc.next());
			int a = H / 10;
			int b = H % 10;
			int c = M / 10;
			int d = M % 10;

			while (!isMisleadingTime(a, b, c, d)) {
				if (c == 5 && d == 9) {
					c = 0;
					d = 0;
					if (a == 2 && b == 3) {
						a = 0;
						b = 0;
					} else if (b == 9) {
						a++;
						b = 0;
					} else {
						b++;
					}
				} else if (d == 9) {
					c++;
					d = 0;
				} else {
					d++;
				}
			}
			System.out.println(a + "" + b + " " + c + "" + d);
		}
	}

	private static boolean isMisleadingTime(final int a, final int b, final int c, final int d) {
		if (b > 2) {
			return false;
		}
		if (a == 2 && c > 3) {
			return false;
		}
		if (a > 3) {
			return false;
		}
		return true;
	}
}
