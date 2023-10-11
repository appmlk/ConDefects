// OM NAMAH SHIVAY
// Stay strong. Be brave. Always belief.



import com.sun.source.tree.Tree;

import javax.swing.*;
import java.math.BigInteger;
import java.rmi.server.RMIServerSocketFactory;
import java.util.*;
import java.io.*;


public class Main {
    static boolean prime[];

    static class Pair implements Comparable<Pair> {
        int x;
        int y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public int compareTo(Pair o){
            if(this.y!=o.y) return this.y-o.y;
            else return o.x-this.x;
        }
    }

    static long power(long x, long y, long p) {
        if (y == 0) return 1;
        if (x == 0) return 0;
        long res = 1l;
        x = x % p;
        while (y > 0) {
            if (y % 2 == 1) res = (res * x) % p;
            y = y >> 1;
            x = (x * x) % p;
        }
        return res;
    }

    static void sort(long[] a) {
        ArrayList<Long> l = new ArrayList<>();
        for (long i : a) l.add(i);
        Collections.sort(l);
        for (int i = 0; i < a.length; i++) a[i] = l.get(i);
    }

    static void sort(int[] a) {
        ArrayList<Integer> l = new ArrayList<>();
        for (int i : a) l.add(i);
        Collections.sort(l);
        for (int i = 0; i < a.length; i++) a[i] = l.get(i);
    }

    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        String next() {
            while (!st.hasMoreTokens()) try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        int[] readArray(int n) {
            int a[] = new int[n];
            for (int i = 0; i < n; i++) a[i] = nextInt();
            return a;
        }

        long[] readlongArray(int n) {
            long[] a = new long[n];
            for (int i = 0; i < n; i++) a[i] = nextLong();
            return a;
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }

    static void sieveOfEratosthenes(int n) {
        prime = new boolean[n + 1];
        for (int i = 0; i <= n; i++) prime[i] = true;
        for (int p = 2; p * p <= n; p++) {
            if (prime[p] == true) {
                for (int i = p * p; i <= n; i += p) prime[i] = false;
            }
        }
    }

    public static int log2(long x) {
        int v = (int) (Math.log(x) / Math.log(2));
        return v;
    }

    static long binomialCoeff(long n, long r) {
        if (r > n) return 0l;
        long inv[] = new long[(int) r + 1];
        inv[0] = 1;
        if (r + 1 >= 2) inv[1] = 1;
        for (int i = 2; i <= r; i++) {
            inv[i] = mod1 - (mod1 / i) * inv[(int) (mod1 % i)] % mod1;
        }
        long ans = 1l;
        for (int i = 2; i <= r; i++) {
            ans = (int) (((ans % mod1) * (inv[i] % mod1)) % mod1);
        }
        for (int i = (int) n; i >= (n - r + 1); i--) {
            ans = (int) (((ans % mod1) * (i % mod1)) % mod1);
        }
        return ans;
    }

    public static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    static long[] facts = new long[3_000_00];

    static BigInteger bi(String str) {
        return new BigInteger(str);
    }

    static FastScanner fs = null;
    static long mod1 =  1000000007;
    static int pre[];
    static PrintWriter out;
    static long h[][];
    static long w[][];
    static ArrayList<Long> a1;

    static int fuck_edge = -1;

   static int dis[];
   static int low[];
   static int timer;
   //static boolean vis[];

    static Pair a[];
    static boolean vis[];
    static long dp[][];

    static boolean bip = true;
    static int col[];
    static long mod = 998244353;

    static ArrayList<int[]> al[];
    public static void main(String[] args) {
        fs = new FastScanner();
        out = new PrintWriter(System.out);

        int t = 1;
        outer:
        while (t-- > 0) {
            long ans = -1;
            int n = fs.nextInt();
            int m = fs.nextInt();
            long a[] = fs.readlongArray(n);

            long l = 0;
            long r = (long) 1e18;
            while (l <= r) {
                long mid = (l + r) >> 1;
                if (check(mid, a, m)) {
                    ans = mid;
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            out.println(ans);
        }
        out.close();
    }


public static boolean check(long len, long a[],int line){

       long lin = 1l;
       long curr = len;
       long cur=0;
       for(int i=0;i<a.length;i++){
           if(cur>0) curr--;
           if(curr<a[i]) {
               lin++;
               curr = len;
               cur=0;
           }
           if(lin>line || curr<a[i]) return false;
           curr -= a[i];
           cur++;
       }
       return lin<=line;
}

 static long ans;


  static class SegmentTree{
        int tree[];
        int ar[];
        SegmentTree(int n,int arr[]){
            tree = new int[4*n];
            this.ar = arr;
            build(1,arr,0,arr.length-1);
        }

        public void build(int node, int arr[], int l, int r){
            if(l==r) {
                tree[node] = arr[l];
                ar[l]=arr[l];
            }

            int mid = (l+r)/2;
            build(node*2,arr,l,mid);
            build(node*2 +1, arr,mid+1,r);
            tree[node] = Math.max(tree[2*node],tree[2*node + 1]);
        }

        public void update(int node, int pos, int val, int l , int r){
            if(l==r) {
                tree[node] = val;
                ar[l]=node;
            }
            if(l>r) return;
            int mid = (l+r)/2;
            if(mid<pos){
                update(2*node+1,pos,val,mid+1,r);
            }else{
                update(2*node,pos,val,l,mid);
            }
            tree[node] = Math.max(tree[node*2],tree[2*node + 1]);
        }

        public int query(int node, int queryLeft, int queryRight, int left, int right){
            if (queryLeft <= left && right <= queryRight)
                return tree[node];
            int mid = left + (right - left) / 2;
            int minValue = Integer.MIN_VALUE;
            if (queryLeft <= mid)
                minValue = Math.max(minValue, query(2 * node + 1, left, mid, queryLeft, queryRight));
            if (queryRight > mid)
                minValue = Math.max(minValue, query(2 * node + 2, mid + 1, right, queryLeft, queryRight));
            return minValue;
        }
  }


    static long expo(long a, long b, long mod) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) > 0) res = (res * a) % mod;
            a = (a * a) % mod;
            b = b >> 1;
        }
        return res;
    }

    static long mminvprime(long a, long b) {
        return expo(a, b - 2, b);
    }

    static long mod_add(long a, long b, long m) {
        a = a % m;
        b = b % m;
        return (((a + b) % m) + m) % m;
    }

    static long mod_ml(long a, long b, long m) {
        a = a % m;
        b = b % m;
        return (((a * b) % m) + m) % m;
    }

    static long mod_sb(long a, long b, long m) {
        a = a % m;
        b = b % m;
        return (((a - b) % m) + m) % m;
    }

    static long mod_dv(long a, long b, long m) {
        a = a % m;
        b = b % m;
        return (mod_ml(a, mminvprime(b, m), m) + m) % m;
    }


}
class DisjointSetUnion{
     int[] parent;
    private int N;

