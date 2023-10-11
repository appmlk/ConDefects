import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

    // Implement this method
    void solve() {
        int N = nextInt();
        long A = nextLong(), B = nextLong();
        String S = next();
        S += S;
        long ans = Math.min(A * N, B * N);
        for (int i = 0; i < N; i++) {
            long tmp = A * i;
            for (int j = 0; j < N / 2; j++) {
                int l = i + j;
                int r = N - j - 1 + i;
                if (S.charAt(l) != S.charAt(r)) tmp += B;
            }
            ans = Math.min(ans, tmp);
        }
        println(ans);
    }

    // Entrypoint - DO NOT EDIT!!
    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
        flush();
    }

    // Stdin
    static FastReader fr = new FastReader();

    static String next() {
        return fr.next();
    }

    static int nextInt() {
        return fr.nextInt();
    }

    static double nextDouble() {
        return fr.nextDouble();
    }

    static long nextLong() {
        return fr.nextLong();
    }

    static int[] nextInts(int N) {
        int[] ret = new int[N];
        for (int i = 0; i < N; i++) ret[i] = nextInt();
        return ret;
    }

    static class FastReader {

        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    // Stdout
    static PrintWriter out = new PrintWriter(System.out);

    static void println(Object o) {
        out.println(o);
    }

    static void flush() {
        out.flush();
    }
}
