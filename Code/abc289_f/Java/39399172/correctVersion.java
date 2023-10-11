import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.*;



public class Main {
	static final long MOD1=1000000007;
	static final long MOD=998244353;
	static final int NTT_MOD1 = 998244353;
	static final int NTT_MOD2 = 1053818881;
	static final int NTT_MOD3 = 1004535809;
	static long MAX = 1000000000000000010l;//10^18
	public static void main(String[] args){
		PrintWriter out = new PrintWriter(System.out);
		InputReader sc=new InputReader(System.in);
		int sx = sc.nextInt();
		int sy = sc.nextInt();
		int tx = sc.nextInt();
		int ty = sc.nextInt();
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		int d = sc.nextInt();
		if (a == b && c == d) {
			if (sx == tx && sy == ty) {
				System.out.println("Yes");
			}else if (tx == 2 * a - sx && ty == 2 * c - sy) {
				System.out.println("Yes");
				System.out.println(a + " " + b);
			}else {
				System.out.println("No");
			}
			return;
		}
		int nowx = sx;
		int nowy = sy;
		boolean xok = a == b ? (sx == tx || tx == 2 * a - sx) : (sx % 2 == tx % 2);
		boolean yok = c == d ? (sy == ty || ty == 2 * c - sy) : (sy % 2 == ty % 2);
		if (xok && yok) {
			ArrayList<String> ans = new ArrayList<>();
			if ((a == b && tx == 2 * a - sx) || c == d && ty == 2 * c - sy) {
				ans.add(a + " " + c);
				nowx = f(nowx, a);
				nowy = f(nowy, c);
			}
			while (nowx != tx) {
				if (nowx < tx) {
					ans.add(a + " " + c);
					ans.add((a + 1) + " " + c);
					nowx = f(nowx, a);
					nowx = f(nowx, a + 1);
				}else {
					ans.add((a + 1) + " " + c);
					ans.add(a + " " + c);
					nowx = f(nowx, a + 1);
					nowx = f(nowx, a);
				}
			}
			while (nowy != ty) {
				if (nowy < ty) {
					ans.add(a + " " + c);
					ans.add(a + " " + (c + 1));
					nowy = f(nowy, c);
					nowy = f(nowy, c + 1);
				}else {
					ans.add(a + " " + (c + 1));
					ans.add(a + " " + c);
					nowy = f(nowy, c + 1);
					nowy = f(nowy, c);
				}
			}
			out.println("Yes");
			for (String string : ans) {
				out.println(string);
			}
			out.flush();
		}else {
			System.out.println("No");
		}
	}
	static int f(int from, int mid) {
		return 2 * mid - from;
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