import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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
        B_ABGame solver = new B_ABGame();
        solver.solve(1, in, out);
        out.close();
    }

    static class B_ABGame {
        public void solve(int testNumber, FastScanner in, PrintWriter out) {
            long n = in.nextLong();
            long a = in.nextLong();
            long b = in.nextLong();
            out.println(f(n + 1, a, b) - f(a, a, b));
        }

        private long f(long n, long a, long b) {
            if (a <= b) {
                return n;
            }
            long full = n / a;
            long rem = n % a;
            long res = 0;
            res += full * b;
            res += Math.min(rem, b);
            return res;
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

        public long nextLong() {
            return Long.parseLong(next());
        }

    }
}

