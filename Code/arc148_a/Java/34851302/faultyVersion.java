import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static int MOD = (int) (1e9 + 7);

    public static void main(String[] args) throws IOException {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        Reader in = new Reader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        solve(in, out);
        out.close();

    }

    static int add_self(int a, int b) {
        a += b;
        return a >= MOD ? a - MOD : a;
    }

    static int sub_self(int i, int i1) {
        i -= i1;
        return i >= 0 ? i : i + MOD;
    }

    static int[][] dp;

    private static void solve(Reader in, PrintWriter out) throws IOException {
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int gcd = 0;
        for (int i = 0; i < n - 1; i++) {
            gcd = calcGCD(gcd, Math.abs(arr[i + 1] - arr[i]));
        }
        if(gcd== 0 || gcd > 1) {
            out.print(1);
        } else {
            out.print(2);
        }
        out.close();
    }

    static int calcGCD(int a, int b) {
        if(a==0) return b;
        return calcGCD(b % a, b);
    }

    public static int[][] parents3(int[][] g, int root) {
        int n = g.length;
        int[] par = new int[n];
        Arrays.fill(par, -1);
        int[] depth = new int[n];
        int[] q = new int[n];
        q[0] = root;
        for (int p = 0, r = 1; p < r; p++) {
            int cur = q[p];
            for (int nex : g[cur]) {
                if (par[cur] != nex) {
                    q[r++] = nex;
                    par[nex] = cur;
                    depth[nex] = depth[cur] + 1;
                }
            }
        }
        return new int[][]{par, q, depth};
    }

    static int[][] packU(int n, int[] from, int[] to) {
        int[][] g = new int[n][];
        int[] p = new int[n];

        for (int i : from) {
            p[i]++;
        }
        for (int i : to) {
            p[i]++;
        }
        for (int i = 0; i < n; i++) {
            g[i] = new int[p[i]];
        }
        for (int i = 0; i < from.length; i++) {
            g[from[i]][--p[from[i]]] = to[i];
            g[to[i]][--p[to[i]]] = from[i];
        }
        return g;
    }

    static class Reader {
        BufferedReader br;
        StringTokenizer st;

        public Reader(InputStream in) {
            br = new BufferedReader(new
                    InputStreamReader(in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    static class Pair<T> {
        T a;
        T b;

        public Pair(T a, T b) {
            this.a = a;
            this.b = b;
        }

        T first() {
            return a;
        }

        T second() {
            return b;
        }
    }

    public static class SegmentTreeRMQL {
        public int M, H, N;
        public long[] st;

        public SegmentTreeRMQL(int n) {
            N = n;
            M = Integer.highestOneBit(Math.max(N - 1, 1)) << 2;
            H = M >>> 1;
            st = new long[M];
            Arrays.fill(st, 0, M, Long.MAX_VALUE);
        }

        public SegmentTreeRMQL(long[] a) {
            N = a.length;
            M = Integer.highestOneBit(Math.max(N - 1, 1)) << 2;
            H = M >>> 1;
            st = new long[M];
            for (int i = 0; i < N; i++) {
                st[H + i] = a[i];
            }
            Arrays.fill(st, H + N, M, Long.MAX_VALUE);
            for (int i = H - 1; i >= 1; i--) propagate(i);
        }

        public void update(int pos, long x) {
            st[H + pos] = x;
            for (int i = (H + pos) >>> 1; i >= 1; i >>>= 1) propagate(i);
        }

        private void propagate(int i) {
            st[i] = Math.min(st[2 * i], st[2 * i + 1]);
        }

        public long minx(int l, int r) {
            long min = Long.MAX_VALUE / 2;
            if (l >= r) return min;
            while (l != 0) {
                int f = l & -l;
                if (l + f > r) break;
                long v = st[(H + l) / f];
                if (v < min) min = v;
                l += f;
            }

            while (l < r) {
                int f = r & -r;
                long v = st[(H + r) / f - 1];
                if (v < min) min = v;
                r -= f;
            }
            return min;
        }

        public long min(int l, int r) {
            return l >= r ? 0 : min(l, r, 0, H, 1);
        }

        private long min(int l, int r, int cl, int cr, int cur) {
            if (l <= cl && cr <= r) {
                return st[cur];
            } else {
                int mid = cl + cr >>> 1;
                long ret = Long.MAX_VALUE;
                if (cl < r && l < mid) {
                    ret = Math.min(ret, min(l, r, cl, mid, 2 * cur));
                }
                if (mid < r && l < cr) {
                    ret = Math.min(ret, min(l, r, mid, cr, 2 * cur + 1));
                }
                return ret;
            }
        }

        public int firstle(int l, long v) {
            int cur = H + l;
            while (true) {
                if (st[cur] <= v) {
                    if (cur < H) {
                        cur = 2 * cur;
                    } else {
                        return cur - H;
                    }
                } else {
                    cur++;
                    if ((cur & cur - 1) == 0) return -1;
                    if ((cur & 1) == 0) cur >>>= 1;
                }
            }
        }

        public int lastle(int l, long v) {
            int cur = H + l;
            while (true) {
                if (st[cur] <= v) {
                    if (cur < H) {
                        cur = 2 * cur + 1;
                    } else {
                        return cur - H;
                    }
                } else {
                    if ((cur & cur - 1) == 0) return -1;
                    cur--;
                    if ((cur & 1) == 1) cur >>>= 1;
                }
            }
        }
    }

}