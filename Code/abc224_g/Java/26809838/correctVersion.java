import java.util.*;

public class Main {
    void solve() {
        Scanner in = new Scanner(System.in);
        long n = in.nextLong();
        long s = n + 1 - in.nextLong();
        long t = n + 1 - in.nextLong();
        long b = in.nextLong();
        long a = in.nextLong();
        double left = 0;
        double right = 10000000000000000000.0;
        for (int i = 0; i < 100; i++) {
            double mid = (left + right) / 2;
            long cSub = Math.min((long)Math.ceil((mid + a) / b), n - t + 1);
            double prb = (n - cSub) * (mid + a);
            prb += b * cSub * (cSub - 1) / 2;
            if (mid * n > prb) {
                right = mid;
            } else {
                left = mid;
            }
        }
        double ans = left + a;
        if (t <= s) {
            ans = Math.min(ans, (s - t) * b);
        }
        System.out.println(ans);
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}
