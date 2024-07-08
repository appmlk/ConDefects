import static java.lang.Math.*;
import static java.util.Arrays.*;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

class Solver extends Functions{
  public Solver(MyReader in,MyWriter out,MyWriter log){ super(in,out,log); }

  public static boolean multi = false;

  Object solve(){
    int N = in.it();
    int Q = in.it();
    int[] A = in.it(N);
    int[] B = in.it(N);
    SegmentTree<Data, Integer> segA = new SegmentTree<>(N -1){
      @Override
      protected void agg(Data v,Data a,Data b){ v.v = gcd(a.v,b.v); }

      @Override
      protected Data e(){ return new Data(0); }

      @Override
      protected Data init(int i){ return new Data(abs(A[i] -A[i +1])); }

      @Override
      protected void map(Data v,Integer f){}
    };
    SegmentTree<Data, Integer> segB = new SegmentTree<>(N -1){
      @Override
      protected void agg(Data v,Data a,Data b){ v.v = gcd(a.v,b.v); }

      @Override
      protected Data e(){ return new Data(0); }

      @Override
      protected Data init(int i){ return new Data(abs(B[i] -B[i +1])); }

      @Override
      protected void map(Data v,Integer f){}
    };

    while (Q-- > 0) {
      int h1 = in.idx();
      int h2 = in.idx();
      int w1 = in.idx();
      int w2 = in.idx();
      long a,b;
      if (h2 -h1 == 0 && w2 -w1 == 0) {
        out.println(A[h1] +B[w1]);
        continue;
      }
      if (h2 -h1 == 0)
        a = A[h1] +B[w1];
      else
        a = segA.get(h1,h2).v;
      if (w2 -w1 == 0)
        b = A[h1] +B[w1];
      else
        b = segB.get(w1,w2).v;
      out.println(gcd(A[h1] +B[w1],gcd(a,b)));
    }
    return null;
  }

  long gcd(long a,long b){
    while (0 < b) {
      long t = a;
      a = b;
      b = t %b;
    }
    return a;
  }
}

class Data extends BaseV{
  long v;

  public Data(long v){ this.v = v; }

  @Override
  public String toString(){ return "" +v; }
}

class PrefixSum{
  private long[] sum;
  private int i;

  public PrefixSum(int n){ sum = new long[n +1]; }

  public PrefixSum(long[] a){
    this(a.length);
    for (int i = 0;i < a.length;i++)
      sum[i +1] = sum[i] +a[i];
  }

  public void add(long a){ sum[i +1] = sum[i++] +a; }

  public long get(int l,int r){ return sum[r] -sum[l]; }

  public long get(int i){ return get(i,i +1); }
}

class PersistentUnionFind{
  int num;
  protected PersistentArray dat,nxt;

  public PersistentUnionFind(int n){
    dat = new PersistentArray(n);
    nxt = new PersistentArray(n);
    for (int i = 0;i < n;i++) {
      dat.set(i,-1,-1);
      nxt.set(i,i,-1);
    }
    num = n;
  }

  public int root(int x,int t){
    int d = dat.get(x,t);
    return d < 0 ? x : dat.set(x,root(d,t),t);
  }

  public boolean same(int u,int v,int t){ return root(u,t) == root(v,t); }

  public boolean unite(int u,int v,int t,int t2){
    if ((u = root(u,t)) == (v = root(v,t)))
      return false;

    if (dat.get(u,t) > dat.get(v,t)) {
      u ^= v;
      v ^= u;
      u ^= v;
    }
    dat.set(u,dat.get(u,t) +dat.get(v,t),t2);
    dat.set(v,u,t2);
    num--;
    var nu = nxt.get(u,t);
    var nv = nxt.get(v,t);
    nxt.set(u,nv,t2);
    nxt.set(v,nu,t2);
    return true;
  }

  public int size(int x,int t){ return -dat.get(root(x,t),t); }

  public int[] getGroup(int x,int t){
    int[] ret = new int[size(x,t)];
    for (int i = 0,c = root(x,t);i < ret.length;i++)
      ret[i] = c = nxt.get(c,t);
    return ret;
  }
}

class PersistentArray{
  private TreeMap<Integer, Integer>[] arr;

  @SuppressWarnings("unchecked")
  public PersistentArray(int n){
    arr = new TreeMap[n];
    setAll(arr,i -> new TreeMap<>());
  }

