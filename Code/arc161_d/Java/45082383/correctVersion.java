import static java.lang.Math.*;
import static java.util.Arrays.*;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.*;

class Solver {
  long st = System.currentTimeMillis();

  long elapsed() { return System.currentTimeMillis() - st; }

  void reset() { st = System.currentTimeMillis(); }

  static int infI = (1 << 30) - 1;
  static long infL = 1L << 60;
  // static long mod = (int) 1e9 +7;
  static long mod = 998244353;
  static String yes = "Yes";
  static String no = "No";

  Random rd = ThreadLocalRandom.current();
  MyReader in = new MyReader(System.in);
  MyWriter out = new MyWriter(System.out);
  MyWriter log = new MyWriter(System.err) {
    @Override
    void println(Object obj) { super.println(obj == null ? "null" : obj); };

    @Override
    protected void ln() {
      super.ln();
      flush();
    };
  };

  int N = in.it();
  int D = in.it();

  Object solve() {
    if (D * N > N * (N - 1L) / 2)
      return false;

    out.println(true);

    for (int u = 0; u < N; u++)
      for (int d = 1; d <= D; d++)
	out.println(u + 1, (u + d) % N + 1);

    return null;
  }

}

class Edge<L> extends Nd<L> {
  Node<L> u, v;

  Edge(int id, Node<L> u, Node<L> v, L val) {
    super(id, val);
    this.u = u;
    this.v = v;
  }

  @Override
  public String toString() { return "(" + getClass().getSimpleName() + "," + (u.id + 1) + "," + (v.id + 1) + ")"; }

}

class Node<L> extends Nd<L> {
  Node<L> p;
  int dpt;
  Collection<Edge<L>> go, back;

  Node(int id, L val) {
    super(id, val);
    go = new HashSet<>();
    back = new HashSet<>();
  }

  @Override
  public String toString() { return "" + (id + 1); }

}

class Nd<L> {
  int id, in, out;
  L val;

  Nd(int id, L val) {
    this.id = id;
    this.val = val;
  }

  @Override
  public int hashCode() { return Objects.hash(id); }

  @Override
  public boolean equals(Object obj) {
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

  @SuppressWarnings("unchecked")
  public Graph(int n, int m, boolean dir) {
    this.n = n;
    nds = new Node[n];
    tour = new Nd[2 * n];
    for (int i = 0; i < n; i++) {
      nds[i] = new Node<>(i, null);
      if (!dir)
	nds[i].back = nds[i].go;
    }
    es = new Edge[m];
  }

  public void addEdge(int u, int v, L l) {
    Node<L> nu = nds[u];
    Node<L> nv = nds[v];
    Edge<L> e = new Edge<>(eid, nu, nv, l);
    Edge<L> rev = new Edge<>(eid, nv, nu, l);
    nu.go.add(e);
    nv.back.add(rev);
    es[eid++] = e;
  }

  public Collection<Edge<L>> go(int u) { return nds[u].go; }

  public Collection<Edge<L>> back(int u) { return nds[u].back; }

  public void makeTree(int s) {
    lca = null;
    Stack<Node<L>> stk = new Stack<>();
    nds[s].dpt = -1;
    nds[s].p = null;
    stk.add(nds[s]);
    stk.add(nds[s]);
    int ei = 0;
    while (!stk.isEmpty()) {
      Node<L> u = stk.pop();
      if (u.dpt < 0) {
	tour[u.in = u.out = ei++] = u;
	for (Edge<L> e : u.go)
	  if (e.v != u.p) {
	    es[e.id] = e;
	    e.v.dpt = u.dpt - 1;
	    e.v.p = u;
	    stk.add(e.v);
	    stk.add(e.v);
	  }
	u.dpt = ~u.dpt;
      } else {
	u.out = ei;
	tour[ei++] = u.p;
      }
    }
    for (Edge<L> e : es) {
      e.in = e.v.in;
      e.out = e.v.out;
    }
  }

  public Node<L> lca(int u, int v) { return lca(nds[u], nds[v]); }

  public Node<L> lca(Node<L> u, Node<L> v) {
    if (lca == null)
      makeLca();

    int l = u.in;
    int r = v.in;
    if (l > r) {
      l ^= r;
      r ^= l;
      l ^= r;
    }
    r++;
    int k = max(1, 31 - Integer.numberOfLeadingZeros(r - l - 1));
    Node<L> a = lca[k * (2 * n - 1) + l];
    Node<L> b = lca[k * (2 * n - 1) + r - (1 << k)];
    return a.dpt < b.dpt ? a : b;
  }

  @SuppressWarnings("unchecked")
  private void makeLca() {
    int n = tour.length - 1;
    int K = max(1, 32 - Integer.numberOfLeadingZeros(n - 1));
    lca = new Node[K * n];
    for (int i = 0; i < n; i++)
      lca[i] = (Node<L>) tour[i];
    for (int k = 0; k + 1 < K; k++)
      for (int i = 0; i + (2 << k) <= n; i++) {
	Node<L> a = lca[k * n + i];
	Node<L> b = lca[k * n + i + (1 << k)];
	lca[(k + 1) * n + i] = a.dpt < b.dpt ? a : b;
      }
  }

}

class UnionFind {
  int num;
  int[] par;
  int[] size;

