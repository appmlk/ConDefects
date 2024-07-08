
import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int offset = (int) 1e9 + 2;
    static int[][] val = {
            {0, 1},
            {1, 0},
            {2, 1},
            {1, 2}
    };


    public static long calArea(long x, long y) {
        int col = val.length, row = val[0].length;
        long ans = (x / col) * (y / row) * 8;
        for (int i = 0; i < x % col; i++) {
            long sum = 0;
            for (int j = 0; j < row; j++) {
                sum += val[i][j];
            }
            ans += sum * (y / row);
        }
        for (int i = 0; i < y % row; i++) {
            long sum = 0;
            for (int j = 0; j < col; j++) {
                sum += val[j][i];
            }
            ans += sum * (x / col);
        }
        for (int i = 0; i < x % col; i++) {
            for (int j = 0; j < y % row; j++) {
                ans += val[i][j];
            }
        }
        return ans;
    }

    public static void solve() throws IOException{
        long a = in.nextInt();
        long b = in.nextInt();
        long c = in.nextInt();
        long d = in.nextInt();
        a += offset;
        b += offset;
        c += offset;
        d += offset;
        out.println(calArea(a, b) + calArea(c, d) - calArea(a, d) - calArea(c, b));

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