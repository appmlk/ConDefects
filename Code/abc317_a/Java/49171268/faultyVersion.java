import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int h = sc.nextInt();
        int x = sc.nextInt();
        int count = 0;
        for (int i = 1; i <= n; i++) {
            int p = sc.nextInt();
            if (h+p >= x) count++;
        }
        System.out.println(count);
    }
}
