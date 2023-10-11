import java.util.Scanner;

public class Main {
    static final int mod = 992844353;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        long n = scan.nextLong();
        long res = 0, p = 10, q = 1;
        while (true) {
            long m = Math.min(n, p - 1) - q + 1;
            m %= mod;
            res = (res + (m * (m + 1) / 2) % mod) % mod;
            if (p > n) break;
            p *= 10;
            q *= 10;
        }
        System.out.println(res);
    }
}