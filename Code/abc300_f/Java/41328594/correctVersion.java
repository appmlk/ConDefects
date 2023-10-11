
import java.util.Scanner;
/*

 */
public class Main {

    static long n, m, k;
    static long[] sum = new long[300005];
    static String s;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextLong();
        m = sc.nextLong();
        k = sc.nextLong();

        sc.nextLine();
        s = sc.nextLine();
        s = ' ' + s;

        for (int i = 1; i <= n; ++i) {
            sum[i] = sum[i - 1];
            if (s.charAt(i) == 'x')
                sum[i]++;
        }

        long l = k, r = n * m, ans = k;
        while (l <= r) {
            long mid = (l + r) >> 1;
            if (check(mid)) {
                ans = mid;
                l = mid + 1;
            }
            else {
                r = mid - 1;
            }
        }
        System.out.println(ans);
    }

    public static boolean check(long val) {
        boolean ok = false;
        long x = sum[(int) n] * (val / n) + sum[(int) (val % n)];
        for (int i = 1; i <= n; ++i) {
            if (x <= k) {
                ok = true;
                break;
            }
            if (val + i > n * m)
                return ok;
            if (s.charAt(i) == 'x')
                x -= 1;
            if (s.charAt((int) ((val + i - 1) % n) + 1) == 'x') {
                x += 1;
            }
        }
        if (x <= k)
            ok = true;
        return ok;
    }

}