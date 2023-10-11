import java.util.*;
import java.io.*;
import java.math.*;

class Main{
    void solve(PrintWriter out, In in) { 
        long x = in.nextLong() , a = in.nextLong() , d = in.nextLong() , n = in.nextLong();
        if(d < 0) {
            long left = a + (n - 1) * d ;
            a = left ;
            d *= -1;
        }
        long l = 0 , r = n - 1 ;
        while(l <= r) {
            long mid = (r + l) / 2;
            if(a + d * mid < x) l = mid + 1;
            else r = mid - 1;
        }
        long ans = (long)8e18;
        for(long i = Math.max(0L,l - 100); i <= Math.min(n - 1 , l + 100) ; i ++ ) {
            ans = Math.min(Math.abs((a + d * i) - x), ans);
        }
        out.print(ans);
    }
    void swap(int [] array , int l , int r) {
        int tmp = array[l];
        array[l] = array[r];
        array[r] = tmp;
    }
    void swap(long [] array , int l , int r) {
        long tmp = array[l];
        array[l] = array[r];
        array[r] = tmp;
    }
    int [] fill(int [] array , int max) {
        Arrays.fill(array,max);
        return array;
    }
    long [] fill(long [] array , long max) {
        Arrays.fill(array,max);
        return array;
    }
    int [][] fill(int [][] array , int max) {
        for(int [] tmp : array) 
            Arrays.fill(tmp,max);
        return array;
    }
    long [][] fill(long [][] array , long max) {
        for(long [] tmp : array) 
            Arrays.fill(tmp,max);
        return array;
    }
    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        In in  = new In();
        new Main().solve(out,in);
        out.flush();
    }
    final int inf = Integer.MAX_VALUE / 10 ;
    final long lnf = Long.MAX_VALUE / 10 ;
}
class Pair implements Comparable<Pair>{
    private int first ;
    private int second;
    Pair(int first,int second) {
        this.first = first;
        this.second = second;
    }
    int first()  { 
        return this.first ;
    }
    int second() { 
        return this.second;
    }
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pair)) {
            return false;
        }
        Pair that = (Pair)o;
        return first == that.first && second == that.second;
    }
    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
    @Override
    public int compareTo(Pair o) {
        return first == o.first ? Integer.compare(second, o.second) : Integer.compare(first, o.first);
    }
    @Override
    public String toString(){ 
        return first()+" "+second();
    }
}
class PairII {
    private int first;
    private int second;
    private int third;
    PairII(int first, int second, int third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }
    int first() {
        return this.first;
    }
    int second() {
        return this.second;
    }
    int third() {
        return this.third;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        PairII other = (PairII) obj;
        return this.first == other.first && this.second == other.second && this.third == other.third;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + first;
        result = 31 * result + second;
        result = 31 * result + third;
        return result;
    }
    @Override 
    public String toString() {
        return this.first+" "+this.second+" "+this.third;
    }
}
class In{
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
    private int readByte() { 
        if (hasNextByte()) return buffer[ptr++]; else return -1;
    }
    private static boolean isPrintableChar(int c) { 
        return 33 <= c && c <= 126;
    }
    public boolean hasNext() { 
        while(hasNextByte() && !isPrintableChar(buffer[ptr])) {
            ptr++; 
        }
        return hasNextByte();
    }
    String next() {
        if (!hasNext()) throw new NoSuchElementException();
        StringBuilder sb = new StringBuilder();
        int b = readByte();
        while(isPrintableChar(b)) {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }

    long nextLong() {
        if (!hasNext()) throw new NoSuchElementException();
        long n = 0;
        boolean minus = false;
        int b = readByte();
        if (b == '-') {
            minus = true;
            b = readByte();
        }
        if (b < '0' || '9' < b) {
            throw new NumberFormatException();
        }
        while(true){
            if ('0' <= b && b <= '9') {
                n *= 10;
                n += b - '0';
            }else if(b == -1 || !isPrintableChar(b)){
                return minus ? -n : n;
            }else{
                throw new NumberFormatException();
            }
            b = readByte();
        }
    }
    int nextInt() {
        long nl = nextLong();
        if (nl < Integer.MIN_VALUE || nl > Integer.MAX_VALUE) throw new NumberFormatException();
        return (int) nl;
    }
    double nextDouble() { 
        return Double.parseDouble(next());
    }
    int [] IntArray(int n) {
        final int [] Array = new int [n];
        for(int i = 0 ; i < n ; i ++ ) {
            Array[i] = nextInt();
        }
        return Array;
    }
    int [][] IntArray(int n , int m) {
        final int [][] Array = new int [n][m];
        for(int i = 0 ; i < n ; i ++ ) {
            Array[i] = IntArray(m);
        }
        return Array;
    }   
    long [] LongArray(int n) {
        final long [] Array = new long [n];
        for(int i = 0 ; i < n ; i ++ ) {
            Array[i] = nextLong();
        }
        return Array;
    }
    long [][] LongArray(int n , int m) {
        final long [][] Array = new long [n][m];
        for(int i = 0 ; i < n ; i ++ ) {
            Array[i] = LongArray(m);
        }
        return Array;
    }
    String [] StringArray(int n) {
        final String [] Array = new String [n];
        for(int i = 0 ; i < n ; i ++ ) {
            Array[i] = next();
        }
        return Array;
    }
    char [] CharArray(int n) {
        final char [] Array = new char[n];
        for(int i = 0 ; i < n ; i ++ ) {
            Array[i] = next().charAt(0);
        }
        return Array;
    }
    char [][] CharArray(int n , int m) {
        final char [][] Array = new char [n][m];
        for(int i = 0 ; i < n ; i ++ ) {
            Array[i] = next().toCharArray();
        }
        return Array;
    }
}
