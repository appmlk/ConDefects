import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;

class Main {
  public static void solve(Read br, Write out) {
    long x = br.readL();
    long min = 1000000000000000000L;
    if (x < 10) {
      out.pl(x);
      return;
    }
    int k = 0;
    long t = 1;
    while (t <= x) {
      t *= 10;
      k++;
    }
    for (int i = 1; i <= 9; i++) {
      for (int d = -9; d <= 9; d++) {
        long a = 0;
        for (int w = 0; w < k; w++) {
          int q = i + d * w;
          if (q < 0 || q > 9) {
            a = x - 1;
            break;
          }
          a = a * 10 + q;
        }
        if (a >= x && a < min) {
          min = a;
        }
      }
    }
    out.pl(min);
  }

  public static boolean func(int a[], int b[]) {
    for (int i = 0; i < a.length; i++) {
      if (a[i] != b[i]) {
        return false;
      }
    }
    return true;
  }

  static class PermuT {
    int n;
    public int a[];
    boolean flg;

    public PermuT(int n) {
      this.n = n;
      flg = true;
      a = new int[n];
      for (int i = 0; i < n; i++) {
        a[i] = i + 1;
      }
    }

    public boolean next() {
      for (int i = n - 2; i >= 0; i--) {
        if (a[i] >= a[i + 1]) {
          continue;
        }
        for (int j = n - 1;; j--) {
          if (a[i] >= a[j]) {
            continue;
          }
          int temp = a[i];
          a[i] = a[j];
          a[j] = temp;
          i++;
          for (j = n - 1; i < j; i++, j--) {
            temp = a[i];
            a[i] = a[j];
            a[j] = temp;
          }
          return true;
        }
      }
      flg = false;
      return false;
    }
  }

  public static void main(String args[]) {
    Read br = new Read();
    Write out = new Write();
    solve(br, out);
    out.flush();
  }

  static class Tree {
    int n;
    int root;
    int parent[];
    int height[];
    int maxh;
    ArrayList<ArrayList<Integer>> child;
    int lca[][];

    public Tree(int nn, int r) {
      n = nn;
      root = r;
      parent = new int[n];
      height = new int[n];
      child = new ArrayList<>();
      for (int i = 0; i < n; i++) {
        child.add(new ArrayList<>());
      }
    }

    public Tree(int par[], int r) {
      n = par.length;
      root = r;
      parent = new int[n];
      height = new int[n];
      child = new ArrayList<>();
      for (int i = 0; i < n; i++) {
        child.add(new ArrayList<>());
      }
      for (int i = 0; i < n; i++) {
        parent[i] = par[i];
        if (parent[i] != -1) {
          child.get(parent[i]).add(i);
        }
      }
      ArrayDeque<Integer> q = new ArrayDeque<>();
      q.add(root);
      while (q.size() > 0) {
        int v = q.poll();
        for (int u : child.get(v)) {
          height[u] = height[v] + 1;
          if (height[u] > maxh) {
            maxh = height[u];
          }
          q.add(u);
        }
      }
    }

    public Tree(Graph g, int r) {
      this(g.n, r);
      construct(g);
    }

    public void construct(Graph g) {
      constructdfs(g, root, -1, 0, new boolean[n]);
    }

    public void constructdfs(Graph g, int now, int before, int h, boolean flg[]) {
      flg[now] = true;
      parent[now] = before;
      height[now] = h;
      if (h > maxh) {
        maxh = h;
      }
      for (Graph.Edge e : g.g.get(now)) {
        if (e.v != before) {
          constructdfs(g, e.v, now, h + 1, flg);
          child.get(now).add(e.v);
        }
      }
    }

