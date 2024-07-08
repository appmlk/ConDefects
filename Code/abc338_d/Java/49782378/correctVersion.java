import java.io.*;
import java.util.*;

public class Main {
    static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

    static long[] d;

    public static void main(String[] args) {
        AReader sc = new AReader();
        int n = sc.nextInt(), m = sc.nextInt();
        int[] x = new int[m + 1];
        d = new long[n + 2];
        for (int i = 1; i <= m; i++) {
            x[i] = sc.nextInt();
        }
        long ans = 0;
        for (int i = 1; i <= m - 1; i++) {
            int from = x[i], to = x[i + 1];
            if (from > to) {
                int temp = from;
                from = to;
                to = temp;
            }
            if (to - from <= n + from - to) {
                int dis = n + from - to - to + from;
                d[from] += dis;
                d[to] -= dis;
                ans += to - from;
            } else {
                int dis = to - from - n - from + to;
                d[1] += dis;
                d[from] -= dis;
                d[to] += dis;
                d[n + 1] -= dis;
                ans += n + from - to;
            }
        }
        long mn = Long.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            d[i] += d[i - 1];
            mn = Math.min(mn, d[i]);
        }
        pw.println(ans + mn);
        pw.close();
    }

    static class AReader {
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private StringTokenizer tokenizer = new StringTokenizer("");

        private String innerNextLine() {
            try {
                return br.readLine();
            } catch (IOException ex) {
                return null;
            }
        }

        public boolean hasNext() {
            while (!tokenizer.hasMoreTokens()) {
                String nextLine = innerNextLine();
                if (nextLine == null) {
                    return false;
                }
                tokenizer = new StringTokenizer(nextLine);
            }
            return true;
        }

        public String nextLine() {
            tokenizer = new StringTokenizer("");
            return innerNextLine();
        }

        public String next() {
            hasNext();
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

    }
}