  public UnionFind(int n) {
    par = new int[n];
    Arrays.setAll(par, i -> i);
    size = new int[n];
    Arrays.fill(size, 1);
    num = n;
  }

  int root(int x) { return x == par[x] ? x : (par[x] = root(par[x])); }

  boolean same(int u, int v) { return root(u) == root(v); }

  boolean unite(int u, int v) {
    if ((u = root(u)) == (v = root(v)))
      return false;

    if (size[u] < size[v]) {
      u ^= v;
      v ^= u;
      u ^= v;
    }
    par[v] = u;
    size[u] += size[v];
    num--;
    return true;
  }

  int size(int x) { return size[root(x)]; }

}

abstract class Seg<V, F> {
  protected int n;
  private V e;
  private V[] val;
  private F[] lazy;
  private int[] rg;
  private int[] stk = new int[100];

  @SuppressWarnings("unchecked")
  Seg(int n, V e, IntFunction<V> sup) {
    this.n = n;
    this.e = e;
    val = (V[]) new Object[n << 1];
    lazy = (F[]) new Object[n];

    rg = new int[n << 1];
    for (int i = n << 1; --i > 0;)
      rg[i] = i < n ? rg[i << 1] : 1;

    build(sup);
  }

  void build(IntFunction<V> sup) {
    for (int i = 0; i < n; i++) {
      val[i] = e;
      val[i + n] = sup.apply(i);
    }
  }

  V agg(V v0, V v1) { throw new UnsupportedOperationException("agg"); }

  V map(V v, F f) { throw new UnsupportedOperationException("map"); }

  F comp(F f0, F f1) { throw new UnsupportedOperationException("comp"); }

  F powF(F f, int rg) { throw new UnsupportedOperationException("powF"); }

  void merge(int i) { val[i] = agg(eval(i << 1), eval(i << 1 | 1)); }

  void up(int l, int r) {
    l += n;
    r += n;
    l /= l & -l;
    r /= r & -r;
    while (l != r)
      if (l > r)
	merge(l >>= 1);
      else
	merge(r >>= 1);
    while (1 < l)
      merge(l >>= 1);
  }

  private void comp(int i, F f) {
    if (i < n)
      lazy[i] = lazy[i] != null ? comp(lazy[i], f) : f;
    else
      val[i] = map(val[i], f);
  }

  private V eval(int i) {

    if (i < n && lazy[i] != null) {
      val[i] = map(val[i], powF(lazy[i], rg[i]));
      comp(i << 1, lazy[i]);
      comp(i << 1 | 1, lazy[i]);
      lazy[i] = null;
    }

    return val[i];
  }

