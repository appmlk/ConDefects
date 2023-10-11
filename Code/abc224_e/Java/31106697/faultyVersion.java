import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        InputReader reader = new InputReader(System.in);
        PrintWriter writer = new PrintWriter(System.out, false);
        int H = reader.nextInt();
        int W = reader.nextInt();
        int N = reader.nextInt();
        int[] R = new int[N];
        int[] C = new int[N];
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            R[i] = reader.nextInt() - 1;
            C[i] = reader.nextInt() - 1;
            A[i] = reader.nextInt();
        }
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            map.computeIfAbsent(A[i], x -> new ArrayList<>()).add(i);
        }
        int[] dp = new int[N];
        int[] row = new int[H];
        int[] col = new int[W];
        for (List<Integer> list : map.values()) {
            for (int i : list) {
                dp[i] = Math.max(row[R[i]], col[C[i]]);
            }
            for (int i : list) {
                row[R[i]] = Math.max(row[R[i]], dp[i] + 1);
                col[C[i]] = Math.max(col[C[i]], dp[i] + 1);
            }
        }
        for (int i = 0; i < N; i++) {
            writer.println(dp[i]);
        }
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