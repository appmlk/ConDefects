import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.function.IntFunction;
import java.util.function.Predicate;

public class Main{
  long st = System.currentTimeMillis();

  long elapsed(){ return System.currentTimeMillis() -st; }

  final MyReader in = new MyReader(System.in);
  final MyWriter out = new MyWriter(System.out);
  final MyWriter log = new MyWriter(System.err){
    @Override
    void ln(){
      super.ln();
      flush();
    };
  };

  public static void main(final String[] args){ new Main().exe(); }

  private void exe(){
    input();
    preCalc();
    //    solve();
    out.println(solve());
    out.flush();
    log.println(elapsed());
  }

  int N = in.it();
  long[][] J = in.lg(N,3);

  private void input(){
    for (int i = 0;i < N;i++) {
      J[i][0]--;
      J[i][1]--;
    }
  }

  private void preCalc(){}

  long solve(){
    return bSearch(infI *10L,0,s -> {

      int[][] len = new int[N][N];
      for (var le:len)
        Arrays.fill(le,infI);
      for (int i = 0;i < N;i++)
        for (int j = 0;j < N;j++)
          if (J[i][2] *s >= Math.abs(J[i][0] -J[j][0]) +Math.abs(J[i][1] -J[j][1]))
            len[i][j] = 0;

      for (int k = 0;k < N;k++)
        for (int i = 0;i < N;i++)
          for (int j = 0;j < N;j++)
            len[i][j] = Math.min(len[i][j],len[i][k] +len[k][j]);

      for (int i = 0;i < N;i++) {
        boolean clear = true;
        for (int j = 0;j < N;j++)
          if (0 < len[i][j]) {
            clear = false;
            break;
          }
        if (clear)
          return true;
      }

      return false;
    });
  }

  long bSearch(final long s,final long e,final Predicate<Long> judge){
    if (Math.abs(s -e) < 2)
      return s;
    final long c = (s +e) /2;
    return judge.test(c) ? bSearch(c,e,judge) : bSearch(s,c,judge);
  }

  static class UnionFind{
    final int[] par;
    final int[] size;

    public UnionFind(final int n){
      par = new int[n];
      Arrays.setAll(par,i -> i);
      size = new int[n];
      Arrays.fill(size,1);
    }

    int root(final int x){ return x == par[x] ? x : (par[x] = root(par[x])); }

    boolean same(final int u,final int v){ return root(u) == root(v); }

    void merge(final int x,final int y){
      int px = root(x);
      int py = root(y);
      if (px == py)
        return;
      if (size[px] < size[py]) {
        final int t = px;
        px = py;
        py = t;
      }

      par[py] = px;
      size[px] += size[py];
    }

    int size(final int x){ return size[root(x)]; }

  }

  /* 定数 */
  final static int infI = (int) 1e9;
  final static long infL = (long) 1e18;
  //  final static long mod = (int) 1e9 +7;
  final static long mod = 998244353;
  final static String yes = "Yes";
  final static String no = "No";

  /* Util */
  void swap(final int[] arr,final int i,final int j){
    int t = arr[i];
    arr[i] = arr[j];
    arr[j] = t;
  }

  void swap(final long[] arr,final int i,final int j){
    long t = arr[i];
    arr[i] = arr[j];
    arr[j] = t;
  }

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

    int[][] trans(final int[][] mat){
      int[][] ret = new int[mat[0].length][mat.length];

      for (int i = 0;i < mat.length;i++)
        for (int j = 0;j < mat[0].length;j++)
          ret[j][i] = mat[i][j];
      return ret;
    }

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

    long[][] trans(final long[][] mat){
      long[][] ret = new long[mat[0].length][mat.length];

      for (int i = 0;i < mat.length;i++)
        for (int j = 0;j < mat[0].length;j++)
          ret[j][i] = mat[i][j];
      return ret;
    }

    double dbl(){ return Double.parseDouble(str()); }

    double[] dbl(final int N){
      double[] a = new double[N];
      Arrays.setAll(a,i -> dbl());
      return a;
    }

    public double[][] dbl(final int H,final int W){ return arr(new double[H][],i -> dbl(W)); }

    char[] ch(){ return str().toCharArray(); }

    char[][] ch(final int H){ return arr(new char[H][],i -> ch()); }

    char[][] trans(final char[][] mat){
      char[][] ret = new char[mat[0].length][mat.length];

      for (int i = 0;i < mat.length;i++)
        for (int j = 0;j < mat[0].length;j++)
          ret[j][i] = mat[i][j];
      return ret;
    }

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

    void sp(){ write((byte) ' '); }

    void ln(){ write((byte) '\n'); }

    void write(final byte b){
      buf[tail++] = b;
      if (tail == buf.length)
        flush();
    }

    void write(final byte[] b,final int off,final int len){
      for (int i = off;i < off +len;i++)
        write(b[i]);
    }

    void write(long n){
      if (n < 0) {
        n = -n;
        write((byte) '-');
      }

      int i = ibuf.length;
      do {
        ibuf[--i] = (byte) (n %10 +'0');
        n /= 10;
      } while (n > 0);

      write(ibuf,i,ibuf.length -i);
    }

    void println(final boolean b){ println(b ? yes : no); }

    void println(final long n){
      write(n);
      ln();
    }

    public void println(final double d){ println(String.valueOf(d)); }

    void println(final String s){
      for (byte b:s.getBytes())
        write(b);
      ln();
    }

    public void println(final char[] s){
      for (char b:s)
        write((byte) b);
      ln();
    }

    void println(final int[] a){
      for (int i = 0;i < a.length;i++) {
        if (0 < i)
          sp();
        write(a[i]);
      }
      ln();
    }

    void println(final long[] a){
      for (int i = 0;i < a.length;i++) {
        if (0 < i)
          sp();
        write(a[i]);
      }
      ln();
    }

  }
}