    public void const_lowest_common_ancestor() {
      int k = 1;
      while ((1 << k) <= maxh) {
        k++;
      }
      lca = new int[k][n];
      for (int i = 0; i < n; i++) {
        lca[0][i] = parent[i];
        if (lca[0][i] == -1) {
          lca[0][i] = i;
        }
      }
      for (int i = 1; i < k; i++) {
        for (int v = 0; v < n; v++) {
          lca[i][v] = lca[i - 1][lca[i - 1][v]];
        }
      }
    }

    public int get_anscestor(int u, int d) {
      int k = lca.length;
      for (int i = 0; i < k; i++) {
        if ((d >> i) % 2 > 0) {
          u = lca[i][u];
        }
      }
      return u;
    }

    public int query_lowest_common_ancestor(int u, int v) {
      if (height[u] < height[v]) {
        return query_lowest_common_ancestor(v, u);
      }
      int k = lca.length;
      u = get_anscestor(u, height[u] - height[v]);
      if (u == v) {
        return u;
      }
      for (int i = k - 1; i >= 0; i--) {
        if (lca[i][u] != lca[i][v]) {
          u = lca[i][u];
          v = lca[i][v];
        }
      }
      return lca[0][u];
    }
  }

  public static int comp(long a, long b) {
    return a > b ? 1 : a < b ? -1 : 0;
  }

  public static long fact(int i) {
    if (i == 1 || i == 0) {
      return 1L;
    }
    return i * fact(i - 1);
  }

  public static long gcd(long a, long b) {
    if (a < b) {
      return gcd(b, a);
    }
    long c = a % b;
    while (c != 0) {
      a = b;
      b = c;
      c = a % b;
    }
    return b;
  }

  public static long lcm(long a, long b) {
    return a / gcd(a, b) * b;
  }

  public static int gcd(int a, int b) {
    if (a < b) {
      return gcd(b, a);
    }
    int c = a % b;
    while (c != 0) {
      a = b;
      b = c;
      c = a % b;
    }
    return b;
  }

  public static int lcm(int a, int b) {
    return a / gcd(a, b) * b;
  }

  public static void sortA(int n[]) {
    Arrays.sort(n);
  }

  public static void sortD(int n[]) {
    Arrays.sort(n);
    rev(n);
  }

  public static void rev(int n[]) {
    int l = n.length;
    for (int i = 0; i < l / 2; i++) {
      int t = n[i];
      n[i] = n[l - i - 1];
      n[l - i - 1] = t;
    }
  }

  public static void sortA(ArrayList<Integer> n) {
    Collections.sort(n);
  }

  public static void sortD(ArrayList<Integer> n) {
    Collections.sort(n, Collections.reverseOrder());
  }

  public static void sortA(int n[][], int k) {
    Arrays.sort(n, (a, b) -> Integer.compare(a[k], b[k]));
  }

  public static void sortD(int n[][], int k) {
    Arrays.sort(n, (a, b) -> Integer.compare(b[k], a[k]));
  }

  public static void mysort(int n[][], int k) {
    Arrays.sort(n, new Comparator<int[]>() {
      public int compare(int[] x, int[] y) {
        if (x[k] == y[k]) {
          return x[1] - y[1];
        }
        return x[k] - y[k];
      }
    });
  }

  static class ModFunc {
    int n;
    long mod;
    long fact[], invfact[];

    public ModFunc(int n, long mod) {
      this.n = n;
      this.mod = mod;
      fact = new long[n + 1];
      invfact = new long[n + 1];
      modfact();
      modinvfact();
    }

    public static long modfact(int n, long mod) {
      long k = 1;
      for (int i = 1; i <= n; i++) {
        k = k * i % mod;
      }
      return k;
    }

    public static long modinvfact(int n, long mod) {
      return modinv(modfact(n, mod), mod);
    }

    public static long modinv(long a, long mod) {
      long x1 = 1, x2 = 0;
      long p = a, q = mod, t;
      while (q != 0) {
        t = p / q;
        t = x1 - t * x2;
        x1 = x2;
        x2 = t;
        t = p % q;
        p = q;
        q = t;
      }
      return x1 < 0 ? x1 + mod : x1;
    }