  protected void down(int l, int r) {
    l += n;
    r += n;
    l /= l & -l;
    r /= r & -r;
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

  void upd(int i, F f) { upd(i, i + 1, f); }

  void upd(int l, int r, F f) {
    l += n;
    r += n;
    do {
      if ((l & 1) == 1)
	comp(l++, f);
      if ((r & 1) == 1)
	comp(--r, f);
    } while ((l >>= 1) < (r >>= 1));
  }

  V get(int i) { return eval(i + n); }

  V get(int l, int r) {
    l += n;
    r += n;
    V vl = e;
    V vr = e;
    while (l < r) {
      if ((l & 1) == 1)
	vl = agg(vl, eval(l++));
      if ((r & 1) == 1)
	vr = agg(eval(--r), vr);
      l >>= 1;
      r >>= 1;
    }

    return agg(vl, vr);
  }

}

class DualSegmentTree<V, F> extends Seg<V, F> {

  DualSegmentTree(int n, V e) { this(n, e, i -> e); }

  DualSegmentTree(int n, V e, IntFunction<V> sup) { super(n, e, sup); }

  @Override
  F powF(F f, int rg) { return f; }

  @Override
  protected void upd(int l, int r, F f) {
    down(l, r);
    super.upd(l, r, f);
  }

  @Override
  V get(int i) {
    down(i, i + 1);
    return super.get(i);
  }

}

class RelaxedNTT {
  MyWriter log = new MyWriter(System.err) {
    @Override
    void println(Object obj) { super.println(obj == null ? "null" : obj); };

    @Override
    protected void ln() {
      super.ln();
      flush();
    };
  };
  int n = 0;
  long[] f = new long[16];
  long[] g = new long[16];
  long[] h = new long[16];

  long append(long a, long b) {
    f = grow(f, n);
    g = grow(g, n);
    f[n] = a;
    g[n] = b;
    n++;
    int m = n + 1 & -(n + 1);
    if (n < m) {
      m = m - 1 >> 1;
      calc(n >> 1, n, n >> 1, n);
    }

    int s = 0;
    for (int i = 1; i <= m; s += i, i <<= 1) {
      calc(n - i, n, s, s + i);
      calc(s, s + i, n - i, n);
    }
    return h[n - 1];
  }

  private void calc(int l1, int r1, int l2, int r2) {
    h = grow(h, r1 + r2 - 1);
    long[] conv = NTT.convolution(f, l1, r1, g, l2, r2);
    for (int i = 0; i < conv.length; i++)
      h[i + l1 + l2] = (h[i + l1 + l2] + conv[i]) % 998_244_353;
  }

  private long[] grow(long[] arr, int i) { return i < arr.length ? arr : copyOf(arr, i << 1); }
}

class NTT {
  static long[] convolution(long[] a, long[] b, int l) { return copyOf(convolution(a, b), l); }

  static long[] convolution(long[] a, long[] b) { return convolution(a, 0, a.length, b, 0, b.length); }

  static long[] convolution(long[] a, int al, int ar, long[] b, int bl, int br) {
    while (al < ar && a[ar - 1] == 0)
      ar--;
    while (bl < br && b[br - 1] == 0)
      br--;

    int n = ar - al;
    int m = br - bl;

    if (n == 0 || m == 0)
      return new long[0];

    int z = max(1, Integer.highestOneBit(n + m - 2) << 1);
    long[] ta = new long[z];
    long[] tb = new long[z];
    System.arraycopy(a, al, ta, 0, ar - al);
    System.arraycopy(b, bl, tb, 0, br - bl);
    a = ta;
    b = tb;

    int g = 3;
    long[][] sum = sum(998_244_353, g);

    butterfly(a, sum[0]);
    butterfly(b, sum[0]);
    for (int i = 0; i < z; i++)
      a[i] = a[i] * b[i] % 998_244_353;
    butterflyInv(a, sum[1]);

    long iz = pow(z, 998_244_353 - 2);
    for (int i = 0; i < a.length; i++)
      a[i] = a[i] * iz % 998_244_353;
    return a;
  }

