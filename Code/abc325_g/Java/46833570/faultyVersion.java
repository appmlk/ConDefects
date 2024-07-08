import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.IntUnaryOperator;
import java.util.function.LongUnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    static In in = new In();
    static Out out = new Out(false, false);
    static final long inf = 0x1fffffffffffffffL;
    static final int iinf = 0x3fffffff;
    static final double eps = 1e-9;
    static long mod = 998244353;

    void solve() {
//        for (int v = 0; v < 1000; v++) {
//            int n = 1000;
//            char[] s = new char[n];
//            for (int i = 0; i < s.length; i++) {
//                s[i] = random() < 0.3333 ? 'o' : random() < 0.5 ? 'f' : 'a';
//            }
//            k1(s, 10);
//        }
        char[] s = in.nextCharArray();
        int k = in.nextInt();
        if (k == 0) {
            out.println(k0(s));
        } else {
            out.println(k1(s, k));
        }
    }

    int k1(char[] s, int k) {
        int n = s.length;
        int[][] ok = new int[n][n + 1];
        int[][] ok2 = new int[n][n + 1];
        for (int i = n - 1; i >= 0; i--) {
            if (s[i] == 'o') {
                if (i + 1 < n && s[i + 1] == 'f') {
                    ok[i][i + 2] = 2;
                    for (int j = i + 3; j <= n; j++) {
                        ok[i][j] = Math.min(ok2[i + 2][j] + k, j - (i + 2)) + 2;
                    }
                } else if (i + 1 < n && s[i + 1] == 'o') {
                    for (int j = i + 2; j < n; j++) {
                        if (ok[i + 1][j] == j - (i + 1) && s[j] == 'f') {
                            ok[i][j + 1] = j + 1 - i;
                            for (int r = j + 2; r <= n; r++) {
                                ok[i][r] = Math.min(ok2[j + 1][r] + k, r - (j + 1)) + (j + 1 - i);
                            }
                        }
                    }
                }
            }
            for (int j = i + 1; j <= n; j++) {
                ok2[i][j] = Math.max(ok2[i][j], ok[i][j]);
                if (i + 1 < n) {
                    ok2[i][j] = Math.max(ok2[i][j], ok2[i + 1][j]);
                }
            }
            for (int j = i + 1; j <= n; j++) {
                ok2[i][j] = Math.max(ok2[i][j], ok2[i][j - 1]);
                ok[i][j] = Math.max(ok[i][j], ok[i][j - 1]);
            }
            for (int r = i + 1; r <= n; r++) {
                for (int m = i + 1; m < r; m++) {
                    ok[i][r] = Math.max(ok[i][r], ok[i][m] + ok2[m][r]);
                    ok2[i][r] = Math.max(ok2[i][r], ok2[i][m] + ok2[m][r]);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n; j++) {
                ans = Math.max(ans, ok2[i][j]);
            }
        }
        return n - ans;
    }

    int k0(char[] s) {
        int depth = 0;
        int ans = s.length;
        for (char ch : s) {
            if (ch == 'o') {
                depth++;
            } else if (ch == 'f') {
                if (depth >= 1) {
                    ans -= 2;
                    depth--;
                }
            } else {
                depth = 0;
            }
        }
        return ans;
    }

    public static void main(String... args) {
        new Main().solve();
        out.flush();
    }
}

class In {
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

    public String next() {
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

    public String nextLine() {
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

    public int nextInt() {
        long val = nextLong();
        if (val < Integer.MIN_VALUE || Integer.MAX_VALUE < val) {
            throw new NumberFormatException();
        }
        return (int)val;
    }

    public long nextLong() {
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

    public double nextDouble() {
        return Double.parseDouble(next());
    }

    public char[] nextCharArray() {
        return next().toCharArray();
    }

    public String[] nextStringArray(int n) {
        String[] s = new String[n];
        for (int i = 0; i < n; i++) {
            s[i] = next();
        }
        return s;
    }

    public char[][] nextCharMatrix(int n, int m) {
        char[][] a = new char[n][m];
        for (int i = 0; i < n; i++) {
            a[i] = next().toCharArray();
        }
        return a;
    }

    public int[] nextIntArray(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = nextInt();
        }
        return a;
    }

    public int[] nextIntArray(int n, IntUnaryOperator op) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = op.applyAsInt(nextInt());
        }
        return a;
    }