    private void modfact() {
      fact[0] = 1;
      for (int i = 1; i <= n; i++) {
        fact[i] = fact[i - 1] * i % mod;
      }
    }

    private void modinvfact() {
      invfact[n] = modinv(fact[n], mod);
      for (int i = n - 1; i >= 0; i--) {
        invfact[i] = invfact[i + 1] * (i + 1) % mod;
      }
    }

    public long modConv(int n, int k) {
      return ((fact[n] * invfact[n - k]) % mod) * invfact[k] % mod;
    }

    public static long modpow(long x, long n, long pow) {
      long r = 1;
      while (n >= 1) {
        if (1 == (n & 1)) {
          r = r * x % pow;
        }
        x = x * x % pow;
        n /= 2;
      }
      return r;
    }
  }

  static class Permu {
    int n;
    public int a[];
    boolean flg;

    public Permu(int n) {
      this.n = n;
      flg = true;
      a = new int[n];
      for (int i = 0; i < n; i++) {
        a[i] = i;
      }
    }

    public Permu(int k[]) {
      this.n = k.length;
      flg = true;
      a = new int[n];
      for (int i = 0; i < n; i++) {
        a[i] = k[i];
      }
    }

    public boolean next() {
      for (int i = n - 2; i >= 0; i--) {
        if (a[i] >= a[i + 1]) {
          continue;
        }
        for (int j = n - 1;; j--) {
          if (a[i] >= a[j]) {
            continue;
          }
          int temp = a[i];
          a[i] = a[j];
          a[j] = temp;
          i++;
          for (j = n - 1; i < j; i++, j--) {
            temp = a[i];
            a[i] = a[j];
            a[j] = temp;
          }
          return true;
        }
      }
      flg = false;
      return false;
    }

    public boolean before() {
      for (int i = n - 2; i >= 0; i--) {
        if (a[i] <= a[i + 1]) {
          continue;
        }
        for (int j = n - 1;; j--) {
          if (a[i] <= a[j]) {
            continue;
          }
          int temp = a[i];
          a[i] = a[j];
          a[j] = temp;
          i++;
          for (j = n - 1; i < j; i++, j--) {
            temp = a[i];
            a[i] = a[j];
            a[j] = temp;
          }
          return true;
        }
      }
      flg = false;
      return false;
    }
  }

  static class Compress {
    ArrayList<Integer> a;
    HashMap<Integer, Integer> map;

    public Compress(int[] b) {
      HashSet<Integer> temp = new HashSet<>();
      for (int i : b) {
        temp.add(i);
      }
      a = new ArrayList<Integer>(temp);
      setup();
    }

    public Compress(ArrayList<Integer> b) {
      a = new ArrayList<Integer>(new HashSet<Integer>(b));
      setup();
    }

    private void setup() {
      map = new HashMap<>();
      sortA(a);
      for (int i = 0; i < a.size(); i++) {
        map.put(a.get(i), i);
      }
    }

    public int get(int i) {
      return map.get(i);
    }
  }

  static class UnionFindTree {
    int parent[];
    int size;
    int vol[];

    public UnionFindTree(int s) {
      size = s;
      parent = new int[size];
      vol = new int[size];
      for (int i = 0; i < size; i++) {
        parent[i] = i;
        vol[i] = 1;
      }
    }

    public int root(int n) {
      if (parent[n] == n) {
        return n;
      }
      parent[n] = root(parent[n]);
      return parent[n];
    }

    public void unite(int a, int b) {
      int ra = root(a), rb = root(b);
      if (ra == rb) {
        return;
      }
      if (vol[ra] < vol[rb]) {
        a = ra;
        ra = rb;
        rb = a;
      }
      parent[rb] = ra;
      vol[ra] += vol[rb];
    }

    public boolean same(int a, int b) {
      return root(a) == root(b);
    }
  }

