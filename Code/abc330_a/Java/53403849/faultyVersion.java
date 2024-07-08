import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int L = sc.nextInt();
        int ans = 0;
        for(int i=0;i<N;i++){
            int a = sc.nextInt();
            if(a>=L)ans++;
        }
        System.err.println(ans);
    }
}
