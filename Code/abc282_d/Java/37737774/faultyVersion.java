import java.util.*;
import java.io.*;

public class Main {
    static MyScanner sc;
    static PrintWriter out;
    static {
        sc = new MyScanner();
        out = new PrintWriter(System.out);
    }
    public static Long bfs(Node[] g, int n, int s) {
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        g[s].color = 1;
        long a = 1, b = 0;
        while(!q.isEmpty()) {
            int u = q.poll();
            if(g[u].visited)
                continue;
            g[u].visited = true;
            for(int v : g[u].l) {
                if(g[v].color == g[u].color) {
                    return null;
                }
                else if(g[v].color == 0) {
                    if(g[u].color == 1)
                        b++;
                    else
                        a++;
                    g[v].color = g[u].color * -1;
                    q.add(v);
                }
            }
        }
        return (a * (a - 1) + b * (b - 1)) / 2;
    }
    public static void solve() {
        int n = sc.nextInt();
        int m = sc.nextInt();
        Node[] g = new Node[n];
        for(int i = 0; i < n; i++)
            g[i] = new Node();
        for(int i = 0; i < m; i++) {
            int u = sc.nextInt() - 1;
            int v = sc.nextInt() - 1;
            g[u].l.add(v);
            g[v].l.add(u);
        }
        int ans = n * (n - 1) / 2 - m;
        for(int i = 0; i < n; i++) {
            if(g[i].visited)
                continue;
            Long val = bfs(g, n, i);
            if(val == null) {
                out.println(0);
                return;
            }
            ans -= val;
        }
        out.println(ans);
    }
    public static void main(String[] args) {
        int t = 1;
        while(t-- > 0)
            solve();
        out.flush();
    }
}

class Pair {
    long x, y;
    Pair(long x, long y) {
        this.x = x;
        this.y = y;
    }
}

class Node {
    ArrayList<Integer> l;
    boolean visited;
    int color;
    Node() {
        l = new ArrayList<>();
        visited = false;
        color = 0;
    }
}

class MyScanner {
    BufferedReader br;
    StringTokenizer tok;
    MyScanner() {
        try { br = new BufferedReader(new InputStreamReader(System.in)); }
        catch(Exception e) { System.out.println(e); }
        tok = new StringTokenizer("");
    }
    public String next() {
        try {
            while(!tok.hasMoreTokens()) tok = new StringTokenizer(br.readLine());
        }
        catch(Exception e) { System.out.println(e); }
        return tok.nextToken();
    }
    public int nextInt() {
        return Integer.parseInt(next());
    }
    public long nextLong() {
        return Long.parseLong(next());
    }
    public double nextDouble() {
        return Double.parseDouble(next());
    }
}
