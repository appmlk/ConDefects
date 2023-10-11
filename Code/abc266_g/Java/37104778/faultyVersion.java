import java.util.*;
import java.io.*;

class Main {
    private static final void solve() throws IOException {
        final int r = ni(), g = ni(), b = ni(), k = ni();
        final int n1 = r - k, n2 = g - k, n3 = b, n4 = k;
        var fa = new ModIntFactory(998244353);
        var ans = fa.factorial(n1 + n3 + n4).div(fa.factorial(n1)).div(fa.factorial(n3)).div(fa.factorial(n4));
        ans.mulAsg(fa.combination(n1 + n2 + n4, n2));
        ou.println(ans);
    }

    public static void main(String[] args) throws IOException {
        solve();
        ou.flush();
    }

    private static final int ni() throws IOException {
        return sc.nextInt();
    }

    private static final int[] ni(int n) throws IOException {
        return sc.nextIntArray(n);
    }

    private static final long nl() throws IOException {
        return sc.nextLong();
    }

    private static final long[] nl(int n) throws IOException {
        return sc.nextLongArray(n);
    }

    private static final String ns() throws IOException {
        return sc.next();
    }

    private static final double nd() throws IOException {
        return sc.nextDouble();
    }

    private static final ContestInputStream sc = new ContestInputStream();
    private static final ContestOutputStream ou = new ContestOutputStream();
}

class ModIntFactory {
    private final ModArithmetic ma;
    private final int mod;

    private final boolean usesMontgomery;
    private final ModArithmetic.ModArithmeticMontgomery maMontgomery;

    private ArrayList<Integer> factorial;

    public ModIntFactory(int mod) {
        this.ma = ModArithmetic.of(mod);
        this.usesMontgomery = ma instanceof ModArithmetic.ModArithmeticMontgomery;
        this.maMontgomery = usesMontgomery ? (ModArithmetic.ModArithmeticMontgomery) ma : null;
        this.mod = mod;

        this.factorial = new ArrayList<>();
    }

    public ModIntFactory(double mod) {
        this((int) mod);
    }

    public ModInt create(long value) {
        if ((value %= mod) < 0)
            value += mod;
        if (usesMontgomery) {
            return new ModInt(maMontgomery.generate(value));
        } else {
            return new ModInt((int) value);
        }
    }

    private void prepareFactorial(int max) {
        factorial.ensureCapacity(max + 1);
        if (factorial.size() == 0)
            factorial.add(1);
        for (int i = factorial.size(); i <= max; i++) {
            if (usesMontgomery)
                factorial.add(ma.mul(factorial.get(i - 1), maMontgomery.generate(i)));
            else
                factorial.add(ma.mul(factorial.get(i - 1), i));
        }
    }

    public ModInt factorial(int i) {
        prepareFactorial(i);
        return create(factorial.get(i));
    }

    public ModInt permutation(int n, int r) {
        if (n < 0 || r < 0 || n < r)
            return create(0);
        prepareFactorial(n);
        return create(ma.div(factorial.get(n), factorial.get(r)));
    }

    public ModInt combination(int n, int r) {
        if (n < 0 || r < 0 || n < r)
            return create(0);
        prepareFactorial(n);
        return create(ma.div(factorial.get(n), ma.mul(factorial.get(r), factorial.get(n - r))));
    }

    public int getMod() {
        return mod;
    }

    public class ModInt {
        private int value;

        private ModInt(int value) {
            this.value = value;
        }

        public int mod() {
            return mod;
        }

        public int value() {
            if (usesMontgomery) {
                return maMontgomery.reduce(value);
            }
            return value;
        }

        public ModInt add(ModInt mi) {
            return new ModInt(ma.add(value, mi.value));
        }

        public ModInt add(ModInt mi1, ModInt mi2) {
            return new ModInt(ma.add(value, mi1.value)).addAsg(mi2);
        }

        public ModInt add(ModInt mi1, ModInt mi2, ModInt mi3) {
            return new ModInt(ma.add(value, mi1.value)).addAsg(mi2).addAsg(mi3);
        }

