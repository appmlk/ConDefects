import java.util.*;
import java.io.PrintWriter;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        BigDecimal x = sc.nextBigDecimal();
        
        double result = x.setScale(0,BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(result);
    }
}