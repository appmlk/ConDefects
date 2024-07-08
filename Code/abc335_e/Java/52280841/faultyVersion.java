import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);) {
			int n = Integer.parseInt(sc.next());
			int m = Integer.parseInt(sc.next());
			int[] a = new int[n];
			for(int i = 0; i < n; i++) a[i] = Integer.parseInt(sc.next());
			
			List<List<Integer>> g = new ArrayList<List<Integer>>(); 
			for(int i = 0; i < n; i++) g.add(new ArrayList<Integer>());
			for(int i = 0; i < m; i++) {
				int u = Integer.parseInt(sc.next());
				int v = Integer.parseInt(sc.next());
				u--;v--;
				g.get(u).add(v);
				g.get(v).add(u);
			}
			
			int[] dist = new int[n];
			boolean[] used = new boolean[n];
			final int INF = 1 << 30;
			Arrays.fill(dist, -INF);
			dist[0] = 1;
			PriorityQueue<Integer[]> pq = new PriorityQueue<Integer[]>((u, v) -> {
				if(Integer.compare(u[0], v[0]) == 0) {
					return Integer.compare(v[1], u[1]);
				} 
				return Integer.compare(u[0], v[0]);
			});
			pq.add(new Integer[] {a[0], -dist[0], 0});
			while(!pq.isEmpty()) {
				int v = pq.peek()[2];
				pq.poll();
				if(used[v]) continue;
				used[v] = true;
				for(int to : g.get(v)) {
					if(a[v] <= a[to]) {
						dist[to] = Math.max(dist[to], dist[v] + (a[v] != a[to] ? 1 : 0));
						pq.add(new Integer[] {a[to], -dist[to], to});
					}
				}
			}
			
			
			System.out.println(Math.max(dist[n - 1], 0));
		}
	}
	
	static class UnionFind{
		
		private int[] par;
		private int[] siz;
		
		public UnionFind(int n) {
			par = new int[n];
			siz= new int[n];
			Arrays.fill(par, -1);
			Arrays.fill(siz, 1);
		}
		
		public int root(int x) {
			if(par[x] == -1) return x;
			return par[x] = root(par[x]);
		}
		
		public boolean same(int x, int y) {
			return root(x) == root(y);
		}
		
		public int siz(int x) {
			return siz[root(x)];
		}
		
		public boolean unite(int x, int y) {
			x = root(x);
			y = root(y);
			
			if(x == y) return false;
			
			if(siz[x] > siz[y]) {
				int tmp = x;
				x = y;
				y = tmp;
			}
			
			par[x] = y;
			siz[y] += siz[x];
			
			return true;
		}
	}
	
}

