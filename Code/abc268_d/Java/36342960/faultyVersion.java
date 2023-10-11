import java.io.*;
import java.math.BigInteger;
import java.util.*;

 
 
public class Main {
	public static void main(String[] args) throws IOException {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		PrintWriter out = new PrintWriter(outputStream);
		InputReader in = new InputReader(inputStream);
		
//		for(int i=4;i<=4;i++) {
//			InputStream uinputStream = new FileInputStream("timeline.in");
			//String f = i+".in";
			//InputStream uinputStream = new FileInputStream(f);
//			InputReader in = new InputReader(uinputStream);
//			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("timeline.out")));
//		}
		Task t = new Task();
		t.solve(in, out);
		out.close();
	}	
	
	static class Task{
 
		public void solve(InputReader in, PrintWriter out) throws IOException {
			int n = in.nextInt();
			int m = in.nextInt();
			int l = 0;
			String[] arr = new String[n];
			for(int i=0;i<n;i++) {
				arr[i] = in.next();
				l+=arr[i].length();
			}
			HashSet<String> s = new HashSet<String>();
			for(int i=0;i<m;i++) s.add(in.next());
			
			int left = 16-l-n+1;
			int[] tmp = new int[n-1];
			ArrayList<int[]> underscore = new ArrayList<int[]>();
			dfs(left,0,n-1,tmp,underscore);
			ArrayList<int[]> arrange = new ArrayList<int[]>();
			dfs2(0,n,new int[n],new boolean[n],arrange);
			
			boolean can = false;
			for(int i=0;i<arrange.size();i++) {//0,2,1,3
				for(int p=0;p<underscore.size();p++) {//[1,0,0]
					StringBuilder sb = new StringBuilder();
					for(int j=0;j<n;j++) {
						sb.append(arr[arrange.get(i)[j]]);
						if(j<n-1) {
							sb.append("_");						
							for(int k=0;k<underscore.get(p)[j];k++) {
								sb.append("_");
							}
						}
					}
					if(!s.contains(sb.toString())) {
						out.println(sb.toString());
						can = true;
						break;
					}					
				}
				if(can) break;
			}
			if(!can) out.println(-1);
		}
		void dfs(int left, int cur, int m, int[] arr, ArrayList<int[]> ret){
			if(cur==m) {
				ret.add(arr.clone());
				return;			
			}
			for(int i=0;i<=left;i++) {
				arr[cur] = i;
				dfs(left-i,cur+1,m,arr,ret);
			}
		}
		void dfs2(int cur, int m, int[] arr, boolean[] vis, ArrayList<int[]> ret) {
			if(cur==m) {
				ret.add(arr.clone());
				return;
			}
			for(int i=0;i<m;i++) {
				if(!vis[i]) {
					vis[i] = true;
					arr[cur] = i;
					dfs2(cur+1,m,arr,vis,ret);
					vis[i] = false;
				}
			}
		}
		
	    
	    public class TreeNode {
		    int val;
		    TreeNode left;
		    TreeNode right;
		    TreeNode() {}
		    TreeNode(int val) { this.val = val; }
		    TreeNode(int val, TreeNode left, TreeNode right) {
			    this.val = val;
			    this.left = left;
			    this.right = right;
		    }
	    }	    
	    
		long work(long a, long b, long c, long d, int m, int f) {
			long col = (d-b)/2+1;
			long row = (c-a)/2+1;
			
			long diff_row = 2*m%f*col%f;
			
			long a1_col = ((a-1)*m%f+b)%f;
			long an_col = ((a-1)*m%f+d)%f;
			//long sum_col = (a1_col+an_col)*col/2%f;
			long sum_col = ((a-1)*m%f + (b+d)/2)%f*col%f;
			
			long a1_row = sum_col;
			long an_row = (row-1)*diff_row%f+a1_row;
			an_row%=f;
			//long sum_row = (a1_row+an_row)*row/2%f;
			long sum_row = (a1_row+(row-1)*m%f*col%f)%f*row%f;
			return sum_row;
		}

		
		private static long qpow(long a, long p, long MOD) {
			long m = Long.highestOneBit(p);
			long ans = 1;
			for (; m > 0; m >>>= 1) {
				ans = ans * ans %MOD;
				if ((p & m) > 0) ans = ans * a %MOD;
			}
			return ans;
		}			
		

