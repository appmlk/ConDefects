import static java.lang.Math.*;
import static java.util.Arrays.*;
import java.io.*;
import java.util.*;

public class Main {

    void solve(){
        StringBuilder res = new StringBuilder();
        String p = scanner.next();
        while (res.length() < 6){
            res.append(p);
        }
        out.println(p);
    }

    private static final boolean memory = false;
    private static final boolean singleTest = true;

    // ----- runner templates ----- //

    void run() {
        int numOfTests = singleTest? 1: scanner.nextInt();
        for(int testIdx = 1; testIdx <= numOfTests; testIdx++){
            solve();
        }
        out.flush();
        out.close();
    }

    // ----- runner templates ----- //

    public static void main(String[] args) {
        if(memory) {
            new Thread(null, () -> new Main().run(), "go", 1 << 26).start();
        }
        else{
            new Main().run();
        }
    }

    //------ input and output ------//

    public static FastScanner scanner = new FastScanner(System.in);
    public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

    public static class FastScanner {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar, numChars;

        public FastScanner(InputStream stream) {
            this.stream = stream;
        }

        public int read() {
            if (numChars == -1) throw new InputMismatchException();
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0) return -1;
            }
            return buf[curChar++];
        }

        public int nextInt() {
            return (int) nextLong();
        }

        public long nextLong() {
            int c = read();
            while (isWhitespace(c)) {
                c = read();
            }
            boolean negative = false;
            if (c == '-') {
                negative = true;
                c = read();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res = res * 10 + c - '0';
                c = read();
            } while (!isWhitespace(c));
            return negative ? -res : res;
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public char nextChar() {
            int c = read();
            while (isWhitespace(c)) {
                c = read();
            }
            return (char) c;
        }

        public String next() {
            int c = read();
            while (isWhitespace(c)) {
                c = read();
            }
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isWhitespace(c));
            return res.toString();
        }

        private boolean isWhitespace(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }
    }

    int[] nextIntArray(int n, int base){
        int[] arr = new int[n + base];
        for(int i = base; i < n + base; i++){arr[i] = scanner.nextInt();}
        return arr;
    }

    long[] nextLongArray(int n, int base){
        long[] arr = new long[n + base];
        for(int i = base; i < n + base; i++){arr[i] = scanner.nextLong();}
        return arr;
    }

    int[][] nextIntGrid(int n, int m, int base){
        int[][] grid = new int[n + base][m + base];
        for(int i = base; i < n + base; i++){for(int j = base; j < m + base; j++){grid[i][j] = scanner.nextInt();}}
        return grid;
    }

    char[][] nextCharGrid(int n, int m, int base){
        char[][] grid = new char[n + base][m + base];
        for(int i = base; i < n + base; i++){for(int j = base; j < m + base; j++){grid[i][j] = scanner.nextChar();}}
        return grid;
    }

    double[][] nextDoubleGrid(int n, int m, int base){
        double[][] grid = new double[n + base][m + base];
        for(int i = base; i < n + base; i++){for(int j = base; j < m + base; j++){grid[i][j] = scanner.nextDouble();}}
        return grid;
    }

    int[][] nextUnweightedGraph(int n, int m, int base){
        int[][] g = new int[base + n][];
        int[][] edges = new int[m][2];
        int[] adjSize = new int[n+base];
        for(int i = 0; i < m; i++){
            int a = scanner.nextInt(), b = scanner.nextInt();
            edges[i][0]=a;
            adjSize[a]++;
            edges[i][1]=b;
            adjSize[b]++;
        }
        for(int i = base; i < base + n; i++){
            g[i]=new int[adjSize[i]];
            adjSize[i]=0;
        }
        for(int[] e: edges){
            int a = e[0], b = e[1];
            g[a][adjSize[a]++]=b;
            g[b][adjSize[b]++]=a;
        }
        return g;
    }



    //------ debug and print functions ------//

    void debug(Object...os){out.println(deepToString(os));}
    void print(int[] arr, int start, int end){for(int i = start; i <= end; i++){out.print(arr[i]);out.print(i==end? '\n':' ');}}
    void print(long[] arr, int start, int end){for(int i = start; i <= end; i++){out.print(arr[i]);out.print(i==end? '\n':' ');}}
    void print(char[] arr, int start, int end){for(int i = start; i <= end; i++){out.print(arr[i]);out.print(i==end? '\n':' ');}}
    void print(Object... o){for(int i = 0; i < o.length; i++){out.print(o[i]);out.print(i==o.length-1?'\n':' ');}}
    <T> void printArrayList(List<T> arr, int start, int end){for(int i = start; i <= end; i++){out.print(arr.get(i));out.print(i==end? '\n':' ');}}


    //------ sort primitive type arrays ------//

    static void sort(int[] arr){
        List<Integer> temp = new ArrayList<>();
        for(int val: arr){temp.add(val);}
        Collections.sort(temp);
        for(int i = 0; i < arr.length; i++){arr[i] = temp.get(i);}
    }
    static void sort(long[] arr){
        List<Long> temp = new ArrayList<>();
        for(long val: arr){temp.add(val);}
        Collections.sort(temp);
        for(int i = 0; i < arr.length; i++){arr[i] = temp.get(i);}
    }
    static void sort(char[] arr) {
        List<Character> temp = new ArrayList<>();
        for (char val : arr) {temp.add(val);}
        Collections.sort(temp);
        for (int i = 0; i < arr.length; i++) {arr[i] = temp.get(i);}
    }

}