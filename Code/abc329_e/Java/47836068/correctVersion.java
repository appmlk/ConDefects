
import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void solve() throws IOException{
        int n = in.nextInt();
        int m = in.nextInt();
        boolean[][] dp = new boolean[n+1][m+1];
        dp[0][0] = true;
        String s = in.nextLine();
        String t = in.nextLine();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s.charAt(i-1) == t.charAt(j-1)) {
                    dp[i][j] |= dp[i-1][j-1] | dp[i-1][m];
                }
                if (s.charAt(i-1) == t.charAt(0)) {
                    dp[i][1] |= dp[i-1][j];
                }
            }
        }
        out.println(dp[n][m] ? "Yes" : "No");
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

 */