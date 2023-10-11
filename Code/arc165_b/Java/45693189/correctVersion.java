import static java.lang.Math.*;
import static java.util.Arrays.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.*;

class Solver{
  long st = System.currentTimeMillis();

  long elapsed(){ return System.currentTimeMillis() -st; }

  void reset(){ st = System.currentTimeMillis(); }

  final static int infI = (1 <<30) -1;
  final static long infL = 1L <<60;
  final static long mod = (int) 1e9 +7;
  //  final static long mod = 998244353;
  final static String yes = "Yes";
  final static String no = "No";

  Random rd = ThreadLocalRandom.current();
  MyReader in = new MyReader(System.in);
  MyWriter out = new MyWriter(System.out){
    //    @Override
    //    void println(Object obj){};
  };
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
  int K = in.it();
  int[] P = in.it(N);

  Object solve(){
    int cnt = 0;
    for (int i = 1;i < N;i++) {
      if (P[i -1] < P[i])
        cnt++;
      else
        cnt = 0;
      if (cnt == K -1)
        return P;
    }
    var a = copyOf(P,N);
    var b = copyOf(P,N);
    sort(a,N -K,N);

    int[] min = copyOf(P,N);
    for (int i = N -K +1;i < N;i++)
      min[i] = min(min[i -1],P[i]);

    int s = N -K;
    while (0 < s && P[s -1] < P[s])
      s--;

    while (s < N -K) {
      if (P[N -K -1] < min[s +K -1])
        break;
      s++;
    }

    sort(b,s,s +K);

    for (int i = 0;i < N;i++) {
      if (a[i] > b[i])
        return a;
      if (a[i] < b[i])
        return b;
    }

    return a;
  }

  long gcd(long a,long b){ return b == 0 ? a : gcd(b,a %b); }

  long lcm(long a,long b){ return b /gcd(a,b) *a; }

  void reverse(Object arr){
    if (!arr.getClass().isArray())
      throw new UnsupportedOperationException("reverse");

    int l = 0;
    int r = Array.getLength(arr) -1;

    while (l < r) {
      Object t = Array.get(arr,l);
      Array.set(arr,l,Array.get(arr,r));
      Array.set(arr,r,t);
      l++;
      r--;
    }

  }

  long ceil(long a,long b){ return (a +b -1) /b; }

  int bSearchI(int o,int n,Predicate<Integer> judge){
    if (!judge.test(o))
      return o -(n -o) /abs(n -o);
    for (int c = 0;1 < abs(n -o);)
      if (judge.test(c = o +n >>1))
        o = c;
      else
        n = c;
    return o;
  }

  long bSearchL(long o,long n,Predicate<Long> judge){
    for (long c = 0;1 < abs(n -o);)
      if (judge.test(c = o +n >>1))
        o = c;
      else
        n = c;
    return o;
  }

  int[][] addId(int[][] T){
    return Util.arr(new int[T.length][],i -> {
      int[] t = copyOf(T[i],T[i].length +1);
      t[t.length -1] = i;
      return t;
    });
  }
  //  <T> void log(BIT seg){
  //    long[] a = new long[seg.n];
  //    for (int i = 0;i < a.length;i++)
  //      a[i] = seg.get(i);
  //    log.println(a);
  //    log.println("");
  //  }

  // double inv(long x){ return 1.0 /x; }
  long inv(long x){ return pow(x,mod -2); }

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
}

class Prime{

  BitSet primes;
  int[] spf;
  int[] P;

  Prime(){ this(10_000_000,1_000_000); }

  Prime(int n,int s){
    primes = new BitSet(n +1);
    primes.set(2,n +1);
    spf = new int[min(s,n) +1];

    for (int p = 2;p *p <= n || p < spf.length;p++)
      if (primes.get(p))
        for (int nn = p;nn <= n;primes.clear(nn += p))
          if (nn < spf.length)
            spf[nn] = p;

    P = primes.stream().toArray();
  }

  long[][] factorize(long n){
    List<long[]> ret = new ArrayList<>();
    for (int pi = 0;pi < P.length && n >= spf.length;pi++)
      n = extracted(n,ret,P[pi]);

    if (n < spf.length)
      while (n > 1)
        n = extracted(n,ret,spf[(int) n]);

    for (long p = P[P.length -1];p *p <= n && n >= spf.length;p += 2)
      n = extracted(n,ret,p);

    if (n < spf.length)
      while (n > 1)
        n = extracted(n,ret,spf[(int) n]);

    if (1 < n)
      ret.add(new long[]{n, 1});
    return ret.toArray(new long[ret.size()][]);
  }

  long extracted(long n,List<long[]> ret,long p){
    int cnt = 0;
    while (n %p == 0) {
      cnt++;
      n /= p;
    }
    if (0 < cnt)
      ret.add(new long[]{p, cnt});
    return n;
  }

