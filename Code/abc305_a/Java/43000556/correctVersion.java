import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();

        int rem = n % 5;

        int ans = n;

        if(rem >= 0 & rem < 3) {
            ans = ans - rem ;
        }else {
            ans = ans - rem + 5;
        }

        System.out.println(ans);
    }
}