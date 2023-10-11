import java.util.*;
import java.io.*;
import java.util.function.*;
import java.util.stream.*;
// import java.math.*;


public final class Main {

  public static final void main(String[] args) {
    final int N = getNextInt();
    final int K = getNextInt();
    final int X = getNextInt();
    final int[] A = getIntArray(N);
    final int[] remainA = new int[N];

    int remainCoupon = K;
    for(int aIdx = 0; aIdx < N; aIdx++) {
      int maxUseCoupon = A[aIdx] / X;
      remainA[aIdx] = A[aIdx] - Math.min(remainCoupon, maxUseCoupon) * X;
      remainCoupon -= Math.min(remainCoupon, maxUseCoupon);
    }
    long sum = 0;
    if(remainCoupon > 0) {
      Arrays.sort(remainA);
      sum = Arrays.stream(remainA).limit(Math.max(0, N - remainCoupon)).mapToLong(i -> i).sum();
    } else {
      sum = Arrays.stream(remainA).mapToLong(i -> i).sum();
    }
    println(sum);
    flush();
  }


  // final int[][] dir = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
  // final BiPredicate<Integer, Integer> isInside = (h, w) -> h >= 0 && h < H && w >= 0 && w < W;
  // String.format("%.15f", d);

  private static Scanner scanner;
  private static PrintWriter writer;
  // private static final long PRIME = 1000000007;
  // private static final long PRIME = 998244353;

  static {
    scanner = new Scanner(System.in);
    writer = new PrintWriter(System.out);
  }

  private static int[] concat(int[] ary1, int[] ary2) {
    final int[] ret = Arrays.copyOf(ary1, ary1.length + ary2.length);
    for(int idx = 0; idx < ary2.length; idx++) {
      ret[ary1.length + idx] = ary2[idx];
    }
    return ret;
  }

  private static <E> List<E> concat(List<E> l1, List<E> l2) {
    return Stream.concat(l1.stream(), l2.stream()).collect(Collectors.toList());
  }

  private static String getNextLine() {
    return scanner.nextLine();
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
    return IntStream.generate(() -> getNextInt()).limit(length).map(mapper).toArray();
  }

  private static List<Integer> getIntList(int length) {
    return getIntList(length, v -> v);
  }

  private static List<Integer> getIntList(int length, Function<Integer, Integer> mapper) {
    return Stream.generate(() -> getNextInt()).limit(length).map(mapper)
        .collect(Collectors.toCollection(ArrayList::new));
  }

  private static long[] getLongArray(int length) {
    return getLongArray(length, v -> v);
  }

  private static long[] getLongArray(int length, LongUnaryOperator mapper) {
    return LongStream.generate(() -> getNextLong()).limit(length).map(mapper).toArray();
  }

  private static List<Long> getLongList(int length) {
    return getLongList(length, v -> v);
  }

  private static List<Long> getLongList(int length, Function<Long, Long> mapper) {
    return Stream.generate(() -> getNextLong()).limit(length).map(mapper)
        .collect(Collectors.toCollection(ArrayList::new));
  }

  private static int[][] get2dIntArray(int rows, int cols) {
    return get2dIntArray(rows, cols, v -> v);
  }

  private static int[][] get2dIntArray(int rows, int cols, IntUnaryOperator mapper) {
    return Stream.generate(() -> getIntArray(cols, mapper)).limit(rows).toArray(int[][]::new);
  }

  private static List<List<Integer>> get2dIntList(int rows, int cols) {
    return get2dIntList(rows, cols, v -> v);
  }

  private static List<List<Integer>> get2dIntList(int rows, int cols, Function<Integer, Integer> mapper) {
    return Stream.generate(() -> getIntList(cols, mapper)).limit(rows).collect(Collectors.toCollection(ArrayList::new));
  }

  private static long[][] get2dLongArray(int rows, int cols) {
    return get2dLongArray(rows, cols, v -> v);
  }

  private static long[][] get2dLongArray(int rows, int cols, LongUnaryOperator mapper) {
    return Stream.generate(() -> getLongArray(cols, mapper)).limit(rows).toArray(long[][]::new);
  }

  private static List<List<Long>> get2dLongList(int rows, int cols) {
    return get2dLongList(rows, cols, v -> v);
  }

  private static List<List<Long>> get2dLongList(int rows, int cols, Function<Long, Long> mapper) {
    return Stream.generate(() -> getLongList(cols, mapper)).limit(rows).collect(Collectors.toCollection(ArrayList::new));
  }

  private static void print(int[] argi) {
    Arrays.stream(argi).forEach(i -> print(String.valueOf(i) + " "));
  }

  private static void print(long[] argl) {
    Arrays.stream(argl).forEach(l -> print(String.valueOf(l) + " "));
  }

  private static void print(char[] argc) {
    print(String.valueOf(argc));
  }

  private static void print(Collection list) {
    list.stream().forEach(e -> print(e.toString() + " "));
  }

  private static void print(Object obj) {
    writer.print(obj);
  }

  private static void print(Object... arg) {
    Arrays.stream(arg).forEach(obj -> print(obj));
  }

  private static void println(int[] argi) {
    print(argi);
    println();
  }

  private static void println(long[] argl) {
    print(argl);
    println();
  }

  private static void println(char[] argc) {
    print(argc);
    println();
  }

  private static void println(char[][] cmap) {
    Arrays.stream(cmap).forEach(line -> println(line));
  }

  private static void println(Collection list) {
    print(list);
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
