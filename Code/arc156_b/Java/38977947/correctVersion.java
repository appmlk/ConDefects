import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.*;

class Main{

  /* 定数 */
  static int infI = (int) 1e9;
  static long infL = (long) 1e18;
  //  static long mod = (int) 1e9 +7;
  static long mod = 998244353;
  static String yes = "Yes";
  static String no = "No";
  Random rd = ThreadLocalRandom.current();

  /* 入出力とか */
  static InputStream is;
  MyReader in = new MyReader(is);
  MyWriter out = new MyWriter(System.out);
  MyWriter log = new MyWriter(System.err){
    @Override
    boolean println(Object obj){
      assert println(obj);
      return true;
    };

    @Override
    void ln(){
      super.ln();
      flush();
    };
  };
  long st = System.currentTimeMillis();

  int N = in.it();
  int K = in.it();
  int[] A = in.it(N);
  int M = 200000;
  int[] cnt = new int[M +1];

  Object solve(){
    for (var a:A)
      cnt[a]++;

    TreeSet<Integer> mex = new TreeSet<>();
    for (int i = 0;i <= M;i++)
      if (cnt[i] == 0)
        mex.add(i);
    for (int i = M +1;i < 3 *M +100;i++)
      mex.add(i);

    if (mex.first() == 0) {
      mex.pollFirst();
      K--;
      cnt[0]++;
    }

    long ans = 1;
    Combin cm = new Combin(3 *M +100);
    while (K > 0) {
      int m = mex.pollFirst();
      ans += cm.nHr(m,K);
      K--;
    }

    return ans %mod;
  }

  static class Combin{
    final long[] fac;
    final long[] finv;
    long[] inv;

    public Combin(final int n){
      fac = new long[n];
      finv = new long[n];
      inv = new long[n];

      fac[0] = fac[1] = 1;
      finv[0] = finv[1] = 1;
      inv[1] = 1;
      for (int i = 2;i < n;i++) {
        fac[i] = fac[i -1] *i %mod;
        inv[i] = mod -inv[(int) (mod %i)] *(mod /i) %mod;
        finv[i] = finv[i -1] *inv[i] %mod;
      }
    }

    long nHr(int n,int r){ return r < 0 ? 0 : nCr(n +r -1,r); }

    long nCr(final int n,final int r){
      if (r < 0 || n -r < 0)
        return 0;

      return fac[n] *(finv[r] *finv[n -r] %mod) %mod;
    }

  }

  long solve2(){
    int N = in.it();
    char[] S = in.ch();
    log.println(S);

    List<Integer> list = new ArrayList<>();
    for (int i = 0;i < N;i++) {
      char c = S[i];
      if (c == '1')
        list.add(i);
    }

    int cnt = list.size();
    if (cnt == 0)
      return 0;

    if (cnt %2 == 1)
      return -1;

    if (cnt == 2 && list.get(0) +1 == list.get(1))
      return 2;

    return cnt /2;
  }

  long pop(long x){
    x -= x >>1 &0x5555555555555555L;
    x = (x &0x3333333333333333L) +(x >>2 &0x3333333333333333L);
    x = x +(x >>4) &0x0f0f0f0f0f0f0f0fL;
    x += x >>8;
    x += x >>16;
    x += x >>32;
    return x &0x0000007f;
  }

  long pow(final long l,final long i){
    if (i == 0)
      return 1;
    if (i == 1)
      return l;
    return pow(l *l %mod,i /2) *pow(l,i &1) %mod;
  }

  long mod(final long n){ return (n %mod +mod) %mod; }

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

  static abstract class Seg<V, F> {
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

  /* 実行 */
  public static void main(String[] args) throws Exception{
    is = System.in;
    assert local();
    new Main().exe();
  }

  static boolean local() throws Exception{
    is = new FileInputStream("src/input.txt");
    return true;
  }

  long elapsed(){ return System.currentTimeMillis() -st; }

  void exe(){
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

    boolean println(Object obj){
      if (obj instanceof Boolean)
        println((boolean) obj);
      else if (obj instanceof char[])
        println((char[]) obj);
      else if (obj instanceof int[])
        println((int[]) obj);
      else if (obj instanceof long[])
        println((long[]) obj);
      else if (obj instanceof double[])
        println((double[]) obj);
      else
        println(Objects.toString(obj));
      return true;
    }
  }
}
