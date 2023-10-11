
import java.io.BufferedInputStream;
import java.util.Scanner;

public class Main {



    public static void main(String[] args) {

        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int n = sc.nextInt();
        int x = sc.nextInt();

        long ans = Long.MAX_VALUE;
        long acc = 0;
        for (int i = 0; i < n; i++) {
            int a = sc.nextInt(), b = sc.nextInt();
            acc = acc + a + b;
            long left = x - i - 1;
            ans = Math.min(ans, acc + left * b);
        }

        System.out.println(ans);


    }

}