  private static void butterflyInv(long[] a, long[] sum) {
    int n = a.length;
    int h = 32 - Integer.numberOfLeadingZeros(n - 1);

    for (int ph = h; ph >= 1; ph--) {
      int w = 1 << ph - 1, p = 1 << h - ph;
      long now = 1;
      for (int s = 0; s < w; s++) {
	int offset = s << h - ph + 1;
	for (int i = 0; i < p; i++) {
	  long l = a[i + offset];
	  long r = a[i + offset + p];
	  a[i + offset] = (l + r) % 998_244_353;
	  a[i + offset + p] = (998_244_353 + l - r) * now % 998_244_353;
	}
	int x = Integer.numberOfTrailingZeros(~s);
	now = now * sum[x] % 998_244_353;
      }
    }
  }

  private static void butterfly(long[] a, long[] sum) {
    int n = a.length;
    int h = 32 - Integer.numberOfLeadingZeros(n - 1);
    long ADD = (998_244_353 - 2L) * 998_244_353;

    for (int ph = 1; ph <= h; ph++) {
      int w = 1 << ph - 1, p = 1 << h - ph;
      long now = 1;
      for (int s = 0; s < w; s++) {
	int offset = s << h - ph + 1;
	for (int i = 0; i < p; i++) {
	  long l = a[i + offset];
	  long r = a[i + offset + p] * now;
	  a[i + offset] = (l + r) % 998_244_353;
	  a[i + offset + p] = (l - r + ADD) % 998_244_353;
	}
	int x = Integer.numberOfTrailingZeros(~s);
	now = now * sum[x] % 998_244_353;
      }
    }
  }

  private static long[][] sum(int mod, int g) {
    long[][] sum = new long[2][30];
    long[] es = new long[30];
    long[] ies = new long[30];
    int cnt2 = Integer.numberOfTrailingZeros(mod - 1);
    long e = pow(g, mod - 1 >> cnt2);
    long ie = pow(e, mod - 2);
    for (int i = cnt2; i >= 2; i--) {
      es[i - 2] = e;
      ies[i - 2] = ie;
      e = e * e % mod;
      ie = ie * ie % mod;
    }
    long se = 1;
    long sie = 1;
    for (int i = 0; i < cnt2 - 2; i++) {
      sum[0][i] = es[i] * se % mod;
      se = se * ies[i] % mod;
      sum[1][i] = ies[i] * sie % mod;
      sie = sie * es[i] % mod;
    }
    return sum;
  }

  static long pow(long x, long n) {
    x %= 998_244_353;
    long ret = 1;
    do {
      if ((n & 1) == 1)
	ret = ret * x % 998_244_353;
      x = x * x % 998_244_353;
    } while (0 < (n >>= 1));

    return ret;
  }

  static long[] sum(long[] a, long[] b) {
    long[] ret = new long[max(a.length, b.length)];
    for (int i = 0; i < a.length; i++)
      ret[i] += a[i];
    for (int i = 0; i < b.length; i++)
      ret[i] += b[i];

    for (int i = 0; i < ret.length; i++)
      if (998_244_353 < ret[i])
	ret[i] -= 998_244_353;
    return ret;
  }
}

class Util {
  static int[] arrI(int N, IntUnaryOperator f) {
    int[] ret = new int[N];
    setAll(ret, f);
    return ret;
  }

  static long[] arrL(int N, IntToLongFunction f) {
    long[] ret = new long[N];
    setAll(ret, f);
    return ret;
  }

  static double[] arrD(int N, IntToDoubleFunction f) {
    double[] ret = new double[N];
    setAll(ret, f);
    return ret;
  }

  static <T> T[] arr(T[] arr, IntFunction<T> f) {
    setAll(arr, f);
    return arr;
  }

}

class MyReader {
  byte[] buf = new byte[1 << 16];
  int ptr = 0;
  int tail = 0;
  InputStream in;

  MyReader(InputStream in) { this.in = in; }

  byte read() {
    if (ptr == tail)
      try {
	tail = in.read(buf);
	ptr = 0;
      } catch (IOException e) {}
    return buf[ptr++];
  }

  boolean isPrintable(byte c) { return 32 < c && c < 127; }

  boolean isNum(byte c) { return 47 < c && c < 58; }

  byte nextPrintable() {
    byte ret = read();
    while (!isPrintable(ret))
      ret = read();
    return ret;
  }

  int it() { return toIntExact(lg()); }

  int[] it(int N) { return Util.arrI(N, i -> it()); }