        public ModInt add(ModInt mi1, ModInt mi2, ModInt mi3, ModInt mi4) {
            return new ModInt(ma.add(value, mi1.value)).addAsg(mi2).addAsg(mi3).addAsg(mi4);
        }

        public ModInt add(ModInt mi1, ModInt... mis) {
            ModInt mi = add(mi1);
            for (ModInt m : mis)
                mi.addAsg(m);
            return mi;
        }

        public ModInt add(long mi) {
            return new ModInt(ma.add(value, ma.remainder(mi)));
        }

        public ModInt sub(ModInt mi) {
            return new ModInt(ma.sub(value, mi.value));
        }

        public ModInt sub(long mi) {
            return new ModInt(ma.sub(value, ma.remainder(mi)));
        }

        public ModInt mul(ModInt mi) {
            return new ModInt(ma.mul(value, mi.value));
        }

        public ModInt mul(ModInt mi1, ModInt mi2) {
            return new ModInt(ma.mul(value, mi1.value)).mulAsg(mi2);
        }

        public ModInt mul(ModInt mi1, ModInt mi2, ModInt mi3) {
            return new ModInt(ma.mul(value, mi1.value)).mulAsg(mi2).mulAsg(mi3);
        }

        public ModInt mul(ModInt mi1, ModInt mi2, ModInt mi3, ModInt mi4) {
            return new ModInt(ma.mul(value, mi1.value)).mulAsg(mi2).mulAsg(mi3).mulAsg(mi4);
        }

        public ModInt mul(ModInt mi1, ModInt... mis) {
            ModInt mi = mul(mi1);
            for (ModInt m : mis)
                mi.mulAsg(m);
            return mi;
        }

        public ModInt mul(long mi) {
            return new ModInt(ma.mul(value, ma.remainder(mi)));
        }

        public ModInt div(ModInt mi) {
            return new ModInt(ma.div(value, mi.value));
        }

        public ModInt div(long mi) {
            return new ModInt(ma.div(value, ma.remainder(mi)));
        }

        public ModInt inv() {
            return new ModInt(ma.inv(value));
        }

        public ModInt pow(long b) {
            return new ModInt(ma.pow(value, b));
        }

        public ModInt pow(ModInt mi) {
            return new ModInt(ma.pow(value, mi.value));
        }

        public ModInt addAsg(ModInt mi) {
            this.value = ma.add(value, mi.value);
            return this;
        }

        public ModInt addAsg(ModInt mi1, ModInt mi2) {
            return addAsg(mi1).addAsg(mi2);
        }

        public ModInt addAsg(ModInt mi1, ModInt mi2, ModInt mi3) {
            return addAsg(mi1).addAsg(mi2).addAsg(mi3);
        }

        public ModInt addAsg(ModInt mi1, ModInt mi2, ModInt mi3, ModInt mi4) {
            return addAsg(mi1).addAsg(mi2).addAsg(mi3).addAsg(mi4);
        }

        public ModInt addAsg(ModInt... mis) {
            for (ModInt m : mis)
                addAsg(m);
            return this;
        }

        public ModInt addAsg(long mi) {
            this.value = ma.add(value, ma.remainder(mi));
            return this;
        }

        public ModInt subAsg(ModInt mi) {
            this.value = ma.sub(value, mi.value);
            return this;
        }

        public ModInt subAsg(long mi) {
            this.value = ma.sub(value, ma.remainder(mi));
            return this;
        }

        public ModInt mulAsg(ModInt mi) {
            this.value = ma.mul(value, mi.value);
            return this;
        }

        public ModInt mulAsg(ModInt mi1, ModInt mi2) {
            return mulAsg(mi1).mulAsg(mi2);
        }

        public ModInt mulAsg(ModInt mi1, ModInt mi2, ModInt mi3) {
            return mulAsg(mi1).mulAsg(mi2).mulAsg(mi3);
        }

        public ModInt mulAsg(ModInt mi1, ModInt mi2, ModInt mi3, ModInt mi4) {
            return mulAsg(mi1).mulAsg(mi2).mulAsg(mi3).mulAsg(mi4);
        }

