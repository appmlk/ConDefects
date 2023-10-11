import java.util.Scanner;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        double a = sc.nextInt();
        double b = sc.nextInt();
        sc.close();
        double x = b / a;

        BigDecimal bd = new BigDecimal(x).setScale(3, RoundingMode.HALF_UP);
        x = bd.doubleValue();

        System.out.println(x);
    }
}