import java.io.*;
import java.util.*;
import java.util.function.*;

class Main{

  /* 定数 */
  static int infI = (int) 1e9;
  static long infL = (long) 1e18;
  static long mod = (int) 1e9 +7;
  //  static long mod = 998244353;
  static String yes = "Yes";
  static String no = "No";
  static boolean local = false;

  /* 入出力とか */
  static InputStream is;
  MyReader in = new MyReader(is);
  MyWriter out = new MyWriter(System.out);
  MyLogger log = new MyLogger();
  long st = System.currentTimeMillis();

  void local(){}

  int N = in.it() <<1;
  int M = in.it();
  char[][] A = in.ch(N);

  int[] solve(){
    int[][] T = new int[N][2];
    for (int i = 0;i < N;i++)
      T[i][0] = i;

    for (int j = 0;j < M;j++) {
      for (int i = 0;i < N;i += 2) {
        char a = A[T[i][0]][j];
        char b = A[T[i +1][0]][j];
        if (win(a,b))
          T[i][1]++;
        if (win(b,a))
          T[i +1][1]++;
      }
      Arrays.sort(T,Comparator.comparing(t -> (-t[1] <<10) +t[0]));
    }

    int[] ans = new int[N];
    for (int i = 0;i < N;i++)
      ans[i] = T[i][0] +1;

    return ans;
  }

  private boolean win(char a,char b){
    if (a == 'G' && b == 'C')
      return true;
    if (a == 'C' && b == 'P')
      return true;
    if (a == 'P' && b == 'G')
      return true;
    return false;
  }

  static class SegmentTree<V> {
    Seg<V, V> seg;

    SegmentTree(Seg<V, V> seg){ this.seg = seg; }

    void upd(int i,V f){
      seg.upd(i,i +1,f);
      seg.up(i,i +1);
    }

    void set(int i,V v){
      seg.set(i,i +1,v);
      seg.up(i,i +1);
    }

    V get(int i){ return get(i,i +1); }

    V get(int l,int r){ return seg.get(l,r); }
  }

  static class DualSegmentTree<V, F> {
    Seg<V, F> seg;

    DualSegmentTree(Seg<V, F> seg){ this.seg = seg; }

    void upd(int i,F f){ upd(i,i +1,f); }

    void upd(int l,int r,F f){
      seg.down(l,r);
      seg.upd(l,r,f);
    }

    void set(int i,V v){ set(i,i +1,v); }

    void set(int l,int r,V v){
      seg.down(l,r);
      seg.set(l,r,v);
    }

    V get(int i){
      seg.down(i,i +1);
      return seg.get(i,i +1);
    }

  }

  static class LazySegmentTree<V, F> {
    Seg<V, F> seg;

    LazySegmentTree(Seg<V, F> seg){ this.seg = seg; }

    void upd(int i,F f){ upd(i,i +1,f); }

    void upd(int l,int r,F f){
      seg.down(l,r);
      seg.upd(l,r,f);
      seg.up(l,r);
    }

    void set(int i,V v){ set(i,i +1,v); }

    void set(int l,int r,V v){
      seg.down(l,r);
      seg.set(l,r,v);
      seg.up(l,r);
    }

    V get(int i){ return get(i,i +1); }

    V get(int l,int r){
      seg.down(l,r);
      return seg.get(l,r);
    }
  }

  abstract class Seg<V, F> {
    int n;
    V e;
    V[] val;
    F[] lazy;
    V[] over;
    int[][] lr;

    @SuppressWarnings("unchecked")
    Seg(int n,V e){
      this.n = n;
      this.e = e;
      val = (V[]) new Object[n <<1];

      lazy = (F[]) new Object[n];
      over = (V[]) new Object[n];

      lr = new int[n <<1][];
      for (int i = n <<1;--i > 0;)
        lr[i] = new int[]{i < n ? lr[i <<1][0] : i, i < n ? lr[i <<1 |1][1] : i +1};
    }

    abstract V agg(V v0,V v1);

    abstract V map(V v,F f);

    abstract F comp(F f0,F f1);

    abstract F powF(F f,int[] lr);

    abstract V powV(V v,int[] lr);

    void merge(int i){ val[i] = agg(eval(i <<1),eval(i <<1 |1)); }

    void uprec(int l,int r){
      if (l == r) {
        while (0 < (l >>= 1))
          merge(l);
        return;
      }

      if (l < r)
        merge(r >>= 1);
      uprec(r,l);
    }

    void up(int l,int r){
      l += n;
      r += n;
      uprec(l /(l &-l),r /(r &-r));
    }

