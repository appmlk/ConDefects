import java.io.IOException;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.util.NoSuchElementException;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

public class Main{
  long st = ManagementFactory.getRuntimeMXBean().getStartTime();
  boolean isDebug = ManagementFactory.getRuntimeMXBean().getInputArguments().toString().indexOf("-agentlib:jdwp") > 0;
  FastScanner sc = new FastScanner();
  PrintWriter pw = new PrintWriter(System.out);

  public static void main(String[] args){ new Main().exe(); }

  void exe(){
    input();
    preCalc();
    solve();
    pw.flush();
    info(System.currentTimeMillis() -st);
  }

  int N = sc.it();

  void input(){}

  void preCalc(){}

  void solve(){ out(N < 3 || N==1 ? "No" : "Yes"); }

  /* 出力 */
  void out(Object ret){ pw.println(ret); }

  void out(int[] ret){
    StringBuilder sb = new StringBuilder();
    sb.append(ret[0]);
    for (int i = 1;i < ret.length;i++)
      sb.append(" " +ret[i]);

    pw.println(sb.toString());
  }

  void debug(boolean info){
    if (isDebug && info)
      System.out.println();
  }

  void info(Object info){
    if (isDebug)
      System.out.println(info);
  }

  /* 入力 */
  static class FastScanner{
    byte[] buf = new byte[1024];
    int ptr = 0;
    int buflen = 0;

    boolean hasNextByte(){
      if (ptr >= buflen) {
        ptr = 0;
        try {
          buflen = System.in.read(buf);
        } catch (IOException e) {
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

    boolean isPrintableChar(int c){ return 33 <= c && c <= 126; }

    boolean isNumeric(int c){ return '0' <= c && c <= '9'; }

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

    long[] lg(int N){ return IntStream.range(0,N).mapToLong(i -> lg()).toArray(); }

    long[][] lg(int H,int W){ return arr(new long[H][],i -> lg(W)); }

    double dbl(){ return Double.parseDouble(str()); }

    int it(){ return Math.toIntExact(lg()); }

    int[] it(int N){ return IntStream.range(0,N).map(i -> it()).toArray(); }

    int[][] it(int H,int W){ return arr(new int[H][],i -> it(W)); }

    int idx(){ return it() -1; }

    int[] idx(int N){ return IntStream.range(0,N).map(i -> idx()).toArray(); }

    String str(){
      if (!hasNext())
        throw new NoSuchElementException();
      StringBuilder ret = new StringBuilder();
      int b = readByte();
      while (isPrintableChar(b)) {
        ret.appendCodePoint(b);
        b = readByte();
      }
      return ret.toString();
    }

    char[] ch(){ return str().toCharArray(); }

    char[][] ch(int H){ return arr(new char[H][],i -> ch()); }

    <T> T[] arr(T[] arr,IntFunction<T> func){ return IntStream.range(0,arr.length).mapToObj(func).toArray(t -> arr); }
  }
}
