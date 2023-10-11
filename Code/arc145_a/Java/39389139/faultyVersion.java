import java.io.*;
import java.util.*;

public class Main {
    static FastIn in = new FastIn();
    static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
    static final long inf = 0x1fffffffffffffffL;
    static final int IINF = 0x3fffffff;
    static final double eps = 1e-9;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------


    void solve() {
        int n = in.nextInt();
        String s = in.next();
        if (n <= 2) {
            if (s.charAt(0) != s.charAt(n - 1)) out.println("No");
            else out.println("Yes");
            return;
        }
        if (s.charAt(0) == 'B' || s.charAt(n - 1) == 'A') out.println("Yes");
        out.println("No");
    }


    int gcd(int a, int b) {
        return a == 0 ? b : gcd(b % a, a);
    }

    long exGcd(long a, long b, int[] x, int[] y) {
        if (b == 0) {
            x[0] = 1;
            y[0] = 0;
            return a;
        } else {
            long d = exGcd(b, Math.floorMod(a, b), y, x);
            y[0] -= a / b * x[0];
            return d;
        }
    }

    static long qmi(long a, long b, long mod) {
        long res = 1;
        while (b != 0) {
            if ((b & 1) == 1) {
                res = res * a % mod;
            }
            a = a * a % mod;
            b >>= 1;
        }
        return res;
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

