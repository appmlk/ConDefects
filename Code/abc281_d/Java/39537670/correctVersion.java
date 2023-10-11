import java.util.*;
import java.io.*;
import java.math.*;
//An apple a day

public class Main{
	static int maxn = 400005,n,m;
	static long INF = (long)1e18,ans = 0,mod = (int)1e9+7;
	static Scanner sc = new Scanner (System.in);
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StreamTokenizer st  =new StreamTokenizer(bf);
    static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    public static void main(String[]args) throws IOException{
    	int T = 1;
    	//T = I();
        while(T-->0) solve();
        pw.flush();
    }
    static final int I() throws IOException {
    	st.nextToken();
    	return (int)st.nval;
    }
    
    static String S() throws IOException{
    	String res = bf.readLine();
    	while(res.equals("")) res = bf.readLine();
    	return res;
    }
    
    
    static void solve() throws IOException{
    	n = I();m = I();int d = I();
    	long dp[][][] = new long[n+1][n+1][d];
    	for (long [][]a:dp)
    		for (long []b:a) Arrays.fill(b, -INF);
    	for (int i = 0 ; i <=n ; i++) dp[i][0][0] = 0;
    	for (int i = 1 ; i <=n ; i++) {
    		int x = I();
    		for (int j = 1 ; j <=Math.min(i, m) ; j++)
    			for (int k = 0 ; k <d ; k++) {
    				dp[i][j][k] = Math.max(dp[i-1][j][k],dp[i-1][j-1][((k-x)%d+d)%d] + x);
    			}
    	}
    	pw.println(dp[n][m][0] < 0?-1:dp[n][m][0]);
    }
}
