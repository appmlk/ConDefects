import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long ans = 0L;
        String[] strAry = sc.nextLine().split(" ", 0);
        long[] longAry = new long[strAry.length];
        for (int i = 0; i < strAry.length; i++) {
            longAry[i] = Long.parseLong(strAry[i]);
        }
        for (int i = 0; i < 63; i++) {
            ans += longAry[i] << i;
        }
        if (longAry[63] == 1) {
            long L = 9223372036854775807L;
            BigInteger bians;
            bians = BigInteger.valueOf(ans + 1).add(BigInteger.valueOf(L));
            System.out.println(bians);
        }else {
            System.out.println(BigInteger.valueOf(ans));
        }
    }
}
