
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();//序列长度
        int m = sc.nextInt();//数字范围：1 ~ m
        int k = sc.nextInt();//相邻数字最小差值
        long[][] f = new long[n + 1][m + 1];
        //f[i][j]表示长度为 i 的序列，最后一个数选 1 ~ j （最后一个数不超过 j ），所构成的方案数
        int mod = 998244353;
        for (int i = 1; i <= m; i++) {
            f[1][i] = i;
        }
        for (int i = 2; i <= n; i++) {//枚举每次的数字
            for (int j = 1; j <= m; j++) {//枚举第 i 个数
                if (k == 0) {
                    f[i][j] += f[i - 1][m];//当前状态可以由之前的任意状态转移过来
                    f[i][j] %= mod;
                    continue;
                }

                if (j + k <= m) {
                    f[i][j] += f[i - 1][m] - f[i - 1][j + k - 1];
                    f[i][j] %= mod;
                }
                if (j - k > 0) {
                    f[i][j] += f[i - 1][j - k];
                    f[i][j] %= mod;
                }
            }
            for (int j = 1; j <= m; j++) {
                f[i][j] += f[i][j - 1];
                f[i][j] %= mod;
            }

        }
        System.out.println(f[n][m]);


    }
}