    public int[][] nextIntMatrix(int h, int w) {
        int[][] a = new int[h][w];
        for (int i = 0; i < h; i++) {
            a[i] = nextIntArray(w);
        }
        return a;
    }

    public long[] nextLongArray(int n) {
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = nextLong();
        }
        return a;
    }

    public long[] nextLongArray(int n, LongUnaryOperator op) {
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = op.applyAsLong(nextLong());
        }
        return a;
    }

    public long[][] nextLongMatrix(int h, int w) {
        long[][] a = new long[h][w];
        for (int i = 0; i < h; i++) {
            a[i] = nextLongArray(w);
        }
        return a;
    }

    public List<List<Integer>> nextGraph(int n, int m, boolean directed) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            res.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int u = nextInt() - 1;
            int v = nextInt() - 1;
            res.get(u).add(v);
            if (!directed) {
                res.get(v).add(u);
            }
        }
        return res;
    }
}

class Out {
    private final PrintWriter out = new PrintWriter(System.out);
    private final PrintWriter err = new PrintWriter(System.err);
    public boolean autoFlush;
    public boolean enableDebug;

    public Out(boolean autoFlush, boolean enableDebug) {
        this.autoFlush = autoFlush;
        this.enableDebug = enableDebug;
    }

    public void debug(Object... args) {
        if (!enableDebug) {
            return;
        }
        if (args == null || args.getClass() != Object[].class) {
            args = new Object[] {args};
        }
        err.println(Arrays.stream(args).map(obj -> format(obj, true)).collect(Collectors.joining(" ")));
        err.flush();
    }

    private String format(Object obj, boolean canMultiline) {
        if (obj == null) return "null";
        Class<?> clazz = obj.getClass();
        if (clazz == Double.class) return String.format("%.10f", obj);
        if (clazz == int[].class) return Arrays.toString((int[])obj);
        if (clazz == long[].class) return Arrays.toString((long[])obj);
        if (clazz == char[].class) return String.valueOf((char[])obj);
        if (clazz == boolean[].class) return IntStream.range(0, ((boolean[])obj).length).mapToObj(i -> ((boolean[])obj)[i] ? "1" : "0").collect(Collectors.joining());
        if (clazz == double[].class) return Arrays.toString(Arrays.stream((double[])obj).mapToObj(a -> format(a, false)).toArray());
        if (canMultiline && clazz.isArray() && clazz.componentType().isArray()) return Arrays.stream((Object[])obj).map(a -> format(a, false)).collect(Collectors.joining("\n"));
        if (clazz == Object[].class) return Arrays.toString(Arrays.stream((Object[])obj).map(a -> format(a, false)).toArray());
        return String.valueOf(obj);
    }

    public void println(Object... args) {
        if (args == null || args.getClass() != Object[].class) {
            args = new Object[] {args};
        }
        out.println(Arrays.stream(args)
                .map(obj -> obj instanceof Double ? String.format("%.10f", obj) : String.valueOf(obj))
                .collect(Collectors.joining(" ")));
        if (autoFlush) {
            out.flush();
        }
    }

    public void println(char a) {
        out.println(a);
        if (autoFlush) {
            out.flush();
        }
    }

    public void println(int a) {
        out.println(a);
        if (autoFlush) {
            out.flush();
        }
    }

    public void println(long a) {
        out.println(a);
        if (autoFlush) {
            out.flush();
        }
    }

    public void println(double a) {
        out.println(String.format("%.10f", a));
        if (autoFlush) {
            out.flush();
        }
    }

    public void println(String s) {
        out.println(s);
        if (autoFlush) {
            out.flush();
        }
    }

    public void println(char[] s) {
        out.println(String.valueOf(s));
        if (autoFlush) {
            out.flush();
        }
    }

    public void println(int[] a) {
        StringJoiner joiner = new StringJoiner(" ");
        for (int i : a) {
            joiner.add(Integer.toString(i));
        }
        out.println(joiner);
        if (autoFlush) {
            out.flush();
        }
    }

    public void println(long[] a) {
        StringJoiner joiner = new StringJoiner(" ");
        for (long i : a) {
            joiner.add(Long.toString(i));
        }
        out.println(joiner);
        if (autoFlush) {
            out.flush();
        }
    }

    public void flush() {
        err.flush();
        out.flush();
    }
}