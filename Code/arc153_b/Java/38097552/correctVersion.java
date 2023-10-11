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
            int h = fReader.nextInt(), w = fReader.nextInt();
            char[][] a = new char[h][w];
            for(int i=0;i<h;i++) {
                String s = fReader.nextString();
                for(int j=0;j<w;j++) {
                    a[i][j] = s.charAt(j);
                }
            }
            int x = 0, y = 0, k = 1;
            int q = fReader.nextInt();
            for(int i=0;i<q;i++) {
                int x0 = fReader.nextInt()-1, y0 = fReader.nextInt()-1;
                x = (x0-x+h)%h;
                y = (y0-y+w)%w;
                k *= -1;
            }
            char[][] res = new char[h][w];
            for(int i=0;i<h;i++) {
                for(int j=0;j<w;j++) {
                    res[(k*i+x+h)%h][(k*j+y+w)%w] = a[i][j];
                }
            }
            for(int i=0;i<h;i++) {
                for(int j=0;j<w;j++) o.print(res[i][j]);
                o.println();
            }
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
    public static long qpow(long a, long n, int m){
        long ret = 1l;
        a %= m;
        while(n > 0){
            if((n & 1) == 1){
                ret = ret * a % m;
            }
            n >>= 1;
            a = a * a % m;
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
    static class Pair implements Comparable<Pair>{
        Long x, y;
        public Pair(long x, long y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public int hashCode() {
            int prime = 131, ret = 1;
            ret = ret*prime + x.hashCode();
            ret = ret*prime + y.hashCode();
            return ret;
        }
        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Pair) {
                return x.equals(((Pair) obj).x) && y.equals(((Pair) obj).y);
            }
            return false;
        }
        @Override
        public int compareTo(Pair node) {
            int cp1 = Long.compare(x, node.x);
            int cp2 = Long.compare(y, node.y);
            return cp1 == 0 ? cp2 : cp1;
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