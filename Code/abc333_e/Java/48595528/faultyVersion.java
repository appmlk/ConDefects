import java.util.*;

// 後ろからイベントを見ていく（敵の出現を予測し、武器を拾うかどうか決める）
public class Main {

	public static void main(String[] args) {

		// 入力
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.next());
		int[] t = new int[n];
		int[] x = new int[n];
		for (int i = 0; i < n; i++) {
			t[i] = Integer.parseInt(sc.next());
			x[i] = Integer.parseInt(sc.next());
		}

		int[] task = new int[n + 300000];
		boolean[] hirou = new boolean[n];
		for (int i = n - 1; 0 <= i; i--) {
			if (t[i] == 2) {
				task[x[i]]++;
			} else {
				if (1 <= task[x[i]]) {
					task[x[i]]--;
					hirou[i] = true;
				}
			}
		}

		// 敗北したケース
		for (int i = 0; i < n; i++) {
			if (task[i] != 0) {
				System.out.println(-1);
				return;
			}
		}

		// 勝利したケース
		int have = 0;
		int haveMax = 0;
		for (int i = 0; i < n; i++) {
			if (t[i] == 1) {
				have += (hirou[i] ? 1 : 0);
				haveMax = Math.max(haveMax, have);
			} else {
				have--;
			}
		}
		System.out.println(haveMax);
		StringBuilder answer = new StringBuilder();
		for (int i = 0; i < n; i++) {
			if (t[i] == 1) {
				answer.append((hirou[i] ? 1 : 0));
				answer.append(" ");
			}
		}
		System.out.println(answer);
	}
}