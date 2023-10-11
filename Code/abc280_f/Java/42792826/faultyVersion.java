import java.io.*;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.*;
import java.util.stream.*;
import java.awt.Point;

import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.util.Collections.*;

class Solver{
  long st = System.currentTimeMillis();

  long elapsed(){ return System.currentTimeMillis() -st; }

  void reset(){ st = System.currentTimeMillis(); }

  static int infI = (int) 1e9;
  static long infL = (long) 1e18;
  //  static long mod = (int) 1e9 +7;
  static long mod = 998244353;
  static String yes = "Yes";
  static String no = "No";

  Random rd = ThreadLocalRandom.current();
  MyReader in = new MyReader(System.in);
  MyWriter out = new MyWriter(System.out);
  MyWriter log = new MyWriter(System.err){
    @Override
    protected void ln(){
      super.ln();
      flush();
    };
  };
  int N = in.it();
  int M = in.it();
  int Q = in.it();

  Object solve(){
    UnionFindPotential uf = new UnionFindPotential(N);

    Set<Integer> infs = new HashSet<>();
    for (int i = 0;i < M;i++) {
      int a = in.idx();
      int b = in.idx();
      long c = in.lg();

      if (!uf.valid(a,b,c))
        infs.add(uf.root(a));
      else
        uf.unite(a,b,c);
    }

    for (var i:new ArrayList<>(infs))
      infs.add(uf.root(i));

    while (Q-- > 0) {
      int x = in.idx();
      int y = in.idx();

      if (!uf.same(x,y))
        out.println("nan");
      else if (infs.contains(uf.root(x)))
        out.println("inf");
      else
        out.println(uf.dist(y) -uf.dist(x));

    }
    return null;
  }
}

class UnionFind{
  int num;
  int[] par;
  int[] size;

  public UnionFind(int n){
    par = new int[n];
    Arrays.setAll(par,i -> i);
    size = new int[n];
    Arrays.fill(size,1);
    num = n;
  }

  int root(int x){ return x == par[x] ? x : (par[x] = root(par[x])); }

  boolean same(int u,int v){ return root(u) == root(v); }

  boolean unite(int u,int v){
    if ((u = root(u)) == (v = root(v)))
      return false;

    if (size[u] < size[v]) {
      u ^= v;
      v ^= u;
      u ^= v;
    }
    sub(u,v);
    par[v] = u;
    size[u] += size[v];
    num--;
    return true;
  }

  protected void sub(int u,int v){}

  int size(int x){ return size[root(x)]; }

}

class UnionFindGroup extends UnionFind{
  List<Set<Integer>> group;

  UnionFindGroup(int n){
    super(n);
    group = new ArrayList<>();
    for (int i = 0;i < n;i++) {
      Set<Integer> set = new HashSet<>();
      set.add(i);
      group.add(set);
    }
  }

  Set<Integer> getGroup(int i){ return group.get(root(i)); }

  @Override
  protected void sub(int u,int v){ group.get(u).addAll(group.get(v)); }

}

class UnionFindPotential extends UnionFind{
  long[] dist;

  UnionFindPotential(int n){
    super(n);
    dist = new long[n];
  }

  long sum(long a,long b){ return a +b; }

  long sub(long a,long b){ return a -b; }

  @Override
  int root(int x){
    if (x == par[x])
      return x;

    int r = root(par[x]);
    dist[x] = sum(dist[x],dist[par[x]]);

    return par[x] = r;
  }

  @Override
  boolean unite(int u,int v){ return unite(u,v,0); }

  boolean valid(int u,int v,long c){ return !same(u,v) || sub(dist(v),dist(u)) == c; }

  boolean unite(int u,int v,long c){
    assert valid(u,v,c);

    if ((u = root(u)) == (v = root(v)))
      return false;

    c = sum(c,sub(dist(u),dist(v)));
    if (size[u] < size[v]) {
      u ^= v;
      v ^= u;
      u ^= v;
      c = sub(0,c);
    }

    par[v] = u;
    dist[v] = c;
    size[u] += size[v];
    num--;
    return true;
  }

  long dist(int x){
    root(x);
    return dist[x];
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

  int[][] qry(int Q){ return Util.arr(new int[Q][],i -> new int[]{idx(), idx(), i}); }

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

  void println(Object obj){
    if (obj == null)
      return;

    if (obj instanceof Collection<?>)
      for (var e:(Collection<?>) obj)
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