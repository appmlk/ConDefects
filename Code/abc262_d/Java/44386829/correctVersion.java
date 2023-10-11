import java.util.Scanner;
 
public class Main{
      public static int mod(int[] arr) {
 
        int mod = 998244353;
        int n = arr.length;
        int ans = 0;
        for (int m = 1; m <= n; ++m) {
            int[][] f = new int[m + 1][m]; // n + 1 = mod  m = 余数
            f[0][0] = 1;
            for (int i = 1; i <= n; ++i) {
                for (int j = m; j > 0; --j) {
                    for (int k = 0; k < m; ++k) {
                        f[j][(k + arr[i - 1]) % m] = (f[j][(k + arr[i - 1]) % m] + f[j - 1][k]) % mod;
                    }
                }
            }
            ans = (ans + f[m][0]) % mod;
        }
        return ans;
    }
 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        System.out.println(mod(arr));
    }
}