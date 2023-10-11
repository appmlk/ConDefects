import java.util.*;
import java.io.*;

class Main{
    void solve(PrintWriter out, In in) {        
        int n = in.nextInt();
        PriorityQueue<Integer> value1 = new PriorityQueue<>();
        PriorityQueue<Integer> value2 = new PriorityQueue<>(Comparator.reverseOrder());
        int [] A = in.IntArray(n);
        int [] ans = new int[n];
        for(int i = 0 ; i < n ; i ++ ) {
            value1.add(A[i]);
            value2.add(A[i]);
        }
        for(int i = 1 ; i < n ; i += 2 ) {
            ans[i] = value2.poll();
        }   
        for(int i = n - 1 ; i >= 0 ; i -= 2 ) {
            ans[i] = value1.poll();
        }
        for(int i = 1 ; i < n ; i += 2 ) {
            if(ans[i-1] < ans[i] && ans[i] > ans[i+1]) continue;
            out.println(no);
            return;
        }
        out.println(yes);
    }   
    /* Library */   

    /* ------- */
    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        In in  = new In();
        new Main().solve(out,in);
        out.flush();
    }
    final long MOD7 = 1000000007; final long MOD9 = 998244353 ;
    final int [] X4 = {0,1,0,-1}; final int [] Y4 = {-1,0,1,0};
    final int [] X8 = {-1,-1,0,1,1,1,0,-1}; final int [] Y8 = {0,1,1,1,0,-1,-1,-1};
    final int Inf = (int)1e9; final long Lnf = (long)1e18;
    final String yes = "Yes"; final String no = "No";
}
/* Class */

/* ----- */
class Pair{
    private int first ;
    private int second;
    Pair(int first,int second) {
        this.first = first;
        this.second = second;
    }
    int first()  { return this.first ; }
    int second() { return this.second; }
    @Override
    public String toString(){ return first()+" = "+second(); }
}
class PairII {
    private int first ;
    private int second ;
    private int third;
    PairII(int first, int second, int third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }
    int first()  { return this.first ; }
    int second() { return this.second; }
    int third()  { return this.third ; }
    @Override
    public String toString(){ return first()+" = "+second()+" = "+third() ; }
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
