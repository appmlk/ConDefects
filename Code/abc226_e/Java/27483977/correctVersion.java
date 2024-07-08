import java.io.*;
import java.util.*;
 
public class Main
{
	static class InputReader {
		BufferedReader reader;
		StringTokenizer tokenizer;
		public InputReader(InputStream stream) {
			reader = new BufferedReader(new InputStreamReader(stream), 32768);
			tokenizer = null;
		}
		String next() { // reads in the next string
			while (tokenizer == null || !tokenizer.hasMoreTokens()) {
				try {
					tokenizer = new StringTokenizer(reader.readLine());
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
			return tokenizer.nextToken();
		}
		public int nextInt() { // reads in the next int
			return Integer.parseInt(next());
		}
		public long nextLong() { // reads in the next long
			return Long.parseLong(next());
		}
		public double nextDouble() { // reads in the next double
			return Double.parseDouble(next());
			}
		}
	static InputReader r = new InputReader(System.in);
	static PrintWriter pw = new PrintWriter(System.out);
	static boolean[] visit;static ArrayList<Integer>[] adj; static int n,m, count, vertices, edges;
	public static void main(String[] args)
	{
		n = r.nextInt();
		m = r.nextInt();
		adj = new ArrayList[n];
		for (int i = 0; i < n; i ++)
		{
			adj[i] = new ArrayList<Integer>();
		}
		visit = new boolean[n];
		count = 0;
		boolean flag = false;
		for (int i = 0; i < m; i ++)
		{
			int a = r.nextInt() - 1; int b = r.nextInt()-1;
			adj[a].add(b);
			adj[b].add(a);
		}
		for (int i = 0; i < n; i ++)
		{
			edges = 0;
			vertices = 0;
			if (!visit[i])
			{
				count ++;
				dfs(i);
				if (edges!=2*vertices)
				{
					flag = true;
				}
			}
		}
		pw.println(flag ? 0: binpow(2,count,998244353));
		pw.close();
	}
	static void dfs(int node)
	{
		vertices ++;
		visit[node] = true;
		for (int i : adj[node])
		{
			if (!visit[i])
			{
				dfs(i);
			}
		}
		edges += adj[node].size();
	}
	public static long binpow(long x, long n, long m) {
		assert(n >= 0);
		x %= m;  // note: m * m must be less than 2^63 to avoid ll overflow
		long res = 1;
		while (n > 0) {
			if (n % 2 == 1)  // if n is odd
				res = res * x % m;
			x = x * x % m;
			n /= 2;  // divide by two
		}
		return res;
	}
}