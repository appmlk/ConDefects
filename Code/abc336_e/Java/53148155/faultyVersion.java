import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UncheckedIOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.*;


public class Main {
	static final long MOD1=1000000007;
	static long MOD=998244353;
	static final int NTT_MOD1 = 998244353;
	static final int NTT_MOD2 = 1053818881;
	static final int NTT_MOD3 = 1004535809;
	static long MAX = 1000000000000000010l;//10^18
	static boolean ok = false;
	static long ans = 0;
	static final int[] dx = {1, -1, 0, 0};
	static final int[] dy = {0, 0, 1, -1};
	static final long[] MODS = {998244353, 1000000007, 1000000009, 1000000021};
	static class InputReader { 
		private InputStream in;
		private byte[] buffer = new byte[1024];
		private int curbuf;
		private int lenbuf;
		public InputReader(InputStream in) {
			this.in = in;
			this.curbuf = this.lenbuf = 0;
		}
		public boolean hasNextByte() {
			if (curbuf >= lenbuf) {
				curbuf = 0;
				try {
					lenbuf = in.read(buffer);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (lenbuf <= 0)
					return false;
			}
			return true;
		}
 
		private int readByte() {
			if (hasNextByte())
				return buffer[curbuf++];
			else
				return -1;
		}
 
		private boolean isSpaceChar(int c) {
			return !(c >= 33 && c <= 126);
		}
 
		private void skip() {
			while (hasNextByte() && isSpaceChar(buffer[curbuf]))
				curbuf++;
		}
 
		public boolean hasNext() {
			skip();
			return hasNextByte();
		}
 
		public String next() {
			if (!hasNext())
				throw new NoSuchElementException();
			StringBuilder sb = new StringBuilder();
			int b = readByte();
			while (!isSpaceChar(b)) {
				sb.appendCodePoint(b);
				b = readByte();
			}
			return sb.toString();
		}
 
		public int nextInt() {
			if (!hasNext())
				throw new NoSuchElementException();
			int c = readByte();
			while (isSpaceChar(c))
				c = readByte();
			boolean minus = false;
			if (c == '-') {
				minus = true;
				c = readByte();
			}
			int res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res = res * 10 + c - '0';
				c = readByte();
			} while (!isSpaceChar(c));
			return (minus) ? -res : res;
		}
 
		public long nextLong() {
			if (!hasNext())
				throw new NoSuchElementException();
			int c = readByte();
			while (isSpaceChar(c))
				c = readByte();
			boolean minus = false;
			if (c == '-') {
				minus = true;
				c = readByte();
			}
			long res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res = res * 10 + c - '0';
				c = readByte();
			} while (!isSpaceChar(c));
			return (minus) ? -res : res;
		}
 
		public double nextDouble() {
			return Double.parseDouble(next());
		}
 
		public int[] nextIntArray(int n) {
			int[] a = new int[n];
			for (int i = 0; i < n; i++)
				a[i] = nextInt();
			return a;
		}
		public double[] nextDoubleArray(int n) {
			double[] a = new double[n];
			for (int i = 0; i < n; i++)
				a[i] = nextDouble();
			return a;
		}
		public long[] nextLongArray(int n) {
			long[] a = new long[n];
			for (int i = 0; i < n; i++)
				a[i] = nextLong();
			return a;
		}
 
		public char[][] nextCharMap(int n, int m) {
			char[][] map = new char[n][m];
			for (int i = 0; i < n; i++)
				map[i] = next().toCharArray();
			return map;
		}
	}
	public static void main(String[] args){
		PrintWriter out = new PrintWriter(System.out);
		InputReader sc=new InputReader(System.in);		
		long n = sc.nextLong();
		char[] cs = String.valueOf(n).toCharArray();
		long ans = 0;
		int s = cs.length;
		for (int i = 1; i <= 13 * 9; i++) {
			long[][][][] dp = new long[s + 1][2][i + 1][i];
			dp[0][1][0][0] = 1;
			for (int j = 0; j < s; j++) {
				int c = Character.getNumericValue(cs[j]);
				for (int j2 = 0; j2 < 2; j2++) {
					for (int k = 0; k <= i; k++) {
						for (int k2 = 0; k2 < i; k2++) {
							long pre = dp[j][j2][k][k2];
							if (pre == 0)  continue;
							for (int l = 0; l < 10; l++) {
								int nj2 = j2;
								int nk = k + l;
								int nk2 = (k2 * 10 + l) % i;
								if (c < l && j2 == 1) continue;
								if (c > l) nj2 = 0;
								if (k + l > i) continue;
								dp[j + 1][nj2][nk][nk2] += pre;
							}
						}
					}
				}
			}
			ans += dp[s][0][i][0] + dp[s][1][i][0];
		}
		System.out.println(ans);
	}
}
