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
	static long MAX = 1000000000000000000l;//10^18
	static int index = 2;
	public static void main(String[] args){
		PrintWriter out = new PrintWriter(System.out);
		InputReader sc=new InputReader(System.in);
		int n = sc.nextInt();
		int A = sc.nextInt();
		int[] W = new int[n];
		int[] X = new int[n];
		int[] V = new int[n];
		for (int i = 0; i < V.length; i++) {
			W[i] = sc.nextInt();
			X[i] = sc.nextInt();
			V[i] = sc.nextInt();
		}
		int ans = 0;
		for (int i = 0; i < n; i++) {
			int sum = 0;
			ArrayList<Pair2> list = new ArrayList<>();
			for (int j = 0; j < n; j++) {
				if (V[j] - V[i] > 0) {
					if (X[j] <= X[i] + A) {
						int v = V[j] - V[i];
						int s = Math.max(0, X[i] - X[j]);
						int t = X[i] + A - X[j];
						list.add(new Pair2(s, v, W[j]));
						list.add(new Pair2(t, v, -W[j]));
					}
				}else if (V[j] - V[i] < 0) {
					if (X[j] >= X[i]) {
						int v = V[i] - V[j];
						int s = Math.max(0, X[j] - (X[i] + A));
						int t = X[j] - X[i];
						list.add(new Pair2(s, v, W[j]));
						list.add(new Pair2(t, v, -W[j]));
					}
				}else {
					if(X[i] <= X[j] && X[j] <= X[i] + A) sum += W[j];
				}
			}
			Collections.sort(list);
			ans = Math.max(sum, ans);
			for (Pair2 p : list) {
				sum += p.z;
				ans = Math.max(sum, ans);
			}
		}
		System.out.println(ans);
   	}
	static class Pair2 implements Comparable<Pair2>{
    	public int x;
    	public int y;
    	public int z;
    	public Pair2(int x,int y,int z) {
    		this.x=x;
    		this.y=y;
    		this.z=z;
    	}
    	@Override
    	public boolean equals(Object obj) {
    		if(obj instanceof Pair2) {
    			Pair2 other = (Pair2) obj;
    			return other.x==this.x && other.y==this.y&& other.z==this.z;
    		}
    		return false;
    	}//同値の定義
    	@Override
    	public int hashCode() {
    		return Objects.hash(this.x,this.y,this.z);
    	}//これ書かないと正しく動作しない（要　勉強）
    	@Override
    	public int compareTo( Pair2 p2 ){
    		if (this.x * p2.y > this.y * p2.x) {
    			return 1;
    		}
    		else if (this.x * p2.y < this.y * p2.x) {
    			return -1;
    		}
    		else {
    			if (this.z < p2.z) {
        			return 1;
        		}
        		else if (this.z > p2.z) {
        			return -1;
        		}
        		else {
        			return 0;
        		}
    		}
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