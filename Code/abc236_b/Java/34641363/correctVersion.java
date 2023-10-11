import java.io.IOException;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.util.Scanner;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

class Main{
  long st = ManagementFactory.getRuntimeMXBean().getStartTime();
  boolean isDebug = ManagementFactory.getRuntimeMXBean().getInputArguments().toString().contains("-agentlib:jdwp");
  AbstractScanner sc = new FastScanner();
  PrintWriter pw = new PrintWriter(System.out);

  public static void main(final String[] args){ new Main().exe(); }

  void exe(){
    input();
    preCalc();
    solve();
    pw.flush();
  }

  int N = sc.it();
  int[] A = sc.it(4 *N -1);

  void input(){}

  void preCalc(){}

  void solve(){
    long ret = 2L *N *(N +1);
    for (var a:A)
      ret -= a;

    out(ret);

  }

  /* Util  */
  int min(int a,int b){ return Math.min(a,b); }

  long min(long a,long b){ return Math.min(a,b); }

  double min(double a,double b){ return Math.min(a,b); }

  int max(int a,int b){ return Math.max(a,b); }

  long max(long a,long b){ return Math.max(a,b); }

  double max(double a,double b){ return Math.max(a,b); }

  /* 出力 */
  void out(final Object ret){ pw.println(ret); }

  void out(final int[] ret){
    final StringBuilder sb = new StringBuilder();
    sb.append(ret[0]);
    for (int i = 1;i < ret.length;i++)
      sb.append(" " +ret[i]);

    pw.println(sb.toString());
  }

  void debug(final boolean b){
    if (b)
      info(null);
  }

  void info(final Object info){
    if (isDebug)
      System.out.println(info);
  }

  /* 入力 */
  static abstract class AbstractScanner{
    abstract long lg();

    long[] lg(final int N){ return IntStream.range(0,N).mapToLong(i -> lg()).toArray(); }

    long[][] lg(final int H,final int W){ return arr(new long[H][],i -> lg(W)); }

    double dbl(){ return Double.parseDouble(str()); }

    int it(){ return Math.toIntExact(lg()); }

    int[] it(final int N){ return IntStream.range(0,N).map(i -> it()).toArray(); }

    int[][] it(final int H,final int W){ return arr(new int[H][],i -> it(W)); }

    int idx(){ return it() -1; }

    int[] idx(final int N){ return IntStream.range(0,N).map(i -> idx()).toArray(); }

    int[][] idx(final int H,final int W){ return arr(new int[H][],i -> idx(W)); }

    abstract String str();

    char[] ch(){ return str().toCharArray(); }

    char[][] ch(final int H){ return arr(new char[H][],i -> ch()); }

    <T> T[] arr(final T[] arr,final IntFunction<T> func){
      return IntStream.range(0,arr.length).mapToObj(func).toArray(t -> arr);
    }
  }

  static class FastScanner extends AbstractScanner{
    int buflen = 1024;
    byte[] buf = new byte[buflen];
    int ptr = buflen -1;

    int readByte(){
      final int ret = buf[ptr++];
      ptr %= buflen;
      if (ptr == 0)
        try {
          System.in.read(buf);
        } catch (final IOException e) {
          e.printStackTrace();
        }
      return ret;
    }

    boolean isPrintableChar(final int c){ return 32 < c && c < 127; }

    boolean isNumeric(final int c){ return 47 < c && c < 58; }

    int nextPrintableChar(){
      int ret = readByte();
      while (!isPrintableChar(ret))
        ret = readByte();
      return ret;
    }

    @Override
    long lg(){
      final int s = nextPrintableChar();
      final boolean negative = s == 45;
      long ret = 0;
      for (int b = negative ? readByte() : s;isPrintableChar(b);b = readByte())
        if (isNumeric(b))
          ret = ret *10 +b -48;
        else
          throw new NumberFormatException();
      return negative ? -ret : ret;
    }

    @Override
    String str(){
      final StringBuilder ret = new StringBuilder();
      for (int b = nextPrintableChar();isPrintableChar(b);b = readByte())
        ret.appendCodePoint(b);

      return ret.toString();
    }

  }

  static class InteractiveScanner extends AbstractScanner{
    Scanner org = new Scanner(System.in);

    @Override
    long lg(){ return org.nextLong(); }

    @Override
    String str(){ return org.next(); }

  }
}
