import java.util.*; 
import java.io.*;
import java.math.*;
import java.util.stream.*;
import java.util.function.*;

class Main implements Runnable {

    public void solve() {    
        int n = in.nextInt() , m = in.nextInt();
        Floyd_Warshall fw = new Floyd_Warshall(n);
        for(int i = 0 ; i < m ; i ++) {
            int u = in.nextInt() , v = in.nextInt() ;
            long w = in.nextLong();
            --u;--v;
            fw.addEdge(u, v, w);
        }
        fw.run();
        int [][] dp = new int[1 << n][n];
        for(int i = 0 ; i < (1 << n) ; i ++) for(int j = 0 ; j < n ; j ++) dp[i][j] = inf ;
        for(int i = 0 ; i < n ; i ++) dp[1 << i][i] = 0 ;
        for(int i = 0 ; i < (1 << n) ; i ++) {
            for(int j = 0 ; j < n ; j ++) if(((i >> j) & 1) == 1) {
                if(dp[i][j] == inf) continue;
                for(int k = 0 ; k < n ; k ++) if(((i >> k) & 1) == 0 && fw.dist(j , k) != -1) {
                    dp[i | (1 << k)][k] = Math.min(dp[i | (1 << k)][k] , dp[i][j] + (int) fw.dist(j , k));
                }
            } 
        }
        int min = inf ; 
        for(int i = 0 ; i < n ; i ++) min = Math.min(min , dp[(1 << n) - 1][i]);
        out.println(min == inf ? no : min);
    }

    // ===============================================================================================================

    class Floyd_Warshall {

        private long [][] dist ;
        private int N ;
        private boolean cycle ;

        Floyd_Warshall(int N)
        {
            this.N = N ;
            this.cycle = false;
            this.dist = new long[N][N];
            for(long [] v : dist) Arrays.fill(v , inf);
            for(int i = 0 ; i < N ; i ++ ) dist[i][i] = 0 ;
        }

        public void addEdge(int u , int v , long w) { this.dist[u][v] = w ; }

        public void run()
        {
            for(int mid = 0 ; mid < N ; mid ++ )
            {
                for(int begin = 0 ; begin < N ; begin ++ )
                {
                    for(int end = 0 ; end < N ; end ++ )
                    {
                        long u = dist[begin][mid] , v = dist[mid][end] , g = dist[begin][end] ;

                        if(u == inf || v == inf) continue;
                        if((u + v) < g) // 最短経路の上書き.
                        {
                            dist[begin][end] = u + v ;
                        }
                        else if(((u + v) == g)) // 最短経路の内のある処理.
                        {

                        }
                    }
                    if(dist[begin][begin] < 0) cycle = true ;
                }
            }
        }

        public boolean isCycle() { return cycle; }

        public long dist(int u , int v) { return dist[u][v] == inf ? -1 : dist[u][v] ; }

    }

    // ===============================================================================================================

    public PrintWriter out = new PrintWriter(System.out);
    public In in = new In() ;
    public static final int  inf = (1  << 30);
    public static final long lnf = (1L << 60);
    public static final String yes = "Yes" , no  = "No" ;
    public static final int mod7 = 1000000007 , mod9 = 998244353;
    public static final int [] dy4 = {-1,0,1,0} , dx4 = {0,1,0,-1};
    public static final int [] dy8 = {-1,-1,-1,0,1,1,1,0} , dx8 = {-1,0,1,1,1,0,-1,-1};

    // ===============================================================================================================

    public static void main(String ... args) { new Thread(null, new Main(), "", Runtime.getRuntime().maxMemory()).start(); }

    public void run() { solve(); out.flush(); }

    // ===============================================================================================================

    // ===============================================================================================================

    public Integer [] parseInt(int [] array) { return IntStream.of(array).boxed().toArray(Integer[]::new); }

    public Long [] parseLong(long [] array) { return LongStream.of(array).boxed().toArray(Long[]::new); }

    // ===============================================================================================================

}

class In {

    private final InputStream in = System.in;
    private final Scanner sc = new Scanner(System.in);
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

    private  boolean hasNext() { 
        while(hasNextByte() && !isPrintableChar(buffer[ptr])) {
            ptr++; 
        }
        return hasNextByte();
    }
    
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

    public long nextLong() {
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

    public int nextInt() {
        long nl = nextLong();
        if (nl < Integer.MIN_VALUE || nl > Integer.MAX_VALUE) throw new NumberFormatException();
        return (int) nl;
    }

    public double nextDouble() { 
        return Double.parseDouble(next());
    }

    public char nextChar() {
        return next().charAt(0);
    }

    public BigInteger nextBigInteger() {
        return sc.nextBigInteger();
    }

    public int [] nextInt(int n) {
        int [] array = new int[n];
        for(int i = 0 ; i < n ; i ++) {
            array[i] = nextInt();
        }
        return array ;
    }

    public int [][] nextInt(int n , int m) {
        int [][] array = new int[n][m];
        for(int i = 0 ; i < n ; i ++) {
            array[i] = nextInt(m);
        }
        return array ;
    }

    public long [] nextLong(int n) {
        long [] array = new long[n];
        for(int i = 0 ; i < n ; i ++) {
            array[i] = nextLong();
        }
        return array ;
    }

    public long [][] nextLong(int n , int m) {
        long [][] array = new long[n][m];
        for(int i = 0 ; i < n ; i ++) {
            array[i] = nextLong(m);
        }
        return array ;
    }

    public double [] nextDouble(int n) {
        double [] array = new double[n];
        for(int i = 0 ; i < n ; i ++) {
            array[i] = nextDouble();
        }
        return array ;
    }
    
    public String [] next(int n) {
        String [] array = new String[n];
        for(int i = 0 ; i < n ; i ++) {
            array[i] = next();
        }
        return array ;
    }

    public String [][] next(int n , int m) {
        String [][] array = new String[n][m];
        for(int i = 0 ; i < n ; i ++) {
            array[i] = next(m);
        }
        return array ;
    }

    public char [] nextChar(int n) {
        char [] array = new char[n];
        String string = next() ;
        for(int i = 0 ; i < n ; i ++) {
            array[i] = string.charAt(i);
        }
        return array ;
    }

    public char [][] nextChar(int n , int m) {
        char [][] array = new char[n][m];
        for(int i = 0 ; i < n ; i ++) {
            array[i] = nextChar(m);
        }
        return array ;
    }

}
