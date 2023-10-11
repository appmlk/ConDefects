import java.util.*;
import java.lang.*;
import java.io.*;

public class Main
{
	PrintWriter out = new PrintWriter(System.out);
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer tok = new StringTokenizer("");
    String next() throws IOException {
        if (!tok.hasMoreTokens()) { tok = new StringTokenizer(in.readLine()); }
        return tok.nextToken();
    }
    int ni() throws IOException { return Integer.parseInt(next()); }
    long nl() throws IOException { return Long.parseLong(next()); }
    int[] na(int n) throws IOException { int[]A=new int[n]; for (int i=0;i<n;i++) A[i]=ni(); return A; }
    
    long mod=1000000007;
    
    void solve() throws IOException {
        int n=ni();
        int[]A=na(n);
        
        int x=0;
        for (int v:A) x^=v;
        
        boolean f=false;
        for (int v:A)
            if (x==v) f=true;
            
        if (f || n%2==1) out.println("Win");
        else out.println("Lose");
        out.flush();
    }
    
    int gcd(int a,int b) { return(b==0?a:gcd(b,a%b)); }
    long gcd(long a,long b) { return(b==0?a:gcd(b,a%b)); }
    long mp(long a,long p) { long r=1; while(p>0) { if ((p&1)==1) r=(r*a)%mod; p>>=1; a=(a*a)%mod; } return r; }
    
    public static void main(String[] args) throws IOException {
        new Main().solve();
    }
}