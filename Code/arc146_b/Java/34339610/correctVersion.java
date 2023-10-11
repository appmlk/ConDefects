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
    static int n, m, k;
    static void solve(PrintWriter o) {
        try {
            n = fReader.nextInt();
            m = fReader.nextInt();
            k = fReader.nextInt();
            a = new int[n];
            for(int i=0;i<n;i++) a[i] = fReader.nextInt();
            int ans = 0;
            for(int i=30;i>=0;i--) if(check(ans|(1<<i))) ans |= 1<<i;
            o.println(ans);
        } catch (Exception e){e.printStackTrace();}
    }
    static boolean check(int left) {
        List<Integer> li = new ArrayList<>();
        for(int i=0;i<n;i++) {
            if(a[i] <= left) {
                li.add(left - a[i]);
                continue;
            }
            int right = 0;
            for(int j=30;j>=0;j--) {
                if((left & 1<<j) > 0) right |= 1<<j;
                else {
                    if(((right|1<<j)-1) < a[i]) right |= 1<<j;
                }
            }
            int cnt = right - a[i];
            li.add(cnt);
        }
        Collections.sort(li);
        long total = 0l;
        for(int i=0;i<k;i++) total += li.get(i);
        return total <= m;
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
                ret = ret * a % mod;
            }
            n >>= 1;
            a = a * a % mod;
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