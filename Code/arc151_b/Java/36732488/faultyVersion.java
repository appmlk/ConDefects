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
        int m = in.nextInt();
        int[] p = in.nextIntArray(n, i -> i - 1);
        long inv2 = (mod + 1) / 2;
        UnionFind uf = new UnionFind(n);
        long ans = Combination.pow(m, n - 1) * (m - 1) % mod * inv2 % mod;
        uf.unite(0, p[0]);
        for (int i = 1; i < n; i++) {
            int u = uf.size();
            if (!uf.same(i, p[i])) {
                ans += Combination.pow(m, u - 1) * (m - 1) % mod * inv2 % mod;
                ans %= mod;
            }
            uf.unite(i, p[i]);
        }
        out.println(ans);
    }

    static class Combination {
        private static final int MEMO_THRESHOLD = 1000000;
        static long mod = Main.mod;
        private static final List<Long> inv = new ArrayList<>();
        private static final List<Long> fact = new ArrayList<>();
        private static final List<Long> invFact = new ArrayList<>();
        private static final Map<Long, List<Long>> pow = new HashMap<>();

        private static void buildInvTable(int n) {
            if (inv.isEmpty()) {
                inv.add(null);
                inv.add(1L);
            }
            for (int i = inv.size(); i <= n; i++) {
                inv.add(mod - inv.get((int)(mod % i)) * (mod / i) % mod);
            }
        }

        private static void buildFactTable(int n) {
            if (fact.isEmpty()) {
                fact.add(1L);
                invFact.add(1L);
            }
            for (int i = fact.size(); i <= n; i++) {
                fact.add(fact.get(i - 1) * i % mod);
                invFact.add(inv(fact.get(i)));
            }
        }

        public static void setupPowTable(long a) {
            pow.put(a, new ArrayList<>(Collections.singleton(1L)));
        }

        private static void rangeCheck(long n, long r) {
            if (n < r) {
                throw new IllegalArgumentException("n < r");
            }
            if (n < 0) {
                throw new IllegalArgumentException("n < 0");
            }
            if (r < 0) {
                throw new IllegalArgumentException("r < 0");
            }
        }

        static long fact(int n) {
            buildFactTable(n);
            return fact.get(n);
        }

        static long invFact(int n) {
            buildFactTable(n);
            return invFact.get(n);
        }

        private static long comb0(int n, int r) {
            rangeCheck(n, r);
            return fact(n) * invFact(r) % mod * invFact(n - r) % mod;
        }

        static long comb(long n, long r) {
            rangeCheck(n, r);
            if (n < MEMO_THRESHOLD) {
                return comb0((int)n, (int)r);
            }
            r = Math.min(r, n - r);
            long x = 1, y = 1;
            for (long i = 1; i <= r; i++) {
                x = x * (n - r + i) % mod;
                y = y * i % mod;
            }
            return x * inv(y) % mod;
        }

        private static long perm0(int n, int r) {
            rangeCheck(n, r);
            return fact(n) * invFact(n - r) % mod;
        }

        static long perm(long n, long r) {
            rangeCheck(n, r);
            if (n < MEMO_THRESHOLD) {
                return perm0((int)n, (int)r);
            }
            long x = 1;
            for (long i = 1; i <= r; i++) {
                x = x * (n - r + i) % mod;
            }
            return x;
        }

        static long homo(long n, long r) {
            return r == 0 ? 1 : comb(n + r - 1, r);
        }

        private static long inv0(int a) {
            buildInvTable(a);
            return inv.get(a);
        }

        static long inv(long a) {
            if (a < MEMO_THRESHOLD) {
                return inv0((int)a);
            }
            long b = mod;
            long u = 1, v = 0;
            while (b >= 1) {
                long t = a / b;
                a -= t * b;
                u -= t * v;
                if (a < 1) {
                    return (v %= mod) < 0 ? v + mod : v;
                }
                t = b / a;
                b -= t * a;
                v -= t * u;
            }
            return (u %= mod) < 0 ? u + mod : u;
        }

        static long pow(long a, long b) {
            if (pow.containsKey(a) && b < MEMO_THRESHOLD) {
                return powMemo(a, (int)b);
            }
            long x = 1;
            while (b > 0) {
                if (b % 2 == 1) {
                    x = x * a % mod;
                }
                a = a * a % mod;
                b >>= 1;
            }
            return x;
        }

        static long powMemo(long a, int b) {
            List<Long> powMemo = pow.get(a);
            while (powMemo.size() <= b) {
                powMemo.add(powMemo.get(powMemo.size() - 1) * a % Main.mod);
            }
            return powMemo.get(b);
        }
    }

    static class UnionFind {
        private int size;
        private final int n;
        private final int[] parent;
        private final int[] sizes;

        public UnionFind(int n) {
            this.n = n;
            this.size = n;
            this.parent = new int[n];
            this.sizes = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                sizes[i] = 1;
            }
        }

        public int root(int n) {
            while (n != parent[n]) {
                n = parent[n] = parent[parent[n]];
            }
            return n;
        }

        public void unite(int x, int y) {
            x = root(x);
            y = root(y);
            if (x != y) {
                if (sizes[x] > sizes[y]) {
                    parent[y] = x;
                    sizes[x] += sizes[y];
                } else {
                    parent[x] = y;
                    sizes[y] += sizes[x];
                }
                size--;
            }
        }

        public int size() {
            return size;
        }

        public boolean same(int x, int y) {
            return root(x) == root(y);
        }

        public int getSize(int n) {
            return sizes[root(n)];
        }

        @Override
        public String toString() {
            Map<Integer, List<Integer>> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                map.computeIfAbsent(root(i), key -> new ArrayList<>()).add(i);
            }
            return new ArrayList<>(map.values()).toString();
        }
    }

    public static void main(String... args) {
        new Main().solve();
        out.flush();
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

    void println(char a) {
        out.println(a);
        if (autoFlush) {
            out.flush();
        }
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
