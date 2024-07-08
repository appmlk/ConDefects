import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import java.io.*;

class Solve extends Util {
  void solve() {
    int n = in.i();
    String t = in.s();
    char[] ts = t.toCharArray();
    String[] ss = new String[n];
    char[][] sss = new char[n][];
    for (int i = 0; i < n; i++) {
      ss[i] = in.s();
      sss[i] = ss[i].toCharArray();
    }

    // 前a文字、後b文字の部分列を持って、合わせてtの長さ以上なら連結したものは合格
    // それでも計算が間に合わない？

    int[] head = new int[n];
    int[] tail = new int[n];
    HashMap<Integer, Integer> headmap = new HashMap<>(n);
    HashMap<Integer, Integer> tailmap = new HashMap<>(n);
    for (int i = 0; i < n; i++) {
      {
        int tsi = 0;
        for (int j = 0; j < sss[i].length && tsi < ts.length; j++) {
          if (sss[i][j] == ts[tsi]) {
            tsi++;
          }
        }
        head[i] = tsi;
        headmap.put(tsi, headmap.getOrDefault(tsi, 0) + 1);
      }
      {
        int tsi = 0;
        for (int j = 0; j < sss[i].length && tsi < ts.length; j++) {
          if (sss[i][sss[i].length - j - 1] == ts[ts.length - tsi - 1]) {
            tsi++;
          }
        }
        tail[i] = tsi;
        tailmap.put(tsi, tailmap.getOrDefault(tsi, 0) + 1);
      }
    }

    long ans = 0;
    var headset = headmap.entrySet();
    var tailset = tailmap.entrySet();
    for (var hd : headset) {
      for (var td : tailset) {
        if (hd.getKey() + td.getKey() >= ts.length) ans += hd.getValue() * td.getValue();
      }
    }

    out.an(ans);
  }
}























// My Library
// Version: 2023.07.01
// =================================================================================================

// TODO:
// * end を半開区間にする

public class Main extends Util {
  public static void main(String[] args) {
    try {
      new Solve().solve();
    } catch (Exception e) {
      throw e;
    } finally {
      out.flush();
    }
  }
}

@FunctionalInterface
interface F1<T, R> extends Function<T, R> {
  default R x(T t) { return apply(t); }
}
@FunctionalInterface
interface F2<T, U, R> extends BiFunction<T, U, R> {
  default F1<U, R> x(T p1) { return p2 -> apply(p1, p2); }
  default F1<T, F1<U, R>> currying() { return p1 -> p2 -> apply(p1, p2); }
  default F2<U, T, R> flip() { return (p1, p2) -> apply(p2, p1); }
}
@FunctionalInterface
interface F3<T, U, V, R> {
  R apply(T t, U u, V v);
  default F2<U, V, R> x(T p1) { return (p2, p3) -> apply(p1, p2, p3); }
  default F1<V, R> x(T p1, U p2) { return p3 -> apply(p1, p2, p3); }
  default F1<T, F1<U, F1<V, R>>> currying() { return p1 -> p2 -> p3 -> apply(p1, p2, p3); }
  default F3<V, U, T, R> flip() { return (p1, p2, p3) -> apply(p3, p2, p1); }
}
@FunctionalInterface
interface F4<T, U, V, W, R> {
  R apply(T t, U u, V v, W w);
  default F3<U, V, W, R> x(T p1) { return (p2, p3, p4) -> apply(p1, p2, p3, p4); }
  default F2<V, W, R> x(T p1, U p2) { return (p3, p4) -> apply(p1, p2, p3, p4); }
  default F1<W, R> x(T p1, U p2, V p3) { return p4 -> apply(p1, p2, p3, p4); }
  default F1<T, F1<U, F1<V, F1<W, R>>>> currying() { return p1 -> p2 -> p3 -> p4 -> apply(p1, p2, p3, p4); }
  default F4<W, V, U, T, R> flip() { return (p1, p2, p3, p4) -> apply(p4, p3, p2, p1); }
}
@FunctionalInterface
interface F5<T, U, V, W, X, R> {
  R apply(T t, U u, V v, W w, X x);
  default F4<U, V, W, X, R> x(T p1) { return (p2, p3, p4, p5) -> apply(p1, p2, p3, p4, p5); }
  default F3<V, W, X, R> x(T p1, U p2) { return (p3, p4, p5) -> apply(p1, p2, p3, p4, p5); }
  default F2<W, X, R> x(T p1, U p2, V p3) { return (p4, p5) -> apply(p1, p2, p3, p4, p5); }
  default F1<X, R> x(T p1, U p2, V p3, W p4) { return p5 -> apply(p1, p2, p3, p4, p5); }
  default F1<T, F1<U, F1<V, F1<W, F1<X, R>>>>> currying() { return p1 -> p2 -> p3 -> p4 -> p5 -> apply(p1, p2, p3, p4, p5); }
  default F5<X, W, V, U, T, R> flip() { return (p1, p2, p3, p4, p5) -> apply(p5, p4, p3, p2, p1); }
}
@FunctionalInterface
interface F6<T, U, V, W, X, Y, R> {
  R apply(T t, U u, V v, W w, X x, Y y);
  default F5<U, V, W, X, Y, R> x(T p1) { return (p2, p3, p4, p5, p6) -> apply(p1, p2, p3, p4, p5, p6); }
  default F4<V, W, X, Y, R> x(T p1, U p2) { return (p3, p4, p5, p6) -> apply(p1, p2, p3, p4, p5, p6); }
  default F3<W, X, Y, R> x(T p1, U p2, V p3) { return (p4, p5, p6) -> apply(p1, p2, p3, p4, p5, p6); }
  default F2<X, Y, R> x(T p1, U p2, V p3, W p4) { return (p5, p6) -> apply(p1, p2, p3, p4, p5, p6); }
  default F1<Y, R> x(T p1, U p2, V p3, W p4, X p5) { return p6 -> apply(p1, p2, p3, p4, p5, p6); }
  default F1<T, F1<U, F1<V, F1<W, F1<X, F1<Y, R>>>>>> currying() { return p1 -> p2 -> p3 -> p4 -> p5 -> p6 -> apply(p1, p2, p3, p4, p5, p6); }
  default F6<Y, X, W, V, U, T, R> flip() { return (p1, p2, p3, p4, p5, p6) -> apply(p6, p5, p4, p3, p2, p1); }
}
@FunctionalInterface
interface F7<T, U, V, W, X, Y, Z, R> {
  R apply(T t, U u, V v, W w, X x, Y y, Z z);
  default F6<U, V, W, X, Y, Z, R> x(T p1) { return (p2, p3, p4, p5, p6, p7) -> apply(p1, p2, p3, p4, p5, p6, p7); }
  default F5<V, W, X, Y, Z, R> x(T p1, U p2) { return (p3, p4, p5, p6, p7) -> apply(p1, p2, p3, p4, p5, p6, p7); }
  default F4<W, X, Y, Z, R> x(T p1, U p2, V p3) { return (p4, p5, p6, p7) -> apply(p1, p2, p3, p4, p5, p6, p7); }
  default F3<X, Y, Z, R> x(T p1, U p2, V p3, W p4) { return (p5, p6, p7) -> apply(p1, p2, p3, p4, p5, p6, p7); }
  default F2<Y, Z, R> x(T p1, U p2, V p3, W p4, X p5) { return (p6, p7) -> apply(p1, p2, p3, p4, p5, p6, p7); }
  default F1<Z, R> x(T p1, U p2, V p3, W p4, X p5, Y p6) { return p7 -> apply(p1, p2, p3, p4, p5, p6, p7); }
  default F1<T, F1<U, F1<V, F1<W, F1<X, F1<Y, F1<Z, R>>>>>>> currying() { return p1 -> p2 -> p3 -> p4 -> p5 -> p6 -> p7 -> apply(p1, p2, p3, p4, p5, p6, p7); }
  default F7<Z, Y, X, W, V, U, T, R> flip() { return (p1, p2, p3, p4, p5, p6, p7) -> apply(p7, p6, p5, p4, p3, p2, p1); }
}

