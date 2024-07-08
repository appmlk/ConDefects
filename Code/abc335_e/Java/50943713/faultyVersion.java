import static java.lang.Math.*;
import static java.util.Arrays.*;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.function.*;

class Solver extends BaseSolver{
  public Solver(MyReader in,MyWriter out,MyWriter log){ super(in,out,log); }

  public static boolean multi = false;

  public Object solve(){
    int N = in.it();
    int M = in.it();
    int[] A = in.idx(N);
    List<List<Integer>> g = new ArrayList<>();
    for (int i = 0;i < N;i++)
      g.add(new ArrayList<>());
    for (int i = 0;i < M;i++) {
      int u = in.idx();
      int v = in.idx();
      g.get(u).add(v);
      g.get(v).add(u);
    }
    List<List<Integer>> list = new ArrayList<>();
    for (int i = 0;i < 200000;i++)
      list.add(new ArrayList<>());
    for (int i = 0;i < N;i++)
      list.get(A[i]).add(i);
    int[] num = new int[N];
    fill(num,-infI);
    num[0] = 1;
    for (var que:list) {
      que.sort(Comparator.comparing(i -> num[i]));
      while (!que.isEmpty()) {
        int u = que.remove(que.size() -1);
        for (int v:g.get(u)) {
          if (A[u] > A[v])
            continue;
          if (A[u] == A[v] && num[v] < num[u]) {
            num[v] = num[u];
            que.add(v);
          }
          if (A[u] < A[v] && num[v] < num[u] +1)
            num[v] = num[u] +1;
        }
      }
    }
    return num[N -1];
  }

  <T extends BaseV> void log(AVLTree<Data> seg,int n){
    Data[] a = new Data[n];
    for (int i = 0;i < n;i++)
      a[i] = seg.get(i);
    for (int i = 1;i < n;i++)
      assert a[i -1].v < a[i].v;
    log.println(a);
  }

  private long hash(int[] A){
    long ret = 0;
    for (var a:A) {
      ret = mul(ret,mod) +a;
      if (ret >= MOD)
        ret -= MOD;
    }
    return ret;
  }

  final static long MASK30 = (1L <<30) -1;
  final static long MASK31 = (1L <<31) -1;
  final static long MOD = (1L <<61) -1;

  private static long mul(final long l,final long r){
    final long lu = l >>31;
    final long ld = l &MASK31;
    final long ru = r >>31;
    final long rd = r &MASK31;
    final long middleBit = ld *ru +lu *rd;
    return mod((lu *ru <<1) +ld *rd +((middleBit &MASK30) <<31) +(middleBit >>30));
  }

  private static long mod(long val){
    while (val < 0)
      val += MOD;
    val = (val &MOD) +(val >>61);
    return val > MOD ? val -MOD : val;
  }
}

class Data extends BaseV{
  long v;

  public Data(long v){ this.v = v; }

  @Override
  public String toString(){ return "" +v; }
}

class RollingHash{
  private static long MASK30 = (1L <<30) -1;
  private static long MASK31 = (1L <<31) -1;
  private static long MOD = (1L <<61) -1;
  public static long m = base();
  private static long[] pow = {1};
  int n;
  private long[] hash,S;
  private boolean updatable;
  private RollingHash rev;

  public RollingHash(char[] S,boolean updatable){ this(S.length,i -> S[i],updatable); }

  public RollingHash(int[] S,boolean updatable){ this(S.length,i -> S[i],updatable); }

  public RollingHash(long[] S,boolean updatable){ this(S.length,i -> S[i],updatable); }

  public RollingHash(int n,IntToLongFunction f,boolean updatale){
    S = new long[n];
    updatable = updatale;
    this.n = S.length;
    hash = new long[n +1];
    setPow(n);
    for (int i = 0;i < n;i++)
      set(i,f.applyAsLong(i));
  }

  public long get(int l,int r){
    if (l > r)
      return (rev == null ? rev = rev() : rev).get(n -l,n -r);
    return mod(hash(r) -mul(hash(l),pow[r -l]));
  }

  public void upd(int i,long v){
    assert updatable;
    set(i,v);
    if (rev != null)
      rev.set(n -i -1,v);
  }

