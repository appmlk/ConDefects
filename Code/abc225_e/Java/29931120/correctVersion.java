import java.util.*;
import java.io.*;

public class Main {
    
    public static Scanner sc = new Scanner(System.in);
    public static PrintWriter pw = new PrintWriter(System.out);
    
    public static void main(String[] args) {
        
        int t = 1;
        while( t > 0 ) {
            solve();
            t--;
        }
        
        pw.flush();
        
    }
    
    static void solve() {
        
        int N = sc.nextInt();
        Pair[] xy = new Pair[N];
        long px = 1;
        long py = 0;
        long ans = 0;
        for( int i = 0; i < N; i++ ) {
            long x = sc.nextInt();
            long y = sc.nextInt();
            xy[i] = new Pair(x,y);
        }
        
        Arrays.sort( xy, (p0,p1) -> p0.b*(p1.a-1) > p1.b*(p0.a-1) ? 1 : -1 );
        for( int i = 0; i < N; i++ ) {
            if( py*xy[i].a <= px*(xy[i].b-1) ) {
                ans++;
                px = xy[i].a-1;
                py = xy[i].b;
            }
        }
        
        pw.println(ans);
        
    }
    
}

class Pair {
    
    long a,b;
    
    public Pair(long a, long b) {
        
        this.a = a;
        this.b = b;
        
    }
    
}