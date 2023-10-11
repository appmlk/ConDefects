import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.*;

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
    void println(Object obj){ super.println(obj == null ? "null" : obj); };

    @Override
    protected void ln(){
      super.ln();
      flush();
    };
  };
  int N = in.it();
  int K = in.it();
  long[][] T = in.lg(N,2);

  Object solve(){
    return bSearchI(infI,0,ans -> {

      long sum = 0;
      for (var t:T)
        if (t[0] >= ans)
          sum += t[1];

      return sum <= K;

    });
  }

  int bSearchI(int o,int n,Predicate<Integer> judge){
    for (int c = 0;1 < abs(n -o);)
      if (judge.test(c = o +n >>1))
        o = c;
      else
        n = c;
    return o;
  }
}

class AVLTree<V> {
  private V e;
  private Node nl;
  private Comparator<V> cmp;

  @SuppressWarnings("unchecked")
  AVLTree(V e){ this((Comparator<V>) Comparator.naturalOrder(),e); }

  @SuppressWarnings("unchecked")
  AVLTree(){ this((Comparator<V>) Comparator.naturalOrder(),null); }

  <U extends Comparable<U>> AVLTree(Function<V, U> f,V e){ this(Comparator.comparing(f),e); }

  <U extends Comparable<U>> AVLTree(Function<V, U> f){ this(Comparator.comparing(f),null); }

  protected AVLTree(Comparator<V> cmp,V e){
    this.e = e;
    nl = new Node(null);
    nl.par = nl.lft = nl.rht = nl;
    nl.sz = nl.cnt = nl.rnk = 0;
    this.cmp = cmp;
  }

  void add(V v){
    if (nl.rht == nl) {
      nl.cld(1,new Node(v));
      return;
    }

    Node nd = nl.rht;
    for (int c;true;nd = nd.cld(c))
      if ((c = cmp.compare(v,nd.val)) == 0) {
        nd.cnt++;
        break;
      } else if (nd.cld(c) == nl) {
        nd.cld(c,new Node(v));
        break;
      }

    balance(nd);
  }

  V get(int k){
    assert k < size();
    for (var nd = nl.rht;true;)
      if (k < nd.lft.sz)
        nd = nd.lft;
      else if (k < nd.lft.sz +nd.cnt)
        return nd.val;
      else {
        k -= nd.lft.sz +nd.cnt;
        nd = nd.rht;
      }
  }

  V sum(int k){
    assert k <= size();
    V ret = e;
    for (var nd = nl.rht;true;)
      if (k == 0)
        return ret;
      else if (k <= nd.lft.sz)
        nd = nd.lft;
      else if (k <= nd.lft.sz +nd.cnt)
        return sum(sum(ret,nd.lft == nl ? e : nd.lft.sum),pow(nd.val,k -nd.lft.sz));
      else {
        ret = sum(sum(ret,nd.lft == nl ? e : nd.lft.sum),pow(nd.val,nd.cnt));
        k -= nd.lft.sz +nd.cnt;
        nd = nd.rht;
      }
  }

  boolean remove(V v){
    Node nd;
    if ((nd = node(v)) == nl)
      return false;
    delete(nd);
    return true;
  }

  V peek(){ return first(); }

  V first(){ return end(nl.rht,-1).val; }

  V last(){ return end(nl.rht,1).val; }

  V floor(V v){ return near(nl.rht,v,2,1).val; }

  V ceiling(V v){ return near(nl.rht,v,2,-1).val; }

  V lower(V v){ return near(nl.rht,v,0,1).val; }

  V higher(V v){ return near(nl.rht,v,0,-1).val; }

  V poll(){ return pollFirst(); }

  V pollFirst(){ return poll(end(nl.rht,-1)); }

  V pollLast(){ return poll(end(nl.rht,1)); }

  V pollFloor(V v){ return poll(near(nl.rht,v,2,1)); }

  V pollCeiling(V v){ return poll(near(nl.rht,v,2,-1)); }

  V pollLower(V v){ return poll(near(nl.rht,v,0,1)); }

  V pollHigher(V v){ return poll(near(nl.rht,v,0,-1)); }

  V poll(Node nd){
    V ret = nd.val;
    delete(nd);
    return ret;
  }

  int cnt(V v){ return node(v).cnt; }

  boolean contains(V v){ return v != null && node(v) != nl; }

  int size(){ return nl.rht == nl ? 0 : nl.rht.sz; }

  boolean isEmpty(){ return nl.rht == nl; }

  int lowerBound(V v){ return bound(v,0); }

  int upperBound(V v){ return bound(v,1); }

  private int bound(V v,int u){
    var nd = nl.rht;
    for (int ret = 0,c;true;)
      if (nd == nl || (c = cmp.compare(v,nd.val)) == 0)
        return ret +u *nd.cnt +nd.lft.sz;
      else if (c < 0)
        nd = nd.lft;
      else if (0 < c) {
        ret += nd.lft.sz +nd.cnt;
        nd = nd.rht;
      }
  }

