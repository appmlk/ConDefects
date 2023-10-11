import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {
    private static int MOD = 998244353;
    private static long Inf = (long) 1E15;

    public static void main(String[] args) throws Exception {
        InputStream inputStream = System.in;
        //InputStream inputStream = new FileInputStream(new File("/tmp/input.txt"));
        OutputStream outputStream = System.out;
        Scanner in = new Scanner(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        //int t = in.nextInt();
        int t = 1;
        for (int i = 0; i < t; i++) {
            Task solver = new Task();
            solver.solve(in, out);
        }
        out.close();
    }

    private static long modularPow(long base, int exp, int mod) {
        if (mod == 1) {
            return 0L;
        }
        long res = 1;
        base = base % mod;
        while (exp > 0) {
            if (exp % 2 == 1) {
                res = (res * base) % mod;
            }
            exp = exp >> 1;
            base = (base * base) % mod;
        }
        return res;
    }

    private static long inv(int a, int mod) {
        return modularPow(a, mod-2, mod);
    }


    static class Task {
        public void solve(Scanner in, PrintWriter out) {
            int N = in.nextInt();
            int M = in.nextInt();
            int K = in.nextInt();


            long[][] p = new long[K+1][N+1];
            p[0][0] = 1;
            long invMod = inv(M, MOD);

            for (int i = 1; i <= K; i++) {
                for (int r = 0; r <= N; r++) {
                    for (int m = 1; m <= M; m++) {
                        if (m <= N-r) {
                            p[i][r+m] += (p[i-1][r] * invMod);
                            p[i][r+m] %= MOD;
                        } else {
                            p[i][N - (m-N+r)]  += (p[i-1][r] * invMod);
                            p[i][N - (m-N+r)] %= MOD;
                        }
                    }
                }
            }

            long ans = 0;
            for (int i = 1; i <= K; i++) {
                ans = (ans + p[i][N]) % MOD;
            }
            out.println(ans);
        }
    }
}
