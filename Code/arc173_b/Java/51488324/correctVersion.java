import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static boolean check(int x1, int y1, int x2, int y2, int x3, int y3) {
        // x1 - x2 / y1 - y2 == x1 - x3 / y1 - y3
        return (long) (y1 - y2) * (x1 - x3) == (long) (x1 - x2) * (y1 - y3);
    }

    public static void solve() throws IOException{
        int n = in.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = in.nextInt();
            y[i] = in.nextInt();
        }
        int tot = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int cnt = 2;
                for (int k = 0; k < n; k++) {
                    if (k == i || k == j) continue;
                    if (check(x[i], y[i], x[j], y[j], x[k], y[k])) cnt++;
                }
                tot = Math.max(tot, cnt);
            }
        }
        out.println(Math.min(n / 3, n - tot));
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