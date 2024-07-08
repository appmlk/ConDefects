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
    static int n, m, k, now = 0;
    static List<int[]>[] G;
    static boolean[] vis;
    static int lamp[];
    static List<Integer> res = new ArrayList<>();
    static void solve(PrintWriter o) {
        try {
            n = nextInt();
            m = nextInt();
            k = nextInt();
            G = new ArrayList[n];
            Arrays.setAll(G, key->new ArrayList<>());
            vis = new boolean[n];
            lamp = new int[n];
            for(int i=0;i<m;i++) {
                int u = nextInt();
                int v = nextInt();
                u--;
                v--;
                G[u].add(new int[]{v, i});
                G[v].add(new int[]{u, i});
            }
            for(int i=0;i<n;i++) if(!vis[i]) dfs(i);
            if(now < k) o.println("No");
            else {
                o.println("Yes");
                o.println(res.size());
                for(int num: res) o.print(num + " ");
                o.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static void dfs(int u) {
        vis[u] = true;
        for(int[] it: G[u]) {
            int v = it[0];
            int id = it[1];
            if(vis[v]) continue;
            dfs(v);
            if(lamp[v] == 0 && now < k) {
                now -= lamp[u] + lamp[v];
                lamp[u] ^= 1;
                lamp[v] ^= 1;
                now += lamp[u] + lamp[v];
                res.add(id+1);
            }
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
            if((n & 1) == 1){
                ret = ret * a % md;
            }
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
                for(int i=1;i<(x&-x);i<<=1) {
                    tree[x] = Math.max(tree[x], tree[x-i]);
                }
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
    public static class Pair{
        Integer u;
        Integer v;
        public Pair(Integer u, Integer v) {
            this.u = u;
            this.v = v;
        }
        @Override
        public int hashCode() {
            int prime = 31, ret = 1;
            ret = ret*prime + u.hashCode();
            ret = ret*prime + v.hashCode();
            return ret;
        }
        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Pair) {
                return u.equals(((Pair) obj).u) && v.equals(((Pair) obj).v);
            }
            return false;
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