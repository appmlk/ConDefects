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
    final int W = getNextInt();
    final int H = getNextInt();
    final int N = getNextInt();
    final int[][] PQ = get2dIntArray(N, 2);
    final int NA = getNextInt();
    final int[] A = new int[NA + 2];
    for(int aIdx = 1; aIdx <= NA; aIdx++) {
      A[aIdx] = getNextInt();
    }
    A[NA + 1] = W;
    final int NB = getNextInt();
    final int[] B = new int[NB + 2];
    for(int bIdx = 1; bIdx <= NB; bIdx++) {
      B[bIdx] = getNextInt();
    }
    B[NB + 1] = H;

    int[][] berryPos = new int[N][2];
    for(int i = 0; i < N; i++) {
      int l = 0;
      int r = NA + 1;
      while(r - l > 1) {
        int m = (r + l) / 2;
        if(A[m] > PQ[i][0]) {
          r = m;
        } else {
          l = m;
        }
      }
      berryPos[i][0] = l;
      l = 0;
      r = NB + 1;
      while(r - l > 1) {
        int m = (r + l) / 2;
        if(B[m] > PQ[i][1]) {
          r = m;
        } else {
          l = m;
        }
      }
      berryPos[i][1] = l;
    }

    Map<Integer, Map<Integer, Integer>> berryCount = new HashMap<>();
    for(int i = 0; i < N; i++) {
      berryCount.merge(berryPos[i][0], new HashMap<>(), (o, n) -> o).merge(berryPos[i][1], 1, (o, n) -> o + 1);
    }

    long entryCount = 0;
    int berryMax = 0;
    int berryMin = Integer.MAX_VALUE;
    for(Map<Integer, Integer> e: berryCount.values()) {
      for(int berry: e.values()) {
        entryCount++;
        berryMax = max(berryMax, berry);
        berryMin = min(berryMin, berry);
      }
    }
    if(entryCount < (long)(NA + 1) * (NB + 1)) {
      berryMin = 0;
    }
    println(berryMin + " " + berryMax);
    flush();
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
