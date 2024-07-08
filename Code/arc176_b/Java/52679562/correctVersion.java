import static java.lang.Math.*;
import static java.util.Arrays.*;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

public final class Main{
  public static void main(String[] args) throws Exception{
    MyReader in = new MyReader(System.in);
    MyWriter out = new MyWriter(System.out,false),log = new MyWriter(System.err,true);
    int T = Solver.multi ? in.it() : 1;
    while (T-- > 0)
      Optional.ofNullable(new Solver(in,out,log).solve()).ifPresent(out::println);
    out.flush();
  }
}

class Solver extends BaseSolver{
  public Solver(MyReader in,MyWriter out,MyWriter log){ super(in,out,log); }

  public static boolean multi = true;

  public Object solve(){
    mod = 10;
    long N = in.lg();
    long M = in.lg();
    long K = in.lg();
    if (M -K == 1 && N >= K -1)
      return 0;
    long v = N %(M -K);
    v += (M -1 -v) /(M -K) *(M -K);
    if (M > N)
      v = N;
    return pow(2,v);
  }
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

class Permutation{
  private int n;
  int[] arr;

  public Permutation(int n){ this(Util.arrI(n,i -> i)); }

  public Permutation(int[] arr){
    n = arr.length;
    this.arr = copyOf(arr,n);
  }

  public boolean increment(){ return crement(1); }

  public boolean decrement(){ return crement(-1); }

  private boolean crement(int d){
    int l = n -2;
    while (0 <= l && arr[l] *d >= arr[l +1] *d)
      l--;

    if (l < 0)
      return false;

    int r = n -1;
    while (arr[l] *d >= arr[r] *d)
      r--;
    swap(l,r);

    l++;
    r = n -1;
    while (l < r)
      swap(l++,r--);

    return true;
  }

  private void swap(int l,int r){
    arr[l] ^= arr[r];
    arr[r] ^= arr[l];
    arr[l] ^= arr[r];
  }
}

class Prime{
  private long[] spf,
      arrI = {2, 7, 61},
      arrL = {2, 325, 9375, 28178, 450775, 9780504, 1795265022};

  public Prime(){ this(1_000_000); }

  public Prime(int n){
    spf = new long[n +1];
    Arrays.setAll(spf,i -> i);
    for (int p = 2;p *p <= n;p++)
      if (spf[p] == p)
        for (int l = p *p;l <= n;l += p)
          spf[l] = p;
  }

  public long[] divisors(long n){
    long[] fs = factorize(n);
    int l = fs.length > 0 ? 2 : 1,id = 0;
    for (int i = 1,sz = 1;i < fs.length;i++,l += sz)
      if (fs[i -1] < fs[i])
        sz = l;

    long[] ret = new long[l];
    ret[id++] = 1;
    for (int i = 0,s = 0,sz = 1;i < fs.length;i++,s += sz) {
      if (0 < i && fs[i -1] < fs[i]) {
        sz = id;
        s = 0;
      }
      for (int j = s;j < s +sz;j++)
        ret[id++] = ret[j] *fs[i];
    }
    sort(ret);
    return ret;
  }

  public long[] factorize(long n){
    if (n < 2)
      return new long[0];
    long[] ret = new long[64];
    int h = 0,t = 0;
    ret[t++] = n;
    while (h < t) {
      long cur = ret[h++];
      if (!isPrime(cur)) {
        var p = rho(cur);
        ret[--h] = p;
        ret[t++] = cur /p;
      }
    }
    sort(ret,0,t);
    return copyOf(ret,t);
  }

  public boolean isPrime(long n){
    if (n < spf.length)
      return 1 < n && spf[(int) n] == n;
    if ((n &1) == 0)
      return false;
    ModInt bm = new ModInt(n);
    long lsb = n -1 &-n +1;
    long m = (n -1) /lsb;
    a:for (var a:n < 1 <<30 ? arrI : arrL) {
      long z = bm.pow(a %n,m);
      if (z < 2)
        continue;
      for (long k = 1;k <= lsb;k <<= 1)
        if (z == n -1)
          continue a;
        else
          z = bm.mul(z,z);
      return false;
    }
    return true;
  }