  private void set(int i,long v){
    if (updatable)
      for (int x = i +1;x <= n;x += x &-x)
        hash[x] = mod(hash[x] +mul(v -S[i],pow[x -i -1]));
    else
      hash[i +1] = mod(mul(hash[i],m) +v);
    S[i] = v;
  }

  private long hash(int i){
    long ret = 0;
    if (updatable)
      for (int x = i;x > 0;x -= x &-x)
        ret = mod(ret +mul(hash[x],pow[i -x]));
    else
      ret = hash[i];
    return ret;
  }

  private void setPow(int n){
    if (n < pow.length)
      return;
    int s = pow.length;
    pow = copyOf(pow,max(pow.length <<1,n +1));
    for (int i = s;i < pow.length;i++)
      pow[i] = mul(pow[i -1],m);
  }

  private RollingHash rev(){
    long[] s = new long[n];
    for (int i = 0;i < n;i++)
      s[i] = S[n -1 -i];
    return new RollingHash(s,updatable);
  }

  private static long mul(long a,long b){
    long lu = a >>31;
    long ld = a &MASK31;
    long ru = b >>31;
    long rd = b &MASK31;
    long mid = ld *ru +lu *rd;
    return mod((lu *ru <<1) +ld *rd +((mid &MASK30) <<31) +(mid >>30));
  }

  private static long mod(long val){
    while (val < 0)
      val += MOD;
    val = (val &MOD) +(val >>61);
    return val > MOD ? val -MOD : val;
  }

  private static long pow(long x,long n){
    long ret = 1;
    do {
      if ((n &1) == 1)
        ret = mul(ret,x);
      x = mul(x,x);
    } while (0 < (n >>= 1));
    return ret;
  }

  private static long base(){
    long m = 0;
    for (int k = 1;m < Util.infI;m = pow(37,k))
      while (!isPrimeRoot(k))
        k = ThreadLocalRandom.current().nextInt(Util.infI);
    return m;
  }

  private static boolean isPrimeRoot(long a){
    long b = MOD -1;
    while (0 < b) {
      long t = a;
      a = b;
      b = t %b;
    }
    return a > 1;
  }
}

abstract class AVLTree<V extends BaseV> {
  private V e = e(),t = e();
  private Node root;
  private Comparator<V> cmp;

  public AVLTree(){ this(Util.cast(Comparator.naturalOrder())); }

  public AVLTree(Comparator<V> cmp){ this.cmp = cmp; }

  public void add(V v){
    if (root == null)
      root = new Node(v,1);
    else
      root = add(root,v);
  }

  private Node add(Node nd,V v){
    if (nd.leaf) {
      int c = cmp.compare(nd.val,v);
      if (c == 0) {
        nd.sz++;
        return nd;
      } else {
        var ret = new Node(e(),0);
        ret.cld(-c,new Node(v,1));
        ret.cld(c,nd);
        nd = ret;
      }
    } else {
      int c = cmp.compare(v,nd.rht.l);
      nd.cld(-1,c < 0 ? add(nd.lft,v) : nd.lft);
      nd.cld(1,c < 0 ? nd.rht : add(nd.rht,v));
    }
    return balance(nd);
  }

  public V del(V v){
    var ret = e();
    root = del(ret,root,v);
    return ret;
  }

