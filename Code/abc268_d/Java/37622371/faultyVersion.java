
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static class Solution {

        int sz = 0;
        String[] ws;
        Set<String> dict;

        public Solution(String[] ws, Set<String> dict) {
            this.ws = ws;
            this.dict = dict;
            for (String w: ws) {
                sz += w.length();
            }
        }

//        String dfs2(int[] arr, int id, int )

        // 写个互递归
        String dfs2(int[] arr, boolean[] used, int id, int[] bx, int total) {

            if (id == arr.length) {
                // *) 16
                StringBuilder sb = new StringBuilder();
                sb.append(ws[arr[0]]);
                for (int i = 1; i < arr.length; i++) {
                    for (int j = 0; j < bx[i]; j++) {
                        sb.append('_');
                    }
                    sb.append(ws[arr[i]]);
                }
                String expr = sb.toString();
                if (!dict.contains(expr)) {
                    return expr;
                }

                return null;
            }


            int left = arr.length - id;

            if (total < left) return null;

            for (int i = 1; i <= total; i++) {

                if (total - i < arr.length - id - 1) break;

                bx[id] = i;
                String res = dfs(arr, used, id, bx, total - i);
                if (res != null) return res;
            }

            return null;
        }

        // *) 只能暴力搞了
        String dfs(int[] arr, boolean[] used, int id, int[] bx, int total) {
//            if (id == arr.length) {
//                // *) 16
//                StringBuilder sb = new StringBuilder();
//                sb.append(ws[arr[0]]);
//                for (int i = 1; i < arr.length; i++) {
//                    for (int j = 0; j < bx[i]; j++) {
//                        sb.append(' ');
//                    }
//                    sb.append(ws[arr[i]]);
//                }
//                String expr = sb.toString();
//                if (!dict.contains(expr)) {
//                    return expr;
//                }
//
//                return null;
//            }

            for (int i = 0; i < arr.length; i++) {
                if (!used[i]) {
                    used[i] = true;
                    arr[id] = i;
                    String r = dfs2(arr, used, id + 1, bx, total);
                    if (r != null) return r;
                    used[i] = false;
                }
            }
            return null;
        }

        // *)
        String solve() {
            int n = ws.length;
            String r = dfs(new int[n], new boolean[n], 0, new int[n], 16 - sz);
            return r == null ? "-1" : r;
        }
    }

    public static void main(String[] args) {

        AReader sc = new AReader();
        int n = sc.nextInt();
        int m = sc.nextInt();

        String[] ws = new String[n];
        for (int i = 0; i < n; i++) {
            ws[i] = sc.next();
        }

        Set<String> sets = new HashSet<>();
        for (int i = 0; i < m; i++) {
            sets.add(sc.next());
        }

        Solution solution = new Solution(ws, sets);
        System.out.println(solution.solve());
    }


    static
    class AReader {
        private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        private StringTokenizer tokenizer = new StringTokenizer("");
        private String innerNextLine() {
            try {
                return reader.readLine();
            } catch (IOException ex) {
                return null;
            }
        }
        public boolean hasNext() {
            while (!tokenizer.hasMoreTokens()) {
                String nextLine = innerNextLine();
                if (nextLine == null) {
                    return false;
                }
                tokenizer = new StringTokenizer(nextLine);
            }
            return true;
        }
        public String nextLine() {
            tokenizer = new StringTokenizer("");
            return innerNextLine();
        }
        public String next() {
            hasNext();
            return tokenizer.nextToken();
        }
        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

//        public BigInteger nextBigInt() {
//            return new BigInteger(next());
//        }
        // 若需要nextDouble等方法，请自行调用Double.parseDouble包装
    }
}
