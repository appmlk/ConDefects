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

    static long MOD = 998244353;

    static void solve() {
        int n = in.nextInt(), q = in.nextInt();
        long[] nums1 = in.nextLongArray(n);
        long[] nums2 = in.nextLongArray(n);
        SegmentTree st = new SegmentTree();
        for (int i = 0; i < n; i++) {
            st.update(i, i, nums1[i], nums2[i]);
        }
        while (q-- > 0) {
            int c = in.nextInt(), l = in.nextInt() - 1, r = in.nextInt() - 1;
            if (c == 3) {
                long ans = st.query(l, r);
                out.println(ans);
                continue;
            }
            long v = in.nextLong();
            if (c == 1) {
                st.update(l, r, v, 0);
            } else {
                st.update(l, r, 0, v);
            }
        }
    }

    // 区间求和（累加）

    static class SegmentTree {
        static class Node {
            Node left, right;
            long val, add;
        }

        Node root_a = new Node();
        Node root_b = new Node();
        Node root_ab = new Node();
        int N = (int) 1e9;
        long MOD = 998244353;

        public void update(int left, int right, long x, long y) {
            update(root_a, root_b, root_ab, 0, N, left, right, x % MOD, y % MOD);
        }

        private void update(Node a, Node b, Node ab, int start, int end, int left, int right, long x, long y) {
            if (left <= start && right >= end) {
                ab.val += y * a.val % MOD + x * b.val % MOD + (end - start + 1) * x % MOD * y % MOD;
                ab.val %= MOD;

                a.val += (end - start + 1) * x;
                a.val %= MOD;
                a.add = x;

                b.val += (end - start + 1) * y;
                b.val %= MOD;
                b.add = y;
                return;
            }
            int mid = start + (end - start) / 2;
            pushDown(ab, a, b, mid - start + 1, end - mid);

            if (left <= mid) update(a.left, b.left, ab.left, start, mid, left, right, x, y);
            if (right > mid) update(a.right, b.right, ab.right, mid + 1, end, left, right, x, y);
            pushUp(a);
            pushUp(b);
            pushUp(ab);
        }


        public long query(int left, int right) {
            return query(root_a, root_b, root_ab, 0, N, left, right);
        }

        private long query(Node a, Node b, Node ab, int start, int end, int left, int right) {
            if (left <= start && right >= end) {
                return ab.val;
            }
            int mid = start + (end - start) / 2;
            pushDown(ab, a, b, mid - start + 1, end - mid);

            long res = 0;
            if (left <= mid) res += query(a.left, b.left, ab.left, start, mid, left, right);
            if (right > mid) res += query(a.right, b.right, ab.right, mid + 1, end, left, right);
            return res % MOD;
        }

        private void pushUp(Node node) {
            node.val = (node.left.val + node.right.val) % MOD;
        }

        private void pushDown(Node node, int leftNum, int rightNum) {
            if (node.add == 0) return;
            node.left.val += node.add * leftNum;
            node.left.val %= MOD;
            node.right.val += node.add * rightNum;
            node.right.val %= MOD;
            node.left.add += node.add;
            node.left.add %= MOD;
            node.right.add += node.add;
            node.right.add %= MOD;
            node.add = 0;
        }

        void pushDown(Node node) {
            if (node.left == null) node.left = new Node();
            if (node.right == null) node.right = new Node();
        }

        private void pushDown(Node node, Node a, Node b, int leftNum, int rightNum) {
            pushDown(node);
            pushDown(a);
            pushDown(b);
            long x = a.add, y = b.add;
            if (x == 0 && y == 0) {
                return;
            }

            node.left.val += x * b.left.val % MOD + y * a.left.val % MOD + x * y % MOD * leftNum % MOD;
            node.left.val %= MOD;
            node.right.val += x * b.right.val % MOD + y * a.right.val % MOD + x * y % MOD * rightNum % MOD;
            node.right.val %= MOD;

            pushDown(a, leftNum, rightNum);
            pushDown(b, leftNum, rightNum);

        }
    }


    static class DSU {
        int[] parent;

        public DSU(int n) {
            parent = new int[n];
            Arrays.setAll(parent, i -> i);
        }

        int find(int x) {
            return parent[x] = parent[x] == x ? x : find(parent[x]);
        }

        void union(int x, int y) {
            parent[find(x)] = parent[find(y)];
        }

        boolean connect(int x, int y) {
            return find(parent[x]) == find(parent[y]);
        }
    }

    static class Reader {
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
}