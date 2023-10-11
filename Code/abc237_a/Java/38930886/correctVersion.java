import static java.lang.System.*;
import java.util.*;

public class Main{
    public static void solve(){
        Scanner sc = new Scanner(in);
        long N = sc.nextLong();
        if(N >= Integer.MIN_VALUE && N <= Integer.MAX_VALUE){
            out.println("Yes");
        }else out.println("No");
    }
    public static void main(String[] args) {
        solve();
    }
}