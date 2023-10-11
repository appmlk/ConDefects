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
            long n = fReader.nextLong(), a = fReader.nextLong(), b = fReader.nextLong();
            long res = 0l;
            if(b >= a) {
                res += Math.max(0, n-a+1);
            }
            else {
                long t = n/a, mod = n%a;
                res += 1l*Math.max(0, t-1)*b;
                if(t > 0) res += Math.min(mod+1, b);
            }
            o.println(res);
        } catch (Exception e){e.printStackTrace();}
    }
    public static int upper_bound(List<int[]> a, int val){
        int l = 0, r = a.size();
        while(l < r){
            int mid = l + (r - l) / 2;
            if(a.get(mid)[0] <= val) l = mid + 1;
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
                ret = ret * a;
            }
            n >>= 1;
            a = a * a;
        }
        return ret;
    }
    public static class unionFind {
        int[] parent;
        int[] size;
        int n;
        public unionFind(int n){
            this.n = n;
            parent = new int[n+1];
            size = new int[n+1];
            for(int i=1;i<n;i++){
                parent[i] = i;
                size[i] = 1;
            }
        }
        public int find(int p){
            while(p != parent[p]){
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
        public int getCount(){
            return n;
        }
        public int[] getSize(){
            return size;
        }
    }
    public static class FenWick {
        long[] tree;
        int n;
        public FenWick(int n){
            this.n = n;
            tree = new long[n+1];
        }
        public void add(int x, int val){
            while(x <= n){
                tree[x] += val;
                x += lowBit(x);
            }
        }
        public int query(int x){
            int ret = 0;
            while(x > 0){
                ret += tree[x];
                x -= lowBit(x);
            }
            return ret;
        }
        public int lowBit(int x) {
            return x&-x;
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

