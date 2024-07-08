import java.io.*;
import java.util.*;

class Main {
	int N, M;
	int[] A;
	UnionFind uf;
	
	@SuppressWarnings("unchecked")
	void calc() {
		int[] ns = nextInts();
		N = ns[0]; M = ns[1];
		uf = new UnionFind(N);
		A = nextInts();
		List<int[]>[] no = new List[200001]; Arrays.setAll(no, i -> new ArrayList<>());
		for (int i = 0; i < M; i++) {
			int[] uv = nextInts();
			int u = uv[0] - 1, v = uv[1] - 1;
			if (A[u] == A[v]) uf.connect(u, v);
			if (A[u] < A[v]) no[A[u]].add(new int[] {u, v});
			if (A[u] > A[v]) no[A[v]].add(new int[] {v, u});
		}
		
		var dp = new int[N];
		int r0 = uf.getRoot(0);
		dp[r0] = 1;
		for (var n: no) {
			for (int[] uv: n) {
				int u = uf.getRoot(uv[0]), v = uf.getRoot(uv[1]);
				if (dp[u] > 0) dp[v] = Math.max(dp[v], dp[u]+1);
			}
		}
		System.out.println(dp[uf.getRoot(N-1)]);
	}

	// ---
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String next() { try { return br.readLine(); } catch (Exception e) { return null; } }
	String[] nexts() { return next().split(" "); }

	static int i(String s) { return Integer.parseInt(s); }
	int nextInt() { return i(next()); }
	int[] nextInts() { return Arrays.stream(nexts()).mapToInt(Main::i).toArray(); }

	public static void main(String[] args) {
		new Main().calc();
	}
}

final class UnionFind {
	int[] size, parent;
	
	UnionFind(int n) {
		size = new int[n];
		parent = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
			size[i] = 1;
		}
	}
	
	boolean isConnected(int x, int y) {
		return getRoot(x) == getRoot(y);
	}

	boolean connect(int x, int y) {
		int ix = getRoot(x);
		int iy = getRoot(y);
		if (ix == iy) return false;
		if (size[ix] > size[iy]) {
			parent[iy] = ix;
			size[ix] += size[iy];
		} else {
			parent[ix] = iy;
			size[iy] += size[ix];
		}
		return true;
	}

	int getRoot(int x) {
		if (x != parent[x]) {
			parent[x] = getRoot(parent[x]);
		}
		return parent[x];
	}
	
	int size(int x) { return size[getRoot(x)]; }
}
