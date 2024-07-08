import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

// ABC360D
// https://atcoder.jp/contests/abc360/tasks/abc360_d
public class Main {
	public static void main(String[] args) throws Exception {
		/* --- Input --- */
		var br = new BufferedReader(new InputStreamReader(System.in));
		var sa = br.readLine().split(" ");
		var N = Integer.parseInt(sa[0]);
		var T = Long.parseLong(sa[1]);
		var S = br.readLine().toCharArray();
		sa = br.readLine().split(" ");
		var X = new long[N];
		for (var i = 0; i < N; i++) X[i] = Long.parseLong(sa[i]);
		br.close();
		/* --- Process --- */
		// 負の方向を向いているアリの初期位置
		var X0 = new ArrayList<Long>();
		for(var i=0; i<N; i++) if (S[i] == '0') X0.add(X[i]);
		Collections.sort(X0);
		// 正の方向を向いているアリについて、負の方向を向いているアリのうち自分の位置〜2*Tの範囲にいるアリの数を数える
		var ans = 0L;
		for (var i = 0; i < N; i++) {
			if (S[i] == '0') continue;
			// 正の方向で一番近いアリのインデックス
			var l = ~Collections.binarySearch(X0, X[i], (a, b) -> (a.compareTo(b) > 0) ? 1 : -1);
			// 正の方向で2*Tの範囲内で一番遠いアリのインデックス
			var r = ~Collections.binarySearch(X0, X[i] + T * 2, (a, b) -> (a.compareTo(b) > 0) ? 1 : -1)-1;
			ans += r - l + 1;
		}
		/* --- Output --- */
		System.out.println(ans);
		System.out.flush();
	}
}
