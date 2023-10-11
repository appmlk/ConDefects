import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;



public class Main {
	static final long MOD=1000000007;
	static final long MOD1=998244353;
	static int size = 0;
	static long MAX = 1000000000000000000l;
	static long M;
	public static void main(String[] args){
		PrintWriter out = new PrintWriter(System.out);
		InputReader sc=new InputReader(System.in);
		int n = sc.nextInt();
		graph G = new graph(n);
		for (int i = 0; i < n-1; i++) {
			int a = sc.nextInt()-1;
			int b = sc.nextInt()-1;
			long w = sc.nextLong();
			G.addEdge(a, b, w);
			G.addEdge(b, a, w);
		}
		long[] D = sc.nextLongArray(n);
		Rerooting rerooting = new Rerooting(n, 0l, G, D);
		long[] ans = new long[n];
		rerooting.calc(0, -1, ans);
		for (int i = 0; i < ans.length; i++) {
			out.println(ans[i]);
		}
		out.flush();
   	}
	//全方位木dp, 更新するものが1つver
	//関数merge, f, gを変えて使う
	//注)fとgのindexは適切なものを使う
	static class Rerooting {
		long E;
		long[] dp1;
		long[] dp2;
		graph G;
		long[] D;
		public Rerooting(int n, long E, graph G, long[] D){
			this.E = E;
			this.dp1 = new long[n];
			this.dp2 = new long[n];
			this.G = G;
			this.D = Arrays.copyOf(D, n);
			dfs1(0, -1);
			dfs2(0, -1);
		}
		//dp_v = g(merge(f(dp_ch1,c), f(dp_ch2,c), ..))
		long f(long dp, int index, long v) {
			return Math.max(dp, D[index]) + v;
		}
		long g(long x, int index) {
			return x;
		}
		long merge(long x, long y) {
			return Math.max(x, y);
		}
		void dfs1(int v, int p) {
			long res = E;
			for (Edge e: G.list[v]) {
				if (e.to == p) continue;
				dfs1(e.to, v);
				res = merge(res, f(dp1[e.to], e.to, e.v));
			}
			dp1[v] = g(res, v);
		}
		void dfs2(int v, int p) {
			if (p==-1) dp2[v] = E;
			int size = G.list[v].size();
			long[] dp_l = new long[size+1];
			long[] dp_r = new long[size+1];
			dp_l[0] = E;
			dp_r[size] = E;
			long par_w = 0;
			for (int i = 1; i < size; i++) {
				int to = G.list[v].get(i-1).to;
				long w = G.list[v].get(i-1).v; 
				if (to == p) {
					par_w = w;
					dp_l[i] = dp_l[i-1];
					continue;
				}
				dp_l[i] = merge(dp_l[i-1], f(dp1[to], to, w));
			}
			for (int i = size-1; i >= 0; i--) {
				int to = G.list[v].get(i).to;
				long w = G.list[v].get(i).v; 
				if (to == p) {
					par_w = w;
					dp_r[i] = dp_r[i+1];
					continue;
				}
				dp_r[i] = merge(dp_r[i+1], f(dp1[to], to, w));
			}
			for (int i = 0; i < size; i++) {
				int to = G.list[v].get(i).to;
				if (to == p) continue;
				dp2[to] = merge(dp_l[i], dp_r[i+1]);
				if(v!=0) dp2[to] = merge(dp2[to], f(dp2[v], p, par_w));
				dp2[to] = g(dp2[to], v);
				dfs2(to, v);
			}
		}
		void calc(int v, int p, long[] ans) {
			ans[v] = E;
			for (Edge e: G.list[v]) {
				if (e.to == p) {
					ans[v] = f(dp2[v], p, e.v);
					continue;
				}
				ans[v] = merge(ans[v], f(dp1[e.to], e.to, e.v));
				calc(e.to, v, ans);
			}
			ans[v] = g(ans[v], v);
		}
	}
	static class Edge implements Comparable<Edge>{
		int to;
		long v;
		public Edge(int to,long v) {
			this.to=to;
			this.v=v;
		}
		@Override
		public boolean equals(Object obj){
			if(obj instanceof Edge) {
				Edge other = (Edge) obj;
				return other.to==this.to && other.v==this.v;
			}
			return false;
		}//同値の定義
		@Override
		public int hashCode() {
			return Objects.hash(this.to,this.v);
		}
		@Override
		  public int compareTo( Edge p2 ){
			 if (this.v>p2.v) {
				return 1;
			}
			 else if (this.v<p2.v) {
				return -1;
			}
			 else {
				return 0;
			}
		  }
	} 
	static class graph{
		ArrayList<Edge>[] list;
		int size;
		long INF=Long.MAX_VALUE/2;
		int[] color;
		@SuppressWarnings("unchecked")
		public graph(int n) {
			size = n;
			list = new ArrayList[n];
			color =new int[n];
			for(int i=0;i<n;i++){
				list[i] = new ArrayList<Edge>();
			}
		}
		void addEdge(int from,int to,long w) {
			list[from].add(new Edge(to,w));
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