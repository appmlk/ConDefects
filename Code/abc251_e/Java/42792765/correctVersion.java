

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    static StringTokenizer st;
    // static int MOD = (int) 1e9+7;
    static int MOD = 998244353;
    public static void main(String[] args) throws IOException {
        int n = readInt();
        int[] A = new int[n + 2];
        long ans;
        for (int i = 1; i <= n; i++) A[i] = readInt();
        A[n + 1] = A[1];
        long[] dp = new long[n + 1];
        Arrays.fill(dp, Long.MAX_VALUE / 4);
        dp[0] = 0; dp[1] = A[1];
        for (int i = 2; i <= n; i++) {
            // buy the previous one
            dp[i] = Math.min(dp[i], dp[i - 2] + A[i - 1]);
            // buy the current one
            dp[i] = Math.min(dp[i], dp[i - 1] + A[i]);
        }
        ans = dp[n];
        long[] dp2 = new long[n + 5];
        Arrays.fill(dp2, Long.MAX_VALUE / 4);
        dp2[1] = 0; dp2[2] = A[2];
        for (int i = 3; i <= n + 1; i++) {
            // buy the previous one
            dp2[i] = Math.min(dp2[i], dp2[i - 2] + A[i - 1]);
            // buy the current one
            dp2[i] = Math.min(dp2[i], dp2[i - 1] + A[i]);
        }
        ans = Math.min(ans, dp2[n + 1]);
        System.out.println(ans);
    }

    static String next() throws IOException {
        while (st == null || !st.hasMoreTokens())
            st = new StringTokenizer(br.readLine().trim());
        return st.nextToken();
    }
    static long readLong() throws IOException {
        return Long.parseLong(next());
    }
    static int readInt() throws IOException {
        return Integer.parseInt(next());
    }
    static double readDouble() throws IOException {
        return Double.parseDouble(next());
    }
    static char readCharacter() throws IOException {
        return next().charAt(0);
    }
    static String readLine() throws IOException {
        return br.readLine().trim();
    }
    static int readLongLineInt() throws IOException{
        int x = 0, c;
        while((c = br.read()) != ' ' && c != '\n')
            x = x * 10 + (c - '0');
        return x;
    }
    static long pow (long x, long exp){
        if (exp==0) return 1;
        long t = pow(x, exp/2);
        t = t*t % MOD;
        if (exp%2 == 0) return t;
        return t*x%MOD;
    }
}