import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
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

    int n;
    long[] x, y;
    void solve() {
        n = in.nextInt();
        x = new long[n + 2];
        y = new long[n + 2];
        for (int i = 0; i < n; i++) {
            x[i + 1] = in.nextLong();
        }
        for (int i = 0; i < n; i++) {
            y[i + 1] = in.nextLong();
        }
        x[0] = -inf;
        x[n + 1] = inf;
//        out.debug(f(1));
//        out.debug(f(2));
//        out.debug(f(3));
        long left = 0;
        long right = inf;
        while (right - left > 1) {
            long mid = (left + right) / 2;
            if (f(mid)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        out.println(left >= inf / 2 ? -1 : left);
    }

    boolean f(long mid) {
        PriorityQueue<LongPair> queue1 = new PriorityQueue<>();
        long count = 0;
        for (int i = 1; i <= n + 1; i++) {
            if (y[i - 1] < y[i]) {
                queue1.add(new LongPair(x[i - 1] + 1, y[i] - y[i - 1]));
            } else if (y[i - 1] > y[i]) {
                long rem = y[i - 1] - y[i];
                // v <= x[i] - 1 - mid
                while (!queue1.isEmpty() && queue1.peek().first <= x[i] - 1 - mid) {
                    LongPair p = queue1.remove();
                    count += p.second;
                }
                if (count < rem) {
                    return false;
                }
                count -= rem;
            }
        }
        return true;
    }

    public static void main(String... args) {
        new Main().solve();
        out.flush();
    }
}

class BinaryTrie {
    private static final int BIT_LEN = 63;
    private final Node root = new Node();

    public void clear() {
        root.count = 0;
        root.left = null;
        root.right = null;
    }

    public long size() {
        return root.count;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public long get(long i) {
        if (i < 0 || size() <= i) {
            throw new IndexOutOfBoundsException();
        }
        Node node = root;
        long val = 0;
        for (int j = 0; j < BIT_LEN; j++) {
            val *= 2;
            long lc = node.left == null ? 0 : node.left.count;
            if (i < lc) {
                node = node.left;
            } else {
                val++;
                i -= lc;
                node = node.right;
            }
        }
        return val;
    }

    public void add(long x) {
        add(x, 1);
    }

    public void add(long x, long count) {
        if (x < 0 || count < 0) {
            throw new IllegalArgumentException();
        }
        Node node = root;
        node.count += count;
        node.sum += x * count;
        for (int i = BIT_LEN - 1; i >= 0; i--) {
            if ((x >> i & 1) == 0) {
                if (node.left == null) {
                    node.left = new Node();
                }
                node.left.count += count;
                node.left.sum += x * count;
                node = node.left;
            } else {
                if (node.right == null) {
                    node.right = new Node();
                }
                node.right.count += count;
                node.right.sum += x * count;
                node = node.right;
            }
        }
    }

    public boolean remove(long x) {
        return remove(x, 1);
    }

    public boolean remove(long x, long count) {
        if (x < 0 || count < 0) {
            throw new IllegalArgumentException();
        }
        if (count == 0) {
            return false;
        }
        if (size() < count) {
            throw new NoSuchElementException();
        }
        Node node = root;
        node.count -= count;
        node.sum -= x * count;
        for (int i = BIT_LEN - 1; i >= 0; i--) {
            if ((x >> i & 1) == 0) {
                if (node.left.count < count) {
                    throw new NoSuchElementException();
                }
                node.left.count -= count;
                node.left.sum -= x * count;
                if (node.left.count == 0) {
                    node.left = null;
                    return true;
                }
                node = node.left;
            } else {
                if (node.right.count < count) {
                    throw new NoSuchElementException();
                }
                node.right.count -= count;
                node.right.sum -= x * count;
                if (node.right.count == 0) {
                    node.right = null;
                    return true;
                }
                node = node.right;
            }
        }
        return true;
    }

    public long count(long x) {
        Node node = root;
        for (int i = BIT_LEN - 1; i >= 0; i--) {
            if ((x >> i & 1) == 0) {
                node = node.left;
            } else {
                node = node.right;
            }
            if (node == null) {
                return 0;
            }
        }
        return node.count;
    }

    public boolean contains(long x) {
        return count(x) >= 1;
    }

    public OptionalLong min() {
        if (isEmpty()) {
            return OptionalLong.empty();
        }
        Node node = root;
        long val = 0;
        for (int j = 0; j < BIT_LEN; j++) {
            val *= 2;
            if (node.left != null) {
                node = node.left;
            } else {
                node = node.right;
                val++;
            }
        }
        return OptionalLong.of(val);
    }

    public OptionalLong max() {
        if (isEmpty()) {
            return OptionalLong.empty();
        }
        Node node = root;
        long val = 0;
        for (int j = 0; j < BIT_LEN; j++) {
            val *= 2;
            if (node.right == null) {
                node = node.left;
            } else {
                node = node.right;
                val++;
            }
        }
        return OptionalLong.of(val);
    }

    public long lowerBound(long x) {
        if (isEmpty()) {
            return 0;
        }
        Node node = root;
        long i = 0;
        for (int j = BIT_LEN - 1; j >= 0; j--) {
            if ((x >> j & 1) == 0) {
                node = node.left;
            } else {
                i += node.left == null ? 0 : node.left.count;
                node = node.right;
            }
            if (node == null) {
                return i;
            }
        }
        return i;
    }

    public long upperBound(long x) {
        return lowerBound(x + 1);
    }

    public long sum(long l, long r) {
        if (0 < l) {
            return sum(0, r) - sum(0, l);
        }
        Node node = root;
        long acc = 0;
        long val = 0;
        for (int j = 0; j < BIT_LEN; j++) {
            val *= 2;
            long lc = node.left == null ? 0 : node.left.count;
            if (r < lc) {
                node = node.left;
            } else {
                acc += node.left == null ? 0 : node.left.sum;
                r -= lc;
                val++;
                node = node.right;
            }
            if (node == null) {
                return acc;
            }
        }
        acc += Math.min(r, node.count) * val;
        return acc;
    }

    public OptionalLong lower(long x) {
        long index = lowerBound(x) - 1;
        if (index < 0) {
            return OptionalLong.empty();
        }
        return OptionalLong.of(get(index));
    }

    public OptionalLong higher(long x) {
        long index = upperBound(x);
        if (size() <= index) {
            return OptionalLong.empty();
        }
        return OptionalLong.of(get(index));
    }

    public OptionalLong floor(long x) {
        long index = upperBound(x) - 1;
        if (index < 0) {
            return OptionalLong.empty();
        }
        return OptionalLong.of(get(index));
    }

    public OptionalLong ceil(long x) {
        long index = lowerBound(x);
        if (size() <= index) {
            return OptionalLong.empty();
        }
        return OptionalLong.of(get(index));
    }

    public List<Long> toList() {
        List<Long> list = new ArrayList<>();
        root.collect(0, 0, list);
        return Collections.unmodifiableList(list);
    }

    @Override
    public String toString() {
        return toList().toString();
    }

    private static class Node {
        private long count;
        private long sum;
        private Node left;
        private Node right;

        private void collect(int depth, long val, List<Long> list) {
            if (left != null) {
                left.collect(depth + 1, val * 2, list);
            }
            if (right != null) {
                right.collect(depth + 1, val * 2 + 1, list);
            }
            if (depth == BIT_LEN) {
                for (long i = 0; i < count; i++) {
                    list.add(val);
                }
            }
        }
    }
}

class LongPair implements Comparable<LongPair> {
    long first;
    long second;

    LongPair(long first, long second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof LongPair)) {
            return false;
        }
        LongPair that = (LongPair)o;
        return first == that.first && second == that.second;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(first) * 31 + Long.hashCode(second);
    }

    @Override
    public int compareTo(LongPair o) {
        return first == o.first ? Long.compare(second, o.second) : Long.compare(first, o.first);
    }

    @Override
    public String toString() {
        return String.format("[%d, %d]", first, second);
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