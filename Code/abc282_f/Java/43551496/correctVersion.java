

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        AReader sc = new AReader();
        int n = sc.nextInt();

        // *) layer 层次
        int layer = (int)(Math.log(n)/Math.log(2)) + 1;
        int[][] opt = new int[layer][n + 1];

        int ptr = 1;
        for (int i = 0; i < layer; i++) {
            int t = 1 << i;
            for (int j = 1; j + t - 1 <= n; j++) {
                opt[i][j] = ptr++;
            }
        }
        System.out.println(ptr - 1);
        for (int i = 0; i < layer; i++) {
            int t = 1 << i;
            for (int j = 1; j + t - 1 <= n; j++) {
                System.out.println(j + " " + (j + t - 1));
            }
        }
        System.out.flush();

        int q = sc.nextInt();
        for (int i = 0; i < q; i++) {
            int l = sc.nextInt(), r = sc.nextInt();
            int d = r - l + 1;
            int x = (int)(Math.log(d) / Math.log(2));

            System.out.println(opt[x][l] + " " + opt[x][r - (1 << x) + 1]);
            System.out.flush();
        }

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
