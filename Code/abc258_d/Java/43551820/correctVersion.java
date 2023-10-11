import com.sun.source.tree.WhileLoopTree;

import java.io.*;
import java.util.*;
import java.util.function.IntUnaryOperator;
import java.util.function.LongUnaryOperator;
import java.util.stream.Collectors;

public class Main {
    static In in = new FastIn();
    static Out out = new Out(false);
    static final long inf = 0x1fffffffffffffffL;
    static final int iinf = 0x3fffffff;
    static final double eps = 1e-9;
    static long mod = 998244353;
    void solve() {
        int n = in.nextInt();
        long x = in.nextLong();
        long[] a = new long[n];
        long[] b = new long[n];
        long[] sum = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextLong();
            b[i] = in.nextLong();
            if(i == 0){
                sum[i] = a[i] + b[i];
            }else{
                sum[i] = sum[i-1] + a[i] + b[i];
            }
        }
        long ans = a[0] + x * b[0];
        for (int i = 0; i < Math.min(n,x); i++) {
            ans = Math.min(ans,sum[i] + (x-i-1) * b[i]);
        }
        out.println(ans);
    }
    long lcm(long a,long b){
        return a / gcd(a,b) * b;
    }
    long gcd(long a,long b){
        return b == 0 ? a : gcd(b,a%b);
    }
    public static void main(String... args) {
        new Main().solve();
        out.flush();
    }
}
class IntPair{
    int f;
    int s;
    IntPair(int f,int s){
        this.f = f;
        this.s = s;
    }
}
class LongPair{
    long f;
    long s;
    LongPair(long f,long s){
        this.f = f;
        this.s = s;
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
        if ((int)val != val) {
            throw new NumberFormatException();
        }
        return (int)val;
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

    char[] nextCharArray() {
        return next().toCharArray();
    }

    String[] nextStringArray(int n) {
        String[] s = new String[n];
        for (int i = 0; i < n; i++) {
            s[i] = next();
        }
        return s;
    }

    char[][] nextCharGrid(int n, int m) {
        char[][] a = new char[n][m];
        for (int i = 0; i < n; i++) {
            a[i] = next().toCharArray();
        }
        return a;
    }

    int[] nextIntArray(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = nextInt();
        }
        return a;
    }

    int[] nextIntArray(int n, IntUnaryOperator op) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = op.applyAsInt(nextInt());
        }
        return a;
    }

    int[][] nextIntMatrix(int h, int w) {
        int[][] a = new int[h][w];
        for (int i = 0; i < h; i++) {
            a[i] = nextIntArray(w);
        }
        return a;
    }

    long[] nextLongArray(int n) {
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = nextLong();
        }
        return a;
    }

    long[] nextLongArray(int n, LongUnaryOperator op) {
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = op.applyAsLong(nextLong());
        }
        return a;
    }

    long[][] nextLongMatrix(int h, int w) {
        long[][] a = new long[h][w];
        for (int i = 0; i < h; i++) {
            a[i] = nextLongArray(w);
        }
        return a;
    }

    List<List<Integer>> nextEdges(int n, int m, boolean directed) {
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
    boolean autoFlush = false;
    boolean enableDebug;

    Out(boolean enableDebug) {
        this.enableDebug = enableDebug;
    }

    void println(Object... args) {
        if (args == null || args.getClass() != Object[].class) {
            args = new Object[] {args};
        }
        out.println(Arrays.stream(args).map(obj -> {
            Class<?> clazz = obj == null ? null : obj.getClass();
            return clazz == Double.class ? String.format("%.10f", obj) :
                    clazz == byte[].class ? Arrays.toString((byte[])obj) :
                            clazz == short[].class ? Arrays.toString((short[])obj) :
                                    clazz == int[].class ? Arrays.toString((int[])obj) :
                                            clazz == long[].class ? Arrays.toString((long[])obj) :
                                                    clazz == char[].class ? Arrays.toString((char[])obj) :
                                                            clazz == float[].class ? Arrays.toString((float[])obj) :
                                                                    clazz == double[].class ? Arrays.toString((double[])obj) :
                                                                            clazz == boolean[].class ? Arrays.toString((boolean[])obj) :
                                                                                    obj instanceof Object[] ? Arrays.deepToString((Object[])obj) :
                                                                                            String.valueOf(obj);
        }).collect(Collectors.joining(" ")));
        if (autoFlush) {
            out.flush();
        }
    }

    void debug(Object... args) {
        if (!enableDebug) {
            return;
        }
        if (args == null || args.getClass() != Object[].class) {
            args = new Object[] {args};
        }
        err.println(Arrays.stream(args).map(obj -> {
            Class<?> clazz = obj == null ? null : obj.getClass();
            return clazz == Double.class ? String.format("%.10f", obj) :
                    clazz == byte[].class ? Arrays.toString((byte[])obj) :
                            clazz == short[].class ? Arrays.toString((short[])obj) :
                                    clazz == int[].class ? Arrays.toString((int[])obj) :
                                            clazz == long[].class ? Arrays.toString((long[])obj) :
                                                    clazz == char[].class ? Arrays.toString((char[])obj) :
                                                            clazz == float[].class ? Arrays.toString((float[])obj) :
                                                                    clazz == double[].class ? Arrays.toString((double[])obj) :
                                                                            clazz == boolean[].class ? Arrays.toString((boolean[])obj) :
                                                                                    obj instanceof Object[] ? Arrays.deepToString((Object[])obj) :
                                                                                            String.valueOf(obj);
        }).collect(Collectors.joining(" ")));
        err.flush();
    }

    void println(int a) {
        out.println(a);
        if (autoFlush) {
            out.flush();
        }
    }

    void println(long a) {
        out.println(a);
        if (autoFlush) {
            out.flush();
        }
    }

    void println(double a) {
        out.println(String.format("%.10f", a));
        if (autoFlush) {
            out.flush();
        }
    }

    void println(String s) {
        out.println(s);
        if (autoFlush) {
            out.flush();
        }
    }

    void println(char[] s) {
        out.println(String.valueOf(s));
        if (autoFlush) {
            out.flush();
        }
    }

    void println(int[] a) {
        StringJoiner joiner = new StringJoiner(" ");
        for (int i : a) {
            joiner.add(Integer.toString(i));
        }
        out.println(joiner);
        if (autoFlush) {
            out.flush();
        }
    }

    void println(long[] a) {
        StringJoiner joiner = new StringJoiner(" ");
        for (long i : a) {
            joiner.add(Long.toString(i));
        }
        out.println(joiner);
        if (autoFlush) {
            out.flush();
        }
    }

    void flush() {
        err.flush();
        out.flush();
    }
}