import java.util.*;

public class Main {
	private static final Scanner SC = new Scanner(System.in);
	public static void main(String[] args) {
		new Main().solve();
	}

	private int calc(int a, int b, int c, int x) {
		boolean rest = false;
		int last = -1, ans = 0;
		for (int i = 0; i < x; ++i) {
			if (!rest) {
				ans += b;
				if (i - last == a) {
					last = i;
					rest = !rest;
				}
			} else {
				if (i - last == c) {
					last = i;
					rest = !rest;
				}
			}
		}
		return ans;
	}

	private void solve() {
		int[] f = new int[6];
		for (int i = 0; i < 6; ++i) {
			f[i] = SC.nextInt();
		}
		int x = SC.nextInt();
		int a = calc(f[0], f[1], f[2], x), b = calc(f[3], f[4], f[5], x);
		if (a == b) {
			System.out.println("Draw");
		} else if (a > b) {
			System.out.println("Takahashi");
		} else {
			System.out.println("Aoki");
		}
	}
}