        public ModInt mulAsg(ModInt... mis) {
            for (ModInt m : mis)
                mulAsg(m);
            return this;
        }

        public ModInt mulAsg(long mi) {
            this.value = ma.mul(value, ma.remainder(mi));
            return this;
        }

        public ModInt divAsg(ModInt mi) {
            this.value = ma.div(value, mi.value);
            return this;
        }

        public ModInt divAsg(long mi) {
            this.value = ma.div(value, ma.remainder(mi));
            return this;
        }

        public ModInt powAsg(ModInt mi) {
            this.value = ma.pow(value, mi.value());
            return this;
        }

        public ModInt powAsg(long mi) {
            this.value = ma.pow(value, mi);
            return this;
        }

        @Override
        public String toString() {
            return String.valueOf(value());
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof ModInt) {
                ModInt mi = (ModInt) o;
                return mod() == mi.mod() && value() == mi.value();
            }
            return false;
        }

        @Override
        public int hashCode() {
            return (1 * 37 + mod()) * 37 + value();
        }
    }

    private static abstract class ModArithmetic {
        abstract int mod();

        abstract int remainder(long value);

        abstract int add(int a, int b);

        abstract int sub(int a, int b);

        abstract int mul(int a, int b);

        int div(int a, int b) {
            return mul(a, inv(b));
        }

        int inv(int a) {
            int b = mod();
            if (b == 1)
                return 0;
            long u = 1, v = 0;
            while (b >= 1) {
                int t = a / b;
                a -= t * b;
                int tmp1 = a;
                a = b;
                b = tmp1;
                u -= t * v;
                long tmp2 = u;
                u = v;
                v = tmp2;
            }
            if (a != 1)
                throw new ArithmeticException("divide by zero");
            return remainder(u);
        }

        int pow(int a, long b) {
            if (b < 0)
                throw new ArithmeticException("negative power");
            int r = 1;
            int x = a;
            while (b > 0) {
                if ((b & 1) == 1)
                    r = mul(r, x);
                x = mul(x, x);
                b >>= 1;
            }
            return r;
        }

        static ModArithmetic of(int mod) {
            if (mod <= 0) {
                throw new IllegalArgumentException();
            } else if (mod == 1) {
                return new ModArithmetic1();
            } else if (mod == 2) {
                return new ModArithmetic2();
            } else if (mod == 998244353) {
                return new ModArithmetic998244353();
            } else if (mod == 1000000007) {
                return new ModArithmetic1000000007();
            } else if ((mod & 1) == 1) {
                return new ModArithmeticMontgomery(mod);
            } else {
                return new ModArithmeticBarrett(mod);
            }
        }

        private static final class ModArithmetic1 extends ModArithmetic {
            int mod() {
                return 1;
            }

            int remainder(long value) {
                return 0;
            }

            int add(int a, int b) {
                return 0;
            }

            int sub(int a, int b) {
                return 0;
            }

            int mul(int a, int b) {
                return 0;
            }

            int pow(int a, long b) {
                return 0;
            }
        }

        private static final class ModArithmetic2 extends ModArithmetic {
            int mod() {
                return 2;
            }

            int remainder(long value) {
                return (int) (value & 1);
            }

            int add(int a, int b) {
                return a ^ b;
            }

            int sub(int a, int b) {
                return a ^ b;
            }

            int mul(int a, int b) {
                return a & b;
            }
        }

        private static final class ModArithmetic998244353 extends ModArithmetic {
            private final int mod = 998244353;

            int mod() {
                return mod;
            }

            int remainder(long value) {
                return (int) ((value %= mod) < 0 ? value + mod : value);
            }

            int add(int a, int b) {
                int res = a + b;
                return res >= mod ? res - mod : res;
            }

            int sub(int a, int b) {
                int res = a - b;
                return res < 0 ? res + mod : res;
            }

            int mul(int a, int b) {
                return (int) (((long) a * b) % mod);
            }
        }

        private static final class ModArithmetic1000000007 extends ModArithmetic {
            private final int mod = 1000000007;

            int mod() {
                return mod;
            }

            int remainder(long value) {
                return (int) ((value %= mod) < 0 ? value + mod : value);
            }

            int add(int a, int b) {
                int res = a + b;
                return res >= mod ? res - mod : res;
            }

            int sub(int a, int b) {
                int res = a - b;
                return res < 0 ? res + mod : res;
            }

            int mul(int a, int b) {
                return (int) (((long) a * b) % mod);
            }
        }

        private static final class ModArithmeticMontgomery extends ModArithmeticDynamic {
            private final long negInv;
            private final long r2;

            private ModArithmeticMontgomery(int mod) {
                super(mod);
                long inv = 0;
                long s = 1, t = 0;
                for (int i = 0; i < 32; i++) {
                    if ((t & 1) == 0) {
                        t += mod;
                        inv += s;
                    }
                    t >>= 1;
                    s <<= 1;
                }
                long r = (1l << 32) % mod;
                this.negInv = inv;
                this.r2 = (r * r) % mod;
            }

            private int generate(long x) {
                return reduce(x * r2);
            }

            private int reduce(long x) {
                x = (x + ((x * negInv) & 0xffff_ffffl) * mod) >>> 32;
                return (int) (x < mod ? x : x - mod);
            }

            @Override
            int remainder(long value) {
                return generate((value %= mod) < 0 ? value + mod : value);
            }

            @Override
            int mul(int a, int b) {
                return reduce((long) a * b);
            }

            @Override
            int inv(int a) {
                return super.inv(reduce(a));
            }

            @Override
            int pow(int a, long b) {
                return generate(super.pow(a, b));
            }
        }

        private static final class ModArithmeticBarrett extends ModArithmeticDynamic {
            private static final long mask = 0xffff_ffffl;
            private final long mh;
            private final long ml;

            private ModArithmeticBarrett(int mod) {
                super(mod);
                long a = (1l << 32) / mod;
                long b = (1l << 32) % mod;
                long m = a * a * mod + 2 * a * b + (b * b) / mod;
                mh = m >>> 32;
                ml = m & mask;
            }

            private int reduce(long x) {
                long z = (x & mask) * ml;
                z = (x & mask) * mh + (x >>> 32) * ml + (z >>> 32);
                z = (x >>> 32) * mh + (z >>> 32);
                x -= z * mod;
                return (int) (x < mod ? x : x - mod);
            }

            @Override
            int remainder(long value) {
                return (int) ((value %= mod) < 0 ? value + mod : value);
            }

            @Override
            int mul(int a, int b) {
                return reduce((long) a * b);
            }
        }

        private static class ModArithmeticDynamic extends ModArithmetic {
            final int mod;

            ModArithmeticDynamic(int mod) {
                this.mod = mod;
            }

            int mod() {
                return mod;
            }

            int remainder(long value) {
                return (int) ((value %= mod) < 0 ? value + mod : value);
            }

            int add(int a, int b) {
                int sum = a + b;
                return sum >= mod ? sum - mod : sum;
            }

            int sub(int a, int b) {
                int sum = a - b;
                return sum < 0 ? sum + mod : sum;
            }

            int mul(int a, int b) {
                return (int) (((long) a * b) % mod);
            }
        }
    }
}

