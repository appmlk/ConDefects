import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.ToIntFunction;

public final class Main {
    void solve(Input in, PrintWriter out) {
        int N = in.nextInt();
        int K = in.nextInt();
        int[] P = in.nextIntArray(N);
        int[] Q = in.nextIntArray(N);
        decrement(P);
        decrement(Q);
        var a = new int[N];
        for (int i = 0; i < P.length; i++) {
            a[P[i]] = Q[i];
        }
        var dp = new long[N + 1][K + 1][N + 1];
        dp[0][0][N] = 1;
        for (int i = 0; i < N; i++) {
            for (int k = 0; k <= K; k++) {
                for (int q = 0; q <= N; q++) {
                    if (k + 1 <= K && a[i] < q) {
                        add(dp[i + 1][k + 1], q, dp[i][k][q]);
                    }
                    add(dp[i + 1][k], Math.min(q, a[i]), dp[i][k][q]);
                }
            }
        }
        var ans = mint(0);
        for (int q = 0; q < N; q++) {
            ans.add(dp[N][K][q]);
        }
        out.println(ans);
    }
    private static int[] decrement(int[] a) {
        for (int i = 0; i < a.length; i++)
            a[i]--;
        return a;
    }
    private static final int MOD = 998_244_353;
    static int rem(long a) {
        return Mod.rem(a, MOD);
    }
    static long add(long x, long y) {
        return Mod.add(x, y, MOD);
    }
    static void add(long[] a, int i, long v) {
        Mod.add(a, i, v, MOD);
    }
    static long sub(long x, long y) {
        return Mod.sub(x, y, MOD);
    }
    static long mul(long x, long y) {
        return Mod.mul(x, y, MOD);
    }
    static long pow(long base, long exp) {
        return Mod.pow(base, exp, MOD);
    }
    static long pow(long base, ToIntFunction<Mod> exp) {
        return Mod.pow(base, exp, MOD);
    }
    static long mulInverse(long a) {
        return Mod.mulInverse(a, MOD);
    }
    static long div(long x, long y) {
        return Mod.div(x, y, MOD);
    }
    static long[] cachePow(long base, int maxExponent) {
        return Mod.cachePow(base, maxExponent, MOD);
    }
    static long[] factorial(int maxN) {
        return Mod.factorial(maxN, MOD);
    }
    static long[] inverseFactorial(long[] factorial) {
        return Mod.inverseFactorial(factorial, MOD);
    }
    static Mod.Permutation permutation(int maxN) {
        return Mod.permutation(maxN, MOD);
    }
    static Mod.Combination combination(int maxN) {
        return Mod.combination(maxN, MOD);
    }
    static Mod.Int mint(long initialValue) {
        return Mod.mint(initialValue, MOD);
    }
    static Mod.Int mint(Mod.Int initialValue) {
        return Mod.mint(initialValue, MOD);
    }
    static final class Mod {
        final int divisor;

