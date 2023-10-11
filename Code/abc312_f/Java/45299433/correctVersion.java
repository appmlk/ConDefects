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
            int n = fReader.nextInt(), m = fReader.nextInt();
            List<Integer> li1 = new ArrayList<>();
            List<Integer> li2 = new ArrayList<>();
            List<int[]> li12 = new ArrayList<>();
            List<Integer> li3 = new ArrayList<>();
            for(int i=0;i<n;i++) {
                int t = fReader.nextInt(), x = fReader.nextInt();
                if(t == 0) {
                    li1.add(x);
                    li12.add(new int[]{x, 0});
                }
                else if(t == 1) {
                    li2.add(x);
                    li12.add(new int[]{x, 1});
                }
                else li3.add(x);
            }
            Collections.sort(li1, (x,y)->y-x);
            Collections.sort(li2, (x,y)->y-x);
            Collections.sort(li12, (x,y)->y[0] == x[0] ? x[1]-y[1] : y[0]-x[0]);
            Collections.sort(li3, (x,y)->y-x);
            long[] preSum1 = new long[li1.size()+1];
            for(int i=0;i<li1.size();i++) preSum1[i+1] = preSum1[i] + li1.get(i);
            long[] preSum2 = new long[li2.size()+1];
            for(int i=0;i<li2.size();i++) preSum2[i+1] = preSum2[i] + li2.get(i);
            long[] preSum12 = new long[li12.size()+1];
            int[] preSumCnt = new int[li12.size()+1];
            for(int i=0;i<li12.size();i++) {
                preSum12[i+1] = preSum12[i] + li12.get(i)[0];
                preSumCnt[i+1] = preSumCnt[i] + li12.get(i)[1];
            }
            long[] preSum3 = new long[li3.size()+1];
            for(int i=0;i<li3.size();i++) preSum3[i+1] = preSum3[i] + li3.get(i);
            long res = preSum1[Math.min(m, li1.size())];
            for(int i=1;i<=Math.min(m, li3.size());i++) {
                long k = preSum3[i];
                int num = Math.min(li12.size(), m-i);
                int t = preSumCnt[num];
                if(k >= t) res = Math.max(res, preSum12[num]);
                else res = Math.max(res, preSum2[(int)k] + preSum1[Math.min(li1.size(), (int)(num-k))]);
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