class RLENode<T> {
  T val;
  int len;
  RLENode(T val, int len) {
    this.val = val;
    this.len = len;
  }
}
// FIXME:
class RLE<T> {
  RLENode<T>[] values;

  public RLE(T[] target) {
    if (target.length == 0) {
      @SuppressWarnings("unchecked")
      RLENode<T>[] array = new RLENode[0];
      values = array;
      return;
    }

    List<RLENode<T>> list = new ArrayList<>();
    T cur = target[0];
    int cnt = 1;
    for (int i = 1; i < target.length; i++) {
      if (target[i].equals(cur)) { // NOTE: 参照比較をしないように注意
        cnt++;
      } else {
        list.add(new RLENode<T>(cur, cnt));
        cur = target[i];
        cnt = 1;
      }
    }
    list.add(new RLENode<T>(cur, cnt));
    @SuppressWarnings("unchecked")
    RLENode<T>[] array = list.toArray(RLENode[]::new);
    values = array;
  }

  void print() {
    for (int i = 0; i < values.length; i++) {
      System.out.println(values[i].val + " " + values[i].len);
    }
  }

  HashMap<T, Integer> toMap() {
    HashMap<T, Integer> map = new HashMap<>();
    for (int i = 0; i < values.length; i++) {
      map.put(values[i].val, values[i].len);
    }
    return map;
  }
}

class Graph {
  List<Integer>[] edges;
  int[] weights;
  int n;
  boolean sortedEdges;
  boolean isDirected;

  @SuppressWarnings("unchecked")
  Graph(int n, boolean isDirected, boolean sortEdges) {
    this.n = n;
    this.edges = new ArrayList[n+1];
    for (int i = 1; i <= n; i++) {
      edges[i] = new ArrayList<Integer>();
    }
    this.weights = new int[n+1];
    this.sortedEdges = !sortEdges;
    this.isDirected = isDirected;
  }

  Graph(int n, boolean isDirected) {
    this(n, isDirected, false);
  }

  public void addEdge(int u, int v) {
    assert 1 <= u && u <= n;
    assert 1 <= v && v <= n;
    edges[u].add(v);
    if (!isDirected) edges[v].add(u);
    sortedEdges = false;
  }

  public void addEdge(int[] u, int[] v) {
    assert u != null && v != null;
    assert u.length == v.length;
    for (int i = 0; i < u.length; i++) addEdge(u[i], v[i]);
  }

  private void sortEdges() {
    for (int i = 1; i <= n; i++) Collections.sort(this.edges[i]);
    sortedEdges = true;
  }

  public List<Integer> bfs(Graph g, int s) {
    int[] ps = new int[this.n+1];
    int[] ds = new int[this.n+1];
    int[] cs = new int[this.n+1];
    List<Integer> path = new ArrayList<Integer>();
    Arrays.fill(ds, Integer.MAX_VALUE);
    ds[s] = 0;

    Deque<Integer> q = new ArrayDeque<Integer>();
    q.add(s);
    cs[s] = 1;

    while (!q.isEmpty()) {
      int u = q.remove();
      path.add(u);
      for (int v : g.edges[u]) {
        if (cs[v] == 0) {
          ps[v] = u;
          ds[v] = ds[u] + 1;
          cs[v] = 1;
          q.add(v);
        }
      }
      cs[u] = 2;
    }

    return path;
  }

  public int[] dfs(int s, int g) {
    int[] ds = new int[n+1]; // depth
    int[] fs = new int[n+1]; // first discovered
    int[] cs = new int[n+1]; // color (0: white, 1: gray, 2: black)
    int[] pi = new int[n+1]; // parent index
    List<Integer> path = new ArrayList<Integer>();
    int time = 0;

    sortEdges();
    dfs(ds, fs, cs, pi, path, time, s, g);

    int[] res = Util.toIntArray(path);
    Util.reverse(res);

    return res;
  }

  public boolean dfs(int[] ds, int[] fs, int[] cs, int[] pi, List<Integer> path, int time, int u, int g) {
    time++;
    ds[u] = time;
    cs[u] = 1;
    if (u == g) {
      path.add(u);
      return true;
    }
    for (int v : this.edges[u]) {
      if (pi[u] != v) {
        pi[v] = u;
        boolean res = dfs(ds, fs, cs, pi, path, time, v, g);
        if (res) {
          path.add(u);
          return true;
        }
      }
    }
    cs[u] = 2;
    time++;
    fs[u] = time;
    return false;
  }
}

// convert list, array, map, set, queue, stack
class Test {
  public static long pow(long a, long b, long mod) {
    if (b == 0) return 1;
    if (b == 1) return a;
    if (b % 2 == 0) return pow(a * a % mod, b / 2, mod);
    return a * pow(a * a % mod, b / 2, mod) % mod;
  }

  public static void sortSome(long[] key, long[]... value) {
    long[][] array = new long[key.length][1+value.length];
    for (int i = 0; i < key.length; i++) {
      array[0][i] = key[i];
      for (int j = 1; j < array[i].length; j++) {
        array[j][i] = value[j-1][i];
      }
    }
    Arrays.sort(array, Comparator.comparingLong(a -> a[0]));
  }

  public static long[] primeFact(long n, boolean allow_dup) {
    List<Long> list = new ArrayList<>();
    int upper = (int)Math.sqrt(n) + 1;
    for (long i = 2; i <= upper; i++) {
      if (n % i != 0) continue;
      list.add(i);
      n /= i;
      while (n % i == 0) {
        if (allow_dup) list.add(i);
        n /= i;
      }
    }
    if (n != 1) list.add(n);
    return list.stream().mapToLong(i -> i).toArray();
  }
  static List<long[]> permutation(long[] seed) {
    List<long[]> list = new ArrayList<>();
    long[] perm = new long[seed.length];
    boolean[] used = new boolean[seed.length];
    buildPerm(list, seed, perm, used, 0);
    return list;
  }
  static void buildPerm(List<long[]> list, long[] seed, long[] perm, boolean[] used, int index) {
    if (index == seed.length) {
      list.add(perm.clone());
      return;
    }
    for (int i = 0; i < seed.length; i++) {
      if (used[i]) continue;
      perm[index] = seed[i];
      used[i] = true;
      buildPerm(list, seed, perm, used, index + 1);
      used[i] = false;
    }
  }

