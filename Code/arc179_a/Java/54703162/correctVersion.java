import java.util.*;
import java.io.*;
import java.util.function.*;
import java.util.stream.*;

class Main {
  public static void main(String[] args) {new Main(args);}
  final IO io;
  Main(String[] args) {io = new IO(); execute(args); io.flush();}
    // @SuppressWarnings({"unchecked"})
  void execute(String[] args) {
    final int N = io.getNextInt();
    final int K = io.getNextInt();
    final int[] A = io.getIntArray(N);
    Arrays.sort(A);
    if(K <= 0) {
      if(Arrays.stream(A).asLongStream().sum() < K) {
        io.println("No");
        return;
      }
      for(int idx = 0; idx < A.length / 2; idx++) {
        int tmp = A[idx];
        A[idx] = A[A.length - idx - 1];
        A[A.length - idx - 1] = tmp;
      }
    }
    io.println("Yes");
    io.println(A);
  }
}



class IO {
  private BufferedReader reader;
  private PrintWriter writer;
  private String[] readBuffer;
  private int readBufferCursor;

  IO() {
    reader = new BufferedReader(new InputStreamReader(System.in));
    writer = new PrintWriter(System.out);
    readBuffer = new String[] {};
    readBufferCursor = 0;
    // ファイル入力用
    // try {
    // reader = new BufferedReader(new InputStreamReader(
    // new FileInputStream("test.txt")));
    // } catch (FileNotFoundException e) {
    // e.printStackTrace();
    // }
    // ファイル出力用
    // try {
    // writer = new PrintWriter(new File("test.txt"));
    // } catch (FileNotFoundException e) {
    // e.printStackTrace();
    // }
  }

  String getNextLine() {
    try {
      return reader.readLine();
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  String getNext() {
    if (readBuffer.length == readBufferCursor) {
      readBuffer = getNextLine().trim().split("\\s");
      readBufferCursor = 0;
    }
    String ret = readBuffer[readBufferCursor];
    readBuffer[readBufferCursor++] = null;
    return ret;
  }

  int getNextInt() {
    return Integer.parseInt(getNext());
  }

  long getNextLong() {
    return Long.parseLong(getNext());
  }

  double getNextDouble() {
    return Double.parseDouble(getNext());
  }

  int[] getIntArray(int length) {
    return getIntArray(length, v -> v);
  }

  int[] getIntArray(int length, IntUnaryOperator mapper) {
    return IntStream.generate(() -> getNextInt()).limit(length).map(mapper).toArray();
  }

  int[] getIntArray(int length, IntUnaryOperator mapper, int headOffset, int tailOffset) {
    return IntStream.concat(IntStream.generate(() -> 0).limit(headOffset),
           IntStream.concat(IntStream.generate(() -> getNextInt()).limit(length).map(mapper),
           IntStream.generate(() -> 0).limit(tailOffset))).toArray();
  }

  long[] getLongArray(int length) {
    return getLongArray(length, v -> v);
  }

  long[] getLongArray(int length, LongUnaryOperator mapper) {
    return LongStream.generate(() -> getNextLong()).limit(length).map(mapper).toArray();
  }

  long[] getLongArray(int length, LongUnaryOperator mapper, int headOffset, int tailOffset) {
    return LongStream.concat(LongStream.generate(() -> 0).limit(headOffset),
           LongStream.concat(LongStream.generate(() -> getNextInt()).limit(length).map(mapper),
           LongStream.generate(() -> 0).limit(tailOffset))).toArray();
  }

  double[] getDoubleArray(int length) {
    return DoubleStream.generate(() -> getNextDouble()).limit(length).toArray();
  }

  int[][] get2dIntArray(int rows, int cols) {
    return get2dIntArray(rows, cols, v -> v);
  }

  int[][] get2dIntArray(int rows, int cols, IntUnaryOperator mapper) {
    return Stream.generate(() -> getIntArray(cols, mapper)).limit(rows).toArray(int[][]::new);
  }

  long[][] get2dLongArray(int rows, int cols) {
    return get2dLongArray(rows, cols, v -> v);
  }

  long[][] get2dLongArray(int rows, int cols, LongUnaryOperator mapper) {
    return Stream.generate(() -> getLongArray(cols, mapper)).limit(rows).toArray(long[][]::new);
  }

  int[] getCharIntArray(IntUnaryOperator mapper) {
    return getNext().chars().map(mapper).toArray();
  }

  char[][] get2dCharArray(int rows) {
    return Stream.generate(() -> getNext().toCharArray()).limit(rows).toArray(char[][]::new);
  }

  int[][] get2dCharIntArray(int rows, IntUnaryOperator mapper) {
    return Stream.generate(() -> getNext().chars().map(mapper).toArray()).limit(rows).toArray(int[][]::new);
  }

  void print(int... ary) {
    for (int idx = 0; idx < ary.length; idx++) {
      print(ary[idx] + (idx < ary.length - 1 ? " " : ""));
    }
  }

  void print(long... ary) {
    for (int idx = 0; idx < ary.length; idx++) {
      print(ary[idx] + (idx < ary.length - 1 ? " " : ""));
    }
  }

  void print(char[] ary) {
    print(String.valueOf(ary));
  }

  void print(Collection<?> list) {
    for (Iterator<?> itr = list.iterator(); itr.hasNext();) {
      print(itr.next() + (itr.hasNext() ? " " : ""));
    }
  }

  void print(Object obj) {
    writer.print(obj);
  }

  void println(int... ary) {
    print(ary);
    println();
  }

  void println(int[][] arys) {
    Arrays.stream(arys).forEach(ary -> println(ary));
  }

  void println(long... ary) {
    print(ary);
    println();
  }

  void println(long[][] arys) {
    Arrays.stream(arys).forEach(ary -> println(ary));
  }

  void println(char[] ary) {
    print(ary);
    println();
  }

  void println(char[][] arys) {
    Arrays.stream(arys).forEach(ary -> println(ary));
  }

  void println(Collection<?> list) {
    print(list);
    println();
  }

  void println(Object obj) {
    print(obj);
    println();
  }

  void println() {
    writer.println();
    flush();
  }

  void printf(String format, Object... args) {
    print(String.format(format, args));
    flush();
  }

  void flush() {
    writer.flush();
  }
}
