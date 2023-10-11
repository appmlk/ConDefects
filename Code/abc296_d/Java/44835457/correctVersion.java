import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Kattio io = new Kattio();
        long n = io.nextLong();
        long m = io.nextLong();
        if (n * n > 0 && n * n < m) {
            io.println(-1);
        } else if (m <= n) {
            io.println(m);
        } else {
            long ans = Long.MAX_VALUE;
            long l = (m + n - 1) / n;
            double end = Math.sqrt(m + n);
            while (l <= end) {
                long k = m % l;
                if (k == 0) {
                    ans = m;
                    break;
                } else if (ans > m + l - k) {
                    ans = m + l - k;
                }
                l++;
            }
            io.println(ans);
        }
        io.flush();
    }

    public static class Kattio extends PrintWriter {
        private BufferedReader r;
        private StringTokenizer st;

        // 标准 IO
        public Kattio() {
            this(System.in, System.out);
        }

        public Kattio(InputStream i, OutputStream o) {
            super(o);
            r = new BufferedReader(new InputStreamReader(i));
        }

        // 文件 IO
        public Kattio(String intput, String output) throws IOException {
            super(output);
            r = new BufferedReader(new FileReader(intput));
        }

        // 在没有其他输入时返回 null
        public String next() {
            try {
                while (st == null || !st.hasMoreTokens())
                    st = new StringTokenizer(r.readLine());
                return st.nextToken();
            } catch (Exception e) {
            }
            return null;
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }


}
