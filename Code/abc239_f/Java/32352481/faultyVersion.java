import java.io.*;
import java.util.*;

public class Main {
    public static int INF = 0x3f3f3f3f;
    public static int mod = 1000000007;
    public static int mod9 = 998244353;
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
            int[] d = new int[n];
            DSU dsu = new DSU(n);
            for(int i=0;i<n;i++) d[i] = fReader.nextInt();
            for(int i=0;i<m;i++) {
                int u = fReader.nextInt(), v = fReader.nextInt();
                d[u]--;
                d[v]--;
                dsu.merge(u, v);
            }
            List<Integer>[] li = new ArrayList[n];
            for(int i=0;i<n;i++) li[i] = new ArrayList<>();
            for(int i=0;i<n;i++){
                if(d[i] < 0) {
                    o.println(-1);
                    return;
                }
                int top = dsu.top(i);
                for(int j=0;j<d[i];j++) li[top].add(i);
            }
            Queue<Integer> com1 = new ArrayDeque<>();
            List<List<Integer>> com2 = new ArrayList<>();
            for(int i=0;i<n;i++){
                if(li[i].size() == 1) com1.offer(li[i].get(0));
                else if(li[i].size() > 1) com2.add(li[i]);
            }
            List<int[]> res = new ArrayList<>();
            for(List<Integer> curLi: com2) {
                for(int i=0;i<curLi.size()-1;i++) {
                    if(com1.isEmpty()) {
                        o.println(-1);
                        return;
                    }
                    int u = curLi.get(i), v = com1.poll();
                    dsu.merge(u, v);
                    res.add(new int[]{u, v});
                }
                com1.offer(curLi.get(curLi.size()-1));
            }
            if(com1.size() != 2) {
                o.println(-1);
                return;
            }
            int u = com1.poll(), v = com1.poll();
            dsu.merge(u, v);
            res.add(new int[]{u, v});
            if(dsu.getComNum() != 1) {
                o.println(-1);
                return;
            }
            for(int[] item: res) {
                o.println((item[0]+1) + " " + (item[1]+1));
            }
        } catch (Exception e){e.printStackTrace();}
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
    public static boolean isPrime(long x){
        boolean ok = true;
        for(long i=2;i<=Math.sqrt(x);i++){
            if(x % i == 0){
                ok = false;
                break;
            }
        }
        return ok;
    }
    public static void reverse(int[] array){
        reverse(array, 0 , array.length-1);
    }
    public static void reverse(int[] array, int left, int right) {
        if (array != null) {
            int i = left;
            for(int j = right; j > i; ++i) {
                int tmp = array[j];
                array[j] = array[i];
                array[i] = tmp;
                --j;
            }
        }
    }
    public static long qpow(long a, long n){
        long ret = 1l;
        while(n > 0){
            if((n & 1) == 1){
                ret = ret * a % mod9;
            }
            n >>= 1;
            a = a * a % mod9;
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
        public int top(int p){
            while(parent[p] != p){
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
            return p;
        }
        public void merge(int p, int q){
            int root_p = top(p);
            int root_q = top(q);
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
        public int getComNum(){
            return n;
        }
        public int[] getSize(){
            return size;
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