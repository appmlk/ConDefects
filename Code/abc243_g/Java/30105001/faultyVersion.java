import java.util.*;

@SuppressWarnings("unused")
public class Main {

  private static void solve() {
    int t = ni();
    for (int i = 0; i < t; i++) {
      long x = nl();
      out.println(solve(x));
    }

  }

  private static long solve(long x) {
    if (x == 1) {
      return 1;
    }

    long n1 = sqrt(x);
    int n = (int)sqrt(n1);

    int m = 6;
    long[][] dp = new long[m][n + 1];

    // for (int i = 2; i <= n1; i++) {
    //   for (int j = 2; j * j <= i; j ++) {
    //     dp[0][j] ++;
    //   }
    // }
    for (int j = 2; j <= n; j ++) {
      dp[0][j] = Math.max(0, n1 - j * j + 1);
    }

    long ret = n1;

    for (int i = 1; i < m; i ++) {
      for (int j = 2; j <= n; j ++) {
        for (long k = 1L * j * j; k <= n; k ++) {
          dp[i][j] += dp[i-1][(int)k];
        }
      }
    }

    for (int i = 0; i < m; i ++) {
      for (int j = 0; j <= n; j ++) {
        ret += dp[i][j];
      }
    }
    return ret;
  }

  private static long sqrt(long x) {
    long ok = 1;
    long ng = 3000000001L;
    while (Math.abs(ok - ng) > 1) {
      long k = (ok + ng) / 2;
      if (k * k <= x) {
        ok = k;
      } else {
        ng = k;
      }
    }

    return ok;
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
