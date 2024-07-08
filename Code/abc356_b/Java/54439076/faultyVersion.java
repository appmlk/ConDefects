import java.util.*;
import java.io.*;
import java.util.function.*;

public class Main {

	public static boolean solve(int[] A, int[][] X, int N, int M) {
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < M; j++) {
				X[i][j] += X[i-1][j];
			}
		}

		for (int j = 0; j < M; j++) {
			if (X[N-1][j] < A[j]) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		ContestScanner sc = new ContestScanner(System.in);
		FastWriter out = new FastWriter(System.out);

		int N = sc.nextInt();
		int M = sc.nextInt();
		int[] A = sc.nextIntArray(M);
		int[][] X = sc.nextIntMatrix(N, M);

		out.write((solve(A, X, N, M) ? "YES\n" : "NO\n"));
		
		out.flush();
	}
}

class ContestScanner {
    private final InputStream in;
    private final byte[] buffer = new byte[1024];
    private int ptr = 0;
    private int buflen = 0;
 
    private static final long LONG_MAX_TENTHS = 922337203685477580L;
    private static final int LONG_MAX_LAST_DIGIT = 7;
    private static final int LONG_MIN_LAST_DIGIT = 8;
 
    public ContestScanner(InputStream in) {
        this.in = in;
    }
 
    public ContestScanner(File file) throws FileNotFoundException {
        this(new BufferedInputStream(new FileInputStream(file)));
    }
 
