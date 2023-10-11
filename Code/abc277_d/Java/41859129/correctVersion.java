import com.sun.source.tree.WhileLoopTree;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    static StringTokenizer st;
    static int MOD = (int) 1e9+7;
    public static void main(String[] args) throws IOException {
        int n = readInt(), m = readInt();
        long sum = 0;
        int[] A = new int[n];
        Map<Integer, Integer> cnt = new HashMap<>();
        TreeSet<Integer> ts = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            A[i] = readInt(); sum+=A[i];
            ts.add(A[i]);
            cnt.put(A[i], cnt.getOrDefault(A[i], 0) + 1);
        }
        int cur = 0;
        long zero = 0;
        while (true) {
            if (cnt.getOrDefault(cur, 0) > 0) {
                zero += (long) cnt.get(cur) * cur;
                cur++;
            } else {
                break;
            }
        }
        long ans = sum - zero;
        for (int v : ts) {
            if (v < cur) continue;
            cur = v;
            long tmp = 0;
            while (true) {
                if (cnt.getOrDefault(cur, 0) > 0) {
                    tmp += (long) cnt.get(cur) * cur;
                    cur++;
                } else {
                    break;
                }
            }
            if (cur == m) tmp += zero;
            ans = Math.min(ans, sum - tmp);
        }
        System.out.println(ans);
        /*int n = readInt();
        int[] deg = new int[n + 1];
        Set<Integer>[] adj = new Set[n + 1];
        for (int i = 1; i <= n; i++) adj[i] = new HashSet<>();
        for (int i = 0; i < n - 1; i++) {
            int u = readInt(), v = readInt();
            deg[u]++; deg[v]++;
            adj[u].add(v); adj[v].add(u);
        }
        List<Integer> ans = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> vis = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            if (deg[i] == 1) {
                q.add(i);
            }
        }
        if (q.isEmpty()) throw new IOException();
        while (!q.isEmpty()) {
            int sz = q.size();
            while (sz-->0) {
                int u = q.poll();
                int mid = adj[u].stream().findFirst().get();
                if (vis.contains(mid) || vis.contains(u)) continue;
                vis.add(mid); vis.add(u);
                ans.add(adj[mid].size());
                for (int v : adj[mid]) {
                    deg[v]--; vis.add(v);
                    if (deg[v] == 1) {
                        adj[v].remove(mid);
                        int nxt = adj[v].stream().findFirst().get();
                        deg[nxt]--;
                        if (!vis.contains(nxt)) {
                            q.add(nxt);
                        }
                    }
                }
                adj[u].clear();
                adj[mid].clear();
            }
        }
        Collections.sort(ans);
        for (int v : ans) {
            System.out.print(v + " ");
        }
        System.out.println();*/
    }

    static String next() throws IOException {
        while (st == null || !st.hasMoreTokens())
            st = new StringTokenizer(br.readLine().trim());
        return st.nextToken();
    }
    static long readLong() throws IOException {
        return Long.parseLong(next());
    }
    static int readInt() throws IOException {
        return Integer.parseInt(next());
    }
    static double readDouble() throws IOException {
        return Double.parseDouble(next());
    }
    static char readCharacter() throws IOException {
        return next().charAt(0);
    }
    static String readLine() throws IOException {
        return br.readLine().trim();
    }
    static int readLongLineInt() throws IOException{
        int x = 0, c;
        while((c = br.read()) != ' ' && c != '\n')
            x = x * 10 + (c - '0');
        return x;
    }
}
