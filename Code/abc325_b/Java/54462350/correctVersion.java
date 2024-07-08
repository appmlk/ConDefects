import java.util.Scanner;

public class Main{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.next());
		long[] sum = new long[24];
		for (int i = 0; i < n; i++) {
			long w = Long.parseLong(sc.next());
			int x = Integer.parseInt(sc.next());
			int s = (33 - x) % 24;
			int e = (42 - x) % 24;
			if (s < e) {
				for (int t = s; t < e; t++) {
					sum[t] += w;
				}
			} else {
				for (int t = s; t < 24; t++) {
					sum[t] += w;
				}
				for (int t = 0; t < e; t++) {
					sum[t] += w;
				}
			}
		}
		sc.close();

		long max = sum[0];
		for (int i = 1; i < 24; i++) {
			if (max < sum[i]) {
				max = sum[i];
			}
		}
		System.out.println(max);
	}
}