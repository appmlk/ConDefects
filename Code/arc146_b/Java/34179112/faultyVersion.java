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
        B_PlusAndAnd solver = new B_PlusAndAnd();
        solver.solve(1, in, out);
        out.close();
    }

    static class B_PlusAndAnd {
        public void solve(int testNumber, FastScanner in, PrintWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();
            int k = in.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
            }

            int ans = 0;
            int[] cost = new int[n];
            Integer[] o = new Integer[n];
            for (int i = 0; i < n; i++) {
                o[i] = i;
            }
            for (int bit = 30; bit >= 0; bit--) {
                for (int i = 0; i < n; i++) {
                    int x = a[i];
                    int y = ans | (1 << bit);
                    // Find the smallest number >= x that has all bits from y set.
                    int r = 0;
                    boolean eq = true;
                    for (int j = 30; j >= 0; j--) {
                        int bitX = (x >> j) & 1;
                        int bitY = (y >> j) & 1;
                        int bitR = 0;
                        if (bitY == 1 || (eq && bitX == 1)) {
                            bitR = 1;
                        }
                        eq = eq && bitR > bitX;
                        r |= bitR << j;
                    }
                    cost[i] = r - x;
                }
                Arrays.sort(o, (u, v) -> Integer.compare(cost[u], cost[v]));
                long totalCost = 0;
                for (int i = 0; i < k; i++) {
                    totalCost += cost[o[i]];
                }
                if (totalCost <= m) {
                    ans |= 1 << bit;
                }
            }
            out.println(ans);
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

