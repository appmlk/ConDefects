import java.util.*;
import java.io.*;
import java.math.*;
// import java.util.stream.Stream;

class Main{
    static BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static PrintWriter output = new PrintWriter(System.out);
    static Scanner sc = new Scanner(System.in);
    static long ans = -1;
    
    public static void main(String[] args) throws IOException{
        int n = sc.nextInt();
        int m = sc.nextInt();
        long [] a = new long[n];
        long now_m = 0;
        long now = 0;
        for(int i=0;i<n;i++) a[i] = sc.nextLong();
        for(int i=0;i<m;i++) now_m += a[i] * (i+1);
        for(int i=0;i<m;i++) now += a[i];
        long ans = now_m;
        for(int i=0;i<n-m;i++) {
            long next_m = now_m - now + a[i+m]*m;
            long next_t = now - a[i] + a[i+m];
            now_m = next_m;
            now = next_t;
            ans = Math.max(ans,now_m);
        }
        output.print(ans);
        output.flush();
    }
}