  static void combInternal(List<int[]> res, int input[], int empty[], int start, int end, int index, int r) {
    if (index == r) {
      res.add(Arrays.copyOf(empty, r));
      return;
    }

    for (int y = start; y <= end && end - y + 1 >= r - index; y++) {
      empty[index] = input[y];
      combInternal(res, input, empty, y + 1, end, index + 1, r);
    }
  }

  static List<int[]> combination(int input[], int n, int r) {
    int empty[] = new int[r];
    List<int[]> list = new ArrayList<>();
    combInternal(list, input, empty, 0, n-1, 0, r);
    return list;
  }
}

class Util {
  static In in = new In();
  static Out out = new Out();
  public static final int N10_9 = 1000000000;
  public static final int N10_9_7 = 1000000007;
  public static final char[] abc = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                                    'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
  public static final char[] ABC = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                                    'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

  public static void sort(Object[] a) {
    Arrays.sort(a);
  }
  public static <T> void sort(T[] a, Comparator<? super T> c) {
    Arrays.sort(a, c);
  }
  public static void sort(int[] a) {
    Arrays.sort(a);
  }
  public static void sort(int[] a, int from, int to) {
    Arrays.sort(a, from, to);
  }
  public static void sort(long[] a) {
    Arrays.sort(a);
  }
  public static void sort(long[] a, int from, int to) {
    Arrays.sort(a, from, to);
  }
  public static void sort(double[] a) {
    Arrays.sort(a);
  }
  public static void sort(double[] a, int from, int to) {
    Arrays.sort(a, from, to);
  }
  public static void sort(char[] a) {
    Arrays.sort(a);
  }
  public static void sort(char[] a, int from, int to) {
    Arrays.sort(a, from, to);
  }

  public static int gcd(int a, int b) {
    return MyMath.gcd(a, b);
  }
  public static long gcd(long a, long b) {
    return MyMath.gcd(a, b);
  }
  public static int lcm(int a, int b) {
      return MyMath.lcm(a, b);
  }
  public static long lcm(long a, long b) {
      return MyMath.lcm(a, b);
  }

  public static int sum(int[] a, int start, int end) {
    int sum = 0;
    for (int i = start; i < end; i++) sum += a[i];
    return sum;
  }
  public static long sum(long[] a, int start, int end) {
    long sum = 0;
    for (int i = start; i < end; i++) sum += a[i];
    return sum;
  }
  public static double sum(double[] a, int start, int end) {
    double sum = 0;
    for (int i = start; i < end; i++) sum += a[i];
    return sum;
  }
  public static int sum(int[] a) {
    return sum(a, 0, a.length);
  }
  public static long sum(long[] a) {
    return sum(a, 0, a.length);
  }
  public static double sum(double[] a) {
    return sum(a, 0, a.length);
  }
  public static int sum(int a, int ... b) {
    return a + sum(b);
  }
  public static long sum(long a, long ... b) {
    return a + sum(b);
  }
  public static double sum(double a, double ... b) {
    return a + sum(b);
  }

  public static int lower_bound(List<Integer> list, int target) {
    return ~Collections.binarySearch(list, target, (x, y) -> x.compareTo(y) >= 0 ? 1 : -1);
  }

  public static int upper_bound(List<Integer> list, int target) {
    return ~Collections.binarySearch(list, target, (x, y) -> x.compareTo(y) > 0 ? 1 : -1);
  }

  public static int lower_bound(List<Long> list, long target) {
    return ~Collections.binarySearch(list, target, (x, y) -> x.compareTo(y) >= 0 ? 1 : -1);
  }

  public static int upper_bound(List<Long> list, long target) {
    return ~Collections.binarySearch(list, target, (x, y) -> x.compareTo(y) > 0 ? 1 : -1);
  }

  // ビット全探索
  public static boolean[][] bitAll(int n) {
    boolean[][] res = new boolean[1 << n][n];
    for (int i = 0; i < 1 << n; i++) {
      for (int j = 0; j < n; j++) {
        res[i][j] = (i >> j & 1) == 1;
      }
    }
    return res;
  }
  public static boolean[][] bitAll(int n, int bitCount) {
    List<boolean[]> res = new ArrayList<>();
    boolean[] b = new boolean[n];
    for (int i = (1 << bitCount) - 1; i < 1 << n;) {
      assert Integer.bitCount(i) == bitCount;

      for (int j = 0; j < n; j++) b[j] = (i >> j & 1) == 1;
      res.add(b.clone());

      int t = i | i - 1;
      i = t + 1 | (~t & -~t) - 1 >> Integer.numberOfTrailingZeros(i) + 1;
    }
    return toBooleanArrayArray(res);
  }
  public static int[][] bitAllFilter(boolean[][] use, int[] array) {
    int[][] res = new int[use.length][];
    for (int i = 0; i < use.length; i++) {
      int size = 0;
      for (int j = 0; j < use[i].length; j++) if (use[i][j]) size++;
      res[i] = new int[size];
      int index = 0;
      for (int j = 0; j < array.length; j++)
        if (use[i][j]) res[i][index++] = array[j];
    }
    return res;
  }
  public static long[][] bitAllFilter(boolean[][] use, long[] array) {
    long[][] res = new long[use.length][];
    for (int i = 0; i < use.length; i++) {
      int size = 0;
      for (int j = 0; j < use[i].length; j++) if (use[i][j]) size++;
      res[i] = new long[size];
      int index = 0;
      for (int j = 0; j < array.length; j++)
        if (use[i][j]) res[i][index++] = array[j];
    }
    return res;
  }
  public static double[][] bitAllFilter(boolean[][] use, double[] array) {
    double[][] res = new double[use.length][];
    for (int i = 0; i < use.length; i++) {
      int size = 0;
      for (int j = 0; j < use[i].length; j++) if (use[i][j]) size++;
      res[i] = new double[size];
      int index = 0;
      for (int j = 0; j < array.length; j++)
        if (use[i][j]) res[i][index++] = array[j];
    }
    return res;
  }
  public static Object[][] bitAllFilter(boolean[][] use, Object[] array) {
    Object[][] res = new Object[use.length][];
    for (int i = 0; i < use.length; i++) {
      int size = 0;
      for (int j = 0; j < use[i].length; j++) if (use[i][j]) size++;
      res[i] = new Object[size];
      int index = 0;
      for (int j = 0; j < array.length; j++)
        if (use[i][j]) res[i][index++] = array[j];
    }
    return res;
  }
  public static int[][][] bitAllFilter(boolean[][] use, int[][] array) {
    int[][][] res = new int[use.length][][];
    for (int i = 0; i < use.length; i++) {
      int size = 0;
      for (int j = 0; j < use[i].length; j++) if (use[i][j]) size++;
      res[i] = new int[size][];
      int index = 0;
      for (int j = 0; j < array.length; j++)
        if (use[i][j]) res[i][index++] = array[j];
    }
    return res;
  }
  public static long[][][] bitAllFilter(boolean[][] use, long[][] array) {
    long[][][] res = new long[use.length][][];
    for (int i = 0; i < use.length; i++) {
      int size = 0;
      for (int j = 0; j < use[i].length; j++) if (use[i][j]) size++;
      res[i] = new long[size][];
      int index = 0;
      for (int j = 0; j < array.length; j++)
        if (use[i][j]) res[i][index++] = array[j];
    }
    return res;
  }

