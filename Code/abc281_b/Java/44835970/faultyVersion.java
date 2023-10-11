import java.util.Scanner;

public class Main {
	public static void main(final String[] args) {
		try (final Scanner sc = new Scanner(System.in)) {
			final String S = sc.next();
			if (S.length() != 8) {
				System.out.println("No");
				return;
			}

			if (Character.isDigit(S.charAt(0)) || Character.isDigit(S.charAt(S.length() - 1))) {
				System.out.println("No");
				return;
			}

			if (Character.isDigit(S.charAt(1)) && Character.isDigit(S.charAt(S.length() - 2))) {
				System.out.println("No");
				return;
			}
			try {
				final int numS = Integer.parseInt(S.substring(1, S.length() - 1));
				if (100000 > numS || numS > 999999) {
					System.out.println("No");
					return;
				}
			} catch (final NumberFormatException e) {
				System.out.println("No");
				return;
			}

			System.out.println("Yes");
		}
	}
}
