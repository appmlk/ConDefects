import java.io.BufferedInputStream;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int n = sc.nextInt();
        long[] arr = new long[n];

        long[] presum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextLong();
            presum[i + 1] = presum[i] + arr[i];
        }

//        // *)
//        long[][] opt = new long[n][n];
//
//        // 暴力枚举
//        for (int i = 0; i < n; i++) {
//            for (int j = i + 1; j < n; j++) {
//                // 快速获取，有几个 Ai, Aj
//
//            }
//        }


        // n - 2
        // x - 2, n - x

        // 背包问题吗？
        // 7 -> 6 + 1
        // 1 + 4 + 2

        long[] pack = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            int b = i - 1;

            long val = 0;
            if (b % 2 == 0) {
                val = presum[b / 2] * 2;
            } else {
                val = presum[(b + 1) / 2] * 2 - arr[(b + 1) / 2];
            }

            for (int j = 0; j + i <= n; j++) {
                pack[j + i] = Math.max(pack[j + i], pack[j] + val);
            }
        }

        System.out.println(pack[n]);

    }

}