    public DisjointSetUnion(int n){
        this.N = n;
        this.parent = new int[this.N];
        for(int i = 0; i < this.N; i++){
            this.parent[i] = i;
        }
    }

    public boolean areConnected(int u, int v){
        return find(u) == find(v);
    }

    public void union(int u, int v){
        if(u != v){
            int a = find(u);
            int b = find(v);
            parent[a] = b;
        }
    }

    public int find(int u){
        int x = u;
        while(x != this.parent[x]){
            x = this.parent[x];
        }

        this.parent[u] = x;
        return x;
    }
}
 class Fenwick{
    long bit[];
    int n;
    Fenwick(int n){
        bit = new long[n+1];
        this.n=n;
    }
    public  void update(int x, int v){
        int i=x;
        while(i<=this.n){
            bit[i] += v;
            i += i&(-i);
        }
    }

    public long sum(int x){
        long sum=0;
        int i=x;
        while(i>=1){
            sum += bit[i];
            i -= i&(-i);
        }
        return sum;
    }
}

// Arrays.sort(one, Comparator.comparingLong(o->o[0]));
//Arrays.sort(wt,(t1,t2) -> {
//        return (int)(t2.w - t1.w);
//        });
//Arrays.sort(arr, new Comparator<int[]>() {
//public int compare(int[] frst, int[] scnd) {
//        if(frst[colmn-1] > scnd[colmn-1]) {
//        return 1;
//        }
//        else return -1;
//        }
//        });

//class Trie{
//    Trie[] links;
//    public Trie(int m){
//        links = new Trie[m+1];
//    }