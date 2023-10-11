import java.util.Scanner;

public class Main {
	public static void main(final String[] args) {
		try (final Scanner sc = new Scanner(System.in)) {
			final char[] S = sc.next().toCharArray();
			final char[] T = sc.next().toCharArray();
			int ans = 1;
			for (int i = 0; i < S.length; i++) {
				if (S[i] != T[i]) {
					break;
				}
				ans++;
			}
			System.out.println(ans);
		}
	}
}