    public ContestScanner() {
        this(System.in);
    }
 
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
        if (hasNextByte()) return buffer[ptr++];
        else return -1;
    }
 
    private static boolean isPrintableChar(int c) {
        return 33 <= c && c <= 126;
    }
 
    public boolean hasNext() {
        while (hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++;
        return hasNextByte();
    }
 
    public String next() {
        if (!hasNext()) throw new NoSuchElementException();
        StringBuilder sb = new StringBuilder();
        int b = readByte();
        while (isPrintableChar(b)) {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }
 
    public String nextLine() {
        if (!hasNext()) throw new NoSuchElementException();
        StringBuilder sb = new StringBuilder();
        int b = readByte();
        while (isPrintableChar(b) || b == ' ') {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString().trim();
    }
 
    public String[] nextStringArray(int length) {
        String[] array = new String[length];
        for (int i = 0; i < length; i++) array[i] = this.next();
        return array;
    }
 
    public String[] nextStringArray(int length, UnaryOperator<String> map) {
        String[] array = new String[length];
        for (int i = 0; i < length; i++) array[i] = map.apply(this.next());
        return array;
    }
 
    public String[][] nextStringMatrix(int height, int width) {
        String[][] mat = new String[height][width];
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                mat[h][w] = this.next();
            }
        }
        return mat;
    }
 
    public String[][] nextStringMatrix(int height, int width, UnaryOperator<String> map) {
        String[][] mat = new String[height][width];
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                mat[h][w] = map.apply(this.next());
            }
        }
        return mat;
    }
 
    public char[][] nextCharMatrix(int height, int width) {
        char[][] mat = new char[height][width];
        for (int h = 0; h < height; h++) {
            String s = this.next();
            for (int w = 0; w < width; w++) {
                mat[h][w] = s.charAt(w);
            }
        }
        return mat;
    }
 
    public char[][] nextCharMatrix(int height, int width, UnaryOperator<Character> map) {
        char[][] mat = new char[height][width];
        for (int h = 0; h < height; h++) {
            String s = this.next();
            for (int w = 0; w < width; w++) {
                mat[h][w] = map.apply(s.charAt(w));
            }
        }
        return mat;
    }
 
    public int nextInt() {
        long nl = nextLong();
        if (nl < Integer.MIN_VALUE || nl > Integer.MAX_VALUE) throw new NumberFormatException();
        return (int) nl;
    }
 
    public int[] nextIntArray(int length) {
        int[] array = new int[length];
        for (int i = 0; i < length; i++) array[i] = this.nextInt();
        return array;
    }
 
    public int[] nextIntArray(int length, IntUnaryOperator map) {
        int[] array = new int[length];
        for (int i = 0; i < length; i++) array[i] = map.applyAsInt(this.nextInt());
        return array;
    }
 
    public int[][] nextIntMatrix(int height, int width) {
        int[][] mat = new int[height][width];
        for (int h = 0; h < height; h++)
            for (int w = 0; w < width; w++) {
                mat[h][w] = this.nextInt();
            }
        return mat;
    }
 
    public int[][] nextIntMatrix(int height, int width, IntUnaryOperator map) {
        int[][] mat = new int[height][width];
        for (int h = 0; h < height; h++)
            for (int w = 0; w < width; w++) {
                mat[h][w] = map.applyAsInt(this.nextInt());
            }
        return mat;
    }
 
    public Integer[] nextIntegerArray(int length) {
        Integer[] array = new Integer[length];
        for (int i = 0; i < length; i++) array[i] = this.nextInt();
        return array;
    }
 
    public Integer[] nextIntegerArray(int length, IntUnaryOperator map) {
        Integer[] array = new Integer[length];
        for (int i = 0; i < length; i++) array[i] = map.applyAsInt(this.nextInt());
        return array;
    }
 
    public Integer[][] nextIntegerMatrix(int height, int width) {
        Integer[][] mat = new Integer[height][width];
        for (int h = 0; h < height; h++)
            for (int w = 0; w < width; w++) {
                mat[h][w] = this.nextInt();
            }
        return mat;
    }
 
    public Integer[][] nextIntegerMatrix(int height, int width, IntUnaryOperator map) {
        Integer[][] mat = new Integer[height][width];
        for (int h = 0; h < height; h++)
            for (int w = 0; w < width; w++) {
                mat[h][w] = map.applyAsInt(this.nextInt());
            }
        return mat;
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
            } else if (b == -1 || !isPrintableChar(b)) {
                return minus ? -n : n;
            } else {
                throw new NumberFormatException();
            }
            b = readByte();
        }
    }
 
    public long[] nextLongArray(int length) {
        long[] array = new long[length];
        for (int i = 0; i < length; i++) array[i] = this.nextLong();
        return array;
    }
 
    public long[] nextLongArray(int length, LongUnaryOperator map) {
        long[] array = new long[length];
        for (int i = 0; i < length; i++) array[i] = map.applyAsLong(this.nextLong());
        return array;
    }
 
    public long[][] nextLongMatrix(int height, int width) {
        long[][] mat = new long[height][width];
        for (int h = 0; h < height; h++)
            for (int w = 0; w < width; w++) {
                mat[h][w] = this.nextLong();
            }
        return mat;
    }
 
    public long[][] nextLongMatrix(int height, int width, LongUnaryOperator map) {
        long[][] mat = new long[height][width];
        for (int h = 0; h < height; h++)
            for (int w = 0; w < width; w++) {
                mat[h][w] = map.applyAsLong(this.nextLong());
            }
        return mat;
    }
 
    public Long[] nextLongNonPrimitiveArray(int length) {
        Long[] array = new Long[length];
        for (int i = 0; i < length; i++) array[i] = this.nextLong();
        return array;
    }
 
    public Long[] nextLongNonPrimitiveArray(int length, LongUnaryOperator map) {
        Long[] array = new Long[length];
        for (int i = 0; i < length; i++) array[i] = map.applyAsLong(this.nextLong());
        return array;
    }
 
    public Long[][] nextLongNonPrimitiveMatrix(int height, int width) {
        Long[][] mat = new Long[height][width];
        for (int h = 0; h < height; h++)
            for (int w = 0; w < width; w++) {
                mat[h][w] = this.nextLong();
            }
        return mat;
    }
 
    public Long[][] nextLongNonPrimitiveMatrix(int height, int width, LongUnaryOperator map) {
        Long[][] mat = new Long[height][width];
        for (int h = 0; h < height; h++)
            for (int w = 0; w < width; w++) {
                mat[h][w] = map.applyAsLong(this.nextLong());
            }
        return mat;
    }
 
    public double nextDouble() {
        return Double.parseDouble(next());
    }
 
    public double[] nextDoubleArray(int length) {
        double[] array = new double[length];
        for (int i = 0; i < length; i++) array[i] = this.nextDouble();
        return array;
    }
 
    public double[] nextDoubleArray(int length, DoubleUnaryOperator map) {
        double[] array = new double[length];
        for (int i = 0; i < length; i++) array[i] = map.applyAsDouble(this.nextDouble());
        return array;
    }
 
    public double[][] nextDoubleMatrix(int height, int width) {
        double[][] mat = new double[height][width];
        for (int h = 0; h < height; h++)
            for (int w = 0; w < width; w++) {
                mat[h][w] = this.nextDouble();
            }
        return mat;
    }
 
    public double[][] nextDoubleMatrix(int height, int width, DoubleUnaryOperator map) {
        double[][] mat = new double[height][width];
        for (int h = 0; h < height; h++)
            for (int w = 0; w < width; w++) {
                mat[h][w] = map.applyAsDouble(this.nextDouble());
            }
        return mat;
    }
}
 
class FastWriter {
    private static final int BUF_SIZE = 1 << 13;
    private final byte[] buf = new byte[BUF_SIZE];
    private final OutputStream out;
    private int ptr = 0;
 
    private FastWriter() {
        out = null;
    }
 
