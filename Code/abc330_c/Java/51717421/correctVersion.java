
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            long d = sc.nextLong();

            long sqrt = (long) Math.sqrt(d);
            long min = Long.MAX_VALUE;
            for (long i = 0; i < 2 * sqrt; i++) {
                long s = getAbsMaxSqrt(d - i * i);
                if (s < min)
                    min = s;
            }

            System.out.println(min);
        }
    }

    static long getAbsMaxSqrt(long n) {
        long sqrt = (long) Math.sqrt(n);
        long min = Long.MAX_VALUE;
        for (long i = sqrt; i < sqrt + 2; i++) {
            long s = Math.abs(n - i * i);
            if (s < min)
                min = s;
        }
        return min;
    }
}
