import java.util.*;
import java.io.*;
import java.util.function.*;


public final class Main {
  private static final Scanner scanner;
  private static final PrintWriter writer;

  static {
    scanner = new Scanner(System.in);
    writer = new PrintWriter(System.out);
  }

  public static final void main(String[] args) {
    final long N = getNextLong();

    long X = Long.MAX_VALUE;
    for(long a = 0; a < 1000000; a++) {
      long l = a;
      long r = 1000000;
      if(func(a, l) > N) {
        break;
      } else if(func(a, l) == N) {
        r = l;
      }
      while(r - l > 1) {
        final long m = (r + l) / 2;
        if(func(a, m) < N) {
          l = m;
        } else {
          r = m;
        }
      }
      X = min(X, func(a, r));
    }

    println(X);
    flush();
  }

  private static final long func(long a, long b) {
    return a * a * a + a * a * b + a * b * b + b * b * b;
  }

  private static final int max(int a, int b) {
    return a < b ? b : a;
  }

  private static final int max(int... arg) {
    return Arrays.stream(arg).max().orElse(0);
  }

  private static final int min(int a, int b) {
    return a > b ? b : a;
  }

  private static final int min(int... arg) {
    return Arrays.stream(arg).min().orElse(0);
  }

  private static final int abs(int x) {
    return x < 0 ? -x : x;
  }

  private static final int pow(int x, int p) {
    return p <= 0 ? 1 : x * pow(x, p - 1);
  }

  private static final long max(long a, long b) {
    return a < b ? b : a;
  }

  private static final long max(long... arg) {
    return Arrays.stream(arg).max().orElse(0);
  }

  private static final long min(long a, long b) {
    return a > b ? b : a;
  }

  private static final long min(long... arg) {
    return Arrays.stream(arg).min().orElse(0);
  }

  private static final long abs(long x) {
    return x < 0L ? -x : x;
  }

  private static final long pow(long x, int p) {
    return p <= 0 ? 1L : x * pow(x, p - 1);
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

  private static final int[] getIntArray(int length) {
    int[] ret = new int[length];
    for(int i = 0; i < length; i++) {
      ret[i] = getNextInt();
    }
    return ret;
//  return IntStream.generate(()->getNextInt()).limit(length).toArray();
  }

  private static final int[] getIntArray(int length, IntUnaryOperator mapper) {
    int[] ret = new int[length];
    for(int i = 0; i < length; i++) {
      ret[i] = mapper.applyAsInt(getNextInt());
    }
    return ret;
//  return IntStream.generate(()->getNextInt()).limit(length).map(mapper).toArray();
  }

  private static final int[][] get2dIntArray(int rows, int cols) {
    int[][] ret = new int[rows][];
    for(int i = 0; i < rows; i++) {
      ret[i] = getIntArray(cols);
    }
    return ret;
//  return Stream.generate(()->getIntArray(cols)).limit(rows).toArray(int[][]::new);
  }

  private static final int[][] get2dIntArray(int rows, int cols, IntUnaryOperator mapper) {
    int[][] ret = new int[rows][];
    for(int i = 0; i < rows; i++) {
      ret[i] = getIntArray(cols, mapper);
    }
    return ret;
//  return Stream.generate(()->getIntArray(cols, mapper)).limit(rows).toArray(int[][]::new);
  }

  private static final void print(int[] argi) {
    for(int i = 0, length = argi.length; i < length; i++) {
      print(String.valueOf(argi[i]) + " ");
    }
//  Arrays.stream(argi).forEach(i->print(String.valueOf(i) + " "));
  }

  private static final void print(Object obj) {
    writer.print(obj);
  }

  private static final void print(Object... arg) {
    for(int i = 0, length = arg.length; i < length; i++) {
      print(arg[i]);
    }
//  Arrays.stream(arg).forEach(obj->print(obj));
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
