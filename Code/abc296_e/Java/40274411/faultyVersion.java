import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeMap;

public final class Main {
	static FastReader fr = new FastReader();
	static PrintWriter out = new PrintWriter(System.out);
	static final int gigamod = 1000000007;
	static final int mod = 998244353;
	static int t = 1;
	static boolean[] isPrime;
	static int[] smallestFactorOf;
	static final int UP = 0, LEFT = 1, DOWN = 2, RIGHT = 3;
	static int cmp;
	static long[] twoPow;
	static int sz = 5;
	
	@SuppressWarnings({"unused"})
	public static void main(String[] args) throws Exception {
		OUTER: 
		for (int tc = 0; tc < t; tc++) {
			
			int n = fr.nextInt();
			int[] arr = fr.nextIntArray(n);
			
			Digraph dg = new Digraph(n);
			for (int i = 0; i < n; i++)
				dg.addEdge(i, arr[i] - 1);
			
			// every node which is the part of some cycle is
			// a winning node for us
			boolean[] marked = new boolean[n];
			boolean[] onStack = new boolean[n];
			boolean[] isAns = new boolean[n];
			for (int i = 0; i < n; i++)
				if (!marked[i])
					dfs(i, dg, marked, onStack, isAns, new int[1]);

			int ans = 0;
			for (boolean b : isAns)
				if (b)
					ans++;
			
			out.println(ans);
		}
		out.close();
	}
	
	static void dfs(int current, Digraph dg, boolean[] marked, boolean[] onStack, boolean[] isAns, int[] curseNode) {
		if (onStack[current]) {
			// this is the part of some cycle
			isAns[current] = true;
			curseNode[0] = current;
			return;
		}
		
		if (marked[current]) return; // this node was processed earlier
		
		marked[current] = true;
		onStack[current] = true;
		
		for (int adj : dg.adj(current)) {
			dfs(adj, dg, marked, onStack, isAns, curseNode);
			
			if (isAns[adj] && curseNode[0] != -1)
				isAns[current] = true;
		}
		
		if (curseNode[0] == current)
			curseNode[0] = -1;
		
		onStack[current] = false;
	}
	
	static int nn;
	static int[] arr;
	static int[] treeSum;
	static int[] treeMinPrefSum;
	static int[] lazy;
	
	static void init(int n, int[] brr) {
		nn = n;
		arr = brr.clone();
		treeSum = new int[4 * nn];
		lazy = new int[4 * nn];
		treeMinPrefSum = new int[4 * nn];
		build(1, 0, nn - 1);
	}
	
	static void build(int node, int left, int right) {
		if (left == right) {
			treeSum[node] = arr[left];
			treeMinPrefSum[node] = arr[left];
			return;
		}
			
		int mid = (left + right) >> 1;
		build((node<<1), left, mid);
		build((node<<1|1), mid + 1, right);
		
		treeSum[node] = treeSum[node<<1] + treeSum[node<<1|1];
	
	}
	
	static void flip(int node, int left, int right, int idx) {
		if (left == right) {
			treeSum[node] = -treeSum[node];
			return;
		}
		
		if (idx < left || idx > right) return;
		
		int mid = (left + right) >> 1;
		flip((node<<1), left, mid, idx);
		flip((node<<1|1), mid + 1, right, idx);
	}
	
	/*static int nn;
	static int[] arr;
	static int[] tree;
	static int[] lazy;
	
	static void build(int node, int leftt, int rightt) {
		if (leftt == rightt) {
			tree[node] = arr[leftt];
			return;
		}
		
		int mid = (leftt + rightt) >> 1;
		build(node << 1, leftt, mid);
		build(node << 1 | 1, mid + 1, rightt);
		
		tree[node] = Math.min(tree[node << 1], tree[node << 1 | 1]);
	}
	
	static void segAdd(int node, int leftt, int rightt, int segL, int segR, int val) {
		if (lazy[node] != 0) {
			tree[node] += lazy[node];
			if (leftt != rightt) {
				lazy[node << 1] += lazy[node];
				lazy[node << 1 | 1] += lazy[node];
			}
			lazy[node] = 0;
		}
		
		if (segL > rightt || segR < leftt) return;
		
		if (segL <= leftt && rightt <= segR) {
			tree[node] += val;
			if (leftt != rightt) {
				lazy[node << 1] += val;
				lazy[node << 1 | 1] += val;
			}
			lazy[node] = 0;
			return;
		}
		
		int mid = (leftt + rightt) >> 1;
		segAdd(node << 1, leftt, mid, segL, segR, val);
		segAdd(node << 1 | 1, mid + 1, rightt, segL, segR, val);
		
		tree[node] = Math.min(tree[node << 1], tree[node << 1 | 1]);
	}
	
	static int minQuery(int node, int leftt, int rightt, int segL, int segR) {
		if (lazy[node] != 0) {
			tree[node] += lazy[node];
			if (leftt != rightt) {
				lazy[node << 1] += lazy[node];
				lazy[node << 1 | 1] += lazy[node];
			}
			lazy[node] = 0;
		}
		
		if (segL > rightt || segR < leftt) return Integer.MAX_VALUE / 10;
		
		if (segL <= leftt && rightt <= segR)
			return tree[node];
		
		int mid = (leftt + rightt) >> 1;
		
		return Math.min(minQuery(node << 1, leftt, mid, segL, segR), 
				minQuery(node << 1 | 1, mid + 1, rightt, segL, segR));
	}
	
	*/
	
	static void compute_automaton(String s, int[][] aut) {
	    s += '#';
	    int n = s.length();
	    int[] pi = prefix_function(s.toCharArray());
	    for (int i = 0; i < n; i++) {
	        for (int c = 0; c < 26; c++) {
	            int j = i;
	            while (j > 0 && 'A' + c != s.charAt(j))
	                j = pi[j-1];
	            if ('A' + c == s.charAt(j))
	                j++;
	            aut[i][c] = j;
	        }
	    }
	}
	
	static void timeDFS(int current, int from, UGraph ug, 
			int[] time, int[] tIn, int[] tOut) {
		tIn[current] = ++time[0];
		for (int adj : ug.adj(current))
			if (adj != from)
				timeDFS(adj, current, ug, time, tIn, tOut);
		tOut[current] = ++time[0];
	}
		
