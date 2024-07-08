import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        long D = sc.nextLong();

        long res = D;
        for (int x = 0; x <= 2000000; x++) {
            if (x * x > D) {
                res = Math.min(res, x * x - D);
                break;
            } else {
                long y = (long) Math.sqrt(Math.abs(x * x - D));
                long res1 = Math.min(Math.abs(x * x + y * y - D), Math.abs(x * x + (y + 1) * (y + 1) - D));
                res = Math.min(res, res1);
            }
        }

        System.out.println(res);

    }

}