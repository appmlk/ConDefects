import java.util.*;

public class Main {
    static int N = (int)1e5;
    static long[] val = new long[N];
    static long[] sum = new long[N];
    static long[] dp = new long[N];
    static List<List<Integer>> g = new ArrayList<>();
    static long ans = (long)1L << 62, total;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        for (int i = 0; i < n; i++) g.add(new ArrayList<Integer>());
        for (int i = 0; i < n - 1; i++) {
            int u = sc.nextInt() - 1, v = sc.nextInt() - 1;
            g.get(u).add(v);
            g.get(v).add(u);
        }
        for (int i = 0; i < n; i++) {
            val[i] = sc.nextLong();
            total += val[i];
        }

        dfs(0, -1);
        dfs2(0, -1);

        System.out.println(ans);
    }

    public static void dfs(int u, int par) {
        for (var v : g.get(u)) {
            if (v == par) continue;
            dfs(v, u);
            sum[u] += sum[v];
            dp[u] += dp[v];
        }
        dp[u] += sum[u];
        sum[u] += val[u];
    }

    public static void dfs2(int u, int par) {
        ans = Math.min(ans, dp[u]);
        for (var v : g.get(u)) {
            if (v == par) continue;
            dp[v] = dp[u] - sum[v] + (total - sum[v]);
            dfs2(v, u);
        }
    }

}