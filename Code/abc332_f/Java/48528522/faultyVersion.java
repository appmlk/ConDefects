import static java.lang.Math.*;
import static java.util.Arrays.*;

import java.awt.Point;
import java.io.*;
import java.lang.reflect.Array;
import java.math.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.*;
import java.util.stream.*;

class Solver extends Util{
  //  static long mod = (int) 1e9 +7;
  static long mod = 998244353;

  //  Object solve(){
  //    int N;
  //    //    for (int j = 0;j < N;j++)
  //    //      extracted(N,j);
  //    N = 200000;
  //    AVLSegmentTree<Data, Long> seg = avl(N);
  //    RangeData<Data, Long> seg2 = lazy(N);
  //    //    for (int i = 0;i < N;i++) {
  //    //      long v = rd.nextInt(1000);
  //    //      seg.add(new Data(v));
  //    //      seg2.upd(i,v);
  //    //    }
  //    //    for (int i = 0;i < N;i++) {
  //    //      long a = seg.get(i).v;
  //    //      long b = seg2.get(i).v;
  //    //      assert a == b;
  //    //    }
  //
  //    while (elapsed() < 1000) {
  //      int i,j;
  //      do {
  //        i = rd.nextInt(N);
  //        j = rd.nextInt(N) +1;
  //      } while (i >= j);
  //      long x = rd.nextInt(10) +1;
  //      //      log.printlns(i,j,x);
  //      if (rd.nextBoolean()) {
  //        seg.upd(i,j,x);
  //        seg2.upd(i,j,x);
  //      } else {
  //        var a = seg.get(i,j).v;
  //        var b = seg2.get(i,j).v;
  //        assert a == b : i +"," +j;
  //      }
  //    }
  //
  //    int cnt = 0;
  //    seg = avl(N);
  //    reset();
  //    while (elapsed() < 1000) {
  //      cnt++;
  //      int i,j;
  //      do {
  //        i = rd.nextInt(N);
  //        j = rd.nextInt(N) +1;
  //      } while (i >= j);
  //      long x = rd.nextInt(10) +1;
  //      if (rd.nextBoolean())
  //        seg.upd(i,j,x);
  //      else
  //        seg.get(i,j);
  //    }
  //
  //    return cnt;
  //  }
  //
  //  private RangeData<Data, Long> lazy(int N){
  //    RangeData<Data, Long> seg2 = new LazySegmentTree<>(N){
  //      @Override
  //      protected Data e(){ return new Data(0); }
  //
  //      @Override
  //      protected void agg(Data x,Data a,Data b){ x.v = a.v +b.v; }
  //
  //      @Override
  //      protected void map(Data v,Long f){ v.v += f *v.sz; }
  //
  //      @Override
  //      protected Long comp(Long f,Long g){ return f +g; }
  //    };
  //    return seg2;
  //  }
  //
  //  private AVLSegmentTree<Data, Long> avl(int N){
  //    AVLSegmentTree<Data, Long> seg = new AVLSegmentTree<>(N){
  //      @Override
  //      protected Data e(){ return new Data(0); }
  //
  //      @Override
  //      protected void agg(Data x,Data a,Data b){ x.v = a.v +b.v; }
  //
  //      @Override
  //      protected void map(Data v,Long f){ v.v += f *v.sz; }
  //
  //      @Override
  //      protected Long comp(Long f,Long g){ return f +g; }
  //
  //      protected void pow(Data v,Data a,int n){ v.v += a.v *n; }
  //    };
  //    return seg;
  //  }

  Object solve(){
    int N = in.it();
    int Q = in.it();
    long[] A = in.lg(N);

    var seg = new AVLSegmentTree<Data, long[]>(){
      @Override
      protected long[] comp(long[] f,long[] g){ return new long[]{f[0] *g[0] %mod, (f[1] *g[0] +g[1]) %mod}; }

      @Override
      protected Data e(){ return new Data(0); }

      @Override
      protected void map(Data v,long[] f){ v.v = (v.v *f[0] +f[1]) %mod; }

      @Override
      protected void agg(Data v,Data a,Data b){ v.v = a.v +b.v; }
    };

    seg.build(N,i -> new Data(A[i]));

    long[] ans = new long[N];

    while (Q-- > 0) {
      int l = in.idx();
      int r = in.it();

      long inv = inv(r -l,mod);

      long a = (r -l -1) *inv %mod;

      long x = in.lg();
      long b = x *inv %mod;
      seg.upd(l,r,new long[]{a, b});
    }

    for (int i = 0;i < N;i++)
      ans[i] = seg.get(i).v;
    return ans;
  }

