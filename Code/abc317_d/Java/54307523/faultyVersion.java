import java.util.*;
import java.io.*;
import java.util.function.*;
import java.util.stream.*;

@SuppressWarnings({ "unused" })
public final class Main {

  // @SuppressWarnings({"unchecked"})
  public static final void main(String[] args) {
    final IO io = new IO();
    final int N = io.getNextInt();
    final int[][] XYZs = io.get2dIntArray(N, 3);
    final int Zsum = Arrays.stream(XYZs).mapToInt(xyz -> xyz[2]).sum();
    final long[][] dp = new long[2][Zsum + 1];
    final long maxDp = 10000000000L;
    Arrays.fill(dp[0], maxDp);
    dp[0][0] = 0;
    for (int section = 0; section < N; section++) {
      dp[(section + 1) % 2] = new long[Zsum + 1];
      for (int seats = 0; seats <= Zsum; seats++) {
        dp[(section + 1) % 2][seats] = dp[section % 2][seats];
        if (XYZs[section][2] <= seats && dp[section % 2][seats - XYZs[section][2]] < maxDp) {
          dp[(section + 1) % 2][seats] = Math.min(dp[(section + 1) % 2][seats],
              dp[section % 2][seats - XYZs[section][2]] + Math.max(0, (int)(((long)XYZs[section][1] - XYZs[section][0]) / 2 + 1)));
        }
      }
    }
    final int Zborder = Zsum / 2 + 1;
    io.println(Arrays.stream(dp[N % 2]).skip(Zborder).min().getAsLong());
    io.flush();
  }

  private static final class IO {
    private final BufferedReader reader;
    private final PrintWriter writer;
    private String[] readBuffer;
    private int readBufferCursor;

    private IO() {
      reader = new BufferedReader(new InputStreamReader(System.in));
      writer = new PrintWriter(System.out);
      readBuffer = new String[] {};
      readBufferCursor = 0;
      // try {
      // reader = new BufferedReader(new InputStreamReader(new
      // FileInputStream("test.txt")));
      // } catch (FileNotFoundException e) {
      // e.printStackTrace();
      // }
    }

    private String getNextLine() {
      try {
        return reader.readLine();
      } catch (IOException e) {
        e.printStackTrace();
        return null;
      }
    }

    private String getNext() {
      // return scanner.next();
      if (readBuffer.length == readBufferCursor) {
        readBuffer = getNextLine().trim().split("\\s");
        readBufferCursor = 0;
      }
      return readBuffer[readBufferCursor++];
    }

    private int[] getCharIntArray() {
      return getCharIntArray(v -> v);
    }

    private int[] getCharIntArray(IntUnaryOperator mapper) {
      return getNext().chars().map(mapper).toArray();
    }

    private char[][] get2dCharArray(int rows) {
      return Stream.generate(() -> getNext().toCharArray()).limit(rows).toArray(char[][]::new);
    }

    private char[][] get2dCharArrayWithBorder(int rows, int cols, char borderChar) {
      Stream.Builder<char[]> sb = Stream.builder();
      sb.add(Character.toString(borderChar).repeat(cols + 2).toCharArray());
      for (int idx = 0; idx < rows; idx++) {
        sb.add((Character.toString(borderChar) + getNext() + Character.toString(borderChar)).toCharArray());
      }
      sb.add(Character.toString(borderChar).repeat(cols + 2).toCharArray());
      return sb.build().toArray(char[][]::new);
    }

    private int[][] get2dCharIntArray(int rows) {
      return get2dCharIntArray(rows, v -> v);
    }

    private int[][] get2dCharIntArray(int rows, IntUnaryOperator mapper) {
      return Stream.generate(() -> getNext().chars().map(mapper).toArray()).limit(rows).toArray(int[][]::new);
    }

    private int getNextInt() {
      return Integer.parseInt(getNext());
    }

    private long getNextLong() {
      return Long.parseLong(getNext());
    }

    private double getNextDouble() {
      return Double.parseDouble(getNext());
    }

    private int[] getIntArray(int length) {
      return getIntArray(length, v -> v);
    }

