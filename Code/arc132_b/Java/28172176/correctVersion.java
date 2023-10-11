import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Main {
    static long MOD = 998244353l;
    int min = Integer.MAX_VALUE;
    boolean re = false;

    public static void main(String[] args) throws Exception {
        // FileInputStream fis = new FileInputStream(new
        // File("/Users/goudezhao/Public/JavaTest/1.txt"));
        var sc = new FastScanner();
        // var sc = new FastScanner(fis);
        // var pw = new FastPrintStream("/Users/goudezhao/Public/JavaTest/1.out");
        var pw = new FastPrintStream();
        solve(sc, pw);
        sc.close();
        pw.flush();
        pw.close();
    }

    public static void solve(FastScanner sc, FastPrintStream pw) {
        int n = sc.nextInt();
        int a[] = new int[n];
        Arrays.setAll(a, i->sc.nextInt());
        int index=0;
        for (int i=0;i<n;i++) {
            if (a[i]==n) {
                index= i;
                break;
            }
        }
        
        if (index==0) {
            pw.println(1);
            return;
        }
        if (a[index-1]==1) {
            if (n==2) {
                pw.println(0);
                return;
            }
            pw.println(Math.min(index+1, 1+(n-index)));
            return;
        }
        if (index==n-1) {
            pw.println(0);
            return;
        }
        pw.println(Math.min(index+1, 2+(n-index-1)));
    }

    public static void swap(int[] s, int i, int j) {
        int tmp = s[i];
        s[i] = s[j];
        s[j] = tmp;
    }

    public void permutation(int[] s, int from, int to, Point a[], Point b[]) {
        if (to <= 1)
            return;
        if (from == to) {
            check(s, a, b);
        } else {
            for (int i = from; i <= to; i++) {
                swap(s, i, from); // 交换前缀，使其产生下一个前缀
                permutation(s, from + 1, to, a, b);
                swap(s, from, i); // 将前缀换回，继续做上一个前缀的排列
            }
        }
    }

    public void check(int[] s, Point a[], Point b[]) {
        Point tempa[] = new Point[a.length];
        Point tempb[] = new Point[b.length];
        for (int i = 0; i < a.length; i++) {
            tempa[i] = new Point();
            tempa[i].x = Math.min(a[i].x, a[i].y);
            tempa[i].y = Math.max(a[i].x, a[i].y);
        }
        for (int i = 0; i < a.length; i++) {
            tempb[i] = new Point();
            tempb[i].x = Math.min(s[b[i].x - 1], s[b[i].y - 1]);
            tempb[i].y = Math.max(s[b[i].x - 1], s[b[i].y - 1]);
        }
        Arrays.sort(tempa);
        Arrays.sort(tempb);
        for (int i = 0; i < a.length; i++) {
            if (tempa[i].x != tempb[i].x || tempa[i].y != tempb[i].y) {
                return;
            }
        }
        this.re = true;
    }

    public static long anothertoTen(long ano, int another) {
        long ten = 0;
        long now = 1;
        long temp = ano;
        while (temp > 0) {
            long i = temp % 10;
            ten += now * i;
            now *= another;
            temp /= 10;
        }
        return ten;
    }

    public static long tentoAnother(long ten, int another) {
        Stack<Long> stack = new Stack<Long>();
        while (ten > 0) {
            stack.add(ten % another);
            ten /= another;
        }
        long re = 0;
        while (!stack.isEmpty()) {
            long pop = stack.pop();
            re = re * 10 + pop;
        }
        return re;
    }

    // 2C5 = 5*4/(2*1)
    public static long XCY(long x, long y) {
        long temp = 1;
        for (int i = 0; i < x; i++) {
            temp = (temp * (y - i)) % MOD;
        }
        long tempx = 1;
        for (int i = 2; i <= x; i++) {
            tempx = (tempx * i) % MOD;
        }
        tempx = modpow(tempx, (long) MOD - 2);
        temp = (temp * tempx) % MOD;
        return temp;
    }

    static long modpow(long N, Long K) {
        return BigInteger.valueOf(N).modPow(BigInteger.valueOf(K), BigInteger.valueOf(MOD)).longValue();
    }

    static long modpow(long N, Long K, long mod) {
        return BigInteger.valueOf(N).modPow(BigInteger.valueOf(K), BigInteger.valueOf(mod)).longValue();
    }

    public static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        if (a < b) {
            return gcd(b, a);
        }
        return gcd(b, a % b);
    }

    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        if (a < b) {
            return gcd(b, a);
        }
        return gcd(b, a % b);
    }

}

