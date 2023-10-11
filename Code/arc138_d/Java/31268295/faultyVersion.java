import java.util.*;
import java.io.*;

class Main {
    private static final void solve() throws IOException {
        final int n = ni(), nn = 1 << n, k = ni();
        if (n == 1) {
            ou.println("Yes").println(1);
            return;
        }
        if (k % 2 == 0 || n == k) {
            ou.printYN(false);
            return;
        }
        var a = new boolean[nn];
        a[0] = true;
        var t = new int[n];
        int c = 0;
        for (int i = 1; i < nn; i++) {
            if (a[i])
                continue;
            if (Integer.bitCount(i) != k)
                continue;
            t[c++] = i;
            for (int j = 0; j < nn; j++)
                a[j ^ i] |= a[j];
        }
        ou.printYN(true);
        var aa = new int[nn];
        var tt = new ArrayList<Boolean>();
        int cnt = 1;
        for (int i = 0; i < n; i++) {
            tt.clear();
            for (int j = 0; j < cnt; j++)
                tt.add(false);
            for (int j = 0; j < cnt; j++)
                tt.add(true);
            for (int j = 0; j < cnt; j++)
                tt.add(true);
            for (int j = 0; j < cnt; j++)
                tt.add(false);
            for (int j = 0; j < nn; j++)
                if (tt.get(j % tt.size()))
                    aa[j] |= 1 << i;
            cnt <<= 1;
        }
        for (int i : aa) {
            int ans = 0;
            for (int j = 0; j < n; j++) {
                if ((i & 1) == 1)
                    ans ^= t[j];
                i >>= 1;
            }
            ou.print(ans).print(" ");
        }
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
        return sc.nextString();
    }

    private static final ContestInputStream sc = new ContestInputStream();
    private static final ContestOutputStream ou = new ContestOutputStream();
}

class ContestInputStream extends FilterInputStream {
    protected final byte[] buf;
    protected int pos = 0;
    protected int lim = 0;
    private final char[] cbuf;

    public ContestInputStream() {
        super(System.in);
        this.buf = new byte[1 << 13];
        this.cbuf = new char[1 << 19];
    }

    boolean hasRemaining() throws IOException {
        if (pos < lim)
            return true;
        lim = in.read(buf);
        pos = 0;
        return lim > 0;
    }

    int remaining() throws IOException {
        if (pos >= lim) {
            lim = in.read(buf);
            pos = 0;
        }
        return lim - pos;
    }

    @Override
    public int available() throws IOException {
        if (pos < lim)
            return lim - pos + in.available();
        return in.available();
    }

    @Override
    public long skip(long n) throws IOException {
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
    public int read() throws IOException {
        if (hasRemaining())
            return buf[pos++];
        return -1;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        if (pos < lim) {
            int rem = Math.min(lim - pos, len);
            for (int i = 0; i < rem; i++)
                b[off + i] = buf[pos + i];
            pos += rem;
            return rem;
        }
        return in.read(b, off, len);
    }

    public char[] readToken() throws IOException {
        int cpos = 0;
        int rem;
        int i;
        byte b;
        l: while ((rem = remaining()) > 0) {
            for (i = 0; i < rem; i++) {
                b = buf[pos + i];
                if (b <= 0x20) {
                    pos += i + 1;
                    cpos += i;
                    break l;
                }
                cbuf[cpos + i] = (char) b;
            }
            pos += rem;
            cpos += rem;
        }
        char[] arr = new char[cpos];
        for (i = 0; i < cpos; i++)
            arr[i] = cbuf[i];
        return arr;
    }

    public int readToken(char[] cbuf, int off) throws IOException {
        int cpos = off;
        int rem;
        int i;
        byte b;
        l: while ((rem = remaining()) > 0) {
            for (i = 0; i < rem; i++) {
                b = buf[pos + i];
                if (b <= 0x20) {
                    pos += i + 1;
                    cpos += i;
                    break l;
                }
                cbuf[cpos + i] = (char) b;
            }
            pos += rem;
            cpos += rem;
        }
        return cpos - off;
    }

    public int readToken(char[] cbuf) throws IOException {
        return readToken(cbuf, 0);
    }

    public String nextString() throws IOException {
        int cpos = 0;
        int rem;
        int i;
        byte b;
        l: while ((rem = remaining()) > 0) {
            for (i = 0; i < rem; i++) {
                b = buf[pos + i];
                if (b <= 0x20) {
                    pos += i + 1;
                    cpos += i;
                    break l;
                }
                cbuf[cpos + i] = (char) b;
            }
            pos += rem;
            cpos += rem;
        }
        return String.valueOf(cbuf, 0, cpos);
    }

    public int nextInt() throws IOException {
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
        return value;
    }

    public long nextLong() throws IOException {
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
        return value;
    }

    public float nextFloat() throws IOException {
        return Float.parseFloat(nextString());
    }

    public double nextDouble() throws IOException {
        return Double.parseDouble(nextString());
    }

    public boolean[] nextBooleanArray(char ok) throws IOException {
        char[] s = nextString().toCharArray();
        int n = s.length;
        boolean[] t = new boolean[n];
        for (int i = 0; i < n; i++)
            t[i] = s[i] == ok;
        return t;
    }

    public String[] nextStringArray(int len) throws IOException {
        String[] arr = new String[len];
        for (int i = 0; i < len; i++)
            arr[i] = nextString();
        return arr;
    }

    public int[] nextIntArray(int len) throws IOException {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++)
            arr[i] = nextInt();
        return arr;
    }

