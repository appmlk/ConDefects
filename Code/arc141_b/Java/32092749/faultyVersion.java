import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.io.*;
public class Main {
    static final long INF = 1l << 60;
    static final int inf = 1 << 30;
    static final int MOD = 998244353;
    //static final int MOD = 1000000007;
    static final double EPS = 1e-9;
    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        PrintWriter pw = new PrintWriter(System.out);
        Mod mod = new Mod(MOD);
        long n = sc.nextLong();
        long m = sc.nextLong();
        if (n>=60) {pw.println(0);return;}
        long[] a = new long[60];
        for (int i = 0; i < 60; i++) {
            if (((1l<<(i+1))-1) < m) a[i] = mod.pow(2, i);
            else {a[i] = mod.mod(m+1-(1l<<i));break;}
        }
        long[][] dp = new long[(int)n+1][60];
        dp[0][0] = dp[1][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 60; j++) for (int k = j+1; k < 60; k++) {
                dp[i+1][k] = mod.add(dp[i+1][k], mod.mul(dp[i][j], a[k]));
            }
        }
        long ans = 0;
        for (long x : dp[(int)n]) ans = mod.add(ans, x);
        pw.println(ans);
        pw.close();
    }
}
class Mod {
    private long mod;
    public Mod(long mod) { this.mod = mod; }
    public long getMod() { return mod; }
    public long mod(long x) { x %= mod; return x < 0 ? x + mod : x; }
    public long add(long x, long y) { return mod(mod(x) + mod(y)); }
    public long sub(long x, long y) { return mod(mod(x) - mod(y)); }
    public long mul(long x, long y) { return mod(mod(x) * mod(y)); }
    public long div(long x, long y) { return mul(mod(x), inv(y)); }
    public long inv(long x) {
        x = mod(x);
        long y = mod, u = 1, v = 0;
        while (y > 0) {
            long t = x / y;
            x -= t * y;
            u -= t * v;
            t = x; x = y; y = t;
            t = u; u = v; v = t;
        }
        return mod(u);
    }
    public long pow(long x, long n) {
        long res = 1; x = mod(x);
        while (n > 0) {
            if ((n & 1) == 1) res = mul(res, x);
            x = mul(x, x);
            n >>= 1;
        }
        return res;
    }
    public long[][] comb(int n) {
        long[][] res = new long[n+1][n+1];
        res[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            res[i][0] = 1;
            for (int j = 1; j <= n; j++) {
                res[i][j] = add(res[i-1][j-1], res[i-1][j]);
            }
        }
        return res;
    }
}
class FastScanner {
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