final class ContestInputStream extends FilterInputStream {
    protected final byte[] buf;
    protected int pos = 0;
    protected int lim = 0;
    private final char[] cbuf;

    public ContestInputStream() {
        super(System.in);
        this.buf = new byte[1 << 13];
        this.cbuf = new char[1 << 20];
    }

    boolean hasRemaining() throws IOException {
        if (pos < lim)
            return true;
        lim = in.read(buf);
        pos = 0;
        return lim > 0;
    }

    final int remaining() throws IOException {
        if (pos >= lim) {
            lim = in.read(buf);
            pos = 0;
        }
        return lim - pos;
    }

    @Override
    public final int available() throws IOException {
        if (pos < lim)
            return lim - pos + in.available();
        return in.available();
    }

    @Override
    public final long skip(long n) throws IOException {
        if (pos < lim) {
            int rem = lim - pos;
            if (n < rem) {
                pos += n;
                return n;
            }
            pos = lim;
            return rem;
        }
        return in.skip(n);
    }

    @Override
    public final int read() throws IOException {
        if (hasRemaining())
            return buf[pos++];
        return -1;
    }

    @Override
    public final int read(byte[] b, int off, int len) throws IOException {
        if (pos < lim) {
            int rem = Math.min(lim - pos, len);
            for (int i = 0; i < rem; i++)
                b[off + i] = buf[pos + i];
            pos += rem;
            return rem;
        }
        return in.read(b, off, len);
    }