  public int get(int i,int t){ return arr[i].floorEntry(t).getValue(); }

  public int set(int i,int v,int t){
    arr[i].put(t,v);
    return v;
  }
}

abstract class AVLSegmentTree<V extends BaseV, F> {
  private V e = e();
  private Node root;

  public AVLSegmentTree(int n){ root = new Node(e(),n); }

  public AVLSegmentTree(){}

  public void build(int n,IntFunction<V> init){ root = build(0,n,init); }

  private Node build(int i,int n,IntFunction<V> init){
    if (n < 2)
      return n < 1 ? null : new Node(init.apply(i),1);
    var ret = new Node(e(),n);
    ret.cld(-1,build(i,n /2,init));
    ret.cld(1,build(i +n /2,n -n /2,init));
    return ret.merge();
  }

  public void add(V v){ add(v,1); }

  public void add(V v,int k){ ins(size(),v,k); }

  public void ins(int i,V v){ ins(i,v,1); }

  public void ins(int i,V v,int k){ root = root == null ? new Node(v,k) : ins(root,i,v,k); }

  private Node ins(Node nd,int i,V v,int k){
    if (nd.leaf && (i == 0 || i == nd.sz)) {
      split(nd,i == 0 ? 1 : -1,v,k,nd.sz +k);
      return nd.merge();
    }

    if (nd.leaf)
      split(nd,1,ag(e(),e,nd.val),i,nd.sz);
    else
      nd.push();

    if (i < nd.lft.sz)
      nd.cld(-1,ins(nd.lft,i,v,k));
    else
      nd.cld(1,ins(nd.rht,i -nd.lft.sz,v,k));

    return balance(nd);
  }

  public V del(int i){
    var ret = e();
    root = del(ret,root,i);
    return ret;
  }

  private Node del(V ret,Node nd,long i){
    if (nd.leaf) {
      nd.sz--;
      ag(ret,e,nd.val);
      return 0 < nd.sz ? nd : null;
    }
    nd.push();
    int c = i < nd.lft.sz ? -1 : 1;
    Node del = c < 0 ? del(ret,nd.lft,i) : del(ret,nd.rht,i -nd.lft.sz);
    if (del == null)
      return nd.cld(-c);
    nd.cld(c,del);
    return balance(nd);
  }

  public void upd(int i,F f){ upd(i,i +1,f); }

  public void upd(int l,int r,F f){
    if (l == r)
      return;
    if (size() < r)
      add(e(),r -size());
    root = upd(root,l,r,f);
  }

  private Node upd(Node nd,int l,int r,F f){
    if (l == 0 && r == nd.sz)
      return nd.prop(f);

    if (nd.leaf)
      split(nd,1,ag(e(),e,nd.val),0 < l ? l : r,nd.sz);
    else
      nd.push();

    if (l < nd.lft.sz)
      nd.cld(-1,upd(nd.lft,l,min(nd.lft.sz,r),f));
    if (nd.lft.sz < r)
      nd.cld(1,upd(nd.rht,max(0,l -nd.lft.sz),r -nd.lft.sz,f));
    return balance(nd);
  }

  public void toggle(int l,int r){ root = l < r ? toggle(root,l,r) : root; }

  private Node toggle(Node nd,int l,int r){
    nd.push();
    if (l == 0 && r == nd.sz)
      return nd.toggle();
    else if (r < nd.sz) {
      split(nd,r);
      return merge(toggle(nd.lft,l,r),nd,nd.rht);
    } else {
      split(nd,l);
      return merge(nd.lft,nd,toggle(nd.rht,0,r -l));
    }
  }

  private void split(Node nd,int i){
    if (nd.leaf)
      split(nd,1,ag(e(),e,nd.val),i,nd.sz);
    else {
      nd.push();
      if (i < nd.lft.sz) {
        split(nd.lft,i);
        var lft = nd.lft;
        nd.cld(-1,lft.lft);
        nd.cld(1,merge(lft.rht,lft,nd.rht));
      } else if (nd.lft.sz < i) {
        split(nd.rht,i -nd.lft.sz);
        var rht = nd.rht;
        nd.cld(1,rht.rht);
        nd.cld(-1,merge(nd.lft,rht,rht.lft));
      }
    }
  }

