import java.util.*;
import java.io.*;
import java.math.*;

public class Main{

	static FastScanner sc ;
	static PrintWriter pw ;

	static final int inf = Integer.MAX_VALUE / 2 ;
//	static final long inf = Long.MAX_VALUE / 2L ;
	static final String LF = "\n" ;
	static final String yes = "Yes" ;
	static final String no = "No" ;
	static final int mod = 1000000007 ;
//	static final int mod = 998244353 ;

public static void solve() {
	
	int N = ni() ;
	String S = ns() ;
	
	TreeSet<String> res = new TreeSet<String>() ;
	res.add( S ) ;
	
	int left = 0 ;
	for ( ; left < S.length() ; left++ ) if ( S.charAt( left ) == 'p' ) break ;
	
	String T = S.substring( 0 , left ) ;
	for ( int i = left + 1 ; i <= S.length() ; i++ ) {
		StringBuilder sb = new StringBuilder( T ) ;
		sb.append( f( S.substring( left , i ) ) ) ;
		sb.append( S.substring( i , S.length() ) ) ;
		res.add( sb.toString() ) ;
	}
	pr( res.first() ) ;
}

public static String f( String s ) {
	StringBuilder sb = new StringBuilder() ;
	for ( int i = 0 ; i < s.length() ; i++ ) {
		char ch = s.charAt( s.length() - 1 - i ) == 'd' ? 'p' : 'd' ;
		sb.append( ch ) ;
	}
	return sb.toString() ;
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
public static long nl() { return Long.parseLong( sc.next() ) ;}
public static long[] nla( int N ) { long[] res = new long[N]; Arrays.setAll( res , i -> nl() ) ; return res ; }
public static String ns() { return sc.next() ;}
public static String[] nsa( int N ) { String[] res = new String[N]; Arrays.setAll( res , i -> ns() ) ; return res ; }
public static void pr( int[] o ) { for ( int i = 0 ; i < o.length - 1 ; i++ ) pw.print( o[i] + " " ) ; pw.println( o[o.length-1] ) ; }
public static void pr( long[] o ) { for ( int i = 0 ; i < o.length - 1 ; i++ ) pw.print( o[i] + " " ) ; pw.println( o[o.length-1] ) ; }
public static void pr( Object o ) { pw.println( o ) ; }
public static void pr( Object... o ) { for ( int i = 0 ; i < o.length - 1 ; i++ ) pw.print( o[i] + " " ) ; pw.println( o[o.length-1] ) ; }
public static void debug( Object... o ) { pr( o ) ; pw.flush() ; }
public static int max( int... x ) { int res = x[0] ; for ( int i = 1 ; i < x.length ; i++ ) res = Math.max( res , x[i] ) ; return res ; }
public static int min( int... x ) { int res = x[0] ; for ( int i = 1 ; i < x.length ; i++ ) res = Math.min( res , x[i] ) ; return res ; }
public static long max( long... x ) { long res = x[0] ; for ( int i = 1 ; i < x.length ; i++ ) res = Math.max( res , x[i] ) ; return res ; }
public static long min( long... x ) { long res = x[0] ; for ( int i = 1 ; i < x.length ; i++ ) res = Math.min( res , x[i] ) ; return res ; }
public static long pow( int a , int b ) { long res = 1L ; for ( int i = 0 ; i < b ; i++ ) res *= (long)a ; return res ; }
public static long[] getsum( int[] a ) { long[] res = new long[a.length+1] ; for ( int i = 0 ; i < a.length ; i++ ) res[i+1] += res[i] + a[i] ; return res ; }
public static long[] getsum( long[] a ) { long[] res = new long[a.length+1] ; for ( int i = 0 ; i < a.length ; i++ ) res[i+1] += res[i] + a[i] ; return res ; }
public static int lb( int[] A , int key ) { int i = Arrays.binarySearch( A , key ) ; if ( i < 0 ) return -1 * ( i + 1 ) ; while ( i >= 0 && A[i] == key ) i-- ; return i + 1 ; }
public static int ub( int[] A , int key ) { int i = Arrays.binarySearch( A , key ) ; if ( i < 0 ) return -1 * ( i + 1 ) ; while ( i < A.length && A[i] == key ) i++ ; return i ; }
public static int lb( long[] A , long key ) { int i = Arrays.binarySearch( A , key ) ; if ( i < 0 ) return -1 * ( i + 1 ) ; while ( i >= 0 && A[i] == key ) i-- ; return i + 1 ; }
public static int ub( long[] A , long key ) { int i = Arrays.binarySearch( A , key ) ; if ( i < 0 ) return -1 * ( i + 1 ) ; while ( i < A.length && A[i] == key ) i++ ; return i ; }
}