  public static int[] arrayRange(int start, int end, int headspace, int tailspace) {
    int n = end - start + 1;
    int[] res = new int[n + headspace + tailspace];
    int cur = start;
    for (int i = headspace; i < n + headspace; i++) res[i] = cur++;
    return res;
  }
  public static int[] arrayRange(int start, int end) {
    return arrayRange(start, end, 0, 0);
  }

  public static long[] arrayRange(long start, long end, int headspace, int tailspace) {
    long n = end - start + 1;
    long[] res = new long[(int)n + headspace + tailspace];
    long cur = start;
    for (int i = headspace; i < n + headspace; i++) res[i] = cur++;
    return res;
  }
  public static long[] arrayRange(long start, long end) {
    return arrayRange(start, end, 0, 0);
  }

  public static long[] toLongArray(Collection<?> list) {
    return list.stream().mapToLong(i -> (long)i).toArray();
  }
  public static int[] toIntArray(Collection<?> list) {
    return list.stream().mapToInt(i -> (int)i).toArray();
  }
  public static double[] toDoubleArray(Collection<?> list) {
    return list.stream().mapToDouble(i -> (double)i).toArray();
  }
  public static boolean[] toBooleanArray(Collection<?> list) {
    Object[] a = list.toArray();
    boolean[] b = new boolean[a.length];
    for (int i = 0; i < b.length; i++) {
      b[i] = (boolean)a[i];
    }
    return b;
  }
  public static String[] toStringArray(Collection<?> list) {
    return list.stream().map(i -> i.toString()).toArray(String[]::new);
  }

  public static long[][] toLongArrayArray(Collection<?> list) {
    return list.toArray(long[][]::new);
  }
  public static int[][] toIntArrayArray(Collection<?> list) {
    return list.toArray(int[][]::new);
  }
  public static double[][] toDoubleArrayArray(Collection<?> list) {
    return list.toArray(double[][]::new);
  }
  public static boolean[][] toBooleanArrayArray(Collection<?> list) {
    return list.toArray(boolean[][]::new);
  }
  public static String[][] toStringArrayArray(Collection<?> list) {
    return list.toArray(String[][]::new);
  }

  public static <T> void reverse(T[] array) {
    int len = array.length;
    for (int i = 0; i < len / 2; i++) swap(array, i, len - i - 1);
  }
  public static void reverse(boolean[] array) {
    int len = array.length;
    for (int i = 0; i < len / 2; i++) swap(array, i, len - i - 1);
  }
  public static void reverse(int[] array) {
    int len = array.length;
    for (int i = 0; i < len / 2; i++) swap(array, i, len - i - 1);
  }
  public static void reverse(long[] array) {
    int len = array.length;
    for (int i = 0; i < len / 2; i++) swap(array, i, len - i - 1);
  }
  public static void reverse(double[] array) {
    int len = array.length;
    for (int i = 0; i < len / 2; i++) swap(array, i, len - i - 1);
  }
  public static void reverse(float[] array) {
    int len = array.length;
    for (int i = 0; i < len / 2; i++) swap(array, i, len - i - 1);
  }
  public static void reverse(byte[] array) {
    int len = array.length;
    for (int i = 0; i < len / 2; i++) swap(array, i, len - i - 1);
  }
  public static void reverse(char[] array) {
    int len = array.length;
    for (int i = 0; i < len / 2; i++) swap(array, i, len - i - 1);
  }

  public static int[] distinct(int[] a) {
    return Arrays.stream(a).distinct().toArray();
  }
  public static long[] distinct(long[] a) {
    return Arrays.stream(a).distinct().toArray();
  }
  public static double[] distinct(double[] a) {
    return Arrays.stream(a).distinct().toArray();
  }

  public static void fill(int[] a, int v) {
    Arrays.fill(a, v);
  }
  public static void fill(long[] a, long v) {
    Arrays.fill(a, v);
  }
  public static void fill(double[] a, double v) {
    Arrays.fill(a, v);
  }
  public static void fill(boolean[] a, boolean v) {
    Arrays.fill(a, v);
  }
  public static void fill(char[] a, char v) {
    Arrays.fill(a, v);
  }

  public static void fill(int[][] a, int v) {
    for (int[] i : a) fill(i, v);
  }
  public static void fill(long[][] a, long v) {
    for (long[] i : a) fill(i, v);
  }
  public static void fill(double[][] a, double v) {
    for (double[] i : a) fill(i, v);
  }
  public static void fill(boolean[][] a, boolean v) {
    for (boolean[] i : a) fill(i, v);
  }
  public static void fill(char[][] a, char v) {
    for (char[] i : a) fill(i, v);
  }

  public static void fill(int[][][] a, int v) {
    for (int[][] i : a) fill(i, v);
  }
  public static void fill(long[][][] a, long v) {
    for (long[][] i : a) fill(i, v);
  }
  public static void fill(double[][][] a, double v) {
    for (double[][] i : a) fill(i, v);
  }
  public static void fill(boolean[][][] a, boolean v) {
    for (boolean[][] i : a) fill(i, v);
  }
  public static void fill(char[][][] a, char v) {
    for (char[][] i : a) fill(i, v);
  }

  public static void fill(int[][][][] a, int v) {
    for (int[][][] i : a) fill(i, v);
  }
  public static void fill(long[][][][] a, long v) {
    for (long[][][] i : a) fill(i, v);
  }
  public static void fill(double[][][][] a, double v) {
    for (double[][][] i : a) fill(i, v);
  }
  public static void fill(boolean[][][][] a, boolean v) {
    for (boolean[][][] i : a) fill(i, v);
  }
  public static void fill(char[][][][] a, char v) {
    for (char[][][] i : a) fill(i, v);
  }

