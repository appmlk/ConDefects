import java.io.BufferedReader;
import java.io.InputStreamReader;

/* --- ABC338D --- */
public class Main {
	public static void main(String[] args) throws Exception {
		/* --- Input --- */
		var br = new BufferedReader(new InputStreamReader(System.in));
		var sa = br.readLine().split(" ");
		var N = Integer.parseInt(sa[0]);
		var M = Integer.parseInt(sa[1]);
		sa = br.readLine().split(" ");
		var X = new int[M];
		for (var i = 0; i < M; i++) X[i] = Integer.parseInt(sa[i]) - 1; // 0-indexed
		br.close();
		/* --- Process --- */
		// いもす法で封鎖によって遠回りさせる長さが最も短い橋を探す
		var imos = new long[N+1];
		var L = new int[M]; // 始点
		var R = new int[M]; // 終点
		var D1 = new int[M]; // 橋Nを通らない距離
		var D2 = new int[M]; // 橋Nを通る距離
		var P = new int[M]; // 橋Nを通るか
		for (var i = 1; i < M; i++){
			L[i] = X[i-1];
			R[i] = X[i];
			if(L[i] > R[i]){
				var temp = L[i];
				L[i] = R[i];
				R[i] = temp;
			}
			P[i] = R[i] - L[i] > N / 2 ? 1 : 0; // 橋Nを通るか
			D1[i] = Math.abs(L[i] - R[i]);
			D2[i] = N - D1[i];
			var D = Math.abs(D1[i] - D2[i]); // 遠回りさせる長さ
			if(P[i] == 0){
				// 橋Nを通るルートが遠回り
				imos[R[i]] += D;
				imos[N] -= D;
				imos[0] += D;
				imos[L[i]] -=D;
			}else {
				// 橋Nを通らないルートが遠回り
				imos[L[i]] += D;
				imos[R[i]] -= D;
			}
		}
		for(var i=1; i<=N; i++) imos[i] += imos[i-1];
		var max = 0L;
		var block = -1;
		for(var i=0; i<=N; i++){
			if(imos[i] >= max){
				max = imos[i];
				block = i;
			}
		}
		// 橋を封鎖してツアーの長さを求める
		var ans = 0;
		for(var i=1; i<M; i++){
			if(P[i] == 0){
				// 橋Nを通らないルートに封鎖された橋があるか
				if(L[i] <= block && block < R[i]){
					ans += D2[i]; // 橋Nを通らない
				}else{
					ans += D1[i]; // 橋Nを通る
				}
			} else {
				// 橋Nを通るルートに封鎖された橋があるか
				if((R[i] <= block && block < N) || (0 <= block && block < L[i])){
					ans += D1[i]; // 橋Nを通らない
				}else{
					ans += D2[i]; // 橋Nを通る
				}
			}
		}
		/* --- Output --- */
		System.out.println(ans);
		System.out.flush();
	}
}
