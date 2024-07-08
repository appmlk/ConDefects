import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        long k = sc.nextLong();
        long[][] w = new long[n][n];
        for (int i = 0; i < n; i++) {
            //-1L表示顶点没有边相连
            Arrays.fill(w[i], -1L);
        }

        for (int i = 0; i < m; i++) {
            //下标从0开始
            int u = sc.nextInt() - 1;
            int v = sc.nextInt() - 1;
            w[u][v] = sc.nextLong();
            //无向图，顶点 u 和顶点 v 之间的边具有相同的权重。保证二维数组中的对称性。
            w[v][u] = w[u][v];
        }

        boolean[] visit = new boolean[n];//访问布尔类型的数组，表示所有顶点都未被访问过
        visit[0] = true;//0节点必须访问，也是从0开始
        //顶点数 n，模数 k，邻接矩阵 w，访问标记数组 visit，已访问的顶点数量 count，当前路径权值之和 sum
        long ans = dfs(n, k, w, visit, 1, 0L);
        System.out.println(ans);
    }

    //深度优先搜索（DFS）算法，寻找一条特定路径上的权值之和的最小值。
    private static long dfs(int n, long mod, long[][] w, boolean[] visit, int count, long sum) {
        //如果已经访问了所有的顶点,返回当前路径权值之和 sm 对取模值 mod 的结果。
        if (count == n) {
            return sum % mod;
        }

        long ans = mod;
        //遍历图中的顶点
        for (int i = 0; i < n; i++) {
            if (!visit[i]) continue;
            //遍历图中的其他未访问的顶点
            for (int j = 1; j < n; j++) {
                if (visit[j]) continue;
                if (w[i][j] < 0) continue;
                visit[j] = true;//将顶点 j 标记为已访问
                ans = Math.min(ans, dfs(n, mod, w, visit, count + 1, sum + w[i][j]));
                visit[j] = false;//恢复顶点 j 的访问状态，将其标记为未访问
            }
        }
        return ans;
    }
}
