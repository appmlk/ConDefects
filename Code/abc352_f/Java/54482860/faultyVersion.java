// package Codeforce;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    static Reader in = new Reader();

    public static void main(String[] args) {
//        int t = in.nextInt();
//        while (t-- > 0) {
//            solve();
//        }

        solve();

        out.close();
    }


    static void solve() {
        n = in.nextInt();
        int m = in.nextInt();
        uf = new UnionFind(n + 1);
        for (int i = 0; i < m; i++) {
            int a = in.nextInt() - 1, b = in.nextInt() - 1, c = in.nextInt();
            uf.union(a, b, c);
        }
        for (int i = 0; i < n; i++) {
            if (uf.find(i) == i) {
                int shape = 0;
                List<Integer> list = new ArrayList<>();
                for (int j = 0; j < n; j++) {
                    if (uf.find(j) == i) {
                        shape |= 1 << uf.inc[j];
                        list.add(j);
                    }
                }
                src.add(list);
                shapes.add(shape);
            }
        }
        int size = shapes.size();
        sets = new Set[size];
        Arrays.setAll(sets, e -> new HashSet<>());
        memo = new Boolean[size + 1][1 << n];
        dfs(0, 0);

        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        for (int i = 0; i < size; i++) {
            for (int j : src.get(i)) {
                if (sets[i].size() == 1) {
                    int f = sets[i].iterator().next();
                    ans[j] = uf.inc[j] + f + 1;
                }
            }
        }

        for (int x : ans) {
            out.print(x + " ");
        }
        out.println();

    }

    static boolean dfs(int i, int s) {
        if (i == shapes.size()) {
            return s == (1 << n) - 1;
        }
        if (memo[i][s] != null) {
            return memo[i][s];
        }

        int shape = shapes.get(i);
        boolean res = false;
        for (int j = 0; j < n; j++) {
            int next = shape << j;
            if (next >= (1 << n) || (next & s) != 0) {
                continue;
            }
            if (dfs(i + 1, s | next)) {
                sets[i].add(j);
                res = true;
            }
        }
        return memo[i][s] = res;
    }

    static Boolean[][] memo;
    static Set<Integer>[] sets;
    static List<Integer> shapes = new ArrayList<>();
    static List<List<Integer>> src = new ArrayList<>();
    static UnionFind uf;
    static int n;
    

    static class UnionFind {
        int[] parent;
        int[] inc;

        UnionFind(int n) {
            parent = new int[n];
            Arrays.setAll(parent, i -> i);
            inc = new int[n];
        }

        int find(int x) {
            if (parent[x] != x) {
                int fx = find(parent[x]);
                inc[x] += inc[parent[x]];
                parent[x] = fx;
            }
            return parent[x];
        }

        void union(int x, int y, int dis) {
            int fx = find(x), fy = find(x);
            if (fx == fy) {
                return;
            }
            int d = dis - inc[x] + inc[y];
            if (d < 0) {
                union(y, x, -dis);
                return;
            }
            inc[fx] = dis - inc[x] + inc[y];
            parent[fx] = fy;
        }
    }
}


class Reader {
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer st;

    boolean hasNext() {
        try {
            while (st == null || !st.hasMoreElements()) {
                st = new StringTokenizer(br.readLine());
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    String next() {
        try {
            while (st == null || !st.hasMoreElements()) {
                st = new StringTokenizer(br.readLine());
            }
        } catch (Exception ignored) {
        }
        return st.nextToken();
    }

    int nextInt() {
        return Integer.parseInt(next());
    }

    int[] nextIntArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = nextInt();
        }
        return arr;
    }

    long nextLong() {
        return java.lang.Long.parseLong(next());
    }

    long[] nextLongArray(int n) {
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = nextLong();
        }
        return arr;
    }

    String nextLine() {
        String s = "";
        try {
            s = br.readLine();
        } catch (Exception ignored) {
        }
        return s;
    }
}
