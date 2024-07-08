import static java.lang.Math.*;
import static java.util.Arrays.*;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

class Solver extends BaseSolver{
  public Solver(MyReader in,MyWriter out,MyWriter log){ super(in,out,log); }

  public static boolean multi = false;

  public Object solve(){
    int N = in.it();
    int[][] E = in.idx(N -1,2);
    long[] C = in.lg(N);
    ReRootingDp<Long, long[], Long> dp = new ReRootingDp<>(N){
      @Override
      protected long[] e(){ return new long[]{0, 0}; }

      @Override
      protected long[] agg(long[] a,long[] b){ return new long[]{a[0] +b[0], a[1] +b[1]}; }

      @Override
      protected long[] adj(long[] v,Edge<Long> e){ return new long[]{v[0] +C[e.v], v[0] +C[e.v] +v[1]}; }

      @Override
      protected Long ans(int u,long[] sum){
        long ret = 0;
        for (var d:sur(u))
          ret += d[1];
        return ret;
      }
    };
    for (var e:E)
      dp.addEdge(e[0],e[1]);

    long ans = Long.MAX_VALUE;
    Long[] calc = dp.calc();
    for (int i = 0;i < N;i++)
      ans = min(ans,calc[i]);

    return ans;
  }
}

abstract class ReRootingDp<L, D, A> extends Graph<L>{
  private D[] dp;
  private A[] ans;

  public ReRootingDp(int N){
    super(N,false);
    dp = Util.cast(new Object[2 *N]);
    ans = Util.cast(Array.newInstance(ans(0,e()).getClass(),n));
  }

  protected abstract D e();
  protected abstract D agg(D a,D b);
  protected abstract D adj(D v,Edge<L> e);
  protected abstract A ans(int u,D sum);

  protected MyList<D> sur(int u){ return go(u).map(e -> dp[e.id]); }

  public A[] calc(){
    for (var e:es)
      e.re.id = e.id +n;

    var stk = new MyStack<Edge<L>>();
    var se = new Edge<L>(n -1,-1,0,null);
    stk.add(se);
    while (!stk.isEmpty()) {
      var e = stk.pop();
      if (dp[e.id] == null) {
        dp[e.id] = e();
        for (var ee:go(e.v))
          if (ee != e.re) {
            stk.add(ee);
            stk.add(ee);
          }
      } else {
        for (var ee:go(e.v))
          if (ee != e.re)
            dp[e.id] = agg(dp[e.id],dp[ee.id]);
        if (e.u > -1)
          dp[e.id] = adj(dp[e.id],e);
      }
    }
    stk.add(se);
    while (!stk.isEmpty()) {
      var e = stk.pop();
      var es = go(e.v);
      int n = es.size();
      D[] pre = Util.cast(new Object[n +1]),suf = Util.cast(new Object[n +1]);
      pre[0] = e();
      suf[n] = e();
      for (int i = 0;i < n;i++) {
        pre[i +1] = agg(pre[i],dp[es.get(i).id]);
        suf[n -1 -i] = agg(dp[es.get(n -1 -i).id],suf[n -i]);
      }

      ans[e.v] = ans(e.v,suf[0]);

      for (int i = 0;i < n;i++) {
        Edge<L> ee = es.get(i);
        if (ee != e.re) {
          dp[ee.re.id] = adj(agg(pre[i],suf[i +1]),ee.re);
          stk.add(ee);
        }
      }
    }
    return ans;
  }
}

class Edge<L> {
  public int id,u,v;
  public L val;
  public Edge<L> re;

  public Edge(int id,int u,int v,L val){
    this.id = id;
    this.u = u;
    this.v = v;
    this.val = val;
  }
}

class Graph<L> {
  public int n;
  public MyList<Edge<L>> es;
  private MyList<Edge<L>>[] go,bk;

  public Graph(int n,boolean dir){
    this.n = n;
    go = Util.cast(new MyList[n]);
    bk = dir ? Util.cast(new MyList[n]) : go;
    for (int i = 0;i < n;i++) {
      go[i] = new MyList<>();
      bk[i] = new MyList<>();
    }
    es = new MyList<>();
  }

  protected L inv(L l){ return l; }

  public void addEdge(int u,int v){ addEdge(u,v,null); }

  public void addEdge(int u,int v,L l){
    var e = new Edge<>(es.size(),u,v,l);
    var re = new Edge<>(e.id,e.v,e.u,inv(e.val));
    es.add(e);
    go[u].add(re.re = e);
    bk[v].add(e.re = re);
  }

  public MyList<Edge<L>> go(int u){ return go[u]; }

  public MyList<Edge<L>> bk(int u){ return bk[u]; }
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

abstract class AVLSegmentTree<V extends BaseV, F> {
  private V e = e(),t = e();
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
    if (nd.lft == null && (i == 0 || i == nd.sz)) {
      split(nd,i == 0 ? 1 : -1,v,k,nd.sz +k);
      return nd.merge();
    }

    if (nd.lft == null)
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

