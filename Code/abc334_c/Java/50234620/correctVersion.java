
import java.io.*;
import java.math.BigInteger;
import java.util.*;


public class Main {
    static int mod = (int) 1e9 + 7;

    public static void main(String[] args) throws IOException {
        int t = 1;
        for (int o = 0; o < t; o++) {
            int n = input.nextInt();
            int k = input.nextInt();
            int[] arr = new int[k];
            for (int i = 0; i < k; i++) {
                arr[i] = input.nextInt();
            }
            if (k % 2 == 0) {
                long ans = 0;
                for (int i = 1; i < k; i += 2) {
                    ans += arr[i] - arr[i - 1];
                }
                out.println(ans);
            } else {
                long[] pre = new long[k];
                for (int i = 1; i < k; i += 2) {
                    pre[i] = arr[i] - arr[i - 1] + (i - 2 >= 0 ? pre[i - 2] : 0);
                }
                long[] nex = new long[k];
                for (int i = k - 2; i >= 0; i -= 2) {
                    nex[i] = arr[i + 1] - arr[i] + (i + 2 < k ? nex[i + 2] : 0);
                }
                long ans = Long.MAX_VALUE;
                for (int i = 0; i < k; i++) {
                    long res = 0;
                    if (i % 2 == 0) {
                        res += (i != 0 ?pre[i - 1]:0) + (i+1<k?nex[i + 1]:0);
                    } else {
                        if (i - 2 >= 0) res += pre[i - 2];
                        if (i + 1 < k) res += arr[i + 1] - arr[i - 1];
                        if (i + 2 < k) res+=nex[i+2];
                    }
                    ans =Math.min(ans, res);
                }
                out.println(ans);
            }
        }
        out.flush();
        out.close();
        br.close();
    }

    public static class Node {
        int l;
        int r;
        int val;

        public Node(int l, int r, int val) {
            this.l = l;
            this.r = r;
            this.val = val;
        }
    }


    static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
    static Input input = new Input(System.in);
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static class Input {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public Input(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public String nextLine() {
            String str = null;
            try {
                str = reader.readLine();
            } catch (IOException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }
            return str;
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public Double nextDouble() {
            return Double.parseDouble(next());
        }

        public BigInteger nextBigInteger() {
            return new BigInteger(next());
        }
    }

}
