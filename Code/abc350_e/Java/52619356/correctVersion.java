
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    private Map<Long, Double> cached = new HashMap<>();
    public static void main(String[] args) throws Throwable {
        Main main = new Main();
        main.solve();
    }
    public void solve() throws Throwable {
        FastScan scan = new FastScan(System.in);
        long N = scan.nextLong();
        int A = scan.nextInt();
        long X = scan.nextLong();
        long Y = scan.nextLong();
        double ans = f(N, A, X, Y);
        PrintWriter pw = new PrintWriter(System.out);
        pw.printf("%.9f\n",ans);
        pw.flush();
        pw.close();
    }
    private Double f(long N, int A, long X, long Y) {
        if (N == 0) {
            return 0.0;
        }
        if (N == 1) {
            return Math.min(X, (double) Y * 6 / 5);
        }
        if (cached.containsKey(N)) {
            return cached.get(N);
        }
        double f1 = X + f(N/A, A, X, Y);
        double f2 = (6 * Y + f(N/2, A, X, Y) + f(N/3, A, X, Y) + f(N/4, A, X, Y) + f(N/5, A, X, Y) + f(N/6, A, X, Y)) / 5;
        double min = Math.min(f1, f2);
        cached.put(N, min);
        return min;
    }
    class FastScan {
        BufferedReader br;
        StringTokenizer st;
        FastScan(InputStream is) {
            InputStreamReader isr = new InputStreamReader(is);
            this.br = new BufferedReader(isr);
        }
        String next() throws IOException {
            while (this.st == null || !this.st.hasMoreTokens()) {
                this.st = new StringTokenizer(br.readLine().trim());
            }
            return st.nextToken();
        }
        long nextLong() throws IOException {
            return Long.parseLong(this.next());
        }
        int nextInt() throws IOException {
            return Integer.parseInt(this.next());
        }
    }
}
