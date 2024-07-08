import java.io.*;
import java.util.*;

public class Main {
    public static int INF = 0x3f3f3f3f;
    public static int mod = 1000000007;
    public static int mod9 = 998244353;
    public static long MAX = (long)1e12;
    public static void main(String args[]){
        try {
            PrintWriter o = new PrintWriter(System.out);
            FastScanner sc = new FastScanner();
            boolean multiTest = false;
            if(multiTest) {
                int t = fReader.nextInt(), loop = 0;
                while (loop < t) {
                    loop++;
                    solve(o, sc);
                }
            } else solve(o, sc);
            o.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    static void solve(PrintWriter o, FastScanner sc){
        try {
            int N = sc.nextInt();
            int[] arr = new int[N];
            for(int i=0;i<N;i++){
                arr[i] = sc.nextInt()-1;
            }
            int Q = sc.nextInt();
            int d = Math.max(1, (int)(N / Math.sqrt(N)));
            int cur = 0;
            int nl = 0, nr = -1;
            int[] res = new int[Q];
            int[] count = new int[N];
            int[][] qq = new int[Q][4];
            for(int i=0;i<Q;i++){
                int l = sc.nextInt()-1, r = sc.nextInt()-1;
                qq[i] = new int[]{l, r, i, r/d};
            }
            Arrays.sort(qq, (a,b) -> a[3] == b[3] ? ((a[3] & 1) == 1 ? a[0] - b[0] : b[0] - a[0]) : a[3] - b[3]);
            for(int[] q: qq){
                while(nl < q[0]){
                    cur = addAndRemove(nl++, cur, arr, count, -1);
                }
                while(nl > q[0]){
                    cur = addAndRemove(--nl, cur, arr, count, +1);
                }
                while(nr < q[1]){
                    cur = addAndRemove(++nr, cur, arr, count, +1);
                }
                while(nr > q[1]){
                    cur = addAndRemove(nr--, cur, arr, count, -1);
                }
                res[q[2]] = cur;
            }
            for(int i=0;i<Q;i++){
                o.println(res[i]);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    static int addAndRemove(int x, int cur, int[] arr, int[] count, int val){
        cur -= count[arr[x]] / 2;
        count[arr[x]] += val;
        cur += count[arr[x]] / 2;
        return cur;
    }
    public static int get_first_bit(long x){
        for(int i=63;i>=0;i--){
            if((x & 1l << i) > 0){
                return i;
            }
        }
        return -1;
    }
    public static int countOne(long x){
        int count = 0;
        for(int i=0;i<64;i++){
            if((x & 1l << i) > 0){
                count++;
            }
        }
        return count;
    }
    public static int gcd(int a, int b){
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
    public static boolean doubleEqual(double d1, double d2){
        if(Math.abs(d1-d2) < 1e-6){
            return true;
        }
        return false;
    }
    public static long fac(int n){
        long ret = 1;
        while(n > 0){
            ret = ret * n % mod;
            n--;
        }
        return ret;
    }
    public static long qpow(int n, int m){
        long n_ = n, ret = 1;
        while(m > 0){
            if((m&1) == 1){
                ret = ret * n_ % mod;
            }
            m >>= 1;
            n_ = n_ * n_ % mod;
        }
        return ret;
    }
    static class FastScanner {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1024];
        private int ptr = 0;
        private int buflen = 0;
        private boolean hasNextByte() {
            if (ptr < buflen) {
                return true;
            } else {
                ptr = 0;
                try {
                    buflen = in.read(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (buflen <= 0) {
                    return false;
                }
            }
            return true;
        }
        private int readByte() { if (hasNextByte()) return buffer[ptr++]; else return -1;}
        private static boolean isPrintableChar(int c) { return 33 <= c && c <= 126;}
        public boolean hasNext() { while (hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++; return hasNextByte();}
        public String next() {
            if (!hasNext()) throw new NoSuchElementException();
            StringBuilder sb = new StringBuilder();
            int b = readByte();
            while(isPrintableChar(b)) {
                sb.appendCodePoint(b);
                b = readByte();
            }
            return sb.toString();
        }
        public long nextLong() {
            if (!hasNext()) throw new NoSuchElementException();
            long n = 0;
            boolean minus = false;
            int b = readByte();
            if (b == '-') {
                minus = true;
                b = readByte();
            }
            if (b < '0' || '9' < b) {
                throw new NumberFormatException();
            }
            while (true) {
                if ('0' <= b && b <= '9') {
                    n *= 10;
                    n += b - '0';
                } else if (b == -1 || !isPrintableChar(b)) {
                    return minus ? -n : n;
                } else {
                    throw new NumberFormatException();
                }
                b = readByte();
            }
        }
        public int nextInt() {
            long nl = nextLong();
            if (nl < Integer.MIN_VALUE || nl > Integer.MAX_VALUE) throw new NumberFormatException();
            return (int) nl;
        }
        public double nextDouble() { return Double.parseDouble(next());}
    }
    public static class fReader {
        private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        private static StringTokenizer tokenizer = new StringTokenizer("");
        private static String next() throws IOException{
            while(!tokenizer.hasMoreTokens()){
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }
        public static int nextInt() throws IOException{
            return Integer.parseInt(next());
        }
        public static Long nextLong() throws IOException{
            return Long.parseLong(next());
        }
        public static double nextDouble() throws IOException{
            return Double.parseDouble(next());
        }
        public static String nextLine() throws IOException{
            return reader.readLine();
        }
    }
}