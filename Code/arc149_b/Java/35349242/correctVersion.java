import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        FastScanner in = new FastScanner(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        B_TwoLISSum solver = new B_TwoLISSum();
        solver.solve(1, in, out);
        out.close();
    }

    static class B_TwoLISSum {
        public void solve(int testNumber, FastScanner in, PrintWriter out) {
            int n = in.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt() - 1;
            }
            int[] b = new int[n];
            for (int i = 0; i < n; i++) {
                b[i] = in.nextInt() - 1;
            }
            Integer[] o = new Integer[n];
            for (int i = 0; i < n; i++) {
                o[i] = i;
            }
            int[] c = new int[n];
            int ans = 0;
            for (int step = 0; step < 2; step++) {
                final int[] fa = a;
                Arrays.sort(o, (u, v) -> Integer.compare(fa[u], fa[v]));
                for (int i = 0; i < n; i++) {
                    c[i] = b[o[i]];
                }
                ans = Math.max(ans, n + lis(c));
                int[] t = a;
                a = b;
                b = t;
            }
            out.println(ans);
        }

        private int lis(int[] a) {
            int n = a.length;
            int[] lis = new int[n];
            int[] smallest = new int[n];
            int k = 0;
            for (int i = 0; i < n; i++) {
                int l = -1;
                int r = k;
                while (r - l > 1) {
                    int m = (l + r) / 2;
                    if (smallest[m] <= a[i]) {
                        l = m;
                    } else {
                        r = m;
                    }
                }
                smallest[r] = a[i];
                if (r == k) {
                    ++k;
                }
                lis[i] = r + 1;
            }
            int max = 0;
            for (int x : lis) {
                max = Math.max(max, x);
            }
            return max;
        }

    }

    static class FastScanner {
        private BufferedReader in;
        private StringTokenizer st;

        public FastScanner(InputStream stream) {
            try {
                in = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
            } catch (Exception e) {
                throw new AssertionError();
            }
        }

        public String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    String rl = in.readLine();
                    if (rl == null) {
                        return null;
                    }
                    st = new StringTokenizer(rl);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }
}

