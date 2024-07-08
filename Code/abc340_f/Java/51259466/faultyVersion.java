import java.util.Scanner;

public class Main {
    private static long[] exgcd(long a, long b) {
        if (b == 0) {
            return new long[] { 1, 0, a };
        }
        long[] xy = exgcd(b, a % b);
        long x = xy[0], y = xy[1];
        xy[0] = y;
        xy[1] = a / b * x - y;
        return xy;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        long a, b;
        a = in.nextLong();
        b = in.nextLong();
        long[] res = exgcd(Math.abs(b), Math.abs(a));
        long x = res[0], y = res[1], g = res[2];
        if (g > 2)
            System.out.println(-1);
        else {
            if (g == 1) {
                x *= 2;
                y *= 2;
            }
            if (Math.abs(x) > 1E17 || Math.abs(y) > 1E17)
                System.out.println(-1);
            if (b < 0)
                x *= -1;
            if (a > 0)
                y *= -1;
            System.out.printf("%d %d", x, y);
        }
        in.close();
    }
}