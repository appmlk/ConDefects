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
  int N = in.it();
  char[][] S = in.ch(N);

  public Object solve(){

    return calc('R') +calc('B');
  }

  private int calc(char c){
    int[][] len = new int[N][N];
    for (var is:len)
      fill(is,infI);

    Deque<int[]> que = new ArrayDeque<>();
    if (c == 'R') {
      if (S[0][0] == c) {
        len[0][0] = 0;
        que.add(new int[]{0, 0, 0});
      } else {
        len[0][0] = 1;
        que.add(new int[]{0, 0, 1});
      }
    } else if (S[0][0] == c) {
      len[0][N -1] = 0;
      que.add(new int[]{0, N -1, 0});
    } else {
      len[0][N -1] = 1;
      que.add(new int[]{0, N -1, 1});
    }

    while (!que.isEmpty()) {
      var cur = que.poll();
      int i = cur[0];
      int j = cur[1];

      List<int[]> sur = new ArrayList<>();
      if (0 < i)
        sur.add(new int[]{i -1, j});
      if (0 < j)
        sur.add(new int[]{i, j -1});
      if (i +1 < N)
        sur.add(new int[]{i +1, j});
      if (j +1 < N)
        sur.add(new int[]{i, j +1});

      for (var s:sur) {
        int l = S[s[0]][s[1]] == c ? 0 : 1;
        if (len[s[0]][s[1]] > cur[2] +l) {
          len[s[0]][s[1]] = cur[2] +l;
          if (l == 0)
            que.addFirst(new int[]{s[0], s[1], len[s[0]][s[1]]});
          else
            que.addLast(new int[]{s[0], s[1], len[s[0]][s[1]]});
        }
      }
    }

    return c == 'R' ? len[N -1][N -1] : len[N -1][0];
  }
}

class Data extends BaseV{
  long v;

