import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Parameter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.concurrent.CompletableFuture;

import static java.util.Arrays.binarySearch;
import static java.util.Arrays.copyOfRange;
import static java.util.Arrays.fill;

public class Main {

    public static void main(String[] args) throws Exception {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;

        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);

        Task solver = new Task();
        solver.solve(in, out);
        out.close();
    }
}

class Task {

    int mod = 998244353;

    public void solve(InputReader in, PrintWriter out) throws Exception {
        int n = in.nextInt();
        long x = in.nextLong();
        long y = in.nextLong();
        long[] a = new long[n];
        long[] b = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextLong();
        }
        for (int i = 0; i < n; i++) {
            b[i] = in.nextLong();
        }
        long[] dp = new long[1 << n];
        Arrays.fill(dp, Long.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i < (1 << n); i++) {
            for (int j = 0; j < n; j++) if ((i & (1 << j)) != 0) {
                dp[i] = Math.min(dp[i], dp[i ^ (1 << j)] + Math.abs(b[Integer.bitCount(i) - 1] - a[j]) * x + count(i, Integer.bitCount(i) - 1) * y);
            }
        }
        out.println(dp[(1 << n) - 1]);

    }

    private long count(int b, int x) {
        int cnt = 0;
        for (int i = x + 1; i <= 20; i++) if ((b & (1 <<i)) != 0) {
            cnt++;
        }
        return cnt;
    }

}

class InputReader {

    private final BufferedReader reader;
    private StringTokenizer tokenizer;

    public InputReader(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream));
        tokenizer = null;
    }

    public String nextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String next() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            tokenizer = new StringTokenizer(nextLine());
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

}