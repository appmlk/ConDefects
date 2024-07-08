//package atcoder.abc345;

import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Main {
    static InputReader in;
    static PrintWriter out;

    public static void main(String[] args) {
        //initReaderPrinter(true);
        initReaderPrinter(false);
        //solve(in.nextInt());
        solve(1);
    }  
   
    /*
        General tips
        1. It is ok to fail, but it is not ok to fail for the same mistakes over and over!
        2. Train smarter, not harder!
        3. If you find an answer and want to return immediately, don't forget to flush before return!
    */
    /*
        Read before practice
        1. Set a timer based on a problem's difficulty level: 45 minutes at your current target practice level;
        2. During a problem solving session, focus! Do not switch problems or even worse switch to do something else;
        3. If fail to solve within timer limit, read editorials to get as little help as possible to get yourself unblocked;
        4. If after reading the entire editorial and other people's code but still can not solve, move this problem to to-do list
           and re-try in the future.
        5. Keep a practice log about new thinking approaches, good tricks, bugs; Review regularly;
        6. Also try this new approach suggested by um_nik: Solve with no intention to read editorial.
           If getting stuck, skip it and solve other similar level problems.
           Wait for 1 week then try to solve again. Only read editorial after you solved a problem.
        7. Remember to also submit in the original problem link (if using gym) so that the 1 v 1 bot knows which problems I have solved already.
        8. Form the habit of writing down an implementable solution idea before coding! You've taken enough hits during contests because you
        rushed to coding!
    */
    /*
        Read before contests and lockout 1 v 1
        Mistakes you've made in the past contests:
        1. Tried to solve without going through given test examples -> wasting time on solving a different problem than asked;
        2. Rushed to coding without getting a comprehensive sketch of your solution -> implementation bugs and WA; Write down your idea step
        by step, no need to rush. It is always better to have all the steps considered before hand! Think about all the past contests that
        you have failed because slow implementation and implementation bugs! This will be greatly reduced if you take your time to get a
        thorough idea steps!
        3. Forgot about possible integer overflow;

        When stuck:
        1. Understand problem statements? Walked through test examples?
        2. Take a step back and think about other approaches?
        3. Check rank board to see if you can skip to work on a possibly easier problem?
        4. If none of the above works, take a guess?
        5. Any chance you got WA due to integer overflow, especially if you are dealing with all subarrays. The sum
           can get deceptively large! If in doubt, just use long instead of int.
    */

    static int n, h, w;
    static int[][] tiles;
    static void solve(int testCnt) {
        for (int testNumber = 0; testNumber < testCnt; testNumber++) {
            n = in.nextInt();
            h = in.nextInt();
            w = in.nextInt();
            tiles = new int[n][];
            for(int i = 0; i < n; i++) {
                tiles[i] = in.nextIntArrayPrimitive(2);
            }
            boolean ans = false;
            for(int i = 1; i < (1 << n) && !ans; i++) {
                List<Integer> p = new ArrayList<>();
                for(int j = 0; j < n; j++) {
                    if((i & (1 << j)) != 0) {
                        p.add(j);
                    }
                }
//                if(p.size() == 3 && p.get(0) == 1 && p.get(1) == 3 && p.get(2) == 4) {
//                    out.println();
//                }
                boolean[][] g = new boolean[h][w];
                ans = compute(g, p, 0);
                //try the very first permutation
                if(!ans) {
                    while(next_permutation(p)) {
                        g = new boolean[h][w];
                        ans |= compute(g, p, 0);
                        if(ans) {
                            break;
                        }
                    }
                }
            }
            out.println(ans ? "Yes" : "No");
        }
        out.close();
    }

    static boolean compute(boolean[][] g, List<Integer> p, int idx) {
        int r = -1, c = -1;
        for(int i = 0; i < h; i++) {
            for(int j = 0;j < w; j++) {
                if(!g[i][j]) {
                    r = i;
                    c = j;
                    break;
                }
            }
            if(r >= 0) {
                break;
            }
        }
        if(r < 0) {
            return true;
        }
        else if(idx >= p.size()) {
            return false;
        }
        boolean ans = false;
        int x = tiles[p.get(idx)][0], y = tiles[p.get(idx)][1];
        if(placeTile(g, r, c, x, y)) {
            ans |= compute(g, p, idx + 1);
            backtrack(g, r, c, x, y);
        }
        if(!ans && x != y && placeTile(g, r, c, y, x)) {
            ans |= compute(g, p, idx + 1);
            backtrack(g, r, c, y, x);
        }
        return ans;
    }

    static boolean placeTile(boolean[][] g, int r, int c, int x, int y) {
        if(r + x > g.length || c + y > g[0].length) {
            return false;
        }
        for(int i = r; i < r + x; i++) {
            for(int j = c; j < c + y; j++) {
                if(g[i][j]) {
                    return false;
                }
            }
        }
        for(int i = r; i < r + x; i++) {
            for(int j = c; j < c + y; j++) {
                g[i][j] = true;
            }
        }
        return true;
    }

    static void backtrack(boolean[][] g, int r, int c, int x, int y) {
        for(int i = r; i < r + x; i++) {
            for(int j = c; j < c + y; j++) {
                g[i][j] = false;
            }
        }
    }

    static boolean next_permutation(List<Integer> p) {
        for (int a = p.size() - 2; a >= 0; --a)
            if (p.get(a) < p.get(a + 1))
                for (int b = p.size() - 1;; --b)
                    if (p.get(b) > p.get(a)) {
                        int t = p.get(a);
                        p.set(a, p.get(b));
                        p.set(b, t);
                        for (++a, b = p.size() - 1; a < b; ++a, --b) {
                            t = p.get(a);
                            p.set(a, p.get(b));
                            p.set(b, t);
                        }
                        return true;
                    }
        return false;
    }

    static long addWithMod(long x, long y, long mod) {
        return (x + y) % mod;
    }

    static long subtractWithMod(long x, long y, long mod) {
        return ((x - y) % mod + mod) % mod;
    }

    static long multiplyWithMod(long x, long y, long mod) {
        x %= mod;
        y %= mod;
        return x * y % mod;
    }

    static long modInv(long x, long mod) {
        return fastPowMod(x, mod - 2, mod);
    }

    static long fastPowMod(long x, long n, long mod) {
        if (n == 0) return 1;
        long half = fastPowMod(x, n / 2, mod);
        if (n % 2 == 0) return half * half % mod;
        return half * half % mod * x % mod;
    }

    static void initReaderPrinter(boolean test) {
        if (test) {
            try {
                in = new InputReader(new FileInputStream("input.in"));
                out = new PrintWriter(new FileOutputStream("output.out"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            in = new InputReader(System.in);
            out = new PrintWriter(System.out);
        }
    }

    static class InputReader {
        BufferedReader br;
        StringTokenizer st;

        InputReader(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream), 32768);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
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

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        Integer[] nextIntArray(int n) {
            Integer[] a = new Integer[n];
            for (int i = 0; i < n; i++) a[i] = nextInt();
            return a;
        }

        int[] nextIntArrayPrimitive(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = nextInt();
            return a;
        }

        int[] nextIntArrayPrimitiveOneIndexed(int n) {
            int[] a = new int[n + 1];
            for (int i = 1; i <= n; i++) a[i] = nextInt();
            return a;
        }

        Long[] nextLongArray(int n) {
            Long[] a = new Long[n];
            for (int i = 0; i < n; i++) a[i] = nextLong();
            return a;
        }

        long[] nextLongArrayPrimitive(int n) {
            long[] a = new long[n];
            for (int i = 0; i < n; i++) a[i] = nextLong();
            return a;
        }

        long[] nextLongArrayPrimitiveOneIndexed(int n) {
            long[] a = new long[n + 1];
            for (int i = 1; i <= n; i++) a[i] = nextLong();
            return a;
        }

        String[] nextStringArray(int n) {
            String[] g = new String[n];
            for (int i = 0; i < n; i++) g[i] = next();
            return g;
        }

        List<Integer>[] readUnWeightedGraphOneIndexed(int n, int m) {
            List<Integer>[] adj = new List[n + 1];
            for (int i = 0; i <= n; i++) {
                adj[i] = new ArrayList<>();
            }
            for (int i = 0; i < m; i++) {
                int u = nextInt();
                int v = nextInt();
                adj[u].add(v);
                adj[v].add(u);
            }
            return adj;
        }

        List<int[]>[] readWeightedGraphOneIndexed(int n, int m) {
            List<int[]>[] adj = new List[n + 1];
            for (int i = 0; i <= n; i++) {
                adj[i] = new ArrayList<>();
            }
            for (int i = 0; i < m; i++) {
                int u = nextInt();
                int v = nextInt();
                int w = in.nextInt();
                adj[u].add(new int[]{v, w});
                adj[v].add(new int[]{u, w});
            }
            return adj;
        }

        List<Integer>[] readUnWeightedGraphZeroIndexed(int n, int m) {
            List<Integer>[] adj = new List[n];
            for (int i = 0; i < n; i++) {
                adj[i] = new ArrayList<>();
            }
            for (int i = 0; i < m; i++) {
                int u = nextInt() - 1;
                int v = nextInt() - 1;
                adj[u].add(v);
                adj[v].add(u);
            }
            return adj;
        }

        List<int[]>[] readWeightedGraphZeroIndexed(int n, int m) {
            List<int[]>[] adj = new List[n];
            for (int i = 0; i < n; i++) {
                adj[i] = new ArrayList<>();
            }
            for (int i = 0; i < m; i++) {
                int u = nextInt() - 1;
                int v = nextInt() - 1;
                int w = in.nextInt();
                adj[u].add(new int[]{v, w});
                adj[v].add(new int[]{u, w});
            }
            return adj;
        }

        /*
            A more efficient way of building an undirected graph using int[] instead of ArrayList to store each node's neighboring nodes.
            1-indexed.
         */
        int[][] buildUndirectedGraph(int nodeCnt, int edgeCnt) {
            int[] end1 = new int[edgeCnt], end2 = new int[edgeCnt];
            int[] edgeCntForEachNode = new int[nodeCnt + 1], idxForEachNode = new int[nodeCnt + 1];
            for (int i = 0; i < edgeCnt; i++) {
                int u = in.nextInt(), v = in.nextInt();
                edgeCntForEachNode[u]++;
                edgeCntForEachNode[v]++;
                end1[i] = u;
                end2[i] = v;
            }
            int[][] adj = new int[nodeCnt + 1][];
            for (int i = 1; i <= nodeCnt; i++) {
                adj[i] = new int[edgeCntForEachNode[i]];
            }
            for (int i = 0; i < edgeCnt; i++) {
                adj[end1[i]][idxForEachNode[end1[i]]] = end2[i];
                idxForEachNode[end1[i]]++;
                adj[end2[i]][idxForEachNode[end2[i]]] = end1[i];
                idxForEachNode[end2[i]]++;
            }
            return adj;
        }

        /*
            A more efficient way of building an undirected weighted graph using int[] instead of ArrayList to store each node's neighboring nodes.
            1-indexed.
        */
        int[][][] buildUndirectedWeightedGraph(int nodeCnt, int edgeCnt) {
            int[] end1 = new int[edgeCnt], end2 = new int[edgeCnt], weight = new int[edgeCnt];
            int[] edgeCntForEachNode = new int[nodeCnt + 1], idxForEachNode = new int[nodeCnt + 1];
            for (int i = 0; i < edgeCnt; i++) {
                int u = in.nextInt(), v = in.nextInt(), w = in.nextInt();
                edgeCntForEachNode[u]++;
                edgeCntForEachNode[v]++;
                end1[i] = u;
                end2[i] = v;
                weight[i] = w;
            }
            int[][][] adj = new int[nodeCnt + 1][][];
            for (int i = 1; i <= nodeCnt; i++) {
                adj[i] = new int[edgeCntForEachNode[i]][2];
            }
            for (int i = 0; i < edgeCnt; i++) {
                adj[end1[i]][idxForEachNode[end1[i]]][0] = end2[i];
                adj[end1[i]][idxForEachNode[end1[i]]][1] = weight[i];
                idxForEachNode[end1[i]]++;
                adj[end2[i]][idxForEachNode[end2[i]]][0] = end1[i];
                adj[end2[i]][idxForEachNode[end2[i]]][1] = weight[i];
                idxForEachNode[end2[i]]++;
            }
            return adj;
        }

        /*
            A more efficient way of building a directed graph using int[] instead of ArrayList to store each node's neighboring nodes.
            1-indexed.
        */
        int[][] buildDirectedGraph(int nodeCnt, int edgeCnt) {
            int[] from = new int[edgeCnt], to = new int[edgeCnt];
            int[] edgeCntForEachNode = new int[nodeCnt + 1], idxForEachNode = new int[nodeCnt + 1];
            //from u to v: u -> v
            for (int i = 0; i < edgeCnt; i++) {
                int u = in.nextInt(), v = in.nextInt();
                edgeCntForEachNode[u]++;
                from[i] = u;
                to[i] = v;
            }
            int[][] adj = new int[nodeCnt + 1][];
            for (int i = 1; i <= nodeCnt; i++) {
                adj[i] = new int[edgeCntForEachNode[i]];
            }
            for (int i = 0; i < edgeCnt; i++) {
                adj[from[i]][idxForEachNode[from[i]]] = to[i];
                idxForEachNode[from[i]]++;
            }
            return adj;
        }

        /*
            A more efficient way of building a directed weighted graph using int[] instead of ArrayList to store each node's neighboring nodes.
            1-indexed.
        */
        int[][][] buildDirectedWeightedGraph(int nodeCnt, int edgeCnt) {
            int[] from = new int[edgeCnt], to = new int[edgeCnt], weight = new int[edgeCnt];
            int[] edgeCntForEachNode = new int[nodeCnt + 1], idxForEachNode = new int[nodeCnt + 1];
            //from u to v: u -> v
            for (int i = 0; i < edgeCnt; i++) {
                int u = in.nextInt(), v = in.nextInt(), w = in.nextInt();
                edgeCntForEachNode[u]++;
                from[i] = u;
                to[i] = v;
                weight[i] = w;
            }
            int[][][] adj = new int[nodeCnt + 1][][];
            for (int i = 1; i <= nodeCnt; i++) {
                adj[i] = new int[edgeCntForEachNode[i]][2];
            }
            for (int i = 0; i < edgeCnt; i++) {
                adj[from[i]][idxForEachNode[from[i]]][0] = to[i];
                adj[from[i]][idxForEachNode[from[i]]][1] = weight[i];
                idxForEachNode[from[i]]++;
            }
            return adj;
        }
    }
}
