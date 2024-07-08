
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {


    public static void main(String[] args) throws IOException {
        int n = in.nextInt();
        int h = in.nextInt();
        int[] dis = new int[n+1];
        for (int i = 1; i <= n; i++) {
            dis[i] = in.nextInt();
        }
        int[] p = new int[n];
        int[] f = new int[n];
        for (int i = 1; i < n; i++) {
            p[i] = in.nextInt();
            f[i] = in.nextInt();
        }
        int[][][] dp = new int[n+1][h+1][h+1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= h; j++) {
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
            }
        }
        Arrays.fill(dp[0][h], 0);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= h; j++) {
                for (int k = 0; k <= h; k++) {
                    if (dp[i][j][k] == Integer.MAX_VALUE) continue;
                    int d = dis[i+1] - dis[i];
                    //不加油
                    if (j - d >= 0 && k + d <= h) {
                        dp[i+1][j-d][k+d] = Math.min(dp[i+1][j-d][k+d], dp[i][j][k]);
                    }
                    //去时加油
                    if (Math.min(h, j+f[i]) - d >= 0 && k + d <= h) {
                        dp[i+1][Math.min(h, j+f[i])-d][k+d] = Math.min(dp[i+1][Math.min(h, j+f[i])-d][k+d], dp[i][j][k]+p[i]);
                    }
                    /*
                        回时加油
                        设i+1处为x油
                        math.min(x - d + f[i], h) = k
                        k != h  x - d + f[i]唯一？？
                        k = h x可以为多
                        k - f[i] + d = x
                     */
                    if (j - d < 0) continue;
                    if (k < h) {
                        if (k - f[i] + d <= h && k - f[i] >= 0) {
                            dp[i+1][j-d][k-f[i]+d] = Math.min(dp[i+1][j-d][k-f[i]+d], dp[i][j][k]+p[i]);
                        }
                    }else {
                        for (int l = k - f[i] + d; l <= k; l++) {
                            dp[i+1][j-d][l] = Math.min(dp[i+1][j-d][l], dp[i][j][k]+p[i]);
                        }
                    }
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= h; i++) {
            ans = Math.min(ans, dp[n][i][i]);
        }
        if (ans == Integer.MAX_VALUE) out.println(-1);
        else out.println(ans);

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
0 : 1
1 : 2 3
2 : 4 5
3 : 6

0 : 2
1 : 1 4 5
2 : 6 3
3 :

size[2][1] = 4 5

dp[2] += (dp[1] - dp[0])


 0
1 2
 */