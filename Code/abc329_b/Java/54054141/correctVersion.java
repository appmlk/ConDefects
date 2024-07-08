import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringJoiner;
import java.util.function.IntUnaryOperator;
import java.util.function.LongUnaryOperator;
import java.util.stream.Collectors;

public class Main {
    static void solve() {
        int n = sc.nextInt();
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            list.add(sc.nextInt());
        }
        List<Integer> list2 = new ArrayList<Integer>(new HashSet<>(list));
        Collections.sort(list2);
        System.out.println(list2.get(list2.size() - 2));
    }

    final static ContestsPrinter pw = new ContestsPrinter();
    final static FastScanner sc = new FastScanner();

    public static void main(String[] args) {
        solve();
        pw.close();
    }

    /*
     * /////////////////////////////////////////////////////////////////////////////
     * My Library *
     * 
     * @author Rounin（ウソです。諸先輩方の知恵を流用しています）
     *//////////////////////////////////////////////////////////////////////////////

    /**
     * 階乗を算出する
     * [引数]n：任意の整数(int)
     * [戻り値] 任意の整数を階乗した値
     */
    static long factorial(int n) {
        if (n == 0)
            return 1;

        return n * factorial(n - 1);
    }

    /**
     * べき乗を行う
     * [引数]m：底となる整数(int)、n：べき指数となる整数(int)
     * [戻り値] 計算結果(int)
     */
    static int exponentiation(int m, int n) {
        if (n == 0)
            return 1;

        int ans = m;
        for (int i = 1; i < n; i++)
            ans = ans * m;
        return ans;
    }

    /**
     * 順列を生成する
     * [引数]array：配列(int)、start：任意の整数(int)、end：任意の整数(int)
     * [戻り値] boolean
     */
    static boolean nextPermutation(int[] array, int start, int end) {

        if (array == null || start > end || start < 0 || end > array.length) {
            System.out.println("Error: 引数が正しくありません。");
            return false;
        }

        for (int i = end - 2; i >= start; i--) {
            if (array[i] < array[i + 1]) {
                int j = end - 1;
                while (array[i] >= array[j]) {
                    j--;
                }

                // swap
                int tmp = array[j];
                array[j] = array[i];
                array[i] = tmp;

                Arrays.sort(array, i + 1, end);
                return true;
            }
        }
        return false;
    }

    /**
     * 最大公約数を算出する
     * [引数]a：任意の整数(int)、b：任意の整数(int)
     * [戻り値] 最大公約数
     */
    static int gcd(int a, int b) {
        return b > 0 ? gcd(b, a % b) : a;
    }

    /**
     * 最小公倍数を算出する
     * [引数]a：任意の整数(int)、b：任意の整数(int)
     * [戻り値] 最小公倍数
     */
    static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    /**
     * 任意の値から値までの間にある約数を算出する
     * [引数]start：任意の整数(int)、end：任意の整数(int)
     * [戻り値] 約数の数
     */
    static int calculateDivisorsInRange(int start, int end) {
        int cnt = 0;
        for (int i = start; i <= end; i++) {
            for (int j = 1; j <= i; j++) {
                if (i % j == 0)
                    cnt++;
            }
        }
        return cnt;
    }

    /**
     * 素数判定を行う
     * [引数]n：任意の整数(int)
     * [戻り値] boolean
     */
    static boolean isPrime(int n) {
        if (n == 2)
            return true;
        if (n < 2 || n % 2 == 0)
            return false;
        double d = Math.sqrt(n);
        for (int i = 3; i <= d; i += 2)
            if (n % i == 0) {
                return false;
            }
        return true;
    }

    /**
     * 倍数判定（10進数以外のときに有用）を行う
     * [引数]s：任意の文字列(String)、base：任意の整数(int)、m：任意の整数(int)
     * [戻り値] boolean
     */
    static boolean isMultiple(String s, int base, int m) {
        int temp = 0;
        for (int i = 0; i < s.length(); i++) {
            temp = (temp * base + Character.getNumericValue(s.charAt(i))) % m;
        }
        if (temp == 0) {
            return true;
        }
        return false;
    }

    /**
     * 進数変換を行う
     * [引数]sm：任意の文字列(String)、m：変換前の進数(int)、n：変換後の進数(int)
     * [戻り値] 進数変換後の文字列
     */
    static String toNbase(String sm, int m, int n) {
        return Long.toString(Long.parseLong(sm, m), n);
    }

    /**
     * int配列を降順にソートする
     */
    public static void sortDes(int[] a) {
        Arrays.sort(a);
        var temp = Arrays.copyOf(a, a.length);

        for (int i = 0; i < temp.length; i++) {
            a[i] = temp[temp.length - i - 1];
        }
    }

    /**
     * long配列を降順にソートする
     */
    public static void sortDes(long[] a) {
        Arrays.sort(a);
        var temp = Arrays.copyOf(a, a.length);

        for (int i = 0; i < temp.length; i++) {
            a[i] = temp[temp.length - i - 1];
        }
    }

    /*---       ---*/
    /*--- util ---*/
    /*---       ---*/
    public static List<Integer> toList(int[] a) {
        return Arrays.stream(a).boxed().collect(Collectors.toList());
    }

    public static List<Long> toList(long[] a) {
        return Arrays.stream(a).boxed().collect(Collectors.toList());
    }

    public static String reverse(String str) {
        return new StringBuilder().append(str).reverse().toString();
    }

    public static boolean isKaibun(String str) {
        return str.equals(reverse(str));
    }

    public static String toString(int a) {
        return String.valueOf(a);
    }

    public static String toString(long a) {
        return String.valueOf(a);
    }

    public static int toInt(String a) {
        return Integer.valueOf(a);
    }

    public static long toLong(String a) {
        return Long.valueOf(a);
    }

    public static char[] toCharArray(int a) {
        return String.valueOf(a).toCharArray();
    }

    public static char[] toCharArray(long a) {
        return String.valueOf(a).toCharArray();
    }

    public static char[] toCharArray(String a) {
        return a.toCharArray();
    }

    public static boolean isAllTrue(boolean[] boo) {
        for (boolean b : boo) {
            if (!b)
                return false;
        }
        return true;
    }

    public static int cntBoolean(boolean[] boo) {
        int cnt = 0;
        for (boolean b : boo) {
            if (b)
                cnt++;
        }
        return cnt;
    }

    public static void sort(int[] a) {
        Arrays.sort(a);
    }

    public static void sort(long[] a) {
        Arrays.sort(a);
    }

    public static int abs(int a, int b) {
        return Math.abs(a - b);
    }

    public static long abs(long a, long b) {
        return Math.abs(a - b);
    }

    public static void fill(int[] array, int val) {
        Arrays.fill(array, val);
    }

    public static void fill(char[] array, char val) {
        Arrays.fill(array, val);
    }

    public static void fill(boolean[] array, boolean val) {
        Arrays.fill(array, val);
    }

    public static void fill(int[][] array, int val) {
        for (var a : array)
            Arrays.fill(a, val);
    }

    public static void fill(long[] array, long val) {
        Arrays.fill(array, val);
    }

    public static void fill(long[][] array, long val) {
        for (var a : array)
            Arrays.fill(a, val);
    }

    public static void initializeDp(int[][] dp, int val) {
        fill(dp, val);
        dp[0][0] = 0;
    }

    public static void initializeDp(long[][] dp, long val) {
        fill(dp, val);
        dp[0][0] = 0L;
    }

    public static int max(int... array) {
        return Arrays.stream(array).max().getAsInt();
    }

    public static int sum(int... array) {
        return Arrays.stream(array).sum();
    }

    public static int max(int a, int b) {
        return Math.max(a, b);
    }

    public static int min(int... array) {
        return Arrays.stream(array).min().getAsInt();
    }

    public static int min(int a, int b) {
        return Math.min(a, b);
    }

    public static long max(long... array) {
        return Arrays.stream(array).max().getAsLong();
    }

    public static long max(long a, long b) {
        return Math.max(a, b);
    }

    public static long sum(long... array) {
        return Arrays.stream(array).sum();
    }

    public static long min(long a, long b) {
        return Math.min(a, b);
    }

    public static long min(long... array) {
        return Arrays.stream(array).min().getAsLong();
    }

    /*---       ---*/
    /*--- debug ---*/
    /*---       ---*/
    public static void debug(Object o, Object... args) {
        var format = "%s";
        var temp = new Object[args.length + 1];
        temp[0] = o;
        System.arraycopy(args, 0, temp, 1, args.length);
        for (int i = 0; i < temp.length - 1; i++) {
            format += " %s";
        }
        System.out.printf(format, temp);
        System.out.println("");
    }

    public static void debug(int[] x) {
        out(Arrays.toString(x));
    }

    public static void debug(boolean[] x) {
        out(Arrays.toString(x));
    }

    public static void debug(long[] x) {
        out(Arrays.toString(x));
    }

    public static void debug(int[][] x) {
        out(Arrays.deepToString(x));
    }

    public static void debugln(int[][] x) {
        for (int[] w : x) {
            for (var i : w) {
                System.err.print(i + " ");
            }
            out("");
        }
        out("///////////////////");
    }

    public static void debug(long[][] x) {
        out(Arrays.deepToString(x));
    }

    public static void debugln(long[][] x) {
        for (long[] w : x) {
            for (var i : w) {
                System.err.print(i + " ");
            }
            out("");
        }
        out("///////////////////");
    }

    public static void debug(boolean[][] x) {
        out(Arrays.deepToString(x));
    }

    public static void debug(char[][] x) {
        out(Arrays.deepToString(x));
    }

    public static void debug(Object[] x) {
        out(Arrays.toString(x));
    }

    public static void debug(Object[][] x) {
        out(Arrays.deepToString(x));
    }

    public static void debug(Object a) {
        System.err.println(a);
    }

    public static void out(String x) {
        System.err.println(x);
    }
}

