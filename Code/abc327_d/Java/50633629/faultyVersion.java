import java.io.*;
import java.util.*;


public class Main {
    static int inf = (int) 1e9;
    static int mod = (int) 1e9 + 7;
    static int mod9 = 998244353;
    static Vector<Integer> [] g;
    static HashSet<Node> cnt;
    static boolean st = true;

    public static void main(String[] args) throws IOException {
        int n = input.nextInt();
        int m = input.nextInt();
        cnt = new HashSet<Node>();

         g = new Vector[n + 1];
        for (int i = 0; i < n + 1; i++) {
            g[i] = new Vector<>();
        }
        int[] a = new int[m];
        for (int i = 0; i < m; i++) {
            a[i] = input.nextInt();
        }
        HashSet<Integer> A = new HashSet<>();
        HashSet<Integer> B = new HashSet<>();

        for (int i = 0; i < m; i++) {
            int num = input.nextInt();
            g[a[i]].add(num);
            g[num].add(a[i]);
        }
        for (int i = 1; i <= n; i++) {
            if (!A.contains(i) && !A.contains(i)){
                A.add(i);
                add(i, B, A);
            }
                
        }


        if (A.size() < B.size()){
            for (Integer integer : A) {
                if (B.contains(integer)){
                    st = false;
                    break;
                }
            }
        }else {
            for (Integer integer : B) {
                if (A.contains(integer)){
                    st = false;
                    break;
                }
            }
        }
        if (st) out.println("Yes");
        else out.print("No");

        out.flush();
        out.close();
        br.close();
    }
    public static void add(int n, HashSet<Integer> A, HashSet<Integer> B){
        if (!st) return;
        for (int i = 0; i < g[n].size(); i++) {
            if (cnt.contains(new Node(n, g[n].get(i)))) continue;
            int x = g[n].get(i);
            if (x == n){
                st = false;
                return;
            }
            if (B.contains(x)){
                st = false;
                return;
            }
            if (A.contains(x)) continue;
            A.add(x);
            cnt.add(new Node(n, x));
            add(x, B, A);
        }
    }

    public static class Node{
        int x;
        int y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x && y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
    static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
    static Input input = new Input(System.in);
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


        public int nextInt() {
            return Integer.parseInt(next());
        }
        public long nextLong() {
            return Long.parseLong(next());
        }
    }
}
