import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner kbs = new Scanner(System.in);
        long n1 = kbs.nextLong();
        long n2 = kbs.nextLong();
        do {
            if (n1 % 10 + n2 % 10 >= 10) {
                System.out.println("Hard");
                return;
            }
            n1 /= 10;
            n2 /= 10;
        } while (n1 != 0 && n2 != 0);
        System.out.println("Easy");
    }
}