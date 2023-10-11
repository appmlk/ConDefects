import java.util.Scanner;
import java.math.BigDecimal;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    BigDecimal result = new BigDecimal("0");
    
    for(int i = 0; i < 64; i++) {
      BigDecimal plus = new BigDecimal("0");
      if(sc.nextInt() == 1) {
        plus = pow2(i);
      }
      result = result.add(plus);
    }
    System.out.println(result.longValue());
  }
  
  public static BigDecimal pow2(int n) {
    if(n == 0) {
      return BigDecimal.valueOf(1);
    }
    return pow2(n - 1).multiply(BigDecimal.valueOf(2));
  }
}