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
    static void solve(PrintWriter o) {
        try {
            long x = nextLong(), y = nextLong();
            long g = gcd(x, -y);
            if(Math.abs(g) >= 3) {
                o.println(-1);
                return;
            }
            long[] ans = extgcd(x, -y);
            ans[0] = ans[0]*2/g;
            ans[1] = ans[1]*2/g;
            o.println(ans[1] + " " + ans[0]);
        } catch (Exception e) {
            e.printStackTrace();
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
    public static long gcd(long a, long b){
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