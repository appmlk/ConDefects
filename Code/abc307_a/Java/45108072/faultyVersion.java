import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int sum = 0;
        for (int i = 1; i <= n; i ++) {
            int a = sc.nextInt();
            sum += a;
            if (i % 7 == 0) {
                System.out.println(sum);
                sum = 0;
            }
        }
    }
}