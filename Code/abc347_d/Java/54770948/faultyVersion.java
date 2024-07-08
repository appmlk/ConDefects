import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = Integer.parseInt(sc.next());
		int b = Integer.parseInt(sc.next());
		long c = Long.parseLong(sc.next());
		sc.close();

		int pc = Long.bitCount(c);
		if (a + b < pc || (a + b + pc) % 2 == 1 || (a + b + pc) > 120) {
			System.out.println(-1);
		} else {
			long x = 0L;
			int px = 0;
			long y = 0L;
			int d = (a + b - pc) / 2;//重なり部分
			int e = 0;//重なり処理回数
			long z = 1L;
			for (int i = 0; i < 60; i++) {
				if (c % 2L == 1L) {
					if (px < a - d) {
						x += z;
						px++;
					} else {
						y += z;
					}
				} else if (e < d) {
					x += z;
					y += z;
					e++;
				}
				c /= 2L;
				z *= 2L;
			}
			System.out.println(x + " " + y);
		}
	}
}