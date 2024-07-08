import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringJoiner;

public class Main {

    static int N;
    static int[] U, V;

    public static void main(String[] args) {
        var sc = new FastScanner(System.in);
        N = sc.nextInt();
        U = new int[N-1];
        V = new int[N-1];
        for (int i = 0; i < N - 1; i++) {
            U[i] = sc.nextInt()-1;
            V[i] = sc.nextInt()-1;
        }

        writeSingleLine(solve());
    }

    static int[] solve() {
        // 次数1の点の横が必ず星の中心
        int[] cnt = new int[N];
        int[] q = new int[N];
        int s = 0, t = 0;
        var G = adjB(N, U, V);
        for (int i = 0; i < N; i++) {
            cnt[i] = G[i].length;
            if( cnt[i] == 1 ) {
                q[t++] = i;
            }
        }

        var ans = new ArrayList<Integer>();
        while( s != t ) {
            int a = q[s++];
            if( cnt[a] != 1 ) continue;

            int center = -1;
            for (int b : G[a]) {
                if( cnt[b] != 0 ) {
                    center = b;
                    break;
                }
            }

            ans.add( cnt[center] );
            for (int frontier : G[center]) {
                cnt[frontier]--;
                if( cnt[frontier] == 1 ) {
                    for (int next : G[frontier]) {
                        if( next != center ) {
                            q[t++] = next;
                            cnt[next]--;
                            cnt[frontier]--;
                        }
                    }
                }
            }
        }

        return ans.stream().mapToInt(i -> i).sorted().toArray();
    }

    static int[][] adjB(int n, int[] from, int[] to) {
        int[][] adj = new int[n][];
        int[] cnt = new int[n];
        for (int f : from) {
            cnt[f]++;
        }
        for (int t : to) {
            cnt[t]++;
        }
        for (int i = 0; i < n; i++) {
            adj[i] = new int[cnt[i]];
        }
        for (int i = 0; i < from.length; i++) {
            adj[from[i]][--cnt[from[i]]] = to[i];
            adj[to[i]][--cnt[to[i]]] = from[i];
        }
        return adj;
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
