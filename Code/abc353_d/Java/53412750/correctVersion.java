import java.io.BufferedReader;
import java.io.InputStreamReader;

// ABC353D
// https://atcoder.jp/contests/abc353/tasks/abc353_d
public class Main {
	private static final long MOD = 998244353L;
	private static final int M = 11; // 制約上与えられるA[i]の最大桁数

	public static void main(String[] args) throws Exception {
		/* --- Input --- */
		var br = new BufferedReader(new InputStreamReader(System.in));
		var N = Integer.parseInt(br.readLine());
		var sa = br.readLine().split(" ");
		var A = new long[N];
		for (var i = 0; i < N; i++) A[i] = Long.parseLong(sa[i]);
		br.close();
		/* --- Process --- */
		// Arrays.sort(A); // f(x,y)はxyの順序を入れ替えると結果が変わるためソートNG
		// (1)yに指定されて等倍で加算される分を計算：i番目はi-1回加算される(0-indexedならi回)
		var ans = 0L;
		for (var i = 1; i < N; i++) ans += A[i] * i % MOD;
		// (2)xに指定されて10^*倍で加算される分を計算：i番目はi番目より後ろの要素の桁数だけ10^*倍して加算される
		// pow[i]：10^i
		var pow = new long[M + 1];
		pow[1] = 1L;
		for (var i = 2; i < M + 1; i++) pow[i] = pow[i - 1] * 10L;
		// K[i][j]：i番目までのj桁の要素の数
		var K = new int[N][M];
		for (var i = 0; i < N; i++) {
			if (i != 0) System.arraycopy(K[i - 1], 0, K[i], 0, M);
			for (var j = 2; j < pow.length; j++) {
				if (A[i] < pow[j]) {
					K[i][j - 1]++;
					break;
				}
			}
		}
		// K[N - 1][j] - K[i][j]：i番目より後ろのj桁の要素の数
		for (var i = 0; i < N; i++) {
			for (var j = 1; j < M; j++) {
				var add = (A[i] % MOD) * (pow[j + 1] % MOD) % MOD; // A[i]を10^(j+1)倍した値が加算される
				add = add * (K[N - 1][j] - K[i][j]) % MOD; // i番目より後ろのj桁の要素の数だけ繰り返し加算される
				ans = (ans + add) % MOD;
			}
		}
		/* --- Output --- */
		System.out.println(ans);
		System.out.flush();
	}
}
