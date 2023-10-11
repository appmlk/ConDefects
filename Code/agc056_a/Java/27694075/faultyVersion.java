import java.io.*;
import java.util.*;

public class Main extends PrintWriter {
	Main() { super(System.out); }
	Scanner sc = new Scanner(System.in);
	public static void main(String[] $) {
		Main o = new Main(); o.main(); o.flush();
	}

	byte[][] cc;
	void mark(int i, int j) {
		cc[i][j] = '#';
	}
	void solve(int l, int r) {
		while (r - l + 1 >= 10) {
			// ##..#
			// #..##
			// .....
			// .#.##
			// ##..#
			mark(l, l);
			mark(l, l + 1);
			mark(l + 1, l);
			mark(l, r);
			mark(l + 1, r);
			mark(l + 1, r - 1);
			mark(r, l);
			mark(r, l + 1);
			mark(r - 1, l + 1);
			mark(r, r);
			mark(r - 1, r);
			mark(r - 1, r - 1);
			l += 2;
			r -= 2;
		}
		if (r - l + 1 == 6) {
			// ###...
			// ...###
			// ###...
			// ...###
			// ###...
			// ...###
			mark(l + 0, l + 0); mark(l + 0, l + 1); mark(l + 0, l + 2);
			mark(l + 1, l + 3); mark(l + 1, l + 4); mark(l + 1, l + 5);
			mark(l + 2, l + 0); mark(l + 2, l + 1); mark(l + 2, l + 2);
			mark(l + 3, l + 3); mark(l + 3, l + 4); mark(l + 3, l + 5);
			mark(l + 4, l + 0); mark(l + 4, l + 1); mark(l + 4, l + 2);
			mark(l + 5, l + 3); mark(l + 5, l + 4); mark(l + 5, l + 5);
		} else if (r - l + 1 == 7) {
			// ##...#.
			// ##....#
			// ..###..
			// ..###..
			// ..###..
			// #....##
			// .#...##
			mark(l + 0, l + 0); mark(l + 0, l + 1); mark(l + 0, l + 5);
			mark(l + 1, l + 0); mark(l + 1, l + 1); mark(l + 1, l + 6);
			mark(l + 2, l + 2); mark(l + 2, l + 3); mark(l + 2, l + 4);
			mark(l + 3, l + 2); mark(l + 3, l + 3); mark(l + 3, l + 4);
			mark(l + 4, l + 2); mark(l + 4, l + 3); mark(l + 4, l + 4);
			mark(l + 5, l + 0); mark(l + 5, l + 5); mark(l + 5, l + 6);
			mark(l + 6, l + 1); mark(l + 6, l + 5); mark(l + 6, l + 6);
		} else if (r - l + 1 == 8) {
			// ##...#..
			// #...##..
			// ..##...#
			// ..#...##
			// .#..##..
			// ##..#...
			// ...#..##
			// ..##..#.
			mark(l + 0, l + 0); mark(l + 0, l + 1); mark(l + 0, l + 5);
			mark(l + 1, l + 0); mark(l + 1, l + 4); mark(l + 1, l + 5);
			mark(l + 2, l + 2); mark(l + 2, l + 3); mark(l + 2, l + 7);
			mark(l + 3, l + 2); mark(l + 3, l + 6); mark(l + 3, l + 7);
			mark(l + 4, l + 1); mark(l + 4, l + 4); mark(l + 4, l + 5);
			mark(l + 5, l + 0); mark(l + 5, l + 1); mark(l + 5, l + 4);
			mark(l + 6, l + 3); mark(l + 6, l + 6); mark(l + 6, l + 7);
			mark(l + 7, l + 2); mark(l + 7, l + 3); mark(l + 7, l + 6);
		} else if (r - l + 1 == 9) {
			// ###...
			// ...###
			// ......###
			// ###...
			// ...###
			// ......###
			// ###...
			// ...###
			// ......###
			mark(l + 0, l + 0); mark(l + 0, l + 1); mark(l + 0, l + 2);
			mark(l + 1, l + 3); mark(l + 1, l + 4); mark(l + 1, l + 5);
			mark(l + 2, l + 6); mark(l + 2, l + 7); mark(l + 2, l + 8);
			mark(l + 3, l + 0); mark(l + 3, l + 1); mark(l + 3, l + 2);
			mark(l + 4, l + 3); mark(l + 4, l + 4); mark(l + 4, l + 5);
			mark(l + 5, l + 6); mark(l + 5, l + 7); mark(l + 5, l + 8);
			mark(l + 6, l + 0); mark(l + 6, l + 1); mark(l + 6, l + 2);
			mark(l + 7, l + 3); mark(l + 7, l + 4); mark(l + 7, l + 5);
			mark(l + 8, l + 6); mark(l + 8, l + 7); mark(l + 8, l + 8);
		}
	}
	void main() {
		int n = sc.nextInt();
		cc = new byte[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				cc[i][j] = '.';
		solve(0, n - 1);
		for (int i = 0; i < n; i++)
			println(new String(cc[i]));
	}
}
