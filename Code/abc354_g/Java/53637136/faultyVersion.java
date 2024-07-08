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
        G_SelectStrings solver = new G_SelectStrings();
        solver.solve(1, in, out);
        out.close();
    }

    static class G_SelectStrings {
        public void solve(int testNumber, FastScanner in, PrintWriter out) {
            int n = in.nextInt();
            String[] s = new String[n];
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                s[i] = in.next();
            }
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
            }

            Graph g = new Graph(2 * n + 2);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i != j && s[i].contains(s[j])) {
                        g.addEdge(i, j + n, g.infinity);
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                g.addEdge(g.s, i, a[i]);
            }
            for (int i = 0; i < n; i++) {
                g.addEdge(i + n, g.t, a[i]);
            }
            long sum = 0;
            for (long x : a) {
                sum += x;
            }
            out.println(sum - g.maxFlow());
        }

        class Graph {
            final long infinity = (long) 1e15;
            int n;
            int s;
            int t;
            int[] firstEdge;
            int[] nextEdge;
            int[] edgeDst;
            long[] edgeCap;
            long[] edgeFlow;
            int numEdges;
            int[] level;
            int[] q;
            int[] lastEdge;

            Graph(int n) {
                this.n = n;
                s = n - 1;
                t = n - 2;
                firstEdge = new int[n];
                final int initialEdges = 10;
                nextEdge = new int[initialEdges];
                edgeDst = new int[initialEdges];
                edgeCap = new long[initialEdges];
                edgeFlow = new long[initialEdges];
                numEdges = 0;
                level = new int[n];
                q = new int[n];
                lastEdge = new int[n];
                Arrays.fill(firstEdge, -1);
            }

            void reallocate() {
                int k = nextEdge.length * 3 / 2 + 1;
                nextEdge = Arrays.copyOf(nextEdge, k);
                edgeDst = Arrays.copyOf(edgeDst, k);
                edgeCap = Arrays.copyOf(edgeCap, k);
                edgeFlow = Arrays.copyOf(edgeFlow, k);
            }

            void addEdge(int a, int b, long cap) {
                if (numEdges + 2 >= nextEdge.length) {
                    reallocate();
                }
                int e = numEdges++;
                nextEdge[e] = firstEdge[a];
                firstEdge[a] = e;
                edgeDst[e] = b;
                edgeCap[e] = cap;
                edgeFlow[e] = 0;

                e = numEdges++;
                nextEdge[e] = firstEdge[b];
                firstEdge[b] = e;
                edgeDst[e] = a;
                edgeCap[e] = 0;
                edgeFlow[e] = 0;
            }

            long maxFlow() {
                long flow = 0;
                while (bfs()) {
                    for (int i = 0; i < n; i++) {
                        lastEdge[i] = firstEdge[i];
                    }
                    while (true) {
                        long add = dfs(s, infinity);
                        if (add == 0) {
                            break;
                        }
                        flow += add;
                    }
                }
                return flow;
            }

            long dfs(int v, long delta) {
                if (v == t) {
                    return delta;
                }
                for (; lastEdge[v] >= 0; lastEdge[v] = nextEdge[lastEdge[v]]) {
                    int e = lastEdge[v];
                    int u = edgeDst[e];
                    if (level[u] != level[v] + 1) {
                        continue;
                    }
                    long x = Math.min(delta, edgeCap[e] - edgeFlow[e]);
                    if (x <= 0) {
                        continue;
                    }
                    x = dfs(u, x);
                    if (x > 0) {
                        edgeFlow[e] += x;
                        edgeFlow[e ^ 1] -= x;
                        return x;
                    }
                }
                level[v] = -1;
                return 0;
            }

            boolean bfs() {
                Arrays.fill(level, -1);
                q[0] = s;
                level[s] = 0;
                int qt = 0;
                int qh = 1;
                while (qt < qh) {
                    int v = q[qt++];
                    for (int e = firstEdge[v]; e >= 0; e = nextEdge[e]) {
                        int u = edgeDst[e];
                        if (edgeFlow[e] < edgeCap[e] && level[u] < 0) {
                            level[u] = level[v] + 1;
                            q[qh++] = u;
                        }
                    }
                }
                return level[t] >= 0;
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

