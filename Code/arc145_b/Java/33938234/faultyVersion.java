
import java.util.*;
public class Main{
    static long dp[][] = new long[5005][5005];
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long n = in.nextLong();
        long a = in.nextLong();
        long b = in.nextLong();
        if(n<a){
            System.out.println(0);
            return;
        }
        if(a>b){
            long ans=(n/a-1)*b;
            if(n%a>=b)
                ans+=b-1;
            else
                ans+=n%a;
            System.out.println(ans);
        }else{
            System.out.println(n-a+1);
        }
    }

}
