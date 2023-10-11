import java.io.*;
import java.util.*;
import java.util.function.*;

public class Main{
  /* 定数 */
  static int infI = (int) 1e9;
  static long infL = (long) 1e18;
  // static long mod = (int) 1e9 +7;
  static long mod = 998244353;
  static String yes = "Yes";
  static String no = "No";

  /* 入出力とか */
  long st = System.currentTimeMillis();
  MyReader in = new MyReader(System.in);
  MyWriter out = new MyWriter(System.out);
  MyWriter log = new MyWriter(System.err){
    @Override
    void ln(){
      super.ln();
      flush();
    };
  };

  int N = in.it();
  int[] A = in.it(N);

  long solve(){
    long ans = 0;
    long inv2 = pow(2,mod -2);

    List<Integer> list = new ArrayList<>();
    for (int i = 0;i < N;i++)
      list.add(i);
    list.sort(Comparator.comparing(t -> A[t]));

    BIT bit = new BIT(N,Long::sum,(a,b) -> (a +b) %mod,a -> -a);
    for (int i = 0;i < N;i++) {
      int id = list.get(i);
      Long tmp = bit.get(id);
      if (tmp != null)
        ans += tmp *pow(2,id -1) %mod;
      bit.add(id,pow(inv2,id));
    }

    return ans %mod;
  }

  static class BIT{
    int n;
    final BinaryOperator<Long> upd;
    final BinaryOperator<Long> opr;
    final UnaryOperator<Long> inv;
    final Long[] bit;

    Long upd(final Long a,final Long b){
      if (a == null)
        return b;

      return upd.apply(a,b);
    }

    Long opr(final Long a,final Long b){
      if (a == null || b == null)
        return a != null ? a : b;

      return opr.apply(a,b);
    }

    Long inv(final Long t){ return t == null ? t : inv.apply(t); }

    BIT(final int n,final BinaryOperator<Long> upd,
        final BinaryOperator<Long> opr,
        final UnaryOperator<Long> inv){
      this.n = n;
      this.upd = upd;
      this.opr = opr;
      this.inv = inv;
      bit = new Long[n +1];
    }

    void add(int x,final Long v){
      for (x++;x <= n;x += x &-x)
        bit[x] = opr(bit[x],v);
    }

    Long get(int x){
      Long ret = null;
      for (x++;x > 0;x -= x &-x)
        ret = opr(ret,bit[x]);
      return ret;
    }

    Long get(final int l,final int r){ return opr(get(r -1),inv(get(l -1))); }
  }

  long pow(long x,long n){ return 1 < n ? pow(x *x %mod,n /2) *pow(x,n &1) %mod : 0 < n ? x : 1; }

  /* Util */
  long mod(long n){ return (n %mod +mod) %mod; }

  int[][] trans(int[][] M){
    int[][] ret = new int[M[0].length][M.length];

    for (int i = 0;i < M.length;i++)
      for (int j = 0;j < M[0].length;j++)
        ret[j][i] = M[i][j];
    return ret;
  }

  long[][] trans(long[][] M){
    long[][] ret = new long[M[0].length][M.length];

    for (int i = 0;i < M.length;i++)
      for (int j = 0;j < M[0].length;j++)
        ret[j][i] = M[i][j];
    return ret;
  }

  int[][] toi(char[][] s){
    int[][] ret = new int[s.length][];
    Arrays.setAll(ret,i -> toi(s[i]));
    return ret;
  }

  int[] toi(char[] s){
    int[] ret = new int[s.length];
    Arrays.setAll(ret,i -> toi(s[i]));
    return ret;
  }

  int toi(char c){
    if (c == '.')
      return 0;
    if (c == '#')
      return 1;
    if ('a' <= c && c <= 'z')
      return c -'a';
    if ('A' <= c && c <= 'Z')
      return c -'A';
    if ('0' <= c && c <= '9')
      return c -'0';
    return c;
  }

  /* 実行 */
  public static void main(String[] args){ new Main().exe(); }

  long elapsed(){ return System.currentTimeMillis() -st; }

  void exe(){
    out.println(solve());
    out.flush();
    log.println(elapsed());
  }

  /* 入力 */
  static class MyReader{
    byte[] buf = new byte[1 <<16];
    int head = 0;
    int tail = 0;
    InputStream in;

    public MyReader(InputStream in){ this.in = in; }

