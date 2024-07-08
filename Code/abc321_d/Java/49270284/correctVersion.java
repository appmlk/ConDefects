import java.util.*;
import java.io.*;
import java.util.function.*;
import java.util.stream.*;


@SuppressWarnings({"unused"})
public final class Main {

    // @SuppressWarnings({"unchecked"})
  public static final void main(String[] args) {
    final int N = getNextInt();
    final int M = getNextInt();
    final long P = getNextLong();
    final long[] A = getLongArray(N);
    final long[] B = getLongArray(M);
    Arrays.sort(B);
    final CumulativeSum cumulB = new CumulativeSum(B);

    long sumPrice = 0;
    for(int aIdx = 0; aIdx < N; aIdx++) {
      final long maxB = Math.max(0, P - A[aIdx]);
      final int bIdx = binSearch(B, maxB, (aryData, target) -> aryData >= maxB);
      if(bIdx >= 0) {
        sumPrice += A[aIdx] * (bIdx + 1) + cumulB.getSum(0, bIdx + 1);
      }
      if(bIdx < M - 1) {
        sumPrice += P * (M - bIdx - 1);
      }
    }
    println(sumPrice);
    flush();
  }

  //  二分探索、predicatorのfalseとtrueの境界線がlとr
  static int binSearch(long[] array, long target, BiPredicate<Long, Long> predicator) {
    int l = -1;
    int r = array.length;
    while(r - l > 1) {
      final int m = (r + l) / 2;
      if(predicator.test(array[m], target)) {
        r = m;
      } else {
        l = m;
      }
    }
    return l;
  }
  private static class CumulativeSum {
    long[] data;
    int size;

    CumulativeSum(long[] source) {
      size = source.length;
      data = new long[size];
      for(int idx = 0; idx < size; idx++) {
        data[idx] = (idx > 0 ? data[idx - 1] : 0) + source[idx];
      }
    }

    long getSum(int st, int ed) {
      return data[ed - 1] - (st > 0 ? data[st - 1] : 0);
    }
  }



//  以下Utility

  private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  private static String[] readBuffer = new String[] {};
  private static int readBufferCursor = 0;
  private static PrintWriter writer = new PrintWriter(System.out);

  //  private static BufferedReader reader;
  //  static {
  //   try {
  //     reader = new BufferedReader(new InputStreamReader(new FileInputStream("test.txt")));
  //   } catch (FileNotFoundException e) {
  //     e.printStackTrace();
  //   }
  //  }


  private static int[] concat(int[]... arys) {
    IntStream st = Arrays.stream(arys[0]);
    for(int idx = 1; idx < arys.length; idx++) {
      st = IntStream.concat(st, Arrays.stream(arys[idx]));
    }
    return st.toArray();
  }

  @SafeVarargs
  private static <E> List<E> concat(List<E>... lists) {
    Stream<E> st = lists[0].stream();
    for(int idx = 1; idx < lists.length; idx++) {
      st = Stream.concat(st, lists[idx].stream());
    }
    return st.collect(Collectors.toList());
  }

  private static String getNextLine() {
    try {
      return reader.readLine();
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  private static String getNext() {
    // return scanner.next();
    if(readBuffer.length == readBufferCursor) {
      readBuffer = getNextLine().trim().split("\\s");
      readBufferCursor = 0;
    }
    return readBuffer[readBufferCursor++];
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
    return Integer.parseInt(getNext());
  }

  private static long getNextLong() {
    return Long.parseLong(getNext());
  }

  private static double getNextDouble() {
    return Double.parseDouble(getNext());
  }

  private static int[] getIntArray(int length) {
    return getIntArray(length, v -> v);
  }

  private static int[] getIntArray(int length, IntUnaryOperator mapper) {
    return IntStream.generate(() -> getNextInt()).limit(length).map(mapper).toArray();
  }

  private static int[][] getIntArrayWithSeq(int length) {
    return getIntArrayWithSeq(length, v -> v);
  }

  private static int[][] getIntArrayWithSeq(int length, IntUnaryOperator mapper) {
    int[][] array = new int[length][2];
    for(int counter = 0; counter < length; counter++) {
      array[counter][0] = counter;
      array[counter][1] = mapper.applyAsInt(getNextInt());
    }
    return array;
  }

  private static int getBitLineInt() {
    final int[] line = getCharIntArray(c -> c - '0');
    int result = 0;
    for(int pos = 0; pos < line.length; pos++) {
      result <<= 1;
      result |= line[pos];
    }
    return result;
  }

  private static int[] getBitLineIntAry(int length) {
    final int[] bitAry = new int[length];
    for(int idx = 0; idx < length; idx++) {
      bitAry[idx] = getBitLineInt();
    }
    return bitAry;
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

  private static double[] getDoubleArray(int length) {
    return DoubleStream.generate(() -> getNextDouble()).limit(length).toArray();
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

  private static void print(int... ary) {
    for(int idx = 0; idx < ary.length; idx++) {
      print(ary[idx] + (idx < ary.length - 1 ? " " : ""));
    }
  }

  private static void print(long... ary) {
    for(int idx = 0; idx < ary.length; idx++) {
      print(ary[idx] + (idx < ary.length - 1 ? " " : ""));
    }
  }

  private static void print(char[] ary) {
    print(String.valueOf(ary));
  }

  private static void print(Collection<?> list) {
    for(Iterator<?> itr = list.iterator(); itr.hasNext();) {
      print(itr.next() + (itr.hasNext() ? " " : ""));
    }
  }

  private static void print(Object obj) {
    writer.print(obj);
  }

  private static void println(int... ary) {
    print(ary);
    println();
  }

  private static void println(long... ary) {
    print(ary);
    println();
  }

  private static void println(char[] ary) {
    print(ary);
    println();
  }

  private static void println(char[][] cmap) {
    Arrays.stream(cmap).forEach(line -> println(line));
  }

  private static void println(Collection<?> list) {
    print(list);
    println();
  }

  private static void println(Object obj) {
    print(obj);
    println();
  }

  private static void println() {
    writer.println();
  }

  private static void flush() {
    writer.flush();
  }
}