  // NOTE: These methods require Java 16 or higher.
  public static List<Long> arrayToList(long[] array) {
    return Arrays.stream(array).boxed().collect(Collectors.toList());
    //return Arrays.stream(array).boxed().toList();
  }
  public static List<Integer> arrayToList(int[] array) {
    return Arrays.stream(array).boxed().collect(Collectors.toList());
    //return Arrays.stream(array).boxed().toList();
  }
  public static List<Double> arrayToList(double[] array) {
    return Arrays.stream(array).boxed().collect(Collectors.toList());
    //return Arrays.stream(array).boxed().toList();
  }
  public static List<Boolean> arrayToList(boolean[] array) {
    Boolean[] a = new Boolean[array.length];
    for (int i = 0; i < a.length; i++) a[i] = array[i];
    return Arrays.stream(a).collect(Collectors.toList());
    //return Arrays.stream(a).toList();
  }
  public static List<Character> arrayToList(char[] array) {
    return Arrays.stream(arrayBoxed(array)).collect(Collectors.toList());
  }
  public static <T> List<T> arrayToList(T[] array) {
    return Arrays.stream(array).collect(Collectors.toList());
    //return Arrays.stream(array).toList();
  }

  public static Long[] arrayBoxed(long[] array) {
    return Arrays.stream(array).boxed().toArray(Long[]::new);
  }
  public static Integer[] arrayBoxed(int[] array) {
    return Arrays.stream(array).boxed().toArray(Integer[]::new);
  }
  public static Double[] arrayBoxed(double[] array) {
    return Arrays.stream(array).boxed().toArray(Double[]::new);
  }
  public static Boolean[] arrayBoxed(boolean[] array) {
    Boolean[] a = new Boolean[array.length];
    for (int i = 0; i < array.length; i++) {
      a[i] = array[i];
    }
    return a;
  }
  public static Character[] arrayBoxed(char[] array) {
    Character[] a = new Character[array.length];
    for (int i = 0; i < array.length; i++) {
      a[i] = array[i];
    }
    return a;
  }

  public static long[] arrayUnboxed(Long[] array) {
    return Arrays.stream(array).mapToLong(i -> i).toArray();
  }
  public static int[] arrayUnboxed(Integer[] array) {
    return Arrays.stream(array).mapToInt(i -> i).toArray();
  }
  public static double[] arrayUnboxed(Double[] array) {
    return Arrays.stream(array).mapToDouble(i -> i).toArray();
  }
  public static boolean[] arrayUnboxed(Boolean[] array) {
    boolean[] a = new boolean[array.length];
    for (int i = 0; i < array.length; i++) {
      a[i] = array[i];
    }
    return a;
  }
  public static char[] arrayUnboxed(Character[] array) {
    char[] a = new char[array.length];
    for (int i = 0; i < array.length; i++) {
      a[i] = array[i];
    }
    return a;
  }

  // 最大値 (int)
  public static int max(int a, int b) {
    return Math.max(a, b);
  }
  public static int max(int a, int ... nums) {
    return max(a, max(nums));
  }
  public static int max(int[] a, int start, int end) {
    assert start >= 0 && end < a.length && start <= end;
    int res = a[0];
    for (int i = start; i <= end; i++) res = Math.max(res, a[i]);
    return res;
  }
  public static int max(int[] a, int start) {
    return max(a, start, a.length - 1);
  }
  public static int max(int[] a) {
    return max(a, 0, a.length - 1);
  }
  public static int maxIndex(int[] a, int start, int end) {
    assert start >= 0 && end < a.length && start <= end;
    int res = start;
    for (int i = start; i <= end; i++) {
      if (a[i] > a[res]) res = i;
    }
    return res;
  }
  public static int maxIndex(int[] a, int start) {
    return maxIndex(a, start, a.length - 1);
  }
  public static int maxIndex(int[] a) {
    return maxIndex(a, 0, a.length - 1);
  }

  // 最小値 (int)
  public static int min(int a, int b) {
    return Math.min(a, b);
  }
  public static int min(int a, int ... nums) {
    return min(a, min(nums));
  }
  public static int min(int[] a, int start, int end) {
    assert start >= 0 && end < a.length && start <= end;
    int res = a[0];
    for (int i = start; i <= end; i++) res = Math.min(res, a[i]);
    return res;
  }
  public static int min(int[] a, int start) {
    return min(a, start, a.length - 1);
  }
  public static int min(int[] a) {
    return min(a, 0, a.length - 1);
  }
  public static int minIndex(int[] a, int start, int end) {
    assert start >= 0 && end < a.length && start <= end;
    int res = start;
    for (int i = start; i <= end; i++) {
      if (a[i] < a[res]) res = i;
    }
    return res;
  }
  public static int minIndex(int[] a, int start) {
    return minIndex(a, start, a.length - 1);
  }
  public static int minIndex(int[] a) {
    return minIndex(a, 0, a.length - 1);
  }

  // 最大値 (long)
  public static long max(long a, long b) {
    return Math.max(a, b);
  }
  public static long max(long a, long ... nums) {
    return max(a, max(nums));
  }
  public static long max(long[] a, int start, int end) {
    assert start >= 0 && end < a.length && start <= end;
    long res = a[0];
    for (int i = start; i <= end; i++) res = Math.max(res, a[i]);
    return res;
  }
  public static long max(long[] a, int start) {
    return max(a, start, a.length - 1);
  }
  public static long max(long[] a) {
    return max(a, 0, a.length - 1);
  }
  public static int maxIndex(long[] a, int start, int end) {
    assert start >= 0 && end < a.length && start <= end;
    int res = start;
    for (int i = start; i <= end; i++) {
      if (a[i] > a[res]) res = i;
    }
    return res;
  }
  public static int maxIndex(long[] a, int start) {
    return maxIndex(a, start, a.length - 1);
  }
  public static int maxIndex(long[] a) {
    return maxIndex(a, 0, a.length - 1);
  }

  // 最小値 (long)
  public static long min(long a, long b) {
    return Math.min(a, b);
  }
  public static long min(long a, long ... nums) {
    return min(a, min(nums));
  }
  public static long min(long[] a, int start, int end) {
    assert start >= 0 && end < a.length && start <= end;
    long res = a[0];
    for (int i = start; i <= end; i++) res = Math.min(res, a[i]);
    return res;
  }
  public static long min(long[] a, int start) {
    return min(a, start, a.length - 1);
  }
  public static long min(long[] a) {
    return min(a, 0, a.length - 1);
  }
  public static int minIndex(long[] a, int start, int end) {
    assert start >= 0 && end < a.length && start <= end;
    int res = start;
    for (int i = start; i <= end; i++) {
      if (a[i] < a[res]) res = i;
    }
    return res;
  }
  public static int minIndex(long[] a, int start) {
    return minIndex(a, start, a.length - 1);
  }
  public static int minIndex(long[] a) {
    return minIndex(a, 0, a.length - 1);
  }

