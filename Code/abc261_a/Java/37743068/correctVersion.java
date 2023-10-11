import java.math.BigInteger;
import java.util.*;

public class Main {
      static int N=(int)110;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
       int l1=sc.nextInt(),r1=sc.nextInt();
       int l2=sc.nextInt(),r2=sc.nextInt();
       int max=Math.max(l1,l2);
       int min=0;
     if (l2<r1&&r1<=r2||l1<r2&&r2<=r1){
         min=Math.min(r1,r2);
         System.out.println(min-max);
         return;
     }
        System.out.println(0);

        }
    }


