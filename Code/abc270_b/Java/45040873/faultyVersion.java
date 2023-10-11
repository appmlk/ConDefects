import java.util.Scanner;

public class Main {
	public static void main(final String[] args) {
		try (final Scanner sc = new Scanner(System.in)) {
			final int X = Integer.parseInt(sc.next());
			final int Y = Integer.parseInt(sc.next());
			final int Z = Integer.parseInt(sc.next());

			if (X * X <= Y * Y) {
				System.out.println(Math.abs(X));
			} else if (Z * Z <= Y * Y) {
				if ((X ^ Z) >= 0) {
					// 符号が同じ
					System.out.println(Math.abs(X));
				} else {
					// 符号が違う
					System.out.println(Math.abs(X) + Math.abs(Z) * 2);
				}
			} else {
				System.out.println(-1);
			}
		}
	}
}
