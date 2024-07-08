
import java.io.*;
import java.util.*;

public class Main {
    static PrintWriter printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StreamTokenizer stn = new StreamTokenizer(bf);
    static Scanner sc = new Scanner(System.in);
    static Utils utils = new Utils();


    public static void main(String[] args) throws IOException {
        // int o = readInt();
        // while (o-- > 0)
        solve();
        closeAndFlush();
    }

    static class Pair {
        int first, second;
        // long first, second;

        public Pair() {
        }

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
        // public Pair(long first, long second) {
        //     this.first = first;
        //     this.second = second;
        // }


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

    static class Edge {
        int to, pe;

        public Edge() {
        }

        public Edge(int to, int pe) {
            this.to = to;
            this.pe = pe;
        }
    }

    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};//上右下左
    static int inf = 1 << 30;
    static int[] head;
    static Edge[] edges;

    public static void solve() throws IOException {
        int n = readInt(), m = readInt(), L = readInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = readInt();
        Pair[] b = new Pair[m];
        for (int i = 0; i < m; i++) b[i] = new Pair(i, readInt());
        Arrays.sort(b, (p, q) -> p.second - q.second);
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < L; i++) {
            int c = readInt() - 1, d = readInt() - 1;
            Set<Integer> set = map.getOrDefault(c, new HashSet<>());
            set.add(d);
            map.put(c, set);
        }
        Set.of();
        long res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map.getOrDefault(i, new HashSet<>()).contains(b[j].first)) continue;
                res = Math.max(res, a[i] + b[j].second);
                break;
            }
        }

        printWriter.println(res);

    }

    public static String readString() throws IOException {
        return sc.next();
//        return sc.nextLine();
//        return bf.readLine();
    }

    public static String readStringPlus() throws IOException {
        return (" " + readString());
    }

    public static int readInt() throws IOException {
//        stn.nextToken();
//        return (int) stn.nval;
        return sc.nextInt();
//        return Integer.parseInt(bf.readLine());
    }

    public static double readDouble() throws IOException {
        return sc.nextDouble();
    }

    public static long readLong() throws IOException {
//        stn.nextToken();
//        return (long) stn.nval;
        return sc.nextLong();
    }

    public static void closeAndFlush() throws IOException {
        printWriter.flush();
        printWriter.close();
        sc.close();
        bf.close();
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
        int[] arr = new int[n + 5];
        for (int i = 1; i <= n; i++) {
            arr[i] = Main.readInt();
        }
        return arr;
    }

    public static int[][] nextIntArray(int n, int m) throws IOException {
        int[][] arr = new int[n + 5][m + 5];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                arr[i][j] = Main.readInt();
            }
        }
        return arr;
    }

    public static long[] nextLongArray(int n) throws IOException {
        long[] arr = new long[n + 5];
        for (int i = 1; i <= n; i++) {
            arr[i] = Main.readLong();
        }
        return arr;
    }

    public static long[][] nextLongArray(int n, int m) throws IOException {
        long[][] arr = new long[n + 5][m + 5];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                arr[i][j] = Main.readLong();
            }
        }
        return arr;
    }

    public static String[] nextStringArray(int n) throws IOException {
        String[] strings = new String[n + 5];
        for (int i = 1; i <= n; i++) {
            strings[i] = " " + Main.readString();
        }
        return strings;
    }

    public static char[] nextCharArray(int n) throws IOException {
        return (" " + Main.readString()).toCharArray();
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

    public static int max(int... arr) {
        return Arrays.stream(arr).max().getAsInt();
    }

    public static long max(long[] arr) {
        return Arrays.stream(arr).max().getAsLong();
    }

    public static int min(int... arr) {
        return Arrays.stream(arr).min().getAsInt();
    }

    public static long min(long... arr) {
        return Arrays.stream(arr).min().getAsLong();
    }
}