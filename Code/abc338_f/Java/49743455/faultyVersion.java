import java.io.*;
import java.util.*;

public class Main {
    private static final int INF = 1000000000;
    public static void main(String[] args) {
        InputReader reader = new InputReader(System.in);
        PrintWriter writer = new PrintWriter(System.out, false);
        int N = reader.nextInt();
        int M = reader.nextInt();
        int[][] dist = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }
        for (int i = 0; i < M; i++) {
            int u = reader.nextInt() - 1;
            int v = reader.nextInt() - 1;
            int w = reader.nextInt();
            dist[u][v] = w;
        }
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        int[][] dp = new int[(1 << N)][N];
        for (int[] row : dp) Arrays.fill(row, INF);
        for (int i = 0; i < N; i++) {
            dp[(1 << i)][i] = 0;
        }
        for (int mask = 0; mask < (1 << N); mask++) {
            for (int i = 0; i < N; i++) {
                if ((mask & (1 << i)) != 0) {
                    for (int j = 0; j < N; j++) {
                        if ((mask & (1 << j)) != 0) {
                            dp[mask][i] = Math.min(dp[mask][i], dp[mask ^ (1 << i)][j] + dist[i][j]);
                        }
                    }
                }
            }
        }
        int result = INF;
        for (int i = 0; i < N; i++) {
            result = Math.min(result, dp[(1 << N) - 1][i]);
        }
        writer.println(result == INF ? "No" : result);
        writer.close();
        System.exit(0);
    }
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;
        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }
        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }
        public int nextInt() {
            return Integer.parseInt(next());
        }
        public long nextLong() {
            return Long.parseLong(next());
        }
        public double nextDouble() {
            return Double.parseDouble(next());
        }
        public String nextLine() {
            String str = "";
            try {
                str = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}