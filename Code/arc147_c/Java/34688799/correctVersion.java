import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
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
        C_MinDiffSum solver = new C_MinDiffSum();
        solver.solve(1, in, out);
        out.close();
    }

    static class C_MinDiffSum {
        public void solve(int testNumber, FastScanner in, PrintWriter out) {
            int n = in.nextInt();
            int[] ls = new int[n];
            int[] rs = new int[n];
            for (int i = 0; i < n; i++) {
                ls[i] = in.nextInt();
                rs[i] = in.nextInt();
            }
            Arrays.sort(ls);
            Arrays.sort(rs);

            int[] coef = new int[n];
            coef[0] = -(n - 1);
            for (int i = 1; i < n; i++) {
                coef[i] = coef[i - 1] + 2;
            }
            long[] sumCoef = new long[n + 1];
            for (int i = 0; i < n; i++) {
                sumCoef[i + 1] = sumCoef[i] + coef[i];
            }

            long[] sl = new long[n + 1];
            long[] sr = new long[n + 1];
            for (int i = 0; i < n; i++) {
                sr[i + 1] = sr[i] + (long) coef[i] * rs[i];
            }
            for (int i = 0; i < n; i++) {
                sl[i + 1] = sl[i] + (long) coef[n - 1 - i] * ls[n - 1 - i];
            }

            long ans = Long.MAX_VALUE;
            for (int who = 0; who < 2 * n; who++) {
                int x = who < n ? ls[who] : rs[who - n];

                int numR = numSmaller(rs, x);
                int numL = numGreater(ls, x);

                long cur = sl[numL] + sr[numR];
                cur += (sumCoef[n - numL] - sumCoef[numR]) * x;
                ans = Math.min(ans, cur);
            }
            out.println(ans);
        }

        private int numSmaller(int[] a, int x) {
            int l = -1;
            int r = a.length;
            while (r - l > 1) {
                int m = (l + r) / 2;
                if (a[m] < x) {
                    l = m;
                } else {
                    r = m;
                }
            }
            return r;
        }

        private int numGreater(int[] a, int x) {
            int l = -1;
            int r = a.length;
            while (r - l > 1) {
                int m = (l + r) / 2;
                if (a[m] > x) {
                    r = m;
                } else {
                    l = m;
                }
            }
            return a.length - r;
        }

    }

    static class FastScanner {
        private BufferedReader in;
        private StringTokenizer st;

        public FastScanner(InputStream stream) {
            in = new BufferedReader(new InputStreamReader(stream));
        }

        public String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(in.readLine());
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

