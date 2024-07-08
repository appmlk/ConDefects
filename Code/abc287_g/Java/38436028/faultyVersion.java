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
		int n = sc.nextInt();
		long[] a = new long[n];
		long[] b = new long[n];
		HashSet<Long> set = new HashSet<>(); 
		for (int i = 0; i < b.length; i++) {
			a[i] = sc.nextLong();
			b[i] = sc.nextLong();
			set.add(a[i]);
		}
		int q = sc.nextInt();
		int[] t = new int[q];
		int[] query_x = new int[q];
		long[] query_y = new long[q];
		for (int i = 0; i < query_y.length; i++) {
			t[i] = sc.nextInt();
			query_x[i] = sc.nextInt() - 1;
			if (t[i] <= 2) {
				query_y[i] = sc.nextLong();
				if (t[i] == 1) set.add(query_y[i]);
			}
		}
		ArrayList<Long> list = new ArrayList<>(set);
		Collections.sort(list);
		HashMap<Long, Integer> map = new HashMap<>();
		for (long l : list) map.put(l, map.size());
		int s = map.size();
		FenwickTree count = new FenwickTree(s);
		FenwickTree score = new FenwickTree(s);
		for (int i = 0; i < n; i++) {
			int c = map.get(a[i]);
			count.add(c, b[i]);
			score.add(c, b[i] * a[i]);
		}
		for (int i = 0; i < q; i++) {
			if (t[i] == 1) {
				int c = map.get(a[query_x[i]]);
				count.add(c, -b[query_x[i]]);
				score.add(c, -b[query_x[i]] * a[query_x[i]]);
				a[query_x[i]] = query_y[i];
				c = map.get(query_y[i]);
				count.add(c, b[query_x[i]]);
				score.add(c, b[query_x[i]] * a[query_x[i]]);
			}else if (t[i] == 2) {
				int c = map.get(a[query_x[i]]);
				count.add(c, -b[query_x[i]] + query_y[i]);
				score.add(c, (- b[query_x[i]] + query_y[i]) * a[query_x[i]]);
				b[query_x[i]] =query_y[i];
			}else {
				long r = query_x[i] + 1;
				long sum = count.sum(0, s);
				if (r > sum) {
					out.println(-1);
				}else {
					int from = 0;
					int to = s - 1;
					while ((to-from)>=1) {
						int mid = (to+from)/2;
						if (count.sum(mid, s) <= r) to = mid;
						else from = mid + 1;
					}
					long res = r - count.sum(to, s);
					long ans = score.sum(to, s);
					if (to - 1 >= 0) ans += res * list.get(to - 1);
					out.println(ans);
				}
			}
		}
		out.flush();
	}
	static class FenwickTree{
	    private int _n;
	    private long[] data;

	    public FenwickTree(int n){
	        this._n = n;
	        data = new long[n];
	    }

	    /**
	     * @verified https://atcoder.jp/contests/practice2/tasks/practice2_b
	     * @submission https://atcoder.jp/contests/practice2/submissions/16580495
	     */
	    public FenwickTree(long[] data) {
	        this(data.length);
	        build(data);
	    }

	    public void set(int p, long x){
	        add(p, x - get(p));
	    }

	    public void add(int p, long x){
	        assert(0<=p && p<_n);
	        p++;
	        while(p<=_n){
	            data[p-1] += x;
	            p += p&-p;
	        }
	    }
	    public long sum(int l, int r){
	        assert(0<=l && l<=r && r<=_n);
	        return sum(r)-sum(l);
	    }

	    public long get(int p){
	        return sum(p, p+1);
	    }

	    private long sum(int r){
	        long s = 0;
	        while(r>0){
	            s += data[r-1];
	            r -= r&-r;
	        }
	        return s;
	    }

	    private void build(long[] dat) {
	        System.arraycopy(dat, 0, data, 0, _n);
	        for (int i=1; i<=_n; i++) {
	            int p = i+(i&-i);
	            if(p<=_n){
	                data[p-1] += data[i-1];
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