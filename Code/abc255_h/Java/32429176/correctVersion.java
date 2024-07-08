import java.util.*;

@SuppressWarnings("unused")
public class Main {

  static class LazySegTree<S, F> {
    final int MAX;

    final int N;
    final int Log;
    final java.util.function.BinaryOperator<S> Op;
    final S E;
    final java.util.function.BiFunction<F, S, S> Mapping;
    final java.util.function.BinaryOperator<F> Composition;
    final F Id;

    final S[] Dat;
    final F[] Laz;

    @SuppressWarnings("unchecked")
    public LazySegTree(int n, java.util.function.BinaryOperator<S> op, S e,
        java.util.function.BiFunction<F, S, S> mapping, java.util.function.BinaryOperator<F> composition, F id) {
      this.MAX = n;
      int k = 1;
      while (k < n)
        k <<= 1;
      this.N = k;
      this.Log = Integer.numberOfTrailingZeros(N);
      this.Op = op;
      this.E = e;
      this.Mapping = mapping;
      this.Composition = composition;
      this.Id = id;
      this.Dat = (S[]) new Object[N << 1];
      this.Laz = (F[]) new Object[N];
      java.util.Arrays.fill(Dat, E);
      java.util.Arrays.fill(Laz, Id);
    }

    public LazySegTree(S[] dat, java.util.function.BinaryOperator<S> op, S e,
        java.util.function.BiFunction<F, S, S> mapping, java.util.function.BinaryOperator<F> composition, F id) {
      this(dat.length, op, e, mapping, composition, id);
      build(dat);
    }

    private void build(S[] dat) {
      int l = dat.length;
      System.arraycopy(dat, 0, Dat, N, l);
      for (int i = N - 1; i > 0; i--) {
        Dat[i] = Op.apply(Dat[i << 1 | 0], Dat[i << 1 | 1]);
      }
    }

    private void push(int k) {
      if (Laz[k] == Id)
        return;
      int lk = k << 1 | 0, rk = k << 1 | 1;
      Dat[lk] = Mapping.apply(Laz[k], Dat[lk]);
      Dat[rk] = Mapping.apply(Laz[k], Dat[rk]);
      if (lk < N)
        Laz[lk] = Composition.apply(Laz[k], Laz[lk]);
      if (rk < N)
        Laz[rk] = Composition.apply(Laz[k], Laz[rk]);
      Laz[k] = Id;
    }

    private void pushTo(int k) {
      for (int i = Log; i > 0; i--)
        push(k >> i);
    }

    private void pushTo(int lk, int rk) {
      for (int i = Log; i > 0; i--) {
        if (((lk >> i) << i) != lk)
          push(lk >> i);
        if (((rk >> i) << i) != rk)
          push(rk >> i);
      }
    }

    private void updateFrom(int k) {
      k >>= 1;
      while (k > 0) {
        Dat[k] = Op.apply(Dat[k << 1 | 0], Dat[k << 1 | 1]);
        k >>= 1;
      }
    }

    private void updateFrom(int lk, int rk) {
      for (int i = 1; i <= Log; i++) {
        if (((lk >> i) << i) != lk) {
          int lki = lk >> i;
          Dat[lki] = Op.apply(Dat[lki << 1 | 0], Dat[lki << 1 | 1]);
        }
        if (((rk >> i) << i) != rk) {
          int rki = (rk - 1) >> i;
          Dat[rki] = Op.apply(Dat[rki << 1 | 0], Dat[rki << 1 | 1]);
        }
      }
    }

    public void set(int p, S x) {
      exclusiveRangeCheck(p);
      p += N;
      pushTo(p);
      Dat[p] = x;
      updateFrom(p);
    }

    public S get(int p) {
      exclusiveRangeCheck(p);
      p += N;
      pushTo(p);
      return Dat[p];
    }

    public S prod(int l, int r) {
      if (l > r) {
        throw new IllegalArgumentException(
            String.format("Invalid range: [%d, %d)", l, r));
      }
      inclusiveRangeCheck(l);
      inclusiveRangeCheck(r);
      if (l == r)
        return E;
      l += N;
      r += N;
      pushTo(l, r);
      S sumLeft = E, sumRight = E;
      while (l < r) {
        if ((l & 1) == 1)
          sumLeft = Op.apply(sumLeft, Dat[l++]);
        if ((r & 1) == 1)
          sumRight = Op.apply(Dat[--r], sumRight);
        l >>= 1;
        r >>= 1;
      }
      return Op.apply(sumLeft, sumRight);
    }

    public S allProd() {
      return Dat[1];
    }

    public void apply(int p, F f) {
      exclusiveRangeCheck(p);
      p += N;
      pushTo(p);
      Dat[p] = Mapping.apply(f, Dat[p]);
      updateFrom(p);
    }

