import java.math.*;
import java.util.*;
import java.io.*;
import java.lang.*;
public class Main implements Runnable{ 
    public void run(){
        if(Static.CHECKER){
            try{
                System.setErr(new PrintStream(new FileOutputStream("error.txt")));
                System.setOut(new PrintStream(new FileOutputStream("output.txt")));
                System.setIn(new FileInputStream("input.txt"));
            }catch(Exception e){
                System.err.println("NOT A PROBLEM");
            }
        }
        OutputWriter out = new OutputWriter(System.out);
        Solution problem = new Solution(System.in , out);
        int testCases = 1 , need = Static.NEED;
        long s = 0l , e = 0l;
        if(Static.HAS_TESTCASES != 0) testCases = problem.i();
        for(int i = 1 ; i <= testCases ; i++){
            if(need != 0) s = System.currentTimeMillis();
            problem.solution();
            if(need != 0) e = System.currentTimeMillis();
            if(need != 0) System.err.println(i + " - " + (e - s) + " " + "ms");
        }
        out.flush();
        out.close();
    }
    public static void main(String[] args) throws Exception{
        new Thread(null , new Main() , "Main" , 1l << 26).start();
    }
}
final class Static{
    protected final static boolean CHECKER = System.getProperty("ONLINE_JUDGE") != null;
    protected final static int HAS_TESTCASES = 0;
    protected final static int NEED = 0;
}

class Solution{
    boolean[][] col , row;
    char[][] grid;
    boolean[] r , c;
    String s , t;
    int n , a , b;
    int cnt = (int) 100;
    public void solution(){
        n = a = b = i();
        col = new boolean[n][3];
        row = new boolean[n][3];
        r = new boolean[n];
        c = new boolean[n];
        grid = new char[n][n];
        s = s();
        t = s();
        for(int i = 0 ; i < n ; i++) Arrays.fill(grid[i] , '.');
        if(finder(0 , 0)){
            o.p(yes);
            for(int i = 0 ; i < n ; i++){
                o.p(new String(grid[i]));
            }
        }else{
            o.p(no);
        }
    }
    private boolean finder(int x , int  y){
        if(x == n){
            for(int i = 0 ; i < n ; i++){
                for(int j = 0 ; j < 3 ; j++){
                    if(!row[i][j] || !col[i][j]) return false;
                }
            }
            return a == 0 && b == 0;
        }
        if(y == n) return finder(x + 1 , 0);
        for(char i = 'A' ; i < 'D' ; i++) if(!col[y][i - 'A'] && !row[x][i - 'A']){
            if(!r[x] && i != s.charAt(x)) continue;
            if(!c[y] && i != t.charAt(y)) continue;
            boolean R = false , T = false;
            if(!r[x]) r[x] = R = true;
            if(!c[y]) c[y] = T = true;
            if(R) a -= 1;
            if(T) b -= 1;
            grid[x][y] = i;
            row[x][i - 'A'] = true;
            col[y][i - 'A'] = true;
            if(finder(x , y + 1)) return true;
            grid[x][y] = '.';
            row[x][i - 'A'] = !true;
            col[y][i - 'A'] = !true;
            if(R) r[x] = false;
            if(T) c[y] = false;
            if(R) a += 1;
            if(T) b += 1;
        }
        return finder(x , y + 1);
    }

