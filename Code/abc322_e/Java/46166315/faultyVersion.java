
import java.io.*;
import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static PrintWriter printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    static Utils utils = new Utils();

    public static void main(String[] args) throws IOException {
//        int o = readInt();
//        while (o-- > 0)
            solve();
        closeAndFlush();
    }

    static class Edge {
        int to, pe, w;

        public Edge(int to, int pe) {
            this.to = to;
            this.pe = pe;
        }

        public Edge(int to, int pe, int w) {
            this.to = to;
            this.pe = pe;
            this.w = w;
        }
    }

    static class Pair {
        int first;
        int second;

        public Pair() {
        }

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return first == pair.first && second == pair.second;
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }

        @Override
        public String toString() {
            return first + " " + second;
        }
    }

    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};//上右下左
    static int n, m, idx = 1;
    static int[] head;
    static Edge[] edges;
//    static int inf = 1 << 30;
    static long inf = utils.forceLong(1e14);

    public static void solve() throws IOException {
        int n = readInt(), k = readInt(), p = readInt();
        long[] dp = new long[100005], c = new long[105];
        int[][] a = new int[105][15];
        int m = 0;
        /**
         * 组成 k个 p
         * 第 i个 p表示第 i个产品达到参数 p
         */
        for (int i = 1; i <= k; i++) m = m * 10 + p;
        for (int i = 1; i <= n; i++) {
            c[i] = readInt();
            for (int j = 1; j <= k; j++) {
                a[i][j] = readInt();
            }
        }
        // dp[x]数组定义：表示 k个背包状态为 x的最小花费,最终求dp[m]的表示每一个产品都为 p的最小代价
//        Arrays.fill(dp, inf);
        Arrays.fill(dp, 0x3f3f3f3f);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {// 物品
            for (int j = m; j >= 0; j--) {// 价值
                int[] w = new int[6];
                for (int le = 1, ri = j; le <= k; le++, ri /= 10) {
                    w[le] = Math.max(0, ri % 10 - a[i][le]);
                }
                int x = 0;
                for (int l = k; l >= 1; --l) x = x * 10 + w[l];
                dp[j] = Math.min(dp[j], dp[x] + c[i]);// 01背包
            }
        }
        printWriter.println(dp[m] > utils.forceLong(1e11) ? -1 : dp[m]);
    }

    public static void add(int a, int b) {
        edges[idx] = new Edge(b, head[a]);
        head[a] = idx++;
    }

    public static void add(int a, int b, int c) {
        edges[idx] = new Edge(b, head[a], c);
        head[a] = idx++;
    }

    public static int readInt() throws IOException {
        return sc.nextInt();
    }

    public static long readLong() throws IOException {
        return sc.nextLong();
    }

    public static String readString() throws IOException {
        return sc.next();
//        return sc.nextLine();
    }

    public static void closeAndFlush() throws IOException {
        printWriter.flush();
        printWriter.close();
        sc.close();
    }

    public static void arrayToString(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            printWriter.print(arr[i] + " ");
        }
        printWriter.println();
    }

    public static void arrayToString(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                printWriter.print(arr[i][j] + " ");
            }
            printWriter.println();
        }
    }
}

class Utils {
    public static int[] nextIntArray(int n) throws IOException {
        int[] arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Main.readInt();
        }
        return arr;
    }

    public static int[][] nextIntArray(int n, int m) throws IOException {
        int[][] arr = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                arr[i][j] = Main.readInt();
            }
        }
        return arr;
    }

    public static long[] nextLongArray(int n) throws IOException {
        long[] arr = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Main.readLong();
        }
        return arr;
    }

    public static long[][] nextLongArray(int n, int m) throws IOException {
        long[][] arr = new long[n + 1][];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                arr[i][j] = Main.readLong();
            }
        }
        return arr;
    }

    public static String[] nextStringArray(int n) throws IOException {
        String[] strings = new String[n + 1];
        for (int i = 1; i <= n; i++) {
            strings[i] = " " + Main.readString();
        }
        return strings;
    }

    public static char[][] nextCharArray(int n, int m) throws IOException {
        char[][] chars = new char[n + 5][m + 5];
        for (int i = 1; i <= n; i++) {
            chars[i] = (" " + Main.readString()).toCharArray();
        }
        return chars;
    }

    public static int forceInt(long n) {
        return (int) n;
    }

    public static int forceInt(double n) {
        return (int) n;
    }

    public static long forceLong(int n) {
        return n;
    }

    public static long forceLong(double n) {
        return (long) n;
    }

    public static int max(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }

    public static int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}

