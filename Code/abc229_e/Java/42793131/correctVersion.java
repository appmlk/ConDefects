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
  int[][] T = in.idx(M,2);

  Object solve(){
    sort(T,Comparator.comparing(t -> -t[0]));
    UnionFindUndo uf = new UnionFindUndo(N);
    for (var t:T)
      uf.unite(t[0],t[1]);

    for (int i = 0;i < N;i++) {
      while (uf.size(i) > 1)
        uf.undo();
      out.println(uf.num -i -1);
    }

    return null;
  }

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
  boolean unite(int u,int v){
    if (!super.unite(u,v))
      return false;
    group.get(root(u)).addAll(group.get(root(v)));
    return true;
  }

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
    if (data[x] < 0)
      return x;

    int r = root(data[x]);
    dist[x] = sum(dist[x],dist[data[x]]);

    return data[x] = r;
  }

  @Override
  boolean unite(int u,int v){ return unite(u,v,0); }

  boolean valid(int u,int v,long c){ return !same(u,v) || sub(dist(v),dist(u)) == c; }

  boolean unite(int u,int v,long c){
    assert valid(u,v,c);
    c = sum(c,sub(dist(u),dist(v)));
    u = root(u);
    v = root(v);
    if (!super.unite(u,v))
      return false;
    if (data[u] > data[v])
      dist[u] = sub(0,c);
    else
      dist[v] = c;
    return true;
  }

  long dist(int x){
    root(x);
    return dist[x];
  }

}

class UnionFindUndo extends UnionFind{
  Stack<int[]> histry;

  UnionFindUndo(int n){
    super(n);
    histry = new Stack<>();
  }

  @Override
  int root(int x){ return data[x] < 0 ? x : root(data[x]); }

  @Override
  boolean unite(int u,int v){
    if ((u = root(u)) == (v = root(v)))
      return false;
    histry.add(new int[]{u, data[u]});
    histry.add(new int[]{v, data[v]});
    return super.unite(u,v);
  }

  void undo(){
    var h = histry.pop();
    data[h[0]] = h[1];
    h = histry.pop();
    data[h[0]] = h[1];
    num++;
  }

}

class UnionFind{
  int num;
  int[] data;

  public UnionFind(int n){
    data = new int[n];
    fill(data,-1);
    num = n;
  }

  int root(int x){ return data[x] < 0 ? x : (data[x] = root(data[x])); }

  boolean same(int u,int v){ return root(u) == root(v); }

  boolean unite(int u,int v){
    if ((u = root(u)) == (v = root(v)))
      return false;

    if (data[u] > data[v]) {
      u ^= v;
      v ^= u;
      u ^= v;
    }
    data[u] += data[v];
    data[v] = u;
    num--;
    return true;
  }

  int size(int x){ return -data[root(x)]; }

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