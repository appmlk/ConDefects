import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.util.function.IntUnaryOperator;
import java.util.function.LongUnaryOperator;
import java.util.stream.Collectors;

public class Main {
    static In in = new In();
    static Out out = new Out();
    static final long inf = 0x1fffffffffffffffL;
    static final int iinf = 0x3fffffff;
    static final double eps = 1e-9;
    static long mod = 998244353;

    List<List<Integer>> edges;
    void solve() {
        int n = in.nextInt();
        edges = in.nextEdges(n, n - 1, false);
        IntPair ret1 = dfs1(0, 0, 0, -1, null);
        IntPair ret2 = dfs1(ret1.second, ret1.second, 0, -1, null);
        int d = ret2.first;
        dfs2(ret2.second, ret2.second, ret1.second);
        List<Integer> leaf1 = new ArrayList<>();
        List<Integer> leaf2 = new ArrayList<>();
        dfs1(ret1.second, ret1.second, 0, d, leaf1);
        dfs1(ret2.second, ret2.second, 0, d, leaf2);
        long[] pow2 = new long[n + 1];
        pow2[0]++;
        for (int i = 0; i < n; i++) {
            pow2[i + 1] = pow2[i] * 2 % mod;
        }
        if (d % 2 == 0) {
            int center = road.get(d / 2);
            long ans = 1;
            int c = 0;
            for (Integer child : edges.get(center)) {
                List<Integer> list = new ArrayList<>();
                dfs1(child, center, 1, d / 2, list);
                ans = ans * ((list.size() + 1) % mod) % mod;
                if (list.size() > 0) {
                    c += list.size();
                }
            }
            ans = (ans + mod - (c + 1) % mod) % mod;
            out.println(ans);
        } else {
            out.println((long)leaf1.size() * leaf2.size() % mod);
        }
    }

    IntPair dfs1(int node, int parent, int depth, int check, List<Integer> list) {
        int maxDepth = depth;
        int maxNode = node;
        if (depth == check) {
            list.add(node);
        }
        for (Integer child : edges.get(node)) {
            if (child == parent) {
                continue;
            }
            IntPair res = dfs1(child, node, depth + 1, check, list);
            if (maxDepth < res.first) {
                maxDepth = res.first;
                maxNode = res.second;
            }
        }
        return new IntPair(maxDepth, maxNode);
    }

    List<Integer> road = new ArrayList<>();
    boolean dfs2(int node, int parent, int target) {
        boolean ret = node == target;
        for (Integer child : edges.get(node)) {
            if (child == parent) {
                continue;
            }
            boolean res = dfs2(child, node, target);
            if (res) {
                ret = true;
            }
        }
        if (ret) {
            road.add(node);
        }
        return ret;
    }

    class IntPair implements Comparable<IntPair> {
        int first;
        int second;

        IntPair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof IntPair)) {
                return false;
            }
            IntPair that = (IntPair)o;
            return first == that.first && second == that.second;
        }

        @Override
        public int hashCode() {
            return first * 31 + second;
        }

        @Override
        public int compareTo(IntPair o) {
            return first == o.first ? Integer.compare(second, o.second) : Integer.compare(first, o.first);
        }

        @Override
        public String toString() {
            return String.format("[%d, %d]", first, second);
        }
    }

    public static void main(String... args) {
        new Main().solve();
        out.flush();
    }
}

class In {
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in), 0x10000);
    private StringTokenizer tokenizer;

    String next() {
        try {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
        } catch (IOException ignored) {
        }
        return tokenizer.nextToken();
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

    char[] nextCharArray() {
        return next().toCharArray();
    }

    String[] nextStringArray(int n) {
        String[] s = new String[n];
        for (int i = 0; i < n; i++) {
            s[i] = next();
        }
        return s;
    }

    char[][] nextCharGrid(int n, int m) {
        char[][] a = new char[n][m];
        for (int i = 0; i < n; i++) {
            a[i] = next().toCharArray();
        }
        return a;
    }

    int[] nextIntArray(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = nextInt();
        }
        return a;
    }

    int[] nextIntArray(int n, IntUnaryOperator op) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = op.applyAsInt(nextInt());
        }
        return a;
    }

    int[][] nextIntMatrix(int h, int w) {
        int[][] a = new int[h][w];
        for (int i = 0; i < h; i++) {
            a[i] = nextIntArray(w);
        }
        return a;
    }

    long[] nextLongArray(int n) {
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = nextLong();
        }
        return a;
    }

    long[] nextLongArray(int n, LongUnaryOperator op) {
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = op.applyAsLong(nextLong());
        }
        return a;
    }

    long[][] nextLongMatrix(int h, int w) {
        long[][] a = new long[h][w];
        for (int i = 0; i < h; i++) {
            a[i] = nextLongArray(w);
        }
        return a;
    }

    List<List<Integer>> nextEdges(int n, int m, boolean directed) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            res.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int u = nextInt() - 1;
            int v = nextInt() - 1;
            res.get(u).add(v);
            if (!directed) {
                res.get(v).add(u);
            }
        }
        return res;
    }
}

class Out {
    private final PrintWriter out = new PrintWriter(System.out);
    boolean autoFlush = false;

    void println(Object... args) {
        if (args == null || args.getClass() != Object[].class) {
            args = new Object[] {args};
        }
        out.println(Arrays.stream(args).map(obj -> {
            Class<?> clazz = obj == null ? null : obj.getClass();
            return clazz == byte[].class ? Arrays.toString((byte[])obj) :
                   clazz == short[].class ? Arrays.toString((short[])obj) :
                   clazz == int[].class ? Arrays.toString((int[])obj) :
                   clazz == long[].class ? Arrays.toString((long[])obj) :
                   clazz == char[].class ? Arrays.toString((char[])obj) :
                   clazz == float[].class ? Arrays.toString((float[])obj) :
                   clazz == double[].class ? Arrays.toString((double[])obj) :
                   clazz == boolean[].class ? Arrays.toString((boolean[])obj) :
                   obj instanceof Object[] ? Arrays.deepToString((Object[])obj) :
                   String.valueOf(obj);
        }).collect(Collectors.joining(" ")));
        if (autoFlush) {
            out.flush();
        }
    }

    void println(char[] s) {
        out.println(String.valueOf(s));
        if (autoFlush) {
            out.flush();
        }
    }

    void println(int[] a) {
        StringJoiner joiner = new StringJoiner(" ");
        for (int i : a) {
            joiner.add(Integer.toString(i));
        }
        out.println(joiner);
        if (autoFlush) {
            out.flush();
        }
    }

    void println(long[] a) {
        StringJoiner joiner = new StringJoiner(" ");
        for (long i : a) {
            joiner.add(Long.toString(i));
        }
        out.println(joiner);
        if (autoFlush) {
            out.flush();
        }
    }

    void flush() {
        out.flush();
    }
}
