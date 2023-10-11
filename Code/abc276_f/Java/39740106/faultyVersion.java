import java.io.* ;
import java.math.* ;
import java.util.* ;



public class Main {
	final static int N = 300000;
	
	long mod = 998244353L;
	int n;
	int[] as = new int[N];

	long[] u = new long[N];
	long[] d = new long[N];
	long[] rs = new long[N];

	long[] tr = new long[N << 2];
	long[] ct = new long[N << 2];

	int lc(int p) { return p << 1; }
	int rc(int p) { return p << 1 | 1; }

	void push_up(int p, int l, int r) {
		if (l == r) return;
		tr[p] = tr[lc(p)] + tr[rc(p)];
		ct[p] = ct[lc(p)] + ct[rc(p)];
	}

	void add(int p, int l, int r, int pos) {
		if (pos <= l && r <= pos) {
			tr[p] += pos;
			ct[p] += 1;
			return;
		}

		int mid = (l + r) >> 1;

		if (pos <= mid) {
			add(lc(p), l, mid, pos);
		} else {
			add(rc(p), mid + 1, r, pos);
		}

		push_up(p, l, r);
	}

	long[] query(int p, int l, int r, int ll, int rr) {
		if (ll <= l && r <= rr) {
			return new long[] { tr[p], ct[p] };
		}

		int mid = (l + r) >> 1;

		long res = 0;
		long cnt = 0;

		if (ll <= mid) {
			long[] nd =  query(lc(p), l, mid, ll, rr);
			res += nd[0];
			cnt += nd[1];
		}
		if (rr > mid) {
			long[] nd =  query(rc(p), mid + 1, r, ll, rr);
			res += nd[0];
			cnt += nd[1];
		}

		return new long[] {res, cnt};
	}
	
	public void solve() throws Exception {
		
		n = nextInt();
		for (int i = 1; i <= n; i ++) {
			as[i] = nextInt();
		}		

		for (int i = 1; i <= n; i ++) {
			add(1, 1, N - 1, as[i]);
			u[i] += u[i - 1];
			u[i] += query(1, 1, N - 1, as[i], N - 1)[0] * 2 - as[i];
			if (as[i] > 1) 
				u[i] += query(1, 1, N - 1, 1, as[i] - 1)[1] * as[i] * 2;
			d[i] += d[i - 1] + i * 2 - 1;
		}

		for (int i = 1; i <= n; i ++) {

			long ni = qpow(d[i], mod - 2, mod);

			rs[i] = u[i] * ni % mod;
			cout.println(rs[i]);
		}

		cout.flush();
	}
	
	public static void main(String[] args) throws Exception {
		
		Main cmd = new Main();
		cmd.solve();
		
	}

	static BufferedReader cin = 
	new BufferedReader (
		new InputStreamReader (System.in)
	);

	static PrintWriter cout = new PrintWriter (
		new OutputStreamWriter (System.out)
	);
	
	static StreamTokenizer input = new StreamTokenizer(
		new BufferedReader(
			new InputStreamReader(System.in)
		)
	);
	
	static Scanner next = new Scanner(System.in);

	int nextInt() throws Exception {
		int x = 0, f = 1;
		char c = (char)cin.read();
		while (c > '9' || c < '0') {
			if (c == '-') f = -1;
			c = (char)cin.read();
		}
		while (c <= '9' && c >= '0') {
			x = x * 10 + (int)c - (int)'0';
			c = (char)cin.read();
		}
		return x * f;
	}

	long nextLong() throws Exception {
		long x = 0, f = 1;
		char c = (char)cin.read();
		while (c > '9' || c < '0') {
			if (c == '-') f = -1L;
			c = (char)cin.read();
		}
		while (c <= '9' && c >= '0') {
			x = x * 10 + (long)c - (long)'0';
			c = (char)cin.read();
		}
		return x * f;
	}

	double nextDouble() throws Exception {
		double x = 0, f = 1;
		char c = (char)cin.read();
		while (c > '9' || c < '0') {
			if (c == '-') f = -1.0;
			c = (char)cin.read();
		}
		long h = 0;
		while (c <= '9' && c >= '0') {
			h = h * 10 + (long)c - (long)'0';
			c = (char)cin.read();
		}
		double p = 0.1, e = 0;
		if (c == '.') {
			c = (char)cin.read();
			while (c <= '9' && c >= '0') {
				e += p * ((double)c - (double)'0');
				p *= 0.1;
				c = (char)cin.read();
			}
		}
		return ((double)h + e) * f;
	}

	static int sed = (int)System.currentTimeMillis();

	public static void seed(int x) { sed = x; }

	public static int _01_() {
		sed ^= sed << 13;
		sed ^= sed >> 17;
		sed ^= sed << 5;
		return (sed & 1) == 1 ? 1 : 0;
	}

	public static boolean nextBoolean() {
		return _01_() == 1;
	}

	public static int nextInt(int n) {
		if (n <= 1) return n;

		int x = n;
		if ((x & 1) == 1) x ++;

		int k = nextInt(x >> 1);

		int res = 2 * k - 1;
		if (_01_() == 1) res ++;

		if (res > n) return nextInt(n);
		return res;
	}

	public static long nextLong(long n) {
		if (n <= 1) return n;

		long x = n;
		if ((x & 1) == 1) x ++;

		long k = nextLong(x >> 1);

		long res = 2 * k - 1;
		if (_01_() == 1) res ++;

		if (res > n) return nextLong(n);
		return res;
	}

	// 获取 1 ~ n 中随机的 m 个
	public static int[] nextInts(int n, int m) {
		int[] as, rs;
		as = new int[n];
		rs = new int[m];

		for (int i = 0; i < n; i ++) {
			as[i] = i + 1;
		}

		for (int i = 0; i < m; i ++) {
			int t = nextInt(m - i);
			rs[i] = as[m - 1 - t + 1];
			int tp = as[m - 1 - t + 1];
			as[m - 1 - t + 1] = as[i];
			as[i] = tp;
		}

		return rs;
	}

	public static long qpow(long a, long b, long mod) {
		if (b == 1) return a % mod;
		if (a == 1) return a;

		long res = qpow(a, b >> 1, mod);

		res = res * res % mod;

		if ((b & 1) > 0) res = res * a % mod;

		return res;
	}

	public static long gcd(long a, long b) {
		if (b == 0) return a;
		return gcd(b, a % b);
	}
}