
import java.util.Arrays;
import java.util.Scanner;

class D {
    public static void main(String... args) {
        final Scanner sc = new Scanner(System.in);
        final int N = sc.nextInt();
        final int M = sc.nextInt();
        final int[] L = new int[N];
        Arrays.setAll(L, $ -> sc.nextInt());
        long sum = 0;
        for (int l : L)
            sum += l;
        long lo = sum / M - 1;
        long hi = sum + N - 1;
        while (hi - lo > 1) {
            final long W = (hi + lo + 1) / 2;
            boolean failed = L[0] > W;
            long w = L[0];
            for (int i = 1, j = 0; i < N; i++) {
                if (w + L[i] + 1 <= W) {
                    w += L[i] + 1;
                } else {
                    if (++j >= M || L[i] > W) {
                        failed = true;
                        break;
                    }
                    w = L[i];
                }
            }
            if (failed)
                lo = W;
            else
                hi = W;
        }
        System.out.println(hi);
    }
}

public class Main {
    public static void main(String... args) {
        D.main();
    }
}