class FastScanner {

    private final InputStream in = System.in;
    private final byte[] buffer = new byte[1024];
    private int ptr = 0;
    private int buflen = 0;

    private boolean hasNextByte() {
        if (ptr < buflen) {
            return true;
        } else {
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
        if (hasNextByte())
            return buffer[ptr++];
        else
            return -1;
    }

    private static boolean isPrintableChar(int c) {
        return 33 <= c && c <= 126;
    }

    public boolean hasNext() {
        while (hasNextByte() && !isPrintableChar(buffer[ptr]))
            ptr++;
        return hasNextByte();
    }

    public String next() {
        if (!hasNext())
            throw new NoSuchElementException();
        StringBuilder sb = new StringBuilder();
        int b = readByte();
        while (isPrintableChar(b)) {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }

    public long nextLong() {
        if (!hasNext())
            throw new NoSuchElementException();
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
                n *= 10;
                n += b - '0';
            } else if (b == -1 || !isPrintableChar(b)) {
                return minus ? -n : n;
            } else {
                throw new NumberFormatException();
            }
            b = readByte();
        }
    }

    public int nextInt() {
        long nl = nextLong();
        if (nl < Integer.MIN_VALUE || nl > Integer.MAX_VALUE)
            throw new NumberFormatException();
        return (int) nl;
    }

