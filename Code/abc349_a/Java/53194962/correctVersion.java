import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int sum = 0;
        for (int i = 1; i <= n - 1; i++) {
            int a = sc.nextInt();
            sum += a;
        }
        System.out.println(0 - sum);
        sc.close();
    }
}