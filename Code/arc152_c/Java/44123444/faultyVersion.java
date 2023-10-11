import static java.lang.Math.*;
import static java.util.Arrays.*;

import java.awt.Point;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.*;

import javax.management.RuntimeErrorException;

class Solver{
  long st = System.currentTimeMillis();

  long elapsed(){ return System.currentTimeMillis() -st; }

  void reset(){ st = System.currentTimeMillis(); }

  static int infI = (int) 1e9;
  static long infL = (long) 1e18;
  //  static long mod = (int) 1e9 +7;
  static long mod = 998244353;
  static String yes = "Yes";
  static String no = "No";

  Random rd = ThreadLocalRandom.current();
  MyReader in = new MyReader(System.in);
  MyWriter out = new MyWriter(System.out);
  MyWriter log = new MyWriter(System.err){
    @Override
    void println(Object obj){ super.println(obj == null ? "null" : obj); };

    @Override
    protected void ln(){
      super.ln();
      flush();
    };
  };

  int N = in.it();
  int[] A = in.it(N);

  Object solve(){

    int L = A[N -1] -A[0];

    long gcd = 0;

    for (var a:A) {
      int tmp = A[N -1] -(2 *a -A[0]);
      tmp += L;
      gcd = gcd(gcd,tmp);
      gcd = gcd(gcd,3 *L -tmp);
    }
    long ans = A[N -1] %gcd;

    if (ans < L)
      ans += L;
    return ans;
  }

  long gcd(long a,long b){ return b == 0 ? a : gcd(b,a %b); }
}

abstract class Seg<V, F> {
  protected int n;
  private V e;
  private V[] val;
  private F[] lazy;
  private int[] rg;
  private int[] stk = new int[100];

  @SuppressWarnings("unchecked")
  Seg(int n,V e,IntFunction<V> sup){
    this.n = n;
    this.e = e;
    val = (V[]) new Object[n <<1];
    lazy = (F[]) new Object[n];

    rg = new int[n <<1];
    for (int i = n <<1;--i > 0;)
      rg[i] = i < n ? rg[i <<1] : 1;

    build(sup);
  }

  void build(IntFunction<V> sup){
    for (int i = 0;i < n;i++) {
      val[i] = e;
      val[i +n] = sup.apply(i);
    }
  }

  V agg(V v0,V v1){ throw new UnsupportedOperationException("agg"); }

  V map(V v,F f){ throw new UnsupportedOperationException("map"); }

  F comp(F f0,F f1){ throw new UnsupportedOperationException("comp"); }

  F powF(F f,int rg){ throw new UnsupportedOperationException("powF"); }

  void merge(int i){ val[i] = agg(eval(i <<1),eval(i <<1 |1)); }

  void up(int l,int r){
    l += n;
    r += n;
    l /= l &-l;
    r /= r &-r;
    while (l != r)
      if (l > r)
        merge(l >>= 1);
      else
        merge(r >>= 1);
    while (1 < l)
      merge(l >>= 1);
  }

  private void comp(int i,F f){
    if (i < n)
      lazy[i] = lazy[i] != null ? comp(lazy[i],f) : f;
    else
      val[i] = map(val[i],f);
  }

  private V eval(int i){

    if (i < n && lazy[i] != null) {
      val[i] = map(val[i],powF(lazy[i],rg[i]));
      comp(i <<1,lazy[i]);
      comp(i <<1 |1,lazy[i]);
      lazy[i] = null;
    }

    return val[i];
  }

  protected void down(int l,int r){
    l += n;
    r += n;
    l /= l &-l;
    r /= r &-r;
    int s = 0;
    while (0 < r) {
      while (l > r) {
        stk[++s] = l;
        l >>= 1;
      }
      stk[++s] = r;
      if (l == r)
        l >>= 1;
      r >>= 1;
    }

    while (0 < s)
      eval(stk[--s]);
  }

  void upd(int i,F f){ upd(i,i +1,f); }

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

  V get(int i){ return eval(i +n); }

  V get(int l,int r){
    l += n;
    r += n;
    V vl = e;
    V vr = e;
    while (l < r) {
      if ((l &1) == 1)
        vl = agg(vl,eval(l++));
      if ((r &1) == 1)
        vr = agg(eval(--r),vr);
      l >>= 1;
      r >>= 1;
    }

    return agg(vl,vr);
  }

}

class DualSegmentTree<V, F> extends Seg<V, F>{

