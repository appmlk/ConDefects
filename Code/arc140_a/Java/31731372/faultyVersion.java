

import java.io.*;
import java.util.*;

public class Main {

    private static int[][] dirs = {{-1,-1}, {1, 1}, {-1, 1}, {1, -1}};
    private static long inf = (long) 1e13;
        private static long div = 998_244_353L;
//    private static long div = ((long)1e9) + 7;


    public static class SegTree {
        int n;
        int[] t;
        boolean[] marked;

        SegTree(int n) {
            this.n = n;
            this.t = new int[4 * n + 1];
            this.marked = new boolean[4 * n + 1];
        }

        void push(int v) {
            if (marked[v]) {
                t[v*2] = t[v*2+1] = t[v];
                marked[v*2] = marked[v*2+1] = true;
                marked[v] = false;
            }
        }

        private void update(int v, int tl, int tr, int l, int r, int new_val) {
            if (l > r)
                return;
            if (l == tl && tr == r) {
                t[v] = new_val;
                marked[v] = true;
            } else {
                push(v);
                int tm = (tl + tr) / 2;
                update(v*2, tl, tm, l, Math.min(r, tm), new_val);
                update(v*2+1, tm+1, tr, Math.max(l, tm+1), r, new_val);
            }
        }

        void update(int l, int r, int new_val) {
            update(1, 1, n, l, r, new_val);
        }

        private int get(int v, int tl, int tr, int pos) {
            if (tl == tr) {
                return t[v];
            }
            push(v);
            int tm = (tl + tr) / 2;
            if (pos <= tm)
                return get(v*2, tl, tm, pos);
            else
                return get(v*2+1, tm+1, tr, pos);
        }

        int get(int pos) {
            return get(1, 1, n, pos);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static void perms(int offset, List<List<Integer>> perms, int[] arr) {
        if (offset == arr.length) {
            List<Integer> perm = new ArrayList<>();
            for (int j : arr) perm.add(j);
            perms.add(perm);
            return;
        }

        for(int i = offset; i < arr.length; ++i) {
            swap(arr, offset, i);
            perms(offset + 1, perms, arr);
            swap(arr, offset, i);
        }

    }

    private static long pow(long num, long pow, long div) {
        if (pow == 0) return 1L;
        long res = pow(num, pow/2, div);
        long ret = 1;
        if (pow % 2 != 0) ret = num % div;
        ret = (ret * res) % div;
        ret = (ret * res) % div;
        return ret;
    }


    private static void dfs(int source, List<List<int[]>> adj, boolean[] visited, boolean[] marked) {
        for(var ng:adj.get(source)) {
            int to = ng[0];
            int eId = ng[1];
            if (visited[to]) continue;
            visited[to] = true;
            marked[eId] = true;
            dfs(to, adj, visited, marked);
        }
    }

    private static void bfs(List<List<int[]>> adj, boolean[] visited, boolean[] marked) {
        visited[0] = true;
        Queue<Integer> bfs = new LinkedList<>();
        bfs.add(0);
        while(!bfs.isEmpty()) {
            Queue<Integer> next = new LinkedList<>();
            while(!bfs.isEmpty()) {
                var head = bfs.remove();
                for(var ng:adj.get(head)) {
                    int to = ng[0];
                    int eId = ng[1];
                    if (visited[to]) continue;
                    visited[to] = true;
                    marked[eId] = true;
                    next.add(to);
                }
            }
            bfs = next;
        }
    }

    public static void main(String[] commands) throws Exception {
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = rd.readLine().split(" ");
        int N = Integer.parseInt(parts[0]);
        int K = Integer.parseInt(parts[1]);
        String s = rd.readLine();
        
        List<Integer> divs = new ArrayList<>();
        for(int x = 1; x * x <= N; ++x) {
            if (N % x != 0) continue;
            int first = x;
            int second = N/x;
            divs.add(first);
            if (first != second) {
                divs.add(second);
            }
        }
        
        for(var t:divs) {
            if (N % t != 0) continue;
            boolean[] visited = new boolean[N];
            int k = K;

            for(int st = 0;st < N; ++st) {
                int[] count = new int[26];

                int offset = st;
                while(offset < N && !visited[offset]) {
                    visited[offset] = true;
                    int curr = s.charAt(offset) - 'a';
                    count[curr]++;
                    offset += t;
                }

                int total = 0;
                int max = 0;
                for(int c:count) {
                    max = Math.max(max, c);
                    total += c;
                }

                k -= total - max;
            }

            if (k >= 0) {
                System.out.println(t);
                return;
            }

        }
    }
}