  static class WeightUnionFindTree {
    int parent[];
    int size;
    int vol[];
    int diff[];

    public WeightUnionFindTree(int s) {
      size = s;
      parent = new int[size];
      vol = new int[size];
      diff = new int[size];
      for (int i = 0; i < size; i++) {
        parent[i] = i;
        vol[i] = 1;
        diff[i] = 0;
      }
    }

    public int root(int n) {
      if (parent[n] == n) {
        return n;
      }
      int r = root(parent[n]);
      diff[n] += diff[parent[n]];
      parent[n] = r;
      return parent[n];
    }

    public int weight(int n) {
      root(n);
      return diff[n];
    }

    public void unite(int a, int b, int w) {
      int ra = root(a), rb = root(b);
      if (ra == rb) {
        return;
      }
      w += weight(a);
      w -= weight(b);
      if (vol[ra] < vol[rb]) {
        a = ra;
        ra = rb;
        rb = a;
        w = -w;
      }
      parent[rb] = ra;
      vol[ra] += vol[rb];
      diff[rb] = w;
    }

    public int differ(int a, int b) {
      return weight(a) - weight(b);
    }

    public boolean same(int a, int b) {
      return root(a) == root(b);
    }
  }

  static abstract class SegmentTree {
    int n, leafN;
    int ar[];
    int lazy[];
    int temp;

    public SegmentTree(int x, int temp) {
      leafN = x;
      this.temp = temp;
      n = twon(leafN);
      ar = new int[2 * n - 1];
      lazy = new int[2 * n - 1];
      for (int i = 0; i < n * 2 - 1; i++) {
        ar[i] = temp;
        lazy[i] = temp;
      }
    }

    public abstract int func(int a, int b);

    public void eval(int k) {
      if (lazy[k] == temp) {
        return;
      }
      if (k < n - 1) {
        lazy[k * 2 + 1] = lazy[k];
        lazy[k * 2 + 2] = lazy[k];
      }
      ar[k] = lazy[k];
      lazy[k] = temp;
    }

    public void update(int i, int x) {
      int now = i + n - 1;
      ar[now] = x;
      while (now > 0) {
        now = (now - 1) / 2;
        ar[now] = func(ar[now * 2 + 1], ar[now * 2 + 2]);
      }
    }

    public void add(int i, int x) {
      update(i, ar[i + n - 1] + x);
    }

    public void update(int a, int b, int x) {
      update(a, b, x, 0, 0, n);
    }

    public void update(int a, int b, int x, int k, int l, int r) {
      eval(k);
      if (r <= a || b <= l) {
        return;
      } else if (a <= l && r <= b) {
        lazy[k] = x;
        eval(k);
      }
      update(a, b, x, k * 2 + 1, l, (l + r) / 2);
      update(a, b, x, k * 2 + 2, (l + r) / 2, r);
      ar[k] = func(ar[k * 2 + 1], ar[k * 2 + 2]);
    }

    public int get(int i) {
      return ar[i + n - 1];
    }

    public int query(int a, int b) {
      return query(a, b, 0, 0, n);
    }

    public int query(int a, int b, int k, int l, int r) {
      eval(k);
      if (r <= a || b <= l) {
        return temp;
      } else if (a <= l && r <= b) {
        return ar[k];
      }
      int t1 = query(a, b, k * 2 + 1, l, (l + r) / 2), t2 = query(a, b, k * 2 + 2, (l + r) / 2, r);
      return func(t1, t2);
    }

    private int twon(int x) {
      int i = 1;
      while (i < x) {
        i *= 2;
      }
      return i;
    }
  }

  static class SegmentTreeE extends SegmentTree {
    public SegmentTreeE(int x) {
      super(x, 0);
    }

    public int func(int a, int b) {
      return a ^ b;
    }
  }

  static class SegmentTreeMin extends SegmentTree {
    public SegmentTreeMin(int x) {
      super(x, Integer.MAX_VALUE);
    }

