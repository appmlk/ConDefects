import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main {

	static long mod = 1000000007;
	static long INF = Long.MAX_VALUE;
	static String Yes = "Yes";
	static String No = "No";
	static String LF = "\n";

	static String Grid[];
	static Queue<Long> AD = new ArrayDeque<Long>();
	static Queue<Long> PQ = new PriorityQueue<Long>();
	static TreeSet<Integer> TS = new TreeSet<Integer>();
	static LinkedHashMap<Long, Long> LHM = new LinkedHashMap<Long, Long>();
	static TreeMap<Long, Long> Imos = new TreeMap<Long, Long>();
	static class oneNodeList extends HashMap<Integer, Integer>{}
	static oneNodeList[] edgeList;
	static long DPDistance[];

	static StringBuffer sb = new StringBuffer();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		// AtCoder_Temp
		int N = Integer.parseInt(sc.next());
		int A[] = new int[N+1];

		for(int i=1; i<=N; i++) {
			A[i] = Integer.parseInt(sc.next());
		}

		long cnt = 0;
		long Ans = 0;
		// 一致の組
		for(int i=1; i<=N; i++) {
			if(A[i] == i) {
				cnt++;
				A[i] = 0;
			}
		}
		Ans += (cnt*(cnt-1))/2;

		// ペアの組
		for(int i=1; i<=N; i++) {
			if(A[i] != 0) {
				if(A[A[i]] == i) {
					Ans++;
					A[A[i]] = 0;
					A[i] = 0;
				}
			}
		}

		System.out.println(Ans);

		// Scannerクラスのインスタンスをクローズ
		sc.close();
	}

	/**
	 * 素因数分解
	 * @param N 合成数
	 */
	static void PrimeFactorization() {
		// AtCoder_Temp
		long N = Long.parseLong(sc.next());

		Double NSqrt = Math.sqrt(N);
		HashMap<Long, Long> HM = new HashMap<Long, Long>();
		for(long i=2; i<=NSqrt; i++) {
			while(N%i == 0) {
				if(HM.containsKey(i)) {
					HM.put(i, HM.get(i)+1);
				} else {
					HM.put(i, (long) 1);
				}
				N /= i;
			}
		}
		if(N != 1) {
			if(HM.containsKey(N)) {
				HM.put(N, HM.get(N)+1);
			} else {
				HM.put(N, (long) 1);
			}
		}

		long Ans = 1;
/*
		for(Entry<Long, Long> e : HM.entrySet() ) {
			long K = e.getKey();
			long V = e.getValue();
		}
*/
		System.out.println(Ans);
	}

	/**
	 * HashMap in HashMap
	 * @param N 要素数
	 */
	static void MapInMap(int N) {

		HashMap<Integer, HashMap<Integer, Integer>> B = new HashMap<Integer, HashMap<Integer, Integer>>();
		for(int i=0; i<N; i++) {
			int AWk = Integer.parseInt(sc.next());
			if(B.containsKey(AWk)) {
				HashMap<Integer, Integer> HM = B.get(AWk);
				HM.put(HM.size()+1, i+1);
				B.put(AWk, HM);
			} else {
				HashMap<Integer, Integer> HM = new HashMap<Integer, Integer>();
				HM.put(1, i+1);
				B.put(AWk, HM);
			}
		}
	}
	/**
	 * 順列全探索
	 * @param N 要素数
	 */
	static void SearchPermutation(int N) {

		/* 順列格納用配列 */
		var arr = new int[N];
		for (int i=0; i<N; i++) {
			arr[i] = i;
		}

		/* 順列全探索 */
		do {
			// ここにロジックを追加
		} while (nextPermutation(arr));

	}
	/* 順列 */
	public static boolean nextPermutation(int[] arr) {
		int len = arr.length;
		int left = len - 2;
		while (left >= 0 && arr[left] >= arr[left+1]) left--;
		if (left < 0) return false;
		int right = len - 1;
		while (arr[left] >= arr[right]) right--;
		{ int t = arr[left]; arr[left] = arr[right];  arr[right] = t; }
		left++;
		right = len - 1;
		while (left < right) {
			{ int t = arr[left]; arr[left] = arr[right]; arr[right] = t; }
			left++;
			right--;
		}
		return true;
	}

	/**
	 * 三角形の面積
	 */
	static double GetTriangleArea(double AX,double AY,double BX,double BY,double CX,double CY) {
		return (((CX-BX)*(AY-BY))-((AX-BX)*(CY-BY)))*0.5;
	}

	/**
	 * いもす法（1次元）セットアップ
	 * @param N 要素数
	 */
	static void ImosSetUp1D(int N) {

		for(int i=0; i<N; i++) {
			long A = Long.parseLong(sc.next());
			long B = Long.parseLong(sc.next());
			long P = Long.parseLong(sc.next());

			if(Imos.containsKey(A)) {
				Imos.put(A, Imos.get(A)+P);
			} else {
				Imos.put(A, P);
			}
			if(Imos.containsKey(B)) {
				Imos.put(B, Imos.get(B)-P);
			} else {
				Imos.put(B, -P);
			}
		}
	}
	/**
	 * いもす法（1次元）シミュレートロジック
	 */
	static void ImosSimulate1D() {
		/*
		long nowD = 1;
		long nowP = 0;
		for(Entry<Long, Long> e : Imos.entrySet() ) {
			long nextD =  e.getKey();
			long addP = e.getValue();
			// 何かしらのロジック
			nowD = nextD;
			nowP += addP;
		}
		*/
	}

	/**
	 * グラフの生成（隣接リスト）
	 * @param N 頂点数
	 * @param M 辺の数
	 */
	static void makeGraph2(int N, long M) {

		edgeList = new oneNodeList[N];
		for(int i=0; i<N; i++) {
			edgeList[i] = new oneNodeList();
		}

		for(long i=0; i<M; i++) {
			int A = Integer.parseInt(sc.next())-1;
			int B = Integer.parseInt(sc.next())-1;

			edgeList[A].put(B, 1);
			edgeList[B].put(A, 1);	// 無向グラフの場合、逆方向にも張る
		}
	}

	/**
	 * DFS（深さ優先探索）（隣接リスト）
	 * @param v チェック対象の座標
	 */
	static void DFS2(int v) {
		for(Map.Entry<Integer, Integer> entry : edgeList[v].entrySet()) {
			if(DPDistance[entry.getKey()] < 0) {
				DPDistance[entry.getKey()] = DPDistance[v] + entry.getValue();
				DFS2(entry.getKey());
			}
		}
	}

	/**
	 * BFS（幅優先探索）（隣接リスト）
	 * @param S 開始座標
	 */
	static void BFS2(int N, int S) {

		Deque<Integer> q = new ArrayDeque<>();

		DPDistance = new long[N];
		Arrays.fill(DPDistance, INF);
		DPDistance[S] = 0;

		q.add(S);

		while(q.size() > 0) {
			int now = q.poll();

			for(Map.Entry<Integer, Integer> entry : edgeList[now].entrySet()) {
				DPDistance[entry.getKey()] = Math.min(DPDistance[entry.getKey()], DPDistance[now] + entry.getValue());
				q.add(entry.getKey());
			}
			edgeList[now].clear();
		}
	}

	/**
	 * ダイクストラ法（隣接リスト）
	 * ※頂点の距離のみ実装済み
	 * ※辺の重み未対応
	 *
	 * @param N 頂点数
	 * @param S 開始頂点
	 */
	static void dijkstra(int N, int S) {

		DPDistance = new long[N];
		boolean FIX[] = new boolean[N];
		Arrays.fill(DPDistance, INF);
		Arrays.fill(FIX, false);

		DPDistance[S] = 0;

		for(;;) {

			long min = INF; // 最小の重み
			int now = -1; // 今回処理する頂点

			// 処理対象の頂点を決める
			for(int i=0; i<N; i++) {
				if(!FIX[i]) {
					if(min > DPDistance[i]) {
						min = DPDistance[i];
						now = i;
					}
				}
			}
			// 頂点が決まらなかったら処理終了
			if(now < 0) {
				break;
			}

			// 処理対象の頂点を確定済み状態にする
			FIX[now] = true;

			for(int i=0; i < edgeList[now].size(); i++) {
				int next = edgeList[now].get(i);
				if(!FIX[next]) {
					DPDistance[next] = Math.min(DPDistance[next], DPDistance[now] + 1);
				}
			}
		}
	}

	/**
	 * bit全探索
	 * @param N 要素数
	 */
	static long bitSearch(int N) {

		long DP = INF;
		// 全探索パターン数
		long Pattern = (long) Math.pow(2, N);

		// 全探索パターン数分ループ
		for(long i=0; i<Pattern; i++) {

			// j番目の要素のbit判定
			for(long j=0; j<N; j++) {
				if((1&i>>j) == 1) {
					// j番目の要素がONの場合
					// TODO ONの場合のコードを実装
				} else {
					// TODO OFFの場合のコードを実装
				}
			}

			// TODO DPの判定を実装
		}

		// 求めたDPの返却
		return DP;
	}

	/**
	 * ビット演算(AND)
	 * @param A
	 * @param B
	 * @return
	 */
	static long bitAND(long A, long B) {
		return A&B;
	}

	/**
	 * ビット演算(OR)
	 * @param A
	 * @param B
	 * @return
	 */
	static long bitOR(long A, long B) {
		return A|B;
	}

	/**
	 * ビット演算(XOR)
	 * @param A
	 * @param B
	 * @return
	 */
	static long bitXOR(long A, long B) {
		return A^B;
	}

	/**
	 * ビット演算(NOT)
	 * @param A
	 * @param B
	 * @return
	 */
	static long bitNOT(long A) {
		return ~A;
	}

	/**
	 * 小さい順に約数を列挙する
	 * @param N 約数を求めたい数値
	 * @param sqrtN Nの平方根（あらかじめsqrtで求めておくこと）
	 * @param Start 集計を開始する値（1を設定して呼び出す）
	 */
	static void makeDivisors(long N, double sqrtN, long Start) {
		while(Start <= sqrtN) {
			if(N%Start == 0) {
				System.out.println(Start);
				makeDivisors(N,sqrtN,Start+1);
				if(Start * Start != N) {
					System.out.println(N/Start);
				}
				break;
			}
			Start++;
		}
	}

	/**
	 * 壁座標が与えられるパターンのとき、グリッドを作成する
	 * @param H
	 * @param W
	 * @param N
	 * @return なし
	 */
	public static void makeGrid(int H, int W, int N) {
		Grid = new String[H+2];

		// グリッドの初期化
		for(int i=0; i<H+1; i++) {
			Grid[i] = "#";
		}
		for(int i=1; i<W+2; i++) {
			Grid[0] += "#";
			for(int j=1; j<H+1; j++) {
				Grid[j] += ".";
			}
			Grid[H+1] += "#";
		}
		for(int i=0; i<H+1; i++) {
			Grid[i] += "#";
		}

		// 与えられた座標を壁にする
		for(int i=0; i<N; i++) {
			int r = sc.nextInt();
			int c = sc.nextInt();
			Grid[r] = Grid[r].substring(0, c) + "#" + Grid[r].substring(c+2);
		}
	}

	/**
	 * 最小公倍数を求める
	 * @param A
	 * @param B
	 * @return lcm
	 */
	public static long lcm(long A, long B) {
		return A * B / gcd(A,B);
	}

	/**
	 * 最大公約数を求める
	 * @param A
	 * @param B
	 * @return gcd
	 */
	public static long gcd(long A, long B) {
		return gcdMain(Math.max(A, B), Math.min(A, B));
	}
	public static long gcdMain(long numBig, long numSmall) {
		if(numSmall==0) return numBig;
		else return gcd(numSmall, numBig%numSmall);
	}

	/**
	 * 組み合わせの数を求める
	 * @param n 要素素
	 * @param k 選択する数
	 * @param M
	 * @return nCk
	 */
	public static long nCk(long n,long k) {
		long ret = 1;
		long min = Math.min(k, n-k);
		for(long i=1; i<=min; i++) {
			ret *= n+1-i;
			ret /= i;
		}
		return ret;
	}
    /**
     * 組み合わせの数を求める（MOD処理含む）
     * @param n 要素素
     * @param k 選択する数
     * @param M
     * @return nCk mod M (M must be prime number) O(min(k,n-k)*logM)
     */
    public static int nCk(int n,int k,int M) {
        long ret = 1;
        int min = Math.min(k, n-k);
        for(int i=1;i<=min;i++) {
            ret = (ret * modPow(i,M-2,M)) % M;
        }
        for(int i=n-min+1;i<=n;i++) {
            ret = (ret * i) % M;
        }
        return (int)ret;
    }


    /**
     * 累乗計算（MOD処理含む）
     * @param a
     * @param b
     * @param M
     * @return a^b mod M O(logB)
     */
	public static long modPow(long a,long b,int M) {

		long ret = 1;
		if(b > 0) {
			TreeMap<Long, Long> memo = new TreeMap<Long, Long>(Collections.reverseOrder());
			memo.put((long) 1, a%M);

			long PowTmp = 1;
			while(PowTmp < b) {
				memo.put(PowTmp*2, (memo.get(PowTmp)*memo.get(PowTmp))%M);
				PowTmp *= 2;
	        }

			ret = a;
			b--;
			for(Entry<Long, Long> e : memo.entrySet() ) {
				long nowB = e.getKey();
				long nowA = e.getValue();

				while(b - nowB >= 0) {
					ret *= nowA;
					ret %= M;
					b -= nowB;
		        }
			}
		}

		return ret;
    }
}