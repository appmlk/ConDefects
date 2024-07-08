
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int n = sc.nextInt();

            int prev = -1;
            while (n > 0) {
                int number = n % 10;
                if (number <= prev) {
                    System.out.println("No");
                    return;
                }
                prev = number;
                n /= 10;
            }

            System.out.println("Yes");
        }
    }
}
