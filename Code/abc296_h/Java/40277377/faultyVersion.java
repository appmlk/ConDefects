import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.Map.Entry;
import java.io.BufferedReader;
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
        Ex_Unite solver = new Ex_Unite();
        solver.solve(1, in, out);
        out.close();
    }

    static class Ex_Unite {
        public void solve(int testNumber, FastScanner in, PrintWriter out) {
            int height = in.nextInt();
            int width = in.nextInt();
            char[][] s = new char[height][];
            for (int r = 0; r < height; r++) {
                s[r] = in.next().toCharArray();
            }
            final int infinity = height * width;
            Map<State, Integer> dp = new HashMap<>();
            dp.put(new State(new int[width], 0), 0);
            for (int r = 0; r < height; r++) {
                for (int c = 0; c < width; c++) {
                    Map<State, Integer> ndp = new HashMap<>();
                    for (Map.Entry<State, Integer> e : dp.entrySet()) {
                        for (int x = 0; x < 2; x++) {
                            int cost = x;
                            if (s[r][c] == '#') {
                                if (x == 0) {
                                    continue;
                                }
                                cost = 0;
                            }

                            State st = e.getKey();
                            int[] a = st.a.clone();
                            if (x == 0) {
                                if (a[c] != 0) {
                                    int numWithCol = 0;
                                    for (int v : a) {
                                        if (v == a[c]) {
                                            ++numWithCol;
                                        }
                                    }
                                    if (numWithCol == 1 && st.numCols != 1) {
                                        continue;
                                    }
                                }
                                a[c] = 0;
                            } else {
                                int colUp = a[c];
                                int colLeft = c == 0 ? 0 : a[c - 1];
                                if (colUp != 0 && colLeft != 0) {
                                    a[c] = colLeft;
                                    for (int i = 0; i < a.length; i++) {
                                        if (a[i] == colUp) {
                                            a[i] = colLeft;
                                        }
                                    }
                                } else if (colUp != 0) {
                                    a[c] = colUp;
                                } else if (colLeft != 0) {
                                    a[c] = colLeft;
                                } else {
                                    a[c] = st.numCols + 1;
                                }
                            }

                            State nst = new State(a, st.seenBlack);
                            if (nst.numCols == 0) {
                                if (nst.seenBlack != 0) {
                                    nst.seenBlack = 2;
                                }
                            } else {
                                if (nst.seenBlack == 2) {
                                    continue;
                                }
                                nst.seenBlack = 1;
                            }
                            int val = e.getValue() + cost;
                            int old = ndp.getOrDefault(nst, infinity);
                            if (val < old) {
                                ndp.put(nst, val);
                            }
                        }
                    }

                    dp = ndp;
                }
            }

            int ans = infinity;
            for (Map.Entry<State, Integer> e : dp.entrySet()) {
                State st = e.getKey();
                if (st.seenBlack >= 1) {
                    ans = Math.min(ans, e.getValue());
                }
            }
            out.println(ans);
        }

        class State {
            int[] a;
            int seenBlack;
            int numCols;

            State(int[] a, int seenBlack) {
                this.seenBlack = seenBlack;

                int maxCol = 0;
                for (int x : a) {
                    maxCol = Math.max(maxCol, x);
                }
                int[] repaint = new int[maxCol + 1];
                Arrays.fill(repaint, -1);
                repaint[0] = 0;
                numCols = 0;
                for (int x : a) {
                    if (repaint[x] < 0) {
                        ++numCols;
                        repaint[x] = numCols;
                    }
                }
                for (int i = 0; i < a.length; i++) {
                    a[i] = repaint[a[i]];
                }
                this.a = a;
            }

            public boolean equals(Object o) {
                State state = (State) o;
                return seenBlack == state.seenBlack && Arrays.equals(a, state.a);
            }

            public int hashCode() {
                int result = Objects.hash(seenBlack);
                result = 31 * result + Arrays.hashCode(a);
                return result;
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

