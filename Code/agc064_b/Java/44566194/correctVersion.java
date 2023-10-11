import static java.lang.Math.*;
import static java.util.Arrays.*;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solver{
  long st = System.currentTimeMillis();

  long elapsed(){ return System.currentTimeMillis() -st; }

  void reset(){ st = System.currentTimeMillis(); }

  static int infI = (1 <<30) -1;
  static long infL = 1L <<60;
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

  Object solve(){
    int N = in.it();
    int M = in.it();
    Graph<Character> G = new Graph<>(N,M,false);
    for (int i = 0;i < M;i++)
      G.addEdge(in.idx(),in.idx(),in.ch()[0]);
    char[] S = in.ch();

    UnionFind uf = new UnionFind(N);
    Set<Integer> ans = new HashSet<>();
    boolean[] clr = new boolean[N];
    Queue<Edge<Character>> que = new ArrayDeque<>();
    for (var e:G.es) {
      int a = e.u.id;
      int b = e.v.id;
      char c = e.val;
      if (S[a] == c && S[b] == c && !uf.same(a,b)) {
        uf.unite(a,b);
        if (!clr[a])
          que.addAll(G.go(a));
        if (!clr[b])
          que.addAll(G.go(b));
        clr[a] = true;
        clr[b] = true;
        ans.add(e.id);
      }
    }

    while (!que.isEmpty()) {
      var e = que.poll();
      int a = e.u.id;
      int b = e.v.id;
      char c = e.val;

      if (uf.same(a,b))
        continue;

      if (c == S[b]) {
        if (!clr[b])
          que.addAll(G.go(b));
        clr[b] = true;
        uf.unite(a,b);
        ans.add(e.id);
      }
    }

    for (var e:G.es) {
      int a = e.u.id;
      int b = e.v.id;
      if (!clr[a] || !clr[b])
        return false;
      if (!uf.same(a,b)) {
        uf.unite(a,b);
        ans.add(e.id);
      }
    }
    if (uf.num > 1)
      return false;

    out.println(true);
    var itr = ans.iterator();
    int[] arr = new int[N -1];
    for (int i = 1;i < N;i++)
      arr[i -1] = itr.next() +1;
    sort(arr);
    return arr;
  }

  private void swap(int[] ans,int a,int b){
    ans[a] ^= ans[b];
    ans[b] ^= ans[a];
    ans[a] ^= ans[b];
  }

  long[] divisors(long n){
    Deque<Long> q = new ArrayDeque<>();
    for (long p = (long) Math.sqrt(n);p > 0;p--)
      if (n %p == 0) {
        q.addFirst(p);
        if (p *p != n)
          q.addLast(n /p);
      }
    return q.stream().mapToLong(i -> i).toArray();
  }

  long pow(long x,long n){ return pow(x,n,mod); }

  long pow(long x,long n,long mod){
    x %= mod;
    long ret = 1;
    do {
      if ((n &1) == 1)
        ret = ret *x %mod;
      x = x *x %mod;
    } while (0 < (n >>= 1));

    return ret;
  }

  long mul(long... arr){
    long ret = 1;
    for (var a:arr)
      ret = ret *(a %mod) %mod;
    return ret < 0 ? ret +mod : ret;
  }

  long sum(long... arr){
    long ret = 0;
    for (var a:arr)
      ret = (ret +a %mod) %mod;
    return ret < 0 ? ret +mod : ret;
  }

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
      eval(stk[s--]);
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

class UnionFind{
  int num;
  int[] dat;
  int[] nxt;

  public UnionFind(int n){
    dat = new int[n];
    nxt = new int[n];
    setAll(nxt,i -> i);
    fill(dat,-1);
    num = n;
  }

  int root(int x){ return dat[x] < 0 ? x : (dat[x] = root(dat[x])); }

  boolean same(int u,int v){ return root(u) == root(v); }

  boolean unite(int u,int v){
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

  int size(int x){ return -dat[root(x)]; }

  int[] getGroup(int x){
    int[] ret = new int[size(x)];
    for (int i = 0,c = root(x);i < ret.length;i++)
      ret[i] = c = nxt[c];
    return ret;
  }

}

class Edge<L> extends Nd<L>{
  Node<L> u,v;

  Edge(int id,Node<L> u,Node<L> v,L val){
    super(id,val);
    this.u = u;
    this.v = v;
  }

  @Override
  public String toString(){ return "(" +getClass().getSimpleName() +"," +(u.id +1) +"," +(v.id +1) +")"; }

}

class Node<L> extends Nd<L>{
  Node<L> p;
  int dpt;
  Collection<Edge<L>> go,back;

  Node(int id,L val){
    super(id,val);
    go = new HashSet<>();
    back = new HashSet<>();
  }

  @Override
  public String toString(){ return "" +(id +1); }

}

class Nd<L> {
  int id,in,out;
  L val;

  Nd(int id,L val){
    this.id = id;
    this.val = val;
  }

  @Override
  public int hashCode(){ return Objects.hash(id); }

  @Override
  public boolean equals(Object obj){
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Nd other = (Nd) obj;
    return id == other.id;
  }

}

class Graph<L> {
  private int n;
  private int eid;

  Edge<L>[] es;
  Node<L>[] nds;
  Nd<L>[] tour;
  private Node<L>[] lca;

  //  Tree(int n,MyReader in,Supplier<L> sup){
  //    this(n);
  //    for (int i = 1;i < n;i++)
  //      addEdge(in.idx(),in.idx(),sup.get());
  //  }
  //
  //  @SuppressWarnings("unchecked")
  //  Tree(int n,MyReader in){ this(n,in,() -> (L) Integer.valueOf(1)); }

  @SuppressWarnings("unchecked")
  public Graph(int n,int m,boolean dir){
    this.n = n;
    nds = new Node[n];
    tour = new Nd[2 *n];
    for (int i = 0;i < n;i++) {
      nds[i] = new Node<>(i,null);
      if (!dir)
        nds[i].back = nds[i].go;
    }
    es = new Edge[m];
  }

  public void addEdge(int u,int v,L l){
    Node<L> nu = nds[u];
    Node<L> nv = nds[v];
    Edge<L> e = new Edge<>(eid,nu,nv,l);
    Edge<L> rev = new Edge<>(eid,nv,nu,l);
    nu.go.add(e);
    nv.back.add(rev);
    es[eid++] = e;
  }

  public Collection<Edge<L>> go(int u){ return nds[u].go; }

  public Collection<Edge<L>> back(int u){ return nds[u].back; }

  public void makeTree(int s){
    lca = null;
    Stack<Node<L>> stk = new Stack<>();
    nds[s].dpt = -1;
    nds[s].p = null;
    stk.add(nds[s]);
    stk.add(nds[s]);
    int ei = 0;
    while (!stk.isEmpty()) {
      var u = stk.pop();
      if (u.dpt < 0) {
        tour[u.in = u.out = ei++] = u;
        for (var e:u.go)
          if (e.v != u.p) {
            es[e.id] = e;
            e.v.dpt = u.dpt -1;
            e.v.p = u;
            stk.add(e.v);
            stk.add(e.v);
          }
        u.dpt = ~u.dpt;
      } else {
        if (tour[ei -1] != u)
          tour[u.out = ei++] = u;
        tour[ei++] = u.p;
      }
    }
  }

  public Node<L> lca(int u,int v){ return lca(nds[u],nds[v]); }

  public Node<L> lca(Node<L> u,Node<L> v){
    if (lca == null)
      makeLca();

    var l = u.in;
    var r = v.in;
    if (l > r) {
      l ^= r;
      r ^= l;
      l ^= r;
    }
    r++;
    int k = max(1,31 -Integer.numberOfLeadingZeros(r -l -1));
    Node<L> a = lca[k *(2 *n -1) +l];
    Node<L> b = lca[k *(2 *n -1) +r -(1 <<k)];
    return a.dpt < b.dpt ? a : b;
  }

  @SuppressWarnings("unchecked")
  private void makeLca(){
    int n = tour.length -1;
    int K = max(1,32 -Integer.numberOfLeadingZeros(n -1));
    lca = new Node[K *n];
    for (int i = 0;i < n;i++)
      lca[i] = (Node<L>) tour[i];
    for (int k = 0;k +1 < K;k++)
      for (int i = 0;i +(2 <<k) <= n;i++) {
        Node<L> a = lca[k *n +i];
        Node<L> b = lca[k *n +i +(1 <<k)];
        lca[(k +1) *n +i] = a.dpt < b.dpt ? a : b;
      }
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

  void println(Object... o){
    print(Util.arr(new Object[o.length],i -> o[i]));
    ln();
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
