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
    String[] SS = in.str(N);

    a:for (int i = 0;i < N;i++)
      for (int j = 0;j < N;j++) {
        if (i == j)
          continue;
        if (SS[i].contains(SS[j])) {
          SS[j] = SS[N -1];
          N--;
          i--;
          continue a;
        }
      }

    char[][] S = new char[N][];
    for (int i = 0;i < N;i++)
      S[i] = SS[i].toCharArray();
    RollingHash[] rhs = new RollingHash[N];
    for (int i = 0;i < N;i++)
      rhs[i] = new RollingHash(S[i],false);
    int[][] E = new int[N][N];
    for (int i = 0;i < N;i++)
      for (int j = 0;j < N;j++)
        for (int l = 1;l <= min(S[i].length,S[j].length);l++)
          if (rhs[i].get(S[i].length -l,S[i].length) == rhs[j].get(0,l))
            E[i][j] = l;

    long[][] dp = new long[1 <<N][N];

    for (int bit = 0;bit < 1 <<N;bit++)
      for (int i = 0;i < N;i++)
        for (int j = 0;j < N;j++) {
          if ((bit >>i &1) == 0 || (bit >>j &1) == 1)
            continue;
          dp[bit |1 <<j][j] = max(dp[bit |1 <<j][j],dp[bit][i] +E[i][j]);
        }
    long sum = 0;
    for (var s:S)
      sum += s.length;

    long ans = infL;
    for (int i = 0;i < N;i++)
      ans = min(ans,sum -dp[(1 <<N) -1][i]);
    return ans;
  }

  <T extends BaseV> void log(Seg<T, ?> seg,int n){
    T[] a = (T[]) new BaseV[n];
    for (int i = 0;i < n;i++)
      a[i] = seg.get(i);
    log.println(a);
  }

  long min(long... a){
    long ret = a[0];
    for (var aa:a)
      ret = Math.min(ret,aa);
    return ret;
  }

  long max(long... a){
    long ret = a[0];
    for (var aa:a)
      ret = Math.max(ret,aa);
    return ret;
  }

  private long hash(int[] A){
    long ret = 0;
    for (var a:A) {
      ret = Mul(ret,972029) +a;
      if (ret >= MOD)
        ret -= MOD;
    }
    return ret;
  }

  final static long MASK30 = (1L <<30) -1;
  final static long MASK31 = (1L <<31) -1;
  final static long MOD = (1L <<61) -1;

  private static long Mul(final long l,final long r){
    final long lu = l >>31;
    final long ld = l &MASK31;
    final long ru = r >>31;
    final long rd = r &MASK31;
    final long middleBit = ld *ru +lu *rd;
    return (lu *ru <<1) +ld *rd +((middleBit &MASK30) <<31) +(middleBit >>30);
  }
}

class Data extends BaseV{
  long[][] arr;

  public Data(){ arr = new long[2][2]; }

  public Data(int v){
    this();
    arr[0][0] = v;
    arr[0][1] = 1;
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

  private Node del(V ret,Node nd,int i){
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
      ag(ret,ret,pw(t,nd.val,r -l));
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

  private V pw(V ret,V a,int n){
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
      tog(val);
      if (!leaf)
        tog ^= 1;
      return this;
    }

    private Node cld(int c){ return c < 0 ? lft : rht; }

    private void cld(int c,Node nd){ nd = c < 0 ? (lft = nd) : (rht = nd); }

    private V val(){ return leaf && 1 < sz ? pw(e(),val,sz) : val; }
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

class RollingHash{
  private static long MASK30 = (1L <<30) -1;
  private static long MASK31 = (1L <<31) -1;
  private static long MOD = (1L <<61) -1;
  private static long m = base();
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

  public static boolean equal(RollingHash rhS,int sl,int sr,RollingHash rhT,int tl,int tr){
    return rhS.get(sl,sr) == rhT.get(tl,tr);
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

  public int[] arrI(int N,IntUnaryOperator f){
    int[] ret = new int[N];
    setAll(ret,f);
    return ret;
  }

  public long[] arrL(int N,IntToLongFunction f){
    long[] ret = new long[N];
    setAll(ret,f);
    return ret;
  }

  public double[] arrD(int N,IntToDoubleFunction f){
    double[] ret = new double[N];
    setAll(ret,f);
    return ret;
  }

  public <T> T[] arr(T[] arr,IntFunction<T> f){
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

class MyReader extends Util{
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

  public int[] it(int N){ return arrI(N,i -> it()); }

  public int[][] it(int H,int W){ return arr(new int[H][],i -> it(W)); }

  public int idx(){ return it() -1; }

  public int[] idx(int N){ return arrI(N,i -> idx()); }

  public int[][] idx(int H,int W){ return arr(new int[H][],i -> idx(W)); }

  public long lg(){
    byte i = nextPrintable();
    boolean negative = i == 45;
    long n = negative ? 0 : i -'0';
    while (isPrintable(i = read()))
      n = 10 *n +i -'0';
    return negative ? -n : n;
  }

  public long[] lg(int N){ return arrL(N,i -> lg()); }

  public long[][] lg(int H,int W){ return arr(new long[H][],i -> lg(W)); }

  public double dbl(){ return Double.parseDouble(str()); }

  public double[] dbl(int N){ return arrD(N,i -> dbl()); }

  public double[][] dbl(int H,int W){ return arr(new double[H][],i -> dbl(W)); }

  public char[] ch(){ return str().toCharArray(); }

  public char[][] ch(int H){ return arr(new char[H][],i -> ch()); }

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

  public String[] str(int N){ return arr(new String[N],i -> str()); }
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
