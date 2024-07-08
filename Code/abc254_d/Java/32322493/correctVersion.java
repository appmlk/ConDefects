import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

class Main {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        var writer = new PrintWriter(System.out);
        var N = readInt(scanner);
        var ans = 0;
        var squareNumbers = findSquareNumber(N);
        var primes = findPrimeNumber(N);
        for (var i = 1; i <= N; i++) {
            var counter = calcCounterNumber(i, primes);
            var low = 0;
            var high = squareNumbers.size();
            var mid = (low + high) / 2;
            while (high - low > 1) {
                if ((long) counter * (long) squareNumbers.get(mid) > (long) N) {
                    high = mid;
                } else {
                    low = mid;
                }
                mid = (high + low) / 2;
            }
            ans += high;
        }
        writer.println(ans);

        scanner.close();
        writer.flush();
    }

    /**
     * 入力をInt型で受け取ります
     * @param scanner Scanner
     * @return 入力値(int)
     */
    static Integer readInt(final Scanner scanner) {
        return Integer.parseInt(scanner.next());
    }

    /**
     * N以下の平方数を取得します.
     * @param N 上限
     * @return 平方数のリスト
     */
    static List<Integer> findSquareNumber(final Integer N) {
        var squareNumbers = new ArrayList<Integer>();
        for (var i = 1; i <= N; i++) {
            if (i * i > N) {
                break;
            }
            squareNumbers.add(i * i);
        }
        return squareNumbers;
    }

    static List<Integer> findPrimeNumber(final Integer N) {
        var primes = new ArrayList<Integer>();
        var isPrime = new boolean[N + 2];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (var i = 2; i <= N; i++) {
            if (isPrime[i]) {
                primes.add(i);
                var idx = i * 2;
                while (idx <= N) {
                    isPrime[idx] = false;
                    idx += i;
                }
            }
        }
        return primes;
    }
    /**
     * i にかけることで平方数となる最小の数値を計算します.
     * @param i 対象の数
     * @param primes 素数
     * @return iを平方数にする最小の数
     */
    static Integer calcCounterNumber(final Integer i, final List<Integer> primes) {
        if (i == 1) {
            return 1;
        }
        var P = i;
        var counter = 1;
        for (var div : primes) {
            var count = 0;
            while (P % div == 0) {
                P /= div;
                count += 1;
            }
            if (count % 2 == 1) {
                counter *= div;
            }
            if (P <= 1) {
                break;
            }
        }
        return counter;
    }
}