class Range {
    long start = 0;
    long end = 0;
}

class Point implements Comparable {
    int x;
    int y;

    public int compareTo(Object p) {
        Point t = (Point) p;
        if (t.x != this.x) {
            return t.x - this.x;
        }
        return t.y - this.y;
    }
}

class PointY implements Comparable {
    int index;
    long a;
    long b;

    public int compareTo(Object p) {
        PointY t = (PointY) p;
        if (this.a > t.a) {
            return -1;
        }
        if (this.a < t.a) {
            return 1;
        }
        return 0;
    }
}

class FastPrintStream implements AutoCloseable {
    private static final int BUF_SIZE = 1 << 15;
    private final byte[] buf = new byte[BUF_SIZE];
    private int ptr = 0;
    private final java.lang.reflect.Field strField;
    private final java.nio.charset.CharsetEncoder encoder;

    private java.io.OutputStream out;

    public FastPrintStream(java.io.OutputStream out) {
        this.out = out;
        java.lang.reflect.Field f;
        try {
            f = java.lang.String.class.getDeclaredField("value");
            f.setAccessible(true);
        } catch (NoSuchFieldException | SecurityException e) {
            f = null;
        }
        this.strField = f;
        this.encoder = java.nio.charset.StandardCharsets.US_ASCII.newEncoder();
    }

    public FastPrintStream(java.io.File file) throws java.io.IOException {
        this(new java.io.FileOutputStream(file));
    }

    public FastPrintStream(java.lang.String filename) throws java.io.IOException {
        this(new java.io.File(filename));
    }

    public FastPrintStream() {
        this(System.out);
        try {
            java.lang.reflect.Field f = java.io.PrintStream.class.getDeclaredField("autoFlush");
            f.setAccessible(true);
            f.set(System.out, false);
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException e) {
            // ignore
        }
    }

    public FastPrintStream println() {
        if (ptr == BUF_SIZE)
            internalFlush();
        buf[ptr++] = (byte) '\n';
        return this;
    }

    public FastPrintStream println(java.lang.Object o) {
        return print(o).println();
    }

    public FastPrintStream println(java.lang.String s) {
        return print(s).println();
    }

    public FastPrintStream println(char[] s) {
        return print(s).println();
    }

    public FastPrintStream println(char c) {
        return print(c).println();
    }

    public FastPrintStream println(int x) {
        return print(x).println();
    }

    public FastPrintStream println(long x) {
        return print(x).println();
    }

    public FastPrintStream println(double d, int precision) {
        return print(d, precision).println();
    }

    private FastPrintStream print(byte[] bytes) {
        int n = bytes.length;
        if (ptr + n > BUF_SIZE) {
            internalFlush();
            try {
                out.write(bytes);
            } catch (java.io.IOException e) {
                throw new RuntimeException();
            }
        } else {
            System.arraycopy(bytes, 0, buf, ptr, n);
            ptr += n;
        }
        return this;
    }

    public FastPrintStream print(java.lang.Object o) {
        return print(o.toString());
    }

    public FastPrintStream print(java.lang.String s) {
        if (strField == null) {
            return print(s.getBytes());
        } else {
            try {
                return print((byte[]) strField.get(s));
            } catch (IllegalAccessException e) {
                return print(s.getBytes());
            }
        }
    }

