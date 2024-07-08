import java.util.*;

public class Main{
      // 看过题解
      // https://atcoder.jp/contests/arc177/submissions/53415276
      public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] coins = new int[6];
        for (int i = 0; i < 6; i++) {
            coins[i] = scanner.nextInt();
        }
        int n = scanner.nextInt();
        int[] totals = new int[n];
        for (int i = 0; i < n; i++) {
            totals[i] = scanner.nextInt();
        }
        for (int t : totals) {
            if (!valid(coins, t)) {
                System.out.println("No");
                return;
            }
        }
        System.out.println("Yes");
    }

    public static boolean valid(int[] coins, int total) {
        int[] coinTypes = new int[]{1, 2, 10, 100, 200, 500};
        for (int i = 5; i >= 0; i--) {
            while (total >= coinTypes[i] && coins[i] > 0) {
                total -= coinTypes[i];
                coins[i]--;
            }
        }
        if (total > 0) {
            return false;
        }
        return true;
    }
}