  // 最大値 (double)
  public static double max(double a, double b) {
    return Math.max(a, b);
  }
  public static double max(double a, double ... nums) {
    return max(a, max(nums));
  }
  public static double max(double[] a, int start, int end) {
    assert start >= 0 && end < a.length && start <= end;
    double res = a[0];
    for (int i = start; i <= end; i++) res = Math.max(res, a[i]);
    return res;
  }
  public static double max(double[] a, int start) {
    return max(a, start, a.length - 1);
  }
  public static double max(double[] a) {
    return max(a, 0, a.length - 1);
  }
  public static int maxIndex(double[] a, int start, int end) {
    assert start >= 0 && end < a.length && start <= end;
    int res = start;
    for (int i = start; i <= end; i++) {
      if (a[i] > a[res]) res = i;
    }
    return res;
  }
  public static int maxIndex(double[] a, int start) {
    return maxIndex(a, start, a.length - 1);
  }
  public static int maxIndex(double[] a) {
    return maxIndex(a, 0, a.length - 1);
  }

  // 最小値 (double)
  public static double min(double a, double b) {
    return Math.min(a, b);
  }
  public static double min(double a, double ... nums) {
    return min(a, min(nums));
  }
  public static double min(double[] a, int start, int end) {
    assert start >= 0 && end < a.length && start <= end;
    double res = a[0];
    for (int i = start; i <= end; i++) res = Math.min(res, a[i]);
    return res;
  }
  public static double min(double[] a, int start) {
    return min(a, start, a.length - 1);
  }
  public static double min(double[] a) {
    return min(a, 0, a.length - 1);
  }
  public static int minIndex(double[] a, int start, int end) {
    assert start >= 0 && end < a.length && start <= end;
    int res = start;
    for (int i = start; i <= end; i++) {
      if (a[i] < a[res]) res = i;
    }
    return res;
  }
  public static int minIndex(double[] a, int start) {
    return minIndex(a, start, a.length - 1);
  }
  public static int minIndex(double[] a) {
    return minIndex(a, 0, a.length - 1);
  }

  // 絶対値
  public static int abs(int a) {
    return Math.abs(a);
  }
  public static long abs(long a) {
    return Math.abs(a);
  }
  public static double abs(double a) {
    return Math.abs(a);
  }

  // チェビシェフ距離（チェス盤距離）
  public static long chebyshevDistance(long x1, long y1, long x2, long y2) {
    return max(abs(x1 - x2), abs(y1 - y2));
  }
  public static int chebyshevDistance(int x1, int y1, int x2, int y2) {
    return max(abs(x1 - x2), abs(y1 - y2));
  }

  // マンハッタン距離
  public static long manhattanDistance(long x1, long y1, long x2, long y2) {
    return abs(x1 - x2) + abs(y1 - y2);
  }
  public static int manhattanDistance(int x1, int y1, int x2, int y2) {
    return abs(x1 - x2) + abs(y1 - y2);
  }

  // ユークリッド距離
  public static int euclideanDistance(int x1, int y1, int x2, int y2) {
    return (int) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
  }
  public static long euclideanDistance(long x1, long y1, long x2, long y2) {
    return (long) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
  }

  // 外積
  public static long cross(long ox, long oy, long ax, long ay, long bx, long by) {
    // 外積: |a||b|sinθ = axby - bxay
    return (ax - ox) * (by - oy) - (bx - ox) * (ay - oy);
  }
  public static long cross(long ax, long xy, long bx, long by) {
    // 外積: |a||b|sinθ = axby - bxay
    return cross(0, 0, ax, xy, bx, by);
  }

  // 内積
  public static long dot(long ox, long oy, long ax, long ay, long bx, long by) {
    // 内積: |a||b|cosθ = axbx + ayby
    return (ax - ox) * (bx - ox) + (ay - oy) * (by - oy);
  }
  public static long dot(long ax, long xy, long bx, long by) {
    // 内積: |a||b|cosθ = axbx + ayby
    return dot(0, 0, ax, xy, bx, by);
  }

  // swap
  public static String swap(String s, int i, int j) {
    char[] cs = s.toCharArray();
    char tmp = cs[i]; cs[i] = cs[j]; cs[j] = tmp;
    return String.valueOf(cs);
  }
  public static void swap(boolean[] a, int i, int j) {
    boolean tmp = a[i]; a[i] = a[j]; a[j] = tmp;
  }
  public static void swap(int[] a, int i, int j) {
    int tmp = a[i]; a[i] = a[j]; a[j] = tmp;
  }
  public static void swap(long[] a, int i, int j) {
    long tmp = a[i]; a[i] = a[j]; a[j] = tmp;
  }
  public static void swap(double[] a, int i, int j) {
    double tmp = a[i]; a[i] = a[j]; a[j] = tmp;
  }
  public static void swap(float[] a, int i, int j) {
    float tmp = a[i]; a[i] = a[j]; a[j] = tmp;
  }
  public static void swap(byte[] a, int i, int j) {
    byte tmp = a[i]; a[i] = a[j]; a[j] = tmp;
  }
  public static void swap(char[] a, int i, int j) {
    char tmp = a[i]; a[i] = a[j]; a[j] = tmp;
  }
  public static <T> void swap(T[] a, int i, int j) {
    T tmp = a[i]; a[i] = a[j]; a[j] = tmp;
  }
  public static <T> void swap(List<T> a, int i, int j) {
    T tmp = a.get(i); a.set(i, a.get(j)); a.set(j, tmp);
  }

  // 間にあるかどうか
  public static boolean between(int a, int x, int b) {
    if (a > b) return between(b, x, a);
    return a <= x && x <= b;
  }
  public static boolean between(long a, long x, long b) {
    if (a > b) return between(b, x, a);
    return a <= x && x <= b;
  }
  public static boolean between(double a, double x, double b) {
    if (a > b) return between(b, x, a);
    return a <= x && x <= b;
  }
  public static boolean between(float a, float x, float b) {
    if (a > b) return between(b, x, a);
    return a <= x && x <= b;
  }
  public static boolean between(byte a, byte x, byte b) {
    if (a > b) return between(b, x, a);
    return a <= x && x <= b;
  }
  public static boolean between(char a, char x, char b) {
    if (a > b) return between(b, x, a);
    return a <= x && x <= b;
  }

  // modpow (a^b mod m) 繰り返し二乗法
  public static long modpow(long a, long b, long m) {
    long res = 1;
    while (b > 0) {
      if ((b & 1) == 1) res = res * a % m;
      a = a * a % m;
      b >>= 1;
    }
    return res;
  }

  public static long modadd(long a, long b, long m) {
    return (a + b) % m;
  }

  public static long modsub(long a, long b, long m) {
    return (a - b + m) % m;
  }

  public static long modmul(long a, long b, long m) {
    return (a * b) % m;
  }

  public static long moddiv(long a, long b, long m) {
    return (a * modpow(b, m - 2, m)) % m;
  }
}

class ModInt {
  private long mod;
  private long val;

  public ModInt(long val, long mod) {
    this.mod = mod;
    this.val = val % mod;
  }

