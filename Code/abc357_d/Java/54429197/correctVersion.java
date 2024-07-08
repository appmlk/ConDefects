import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static long m = 998244353;
    private static long mod(long a) { return ((a % m) + m) % m; }
    private static long mul(long a, long b) { return mod(mod(a) * mod(b)); }
    private static long mexp(long a, long p) {
        long res = 1;
        while(p > 0) { if((p & 1) == 1) res = mul(res, a); a = mul(a, a); p >>= 1; } 
        return res;
    }
    public static void main(String[] args) {
        long n = sc.nextLong(); int len = String.valueOf(n).length();
        long req = mexp(10, len);
        long num = mexp(req, n) - 1, den = mexp(req - 1, m - 2);
        System.out.println(mul(n, mul(num, den)));
    }
}