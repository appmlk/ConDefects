import java.util.Scanner;

public class Main {
    private static long[] s;

    private static long getsum(int l, int r) {
        return s[r] - s[l - 1];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int q = in.nextInt();
        int[] last = new int[n + 1];
        long[] a = new long[n + 1];
        s = new long[q + 1];
        int now = 0;
        for (int i = 1; i <= q; i++) {
            int x = in.nextInt();
            if (last[x] > 0) {
                a[x] += getsum(last[x], i - 1);
                last[x] = 0;
                now--;
            } else {
                last[x] = i;
                now++;
            }
            s[i] = s[i - 1] + now;
        }
        for (int i = 1; i <= n; i++)
            if (last[i] > 0)
                a[i] += getsum(last[i], q);
        for (int i = 1; i <= n; i++)
            System.out.printf("%d ", a[i]);
        in.close();
    }
}