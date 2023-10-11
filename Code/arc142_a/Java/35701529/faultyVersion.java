import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.management.ManagementFactory;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.IntFunction;

class Main{
  boolean isDebug = ManagementFactory.getRuntimeMXBean().getInputArguments().toString()
      .contains("-agentlib:jdwp");
  final MyReader in = new MyReader(System.in);
  final MyWriter out = new MyWriter(System.out);

  public static void main(final String[] args){ new Main().exe(); }

  private void exe(){
    input();
    preCalc();
    solve();
    out.flush();
  }

  long N = in.lg();
  long K = in.lg();

  private void input(){}

  private void preCalc(){}

  void solve(){
    if (K %10 == 0) {
      out.println(0);
      return;
    }

    Set<Long> set = new HashSet<>();
    long num = rev(K);

    while (num <= N) {
      set.add(num);
      num *= 10;
    }
    num = rev(rev(K));

    while (num <= N) {
      set.add(num);
      num *= 10;
    }

    out.println(set.size());
  }

  long rev(long x){
    long ret = 0;

    while (0 < x) {
      ret *= 10;
      ret += x %10;
      x /= 10;
    }
    return ret;
  }

  /* 定数 */
  final int mod = (int) 1e9 +7;
  final String yes = "Yes";
  final String no = "No";

  /* 入力 */
  static class MyReader{
    byte[] buf = new byte[1 <<16];
    int head = 0;
    int tail = 0;
    InputStream in;

    public MyReader(final InputStream in){ this.in = in; }

    byte read(){
      if (head == tail) {
        try {
          tail = in.read(buf);
        } catch (IOException e) {
          e.printStackTrace();
        }
        head = 0;
      }
      return buf[head++];
    }

    boolean isPrintable(final byte c){ return 32 < c && c < 127; }

    boolean isNum(final byte c){ return 47 < c && c < 58; }

    byte nextPrintable(){
      byte ret = read();
      return isPrintable(ret) ? ret : nextPrintable();
    }

    int it(){ return (int) lg(); }

    int[] it(final int N){
      int[] a = new int[N];
      Arrays.setAll(a,i -> it());
      return a;
    }

    int[][] it(final int H,final int W){ return arr(new int[H][],i -> it(W)); }

    int idx(){ return it() -1; }

    int[] idx(final int N){
      int[] a = new int[N];
      Arrays.setAll(a,i -> idx());
      return a;
    }

    int[][] idx(final int H,final int W){ return arr(new int[H][],i -> idx(W)); }

    long lg(){
      byte i = nextPrintable();
      boolean negative = i == 45;
      long n = negative ? 0 : i -'0';
      while (isPrintable(i = read()))
        n = 10 *n +i -'0';
      return negative ? -n : n;
    }

    long[] lg(final int N){
      long[] a = new long[N];
      Arrays.setAll(a,i -> lg());
      return a;
    }

    long[][] lg(final int H,final int W){ return arr(new long[H][],i -> lg(W)); }

    char[] ch(){ return str().toCharArray(); }

    char[][] ch(final int H){ return arr(new char[H][],i -> ch()); }

    String line(){
      StringBuilder sb = new StringBuilder();

      byte c;
      while (isPrintable(c = read()) || c == ' ')
        sb.append((char) c);
      return sb.toString();
    }

    String str(){
      StringBuilder sb = new StringBuilder();
      sb.append((char) nextPrintable());
      byte c;
      while (isPrintable(c = read()))
        sb.append((char) c);
      return sb.toString();
    }

    String[] str(final int N){ return arr(new String[N],i -> str()); }

    <T> T[] arr(final T[] arr,final IntFunction<T> f){
      Arrays.setAll(arr,f);
      return arr;
    }
  }

  /* 出力 */
  static class MyWriter{
    OutputStream out;

    byte[] buf = new byte[1 <<16];
    byte[] ibuf = new byte[20];

    int tail = 0;

    public MyWriter(final OutputStream out){ this.out = out; }

    void flush(){
      try {
        out.write(buf,0,tail);
        tail = 0;
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    void write(final byte b){
      buf[tail++] = b;
      if (tail == buf.length)
        flush();
    }

    void write(final byte[] b,final int off,final int len){
      for (int i = off;i < off +len;i++)
        write(b[i]);
    }

    void write(final char c){ write((byte) c); }

    void write(long n){
      if (n < 0) {
        n = -n;
        write('-');
      }

      int i = ibuf.length;
      do {
        ibuf[--i] = (byte) (n %10 +'0');
        n /= 10;
      } while (n > 0);

      write(ibuf,i,ibuf.length -i);
    }

    void println(final long n){
      write(n);
      write('\n');
    }

    public void println(final double d){ println(String.valueOf(d)); }

    void println(final String s){
      byte[] b = s.getBytes();
      for (byte bb:b)
        write(bb);
      write('\n');
    }

    public void println(final char[] s){
      for (char bb:s)
        write(bb);
      write('\n');
    }

    void println(final int[] a){
      for (int i = 0;i < a.length;i++) {
        if (0 < i)
          write(' ');
        write(a[i]);
      }
      write('\n');
    }

  }
}
