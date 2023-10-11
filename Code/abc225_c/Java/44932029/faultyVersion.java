import java.util.*;
import java.io.*;
import java.util.function.*;
import java.util.stream.*;
import java.math.*;


public final class Main {
  private static Scanner scanner;
  private static PrintWriter writer;
  // private static final long PRIME = 1000000007;
  // private static final long PRIME = 998244353;

  static {
    scanner = new Scanner(System.in);
    writer = new PrintWriter(System.out);
  }

  public static final void main(String[] args) {
    final int N = getNextInt();
    final int M = getNextInt();
    final int[][] B = get2dIntArray(N, M);
    if(B[0][0] % 7  == 0 || B[0][0] % 7 > 8 - M) {
      println("No");
      flush();
      return;
    }
    for(int idx = 1; idx < M; idx++) {
      if(B[0][idx] != B[0][idx - 1] + 1) {
        println("No");
        flush();
        return;
      }
    }
    for(int hidx = 1; hidx < N; hidx++) {
      for(int widx = 0; widx < M; widx++) {
        if(B[hidx][widx] != B[hidx - 1][widx] + 7) {
          println("No");
          flush();
          return;
        }
      }
    }
    println("Yes");
    flush();
  }


  private static String getNext() {
    return scanner.next();
  }

  private static int[] getCharIntArray() {
    return getCharIntArray(v -> v);
  }

  private static int[] getCharIntArray(IntUnaryOperator mapper) {
    return getNext().chars().map(mapper).toArray();
  }

  private static char[][] get2dCharArray(int rows) {
    return Stream.generate(() -> getNext().toCharArray()).limit(rows).toArray(char[][]::new);
  }

  private static int[][] get2dCharIntArray(int rows) {
    return get2dCharIntArray(rows, v -> v);
  }

  private static int[][] get2dCharIntArray(int rows, IntUnaryOperator mapper) {
    return Stream.generate(() -> getNext().chars().map(mapper).toArray()).limit(rows).toArray(int[][]::new);
  }

  private static int getNextInt() {
    return Integer.parseInt(scanner.next());
  }

  private static long getNextLong() {
    return Long.parseLong(scanner.next());
  }

  private static double getNextDouble() {
    return Double.parseDouble(scanner.next());
  }

  private static int[] getIntArray(int length) {
    return getIntArray(length, v -> v);
  }

  private static int[] getIntArray(int length, IntUnaryOperator mapper) {
    return IntStream.generate(()->getNextInt()).limit(length).map(mapper).toArray();
  }

  private static List<Integer> getIntList(int length) {
    return getIntList(length, v -> v);
  }

  private static List<Integer> getIntList(int length, Function<Integer, Integer> mapper) {
    return Stream.generate(()->getNextInt()).limit(length).map(mapper).collect(Collectors.toCollection(ArrayList::new));
  }

  private static long[] getLongArray(int length) {
    return getLongArray(length, v -> v);
  }

  private static long[] getLongArray(int length, LongUnaryOperator mapper) {
    return LongStream.generate(()->getNextLong()).limit(length).map(mapper).toArray();
  }

  private static List<Long> getLongList(int length) {
    return getLongList(length, v -> v);
  }

  private static List<Long> getLongList(int length, Function<Long, Long> mapper) {
    return Stream.generate(()->getNextLong()).limit(length).map(mapper).collect(Collectors.toCollection(ArrayList::new));
  }

  private static int[][] get2dIntArray(int rows, int cols) {
    return get2dIntArray(rows, cols, v -> v);
  }

  private static int[][] get2dIntArray(int rows, int cols, IntUnaryOperator mapper) {
    return Stream.generate(()->getIntArray(cols, mapper)).limit(rows).toArray(int[][]::new);
  }

  private static long[][] get2dLongArray(int rows, int cols) {
    return get2dLongArray(rows, cols, v -> v);
  }

  private static long[][] get2dLongArray(int rows, int cols, LongUnaryOperator mapper) {
    return Stream.generate(()->getLongArray(cols, mapper)).limit(rows).toArray(long[][]::new);
  }

  private static void print(int[] argi) {
    Arrays.stream(argi).forEach(i->print(String.valueOf(i) + " "));
  }

  private static void print(Object obj) {
    writer.print(obj);
  }

  private static void print(Object... arg) {
    Arrays.stream(arg).forEach(obj->print(obj));
  }

  private static void println(int[] argi) {
    print(argi);
    println();
  }

  private static void println(Object obj) {
    print(obj);
    println();
  }

  private static void println(Object... arg) {
    print(arg);
    println();
  }

  private static void println() {
    writer.println();
  }

  private static void flush() {
    writer.flush();
  }
}
