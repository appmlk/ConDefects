import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Reader sc = new Reader();
        int n = sc.nextInt();
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for(int i = 0; i < n - 1; ++i){
            int x = sc.nextInt() - 1,  y = sc.nextInt() - 1;
            adj[x].add(y);
            adj[y].add(x);
        }
        int root1 = bfs(adj, 0), root2 = bfs(adj, root1);
        int[][] fa1 = new int[n][19], fa2 = new int[n][19];
        for(int i = 0; i < n; ++i){
            Arrays.fill(fa1[i], -1);
            Arrays.fill(fa2[i], -1);
        }
        dfs(adj, fa1, root1, -1);
        dfs(adj, fa2, root2, -1);
        int q = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (q-- > 0){
            int u = sc.nextInt() - 1, k = sc.nextInt();
            int ans = Math.max(getKthFather(fa1, u, k), getKthFather(fa2, u, k));
            sb.append(ans >= 0? ans + 1: -1).append('\n');
        }
        System.out.print(sb);
    }

    static int bfs(List<Integer>[] adj, int root){
        Queue<Integer> q = new LinkedList<>();
        q.add(root);
        boolean[] vis = new boolean[adj.length];
        vis[root] = true;
        int res = 0;
        while (!q.isEmpty()){
            int size = q.size();
            while (size-- > 0){
                res = q.poll();
                for(int x: adj[res]){
                    if(!vis[x]){
                        vis[x] = true;
                        q.add(x);
                    }
                }
            }
        }
        return res;
    }
    static void dfs(List<Integer>[] adj, int[][] fa, int root, int f){
        fa[root][0] = f;
        for(int i = 1; i < 19 && fa[root][i - 1] >= 0; ++i){
            fa[root][i] = fa[fa[root][i - 1]][i - 1];
        }
        for(int x: adj[root]){
            if(x == f) continue;
            dfs(adj, fa, x, root);
        }
    }

    static int getKthFather(int[][] fa, int cur, int k){
        for(int i = 18; i >= 0 && cur >= 0; --i){
            if((k & (1 << i)) > 0){
                cur = fa[cur][i];
            }
        }
        return cur;
    }
}

class Reader{
    InputStream stream = System.in;
    byte[] buf = new byte[4096];
    int cur = 0, len = 0;
    int read(){
        if(cur >= len){
            try {
                len = stream.read(buf);
                cur = 0;
            } catch (IOException e) {
                return -1;
            }
            if(len <= 0){
                return -1;
            }
        }
        return buf[cur++];
    }
    int nextInt(){
        int c = read();
        while (c < '0' || c > '9') c = read();
        int res = 0;
        while (c >= '0' && c <= '9'){ res = res * 10 + c - '0'; c = read();}
        return res;
    }
}