		public int cnr(int n, int r, int mod) {
			if(r==0||n==r) return 1;
			long x = cnr(n-1,r,mod);
			x%=mod;
			x+=cnr(n-1,r-1,mod);
			x%=mod;
			return (int) x;
		}
		
		public long fmi(long base, long exp, long fa) {
			if(exp==1) return base;
			else if(exp%2==0) {
				long tmp = fmi(base, exp/2, fa);
				return tmp*tmp%fa;
			}else {
				long tmp = fmi(base,exp-1,fa);
				return base*tmp%fa;
			}
		}
	    
	    public class edge implements Comparable<edge>{
	    	int f, t, len;
	    	public edge(int a, int b, int c) {
	    		f = a; t = b; len = c;
	    	}

			@Override
			public int compareTo(Main.Task.edge o) {
				// TODO Auto-generated method stub
				return this.len - o.len;
			}
	    }
		
 
		 public Set<Integer> get_factor(int number) {
			 int n = number;
			 Set<Integer> primeFactors = new HashSet<Integer>();
			 for (int i = 2; i <= n/i; i++) { while (n % i == 0) { primeFactors.add(i); n /= i; } } if(n>1)
			 primeFactors.add(n);
			 return primeFactors;
		}
 
		private static int combx(int n, int k) {
			int comb[][] = new int[n+1][n+1];
			for(int i = 0; i <=n; i ++){
				comb[i][0] = comb[i][i] = 1;
				for(int j = 1; j < i; j++){
				comb[i][j] = comb[i-1][j] + comb[i-1][j-1];
				comb[i][j] %= 1000000007;
				}
			}
			return comb[n][k];
		}
 
 
		
		class trie_node{
			boolean end;
			int val;
			int lvl;
			trie_node zero;
			trie_node one;
			public trie_node() {
				zero = null;
				one = null;		
				end = false;
				val = -1;
				lvl = -1;
			}
		}
		class trie{
			trie_node root = new trie_node();			
			public void build(int x, int sz) {
				trie_node cur = root;				
				for(int i=sz;i>=0;i--) {
					int v = (x&(1<<i))==0?0:1;
					if(v==0&&cur.zero==null) {
						cur.zero = new trie_node();
					}
					if(v==1&&cur.one==null) {
						cur.one = new trie_node();
					}
					cur.lvl = i;
					if(i==0) {
						cur.end = true;
						cur.val = x;						
					}else {
 
						if(v==0)
							cur = cur.zero;
						else 
							cur = cur.one;
						cur.val = v;
						cur.lvl = i;						
					}
				}
			}
			int search(int num, int limit, trie_node r, int lvl) {
				if(r==null) return -1;
				if(r.end) return r.val;
				int f = -1;
				int num_val = (num&1<<lvl)==0?0:1;
				int limit_val = (limit&1<<lvl)==0?0:1;
				
				if(limit_val==1) {
					if(num_val==0) {
						int t = search(num,limit,r.one,lvl-1);
						if(t>f) return t;
						t = search(num,limit,r.zero,lvl-1);
						if(t>f) return t;						
					}else {
						int t = search(num,limit,r.zero,lvl-1);
						if(t>f) return t;
						t = search(num,limit,r.one,lvl-1);
						if(t>f) return t;						
					}					
				}else {
					int t = search(num,limit,r.zero,lvl-1);
					if(t>f) return t;
				}
				return f;
			}
		}
	    public int[] maximizeXor(int[] nums, int[][] queries) {
	    	int m = queries.length;
	        int ret[] = new int[m];
	        trie t = new trie();
	        int sz = 5;
	        for(int i:nums) t.build(i,sz);
	        int p = 0;	        
	        for(int x[]:queries) {
	        	if(p==1) {
	        		Dumper.print("here");
	        	}
	        	ret[p++] = t.search(x[0], x[1], t.root, sz);
	        }
	        return ret;
	    }		
 