  long inv(long x,long mod){ return pow(x,mod -2,mod); }

  long pow(long x,long n){ return pow(x,n,mod); }

  long pow(long x,long n,long mod){
    x %= mod;
    long ret = 1;
    while (0 < n) {
      if ((n &1) == 1)
        ret = ret *x %mod;
      x = x *x %mod;
      n >>= 1;
    }

    return ret;
  }
}

class Data extends BaseV{
  long v;

  public Data(long v){ this.v = v; }
}

abstract class AVLSegmentTree<V extends BaseV, F> extends RangeData<V, F>{
  private V e = e();
  private Node nl = new Node(0);

  public AVLSegmentTree(int n){ nl.cld(1,new Node(n)); }

  public AVLSegmentTree(){ this(0); }

  public void add(V v){ ins(size(),v); }

  public void ins(int i,V v){
    if (v.sz == 0)
      v.sz = 1;
    ins(nl.rht,i,v);
  }

  private void ins(Node nd,int i,V v){
    if (nd.sz == 0) {
      nd.val = v;
      nd.sz = v.sz;
      v.sz = 1;
      return;
    }

    if (nd.leaf)
      split(nd,i);

    if (i == 0 || i < nd.lft.sz)
      ins(nd.lft,i,v);
    else
      ins(nd.rht,i -nd.lft.sz,v);

    if (abs(nd.bis) > 1)
      nd.par.cld(nd.par.lft == nd ? -1 : 1,nd = rotate(nd));
    nd.merge();
  }

  @Override
  public void upd(int i,F f){ upd(i,i +1,f); }

  @Override
  public void upd(int l,int r,F f){
    if (size() < r) {
      var v = e();
      v.sz = r -size();
      add(v);
    }
    upd(nl.rht,l,r,f);
  }

  private void upd(Node nd,int l,int r,F f){
    if (r < 1 || nd.sz < l || (l = max(0,l)) == (r = min(nd.sz,r)))
      return;
    if (l == 0 && r == nd.sz) {
      nd.prop(f);
      return;
    }
    if (nd.leaf)
      split(nd,0 < l ? l : r);
    else
      nd.push();
    upd(nd.lft,l,r,f);
    upd(nd.rht,l -nd.lft.sz,r -nd.lft.sz,f);
    if (abs(nd.bis) > 1)
      nd.par.cld(nd.par.lft == nd ? -1 : 1,nd = rotate(nd));
    nd.merge();
  }

  private void split(Node nd,int c){
    nd.leaf = false;
    nd.cld(-1,new Node(nd.val,c));
    nd.cld(1,new Node(nd.sz -c));
    agg(nd.rht.val,nd.val,e);
    nd.val = e();
  }

  public void build(int n,IntFunction<V> init){ nl.cld(1,build(0,n,init)); }

  private Node build(int i,int n,IntFunction<V> init){
    var ret = new Node(n);
    if (n == 1) {
      ret.val = init.apply(i);
      ret.val.sz = 1;
    } else {
      ret.leaf = false;
      ret.cld(-1,build(i,n /2,init));
      ret.cld(1,build(i +n /2,n -n /2,init));
      ret.merge();
    }
    return ret;
  }

  @Override
  public V get(int i){ return get(i,i +1); }

  @Override
  public V get(int l,int r){
    V ret = e();
    get(ret,nl.rht,l,r);
    return ret;
  }

  private void get(V ret,Node nd,int l,int r){
    if (r < 1 || nd.sz < l)
      return;
    l = max(0,l);
    r = min(nd.sz,r);
    if (l == r)
      return;
    if (l == 0 && r == nd.sz) {
      agg(ret,ret,nd.val());
      ret.sz += nd.sz;
      return;
    }
    if (nd.leaf) {
      pow(ret,nd.val,r -l);
      ret.sz += r -l;
      return;
    }
    nd.push();

    get(ret,nd.lft,l,r);
    get(ret,nd.rht,l -nd.lft.sz,r -nd.lft.sz);
  }

  public V all(){ return nl.rht.val(); }

  public int size(){ return nl.rht.sz; }

  private class Node{
    private int rnk,bis,sz;
    private V val;
    private F laz;
    private Node par,lft,rht;
    private boolean leaf = true;

    private Node(int sz){ this(e(),sz); }

    private Node(V val,int sz){
      this.sz = sz;
      this.val = val;
      val.sz = 1;
    }

