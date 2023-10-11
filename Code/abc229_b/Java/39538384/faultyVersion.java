import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

class Main {

    private static void solve() throws IOException {
        long A = IO.nextLong();
        long B = IO.nextLong();

        while (A > 0 && B > 0) {
            if (A % 10 + B % 10 > 10) {
                out.println("Hard");
                return;
            }
            A = A / 10;
            B = B / 10;
        }

        out.println("Easy");
    }

    public static void main(String[] args) throws Exception {
        solve();
        out.flush();
    }

    private static Scanner sc = new Scanner(System.in);
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static PrintWriter out = new PrintWriter(System.out);

    static class IO {

        public static String next() throws IOException {
            return sc.next();
        }

        public static int nextInt() throws IOException {
            return Integer.parseInt(sc.next());
        }

        public static long nextLong() throws IOException {
            return Long.parseLong(sc.next());
        }

        public static double nextDouble() throws IOException {
            return Double.parseDouble(sc.next());
        }

        public static int[] nextIntArray(int n) throws IOException {
            int[] intArray = new int[n];
            for (int i = 0; i < n; i++) {
                intArray[i] = Integer.parseInt(sc.next());
            }
            return intArray;
        }

        public static String readLine() throws IOException {
            return br.readLine();
        }

        public static String[] inputStringArray(String split) throws IOException {
            return readLine().split(split);
        }

        public static int[] inputIntArray(String split) throws IOException {
            return Arrays.stream(inputStringArray(split)).mapToInt(Integer::parseInt).toArray();
        }
    }
}