  private void delete(Node nd){
    if (nd == nl)
      return;

    if (1 < nd.cnt) {
      nd.cnt--;
      balance(nd);
      return;
    }

    if (nd.lft != nl && nd.rht != nl) {
      Node rep = end(nd.rht,-1);
      nd.ovr(rep);
      nd = rep;
    }

    balance(nd.par.cld(nd.par.lft == nd ? -1 : 1,nd.lft != nl ? nd.lft : nd.rht));
  }

  private Node end(Node nd,int c){
    while (nd.cld(c) != nl)
      nd = nd.cld(c);
    return nd;
  }

  private Node near(Node nd,V v,int eq,int l){
    var ret = nl;
    for (int c;nd != nl;nd = nd.cld(c *l))
      if ((c = cmp.compare(v,nd.val) *l) >= 0)
        if (nd.cld(l) == nl || c == (eq ^2))
          return nd;
        else
          ret = nd;
    return ret;
  }

  private Node node(V v){
    assert v != null;
    var ret = near(nl.rht,v,2,1);
    return Objects.equals(v,ret.val) ? ret : nl;
  }

  private void balance(Node nd){
    while (nd != nl) {
      nd.upd();
      var par = nd.par;
      if (abs(nd.bis) > 1) {
        int c = par.lft == nd ? -1 : 1;
        nd = nd.bis < 0
            ? nd.lft.bis > 0 ? rotate2(nd,1) : rotate1(nd,1)
            : nd.rht.bis < 0 ? rotate2(nd,-1) : rotate1(nd,-1);
        par.cld(c,nd);
      }
      nd = par;
    }
  }

  private Node rotate1(Node u,int c){
    var v = u.cld(-c);
    u.cld(-c,v.cld(c)).upd();
    v.cld(c,u).upd();
    return v;
  }

  private Node rotate2(Node u,int c){
    var v = u.cld(-c);
    var w = v.cld(c);
    u.cld(-c,w.cld(c)).upd();
    v.cld(c,w.cld(-c)).upd();
    w.cld(-c,v).cld(c,u).upd();
    return w;
  }

  private class Node{
    int sz,cnt,rnk,bis;
    V val,sum;
    Node par,lft,rht;

    Node(V val){
      sz = cnt = rnk = 1;
      this.val = sum = val;
      par = lft = rht = nl;
    }

    Node cld(int c){ return c < 0 ? lft : rht; }

    Node cld(int c,Node nd){
      if (c < 0)
        lft = nd;
      else
        rht = nd;
      nd.par = this;
      return this;
    }

    void ovr(Node nd){
      cnt = nd.cnt;
      val = nd.val;
      nd.cnt = 1;
    }

    void upd(){
      sz = lft.sz +rht.sz +cnt;
      rnk = max(lft.rnk,rht.rnk) +1;
      bis = rht.rnk -lft.rnk;
      sum = pow(val,cnt);
      if (lft != nl)
        sum = sum(sum,lft.sum);
      if (rht != nl)
        sum = sum(sum,rht.sum);
    }

  }

  V pow(V v,int cnt){ return null; }

  V sum(V v0,V v1){ return null; }
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

class VerfyAvl<T> {
  List<T> list;
  Comparator<T> cmp;

  @SuppressWarnings("unchecked")
  VerfyAvl(){ this((Comparator<T>) Comparator.naturalOrder()); }

  private VerfyAvl(Comparator<T> cmp){
    list = new ArrayList<>();
    this.cmp = cmp;
  }

  private void sort(){ list.sort(cmp); }

  T peek(){ return first(); }

  T first(){
    if (list.isEmpty())
      return null;
    return list.get(0);
  }

  T last(){
    if (list.isEmpty())
      return null;
    return list.get(list.size() -1);
  }

  T ceiling(T t){
    for (T e:list)
      if (cmp.compare(t,e) <= 0)
        return e;

    return null;
  }

  T floor(T t){
    for (int i = list.size() -1;i >= 0;i--) {
      var e = list.get(i);
      if (cmp.compare(e,t) <= 0)
        return e;
    }
    return null;
  }

  T poll(){ return pollFirst(); }

  T pollFirst(){ return poll(first()); }

  T pollLast(){ return poll(last()); }

  T pollCeiling(T t){ return poll(ceiling(t)); }

  T pollFloor(T t){ return poll(floor(t)); }

  T getKth(int k){ return list.get(k); }

  void add(T t){
    list.add(t);
    sort();
  }

  int lowerBound(T t){
    int ret = 0;
    for (T e:list)
      if (cmp.compare(e,t) < 0)
        ret++;
    return ret;
  }

  int upperBound(T t){
    int ret = 0;
    for (T e:list)
      if (cmp.compare(e,t) <= 0)
        ret++;
    return ret;
  }

  private T poll(T t){
    var ret = first();
    remove(ret);
    return ret;
  }

  boolean remove(T t){
    if (!contains(t))
      return false;
    list.remove(t);
    sort();
    return true;
  }

  boolean contains(T t){ return t != null && list.contains(t); }

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