  int[][] it(int H, int W) { return Util.arr(new int[H][], i -> it(W)); }

  int idx() { return it() - 1; }

  int[] idx(int N) { return Util.arrI(N, i -> idx()); }

  int[][] idx(int H, int W) { return Util.arr(new int[H][], i -> idx(W)); }

  long lg() {
    byte i = nextPrintable();
    boolean negative = i == 45;
    long n = negative ? 0 : i - '0';
    while (isPrintable(i = read()))
      n = 10 * n + i - '0';
    return negative ? -n : n;
  }

  long[] lg(int N) { return Util.arrL(N, i -> lg()); }

  long[][] lg(int H, int W) { return Util.arr(new long[H][], i -> lg(W)); }

  double dbl() { return Double.parseDouble(str()); }

  double[] dbl(int N) { return Util.arrD(N, i -> dbl()); }

  double[][] dbl(int H, int W) { return Util.arr(new double[H][], i -> dbl(W)); }

  char[] ch() { return str().toCharArray(); }

  char[][] ch(int H) { return Util.arr(new char[H][], i -> ch()); }

  String line() {
    StringBuilder sb = new StringBuilder();

    for (byte c; (c = read()) != '\n';)
      sb.append((char) c);
    return sb.toString();
  }

  String str() {
    StringBuilder sb = new StringBuilder();
    sb.append((char) nextPrintable());

    for (byte c; isPrintable(c = read());)
      sb.append((char) c);
    return sb.toString();
  }

  String[] str(int N) { return Util.arr(new String[N], i -> str()); }

}

class MyWriter {
  OutputStream out;
  byte[] buf = new byte[1 << 16];
  byte[] ibuf = new byte[20];
  int tail = 0;

  MyWriter(OutputStream out) { this.out = out; }

  void flush() {
    try {
      out.write(buf, 0, tail);
      tail = 0;
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  protected void ln() { write((byte) '\n'); }

  private void write(byte b) {
    buf[tail++] = b;
    if (tail == buf.length)
      flush();
  }

  private void write(byte[] b, int off, int len) {
    for (int i = off; i < off + len; i++)
      write(b[i]);
  }

  private void write(long n) {
    if (n < 0) {
      n = -n;
      write((byte) '-');
    }

    int i = ibuf.length;
    do {
      ibuf[--i] = (byte) (n % 10 + '0');
      n /= 10;
    } while (n > 0);

    write(ibuf, i, ibuf.length - i);
  }

  private void print(Object obj) {
    if (obj instanceof Boolean)
      print((boolean) obj ? Solver.yes : Solver.no);
    else if (obj instanceof Character)
      write((byte) (char) obj);
    else if (obj instanceof Integer)
      write((int) obj);
    else if (obj instanceof Long)
      write((long) obj);
    else if (obj instanceof char[])
      for (char b : (char[]) obj)
	write((byte) b);
    else if (obj.getClass().isArray()) {
      int l = Array.getLength(obj);
      for (int i = 0; i < l; i++) {
	print(Array.get(obj, i));
	if (i + 1 < l)
	  write((byte) ' ');
      }
    } else
      for (char b : Objects.toString(obj).toCharArray())
	write((byte) b);
  }

  void println(Object... o) {
    print(Util.arr(new Object[o.length], i -> o[i]));
    ln();
  }

  void println(Object obj) {
    if (obj == null)
      return;

    if (obj instanceof Collection<?>)
      for (Object e : (Collection<?>) obj)
	println(e);
    else if (obj.getClass().isArray() && Array.getLength(obj) > 0 && !(Array.get(obj, 0) instanceof char[])
	&& Array.get(obj, 0).getClass().isArray()) {
      int l = Array.getLength(obj);
      for (int i = 0; i < l; i++)
	println(Array.get(obj, i));
    } else {
      print(obj);
      ln();
    }
  }
}

class Main {
  public static void main(String[] args) throws Exception {
    Solver solver = new Solver();
    Optional.ofNullable(solver.solve()).ifPresent(solver.out::println);
    solver.out.flush();
    // solver.log.println(solver.elapsed());
  }
}
