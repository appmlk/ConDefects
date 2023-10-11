import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Kattio io = new Kattio();
        int n = io.nextInt(), t = io.nextInt();
        int[] c = new int[n + 1], r = new int[n + 1];
        for (int i = 1; i <= n; i++) c[i] = io.nextInt();
        for (int i = 1; i <= n; i++) r[i] = io.nextInt();
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (c[i] == t) {
                if (r[i] > r[ans] || ans == 0) ans = i;
            }
        }
        if (ans != 0) io.println(ans);
        else {
            for (int i = 1; i <= n; i++) {
                if (r[i] > r[ans]) ans = i;
            }
            io.println(ans);
        }
        io.close();
    }

    private static class Kattio extends PrintWriter {
        private BufferedReader br;
        private StringTokenizer st;

        private String line, token;

        public Kattio() {
            this(System.in, System.out);
        }

        public Kattio(InputStream i, OutputStream o) {
            super(o);
            br = new BufferedReader(new InputStreamReader(i));
        }

        public boolean hasMoreTokens() {
            return peekToken() != null;
        }

        public String next() {
            return nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(nextToken());
        }

        public double nextDouble() {
            return Double.parseDouble(nextToken());
        }

        public long nextLong() {
            return Long.parseLong(nextToken());
        }

        private String peekToken() {
            if (token == null)
                try {
                    while (st == null || !st.hasMoreTokens()) {
                        line = br.readLine();
                        if (line == null) return null;
                        st = new StringTokenizer(line);
                    }
                    token = st.nextToken();
                } catch (IOException e) {
                }
            return token;
        }

        private String nextToken() {
            String ans = peekToken();
            token = null;
            return ans;
        }
    }
}