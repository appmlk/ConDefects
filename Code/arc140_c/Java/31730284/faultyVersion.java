import java.io.IOException;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

public class Main{
  long st = ManagementFactory.getRuntimeMXBean().getStartTime();
  boolean isDebug = ManagementFactory.getRuntimeMXBean().getInputArguments().toString().indexOf("-agentlib:jdwp") > 0;
  FastScanner sc = new FastScanner();
  PrintWriter pw = new PrintWriter(System.out);

  public static void main(final String[] args){ new Main().exe(); }

  void exe(){
    input();
    preCalc();
    solve();
    pw.flush();
    info(System.currentTimeMillis() -st);
  }

  int N = sc.it();
  int X = sc.it();

  void input(){}

  void preCalc(){}

  void solve(){

    Deque<Integer> que1 = new ArrayDeque<>();
    Deque<Integer> que2 = new ArrayDeque<>();

    for (int i = 1;i <= N /2;i++)
      que1.add(i);
    for (int i = N;i > N /2;i--)
      que2.add(i);
    que1.remove(X);
    que2.remove(X);

    while (que1.size() < que2.size())
      que1.add(que2.pollLast());

    int[] ret = createA(que1,que2);

    out(ret);
  }

  private int[] createA(Deque<Integer> que1,Deque<Integer> que2){
    int[] ret = new int[N];
    ret[0] = X;

    if (N /2 *2 != N) {
      for (int i = 1;i < N;i += 2)
        ret[i] = que1.pollLast();
      for (int i = 2;i < N;i += 2)
        ret[i] = que2.pollLast();
    } else {
      que2.add(que1.pollLast());
      for (int i = 2;i < N;i += 2)
        ret[i] = que1.pollLast();
      for (int i = 1;i < N;i += 2)
        ret[i] = que2.pollLast();
    }

    return ret;
  }

  /* 出力 */
  void out(final Object ret){ pw.println(ret); }

  void out(final int[] ret){
    final StringBuilder sb = new StringBuilder();
    sb.append(ret[0]);
    for (int i = 1;i < ret.length;i++)
      sb.append(" " +ret[i]);

    pw.println(sb.toString());
  }

  void debug(final boolean info){
    if (isDebug && info)
      System.out.println();
  }

  void info(final Object info){
    if (isDebug)
      System.out.println(info);
  }

  /* 入力 */
  static class FastScanner{
    final byte[] buf = new byte[1024];
    int ptr = 0;
    int buflen = 0;

    boolean hasNextByte(){
      if (ptr >= buflen) {
        ptr = 0;
        try {
          buflen = System.in.read(buf);
        } catch (final IOException e) {
          e.printStackTrace();
        }
        if (buflen <= 0)
          return false;
      }
      return true;
    }

    int readByte(){
      if (hasNextByte())
        return buf[ptr++];
      else
        return -1;
    }

    boolean isPrintableChar(final int c){ return 33 <= c && c <= 126; }

    boolean isNumeric(final int c){ return '0' <= c && c <= '9'; }

    void skipToNextPrintableChar(){
      while (hasNextByte() && !isPrintableChar(buf[ptr]))
        ptr++;
    }

    boolean hasNext(){
      skipToNextPrintableChar();
      return hasNextByte();
    }

    long lg(){
      if (!hasNext())
        throw new NoSuchElementException();
      long ret = 0;
      int b = readByte();
      boolean negative = false;
      if (b == '-') {
        negative = true;
        if (hasNextByte())
          b = readByte();
      }
      if (!isNumeric(b))
        throw new NumberFormatException();
      while (true) {
        if (isNumeric(b))
          ret = ret *10 +b -'0';
        else if (b == -1 || !isPrintableChar(b))
          return negative ? -ret : ret;
        else
          throw new NumberFormatException();
        b = readByte();
      }
    }

    long[] lg(final int N){ return IntStream.range(0,N).mapToLong(i -> lg()).toArray(); }

    double dbl(){ return Double.parseDouble(str()); }

    int it(){ return Math.toIntExact(lg()); }

    int[] it(final int N){ return IntStream.range(0,N).map(i -> it()).toArray(); }

    int[][] it(final int H,final int W){
      final int[][] ret = new int[H][];
      Arrays.setAll(ret,i -> it(W));
      return ret;
    }

    int idx(){ return it() -1; }

    int[] idx(final int N){ return IntStream.range(0,N).map(i -> idx()).toArray(); }

    String str(){
      if (!hasNext())
        throw new NoSuchElementException();
      final StringBuilder ret = new StringBuilder();
      int b = readByte();
      while (isPrintableChar(b)) {
        ret.appendCodePoint(b);
        b = readByte();
      }
      return ret.toString();
    }

    char[] ch(){ return str().toCharArray(); }

    public char[][] ch(final int H){
      final char[][] ret = new char[H][];
      Arrays.setAll(ret,i -> ch());
      return ret;
    }
  }
}