    byte read(){
      if (head == tail)
        try {
          tail = in.read(buf);
          head = 0;
        } catch (IOException e) {
          e.printStackTrace();
        }
      return buf[head++];
    }

    boolean isPrintable(byte c){ return 32 < c && c < 127; }

    boolean isNum(byte c){ return 47 < c && c < 58; }

    byte nextPrintable(){
      byte ret = read();
      while (!isPrintable(ret))
        ret = read();
      return ret;
    }

    int it(){ return (int) lg(); }

    int[] it(int N){
      int[] a = new int[N];
      Arrays.setAll(a,i -> it());
      return a;
    }

    int[][] it(int H,int W){ return arr(new int[H][],i -> it(W)); }

    int idx(){ return it() -1; }

    int[] idx(int N){
      int[] a = new int[N];
      Arrays.setAll(a,i -> idx());
      return a;
    }

    int[][] idx(int H,int W){ return arr(new int[H][],i -> idx(W)); }

    long lg(){
      byte i = nextPrintable();
      boolean negative = i == 45;
      long n = negative ? 0 : i -'0';
      while (isPrintable(i = read()))
        n = 10 *n +i -'0';
      return negative ? -n : n;
    }

    long[] lg(int N){
      long[] a = new long[N];
      Arrays.setAll(a,i -> lg());
      return a;
    }

    long[][] lg(int H,int W){ return arr(new long[H][],i -> lg(W)); }

    double dbl(){ return Double.parseDouble(str()); }

    double[] dbl(int N){
      double[] a = new double[N];
      Arrays.setAll(a,i -> dbl());
      return a;
    }

    double[][] dbl(int H,int W){ return arr(new double[H][],i -> dbl(W)); }

    char[] ch(){ return str().toCharArray(); }

    char[][] ch(int H){ return arr(new char[H][],i -> ch()); }

    String line(){
      StringBuilder sb = new StringBuilder();

      for (byte c;isPrintable(c = read()) || c == ' ';)
        sb.append((char) c);
      return sb.toString();
    }

    String str(){
      StringBuilder sb = new StringBuilder();
      sb.append((char) nextPrintable());

      for (byte c;isPrintable(c = read());)
        sb.append((char) c);
      return sb.toString();
    }

    String[] str(int N){ return arr(new String[N],i -> str()); }

    <T> T[] arr(T[] arr,IntFunction<T> f){
      Arrays.setAll(arr,f);
      return arr;
    }

    int[][] g(int N,int M,boolean d){
      List<List<Integer>> g = new ArrayList<>();
      for (int i = 0;i < N;i++)
        g.add(new ArrayList<>());

      for (int i = 0,u,v;i < M;i++) {
        g.get(u = idx()).add(v = idx());
        if (!d)
          g.get(v).add(u);
      }

      int[][] ret = new int[N][];
      for (int u = 0;u < N;u++) {
        ret[u] = new int[g.get(u).size()];
        for (int i = 0;i < ret[u].length;i++)
          ret[u][i] = g.get(u).get(i);
      }

      return ret;
    }
  }

  /* 出力 */
  static class MyWriter{
    OutputStream out;
    byte[] buf = new byte[1 <<16];
    byte[] ibuf = new byte[20];
    int tail = 0;

    public MyWriter(OutputStream out){ this.out = out; }

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

    void write(byte b){
      buf[tail++] = b;
      if (tail == buf.length)
        flush();
    }

    void write(byte[] b,int off,int len){
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

    void println(boolean b){ println(b ? yes : no); }

    void println(long n){
      write(n);
      ln();
    }

    void println(double d){ println(String.valueOf(d)); }

    void println(String s){ println(s.toCharArray()); }

    void println(char[] s){
      for (char b:s)
        write((byte) b);
      ln();
    }

    void println(int[] a){
      for (int i = 0;i < a.length;i++) {
        if (0 < i)
          sp();
        write(a[i]);
      }
      ln();
    }

    void println(long[] a){
      for (int i = 0;i < a.length;i++) {
        if (0 < i)
          sp();
        write(a[i]);
      }
      ln();
    }

    void println(double[] a){
      for (int i = 0;i < a.length;i++) {
        if (0 < i)
          sp();
        for (char b:String.valueOf(a[i]).toCharArray())
          write((byte) b);
      }
      ln();
    }

  }
}
