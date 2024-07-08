import java.util.*;
import java.io.*;
import java.util.function.*;
import java.util.stream.*;

@SuppressWarnings({ "unused" })
public final class Main {

    // @SuppressWarnings({"unchecked"})
  public static final void main(String[] args) {
    final IO io = new IO();
    final String T = io.getNext();
    final int TLen = T.length();
    final int[][] dp = new int[2][TLen + 1];
    Arrays.fill(dp[1], Integer.MAX_VALUE);
    dp[1][0] = 1;
    final int N = io.getNextInt();
    for(int bag = 0; bag < N; bag++) {
      Arrays.fill(dp[bag % 2], Integer.MAX_VALUE);
      final int A = io.getNextInt();
      for(int sIdx = 0; sIdx < A; sIdx++) {
        final String S = io.getNext();
        for(int tPos = 0; tPos < TLen; tPos++) {
          if(dp[(bag + 1) % 2][tPos] < Integer.MAX_VALUE) {
            dp[bag % 2][tPos] = Math.min(dp[(bag + 1) % 2][tPos], dp[bag % 2][tPos]);
            if(tPos + S.length() <= TLen && T.substring(tPos).startsWith(S)) {
              dp[bag % 2][tPos + S.length()] = Math.min(dp[bag % 2][tPos + S.length()], dp[(bag + 1) % 2][tPos] + 1);
            }
          }
        }
      }
    }
    io.println(dp[(N - 1) % 2][TLen] < Integer.MAX_VALUE ? dp[(N - 1) % 2][TLen] - 1 : -1);
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