  private long rho(long n){
    if (n < spf.length)
      return spf[(int) n];
    if ((n &1) == 0)
      return 2;
    ModInt bm = new ModInt(n);
    for (long t;;) {
      long x = 0,y = x,q = 1,c = Util.rd.nextLong(n -1) +1;
      a:for (int k = 1;;k <<= 1,y = x,q = 1)
        for (int i = k;i-- > 0;) {
          x = bm.mul(x,x) +c;
          if (n <= x)
            x -= n;
          q = bm.mul(q,abs(x -y));
          if ((i &127) == 0 && (t = gcd(q,n)) > 1)
            break a;
        }
      if (t < n)
        return t;
    }
  }

  private long gcd(long a,long b){
    while (0 < b) {
      long t = a;
      a = b;
      b = t %b;
    }
    return a;
  }

  class ModInt{
    private long n,r2,ni;

    public ModInt(long n){
      this.n = n;
      r2 = (1L <<62) %n;
      for (int i = 0;i < 66;i++) {
        r2 <<= 1;
        if (r2 >= n)
          r2 -= n;
      }
      ni = n;
      for (int i = 0;i < 5;++i)
        ni *= 2 -n *ni;
    }

    private static long high(long x,long y){ return multiplyHigh(x,y) +(x >>63 &y) +(y >>63 &x); }

    private long mr(long x,long y){ return high(x,y) +high(-ni *x *y,n) +(x *y == 0 ? 0 : 1); }

    public long mod(long x){ return x < n ? x : x -n; }

    public long mul(long x,long y){ return mod(mr(mr(x,r2),y)); }

    public long pow(long x,long y){
      long z = mr(x,r2);
      long r = 1;
      while (y > 0) {
        if ((y &1) == 1)
          r = mr(r,z);
        z = mr(z,z);
        y >>= 1;
      }
      return mod(r);
    }
  }
}

class Combin{
  int n = 2;
  long[] f,fi;
  long mod = Util.mod;

  public Combin(int n){
    this();
    grow(n);
  }

  public Combin(){ f = fi = new long[]{1, 1}; }

  public void grow(int n){
    n = min((int) mod,n);
    f = copyOf(f,n);
    fi = copyOf(fi,n);
    for (int i = this.n;i < n;i++)
      f[i] = f[i -1] *i %mod;
    fi[n -1] = pow(f[n -1],mod -2);
    for (int i = n;--i > this.n;)
      fi[i -1] = fi[i] *i %mod;
    this.n = n;
  }

  private long pow(long x,long n){
    long ret = 1;
    for (x %= mod;0 < n;x = x *x %mod,n >>= 1)
      if ((n &1) == 1)
        ret = ret *x %mod;
    return ret;
  }

  public long nHr(int n,int r){ return r < 0 ? 0 : nCr(n +r -1,r); }

  public long nCr(int n,int r){
    if (r < 0 || n -r < 0)
      return 0;

    if (this.n <= n)
      grow(max(this.n <<1,n +1));
    return f[n] *(fi[r] *fi[n -r] %mod) %mod;
  }
}

class Data extends BaseV{
  char v,rev;

  public Data(char v){
    this.v = v;
    rev = (char) (v ^32);
  }

  @Override
  public String toString(){ return "" +v; }
}

abstract class RelationalUnionFind<F> extends UnionFind{
  private F[] dist;

  @SuppressWarnings("unchecked")
  public RelationalUnionFind(int n){
    super(n);
    dist = (F[]) new Object[n];
    setAll(dist,i -> id());
  }

  protected abstract F id();
  protected abstract F comp(F a,F b);
  protected abstract F inv(F v);
  protected abstract boolean eq(F a,F b);

  @Override
  public int root(int x){
    if (dat[x] < 0)
      return x;

    int r = root(dat[x]);
    dist[x] = comp(dist[dat[x]],dist[x]);

    return dat[x] = r;
  }