    public final char[] readToken() throws IOException {
        int cpos = 0;
        int rem;
        byte b;
        l: while ((rem = remaining()) > 0) {
            for (int i = 0; i < rem; i++) {
                b = buf[pos + i];
                if (b <= 0x20) {
                    pos += i + 1;
                    cpos += i;
                    if (b == 0x0d && hasRemaining() && buf[pos] == 0x0a)
                        pos++;
                    break l;
                }
                cbuf[cpos + i] = (char) b;
            }
            pos += rem;
            cpos += rem;
        }
        char[] arr = new char[cpos];
        for (int i = 0; i < cpos; i++)
            arr[i] = cbuf[i];
        return arr;
    }

    public final int readToken(char[] cbuf, int off) throws IOException {
        int cpos = off;
        int rem;
        byte b;
        l: while ((rem = remaining()) > 0) {
            for (int i = 0; i < rem; i++) {
                b = buf[pos + i];
                if (b <= 0x20) {
                    pos += i + 1;
                    cpos += i;
                    if (b == 0x0d && hasRemaining() && buf[pos] == 0x0a)
                        pos++;
                    break l;
                }
                cbuf[cpos + i] = (char) b;
            }
            pos += rem;
            cpos += rem;
        }
        return cpos - off;
    }

    public final int readToken(char[] cbuf) throws IOException {
        return readToken(cbuf, 0);
    }

    public final String next() throws IOException {
        int cpos = 0;
        int rem;
        byte b;
        l: while ((rem = remaining()) > 0) {
            for (int i = 0; i < rem; i++) {
                b = buf[pos + i];
                if (b <= 0x20) {
                    pos += i + 1;
                    cpos += i;
                    if (b == 0x0d && hasRemaining() && buf[pos] == 0x0a)
                        pos++;
                    break l;
                }
                cbuf[cpos + i] = (char) b;
            }
            pos += rem;
            cpos += rem;
        }
        return String.valueOf(cbuf, 0, cpos);
    }

    public final char[] nextCharArray() throws IOException {
        return readToken();
    }

    public final int nextInt() throws IOException {
        if (!hasRemaining())
            return 0;
        int value = 0;
        byte b = buf[pos++];
        if (b == 0x2d) {
            while (hasRemaining() && (b = buf[pos++]) > 0x20)
                value = value * 10 - b + 0x30;
        } else {
            do {
                value = value * 10 + b - 0x30;
            } while (hasRemaining() && (b = buf[pos++]) > 0x20);
        }
        if (b == 0x0d && hasRemaining() && buf[pos] == 0x0a)
            pos++;
        return value;
    }

    public final long nextLong() throws IOException {
        if (!hasRemaining())
            return 0L;
        long value = 0L;
        byte b = buf[pos++];
        if (b == 0x2d) {
            while (hasRemaining() && (b = buf[pos++]) > 0x20)
                value = value * 10 - b + 0x30;
        } else {
            do {
                value = value * 10 + b - 0x30;
            } while (hasRemaining() && (b = buf[pos++]) > 0x20);
        }
        if (b == 0x0d && hasRemaining() && buf[pos] == 0x0a)
            pos++;
        return value;
    }

    public final char nextChar() throws IOException {
        if (!hasRemaining())
            throw new EOFException();
        final char c = (char) buf[pos++];
        if (hasRemaining() && buf[pos++] == 0x0d && hasRemaining() && buf[pos] == 0x0a)
            pos++;
        return c;
    }

    public final float nextFloat() throws IOException {
        return Float.parseFloat(next());
    }