    public int func(int a, int b) {
      return Math.min(a, b);
    }
  }

  static class SegmentTreeS extends SegmentTree {
    public SegmentTreeS(int x) {
      super(x, 0);
    }

    public int func(int a, int b) {
      return a + b;
    }
  }

  static class BIT {
    int n;
    int ar[];

    public BIT(int x) {
      n = x + 1;
      ar = new int[n];
      for (int i = 0; i < n; i++) {
        ar[i] = 0;
      }
    }

    public void update(int i, int x) {
      i++;
      for (int ii = i; ii < n; ii += (ii & -ii)) {
        ar[ii] += x;
      }
    }

    public int sum(int i) {
      int k = 0;
      for (int ii = i; ii > 0; ii -= (ii & -ii)) {
        k += ar[ii];
      }
      return k;
    }
  }

  static class Graph {
    int n;
    ArrayList<ArrayList<Edge>> g;

    public Graph(int nn) {
      n = nn;
      g = new ArrayList<ArrayList<Edge>>();
      for (int i = 0; i < n; i++) {
        g.add(new ArrayList<Edge>());
      }
    }

    public void add(int a, int b, int d) {
      g.get(a).add(new Edge(b, d));
      g.get(b).add(new Edge(a, d));
    }

    public void addY(int a, int b, int d) {
      g.get(a).add(new Edge(b, d));
    }

    public void add(int a, int b) {
      g.get(a).add(new Edge(b, 1));
      g.get(b).add(new Edge(a, 1));
    }

    public void addY(int a, int b) {
      g.get(a).add(new Edge(b, 1));
    }

    public int len(int a) {
      return g.get(a).size();
    }

    public long[][] dijkstra(int s) {
      long dist[][] = new long[n][2];
      for (int i = 0; i < n; i++) {
        dist[i][0] = Long.MAX_VALUE;
        dist[i][1] = -1;
      }
      dist[s][0] = 0;
      dist[s][1] = -1;
      PriorityQueue<long[]> q = new PriorityQueue<long[]>(new Comparator<long[]>() {
        public int compare(long[] x, long[] y) {
          return Long.compare(x[1], y[1]);
        }
      });
      q.add(new long[] { s, 0L });
      while (q.size() > 0) {
        long[] p = q.poll();
        if (dist[(int) p[0]][0] != p[1]) {
          continue;
        }
        for (Edge e : g.get((int) p[0])) {
          if (dist[e.v][0] > dist[(int) p[0]][0] + e.d) {
            dist[e.v][0] = dist[(int) p[0]][0] + e.d;
            dist[e.v][1] = (int) p[0];
            q.add(new long[] { e.v, dist[e.v][0] });
          }
        }
      }
      return dist;
    }

