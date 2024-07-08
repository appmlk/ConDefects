import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static long inf = (long) 2e18;
    static int N;
    static int[] vis;
    static LinkedList<Integer> ans;
    static Vector<Node>[] g;
    static int[] lamp;
    static int res = 0;
    static int K;

    public static void main(String[] args) throws IOException {
        N = f.nextInt();
        int M = f.nextInt();
        K = f.nextInt();
        g = new Vector[N+1];
        lamp = new int[N+1];
        vis = new int[N+1];
        ans = new LinkedList<>();
        for (int i = 0; i < N + 1; i++) {
            g[i] = new Vector<>();
        }
        for (int i = 1; i <= M; i++) {
            int x = f.nextInt();
            int y = f.nextInt();
            g[x].add(new Node(y, i));
            g[y].add(new Node(x, i));
        }
        for (int i = 1; i <= N; i++) {
            if (vis[i] == 0)
                dfs(i);
        }
        if (K % 2 != 0){
            w.println("No");
        }else {
            w.println("Yes");
            w.println(ans.size());
            for (Integer o : ans) {
                w.print (o + " ");
            }

        }
        w.flush();
        w.close();
        br.close();
    }
    public static void dfs(int x){
        vis[x] = 1;
        for (int i = 0; i < g[x].size(); i++) {
            Node cur = g[x].get(i);
            int id = cur.id;
            int y = cur.to;
            if (res == K) return;
            if (vis[y] == 1) continue;
            dfs(y);
            if(lamp[y] == 0 && res < K){
                res -= lamp[x] + lamp[y];
                lamp[x] ^= 1;
                lamp[y] ^= 1;
                res += lamp[x] + lamp[y];
                ans.add(id);
            }

        }
    }

    public static class Node{
        int to;
        int id;
        public Node(int to, int id){
            this.to = to;
            this.id = id;
        }
    }

    static PrintWriter w = new PrintWriter(new OutputStreamWriter(System.out));
    static Input f = new Input(System.in);
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static class Input {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public Input(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public String nextLine() {
            String str = null;
            try {
                str = reader.readLine();
            } catch (IOException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }
            return str;
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public Double nextDouble() {
            return Double.parseDouble(next());
        }

        public BigInteger nextBigInteger() {
            return new BigInteger(next());
        }
    }
}