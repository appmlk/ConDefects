import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.IntUnaryOperator;
import java.util.function.LongUnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

public class Main {
    static In in = new FastIn();
    static Out out = new Out(false);
    static final long inf = 0x1fffffffffffffffL;
    static final int iinf = 0x3fffffff;
    static final double eps = 1e-9;
    static long mod = 998244353;

    void solve() {
        long start = System.currentTimeMillis();
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        int thres = (n + 1) / 2;
        for (int prime : new SieveOfEratosthenes(200000)) {
            if (prime == 2) {
                continue;
            }
            if (prime > 100000) {
                break;
            }
            int[] c = new int[prime];
            for (int i = 0; i < n; i++) {
                c[a[i] % prime]++;
                if (c[a[i] % prime] >= thres) {
                    out.println(prime);
                    return;
                }
            }
        }
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        while (System.currentTimeMillis() - start < 1800) {
            int k = Math.min(n, 5);
            int[] v = new int[k];
            for (int i = 0; i < k; i++) {
                v[i] = a[rand.nextInt(n)];
            }
            Arrays.sort(v);
            int gcd = v[1] - v[0];
            for (int i = 2; i < k; i++) {
                if (gcd == 0) {
                    gcd = v[i] - v[i - 1];
                } else if (v[i] - v[i - 1] != 0) {
                    gcd = gcd(gcd, v[i] - v[i - 1]);
                }
            }
            if (gcd <= 2) {
                continue;
            }
            int va = 0;
            int co = 0;
            for (int i = 0; i < n; i++) {
                if (a[i] % gcd == va) {
                    co++;
                } else if (co == 0) {
                    va = a[i] % gcd;
                    co++;
                } else {
                    co--;
                }
            }
            co = 0;
            for (int i = 0; i < n; i++) {
                if (a[i] % gcd == va) {
                    co++;
                }
            }
            if (co >= thres) {
                out.println(gcd);
                return;
            }
        }
        out.println(-1);
    }

    int gcd(int a, int b) {
        return 0 < b ? gcd(b, a % b) : a;
    }

    static class SieveOfEratosthenes implements Iterable<Integer> {
        private final List<Integer> primes = new ArrayList<>();
        private int[] table;

        public SieveOfEratosthenes() {
            this(1000);
        }

        public SieveOfEratosthenes(int initialCapacity) {
            calcTable(initialCapacity);
        }

        private void calcTable(int n) {
            int oldCapacity = 0;
            int[] newTable = new int[n];
            if (table != null) {
                oldCapacity = table.length;
                System.arraycopy(table, 0, newTable, 0, oldCapacity);
            }
            table = newTable;
            for (int i = oldCapacity; i < n; i++) {
                table[i] = i;
            }
            for (int i = 2; i < n; i++) {
                if (i >= oldCapacity && table[i] == i) {
                    primes.add(i);
                }
                for (int prime : primes) {
                    if (prime > table[i] || (long)prime * i >= n) {
                        break;
                    }
                    table[prime * i] = prime;
                }
            }
        }

        private void grow(int minCapacity) {
            int oldCapacity = table.length;
            int newCapacity = (int)Math.min(Integer.MAX_VALUE, (long)oldCapacity + (oldCapacity >> 1));
            calcTable(newCapacity);
        }

        public int minPrimeFactor(int n) {
            if (table.length <= n) {
                grow(n + 1);
            }
            return table[n];
        }

        public int nthPrime(int n) {
            while (primes.size() <= n) {
                grow(table.length + 1);
            }
            return primes.get(n);
        }

        public int countLessThan(int n) {
            while (primes.get(primes.size() - 1) < n) {
                grow(table.length + 1);
            }
            int left = -1;
            int right = primes.size();
            while (right - left > 1) {
                int mid = (left + right) / 2;
                if (primes.get(mid) < n) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
            return right;
        }

        public TreeMap<Integer, Integer> factorize(int n) {
            TreeMap<Integer, Integer> primeFactors = new TreeMap<>();
            while (n > 1) {
                int primeFactor = minPrimeFactor(n);
                int exp = 0;
                while (n % primeFactor == 0) {
                    n /= primeFactor;
                    exp++;
                }
                primeFactors.put(primeFactor, exp);
            }
            return primeFactors;
        }

        public List<Integer> divisors(int n) {
            List<Integer> divisors = new ArrayList<>();
            divisors.add(1);
            TreeMap<Integer, Integer> primeFactors = factorize(n);
            for (Map.Entry<Integer, Integer> entry : primeFactors.entrySet()) {
                int exp = entry.getValue();
                int s = divisors.size();
                for (int i = 0; i < s; i++) {
                    int v = 1;
                    for (int j = 0; j < exp; j++) {
                        v *= entry.getKey();
                        divisors.add(divisors.get(i) * v);
                    }
                }
            }
            Collections.sort(divisors);
            return divisors;
        }

        public IntStream stream() {
            int characteristics = Spliterator.ORDERED | Spliterator.DISTINCT | Spliterator.SORTED | Spliterator.NONNULL | Spliterator.IMMUTABLE;
            Spliterator.OfInt spliterator = Spliterators.spliteratorUnknownSize(iterator(), characteristics);
            return StreamSupport.intStream(spliterator, false);
        }

        @Override
        public PrimitiveIterator.OfInt iterator() {
            return new PrimitiveIterator.OfInt() {
                private int i = 0;

                @Override
                public int nextInt() {
                    return nthPrime(i++);
                }

                @Override
                public boolean hasNext() {
                    return true;
                }
            };
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
