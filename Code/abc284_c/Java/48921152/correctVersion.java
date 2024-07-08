import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N, M;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        if (M == 0) {
            System.out.println(N);
            return;
        }

        // 1부터 시작
        graph = new List[N+1];
        for (int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(input.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph[from].add(to);
            graph[to].add(from);
        }

        // 1부터 시작
        boolean[] isVisit = new boolean[N+1];

        Queue<Integer> queue = new LinkedList<>();
        int cnt = 0;

        for (int i = 1; i <= N; i++) {
            if (isVisit[i])
                continue;
            queue.add(i);
            cnt++;
            isVisit[i] = true;

            while (!queue.isEmpty()) {
                int v = queue.poll();
                for (Integer nextV : graph[v]) {
                    if (isVisit[nextV])
                        continue;
                    queue.add(nextV);
                    isVisit[nextV] = true;
                }
            }
        }
        System.out.println(cnt);
    }
}

class Edge {
    int from, to;

    public Edge(int from, int to) {
        this.from = from;
        this.to = to;
    }
}

