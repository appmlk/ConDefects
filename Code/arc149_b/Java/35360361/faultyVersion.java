import java.util.*;
import java.io.*;

public class Main {
    static ContestScanner sc = new ContestScanner(System.in);
    static ContestPrinter pw = new ContestPrinter(System.out);
    static StringBuilder sb = new StringBuilder();
    static long mod = (long) 1e9 + 7;

    public static void main(String[] args) throws Exception {
        //int T = sc.nextInt();
        //for(int i = 0; i < T; i++)solve();
        
        solve();
        pw.flush();
    }

    public static void solve() {
        int N = sc.nextInt();
        int[] arr = new int[N];
        int[] A = sc.nextIntArray(N);
        int[] B = sc.nextIntArray(N);
        for(int i = 0; i < N; i++){
            arr[A[i]-1] = B[i]-1;
        }
        int[] lis = new int[N+1];
        Arrays.fill(lis,(int)1e9);
        for(int i = 0; i < N; i++){
            int left = -1;
            int right = N;
            while(right - left > 1){
                int mid = (right+left)/2;
                if(arr[i] >= lis[mid]){
                    left = mid;
                }else{
                    right = mid;
                }
            }
            lis[right] = arr[i];
            //pw.printArray(lis);
        }
        int ans = N;
        for(int i = 0; i <= N; i++){
            if(lis[i] > N+1){
                break;
            }
            ans++;
        }
        for(int i = 0; i < N; i++){
            arr[B[i]-1] = A[i]-1;
        }
        Arrays.fill(lis,(int)1e9);
        for(int i = 0; i < N; i++){
            int left = -1;
            int right = N;
            while(right - left > 1){
                int mid = (right+left)/2;
                if(arr[i] >= lis[mid]){
                    left = mid;
                }else{
                    right = mid;
                }
            }
            lis[right] = B[i];
            //pw.printArray(lis);
        }
        int ans2 = N;
        for(int i = 0; i <= N; i++){
            if(lis[i] > N+1){
                break;
            }
            ans2++;
        }
        pw.println(Math.max(ans,ans2));
    }

    static class GeekInteger {
        public static void save_sort(int[] array) {
            shuffle(array);
            Arrays.sort(array);
        }

        public static int[] shuffle(int[] array) {
            int n = array.length;
            Random random = new Random();
            for (int i = 0, j; i < n; i++) {
                j = i + random.nextInt(n - i);
                int randomElement = array[j];
                array[j] = array[i];
                array[i] = randomElement;
            }
            return array;
        }

        public static void save_sort(long[] array) {
            shuffle(array);
            Arrays.sort(array);
        }

        public static long[] shuffle(long[] array) {
            int n = array.length;
            Random random = new Random();
            for (int i = 0, j; i < n; i++) {
                j = i + random.nextInt(n - i);
                long randomElement = array[j];
                array[j] = array[i];
                array[i] = randomElement;
            }
            return array;
        }

    }
}

/**
 * refercence : https://github.com/NASU41/AtCoderLibraryForJava/blob/master/ContestIO/ContestScanner.java
 */
class ContestScanner {
    private final java.io.InputStream in;
    private final byte[] buffer = new byte[1024];
    private int ptr = 0;
    private int buflen = 0;

    private static final long LONG_MAX_TENTHS = 922337203685477580L;
    private static final int LONG_MAX_LAST_DIGIT = 7;
    private static final int LONG_MIN_LAST_DIGIT = 8;

    public ContestScanner(java.io.InputStream in){
        this.in = in;
    }
    public ContestScanner(java.io.File file) throws java.io.FileNotFoundException {
        this(new java.io.BufferedInputStream(new java.io.FileInputStream(file)));
    }
    public ContestScanner(){
        this(System.in);
    }
 