  long[] divisors(long n){
    long[][] facts = factorize(n);
    int l = 1;
    for (var f:facts)
      l *= f[1] +1;

    long[] ret = new long[l];
    int id = 1;
    ret[0] = 1;
    for (var f:facts) {
      int sz = id;
      long p = f[0];
      for (int i = 0;i < f[1];i++,p *= f[0])
        for (int j = 0;j < sz;j++)
          ret[id++] = ret[j] *p;
    }

    return ret;
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

class BIT{
  int n;
  long[] bit;

  long agg(long v0,long v1){ return v0 +v1; }

  long sub(long v0,long v1){ return v0 -v1; }

  BIT(int n){
    this.n = n;
    bit = new long[n +1];
  }

  void upd(int x,long v){
    for (x++;x <= n;x += x &-x)
      bit[x] = agg(bit[x],v);
  }

  long sum(int x){
    long ret = 0;
    for (;x > 0;x -= x &-x)
      ret = agg(ret,bit[x]);
    return ret;
  }

  long get(int i){ return get(i,i +1); }

  long get(int l,int r){ return sub(sum(r),sum(l)); }
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
  List<Edge<L>> go,back;
  int dpt;

  Node(int id,L val){ super(id,val); }

  @Override
  public String toString(){ return "" +(id +1); }

}

class Nd<L> {
  int id,l,r;
  L val;

  Nd(int id,L val){
    this.id = id;
    this.val = val;
  }

}

class Graph<L> {
  public int n;
  List<Edge<L>> es;
  Node<L>[] nds;

  @SuppressWarnings("unchecked")
  public Graph(int n,int m,boolean dir){
    this.n = n;
    nds = new Node[n];
    for (int i = 0;i < n;i++) {
      nds[i] = new Node<>(i,null);
      nds[i].go = new ArrayList<>();
      nds[i].back = dir ? new ArrayList<>() : nds[i].go;
    }
    es = new ArrayList<>(m);
  }

  public void addEdge(int u,int v,L l){
    Edge<L> e = new Edge<>(es.size(),nds[u],nds[v],l);
    es.add(e);
    e.u.go.add(e);
    e.v.back.add(new Edge<>(e.id,e.v,e.u,e.val));
  }

}

class HLD<L> extends Graph<L>{
  Node<L>[] hPar;

  @SuppressWarnings("unchecked")
  HLD(int N,int M,boolean dir){
    super(N,M,dir);
    hPar = new Node[n];
  }

  List<int[]> path(int ui,int vi,boolean incLca){
    var u = nds[ui];
    var v = nds[vi];
    List<int[]> ret = new ArrayList<>();
    while (true) {
      if (u.l > v.l) {
        var t = u;
        u = v;
        v = t;
      }

      var h = hPar[v.l];
      if (h.l <= u.l) {
        ret.add(new int[]{u.l +(incLca ? 0 : 1), v.l +1, u.id});
        return ret;
      }

      ret.add(new int[]{h.l, v.l +1});
      v = h.p;
    }
  }

  Node<L> lca(int ui,int vi){
    List<int[]> path = path(ui,vi,false);
    return nds[path.get(path.size() -1)[2]];
  }

  public void makeTree(int s){
    Stack<Integer> stk = new Stack<>();
    nds[s].p = nds[s];
    nds[s].dpt = 0;
    stk.add(s);
    stk.add(~s);
    while (!stk.isEmpty()) {
      var ui = stk.pop();
      if (ui < 0) {
        var u = nds[~ui];
        u.r = 1;
        for (var e:u.go) {
          if (e.v == u.p)
            continue;
          es.set(e.id,e);
          e.v.dpt = u.dpt +1;
          e.v.p = u;
          stk.add(e.v.id);
          stk.add(~e.v.id);
        }
      } else if (ui != s)
        nds[ui].p.r += nds[ui].r;
    }

    for (var u:nds)
      for (int i = 1;i < u.go.size();i++)
        if (u.r < u.go.get(0).v.r || u.go.get(0).v.r < u.go.get(i).v.r && u.go.get(i).v.r < u.r)
          Collections.swap(u.go,0,i);

    int hid = 0;
    fill(hPar,null);
    stk.add(s);
    while (!stk.isEmpty()) {
      var u = nds[stk.pop()];
      u.r += u.l = hid;
      if (hPar[u.l] == null)
        hPar[u.l] = u;
      hid++;
      for (int i = u.go.size();i-- > 0;) {
        var e = u.go.get(i);
        if (e.v == u.p)
          continue;
        if (i == 0)
          hPar[hid] = hPar[u.l];
        stk.add(e.v.id);
      }
    }

    for (var e:es) {
      e.l = e.v.l;
      e.r = e.v.r;
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
    else if (obj.getClass().isArray() && Array.getLength(obj) > 0 && !(Array.get(obj,0) instanceof char[])
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
  }
}
