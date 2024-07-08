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
    static void solve(PrintWriter o){
        try {
            int N = fReader.nextInt(), P = fReader.nextInt();
            long[][] dp0 = new long[N][N+10];
            long[][] dp1 = new long[N][N+10];
            dp0[0][0] = 1;
            dp1[0][1] = 1;
            for(int i=0;i<N-1;i++){
                for(int j=0;j<N;j++){
                    dp0[i][j] %= P;
                    dp1[i][j] %= P;
                    dp0[i+1][j] += dp0[i][j];
                    dp0[i+1][j] += dp1[i][j];
                    dp0[i+1][j+1] += dp0[i][j] * 3;
                    dp1[i+1][j+1] += dp1[i][j];
                    dp1[i+1][j+2] += dp0[i][j] * 2;
                }
            }
            for(int i=1;i<=N-1;i++) o.print(dp0[N-1][i] + " ");
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
            if((n & 1) == 1) ret = ret * a % mod9;
            n >>= 1;
            a = a * a % mod9;
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
            for(int i=1;i<=n;i++){
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