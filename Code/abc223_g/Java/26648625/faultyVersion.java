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
    static long mod = 1000000007;

    int n;
    List<List<Integer>> edges;
    boolean[] parentUse;
    boolean[] childUse;
    boolean[] slide;
    boolean[] slideToSibling;
    int[] parents;
    void solve() {
        n = in.nextInt();
        edges = in.nextEdges(n, n - 1, false);
        parents = new int[n];
        parentUse = new boolean[n];
        childUse = new boolean[n];
        slide = new boolean[n];
        dfs(0, -1);
        dfs2(0, -1, false);
        int ans = n;
        for (int i = 0; i < n; i++) {
            if (parentUse[i] || childUse[i] && !slide[i]) {
                ans--;
            }
        }
        out.println(ans);
    }

    boolean dfs(int node, int parent) {
        parents[node] = parent;
        int usingChild = -1;
        boolean multiChild = false;
        for (int child : edges.get(node)) {
            if (child == parent) {
                continue;
            }
            if (dfs(child, node)) {
                if (usingChild != -1) {
                    multiChild = true;
                }
                usingChild = child;
            }
        }
        if (usingChild != -1) {
            parentUse[node] = true;
            childUse[usingChild] = true;
            slide[usingChild] = multiChild;
        }
        return usingChild == -1;
    }

    void dfs2(int node, int parent, boolean slideToParent) {
        for (int child : edges.get(node)) {
            if (child == parent) {
                continue;
            }
            if (childUse[child] && slideToParent) {
                slide[child] = true;
            }
            dfs2(child, node, parentUse[child] && (!childUse[node] || slide[node]));
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
