import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    static int N;

    public static void main(String[] args) {
        var sc = new FastScanner(System.in);
        N = sc.nextInt();

        solve(sc);
    }

    // rangeをいい感じに作ってQueryにいい感じに答える
    static void solve(FastScanner sc) {
        var ranges = new ArrayList<Range>();
        var no = 1;
        for (int l = 1; l <= N; l++) {
            ranges.add(new Range(no++, l, l));
            for (int p = 0; p <= 12; p++) {
                int r = l + (int)Math.pow(2, p);
                if( r <= N ) {
                    ranges.add(new Range(no++, l, r));
                }
            }
        }
        System.out.println(ranges.size());
        System.out.flush();
        var b = new StringBuilder();
        for (Range range : ranges) {
            String row = range.l + " " + range.r + "\n";
            b.append(row);
        }
        System.out.print( b );
        System.out.flush();

        var byL = new HashMap<Integer, List<Range>>();
        var byR = new HashMap<Integer, List<Range>>();
        for (Range range : ranges) {
            byL.computeIfAbsent(range.l, k -> new ArrayList<>()).add(range);
            byR.computeIfAbsent(range.r, k -> new ArrayList<>()).add(range);
        }

        int Q = sc.nextInt();
        for (int q = 0; q < Q; q++) {
            int[] query = sc.nextIntArray(2);
            int l = query[0];
            int r = query[1];

            find(l, r, byL, byR);
        }
    }

    static void find(int l, int r, Map<Integer, List<Range>> byL, Map<Integer, List<Range>> byR) {
        for (Range r1 : byL.get(l)) {
            for (Range r2 : byR.get(r)) {
                if( isOk(r1, r2, l, r) ) {
                    System.out.println(r1.no + " " + r2.no);
                    System.out.flush();
                    return;
                }
            }
        }
        throw new RuntimeException("oops");
    }

    static boolean isOk(Range r1, Range r2, int l, int r) {
        return Math.min(r1.l, r2.l) == l && Math.max(r1.r, r2.r) == r && r1.noSpace(r2);
    }

    static class Range {
        final int no, l, r;

        public Range(int no, int l, int r) {
            this.no = no;
            this.l = l;
            this.r = r;
        }

        boolean noSpace(Range range) {
            if( range.l < l ) {
                return range.r >= l;

            } else {
                return range.l <= r;
            }
        }
    }

    static void writeLines(int[] as) {
        var pw = new PrintWriter(System.out);
        for (var a : as) pw.println(a);
        pw.flush();
    }

    static void writeLines(long[] as) {
        var pw = new PrintWriter(System.out);
        for (var a : as) pw.println(a);
        pw.flush();
    }

    static void writeSingleLine(int[] as) {
        var pw = new PrintWriter(System.out);
        for (var i = 0; i < as.length; i++) {
            if (i != 0) pw.print(" ");
            pw.print(as[i]);
        }
        pw.println();
        pw.flush();
    }

    static void debug(Object... args) {
        var j = new StringJoiner(" ");
        for (var arg : args) {
            if (arg == null) j.add("null");
            else if (arg instanceof int[]) j.add(Arrays.toString((int[]) arg));
            else if (arg instanceof long[]) j.add(Arrays.toString((long[]) arg));
            else if (arg instanceof double[]) j.add(Arrays.toString((double[]) arg));
            else if (arg instanceof Object[]) j.add(Arrays.toString((Object[]) arg));
            else j.add(arg.toString());
        }
        System.err.println(j);
    }

    @SuppressWarnings("unused")
    private static class FastScanner {

        private final InputStream in;
        private final byte[] buffer = new byte[1024];
        private int curbuf;
        private int lenbuf;

        public FastScanner(InputStream in) {
            this.in = in;
            this.curbuf = this.lenbuf = 0;
        }

        public boolean hasNextByte() {
            if (curbuf >= lenbuf) {
                curbuf = 0;
                try {
                    lenbuf = in.read(buffer);
                } catch (IOException e) {
                    throw new RuntimeException();
                }
                if (lenbuf <= 0)
                    return false;
            }
            return true;
        }

        private int readByte() {
            if (hasNextByte())
                return buffer[curbuf++];
            else
                return -1;
        }

        private boolean isSpaceChar(int c) {
            return !(c >= 33 && c <= 126);
        }

        private void skip() {
            while (hasNextByte() && isSpaceChar(buffer[curbuf]))
                curbuf++;
        }

        public boolean hasNext() {
            skip();
            return hasNextByte();
        }

        public String next() {
            if (!hasNext())
                throw new RuntimeException();
            StringBuilder sb = new StringBuilder();
            int b = readByte();
            while (!isSpaceChar(b)) {
                sb.appendCodePoint(b);
                b = readByte();
            }
            return sb.toString();
        }

        public int nextInt() {
            if (!hasNext())
                throw new RuntimeException();
            int c = readByte();
            while (isSpaceChar(c))
                c = readByte();
            boolean minus = false;
            if (c == '-') {
                minus = true;
                c = readByte();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new RuntimeException();
                res = res * 10 + c - '0';
                c = readByte();
            } while (!isSpaceChar(c));
            return (minus) ? -res : res;
        }

        public long nextLong() {
            if (!hasNext())
                throw new RuntimeException();
            int c = readByte();
            while (isSpaceChar(c))
                c = readByte();
            boolean minus = false;
            if (c == '-') {
                minus = true;
                c = readByte();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new RuntimeException();
                res = res * 10 + c - '0';
                c = readByte();
            } while (!isSpaceChar(c));
            return (minus) ? -res : res;
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public int[] nextIntArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            return a;
        }

        public double[] nextDoubleArray(int n) {
            double[] a = new double[n];
            for (int i = 0; i < n; i++)
                a[i] = nextDouble();
            return a;
        }

        public long[] nextLongArray(int n) {
            long[] a = new long[n];
            for (int i = 0; i < n; i++)
                a[i] = nextLong();
            return a;
        }

        public char[][] nextCharMap(int n, int m) {
            char[][] map = new char[n][m];
            for (int i = 0; i < n; i++)
                map[i] = next().toCharArray();
            return map;
        }
    }
}
