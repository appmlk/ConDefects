import java.io.*;
import java.math.BigInteger;
import java.util.*;


public class Main {
    static FastIn in = new FastIn();
    static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
    static final double eps = 1e-9;
    static int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    //----------------------------------TEMPLATE-----------------------------------------------------------------------------------------------------------------------------------
    static class SegTree {

        int n;
        long[] sum;

        int[] flip;


        SegTree(int _n) {
            n = _n;
            sum = new long[n * 4];
            flip = new int[n * 4];
        }

        void pushDown(int o, int l, int r) {
            if (flip[o] == 1) {
                int mid = (l + r) / 2;
                sum[o * 2] = mid - l + 1 - sum[o * 2];
                flip[o * 2] ^= 1;
                sum[o * 2 + 1] = r - mid - sum[o * 2 + 1];
                flip[o * 2 + 1] ^= 1;
                flip[o] = 0;
            }
        }


        void pushUp(int o) {
            sum[o] = (sum[o * 2] + sum[o * 2 + 1]);
        }


        void upDate(int o, int l, int r, int L, int R) {
            if (l >= L && r <= R) {
                sum[o] = r - l + 1 - sum[o];
                flip[o] ^= 1;
                return;
            }
            pushDown(o, l, r);
            int mid = (l + r) / 2;
            if (mid >= L) upDate(o * 2, l, mid, L, R);
            if (mid < R) upDate(o * 2 + 1, mid + 1, r, L, R);
            pushUp(o);
        }


        long query(int o, int l, int r, int L, int R) {
            if (l >= L && r <= R) {
                return sum[o];
            }
            pushDown(o, l, r);
            long ans = 0;
            int mid = (l + r) / 2;
            if (mid >= L) ans += query(o * 2, l, mid, L, R);
            if (mid < R) ans += query(o * 2 + 1, mid + 1, r, L, R);
            return ans;
        }

    }


    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------

    //TODO 记得必要时开long或BigInteger


    //int T = in.nextInt();
    long mod = 998244353;

    void solve() {
        int n = in.nextInt(), m = in.nextInt(), k = in.nextInt();
        long[] s = new long[m + 1];
        for (int i = 0; i <= m; i++) {
            s[i] = i;
        }
        for (int i = 2; i <= n; i++) {
            long[] ss = new long[m + 1];
            for (int j = 1; j <= m; j++) {
                ss[j] = (ss[j - 1] + s[m]) % mod;
                ss[j] = ((ss[j] - (s[Math.min(m, j + k - 1)] - s[Math.max(0, j - k)])) % mod + mod) % mod;
            }
            s = ss;
        }
        out.println((s[m] % mod + mod) % mod);
    }


    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public static void main(String... args) {
        new Main().solve();
        out.close();
    }
}

class FastIn extends In {
    private final BufferedInputStream reader = new BufferedInputStream(System.in);
    private final byte[] buffer = new byte[0x10000];
    private int i = 0;
    private int length = 0;

    public int read() {
        if (i == length) {
            i = 0;
            try {
                length = reader.read(buffer);
            } catch (IOException ignored) {
            }
            if (length == -1) {
                return 0;
            }
        }
        if (length <= i) {
            throw new RuntimeException();
        }
        return buffer[i++];
    }

    String next() {
        StringBuilder builder = new StringBuilder();
        int b = read();
        while (b < '!' || '~' < b) {
            b = read();
        }
        while ('!' <= b && b <= '~') {
            builder.appendCodePoint(b);
            b = read();
        }
        return builder.toString();
    }

    String nextLine() {
        StringBuilder builder = new StringBuilder();
        int b = read();
        while (b != 0 && b != '\r' && b != '\n') {
            builder.appendCodePoint(b);
            b = read();
        }
        if (b == '\r') {
            read();
        }
        return builder.toString();
    }

    int nextInt() {
        long val = nextLong();
        if ((int) val != val) {
            throw new NumberFormatException();
        }
        return (int) val;
    }

    @Override
    long nextLong() {
        int b = read();
        while (b < '!' || '~' < b) {
            b = read();
        }
        boolean neg = false;
        if (b == '-') {
            neg = true;
            b = read();
        }
        long n = 0;
        int c = 0;
        while ('0' <= b && b <= '9') {
            n = n * 10 + b - '0';
            b = read();
            c++;
        }
        if (c == 0 || c >= 2 && n == 0) {
            throw new NumberFormatException();
        }
        return neg ? -n : n;
    }
}

class In {
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in), 0x10000);
    private StringTokenizer tokenizer;

    String next() {
        try {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
        } catch (IOException ignored) {
        }
        return tokenizer.nextToken();
    }

    int nextInt() {
        return Integer.parseInt(next());
    }

    long nextLong() {
        return Long.parseLong(next());
    }

    double nextDouble() {
        return Double.parseDouble(next());
    }

}

