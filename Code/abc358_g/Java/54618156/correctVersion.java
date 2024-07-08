// package Codeforce;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    static Reader in = new Reader();

    public static void main(String[] args) {
//        int t = in.nextInt();
//        while (t-- > 0) {
//            solve();
//        }

        solve();

        out.close();
    }

    static long MOD = 998244353;
    static int[][] dirs = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};


    static void solve() {
        int m = in.nextInt();
        int n = in.nextInt();
        int k = in.nextInt();
        int sx = in.nextInt() - 1, sy = in.nextInt() - 1;
        long[][] grid = new long[m][n];
        for (int i = 0; i < m; i++) {
            grid[i] = in.nextLongArray(n);
        }
        long ans = grid[sx][sy] * k;
        queue.offer(new Node(sx, sy, 0, 0));
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int x = node.x, y = node.y;
            for (int[] d : dirs) {
                int nx = x + d[0], ny = y + d[1];
                if (node.k == k) {
                    continue;
                }
                if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                    long cur = node.s + grid[nx][ny] * (k - node.k);
                    if (cur > dp[nx][ny]) {
                        dp[nx][ny] = cur;
                        ans = Math.max(ans, cur);
                        queue.offer(new Node(nx, ny, node.k + 1, node.s + grid[nx][ny]));
                    }
                }
            }
        }

        out.println(ans);
    }

    static Deque<Node> queue = new ArrayDeque<>();

    record Node(int x, int y, int k, long s) {

    }

    static long[][] dp = new long[1001][1001];

}


class Reader {
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer st;

    boolean hasNext() {
        try {
            while (st == null || !st.hasMoreElements()) {
                st = new StringTokenizer(br.readLine());
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    String next() {
        try {
            while (st == null || !st.hasMoreElements()) {
                st = new StringTokenizer(br.readLine());
            }
        } catch (Exception ignored) {
        }
        return st.nextToken();
    }

    int nextInt() {
        return Integer.parseInt(next());
    }

    int[] nextIntArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = nextInt();
        }
        return arr;
    }

    long nextLong() {
        return java.lang.Long.parseLong(next());
    }

    long[] nextLongArray(int n) {
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = nextLong();
        }
        return arr;
    }

    String nextLine() {
        String s = "";
        try {
            s = br.readLine();
        } catch (Exception ignored) {
        }
        return s;
    }
}
