

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Set;

public class Main {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "";
	
	static void solve()
	{
		int n = ni(), m = ni(), K = ni();
		if(K % 2 != 0){
			out.println("No");
			return;
		}
		out.println("Yes");
		int[] from = new int[m];
		int[] to = new int[m];
		DJSet ds = new DJSet(n);
		for(int i = 0;i < m;i++){
			from[i] = ni()-1;
			to[i] = ni()-1;
			ds.unite(from[i], to[i]);
		}

		int[] f = new int[n];
		int rem = K;
		for(int i = 0;i < n;i++){
			if(ds.upper[i] < 0){
				f[i] = Math.min(rem, (-ds.upper[i])/2*2);
				rem -= f[i];
			}
		}
		if(rem > 0){
			out.println("No");
			return;
		}
		boolean[] hit = new boolean[n+1];
		for(int i = 0;i < n;i++){
			if(f[ds.root(i)] > 0){
				hit[i] = true;
				f[ds.root(i)]--;
			}
		}

		int[][] g = packU(n, from, to);
		int[] par = spanningTreeX(g);
		g = parentToG(par);
//		tr(g);
		int[][] pars = parents(g, 0);
		int[] ord = pars[1];
		Set<Long> set = new HashSet<>();
		boolean[] dp = new boolean[n+1];
		for(int i = n;i >= 1;i--){
			int cur = ord[i];
			for(int e : g[cur]){
				if(e != par[cur]){
					dp[cur] ^= dp[e];
				}
			}
			if(hit[cur]){
				dp[cur] ^= true;
			}
			if(dp[cur]){
				set.add((long)cur<<32|par[cur]);
				set.add((long)par[cur]<<32|cur);
			}
		}
		out.println(set.size() / 2);
		for(int i = 0;i < m;i++){
			long code = (long)from[i]<<32|to[i];
			if(set.contains(code)) {
				out.print(i + 1 + " ");
			}
		}
		out.println();
	}

	public static class DJSet {
		public int[] upper;

		public DJSet(int n) {
			upper = new int[n];
			Arrays.fill(upper, -1);
		}

		public int root(int x) {
			return upper[x] < 0 ? x : (upper[x] = root(upper[x]));
		}

		public boolean equiv(int x, int y) {
			return root(x) == root(y);
		}

		public boolean unite(int x, int y) {
			x = root(x);
			y = root(y);
			if (x != y) {
				if (upper[y] < upper[x]) {
					int d = x;
					x = y;
					y = d;
				}
				upper[x] += upper[y];
				upper[y] = x;
			}
			return x == y;
		}

		public int count() {
			int ct = 0;
			for (int u : upper) if (u < 0) ct++;
			return ct;
		}

		public int[][] toBucket() {
			int n = upper.length;
			int[][] ret = new int[n][];
			int[] rp = new int[n];
			for (int i = 0; i < n; i++) if (upper[i] < 0) ret[i] = new int[-upper[i]];
			for (int i = 0; i < n; i++) {
				int r = root(i);
				ret[r][rp[r]++] = i;
			}
			return ret;
		}
	}


	public static int[][] parents(int[][] g, int root)
	{
		int n = g.length;
		int[] par = new int[n];
		Arrays.fill(par, -1);

		int[] depth = new int[n];
		depth[0] = 0;

		int[] q = new int[n];
		q[0] = root;
		for(int p = 0, r = 1;p < r;p++) {
			int cur = q[p];
			for(int nex : g[cur]){
				if(par[cur] != nex){
					q[r++] = nex;
					par[nex] = cur;
					depth[nex] = depth[cur] + 1;
				}
			}
		}
		return new int[][] {par, q, depth};
	}

	public static int[][] parentToG(int[] par)
	{
		int n = par.length;
		int[] ct = new int[n];
		for(int i = 0;i < n;i++){
			if(par[i] >= 0){
				ct[i]++;
				ct[par[i]]++;
			}
		}
		int[][] g = new int[n][];
		for(int i = 0;i < n;i++){
			g[i] = new int[ct[i]];
		}
		for(int i = 0;i < n;i++){
			if(par[i] >= 0){
				g[par[i]][--ct[par[i]]] = i;
				g[i][--ct[i]] = par[i];
			}
		}
		return g;
	}


	public static int[] spanningForest(int[][] g)
	{
		int n = g.length;
		int[] par = new int[n];
		Arrays.fill(par, -1);
		boolean[] ved = new boolean[n];
		int[] q = new int[n];
		int p = 0;
		for(int z = 0;z < n;z++) {
			if(ved[z])continue;
			q[p++] = z;
			ved[z] = true;
			for (int i = p-1; i < p; i++) {
				for (int e : g[q[i]]) {
					if (!ved[e]){
						q[p++] = e;
						ved[e] = true;
						par[e] = q[i];
					}
				}
			}
		}
		return par;
	}

