import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double A = scanner.nextInt();
        double B = scanner.nextInt();
        double C = scanner.nextInt();
        double X = scanner.nextInt();

        double probability = 1;

        if (A < X) {
            probability = C / (B - A);
        }

        if (B < X) {
            probability = 0;
        }
        System.out.println(probability);
    }
}
