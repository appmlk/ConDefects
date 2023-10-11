import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double A = sc.nextDouble();
        double B = sc.nextDouble();
        double ans = B/A;
        System.out.println(String.format("%.3g%n", ans));
    }
}