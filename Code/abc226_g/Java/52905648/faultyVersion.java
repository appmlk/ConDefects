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
    long[] A = new long[6];
    long[] B = new long[6];
    for (int i = 1;i <= 5;i++)
      A[i] = in.lg();
    for (int i = 1;i <= 5;i++)
      B[i] = in.lg();
    f(A,B,5,5);
    f(A,B,4,4);
    f(A,B,3,3);
    f(A,B,4,5);
    f(A,B,3,4);
    f(A,B,3,5);
    for (int b = 5;b >= 2;b--)
      f(A,B,2,b);
    for (int b = 5;b >= 1;b--)
      f(A,B,1,b);
    for (var a:A)
      if (a > 0)
        return false;

    return true;
  }

  private void f(long[] A,long[] B,int a,int b){
    long n = min(A[a],B[b]);
    A[a] -= n;
    B[b] -= n;
    B[b -a] += n;
  }
}

class Grid{
  private int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
  private int H,W;

  public Grid(int H,int W){
    this.H = H;
    this.W = W;
  }

  //  public int[] sur(int p){
  //    int[][] tmp = sur(toi(p),toj(p));
  //    int[] ret = new int[tmp.length];
  //    for (int i = 0;i < tmp.length;i++)
  //      ret[i] = top(tmp[i][0],tmp[i][1]);
  //    return ret;
  //  }

  public MyList<int[]> sur(int i,int j){
    MyList<int[]> ret = new MyList<>();
    for (var d:dirs) {
      int ni = i +d[0];
      int nj = j +d[1];
      if (valid(ni,H) && valid(nj,W))
        ret.add(new int[]{ni, nj});
    }

    return ret;
  }

  private boolean valid(int i,int N){ return 0 <= i && i < N; }

  public int top(int i,int j){ return i *W +j; }

  public int toi(int p){ return p /W; }

  public int toj(int p){ return p %W; }
}

abstract class AVLSegmentTree<V extends BaseV, F> {
  private V e = e();
  private Node root;
  private V[] ret = Util.cast(new BaseV[2]);
  private int ri;

  public AVLSegmentTree(int n){
    this();
    root = new Node(e(),n);
  }

  public AVLSegmentTree(){
    ret[ri] = e();
    ri ^= 1;
  }

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
    ret[ri] = e();
    ri ^= 1;
    if (root != null)
      get(root,l,min(r,size()));
    return ret[ri ^= 1];
  }

  private void get(Node nd,int l,int r){
    if (0 == l && r == nd.sz || nd.lft == null)
      ag(ret[ri],ret[ri ^= 1],nd.lft == null ? pw(nd.val,r -l) : nd.val());
    else {
      nd.push();
      if (l < nd.lft.sz)
        get(nd.lft,l,min(nd.lft.sz,r));
      if (nd.lft.sz < r)
        get(nd.rht,max(0,l -nd.lft.sz),r -nd.lft.sz);
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
    for (var t = ag(e(),e,a);0 < n;n >>= 1,ag(t,t,t))
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
  long v,c;

  public Data(long v){ this.v = v; }

  @Override
  public String toString(){ return "" +v; }
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

class MyListLong{
  private long[] arr;
  private int sz;

  public MyListLong(){ this(16); }

  public MyListLong(int n){ arr = new long[n]; }

  public boolean isEmpty(){ return sz == 0; }

  public int size(){ return sz; }

  public long get(int i){ return arr[i]; }

  public void add(long t){ (arr = sz < arr.length ? arr : copyOf(arr,sz *5 >>2))[sz++] = t; }

  public long[] toArray(){ return copyOf(arr,sz); }
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

  public T[] toArray(){
    if (sz == 0)
      return Util.cast(new Object[0]);
    T[] ret = Util.cast(Array.newInstance(arr[0].getClass(),sz));
    for (int i = 0;i < sz;i++)
      ret[i] = arr[i];
    return ret;
  }

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
