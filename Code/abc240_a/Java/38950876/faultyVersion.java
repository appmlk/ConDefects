import static java.lang.System.*;
import java.util.*;

public class Main{
    public static void solve(){
        Scanner sc = new Scanner(in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        if(a == 1 && b == 10){
            out.println("Yes");
            return;
        }
        if(a == b + 1) out.println("Yes");
        else out.println("No");
    }
    public static void main(String[] args) {
        solve();
    }
}