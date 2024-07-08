import java.util.*;
import java.io.*;

public class Main {
    static long[] swap, gain;
    static long[][] memo;

    public static void main(String[] args) throws IOException {
        int n = sc.nextInt();
        swap = new long[n];
        gain = new long[n];
        long l = 0;
        long r = 0;
        for (int i = 0; i < gain.length; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int z = sc.nextInt();
            if (x > y) {
                swap[i] = 0;
                gain[i] = 0;
                l += z;
            } else {
                swap[i] = ceil(y - x, 2);
                gain[i] = z;
                r += z;
            }
        }
        memo = new long[n][(int) 1e5 + 1];
        for (long[] temp : memo)
            Arrays.fill(temp, -1);
        pw.println(dp(0, ceil(r - l, 2)));
        pw.close();
    }

    static long dp(int idx, long w) {
        if (w <= 0)
            return 0;
        if (idx == swap.length)
            return (int) 1e17;
        if (memo[idx][(int) w] != -1)
            return memo[idx][(int) w];
        return memo[idx][(int) w] = Math.min(dp(idx + 1, w), swap[idx] + dp(idx + 1, w - gain[idx]));
    }

    static long ceil(long n, long m) {
        return n / m + Math.min(1, n % m);
    }

    static long getSum(int[] arr) {
        long sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }

    static int getMin(int[] arr) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            min = Math.min(min, arr[i]);
        }
        return min;
    }

    static int getMax(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        return max;
    }

    static long gcd(long x, long y) {
        if (x == 0)
            return y;
        return gcd(y % x, x);
    }

    static long lcm(long a, long b) {
        return (1l * a * b) / gcd(a, b);
    }

    static void sort(int[] arr) {
        Arrays.sort(arr);
    }

    static ArrayList<Integer>[] Graph(int n) {
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        return graph;
    }

    public static int setBit(int mask, int idx) {
        return mask | (1 << idx);
    }

    public static boolean checkBit(int mask, int idx) {
        return (mask & (1 << idx)) != 0;
    }

    public static long setBit(long mask, int idx) {
        return mask | (1l << idx);
    }

    public static boolean checkBit(long mask, int idx) {
        return (mask & (1l << idx)) != 0;
    }

    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public Scanner(FileReader r) {
            br = new BufferedReader(r);
        }

        public String next() throws IOException {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        public String nextLine() throws IOException {
            return br.readLine();
        }

        public double nextDouble() throws IOException {
            String x = next();
            StringBuilder sb = new StringBuilder("0");
            double res = 0, f = 1;
            boolean dec = false, neg = false;
            int start = 0;
            if (x.charAt(0) == '-') {
                neg = true;
                start++;
            }
            for (int i = start; i < x.length(); i++)
                if (x.charAt(i) == '.') {
                    res = Long.parseLong(sb.toString());
                    sb = new StringBuilder("0");
                    dec = true;
                } else {
                    sb.append(x.charAt(i));
                    if (dec)
                        f *= 10;
                }
            res += Long.parseLong(sb.toString()) / f;
            return res * (neg ? -1 : 1);
        }

        public long[] nextlongArray(int n) throws IOException {
            long[] a = new long[n];
            for (int i = 0; i < n; i++)
                a[i] = nextLong();
            return a;
        }

        public Long[] nextLongArray(int n) throws IOException {
            Long[] a = new Long[n];
            for (int i = 0; i < n; i++)
                a[i] = nextLong();
            return a;
        }

        public int[] nextIntArray(int n) throws IOException {
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            return a;
        }

        public int[][] nextGrid(int n, int m) throws IOException {
            int[][] arr = new int[n][m];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = sc.nextIntArray(m);
            }
            return arr;
        }

        public int[] nextIntCharArray() throws IOException {
            char[] b = sc.next().toCharArray();
            int n = b.length;
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = b[i] - '0';
            return a;
        }

        public int[] NextIntArray(int n) throws IOException {
            int[] arr = new int[n + 1];
            for (int i = 1; i < arr.length; i++) {
                arr[i] = nextInt();
            }
            return arr;
        }

        public Integer[] nextIntegerArray(int n) throws IOException {
            Integer[] a = new Integer[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            return a;
        }

        public ArrayList<Integer>[] directedGraph(int n, int m) throws IOException {
            ArrayList<Integer>[] graph = new ArrayList[n + 1];
            for (int i = 0; i < graph.length; i++) {
                graph[i] = new ArrayList<>();
            }
            while (m-- > 0) {
                int a = nextInt();
                int b = nextInt();
                graph[a].add(b);
            }
            return graph;
        }

        public ArrayList<Integer>[] undirectedGraph(int n, int m) throws IOException {
            ArrayList<Integer>[] graph = new ArrayList[n + 1];
            for (int i = 0; i < graph.length; i++) {
                graph[i] = new ArrayList<>();
            }
            while (m-- > 0) {
                int a = nextInt();
                int b = nextInt();
                graph[a].add(b);
                graph[b].add(a);
            }
            return graph;
        }

        public boolean ready() throws IOException {
            return br.ready();
        }

    }

    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            pw.print(arr[i] + " ");
        }
        pw.println();
    }

    public static void print(int[] arr, String separator) {
        for (int i = 0; i < arr.length; i++) {
            pw.print(arr[i] + separator);
        }
        pw.println();

    }

    public static void print(long[] arr) {
        for (int i = 0; i < arr.length; i++) {
            pw.print(arr[i] + " ");
        }
        pw.println();
    }

    public static void print(ArrayList arr) {
        for (int i = 0; i < arr.size(); i++) {
            pw.print(arr.get(i) + " ");
        }
        pw.println();
    }

    public static void print(int[][] arr) {
        for (int[] i : arr) {
            print(i);
        }
    }

    public static void print(long[][] arr) {
        for (long[] i : arr) {
            print(i);
        }
    }

    public static void print(char[] arr) {
        for (int i = 0; i < arr.length; i++) {
            pw.print(arr[i] + " ");
        }
        pw.println();
    }

    static int inf = 1000000050;
    static long mod = 1000 * 1000 * 1000 + 7;
    static Random rn = new Random();
    static Scanner sc = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(System.out);
}