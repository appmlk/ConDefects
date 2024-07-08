import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        long X = input.nextLong();
        BigDecimal x = new BigDecimal(X);
        BigDecimal divisor = new BigDecimal("10.0");
        BigDecimal div = x.divide(divisor);
        System.out.println( div.setScale(0, RoundingMode.UP).toPlainString());
        input.close();
    }
}