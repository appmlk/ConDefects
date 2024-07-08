import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.function.DoublePredicate;

public class Main {

    static int N, M;
    static Edge[] E;

    public static void main(String[] args) {
        var sc = new FastScanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        E = new Edge[M];
        for (int i = 0; i < M; i++) {
            E[i] = new Edge(sc.nextInt()-1, sc.nextInt()-1, sc.nextInt(), sc.nextInt());
        }

        System.out.println(solve());
    }

    static class Edge {
        final int u, v, b, c;

        public Edge(int u, int v, int b, int c) {
            this.u = u;
            this.v = v;
            this.b = b;
            this.c = c;
        }
    }

    static Edge[][] G;
    static double[] dp;
    static boolean[] dp2;

    static String solve() {
        // 以前やった濃度の問題と一緒の考えでいける
        // 最初 u < v が見えてなくて解けなくない？ってなってた

        G = adjD(N, E);
        dp = new double[N];
        dp2 = new boolean[N];
        var ans = maximaizeD(0, 10000, Main::isOk);
        return BigDecimal.valueOf(ans).toPlainString();
    }

    static boolean isOk(double x) {
        // コスト毎美しさをxとするのにどれだけ美しさが余剰するか、でdp
        Arrays.fill(dp, -1000000.0); // ここで Double.MIN_VALUE/2とやって苦しんだ(Double.MIN_VALUEは0以上で最小の"正の"数値)
        Arrays.fill(dp2, false);
        dp[0] = 0;
        dp2[0] = true;

        for (int i = 0; i < N; i++) {
            if( !dp2[i] ) continue;

            for (Edge e : G[i]) {
                int j = e.v;
                // (b-s)/c = x
                // -> b-s = xc
                // -> s = b-xc
                double s = (double)e.b - x*e.c;
                dp[j] = Math.max(dp[j], dp[i]+s);
                dp2[j] = true;
            }
        }

        return dp[N-1] > 0;
    }

    static Edge[][] adjD(int n, Edge[] es) {
        Edge[][] adj = new Edge[n][];
        int[] cnt = new int[n];

        for (Edge e : es) {
            cnt[e.u]++;
        }
        for (int i = 0; i < n; i++) {
            adj[i] = new Edge[cnt[i]];
        }
        for (Edge e : es) {
            adj[e.u][--cnt[e.u]] = e;
        }
        return adj;
    }

    static double maximaizeD(double lo, double hi, DoublePredicate p) {
        int time = 0;
        while(time++ < 100) {
            double x = (hi + lo) / 2;
            if( p.test(x) ) {
                lo = x;
            } else {
                hi = x;
            }
        }
        return lo;
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
