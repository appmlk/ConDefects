import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
        HashSet<String> edge = new HashSet<>();
        for (int i = 0; i < n; i++) {
            arr.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            arr.get(x).add(y);
            edge.add(x + " " + y);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            boolean[] visited = new boolean[n];
            Queue<Integer> q = new LinkedList<>();
            q.add(i);
            visited[i] = true;
            while (!q.isEmpty()){
                int x = q.poll();
                for (int j = 0; j < arr.get(x).size(); j++) {
                    int y = arr.get(x).get(j);
                    if (visited[y]) continue;
                    visited[y] = true;
                    q.add(y);
                    ans++;
                }
            }
        }
        out.println(ans);
        out.close();
        f.close();
    }
}