		/*
		 * class edge implements Comparable<edge>{ int id,f,t; int len;int short_len;
		 * public edge(int a, int b, int c, int d, int e) { f=a;t=b;len=c;id=d;short_len
		 * = e; }
		 * 
		 * @Override public int compareTo(edge t) { if(this.len-t.len>0) return 1; else
		 * if(this.len-t.len<0) return -1; return 0; } }
		 */
		
		static class lca_naive{
			int n;
			ArrayList<edge>[] g;
			int lvl[];
			int pare[];
			int dist[];
			public lca_naive(int t, ArrayList<edge>[] x) {
				n=t;
				g=x; 
				lvl = new int[n];
				pare = new int[n];
				dist = new int[n];
			}
			void pre_process() {
				dfs(0,-1,g,lvl,pare,dist);
			}
			void dfs(int cur, int pre, ArrayList<edge>[] g, int lvl[], int pare[], int dist[]) {
				for(edge nxt_edge:g[cur]) {
					if(nxt_edge.t!=pre) {
						lvl[nxt_edge.t] = lvl[cur]+1;
						dist[nxt_edge.t] = dist[cur]+nxt_edge.len;
						pare[nxt_edge.t] = cur;
						dfs(nxt_edge.t,cur,g,lvl,pare,dist);
					}
				}				
			}
			public int work(int p, int q) {
				int a = p;
				int b = q;
				while(lvl[p]<lvl[q]) q = pare[q];
				while(lvl[p]>lvl[q]) p = pare[p];			
				while(p!=q){p = pare[p]; q = pare[q];}
				int c = p;
				return dist[a]+dist[b]-dist[c]*2;
			}
		}
		static class lca_binary_lifting{
			int n;
			ArrayList<edge>[] g;
			int lvl[];
			int pare[];
			int dist[];
			int table[][];
			public lca_binary_lifting(int a, ArrayList<edge>[] t) {
				n = a;
				g = t;
				lvl = new int[n];
				pare = new int[n];
				dist = new int[n];				
				table = new int[20][n];				
			}
			void pre_process() {
				dfs(0,-1,g,lvl,pare,dist);
				for(int i=0;i<20;i++) {
					for(int j=0;j<n;j++) {
						if(i==0) table[0][j] = pare[j];
						else table[i][j] = table[i-1][table[i-1][j]];
					}
				}				
			}
			void dfs(int cur, int pre, ArrayList<edge>[] g, int lvl[], int pare[], int dist[]) {
				for(edge nxt_edge:g[cur]) {
					if(nxt_edge.t!=pre) {
						lvl[nxt_edge.t] = lvl[cur]+1;
						dist[nxt_edge.t] = dist[cur]+nxt_edge.len;
						pare[nxt_edge.t] = cur;
						dfs(nxt_edge.t,cur,g,lvl,pare,dist);
					}
				}				
			}			
			public int work(int p, int q) {
				int a = p;
				int b = q;
				if(lvl[p]>lvl[q]) {
					int tmp = p;
					p=q;
					q=tmp;
				}				
				for(int i=19;i>=0;i--) {
					if(lvl[table[i][q]]>=lvl[p]) q=table[i][q];
				}				
				if(p==q) return p;// return dist[a]+dist[b]-dist[p]*2;
				for(int i=19;i>=0;i--) {
					if(table[i][p]!=table[i][q]) {
						p = table[i][p];
						q = table[i][q];
					}
				}
				return table[0][p];
				//return dist[a]+dist[b]-dist[table[0][p]]*2;
			}
		}
		static class lca_sqrt_root{
			int n;
			ArrayList<edge>[] g;
			int lvl[];
			int pare[];
			int dist[];
			int jump[];
			int sz;
			public lca_sqrt_root(int a, ArrayList<edge>[] b) {
				n=a;
				g=b;
				lvl = new int[n];
				pare = new int[n];
				dist = new int[n];
				jump = new int[n];
				sz = (int) Math.sqrt(n);
			}
			void pre_process() {
				dfs(0,-1,g,lvl,pare,dist,jump);
			}
			void dfs(int cur, int pre, ArrayList<edge>[] g, int lvl[], int pare[], int dist[], int[] jump) {
				for(edge nxt_edge:g[cur]) {
					if(nxt_edge.t!=pre) {
						lvl[nxt_edge.t] = lvl[cur]+1;
						dist[nxt_edge.t] = dist[cur]+nxt_edge.len;
						pare[nxt_edge.t] = cur;
						if(lvl[nxt_edge.t]%sz==0) {
							jump[nxt_edge.t] = cur;
						}else {
							jump[nxt_edge.t] = jump[cur];
						}
						dfs(nxt_edge.t,cur,g,lvl,pare,dist,jump);
					}
				}				
			}
			int work(int p, int q) {
				int a = p; int b = q;
				if(lvl[p]>lvl[q]) {
					int tmp = p;
					p=q;
					q=tmp;
				}
				while(jump[p]!=jump[q]) {
					if(lvl[p]>lvl[q]) p = jump[p];
					else q = jump[q];
				}
				while(p!=q) {
					if(lvl[p]>lvl[q]) p = pare[p];
					else q = pare[q];
				}
				return dist[a]+dist[b]-dist[p]*2;
			}
		}
		
//		class edge implements Comparable<edge>{
//			int f,t,len;
//			public edge(int a, int b, int c) {
//				f=a;t=b;len=c;
//			}
//			@Override
//			public int compareTo(edge t) {				
//				return t.len-this.len;
//			}
//		}
	
