import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        int n = 0;
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        int ans = 0;
        boolean[][] g = new boolean[100][100];
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        for (int k = 0; k < n; k++) {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            d = sc.nextInt();
            for (int i = a; i < b; i++) {
                for (int j = c; j < d; j++) {
                    g[i][j] = true;
                }
            }
        }

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (g[i][j] == true) {
                    ans++;
                }
            }
        }

        System.out.println(ans);
    }
}