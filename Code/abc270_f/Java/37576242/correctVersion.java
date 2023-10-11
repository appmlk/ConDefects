import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Ufs {
        int n;
        int[] bset;

        public Ufs(int n) {
            this.n = n;
            this.bset = new int[n + 1];
        }

        int find(int a) {
            if (bset[a] == 0) return a;
            return bset[a] = find(bset[a]);
        }

        void union(int a, int b) {
            int ai = find(a);
            int bi = find(b);
            if (ai != bi) {
                bset[ai] = bi;
            }
        }
    }

    static class Solution {

        // *)
        public long solve(int n, int[] airport, int[] harbors, int[][] edges) {

            List<int[]> es1 = new ArrayList<>();
            List<int[]> es2 = new ArrayList<>();
            List<int[]> es3 = new ArrayList<>();
            List<int[]> es4 = new ArrayList<>();
            for (int[] e: edges) {
                es1.add(e);
                es2.add(e);
                es3.add(e);
                es4.add(e);
            }
            for (int i = 1; i <= n; i++) {
                es2.add(new int[] {i, n + 1, airport[i]});
                es3.add(new int[] {i ,n + 1, harbors[i]});

                es4.add(new int[] {i ,n + 1, airport[i]});
                es4.add(new int[] {i ,n + 2, harbors[i]});
            }

            long r1 = prime(n, es1);
            long r2 = prime(n + 1, es2);
            long r3 = prime(n + 1, es3);
            long r4 = prime(n + 2, es4);

            return Math.min(Math.min(r1, r2), Math.min(r3, r4));

        }

        public long prime(int n, List<int[]> edges) {

            Ufs ufs = new Ufs(n);

            PriorityQueue<int[]> pp = new PriorityQueue<>(Comparator.comparing(x -> x[2]));
            pp.addAll(edges);

            long ans = 0;
            int cnt = 0;

            while (!pp.isEmpty()) {

                int[] cur = pp.poll();
                int u = cur[0], v = cur[1];

                int uf = ufs.find(u), vf = ufs.find(v);
                if (uf != vf) {
                    ufs.union(uf, vf);
                    ans += cur[2];
                    cnt++;
                }
                if (cnt == n - 1) break;
            }

            if (cnt != n - 1) return Long.MAX_VALUE;

            return ans;

        }

    }


    // *)
    public static void main(String[] args) {
        // 最小生成树?
        AReader sc = new AReader();

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] airport = new int[n + 1];
        int[] harbors = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            airport[i] = sc.nextInt();
        }
        for (int i = 1; i <= n; i++) {
            harbors[i] = sc.nextInt();
        }

        int[][] edges = new int[m][3];
        for (int i = 0; i < m; i++) {
            edges[i][0] = sc.nextInt();
            edges[i][1] = sc.nextInt();
            edges[i][2] = sc.nextInt();
        }

        Solution solution = new Solution();
        long res = solution.solve(n, airport, harbors, edges);

        System.out.println(res);

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