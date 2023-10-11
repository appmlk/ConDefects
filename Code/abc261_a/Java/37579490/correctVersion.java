
import java.util.*;

public class Main {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int l1=sc.nextInt();
        int r1=sc.nextInt();
        int l2=sc.nextInt();
        int r2=sc.nextInt();
        int ans=0;
        if(l2<r1&&l1<r2){
            ans+=(Math.min(r1, r2)-Math.max(l1, l2));
        }
        System.out.println(ans);


        
    }
}