    void over(int i,V v){
      if (i < n) {
        over[i] = v;
        lazy[i] = null;
      } else
        val[i] = v;
    }

    void comp(int i,F f){
      if (i < n)
        lazy[i] = lazy[i] != null ? comp(lazy[i],f) : f;
      else
        val[i] = map(val[i],f);
    }

    V eval(int i){
      if (i < n && over[i] != null) {
        val[i] = powV(over[i],lr[i]);
        for (int c = 0;c < 2;c++)
          over(i <<1 |c,over[i]);

        over[i] = null;
      }

      if (i < n && lazy[i] != null) {
        val[i] = map(val[i],powF(lazy[i],lr[i]));
        for (int c = 0;c < 2;c++)
          comp(i <<1 |c,lazy[i]);
        lazy[i] = null;
      }

      return val[i] != null ? val[i] : e;
    }

    void downrec(int l,int r){
      if (l > r) {
        downrec(r,l);
        return;
      }

      if (1 < r)
        downrec(r >>1,l);
      eval(r);
      if (l < r)
        eval(l);
    }

    void down(int l,int r){
      l += n;
      r += n;
      downrec(l /(l &-l),r /(r &-r));
    }

    void upd(int l,int r,F f){
      l += n;
      r += n;
      do {
        if ((l &1) == 1)
          comp(l++,f);
        if ((r &1) == 1)
          comp(--r,f);
      } while ((l >>= 1) < (r >>= 1));
    }

    void set(int l,int r,V v){
      v = v == null ? e : v;
      l += n;
      r += n;
      do {
        if ((l &1) == 1)
          over(l++,v);
        if ((r &1) == 1)
          over(--r,v);
      } while ((l >>= 1) < (r >>= 1));
    }

    V get(int l,int r){
      l += n;
      r += n;
      V vl = e;
      V vr = e;
      do {
        if ((l &1) == 1)
          vl = agg(vl,val[l++]);
        if ((r &1) == 1)
          vr = agg(val[--r],vr);
      } while ((l >>= 1) < (r >>= 1));

      return agg(vl,vr);
    }

  }

  /* Util */
  static long mod(long n){ return (n %mod +mod) %mod; }

  static int[][] trans(int[][] M){
    int[][] ret = new int[M[0].length][M.length];

    for (int i = 0;i < M.length;i++)
      for (int j = 0;j < M[0].length;j++)
        ret[j][i] = M[i][j];
    return ret;
  }

  static long[][] trans(long[][] M){
    long[][] ret = new long[M[0].length][M.length];

    for (int i = 0;i < M.length;i++)
      for (int j = 0;j < M[0].length;j++)
        ret[j][i] = M[i][j];
    return ret;
  }

  /* 実行 */
  public static void main(String[] args) throws Exception{
    assert local = true;
    is = local ? new FileInputStream("src/input.txt") : System.in;

    new Main().exe();
  }

  long elapsed(){ return System.currentTimeMillis() -st; }

  void exe(){
    if (local)
      local();
    Optional.ofNullable(solve()).ifPresent(out::println);
    out.flush();
    log.println(elapsed());
  }

  /* 入力 */
  static class MyReader{
    byte[] buf = new byte[1 <<16];
    int ptr = 0;
    int tail = 0;
    InputStream in;

    MyReader(InputStream in){ this.in = in; }

    byte read(){
      if (ptr == tail)
        try {
          tail = in.read(buf);
          ptr = 0;
        } catch (IOException e) {}
      return buf[ptr++];
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

    int[] chi(){ return toi(ch()); }

    int[][] chi(int H){ return arr(new int[H][],i -> chi()); }

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

  }

  /* 出力 */
  static class MyWriter{
    OutputStream out;
    byte[] buf = new byte[1 <<16];
    byte[] ibuf = new byte[20];
    int tail = 0;

    MyWriter(OutputStream out){ this.out = out; }

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

  /* デバッグ用 */
  static class MyLogger{
    MyWriter org = new MyWriter(System.err);

    void println(Object obj){
      if (!local)
        return;
      if (obj instanceof Boolean)
        org.println((boolean) obj);
      else if (obj instanceof char[])
        org.println((char[]) obj);
      else if (obj instanceof int[])
        org.println((int[]) obj);
      else if (obj instanceof long[])
        org.println((long[]) obj);
      else if (obj instanceof double[])
        org.println((double[]) obj);
      else
        org.println(Objects.toString(obj));
      org.flush();
    }
  }
}