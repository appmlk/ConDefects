import java.io.*;
import java.util.*;

public class Main {

    static Scanner sc;
    static PrintWriter out;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        out = new PrintWriter(System.out);
        new Main().solve();
        out.flush();
    }

    public void solve() {
        int n = sc.nextInt();
        int d = sc.nextInt();
        long tot = 0;
        int[] w = new int[n];
        long[] sum = new long[1<<n];
        for(int i=0; i<n; i++) {
            w[i] = sc.nextInt();
            tot += w[i];
        }
        double avg = (double) tot / d;
        long[][] memo = new long[1<<n][d];
        long[] psum = new long[1<<n];
        for(int i=0; i<1<<n; i++) {
            for(int j=0; j<n; j++) {
                if((i>>j&1)==1) {
                    sum[i] += w[j];
                }
            }
            psum[i] = sum[i]*sum[i];
        }

        for(int rem=0; rem<1<<n; rem++) {
            memo[rem][0] = psum[rem];
        }

        for(int div = 1; div < d; div++) {
            for(int rem = 0; rem < 1<<n; rem++) {
                int mask = rem;
                memo[rem][div] = memo[rem][div-1];
                while(mask > 0) {
                    memo[rem][div] = Math.min(memo[rem][div], psum[mask] + memo[rem-mask][div-1]);
                    mask = (mask-1)&rem;
                }
            }
        }

        double res = (memo[(1<<n)-1][d-1] - (tot*tot/(double)d))/d;
        out.printf("%.15f\n", res);
    }



}
