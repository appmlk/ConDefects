import java.io.*;
import java.util.*;

public class Main {
    private static final long INF = Long.MAX_VALUE;
    public static void main(String[] args) {
        InputReader reader = new InputReader(System.in);
        PrintWriter writer = new PrintWriter(System.out, false);
        int N = reader.nextInt();
        long C = reader.nextLong();
        long[] A = new long[N];
        for (int i = 0; i < N; i++) {
            A[i] = reader.nextLong();
        }
        long[] B = new long[N];
        for (int i = 0; i < N; i++) {
            B[i] = reader.nextLong();
        }
        long[] dp = new long[(1 << N)];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int mask = 0; mask < (1 << N); mask++) {
            int bit = Integer.bitCount(mask);
            if (dp[mask] == INF) continue;
            for (int i = 0; i < N; i++) {
                if ((mask & (1 << i)) != 0) continue;
                long cost = C;
                int newMask = mask;
                for (int j = i, k = bit; j < N && k < N; j++, k++) {
                    if ((mask & (1 << j)) != 0) break;
                    cost += Math.abs(A[j] - B[k]);
                    newMask |= (1 << j);
                    dp[newMask] = Math.min(dp[newMask], dp[mask] + cost);
                }
            }
        }
        writer.println(dp[(1 << N) - 1] - C);
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