  public boolean valid(int u,int v,F c){ return !same(u,v) || eq(dist(u,v),c); }

  @Deprecated
  @Override
  public boolean unite(int u,int v){ return unite(u,v,id()); }

  public boolean unite(int u,int v,F f){
    if (!valid(u,v,f))
      return false;
    if (same(u,v))
      return true;
    f = comp(dist(u),f);
    f = comp(f,inv(dist(v)));
    super.unite(u = root(u),v = root(v));
    if (dat[u] > dat[v])
      dist[u] = inv(f);
    else
      dist[v] = f;
    return true;
  }

  public F dist(int x){
    root(x);
    return dist[x];
  }

  public F dist(int u,int v){ return !same(u,v) ? null : comp(inv(dist(u)),dist(v)); }
}

abstract class DigitDp<T> {
  private int B;
  private int[] N;
  private T[] dp;

  public DigitDp(char[] N){ this(N,10); }

  public DigitDp(char[] N,int B){
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

abstract class Dijkstra<E, L> extends Graph<E>{
  private Comparator<L> cmp;
  private L[] len;
  private int[] hep,idx;
  private Edge<E>[] pre;
  private int sz;

  public Dijkstra(int n,boolean dir){
    super(n,dir);
    hep = new int[n];
    idx = new int[n];
    cmp = cmp();
  }

  protected abstract L zero();
  protected abstract L inf();
  protected abstract L f(L l,Edge<E> e);

  protected Comparator<L> cmp(){ return Util.cast(Comparator.naturalOrder()); }

  public L[] calc(int s){ return calc(s,-1); }

  public L[] calc(int s,int g){
    len = Util.cast(Array.newInstance(zero().getClass(),sz = n));
    pre = Util.cast(new Edge[n]);
    fill(len,inf());
    setAll(hep,i -> i);
    setAll(idx,i -> i);
    set(s,zero());
    for (int cur;0 < sz && (cur = poll()) != g;)
      for (var e:go(cur))
        set((pre[e.v] = e).v,f(len[cur],e));
    return len;
  }

  public L get(int t){ return len[t]; }

  public Deque<Edge<E>> path(int t){
    Deque<Edge<E>> ret = new ArrayDeque<>();
    while (pre[t] != null) {
      ret.addFirst(pre[t]);
      t = pre[t].u;
    }

    return ret;
  }

  private void set(int i,L l){
    if (idx[i] < sz && cmp.compare(l,len[i]) < 0) {
      len[i] = l;
      heapfy(idx[i]);
    }
  }

  private int poll(){
    int ret = hep[0];
    heapfy(swap(0,--sz));
    return ret;
  }

  private void heapfy(int k){
    int p = k -1 >>1;
    if (0 <= p && cmp.compare(len[hep[p]],len[hep[k]]) > 0) {
      heapfy(swap(p,k));
      return;
    }

    int c = k <<1 |1;
    if (sz <= c)
      return;

    if (c +1 < sz && cmp.compare(len[hep[c]],len[hep[c +1]]) > 0)
      c++;

    if (cmp.compare(len[hep[c]],len[hep[k]]) < 0)
      heapfy(swap(c,k));
  }

  private int swap(int i,int j){
    hep[i] ^= hep[j];
    hep[j] ^= hep[i];
    hep[i] ^= hep[j];
    idx[hep[i]] = i;
    idx[hep[j]] = j;
    return i;
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
  public V get(int i){
    down(i,i +1);
    return super.get(i);
  }

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

  public V get(int i){ return get(root,i); }

  private V get(Node nd,int i){
    if (nd.sz == 1)
      return nd.val;
    nd.push();
    return i < nd.lft.sz ? get(nd.lft,i) : get(nd.rht,i -nd.lft.sz);
  }

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
  public static long infL = (1L <<61 |1 <<30) -1,
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

  public String[][] str(int H,int W){ return Util.arr(new String[H][],i -> str(W)); }
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
