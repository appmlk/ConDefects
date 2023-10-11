import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    static int N, K;
    static String S;

    public static void main(String[] args) {
        var sc = new FastScanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        S = sc.next();

        System.out.println(solve());
    }

    static boolean isAll(String S, char c) {
        for (int i = 0; i < S.length(); i++) {
            if( S.charAt(i) != c ) return false;
        }
        return true;
    }

    static int solve() {
        if( isAll(S, 'X') ) {
            return N-1;
        }
        if( isAll(S, 'Y') ) {
            if( N == K ) {
                return 0;
            } else {
                return N-1-K;
            }
        }

        // 頑張ってシミュレーション
        // 端の処理、X->Yした後のX->Yの処理が面倒臭い
        // 何か頭のいい方法がありそうだが...
        var xq = new PriorityQueue<XClustor>();
        var yq = new PriorityQueue<YClustor>();
        boolean prevIsX = S.charAt(0) == 'X';
        boolean prevIsEdge = true;
        int cnt = 1;
        for (int i = 1; i < N; i++) {
            if( prevIsX == (S.charAt(i) == 'X') ) {
                cnt++;
            } else {
                if( prevIsX ) {
                    xq.add( new XClustor(cnt, prevIsEdge) );

                } else {
                    yq.add( new YClustor(cnt, prevIsEdge));
                }
                prevIsX = (S.charAt(i) == 'X');
                cnt = 1;
                prevIsEdge = false;
            }
        }
        boolean edge = true;
        if( prevIsX ) {
            xq.add( new XClustor(cnt, edge) );

        } else {
            yq.add( new YClustor(cnt, edge));
        }

        int point = 0;
        for (int i = 0; i < N-1; i++) {
            if( S.charAt(i) == 'Y' && S.charAt(i+1) == 'Y' ) {
                point++;
            }
        }

        int r = K;
        while( r > 0 && !xq.isEmpty() ) {
            // YXY  2
            // YXXY 2 1
            // YXX  1 1
            var x = xq.poll();
            if( x.size == 1 ) {
                point += (x.edge ? 1 : 2);

            } else {
                point += 1;
                x.size--;
                xq.add(x);
            }
            r--;
        }

        while( r > 0 && !yq.isEmpty() ) {
            // yYY   1 1
            // yYYYy 2 1 1
            // yYYy  2 1
            // yYy   2
            var y = yq.poll();
            point -= y.edge ? 1 : 2;
            r--;
            y.size--;

            while(r > 0 && y.size > 0) {
                point -= 1;
                r--;
                y.size--;
            }
        }

        return point;
    }

    static class XClustor implements Comparable<XClustor> {
        int size;
        final boolean edge;

        public XClustor(int size, boolean edge) {
            this.size = size;
            this.edge = edge;
        }

        @Override
        public int compareTo(XClustor arg) {
            // 端は後から得になることもないので後回し
            if( edge && !arg.edge ) {
                return 1;
            } else if( !edge && arg.edge ) {
                return -1;
            } else {
                return Integer.compare(size, arg.size);
            }
        }
    }

    static class YClustor implements Comparable<YClustor> {
        int size;
        final boolean edge;

        public YClustor(int size, boolean edge) {
            this.size = size;
            this.edge = edge;
        }

        @Override
        public int compareTo(YClustor arg) {
            // 端から消費したほうが得
            if( edge && !arg.edge ) {
                return -1;
            } else if( !edge && arg.edge ) {
                return 1;
            } else {
                // 大きいものから消費する
                return Integer.compare(arg.size, size);
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
