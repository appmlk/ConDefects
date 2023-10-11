import java.io.*;
import java.util.StringTokenizer;

/**
 * E - Distance Sequence
 */
public class Main {

    static class FastReader {

        BufferedReader reader;

        StringTokenizer tokenizer;

        FastReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 1024);
            tokenizer = null;
        }

        // reads in the next string
        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        // reads in the next int
        int nextInt() {
            return Integer.parseInt(next());
        }

        // reads in the next long
        long nextLong() {
            return Long.parseLong(next());
        }

        // reads in the next double
        double nextDouble() {
            return Double.parseDouble(next());
        }

        void close() {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static FastReader in = new FastReader(System.in);

    private static PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    private static final int MAXN = 1010, MAXM = 5010, MOD = 998244353;

    private static long[][] dp = new long[MAXN][MAXM];

    private static int n, m, k;

    public static void main(String[] args) {
        n = in.nextInt();
        m = in.nextInt();
        k = in.nextInt();

        for (int i = 1; i <= m; i++) dp[1][i] = 1;
        for (int i = 2; i <= n; i++) {
            long pre = 0;
            for (int j = 1; j <= m; j++) {
                if (j - k >= 1) pre = (pre + dp[i - 1][j - k]) % MOD;
                dp[i][j] = pre;
            }
            
            long suf = 0;
            for (int j = m; j >= 1; j--) {
                int t = j + k + k == 0 ? 1 : 0;
                if (t <= m) suf = (suf + dp[i - 1][t]) % MOD;
                dp[i][j] = (dp[i][j] + suf) % MOD;
            }
        }

        long ans = 0;
        for (int i = 1; i <= m; i++) {
            ans = (ans + dp[n][i]) % MOD;
        }

        out.println(ans);
        out.flush();
        out.close();
        in.close();
    }
}
