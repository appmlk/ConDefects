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
    static void solve(PrintWriter o) {
        try {
            int n = fReader.nextInt();
            int[] D = new int[n];
            for(int i=0;i<n;i++) D[i] = fReader.nextInt();
            int[] L = new int[2];
            int[] C = new int[2];
            int[] K = new int[2];
            for(int i=0;i<2;i++) {
                L[i] = fReader.nextInt();
                C[i] = fReader.nextInt();
                K[i] = fReader.nextInt();
            }
            int[] dp = new int[K[0]+1];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[0] = 0;
            for(int i=0;i<n;i++) {
                int[] ndp = new int[K[0]+1];
                Arrays.fill(ndp, Integer.MAX_VALUE);
                for(int j=0;j<=K[0];j++) {
                    if(dp[j] == Integer.MAX_VALUE) continue;
                    for (int u = 0; u <= (D[i] + L[0] - 1) / L[0]; u++) {
                        int v = (Math.max(0, D[i] - u * L[0]) + L[1] - 1) / L[1];
                        if(j+u <= K[0]) ndp[j+u] = Math.min(ndp[j+u], dp[j]+v);
                    }
                }
                dp = ndp;
            }
            long res = 1l<<60;
            for(int i=0;i<=K[0];i++) {
                if(dp[i] > K[1]) continue;
                res = Math.min(res, 1l*i*C[0]+1l*dp[i]*C[1]);
            }
            o.println(res >= 1l<<60 ? -1 : res);
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
    public static class DSU {
        int[] parent;
        int[] size;
        int n;
        public DSU(int n){
            this.n = n;
            parent = new int[n];
            size = new int[n];
            for(int i=0;i<n;i++){
                parent[i] = i;
                size[i] = 1;
            }
        }
        public int find(int p){
            while(parent[p] != p){
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
            return p;
        }
        public void union(int p, int q){
            int root_p = find(p);
            int root_q = find(q);
            if(root_p == root_q) return;
            if(size[root_p] >= size[root_q]){
                parent[root_q] = root_p;
                size[root_p] += size[root_q];
                size[root_q] = 0;
            }
            else{
                parent[root_p] = root_q;
                size[root_q] += size[root_p];
                size[root_p] = 0;
            }
            n--;
        }
        public int getTotalComNum(){
            return n;
        }
        public int getSize(int i){
            return size[find(i)];
        }
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
        Integer c2;
        Integer c3;
        Integer c4;
        public Pair(Integer c1, Integer c2, Integer c3, Integer c4) {
            this.c1 = c1;
            this.c2 = c2;
            this.c3 = c3;
            this.c4 = c4;
        }
        @Override
        public int hashCode() {
            int prime = 31, ret = 1;
            ret = ret*prime + c1.hashCode();
            ret = ret*prime + c2.hashCode();
            ret = ret*prime + c3.hashCode();
            ret = ret*prime + c4.hashCode();
            return ret;
        }
        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Pair) {
                return c1.equals(((Pair) obj).c1) && c2.equals(((Pair) obj).c2) && c3.equals(((Pair) obj).c3) && c4.equals(((Pair) obj).c4);
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