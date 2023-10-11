
import java.util.*;
import java.io.*;

public class Main {

    void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int[][] edges = new int[m + 1][3];
        for (int i = 1; i <= m; i++) {
            edges[i] = new int[] {in.nextInt(), in.nextInt(), in.nextInt()};
        }
        long[] res = new long[n + 1];
        int[] a = new int[k];
        for (int i = 0; i < k; i++) {
            a[i] = in.nextInt();
        }
        Arrays.fill(res, -1);
        res[1] = 0;
        for (int i = 0; i < k; i++) {
            int[] edge = edges[a[i]];
            if (res[edge[0]] != -1) {
                if (res[edge[1]] == -1 || res[edge[1]] > res[edge[0]] + edge[2]) {
                    res[edge[1]] = res[edge[0]] + edge[2];
                }
            }
        }
        out.println(res[n]);
    }

    FastScanner in;

    PrintWriter out;

    void run() {
        in = new FastScanner();
        out = new PrintWriter(System.out);
        solve();
        out.close();
    }

    class FastScanner {
        BufferedReader br;

        StringTokenizer st;

        public FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastScanner(String s) {
            try {
                br = new BufferedReader(new FileReader(s));
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        public String nextToken() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(nextToken());
        }

        public long nextLong() {
            return Long.parseLong(nextToken());
        }

        public double nextDouble() {
            return Double.parseDouble(nextToken());
        }
    }

    public static void main(String[] args) {
        new Main().run();
    }

}
