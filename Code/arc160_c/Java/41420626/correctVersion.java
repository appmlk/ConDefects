import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		new Main().run();
	}
	
	class SegTree {
		int n;
		int[] v;
		
		public SegTree(int n_) {
			n = Integer.highestOneBit(n_) * 2;
			v = new int[2 * n];
		}
		
		void set(int k, int val) {
			int pos = id(k, k + 1);
			v[pos] = val;
			while (pos != id(0, n)) {
				pos /= 2;
				v[pos] = v[2 * pos] + v[2 * pos + 1];
			}
		}
		
		int id(int a, int b) {
			int w = Integer.lowestOneBit(a ^ b);
			return n / w + a / w;
		}
		
		int query(int a, int b) {
			if (b - a <= 0) return 0;
			int ma = a + Integer.lowestOneBit(a);
			int mb = b - Integer.lowestOneBit(b);
			if (a < ma && ma <= b) {
				return v[id(a, ma)] + query(ma, b);
			} else {
				return query(a, mb) + v[id(mb, b)];
			}
		}
		
		int kth(int k) {
			int a = 0;
			int b = n;
			while (b - a != 1) {
				int m = (a + b)  /2;
				if (v[id(a, m)] >= k) {
					b = m;
				} else {
					k -= v[id(a, m)];
					a = m;
				}
			}
			return a;
		}
	}
	
	long mod = 998244353;
	
	void run() throws FileNotFoundException {
		
		FastScanner sc = new FastScanner();
		PrintWriter pw = new PrintWriter(System.out);
		
		int N = sc.nextInt();
		int[] A = new int[N];
		for (int i = 0; i < N; ++i) {
			A[i] = sc.nextInt();
		}
		int MAX = 200100;
		long[][] dp = new long[MAX + 1][];
		
		int[] cnt = new int[MAX + 1];
		for (int i = 0; i < N; ++i) {
			cnt[A[i]]++;
		}
		dp[0] = new long[1];
		dp[0][0] = 1;
		for (int i = 0; i < MAX; ++i) {
			int nz = (dp[i].length + cnt[i]) / 2;
			dp[i + 1] = new long[nz + 1];
			for (int carry = 0; carry < dp[i].length; ++carry) {
				int num = cnt[i] + carry;
				dp[i + 1][num / 2] += dp[i][carry];
				dp[i + 1][num / 2] %= mod;
			}
			for (int carry = dp[i + 1].length - 1; carry >= 1; --carry) {
				dp[i + 1][carry - 1] += dp[i + 1][carry];
				dp[i + 1][carry - 1] %= mod;
			}
		}
		long ans = 0;
		for (int i = 0; i < dp[MAX].length; ++i) {
			ans += dp[MAX][i];
			ans %= mod;
		}
		System.out.println(ans);
		pw.close(); 
	}
	
	void tr(Object... objects) {
		System.out.println(Arrays.deepToString(objects));
	}
}

class FastScanner {
	private final InputStream in = System.in;
	private final byte[] buffer = new byte[1024];
	private int ptr = 0;
	private int buflen = 0;

	private boolean hasNextByte() {
		if (ptr < buflen) {
			return true;
		} else {
			ptr = 0;
			try {
				buflen = in.read(buffer);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (buflen <= 0) {
				return false;
			}
		}
		return true;
	}

	private int readByte() {
		if (hasNextByte())
			return buffer[ptr++];
		else
			return -1;
	}

	private static boolean isPrintableChar(int c) {
		return 33 <= c && c <= 126;
	}

	private void skipUnprintable() {
		while (hasNextByte() && !isPrintableChar(buffer[ptr]))
			ptr++;
	}

	public boolean hasNext() {
		skipUnprintable();
		return hasNextByte();
	}

	public String next() {
		if (!hasNext())
			throw new NoSuchElementException();
		StringBuilder sb = new StringBuilder();
		int b = readByte();
		while (isPrintableChar(b)) {
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}

	public long nextLong() {
		if (!hasNext())
			throw new NoSuchElementException();
		long n = 0;
		boolean minus = false;
		int b = readByte();
		if (b == '-') {
			minus = true;
			b = readByte();
		}
		if (b < '0' || '9' < b) {
			throw new NumberFormatException();
		}
		while (true) {
			if ('0' <= b && b <= '9') {
				n *= 10;
				n += b - '0';
			} else if (b == -1 || !isPrintableChar(b)) {
				return minus ? -n : n;
			} else {
				throw new NumberFormatException();
			}
			b = readByte();
		}
	}

	public int nextInt() {
		return (int) nextLong();
	}
}