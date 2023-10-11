import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * * @author zhengnaishan
 * * @date 2023/1/3 9:10
 */
public class Main {


    public static void main(String[] args) {
        Kattio io = new Kattio();
        int n = io.nextInt();
        int k = io.nextInt();
        PriorityQueue<Long> queue = new PriorityQueue<Long>(((o1, o2) -> Long.compare(o2,o1)));
        int[][] que = new int[n + 1][2];
        que[0][0] = 1;
        que[0][1] = 0;
        for (int i = 1; i <= n; i++) {
            que[i][0] = io.nextInt();
            que[i][1] = io.nextInt();
        }
        long sum = 0;
        int cnt1 = 0;
        long now = 0;
        long ans = Long.MIN_VALUE;
        for (int i = que.length - 1; i >= 0; i--) {
            long a = que[i][0];
            long b = que[i][1];
            if (a == 1) {
                cnt1++;
                sum += b - now;
                now = b;
                if (k + 1 < cnt1) break;
                int v = k - cnt1 + 1;
                while (queue.size() > v) {
                    sum += queue.poll();
                }
                ans = Math.max(ans, sum);
            } else if (b > 0) {
                sum += b;
            } else {
                queue.add(b);
            }
        }
        io.println(ans);
        io.flush();
    }


    public static class Kattio extends PrintWriter {
        private BufferedReader r;
        private StringTokenizer st;

        // 标准 IO
        public Kattio() {
            this(System.in, System.out);
        }

        public Kattio(InputStream i, OutputStream o) {
            super(o);
            r = new BufferedReader(new InputStreamReader(i));
        }

        // 文件 IO
        public Kattio(String intput, String output) throws IOException {
            super(output);
            r = new BufferedReader(new FileReader(intput));
        }

        // 在没有其他输入时返回 null
        public String next() {
            try {
                while (st == null || !st.hasMoreTokens())
                    st = new StringTokenizer(r.readLine());
                return st.nextToken();
            } catch (Exception e) {
            }
            return null;
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }
}