  public Data(long v){ this.v = v; }
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
    ri = 1;
  }

  public void build(int n,IntFunction<V> init){ root = build(0,n,init); }

  private Node build(int l,int r,IntFunction<V> init){
    if (r -l == 1)
      return new Node(init.apply(l),1);
    var ret = new Node(e(),r -l);
    ret.lft = build(l,l +r >>1,init);
    ret.rht = build(l +r >>1,r,init);
    return ret.update();
  }

  public void add(V v){ add(v,1); }

  public void add(V v,int k){ ins(size(),v,k); }

  public void ins(int i,V v){ ins(i,v,1); }

  public void ins(int i,V v,int k){ root = root == null ? new Node(v,k) : ins(root,i,v,k); }

  private Node ins(Node nd,int i,V v,int k){
    if (nd.lft == null && (i == 0 || i == nd.sz))
      split(nd,i == 0 ? 1 : -1,v,k,nd.sz +k);
    else {
      if (nd.lft == null)
        split(nd,1,ag(e(),e,nd.val),i,nd.sz);
      else
        nd.push();
      if (i < nd.lft.sz)
        nd.lft = ins(nd.lft,i,v,k);
      else
        nd.rht = ins(nd.rht,i -nd.lft.sz,v,k);
    }
    return balance(nd);
  }

  public void del(int i){ root = del(root,i); }

  private Node del(Node nd,int i){
    if (nd.lft == null)
      return 0 < --nd.sz ? nd : null;
    nd.push();
    int c = i < nd.lft.sz ? -1 : 1;
    Node del = c < 0 ? del(nd.lft,i) : del(nd.rht,i -nd.lft.sz);
    if (del == null)
      return nd.cld(-c);
    nd.cld(c,del);
    return balance(nd);
  }

  public void upd(int i,F f){ upd(i,i +1,f); }

  public void upd(int l,int r,F f){
    if (size() < r)
      add(e(),r -size());
    root = upd(root,l,r,f);
  }

  private Node upd(Node nd,int l,int r,F f){
    if (l == 0 && r == nd.sz)
      nd.prop(f);
    else if (l < r) {
      if (nd.lft == null)
        split(nd,1,ag(e(),e,nd.val),0 < l ? l : r,nd.sz);
      else
        nd.push();
      if (l < nd.lft.sz)
        nd.lft = upd(nd.lft,l,min(nd.lft.sz,r),f);
      if (nd.lft.sz < r)
        nd.rht = upd(nd.rht,max(0,l -nd.lft.sz),r -nd.lft.sz,f);
      nd = balance(nd);
    }
    return nd;
  }

  public void toggle(int l,int r){ root = l < r ? toggle(root,l,r) : root; }

  private Node toggle(Node nd,int l,int r){
    nd.push();
    if (0 < l) {
      split(nd,l);
      nd = merge(nd.lft,nd,toggle(nd.rht,0,r -l));
    } else if (r < nd.sz) {
      split(nd,r);
      nd = merge(toggle(nd.lft,l,r),nd,nd.rht);
    } else
      nd.toggle();
    return nd;
  }

  private void split(Node nd,int i){
    if (nd.lft == null)
      split(nd,1,ag(e(),e,nd.val),i,nd.sz);
    else {
      nd.push();
      Node tmp;
      if (i < nd.lft.sz) {
        split(tmp = nd.lft,i);
        nd.lft = tmp.lft;
        nd.rht = merge(tmp.rht,tmp,nd.rht);
      } else if (nd.lft.sz < i) {
        split(tmp = nd.rht,i -nd.lft.sz);
        nd.rht = tmp.rht;
        nd.lft = merge(nd.lft,tmp,tmp.lft);
      }
    }
  }

  private Node merge(Node lft,Node nd,Node rht){
    if (abs(lft.rnk -rht.rnk) < 2) {
      nd.lft = lft;
      nd.rht = rht;
    } else if (lft.rnk > rht.rnk) {
      lft.push();
      lft.rht = merge(lft.rht,nd,rht);
      nd = lft;
    } else if (lft.rnk < rht.rnk) {
      rht.push();
      rht.lft = merge(lft,nd,rht.lft);
      nd = rht;
    }
    return balance(nd);
  }

  public V get(int i){ return get(root,i); }

  private V get(Node nd,int i){
    if (nd.lft == null)
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
    if (0 == l && r == nd.sz)
      ag(ret[ri],ret[ri ^= 1],nd.val());
    else if (nd.lft == null)
      ag(ret[ri],ret[ri ^= 1],pw(nd.val,r -l));
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

  protected V pow(V a,int n){
    V ret = e();
    for (V t = ag(e(),e,a);0 < n;n >>= 1,t = ag(e(),t,t))
      if (0 < (n &1))
        ret = ag(e(),ret,t);
    return ret;
  }

  private V pw(V a,int n){
    V ret = pow(a,n);
    ret.sz = n;
    return ret;
  }

  private void split(Node nd,int c,V vl,int i,int sz){
    nd.cld(-c,new Node(vl,i));
    nd.cld(c,new Node(nd.val,sz -i));
    nd.val = e();
  }

  private Node balance(Node nd){ return (1 < abs(nd.bis = nd.rht.rnk -nd.lft.rnk) ? rotate(nd) : nd).update(); }

  private Node rotate(Node u){
    var v = u.cld(u.bis);
    v.push();
    if (u.bis *v.bis < -1)
      v = rotate(v);
    u.cld(u.bis,v.cld(-u.bis));
    v.cld(-u.bis,u);
    u.update();
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

    private Node update(){
      bis = rht.rnk -lft.rnk;
      rnk = max(lft.rnk,rht.rnk) +1;
      ag(val,lft.val(),rht.val());
      sz = val.sz;
      return this;
    }

    private void push(){
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
    }

    private void prop(F f){
      map(val,f);
      if (lft != null)
        laz = laz == null ? f : comp(laz,f);
    }

    private void toggle(){
      bis *= -1;
      var tn = lft;
      lft = rht;
      rht = tn;
      tog(val);
      if (lft != null)
        tog ^= 1;
    }

    private Node cld(int c){ return c < 0 ? lft : rht; }

    private void cld(int c,Node nd){ nd = c < 0 ? (lft = nd) : (rht = nd); }

    private V val(){ return lft == null && 1 < sz ? pw(val,sz) : val; }
  }
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