  public long toLong() {
    return val;
  }

  public String toString() {
    return String.valueOf(val);
  }

  // modpow (a^b mod m) 繰り返し二乗法
  public long pow(long x) {
    long res = 1;
    while (x > 0) {
      if ((x & 1) == 1) res = res * val % mod;
      val = val * val % mod;
      x >>= 1;
    }
    return res;
  }

  public long pow(long x, long m) {
    long res = 1;
    while (x > 0) {
      if ((x & 1) == 1) res = res * val % m;
      val = val * val % m;
      x >>= 1;
    }
    return res;
  }

  public long add(long x) {
    val += x;
    val %= mod;
    return val;
  }

  public long sub(long x) {
    val -= x;
    val += mod;
    val %= mod;
    return val;
  }

  public long mul(long x) {
    val *= x;
    val %= mod;
    return val;
  }

  public long div(long x) {
    val *= pow(x, mod - 2);
    val %= mod;
    return val;
  }
}

class MyMath {
  // 最大公約数 (Greatest Common Divisor)
  public static long gcd(long a, long b) {
    if (a < b) {
      long tmp = a; a = b; b = tmp;
    }
    while (b != 0) {
      long tmp = a % b; a = b; b = tmp;
    }
    return a;
  }
  public static int gcd(int a, int b) {
    if (a < b) {
      int tmp = a; a = b; b = tmp;
    }
    while (b != 0) {
      int tmp = a % b; a = b; b = tmp;
    }
    return a;
  }

  // 最小公倍数 (Least Common Multiple)
  public static long lcm(long a, long b) {
    return a * b / gcd(a, b);
  }
  public static int lcm(int a, int b) {
    return a * b / gcd(a, b);
  }

  // 素数列挙 (Prime enumeration)
  public static int[] primes(int max) {
    return primes_base((int)Math.sqrt(max) + 1);
  }
  public static int[] primes_base(int max) {
    boolean[] is_prime = new boolean[max];
    for (int i = 0; i < max; i++) {
      is_prime[i] = true;
    }
    for (int i = 2; i < max; i++) {
      if (is_prime[i]) {
        for (int j = i * 2; j < max; j += i) {
          is_prime[j] = false;
        }
      }
    }
    int cnt = 0;
    for (int i = 2; i < max; i++) {
      if (is_prime[i]) cnt++;
    }
    int[] primes = new int[cnt];
    int index = 0;
    for (int i = 2; i < max && index < cnt; i++) {
      if (is_prime[i]) primes[index++] = i;
    }
    return primes;
  }
  // TODO:
  public static int[] primes_base_bit(int max) {
    long[] is_prime = new long[max/64+1];
    for (int i = 0; i < max; i++) {
      is_prime[i/64] = is_prime[i/64] | (1 << (i%64)); // is_prime[i] = true
    }
    for (int i = 2; i < max; i++) {
      if ((is_prime[i/64] & (1 << (i%64))) != 0) { // is_prime[i]
        for (int j = i * 2; j < max; j += i) {
          is_prime[j/64] = is_prime[j/64] & (~(1 << (j%64))); // is_prime[j] = false
        }
      }
    }
    int cnt = 0;
    for (int i = 2; i < max; i++) {
      if ((is_prime[i/64] & (1 << (i%64))) != 0) cnt++;  // is_prime[i]
    }
    int[] primes = new int[cnt];
    int index = 0;
    for (int i = 2; i < max && index < cnt; i++) {
      if ((is_prime[i/64] & (1 << (i%64))) != 0) primes[index++] = i;  // is_prime[i]
    }
    return primes;
  }
}

class In {
  private final InputStream in;
  private final byte[] buffer = new byte[1024];
  private int ptr = 0;
  private int buflen = 0;

  public In(InputStream in) {
    this.in = in;
  }

  public In() {
    this.in = System.in;
  }

  private final boolean hasNextByte() {
    if (ptr < buflen) return true;
    ptr = 0;
    try {
      buflen = in.read(buffer);
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (buflen <= 0) return false;
    return true;
  }

  private final int readByte() {
    return hasNextByte() ? buffer[ptr++] : -1;
  }

  private static final boolean isPrintableChar(int c) {
    return 33 <= c && c <= 126;
  }

  public final boolean hasNext() {
    while (hasNextByte() && !isPrintableChar(buffer[ptr])) {
      ptr++;
    }
    return hasNextByte();
  }

  public final String s() {
    StringBuilder sb = new StringBuilder();
    int b = readByte();
    while (isPrintableChar(b)) {
      sb.appendCodePoint(b);
      b = readByte();
    }
    return sb.toString();
  }

  public final void nextThrow(int n) {
    for (int i = 0; i < n; i++) this.s();
  }

  public final void nextThrow() {
    this.nextThrow(1);
  }

  public final long l() {
    long n = 0;
    boolean minus = false;
    int b = readByte();
    if (b == '-') {
      minus = true;
      b = readByte();
    }
    assert '0' <= b && b <= '9' : "NumberFormatException";
    while (true) {
      if ('0' <= b && b <= '9') {
        n *= 10;
        n += b - '0';
      } else {
        //assert b == ' ' || b == '\n' : "NumberFormatException";
        return minus ? -n : n;
      }
      b = readByte();
    }
  }
  public long[] la(int n, int headspace, int tailspace) {
    long[] result = new long[n + headspace + tailspace];
    for (int i = headspace; i < n + headspace; i++) result[i] = l();
    return result;
  }
  public long[] la(int n, int headspace) {
    return la(n, headspace, 0);
  }
  public long[] la(int n) {
    return la(n, 0, 0);
  }

  // TODO:
  public long[][] laa(int n, int m) {
    long[][] result = new long[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) result[i][j] = l();
    }
    return result;
  }

  public final int i() {
    long nl = l();
    assert (Integer.MIN_VALUE <= nl || nl <= Integer.MAX_VALUE) : "NumberFormatException";
    return (int)nl;
  }
  public int[] ia(int n, int headspace, int tailspace) {
    int[] result = new int[n + headspace + tailspace];
    for (int i = headspace; i < n + headspace; i++) result[i] = i();
    return result;
  }
  public int[] ia(int n, int headspace) {
    return ia(n, headspace, 0);
  }
  public int[] ia(int n) {
    return ia(n, 0, 0);
  }

  // TODO:
  public int[][] iaa(int n, int m) {
    int[][] result = new int[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) result[i][j] = i();
    }
    return result;
  }
  public int[][] ias(int ncol, int n) {
    int[][] result = new int[ncol][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < ncol; j++) result[j][i] = i();
    }
    return result;
  }

  public double d() {
    return Double.parseDouble(s());
  }

  public boolean[] b(char True) {
    String s = this.s();
    int n = s.length();
    boolean[] array = new boolean[n];
    for (int i = 0; i < n; i++)
      array[i] = s.charAt(i) == True;
    return array;
  }
}

class Out {
  PrintWriter out;

