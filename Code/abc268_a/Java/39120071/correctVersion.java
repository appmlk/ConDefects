import static java.lang.System.*;
import java.util.*;

public class Main{
    public static void solve(){
        Scanner sc = new Scanner(in);
        int A = sc.nextInt();
        int B = sc.nextInt();
        int C = sc.nextInt();
        int D = sc.nextInt();
        int E = sc.nextInt();
        int []freq = new int[101];
            ++freq[A];
            ++freq[B];
            ++freq[C];
            ++freq[D];
            ++freq[E];
            int cnt = 0;
        for(int i = 0; i < 101; ++i){
            if(freq[i] >= 1)++cnt;
        }
        out.println(cnt);
    }
    public static void main(String[] args) {
        solve();
    }
}