		class pair implements Comparable<pair>{
			int idx,lvl;
			public pair(int a, int b) {
				idx = a;
				lvl = b;
			}
			@Override
			public int compareTo(pair t) {
				return t.lvl-this.lvl;
			}
		}		
		
		static class lca_RMQ{
			int n;
			ArrayList<edge>[] g;
			int lvl[];
			int dist[];
			int tour[];
			int tour_rank[];
			int first_occ[];
			int c;
			sgt s;
			public lca_RMQ(int a, ArrayList<edge>[] b) {
				n=a;
				g=b;
				c=0;
				lvl = new int[n];
				dist = new int[n];
				tour = new int[2*n];
				tour_rank = new int[2*n];
				first_occ = new int[n];
				Arrays.fill(first_occ, -1);
			}
			void pre_process() {
				tour[c++] = 0;
				dfs(0,-1);
				for(int i=0;i<2*n;i++) {
					tour_rank[i] = lvl[tour[i]];
					if(first_occ[tour[i]]==-1) first_occ[tour[i]] = i;
				}
				s = new sgt(0,2*n,tour_rank);
			}
			void dfs(int cur, int pre) {
				for(edge nxt_edge:g[cur]) {
					if(nxt_edge.t!=pre) {
						lvl[nxt_edge.t] = lvl[cur]+1;
						dist[nxt_edge.t] = dist[cur]+nxt_edge.len;
						tour[c++] = nxt_edge.t;
						dfs(nxt_edge.t,cur);
						tour[c++] = cur;
					}
				}				
			}			
			int work(int p,int q) {			
				int a = Math.max(first_occ[p], first_occ[q]);
				int b = Math.min(first_occ[p], first_occ[q]);
				int idx = s.query_min_idx(b, a+1);
				//Dumper.print(a+" "+b+" "+idx);
				int c = tour[idx];
				return dist[p]+dist[q]-dist[c]*2;
			}
		}		
 
		  
	    static class sgt{
	    	sgt lt;
	    	sgt rt;
	    	int l,r;
	    	int sum, max, min, lazy;
	    	int min_idx;
	    	public sgt(int L, int R, int arr[]) {
	    		l=L;r=R;
	    		if(l==r-1) {
	    			sum = max = min = arr[l];
	    			lazy = 0;
	    			min_idx = l;
	    			return;
	    		}
	    		lt = new sgt(l, l+r>>1, arr);
	    		rt = new sgt(l+r>>1, r, arr);
	    		pop_up();
	    	}
	    	void pop_up() {
	    		this.sum = lt.sum + rt.sum;
	    		this.max = Math.max(lt.max, rt.max);
	    		this.min = Math.min(lt.min, rt.min);
	    		if(lt.min<rt.min)
	    			this.min_idx = lt.min_idx;
	    		else if(lt.min>rt.min) this.min_idx = rt.min_idx;
	    		else this.min = Math.min(lt.min_idx, rt.min_idx);
	    	}
	    	void push_down() {
	    		if(this.lazy!=0) {
	    			lt.sum+=lazy;
	    			rt.sum+=lazy;
	    			lt.max+=lazy;
	    			lt.min+=lazy;
	    			rt.max+=lazy;
	    			rt.min+=lazy;
	    			lt.lazy+=this.lazy;
	    			rt.lazy+=this.lazy;
	    			this.lazy = 0;
	    		}
	    	}
	    	void change(int L, int R, int v) {
	    		if(R<=l||r<=L) return;
	    		if(L<=l&&r<=R) {
	    			this.max+=v;
	    			this.min+=v;
	    			this.sum+=v*(r-l);
	    			this.lazy+=v;
	    			return;
	    		}
	    		push_down();
	    		lt.change(L, R, v);
	    		rt.change(L, R, v);
	    		pop_up();
	    	}
	    	int query_max(int L, int R) {
	    		if(L<=l&&r<=R) return this.max;
	    		if(r<=L||R<=l) return Integer.MIN_VALUE;
	    		push_down();
	    		return Math.max(lt.query_max(L, R), rt.query_max(L, R));	    		
	    	}
	    	int query_min(int L, int R) {
	    		if(L<=l&&r<=R) return this.min;
	    		if(r<=L||R<=l) return Integer.MAX_VALUE;
	    		push_down();
	    		return Math.min(lt.query_min(L, R), rt.query_min(L, R));	
	    	}
	    	int query_sum(int L, int R) {
	    		if(L<=l&&r<=R) return this.sum;
	    		if(r<=L||R<=l) return 0;
	    		push_down();
	    		return lt.query_sum(L, R) + rt.query_sum(L, R);
	    	}
	    	int query_min_idx(int L, int R) {
	    		if(L<=l&&r<=R) return this.min_idx;
	    		if(r<=L||R<=l) return Integer.MAX_VALUE;
	    		int a = lt.query_min_idx(L, R);
	    		int b = rt.query_min_idx(L, R);
	    		int aa = lt.query_min(L, R);
	    		int bb = rt.query_min(L, R);
	    		if(aa<bb) return a;
	    		else if(aa>bb) return b;	    		
	    		return Math.min(a,b);
	    	}
	    }	   
			    	
