
import java.util.*;

import java.lang.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws java.lang.Exception {
		PrintWriter out = new PrintWriter(System.out);
		FastReader sc = new FastReader();
		int testCases = 1;
//		testCases = sc.nextInt();
		while (testCases-- > 0) {
			
			int n=sc.nextInt();
			int m=sc.nextInt();
			PriorityQueue<Integer>free=new PriorityQueue<>((a,b)->a-b);
			PriorityQueue<int[]>pq=new PriorityQueue<>((a,b)->a[0]-b[0]);
			long ans[]=new long[n];
			int p=0;
			for(int i=0;i<n;i++) free.add(i);
			for(int i=0;i<m;i++) {
				int t=sc.nextInt();
				int w=sc.nextInt();
				int s=sc.nextInt();
				while(!pq.isEmpty()&&pq.peek()[0]<=t) {
					free.add(pq.poll()[1]);
				}
				if(free.isEmpty()) continue;
				int u=free.poll();
				ans[u]+=w;
				pq.add(new int[] {t+s,u});
			}
			for(int i=0;i<n;i++) out.println(ans[i]);
		}
		out.close();
	}
	
	

	
	
	public static long power(long a, long b) {
		long res = 1;
		long mod = (long) 1e18 + 7;
		while (b > 0) {
			if ((b & 1) > 0)
				res = (res * a) % mod;
			a = (a * a) % mod;
			b /= 2;
		}
		return res;
	}

	public static boolean isPallindrome(String a, String b) {
		int n = a.length();
		for (int i = 0; i < n; i++)
			if (a.charAt(i) != b.charAt(i))
				return false;
		return true;
	}

	public static long gcd(long a, long b) {
		if (a == 0)
			return b;
		return gcd(b % a, a);
	}

	public static int[] EEA(int a, int b) {

		if (a == 0)
			return new int[] { b, 0, 1 };
		int out[] = EEA(b % a, a);
		return new int[] { out[0], out[2] - (b / a) * (out[1]), out[1] };

	}

	private static long lcm(long a, long b) {
		return (a * b) / gcd(a, b);
	}

	static class FenWick {
		int n;
		long tree[];
		int mod = 1000000007;

		public FenWick(int n) {
			this.n = n;
			tree = new long[n];
		}

		public void add(int idx, long d) {
			while (idx < n) {
				tree[idx] += d;
				tree[idx] += mod;
				tree[idx] %= mod;
				idx += (idx & (-idx));
			}
		}

		public long find(int idx) {
			long sum = 0;
			while (idx > 0) {
				sum += tree[idx];
				sum %= mod;
				idx -= (idx & (-idx));
			}
			return sum;
		}
	}

	static class DSU {
		int parent[];
		int rank[];
		int size[];

		public DSU(int n) {
			this.parent = new int[n];
			this.rank = new int[n];
			size = new int[n];
			for (int i = 0; i < n; i++)
				size[i] = 1;
			for (int i = 0; i < n; i++)
				parent[i] = i;
		}

		public int find(int x) {
			if (parent[x] == x)
				return x;
			return parent[x] = find(parent[x]);
		}

		public boolean union(int x, int y) {
			int px = find(x);
			int py = find(y);
			if (px == py) {
				return false;
			}
			if (rank[px] > rank[py]) {
				parent[py] = px;
				size[px] += size[py];
			} else if (rank[py] > rank[px]) {
				parent[px] = py;
				size[py] += size[px];
			} else {
				rank[px]++;
				parent[py] = px;
				size[px] += size[py];
			}
			return true;

		}
	}

	static class FastReader {
		BufferedReader br;
		StringTokenizer st;

		public FastReader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() {
			while (st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}

		long nextLong() {
			return Long.parseLong(next());
		}

		double nextDouble() {
			return Double.parseDouble(next());
		}

		String nextLine() {
			String str = "";
			try {
				str = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}

}