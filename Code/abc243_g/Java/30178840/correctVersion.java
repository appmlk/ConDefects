import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Main {
	static final long MOD1=1000000007;
	static final long MOD=998244353;
	static int[] ans;
	public static void main(String[] args){
		PrintWriter out = new PrintWriter(System.out);
		InputReader sc=new InputReader(System.in);
		int t = sc.nextInt();
		long[] dp = new long[100001];
		long[] sum = new long[100001];
		dp[1] = 1;
		sum[1] = 1;
		for (int i = 2; i < sum.length; i++) {
			int from = sqrt_floor(i);
			dp[i] = sum[from];
			sum[i] = sum[i-1] + dp[i];
		}
		for (int i = 0; i < t; i++) {
			long X = sc.nextLong();
			if (X <= 100000) {
				out.println(dp[(int)X]);
			}else {
				long sq = sqrt_floor(X);
				long ans = 0;
				for (long j = 1; j <= 100000; j++) {
					long r = Math.min(sq, (j+1)*(j+1)-1);
					long l =j*j;
					ans += Math.max(0, (r-l+1))*sum[(int) j];
				}
				out.println(ans);
			}
		}
		out.flush();
 	}
	static int sqrt_floor(int n) {
		int x = (int) Math.sqrt(n);
		int ans = 0;
		for (int i = -1; i <= 1; i++) {
			x = x+i;
			if(x*x<=n) ans=x;
		}
		return ans;
	}
	static long sqrt_floor(long n) {
		long x = (long) Math.sqrt(n);
		long ans = 0;
		for (long i = -1; i <= 10; i++) {
			x = x+i;
			if(x*x<=n) ans=x;
		}
		return ans;
	}
	static long solve(int n, long k, int[] a) {
		long[] ten = new long[19];
		ten[0] = 1;
		for (int i = 1; i < ten.length; i++) {
			ten[i] = ten[i-1]*10;
		}
		long ans = 0;
		for (int i = 0; i < a.length; i++) {
			int next = 18;
			if(i+1<n) next = a[i+1];
			long sum = Math.min(k, ten[next - a[i]]-1);
			k -= sum;
			ans += sum * ten[a[i]]; 
		}
		return ans;
	}
	static class Binomial{
		int MAX = 510000;//ほしいサイズ
		long[] fac=new long[MAX];
		long[] finv=new long[MAX];
		long[] inv=new long[MAX];
		public Binomial(){
			fac[0] = fac[1] = 1;
		    finv[0] = finv[1] = 1;
		    inv[1] = 1;
		    for (int i = 2; i < MAX; i++){
		        fac[i] = fac[i - 1] * i % MOD;
		        inv[i] = MOD - inv[(int) (MOD%i)] * (MOD / i) % MOD;
		        finv[i] = finv[i - 1] * inv[i] % MOD;
		    }//facがx!、finvがx!の逆元,10^7くらいまでのテーブル(MAXまで)
		}
		long nCk(int n,int k) {
		    if (n < k) return 0;
		    if (n < 0 || k < 0) return 0;
		    return fac[n] * (finv[k] * finv[n - k] % MOD) % MOD;
		}
		long fac(int n) {
			return fac[n];
		}
		long fac_inv(int n) {
			return finv[n];
		}
	}
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
}