    public void apply(int l, int r, F f) {
      if (l > r) {
        throw new IllegalArgumentException(
            String.format("Invalid range: [%d, %d)", l, r));
      }
      inclusiveRangeCheck(l);
      inclusiveRangeCheck(r);
      if (l == r)
        return;
      l += N;
      r += N;
      pushTo(l, r);
      for (int l2 = l, r2 = r; l2 < r2;) {
        if ((l2 & 1) == 1) {
          Dat[l2] = Mapping.apply(f, Dat[l2]);
          if (l2 < N)
            Laz[l2] = Composition.apply(f, Laz[l2]);
          l2++;
        }
        if ((r2 & 1) == 1) {
          r2--;
          Dat[r2] = Mapping.apply(f, Dat[r2]);
          if (r2 < N)
            Laz[r2] = Composition.apply(f, Laz[r2]);
        }
        l2 >>= 1;
        r2 >>= 1;
      }
      updateFrom(l, r);
    }

    public int maxRight(int l, java.util.function.Predicate<S> g) {
      inclusiveRangeCheck(l);
      if (!g.test(E)) {
        throw new IllegalArgumentException("Identity element must satisfy the condition.");
      }
      if (l == MAX)
        return MAX;
      l += N;
      pushTo(l);
      S sum = E;
      do {
        l >>= Integer.numberOfTrailingZeros(l);
        if (!g.test(Op.apply(sum, Dat[l]))) {
          while (l < N) {
            push(l);
            l = l << 1;
            if (g.test(Op.apply(sum, Dat[l]))) {
              sum = Op.apply(sum, Dat[l]);
              l++;
            }
          }
          return l - N;
        }
        sum = Op.apply(sum, Dat[l]);
        l++;
      } while ((l & -l) != l);
      return MAX;
    }

    public int minLeft(int r, java.util.function.Predicate<S> g) {
      inclusiveRangeCheck(r);
      if (!g.test(E)) {
        throw new IllegalArgumentException("Identity element must satisfy the condition.");
      }
      if (r == 0)
        return 0;
      r += N;
      pushTo(r - 1);
      S sum = E;
      do {
        r--;
        while (r > 1 && (r & 1) == 1)
          r >>= 1;
        if (!g.test(Op.apply(Dat[r], sum))) {
          while (r < N) {
            push(r);
            r = r << 1 | 1;
            if (g.test(Op.apply(Dat[r], sum))) {
              sum = Op.apply(Dat[r], sum);
              r--;
            }
          }
          return r + 1 - N;
        }
        sum = Op.apply(Dat[r], sum);
      } while ((r & -r) != r);
      return 0;
    }

    private void exclusiveRangeCheck(int p) {
      if (p < 0 || p >= MAX) {
        throw new IndexOutOfBoundsException(
            String.format("Index %d is not in [%d, %d).", p, 0, MAX));
      }
    }

    private void inclusiveRangeCheck(int p) {
      if (p < 0 || p > MAX) {
        throw new IndexOutOfBoundsException(
            String.format("Index %d is not in [%d, %d].", p, 0, MAX));
      }
    }

    // **************** DEBUG **************** //

    private int indent = 6;

    public void setIndent(int newIndent) {
      this.indent = newIndent;
    }

    @Override
    public String toString() {
      return toSimpleString();
    }

    private S[] simulatePushAll() {
      S[] simDat = java.util.Arrays.copyOf(Dat, 2 * N);
      F[] simLaz = java.util.Arrays.copyOf(Laz, 2 * N);
      for (int k = 1; k < N; k++) {
        if (simLaz[k] == Id)
          continue;
        int lk = k << 1 | 0, rk = k << 1 | 1;
        simDat[lk] = Mapping.apply(simLaz[k], simDat[lk]);
        simDat[rk] = Mapping.apply(simLaz[k], simDat[rk]);
        if (lk < N)
          simLaz[lk] = Composition.apply(simLaz[k], simLaz[lk]);
        if (rk < N)
          simLaz[rk] = Composition.apply(simLaz[k], simLaz[rk]);
        simLaz[k] = Id;
      }
      return simDat;
    }

    public String toDetailedString() {
      return toDetailedString(1, 0, simulatePushAll());
    }

    private String toDetailedString(int k, int sp, S[] dat) {
      if (k >= N)
        return indent(sp) + dat[k];
      String s = "";
      s += toDetailedString(k << 1 | 1, sp + indent, dat);
      s += "\n";
      s += indent(sp) + dat[k];
      s += "\n";
      s += toDetailedString(k << 1 | 0, sp + indent, dat);
      return s;
    }

    private static String indent(int n) {
      StringBuilder sb = new StringBuilder();
      while (n-- > 0)
        sb.append(' ');
      return sb.toString();
    }

    public String toSimpleString() {
      S[] dat = simulatePushAll();
      StringBuilder sb = new StringBuilder();
      sb.append('[');
      for (int i = 0; i < N; i++) {
        sb.append(dat[i + N]);
        if (i < N - 1)
          sb.append(',').append(' ');
      }
      sb.append(']');
      return sb.toString();
    }
  }

