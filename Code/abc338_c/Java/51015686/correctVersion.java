import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] q = new int[n + 1];
        int[] a = new int[n + 1];
        int[] b = new int[n + 1];
        int maxa = Integer.MAX_VALUE;
        int maxb = Integer.MAX_VALUE;
        int res = 0;
        for (int i = 1; i <= n; i++) {
            q[i] = sc.nextInt();
        }
        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
        }
        for (int i = 1; i <= n; i++) {
            b[i] = sc.nextInt();
        }
        for (int i = 1; i <= n; i++) {
            if(a[i] == 0)
                continue;
            maxa = Math.min(maxa,q[i] / a[i]);
        }
        for (int i = 0; i <= maxa; i++) {
            for (int j = 1; j <= n; j++) {
                if (b[j] == 0)
                    continue;
                maxb = Math.min(maxb,(q[j] - a[j] * i)/b[j]);
            }
            res = Math.max(res, i + maxb);
        }
        System.out.println(res);
    }
}
