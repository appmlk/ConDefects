import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int A = sc.nextInt();
        int B = sc.nextInt();
        int C = sc.nextInt();

        int X = sc.nextInt();
        double rack = 0;

        if (X > A && X <= B) {
            rack = (double) C / (double) (B - A);
        } else if (X <= A) {
            rack = 1;
        } else {
            rack = 0;
        }
        System.out.println(String.format("%.12f", rack));
    }
}
