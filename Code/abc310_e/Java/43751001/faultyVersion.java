import java.io.*;
import java.util.*;

public class Main {
    static int INF = 100000, mod = 1000000007, mod9 = 998244353;
    public static void main(String args[]) {
        try {
            PrintWriter o = new PrintWriter(System.out);
            boolean multiTest = false;
            // init
            if (multiTest) {
                int t = fReader.nextInt(), loop = 0;
                while (loop < t) {
                    loop++;
                    solve(o);
                }
            } else solve(o);
            o.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static void solve(PrintWriter o) {
        try {
            int n = fReader.nextInt();
            String s = fReader.nextString();
            int res = 0;
            long cnt0 = 0, cnt1 = 0;
            for(int i=0;i<n;i++) {
                long ncnt0 = 0, ncnt1 = 0;
                if(s.charAt(i) == '1') {
                    ncnt0 = cnt1;
                    ncnt1 = cnt0+1;
                }
                else {
                    ncnt1 = cnt0+cnt1;
                    ncnt0 = 1;
                }
                cnt0 = ncnt0;
                cnt1 = ncnt1;
                res += cnt1;
            }
            o.println(res);
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
        long ret = 1l;
        a %= md;
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
    }
    public static class FenWick {
        int n;
        long[] tree;
        public FenWick(int n){
            this.n = n;
            tree = new long[n+1];
        }
        private void add(int x, long val){
            while(x <= n){
                tree[x] += val;
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
    }
    public static class Pair<A, B> {
        public A fst;
        public B snd;
        public Pair(A a, B b) {
            this.fst = a;
            this.snd = b;
        }
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            } else if (!(other instanceof Pair)) {
                return false;
            } else {
                Pair<?, ?> o = (Pair)other;
                return this.fst.equals(o.fst) && this.snd.equals(o.snd);
            }
        }
        public int hashCode() {
            int hash = 1;
            if (this.fst != null) {
                hash = 31 * hash + this.fst.hashCode();
            }
            if (this.snd != null) {
                hash = 31 * hash + this.snd.hashCode();
            }
            return hash;
        }
        public String toString() {
            return "(" + this.fst + ", " + this.snd + ")";
        }
        public <A, B> Pair<A, B> make(A x, B y) {
            return new Pair(x, y);
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