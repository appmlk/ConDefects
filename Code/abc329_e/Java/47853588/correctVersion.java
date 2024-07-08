import java.io.*;
import java.util.*;
 
public class Main {
    private static FastScanner fs = new FastScanner();


	private static int rec ( int i , int isStrictStart , String a , String b , int n , int m , int[][] dp ) {
		if ( i == n ) {
			return 1 ;
		}
		if ( dp[i][isStrictStart] != -1 ) return dp[i][isStrictStart] ;
		int possible = 0 ;

		for ( int j = 0 ; j < m ; j ++ ) {
			if ( ( j > 0 ) && ( isStrictStart == 1 ) ) continue ;
			for ( int k = j ; k < m ; k ++ ) {
				int st = ( i - j ) ;
				int ed = st + m - 1 ;
				if ( ( st >= 0 ) && ( ed < n ) ) {
					String sa = a.substring(i, i + ( k - j ) + 1 ) ;
					String sb = b.substring(j, k + 1) ;
					if ( sa.equals(sb) ) {
						int isNexStrictStart = ( k == m - 1 ) ? 0 : 1 ;
						if ( possible == 0 ) possible = rec ( i + ( k - j ) + 1 , isNexStrictStart , a , b , n , m , dp ) ;
					}
				}
			}
		}
		dp[i][isStrictStart] = possible ;
		return possible ;
	}
    
    private static void Test_Case () {
        int n = fs.nextInt() ;
		int m = fs.nextInt() ;
		String a = fs.next() ;
		String b = fs.next() ;

		int[][] dp = new int[n + 1][3] ;
		for ( int i = 0 ; i <= n ; i ++ ) {
			for ( int j = 0 ; j < 3 ; j ++ ) {
				dp[i][j] = -1 ;
			}
		}
		System.out.println(( rec ( 0 , 1 , a , b , n , m , dp ) == 1 ) ? "Yes" : "No" ) ;	
    }
 
	public static void main(String[] subscribeToSecondThread) {
	    int t = 1 ;
	   // int t = fs.nextInt(); 
	   while ( t > 0 ) {
	       Test_Case() ;
	       t -- ;
	   }
	}
	static class FastScanner {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");
 
		String next() {
			while (!st.hasMoreTokens())
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			return st.nextToken();
		}
 
		int nextInt() {
			return Integer.parseInt(next());
		}
 
		int[] readArray(int n) {
			int[] a = new int[n];
			for (int i = 0; i < n; i++)
				a[i] = nextInt();
			return a;
		}
 
		long nextLong() {
			return Long.parseLong(next());
		}
	}
 

}