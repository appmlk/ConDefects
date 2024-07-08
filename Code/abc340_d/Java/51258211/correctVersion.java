import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

class Node {
    int u;
    long d;

    public Node(int _u, long _d) {
        u = _u;
        d = _d;
    }
}

public class Main {
    private static int n;
    private static int[] head, edge, ne, cost;
    private static long[] dis;
    private static int idx;

    private static void graph_init(int n, int m) {
        idx = 1;
        head = new int[n];
        dis = new long[n];
        edge = new int[m];
        ne = new int[m];
        cost = new int[m];
    }

    private static void add(int u, int v, int w) {
        edge[idx] = v;
        cost[idx] = w;
        ne[idx] = head[u];
        head[u] = idx++;
    }

    private static long dijkstra() {
        Arrays.fill(dis, 0x3f3f3f3f3f3f3f3fl);
        dis[1] = 0;
        PriorityQueue<Node> heap = new PriorityQueue<>((x, y) -> Long.compare(x.d, y.d));
        heap.add(new Node(1, 0));
        while (!heap.isEmpty()) {
            Node p = heap.remove();
            int u = p.u;
            long d = p.d;
            if (d > dis[u])
                continue;
            for (int i = head[u]; i > 0; i = ne[i]) {
                int v = edge[i];
                long nd = d + cost[i];
                if (nd < dis[v]) {
                    dis[v] = nd;
                    heap.add(new Node(v, nd));
                }
            }
        }
        return dis[n];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        graph_init(n + 1, 2 * n);
        for (int i = 1, a, b, x; i < n; i++) {
            a = in.nextInt();
            b = in.nextInt();
            x = in.nextInt();
            add(i, i + 1, a);
            add(i, x, b);
        }
        System.out.println(dijkstra());
        in.close();
    }
}