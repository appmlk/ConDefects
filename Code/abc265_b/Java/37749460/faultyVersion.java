import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {


    public static void main(String[] args) {
        AReader sc = new AReader();
        int n = sc.nextInt();
        int m = sc.nextInt();
        long t = sc.nextLong();

        int[] arr = new int[n];
        for (int i = 1; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int[][] bag = new int[m][2];
        for (int i = 0; i < m; i++) {
            bag[i][0] = sc.nextInt();
            bag[i][1] = sc.nextInt();
        }
        Arrays.sort(bag, Comparator.comparing(x -> x[0]));

        boolean f = true;

        int j = 0;
        long at = 0;
        for (int i = 1; f && i < n; i++) {

            while (j < bag.length && bag[j][0] <= i) {
                t += bag[j][1];
                j++;
            }

            if (at + arr[i] > t) {
                f = false;
                break;
            }

            at += arr[i];
        }

        System.out.println(f ? "Yes" : "No");

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