		List<List<Integer>> convert(int arr[][]){
			int n = arr.length;
			List<List<Integer>> ret = new ArrayList<>();
			for(int i=0;i<n;i++) {
				ArrayList<Integer> tmp = new ArrayList<Integer>();
				for(int j=0;j<arr[i].length;j++) tmp.add(arr[i][j]);
				ret.add(tmp);
			}
			return ret;
		}	
	
		public int GCD(int a, int b) {
		   if (b==0) return a;
		   return GCD(b,a%b);
		}
		public long GCD(long a, long b) {
			   if (b==0) return a;
			   return GCD(b,a%b);
		}		
	}
	
	
    static class ArrayUtils {
        static final long seed = System.nanoTime();
        static final Random rand = new Random(seed);
 
        public static void sort(int[] a) {
            shuffle(a);
            Arrays.sort(a);
        }
 
        public static void shuffle(int[] a) {
            for (int i = 0; i < a.length; i++) {
                int j = rand.nextInt(i + 1);
                int t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }
        
        public static void sort(long[] a) {
            shuffle(a);
            Arrays.sort(a);
        }        
        
        public static void shuffle(long[] a) {
            for (int i = 0; i < a.length; i++) {
                int j = rand.nextInt(i + 1);
                long t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }
    }	
	static class BIT{
		int arr[];
		int n;
		public BIT(int a) {
			n=a;
			arr = new int[n];
		}
		int sum(int p) {
			int s=0;
			while(p>0) {
				s+=arr[p];
				p-=p&(-p);
			}
			return s;
		}
		void add(int p, int v) {
			while(p<n) {
				arr[p]+=v;
				p+=p&(-p);
			}
		}
	}
	static class DSU{
		int[] arr;
		int[] sz;
		public DSU(int n) {
			arr = new int[n];
			sz = new int[n];
			for(int i=0;i<n;i++) arr[i] = i;
			Arrays.fill(sz, 1);
		}
		public int find(int a) {
			if(arr[a]!=a) arr[a] = find(arr[a]);
			return arr[a];
		}
		public void union(int a, int b) {
			int x = find(a);
			int y = find(b);
			if(x==y) return;
			arr[y] = x;
			sz[x] += sz[y];
		}
		public int size(int x) {
			return sz[find(x)];
		}
	}	
	static class MinHeap<Key> implements Iterable<Key> {
		private int maxN;
		private int n;
		private int[] pq;
		private int[] qp;
		private Key[] keys;
		private Comparator<Key> comparator;
		
		public MinHeap(int capacity){
			if (capacity < 0) throw new IllegalArgumentException();
			this.maxN = capacity;
			n=0;
			pq = new int[maxN+1];
			qp = new int[maxN+1];
			keys = (Key[]) new Object[capacity+1];
			Arrays.fill(qp, -1);
		}
		
		public MinHeap(int capacity, Comparator<Key> c){
			if (capacity < 0) throw new IllegalArgumentException();
			this.maxN = capacity;
			n=0;
			pq = new int[maxN+1];
			qp = new int[maxN+1];
			keys = (Key[]) new Object[capacity+1];
			Arrays.fill(qp, -1);
			comparator = c;
		}			
		public boolean isEmpty()	{ return n==0; 	}
		public int size()			{ return n;		}
		public boolean contains(int i) {
	        if (i < 0 || i >= maxN) throw new IllegalArgumentException();
	        return qp[i] != -1;
		}	
		public int peekIdx() {
	        if (n == 0) throw new NoSuchElementException("Priority queue underflow");
	        return pq[1];		
		}	
		public Key peek(){
			if(isEmpty()) throw new NoSuchElementException("Priority queue underflow");
			return keys[pq[1]];
		}
		public int poll(){
			if(isEmpty()) throw new NoSuchElementException("Priority queue underflow");
			int min = pq[1];
			exch(1,n--);
			down(1);
			assert min==pq[n+1];
			qp[min] = -1;
			keys[min] = null;		
			pq[n+1] = -1;
			return min;
		}
	    public void update(int i, Key key) {
	        if (i < 0 || i >= maxN) throw new IllegalArgumentException();
	        if (!contains(i)) {
	        	this.add(i, key);
	        }else {
	        	keys[i] = key;
	        	up(qp[i]);
	        	down(qp[i]);
	        }
	    }	
		private void add(int i, Key x){
	        if (i < 0 || i >= maxN) throw new IllegalArgumentException();
	        if (contains(i)) throw new IllegalArgumentException("index is already in the priority queue");
	        n++;
	        qp[i] = n;
	        pq[n] = i;
	        keys[i] = x;
	        up(n);
		}
		private void up(int k){
			while(k>1&&less(k,k/2)){
				exch(k,k/2);
				k/=2;
			}
		}
		private void down(int k){
			while(2*k<=n){
				int j=2*k;
				if(j<n&&less(j+1,j)) j++;
				if(less(k,j)) break;
				exch(k,j);
				k=j;
			}
		}
		
		public boolean less(int i, int j){
	        if (comparator == null) {
	            return ((Comparable<Key>) keys[pq[i]]).compareTo(keys[pq[j]]) < 0;
	        }
	        else {
	            return comparator.compare(keys[pq[i]], keys[pq[j]]) < 0;
	        }
		}
 
		public void exch(int i, int j){
	        int swap = pq[i];
	        pq[i] = pq[j];
	        pq[j] = swap;
	        qp[pq[i]] = i;
	        qp[pq[j]] = j;
		}
 
		@Override
		public Iterator<Key> iterator() {
			// TODO Auto-generated method stub
			return null;
		}
	}  	
 
    private static class InputReader
    {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int zcurChar;
        private int znumChars;
        private SpaceCharFilter filter;
 
        public InputReader(InputStream stream)
        {
            this.stream = stream;
        }
 
        public int read()
        {
            if (znumChars == -1)
                throw new InputMismatchException();
            if (zcurChar >= znumChars)
            {
            	zcurChar = 0;
                try
                {
                    znumChars = stream.read(buf);
                } catch (IOException e)
                {
                    throw new InputMismatchException();
                }
                if (znumChars <= 0)
                    return -1;
            }
            return buf[zcurChar++];
        }
 
        public int nextInt()
        {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-')
            {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do
            {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }
 
        public String nextString()
        {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do
            {
                res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }
 
        public double nextDouble()
        {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-')
            {
                sgn = -1;
                c = read();
            }
            double res = 0;
            while (!isSpaceChar(c) && c != '.')
            {
                if (c == 'e' || c == 'E')
                    return res * Math.pow(10, nextInt());
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            }
            if (c == '.')
            {
                c = read();
                double m = 1;
                while (!isSpaceChar(c))
                {
                    if (c == 'e' || c == 'E')
                        return res * Math.pow(10, nextInt());
                    if (c < '0' || c > '9')
                        throw new InputMismatchException();
                    m /= 10;
                    res += (c - '0') * m;
                    c = read();
                }
            }
            return res * sgn;
        }
 
        public long nextLong()
        {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-')
            {
                sgn = -1;
                c = read();
            }
            long res = 0;
            do
            {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }
 
        public boolean isSpaceChar(int c)
        {
            if (filter != null)
                return filter.isSpaceChar(c);
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }
 
        public String next()
        {
            return nextString();
        }
 
        public interface SpaceCharFilter
        {
            public boolean isSpaceChar(int ch);
        }
        public int[] readIntArray(int n) {
            int[] ret = new int[n];
            for (int i = 0; i < n; i++) {
                ret[i] = nextInt();
            }
            return ret;
        }        
    }    
 
	static class Dumper {
		static void print_int_arr(int[] arr) {
			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
			System.out.println("---------------------");
		}
 
		static void print_char_arr(char[] arr) {
			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
			System.out.println("---------------------");
		}
 
		static void print_double_arr(double[] arr) {
			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
			System.out.println("---------------------");
		}
 
		static void print_2d_arr(int[][] arr, int x, int y) {
			for (int i = 0; i < x; i++) {
				for (int j = 0; j < y; j++) {
					System.out.print(arr[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println();
			System.out.println("---------------------");
		}
 
		static void print_2d_arr(boolean[][] arr, int x, int y) {
			for (int i = 0; i < x; i++) {
				for (int j = 0; j < y; j++) {
					System.out.print(arr[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println();
			System.out.println("---------------------");
		}
 
		static void print(Object o) {
			System.out.println(o.toString());
		}
 
		static void getc() {
			System.out.println("here");
		}
	}
}