    private Node cld(int c){ return c < 0 ? lft : rht; }

    private void cld(int c,Node nd){
      if (c < 0)
        lft = nd;
      else
        rht = nd;
      nd.par = this;
    }

    private void merge(){
      if (leaf)
        return;
      rnk = max(lft.rnk,rht.rnk) +1;
      bis = rht.rnk -lft.rnk;
      agg(val,lft.val(),rht.val());
      val.sz = sz = lft.sz +rht.sz;
    }

    private V val(){
      if (leaf && 1 < sz) {
        var ret = e();
        pow(ret,val,sz);
        ret.sz = sz;
        return ret;
      }
      return val;
    }

    private void push(){
      if (laz != null) {
        lft.prop(laz);
        rht.prop(laz);
        laz = null;
      }
    }

    private void prop(F f){
      map(val,f);
      if (!leaf)
        laz = laz == null ? f : comp(laz,f);
    }
  }

  protected abstract V e();
  protected abstract void agg(V v,V a,V b);
  protected abstract void map(V v,F f);

  protected void pow(V v,V a,int n){
    var x = e();
    agg(x,e,a);
    x.sz = a.sz;
    while (true) {
      if ((n &1) == 1) {
        agg(v,v,x);
        v.sz += x.sz;
      }
      n >>= 1;
      if (n == 0)
        break;
      agg(x,x,x);
      x.sz <<= 1;
    }
  }

  protected F comp(F f,F g){ return null; }

  private Node rotate(Node u){
    var v = u.cld(u.bis);
    v.push();
    if (u.bis *v.bis < 0)
      v = rotate(v);
    u.cld(u.bis,v.cld(-u.bis));
    v.cld(-u.bis,u);
    u.merge();
    return v;
  }
}

abstract class SegmentTree<V extends BaseV, F> extends Seg<V, F>{
  public SegmentTree(int n){ super(n); }

  @Override
  protected abstract void agg(V v,V a,V b);

  @Override
  public void upd(int i,F f){
    super.upd(i,f);
    up(i,i +1);
  }
}

abstract class DualSegmentTree<V extends BaseV, F> extends Seg<V, F>{
  public DualSegmentTree(int n){ super(n); }

  @Override
  protected void rangeMap(int i){}

  @Override
  protected abstract F comp(F f,F g);

  @Override
  public void upd(int i,F f){ upd(i,i +1,f); }

  @Override
  public void upd(int l,int r,F f){
    down(l,r);
    super.upd(l,r,f);
  }

  @Override
  public V get(int i){
    down(i,i +1);
    return super.get(i);
  }
}

abstract class LazySegmentTree<V extends BaseV, F> extends Seg<V, F>{
  public LazySegmentTree(int n){ super(n); }

  @Override
  protected abstract void agg(V v,V a,V b);
  @Override
  protected abstract F comp(F f,F g);

  @Override
  public void upd(int i,F f){ upd(i,i +1,f); }

  @Override
  public void upd(int l,int r,F f){
    down(l,r);
    super.upd(l,r,f);
    up(l,r);
  }

  @Override
  public V get(int i){ return get(i,i +1); }

  @Override
  public V get(int l,int r){
    down(l,r);
    return super.get(l,r);
  }
}

abstract class Seg<V extends BaseV, F> extends RangeData<V, F>{
  private int n,log;
  private V[] val;
  private F[] lazy;

  @SuppressWarnings("unchecked")
  Seg(int n){
    this.n = n;
    while (1 <<log <= n)
      log++;
    val = (V[]) new BaseV[n <<1];
    lazy = (F[]) new Object[n];

    for (int i = -1;++i < n;) {
      V v = val[i +n] = init(i);
      v.sz = 1;
    }
    for (int i = n;--i > 0;merge(i)) {
      V v = val[i] = e();
      v.sz = val[i <<1].sz +val[i <<1 |1].sz;
    }
  }

  protected abstract V e();

  protected V init(int i){ return e(); }

  protected void agg(V v,V a,V b){}

  protected abstract void map(V v,F f);

  protected void rangeMap(int i){ map(val[i],lazy[i]); }

  protected F comp(F f,F g){ return null; }

  private V eval(int i){
    if (0 < i && i < n && lazy[i] != null) {
      rangeMap(i);
      prop(i <<1,lazy[i]);
      prop(i <<1 |1,lazy[i]);
      lazy[i] = null;
    }
    return val[i];
  }

