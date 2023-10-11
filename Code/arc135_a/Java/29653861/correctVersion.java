//ここからimport文
import java.util.*;
import java.io.*;

//import mylib.* ;
//ここまでimport文

public class Main{

	static final int inf = Integer.MAX_VALUE / 2 ;
	static final int minf = inf * -1 ;
	static final String LF = "\n" ;
//	static final int mod = 1000000007 ;
	static final int mod = 998244353 ;
	
	static ArrayList<ArrayList<Integer>> G ;
	static HashMap<Long,Long> map ;


public static void main(String[] args) {

	Scanner sc = new Scanner( System.in ) ;
	PrintStream ps = new PrintStream( System.out ) ;
	StringBuilder sb = new StringBuilder() ;
	
	
	long X = sc.nextLong() ;
	map = new HashMap<Long,Long>() ;
	for ( long i = 1L ; i < 5L ; i++ ) map.put( i , i ) ;
	ps.println( func( X ) ) ;
	
	
	sc.close() ;
	ps.close() ;
}


public static long func( long x ) {
	if ( map.containsKey( x ) ) return map.get( x ) ;
	long res = ( ( func( floor( x ) ) % mod ) * ( func( ceil( x ) ) % mod ) ) % mod ;
	map.put( x , res ) ;
	return res ;
}


public static long floor( long x ) {
	return x / 2L ;
}


public static long ceil( long x ) {
	return ( x + 1L ) / 2L ;
}


}