    public final double nextDouble() throws IOException {
        return Double.parseDouble(next());
    }

    public final boolean[] nextBooleanArray(char ok) throws IOException {
        char[] s = readToken();
        int n = s.length;
        boolean[] t = new boolean[n];
        for (int i = 0; i < n; i++)
            t[i] = s[i] == ok;
        return t;
    }

    public final boolean[][] nextBooleanMatrix(int h, int w, char ok) throws IOException {
        boolean[][] s = new boolean[h][];
        for (int i = 0; i < h; i++) {
            char[] t = readToken();
            int n = t.length;
            s[i] = new boolean[n];
            for (int j = 0; j < n; j++)
                s[i][j] = t[j] == ok;
        }
        return s;
    }

    public final String[] nextStringArray(int len) throws IOException {
        String[] arr = new String[len];
        for (int i = 0; i < len; i++)
            arr[i] = next();
        return arr;
    }

    public final int[] nextIntArray(int len) throws IOException {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++)
            arr[i] = nextInt();
        return arr;
    }

    public final int[] nextIntArray(int len, java.util.function.IntUnaryOperator map) throws IOException {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++)
            arr[i] = map.applyAsInt(nextInt());
        return arr;
    }

    public final long[] nextLongArray(int len, java.util.function.LongUnaryOperator map) throws IOException {
        long[] arr = new long[len];
        for (int i = 0; i < len; i++)
            arr[i] = map.applyAsLong(nextLong());
        return arr;
    }

    public final int[][] nextIntMatrix(int h, int w) throws IOException {
        int[][] arr = new int[h][w];
        for (int i = 0; i < h; i++)
            for (int j = 0; j < w; j++)
                arr[i][j] = nextInt();
        return arr;
    }

    public final int[][] nextIntMatrix(int h, int w, java.util.function.IntUnaryOperator map) throws IOException {
        int[][] arr = new int[h][w];
        for (int i = 0; i < h; i++)
            for (int j = 0; j < w; j++)
                arr[i][j] = map.applyAsInt(nextInt());
        return arr;
    }

    public final long[] nextLongArray(int len) throws IOException {
        long[] arr = new long[len];
        for (int i = 0; i < len; i++)
            arr[i] = nextLong();
        return arr;
    }

    public final long[][] nextLongMatrix(int h, int w) throws IOException {
        long[][] arr = new long[h][w];
        for (int i = 0; i < h; i++)
            for (int j = 0; j < w; j++)
                arr[i][j] = nextLong();
        return arr;
    }

    public final float[] nextFloatArray(int len) throws IOException {
        float[] arr = new float[len];
        for (int i = 0; i < len; i++)
            arr[i] = nextFloat();
        return arr;
    }

    public final double[] nextDoubleArray(int len) throws IOException {
        double[] arr = new double[len];
        for (int i = 0; i < len; i++)
            arr[i] = nextDouble();
        return arr;
    }

    public final char[][] nextCharMatrix(int h, int w) throws IOException {
        char[][] arr = new char[h][];
        for (int i = 0; i < h; i++)
            arr[i] = readToken();
        return arr;
    }

    public final void nextThrow() throws IOException {
        next();
        return;
    }

    public final void nextThrow(int n) throws IOException {
        for (int i = 0; i < n; i++)
            nextThrow();
        return;
    }
}

final class ContestOutputStream extends FilterOutputStream implements Appendable {
    protected final byte[] buf;
    protected int pos = 0;

    public ContestOutputStream() {
        super(System.out);
        this.buf = new byte[1 << 13];
    }

    @Override
    public void flush() throws IOException {
        out.write(buf, 0, pos);
        pos = 0;
        out.flush();
    }

    void put(byte b) throws IOException {
        if (pos >= buf.length)
            flush();
        buf[pos++] = b;
    }

    int remaining() throws IOException {
        if (pos >= buf.length)
            flush();
        return buf.length - pos;
    }

