import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        int[] f = new int[(int)1e6];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            f[a[i]]++;
        }
        for (int p10 = 1; p10 < (int)1e6; p10 *= 10) {
            for (int i = 0; i < f.length; i++) {
                if (i / p10 % 10 > 0) {
                    f[i] += f[i - p10];
                }
            }
        }
        long ans = 0;
        for (int x : a) {
            ans += f[999999 - x];
            while (x > 0) {
                if (x % 10 > 4) break;
                x /= 10;
            }
            if (x == 0) ans--;
        }
        System.out.println(ans);
    }
}