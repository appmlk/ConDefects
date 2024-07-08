import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringJoiner;

public class Main {

    static int N;
    static char[] R, C;

    public static void main(String[] args) {
        var sc = new FastScanner(System.in);
        N = sc.nextInt();
        R = sc.next().toCharArray();
        C = sc.next().toCharArray();

        solve();
    }

    static void solve() {
        // 何をしても抜けがありそうなので枝狩り全探索を試す
        char[][] A = new char[5][5];
        for (int i = 0; i < N; i++) {
            Arrays.fill(A[i], '?');
        }
        var ok = dfs(0, A);
        if( ok ) {
            System.out.println("Yes");
            for (int i = 0; i < N; i++) {
                System.out.println( new String(A[i]) );
            }

        } else {
            System.out.println("No");
        }
    }

    static char[] X = new char[]{'A', 'B', 'C', '.'};

    static boolean dfs(int hw, char[][] A) {
        if( hw == N*N ) {
            // 何かが抜けてるのか通らないので最終チェックを入れてみる
            return isAllOk(A);
        }
        int h = hw / N;
        int w = hw % N;
        for (char x : X) {
            if( isOk(h, w, x, A) ) {
                A[h][w] = x;
                if( dfs(hw+1, A) ) {
                    return true;
                } else {
                    A[h][w] = '?';
                }
            }
        }
        return false;
    }

    static boolean isOk(int h, int w, char x, char[][] A) {
        // A自体現状okのはずなのでhwにxが置けるかどうかだけ見る
        if( x == '.' ) {
            int hc = 0, wc = 0;
            for (int ph = 0; ph < h; ph++) {
                if( A[ph][w] == '.' ) hc++;
            }
            for (int pw = 0; pw < w; pw++) {
                if( A[h][pw] == '.' ) wc++;
            }
            return hc < N-3 && wc < N-3;

        } else {
            boolean firstInH = true, firstInW = true;
            for (int pw = 0; pw < w; pw++) {
                if( A[h][pw] == x ) return false;
                if( A[h][pw] != '.' ) firstInH = false;
            }
            for (int ph = 0; ph < h; ph++) {
                if( A[ph][w] == x ) return false;
                if( A[ph][w] != '.' ) firstInW = false;
            }
            if( firstInH && R[h] != x ) {
                return false;
            }
            if( firstInW && C[w] != x ) {
                return false;
            }
            return true;
        }
    }

    static boolean isAllOk(char[][] A) {
        for (int h = 0; h < N; h++) {
            for (int w = 0; w < N; w++) {
                if( A[h][w] == '.' ) continue;
                if( R[h] == A[h][w] ) {
                    break;
                } else {
                    return false;
                }
            }
        }
        for (int w = 0; w < N; w++) {
            for (int h = 0; h < N; h++) {
                if( A[h][w] == '.' ) continue;
                if( C[w] == A[h][w] ) {
                    break;
                } else {
                    return false;
                }
            }
        }

        for (int h = 0; h < N; h++) {
            int a = 0, b = 0, c = 0;
            for (int w = 0; w < N; w++) {
                switch( A[h][w] ) {
                    case 'A':
                        a++;
                        break;
                    case 'B':
                        b++;
                        break;
                    case 'C':
                        c++;
                        break;
                }
            }
            if( a != 1 || b != 1 || c != 1 ) return false;
        }

        for (int w = 0; w < N; w++) {
            int a = 0, b = 0, c = 0;
            for (int h = 0; h < N; h++) {
                switch( A[h][w] ) {
                    case 'A':
                        a++;
                        break;
                    case 'B':
                        b++;
                        break;
                    case 'C':
                        c++;
                        break;
                }
            }
            if( a != 1 || b != 1 || c != 1 ) return false;
        }

        return true;
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