  private Node merge(Node lft,Node nd,Node rht){
    if (abs(lft.rnk -rht.rnk) < 2) {
      nd.cld(-1,lft);
      nd.cld(1,rht);
    } else if (lft.rnk > rht.rnk) {
      lft.push().cld(1,merge(lft.rht,nd,rht));
      nd = lft;
    } else if (lft.rnk < rht.rnk) {
      rht.push().cld(-1,merge(lft,nd,rht.lft));
      nd = rht;
    }
    return balance(nd);
  }

  public V get(int i){ return get(i,i +1); }

  public V get(int l,int r){
    V ret = e();
    if (root != null)
      get(ret,root,l,min(r,size()));
    return ret;
  }

  private void get(V ret,Node nd,int l,int r){
    if (l == 0 && r == nd.sz)
      ag(ret,ret,nd.val());
    else if (nd.leaf)
      ag(ret,ret,pw(nd.val,r -l));
    else {
      nd.push();
      if (l < nd.lft.sz)
        get(ret,nd.lft,l,min(nd.lft.sz,r));
      if (nd.lft.sz < r)
        get(ret,nd.rht,max(0,l -nd.lft.sz),r -nd.lft.sz);
    }
  }

  public V all(){ return root == null ? e : root.val(); }

  public int size(){ return root == null ? 0 : root.sz; }

  protected abstract V e();
  protected abstract void agg(V v,V a,V b);
  protected abstract void map(V v,F f);
  protected abstract F comp(F f,F g);

  private V ag(V v,V a,V b){
    agg(v,a,b);
    v.sz = a.sz +b.sz;
    return v;
  }

  protected void pow(V v,V a,int n){
    V x = e();
    for (ag(x,e,a);0 < n;n >>= 1,ag(x,x,x))
      if (0 < (n &1))
        ag(v,v,x);
  }

  private V pw(V a,int n){
    var ret = e();
    pow(ret,a,n);
    ret.sz = n;
    return ret;
  }

  private void split(Node nd,int c,V vl,int i,int sz){
    nd.cld(-c,new Node(vl,i));
    nd.cld(c,new Node(nd.val,sz -i));
    nd.val = e();
  }

  private Node balance(Node nd){ return (1 < abs(nd.bis = nd.rht.rnk -nd.lft.rnk) ? (nd = rotate(nd)) : nd).merge(); }

  private Node rotate(Node u){
    var v = u.cld(u.bis).push();
    if (u.bis *v.bis < -1)
      v = rotate(v);
    u.cld(u.bis,v.cld(-u.bis));
    v.cld(-u.bis,u);
    u.merge();
    return v;
  }

  private class Node{
    private int sz,rnk,bis,tog;
    private V val;
    private F laz;
    private Node lft,rht;
    private boolean leaf = true;

    private Node(V val,int sz){
      this.sz = sz;
      this.val = val;
      val.sz = 1;
    }

    private Node merge(){
      bis = rht.rnk -lft.rnk;
      rnk = max(lft.rnk,rht.rnk) +1;
      ag(val,lft.val(),rht.val());
      sz = val.sz;
      leaf = false;
      return this;
    }

    private Node push(){
      if (laz != null) {
        lft.prop(laz);
        rht.prop(laz);
        laz = null;
      }
      if (0 < tog) {
        lft.toggle();
        rht.toggle();
        tog = 0;
      }
      return this;
    }

    private Node prop(F f){
      map(val,f);
      if (!leaf)
        laz = laz == null ? f : comp(laz,f);
      return this;
    }

    private Node toggle(){
      bis *= -1;
      var tn = lft;
      lft = rht;
      rht = tn;
      val.toggle();
      if (!leaf)
        tog ^= 1;
      return this;
    }

    private Node cld(int c){ return c < 0 ? lft : rht; }

    private void cld(int c,Node nd){ nd = c < 0 ? (lft = nd) : (rht = nd); }

    private V val(){ return leaf && 1 < sz ? pw(val,sz) : val; }
  }
}

abstract class Seg<V extends BaseV, F> {
  private int n,log;
  private V[] val;
  private F[] lazy;

  @SuppressWarnings("unchecked")
  protected Seg(int n){
    this.n = n;
    while (1 <<log <= n)
      log++;
    val = (V[]) new BaseV[n <<1];
    lazy = (F[]) new Object[n];

    for (int i = -1;++i < n;)
      (val[i +n] = init(i)).sz = 1;
    for (int i = n;--i > 0;merge(i))
      (val[i] = e()).sz = val[i <<1].sz +val[i <<1 |1].sz;
  }

