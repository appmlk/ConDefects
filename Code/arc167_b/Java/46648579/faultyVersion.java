import java.math.BigInteger;
import java.util.Scanner;

/**
 * @ProjectName: study3
 * @FileName: Ex14
 * @author:HWJ
 * @Data: 2023/10/15 20:28
 */
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        long A = input.nextLong();
        long B = input.nextLong();
        BigInteger[] num = new BigInteger[1000005];
        int p = 0;
        for (int i = 2; i <= Math.sqrt(A); i++) {
            long cont = 0;
            while (A % i == 0) {
                A /= i;
                cont++;
            }
            if (cont > 0) {
                num[p++] = new BigInteger(String.valueOf(cont));
            }
            if (A == 1) {
                break;
            }
            if (i > A) {
                break;
            }
        }
        if (A > 0){
            num[p++] = new BigInteger("1");
        }
        BigInteger ans = new BigInteger(String.valueOf(B));
        for (int i = 0; i < p; i++) {
            ans = (ans.multiply((num[i].multiply(new BigInteger(String.valueOf(B)))).add(new BigInteger("1"))));
        }
        ans = ans.divide(new BigInteger("2"));
        ans = ans.mod(new BigInteger("998244353"));
        System.out.println(ans);
    }

}
