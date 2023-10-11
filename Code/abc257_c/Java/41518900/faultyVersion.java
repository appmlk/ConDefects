import java.util.*;
import java.io.*;

class Main{
    void solve(PrintWriter out, In in) {
        int n = in.nextInt();
        String s = in.next();
        int [] w = in.IntArray(n);
        ArrayList<Integer> zero = new ArrayList<>();
        ArrayList<Integer> one = new ArrayList<>();
        ArrayList<Integer> all = new ArrayList<>();
        for(int i = 0 ; i < n ; i ++ ) {
            if(s.charAt(i) == '0') zero.add(w[i]);
            else one.add(w[i]);
            all.add(w[i]);
        }
        Collections.sort(zero);
        Collections.sort(one);
        int ans = 0;
        // 30 40 45 60 80 
        // 30 60 80
        // 40 45
        for(int i = 0 ; i < n ; i ++ ) {
            int base = all.get(i);
            ans = Math.max(upper_bound(zero,base)+(one.size()-lower_bound(one,base+1)),ans);
        }
        out.print(ans);
    }       
    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        In in  = new In();
        new Main().solve(out,in);
        out.flush();
    }/*
     * upper_bound O(logN)
     * keyより大きいインデックスを返します。
     */
    int upper_bound(int [] A , int key) {
        int left = 0;
        int right = A.length;
        while(left < right) {
            int mid = (left + right) / 2;
            if(A[mid] <= key) left = mid + 1;
            else right = mid;
        }
        return right;
    }
    int upper_bound(long [] A , long key) {
        int left = 0;
        int right = A.length;
        while(left < right) {
            int mid = (left + right) / 2;
            if(A[mid] <= key) left = mid + 1;
            else right = mid;
        }
        return right;
    }
    int upper_bound(ArrayList<Integer> A , int key) {
        int left = 0;
        int right = A.size();
        while(left < right) {
            int mid = (left + right) / 2;
            if(A.get(mid) <= key) left = mid + 1;
            else right = mid;
        }
        return right;
    }
    int upper_bound(ArrayList<Long> A , long key) {
        int left = 0;
        int right = A.size();
        while(left < right) {
            int mid = (left + right) / 2;
            if(A.get(mid) <= key) left = mid + 1;
            else right = mid;
        }
        return right;
    }
    /*
     * lower_bound O(logN)
     * key以下のインデックスを返します。
     */
    int lower_bound(int [] A , int key) {
        int left = 0;
        int right = A.length;
        while(left < right) {
            int mid = (left + right) / 2;
            if(A[mid] < key) left = mid + 1;
            else right = mid;
        }
        return right;
    }
    int lower_bound(long [] A , long key) {
        int left = 0;
        int right = A.length;
        while(left < right) {
            int mid = (left + right) / 2;
            if(A[mid] < key) left = mid + 1;
            else right = mid;
        }
        return right;
    }
    int lower_bound(ArrayList<Integer> A, int key) {
        int left = 0;
        int right = A.size();
        while(left < right) {
            int mid = (left + right) / 2;
            if(A.get(mid) < key) left = mid + 1;
            else right = mid;
        }
        return right;
    }
    int lower_bound(ArrayList<Long> A, long key) {
        int left = 0;
        int right = A.size();
        while(left < right) {
            int mid = (left + right) / 2;
            if(A.get(mid) < key) left = mid + 1;
            else right = mid;
        }
        return right;
    }
}
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