  public void upd(int i,F f){ prop(i +n,f); }

  public void upd(int l,int r,F f){
    for (l += n,r += n;l < r;l >>= 1,r >>= 1) {
      if ((l &1) == 1)
        prop(l++,f);
      if ((r &1) == 1)
        prop(--r,f);
    }
  }

  public V get(int i){ return val[i +n]; }

  public V get(int l,int r){
    V vl = e(),vr = e();
    for (l += n,r += n;l < r;l >>= 1,r >>= 1) {
      if ((l &1) == 1)
        ag(vl,vl,val[l++]);
      if ((r &1) == 1)
        ag(vr,val[--r],vr);
    }
    ag(vl,vl,vr);
    return vl;
  }

  public Deque<V> getList(int l,int r){
    Deque<V> ql = new ArrayDeque<>();
    Deque<V> qr = new ArrayDeque<>();
    for (l += n,r += n;l < r;l >>= 1,r >>= 1) {
      if ((l &1) == 1)
        ql.addLast(val[l++]);
      if ((r &1) == 1)
        qr.addFirst(val[--r]);
    }
    ql.addAll(qr);
    return ql;
  }

  protected abstract V e();

  protected V init(int i){ return e(); }

  protected void agg(V v,V a,V b){}

  private void ag(V v,V a,V b){
    agg(v,a,b);
    v.sz = a.sz +b.sz;
  }

  protected abstract void map(V v,F f);

  protected F comp(F f,F g){ return null; }

  protected void up(int l,int r){
    for (l = oddPart(l +n),r = oddPart(r +n);l != r;)
      merge(l > r ? (l >>= 1) : (r >>= 1));
    while (1 < l)
      merge(l >>= 1);
  }

  protected void down(int l,int r){
    int i = log;
    for (l = oddPart(l +n),r = oddPart(r +n);i > 0;i--) {
      push(l >>i);
      push(r >>i);
    }
  }

  private void merge(int i){ agg(val[i],val[i <<1],val[i <<1 |1]); }

  private void push(int i){
    if (lazy[i] != null) {
      prop(i <<1,lazy[i]);
      prop(i <<1 |1,lazy[i]);
      lazy[i] = null;
    }
  }

  private void prop(int i,F f){
    map(val[i],f);
    if (i < n) {
      lazy[i] = lazy[i] == null ? f : comp(lazy[i],f);
      if (val[i].fail) {
        push(i);
        merge(i);
      }
    }
  }

  private int oddPart(int i){ return i /(i &-i); }
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

abstract class BaseV{
  int sz;
  boolean fail;

  public void toggle(){}
}

class Functions extends Util{
  public Functions(MyReader in,MyWriter out,MyWriter log){ super(in,out,log); }

  protected long inv(long x,long mod){ return pow(x,mod -2,mod); }

  protected long pow(long x,long n){ return pow(x,n,Util.mod); }

  protected long pow(long x,long n,long mod){
    long ret = 1;
    for (x %= mod;0 < n;x = x *x %mod,n >>= 1)
      if ((n &1) == 1)
        ret = ret *x %mod;
    return ret;
  }

  protected int bSearchI(int o,int n,IntPredicate judge){
    if (!judge.test(o))
      return o -Integer.signum(n -o);
    for (int m = 0;1 < abs(n -o);)
      m = judge.test(m = o +n >>1) ? (o = m) : (n = m);
    return o;
  }

  protected long bSearchL(long o,long n,LongPredicate judge){
    for (long m = 0;1 < abs(n -o);)
      m = judge.test(m = o +n >>1) ? (o = m) : (n = m);
    return o;
  }

  protected double bSearchD(double o,double n,DoublePredicate judge){
    for (double m,c = 0;c < 100;c++)
      m = judge.test(m = (o +n) /2) ? (o = m) : (n = m);
    return o;
  }

  protected long ceil(long a,long b){ return (a +b -1) /b; }
}

class Util{
  public static String yes = "Yes",no = "No";
  public static int infI = (1 <<30) -1;
  public static long infL = (1L <<60 |1 <<30) -1;
  private long st = System.currentTimeMillis();
  public static Random rd = ThreadLocalRandom.current();
  public static long mod = 998244353;
  public MyReader in;
  public MyWriter out;
  public MyWriter log;