    private String yes = "Yes";
    private String no = "No";
    private final long mod = (long) 1e9 + 7;
    private final int imin = 1 << -1;
    private final int imax = -1 >>> 1;
    private final long lmin = 1l << -1l;
    private final long lmax = -1l >>> 1l;
    private OutputWriter o;
    private InputStream stream;
    private byte[] buf = new byte[1024];
    private int curChar;
    private int numChars;
    private SpaceCharFilter filter;
    private int min(int... arr){
        int ret = arr[0];
        for(int i : arr) ret=Math.min(ret,i);
        return ret;
    }
    private int max(int... arr){
        int ret = arr[0];
        for(int i : arr) ret = Math.max(ret,i);
        return ret;
    } 
    private long min(long... arr){
        long ret = arr[0];
        for(long i : arr) ret = Math.min(ret , i);
        return ret;
    }
    private long max(long... arr){
        long ret = arr[0];
        for(long i : arr) ret = Math.max(ret , i);
        return ret;
    }
    private double min(double... arr){
        //o.p(arr);
        double ret = arr[0];
        for(double i : arr) ret = Math.min(ret , i);
        return ret;
    }
    private double max(double... arr){
        double ret = arr[0];
        for(double i : arr) ret = Math.max(ret , i);
        return ret;
    }
    public Solution(InputStream stream , OutputWriter o){
        this.stream = stream;
        this.o = o;
    }
    public char[] sca(){
        return s().toCharArray();
    }
    public int read(){
        if(numChars == -1){
            throw new InputMismatchException();
        }
        if(curChar >= numChars){
            curChar = 0;
            try{
                numChars = stream.read(buf);
            }catch(IOException e){
                throw new InputMismatchException();
            }
            if(numChars <= 0){
                return -1;
            }
        }
        return buf[curChar++];
    }
    public int peek(){
        if(numChars == -1){
            return -1;
        }
        if(curChar >= numChars){
            curChar = 0;
            try{
                numChars = stream.read(buf);
            }catch(IOException e){
                return -1;
            }
            if(numChars <= 0){
                return -1;
            }
        }
        return buf[curChar];
    }
    public int i(){
        int c = read();
        while(isSpaceChar(c)){
            c = read();
        }
        int sgn=1;
        if(c == '-'){
            sgn = -1;
            c = read();
        }
        int res = 0;
        do{
            if(c < '0' || c > '9'){
                throw new InputMismatchException();
            }
            res *= 10;
            res += c - '0';
            c = read();
        }
        while(!isSpaceChar(c));
        return res * sgn;
    }
    public long l(){
        int c = read();
        while(isSpaceChar(c)){
            c = read();
        }
        int sgn = 1;
        if(c == '-'){
            sgn = -1;
            c = read();
        }
        long res = 0;
        do{
            if(c < '0' || c > '9'){
                throw new InputMismatchException();
            }
            res *= 10;
            res += c - '0';
            c = read();
        }
        while(!isSpaceChar(c));
        return res * sgn;
    }
    public String s(){
        int c = read();
        while(isSpaceChar(c)){
            c = read();
        }
        StringBuilder res = new StringBuilder();
        do{
            if(Character.isValidCodePoint(c)){
                res.appendCodePoint(c);
            }
            c = read();
        }
        while(!isSpaceChar(c));
        return res.toString();
    }
    public boolean isSpaceChar(int c){
        if(filter != null){
            return filter.isSpaceChar(c);
        }
        return isWhitespace(c);
    }
    public static boolean isWhitespace(int c){
        return c==' '||c=='\n'||c=='\r'||c=='\t'||c==-1;
    }
    private String readLine0(){
        StringBuilder buf = new StringBuilder();
        int c = read();
        while(c != '\n' && c != -1){
            if(c != '\r'){
                buf.appendCodePoint(c);
            }
            c = read();
        }
        return buf.toString();
    }
    public String readLine(){
        String s = readLine0();
        while(s.trim().length() == 0){
            s = readLine0();
        }
        return s;
    }
    public String readLine(boolean ignoreEmptyLines){
        if(ignoreEmptyLines) return readLine();
        return readLine0();
    }
    public char c(){
        int c = read();
        while(isSpaceChar(c)){
            c = read();
        }
        return (char)c;
    }
    public double d(){
        int c = read();
        while(isSpaceChar(c)){
            c = read();
        }
        int sgn = 1;
        if(c == '-'){
            sgn = -1;
            c = read();
        }
        double res = 0;
        while(!isSpaceChar(c) && c != '.'){
            if(c == 'e' || c == 'E'){
                return res * Math.pow(10, i());
            }
            if(c < '0' || c > '9'){
                throw new InputMismatchException();
            }
            res *= 10;
            res += c - '0';
            c = read();
        }if(c == '.'){
            c = read();
            double m = 1;
            while(!isSpaceChar(c)){
                if(c == 'e' || c == 'E'){
                    return res * Math.pow(10 , i());
                }if(c < '0' || c > '9'){
                    throw new InputMismatchException();
                }
                m /= 10;
                res += (c - '0') * m;
                c = read();
            }
        }
        return res * sgn;
    }
    public boolean isExhausted(){
        int value;
        while(isSpaceChar(value = peek()) && value != -1){
            read();
        }
        return value == -1;
    }
    public SpaceCharFilter getFilter(){
        return filter;
    }
    public void setFilter(SpaceCharFilter filter){
        this.filter = filter;
    }
    public interface SpaceCharFilter{
        public boolean isSpaceChar(int ch);
    }
    public int[] ia(int n){
        int[] array = new int[n];
        for(int i = 0 ; i < n ; ++i) array[i]=i();
        return array;
    }
    public int[][] im(int n , int m){
        int[][] matrix = new int[n][m];
        for(int i = 0 ; i < n ; ++i) for(int j = 0 ; j < m ; ++j) matrix[i][j] = i();
        return matrix;
    }
    public int[][] im(int n){
        return im(n , n);
    }
}
class OutputWriter{
    private final PrintWriter writer;
    public OutputWriter(OutputStream outputStream){
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
    }
    public OutputWriter(Writer writer){
        this.writer = new PrintWriter(writer);
    }
    public void p(Object... objects){
        for(int i = 0 ; i < objects.length ; i++){
            if(i != 0){
                writer.print(' ');
            }
            writer.print(objects[i]);
        }
        writer.println();
    }
    public void p(double[] array){
        for(int i = 0 ; i < array.length ; i++){
            if(i != 0){
                writer.print(' ');
            }
            writer.print(array[i]);
        }
        writer.println();
    }
    public void p(int[] array){
        for(int i = 0 ; i < array.length ; i++){
            if(i != 0){
                writer.print(' ');
            }
            writer.print(array[i]);
        }
        writer.println();
    }
    public void p(long[] array){
        for(int i = 0 ; i < array.length ; i++){
            if(i != 0){
                writer.print(' ');
            }
            writer.print(array[i]);
        }
        writer.println();
    }
    public void p(char[] array){
        for(int i = 0 ; i < array.length ; i++){
            if(i != 0){
                writer.print(' ');
            }
            writer.print(array[i]);
        }
        writer.println();
    }
    public void p(String[] array){
        for(int i = 0 ; i < array.length ; i++){
            if(i != 0){
                writer.print(' ');
            }
            writer.print(array[i]);
        }
        writer.println();
    }
    public void p(int[][] matrix){
        for(int i = 0 ; i < matrix.length ; ++i){
            p(matrix[i]);
        }
        writer.println();
    }
    public void p(double[][] matrix){
        for(int i = 0 ; i < matrix.length ; ++i){
            p(matrix[i]);
        }
        writer.println();
    }
    public void p(long[][] matrix){
        for(int i = 0 ; i < matrix.length ; ++i){
            p(matrix[i]);
        }
        writer.println();
    }
    public void p(char[][] matrix){
        for(int i = 0 ; i < matrix.length ; ++i){
            p(matrix[i]);
        }
        writer.println();
    }
    public void p(String[][]matrix){
        for(int i = 0 ; i < matrix.length ; ++i){
            p(matrix[i]);
        }
        writer.println();
    }
    public void p(){
        writer.println();
    }
    public void p_(char i){
        writer.print(i);
    }
    public void p(char i){
        writer.println(i);
    }
    public void close(){
        writer.close();
    }
    public void flush(){
        writer.flush();
    }
    public void p_(String x){
        writer.print(x);
    }
    public void p_(long i){
        writer.print(i);
    }
    public void p(long i){
        writer.println(i);
    }
    public void p_(int i){
        writer.print(i);
    }
    public void p(int i){
        writer.println(i);
    }
}
