import java.util.*;
import java.util.regex.*;
import java.io.*;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.next());
		String s = sc.next();
		int q = Integer.parseInt(sc.next());

		String t = "";
		for (int i = 0; i < 26; i++) {
			t += (char)(97 + i);
		}

		StringBuilder sb1 = new StringBuilder(t);
		for (int i = 0; i < q; i++) {
			char c = sc.next().charAt(0);
			char d = sc.next().charAt(0);

			for (int j = 0; j < 26; j++) {
				if (t.charAt(j) == c) {
					sb1.setCharAt(j, d);
					t = sb1.toString();
				}
			}
		}

		StringBuilder sb2 = new StringBuilder(s);
		for (int i = 0; i < n; i++) {
			int j = (int)(s.charAt(i) - 'a');
			sb2.setCharAt(i, t.charAt(j));
		}
		s = sb2.toString();

		PrintWriter pw = new PrintWriter(System.out);
		pw.println(s);
		pw.flush();
	}
}