    public int[] nextIntArray(int len, java.util.function.IntUnaryOperator map) throws IOException {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++)
            arr[i] = map.applyAsInt(nextInt());
        return arr;
    }

    public long[] nextLongArray(int len, java.util.function.LongUnaryOperator map) throws IOException {
        long[] arr = new long[len];
        for (int i = 0; i < len; i++)
            arr[i] = map.applyAsLong(nextLong());
        return arr;
    }

    public int[][] nextIntMatrix(int h, int w) throws IOException {
        int[][] arr = new int[h][w];
        for (int i = 0; i < h; i++)
            for (int j = 0; j < w; j++)
                arr[i][j] = nextInt();
        return arr;
    }

    public long[] nextLongArray(int len) throws IOException {
        long[] arr = new long[len];
        for (int i = 0; i < len; i++)
            arr[i] = nextLong();
        return arr;
    }

    public long[][] nextLongMatrix(int h, int w) throws IOException {
        long[][] arr = new long[h][w];
        for (int i = 0; i < h; i++)
            for (int j = 0; j < w; j++)
                arr[i][j] = nextLong();
        return arr;
    }

    public float[] nextFloatArray(int len) throws IOException {
        float[] arr = new float[len];
        for (int i = 0; i < len; i++)
            arr[i] = nextFloat();
        return arr;
    }

    public double[] nextDoubleArray(int len) throws IOException {
        double[] arr = new double[len];
        for (int i = 0; i < len; i++)
            arr[i] = nextDouble();
        return arr;
    }

    public char[][] nextCharMatrix(int h, int w) throws IOException {
        char[][] arr = new char[h][];
        for (int i = 0; i < h; i++)
            arr[i] = nextString().toCharArray();
        return arr;
    }
}

class ContestOutputStream extends FilterOutputStream implements Appendable {
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

    public ContestOutputStream print(double value) throws IOException {
        return append(String.valueOf(value));
    }

    public ContestOutputStream println(double value) throws IOException {
        return append(String.valueOf(value)).newLine();
    }

    public ContestOutputStream print(char value) throws IOException {
        return append(String.valueOf(value));
    }

    public ContestOutputStream println(char value) throws IOException {
        return append(String.valueOf(value)).newLine();
    }

    public ContestOutputStream print(String value) throws IOException {
        return append(String.valueOf(value));
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

    public ContestOutputStream println(int[] arr) throws IOException {
        for (int i : arr)
            print(i).newLine();
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
        return this;
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
        return this;
    }

    public ContestOutputStream print(java.util.ArrayList<?> arr) throws IOException {
        final int n = arr.size();
        print(arr.get(0));
        for (int i = 1; i < n; i++)
            print(" ").print(arr.get(i));
        newLine();
        return this;
    }

    public ContestOutputStream println(java.util.ArrayList<?> arr) throws IOException {
        final int n = arr.size();
        for (int i = 0; i < n; i++)
            println(arr.get(i));
        return this;
    }

    public ContestOutputStream println(Object[] arr) throws IOException {
        for (Object i : arr)
            print(i).newLine();
        return this;
    }

    public ContestOutputStream newLine() throws IOException {
        return append('\n');
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
}