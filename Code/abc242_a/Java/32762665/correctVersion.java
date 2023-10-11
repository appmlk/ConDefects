import java.util.*;

class Main {
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)) {
            double a = sc.nextDouble();
            double b = sc.nextDouble();
            double c = sc.nextDouble();
            double x = sc.nextDouble();
            double p = 0;
            if(1<=x && x<=a) {
                p=1;
            } else if(a+1<=x&&x<=b) {
                p=c/(b-a);
            }
            System.out.println(p);
        }
    }
}