import java.util.*;

@SuppressWarnings("unused")
public class Main {

  private static void solve() {
    int n = ni();
    int m = ni();
    char[][] s = new char[m][];
    for (int i = 0; i < m; i++) {
      s[i] = ns();
    }
    int[] a = new int[m];
    int[] mask = new int[m];
    for (int i = 0; i < m; i++) {
      mask[i] = (1 << s[i].length) - 1;
      for (int j = 0; j < s[i].length; j++) {
        if (s[i][j] == 'b') {
          a[i] |= 1 << (s[i].length - j - 1);
        }
      }
    }
    int mod = 998244353;

    int[] v = new int[64];
    v[0] = 1;

    for (int i = 0; i < Math.min(6, n); i ++) {
      var mat = getMat(a, mask, i + 1);
      v = mul(mat, v, mod);
    }
    if (n >= 6) {
      var mat = getMat(a, mask, 6);
      v = pow(mat, v, n - 6, mod);
    }

    long ret = 0;
    for (var x : v) {
      ret += x;
      ret %= mod;
    }

    System.out.println(ret);
  }


	public static int[] pow(int[][] A, int[] v, long e, int mod)
	{
		int[][] MUL = A;
		for(int i = 0;i < v.length;i++)v[i] %= mod;
		for(;e > 0;e>>>=1) {
			if((e&1)==1)v = mul(MUL, v, mod);
			MUL = p2(MUL, mod);
		}
		return v;
	}
	public static int[][] p2(int[][] A, int mod)
	{
		int n = A.length;
		int[][] C = new int[n][n];
		for(int i = 0;i < n;i++){
			for(int j = 0;j < n;j++){
				long sum = 0;
				for(int k = 0;k < n;k++){
					sum += (long)A[i][k] * A[k][j];
					sum %= mod;
				}
				C[i][j] = (int)sum;
			}
		}
		return C;
	}
	public static int[] mul(int[][] A, int[] v, int mod)
	{
		int m = A.length;
		int n = v.length;
		int[] w = new int[m];
		for(int i = 0;i < m;i++){
			long sum = 0;
			for(int k = 0;k < n;k++){
				sum += (long)A[i][k] * v[k];
				sum %= mod;
			}
			w[i] = (int)sum;
		}
		return w;
	}
  private static int[][] getMat(int[] a, int[] mask, int len) {
    int m = a.length;
    int[][] mat = new int[64][64];
    for (int i = 0; i < 64; i++) {
      for (int k = 0; k < 2; k ++) {
        int j = (i << 1) & (64 - 1) | k;
        var ok = true;
        for (int p = 0; p < m; p ++) {
          int x = (1 << len) - 1;
          if (x >= mask[p] && (j & mask[p]) == a[p]) {
            ok = false;
            break;
          }
        }
        if (ok) {
          mat[j][i] += 1;
        }
      }
    }
    return mat;
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