  Out() {
    this.out = new PrintWriter(System.out);
  }

  public final void as() {
    out.print(' ');
  }
  public final void an() {
    out.print('\n');
  }

  public final <T> void a(T n) {
    out.print(n);
  }
  public final <T> void as(T n) {
    out.print(n);
    out.print(' ');
  }
  public final <T> void an(T n) {
    out.println(n);
  }

  public final void yesno(boolean b) {
    out.println(b ? "Yes" : "No");
  }

  public final void aas(int[] n) {
    int nm = n.length-1;
    for (int i = 0; i < nm; i++) {
      out.print(n[i]);
      out.print(' ');
    }
    out.println(n[nm]);
  }
  public final void aan(int[] n) {
    int nm = n.length-1;
    for (int i = 0; i < nm; i++) {
      out.println(n[i]);
    }
    out.println(n[nm]);
  }
  public final void aas(int[][] n) {
    int nm = n.length;
    for (int i = 0; i < nm; i++) {
      aas(n[i]);
    }
  }

  public final void aas(long[] n) {
    int nm = n.length-1;
    for (int i = 0; i < nm; i++) {
      out.print(n[i]);
      out.print(' ');
    }
    out.println(n[nm]);
  }
  public final void aan(long[] n) {
    int nm = n.length-1;
    for (int i = 0; i < nm; i++) {
      out.println(n[i]);
    }
    out.println(n[nm]);
  }
  public final void aas(long[][] n) {
    int nm = n.length;
    for (int i = 0; i < nm; i++) {
      aas(n[i]);
    }
  }

  public final void flush() {
    out.flush();
  }
  public final void flushln() {
    out.print('\n');
    out.flush();
  }
}

// Edge
class E {
  int u;
  int v;
  int w;

  public E(int u, int v, int w) {
    this.u = u;
    this.v = v;
    this.w = w;
  }

  public E(int u, int v) {
    this(u, v, 0);
  }
}

// Union-Find
class UF {
  int[] parent;
  int[] rank;
  int[] size;

  public UF(int n) {
    parent = new int[n+1];
    rank = new int[n+1];
    size = new int[n+1];
    for (int i = 1; i <= n; i++) {
      parent[i] = i;
      rank[i] = 0;
      size[i] = 1;
    }
  }

  public int find(int x) {
    assert (0 < x && x < parent.length) : "IndexOutOfBoundsException";
    if (parent[x] == x) return x;
    return parent[x] = find(parent[x]);
  }

  public void union(int x, int y) {
    assert (0 < x && x < parent.length) : "IndexOutOfBoundsException";
    assert (0 < y && y < parent.length) : "IndexOutOfBoundsException";
    x = find(x);
    y = find(y);
    if (x == y) return;
    if (rank[x] < rank[y]) {
      parent[x] = y;
      size[y] += size[x];
    } else {
      parent[y] = x;
      size[x] += size[y];
      if (rank[x] == rank[y]) rank[x]++;
    }
  }

  public boolean same(int x, int y) {
    assert (0 < x && x < parent.length) : "IndexOutOfBoundsException";
    assert (0 < y && y < parent.length) : "IndexOutOfBoundsException";
    return find(x) == find(y);
  }

  public int size(int x) {
    assert (0 < x && x < parent.length) : "IndexOutOfBoundsException";
    return size[find(x)];
  }
}

class MyComparator {
  private static Comparator<int[]> dictIntArray = null;
  static Comparator<int[]> dictIntArray() {
    if (dictIntArray == null) dictIntArray = (a, b) -> Arrays.compare(a, b);
    return dictIntArray;
  }

  private static Comparator<int[][]> dictIntArrayArray = null;
  static Comparator<int[][]> dictIntArrayArray() {
    if (dictIntArrayArray == null) dictIntArrayArray = (a, b) -> {
      for (int i = 0; i < a.length; i++) {
        int c = Arrays.compare(a[i], b[i]);
        if (c != 0) return c;
      }
      return 0;
    };
    return dictIntArrayArray;
  }
}

// 有理数クラス
class Rational implements Comparable<Rational> {
  long num;
  long den;

  public Rational(long num, long den) {
    assert den != 0 : "denominator is zero";
    if (den < 0) {
      num *= -1;
      den *= -1;
    }
    long g = Util.gcd(Math.abs(num), den);
    this.num = num / g;
    this.den = den / g;
  }
  public Rational(long num) {
    this(num, 1);
  }
  public static Rational of(long num, long den) {
    return new Rational(num, den);
  }
  public static Rational of(long num) {
    return new Rational(num);
  }
  public int compareTo(Rational r) {
    return Long.compare(num * r.den, den * r.num);
  }
  // toString
  public String toString() {
    return num + "/" + den;
  }
  // double
  public double doubleValue() {
    return (double) num / den;
  }
  // float
  public float floatValue() {
    return (float) num / den;
  }
  // long
  public long longValue() {
    return num / den;
  }
  // int
  public int intValue() {
    return (int) (num / den);
  }
  // 約分
  public Rational reduce() {
    long g = Util.gcd(Math.abs(num), den);
    return new Rational(num / g, den / g);
  }
  // 四則演算
  public Rational add(Rational r) {
    return new Rational(num * r.den + den * r.num, den * r.den);
  }
  public Rational sub(Rational r) {
    return new Rational(num * r.den - den * r.num, den * r.den);
  }
  public Rational mul(Rational r) {
    return new Rational(num * r.num, den * r.den);
  }
  public Rational div(Rational r) {
    return new Rational(num * r.den, den * r.num);
  }
  // 逆数
  public Rational inv() {
    return new Rational(den, num);
  }
  // 符号反転
  public Rational neg() {
    return new Rational(-num, den);
  }
  // 絶対値
  public Rational abs() {
    return new Rational(Math.abs(num), den);
  }
  // 累乗
  public Rational pow(long n) {
    return new Rational((long)Math.pow(num, n), (long)Math.pow(den, n));
  }
  public Rational pow(Rational r) {
    return new Rational((long)Math.pow(num, r.num), (long)Math.pow(den, r.num));
  }
  // 平方根
  public Rational sqrt() {
    return new Rational((long)Math.sqrt(num), (long)Math.sqrt(den));
  }
  // 立方根
  public Rational cbrt() {
    return new Rational((long)Math.cbrt(num), (long)Math.cbrt(den));
  }
  // 指数関数
  public Rational exp() {
    return new Rational((long)Math.exp(num), (long)Math.exp(den));
  }
  // 対数関数
  public Rational log() {
    return new Rational((long)Math.log(num), (long)Math.log(den));
  }
  // 三角関数
  public Rational sin() {
    return new Rational((long)Math.sin(num), (long)Math.sin(den));
  }
  public Rational cos() {
    return new Rational((long)Math.cos(num), (long)Math.cos(den));
  }
  public Rational tan() {
    return new Rational((long)Math.tan(num), (long)Math.tan(den));
  }
}
