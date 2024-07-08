import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.IntFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Main {
    public static void main(String[] args) throws IOException {
        run(System.in, new PrintStream(new BufferedOutputStream(System.out), false, StandardCharsets.UTF_8));
    }

    @SuppressWarnings({"DuplicatedCode", "SameParameterValue"})
    static void run(InputStream in, PrintStream out) throws IOException {
        FastScanner scanner = new FastScanner(in);

        var n = scanner.nextUint();
        var abs = scanner.nextUints(n, 2);
        out.println(solve(abs) ? "Takashi" : "Aoki");
        out.flush();
    }

    static boolean solve(int[]... abs) {
        var cache = new byte[1 << abs.length];
        return solve0(abs, cache, 0) == 1;
    }

    private static byte solve0(int[][] abs, byte[] cache, int selection) {
        if (cache[selection] == 0) {
            cache[selection] = 2;

            outer:
            for (int card0 = 0; card0 < abs.length - 1; card0++) {
                if ((selection & (1 << card0)) != 0) {
                    continue;
                }

                for (int card1 = card0 + 1; card1 < abs.length; card1++) {
                    if ((selection & (1 << card1)) != 0) {
                        continue;
                    }

                    if (abs[card0][0] == abs[card1][0] || abs[card0][1] == abs[card1][1]) {
                        if (solve0(abs, cache, selection | (1 << card0) | (1 << card1)) == 2) {
                            cache[selection] = 1;
                            break outer;
                        }
                    }
                }
            }
        }

        return cache[selection];
    }

    @SuppressWarnings({"DuplicatedCode", "unused"})
    static void printIntsLn(PrintStream out, int... ints) {
        printIntsLn(out, ints, " ");
    }

    @SuppressWarnings({"DuplicatedCode", "unused"})
    static void printIntsLn(PrintStream out, int[] ints, String separator) {
        for (int i = 0; i < ints.length; i++) {
            if (i != 0) {
                out.print(separator);
            }

            out.print(ints[i]);
        }

        out.println();
    }

    @SuppressWarnings({"DuplicatedCode", "unused"})
    static void printLongsLn(PrintStream out, long... longs) {
        printLongsLn(out, longs, " ");
    }

    @SuppressWarnings({"DuplicatedCode", "unused"})
    static void printLongsLn(PrintStream out, long[] longs, String separator) {
        for (int i = 0; i < longs.length; i++) {
            if (i != 0) {
                out.print(separator);
            }

            out.print(longs[i]);
        }

        out.println();
    }

    @SuppressWarnings({"DuplicatedCode", "unused"})
    @SafeVarargs
    static <T> void printObjectsLn(PrintStream out, T... objects) {
        printObjectsLn(out, List.of(objects), " ");
    }

    @SuppressWarnings({"DuplicatedCode", "unused"})
    static void printObjectsLn(PrintStream out, Collection<?> objects) {
        printObjectsLn(out, objects, " ");
    }

    @SuppressWarnings({"DuplicatedCode", "unused"})
    static void printObjectsLn(PrintStream out, Collection<?> objects, String separator) {
        var first = true;
        for (Object object : objects) {
            if (!first) {
                out.print(separator);
            }

            out.print(object);
            first = false;
        }

        out.println();
    }

    @SuppressWarnings({"DuplicatedCode", "unused"})
    static int minOf(int... is) {
        if (is.length == 0) {
            throw new IllegalArgumentException();
        }

        var res = Integer.MAX_VALUE;
        for (int i : is) {
            res = Math.min(res, i);
        }
        return res;
    }

    @SuppressWarnings({"DuplicatedCode", "unused"})
    static int maxOf(int... is) {
        if (is.length == 0) {
            throw new IllegalArgumentException();
        }

        var res = Integer.MIN_VALUE;
        for (int i : is) {
            res = Math.max(res, i);
        }
        return res;
    }

    @SuppressWarnings({"DuplicatedCode", "unused"})
    static long minOf(long... is) {
        if (is.length == 0) {
            throw new IllegalArgumentException();
        }

        var res = Long.MAX_VALUE;
        for (long i : is) {
            res = Math.min(res, i);
        }
        return res;
    }

    @SuppressWarnings({"DuplicatedCode", "unused"})
    static long maxOf(long... is) {
        if (is.length == 0) {
            throw new IllegalArgumentException();
        }

        var res = Long.MIN_VALUE;
        for (long i : is) {
            res = Math.max(res, i);
        }
        return res;
    }

    @SuppressWarnings({"DuplicatedCode", "unused"})
    record MinMax<T>(T min, T max) {
        static MinMax<Integer> from(int... is) {
            if (is.length == 0) {
                throw new IllegalArgumentException();
            }

            var min = Integer.MAX_VALUE;
            var max = Integer.MIN_VALUE;
            for (int x : is) {
                min = Math.min(min, x);
                max = Math.max(max, x);
            }

            return new MinMax<>(min, max);
        }

        static MinMax<Long> from(long... is) {
            if (is.length == 0) {
                throw new IllegalArgumentException();
            }

            var min = Long.MAX_VALUE;
            var max = Long.MIN_VALUE;
            for (long x : is) {
                min = Math.min(min, x);
                max = Math.max(max, x);
            }

            return new MinMax<>(min, max);
        }
    }

    @SuppressWarnings({"DuplicatedCode", "unused"})
    static <T> ArrayList<T> fillList(int n, Supplier<T> supplier) {
        return fillList(n, ignore -> supplier.get());
    }

    @SuppressWarnings({"DuplicatedCode", "unused"})
    static <T> ArrayList<T> fillList(int n, IntFunction<T> valueAt) {
        var res = new ArrayList<T>(n);
        for (int i = 0; i < n; i++) {
            res.add(valueAt.apply(i));
        }

        return res;
    }

    @SuppressWarnings({"DuplicatedCode", "unused"})
    static <T> T[] fillArray(int n, Class<T> clazz, Supplier<T> supplier) {
        return fillArray(n, clazz, ignore -> supplier.get());
    }

    @SuppressWarnings({"DuplicatedCode", "unused"})
    static <T> T[] fillArray(int n, Class<T> clazz, IntFunction<T> valueAt) {
        @SuppressWarnings("unchecked") var res = (T[]) Array.newInstance(clazz, n);

        for (int i = 0; i < n; i++) {
            res[i] = valueAt.apply(i);
        }

        return res;
    }

    @SuppressWarnings({"DuplicatedCode", "unused", "SameParameterValue"})
    static <T> T[] fillArray(int n, T value) {
        @SuppressWarnings("unchecked") var res = (T[]) Array.newInstance(value.getClass(), n);
        Arrays.fill(res, value);
        return res;
    }

    @SuppressWarnings({"DuplicatedCode", "unused"})
    static int[] fillIntArray(int n, IntUnaryOperator valueAt) {
        var res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = valueAt.applyAsInt(i);
        }
        return res;
    }

    @SuppressWarnings({"DuplicatedCode", "unused"})
    static int[] fillIntArray(int n, int value) {
        var res = new int[n];
        Arrays.fill(res, value);
        return res;
    }

    @SuppressWarnings({"DuplicatedCode", "unused"})
    static long[] fillLongArray(int n, IntToLongFunction valueAt) {
        var res = new long[n];
        for (int i = 0; i < n; i++) {
            res[i] = valueAt.applyAsLong(i);
        }
        return res;
    }

    @SuppressWarnings({"DuplicatedCode", "unused"})
    static long[] fillLongArray(int n, long value) {
        var res = new long[n];
        Arrays.fill(res, value);
        return res;
    }

    @SuppressWarnings({"DuplicatedCode", "unused"})
    record BinaryString(BitSet bits, int length) {
        public BinaryString {
            if (length < 0) throw new IndexOutOfBoundsException("" + length);
        }

        @Override
        public String toString() {
            return IntStream.range(0, length)
                    .mapToObj(i -> bits.get(i) ? "1" : "0")
                    .collect(Collectors.joining());
        }
    }

    @SuppressWarnings({"DuplicatedCode", "unused"})
    record BinaryGrid(BitSet[] bits, int w) {
        BinaryGrid {
            if (w < 0) throw new IndexOutOfBoundsException("w=" + w);
        }

        int h() {
            return bits.length;
        }

        BitSet row(int i) {
            return bits[i];
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            BinaryGrid that = (BinaryGrid) o;

            if (w != that.w) return false;
            return Arrays.equals(bits, that.bits);
        }

        @Override
        public int hashCode() {
            int result = Arrays.hashCode(bits);
            result = 31 * result + w;
            return result;
        }

        @Override
        public String toString() {
            return Arrays.stream(bits, 0, h())
                    .map(row -> IntStream.range(0, w).mapToObj(j -> row.get(j) ? "1" : "0").collect(Collectors.joining()))
                    .collect(Collectors.joining("\n"));
        }
    }

    @SuppressWarnings({"DuplicatedCode", "unused"})
    static final class Adj {
        static ArrayList<ArrayList<Integer>> undirected(int n, int[][] edges) {
            return undirected(n, edges, 1);
        }

        static ArrayList<ArrayList<Integer>> directed(int n, int[][] edges) {
            return directed(n, edges, 1);
        }

        @SuppressWarnings("SameParameterValue")
        static ArrayList<ArrayList<Integer>> undirected(int n, int[][] edges, int offset) {
            var res = fillList(n, () -> new ArrayList<Integer>(1));

            for (int[] edge : edges) {
                res.get(edge[0] - offset).add(edge[1] - offset);
                res.get(edge[1] - offset).add(edge[0] - offset);
            }

            return res;
        }

        @SuppressWarnings("SameParameterValue")
        static ArrayList<ArrayList<Integer>> directed(int n, int[][] edges, int offset) {
            var res = fillList(n, () -> new ArrayList<Integer>(1));

            for (int[] edge : edges) {
                res.get(edge[0] - offset).add(edge[1] - offset);
            }

            return res;
        }
    }

    @SuppressWarnings({"DuplicatedCode", "unused"})
    static long gcd(long a, long b) {
        while (b != 0) {
            var bb = a % b;
            a = b;
            b = bb;
        }

        return a;
    }

    @SuppressWarnings({"DuplicatedCode", "unused"})
    static int gcd(int a, int b) {
        while (b != 0) {
            var bb = a % b;
            a = b;
            b = bb;
        }

        return a;
    }


    @SuppressWarnings({"DuplicatedCode", "unused"})
    static final class FastScanner {
        private final InputStream in;
        private final byte[] buf = new byte[4 * 1024];
        private int a = 0;
        private int b = 0;

        FastScanner(InputStream in) {
            this.in = in;
        }

        byte nextByte() throws IOException {
            if (a < b) {
                byte v = buf[a];
                a += 1;
                return v;
            } else {
                b = in.read(buf);

                if (b < 0) {
                    throw new EOFException();
                } else {
                    a = 0;
                    return nextByte();
                }
            }
        }

        boolean hasNext() throws IOException {
            if (a < b) return true;
            else if (b < 0) return false;
            else {
                b = in.read(buf);
                a = 0;
                return hasNext();
            }
        }

        char nextChar() throws IOException {
            return (char) nextByte();
        }

        char nextNonWhitespaceChar() throws IOException {
            while (true) {
                var c = nextChar();
                if (!Character.isWhitespace(c)) {
                    return c;
                }
            }
        }

        long nextLong() throws IOException {
            while (true) {
                char c = nextChar();

                if (c == '-') {
                    c = nextChar();
                    if (isAsciiDigit(c)) {
                        return Math.negateExact(nextUlongImpl(c));
                    }
                } else if (isAsciiDigit(c)) {
                    return nextUlongImpl(c);
                }
            }
        }

        long[] nextLongs(int n) throws IOException {
            long[] res = new long[n];

            for (int i = 0; i < n; ++i) {
                res[i] = nextLong();
            }

            return res;
        }

        long[][] nextLongs(int rows, int cols) throws IOException {
            long[][] res = new long[rows][cols];

            for (int i = 0; i < rows; ++i) {
                for (int j = 0; j < cols; ++j) {
                    res[i][j] = nextLong();
                }
            }

            return res;
        }

        long nextUlong() throws IOException {
            long res = nextLong();
            if (res < 0) throw new IllegalArgumentException("Expected a positive number, but got " + res);
            return res;
        }

        long[] nextUlongs(int n) throws IOException {
            long[] res = new long[n];

            for (int i = 0; i < n; ++i) {
                res[i] = nextUlong();
            }

            return res;
        }

        long[][] nextUlongs(int rows, int cols) throws IOException {
            long[][] res = new long[rows][cols];

            for (int i = 0; i < rows; ++i) {
                for (int j = 0; j < cols; ++j) {
                    res[i][j] = nextUlong();
                }
            }

            return res;
        }

        int nextInt() throws IOException {
            return Math.toIntExact(nextLong());
        }

        int[] nextInts(int n) throws IOException {
            int[] res = new int[n];

            for (int i = 0; i < n; ++i) {
                res[i] = nextInt();
            }

            return res;
        }

        int[][] nextInts(int rows, int cols) throws IOException {
            int[][] res = new int[rows][cols];

            for (int i = 0; i < rows; ++i) {
                for (int j = 0; j < cols; ++j) {
                    res[i][j] = nextInt();
                }
            }

            return res;
        }

        int nextUint() throws IOException {
            return Math.toIntExact(nextUlong());
        }

        int[] nextUints(int n) throws IOException {
            int[] res = new int[n];

            for (int i = 0; i < n; ++i) {
                res[i] = nextUint();
            }

            return res;
        }

        int[][] nextUints(int rows, int cols) throws IOException {
            int[][] res = new int[rows][cols];

            for (int i = 0; i < rows; ++i) {
                for (int j = 0; j < cols; ++j) {
                    res[i][j] = nextUint();
                }
            }

            return res;
        }

        <T> T[] nextObjects(int n, Class<T> clazz, IOExceptionThrowingSupplier<T> supplier) throws IOException {
            @SuppressWarnings("unchecked") T[] ret = (T[]) Array.newInstance(clazz, n);
            for (int i = 0; i < n; i++) {
                ret[i] = supplier.get();
            }

            return ret;
        }

        String nextLine() throws IOException {
            StringBuilder sb = new StringBuilder();
            char c = nextChar();

            while (c != '\n') {
                sb.append(c);

                if (!hasNext()) {
                    break;
                } else {
                    c = nextChar();
                }
            }

            if (!sb.isEmpty() && c == '\n' && sb.charAt(sb.length() - 1) == '\r') {
                sb.setLength(sb.length() - 1);
            }

            return sb.toString();
        }

        String[] nextLines(int n) throws IOException {
            if (n < 0) {
                n = nextUint();
                skipToNextLine();
            }

            return nextObjects(n, String.class, this::nextLine);
        }

        String[] nextLines() throws IOException {
            return nextLines(-1);
        }

        void skipToNextLine() throws IOException {
            //noinspection StatementWithEmptyBody
            while (nextChar() != '\n') {
                // just skip to next character
            }
        }

        BinaryString nextBinaryString() throws IOException {
            return nextBinaryString('1');
        }

        BinaryString nextBinaryString(char cTrue) throws IOException {
            return nextBinaryStringImpl(cTrue, -1);
        }

        BinaryString nextBinaryString(char cTrue, int length) throws IOException {
            if (length < 0) throw new IndexOutOfBoundsException("" + length);
            return nextBinaryStringImpl(cTrue, length);
        }

        BinaryGrid nextBinaryGrid(char cTrue) throws IOException {
            return nextBinaryGridImpl(cTrue, -1, -1);
        }

        BinaryGrid nextBinaryGrid(char cTrue, int h, int w) throws IOException {
            return nextBinaryGridImpl(cTrue, h, w);
        }

        private BinaryGrid nextBinaryGridImpl(char cTrue, int h, int w) throws IOException {
            if (h < 0) {
                h = nextUint();
                w = nextUint();

                if (h > 0) {
                    skipToNextLine();
                }
            }

            var bits = new BitSet[h];
            for (int i = 0; i < h; i++) {
                var row = new BitSet(w);

                for (int j = 0; j < w; j++) {
                    if (nextChar() == cTrue) {
                        row.set(j);
                    }
                }

                bits[i] = row;

                if (i != h - 1 || hasNext()) {
                    skipToNextLine();
                }
            }

            return new BinaryGrid(bits, w);
        }

        private BinaryString nextBinaryStringImpl(char cTrue, int length) throws IOException {
            if (length < 0) {
                length = nextUint();
                skipToNextLine();
            }

            BitSet bits = new BitSet(length);
            for (int i = 0; i < length; ++i) {
                if (nextChar() == cTrue) {
                    bits.set(i);
                }
            }

            return new BinaryString(bits, length);
        }

        private long nextUlongImpl(char firstDigit) throws IOException {
            long res = 0;
            char c = firstDigit;

            do {
                long res2 = 10 * res + (c - '0');
                if (res2 >= res) {
                    res = res2;
                } else {
                    throw new ArithmeticException("Long overflow");
                }

                if (hasNext()) {
                    c = nextChar();
                } else {
                    c = 0;
                }
            } while (isAsciiDigit(c));

            if (c != 0 && !isAsciiDigit(c)) {
                a -= 1;
            }

            return res;
        }

        private static boolean isAsciiDigit(char c) {
            return '0' <= c && c <= '9';
        }
    }

    interface IOExceptionThrowingSupplier<T> {
        T get() throws IOException;
    }
}