    private boolean hasNextByte() {
        if (ptr < buflen) {
            return true;
        }else{
            ptr = 0;
            try {
                buflen = in.read(buffer);
            } catch (java.io.IOException e) {
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
        while(hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++;
        return hasNextByte();
    }
    public String next() {
        if (!hasNext()) throw new java.util.NoSuchElementException();
        StringBuilder sb = new StringBuilder();
        int b = readByte();
        while(isPrintableChar(b)) {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }
 
    public long nextLong() {
        if (!hasNext()) throw new java.util.NoSuchElementException();
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
        while (true) {
            if ('0' <= b && b <= '9') {
                int digit = b - '0';
                if (n >= LONG_MAX_TENTHS) {
                    if (n == LONG_MAX_TENTHS) {
                        if (minus) {
                            if (digit <= LONG_MIN_LAST_DIGIT) {
                                n = -n * 10 - digit;
                                b = readByte();
                                if (!isPrintableChar(b)) {
                                    return n;
                                } else if (b < '0' || '9' < b) {
                                    throw new NumberFormatException(
                                        String.format("%d%s... is not number", n, Character.toString(b))
                                    );
                                }
                            }
                        } else {
                            if (digit <= LONG_MAX_LAST_DIGIT) {
                                n = n * 10 + digit;
                                b = readByte();
                                if (!isPrintableChar(b)) {
                                    return n;
                                } else if (b < '0' || '9' < b) {
                                    throw new NumberFormatException(
                                        String.format("%d%s... is not number", n, Character.toString(b))
                                    );
                                }
                            }
                        }
                    }
                    throw new ArithmeticException(
                        String.format("%s%d%d... overflows long.", minus ? "-" : "", n, digit)
                    );
                }
                n = n * 10 + digit;
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
 
    public long[] nextLongArray(int length){
        long[] array = new long[length];
        for(int i=0; i<length; i++) array[i] = this.nextLong();
        return array;
    }
    public long[] nextLongArray(int length, java.util.function.LongUnaryOperator map){
        long[] array = new long[length];
        for(int i=0; i<length; i++) array[i] = map.applyAsLong(this.nextLong());
        return array;
    }
    public int[] nextIntArray(int length){
        int[] array = new int[length];
        for(int i=0; i<length; i++) array[i] = this.nextInt();
        return array;
    }
    public int[] nextIntArray(int length, java.util.function.IntUnaryOperator map){
        int[] array = new int[length];
        for(int i=0; i<length; i++) array[i] = map.applyAsInt(this.nextInt());
        return array;
    }
    public double[] nextDoubleArray(int length){
        double[] array = new double[length];
        for(int i=0; i<length; i++) array[i] = this.nextDouble();
        return array;
    }
    public double[] nextDoubleArray(int length, java.util.function.DoubleUnaryOperator map){
        double[] array = new double[length];
        for(int i=0; i<length; i++) array[i] = map.applyAsDouble(this.nextDouble());
        return array;
    }
 
    public long[][] nextLongMatrix(int height, int width){
        long[][] mat = new long[height][width];
        for(int h=0; h<height; h++) for(int w=0; w<width; w++){
            mat[h][w] = this.nextLong();
        }
        return mat;
    }
    public int[][] nextIntMatrix(int height, int width){
        int[][] mat = new int[height][width];
        for(int h=0; h<height; h++) for(int w=0; w<width; w++){
            mat[h][w] = this.nextInt();
        }
        return mat;
    }
    public double[][] nextDoubleMatrix(int height, int width){
        double[][] mat = new double[height][width];
        for(int h=0; h<height; h++) for(int w=0; w<width; w++){
            mat[h][w] = this.nextDouble();
        }
        return mat;
    }
 
    public char[][] nextCharMatrix(int height, int width){
        char[][] mat = new char[height][width];
        for(int h=0; h<height; h++){
            String s = this.next();
            for(int w=0; w<width; w++){
                mat[h][w] = s.charAt(w);
            }
        }
        return mat;
    }
}

class ContestPrinter extends PrintWriter {
    public ContestPrinter(PrintStream stream) {
        super(stream);
    }
 
    public ContestPrinter() {
        super(System.out);
    }
 
    private static String dtos(double x, int n) {
        StringBuilder sb = new StringBuilder();
        if (x < 0) {
            sb.append('-');
            x = -x;
        }
        x += Math.pow(10, -n) / 2;
        sb.append((long) x);
        sb.append(".");
        x -= (long) x;
        for (int i = 0; i < n; i++) {
            x *= 10;
            sb.append((int) x);
            x -= (int) x;
        }
        return sb.toString();
    }
 
    @Override
    public void print(float f) {
        super.print(dtos(f, 20));
    }
 
    @Override
    public void println(float f) {
        super.println(dtos(f, 20));
    }
 
    @Override
    public void print(double d) {
        super.print(dtos(d, 20));
    }
 
    @Override
    public void println(double d) {
        super.println(dtos(d, 20));
    }
 
    public void printlnArray(String[] array) {
        for (String i : array)
            super.println(i);
    }
 
    public void printSpace(Object... o) {
        int n = o.length - 1;
        for (int i = 0; i < n; i++) {
            super.print(o[i]);
            super.print(" ");
        }
        super.println(o[n]);
    }
 
    public void println(Object... o) {
        int n = o.length - 1;
        for (int i = 0; i < n; i++)
            super.print(o[i]);
        super.println(o[n]);
    }
 
    public void printYN(boolean o) {
        super.println(o ? "Yes" : "No");
    }
 
    public void print(Object... o) {
        int n = o.length - 1;
        for (int i = 0; i < n; i++)
            super.print(o[i]);
        super.print(o[n]);
    }
 
    public void printArray(Object[] array) {
        int n = array.length - 1;
        for (int i = 0; i < n; i++) {
            super.print(array[i]);
            super.print(" ");
        }
        super.println(array[n]);
    }
 
    public void printlnArray(Object[] array) {
        for (Object i : array)
            super.println(i);
    }
 
    public void printArray(int[] array, String separator) {
        int n = array.length - 1;
        if (n == -1)
            return;
        for (int i = 0; i < n; i++) {
            super.print(array[i]);
            super.print(separator);
        }
        super.println(array[n]);
    }
 
    public void printArray(int[] array) {
        this.printArray(array, " ");
    }
 
    public void printArray(Integer[] array) {
        this.printArray(array, " ");
    }
 
    public void printArray(Integer[] array, String separator) {
        int n = array.length - 1;
        for (int i = 0; i < n; i++) {
            super.print(array[i]);
            super.print(separator);
        }
        super.println(array[n]);
    }
 
    public void printlnArray(int[] array) {
        for (int i : array)
            super.println(i);
    }
 
    public void printArray(int[] array, String separator, java.util.function.IntUnaryOperator map) {
        int n = array.length - 1;
        for (int i = 0; i < n; i++) {
            super.print(map.applyAsInt(array[i]));
            super.print(separator);
        }
        super.println(map.applyAsInt(array[n]));
    }
 
    public void printlnArray(int[] array, java.util.function.IntUnaryOperator map) {
        for (int i : array)
            super.println(map.applyAsInt(i));
    }
 
    public void printlnArray(long[] array, java.util.function.LongUnaryOperator map) {
        for (long i : array)
            super.println(map.applyAsLong(i));
    }
 
    public void printArray(int[] array, java.util.function.IntUnaryOperator map) {
        this.printArray(array, " ", map);
    }
 
    public void printArray(long[] array, String separator) {
        int n = array.length - 1;
        for (int i = 0; i < n; i++) {
            super.print(array[i]);
            super.print(separator);
        }
        super.println(array[n]);
    }
 
    public void printArray(long[] array) {
        this.printArray(array, " ");
    }
 
    public void printlnArray(long[] array) {
        for (long i : array)
            super.println(i);
    }
 
    public void printArray(double[] array) {
        printArray(array, " ");
    }
 
    public void printArray(double[] array, String separator) {
        int n = array.length - 1;
        for (int i = 0; i < n; i++) {
            super.print(array[i]);
            super.print(separator);
        }
        super.println(array[n]);
    }
 
    public void printlnArray(double[] array) {
        for (double i : array)
            super.println(i);
    }
 
    public void printArray(boolean[] array, String a, String b) {
        int n = array.length - 1;
        for (int i = 0; i < n; i++)
            super.print((array[i] ? a : b) + " ");
        super.println(array[n] ? a : b);
    }
 
    public void printArray(boolean[] array) {
        this.printArray(array, "Y", "N");
    }
 
    public void printArray(char[] array) {
        for (char c : array)
            this.print(c);
        this.println();
    }
 
    public void printArray(long[] array, String separator, java.util.function.LongUnaryOperator map) {
        int n = array.length - 1;
        for (int i = 0; i < n; i++) {
            super.print(map.applyAsLong(array[i]));
            super.print(separator);
        }
        super.println(map.applyAsLong(array[n]));
    }
 
    public void printArray(long[] array, java.util.function.LongUnaryOperator map) {
        this.printArray(array, " ", map);
    }
 
    public void printArray(ArrayList<?> array) {
        this.printArray(array, " ");
    }
 
    public void printArray(ArrayList<?> array, String separator) {
        int n = array.size() - 1;
        if (n == -1)
            return;
        for (int i = 0; i < n; i++) {
            super.print(array.get(i).toString());
            super.print(separator);
        }
        super.println(array.get(n).toString());
    }
 
    public void printlnArray(ArrayList<?> array) {
        int n = array.size();
        for (int i = 0; i < n; i++)
            super.println(array.get(i).toString());
    }
 
    public void printlnArray(ArrayList<Integer> array, java.util.function.IntUnaryOperator map) {
        int n = array.size();
        for (int i = 0; i < n; i++)
            super.println(map.applyAsInt(array.get(i)));
    }
 
    public void printlnArray(ArrayList<Long> array, java.util.function.LongUnaryOperator map) {
        int n = array.size();
        for (int i = 0; i < n; i++)
            super.println(map.applyAsLong(array.get(i)));
    }
 
    public void printArray(int[][] array) {
        for (int[] a : array)
            this.printArray(a);
    }
 
    public void printArray(int[][] array, java.util.function.IntUnaryOperator map) {
        for (int[] a : array)
            this.printArray(a, map);
    }
 
    public void printArray(long[][] array) {
        int n = array.length;
        if (n == 0)
            return;
        int m = array[0].length - 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++)
                super.print(array[i][j] + " ");
            super.println(array[i][m]);
        }
    }
 
    public void printArray(long[][] array, java.util.function.LongUnaryOperator map) {
        int n = array.length;
        if (n == 0)
            return;
        int m = array[0].length - 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                super.print(map.applyAsLong(array[i][j]));
                super.print(" ");
            }
            super.println(map.applyAsLong(array[i][m]));
        }
    }
 
    public void printArray(boolean[][] array) {
        int n = array.length;
        if (n == 0)
            return;
        int m = array[0].length - 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++)
                super.print(array[i][j] ? "○ " : "× ");
            super.println(array[i][m] ? "○" : "×");
        }
    }
 
    public void printArray(char[][] array) {
        int n = array.length;
        if (n == 0)
            return;
        int m = array[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++)
                super.print(array[i][j]);
            super.println();
        }
    }
}