        Mod(int divisor) {
            if (divisor <= 0)
                throw new IllegalArgumentException();
            this.divisor = divisor;
        }
        static int rem(long a, int mod) {
            a %= mod;
            if (a < 0)
                return (int)(a + mod);
            return (int)a;
        }
        int rem(long a) {
            return rem(a, divisor);
        }
        static long add(long x, long y, int mod) {
            long z = x + y;
            if (((x ^ z) & (y ^ z)) < 0)
                return x % mod + y % mod;
            return z;
        }
        long add(long x, long y) {
            return add(x, y, divisor);
        }
        static void add(long[] a, int i, long v, int mod) {
            a[i] = add(a[i], v, mod);
        }
        void add(long[] a, int index, long v) {
            add(a, index, v, divisor);
        }
        static long sub(long x, long y, int mod) {
            long z = x - y;
            if (((x ^ y) & (x ^ z)) < 0)
                return x % mod - y % mod;
            return z;
        }
        long sub(long x, long y) {
            return sub(x, y, divisor);
        }
        static final long MUL_SAFE_MIN = Integer.MIN_VALUE + 1;
        static final long MUL_SAFE_MAX = Integer.MAX_VALUE;
        static long mul(long x, long y, int mod) {
            if (x > MUL_SAFE_MAX || x < MUL_SAFE_MIN)
                x %= mod;
            if (y > MUL_SAFE_MAX || y < MUL_SAFE_MIN)
                y %= mod;
            return x * y;
        }
        long mul(long x, long y) {
            return mul(x, y, divisor);
        }
        static long pow(long base, long exp, int mod) {
            if (exp < 0)
                throw new IllegalArgumentException();
            long res = 1;
            for (long e = exp; e > 0; e >>= 1) {
                if ((e & 1) != 0)
                    res = mul(res, base, mod);
                base = mul(base, base, mod);
            }
            return res;
        }
        long pow(long base, long exp) {
            return pow(base, exp, divisor);
        }
        static long pow(long base, ToIntFunction<Mod> exp, int mod) {
            // assert isPrime(mod);
            if (base % mod == 0)
                return 0;
            return pow(base, exp.applyAsInt(new Mod(mod - 1)), mod);
        }
        long pow(long base, ToIntFunction<Mod> exp) {
            return pow(base, exp, divisor);
        }
        static long mulInverse(long a, int mod) {
            // assert isCoprime(a, m);
            if (mod == 1)
                return 0;
            if (a == 0)
                throw new IllegalArgumentException("divide by zero");
            a = rem(a, mod);
            long b = mod, x = 1, u = 0, tmp;
            while (b != 0) {
                long k = a / b;

                a -= k * b;
                tmp = a;
                a = b;
                b = tmp;

                x -= k * u;
                tmp = x;
                x = u;
                u = tmp;
            }
            if (a != 1)
                throw new IllegalArgumentException("divide by zero");
            return rem(x, mod);
        }
        long mulInverse(long a) {
            return mulInverse(a, divisor);
        }
        static long div(long x, long y, int mod) {
            return mul(x, mulInverse(y, mod), mod);
        }
        long div(long x, long y) {
            return div(x, y, divisor);
        }
        static long[] cachePow(long base, int maxExponent, int mod) {
            var res = new long[maxExponent + 1];
            long v = res[0] = 1;
            base = rem(base, mod);
            for (int i = 1; i <= maxExponent; i++) {
                v *= base;
                if (v > MUL_SAFE_MAX)
                    v %= mod;
                res[i] = v;
            }
            return res;
        }
        long[] cachePow(long base, int maxExponent) {
            return cachePow(base, maxExponent, divisor);
        }
        static long[] factorial(int maxN, int mod) {
            var res = new long[maxN + 1];
            long v = res[0] = res[1] = 1;
            for (int i = 2; i <= maxN; i++) {
                v *= i;
                if (v > MUL_SAFE_MAX)
                    v %= mod;
                res[i] = v;
            }
            return res;
        }
        long[] factorial(int maxN) {
            return factorial(maxN, divisor);
        }
        static long[] inverseFactorial(long[] factorial, int mod) {
            int n = factorial.length;
            var res = new long[n];
            long v = res[n - 1] = mulInverse(factorial[n - 1], mod);
            for (int i = n - 1; i > 0; i--) {
                v *= i;
                if (v > MUL_SAFE_MAX)
                    v %= mod;
                res[i - 1] = v;
            }
            return res;
        }
        long[] inverseFactorial(long[] factorial) {
            return inverseFactorial(factorial, divisor);
        }
        static Permutation permutation(int maxN, int mod) {
            long[] fact = factorial(maxN, mod);
            long[] invFact = inverseFactorial(fact, mod);
            return new Permutation(fact, invFact, mod);
        }
        Permutation permutation(int maxN) {
            return permutation(maxN, divisor);
        }
        static final class Permutation {
            final int divisor;
            final long[] factorial;
            final long[] inverseFactorial;

            private Permutation(long[] factorial,
                                long[] inverseFactorial,
                                int divisor) {
                this.divisor = divisor;
                this.factorial = factorial;
                this.inverseFactorial = inverseFactorial;
            }
            long of(int n, int k) {
                if (k > n)
                    return 0;
                return mul(factorial[n], inverseFactorial[n - k], divisor);
            }
            Combination combination() {
                return new Combination(factorial, inverseFactorial, divisor);
            }
        }
        static Combination combination(int maxN, int mod) {
            long[] fact = factorial(maxN, mod);
            long[] invFact = inverseFactorial(fact, mod);
            return new Combination(fact, invFact, mod);
        }
        Combination combination(int maxN) {
            return combination(maxN, divisor);
        }
        static final class Combination {
            final int divisor;
            final long[] factorial;
            final long[] inverseFactorial;

            private Combination(long[] factorial,
                                long[] inverseFactorial,
                                int divisor) {
                this.divisor = divisor;
                this.factorial = factorial;
                this.inverseFactorial = inverseFactorial;
            }
            long of(int n, int k) {
                if (k > n)
                    return 0;
                return mul(mul(factorial[n], inverseFactorial[k], divisor),
                           inverseFactorial[n - k],
                           divisor);
            }
            Permutation permutation() {
                return new Permutation(factorial, inverseFactorial, divisor);
            }
        }
        static Int mint(long initialValue, int mod) {
            return new Int(initialValue, mod);
        }
        Int mint(long initialValue) {
            return mint(initialValue, divisor);
        }
        static Int mint(Int initialValue, int mod) {
            return new Int(initialValue.rawValue, mod);
        }
        Int mint(Int initialValue) {
            return mint(initialValue, divisor);
        }
        static final class Int {
            final int divisor;
            private long rawValue;

