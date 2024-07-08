import java.util.*;

public class Main {

    private static int dfs(int v, int[] vis, int[] g) {
        vis[v] = 1;
        int res = -1;
        if (vis[g[v]] == 0)
            res = dfs(g[v], vis, g);
        vis[v] = 2;
        if (vis[g[v]] == 1)
            res = v;
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] g = new int[n];

        for (int i = 0; i < n; i++) {
            g[i] = sc.nextInt() - 1;
        }
        int [] cost = new int[n];
        for (int i = 0; i < n; i++)
            cost[i] = sc.nextInt();
        int[] vis = new int[n];
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int cycleStart = -1;
            if (vis[i] == 0) {
                cycleStart = dfs(i, vis, g);
                if (cycleStart != -1) {
                    int c = Integer.MAX_VALUE;
                    int st = cycleStart;
                    do {
                        c = Math.min(c, cost[st]);
                        st = g[st];
                    } while (st != cycleStart);
                    ans += c;
                }
            }
        }
        System.out.println(ans);
    }
}
