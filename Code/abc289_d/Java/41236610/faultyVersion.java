import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    static StringTokenizer st;
    static int MOD = 998244353;

    public static void main(String[] args) throws IOException {
        /*int t = readInt();
        while (t-->0) {

        }*/
        int n = readInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) A[i] = readInt();
        int m = readInt();
        Set<Integer> B = new HashSet<>();
        for (int i = 0; i < m; i++) B.add(readInt());
        int x = readInt();
        boolean[] dp = new boolean[x+1];
        dp[0] = true;
        for (int i = 1; i <= x; i++) {
            if (B.contains(i)) continue;
            for (int j = 0; j < n; j++) {
                if (i - A[j] >= 0) dp[i] |= dp[i - A[j]];
            }
        }
        System.out.println(dp[x] ? "YES": "NO");
    }
    static int gcd(int a, int b) {
        if (b == 0) return a;
        else return gcd(b, a % b);
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
}
