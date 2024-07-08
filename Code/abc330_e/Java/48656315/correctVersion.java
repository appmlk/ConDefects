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
                int t = fReader.nextInt(), loop = 0;
                while (loop < t) {loop++;solve(o);}
            } else solve(o);
            o.close();
        } catch (Exception e) {e.printStackTrace();}
    }
    static int[] a;
    static int[] u;
    static int[] f;
    static void solve(PrintWriter o) {
        try {
            int n = fReader.nextInt(), q = fReader.nextInt();
            a = new int[n+1];
            u = new int[n+1];
            f = new int[4*n];
            for(int i=1;i<=n;i++) {
                a[i] = fReader.nextInt();
                if(a[i] <= n) u[a[i]]++;
            }
            buildTree(1, 0, n);
            for(int i=0;i<q;i++) {
                int x = fReader.nextInt(), y = fReader.nextInt();
                if(a[x] <= n) update(1, 0, n, a[x], -1);
                a[x] = y;
                if(a[x] <= n) update(1, 0, n, a[x], 1);
                o.println(f[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static void buildTree(int k, int l, int r) {
        if(l == r) {
            if(u[l] > 0) f[k] = 1;
            else f[k] = 0;
            return;
        }
        int m = (l+r)>>1;
        buildTree(2*k, l, m);
        buildTree(2*k+1, m+1, r);
        pushUp(k, l, r);
    }
    static void update(int k, int l, int r, int x, int y) {
        if(l == r) {
            u[l] += y;
            if(u[l] > 0) f[k] = 1;
            else f[k] = 0;
            return;
        }
        int m = (l+r)>>1;
        if(x <= m) update(2*k, l, m, x, y);
        else update(2*k+1, m+1, r, x, y);
        pushUp(k, l, r);
    }
    static void pushUp(int k, int l, int r) {
        int m = (l+r)>>1;
        if(f[2*k] < (m-l+1)) f[k] = f[2*k];
        else f[k] = f[2*k] + f[2*k+1];
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
    public static long gcd(long a, long b){
        return b == 0 ? a : gcd(b, a%b);
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
        Integer c1;
        String str;
        public Pair(Integer c1, String str) {
            this.c1 = c1;
            this.str = str;
        }
        @Override
        public int hashCode() {
            int prime = 31, ret = 1;
            ret = ret*prime + c1.hashCode();
            return ret;
        }
        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Pair) {
                return c1.equals(((Pair) obj).c1);
            }
            return false;
        }
    }
    public static class fReader {
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
}