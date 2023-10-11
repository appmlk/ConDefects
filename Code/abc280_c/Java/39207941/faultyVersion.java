import static java.lang.System.*;
import java.util.*;

public class Main{
    public static void solve(){
        Scanner sc = new Scanner(in);
        char []ch1 = sc.next().toCharArray();
        char []ch2 = sc.next().toCharArray();
        for(int i = 0,j = 0; i < ch1.length; ++i,++j){
            if(ch1[i] != ch2[j]){
                out.println(i + 1);
                return;
            }
        }
        out.println(ch2[ch2.length - 1]);
    }
    public static void main(String[] args) {
        solve();
    }
}