  private Node del(V ret,Node nd,int i){
    if (nd.lft == null) {
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

    if (nd.lft == null)
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
    if (0 < l) {
      split(nd,l);
      return merge(nd.lft,nd,toggle(nd.rht,0,r -l));
    }
    if (r < nd.sz) {
      split(nd,r);
      return merge(toggle(nd.lft,l,r),nd,nd.rht);
    }
    return nd.toggle();
  }

  private void split(Node nd,int i){
    if (nd.lft == null)
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
    else if (nd.lft == null)
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
  protected abstract void tog(V v);

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
    private int sz,bis,rnk,tog;
    private V val;
    private F laz;
    private Node lft,rht;

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
      if (lft != null)
        laz = laz == null ? f : comp(laz,f);
      return this;
    }

    private Node toggle(){
      bis *= -1;
      var tn = lft;
      lft = rht;
      rht = tn;
      tog(val);
      if (lft != null)
        tog ^= 1;
      return this;
    }

    private Node cld(int c){ return c < 0 ? lft : rht; }

    private void cld(int c,Node nd){ nd = c < 0 ? (lft = nd) : (rht = nd); }

    private V val(){ return lft == null && 1 < sz ? pw(val,sz) : val; }
  }
}

abstract class SparseTable2D{
  int h,w,hl,wl;
  long[][][][] tbl;

  SparseTable2D(int h,int w){
    hl = max(1,32 -Integer.numberOfLeadingZeros((this.h = h) -1));
    wl = max(1,32 -Integer.numberOfLeadingZeros((this.w = w) -1));
    tbl = new long[hl][wl][][];
    for (int hi = 0;hi < hl;hi++)
      for (int wi = 0;wi < wl;wi++) {
        int hhl = h -(1 <<hi) +1;
        int wwl = w -(1 <<wi) +1;
        tbl[hi][wi] = new long[hhl][wwl];
        for (int i = 0;i < hhl;i++)
          for (int j = 0;j < wwl;j++)
            if ((hi |wi) == 0)
              tbl[0][0][i][j] = init(i,j);
            else if (0 < hi)
              tbl[hi][wi][i][j] = agg(tbl[hi -1][wi][i][j],tbl[hi -1][wi][i +(1 <<hi -1)][j]);
            else
              tbl[hi][wi][i][j] = agg(tbl[hi][wi -1][i][j],tbl[hi][wi -1][i][j +(1 <<wi -1)]);
      }
  }

  abstract protected long init(int i,int j);
  abstract protected long agg(long a,long b);

  long get(int i0,int j0,int i1,int j1){
    int il = max(0,31 -Integer.numberOfLeadingZeros(i1 -i0 -1));
    int jl = max(0,31 -Integer.numberOfLeadingZeros(j1 -j0 -1));
    i1 = max(0,i1 -(1 <<il));
    j1 = max(0,j1 -(1 <<jl));
    long[][] tmp = tbl[il][jl];
    long ret = agg(tmp[i0][j0],tmp[i0][j1]);
    ret = agg(ret,tmp[i1][j0]);
    ret = agg(ret,tmp[i1][j1]);
    return ret;
  }
}

abstract class SparseTable{
  int n;
  long[] tbl;

  SparseTable(int n){
    int K = max(1,32 -Integer.numberOfLeadingZeros(n -1));
    this.n = 1 <<K;

    tbl = new long[K *this.n];
    for (int i = 0;i < this.n;i++)
      tbl[i] = i < n ? init(i) : 0;
    for (int k = 1;k < K;k++)
      for (int s = 1 <<k;s < this.n;s += 2 <<k) {
        int b = k *this.n;
        tbl[b +s] = s < n ? init(s) : 0;
        tbl[b +s -1] = s < n ? init(s -1) : 0;
        for (int i = 1;i < 1 <<k;i++) {
          tbl[b +s +i] = agg(tbl[b +s +i -1],tbl[s +i]);
          tbl[b +s -1 -i] = agg(tbl[b +s -i],tbl[s -1 -i]);
        }
      }
  }

  abstract protected long init(int i);
  abstract protected long agg(long a,long b);

  long get(int l,int r){
    r--;
    if (l == r)
      return tbl[l];
    int k = 31 -Integer.numberOfLeadingZeros(l ^r);
    return agg(tbl[k *n +l],tbl[k *n +r]);
  }
}

abstract class Sum2D{
  private long[] sum;
  private int w;

  public Sum2D(int h,int w){
    this.w = w;
    sum = new long[(h +1) *(w +1)];
    for (int i = 0;i < h;i++)
      for (int j = 0;j < w;j++)
        sum[top(i +1,j +1)] = a(i,j) +sum[top(i +1,j)] +sum[top(i,j +1)] -sum[top(i,j)];
  }

  abstract long a(int i,int j);

  private int top(int i,int j){ return i *(w +1) +j; }

  long get(int il,int ir,int jl,int jr){ return sum[top(ir,jr)] -sum[top(il,jr)] -sum[top(ir,jl)] +sum[top(il,jl)]; }
}

class Data extends BaseV{
  long v;

  public Data(long v){ this.v = v; }

  @Override
  public String toString(){ return "" +v; }
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

  protected long inv(long x){ return pow(x,mod -2); }

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

  public long lcm(long a,long b){ return b /gcd(a,b) *a; }

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
