import java.util.*;
import java.io.*;

public class Main {
    static MyScanner sc;
    static PrintWriter out;
    static {
        sc = new MyScanner();
        out = new PrintWriter(System.out);
    }
    public static void solve() {
        long num = 998244353;
        long a = sc.nextLong() % num;
        long b = sc.nextLong() % num;
        long c = sc.nextLong() % num;
        long d = sc.nextLong() % num;
        long e = sc.nextLong() % num;
        long f = sc.nextLong() % num;
        long x = (((a * b) % num) * c) % num;
        long y = (((d * e) % num) * f) % num;
        long ans = (x - y + num) % num;
        out.println(ans);
    }
    public static void main(String[] args) {
        int t = 1;
        while(t-- > 0)
            solve();
        out.flush();
    }
}

class MyScanner {
    BufferedReader br;
    StringTokenizer tok;
    MyScanner() {
        try { br = new BufferedReader(new InputStreamReader(System.in)); }
        catch(Exception e) { System.out.println(e); }
        tok = new StringTokenizer("");
    }
    public String next() {
        try {
            while(!tok.hasMoreTokens()) tok = new StringTokenizer(br.readLine());
        }
        catch(Exception e) { System.out.println(e); }
        return tok.nextToken();
    }
    public int nextInt() {
        return Integer.parseInt(next());
    }
    public long nextLong() {
        return Long.parseLong(next());
    }
    public double nextDouble() {
        return Double.parseDouble(next());
    }
}
