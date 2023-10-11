import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static int[] counts;
    private static List<Path>[] tree;
    private static final int MOD = 998244353;

    public static void main(String[] args) {
        int n = nextInt();
        int m = nextInt();
        int k = nextInt();
        int[] a = nextIntArray(m);
        tree = new List[n];
        for (int i = 0; i < n; i++) tree[i] = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            int u = nextInt()-1;
            int v = nextInt()-1;
            tree[u].add(new Path(u, v, i));
            tree[v].add(new Path(v, u, i));
        }
        counts = new int[n-1];
        for (int i = 0; i < m-1; i++) {
            int start = a[i]-1;
            int goal = a[i+1]-1;
            dfs(start, -1, goal);
        }

        int sum = Arrays.stream(counts).sum();
        int max = sum+sum+1;
        long[] dp = new long[max];
        dp[sum] = 1;
        for (int i = 0; i < n - 1; i++) {
            long[] tmp = new long[max];
            int ci = counts[i];
            for (int j = 0; j < max; j++) {
                int minus = j - ci;
                if (minus>=0) {
                    tmp[minus] += dp[j];
                    tmp[minus] %= MOD;
                }
                int plus = j + ci;
                if (plus<max) {
                    tmp[plus] += dp[j];
                    tmp[plus] %= MOD;
                }
            }
            dp = tmp;
        }
        out.println((sum+k>=0&&sum+k<max) ? dp[sum+k] : 0);
        out.flush();
    }

    private static boolean dfs(int current, int prev, int goal) {
        if (current == goal) return true;
        for (Path path : tree[current]) {
            int next = path.to;
            if (next == prev) continue;
            boolean result = dfs(next, current, goal);
            if (result) {
                counts[path.i]++;
                return true;
            }
        }
        return false;
    }

    private static class Path {
        int from;
        int to;
        int i;
        public Path(int from, int to, int i) {
            this.from = from;
            this.to = to;
            this.i = i;
        }
    }

    static PrintWriter out = new PrintWriter(System.out);
    static Scanner scanner = new Scanner(System.in);
    static String next() { return scanner.next(); }
    static int nextInt() { return Integer.parseInt(next()); }
    static long nextLong() { return Long.parseLong(next()); }
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