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
        
        long x = sc.nextLong();
        long y = sc.nextLong();
        long a = sc.nextLong();
        long b = sc.nextLong();
        long c = sc.nextLong();
        if( allVertical(x,y,a,b,c) || allVertical(y,x,a,b,c) ||  oneHorizonal(x,y,a,b,c) || oneHorizonal(x,y,b,a,c) || oneHorizonal(x,y,c,a,b) || oneHorizonal(y,x,a,b,c) || oneHorizonal(y,x,b,a,c) || oneHorizonal(y,x,c,a,b) ) pw.println("Yes");
        else pw.println("No");
        
    }
    
    static boolean allVertical(long x, long y, long a, long b, long c) {
        
        long xsum = 0;
        xsum += (a+y-1)/y;
        xsum += (b+y-1)/y;
        xsum += (c+y-1)/y;
        
        return xsum <= x;
        
    }
    
    static boolean oneHorizonal(long x, long y, long a, long b, long c) {
        
        long xsum = 0;
        y -= (a+x-1)/x;
        if( y <= 0 ) return false;
        
        xsum += (b+y-1)/y;
        xsum += (c+y-1)/y;
        
        return xsum <= x;
        
    }
    
}