    public FastPrintStream print(char[] s) {
        try {
            return print(encoder.encode(java.nio.CharBuffer.wrap(s)).array());
        } catch (java.nio.charset.CharacterCodingException e) {
            byte[] bytes = new byte[s.length];
            for (int i = 0; i < s.length; i++) {
                bytes[i] = (byte) s[i];
            }
            return print(bytes);
        }
    }

    public FastPrintStream print(char c) {
        if (ptr == BUF_SIZE)
            internalFlush();
        buf[ptr++] = (byte) c;
        return this;
    }

    public FastPrintStream print(int x) {
        if (x == 0) {
            if (ptr == BUF_SIZE)
                internalFlush();
            buf[ptr++] = '0';
            return this;
        }
        int d = len(x);
        if (ptr + d > BUF_SIZE)
            internalFlush();
        if (x < 0) {
            buf[ptr++] = '-';
            x = -x;
            d--;
        }
        int j = ptr += d;
        while (x > 0) {
            buf[--j] = (byte) ('0' + (x % 10));
            x /= 10;
        }
        return this;
    }

    public FastPrintStream print(long x) {
        if (x == 0) {
            if (ptr == BUF_SIZE)
                internalFlush();
            buf[ptr++] = '0';
            return this;
        }
        int d = len(x);
        if (ptr + d > BUF_SIZE)
            internalFlush();
        if (x < 0) {
            buf[ptr++] = '-';
            x = -x;
            d--;
        }
        int j = ptr += d;
        while (x > 0) {
            buf[--j] = (byte) ('0' + (x % 10));
            x /= 10;
        }
        return this;
    }

    public FastPrintStream print(double d, int precision) {
        if (d < 0) {
            print('-');
            d = -d;
        }
        d += Math.pow(10, -d) / 2;
        print((long) d).print('.');
        d -= (long) d;
        for (int i = 0; i < precision; i++) {
            d *= 10;
            print((int) d);
            d -= (int) d;
        }
        return this;
    }

