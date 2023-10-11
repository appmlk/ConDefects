import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static BufferedReader br;

    private static int getInt() throws IOException {
        return Integer.parseInt(br.readLine());
    }

    private static int[] intArray() throws IOException {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(m -> Integer.parseInt(m)).toArray();
    }

    private static long[] longArray() throws IOException {
        return Arrays.stream(br.readLine().split(" ")).mapToLong(m -> Long.parseLong(m)).toArray();
    }

    private static void init() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    private static void print(Object o) {
        System.out.println(o);
    }

    private static void printI(int[] p) {
        boolean first = true;
        for  (int i: p) {
            if (first) {
                first = false;
            } else {
                System.out.print(" ");
            }
            System.out.print(i);
        }
        System.out.println();
    }


    public static void main(String[] args) throws IOException {
        init();

        int n = getInt();
        for (int i = 0; i< n; i++) {
            long[] as = longArray();
            long a = as[0];
            long s = as[1];
            if (s < a) {
                print("No");
                continue;
            }

            long s2 = s - a - a;
            if ((s2 & a) == 0) {
                print("Yes");
            } else {
                print("No");
            }

        }
    }
}