	static boolean areCollinear(long x1, long y1, long x2, long y2, long x3, long y3) {
		
		// we will check if c3 lies on line through (c1, c2)
		
		long a = x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2);
		return a == 0;
	}
	
	static int[] treeDiameter(UGraph ug) {
		int n = ug.V();
		
		int farthest = -1;
		int[] distTo = new int[n];
		diamDFS(0, -1, 0, ug, distTo);
		
		int maxDist = -1;
		for (int i = 0; i < n; i++)
			if (maxDist < distTo[i]) {
				maxDist = distTo[i];
				farthest = i;
			}
		
		distTo = new int[n + 1];
		diamDFS(farthest, -1, 0, ug, distTo);
		distTo[n] = farthest;
		
		return distTo;
	}
	
	static void diamDFS(int current, int from, int dist, UGraph ug, int[] distTo) {
		distTo[current] = dist;
		for (int adj : ug.adj(current))
			if (adj != from)
				diamDFS(adj, current, dist + 1, ug, distTo);
	}
	
	static class TreeDistFinder {
		UGraph ug;
		int n;
		int[] depthOf;
		LCA lca;
		
		TreeDistFinder(UGraph ug) {
			this.ug = ug;
			n = ug.V();
			depthOf = new int[n];
			depthCalc(0, -1, ug, 0, depthOf);
			lca = new LCA(ug, 0);
		}
		
		TreeDistFinder(UGraph ug, int a) {
			this.ug = ug;
			n = ug.V();
			depthOf = new int[n];
			depthCalc(a, -1, ug, 0, depthOf);
			lca = new LCA(ug, a);
		}
		
		private void depthCalc(int current, int from, UGraph ug, int depth, int[] depthOf) {
			depthOf[current] = depth;
			for (int adj : ug.adj(current))
				if (adj != from)
					depthCalc(adj, current, ug, depth + 1, depthOf);
		}
		
		public int dist(int a, int b) {
			int lc = lca.lca(a, b);
			return (depthOf[a] - depthOf[lc] + depthOf[b] - depthOf[lc]);
		}
	}
	
	public static long[][] GCDSparseTable(long[] a)
	{
		int n = a.length;
		int b = 32-Integer.numberOfLeadingZeros(n);
		long[][] ret = new long[b][];
		for(int i = 0, l = 1;i < b;i++, l*=2) {
			if(i == 0) {
				ret[i] = a;
			} else {
				ret[i] = new long[n-l+1];
				for(int j = 0;j < n-l+1;j++) {
					ret[i][j] = gcd(ret[i-1][j], ret[i-1][j+l/2]);
				}
			}
		}
		return ret;
	}
	
	public static long sparseRangeGCDQ(long[][] table, int l, int r)
	{
		// [a,b)
		if(l > r)return 1;
		// 1:0, 2:1, 3:1, 4:2, 5:2, 6:2, 7:2, 8:3
		int t = 31-Integer.numberOfLeadingZeros(r-l);
		return gcd(table[t][l], table[t][r-(1<<t)]);
	}
	
	static class Trie {
		
		TrieNode root;
		
		Trie(char[][] strings) {
			root = new TrieNode('A', false);
			construct(root, strings);
		}
		
		public Stack<String> set(TrieNode root) {
			Stack<String> set = new Stack<>();
			
			StringBuilder sb = new StringBuilder();
			for (TrieNode next : root.next)
				collect(sb, next, set);
			
			return set;
		}
		
		private void collect(StringBuilder sb, TrieNode node, Stack<String> set) {
			if (node == null) return;
			
			sb.append(node.character);
			if (node.isTerminal)
				set.add(sb.toString());
			
			for (TrieNode next : node.next)
				collect(sb, next, set);
			
			if (sb.length() > 0)
				sb.setLength(sb.length() - 1);
		}
		
		private void construct(TrieNode root, char[][] strings) {
			
			// we have to construct the Trie
			for (char[] string : strings) {
				if (string.length == 0) continue;
				
				root.next[string[0] - 'a'] = put(root.next[string[0] - 'a'], string, 0);
				
				if (root.next[string[0] - 'a'] != null)
					root.isLeaf = false;
			}
		}
		
		private TrieNode put(TrieNode node, char[] string, int idx) {
			boolean isTerminal = (idx == string.length - 1);
			if (node == null) node = new TrieNode(string[idx], isTerminal);
			
			node.character = string[idx];
			node.isTerminal |= isTerminal;
			
			if (!isTerminal) {
				node.isLeaf = false;
				node.next[string[idx + 1] - 'a'] = put(node.next[string[idx + 1] - 'a'], string, idx + 1);
			}
			
			return node;
		}
		
		class TrieNode {
			char character;
			TrieNode[] next;
			boolean isTerminal, isLeaf;
			boolean canWin, canLose;
			
			TrieNode(char c, boolean isTerminallll) {
				character = c;
				isTerminal = isTerminallll;
				next = new TrieNode[26];
				isLeaf = true;
			}
		}
	}
	
	static class Edge implements Comparable<Edge> {
		int from, to;
		long weight, ans;
		int id;
		// int hash;
		
		Edge(int fro, int t, long wt, int i) {
			from = fro;
			to = t;
			id = i;
			weight = wt;
			// hash = Objects.hash(from, to, weight);
		}
		
		/*public int hashCode() {
			return hash;
		}*/
		
		public int compareTo(Edge that) {
			return Long.compare(this.id, that.id);
		}
	}
	
	public static long[][] minSparseTable(long[] a)
	{
		int n = a.length;
		int b = 32-Integer.numberOfLeadingZeros(n);
		long[][] ret = new long[b][];
		for(int i = 0, l = 1;i < b;i++, l*=2) {
			if(i == 0) {
				ret[i] = a;
			}else {
				ret[i] = new long[n-l+1];
				for(int j = 0;j < n-l+1;j++) {
					ret[i][j] = Math.min(ret[i-1][j], ret[i-1][j+l/2]);
				}
			}
		}
		return ret;
	}
	
	public static long sparseRangeMinQ(long[][] table, int l, int r)
	{
		// [a,b)
		if(l >= r)return Integer.MAX_VALUE;
		// 1:0, 2:1, 3:1, 4:2, 5:2, 6:2, 7:2, 8:3
		int t = 31-Integer.numberOfLeadingZeros(r-l);
		return Math.min(table[t][l], table[t][r-(1<<t)]);
	}
	
	public static long[][] maxSparseTable(long[] a)
	{
		int n = a.length;
		int b = 32-Integer.numberOfLeadingZeros(n);
		long[][] ret = new long[b][];
		for(int i = 0, l = 1;i < b;i++, l*=2) {
			if(i == 0) {
				ret[i] = a;
			}else {
				ret[i] = new long[n-l+1];
				for(int j = 0;j < n-l+1;j++) {
					ret[i][j] = Math.max(ret[i-1][j], ret[i-1][j+l/2]);
				}
			}
		}
		return ret;
	}
	
	public static long sparseRangeMaxQ(long[][] table, int l, int r)
	{
		// [a,b)
		if(l >= r)return Integer.MIN_VALUE;
		// 1:0, 2:1, 3:1, 4:2, 5:2, 6:2, 7:2, 8:3
		int t = 31-Integer.numberOfLeadingZeros(r-l);
		return Math.max(table[t][l], table[t][r-(1<<t)]);
	}
	
	static class LCA {
	    int[] height, first, segtree;
	    ArrayList<Integer> euler;
	    boolean[] visited;
	    int n;

	    LCA(UGraph ug, int root) {
	        n = ug.V();
	        height = new int[n];
	        first = new int[n];
	        euler = new ArrayList<>();
	        visited = new boolean[n];
	        dfs(ug, root, 0);
	        int m = euler.size();
	        segtree = new int[m * 4];
	        build(1, 0, m - 1);
	    }

	    void dfs(UGraph ug, int node, int h) {
	        visited[node] = true;
	        height[node] = h;
	        first[node] = euler.size();
	        euler.add(node);
	        for (int adj : ug.adj(node)) {
	            if (!visited[adj]) {
	                dfs(ug, adj, h + 1);
	                euler.add(node);
	            }
	        }
	    }

	    void build(int node, int b, int e) {
	        if (b == e) {
	            segtree[node] = euler.get(b);
	        } else {
	            int mid = (b + e) / 2;
	            build(node << 1, b, mid);
	            build(node << 1 | 1, mid + 1, e);
	            int l = segtree[node << 1], r = segtree[node << 1 | 1];
	            segtree[node] = (height[l] < height[r]) ? l : r;
	        }
	    }

	    int query(int node, int b, int e, int L, int R) {
	        if (b > R || e < L)
	            return -1;
	        if (b >= L && e <= R)
	            return segtree[node];
	        int mid = (b + e) >> 1;

	        int left = query(node << 1, b, mid, L, R);
	        int right = query(node << 1 | 1, mid + 1, e, L, R);
	        if (left == -1) return right;
	        if (right == -1) return left;
	        return height[left] < height[right] ? left : right;
	    }

	    int lca(int u, int v) {
	        int left = first[u], right = first[v];
	        if (left > right) {
	        	int temp = left;
	        	left = right;
	        	right = temp;
	        }
	        return query(1, 0, euler.size() - 1, left, right);
	    }
	}
	
	static class FenwickTree {

	    long[] array; // 1-indexed array, In this array We save cumulative information to perform efficient range queries and updates

	    public FenwickTree(int size) {
	        array = new long[size + 1];
	    }
	    
	    public long rsq(int ind) {
	        assert ind > 0;
	        long sum = 0;
	        while (ind > 0) {
	            sum += array[ind];
	            //Extracting the portion up to the first significant one of the binary representation of 'ind' and decrementing ind by that number
	            ind -= ind & (-ind);
	        }

	        return sum;
	    }
	    
	    public long rsq(int a, int b) {
	        assert b >= a && a > 0 && b > 0;

	        return rsq(b) - rsq(a - 1);
	    }
	    
	    public void update(int ind, long value) {
	        assert ind > 0;
	        while (ind < array.length) {
	            array[ind] += value;
	            //Extracting the portion up to the first significant one of the binary representation of 'ind' and incrementing ind by that number
	            ind += ind & (-ind);
	        }
	    }

	    public int size() {
	        return array.length - 1;
	    }
	}
	
	static class MaxFenwickTree {

	    long[] array; // 1-indexed array, In this array We save cumulative information to perform efficient range queries and updates

	    public MaxFenwickTree(int size) {
	        array = new long[size + 1];
	    }
	    
	    public long prefixMaxQuery(int upto) {
	        assert upto > 0;
	        long max = 0;
	        while (upto > 0) {
	            max = Math.max(max, array[upto]);
	            //Extracting the portion up to the first significant one of the binary representation of 'ind' and decrementing ind by that number
	            upto -= upto & (-upto);
	        }

	        return max;
	    }
	    
	    public void update(int ind, long value) {
	        assert ind > 0;
	        while (ind < array.length) {
	            array[ind] = value;
	            //Extracting the portion up to the first significant one of the binary representation of 'ind' and incrementing ind by that number
	            ind += ind & (-ind);
	        }
	    }

	    public int size() {
	        return array.length - 1;
	    }
	}
	
	static class Point implements Comparable<Point> {
		long x;
		long y;
		long z;
		long id;
		// private int hashCode;
		
		Point() {
			x = z = y = 0;
			// this.hashCode = Objects.hash(x, y, cost);
		}
		
		Point(Point p) {
			this.x = p.x;
			this.y = p.y;
			this.z = p.z;
			this.id = p.id;
			// this.hashCode = Objects.hash(x, y, cost);
		}
		
		Point(long x, long y, long z, long id) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.id = id;
			// this.hashCode = Objects.hash(x, y, id);
		}
		
		Point(long a, long b) {
			this.x = a;
			this.y = b;
			this.z = 0;
			// this.hashCode = Objects.hash(a, b);
		}
		
		Point(long x, long y, long id) {
			this.x = x;
			this.y = y;
			this.id = id;
		}
		
		@Override
		public int compareTo(Point o) {
			if (this.x < o.x)
				return -1;
			if (this.x > o.x)
				return 1;
			if (this.y < o.y)
				return -1;
			if (this.y > o.y)
				return 1;
			if (this.z < o.z)
				return -1;
			if (this.z > o.z)
				return 1;
			return 0;
		}
		
		@Override
		public boolean equals(Object that) {
			return this.compareTo((Point) that) == 0;
		}
	}
	
	static class BinaryLift {
		
		// FUNCTIONS: k-th ancestor and LCA in log(n) 
		
		int[] parentOf;
		int maxJmpPow;
		int[][] binAncestorOf;
		int n;
		int[] lvlOf;
		
		// How this works?
		
		// a. For every node, we store the b-ancestor for b in {1, 2, 4, 8, .. log(n)}.
		
		// b. When we need k-ancestor, we represent 'k' in binary and for each set bit, we 
		//    lift level in the tree.
		
		public BinaryLift(UGraph tree) {
			n = tree.V();
			maxJmpPow = logk(n, 2) + 1;
			parentOf = new int[n];
			binAncestorOf = new int[n][maxJmpPow];
			lvlOf = new int[n];
			for (int i = 0; i < n; i++)
				Arrays.fill(binAncestorOf[i], -1);
			parentConstruct(0, -1, tree, 0);
			binConstruct();
		}
		
		// TODO: Implement lvlOf[] initialization
		public BinaryLift(int[] parentOf) {
			this.parentOf = parentOf;
			n = parentOf.length;
			maxJmpPow = logk(n, 2) + 1;
			binAncestorOf = new int[n][maxJmpPow];
			lvlOf = new int[n];
			for (int i = 0; i < n; i++)
				Arrays.fill(binAncestorOf[i], -1);
			UGraph tree = new UGraph(n);
			for (int i = 1; i < n; i++)
				tree.addEdge(i, parentOf[i]);
			binConstruct();
			parentConstruct(0, -1, tree, 0);
		}
		
		private void parentConstruct(int current, int from, UGraph tree, int depth) {
			parentOf[current] = from;
			lvlOf[current] = depth;
			for (int adj : tree.adj(current))
				if (adj != from)
					parentConstruct(adj, current, tree, depth + 1);
		}
		
		private void binConstruct() {
			for (int node = 0; node < n; node++)
				for (int lvl = 0; lvl < maxJmpPow; lvl++)
					binConstruct(node, lvl);
		}
		
		private int binConstruct(int node, int lvl) {
			if (node < 0)
				return -1;
			if (lvl == 0)
				return binAncestorOf[node][lvl] = parentOf[node];
			if (node == 0)
				return binAncestorOf[node][lvl] = -1;
			
			if (binAncestorOf[node][lvl] != -1)
				return binAncestorOf[node][lvl];
			return binAncestorOf[node][lvl] = binConstruct(binConstruct(node, lvl - 1), lvl - 1);
		}
		
		// return ancestor which is 'k' levels above this one
		public int ancestor(int node, int k) {
			if (node < 0)
				return -1;
			if (node == 0)
				if (k == 0) return node;
				else return -1;
			if (k > (1 << maxJmpPow) - 1)
				return -1;
			if (k == 0)
				return node;
			
			int ancestor = node;
			int highestBit = Integer.highestOneBit(k);
			
			while (k > 0 && ancestor != -1) {
				ancestor = binAncestorOf[ancestor][logk(highestBit, 2)];
				k -= highestBit;
				highestBit = Integer.highestOneBit(k);
			}
			
			return ancestor;
		}
	
		public int lca(int u, int v) {
			
			if (u == v)
				return u;
			
			// The invariant will be that 'u' is below 'v' initially.
			if (lvlOf[u] < lvlOf[v]) {
				int temp = u;
				u = v;
				v = temp;
			}
			
			// Equalizing the levels.
			u = ancestor(u, lvlOf[u] - lvlOf[v]);
			
			if (u == v)
				return u;
			
			// We will now raise level by largest fitting power of two until possible.
			for (int power = maxJmpPow - 1; power > -1; power--)
				if (binAncestorOf[u][power] != binAncestorOf[v][power]) {
					u = binAncestorOf[u][power];
					v = binAncestorOf[v][power];
				}
			
			return ancestor(u, 1);
		}
	}
	
	static class DFSTree {
		
		// NOTE: The thing is made keeping in mind that the whole
		//  	 input graph is connected.
		
		UGraph tree;
		UGraph backUG;
		int hasBridge;
		int n;
		Edge backEdge;
		
		
		DFSTree(UGraph ug) {
			this.n = ug.V();
			tree = new UGraph(n);
			hasBridge = -1;
			backUG = new UGraph(n);
			treeCalc(0, -1, new boolean[n], ug);
		}
		
		private void treeCalc(int current, int from, boolean[] marked, UGraph ug) {
			if (marked[current]) {
				// This is a backEdge.
				backUG.addEdge(from, current);
				backEdge = new Edge(from, current, 1, 0);
				return;
			}
			
			if (from != -1)
				tree.addEdge(from, current);
			marked[current] = true;
			
			for (int adj : ug.adj(current))
				if (adj != from)
					treeCalc(adj, current, marked, ug);
		}
		
		public boolean hasBridge() {
			if (hasBridge != -1)
				return (hasBridge == 1);
			
			// We have to determine the bridge.
			bridgeFinder();
			return (hasBridge == 1);
		}
		
		int[] levelOf; 
		int[] dp;
		
		
		
		private void bridgeFinder() {
			
			// Finding the level of each node.
			levelOf = new int[n];
			
			levelDFS(0, -1, 0);
			
			// Applying DP solution.
			// dp[i] -> Highest level reachable from subtree of 'i' using
			//   		some backEdge.
			dp = new int[n];
			Arrays.fill(dp, Integer.MAX_VALUE / 100);
			dpDFS(0, -1);
			
			// Now, we will check each edge and determine whether its a
			// bridge.
			
			for (int i = 0; i < n; i++)
				for (int adj : tree.adj(i)) {
					
					// (i -> adj) is the edge.
					
					if (dp[adj] > levelOf[i])
						hasBridge = 1;
				}
			
			if (hasBridge != 1)
				hasBridge = 0;
		}
		
		private void levelDFS(int current, int from, int lvl) {
			levelOf[current] = lvl;
			for (int adj : tree.adj(current))
				if (adj != from)
					levelDFS(adj, current, lvl + 1);
		}
		
		private int dpDFS(int current, int from) {
			dp[current] = levelOf[current];
			
			for (int back : backUG.adj(current))
				dp[current] = Math.min(dp[current], levelOf[back]);
			
			for (int adj : tree.adj(current))
				if (adj != from)
					dp[current] = Math.min(dp[current], dpDFS(adj, current));
			
			return dp[current];
		}
	}
	
	static class UnionFind {
		// Uses weighted quick-union with path compression.
	    private int[] parent;  // parent[i] = parent of i
	    private int[] size;    // size[i] = number of sites in tree rooted at i
	                           // Note: not necessarily correct if i is not a root node
	    private int count;     // number of components

	    public UnionFind(int n) {
	        count = n;
	        parent = new int[n];
	        size = new int[n];
	        for (int i = 0; i < n; i++) {
	            parent[i] = i;
	            size[i] = 1;
	        }
	    }

	    // Number of connected components.
	    public int count() {
	        return count;
	    }
	  
	    // Find the root of p.
	    public int find(int p) {
	    	while (p != parent[p])
	            p = parent[p];
	        return p;
	    }

	    public boolean connected(int p, int q) {
	        return find(p) == find(q);
	    }
	    
	    public int numConnectedTo(int node) {
	    	return size[find(node)];
	    }
	    
	    // Weighted union.
	    public void union(int p, int q) {
	        int rootP = find(p);
	        int rootQ = find(q);
	        
	        if (rootP == rootQ) return;

	        // make smaller root point to larger one
	        if (size[rootP] < size[rootQ]) {
	            parent[rootP] = rootQ;
	            size[rootQ] += size[rootP];
	        }
	        else {
	            parent[rootQ] = rootP;
	            size[rootP] += size[rootQ];
	        }
	        count--;
	    }
	    
	    public static int[] connectedComponents(UnionFind uf) {
	    	// We can do this in nlogn.
	    	
	    	int n = uf.size.length;
	    	int[] compoColors = new int[n];
	    	for (int i = 0; i < n; i++)
	    		compoColors[i] = uf.find(i);
	    	
	    	HashMap<Integer, Integer> oldToNew = new HashMap<>();
	    	int newCtr = 0;
	    	
	    	for (int i = 0; i < n; i++) {
	    		
	    		int thisOldColor = compoColors[i];
	    		Integer thisNewColor = oldToNew.get(thisOldColor);
	    		if (thisNewColor == null)
	    			thisNewColor = newCtr++;
	    		
	    		oldToNew.put(thisOldColor, thisNewColor);
	    		compoColors[i] = thisNewColor;
	    	}
	    	
	    	return compoColors;
	    }
	}
	
	static class UGraph {
		// Adjacency list.
		private HashSet<Integer>[] adj;
		private static final String NEWLINE = "\n";
		private int E;
		
		@SuppressWarnings("unchecked")
		public UGraph(int V) {
			adj = (HashSet<Integer>[]) new HashSet[V];
			E = 0;
			for (int i = 0; i < V; i++)
				adj[i] = new HashSet<Integer>();
		}
		
		public void addEdge(int from, int to) {
			if (adj[from].contains(to)) return;
			E++;
			adj[from].add(to);
			adj[to].add(from);
		}
		
		public HashSet<Integer> adj(int from) {
			return adj[from];
		}
		
		public int degree(int v) {
			return adj[v].size();
		}
		
		public int V() {
			return adj.length;
		}
		
		public int E() {
			return E;
		}
		
		public String toString() {
			StringBuilder s = new StringBuilder();
			s.append(V() + " vertices, " + E() + " edges " + NEWLINE);
			for (int v = 0; v < V(); v++) {
				s.append(v + ": ");
				for (int w : adj[v]) {
					s.append(w + " ");
				}
				s.append(NEWLINE);
			}
			return s.toString();
		}
		
		public static void dfsMark(int current, boolean[] marked, UGraph g) {
			if (marked[current]) return;
			
			marked[current] = true;
			Iterable<Integer> adj = g.adj(current);
			for (int adjc : adj)
				dfsMark(adjc, marked, g);
		}
		
		public static void dfsMark(int current, int from, long[] distTo, boolean[] marked, UGraph g, ArrayList<Integer> endPoints) {
			if (marked[current]) return;
			
			marked[current] = true;
			if (from != -1)
				distTo[current] = distTo[from] + 1;
			HashSet<Integer> adj = g.adj(current);
			int alreadyMarkedCtr = 0;
			for (int adjc : adj) {
				if (marked[adjc]) alreadyMarkedCtr++;
				dfsMark(adjc, current, distTo, marked, g, endPoints);
			}
			
			if (alreadyMarkedCtr == adj.size())
				endPoints.add(current);
		}
		
		public static void bfsOrder(int current, UGraph g) {
		}
		
		public static void dfsMark(int current, int[] colorIds, int color, UGraph g) {
			if (colorIds[current] != -1) return;
			
			colorIds[current] = color;
			Iterable<Integer> adj = g.adj(current);
			for (int adjc : adj)
				dfsMark(adjc, colorIds, color, g);
		}
		
		public static int[] connectedComponents(UGraph g) {
			int n = g.V();
			int[] componentId = new int[n];
			Arrays.fill(componentId, -1);
			int colorCtr = 0;
			for (int i = 0; i < n; i++) {
				if (componentId[i] != -1) continue;
				dfsMark(i, componentId, colorCtr, g);
				colorCtr++;
			}
			
			return componentId;
		}
		
		public static boolean hasCycle(UGraph ug) {
			int n = ug.V();
			boolean[] marked = new boolean[n];
			boolean[] hasCycleFirst = new boolean[1];
			for (int i = 0; i < n; i++) {
				if (marked[i]) continue;
				hcDfsMark(i, ug, marked, hasCycleFirst, -1);
			}
			return hasCycleFirst[0];
		}
		
		// Helper for hasCycle.
		private static void hcDfsMark(int current, UGraph ug, boolean[] marked, boolean[] hasCycleFirst, int parent) {
			if (marked[current]) return;
			if (hasCycleFirst[0]) return;
			
			marked[current] = true;
			HashSet<Integer> adjc = ug.adj(current);
			for (int adj : adjc) {
				if (marked[adj] && adj != parent && parent != -1) {
					hasCycleFirst[0] = true;
					return;
				}
				
				hcDfsMark(adj, ug, marked, hasCycleFirst, current);
			}
		}
	}
	
	static class Digraph {
		// Adjacency list.
		private HashSet<Integer>[] adj;
		private static final String NEWLINE = "\n";
		private int E;
		
		@SuppressWarnings("unchecked")
		public Digraph(int V) {
			adj = (HashSet<Integer>[]) new HashSet[V];
			E = 0;
			for (int i = 0; i < V; i++)
				adj[i] = new HashSet<Integer>();
		}
		
		public void addEdge(int from, int to) {
			if (adj[from].contains(to)) return;
			E++;
			adj[from].add(to);
		}
		
		public HashSet<Integer> adj(int from) {
			return adj[from];
		}
		
		public int V() {
			return adj.length;
		}
		
		public int E() {
			return E;
		}
		
		public Digraph reversed() {
			Digraph dg = new Digraph(V());
			for (int i = 0; i < V(); i++)
				for (int adjVert : adj(i)) dg.addEdge(adjVert, i);
			return dg;
		}
		
		public String toString() {
			StringBuilder s = new StringBuilder();
			s.append(V() + " vertices, " + E() + " edges " + NEWLINE);
			for (int v = 0; v < V(); v++) {
				s.append(v + ": ");
				for (int w : adj[v]) {
					s.append(w + " ");
				}
				s.append(NEWLINE);
			}
			return s.toString();
		}
		
		public static int[] KosarajuSharirSCC(Digraph dg) {
			int[] id = new int[dg.V()];
			
			Digraph reversed = dg.reversed();
			
			// Gotta perform topological sort on this one to get the stack.
			Stack<Integer> revStack = Digraph.topologicalSort(reversed);
			
			// Initializing id and idCtr.
			id = new int[dg.V()];
			int idCtr = -1;
			
			// Creating a 'marked' array.
			boolean[] marked = new boolean[dg.V()];
			
			while (!revStack.isEmpty()) {
				int vertex = revStack.pop();
				if (!marked[vertex]) 
					sccDFS(dg, vertex, marked, ++idCtr, id);
			}
			return id;
		}
		
		private static void sccDFS(Digraph dg, int source, boolean[] marked, int idCtr, int[] id) {
			marked[source] = true;
			id[source] = idCtr;
			for (Integer adjVertex : dg.adj(source))
				if (!marked[adjVertex]) sccDFS(dg, adjVertex, marked, idCtr, id);
		}
		
		public static Stack<Integer> topologicalSort(Digraph dg) {
			// dg has to be a directed acyclic graph.
			// We'll have to run dfs on the digraph and push the deepest nodes on stack first.
			// We'll need a Stack<Integer> and a int[] marked.
			Stack<Integer> topologicalStack = new Stack<Integer>();
			boolean[] marked = new boolean[dg.V()];
			
			// Calling dfs
			for (int i = 0; i < dg.V(); i++)
				if (!marked[i]) 
					runDfs(dg, topologicalStack, marked, i);
			
			return topologicalStack;
		}
		
		public static Stack<Integer> topologicalSort(Digraph dg, int src) {
			// dg has to be a directed acyclic graph.
			// We'll have to run dfs on the digraph and push the deepest nodes on stack first.
			// We'll need a Stack<Integer> and a int[] marked.
			Stack<Integer> topologicalStack = new Stack<Integer>();
			boolean[] marked = new boolean[dg.V()];
			
			// Calling dfs
			runDfs(dg, topologicalStack, marked, src);
			
			return topologicalStack;
		}
		
		static void runDfs(Digraph dg, Stack<Integer> topologicalStack, boolean[] marked, int source) {
			marked[source] = true;
			for (Integer adjVertex : dg.adj(source))
				if (!marked[adjVertex]) 
					runDfs(dg, topologicalStack, marked, adjVertex);
			topologicalStack.add(source);
		}
	}
	
	static class FastReader {
		private BufferedReader bfr;
		private StringTokenizer st;
		public FastReader() {
			bfr = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() {
			if (st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(bfr.readLine());
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

		char nextChar() {
			return next().toCharArray()[0];
		}

		String nextString() {
			return next();
		}

		int[] nextIntArray(int n) {
			int[] arr = new int[n];
			for (int i = 0; i < n; i++)
				arr[i] = nextInt();
			return arr;
		}

		double[] nextDoubleArray(int n) {
			double[] arr = new double[n];
			for (int i = 0; i < arr.length; i++)
				arr[i] = nextDouble();
			return arr;
		}

		long[] nextLongArray(int n) {
			long[] arr = new long[n];
			for (int i = 0; i < n; i++)
				arr[i] = nextLong();
			return arr;
		}
		
		int[][] nextIntGrid(int n, int m) {
			int[][] grid = new int[n][m];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++)
					grid[i][j] = fr.nextInt();
			}
			return grid;
		}
	}
	
	@SuppressWarnings("serial")
	static class CountMap<T> extends TreeMap<T, Integer>{
		
		CountMap() {
		}
		
		CountMap(Comparator<T> cmp) {
		}
		
		CountMap(T[] arr) {
			this.putCM(arr);
		}
		
		public Integer putCM(T key) {
			return super.put(key, super.getOrDefault(key, 0) + 1);
		}
		
		public Integer removeCM(T key) {
			int count = super.getOrDefault(key, -1);
			if (count == -1) return -1;
			if (count == 1)
				return super.remove(key);
			else
				return super.put(key, count - 1);
		}
		
		public Integer getCM(T key) {
			return super.getOrDefault(key, 0);
		}
	
		public void putCM(T[] arr) {
			for (T l : arr)
				this.putCM(l);
		}
	}
	
	static long dioGCD(long a, long b, long[] x0, long[] y0) {
	    if (b == 0) {
	        x0[0] = 1;
	        y0[0] = 0;
	        return a;
	    }
	    long[] x1 = new long[1], y1 = new long[1];
	    long d = dioGCD(b, a % b, x1, y1);
	    x0[0] = y1[0];
	    y0[0] = x1[0] - y1[0] * (a / b);
	    return d;
	}

	static boolean diophantine(long a, long b, long c, long[] x0, long[] y0, long[] g) {
	    g[0] = dioGCD(Math.abs(a), Math.abs(b), x0, y0);
	    if (c % g[0] > 0) {
	        return false;
	    }

	    x0[0] *= c / g[0];
	    y0[0] *= c / g[0];
	    if (a < 0) x0[0] = -x0[0];
	    if (b < 0) y0[0] = -y0[0];
	    return true;
	}
	
	static long[][] prod(long[][] mat1, long[][] mat2) {
		int n = mat1.length;
		long[][] prod = new long[n][n];
		
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				// determining prod[i][j]
				// it will be the dot product of mat1[i][] and mat2[][i] 
				for (int k = 0; k < n; k++)
					prod[i][j] += mat1[i][k] * mat2[k][j];
		return prod;
	}
	static long[][] matExpo(long[][] mat, long power) {
		int n = mat.length;
		long[][] ans = new long[n][n];
		
		if (power == 0)
			return null;
		
		if (power == 1)
			return mat;
		
		long[][] half = matExpo(mat, power / 2);
		ans = prod(half, half);
		
		if (power % 2 == 1) {
			ans = prod(ans, mat);
		}
		
		return ans;
	}
	
	static int KMPNumOcc(char[] text, char[] pat) {
		int n = text.length;
		int m = pat.length;
		
		char[] patPlusText = new char[n + m + 1];
		for (int i = 0; i < m; i++)
			patPlusText[i] = pat[i];
		patPlusText[m] = '^'; 		// Seperator
		for (int i = 0; i < n; i++)
			patPlusText[m + i] = text[i];
		
		int[] fullPi = piCalcKMP(patPlusText);
		int answer = 0;
		for (int i = 0; i < n + m + 1; i++)
			if (fullPi[i] == m)
				answer++;
		return answer;
	}
	static int[] piCalcKMP(char[] s) {
		int n = s.length;
		int[] pi = new int[n];
		for (int i = 1; i < n; i++) {
			int j = pi[i - 1];
			while (j > 0 && s[i] != s[j])
				j = pi[j - 1];
			if (s[i] == s[j])
				j++;
			pi[i] = j;
		}
		return pi;
	}
	static boolean[] prefMatchesSuff(char[] s) {
		int n = s.length;
		boolean[] res = new boolean[n + 1];
		
		int[] pi = prefix_function(s);
		
		res[0] = true;
        for (int p = n; p != 0; p = pi[p])
            res[p] = true;
		
		return res;
	}
	static int[] prefix_function(char[] s) {
	    int n = s.length;
	    int[] pi = new int[n];
	    for (int i = 1; i < n; i++) {
	        int j = pi[i-1];
	        while (j > 0 && s[i] != s[j])
	            j = pi[j-1];
	        if (s[i] == s[j])
	            j++;
	        pi[i] = j;
	    }
	    return pi;
	}
	
	static long hash(long key) {
        long h = Long.hashCode(key);
        h ^= (h >>> 20) ^ (h >>> 12) ^ (h >>> 7) ^ (h >>> 4);
        return h & (gigamod-1);
    }
	static void Yes() {out.println("Yes");}static void YES() {out.println("YES");}static void yes() {out.println("Yes");}static void No() {out.println("No");}static void NO() {out.println("NO");}static void no() {out.println("no");}
	
	static int mapTo1D(int row, int col, int n, int m) {
		// Maps elements in a 2D matrix serially to elements in 
		// a 1D array.
		return row * m + col;
	}
	static int[] mapTo2D(int idx, int n, int m) {
		// Inverse of what the one above does.
		int[] rnc = new int[2];
		rnc[0] = idx / m;
		rnc[1] = idx % m;
		return rnc;
	}
	static long mapTo1D(long row, long col, long n, long m) {
		// Maps elements in a 2D matrix serially to elements in 
		// a 1D array.
		return row * m + col;
	}
	
	static boolean[] primeGenerator(int upto) {
		// Sieve of Eratosthenes: 
		isPrime = new boolean[upto + 1];
		smallestFactorOf = new int[upto + 1];
		Arrays.fill(smallestFactorOf, 1);
		Arrays.fill(isPrime, true);
		isPrime[1] = isPrime[0] = false;

		for (long i = 2; i < upto + 1; i++)
			if (isPrime[(int) i]) {
				smallestFactorOf[(int) i] = (int) i;
				// Mark all the multiples greater than or equal
				// to the square of i to be false.
				for (long j = i; j * i < upto + 1; j++) {
					if (isPrime[(int) j * (int) i]) {
						isPrime[(int) j * (int) i] = false;
						smallestFactorOf[(int) j * (int) i] = (int) i;
					}
				}
			}
		return isPrime;
	}
	static HashMap<Integer, Integer> smolNumPrimeFactorization(int num) {
		if (smallestFactorOf == null)
			primeGenerator(num + 1);
		HashMap<Integer, Integer> fnps = new HashMap<>();
		while (num != 1) {
			fnps.put(smallestFactorOf[num], fnps.getOrDefault(smallestFactorOf[num], 0) + 1);
			num /= smallestFactorOf[num];
		}
		return fnps;
	}
	
	static HashMap<Long, Integer> primeFactorization(long num) {
		
		if (isPrime == null)
			smolNumPrimeFactorization((int) 1e5);
		
		// Returns map of factor and its power in the number.
		HashMap<Long, Integer> map = new HashMap<>();
		while (num % 2 == 0) {
			num /= 2;
			Integer pwrCnt = map.get(2L);
			map.put(2L, pwrCnt != null ? pwrCnt + 1 : 1);
		}
		
		for (long i = 3; i * i <= num; i += 2) {
			while (num % i == 0 && isPrime[(int) i]) {
				num /= i;
				Integer pwrCnt = map.get(i);
				map.put(i, pwrCnt != null ? pwrCnt + 1 : 1);
			}
		}
		// If the number is prime, we have to add it to the 
		// map.
		if (num != 1)
			map.put(num, 1);
		return map;
	}
	static HashSet<Long> divisors(long num) {
		HashSet<Long> divisors = new HashSet<Long>();
		divisors.add(1L);
		divisors.add(num);
		for (long i = 2; i * i <= num; i++) {
			if (num % i == 0) {
				divisors.add(num/i);
				divisors.add(i);
			}
		}
		return divisors;
	}
	static void coprimeGenerator(int m, int n, ArrayList<Point> coprimes, int limit, int numCoprimes) {
		if (m > limit) return;
		if (m <= limit && n <= limit)
			coprimes.add(new Point(m, n));
		if (coprimes.size() > numCoprimes) return;
		coprimeGenerator(2 * m - n, m, coprimes, limit, numCoprimes);
		coprimeGenerator(2 * m + n, m, coprimes, limit, numCoprimes);
		coprimeGenerator(m + 2 * n, n, coprimes, limit, numCoprimes);
	}
	
	static long nCr(long n, long r, long[] fac) { long p = mod; if (r == 0) return 1; return (fac[(int)n] * modInverse(fac[(int)r], p) % p * modInverse(fac[(int)n - (int)r], p) % p) % p; } 
	static long modInverse(long n, long p) { return power(n, p - 2, p); } 
	static long modDiv(long a, long b){return mod(a * power(b, mod - 2, mod), mod);}
	static long power(long x, long y, long p) { long res = 1; x = x % p; while (y > 0) { if ((y & 1)==1) res = (res * x) % p; y = y >> 1; x = (x * x) % p; } return res; } 
	static int logk(long n, long k) { return (int)(Math.log(n) / Math.log(k)); }
	static long gcd(long a, long b) { if (b == 0) { return a; } else { return gcd(b, a % b); } } static int gcd(int a, int b) { if (b == 0) { return a; } else { return gcd(b, a % b); } } static long gcd(long[] arr) { int n = arr.length; long gcd = arr[0]; for (int i = 1; i < n; i++) { gcd = gcd(gcd, arr[i]); } return gcd; } static int gcd(int[] arr) { int n = arr.length; int gcd = arr[0]; for (int i = 1; i < n; i++) { gcd = gcd(gcd, arr[i]); } return gcd; } static long lcm(long[] arr) { long lcm = arr[0]; int n = arr.length; for (int i = 1; i < n; i++) { lcm = (lcm * arr[i]) / gcd(lcm, arr[i]); } return lcm; } static long lcm(long a, long b) { return (a * b)/gcd(a, b); } static boolean less(int a, int b) { return a < b ? true : false; } static boolean isSorted(int[] a) { for (int i = 1; i < a.length; i++) { if (less(a[i], a[i - 1])) return false; } return true; } static boolean isSorted(long[] a) { for (int i = 1; i < a.length; i++) { if (a[i] < a[i - 1]) return false; } return true; } static void swap(int[] a, int i, int j) { int temp = a[i]; a[i] = a[j]; a[j] = temp; } static void swap(long[] a, int i, int j) { long temp = a[i]; a[i] = a[j]; a[j] = temp; } static void swap(double[] a, int i, int j) { double temp = a[i]; a[i] = a[j]; a[j] = temp; } static void swap(char[] a, int i, int j) { char temp = a[i]; a[i] = a[j]; a[j] = temp; }
	static void sort(int[] arr) { shuffleArray(arr, 0, arr.length - 1); Arrays.sort(arr); } static void sort(char[] arr) { shuffleArray(arr, 0, arr.length - 1); Arrays.sort(arr); } static void sort(long[] arr) { shuffleArray(arr, 0, arr.length - 1); Arrays.sort(arr); } static void sort(double[] arr) { shuffleArray(arr, 0, arr.length - 1); Arrays.sort(arr); } static void reverseSort(int[] arr) { shuffleArray(arr, 0, arr.length - 1); Arrays.sort(arr); int n = arr.length; for (int i = 0; i < n/2; i++) swap(arr, i, n - 1 - i); } static void reverseSort(char[] arr) { shuffleArray(arr, 0, arr.length - 1); Arrays.sort(arr); int n = arr.length; for (int i = 0; i < n/2; i++) swap(arr, i, n - 1 - i); } static void reverse(char[] arr) { int n = arr.length; for (int i = 0; i < n / 2; i++) swap(arr, i, n - 1 - i); } static void reverseSort(long[] arr) { shuffleArray(arr, 0, arr.length - 1); Arrays.sort(arr); int n = arr.length; for (int i = 0; i < n/2; i++) swap(arr, i, n - 1 - i); } static void reverseSort(double[] arr) { shuffleArray(arr, 0, arr.length - 1); Arrays.sort(arr); int n = arr.length; for (int i = 0; i < n/2; i++) swap(arr, i, n - 1 - i); } static void reverse(int[] arr) { int n = arr.length; for (int i = 0; i < n / 2; i++) swap(arr, i, n - 1 - i); }
	static void shuffleArray(long[] arr, int startPos, int endPos) { Random rnd = new Random(); for (int i = startPos; i < endPos; ++i) { long tmp = arr[i]; int randomPos = i + rnd.nextInt(endPos - i); arr[i] = arr[randomPos]; arr[randomPos] = tmp; } } static void shuffleArray(int[] arr, int startPos, int endPos) { Random rnd = new Random(); for (int i = startPos; i < endPos; ++i) { int tmp = arr[i]; int randomPos = i + rnd.nextInt(endPos - i); arr[i] = arr[randomPos]; arr[randomPos] = tmp; } }
	static void shuffleArray(double[] arr, int startPos, int endPos) { Random rnd = new Random(); for (int i = startPos; i < endPos; ++i) { double tmp = arr[i]; int randomPos = i + rnd.nextInt(endPos - i); arr[i] = arr[randomPos]; arr[randomPos] = tmp; } }
	static void shuffleArray(char[] arr, int startPos, int endPos) { Random rnd = new Random(); for (int i = startPos; i < endPos; ++i) { char tmp = arr[i]; int randomPos = i + rnd.nextInt(endPos - i); arr[i] = arr[randomPos]; arr[randomPos] = tmp; } }
	static boolean isPrime(long n) {if (n<=1)return false;if(n<=3)return true;if(n%2==0||n%3==0)return false;for(long i=5;i*i<=n;i=i+6)if(n%i==0||n%(i+2)==0)return false;return true;}
	static String toString(int[] dp){StringBuilder sb=new StringBuilder();for(int i=0;i<dp.length;i++)sb.append(dp[i]+" ");return sb.toString();}
	static String toString(boolean[] dp){StringBuilder sb=new StringBuilder();for(int i=0;i<dp.length;i++)sb.append(dp[i]+" ");return sb.toString();}
	static String toString(long[] dp){StringBuilder sb=new StringBuilder();for(int i=0;i<dp.length;i++)sb.append(dp[i]+" ");return sb.toString();}
	static String toString(char[] dp){StringBuilder sb=new StringBuilder();for(int i=0;i<dp.length;i++)sb.append(dp[i]+"");return sb.toString();}
	static String toString(int[][] dp){StringBuilder sb=new StringBuilder();for(int i=0;i<dp.length;i++){for(int j=0;j<dp[i].length;j++){sb.append(dp[i][j]+" ");}sb.append('\n');}return sb.toString();}
	static String toString(long[][] dp){StringBuilder sb=new StringBuilder();for(int i=0;i<dp.length;i++){for(int j=0;j<dp[i].length;j++) {sb.append(dp[i][j]+" ");}sb.append('\n');}return sb.toString();}
	static String toString(double[][] dp){StringBuilder sb=new StringBuilder();for(int i = 0;i<dp.length;i++){for(int j = 0;j<dp[i].length;j++){sb.append(dp[i][j]+" ");}sb.append('\n');}return sb.toString();}
	static String toString(char[][] dp){StringBuilder sb = new StringBuilder();for(int i = 0;i<dp.length;i++){for(int j = 0;j<dp[i].length;j++){sb.append(dp[i][j]+"");}sb.append('\n');}return sb.toString();}
	static long mod(long a, long m){return(a%m+1000000L*m)%m;}
}

