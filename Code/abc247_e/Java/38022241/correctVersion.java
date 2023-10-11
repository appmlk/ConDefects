import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {


    static long solve(int[] arr, int x, int y) {

        long ans = 0;
        int xn = -1, yn = -1;
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > x || arr[i] < y) {
                j = i + 1;
                xn = yn = -1;
            } else {
                if (arr[i] == x) xn = i;
                if (arr[i] == y) yn = i;

                if (xn >= 0 && yn >= 0) {
                    int t = Math.min(xn, yn);
                    ans += (t - j + 1);
                }
            }
        }

        return ans;

    }

    public static void main(String[] args) {
        AReader sc = new AReader();
        int n = sc.nextInt();
        int x = sc.nextInt();
        int y = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println(solve(arr, x, y));
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
