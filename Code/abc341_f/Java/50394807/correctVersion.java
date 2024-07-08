import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public Node[] nodes = null;
    public class Node implements Comparable<Node> {
        public int W;
        public int count;

        public long cost = -1;
        public LinkedList<Node> nexts = new LinkedList<Node>();

        public Node(int count) {
            this.count = count;
        }

        @Override
        public int compareTo(Main.Node o) {
            return this.W - o.W;
        }
    }
    public Main() {

    }


    /**
     * ナップサック問題（各商品1個限定）
     * 最大容量以下で、合計の価値が最大になるように商品を選んだ時の価値の最大値
     * @param N 商品数
     * @param MAX 最大容量
     * @param weight 商品の重さ
     * @param worth 商品の価値
     * @return 価値の最大値
     */
    public static long knapsack(int N, int MAX, int[] weight, long[] worth) {
        if (N == 0) {
            return 0;
        }
        long dp[][] = new long[N][MAX+1];
        for (int i=0; i<=MAX; i++) {
            dp[0][i] = -1;
        }
        dp[0][0] = 0;
        if (weight[0] <= MAX) {
            dp[0][weight[0]] = worth[0];
        }
        for (int i=1; i<N; i++) {
            int wei = weight[i];
            long wor = worth[i];
            for (int s=0; s<=MAX; s++) {
                dp[i][s] = dp[i-1][s];
            }
            for (int s=0; s<=MAX; s++) {
                if (dp[i-1][s] != -1) {
                    if (s + wei <= MAX) {
                        dp[i][s+wei] = Math.max(dp[i-1][s] + wor, dp[i][s+wei]);
                    }
                }
            }
        }
        long max_worth = 0;
        for (int i=0; i<=MAX; i++) {
            if (max_worth < dp[N-1][i]) {
                max_worth = dp[N-1][i];
            }
        }
        return max_worth;
    }

    public void solve() {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();

        nodes = new Node[N];
        for (int i=0; i<N; i++){
            nodes[i] = new Node(0);
        }
        for (int i=0; i<M; i++) {
            int u = in.nextInt()-1;
            int v = in.nextInt()-1;
            nodes[u].nexts.add(nodes[v]);
            nodes[v].nexts.add(nodes[u]);
        }
        for (int i=0; i<N; i++) {
            int W = in.nextInt();
            nodes[i].W = W;
        }
        for (int i=0; i<N; i++) {
            int A = in.nextInt();
            nodes[i].count = A;
        }
        in.close();

        Node[] sorted = new Node[N];
        for (int i=0; i<N; i++) {
            sorted[i] = nodes[i];
        }
        Arrays.sort(sorted);

        for (Node node : sorted) {
            int n = 0;
            int[] wei = new int[node.nexts.size()];
            long[] wor = new long[node.nexts.size()];
            
            for (Node next : node.nexts) {
                if (next.cost == -1) {
                    continue;
                }
                if (next.W < node.W) {
                    wei[n] = next.W;
                    wor[n] = next.cost;
                    n++;
                }
            }
            long max = knapsack(n, node.W-1, wei, wor);
            node.cost = max+1;
        }

        for (Node node : nodes) {
            System.err.print(node.cost + " ");
        }
        System.err.println();

        long sum = 0;
        for (Node node : nodes) {
            sum += node.count * node.cost;
        }

        System.out.println(sum);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}