/*
 * 
 * int[] arr = new int[] {1810, 1700, 1710, 2320, 2000, 1785, 1780
				, 2130, 2185, 1430, 1460, 1740, 1860, 1100, 1905, 1650};
		
		int n = arr.length;
		sort(arr);
		
		int bel1700 = 0, bet1700n1900 = 0, abv1900 = 0;
		for (int i = 0; i < n; i++)
			if (arr[i] < 1700)
				bel1700++;
			else if (1700 <= arr[i] && arr[i] < 1900)
				bet1700n1900++;
			else if (arr[i] >= 1900)
				abv1900++;
		
		
		out.println("COUNT: " + n);
		out.println("PERFS: " + toString(arr));
		out.println("MEDIAN: " + arr[n / 2]);
		out.println("AVERAGE: " + Arrays.stream(arr).average().getAsDouble());
		out.println("[0, 1700): " + bel1700 + "/" + n);
		out.println("[1700, 1900): " + bet1700n1900 + "/" + n);
		out.println("[1900, 2400): " + abv1900 + "/" + n);
 * 
 * */

// NOTES: 
// ASCII VALUE OF 'A': 65
// ASCII VALUE OF 'a': 97
// Range of long: 9 * 10^18
// ASCII VALUE OF '0': 48
// Primes upto 'n' can be given by (n / (logn)). 