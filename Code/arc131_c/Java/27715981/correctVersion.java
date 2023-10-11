import java.io.*;
import java.util.*;

public class Main extends PrintWriter {
	Main() { super(System.out, true); }
	Scanner sc = new Scanner(System.in);
	public static void main(String[] $) {
		Main o = new Main(); o.main(); o.flush();
	}

	void main() {
		int n = sc.nextInt();
		int[] aa = new int[n];
		int x = 0;
		for (int i = 0; i < n; i++)
			x ^= aa[i] = sc.nextInt();
		boolean win = false;
		for (int i = 0; i < n; i++)
			if (aa[i] == x) {
				win = true;
				break;
			}
		println(win || n % 2 == 1 ? "Win" : "Lose");
	}
}
