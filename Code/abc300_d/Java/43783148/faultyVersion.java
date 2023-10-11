import java.util.*;

public class Main {
    public static void main(String[] args) {

        IOHandler io = new IOHandler();
        long n = io.nextLong();
        io.close();

        /*
         * 前提
         * a,b,c <= √n, 特に ac <= √n
         *
         * 方針
         * (ac)^2 = n となる、(a, c) のペアを探す
         * a < b < c かつ b <= n/(ac)^2 となる b を求める
         */

        // a, c の探索範囲となる、素数の集合を求める
        final int z = 1000000; // 素数の集合のオーダー、√max(n) = 10^6
        boolean[] isPrime = new boolean[z + 1];
        int[] countPrimes = new int[z + 1];
        // List<Long> primes = new ArrayList<>();

        // 2以上の整数に対し、その倍数を非素数とする
        for (int p = 2; p <= z; p++) isPrime[p] = true;
        for (int p = 2; p*p <= z; p++) {
            if (isPrime[p]) {
                for (int q = p*p; q <= z; q += p) isPrime[q] = false;
            }
        }

        // 累計の素数の個数を求める
        for (int p = 2; p <= z; p++) {
            if (isPrime[p]) {
                // primes.add((long) p);
                countPrimes[p]++;
            }
        }
        for (int p = 2; p <= z; p++) countPrimes[p] += countPrimes[p - 1];

        long result = 0;
        // a^5 < (ac)^2 * b <= n より
        // a^5 <= n の範囲で a を探索する
        for (long a = 2; a*a*a*a*a <= n; a++) {
            if (!isPrime[(int) a]) continue;

            // (ac)^2 * c < (ac)^2 * b <= n より
            // (ac)^2 * c <= n の範囲で c を探索する
            for (long c = a + 1; a*a*a*c*c <= n; c++) {
                if (isPrime[(int) c]) continue;

                // a < b < c かつ b <= n/(ac)^2 となる b の個数をカウントする
                // -----+--------+----------> b
                //      bl       br
                // bl と br の間にある素数をカウントする(bl は含まない)
                long br = Math.min(c - 1, n / (a*a*c*c));
                long bl = a;
                if (bl < br) {
                    result += countPrimes[(int) br];
                    result -= countPrimes[(int) bl];
                }
            }
        }
        io.output(result);
    }

    private static class IOHandler {
        private Scanner sc = new Scanner(System.in);
        private void close() {this.sc.close();}
        private long nextLong() {return this.sc.nextLong();}
        private void output(long result) {System.out.println(result);}
    }
}