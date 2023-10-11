import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[2 * n + 1];
        for (int i = 0 ; i <= n ; i++) {
            for (int j = 0 ; j < 2 * n + 1 ; j++) {
                if (a[j] == 0) {
                    System.out.println(a[j] + 1);
                    System.out.flush();
                    a[j]++;
                    break;
                }
            }
            int m = sc.nextInt();
            a[m - 1]++;
        }
        System.out.println(sc.nextInt());
        return;
        
    }
}
