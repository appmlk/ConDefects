import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.StringJoiner;

public class Main {

    static int N;
    static int[] P;

    public static void main(String[] args) {
        var sc = new FastScanner(System.in);
        N = sc.nextInt();
        P = new int[N];
        for (int i = 0; i < N; i++) {
            P[i] = sc.nextInt();
        }

        System.out.println(BigDecimal.valueOf(solve()).toPlainString());
    }

    static double solve() {
        // 最初コンテストの出る順序が変更できると考えてこんなん一意では…？となってた
        // dp[i][k] = コンテストをi個見て、k回出場したとした時の最大レート
        // が求まるのでそれを使って各回数毎に求める
        var dp = new double[N+1][N+1];
        for (double[] each : dp) {
            Arrays.fill(each, -1);
        }
        dp[0][0] = 0.0;

        var mul = new double[N+1];
        mul[0] = 1;
        for (int i = 0; i < N; i++) {
            mul[i+1] = mul[i]*0.9;
        }

        for (int i = 0; i < N; i++) {
            int idx = N-i-1;
            var p = P[idx];

            for (int k = 0; k <= i; k++) {
                dp[i+1][k+1] = Math.max(dp[i+1][k+1], dp[i][k] + mul[k]*p);
                dp[i+1][k] = Math.max(dp[i+1][k], dp[i][k]);
            }
        }

        var ans = -1.0;
        double b = 0; // 1.0 + 0.9 + ...
        for (int k = 1; k <= N; k++) {
            var q = dp[N][k];
            b += mul[k-1];
            // debug(q, b, q/b, 1200.0/Math.sqrt(k));
            ans = Math.max(ans, q/b - 1200.0/Math.sqrt(k) );
        }

        return ans;
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
