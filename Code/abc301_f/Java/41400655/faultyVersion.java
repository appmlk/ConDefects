import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		new Main().run();
	}
	
	long pow(long a, long n) {
		if (n == 0) return 1;
		return pow(a * a % mod, n / 2) * (n % 2 == 1 ? a  : 1) % mod;
	}
	
	long inv(long a) {
		return pow(a, mod - 2);
	}
	
	long mod = 998244353;
	int MAX = 600010;
	long[] fac = new long[MAX];
	long[] ifac = new long[MAX];
	long[] pwr = new long[MAX];
	{
		Arrays.fill(fac, 1);
		Arrays.fill(ifac, 1);
		Arrays.fill(pwr, 1);
		for (int i = 2; i < fac.length; ++i) {
			fac[i] = i * fac[i - 1]  % mod;
		}
		ifac[MAX - 1] = inv(fac[MAX - 1]);
		for (int i = MAX - 2; i >= 2; --i) {
			ifac[i] = ifac[i + 1] * (i + 1) % mod;
		}
		for (int i = 1; i < pwr.length; ++i) pwr[i] = 26 * pwr[i - 1] % mod;
	}
	
	
	boolean isLower(char c) {
		return 'a' <= c && c < 'z';
	}
	
	boolean isLarge(char c) {
		return 'A' <= c && c <= 'Z';
	}
	
	long C(int n, int k) {
		if (k < 0 || n - k < 0) return 0;
		return fac[n] * ifac[k] % mod * ifac[n - k] % mod;
	}
	
	long P(int n, int k) {
		return C(n, k) * fac[k] % mod;
	}
	
	void run() throws FileNotFoundException {
		
		FastScanner sc = new FastScanner();
		PrintWriter pw = new PrintWriter(System.out);
		long ans = 0;
		char[] cs = sc.next().toCharArray();
		int N = cs.length;
		long[] dp = new long[N];
		// dp[i] == S[i] が大文字かつ S[0..i) に同じ文字が出現する最初の場所
		{
			int free = 0;
			int set = 0;
			out : for (int i = 0; i < N; ++i) {
				int used = Integer.bitCount(set);
				if (cs[i] == '?') {
					for (int k = 0; k + used <= 26 && k <= free; ++k) {//S[1..i)で?を大文字にする個数
						dp[i] += C(free, k) * P(26 - used, k) % mod * pwr[free - k] % mod * (used + k) % mod;
						dp[i] %= mod;
					}
					++free;
				} else if (isLarge(cs[i])) {
					int id = cs[i] - 'A';
					if ((set >> id) % 2 == 1) {
						for (int k = 0; k + used <= 26 && k <= free; ++k) {
							dp[i] += C(free, k) * P(26 - used, k) % mod * pwr[free - k] % mod;
							dp[i] %= mod;
						}
						break out;
					} else {
						for (int k = 1; k + used <= 26 && k <= free; ++k) { // ひとつは cs[i] で確定
							dp[i] += C(free, k) * k % mod * P(26 - (used + 1), k - 1) % mod * pwr[free - k] % mod;
							dp[i] %= mod;
						}
						set |= 1 << id;
					}
				}
				if (i == N - 1) {
					used = Integer.bitCount(set);
					for (int k = 0; k + used <= 26 && k <= free; ++k) {
						ans += C(free, k) * P(26 - used, k) % mod * pwr[free - k] % mod;
						ans %= mod;
					}
					ans %= mod;
				}
			}
		}
		//dp以降で大文字が0個以上続いている
		for (int i = 0; i < N; ++i) {
			if (isLarge(cs[i]) && i > 0) {
				dp[i] += dp[i - 1];
			}
			if (cs[i] == '?' && i > 0) {
				dp[i] += dp[i - 1] * 26;
			}
			dp[i] %= mod;
		}
		long[] dp3 = new long[N];//dp3[i]=dp2以降で小文字しか現れておらず、小文字は1文字以上現れた
		for (int i = 0; i < N; ++i) {
			long way = 0;
			if (isLower(cs[i])) way = 1;
			else if (cs[i] == '?') way = 26;
			if (i > 0) {
				dp3[i] += way * (dp[i - 1] + dp3[i - 1]);
			}
			dp3[i] %= mod;
		}
		
		ans += dp[N - 1];
		ans += dp3[N - 1];
		ans %= mod;
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