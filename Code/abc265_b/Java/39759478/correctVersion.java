// package abc;

import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    static PrintWriter pw = new PrintWriter(System.out);

    public static void main(String[] args) {

	Scanner scanner = new Scanner(System.in);

	int n = scanner.nextInt();
	int m = scanner.nextInt();
	long t = scanner.nextInt();

	int[] a = new int[n];
	int[] bonus = new int[n];
	for (int i = 0; i < n - 1; i++) {
	    a[i] = scanner.nextInt();
	}
	for (int i = 0; i < m; i++) {
	    int x = scanner.nextInt();
	    int y = scanner.nextInt();
	    bonus[x - 1] = y;
	}

	int now = 0;
	for (int i = 0; i < n; i++) {
	    t += bonus[now];
	    t -= a[i];
	    now = i + 1;
	    if (t <= 0) {
		System.out.println("No");
		System.exit(0);
	    }
	}

	System.out.println("Yes");

	pw.close();
	scanner.close();
    }

}