    public static long[][] floydwarshall(int g[][]) {
      int n = g.length;
      long dist[][] = new long[n][n];
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          dist[i][j] = g[i][j];
          if (dist[i][j] == 0) {
            dist[i][j] = Long.MAX_VALUE;
          }
        }
      }
      for (int k = 0; k < n; k++) {
        for (int i = 0; i < n; i++) {
          for (int j = 0; j < n; j++) {
            if (dist[i][k] == Long.MAX_VALUE || dist[k][j] == Long.MAX_VALUE) {
              continue;
            }
            dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
          }
        }
      }
      return dist;
    }

    public void dfs(int v) {
      ArrayDeque<Integer> q = new ArrayDeque<>();
      boolean flg[] = new boolean[n];
      q.addLast(v);
      flg[v] = true;
      while (q.size() > 0) {
        int now = q.pollLast();
        for (Edge e : g.get(now)) {
          if (!flg[e.v]) {
            flg[e.v] = true;
            q.addLast(e.v);
          }
        }
      }
    }

    public void bfs(int s) {
      boolean flg[] = new boolean[n];
      Queue<Integer> q = new ArrayDeque<Integer>();
      q.add(s);
      while (q.size() > 0) {
        int v = q.poll();
        flg[v] = true;
        for (Edge e : g.get(v)) {
          if (!flg[e.v]) {
            q.add(e.v);
            flg[e.v] = true;
          }
        }
      }
    }

    public int minspantree() {
      PriorityQueue<Edge> q = new PriorityQueue<Edge>(new Comparator<Edge>() {
        public int compare(Edge a, Edge b) {
          return a.d - b.d;
        }
      });
      boolean flg[] = new boolean[n];
      int c = 1, sum = 0;
      for (Edge e : g.get(0)) {
        q.add(e);
      }
      flg[0] = true;
      while (c < n) {
        Edge e = q.poll();
        if (flg[e.v]) {
          continue;
        }
        flg[e.v] = true;
        sum += e.d;
        c++;
        for (Edge i : g.get(e.v)) {
          if (!flg[i.v]) {
            q.add(i);
          }
        }
      }
      return sum;
    }

    public int[] scc1_1() {
      Graph g2 = new Graph(n);
      for (int i = 0; i < n; i++) {
        for (Edge e : g.get(i)) {
          g2.addY(e.v, i);
        }
      }
      ArrayList<Integer> list = new ArrayList<Integer>();
      boolean flg[] = new boolean[n];
      boolean end[] = new boolean[n];
      ArrayDeque<Integer> q = new ArrayDeque<>();
      for (int i = 0; i < n; i++) {
        if (flg[i]) {
          continue;
        }
        q.addLast(-i - 1);
        q.addLast(i + 1);
        while (q.size() > 0) {
          int v = q.pollLast();
          if (v < 0) {
            v = -v - 1;
            if (end[v]) {
              continue;
            }
            end[v] = true;
            list.add(v);
            continue;
          }
          v--;
          if (flg[v]) {
            continue;
          }
          flg[v] = true;
          for (Edge e : g.get(v)) {
            if (flg[e.v]) {
              continue;
            }
            q.addLast(-e.v - 1);
            q.addLast(e.v + 1);
          }
        }
      }
      return g2.scc1_2(list);
    }

    public int[] scc1_2(ArrayList<Integer> list) {
      boolean flg[] = new boolean[n];
      int ren[] = new int[n];
      ArrayDeque<Integer> q = new ArrayDeque<>();
      int num = 0;
      for (int i = n - 1; i >= 0; i--) {
        int now = list.get(i);
        if (flg[now]) {
          continue;
        }
        q.add(now);
        flg[now] = true;
        while (q.size() > 0) {
          int v = q.poll();
          ren[v] = num;
          for (Edge e : g.get(v)) {
            if (flg[e.v]) {
              continue;
            }
            flg[e.v] = true;
            q.add(e.v);
          }
        }
        num++;
      }
      return ren;
    }

    public int[] scc() {
      Graph g2 = new Graph(n);
      for (int i = 0; i < n; i++) {
        for (Edge e : g.get(i)) {
          g2.addY(e.v, i);
        }
      }
      ArrayList<Integer> list = new ArrayList<Integer>();
      boolean flg[] = new boolean[n];
      for (int i = 0; i < n; i++) {
        if (!flg[i]) {
          dfs_scc(i, flg, list);
        }
      }
      return g2.scc2(list);
    }

    public int[] scc2(ArrayList<Integer> list) {
      boolean flg[] = new boolean[n];
      int ren[] = new int[n];
      int num = 0;
      for (int i = n - 1; i >= 0; i--) {
        int now = list.get(i);
        if (!flg[now]) {
          dfs_scc2(now, flg, ren, num);
          num++;
        }
      }
      return ren;
    }

    private void dfs_scc(int v, boolean flg[], ArrayList<Integer> list) {
      flg[v] = true;
      for (Edge e : g.get(v)) {
        if (!flg[e.v]) {
          dfs_scc(e.v, flg, list);
        }
      }
      list.add(v);
    }

    private void dfs_scc2(int v, boolean flg[], int num[], int now) {
      flg[v] = true;
      num[v] = now;
      for (Edge e : g.get(v)) {
        if (!flg[e.v]) {
          dfs_scc2(e.v, flg, num, now);
        }
      }
    }

    public ArrayList<ArrayList<Integer>> two_Edge_connected() {
      int ord[] = new int[n], low[] = new int[n];
      boolean flg[] = new boolean[n];
      for (int i = 0; i < n; i++) {
        if (!flg[i]) {
          tecDfs(i, flg, ord, low, -1);
        }
      }
      flg = new boolean[n];
      ArrayList<ArrayList<Integer>> list = new ArrayList<>();
      int now = 0;
      for (int i = 0; i < n; i++) {
        if (!flg[i]) {
          list.add(new ArrayList<>());
          tecDfs2(i, flg, ord, low, list.get(now));
          now++;
        }
      }
      return list;
    }

    public void tecDfs(int v, boolean flg[], int ord[], int low[], int before) {
      flg[v] = true;
      if (before != -1) {
        ord[v] = ord[before] + 1;
      } else {
        ord[v] = 0;
      }
      low[v] = ord[v];
      for (Edge e : g.get(v)) {
        if (e.v == before) {
          before = -1;
          continue;
        }
        if (flg[e.v]) {
          low[v] = Math.min(low[v], ord[e.v]);
        } else {
          tecDfs(e.v, flg, ord, low, v);
          low[v] = Math.min(low[v], low[e.v]);
        }
      }
    }

    public void tecDfs2(int v, boolean flg[], int ord[], int low[], ArrayList<Integer> list) {
      flg[v] = true;
      list.add(v);
      for (Edge e : g.get(v)) {
        if (flg[e.v]) {
          continue;
        }
        if (ord[v] < low[e.v] || ord[e.v] < low[v]) {
          continue;
        }
        tecDfs2(e.v, flg, ord, low, list);
      }
    }

    static class Edge {
      int v, d;

      public Edge(int v, int d) {
        this.v = v;
        this.d = d;
      }
    }
  }

  static class Read {
    BufferedReader br;

    public Read() {
      br = new BufferedReader(new InputStreamReader(System.in));
    }

    private boolean canprint(int a) {
      return 33 <= a && a <= 126;
    }

    private int skipread() {
      int a = readC();
      while (a != -1 && !canprint(a)) {
        a = readC();
      }
      return a;
    }

    public char readC() {
      try {
        return (char) br.read();
      } catch (IOException e) {
        e.printStackTrace();
        return (char) -1;
      }
    }

    public String readLine() {
      try {
        return br.readLine();
      } catch (IOException e) {
        e.printStackTrace();
        return "";
      }
    }

    public String readS() {
      StringBuilder sb = new StringBuilder();
      int k = skipread();
      while (true) {
        if (!canprint(k)) {
          break;
        }
        sb.append((char) k);
        k = readC();
      }
      return sb.toString();
    }

    public int readI() {
      int r = 0;
      int k = skipread();
      int flg = 1;
      if (k == '-') {
        flg = -1;
        k = readC();
      }
      while (true) {
        if (!canprint(k)) {
          break;
        }
        r = r * 10 + (k - '0');
        k = readC();
      }
      return flg * r;
    }

    public long readL() {
      long r = 0;
      int k = skipread();
      int flg = 1;
      if (k == '-') {
        flg = -1;
        k = readC();
      }
      while (true) {
        if (!canprint(k)) {
          break;
        }
        r = r * 10 + (k - '0');
        k = readC();
      }
      return flg * r;
    }

    public String[] readSs(int n) {
      String[] a = new String[n];
      for (int i = 0; i < n; i++) {
        a[i] = readS();
      }
      return a;
    }

    public int[] readIs(int n) {
      int[] a = new int[n];
      for (int i = 0; i < n; i++) {
        a[i] = readI();
      }
      return a;
    }

    public long[] readLs(int n) {
      long[] a = new long[n];
      for (int i = 0; i < n; i++) {
        a[i] = readL();
      }
      return a;
    }
  }

  static class Write {
    PrintWriter out;
    boolean debug;

    public Write() {
      out = new PrintWriter(System.out);
      debug = false;
    }

    public <T> void pr(T str) {
      out.print(str);
    }

    public <T> void pl(T str) {
      out.println(str);
    }

    public void pr(int[] a) {
      int t = a.length;
      if (t > 0) {
        pr(a[0]);
      }
      for (int i = 1; i < t; i++) {
        pr(" " + a[i]);
      }
      pl("");
    }

    public void pr(int[][] a) {
      for (int i[] : a) {
        pr(i);
      }
    }

    public void pr(long[] a) {
      int t = a.length;
      if (t > 0) {
        pr(a[0]);
      }
      for (int i = 1; i < t; i++) {
        pr(" " + a[i]);
      }
      pl("");
    }

    public void pr(long[][] a) {
      for (long i[] : a) {
        pr(i);
      }
    }

    public void pr(String[] a) {
      int t = a.length;
      if (t > 0) {
        pr(a[0]);
      }
      for (int i = 1; i < t; i++) {
        pr(" " + a[i]);
      }
      pl("");
    }

    public void pr(String[][] a) {
      for (String i[] : a) {
        pr(i);
      }
    }

    public void yes() {
      pl("Yes");
    }

    public void no() {
      pl("No");
    }

    public void yn(boolean flg) {
      if (flg) {
        yes();
      } else {
        no();
      }
    }

    public void flush() {
      out.flush();
    }

    public static <T> void prA(T str) {
      System.out.print(str);
    }

    public static <T> void plA(T str) {
      System.out.println(str);
    }

    public static void prA(int[] a) {
      int t = a.length;
      if (t > 0) {
        prA(a[0]);
      }
      for (int i = 1; i < t; i++) {
        prA(" " + a[i]);
      }
      plA("");
    }

    public static void prA(int[][] a) {
      for (int i[] : a) {
        prA(i);
      }
    }

    public static void prA(long[] a) {
      int t = a.length;
      if (t > 0) {
        prA(a[0]);
      }
      for (int i = 1; i < t; i++) {
        prA(" " + a[i]);
      }
      plA("");
    }

    public static void prA(long[][] a) {
      for (long i[] : a) {
        prA(i);
      }
    }

    public static void prA(String[] a) {
      int t = a.length;
      if (t > 0) {
        prA(a[0]);
      }
      for (int i = 1; i < t; i++) {
        prA(" " + a[i]);
      }
      plA("");
    }

    public static void prA(String[][] a) {
      for (String i[] : a) {
        prA(i);
      }
    }

    public <T> void debugP(T str) {
      if (debug) {
        pl(str);
      }
    }
  }

  public static long stol(String s) {
    return Long.parseLong(s);
  }

  public static int stoi(String s) {
    return Integer.parseInt(s);
  }

  public static int[] stoi(String s[]) {
    int a[] = new int[s.length];
    for (int i = 0; i < s.length; i++) {
      a[i] = stoi(s[i]);
    }
    return a;
  }

  public static String itos(int i) {
    return String.valueOf(i);
  }

  public static String[] itos(int[] a) {
    String s[] = new String[a.length];
    for (int i = 0; i < a.length; i++) {
      s[i] = itos(a[i]);
    }
    return s;
  }

  public static String ctos(char c) {
    return String.valueOf(c);
  }

  public static String cstos(char[] c) {
    return new String(c);
  }

  public static char stoc(String s) {
    return s.charAt(0);
  }

  public static char stoc(String s, int i) {
    return s.charAt(i);
  }

  public static char[] stocs(String s) {
    return s.toCharArray();
  }
}