            private Int(long initialValue, int divisor) {
                this.divisor = divisor;
                rawValue = initialValue;
            }
            int rem() {
                return (int)(rawValue = Mod.rem(rawValue, divisor));
            }
            Int set(long value) {
                this.rawValue = value;
                return this;
            }
            Int add(long a) {
                rawValue = Mod.add(rawValue, a, divisor);
                return this;
            }
            Int add(Int a) {
                return add(a.rawValue);
            }
            Int sub(long a) {
                rawValue = Mod.sub(rawValue, a, divisor);
                return this;
            }
            Int sub(Int a) {
                return sub(a.rawValue);
            }
            Int mul(long a) {
                rawValue = Mod.mul(rawValue, a, divisor);
                return this;
            }
            Int mul(Int a) {
                return mul(a.rawValue);
            }
            Int pow(long exponent) {
                rawValue = Mod.pow(rawValue, exponent, divisor);
                return this;
            }
            Int div(long a) {
                rawValue = Mod.div(rawValue, a, divisor);
                return this;
            }
            Int div(Int a) {
                return div(a.rawValue);
            }
            void addTo(long[] a, int i) {
                Mod.add(a, i, rawValue, divisor);
            }
            @Override
            public String toString() {
                return Integer.toString(rem());
            }
        }
    }
    public static void main(String[] args) {
        var w = new PrintWriter(System.out);
        new Main().solve(new Input(System.in), w);
        w.flush();
    }
    static final class Input {
        private final InputStream in;
        private final byte[] buffer = new byte[8192];
        private int point;
        private int readLength;

        Input(InputStream in) {
            this.in = Objects.requireNonNull(in);
        }
        private int readByte() {
            if (point < readLength)
                return buffer[point++];
            try {
                readLength = in.read(buffer);
            } catch (IOException e) {
                throw new AssertionError(null, e);
            }
            if (readLength == -1)
                return -1;
            point = 1;
            return buffer[0];
        }
        private static boolean isVisibleChar(int c) {
            return 33 <= c && c <= 126;
        }
        char nextChar() {
            int b = readByte();
            while (!(b == -1 || isVisibleChar(b)))
                b = readByte();
            if (b == -1)
                throw new NoSuchElementException();
            return (char)b;
        }
        String next() {
            int c = nextChar();
            var b = new StringBuilder();
            do {
                b.append((char)c);
                c = readByte();
            } while (isVisibleChar(c));
            return b.toString();
        }
        String next(int length) {
            if (length == 0)
                return "";
            var b = new StringBuilder(length);
            int c = nextChar();
            b.append((char)c);
            for (int i = 1; i < length; i++) {
                c = readByte();
                if (!isVisibleChar(c))
                    throw new InputMismatchException();
                b.append((char)c);
            }
            return b.toString();
        }
        long nextLong() {
            int c = nextChar();
            boolean minus = false;
            long limit = -Long.MAX_VALUE;
            if (c == '-') {
                minus = true;
                limit = Long.MIN_VALUE;
                c = readByte();
            }
            long n = 0L;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                if (n < limit / 10L)
                    throw new InputMismatchException("overflow");
                n *= 10L;
                int digit = c - '0';
                if (n < limit + digit)
                    throw new InputMismatchException("overflow");
                n -= digit;
                c = readByte();
            } while (isVisibleChar(c));
            return minus ? n : -n;
        }
        int nextInt() {
            long n = nextLong();
            if (n < Integer.MIN_VALUE || n > Integer.MAX_VALUE)
                throw new InputMismatchException();
            return (int)n;
        }
        double nextDouble() {
            return Double.parseDouble(next());
        }
        int[] nextIntArray(int length) {
            var a = new int[length];
            for (int i = 0; i < length; i++)
                a[i] = nextInt();
            return a;
        }
        long[] nextLongArray(int length) {
            var a = new long[length];
            for (int i = 0; i < length; i++)
                a[i] = nextLong();
            return a;
        }
        char[] nextCharArray(int length) {
            var a = new char[length];
            for (int i = 0; i < length; i++)
                a[i] = nextChar();
            return a;
        }
        int[][] next2dIntArray(int height, int width) {
            var a = new int[height][width];
            for (int i = 0; i < height; i++)
                for (int j = 0; j < width; j++)
                    a[i][j] = nextInt();
            return a;
        }
        long[][] next2dLongArray(int height, int width) {
            var a = new long[height][width];
            for (int i = 0; i < height; i++)
                for (int j = 0; j < width; j++)
                    a[i][j] = nextLong();
            return a;
        }
        char[][] next2dCharArray(int height, int width) {
            var a = new char[height][width];
            for (int i = 0; i < height; i++)
                for (int j = 0; j < width; j++)
                    a[i][j] = nextChar();
            return a;
        }
        int[][] nextVerticalIntArrays(int arrayCount, int arrayLength) {
            var a = new int[arrayCount][arrayLength];
            for (int i = 0; i < arrayLength; i++)
                for (int j = 0; j < arrayCount; j++)
                    a[j][i] = nextInt();
            return a;
        }
        long[][] nextVerticalLongArrays(int arrayCount, int arrayLength) {
            var a = new long[arrayCount][arrayLength];
            for (int i = 0; i < arrayLength; i++)
                for (int j = 0; j < arrayCount; j++)
                    a[j][i] = nextLong();
            return a;
        }
        List<String> nextStringList(int length) {
            var a = new ArrayList<String>(length);
            for (int i = 0; i < length; i++)
                a.add(next());
            return a;
        }
    }
}
