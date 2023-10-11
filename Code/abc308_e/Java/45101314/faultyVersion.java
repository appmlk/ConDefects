import java.util.*; import java.io.*;
import java.math.*; import java.util.stream.*;

class Main implements Runnable{

    // introduced in Java 16
    // record pair(int first , int second) { } 

    void solve() {  
        // jを固定して上にlower下にupperなんか見たことある問題
        int n = in.nextInt();
        int [] A = in.IntArray(n);
        String mex = in.next();
        List<Integer> [][] G = new ArrayList[3][3];
        Counter<Integer> cnt = new Counter();
        long ans = 0 ;
        for(int i = 0 ; i < 3 ; i ++ ) {
            for(int j = 0 ; j < 3 ; j ++ ) {
                G[i][j] = new ArrayList<>();
            }
        }
        for(int i = 0 ; i < n ; i ++ ) {
            char now = mex.charAt(i);
            if(now == 'M') G[0][A[i]].add(i);
            if(now == 'E') G[1][A[i]].add(i);
            if(now == 'X') G[2][A[i]].add(i);
        }
        for(int E = 0 ; E < 3 ; E ++ ) {
            int A_j = E ;
            for(int j_index : G[1][A_j]) {
                for(int M = 0 ; M < 3 ; M ++ ) {
                    for(int X = 0 ; X < 3 ; X ++ ) {
                        int A_i = M ;
                        int A_k = X ;
                        int i_cnt = cnt.under(G[0][A_i], j_index);
                        int k_cnt = cnt.greater(G[2][A_k], j_index);
                        long use_able = -1 ;
                        for(long u = 0 ; u <= 3 ; u ++ ) {
                            if(u != M && u != E && u != X) {
                                use_able = u ;
                                break;
                            }
                        }
                        long c = i_cnt * k_cnt ;
                        ans += (long)use_able * c;
                    }
                }
            }
        }
        out.print(ans);
    }  

    public static void main(String[] args) {
        new Thread(null, new Main(), "", Runtime.getRuntime().maxMemory()).start(); 
    }

    public void run() {   
        solve();
        out.flush();
    }
    
    PrintWriter out = new PrintWriter(System.out);
    In in = new In();

}

class Counter <T extends Comparable<T>>{

    public List<Integer> toList(int [] a) {
        var List = Arrays.stream(a).boxed().collect(Collectors.toList());
        Collections.sort(List);
        return List;
    }

    public List<Long> toList(long [] a) {
        var List = Arrays.stream(a).boxed().collect(Collectors.toList());
        Collections.sort(List);
        return List;
    }

    int or_greater(List<T> A , T key) {
        return A.size() - lower_bound(A, key);
    }

    int or_under(List<T> A , T key) {
        return upper_bound(A, key);
    }

    int greater(List<T> A , T key) {
        return A.size() - upper_bound(A, key);
    }

    int under(List<T> A , T key) {
        return lower_bound(A, key);
    }

    private int lower_bound(List<T> A, T key) {
        int left = 0;
        int right = A.size();
        while(left < right) {
            int mid = (left + right) / 2;
            if(compare(A.get(mid), key , 0)) left = mid + 1;
            else right = mid;
        }
        return right;
    }

    private int upper_bound(List<T> A , T key) {
        int left = 0;
        int right = A.size();
        while(left < right) {
            int mid = (left + right) / 2;
            if(compare(A.get(mid) , key , 1)) left = mid + 1;
            else right = mid;
        }
        return right;
    }
    
    boolean compare(T o1, T o2 , int c) {
        int res = o1.compareTo(o2);
        return c == 0 ? res == -1 : (res == 0 || res ==  -1); 
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
    char nextChar() {
        return next().charAt(0);
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
    char [][] CharArray2(int n , int m) {
        final char [][] Array = new char [n][m];
        for(int i = 0 ; i < n ; i ++ ) {
            for(int j = 0 ; j < n ; j ++ ) {
                Array[i][j] = next().charAt(0);
            }
        }
        return Array;
    }
    List<Integer> [] Graph(int n) {
        @SuppressWarnings("unchecked")
        List<Integer> [] G = new ArrayList[n];
        for(int i = 0 ; i < n ; i ++ ) {
            G[i] = new ArrayList<>();
        }
        return G ;
    }
}