    @Override
    public void write(int b) throws IOException {
        put((byte) b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        int o = off;
        int l = len;
        while (l > 0) {
            int rem = Math.min(remaining(), l);
            for (int i = 0; i < rem; i++)
                buf[pos + i] = b[o + i];
            pos += rem;
            o += rem;
            l -= rem;
        }
    }

    @Override
    public ContestOutputStream append(char c) throws IOException {
        put((byte) c);
        return this;
    }

    @Override
    public ContestOutputStream append(CharSequence csq, int start, int end) throws IOException {
        int off = start;
        int len = end - start;
        while (len > 0) {
            int rem = Math.min(remaining(), len);
            for (int i = 0; i < rem; i++)
                buf[pos + i] = (byte) csq.charAt(off + i);
            pos += rem;
            off += rem;
            len -= rem;
        }
        return this;
    }

    @Override
    public ContestOutputStream append(CharSequence csq) throws IOException {
        return append(csq, 0, csq.length());
    }

    public ContestOutputStream append(char[] arr, int off, int len) throws IOException {
        int o = off;
        int l = len;
        while (l > 0) {
            int rem = Math.min(remaining(), l);
            for (int i = 0; i < rem; i++)
                buf[pos + i] = (byte) arr[o + i];
            pos += rem;
            o += rem;
            l -= rem;
        }
        return this;
    }

    public ContestOutputStream print(char[] arr) throws IOException {
        return append(arr, 0, arr.length).newLine();
    }

    public ContestOutputStream print(boolean value) throws IOException {
        if (value)
            return append("o");
        return append("x");
    }

    public ContestOutputStream println(boolean value) throws IOException {
        if (value)
            return append("o\n");
        return append("x\n");
    }

    public ContestOutputStream print(boolean[][] value) throws IOException {
        final int n = value.length, m = value[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++)
                print(value[i][j]);
            newLine();
        }
        return this;
    }

    public ContestOutputStream print(int value) throws IOException {
        return append(String.valueOf(value));
    }

    public ContestOutputStream println(int value) throws IOException {
        return append(String.valueOf(value)).newLine();
    }

    public ContestOutputStream print(long value) throws IOException {
        return append(String.valueOf(value));
    }

    public ContestOutputStream println(long value) throws IOException {
        return append(String.valueOf(value)).newLine();
    }

    public ContestOutputStream print(float value) throws IOException {
        return append(String.valueOf(value));
    }

    public ContestOutputStream println(float value) throws IOException {
        return append(String.valueOf(value)).newLine();
    }

    private ContestOutputStream dtos(double x, int n) throws IOException {
        if (x < 0) {
            append('-');
            x = -x;
        }
        x += Math.pow(10, -n) / 2;
        long longx = (long) x;
        print(longx);
        append('.');
        x -= longx;
        for (int i = 0; i < n; i++) {
            x *= 10;
            int intx = (int) x;
            print(intx);
            x -= intx;
        }
        return this;
    }

    public ContestOutputStream print(double value) throws IOException {
        return dtos(value, 20);
    }

    public ContestOutputStream println(double value) throws IOException {
        return dtos(value, 20).newLine();
    }

    public ContestOutputStream print(char value) throws IOException {
        return append(value);
    }

    public ContestOutputStream println(char value) throws IOException {
        return append(value).newLine();
    }

    public ContestOutputStream print(String value) throws IOException {
        return append(value);
    }

    public ContestOutputStream println(String value) throws IOException {
        return append(String.valueOf(value)).newLine();
    }

    public ContestOutputStream print(Object value) throws IOException {
        return append(String.valueOf(value));
    }

    public ContestOutputStream println(Object value) throws IOException {
        return append(String.valueOf(value)).newLine();
    }

    public ContestOutputStream printYN(boolean yes) throws IOException {
        if (yes)
            return println("Yes");
        return println("No");
    }

    public ContestOutputStream printAB(boolean yes) throws IOException {
        if (yes)
            return println("Alice");
        return println("Bob");
    }

    public ContestOutputStream print(CharSequence[] arr) throws IOException {
        if (arr.length > 0) {
            append(arr[0]);
            for (int i = 1; i < arr.length; i++)
                append('\u0020').append(arr[i]);
        }
        return this;
    }

