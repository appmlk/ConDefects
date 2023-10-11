import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringJoiner;

public class Main {

    public static void main(String[] args) {
        var sc = new FastScanner(System.in);
        var pw = new PrintWriter(System.out);
        var T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            var N = sc.nextInt();
            var A = sc.nextInt();
            var B = sc.nextInt();
            var X = sc.nextInt();
            var Y = sc.nextInt();
            var Z = sc.nextInt();
            pw.println(solve(N, A, B, X, Y, Z));
        }
        pw.flush();
    }

    static long solve(int N, int A, int B, int X, int Y, int Z) {
        // 1 < A < B => 1を使う
        // A < 1 < B => できるだけAを使って後は1で埋める
        // A < B < 1 => できるだけAとBで構成し、しょうがない部分は1を使う

        // 効率が A < Bとなるように調整する
        // Y/A > Z/B
        if( isCheap(B, Z, A, Y) ) {
            int t = B;
            B = A;
            A = t;
            t = Y;
            Y = Z;
            Z = t;
        }

        if( isCheap(1, X, A, Y) ) {
            return (long)N*X;

        } else if( isCheap(1, X, B, Z) ) {
            int q = N/A;
            int r = N%A;
            return (long)q*Y + (long)r*X;

        } else {
            // できるだけAとBで構成したいが良い組合わせがわからないので全部試す
            // 一つ固定するとあとは分かるので回す数が少ないほうを使う
            if( A < B ) {
                long ans = Long.MAX_VALUE;
                for (int i = 0; i <= Math.min(A-1, N/B); i++) {
                    int n = N;
                    long cost = 0;

                    n -= i*B;
                    cost += (long)i*Z;

                    int q = n/A;
                    int r = n%A;
                    cost += (long)q*Y;
                    cost += (long)r*X;

                    ans = Math.min(ans, cost);
                }
                return ans;

            } else {
                long ans = Long.MAX_VALUE;
                for (int i = 0; i <= Math.min(B-1, N/A); i++) {
                    int n = N;
                    long cost = 0;

                    n -= i*A;
                    cost += (long)i*Y;

                    int qab = (int)(n/((long)A*B));
                    int rab = (int)(n%((long)A*B));
                    cost += (long)qab*B*Y;
                    n = rab;

                    int q = n/B;
                    int r = n%B;
                    cost += (long)q*Z;
                    cost += (long)r*X;

                    ans = Math.min(ans, cost);
                }
                return ans;
            }
        }
    }

    static boolean isCheap(int dist1, int cost1, int dist2, int cost2) {
        // cost1/dist1 < cost2/dist2
        return cost1*dist2 < cost2*dist1;
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
