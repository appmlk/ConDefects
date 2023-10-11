
import java.io.*;
import java.util.*;

public class Main {
    public void solve() throws Exception {
        int n=nextInt(),d=nextInt();
        if (n==1||n*(n-1)/2<=n*d) {
            System.out.println("No");
            return;
        }
        System.out.println("Yes");
        for (int dd = 1; dd <= d; dd++) {
            for (int i = 1; i < n + 1; i++) {
                int j=i+dd;
                if (j>n){
                    j-=n;
                }
                System.out.println(i+" "+(j));
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }

    static PrintWriter out = new PrintWriter(System.out, true);
    static InputReader in = new InputReader(System.in);
    static String next() { return in.next(); }
    static int nextInt() { return Integer.parseInt(in.next()); }
    static long nextLong() { return Long.parseLong(in.next()); }
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
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
    }
}

