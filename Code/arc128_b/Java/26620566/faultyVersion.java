
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final StringJoiner joiner = new StringJoiner("\n");
        for (int i = 0; i < n; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();
            final int c = scanner.nextInt();
            int min = Integer.MAX_VALUE;

            if (a == b) {
                min = a;
            }
            if (b == c) {
                min = b;
            }
            if (c == a) {
                min = c;
            }
            if (a % 3 == b % 3) {
                min = Math.min(min, calc(a, b, c));
            }
            if (b % 3 == c % 3) {
                min = Math.min(min, calc(b, a, c));
            }
            if (c % 3 == a % 3) {
                min = Math.min(min, calc(c, a, b));
            }

            if (min == Integer.MAX_VALUE) {
                min = -1;
            }
            joiner.add(Integer.toString(min));
        }
        System.out.println(joiner);
    }

    private static int calc(final int a, final int b, final int c) { // a <= b
        if (a > b) {
            return calc(b, a, c);
        }
        return b;
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (final IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
