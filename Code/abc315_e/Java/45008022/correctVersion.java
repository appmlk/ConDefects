import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {

    private static List<Integer> ans = new ArrayList<>();
    private static Set<Integer> done = new HashSet<>();
    private static List<Integer>[] g;
    private static void solve() {
        int n = nextInt();
        g = new List[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            int ci = nextInt();
            for (int j = 0; j < ci; j++) {
                int p = nextInt()-1;
                g[i].add(p);
            }
        }
        dfs(0);
        for (int i = 0; i < ans.size()-1; i++) {
            out.print(ans.get(i)+1+" ");
        }
        out.println();
        out.flush();
    }

    private static void dfs(int current) {
        for (Integer next : g[current]) {
            if (done.contains(next)) continue;
            done.add(next);
            dfs(next);
        }
        ans.add(current);
    }

    public static void main(String[] args) {
        new Thread(null, () -> solve(), "", 32L * 1024 * 1024).start();
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