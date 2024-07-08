import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int n = sc.nextInt();
            long[] a = new long[n];
            a[0] = sc.nextLong();
            long min = a[0];
            for (int i = 1; i < n; i++) {
                a[i] = sc.nextInt() + a[i - 1];
                if (min > a[i])
                    min = a[i];
            }
            if (min < 0)
                System.out.println(-min + a[n - 1]);
            else
                System.out.println(a[n - 1]);
        }
    }
}