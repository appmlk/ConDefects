import java.util.*;
import java.io.*;

class Main {
    private static final void solve() throws IOException {
        final int n = ni(), nn = n * n, n2 = n << 1;
        fac = new long[n2 + 1];
        fin = new long[n2 + 1];
        fac[0] = 1L;
        for (int i = 1; i <= n2; i++)
            fac[i] = i * fac[i - 1] % mod;
        fin[n2] = p(fac[n2], mod - 2);
        for (int i = n2; i > 0; i--)
            fin[i - 1] = i * fin[i] % mod;
        var g = new ArrayList<ArrayList<Point>>(nn);
        for (int i = 0; i < nn; i++)
            g.add(new ArrayList<>());
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                g.get(ni() - 1).add(new Point(i, i + j));
        g.removeIf(ArrayList::isEmpty);
        long ans = 0L;
        for (var gg : g) {
            var d = new long[n];
            Collections.sort(gg, Comparator.comparingInt(i -> i.y));
            for (var p : gg) {
                final int ai = p.x, bi = p.y;
                for (int k = 0; k + ai < n; k++) {
                    long mul = com(bi - 1 + k, k);
                    if ((k & 1) == 1)
                        mul = mod - mul;
                    d[k + ai] += mul;
                    d[k + ai] %= mod;
                }
                for (int k = 0; k <= ai; k++)
                    ans += com(bi, k) * d[ai - k] % mod;
            }
        }
        ou.println(ans % mod);
    }

    private static final long com(int n, int k) {
        if (k == 0)
            return 1L;
        if (n < 0 || k < 0 || n - k < 0)
            return 0L;
        return fin[k] * fin[n - k] % mod * fac[n] % mod;
    }

    private static final long p(long a, long b) {
        if (b == 0)
            return 1;
        if ((b & 1) == 1)
            return a * p(a, b - 1) % mod;
        return p(a * a % mod, b >> 1);
    }

    private static long[] fac, fin;
    private static final long mod = 998244353L;

    private static final class Point {
        final int x, y;

        Point(int xx, int yy) {
            x = xx;
            y = yy;
        }
    }

    public static void main(String[] args) throws IOException {
        // for (int i = 0, t = ni(); i < t; i++)
        solve();
        ou.flush();
    }

    private static final int ni() throws IOException {
        return sc.nextInt();
    }

    private static final int[] ni(int n) throws IOException {
        return sc.nextIntArray(n);
    }

    private static final int[] ni1(int n) throws IOException {
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = ni() - 1;
        return a;
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

    private static final char[] nc() throws IOException {
        return sc.nextCharArray();
    }

    private static final int[] nc1() throws IOException {
        char[] c = nc();
        int n = c.length;
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = c[i] - '0';
        return a;
    }

    private static final int[] nca() throws IOException {
        char[] c = nc();
        int n = c.length;
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = c[i] - 'a';
        return a;
    }

    private static final double nd() throws IOException {
        return sc.nextDouble();
    }

    private static final ContestInputStream sc = new ContestInputStream();
    private static final ContestOutputStream ou = new ContestOutputStream();
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
                value = (value << 3) + (value << 1) - b + 0x30;
        } else {
            do {
                value = (value << 3) + (value << 1) + b - 0x30;
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
                value = (value << 3) + (value << 1) - b + 0x30;
        } else {
            do {
                value = (value << 3) + (value << 1) + b - 0x30;
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
        return append(".");
    }

    public ContestOutputStream println(boolean value) throws IOException {
        if (value)
            return append("o\n");
        return append(".\n");
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

    public ContestOutputStream print(Object[] arr) throws IOException {
        if (arr.length > 0) {
            print(arr[0]);
            for (int i = 1; i < arr.length; i++)
                append('\u0020').print(arr[i]);
        }
        return newLine();
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

    public ContestOutputStream println(Object... arr) throws IOException {
        for (Object i : arr)
            print(i);
        return newLine();
    }

    public ContestOutputStream printToChar(int c) throws IOException {
        return print((char) c);
    }
}