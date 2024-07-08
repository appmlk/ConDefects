import java.io.*;
import java.util.*;

public class Main {
    public static int INF = 0x3f3f3f3f, mod = 1000000007, mod9 = 998244353;
    public static void main(String args[]){
        try {
            PrintWriter o = new PrintWriter(System.out);
            boolean multiTest = false;
            // init
            if(multiTest) {
                int t = nextInt(), loop = 0;
                while (loop < t) {loop++;solve(o);}
            } else solve(o);
            o.close();
        } catch (Exception e) {e.printStackTrace();}
    }
    static List<Integer>[] G;
    static long[] sum, tot, C;
    static long res = 1l<<60;
    static void solve(PrintWriter o) {
        try {
            int n = nextInt();
            G = new ArrayList[n];
            Arrays.setAll(G, key->new ArrayList<>());
            sum = new long[n];
            tot = new long[n];
            C = new long[n];
            for(int i=0;i<n-1;i++) {
                int u = nextInt();
                int v = nextInt();
                u--;
                v--;
                G[u].add(v);
                G[v].add(u);
            }
            for(int i=0;i<n;i++) C[i] = nextInt();
            dfs1(0, 0, 0);
            dfs2(0, 0);
            o.println(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static void dfs1(int u, int p, int d) {
        sum[u] = C[u];
        tot[u] = 1l*d*C[u];
        for(int v: G[u]) {
            if(v == p) continue;
            dfs1(v, u, d+1);
            sum[u] += sum[v];
            tot[u] += tot[v];
        }
    }
    static void dfs2(int u, int p) {
        res = Math.min(res, tot[u]);
        for(int v: G[u]) {
            if(v == p) continue;
            sum[u] -= sum[v];
            tot[u] -= tot[v];
            tot[u] += sum[u];
            tot[v] -= sum[v];
            tot[v] += tot[u];
            sum[v] += sum[u];
            dfs2(v, u);
            sum[v] -= sum[u];
            tot[v] -= tot[u];
            tot[v] += sum[v];
            tot[u] -= sum[u];
            tot[u] += tot[v];
            sum[u] += sum[v];
        }
    }
    public static int upper_bound(List<Integer> a, int val){
        int l = 0, r = a.size();
        while(l < r){
            int mid = l + (r - l) / 2;
            if(a.get(mid) <= val) l = mid + 1;
            else r = mid;
        }
        return l;
    }
    public static int lower_bound(List<Integer> a, int val){
        int l = 0, r = a.size();
        while(l < r){
            int mid = l + (r - l) / 2;
            if(a.get(mid) < val) l = mid + 1;
            else r = mid;
        }
        return l;
    }
    public static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a%b);
    }
    public static long[] extgcd(long a, long b) {
        if(b == 0) return new long[]{1, 0};
        long[] it = extgcd(b, a%b);
        long x = it[1], y = it[0];
        y -= a/b*x;
        return new long[]{x, y};
    }
    public static long lcm(long a, long b){
        return a / gcd(a,b)*b;
    }
    public static long qpow(long a, long n, int md){
        a %= md;
        long ret = 1l;
        while(n > 0){
            if((n & 1) == 1) ret = ret * a % md;
            n >>= 1;
            a = a * a % md;
        }
        return ret;
    }
    public static class FenWick {
        int n;
        long[] a;
        long[] tree;
        public FenWick(int n){
            this.n = n;
            a = new long[n+1];
            tree = new long[n+1];
        }
        private void add(int x, long val){
            while(x <= n){
                tree[x] += val;
                x += x&-x;
            }
        }
        private void addMx(int x, long val) {
            a[x] += val;
            tree[x] = a[x];
            while(x <= n) {
                for(int i=1;i<(x&-x);i<<=1) tree[x] = Math.max(tree[x], tree[x-i]);
                x += x&-x;
            }
        }
        private long query(int x){
            long ret = 0l;
            while(x > 0){
                ret += tree[x];
                x -= x&-x;
            }
            return ret;
        }
        private long queryMx(int l, int r) {
            long res = 0l;
            while(l <= r) {
                if(r-(r&-r) >= l) {
                    res = Math.max(res, tree[r]);
                    r -= r&-r;
                }
                else {
                    res = Math.max(res, a[r]);
                    r--;
                }
            }
            return res;
        }
    }
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokenizer = new StringTokenizer("");
    private static String next() throws IOException{
        while(!tokenizer.hasMoreTokens()){tokenizer = new StringTokenizer(reader.readLine());}
        return tokenizer.nextToken();
    }
    public static int nextInt() throws IOException {return Integer.parseInt(next());}
    public static Long nextLong() throws IOException {return Long.parseLong(next());}
    public static double nextDouble() throws IOException {return Double.parseDouble(next());}
    public static char nextChar() throws IOException {return next().toCharArray()[0];}
    public static String nextString() throws IOException {return next();}
    public static String nextLine() throws IOException {return reader.readLine();}
}