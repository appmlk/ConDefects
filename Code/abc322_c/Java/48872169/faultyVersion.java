import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] a = new int[m + 1];
        for (int i = 0; i < m; i++) {
            a[i] = sc.nextInt();
        }
        for (int i = 1, j = 1; i <= n; i++) {
            if (i == a[j]) {
                System.out.println(0);
                j++;
            } else if (i < a[j]) System.out.println(a[j] - i);
        }
    }
}