    public ContestOutputStream print(int[] arr) throws IOException {
        if (arr.length > 0) {
            print(arr[0]);
            for (int i = 1; i < arr.length; i++)
                append('\u0020').print(arr[i]);
        }
        newLine();
        return this;
    }

    public ContestOutputStream print(int[] arr, int length) throws IOException {
        if (length > 0)
            print(arr[0]);
        for (int i = 1; i < length; i++)
            append('\u0020').print(arr[i]);
        newLine();
        return this;
    }

    public ContestOutputStream println(int[] arr) throws IOException {
        for (int i : arr)
            print(i).newLine();
        return this;
    }

    public ContestOutputStream println(int[] arr, int length) throws IOException {
        for (int i = 0; i < length; i++)
            println(arr[i]);
        return this;
    }

    public ContestOutputStream print(boolean[] arr) throws IOException {
        if (arr.length > 0) {
            print(arr[0]);
            for (int i = 1; i < arr.length; i++)
                append('\u0020').print(arr[i]);
        }
        newLine();
        return this;
    }

    public ContestOutputStream print(long[] arr) throws IOException {
        if (arr.length > 0) {
            print(arr[0]);
            for (int i = 1; i < arr.length; i++)
                append('\u0020').print(arr[i]);
        }
        newLine();
        return this;
    }

    public ContestOutputStream print(long[] arr, int length) throws IOException {
        if (length > 0)
            print(arr[0]);
        for (int i = 1; i < length; i++)
            append('\u0020').print(arr[i]);
        newLine();
        return this;
    }

    public ContestOutputStream println(long[] arr, int length) throws IOException {
        for (int i = 0; i < length; i++)
            println(arr[i]);
        return this;
    }

    public ContestOutputStream println(long[] arr) throws IOException {
        for (long i : arr)
            print(i).newLine();
        return this;
    }

    public ContestOutputStream print(float[] arr) throws IOException {
        if (arr.length > 0) {
            print(arr[0]);
            for (int i = 1; i < arr.length; i++)
                append('\u0020').print(arr[i]);
        }
        return this;
    }

    public ContestOutputStream println(float[] arr) throws IOException {
        for (float i : arr)
            print(i).newLine();
        return this;
    }

    public ContestOutputStream print(double[] arr) throws IOException {
        if (arr.length > 0) {
            print(arr[0]);
            for (int i = 1; i < arr.length; i++)
                append('\u0020').print(arr[i]);
        }
        return newLine();
    }

    public ContestOutputStream println(double[] arr) throws IOException {
        for (double i : arr)
            print(i).newLine();
        return this;
    }

    public ContestOutputStream print(java.util.ArrayList<?> arr) throws IOException {
        if (!arr.isEmpty()) {
            final int n = arr.size();
            print(arr.get(0));
            for (int i = 1; i < n; i++)
                print(" ").print(arr.get(i));
        }
        return newLine();
    }

    public ContestOutputStream println(java.util.ArrayList<?> arr) throws IOException {
        final int n = arr.size();
        for (int i = 0; i < n; i++)
            println(arr.get(i));
        return this;
    }

    public ContestOutputStream newLine() throws IOException {
        return append(System.lineSeparator());
    }

    public ContestOutputStream endl() throws IOException {
        newLine().flush();
        return this;
    }

    public ContestOutputStream print(int[][] arr) throws IOException {
        for (int[] i : arr)
            print(i);
        return this;
    }

    public ContestOutputStream print(long[][] arr) throws IOException {
        for (long[] i : arr)
            print(i);
        return this;
    }

    public ContestOutputStream print(char[][] arr) throws IOException {
        for (char[] i : arr)
            print(i);
        return this;
    }

    public ContestOutputStream print(Object[][] arr) throws IOException {
        for (Object[] i : arr)
            print(i);
        return this;
    }

    public ContestOutputStream println() throws IOException {
        return newLine();
    }

    public ContestOutputStream print(Object... arr) throws IOException {
        for (Object i : arr)
            print(i);
        return this;
    }

    public ContestOutputStream println(Object... arr) throws IOException {
        for (Object i : arr)
            print(i);
        return newLine();
    }
}