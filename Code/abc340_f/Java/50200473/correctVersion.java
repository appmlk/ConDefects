
import java.util.*;

public class Main {
    //给你一个二维坐标点 (x,y)
    //求一个二维坐标 (a,b),满足 (a,b),(x,y),(0,0) 所围成的三角形的面积为 1
    //可以获得公式：|AX * BY| = 2
    //可以使用扩欧
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long xx = sc.nextLong(), yy = sc.nextLong();

        long d = exgcd(yy, -xx);
        //  System.out.println(d + " " + x + " " + y);
        //输出 2 1 -1 表示 8 和 6 的 gcd 为 2 , (1,-1) 是方程 8 * x + 6 * y = 2 的一组特解
        if (Math.abs(d) > 2) {
            System.out.println(-1);
            return;
        }
        if (d == 1 || d == -1) {
            x *= 2;
            y *= 2;
        }
        

        System.out.println(x + " " + y);
        //  System.out.println(triangleSquare(0, 0, x, y, xx, yy));
    }

    //给定三个点坐标，求三角形面积
    static double triangleSquare(long x1, long y1, long x2, long y2, long x3, long y3) {
        return Math.abs(x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / 2.0;
    }

    static long x, y;

    static long exgcd(long a, long b) {
        if (b == 0) {
            x = 1;
            y = 0;
            return a;
        }
        long d = exgcd(b, a % b);
        long x1 = x, y1 = y;
        x = y1;
        y = x1 - (a / b) * y1;
        return d;
    }

    //求 a 和 b 的最大公约数,做法：辗转相除法
    static long getGCD(long a, long b) {
        if (b == 0) return a;
        if (a % b == 0) return b;
        return getGCD(b, a % b);
    }
}