    public FastWriter(OutputStream os) {
        this.out = os;
    }
 
    public FastWriter(String path) {
        try {
            this.out = new FileOutputStream(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("FastWriter");
        }
    }
 
    public FastWriter write(byte b) {
        buf[ptr++] = b;
        if (ptr == BUF_SIZE) innerflush();
        return this;
    }
 
    public FastWriter write(char c) {
        return write((byte) c);
    }
 
    public FastWriter write(char[] s) {
        for (char c : s) {
            buf[ptr++] = (byte) c;
            if (ptr == BUF_SIZE) innerflush();
        }
        return this;
    }
 
    public FastWriter write(String s) {
        s.chars().forEach(c -> {
            buf[ptr++] = (byte) c;
            if (ptr == BUF_SIZE) innerflush();
        });
        return this;
    }
 
    private static int countDigits(int l) {
        if (l >= 1000000000) return 10;
        if (l >= 100000000) return 9;
        if (l >= 10000000) return 8;
        if (l >= 1000000) return 7;
        if (l >= 100000) return 6;
        if (l >= 10000) return 5;
        if (l >= 1000) return 4;
        if (l >= 100) return 3;
        if (l >= 10) return 2;
        return 1;
    }
 
    public FastWriter write(int x) {
        if (x == Integer.MIN_VALUE) {
            return write((long) x);
        }
        if (ptr + 12 >= BUF_SIZE) innerflush();
        if (x < 0) {
            write((byte) '-');
            x = -x;
        }
        int d = countDigits(x);
        for (int i = ptr + d - 1; i >= ptr; i--) {
            buf[i] = (byte) ('0' + x % 10);
            x /= 10;
        }
        ptr += d;
        return this;
    }
 
    private static int countDigits(long l) {
        if (l >= 1000000000000000000L) return 19;
        if (l >= 100000000000000000L) return 18;
        if (l >= 10000000000000000L) return 17;
        if (l >= 1000000000000000L) return 16;
        if (l >= 100000000000000L) return 15;
        if (l >= 10000000000000L) return 14;
        if (l >= 1000000000000L) return 13;
        if (l >= 100000000000L) return 12;
        if (l >= 10000000000L) return 11;
        if (l >= 1000000000L) return 10;
        if (l >= 100000000L) return 9;
        if (l >= 10000000L) return 8;
        if (l >= 1000000L) return 7;
        if (l >= 100000L) return 6;
        if (l >= 10000L) return 5;
        if (l >= 1000L) return 4;
        if (l >= 100L) return 3;
        if (l >= 10L) return 2;
        return 1;
    }
 
    public FastWriter write(long x) {
        if (x == Long.MIN_VALUE) {
            return write("" + x);
        }
        if (ptr + 21 >= BUF_SIZE) innerflush();
        if (x < 0) {
            write((byte) '-');
            x = -x;
        }
        int d = countDigits(x);
        for (int i = ptr + d - 1; i >= ptr; i--) {
            buf[i] = (byte) ('0' + x % 10);
            x /= 10;
        }
        ptr += d;
        return this;
    }
 
    public FastWriter write(double x, int precision) {
        if (x < 0) {
            write('-');
            x = -x;
        }
        x += Math.pow(10, -precision) / 2;
        //      if(x < 0){ x = 0; }
        write((long) x).write(".");
        x -= (long) x;
        for (int i = 0; i < precision; i++) {
            x *= 10;
            write((char) ('0' + (int) x));
            x -= (int) x;
        }
        return this;
    }
 
    public FastWriter writeln(char c) {
        return write(c).writeln();
    }
 
    public FastWriter writeln(int x) {
        return write(x).writeln();
    }
 
    public FastWriter writeln(long x) {
        return write(x).writeln();
    }
 
    public FastWriter writeln(double x, int precision) {
        return write(x, precision).writeln();
    }
 
    public FastWriter write(int... xs) {
        boolean first = true;
        for (int x : xs) {
            if (!first) write(' ');
            first = false;
            write(x);
        }
        return this;
    }
 
    public FastWriter write(long... xs) {
        boolean first = true;
        for (long x : xs) {
            if (!first) write(' ');
            first = false;
            write(x);
        }
        return this;
    }
 
    public FastWriter writeln() {
        return write((byte) '\n');
    }
 
    public FastWriter writeln(int... xs) {
        return write(xs).writeln();
    }
 
    public FastWriter writeln(long... xs) {
        return write(xs).writeln();
    }
 
    public FastWriter writeln(char[] line) {
        return write(line).writeln();
    }
 
    public FastWriter writeln(char[]... map) {
        for (char[] line : map) write(line).writeln();
        return this;
    }
 
    public FastWriter writeln(String s) {
        return write(s).writeln();
    }
 
    private void innerflush() {
        try {
            out.write(buf, 0, ptr);
            ptr = 0;
        } catch (IOException e) {
            throw new RuntimeException("innerflush");
        }
    }
 
    public void flush() {
        innerflush();
        try {
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException("flush");
        }
    }
 
    public FastWriter print(byte b) {
        return write(b);
    }
 
    public FastWriter print(char c) {
        return write(c);
    }
 
    public FastWriter print(String s) {
        return write(s);
    }
 
    public FastWriter print(int x) {
        return write(x);
    }
 
    public FastWriter print(long x) {
        return write(x);
    }
 
    public FastWriter print(double x, int precision) {
        return write(x, precision);
    }
 
    public FastWriter print(double x) {
        return print(x, 20);
    }
 
    public FastWriter println(char c) {
        return writeln(c);
    }
 
    public FastWriter println(int x) {
        return writeln(x);
    }
 
    public FastWriter println(long x) {
        return writeln(x);
    }
 
    public FastWriter println(double x, int precision) {
        return writeln(x, precision);
    }
 
    public FastWriter println(double x) {
        return println(x, 20);
    }
 
    public FastWriter println(String s) {
        return writeln(s);
    }
 
    public FastWriter println() {
        return writeln();
    }
 
    public FastWriter print(Object x) {
        return print(x.toString());
    }
 
    public FastWriter println(Object x) {
        return println(x.toString());
    }
 
    public void printArray(int[] array, String separator) {
        int n = array.length;
        if (n == 0) {
            println();
            return;
        }
        for (int i = 0; i < n - 1; i++) {
            print(array[i]);
            print(separator);
        }
        println(array[n - 1]);
    }
 
    public void printArray(int[] array) {
        this.printArray(array, " ");
    }
 
    public void printArray(int[] array, String separator, java.util.function.IntUnaryOperator map) {
        int n = array.length;
        if (n == 0) {
            println();
            return;
        }
        for (int i = 0; i < n - 1; i++) {
            print(map.applyAsInt(array[i]));
            print(separator);
        }
        println(map.applyAsInt(array[n - 1]));
    }
 
    public void printArray(int[] array, java.util.function.IntUnaryOperator map) {
        this.printArray(array, " ", map);
    }
 
    public void printArray(long[] array, String separator) {
        int n = array.length;
        if (n == 0) {
            println();
            return;
        }
        for (int i = 0; i < n - 1; i++) {
            print(array[i]);
            print(separator);
        }
        println(array[n - 1]);
    }
 
    public void printArray(long[] array) {
        this.printArray(array, " ");
    }
 
    public void printArray(long[] array, String separator, java.util.function.LongUnaryOperator map) {
        int n = array.length;
        if (n == 0) {
            println();
            return;
        }
        for (int i = 0; i < n - 1; i++) {
            print(map.applyAsLong(array[i]));
            print(separator);
        }
        println(map.applyAsLong(array[n - 1]));
    }
 
    public void printArray(long[] array, java.util.function.LongUnaryOperator map) {
        this.printArray(array, " ", map);
    }
 
    public void printArray(double[] array, String separator) {
        int n = array.length;
        if (n == 0) {
            println();
            return;
        }
        for (int i = 0; i < n - 1; i++) {
            print(array[i]);
            print(separator);
        }
        println(array[n - 1]);
    }
 
    public void printArray(double[] array) {
        this.printArray(array, " ");
    }
 
    public void printArray(double[] array, String separator, java.util.function.DoubleUnaryOperator map) {
        int n = array.length;
        if (n == 0) {
            println();
            return;
        }
        for (int i = 0; i < n - 1; i++) {
            print(map.applyAsDouble(array[i]));
            print(separator);
        }
        println(map.applyAsDouble(array[n - 1]));
    }
 
    public void printArray(double[] array, java.util.function.DoubleUnaryOperator map) {
        this.printArray(array, " ", map);
    }
 
    public <T> void printArray(T[] array, String separator) {
        int n = array.length;
        if (n == 0) {
            println();
            return;
        }
        for (int i = 0; i < n - 1; i++) {
            print(array[i]);
            print(separator);
        }
        println(array[n - 1]);
    }
 
    public <T> void printArray(T[] array) {
        this.printArray(array, " ");
    }
 
    public <T> void printArray(T[] array, String separator, java.util.function.UnaryOperator<T> map) {
        int n = array.length;
        if (n == 0) {
            println();
            return;
        }
        for (int i = 0; i < n - 1; i++) {
            print(map.apply(array[i]));
            print(separator);
        }
        println(map.apply(array[n - 1]));
    }
 
    public <T> void printArray(T[] array, java.util.function.UnaryOperator<T> map) {
        this.printArray(array, " ", map);
    }
}