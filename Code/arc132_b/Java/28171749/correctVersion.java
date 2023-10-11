import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String[] sa = br.readLine().split(" ");
		int[] p = new int[n];
		for (int i = 0; i < n; i++) {
			p[i] = Integer.parseInt(sa[i]) - 1;
		}
		br.close();

		int s = 0;
		for (int i = 0; i < n; i++) {
			if (p[i] == 0) {
				s = i;
				break;
			}
		}

		boolean flg = true;
		for (int i = 1; i < n; i++) {
			if (p[i - 1] + 1 != p[i] && p[i - 1] - n + 1 != p[i]) {
				flg = false;
				break;
			}
		}

		int ans1 = 0;
		int ans2 = 0;
		if (flg) {
			ans1 = s;
			ans2 = 1 + (n - 1 - s + 1) % n + 1;
		} else {
			ans1 = (s + 1) % n + 1;
			ans2 = 1 + (n - 1 - s);
		}
		System.out.println(Math.min(ans1, ans2));
	}
}
