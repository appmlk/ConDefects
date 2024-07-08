import static java.lang.Math.*;
import static java.util.Arrays.*;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.ArrayList;
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

  public static boolean multi = false;

  public Object solve(){
    int N = in.it();
    int K = in.it();
    int[] P = in.idx(N);
    int[] Q = new int[N];
    for (int i = 0;i < N;i++)
      Q[P[i]] = i;
    List<int[]> list = new ArrayList<>();
    for (int x = 0;x < N;x++) {
      Stack<Integer> stk = new Stack<>();
      for (int i = 0;i < x;i++)
        if (Q[i] -Q[x] >= K)
          stk.add(i);
      stk.add(x);
      int b = stk.pop();
      while (!stk.isEmpty()) {
        int a = stk.pop();
        list.add(new int[]{Q[b] +1, Q[a] +1});
        Q[a] ^= Q[b];
        Q[b] ^= Q[a];
        Q[a] ^= Q[b];
        b = a;
      }
    }
    out.println(list.size());
    return list;
  }
}

class Data extends BaseV{
  long lo,lo2,nlo,v;

  public Data(long lo,long lo2,long nlo,long v){
    this.lo = lo;
    this.lo2 = lo2;
    this.nlo = nlo;
    this.v = v;
  }

  @Override
  public String toString(){ return v +""; }
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
      e.re.id += n;

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

  public void clear(){
    dp[n -1] = null;
    for (var e:es) {
      dp[e.id] = dp[e.re.id] = null;
      go(e.u).clear();
      go(e.v).clear();
    }
    es.clear();
  }
}

class HLD extends Graph<Object>{
  private int[] p,hp,l,r;
  public int[] dpt;

  public HLD(int n){
    super(n,false);
    p = new int[n];
    hp = new int[n];
    l = new int[n];
    r = new int[n];
  }

  public MyList<int[]> auxiliary(MyList<Integer> lis){
    lis = new MyList<>(lis);
    lis.add(0);
    MyList<int[]> ret = new MyList<>();
    lis.sort(Comparator.comparing(i -> l[i]));
    for (int i = lis.size() -1;i > 0;i--)
      lis.add(lca(lis.get(i -1),lis.get(i)));
    lis.sort(Comparator.comparing(i -> l[i]));
    MyStack<Integer> stk = new MyStack<>();
    stk.add(lis.get(0));
    for (var y:lis) {
      while (r[stk.peek()] <= l[y])
        stk.pop();
      if (!stk.peek().equals(y))
        ret.add(new int[]{stk.peek(), y});
      stk.add(y);
    }
    return ret;
  }

  public MyList<int[]> getPath(int u,int v,int incLca){
    MyList<int[]> ret = new MyList<>();
    while (true) {
      if (l[u] > l[v]) {
        var t = u;
        u = v;
        v = t;
      }

      var h = hp[v];
      if (l[h] <= l[u]) {
        ret.add(new int[]{l[u] +1 -incLca, l[v] +1});
        return ret;
      }

      ret.add(new int[]{l[h], l[v] +1});
      v = p[h];
    }
  }

  public int lca(int u,int v){
    while (true) {
      if (l[u] > l[v]) {
        var t = u;
        u = v;
        v = t;
      }

      var h = hp[v];
      if (l[h] <= l[u])
        return u;

      v = p[h];
    }
  }

  public void makeTree(int s){
    MyStack<Integer> stk = new MyStack<>();
    fill(hp,-1);
    p[s] = s;
    stk.add(s);
    stk.add(s);
    while (!stk.isEmpty()) {
      var u = stk.pop();
      if (r[u] < 1) {
        r[u] = 1;
        for (var e:go(u)) {
          if (e.v == p[u])
            continue;
          es.set(e.id,e);
          p[e.v] = u;
          stk.add(e.v);
          stk.add(e.v);
        }
      } else if (u != s)
        r[p[u]] += r[u];
    }

    for (int u = 0;u < n;u++) {
      var go = go(u);
      for (int i = 1;i < go.size();i++)
        if (r[u] < r[go.get(0).v] || r[go.get(0).v] < r[go.get(i).v] && r[go.get(i).v] < r[u])
          go.swap(0,i);
    }

    stk.add(s);
    for (int hid = 0;!stk.isEmpty();) {
      var u = stk.pop();
      r[u] += l[u] = hid++;
      if (hp[u] < 0)
        hp[u] = u;
      var go = go(u);
      for (int i = go.size();i-- > 0;) {
        var v = go.get(i).v;
        if (v == p[u])
          continue;
        if (i == 0)
          hp[v] = hp[u];
        stk.add(v);
      }
    }
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

  public MyList(int n){ arr = Util.cast(new Object[max(16,n)]); }

  public MyList(MyList<T> org){
    this(org.sz);
    System.arraycopy(org.arr,0,arr,0,sz = org.sz);
  }

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

  public void clear(){ sz = 0; }
}

class BaseSolver extends Util{
  public MyReader in;
  public MyWriter out,log;

  public BaseSolver(MyReader in,MyWriter out,MyWriter log){
    this.in = in;
    this.out = out;
    this.log = log;
  }

  protected int[][] addId(int[][] T){
    return arr(new int[T.length][],i -> {
      int[] t = copyOf(T[i],T[i].length +1);
      t[t.length -1] = i;
      return t;
    });
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
