import java.io.*;
import java.lang.reflect.Array;
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
  static long mod = (int) 1e9 +7;
  //  static long mod = 998244353;
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
  long[][] T = in.lg(N,3);

  Object solve(){
    long[][] dist = new long[N][N];
    for (int i = 0;i < N;i++)
      for (int j = 0;j < N;j++)
        dist[i][j] = abs(T[i][0] -T[j][0]) +abs(T[i][1] -T[j][1]);

    return bSearchL(infI,0,S -> {
      for (int s = 0;s < N;s++) {
        Set<Integer> seen = new HashSet<>(N);
        seen.add(s);
        Queue<Integer> que = new ArrayDeque<>(seen);

        while (!que.isEmpty()) {
          var u = que.poll();
          for (int v = 0;v < N;v++)
            if (T[u][2] *S >= dist[u][v] && seen.add(v))
              que.add(v);
        }
        if (seen.size() == N)
          return true;
      }

      return false;
    });
  }

  long bSearchL(long o,long n,Predicate<Long> judge){
    if (!judge.test(o))
      return o -(n -o) /abs(n -o);
    for (long c = 0;1 < abs(n -o);)
      if (judge.test(c = o +n >>1))
        o = c;
      else
        n = c;
    return o;
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

    par[v] = u;
    size[u] += size[v];
    num--;
    return true;
  }

  int size(int x){ return size[root(x)]; }

}

class Edge{
  int id;
  int u;
  int v;
  long l;
  Edge rev;

  Edge(int id,int u,int v){
    this.id = id;
    this.u = u;
    this.v = v;
  }

  void rev(Edge rev){ rev.l = l; }

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

  int[] arrI(int N,IntUnaryOperator f){
    int[] ret = new int[N];
    setAll(ret,f);
    return ret;
  }

  long[] arrL(int N,IntToLongFunction f){
    long[] ret = new long[N];
    setAll(ret,f);
    return ret;
  }

  double[] arrD(int N,IntToDoubleFunction f){
    double[] ret = new double[N];
    setAll(ret,f);
    return ret;
  }

  <T> T[] arr(T[] arr,IntFunction<T> f){
    setAll(arr,f);
    return arr;
  }

  int it(){ return toIntExact(lg()); }

  int[] it(int N){ return arrI(N,i -> it()); }

  int[][] it(int H,int W){ return arr(new int[H][],i -> it(W)); }

  int idx(){ return it() -1; }

  int[] idx(int N){ return arrI(N,i -> idx()); }

  int[][] idx(int H,int W){ return arr(new int[H][],i -> idx(W)); }

  int[][] qry(int Q){ return arr(new int[Q][],i -> new int[]{idx(), idx(), i}); }

  long lg(){
    byte i = nextPrintable();
    boolean negative = i == 45;
    long n = negative ? 0 : i -'0';
    while (isPrintable(i = read()))
      n = 10 *n +i -'0';
    return negative ? -n : n;
  }

  long[] lg(int N){ return arrL(N,i -> lg()); }

  long[][] lg(int H,int W){ return arr(new long[H][],i -> lg(W)); }

  double dbl(){ return Double.parseDouble(str()); }

  double[] dbl(int N){ return arrD(N,i -> dbl()); }

  double[][] dbl(int H,int W){ return arr(new double[H][],i -> dbl(W)); }

  char[] ch(){ return str().toCharArray(); }

  char[][] ch(int H){ return arr(new char[H][],i -> ch()); }

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

  String[] str(int N){ return arr(new String[N],i -> str()); }

  Edge[] e(int N,int M){ return e(N,M,e -> e.l = 1); }

  Edge[] e(int N,int M,Consumer<Edge> f){
    return arr(new Edge[M],i -> {
      Edge e = new Edge(i,idx(),idx());
      f.accept(e);
      return e;
    });
  }

  Edge[][] g(int N,int M,boolean b){ return g(N,b,e(N,M)); }

  Edge[][] g(int N,int M,boolean b,Consumer<Edge> f){ return g(N,b,e(N,M,f)); }

  Edge[][] g(int N,boolean b,Edge[] E){
    int[] c = new int[N];

    for (Edge e:E) {
      c[e.u]++;
      if (!b)
        c[e.v]++;
    }

    Edge[][] g = arr(new Edge[N][],i -> new Edge[c[i]]);

    for (Edge e:E) {

      g[e.u][--c[e.u]] = e;
      if (!b) {
        Edge rev = new Edge(e.id,e.v,e.u);
        e.rev(rev);
        g[e.v][--c[e.v]] = e.rev = rev;
      }
    }
    return g;
  }
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
    else if (obj.getClass().isArray()) {
      int l = Array.getLength(obj);
      for (int i = 0;i < l;i++) {
        print(Array.get(obj,i));
        if (i +1 < l)
          write((byte) ' ');
      }
    } else if (obj instanceof Character)
      write((byte) (char) obj);
    else if (obj instanceof Integer)
      write((int) obj);
    else if (obj instanceof Long)
      write((long) obj);
    else if (obj instanceof char[])
      for (char b:(char[]) obj)
        write((byte) b);
    else
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