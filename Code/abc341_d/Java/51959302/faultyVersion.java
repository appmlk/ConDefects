import java.sql.PreparedStatement;
import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    static Scanner sc = new Scanner(System.in);
    static void cl() throws Exception{
        br.close();
        sc.close();
        pw.close();
    }
    static long gcd(long a, long b){
        return b == 0 ? a : gcd(b, a % b);
    }
    public static void main(String[] args) throws Exception{
        long x = sc.nextLong();
        long y = sc.nextLong();
        long k = sc.nextLong();
        long c = x / gcd(x, y) * y;
        long l = 1, r = (long) 1e10;
        while(l < r){
            long mid = (l + r) / 2;
            if(k <= mid / x + mid / y - 2 * (mid / c)){
                r = mid;
            }else{
                l = mid + 1;
            }
        }
        pw.print(l);
        pw.flush();
        cl();
    }
}