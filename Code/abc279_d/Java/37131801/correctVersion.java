import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        double a = sc.nextDouble();
        double b = sc.nextDouble();
        double low = 1;
        double high = 1e18;
        for (int i = 0 ; i < 5000 ; i++) {
            double c1 = (low * 2 + high) / 3;
            double c2 = (low + high * 2) / 3;
            if (f(a,b,c1) > f(a,b,c2)) low = c1;
            else high = c2;
        }
        System.out.println(Math.min(f(a,b,Math.floor(low)), f(a,b,Math.ceil(low))));


    }
    static double f (double a, double b, double x) {
        return b * (x - 1) + a / Math.sqrt(x);
    }
    
}