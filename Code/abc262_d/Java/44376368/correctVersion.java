import java.io.*;
import java.util.*;
public class Main {
    public static Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    public static int mod = 998244353;
    public static int inv100 = 828542813;

    public static void main(String args[]) {
        int n = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = sc.nextInt();
        }
        int f[][] = new int[n + 1][];
        //f[0][0] = 1;
        int ans = 0;
        for(int m = 1; m <= n; ++m) {
            for(int i = 0; i <= n; ++i) {
                f[i] = new int[m];
            }
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
        out.println(ans % mod);
        out.close();
    }
}