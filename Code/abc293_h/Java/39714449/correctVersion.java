import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        FastScanner in = new FastScanner(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Ex_OptimalPathDecomposition solver = new Ex_OptimalPathDecomposition();
        solver.solve(1, in, out);
        out.close();
    }

    static class Ex_OptimalPathDecomposition {
        Graph g;
        int k;
        final int infinity = (int) 1e9;

        public void solve(int testNumber, FastScanner in, PrintWriter out) {
            int n = in.nextInt();
            g = new Graph(n);
            for (int i = 0; i < n - 1; i++) {
                int a = in.nextInt() - 1;
                int b = in.nextInt() - 1;
                g.addEdge(a, b);
                g.addEdge(b, a);
            }
            int l = 0;
            int r = 20;
            while (r - l > 1) {
                int m = (l + r) / 2;
                k = m;
                int[] d = dfs(0, -1);
                if (Math.min(d[0], Math.min(d[1], d[2])) <= k) {
                    r = m;
                } else {
                    l = m;
                }
            }
            out.println(r);
        }

        private int[] dfs(int v, int p) {
            // d[i] = i children match root's color
            int[] d = new int[]{1, infinity, infinity};
            for (int e = g.firstEdge[v]; e >= 0; e = g.edgeNxt[e]) {
                int u = g.edgeDst[e];
                if (u == p) {
                    continue;
                }
                int[] f = dfs(u, v);
                int[] nd = new int[]{infinity, infinity, infinity};

                // match
                for (int x = 0; x < 2; x++) {
                    for (int y = 0; y < 2; y++) {
                        if (d[x] + f[y] > k + 1) {
                            continue;
                        }
                        nd[x + 1] = Math.min(nd[x + 1], Math.max(d[x], f[y]));
                    }
                }

                // don't match
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {
                        if (d[x] + f[y] > k) {
                            continue;
                        }
                        nd[x] = Math.min(nd[x], Math.max(d[x], f[y] + 1));
                    }
                }

                d = nd;
            }

            return d;
        }

        class Graph {
            int n;
            int[] firstEdge;
            int[] edgeDst;
            int[] edgeNxt;
            int numEdges;

            Graph(int n) {
                this.n = n;
                firstEdge = new int[n];
                Arrays.fill(firstEdge, -1);
                final int initialCapacity = 2 * n - 2;
                edgeDst = new int[initialCapacity];
                edgeNxt = new int[initialCapacity];
                numEdges = 0;
            }

            void addEdge(int a, int b) {
                int e = numEdges++;
                if (e >= edgeDst.length) {
                    reallocate();
                }
                edgeDst[e] = b;
                edgeNxt[e] = firstEdge[a];
                firstEdge[a] = e;
            }

            private void reallocate() {
                int k = edgeDst.length;
                edgeDst = Arrays.copyOf(edgeDst, 3 * k / 2);
                edgeNxt = Arrays.copyOf(edgeNxt, 3 * k / 2);
            }

        }

    }

    static class FastScanner {
        private BufferedReader in;
        private StringTokenizer st;

        public FastScanner(InputStream stream) {
            in = new BufferedReader(new InputStreamReader(stream));
        }

        public String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(in.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }
}

