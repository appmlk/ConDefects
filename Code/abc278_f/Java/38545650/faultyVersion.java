import java.util.*;
import java.io.*;
import java.math.*;

public class Main{

	static FastScanner sc ;
	static PrintWriter pw ;

	static final int inf = Integer.MAX_VALUE / 2 ;
	static final long linf = Long.MAX_VALUE / 2L ;
	static final String yes = "Yes" ;
	static final String no = "No" ;
	static final int mod1 = 1000000007 ;
	static final int mod = 998244353 ;

public static void solve() {
	
	int N = ni() ;
	String[] S = nsa( N ) ;
	for ( int i = 0 ; i < N ; i++ ) {
		S[i] = S[i].substring( 0 , 1 ) + S[i].substring( S[i].length() - 1 , S[i].length() ) ;
	}
	
	char[] w = new char[26] ;
	for ( int i = 0 ; i < 26 ; i++ ) w[i] = (char)( 'a' + i ) ;
	
	boolean[][] dp = new boolean[1<<N][26] ;
	for ( int i = 1 ; i < ( 1 << N ) ; i++ ) {
		for ( int j = 0 ; j < 26 ; j++ ) {
			for ( int k = 0 ; k < N ; k++ ) {
				if ( ( i & ( 1 << k ) ) == 0 ) continue ;
				if ( w[j] != S[k].charAt( 0 ) ) continue ;
				if ( Integer.bitCount( i ) == 1 ) dp[i][j] = true ;
				else dp[i][j] = !dp[i^(1<<k)][(int)(S[k].charAt( 1 ) - 'a')] ;
			}
		}
	}
	
	for ( int i = 0 ; i < 26 ; i++ ) {
		if ( dp[(1<<N)-1][i] ) {
			pr( "First" ) ;
			return ;
		}
	}
	
	pr( "Second" ) ;
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

static class UnionFind{
	int[] par ,siz ;
	int con ;
	public UnionFind( int n ) { init( n ) ; }
	public void init( int n ) {
		this.par = new int[n] ;	this.siz = new int[n] ;
		Arrays.setAll( par , i -> i ) ; Arrays.fill( siz , 1 ) ;
		con = n ;
	}
	public int root( int x ) { if ( x == par[x] ) return x ; return par[x] = root( par[x] ) ; }
	public boolean same( int x , int y ) { return root( x ) == root( y ) ; }
	public int size( int n ) { return siz[root(n)] ; }
	public int cnt() { return con ; }
	public void unite( int x , int y ) {
		if ( same( x , y ) ) return ;
		x = root( x ) ; y = root( y ) ;
		if ( siz[x] > siz[y] ) { par[y] = x ; siz[x] += siz[y] ; }
		else { par[x] = y ; siz[y] += siz[x] ; }
		con-- ;
	}
}

static class MultiSet<T extends Comparable<T>> {
	TreeMap<T,Long> map ; long c ;
	MultiSet() { this.map = new TreeMap<T,Long>() ; c = 0L ; }
	void add( T x ) { add( x , 1L ) ; }
	void add( T x , long c ) { map.put( x , map.getOrDefault( x , 0L ) + c ) ; this.c += c ; }
	void removeAll( T x ) { if ( !map.containsKey( x ) ) return ; remove( x , map.get( x ) ) ; }
	void remove( T x , long c ) { if ( !map.containsKey( x ) ) return ; this.c -= Main.min( map.get( x ) , c ) ; if ( map.get( x ) <= c ) { map.remove( x ) ; return ; } map.put( x , map.get( x ) - c ) ; }
	void remove( T x ) { remove( x , 1 ) ; }
	T max() { return map.lastKey() ; }
	T min() { return map.firstKey() ; }
	T higher( T key ) { return map.higherKey( key ) ; }
	T lower( T key ) { return map.lowerKey( key ) ; }
	T ceiling( T key ) { return map.ceilingKey( key ) ; }
	T floor( T key ) { return map.floorKey( key ) ; }
	boolean contains( T key ) { return map.containsKey( key ) ; }
	long count( T key ) { return map.getOrDefault( key , 0L ) ; }
	int size() { return map.size() ; }
	long amount() { return c ; }
	Set<T> keySet() { return map.keySet() ; }
	void merge( MultiSet<T> S ) { for ( T key : S.keySet() ) add( key , S.count( key ) ) ; this.c += S.amount() ; }
}

static class ModInt{
	int mod ; long[] fac , finv , inv ; int size = 200001 ;
	ModInt( int mod ) { this.mod = mod ; }
	int mod( long a ) {	a %= mod ; return a < 0 ? (int)a + mod : (int)a ; }
	int add( int a , int b ) { return this.mod( (long)a + (long)b ) ; }
	int add( int... x ) { int res = x[0] ; for ( int i = 1 ; i < x.length ; i++ ) res = this.add( res , x[i] ) ; return res ; }
	int add( long a , long b ) { return this.mod( this.mod( a ) + this.mod( b ) ) ; }
	int add( long... x ) { int res = this.mod( x[0] ) ;	for ( int i = 1 ; i < x.length ; i++ ) res = this.add( res , this.mod( x[i] ) ) ; return res ; }
	int time( int a , int b ) { return this.mod( (long)a * (long)b ) ; }
	int time( int... x ) { int res = x[0] ;	for ( int i = 1 ; i < x.length ; i++ ) res = this.time( res , x[i] ) ; return res ; }
	int time( long a , long b ) { return this.time( this.mod( a ) , this.mod( b ) ) ; }
	int time( long... x ) { int res = this.mod( x[0] ) ; for ( int i = 1 ; i < x.length ; i++ ) res = this.time( res , this.mod( x[i] ) ) ;	return res ; }
	int fact( int a ) {	int res = 1 ; for ( int i = 2 ; i <= a ; i++ ) res = this.time( res , i ) ;	return res ; }
	int inv( int a ) { return this.pow( a , mod - 2 ) ; }
	int pow( int a , long b ) {	int res = 1 ; while ( b > 0L ) { if ( ( b & 1L ) != 0L ) res = this.time( res , a ) ; b /= 2L ;	a = this.time( a , a ) ; } return res ; }
	int pow( long a , long b ) { return this.pow( this.mod( a ) , b ) ; }
	int nPk( int n , int k ) { if ( n < k || n < 0 || k < 0 ) return 0 ; int res = 1 ; for ( int i = 1 ; i <= k ; i++ ) { res = this.time( res , n + 1 - i ) ; } return res ; }
	int nCk( int n , int k ) {
		if ( n < k || n < 0 || k < 0 ) return 0 ;
		int res = 1 ;
		for ( int i = 1 ; i <= k ; i++ ) res = this.time( res , this.time( n + 1 - i , this.inv( i ) ) ) ;
		return res ;
	}
	int nCk2( int n , int k ) { if ( n < k || n < 0 || k < 0 ) return 0 ; comInit( n ) ; return this.time( fac[n] , ( this.time( finv[k] , finv[n-k] ) ) ) ; }
	int nHr( int n , int r ) { return nCk2( n + r - 1 , r ) ; }
	void comInit() { comInit( this.size ) ; }
	void comInit( int n ) {
		if ( fac != null && this.size > n ) return ;
		this.size = (int)Math.max( n , this.size ) ;
		fac = new long[size] ; finv = new long[size] ; inv = new long[size] ;
		fac[0] = fac[1] = finv[0] = finv[1] = inv[1] = 1 ;
		for ( int i = 2 ; i < size ; i++ ) { fac[i] = this.time( fac[i-1] , i ) ; inv[i] = mod - this.time( inv[mod%i] , mod / i ) ; finv[i] = this.time( finv[i-1] , inv[i] ) ; }
	}
}

public static int ni() { return Integer.parseInt( sc.next() ) ;}
public static int[] nia( int N ) { int[] res = new int[N]; Arrays.setAll( res , i -> ni() ) ; return res ; }
public static int[][] nia( int N , int M ) { int[][] res = new int[N][M]; for ( int i = 0 ; i < N ; i++ ) Arrays.setAll( res[i] , j -> ni() ) ; return res ; }
public static long nl() { return Long.parseLong( sc.next() ) ;}
public static long[] nla( int N ) { long[] res = new long[N]; Arrays.setAll( res , i -> nl() ) ; return res ; }
public static long[][] nla( int N , int M ) { long[][] res = new long[N][M]; for ( int i = 0 ; i < N ; i++ ) Arrays.setAll( res[i] , j -> nl() ) ; return res ; }
public static String ns() { return sc.next() ;}
public static String[] nsa( int N ) { String[] res = new String[N]; Arrays.setAll( res , i -> ns() ) ; return res ; }
public static void pr( int... o ) { for ( int i = 0 ; i < o.length - 1 ; i++ ) pw.print( o[i] + " " ) ; pw.println( o[o.length-1] ) ; }
public static void pr( long... o ) { for ( int i = 0 ; i < o.length - 1 ; i++ ) pw.print( o[i] + " " ) ; pw.println( o[o.length-1] ) ; }
public static void pr( double... o ) { for ( int i = 0 ; i < o.length - 1 ; i++ ) pw.print( new BigDecimal( o[i] ).toPlainString() + " " ) ; pw.println( new BigDecimal( o[o.length-1] ).toPlainString() ) ; }
public static void pr( String... o ) { for ( int i = 0 ; i < o.length - 1 ; i++ ) pw.print( o[i] + " " ) ; pw.println( o[o.length-1] ) ; }
public static void pr( int o ) { pw.println( o ) ; }
public static void pr( long o ) { pw.println( o ) ; }
public static void pr( double o ) { pw.println( new BigDecimal( o ).toPlainString() ) ; }
public static void pr( String o ) { pw.println( o ) ; }
public static void pr( Object o ) { pw.println( o ) ; }
public static void debug( int... o ) { for ( int i = 0 ; i < o.length - 1 ; i++ ) System.err.print( o[i] + " " ) ; System.err.println( o[o.length-1] ) ; }
public static void debug( long... o ) { for ( int i = 0 ; i < o.length - 1 ; i++ ) System.err.print( o[i] + " " ) ; System.err.println( o[o.length-1] ) ; }
public static void debug( double... o ) { for ( int i = 0 ; i < o.length - 1 ; i++ ) System.err.print( new BigDecimal( o[i] ).toPlainString() + " " ) ; System.err.println( o[o.length-1] ) ; }
public static void debug( String... o ) { for ( int i = 0 ; i < o.length - 1 ; i++ ) System.err.print( o[i] + " " ) ; System.err.println( o[o.length-1] ) ; }
public static void debug( int o ) { System.err.println( o ) ; }
public static void debug( long o ) { System.err.println( o ) ; }
public static void debug( double o ) { System.err.println( new BigDecimal( o ).toPlainString() ) ; }
public static void debug( String o ) { System.err.println( o ) ; }
public static void debug( Object o ) { System.err.println( o ) ; }
public static int max( int... x ) { int res = x[0] ; for ( int i = 1 ; i < x.length ; i++ ) res = Math.max( res , x[i] ) ; return res ; }
public static int min( int... x ) { int res = x[0] ; for ( int i = 1 ; i < x.length ; i++ ) res = Math.min( res , x[i] ) ; return res ; }
public static long max( long... x ) { long res = x[0] ; for ( int i = 1 ; i < x.length ; i++ ) res = Math.max( res , x[i] ) ; return res ; }
public static long min( long... x ) { long res = x[0] ; for ( int i = 1 ; i < x.length ; i++ ) res = Math.min( res , x[i] ) ; return res ; }
public static long pow( int a , int b ) { long res = 1L ; for ( int i = 0 ; i < b ; i++ ) res *= (long)a ; return res ; }
public static long pow( long a , long b ) { long res = 1L ; for ( long i = 0L ; i < b ; i++ ) res *= a ; return res ; }
public static long[] cumsum( int[] a ) { long[] res = new long[a.length+1] ; for ( int i = 0 ; i < a.length ; i++ ) res[i+1] += res[i] + a[i] ; return res ; }
public static long[] cumsum( long[] a ) { long[] res = new long[a.length+1] ; for ( int i = 0 ; i < a.length ; i++ ) res[i+1] += res[i] + a[i] ; return res ; }
public static long gcd( long a, long b ) { if ( b > a ) return gcd( b, a ) ; if ( a % b != 0 ) return gcd( b, a % b ) ;	return b ; }
public static int gcd( int a, int b ) { return (int)gcd( (long)a, (long)b ) ; }
public static int gcd( int... a ) { if ( a.length == 1 ) return a[0] ; int result = gcd( a[0], a[1] ) ; for ( int i = 2 ; i < a.length ; i++ ) result = gcd( result, a[i] ) ; return result ; }
public static long gcd( long... a ) { if ( a.length == 1 ) return a[0] ; long result = gcd( a[0], a[1] ) ; for ( int i = 2 ; i < a.length ; i++ ) result = gcd( result, a[i] ) ; return result ; }
public static long lcm( long a, long b ) { return ( a / gcd( a, b ) ) * b  ; }
public static long lcm( int a, int b ) { return ( (long)a / gcd( a, b ) ) * (long)b ; }
public static String revStr( String s ) { return new StringBuilder( s ).reverse().toString() ; }
public static String sortStr( String s ) { char[] c = s.toCharArray() ; Arrays.sort( c ) ; return String.valueOf( c ) ; }
public static int[] prevPermutation( int[] A ) {
	if ( A == null ) return null ; int N = A.length ;
	for ( int i = 0 ; i < N ; i++ ) A[i] = A[i] * -1 ; A = nextPermutation( A ) ; if ( A != null ) for ( int i = 0 ; i < N ; i++ ) A[i] = A[i] * -1 ; return A ; }
public static int[] nextPermutation( int[] A ) {
	if ( A == null ) return null ;
	for ( int i = A.length - 1 ; i > 0 ; i-- ) {
		if ( A[i-1] < A[i] ) { int j = find( A, A[i-1], i, A.length - 1 ) ; int tmp = A[j] ; A[j] = A[i-1] ; A[i-1] = tmp ; Arrays.sort( A, i, A.length ) ; return A ; }
	}
	return null ;
}
public static int find( int[] A, int dest, int f, int l ) { if ( f == l ) return f ; int m = ( f + l + 1 ) / 2 ; return ( A[m] <= dest ? find( A, dest, f, m - 1 ) : find( A, dest, m, l ) ) ; }
}