import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        char[] s = sc.next().toCharArray();
        long n = sc.nextLong();

        long t = 1, sum = 0;
        for (int i = s.length - 1; i >= 0; --i) {
            if (s[i] == '1') {
                sum += t;
            }
            t *= 2;
        }

        for (int i = 0; i < s.length; ++i) {
            t /= 2;
            if (s[i] == '?') {
                if (sum + t <= n) {
                    sum += t;
                }
            }
        }

        System.out.println(sum <= n ? sum : -1);

        sc.close();
    }
}