  private static void solve() {
    long n = nl();
    int q = ni();

    long[] a = new long[q * 2 + 2];
    int ptr = 0;

    int cur = 1;
    long[][] query = new long[q][3];
    for (int i = 0; i < q; i++) {
      long d = nl();
      long l = nl();
      long r = nl();

      a[ptr++] = l;
      a[ptr++] = r;

      query[i][0] = d;
      query[i][1] = l;
      query[i][2] = r;
    }

    a[ptr++] = n;
    a[ptr++] = 0;
    Arrays.sort(a);
    var cnt = new HashMap<Long, Integer>();
    int idx = 0;
    long[] b = new long[q * 4 + 10];
    for (long v : a) {
      if (!cnt.containsKey(v)) {
        cnt.put(v, idx);
        b[idx] = v;
        idx++;
      }
      if (!cnt.containsKey(v + 1)) {
        cnt.put(v + 1, idx);
        b[idx] = v + 1;
        idx++;
      }
    }

    int mod = 998244353;
    long inv2 = inv(2, mod);
    long[][] dat = new long[idx][2];
    for (int i = 0; i < idx - 1; i++) {
      long x = (b[i + 1] + b[i] - 1) % mod;
      long y = (b[i + 1] - b[i] + mod) % mod;
      dat[i][1] = x * y % mod * inv2 % mod;
    }

    // (a, b) + (c, d) = (a + c, b + d)
    // | 0 0 | a |
    // | 0 1 | b |

    // | 1 d | a |
    // | 0 1 | b |

    var st = new LazySegTree<long[], long[][]>(dat, (o1, o2) -> {
      return new long[] { (o1[0] + o2[0]) % mod, (o1[1] + o2[1]) % mod };
    }, new long[2], (m, o) -> {
      // matrix m * vector o;
      return new long[] {
          (m[0][0] * o[0] + m[0][1] * o[1]) % mod,
          (m[1][0] * o[0] + m[1][1] * o[1]) % mod
      };
    }, (m1, m2) -> {
      return new long[][] {
          {
              (m1[0][0] * m2[0][0] + m1[0][1] * m2[1][0]) % mod,
              (m1[0][0] * m2[0][1] + m1[0][1] * m2[1][1]) % mod,
          },
          {
              (m1[1][0] * m2[0][0] + m1[1][1] * m2[1][0]) % mod,
              (m1[1][0] * m2[0][1] + m1[1][1] * m2[1][1]) % mod
          } };
    }, new long[][] { { 1, 0 }, { 0, 1 } });

    long lastDay = 0;
    for (var p : query) {
      long d = p[0];

      st.apply(0, idx, new long[][] { { 1, (d - lastDay) % mod }, { 0, 1 } });

      int l = cnt.get(p[1]);
      int r = cnt.get(p[2]) + 1;
      var ret = st.prod(l, r)[0];
      out.println(ret);

      st.apply(l, r, new long[][] { { 0, 0 }, { 0, 1 } });

      lastDay = d;
    }
  }

  public static long inv(long a, int p) {
    long ret = 1;
    long mul = a;
    for (long n = p - 2; n > 0; n >>>= 1) {
      if ((n & 1) == 1)
        ret = ret * mul % p;
      mul = mul * mul % p;
    }
    return ret;
  }

  public static void main(String[] args) {
    new Thread(null, new Runnable() {
      @Override
      public void run() {
        long start = System.currentTimeMillis();
        String debug = args.length > 0 ? args[0] : null;
        if (debug != null) {
          try {
            is = java.nio.file.Files.newInputStream(java.nio.file.Paths.get(debug));
          } catch (Exception e) {
            throw new RuntimeException(e);
          }
        }
        reader = new java.io.BufferedReader(new java.io.InputStreamReader(is), 32768);
        solve();
        out.flush();
        tr((System.currentTimeMillis() - start) + "ms");
      }
    }, "", 64000000).start();
  }

  private static java.io.InputStream is = System.in;
  private static java.io.PrintWriter out = new java.io.PrintWriter(System.out);
  private static java.util.StringTokenizer tokenizer = null;
  private static java.io.BufferedReader reader;

  public static String next() {
    while (tokenizer == null || !tokenizer.hasMoreTokens()) {
      try {
        tokenizer = new java.util.StringTokenizer(reader.readLine());
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
    return tokenizer.nextToken();
  }

  private static double nd() {
    return Double.parseDouble(next());
  }

  private static long nl() {
    return Long.parseLong(next());
  }

  private static int[] na(int n) {
    int[] a = new int[n];
    for (int i = 0; i < n; i++)
      a[i] = ni();
    return a;
  }

  private static char[] ns() {
    return next().toCharArray();
  }

  private static long[] nal(int n) {
    long[] a = new long[n];
    for (int i = 0; i < n; i++)
      a[i] = nl();
    return a;
  }

  private static int[][] ntable(int n, int m) {
    int[][] table = new int[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        table[i][j] = ni();
      }
    }
    return table;
  }

  private static int[][] nlist(int n, int m) {
    int[][] table = new int[m][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        table[j][i] = ni();
      }
    }
    return table;
  }

  private static int ni() {
    return Integer.parseInt(next());
  }

  private static void tr(Object... o) {
    if (is != System.in)
      System.out.println(java.util.Arrays.deepToString(o));
  }
}
