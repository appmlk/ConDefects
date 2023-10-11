import java.util.Scanner;

public class Main {
	static long min = -1L;

	public static void main(final String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			final String S = sc.next();
			final long N = sc.nextLong();
			if (Long.parseLong(S.replace('?', '0'), 2) > N) {
				System.out.println(min);
				return;
			}

			atmarkReplace(S, N);

			System.out.println(min);
		}
	}

	public static void atmarkReplace(final String S, final long N) {
		if (!S.contains("?")) {
			min = Long.parseLong(S, 2);
			return;
		}
		if (Long.parseLong(S.replaceFirst("\\?", "1").replace('?', '0'), 2) < N) {
			atmarkReplace(S.replaceFirst("\\?", "1"), N);
		} else {
			atmarkReplace(S.replaceFirst("\\?", "0"), N);
		}

	}
}
