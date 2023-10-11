import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        if (b == 0) {
          System.out.println("0.000");
        } else if (a == b) {
            System.out.println("1.000");
        } else {
            int c = b * 10000 / a;
            if (c % 10 < 5) {
                System.out.println("0." + c / 10);
            } else {
                System.out.println("0." + c / 10 + 1);
            }
        }
    }
}