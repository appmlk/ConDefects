//ここからimport文
import java.util.*;
import java.io.*;

//import mylib.* ;
//ここまでimport文

public class Main{

	static final int inf = Integer.MAX_VALUE / 2 ;
	static final long longInf = Long.MAX_VALUE / 2L ;
	static final int minf = inf * -1 ;
	static final String LF = "\n" ;
	static final int mod = 1000000007 ;
	


public static void main(String[] args) {

	Scanner sc = new Scanner( System.in ) ;
	PrintStream ps = new PrintStream( System.out ) ;
	StringBuilder sb = new StringBuilder() ;
	
	
	long L = sc.nextLong() ;
	long R = sc.nextLong() ;
	
	solve( L , R ) ;
	
	sc.close() ;
	ps.close() ;
}


public static void solve( long L , long R ) {
	for ( long i = R - L ; ; i-- ) {
		for ( long j = 0 ; j < ( R - L ) - i + 1L ; j++ ) {
			if ( gcd( L + j , L + j + i ) == 1L ) {
				System.out.println( i ) ;
				return ;
			}
		}
	}
}


public static long gcd( long a, long b ) {
	if ( b > a ) return gcd( b, a ) ;
	return b == 0L ? a : gcd( b, a % b ) ;
}


}