  private Node del(V ret,Node nd,V v){
    if (nd.leaf) {
      int c = cmp.compare(nd.val,v);
      if (c == 0) {
        nd.sz--;
        ag(ret,e,nd.val);
      }
      return c != 0 || 0 < nd.sz ? nd : null;
    }
    int c = cmp.compare(v,nd.rht.l) *2 +1;
    Node del = del(ret,c < 0 ? nd.lft : nd.rht,v);
    if (del == null)
      return nd.cld(-c);
    nd.cld(c,del);
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

  private V ag(V v,V a,V b){
    agg(v,a,b);
    v.sz = a.sz +b.sz;
    return v;
  }

  protected void pow(V v,V a,int n){
    for (ag(t,e,a);0 < n;n >>= 1,ag(t,t,t))
      if (0 < (n &1))
        ag(v,v,t);
  }

  private V pw(V a,int n){
    V ret = e();
    pow(ret,a,n);
    ret.sz = n;
    return ret;
  }

  private Node balance(Node nd){ return (1 < abs(nd.bis = nd.rht.rnk -nd.lft.rnk) ? (nd = rotate(nd)) : nd).merge(); }

  private Node rotate(Node u){
    var v = u.cld(u.bis);
    if (u.bis *v.bis < -1)
      v = rotate(v);
    u.cld(u.bis,v.cld(-u.bis));
    v.cld(-u.bis,u);
    u.merge();
    return v;
  }

  private class Node{
    private int bis,rnk;
    private int sz;
    private V val,l;
    private Node lft,rht;
    private boolean leaf = true;

    private Node(V val,int sz){
      this.sz = sz;
      this.val = l = val;
      val.sz = 1;
    }

    private Node merge(){
      bis = rht.rnk -lft.rnk;
      rnk = max(lft.rnk,rht.rnk) +1;
      ag(val,lft.val(),rht.val());
      l = lft.l;
      sz = val.sz;
      leaf = false;
      return this;
    }

    private Node cld(int c){ return c < 0 ? lft : rht; }

    private void cld(int c,Node nd){ nd = c < 0 ? (lft = nd) : (rht = nd); }

    private V val(){ return leaf && 1 < sz ? pw(val,sz) : val; }
  }
}

abstract class KetaDp<T> {
  private int B;
  private int[] N;
  private T[] dp;

  public KetaDp(char[] N){ this(N,10); }

  public KetaDp(char[] N,int B){
    this.N = new int[N.length];
    for (int i = 0;i < N.length;i++)
      this.N[i] = N[i] -'0';
    dp = Util.cast(Array.newInstance(init().getClass(),N.length +1 <<1));
    this.B = B;
    setAll(dp,i -> init());
  }

  protected abstract T init();
  protected abstract void f(T pd,T dp,int n,int k);

  protected void mod(T dp){}

  public T get(int i,int same){ return dp[i *2 +same]; }

  public void calc(){
    for (int i = 0;i < N.length;i++) {
      int t = N[i];
      for (int n = 0;n < B;n++) {
        if (n == t)
          f(get(i +1,1),get(i,1),n,N.length -1 -i);
        if (n < t)
          f(get(i +1,0),get(i,1),n,N.length -1 -i);
        if (0 < i)
          f(get(i +1,0),get(i,0),n,N.length -1 -i);
      }
      mod(get(i +1,0));
      mod(get(i +1,1));
    }
  }
}

abstract class LazySegmentTree<V extends BaseV, F> extends Seg<V, F>{
  public LazySegmentTree(int n){ super(n); }

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

abstract class Seg<V extends BaseV, F> {
  private int n,log;
  private V[] val;
  private F[] lazy;

  protected Seg(int n){
    this.n = n;
    while (1 <<log <= n)
      log++;
    val = Util.cast(new BaseV[n <<1]);
    lazy = Util.cast(new Object[n]);

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
    V[] ret = Util.cast(new BaseV[]{e(), e()});
    int i = 0;
    for (var v:getList(l,r)) {
      agg(ret[i],ret[i ^1],v);
      ret[i].sz = ret[i ^= 1].sz +v.sz;
    }
    return ret[i ^1];
  }

  public V[] getList(int l,int r){
    int sz = 0;
    for (int li = l += n,ri = r += n;li < ri;li = li +1 >>1,ri >>= 1)
      sz += (li &1) +(ri &1);
    V[] arr = Util.cast(Array.newInstance(e().getClass(),sz));
    for (int i = 0;l < r;l >>= 1,r >>= 1) {
      if ((l &1) > 0)
        arr[i++] = val[l++];
      if ((r &1) > 0)
        arr[--sz] = val[--r];
    }
    return arr;
  }

  public V[] getPath(int i){
    int sz = 32 -Integer.numberOfLeadingZeros(i +n);
    V[] arr = Util.cast(Array.newInstance(e().getClass(),sz));
    for (i += n;0 < i;i >>= 1)
      arr[--sz] = val[i];
    return arr;
  }

  protected V init(int i){ return e(); }

  protected abstract V e();
  protected abstract void agg(V v,V a,V b);
  protected abstract void map(V v,F f);
  protected abstract F comp(F f,F g);

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
  protected F comp(F f,F g){ return null; }

