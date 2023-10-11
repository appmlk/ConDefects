import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * * @author zhengnaishan
 * * @date 2022/12/7 10:04
 */
public class Main {

    public static void main(String[] args) {
        Kattio io = new Kattio();
        int n = io.nextInt();
        int x = io.nextInt();
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (i != x) queue.add(i);
        }
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        int index = n - 1;
        while (!queue.isEmpty()) {
            Integer a = queue.pollLast();
            if (!queue.isEmpty()) {
                Integer b = queue.pollFirst();
                dp1[index] = a;
                dp2[index] = b;
                index--;
                dp1[index] = b;
                dp2[index] = a;
                index--;
            } else {
                dp1[index] = dp2[index] = a;
                index--;
            }
        }
        dp1[0] = dp2[0] = x;
        int[] dp;
        if (Math.abs(dp1[0] - dp1[1]) == 1) {
            dp = dp1;
        } else {
            dp = dp2;
        }
        for (int i = 0; i < n; i++) {
            System.out.print(dp[i] + " ");
        }
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