  public Util(MyReader in,MyWriter out,MyWriter log){
    this.in = in;
    this.out = out;
    this.log = log;
  }

  protected long elapsed(){ return System.currentTimeMillis() -st; }

  protected void reset(){ st = System.currentTimeMillis(); }

  public static int[] arrI(int N,IntUnaryOperator f){
    int[] ret = new int[N];
    setAll(ret,f);
    return ret;
  }

  public static long[] arrL(int N,IntToLongFunction f){
    long[] ret = new long[N];
    setAll(ret,f);
    return ret;
  }

  public static double[] arrD(int N,IntToDoubleFunction f){
    double[] ret = new double[N];
    setAll(ret,f);
    return ret;
  }

  public static <T> T[] arr(T[] arr,IntFunction<T> f){
    setAll(arr,f);
    return arr;
  }

  int[][] addId(int[][] T){
    return Util.arr(new int[T.length][],i -> {
      int[] t = copyOf(T[i],T[i].length +1);
      t[t.length -1] = i;
      return t;
    });
  }
}

class MyReader{
  private byte[] buf = new byte[1 <<16];
  private int ptr,tail;
  private InputStream in;

  public MyReader(InputStream in){ this.in = in; }

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

  public int it(){ return toIntExact(lg()); }

  public int[] it(int N){ return Util.arrI(N,i -> it()); }

  public int[][] it(int H,int W){ return Util.arr(new int[H][],i -> it(W)); }

  public int idx(){ return it() -1; }

  public int[] idx(int N){ return Util.arrI(N,i -> idx()); }

  public int[][] idx(int H,int W){ return Util.arr(new int[H][],i -> idx(W)); }

  public long lg(){
    byte i = nextPrintable();
    boolean negative = i == 45;
    long n = negative ? 0 : i -'0';
    while (isPrintable(i = read()))
      n = 10 *n +i -'0';
    return negative ? -n : n;
  }

  public long[] lg(int N){ return Util.arrL(N,i -> lg()); }

  public long[][] lg(int H,int W){ return Util.arr(new long[H][],i -> lg(W)); }

  public double dbl(){ return Double.parseDouble(str()); }

  public double[] dbl(int N){ return Util.arrD(N,i -> dbl()); }

  public double[][] dbl(int H,int W){ return Util.arr(new double[H][],i -> dbl(W)); }

  public char[] ch(){ return str().toCharArray(); }

  public char[][] ch(int H){ return Util.arr(new char[H][],i -> ch()); }

  public String line(){
    StringBuilder sb = new StringBuilder();
    for (byte c;(c = read()) != '\n';)
      sb.append((char) c);
    return sb.toString();
  }

  public String str(){
    StringBuilder sb = new StringBuilder();
    sb.append((char) nextPrintable());
    for (byte c;isPrintable(c = read());)
      sb.append((char) c);
    return sb.toString();
  }

  public String[] str(int N){ return Util.arr(new String[N],i -> str()); }
}

class MyWriter{
  private OutputStream out;
  private byte[] buf = new byte[1 <<16],ibuf = new byte[20];
  private int tail;
  private boolean autoflush;

  public MyWriter(OutputStream out,boolean autoflush){
    this.out = out;
    this.autoflush = autoflush;
  }

  public void flush(){
    try {
      out.write(buf,0,tail);
      tail = 0;
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void ln(){
    write((byte) '\n');
    if (autoflush)
      flush();
  }

  private void write(byte b){
    buf[tail++] = b;
    if (tail == buf.length)
      flush();
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
    while (i < ibuf.length)
      write(ibuf[i++]);
  }

  private void print(Object obj){
    if (obj instanceof Boolean)
      print((boolean) obj ? Util.yes : Util.no);
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
      print(Objects.toString(obj).toCharArray());
  }

  public void println(Object obj){
    if (obj == null)
      obj = "null";
    if (obj instanceof Iterable<?>)
      for (Object e:(Iterable<?>) obj)
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

  public void printlns(Object... o){
    print(o);
    ln();
  }
}

class Main{
  public static void main(String[] args) throws Exception{
    var in = new MyReader(System.in);
    var out = new MyWriter(System.out,false);
    var log = new MyWriter(System.err,true);
    int T = Solver.multi ? in.it() : 1;
    while (T-- > 0)
      Optional.ofNullable(new Solver(in,out,log)
          .solve()).ifPresent(out::println);
    out.flush();
  }
}
