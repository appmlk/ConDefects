import java.util.*;
import java.math.*;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int t = sc.nextInt();
        while(t-->0){
            Long n = sc.nextLong(), k =sc.nextLong();
            Long ans=0L;
            while(n>0)
            {
                ans+=n%3;
                n/=3;
            }
            if(ans<=k)
                System.out.println("Yes");
            else
                System.out.println("No");
        }

    }
}