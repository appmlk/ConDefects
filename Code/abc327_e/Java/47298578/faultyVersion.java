
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    public static void solve() throws IOException{
        int n = in.nextInt();
        int[] p = new int[n+1];
        for (int i = 1; i <= n; i++) {
            p[i] = in.nextInt();
        }
        //dp[i][j] = max(dp[i][j], dp[i-1][j-1] * 0.9 /
        double[][] dp = new double[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-1] * 0.9 + p[i]);
            }
        }
        double mul = 1;
        double down = 0;
        double ans = Long.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            down += mul;
            ans = Math.max(ans, dp[n][i] / down - ((double) 1200 / Math.sqrt(i)));
            mul *= 0.9;
        }
        out.println(ans);
    }

    static boolean MULTI_CASE = false;

    public static void main(String[] args) throws IOException {
        if (MULTI_CASE) {
            int T = in.nextInt();
            for (int i = 0; i < T; ++i) {
                solve();
            }
        } else {
            solve();
        }
        out.close();
    }

    static InputReader in = new InputReader();
    static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

    static class InputReader {
        private StringTokenizer st;
        private BufferedReader bf;

        public InputReader() {
            bf = new BufferedReader(new InputStreamReader(System.in));
            st = null;
        }

        public String next() throws IOException {
            while (st == null || !st.hasMoreTokens()) {
                st = new StringTokenizer(bf.readLine());
            }
            return st.nextToken();
        }

        public String nextLine() throws IOException {
            return bf.readLine();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        public double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }
    }
}

/*
0.81 0.9 1
 */