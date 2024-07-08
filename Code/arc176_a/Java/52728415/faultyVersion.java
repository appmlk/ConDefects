import java.util.*;


import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        int t = 1;
        while (t > 0) {
            solve();
            t--;
        }
        w.flush();
        w.close();
    }

    static int mod = (int) 1e9 + 7;

    public static void solve() throws IOException {
        int n = f.nextInt();
        int m = f.nextInt();
        int[] cnt = new int[n];
        for (int i = 0; i < m; i++) {
            int x = f.nextInt() - 1;
            int y = f.nextInt() - 1;
            cnt[(x + y) % n] ++;
        }
        Vector<Integer> c = new Vector<>();
        for (int i = 0; i < n; i++) {
            if (cnt[i] > 0) c.add(i);
        }
        for (int i = 0; i < n; i++) {
            if (cnt[i] == 0 && c.size() < m) c.add(i);
        }
        w.println(n * m);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int x = j;
                int y = (cnt[i] - j + n) % n;
                w.println((x + 1) + " " + (y+1));
            }
        }

    }

    public static class Node {
        int x, y;

        public Node(int x, int y) {
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


    static PrintWriter w = new PrintWriter(new OutputStreamWriter(System.out));
    static Input f = new Input(System.in);

    static class Input {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public Input(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() throws IOException {

            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public String nextLine() throws IOException {
            String str = null;
            str = reader.readLine();
            return str;
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        public Double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }
    }
}