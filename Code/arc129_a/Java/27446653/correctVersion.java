import java.util.*;

public class Main {
    
    public static void main(String[] args) throws Exception {
        
        Scanner sc = new Scanner(System.in);
        long N = sc.nextLong();
        long L = sc.nextLong();
        long R = sc.nextLong();
        char[] s = Long.toString(N,2).toCharArray();
        int X = s.length;
        long ans = 0;
        for( int i = 0; i < X; i++ ) {
            if( s[i] == '0' ) continue;
            long high = Math.min((long)Math.pow(2,X-i)-1,R);
            long low = Math.max((long)Math.pow(2,X-i-1),L);
            if( high < L || low > R ) continue;
            ans += high-low+1;
        }
        
        System.out.println(ans);
        
    }
    
}