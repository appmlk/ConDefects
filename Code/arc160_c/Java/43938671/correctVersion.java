import static java.lang.Math.*;
import static java.util.Arrays.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.*;

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
    void println(Object obj){ super.println(obj == null ? "null" : obj); };

    @Override
    protected void ln(){
      super.ln();
      flush();
    };
  };

  int N = in.it();

  int[] A = in.it(N);
  int M = 200_100;

  Object solve(){
    int[] cnt = new int[M +1];
    for (var a:A)
      cnt[a]++;

    long[] dp = new long[1];
    dp[0] = 1;

    for (int a = 1;a <= M;a++) {
      int N2 = dp.length +cnt[a];
      long[] dp2 = new long[N2];
      for (int p = 0;p < dp.length;p++) {
        dp2[cnt[a]] += dp[p];
        if (cnt[a] +p /2 +1 < N2)
          dp2[cnt[a] +p /2 +1] += mod -dp[p];
      }
      dp2[0] %= mod;
      for (int i = 0;i +1 < N2;i++) {
        dp2[i +1] += dp2[i];
        dp2[i +1] %= mod;
      }
      int l = dp2.length;
      while (dp2[l -1] == 0)
        l--;
      dp = copyOf(dp2,l);
    }

    long ans = 0;
    for (var d:dp)
      ans += d;

    return ans %= mod;

  }

  private Object solve(int[] A){
    String fir = "First";
    String sec = "Second";
    int N = A.length;
    if (N %2 == 1)
      return sec;

    Set<Integer> set = new HashSet<>();
    for (var a:A)
      if (!set.add(a))
        set.remove(a);

    return set.size() == 0 ? sec : fir;
  }
}

class AVLTree<V> {
  @Override
  public String toString(){
    List<String> list = new ArrayList<>();
    for (int i = 0;i < size();i++) {
      V ee = get(i);
      list.add(ee.getClass().isArray() ? Arrays.toString((long[]) ee) : ee.toString());
    }
    return list.toString();
  }

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

class Node{
  Node par;
  int c;
  int cnt = 0;
  Node[] cld = new Node[26];

  public Node(Node par,int c){
    this.par = par;
    this.c = c;
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
