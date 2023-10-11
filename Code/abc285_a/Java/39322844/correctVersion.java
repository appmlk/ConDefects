import static java.lang.System.*;
import java.util.*;

public class Main{
    public static void solve(){
        Scanner sc = new Scanner(in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        out.println(a * 2 == b || a * 2 + 1 == b ? "Yes" : "No");
    }
    public static void main(String[] args) {
        solve();
    }
}