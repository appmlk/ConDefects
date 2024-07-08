import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int n = nextInt();
        long[] a = nextLongArray(n);
        // i 日あって、休日がj日ある時の最大値
        long[] aSum = new long[n+1];
        for (int i = 0; i < n ; i++) {
            aSum[i+1] = aSum[i]+a[i];
        }
        long [] b = new long[n+1];
        for (int i = 1; i < n + 1; i++) {
            b[i] = aSum[(i+1)/2] + aSum[i/2];
        }

        long[][] dp = new long[n+1][n];
        dp[1][0] = 0;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                // iが平日の場合
                dp[i][j] = dp[i-1][j-1] - b[j-1] + b[j];
                // i が休日の場合
                dp[i][0] = dp[i-1][j-1];
            }
        }
        long ans = 0;
        for (long l : dp[n]) {
            ans = Math.max(ans, l);
        }
        out.println(ans);
        out.flush();
    }

    static PrintWriter out = new PrintWriter(System.out);
    static Scanner scanner = new Scanner(System.in);
    static String next() { return scanner.next(); }
    static int nextInt() {
        int res = 0;
        char[] chars = next().toCharArray();
        boolean minus = chars[0] == '-';
        int start = minus?1:0;
        for (int i = start; i < chars.length; i++) {
            res = res*10 + (chars[i]-'0');
        }
        return minus?-res:res;
    }
    static long nextLong() {
        long res = 0;
        char[] chars = next().toCharArray();
        boolean minus = chars[0] == '-';
        int start = minus?1:0;
        for (int i = start; i < chars.length; i++) {
            res = res*10 + (chars[i]-'0');
        }
        return minus?-res:res;
    }
    static double nextDouble() { return Double.parseDouble(next()); }
    static int[] nextIntArray(int n) {
        int[] array = new int[n];
        for (int i = 0; i < n; i++) { array[i] = nextInt(); }
        return array;
    }
    static long[] nextLongArray(int n) {
        long[] array = new long[n];
        for (int i = 0; i < n; i++) { array[i] = nextLong(); }
        return array;
    }

}