  @Override
  public void upd(int i,F f){
    super.upd(i,f);
    up(i,i +1);
  }
}

abstract class DualSegmentTree<V extends BaseV, F> extends Seg<V, F>{
  public DualSegmentTree(int n){ super(n); }

  @Override
  protected void agg(V v,V a,V b){}

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

class UnionFind{
  int num;
  protected int[] dat;
  protected int[] nxt;

  public UnionFind(int n){
    dat = new int[n];
    nxt = new int[n];
    setAll(nxt,i -> i);
    fill(dat,-1);
    num = n;
  }

  public int root(int x){ return dat[x] < 0 ? x : (dat[x] = root(dat[x])); }

  public boolean same(int u,int v){ return root(u) == root(v); }

  public boolean unite(int u,int v){
    if ((u = root(u)) == (v = root(v)))
      return false;

    if (dat[u] > dat[v]) {
      u ^= v;
      v ^= u;
      u ^= v;
    }
    dat[u] += dat[v];
    dat[v] = u;
    num--;
    nxt[u] ^= nxt[v];
    nxt[v] ^= nxt[u];
    nxt[u] ^= nxt[v];
    return true;
  }

  public int size(int x){ return -dat[root(x)]; }

  public int[] getGroup(int x){
    int[] ret = new int[size(x)];
    for (int i = 0,c = root(x);i < ret.length;i++)
      ret[i] = c = nxt[c];
    return ret;
  }
}

abstract class BaseV{
  public int sz;
  public boolean fail;
}

class MyStack<T> extends MyList<T>{
  public T pop(){ return remove(size() -1); }

  public T peek(){ return get(size() -1); }
}

class MyList<T> implements Iterable<T>{
  private T[] arr;
  private int sz;

  public MyList(){ this(16); }

  public MyList(int n){ arr = Util.cast(new Object[n]); }

  public boolean isEmpty(){ return sz == 0; }

  public int size(){ return sz; }

  public T get(int i){ return arr[i]; }

  public void add(T t){ (arr = sz < arr.length ? arr : copyOf(arr,sz *5 >>2))[sz++] = t; }

  public T remove(int i){
    var ret = arr[i];
    sz--;
    for (int j = i;j < sz;j++)
      arr[j] = arr[j +1];
    return ret;
  }

  public T removeFast(int i){
    var ret = arr[i];
    arr[i] = arr[--sz];
    return ret;
  }

  public void sort(){ sort(Util.cast(Comparator.naturalOrder())); }

  public void sort(Comparator<T> cmp){ Arrays.sort(arr,0,sz,cmp); }

  @Override
  public Iterator<T> iterator(){
    return new Iterator<>(){
      int i = 0;

      @Override
      public boolean hasNext(){ return i < sz; }

      @Override
      public T next(){ return arr[i++]; }
    };
  }

  public <U> MyList<U> map(Function<T, U> func){
    MyList<U> ret = new MyList<>(sz);
    forEach(t -> ret.add(func.apply(t)));
    return ret;
  }

  public T[] toArray(){ return copyOf(arr,sz); }

  public void swap(int i,int j){
    var t = arr[i];
    arr[i] = arr[j];
    arr[j] = t;
  }

  public void set(int i,T t){ arr[i] = t; }
}

class BaseSolver extends Util{
  public MyReader in;
  public MyWriter out,log;

  public BaseSolver(MyReader in,MyWriter out,MyWriter log){
    this.in = in;
    this.out = out;
    this.log = log;
  }

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

  protected long gcd(long a,long b){
    while (0 < b) {
      long t = a;
      a = b;
      b = t %b;
    }
    return a;
  }

  protected long ceil(long a,long b){ return (a +b -1) /b; }
}

class Util{
  public static String yes = "Yes",no = "No";
  public static int infI = (1 <<30) -1;
  public static long infL = (1L <<60 |1 <<30) -1,
      mod = 998244353;
  public static Random rd = ThreadLocalRandom.current();
  private long st = System.currentTimeMillis();

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

  public int[][] addId(int[][] T){
    return arr(new int[T.length][],i -> {
      int[] t = copyOf(T[i],T[i].length +1);
      t[t.length -1] = i;
      return t;
    });
  }

  @SuppressWarnings("unchecked")
  public static <T> T cast(Object obj){ return (T) obj; }
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
