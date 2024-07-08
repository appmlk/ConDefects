import java.util.*;
import java.io.*;


public class Main {


    static int[][] dp = new int[101][101];
    public static void main(String[] args) {
        String s = sc.next();
        int len = s.length();
        for (int i = 0; i <= 100; i++) {
            Arrays.fill(dp[i],Integer.MAX_VALUE);
        }

        int n = sc.nextInt();
        dp[0][0] = 0;

        for (int i=1;i<=n;i++) {
            int t = sc.nextInt();
            //遍历物品
            while (t-->0) {
                String th = sc.next();
                for (int j = 0; j <= len-th.length(); j++) {
                    dp[i][j] = Math.min(dp[i-1][j],dp[i][j]);
                    if (dp[i-1][j]==Integer.MAX_VALUE) continue;
                    if (s.startsWith(th, j))
                        dp[i][j+th.length()] = Math.min(dp[i-1][j]+1,dp[i][j+th.length()]);
                }
            }
        }
        System.out.println(dp[n][s.length()]==Integer.MAX_VALUE?-1:dp[n][s.length()]);
    }

    static class FastReader {
        StringTokenizer st;
        BufferedReader br;

        public FastReader() {
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

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
    static FastReader sc = new FastReader();

}
