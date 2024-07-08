import java.io.*;
import java.util.*;

public class Main implements Runnable {
    static final int MOD = 998244353;
    private static void solve(MyScanner in, PrintWriter out) {
        long A1 = in.nextLong();
        long A2 = in.nextLong();
        long A3 = in.nextLong();
        if (A2 < A1) {
            long temp = A2;
            A2 = A1;
            A1 = temp;
        }
        long a1 = exp(10, A1 - 1);
        long a2 = exp(10, A2 - 1);
        long a3 = exp(10, A3 - 1);
        if (A2 == A3) {
            if (A1 == A2) {
                long res = (1 + a3 * 8) % MOD * a3 * 8 % MOD * inv(2) % MOD;
                out.println(res);
            }
            else {
                long res = (a2 * 9 - (a1 * 10 - 1) + a2 * 9 - (a1)) % MOD * a1 * 9 % MOD * inv(2) % MOD;
                res = (res + MOD) % MOD;
                out.println(res);
            }
        }
        else if (A2 + 1 == A3) {
            if (A1 == A2) {
                long res = (a2 * 9 + a2) % MOD * (a2 * 8 + 1) % MOD * inv(2) % MOD;
                res = (res + (a2 * 9) % MOD * (a2 - 1)) % MOD;
                res = (res + MOD) % MOD;
                out.println(res);
            }
            else {
                long res = ((a1 * 10 - 1) + a1) % MOD * a1 * 9 % MOD * inv(2) % MOD;
                out.println(res);
            }
        }
        else out.println(0);
    }

    static long inv(long n) {
        return exp(n, MOD - 2);
    }

    static long exp(long n, long k) {
        long res = 1;
        for (long i = k; i > 0; i >>= 1) {
            if ((i & 1) == 1) res = (res * n) % MOD;
            n = (n * n) % MOD;
        }
        return res;
    }

    public static void main(String[] args) {
        new Thread(null, new Main(), "Main", 1 << 28).start();
    }

    public void run() {
        MyScanner in = new MyScanner();
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        int t = in.nextInt();
//        int t = 1;
        while (t-- > 0) solve(in, out);
        out.close();
    }

    //-----------MyScanner class for faster input----------
    public static class MyScanner {
        BufferedReader br;
        StringTokenizer st;

        public MyScanner() {
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

        String nextLine(){
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

    }
    //--------------------------------------------------------
}
