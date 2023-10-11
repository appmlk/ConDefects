import java.util.*;
import java.io.*;
import java.util.function.*;
import java.util.stream.*;

public final class Main {
  private static final Scanner scanner;
  private static final PrintWriter writer;

  static {
    scanner = new Scanner(System.in);
    writer = new PrintWriter(System.out);
  }

  public static final void main(String[] args) {
    final long PRIME = 998244353;
    List<Integer> sary = new ArrayList<>();
    sary.add(1);
    long s = 1;
    int headIdx = 0;
    int colCount = 1;

    final int Q = getNextInt();
    for (int qidx = 0; qidx < Q; qidx++) {
      switch (getNextInt()) {
        case 1: {
          final int x = getNextInt();
          sary.add(x);
          s *= 10;
          s += x;
          s %= PRIME;
          colCount++;
        }
          break;
        case 2:{
          long sub = pow(10, colCount - 1, PRIME) * sary.get(headIdx++) % PRIME;
          s -= sub;
          if(s < 0) {
            s += PRIME;
          }
        }
          break;
        case 3:{
          println(s);
        }
          break;
        default:
      }
    }
    flush();
  }

  private static final int bitCount(int pattern, int stopBit) {
    int ret = 0;
    for (int i = 0; 1 << i < stopBit; i++) {
      if ((pattern & (1 << i)) != 0) {
        ret++;
      }
    }
    return ret;
  }

  private static final int max(int... arg) {
    return Arrays.stream(arg).max().orElse(0);
  }

  private static final int min(int... arg) {
    return Arrays.stream(arg).min().orElse(0);
  }

  private static final int pow(int x, int p) {
    int r = 1;
    for (int i = 0; (1 << i) <= p; i++) {
      if ((p & (1 << i)) != 0) {
        r *= x;
      }
      x *= x;
    }
    return r;
  }

  private static final long max(long... arg) {
    return Arrays.stream(arg).max().orElse(0);
  }

  private static final long min(long... arg) {
    return Arrays.stream(arg).min().orElse(0);
  }

  private static final long pow(long x, int p) {
    long r = 1;
    for (int i = 0; (1 << i) <= p; i++) {
      if ((p & (1 << i)) != 0) {
        r *= x;
      }
      x *= x;
    }
    return r;
  }

  private static final long pow(long x, int p, long div) {
    long r = 1;
    for (int i = 0; (1 << i) <= p; i++) {
      if ((p & (1 << i)) != 0) {
        r *= x;
        r %= div;
      }
      x *= x;
      x %= div;
    }
    return r;
  }

  private static final short max(short a, short b) {
    return a < b ? b : a;
  }

  private static final short min(short a, short b) {
    return a > b ? b : a;
  }

  private static final String getNext() {
    return scanner.next();
  }

  private static final int getNextInt() {
    return Integer.parseInt(scanner.next());
  }

  private static final long getNextLong() {
    return Long.parseLong(scanner.next());
  }

  private static final double getNextDouble() {
    return Double.parseDouble(scanner.next());
  }

  private static final int[] getIntArray(int length) {
    return getIntArray(length, v -> v);
  }

  private static final int[] getIntArray(int length, IntUnaryOperator mapper) {
    return IntStream.generate(() -> getNextInt()).limit(length).map(mapper).toArray();
  }

  private static final long[] getLongArray(int length) {
    return getLongArray(length, v -> v);
  }

  private static final long[] getLongArray(int length, LongUnaryOperator mapper) {
    return LongStream.generate(() -> getNextLong()).limit(length).map(mapper).toArray();
  }

  private static final int[][] get2dIntArray(int rows, int cols) {
    return get2dIntArray(rows, cols, v -> v);
  }

  private static final int[][] get2dIntArray(int rows, int cols, IntUnaryOperator mapper) {
    return Stream.generate(() -> getIntArray(cols, mapper)).limit(rows).toArray(int[][]::new);
  }

  private static final long[][] get2dLongArray(int rows, int cols) {
    return get2dLongArray(rows, cols, v -> v);
  }

  private static final long[][] get2dLongArray(int rows, int cols, LongUnaryOperator mapper) {
    return Stream.generate(() -> getLongArray(cols, mapper)).limit(rows).toArray(long[][]::new);
  }

  private static final void print(int[] argi) {
    Arrays.stream(argi).forEach(i -> print(String.valueOf(i) + " "));
  }

  private static final void print(Object obj) {
    writer.print(obj);
  }

  private static final void print(Object... arg) {
    Arrays.stream(arg).forEach(obj -> print(obj));
  }

  private static final void println(int[] argi) {
    print(argi);
    println();
  }

  private static final void println(Object obj) {
    print(obj);
    println();
  }

  private static final void println(Object... arg) {
    print(arg);
    println();
  }

  private static final void println() {
    writer.println();
  }

  private static final void flush() {
    writer.flush();
  }
}
