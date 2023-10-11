import java.io.*;
import java.util.StringTokenizer;

/**
 * @author weidq
 * @version 1.0
 * @date 2022-10-20 12:15
 * D - Bitmask
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

    public static void main(String[] args) {
        String s = in.next();
        char[] chars = s.toCharArray();
        long m = in.nextLong();
        int n = chars.length;

        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (chars[i] == '1') {
                long now = 1L << (n - i - 1);
                if (ans + now > m) {
                    ans = -1;
                    break;
                }
                ans += now;
            }
        }

        if (ans == -1) out.println(-1);
        else {
            for (int i = 0; i < n; i++) {
                if (chars[i] == '?') {
                    long now = 1L << (n - i - 1);
                    if (ans + now <= m) {
                        ans += now;
                    }
                }
            }

            out.println(ans);
        }

        out.flush();
        out.close();
        in.close();
    }
}
