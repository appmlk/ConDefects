
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        int t;
        boolean isFileInput = false;
        InputStreamReader in =
                isFileInput ? new FileReader("in.txt") : new InputStreamReader(System.in);
        Scanner sc = new Scanner(in);
        t = sc.nextInt();
        // System.out.println("t = " + t);
        for (int it = 0; it < t; it++) {
            long n, m, k;
            n = sc.nextLong();
            m = sc.nextLong();
            k = sc.nextLong();
            if (n < k) {
                System.out.println(lastDigit(n));
                // return;
            } else if ((n - k) < (m - k)) {
                System.out.println(lastDigit(n));
                // return;
            } else if ((m - k) == 1) {
                System.out.println("0");
            } else {

                System.out.println((lastDigit(((n - k) % (m - k))) * lastDigit(k)) % 10);
            }
        }
    }

    static int lastDigit(long n) {

        if (n == 0)
            return 1;

        else if (n % 4 == 1)
            return 2;
        else if (n % 4 == 2)
            return 4;
        else if (n % 4 == 3)
            return 8;
        else
            return 6;
    }
}
