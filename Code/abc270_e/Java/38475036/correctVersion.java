
import java.util.*;
import java.io.*;

public class Main {

    void solve() {
        int n = in.nextInt();
        long k = in.nextLong();
        long[] arr = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = in.nextLong();
        }
        long l = 1, r = k;
        long idx = 0;
        while (l <= r) {
            long mid = l + (r - l) / 2;
            if (check(mid, arr, k)) {
                l = mid + 1;
                idx = mid;
            } else {
                r = mid - 1;
            }
        }
        for (int i = 1; i <= n; i++) {
            if (arr[i] <= idx) {
                k -= arr[i];
                arr[i] = 0;
            } else {
                k -= idx;
                arr[i] -= idx;
            }
        }
        for (int i = 1; i <= n; i++) {
            if (k <= 0) {
                break;
            }
            if (arr[i] > 0) {
                arr[i]--;
                k--;
            }

        }
        for (int i = 1; i <= n; i++) {
            out.print(arr[i] + " ");
        }

    }

    public boolean check(long mid, long[] arr, long k) {
        long te = 0;
        for (long x : arr) {
            te += Math.min(x, mid);
        }
        return te <= k;
    }

    FastScanner in;

    PrintWriter out;

    void run() {
        in = new FastScanner();
        out = new PrintWriter(System.out);
        solve();
        out.close();
    }

    class FastScanner {
        BufferedReader br;

        StringTokenizer st;

        public FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastScanner(String s) {
            try {
                br = new BufferedReader(new FileReader(s));
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        public String nextToken() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(nextToken());
        }

        public long nextLong() {
            return Long.parseLong(nextToken());
        }

        public double nextDouble() {
            return Double.parseDouble(nextToken());
        }
    }

    public static void main(String[] args) {
        new Main().run();
    }

}