    private void internalFlush() {
        try {
            out.write(buf, 0, ptr);
            ptr = 0;
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void flush() {
        try {
            out.write(buf, 0, ptr);
            out.flush();
            ptr = 0;
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        try {
            out.close();
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int len(int x) {
        int d = 1;
        if (x >= 0) {
            d = 0;
            x = -x;
        }
        int p = -10;
        for (int i = 1; i < 10; i++, p *= 10)
            if (x > p)
                return i + d;
        return 10 + d;
    }

    private static int len(long x) {
        int d = 1;
        if (x >= 0) {
            d = 0;
            x = -x;
        }
        long p = -10;
        for (int i = 1; i < 19; i++, p *= 10)
            if (x > p)
                return i + d;
        return 19 + d;
    }
}

class FastScanner implements AutoCloseable {
    private final java.io.InputStream in;
    private final byte[] buf = new byte[2048];
    private int ptr = 0;
    private int buflen = 0;

    public FastScanner(java.io.InputStream in) {
        this.in = in;
    }

    public FastScanner() {
        this(System.in);
    }

    private boolean hasNextByte() {
        if (ptr < buflen)
            return true;
        ptr = 0;
        try {
            buflen = in.read(buf);
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
        return buflen > 0;
    }

    private int readByte() {
        return hasNextByte() ? buf[ptr++] : -1;
    }

    public boolean hasNext() {
        while (hasNextByte() && !(32 < buf[ptr] && buf[ptr] < 127))
            ptr++;
        return hasNextByte();
    }

    private StringBuilder nextSequence() {
        if (!hasNext())
            throw new java.util.NoSuchElementException();
        StringBuilder sb = new StringBuilder();
        for (int b = readByte(); 32 < b && b < 127; b = readByte()) {
            sb.appendCodePoint(b);
        }
        return sb;
    }

    public String next() {
        return nextSequence().toString();
    }

    public String next(int len) {
        return new String(nextChars(len));
    }

    public char nextChar() {
        if (!hasNextByte())
            throw new java.util.NoSuchElementException();
        return (char) readByte();
    }

    public char[] nextChars() {
        StringBuilder sb = nextSequence();
        int l = sb.length();
        char[] dst = new char[l];
        sb.getChars(0, l, dst, 0);
        return dst;
    }

    public char[] nextChars(int len) {
        if (!hasNext())
            throw new java.util.NoSuchElementException();
        char[] s = new char[len];
        int i = 0;
        int b = readByte();
        while (32 < b && b < 127 && i < len) {
            s[i++] = (char) b;
            b = readByte();
        }
        if (i != len) {
            throw new java.util.NoSuchElementException(
                    String.format("Next token has smaller length than expected.", len));
        }
        return s;
    }

    public long nextLong() {
        if (!hasNext())
            throw new java.util.NoSuchElementException();
        long n = 0;
        boolean minus = false;
        int b = readByte();
        if (b == '-') {
            minus = true;
            b = readByte();
        }
        if (b < '0' || '9' < b)
            throw new NumberFormatException();
        while (true) {
            if ('0' <= b && b <= '9') {
                n = n * 10 + b - '0';
            } else if (b == -1 || !(32 < b && b < 127)) {
                return minus ? -n : n;
            } else
                throw new NumberFormatException();
            b = readByte();
        }
    }

    public int nextInt() {
        return Math.toIntExact(nextLong());
    }

    public double nextDouble() {
        return Double.parseDouble(next());
    }

    public void close() {
        try {
            in.close();
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }

}

/**
 * @verified https://atcoder.jp/contests/practice2/tasks/practice2_j
 */
class SegTree<S> {
    final int MAX;

    final int N;
    final java.util.function.BinaryOperator<S> op;
    final S E;

    final S[] data;

    @SuppressWarnings("unchecked")
    public SegTree(int n, java.util.function.BinaryOperator<S> op, S e) {
        this.MAX = n;
        int k = 1;
        while (k < n)
            k <<= 1;
        this.N = k;
        this.E = e;
        this.op = op;
        this.data = (S[]) new Object[N << 1];
        java.util.Arrays.fill(data, E);
    }

    public SegTree(S[] dat, java.util.function.BinaryOperator<S> op, S e) {
        this(dat.length, op, e);
        build(dat);
    }

    private void build(S[] dat) {
        int l = dat.length;
        System.arraycopy(dat, 0, data, N, l);
        for (int i = N - 1; i > 0; i--) {
            data[i] = op.apply(data[i << 1 | 0], data[i << 1 | 1]);
        }
    }

    public void set(int p, S x) {
        exclusiveRangeCheck(p);
        data[p += N] = x;
        p >>= 1;
        while (p > 0) {
            data[p] = op.apply(data[p << 1 | 0], data[p << 1 | 1]);
            p >>= 1;
        }
    }

    public S get(int p) {
        exclusiveRangeCheck(p);
        return data[p + N];
    }

    public S prod(int l, int r) {
        if (l > r) {
            throw new IllegalArgumentException(String.format("Invalid range: [%d, %d)", l, r));
        }
        inclusiveRangeCheck(l);
        inclusiveRangeCheck(r);
        S sumLeft = E;
        S sumRight = E;
        l += N;
        r += N;
        while (l < r) {
            if ((l & 1) == 1)
                sumLeft = op.apply(sumLeft, data[l++]);
            if ((r & 1) == 1)
                sumRight = op.apply(data[--r], sumRight);
            l >>= 1;
            r >>= 1;
        }
        return op.apply(sumLeft, sumRight);
    }

    public S allProd() {
        return data[1];
    }

    public int maxRight(int l, java.util.function.Predicate<S> f) {
        inclusiveRangeCheck(l);
        if (!f.test(E)) {
            throw new IllegalArgumentException("Identity element must satisfy the condition.");
        }
        if (l == MAX)
            return MAX;
        l += N;
        S sum = E;
        do {
            l >>= Long.numberOfTrailingZeros(l);
            if (!f.test(op.apply(sum, data[l]))) {
                while (l < N) {
                    l = l << 1;
                    if (f.test(op.apply(sum, data[l]))) {
                        sum = op.apply(sum, data[l]);
                        l++;
                    }
                }
                return l - N;
            }
            sum = op.apply(sum, data[l]);
            l++;
        } while ((l & -l) != l);
        return MAX;
    }

    public int minLeft(int r, java.util.function.Predicate<S> f) {
        inclusiveRangeCheck(r);
        if (!f.test(E)) {
            throw new IllegalArgumentException("Identity element must satisfy the condition.");
        }
        if (r == 0)
            return 0;
        r += N;
        S sum = E;
        do {
            r--;
            while (r > 1 && (r & 1) == 1)
                r >>= 1;
            if (!f.test(op.apply(data[r], sum))) {
                while (r < N) {
                    r = r << 1 | 1;
                    if (f.test(op.apply(data[r], sum))) {
                        sum = op.apply(data[r], sum);
                        r--;
                    }
                }
                return r + 1 - N;
            }
            sum = op.apply(data[r], sum);
        } while ((r & -r) != r);
        return 0;
    }

    private void exclusiveRangeCheck(int p) {
        if (p < 0 || p >= MAX) {
            throw new IndexOutOfBoundsException(
                    String.format("Index %d out of bounds for the range [%d, %d).", p, 0, MAX));
        }
    }

    private void inclusiveRangeCheck(int p) {
        if (p < 0 || p > MAX) {
            throw new IndexOutOfBoundsException(
                    String.format("Index %d out of bounds for the range [%d, %d].", p, 0, MAX));
        }
    }

    // **************** DEBUG **************** //

    private int indent = 6;

    public void setIndent(int newIndent) {
        this.indent = newIndent;
    }

    @Override
    public String toString() {
        return toSimpleString();
    }

    public String toDetailedString() {
        return toDetailedString(1, 0);
    }

    private String toDetailedString(int k, int sp) {
        if (k >= N)
            return indent(sp) + data[k];
        String s = "";
        s += toDetailedString(k << 1 | 1, sp + indent);
        s += "\n";
        s += indent(sp) + data[k];
        s += "\n";
        s += toDetailedString(k << 1 | 0, sp + indent);
        return s;
    }

    private static String indent(int n) {
        StringBuilder sb = new StringBuilder();
        while (n-- > 0)
            sb.append(' ');
        return sb.toString();
    }

    public String toSimpleString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < N; i++) {
            sb.append(data[i + N]);
            if (i < N - 1)
                sb.append(',').append(' ');
        }
        sb.append(']');
        return sb.toString();
    }
}

class DSU {
    private int n;
    private int[] parentOrSize;

    public DSU(int n) {
        this.n = n;
        this.parentOrSize = new int[n];
        Arrays.fill(parentOrSize, -1);
    }

    int merge(int a, int b) {
        if (!(0 <= a && a < n) || !(0 <= b && b < n)) {
            return -1;
        }
        int x = leader(a);
        int y = leader(b);
        if (x == y)
            return x;
        if (-parentOrSize[x] < -parentOrSize[y]) {
            int tmp = x;
            x = y;
            y = tmp;
        }
        parentOrSize[x] += parentOrSize[y];
        parentOrSize[y] = x;
        return x;
    }

    boolean same(int a, int b) {
        if (!(0 <= a && a < n) || !(0 <= b && b < n)) {
            return false;
        }
        return leader(a) == leader(b);
    }

    int leader(int a) {
        if (parentOrSize[a] < 0) {
            return a;
        } else {
            parentOrSize[a] = leader(parentOrSize[a]);
            return parentOrSize[a];
        }
    }

    int size(int a) {
        if (!(0 <= a && a < n)) {
            return -1;
        }
        return -parentOrSize[leader(a)];
    }

    ArrayList<ArrayList<Integer>> groups() {
        int[] leaderBuf = new int[n];
        int[] groupSize = new int[n];
        for (int i = 0; i < n; i++) {
            leaderBuf[i] = leader(i);
            groupSize[leaderBuf[i]]++;
        }
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < n; i++) {
            result.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            result.get(leaderBuf[i]).add(i);
        }
        return result;
    }
}
