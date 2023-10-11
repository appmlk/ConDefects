import java.util.*;
import java.io.*;
 
class Main{
    public static final int [] x8 = {0 , 1,1,1,0,-1,-1,-1};
    public static final int [] y8 = {-1,-1,0,1,1, 1, 0,-1};
    public static final int [] y4 = {0,1,0,-1};
    public static final int [] x4 = {1,0,-1,0};
    public static final int MOD = 1000000007;
    public static final int INF = Integer.MAX_VALUE;
    public static Map<Integer, Integer> fact = new HashMap<>();
    public static TreeSet<String> perm = new TreeSet<>();

    public static void main(String[] args) throws IOException {
        PrintWriter output = new PrintWriter(System.out);
        Scanner sc = new Scanner(System.in);
        BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        //st = new StringTokenizer(buff.readLine());
        String N = sc.next();
        int len = N.length();
        String [] s = new String[len];
        for(int i=0;i<len;i++) s[i] = N.substring(i,i+1);
        String [] tmp = new String[len];
        boolean [] used = new boolean[len];
        permutation(s,tmp,used,0,len);
        long max = (long)1e-18;
        for(String x : perm){
            for(int i=1;i<x.length()-1;i++){
                String s1 = x.substring(0,i);
                String s2 = x.substring(i,x.length());
                long v1 = Long.parseLong(s1);
                long v2 = Long.parseLong(s2);
                int len1 = Long.toString(v1).length();
                int len2 = Long.toString(v2).length();
                if(len1 == s1.length() && len2 == s2.length()) max = Math.max(max,v1*v2);
            }
        }
        output.print(max);
        output.flush();
    }
 
    //????????????????????????????????????????????????????????????????????????????????
 
    static void permutation(String[] elements, String[] tmp, boolean[] used, int index,int N) {
        if (index == N) {
            StringBuilder sb = new StringBuilder();
            for(String x : tmp) sb.append(x);
            perm.add(sb.toString());
            return;
        }
        for (int i = 0; i < N; i++) {
            if (used[i])
                continue;
            used[i] = true;
            tmp[index] = elements[i];
            permutation(elements, tmp, used, index + 1,N);
            used[i] = false;
        }
    }

    static int Lowerbound(Integer[] ary, int i) {
		return ~Arrays.binarySearch(ary, i, (x,y)->x.compareTo(y)>=0?1:-1);
	}
    static int Upperbound(Integer[] ary, int i) {
		return ~Arrays.binarySearch(ary, i, (x,y)->x.compareTo(y)>0?1:-1);
	}
    static long Lowerbound(Long[] ary, long i) {
		return ~Arrays.binarySearch(ary, i, (x,y)->x.compareTo(y)>=0?1:-1);
	}
    static long Upperbound(Long[] ary, long i) {
		return ~Arrays.binarySearch(ary, i, (x,y)->x.compareTo(y)>0?1:-1);
	}
    static int gcd(int a, int b) {
        return b == 0 ? a: gcd(b, a % b);
    }
    static int lcm(int a, int b) {
        return a / gcd(a, b) * b;
    }
    static long gcd(long a, long b) {
        return b == 0 ? a: gcd(b, a % b);
    }
    static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }
    static boolean isprime(int N) {
        if(N <= 1)
            return false;
        else if(N == 2)
            return true;
        for(int i = 2; i <= Math.sqrt(N); i++)
            if(N % i == 0)
                return false;
        return true;
    }
    static void factorization(int N){
    for(int i = 2; i <= Math.sqrt(N); i ++) {
        if(N % i == 0) {
            int n = 0;
            while(N % i == 0) {
                N /= i;
                n++;
            }
            fact.put(i, n);
        }
    }
    if(N > 1) fact.put(N, 1);
    }
    static class UnionFind {
        int[] parent;
        int[] rank;
        int[] size;
        int[] min;
        int[] max;
    
        public UnionFind(int n) {
            // 初期化コンストラクタ
            this.parent = new int[n];
            this.rank = new int[n];
            this.size = new int[n];
            this.min = new int[n];
            this.max = new int[n];
    
            // 最初はすべてが根
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
                min[i] = i;
                max[i] = i;
                size[i] = 1;
            }
        }
        public int getMin_node(int n){ 
            return min[find(n)];
        }
        public int getMax_node(int n){ 
            return max[find(n)];
        }
        public int size(int x){
            return size[find(x)];
        }
        /**
         * 要素の根を返す。
         * 経路圧縮付き。（1→3→2となっていて2をfindした際、1→3,2と木の深さを浅くする。）
         *
         * @param x
         * @return 要素xの根
         */
        public int find(int x) {
            if (x == parent[x]) {
                return x;
            } else {
                // 経路圧縮時はrank変更しない
                parent[x] = find(parent[x]);
                return parent[x];
            }
        }
        /**
         * ２つの要素が同じ集合に属するかどうかを返す。
         *
         * @param x
         * @param y
         * @return 同じ集合であればtrue
         */
        public boolean same(int x, int y) {
            return find(x) == find(y);
        }
        /**
         * 要素xが属する集合と要素yが属する集合を連結する。
         * 木の高さ（ランク）を気にして、低い方に高い方をつなげる。（高い方の根を全体の根とする。）
         *
         * @param x
         * @param y
         */
        public void unite(int x, int y) {
            int xRoot = find(x);
            int yRoot = find(y);
    
            if (xRoot == yRoot) {
                // 属する集合が同じな場合、何もしない
                return;
            }
            // rankを比較して共通の根を決定する。
            // ※find時の経路圧縮はrank考慮しない
            if (rank[xRoot] > rank[yRoot]) {
                // xRootのrankのほうが大きければ、共通の根をxRootにする
                parent[yRoot] = xRoot;
            } else if (rank[xRoot] < rank[yRoot]) {
                // yRootのrankのほうが大きければ、共通の根をyRootにする
                parent[xRoot] = yRoot;
            } else {
                // rankが同じであれば、どちらかを根として、rankを一つ上げる。
                parent[xRoot] = yRoot;
                rank[xRoot]++;
                size[yRoot] += size[xRoot];
            }
            min[yRoot] = Math.min(min[xRoot],min[yRoot]);
            max[yRoot] = Math.max(max[xRoot],max[yRoot]);
        }
    }
}