import java.util.*;
import java.io.*;

public class Main {
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
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
                str = br.readLine().trim();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    static FastReader vt = new FastReader();
    static PrintWriter out = new PrintWriter(System.out);
    static boolean cycle = false;
    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        try {
            // int testCases = vt.nextInt();
            int testCases = 1;
            while (testCases-- > 0) {
                // write code here
                solve();
            }
            out.flush();
        } catch (Exception e) {
            return;
        }
    }

    static void solve() {
        int n = vt.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = vt.nextInt() - 1;
        }
        int[] dp = new int[n];
        int[] vis = new int[n];
        int id = 0;
        for (int i = 0; i < n; i++) {
            if (vis[i] == 0) {
                HashSet<Integer> set = new HashSet<>();
                findcycle(i, arr, vis, id, set);
            }
        }
        long ans = 0;
        int[] newvis = new int[n];
        for (Integer i : list) {
            HashSet<Integer> set = new HashSet<>();
            int len = findcyclelength(i, arr, id, set);
            for (Integer j : set) {
                dp[j] = len;
                newvis[j] = 1;
            }
        }
        for (int i = 0; i < n; i++) {
            if (newvis[i] == 0) {
                dp[i] = dfs(i, arr, dp, newvis);
            }
        }
        for (int i = 0; i < n; i++) {
            ans = ans + dp[i];
        }
        out.println(ans);
    }

    static int findcyclelength(int i, int[] arr, int id, HashSet<Integer> set) {
        if (set.contains(i)) {
            return 0;
        }
        set.add(i);
        return 1 + findcyclelength(arr[i], arr, id, set);
    }

    static void findcycle(int i, int[] arr, int[] vis, int id, HashSet<Integer> set) {
        if (vis[i] == 1) {
            if (set.contains(i)) {
                list.add(i);
            }
            return;
        }
        vis[i] = 1;
        set.add(i);
        findcycle(arr[i], arr, vis, id, set);
    }

    static int dfs(int i, int[] arr, int[] dp, int[] vis) {
        if (vis[i] == 1) {
            return dp[i];
        }
        vis[i] = 1;
        int val = dfs(arr[i], arr, dp, vis) + 1;
        return dp[i] = val;
    }
}
