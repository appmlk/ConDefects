import java.util.*;
import static java.lang.Math.abs;
import static java.lang.Math.min;

/**
 * F - Main Street
 */
class Main {
    public static void main(String[] arg) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            long b  = scanner.nextLong();
            long k  = scanner.nextLong();
            long sx = scanner.nextLong();
            long sy = scanner.nextLong();
            long gx = scanner.nextLong();
            long gy = scanner.nextLong();
            Pos[] spos = positions(sx, sy, b);
            Pos[] gpos = positions(gx, gy, b);
            long m = Long.MAX_VALUE;
            for (Pos sp : spos) {
                for (Pos gp : gpos) {
                    m = min(dist(sx, sy, sp.x, sp.y, b, k) +
                            dist(sp.x, sp.y, gp.x, gp.y, b, k) +
                            dist(gp.x, gp.y, gx, gy, b, k), m);
                }
            }
            System.out.println(m);
        }
    }

    public static class Pos {
        final long x;
        final long y;
        Pos(long x, long y) { this.x = x; this.y = y; }
    }

    static Pos[] positions(long x, long y, long b) {
        long xb = x % b;
        long yb = y % b;
        return new Pos[] {
            new Pos(x, y),
            new Pos(x - xb, y),
            new Pos(x, y - yb),
            new Pos(x - xb + b, y),
            new Pos(x, y - yb + b)};
    }

    static long dist(long x1, long y1, long x2, long y2, long b, long k) {
        long d = abs(x2 - x1) + abs(y2 - y1);
        long x1b = x1 % b;
        long y1b = y1 % b;
        long x2b = x2 % b;
        long y2b = y2 % b;
        if ((x1b == 0 && y2b == 0) || (y1b == 0 && x2b == 0)) {
        } else if (x1b == 0 && x2b == 0) {
            if (y1 - y1b == y2 - y2b && x1 != x2) {
                long vy1 = y1b + y2b;
                long vy2 = (b - y1b) + (b - y2b);
                d = abs(x2 - x1) + min(vy1, vy2);
                d = min(d, abs(x2 - x1) * k + abs(y2 - y1));
            }
        } else if (y1b == 0 && y2b == 0) {
            if (x1 - x1b == x2 - x2b && y1 != y2) {
                long vx1 = x1b + x2b;
                long vx2 = (b - x1b) + (b - x2b);
                d = min(vx1, vx2) + abs(y2 - y1);
                d = min(d, abs(x2 - x1) + abs(y2 - y1) * k);
            }
        } else {
            d *= k;
        }
        return d;
    }
}
