//ここからimport文
import java.util.*;
import java.io.*;
import java.math.*;

//import mylib.* ;
//ここまでimport文

public class Main{

	static FastScanner sc ;
	static PrintWriter pw ;

	static final int inf = Integer.MAX_VALUE / 2 ;
//	static final long inf = Long.MAX_VALUE / 2L ;
	static final String LF = "\n" ;
	static final int mod = 1000000007 ;
//	static final long mod = 4611686018427387847L ;

//	static ArrayList<ArrayList<Integer>> G ;

public static void solve() {
	
	long X = nl() ;
	
	if ( X < 100 ) {
		pr( X ) ;
		return ;
	}
	
	int k = String.valueOf( X ).length() ;
	int l = (int)( X / pow( 10 , k - 1 ) ) ;
	long res = 99999999999999999L ;
	while ( true ) {
		for ( int i = -9 ; i <= 9 ; i++ ) {
			int n = l ;
			String str = String.valueOf( n ) ;
			for ( int j = 1 ; j < k ; j++ ) {
				n += i ;
				if ( n < 0 || n > 9 ) break ;
				str += String.valueOf( n ) ;
			}
			if ( str.length() < k ) continue ;
			if ( Long.parseLong( str ) < X ) continue ;
			res = Math.min( res , Long.parseLong( str ) ) ;
			str = "" ;
		}
		l++ ;
		if ( l > (int)( X / pow( 10 , k - 1 ) ) + 2 ) break ;
	}
	
	pr( res ) ;

}

public static long pow( int a , int b ) {
	long res = 1L ;
	for ( int i = 0 ; i < b ; i++ ) res *= a ;
	return res ;
}

public static void main(String[] args) {
	sc = new FastScanner() ;
	pw = new PrintWriter( System.out ) ;
	solve() ;
	pw.flush() ;
	pw.close() ;
}

static class FastScanner {
    private final InputStream in = System.in;
    private final byte[] buffer = new byte[1024];
    private int ptr = 0;
    private int buflen = 0;
    private boolean hasNextByte() {
        if (ptr < buflen) {
            return true;
        }else{
            ptr = 0;
            try {
                buflen = in.read(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (buflen <= 0) {
                return false;
            }
        }
        return true;
    }
    private int readByte() { if (hasNextByte()) return buffer[ptr++]; else return -1;}
    private static boolean isPrintableChar(int c) { return 33 <= c && c <= 126;}
    private void skipUnprintable() { while(hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++;}
    public boolean hasNext() { skipUnprintable(); return hasNextByte();}
    public String next() {
        if (!hasNext()) throw new NoSuchElementException();
        StringBuilder sb = new StringBuilder();
        int b = readByte();
        while(isPrintableChar(b)) {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }
}

public static int ni() { return Integer.parseInt( sc.next() ) ;}
public static int[] nia( int N ) { int[] res = new int[N]; Arrays.setAll( res , i -> ni() ) ; return res ; }
public static void pi( int n ) { pw.println( n ) ; }
public static long nl() { return Long.parseLong( sc.next() ) ;}
public static void pl( long l ) { pw.println( l ) ; }
public static void pd( double d ) { pw.println( d ) ; }
public static String ns() { return sc.next() ;}
public static String[] nsa( int N ) { String[] res = new String[N]; Arrays.setAll( res , i -> ns() ) ; return res ; }
public static void ps( String s ) { pw.println( s ) ; }
public static void psb( StringBuilder sb ) { pw.println( sb.toString() ) ; }
public static BigDecimal nbd() { return new BigDecimal( sc.next() ) ; }
public static void pbd( BigDecimal bd ) { pw.println( bd.toPlainString() ) ; }
public static void pr( Object o ) { pw.println( o ) ; }

}