	public static int[] spanningTreeX(int[][] g)
	{
		int[] par = spanningForest(g);
		int n = g.length;
		par = Arrays.copyOf(par, n+1);
		par[n] = -1;
		for(int i = 0;i < n;i++){
			if(par[i] == -1)par[i] = n;
		}
		return par;
	}


	public static int[][] packU(int n, int[] from, int[] to) {
		return packU(n, from, to, from.length);
	}

	public static int[][] packU(int n, int[] from, int[] to, int sup) {
		int[][] g = new int[n][];
		int[] p = new int[n];
		for (int i = 0; i < sup; i++) p[from[i]]++;
		for (int i = 0; i < sup; i++) p[to[i]]++;
		for (int i = 0; i < n; i++) g[i] = new int[p[i]];
		for (int i = 0; i < sup; i++) {
			g[from[i]][--p[from[i]]] = to[i];
			g[to[i]][--p[to[i]]] = from[i];
		}
		return g;
	}


	public static void main(String[] args) throws Exception
	{
		long S = System.currentTimeMillis();
		is = INPUT.isEmpty() ? System.in : new ByteArrayInputStream(INPUT.getBytes());
		out = new PrintWriter(System.out);
		
		solve();
		out.flush();
		long G = System.currentTimeMillis();

	}
	
	private static boolean eof()
	{
		if(lenbuf == -1)return true;
		int lptr = ptrbuf;
		while(lptr < lenbuf)if(!isSpaceChar(inbuf[lptr++]))return false;
		
		try {
			is.mark(1000);
			while(true){
				int b = is.read();
				if(b == -1){
					is.reset();
					return true;
				}else if(!isSpaceChar(b)){
					is.reset();
					return false;
				}
			}
		} catch (IOException e) {
			return true;
		}
	}
	
	private static byte[] inbuf = new byte[1024];
	static int lenbuf = 0, ptrbuf = 0;
	
	private static int readByte()
	{
		if(lenbuf == -1)throw new InputMismatchException();
		if(ptrbuf >= lenbuf){
			ptrbuf = 0;
			try { lenbuf = is.read(inbuf); } catch (IOException e) { throw new InputMismatchException(); }
			if(lenbuf <= 0)return -1;
		}
		return inbuf[ptrbuf++];
	}
	
	private static boolean isSpaceChar(int c) { return !(c >= 33 && c <= 126); }
//	private static boolean isSpaceChar(int c) { return !(c >= 32 && c <= 126); }
	private static int skip() { int b; while((b = readByte()) != -1 && isSpaceChar(b)); return b; }
	
	private static double nd() { return Double.parseDouble(ns()); }
	private static char nc() { return (char)skip(); }
	
	private static String ns()
	{
		int b = skip();
		StringBuilder sb = new StringBuilder();
		while(!(isSpaceChar(b))){
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}
	
	private static char[] ns(int n)
	{
		char[] buf = new char[n];
		int b = skip(), p = 0;
		while(p < n && !(isSpaceChar(b))){
			buf[p++] = (char)b;
			b = readByte();
		}
		return n == p ? buf : Arrays.copyOf(buf, p);
	}
	
	private static char[][] nm(int n, int m)
	{
		char[][] map = new char[n][];
		for(int i = 0;i < n;i++)map[i] = ns(m);
		return map;
	}

	private static int[][] nmi(int n, int m)
	{
		int[][] map = new int[n][];
		for(int i = 0;i < n;i++)map[i] = na(m);
		return map;
	}

	private static int[] na(int n)
	{
		int[] a = new int[n];
		for(int i = 0;i < n;i++)a[i] = ni();
		return a;
	}

	private static long[] nal(int n)
	{
		long[] a = new long[n];
		for(int i = 0;i < n;i++)a[i] = nl();
		return a;
	}

	private static int ni()
	{
		int num = 0, b;
		boolean minus = false;
		while((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
		if(b == '-'){
			minus = true;
			b = readByte();
		}
		
		while(true){
			if(b >= '0' && b <= '9'){
				num = num * 10 + (b - '0');
			}else{
				return minus ? -num : num;
			}
			b = readByte();
		}
	}
	
	private static long nl()
	{
		long num = 0;
		int b;
		boolean minus = false;
		while((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
		if(b == '-'){
			minus = true;
			b = readByte();
		}
		
		while(true){
			if(b >= '0' && b <= '9'){
				num = num * 10 + (b - '0');
			}else{
				return minus ? -num : num;
			}
			b = readByte();
		}
	}
	
	private static void tr(Object... o) { if(INPUT.length() != 0)System.out.println(Arrays.deepToString(o)); }
}
