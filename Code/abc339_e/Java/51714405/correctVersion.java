import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

// If AtCoder, change className to Main
public class Main {
    static MyScanner sc = new MyScanner();
    public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    public static void main(String[] args) {
        int t = 1;
        while (t-- > 0) {
            solve();
        }
        out.close();
    }

    private static void solve() {
        MaxSegmentTree st = new MaxSegmentTree(500001);
        for (int i = 0; i < 500001; i++) st.set(i, 0);
        int n = sc.nextInt(), d = sc.nextInt();
        int max = 0;
        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            int val = st.rangeMax(Math.max(0, a - d), Math.min(500000, a + d) + 1) + 1;
            st.set(a, val);
            max = Math.max(val, max);
        }
        out.println(max);
    }

//    public static void solve() {
//        MaxSegmentTree st = new MaxSegmentTree(500001);
//        for (int i = 0; i < 500001; i++) st.set(i, 0);
//        int n = sc.nextInt(), d = sc.nextInt();
//        int max = 0;
//        for (int i = 0; i < n; i++) {
//            int a = sc.nextInt();
//            int val = st.max(Math.max(0, a - d), Math.min(500000, a + d)) + 1;
//            st.set(a, val);
//            max = Math.max(val, max);
//        }
//        out.println(max);
//    }

    static class MaxSegmentTree {
        private final int[] segtree;
        private final int len;

        public MaxSegmentTree(int len) {
            this.len = len;
            segtree = new int[len * 2];
            Arrays.fill(segtree, Integer.MAX_VALUE);
        }

        /** Sets the value at ind to val. */
        public void set(int ind, int val) {
            ind += len;
            segtree[ind] = val;
            for (; ind > 1; ind /= 2) {
                segtree[ind / 2] = Math.max(segtree[ind], segtree[ind ^ 1]);
            }
        }

        /** @return the minimum of all elements in [start, end). */
        public int rangeMax(int start, int end) {
            int max = Integer.MIN_VALUE;
            for (start += len, end += len; start < end; start /= 2, end /= 2) {
                if (start % 2 == 1) { max = Math.max(max, segtree[start++]); }
                if (end % 2 == 1) { max = Math.max(max, segtree[--end]); }
            }
            return max;
        }
        public int max(int l, int r) {
            return rangeMax(l, r + 1);
        }
    }





    //-----------PrintWriter for faster output---------------------------------


    //-----------MyScanner class for faster input----------
    public static class MyScanner {
        BufferedReader br;
        StringTokenizer st;

        public MyScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
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

        String nextLine(){
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

    }
}