  private void merge(int i){ agg(val[i],eval(i <<1),eval(i <<1 |1)); }

  private void prop(int i,F f){
    if (i < n)
      lazy[i] = lazy[i] == null ? f : comp(lazy[i],f);
    else
      map(val[i],f);
  }

  protected void up(int l,int r){
    for (l = oddPart(l +n),r = oddPart(r +n);l != r;)
      merge(l > r ? (l >>= 1) : (r >>= 1));
    while (1 < l)
      merge(l >>= 1);
  }

  protected void down(int l,int r){
    int i = log;
    for (l = oddPart(l +n),r = oddPart(r +n);i > 0;i--) {
      eval(l >>i);
      eval(r >>i);
    }
  }

  private int oddPart(int i){ return i /(i &-i); }

  @Override
  public void upd(int i,F f){ prop(i +n,f); }

  @Override
  public void upd(int l,int r,F f){
    for (l += n,r += n;l < r;l >>= 1,r >>= 1) {
      if ((l &1) == 1)
        prop(l++,f);
      if ((r &1) == 1)
        prop(--r,f);
    }
  }

  @Override
  public V get(int i){ return val[i +n]; }

  @Override
  public V get(int l,int r){
    V vl = e(),vr = e();
    for (l += n,r += n;l < r;l >>= 1,r >>= 1) {
      if ((l &1) == 1) {
        var t = eval(l++);
        agg(vl,vl,t);
        vl.sz += t.sz;
      }
      if ((r &1) == 1) {
        var t = eval(--r);
        agg(vr,t,vr);
        vr.sz += t.sz;
      }
    }
    agg(vl,vl,vr);
    return vl;
  }
}

abstract class BaseV{ int sz; }

abstract class RangeData<V, F> {
  public void upd(int i,F f){}

  public void upd(int l,int r,F f){}

  public V get(int i){ return null; }

  public V get(int l,int r){ return null; }
}

class Util{
  public static String yes = "Yes",no = "No";
  public static int infI = (1 <<30) -1;
  public static long infL = (1L <<60 |1 <<30) -1;
  private long st = System.currentTimeMillis();
  public static Random rd = ThreadLocalRandom.current();
  public MyReader in = new MyReader(System.in);
  public MyWriter out = new MyWriter(System.out);
  public MyWriter log = new MyWriter(System.err){
    @Override
    void println(Object obj){ super.println(obj == null ? "null" : obj); };

    @Override
    protected void ln(){
      super.ln();
      flush();
    };
  };

  long elapsed(){ return System.currentTimeMillis() -st; }

  void reset(){ st = System.currentTimeMillis(); }

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
  private byte[] buf = new byte[1 <<16];
  private int ptr,tail;
  private InputStream in;

  MyReader(InputStream in){ this.in = in; }

  private byte read(){
    if (ptr == tail)
      try {
        tail = in.read(buf);
        ptr = 0;
      } catch (IOException e) {}
    return buf[ptr++];
  }

  private boolean isPrintable(byte c){ return 32 < c && c < 127; }

  private byte nextPrintable(){
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
  private OutputStream out;
  private byte[] buf = new byte[1 <<16],ibuf = new byte[20];
  private int tail;

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
      print((boolean) obj ? Util.yes : Util.no);
    else if (obj instanceof Integer)
      write((int) obj);
    else if (obj instanceof Long)
      write((long) obj);
    else if (obj instanceof char[] cs)
      for (char b:cs)
        write((byte) b);
    else if (obj.getClass().isArray()) {
      int l = Array.getLength(obj);
      for (int i = 0;i < l;i++) {
        print(Array.get(obj,i));
        if (i +1 < l)
          write((byte) ' ');
      }
    } else
      print(Objects.toString(obj).toCharArray());
  }

  void println(Object obj){
    if (obj == null)
      return;
    if (obj instanceof Iterable<?> co)
      for (Object e:co)
        println(e);
    else if (obj.getClass().isArray() && Array.getLength(obj) > 0 && Array.get(obj,0).getClass().isArray()) {
      int l = Array.getLength(obj);
      for (int i = 0;i < l;i++)
        println(Array.get(obj,i));
    } else {
      print(obj);
      ln();
    }
  }

  void printlns(Object... o){
    print(o);
    ln();
  }
}

class Main{
  public static void main(String[] args) throws Exception{
    new Solver(){
      public void exe(){
        out.println(solve());
        out.flush();
        log.println(elapsed());
      }
    }.exe();
  }
}
