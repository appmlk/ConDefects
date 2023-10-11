import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    public static void main(String[] args) throws Exception{
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        int n = Integer.valueOf(stringTokenizer.nextToken());
        int k = Integer.valueOf(stringTokenizer.nextToken());
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.valueOf(stringTokenizer.nextToken());
            }
        }
        int q = Integer.valueOf(br.readLine());
        for (int i = 0; i < q; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            long s = Long.valueOf(stringTokenizer.nextToken());
            long t = Long.valueOf(stringTokenizer.nextToken());
            boolean[] visited = new boolean[n];
            Deque<Integer> deque = new LinkedList<>();
            deque.add((int) ((s - 1) % n));
            boolean ok = false;
            int step = 0;
            while (!ok && !deque.isEmpty()){
                int size = deque.size();
                step++;
                for (int j = 0; !ok && j < size; j++) {
                    int poll = deque.poll();
                    if (visited[poll]){
                        continue;
                    }
                    visited[poll] = true;
                    for (int l = 0; !ok && l < graph[0].length; l++) {
                        if (graph[poll][l] == 1){
                            if (l == (t-1)%n){
                                ok = true;
                            }
                            deque.add(graph[poll][l]);
                        }
                    }
                }
            }
            if (ok) {
                out.println(step);
            }else {
                out.println(-1);
            }
        }
        out.close();
    }
}