import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = 0;
        int[] a = new int[m];
        for(int i = 0; i < m; i++) {
            a[i] = sc.nextInt();
        }

        for(int j = 1; j <= n; j++) {
            if ((a[k] - j) < 0) k++;
            System.out.println(a[k] - j);
        }
    }
}