    public double nextDouble() {
        return Double.parseDouble(next());
    }

    public int[] nextIntArray(int size) {
        int[] intArray = new int[size];
        Arrays.setAll(intArray, i -> nextInt());
        return intArray;
    }

    public int[] nextIntArray(int size, IntUnaryOperator map) {
        int[] intArray = new int[size];
        Arrays.setAll(intArray, i -> map.applyAsInt(nextInt()));
        return intArray;
    }

    public int[] nextIntArrayOneToZeroIndex(int size) {
        return nextIntArray(size, i -> i - 1);
    }

    public long[] nextLongArray(int size) {
        long[] longArray = new long[size];
        Arrays.setAll(longArray, i -> nextLong());
        return longArray;
    }

    public long[] nextLongArray(int size, LongUnaryOperator map) {
        long[] longArray = new long[size];
        Arrays.setAll(longArray, i -> map.applyAsLong(nextLong()));
        return longArray;
    }

    public String[] nextStringArray(int size) {
        String[] stringArray = new String[size];
        Arrays.setAll(stringArray, i -> next());
        return stringArray;
    }

    public List<String> nextStringList(int size) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < size; i++)
            list.add(next());
        return list;
    }

    public Integer[] nextIntegerArray(int size) {
        Integer[] ret = new Integer[size];
        for (int i = 0; i < size; i++)
            ret[i] = nextInt();
        return ret;
    }

    public Integer[] nextIntegerArray(int size, IntUnaryOperator map) {
        Integer[] ret = new Integer[size];
        for (int i = 0; i < size; i++)
            ret[i] = map.applyAsInt(nextInt());
        return ret;
    }

    public char[][] nextDimensionalCharArray(int h, int w) {
        char[][] array = new char[h][w];
        for (int i = 0; i < h; i++) {
            array[i] = next().toCharArray();
        }
        return array;
    }

    public int[][] nextDimensionalIntArray(int h, int w) {
        int[][] array = new int[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                array[i][j] = nextInt();
            }
        }
        return array;
    }

    public long[][] nextDimensionalLongArray(int h, int w) {
        long[][] array = new long[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                array[i][j] = nextLong();
            }
        }
        return array;
    }

    public int[][] nextIntArrayFromStr(int h, int w) {
        int[][] array = new int[h][w];

        for (int i = 0; i < h; i++) {
            String temp = next();
            for (int j = 0; j < w; j++) {
                array[i][j] = temp.charAt(j) - '0';
            }
        }

        return array;
    }

    public List<Integer> nextIntgerList(int size) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++)
            list.add(nextInt());
        return list;
    }

    public List<Integer> nextIntgerList(int size, IntUnaryOperator map) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++)
            list.add(map.applyAsInt(nextInt()));
        return list;
    }

}

