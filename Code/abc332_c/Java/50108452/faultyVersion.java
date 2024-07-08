// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		int maxM = Integer.parseInt(st.nextToken());
		String s = r.readLine();

		int m = maxM;
		int maxLogo = 0;
		int l = 0;
		for (int i = 0; i < n; i++) {
			int curNum = Character.getNumericValue(s.charAt(i));
			if (curNum == 0) {
				m = maxM;
				l = maxLogo;
			} else if (curNum == 1) {
				if (m == 0) {
					if (l == 0) {
						maxLogo++;
					} else {
						l--;
					}
				}
			} else {
				if (l == 0) {
					maxLogo++;
				} else {
					l--;
				}
			}
		}

		System.out.println(maxLogo);

		pw.close();
	}
}
