import java.util.*;
import java.io.*;

class Main {
    private static final void solve() {
        int n = sc.nextInt();
        final int m = (int) 1e6;
        var a = sc.nextIntArray(n);
        long ans = 0;
        for (int i : a) {
            var yes = true;
            while (i != 0) {
                yes &= i % 10 < 5;
                i /= 10;
            }
            if (yes)
                ans--;
        }
        var d = new long[m];
        for (int i : a)
            d[i]++;
        var t = new int[6];
        var tt = new int[6];
        for (int i = 0; i < m; i++) {
            int ii = i;
            for (int jjj = 0; jjj < 6; jjj++) {
                tt[jjj] = ii % 10;
                ii /= 10;
            }
            for (int j = 1; j < 64; j++) {
                int jj = j;
                boolean fu = false;
                t = tt.clone();
                for (int k = 0; k < 6; k++) {
                    if ((jj & 1) == 1) {
                        t[k]--;
                        fu ^= true;
                    }
                    jj >>= 1;
                }
                var yes = true;
                for (int k : t)
                    yes &= k != -1;
                if (!yes)
                    continue;
                int to = 0;
                for (int k = 5; k >= 0; k--) {
                    to *= 10;
                    to += t[k];
                }
                if (fu)
                    d[i] += d[to];
                else
                    d[i] -= d[to];
            }
        }
        for (int i : a) {
            int j = m - i - 1;
            ans += d[j];
        }
        ou.println(ans / 2);
    }

    public static void main(String[] args) {
        solve();
        ou.flush();
    }

    private static final ContestScanner sc = new ContestScanner(System.in);
    private static final ContestPrinter ou = new ContestPrinter(System.out);
}

final class ContestScanner {
    private final InputStream in;
    private final byte[] buffer = new byte[1024];
    private int ptr = 0;
    private int buflen = 0;

    public ContestScanner(InputStream in) {
        this.in = in;
    }

    public ContestScanner() {
        this(System.in);
    }

    private boolean hasNextByte() {
        if (ptr < buflen)
            return true;
        ptr = 0;
        try {
            buflen = in.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buflen > 0;
    }

    private int readByte() {
        return hasNextByte() ? buffer[ptr++] : -1;
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

    public void nextThrow(int n) {
        for (int i = 0; i < n; i++)
            this.next();
    }

    public void nextThrow() {
        this.nextThrow(1);
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
        if (b < '0' || '9' < b)
            throw new NumberFormatException();
        while (true) {
            if ('0' <= b && b <= '9') {
                n *= 10;
                n += b - '0';
            } else if (b == -1 || !isPrintableChar(b))
                return minus ? -n : n;
            else
                throw new NumberFormatException();
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

    public boolean[] nextBoolean(char True) {
        String s = this.next();
        int n = s.length();
        boolean[] array = new boolean[n];
        for (int i = 0; i < n; i++)
            array[i] = s.charAt(i) == True;
        return array;
    }

    public long[] nextLongArray(int length) {
        long[] array = new long[length];
        for (int i = 0; i < length; i++)
            array[i] = this.nextLong();
        return array;
    }

    public long[] nextLongArray(int length, java.util.function.LongUnaryOperator map) {
        long[] array = new long[length];
        for (int i = 0; i < length; i++)
            array[i] = map.applyAsLong(this.nextLong());
        return array;
    }

    public long[] nextLongArray(int length, long[] a) {
        long[] array = new long[length + a.length];
        for (int i = 0; i < length; i++)
            array[i] = this.nextLong();
        for (int i = length; i < array.length; i++)
            array[i] = a[i - length];
        return array;
    }

    public int[] nextIntArray(int length) {
        int[] array = new int[length];
        for (int i = 0; i < length; i++)
            array[i] = this.nextInt();
        return array;
    }

    public int[] nextIntArray(int length, java.util.function.IntUnaryOperator map) {
        int[] array = new int[length];
        for (int i = 0; i < length; i++)
            array[i] = map.applyAsInt(this.nextInt());
        return array;
    }

    public int[] nextIntArray(int length, int[] array) {
        int n = length + array.length;
        int[] a = new int[n];
        for (int i = 0; i < length; i++)
            a[i] = this.nextInt();
        for (int i = length; i < n; i++)
            a[i] = array[i - length];
        return a;
    }

    public Integer[] nextIntegerArray(int length, java.util.function.IntUnaryOperator map) {
        Integer[] array = new Integer[length];
        for (int i = 0; i < length; i++)
            array[i] = map.applyAsInt(this.nextInt());
        return array;
    }

    public Integer[] nextIntegerArray(int length) {
        Integer[] array = new Integer[length];
        for (int i = 0; i < length; i++)
            array[i] = this.nextInt();
        return array;
    }

    public double[] nextDoubleArray(int length) {
        double[] array = new double[length];
        for (int i = 0; i < length; i++)
            array[i] = this.nextDouble();
        return array;
    }

    public double[] nextDoubleArray(int length, java.util.function.DoubleUnaryOperator map) {
        double[] array = new double[length];
        for (int i = 0; i < length; i++)
            array[i] = map.applyAsDouble(this.nextDouble());
        return array;
    }

    public String[] nextArray(int length) {
        String[] array = new String[length];
        for (int i = 0; i < length; i++)
            array[i] = this.next();
        return array;
    }

    public long[][] nextLongMatrix(int height, int width) {
        long[][] mat = new long[height][width];
        for (int h = 0; h < height; h++)
            for (int w = 0; w < width; w++)
                mat[h][w] = this.nextLong();
        return mat;
    }

    public long[][] nextLongMatrix(int height, int width, java.util.function.LongUnaryOperator map) {
        long[][] mat = new long[height][width];
        for (int h = 0; h < height; h++)
            for (int w = 0; w < width; w++)
                mat[h][w] = map.applyAsLong(this.nextLong());
        return mat;
    }

    public int[][] nextIntMatrix(int height, int width) {
        int[][] mat = new int[height][width];
        for (int h = 0; h < height; h++)
            for (int w = 0; w < width; w++)
                mat[h][w] = this.nextInt();
        return mat;
    }

    public int[][] nextIntMatrix(int height, int width, java.util.function.IntUnaryOperator map) {
        int[][] mat = new int[height][width];
        for (int h = 0; h < height; h++)
            for (int w = 0; w < width; w++)
                mat[h][w] = map.applyAsInt(this.nextInt());
        return mat;
    }

    public double[][] nextDoubleMatrix(int height, int width) {
        double[][] mat = new double[height][width];
        for (int h = 0; h < height; h++)
            for (int w = 0; w < width; w++)
                mat[h][w] = this.nextDouble();
        return mat;
    }

    public boolean[][] nextBooleanMatrix(int height, int width, char True) {
        boolean[][] mat = new boolean[height][width];
        for (int h = 0; h < height; h++) {
            String s = this.next();
            for (int w = 0; w < width; w++)
                mat[h][w] = s.charAt(w) == True;
        }
        return mat;
    }

    public char[][] nextCharMatrix(int height, int width) {
        char[][] mat = new char[height][width];
        for (int h = 0; h < height; h++) {
            String s = this.next();
            for (int w = 0; w < width; w++)
                mat[h][w] = s.charAt(w);
        }
        return mat;
    }
}

final class ContestPrinter extends PrintWriter {
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