    private int[] getIntArray(int length, IntUnaryOperator mapper) {
      return IntStream.generate(() -> getNextInt()).limit(length).map(mapper).toArray();
    }

    private List<Integer> getIntList(int length) {
      return getIntList(length, v -> v);
    }

    private List<Integer> getIntList(int length, Function<Integer, Integer> mapper) {
      return Stream.generate(() -> getNextInt()).limit(length).map(mapper)
          .collect(Collectors.toCollection(ArrayList::new));
    }

    private long[] getLongArray(int length) {
      return getLongArray(length, v -> v);
    }

    private long[] getLongArray(int length, LongUnaryOperator mapper) {
      return LongStream.generate(() -> getNextLong()).limit(length).map(mapper).toArray();
    }

    private List<Long> getLongList(int length) {
      return getLongList(length, v -> v);
    }

    private List<Long> getLongList(int length, Function<Long, Long> mapper) {
      return Stream.generate(() -> getNextLong()).limit(length).map(mapper)
          .collect(Collectors.toCollection(ArrayList::new));
    }

    private double[] getDoubleArray(int length) {
      return DoubleStream.generate(() -> getNextDouble()).limit(length).toArray();
    }

    private int[][] get2dIntArray(int rows, int cols) {
      return get2dIntArray(rows, cols, v -> v);
    }

    private int[][] get2dIntArray(int rows, int cols, IntUnaryOperator mapper) {
      return Stream.generate(() -> getIntArray(cols, mapper)).limit(rows).toArray(int[][]::new);
    }

    private List<List<Integer>> get2dIntList(int rows, int cols) {
      return get2dIntList(rows, cols, v -> v);
    }

    private List<List<Integer>> get2dIntList(int rows, int cols, Function<Integer, Integer> mapper) {
      return Stream.generate(() -> getIntList(cols, mapper)).limit(rows)
          .collect(Collectors.toCollection(ArrayList::new));
    }

    private long[][] get2dLongArray(int rows, int cols) {
      return get2dLongArray(rows, cols, v -> v);
    }

    private long[][] get2dLongArray(int rows, int cols, LongUnaryOperator mapper) {
      return Stream.generate(() -> getLongArray(cols, mapper)).limit(rows).toArray(long[][]::new);
    }

    private List<List<Long>> get2dLongList(int rows, int cols) {
      return get2dLongList(rows, cols, v -> v);
    }

    private List<List<Long>> get2dLongList(int rows, int cols, Function<Long, Long> mapper) {
      return Stream.generate(() -> getLongList(cols, mapper)).limit(rows)
          .collect(Collectors.toCollection(ArrayList::new));
    }

    private void print(int... ary) {
      for (int idx = 0; idx < ary.length; idx++) {
        print(ary[idx] + (idx < ary.length - 1 ? " " : ""));
      }
    }

    private void print(long... ary) {
      for (int idx = 0; idx < ary.length; idx++) {
        print(ary[idx] + (idx < ary.length - 1 ? " " : ""));
      }
    }

    private void print(char[] ary) {
      print(String.valueOf(ary));
    }

    private void print(Collection<?> list) {
      for (Iterator<?> itr = list.iterator(); itr.hasNext();) {
        print(itr.next() + (itr.hasNext() ? " " : ""));
      }
    }

    private void print(Object obj) {
      writer.print(obj);
    }

    private void println(int... ary) {
      print(ary);
      println();
    }

    private void println(int[][] arys) {
      Arrays.stream(arys).forEach(ary -> println(ary));
    }

    private void println(long... ary) {
      print(ary);
      println();
    }

    private void println(long[][] arys) {
      Arrays.stream(arys).forEach(ary -> println(ary));
    }

    private void println(char[] ary) {
      print(ary);
      println();
    }

    private void println(char[][] arys) {
      Arrays.stream(arys).forEach(ary -> println(ary));
    }

    private void println(Collection<?> list) {
      print(list);
      println();
    }

    private void println(Object obj) {
      print(obj);
      println();
    }

    private void println() {
      writer.println();
    }

    private void printf(String format, Object... args) {
      print(String.format(format, args));
    }

    private void flush() {
      writer.flush();
    }
  }
}
