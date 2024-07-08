import java.util.*;
import java.io.*;


class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.next());

		PrintWriter pw = new PrintWriter(System.out);
		for (int i = 0; i < n + 1; i++) {
			for (int j = 0; j < n - i + 1; j++) {
				for (int k = 0; k < n - j - i + 1; k++) {
					pw.println(String.valueOf(i) + " " + String.valueOf(j) + " " + String.valueOf(k));
				}
			}
		}
		pw.flush();
	}
}