class ContestsPrinter extends java.io.PrintWriter {
    public ContestsPrinter(java.io.PrintStream stream) {
        super(stream);
    }

    public ContestsPrinter(java.io.File file) throws java.io.FileNotFoundException {
        super(new java.io.PrintStream(file));
    }

    public ContestsPrinter() {
        super(System.out);
    }

    private static String doubleToString(double x, int n) {
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
        super.print(doubleToString(f, 20));
    }

    @Override
    public void println(float f) {
        super.println(doubleToString(f, 20));
    }

    @Override
    public void print(double d) {
        super.print(doubleToString(d, 20));
    }

    @Override
    public void println(double d) {
        super.println(doubleToString(d, 20));
    }

    @Override
    public void print(boolean boo) {
        super.print(boo ? "Yes" : "No");
    }

    public void print(int[] array, String separator) {
        int n = array.length;
        if (n == 0) {
            super.println();
            return;
        }
        for (int i = 0; i < n - 1; i++) {
            super.print(array[i]);
            super.print(separator);
        }
        super.println(array[n - 1]);
    }

    public void print(int[] array) {
        this.print(array, " ");
    }

    public void print(int[] array, String separator, java.util.function.IntUnaryOperator map) {
        int n = array.length;
        if (n == 0) {
            super.println();
            return;
        }
        for (int i = 0; i < n - 1; i++) {
            super.print(map.applyAsInt(array[i]));
            super.print(separator);
        }
        super.println(map.applyAsInt(array[n - 1]));
    }

    public void print(int[] array, java.util.function.IntUnaryOperator map) {
        this.print(array, " ", map);
    }

    public void print(long[] array, String separator) {
        int n = array.length;
        if (n == 0) {
            super.println();
            return;
        }
        for (int i = 0; i < n - 1; i++) {
            super.print(array[i]);
            super.print(separator);
        }
        super.println(array[n - 1]);
    }

    public void print(long[] array) {
        this.print(array, " ");
    }

    public void print(long[] array, String separator, java.util.function.LongUnaryOperator map) {
        int n = array.length;
        if (n == 0) {
            super.println();
            return;
        }
        for (int i = 0; i < n - 1; i++) {
            super.print(map.applyAsLong(array[i]));
            super.print(separator);
        }
        super.println(map.applyAsLong(array[n - 1]));
    }

