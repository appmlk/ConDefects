import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n = 3;
    static int[][] a, vis;

    public static int check() {
        for (int i = 0; i < n; i++) {
            if (vis[i][0] != -1 && vis[i][0] == vis[i][1] && vis[i][1] == vis[i][2]) return vis[i][0];
            if (vis[0][i] != -1 && vis[0][i] == vis[1][i] && vis[1][i] == vis[2][i]) return vis[0][i];
        }
        if (vis[0][0] != -1 && vis[0][0] == vis[1][1] && vis[1][1] == vis[2][2]) return vis[0][0];
        if (vis[0][2] != -1 && vis[0][2] == vis[1][1] && vis[1][1] == vis[2][0]) return vis[0][2];
        int sum1 = 0, sum2 = 0;
        int tot = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (vis[i][j] == 0) sum1 += a[i][j];
                else if (vis[i][j] == 1) sum2 += a[i][j];
                else tot++;
            }
        }
        if (tot == 0) return sum1 > sum2 ? 0 : 1;
        return 2;
    }

    public static int dfs(int op) {
        int x = check();
        if (x != 2) return x;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (vis[i][j] != -1) continue;
                vis[i][j] = op;
                int ans = dfs(op^1);
                vis[i][j] = -1;
                if (ans == op) return ans;
            }
        }
        return op == 0 ? 1 : 0;
    }

    public static void solve() throws IOException{
        a = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = in.nextInt();
            }
        }
        vis = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(vis[i], -1);
        }
        if (dfs(0) == 0) out.println("Takahashi");
        else out.println("Aoki");
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