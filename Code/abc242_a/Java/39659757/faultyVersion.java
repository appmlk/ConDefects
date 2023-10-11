import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        double a = sc.nextDouble();
        double b = sc.nextDouble();
        double c = sc.nextDouble();
        double x = sc.nextDouble();
        double output = 0.0;
        if(x <= a){
            output = 1.0;
        } else {
            output = c /(b-a);
        }
        System.out.printf("%.12f",output);
        sc.close();
    }
}