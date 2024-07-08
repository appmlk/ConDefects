
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            long number = sc.nextLong();

            StringBuilder sb = new StringBuilder();

            number -= 1;

            while (number > 0) {
                sb.append(number % 5 * 2);
                number = number / 5;
            }

            sb.reverse();

            if (sb.length() < 1)
                sb.append(0);
            System.out.println(sb.toString());
        }
    }
}