    public void print(long[] array, java.util.function.LongUnaryOperator map) {
        this.print(array, " ", map);
    }

    public <T> void print(T[] array, String separator) {
        int n = array.length;
        if (n == 0) {
            super.println();
            return;
        }
        for (int i = 0; i < n - 1; i++) {
            super.print(array[i]);
            super.print(separator);
        }
        super.println(array[n - 1]);
    }

    public <T> void print(T[] array) {
        this.print(array, " ");
    }

    public <T> void print(T[] array, String separator, java.util.function.UnaryOperator<T> map) {
        int n = array.length;
        if (n == 0) {
            super.println();
            return;
        }
        for (int i = 0; i < n - 1; i++) {
            super.print(map.apply(array[i]));
            super.print(separator);
        }
        super.println(map.apply(array[n - 1]));
    }

    public <T> void print(T[] array, java.util.function.UnaryOperator<T> map) {
        this.print(array, " ", map);
    }

    public String getOutputCollection(Collection<?> c) {
        return String.join(" ", c.stream().map(String::valueOf).collect(Collectors.toList()));
    }

    public void print(Collection<?> c) {
        super.print(getOutputCollection(c));
    }

    public void println(Collection<?> c) {
        StringBuilder sb = new StringBuilder();
        for (Object object : c) {
            if (sb.length() != 0)
                sb.append("\r\n");
            sb.append(object);
        }
        System.out.println(sb);
    }

    public void println(int[] array) {
        print(array, "\r\n");
    }

    public void println(long[] array) {
        print(array, "\r\n");
    }

    public void print(String[] a) {
        StringJoiner joiner = new StringJoiner(" ");
        for (String str : a) {
            joiner.add(str);
        }
        System.out.println(joiner);
    }

    public void println(String[] a) {
        StringJoiner joiner = new StringJoiner("\r\n");
        for (String str : a) {
            joiner.add(str);
        }
        System.out.println(joiner);
    }

    public void print(Object obj) {
        System.out.print(obj);
    }

    public void println(Object obj) {
        System.out.println(obj);
    }

    public void printlnDeep(ArrayList<ArrayList<Integer>> c) {
        StringBuilder sb = new StringBuilder();
        for (var ci : c)
            sb.append(getOutputCollection(ci)).append("\r\n");
        System.out.println(sb);
    }

    public void printZeroToOneIndex(int[] a) {
        print(a, i -> i + 1);
    }

    public void printZeroToOneIndex(List<Integer> a) {
        print(a.stream().map(i -> i + 1).collect(Collectors.toList()));
    }

    public void printlnZeroToOneIndex(List<Integer> a) {
        println(a.stream().map(i -> i + 1).collect(Collectors.toList()));
    }

    public void print(boolean[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i] + " ");
        }
        System.out.println("");
    }

    public void print(boolean[][] b) {
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                System.out.print(b[i][j] ? "o" : "x");
            }
            System.out.println("");
        }
    }

    public void print(int[][] array) {
        for (var i : array)
            print(i);
    }

    public void print(long[][] array) {
        for (var i : array)
            print(i);
    }

    public void print(char[][] array) {
        for (char[] cs : array) {
            for (char c : cs) {
                print(c);
            }
            println();
        }
    }

}

class Graph {
    List<ArrayList<Integer>> g;
    int v;
    int e;
    boolean[] visited;

    public Graph(int v) {
        this.v = v;
        for (var i = 0; i < v; i++)
            g.add(new ArrayList<Integer>());
        visited = new boolean[v];

    }

    public void addEdge(int a, int b) {
        g.get(a).add(b);
        g.get(b).add(a);
        e++;
    }

    public void dfs(int now) {
        visited[now] = true;
        for (var next : g.get(now)) {
            if (visited[next])
                continue;
            dfs(next);
        }
    }

    public boolean marked(int now) {
        return visited[now];
    }

    public boolean isAllConected() {
        for (var bo : visited) {
            if (!bo)
                return false;
        }
        return true;
    }
}
