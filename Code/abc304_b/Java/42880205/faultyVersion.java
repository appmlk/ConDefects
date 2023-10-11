import java.util.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main {
    public static void main(String[] args) {
        // 入力
  
        Scanner sc = new Scanner(System.in);

       BigDecimal num = BigDecimal.valueOf(sc.nextInt());
        int keta = Integer.toString(num.intValue()).length();
        if(keta<=4){
            System.out.println(num);
        }else{
            BigDecimal decimal = num.setScale(3-keta, RoundingMode.FLOOR);
            System.out.println(decimal.intValue());
        }
    

    }
}
