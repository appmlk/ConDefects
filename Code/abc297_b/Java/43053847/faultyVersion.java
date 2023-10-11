import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Kattio io = new Kattio();
        String s = io.next();
        int fb = -1, sb = -1, fr = -1, sr = -1, k = -1;
        boolean bb = false, tk = false;
        for (int i = 0; i < 8; i++) {
            if (fb != sb && fb != -1 && sb != -1 && ((fb + 1) % 2 != (sb + 1) % 2)) bb = true;
            if (s.charAt(i) == 'B' && fb == -1) fb = i;
            else if (s.charAt(i) == 'B') sb = i;
        }
        for (int i = 0; i < 8; i++) {
            if (s.charAt(i) == 'K') k = i;
            else if (s.charAt(i) == 'R' && fr == -1) fr = i;
            else if (s.charAt(i) == 'R') sr = i;
            if (k != -1 && fr != -1 && sr != -1 && k < sr && k > fr) tk = true;
        }
        if (bb && tk) io.println("Yes");
        else io.println("No");
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