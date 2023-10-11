
import java.math.BigInteger;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int times = sc.nextInt();
        long[][] num = new long[n][2];

        for (int i = 0; i < n; i++) {
            num[i][0] = sc.nextInt();
            num[i][1] = sc.nextInt();
        }
        int tempn = times - 1;
        BigInteger sumai = new BigInteger("0");
        BigInteger res = null;
        for (int i = 0; i < n; i++) {
            if (tempn == -1)
                continue;
            sumai = sumai.add(new BigInteger(num[i][0]+"")).add(new BigInteger(num[i][1]+""));
            //res = 前ai、bi项求和+(times-i)*bi;i为第i关
            BigInteger bi = sumai.add(new BigInteger(num[i][1]+"").multiply(new BigInteger(tempn+"")));
            tempn--;
            //赋初值
            if(i == 0) {
                 res = bi;
            }else//获取较小值
                if (res.compareTo(bi) > 0)
                    res = bi;
        }
        System.out.println(res);
    }
}