  DualSegmentTree(int n,V e){ this(n,e,i -> e); }

  DualSegmentTree(int n,V e,IntFunction<V> sup){ super(n,e,sup); }

  @Override
  F powF(F f,int rg){ return f; }

  @Override
  protected void upd(int l,int r,F f){
    down(l,r);
    super.upd(l,r,f);
  }

  @Override
  V get(int i){
    down(i,i +1);
    return super.get(i);
  }

}

class Util{
  static int[] arrI(int N,IntUnaryOperator f){
    int[] ret = new int[N];
    setAll(ret,f);
    return ret;
  }

  static long[] arrL(int N,IntToLongFunction f){
    long[] ret = new long[N];
    setAll(ret,f);
    return ret;
  }

  static double[] arrD(int N,IntToDoubleFunction f){
    double[] ret = new double[N];
    setAll(ret,f);
    return ret;
  }

  static <T> T[] arr(T[] arr,IntFunction<T> f){
    setAll(arr,f);
    return arr;
  }

}

class MyReader{
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

  int it(){ return toIntExact(lg()); }

  int[] it(int N){ return Util.arrI(N,i -> it()); }

  int[][] it(int H,int W){ return Util.arr(new int[H][],i -> it(W)); }

  int idx(){ return it() -1; }

  int[] idx(int N){ return Util.arrI(N,i -> idx()); }

  int[][] idx(int H,int W){ return Util.arr(new int[H][],i -> idx(W)); }

  int[][] qry(int Q){ return Util.arr(new int[Q][],i -> new int[]{idx(), idx(), i}); }

  long lg(){
    byte i = nextPrintable();
    boolean negative = i == 45;
    long n = negative ? 0 : i -'0';
    while (isPrintable(i = read()))
      n = 10 *n +i -'0';
    return negative ? -n : n;
  }

  long[] lg(int N){ return Util.arrL(N,i -> lg()); }

  long[][] lg(int H,int W){ return Util.arr(new long[H][],i -> lg(W)); }

  double dbl(){ return Double.parseDouble(str()); }

  double[] dbl(int N){ return Util.arrD(N,i -> dbl()); }

  double[][] dbl(int H,int W){ return Util.arr(new double[H][],i -> dbl(W)); }

  char[] ch(){ return str().toCharArray(); }

  char[][] ch(int H){ return Util.arr(new char[H][],i -> ch()); }

  String line(){
    StringBuilder sb = new StringBuilder();

    for (byte c;(c = read()) != '\n';)
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

  String[] str(int N){ return Util.arr(new String[N],i -> str()); }

}

class MyWriter{
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

  protected void ln(){ write((byte) '\n'); }

  private void write(byte b){
    buf[tail++] = b;
    if (tail == buf.length)
      flush();
  }

  private void write(byte[] b,int off,int len){
    for (int i = off;i < off +len;i++)
      write(b[i]);
  }

  private void write(long n){
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

  private void print(Object obj){
    if (obj instanceof Boolean)
      print((boolean) obj ? Solver.yes : Solver.no);
    else if (obj instanceof Character)
      write((byte) (char) obj);
    else if (obj instanceof Integer)
      write((int) obj);
    else if (obj instanceof Long)
      write((long) obj);
    else if (obj instanceof char[])
      for (char b:(char[]) obj)
        write((byte) b);
    else if (obj.getClass().isArray()) {
      int l = Array.getLength(obj);
      for (int i = 0;i < l;i++) {
        print(Array.get(obj,i));
        if (i +1 < l)
          write((byte) ' ');
      }
    } else
      for (char b:Objects.toString(obj).toCharArray())
        write((byte) b);
  }

  void println(Object obj){
    if (obj == null)
      return;

    if (obj instanceof Collection<?>)
      for (Object e:(Collection<?>) obj)
        println(e);
    else if (obj.getClass().isArray()
        && Array.getLength(obj) > 0
        && !(Array.get(obj,0) instanceof char[])
        && Array.get(obj,0).getClass().isArray()) {
      int l = Array.getLength(obj);
      for (int i = 0;i < l;i++)
        println(Array.get(obj,i));
    } else {
      print(obj);
      ln();
    }
  }
}

class Main{
  public static void main(String[] args) throws Exception{
    Solver solver = new Solver();
    Optional.ofNullable(solver.solve()).ifPresent(solver.out::println);
    solver.out.flush();
    solver.log.println(solver.elapsed());
  }
}
