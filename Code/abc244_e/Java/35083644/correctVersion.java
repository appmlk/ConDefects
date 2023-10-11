import java.util.ArrayList;
import java.util.List;

public class Main {

  private static final long MOD = 998244353L;

  public static void main(String[] args) throws Exception {
    final FastScanner sc = new FastScanner(System.in);
    final int n = sc.nextInt();
    final int m = sc.nextInt();
    final int k = sc.nextInt();
    final int s = sc.nextInt() - 1;
    final int t = sc.nextInt() - 1;
    final int x = sc.nextInt() - 1;
    final List<List<Integer>> list_edge = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      list_edge.add(new ArrayList<>());
    }
    for (int i = 0; i < m; i++) {
      int u = sc.nextInt() - 1;
      int v = sc.nextInt() - 1;
      list_edge.get(u).add(v);
      list_edge.get(v).add(u);
    }
    sc.close();
    //dp[k][n][2]:=k回目の移動で、頂点nに到達し、且つxにmod2回到達した
    final long[][][] dp = new long[k + 1][n][2];
    dp[0][s][0] = 1;
    for (int i = 1; i <= k; i++) {
      for (int prev = 0; prev < n; prev++) {
        if (dp[i - 1][prev][0] + dp[i - 1][prev][1] == 0) {
          continue;
        }
        for (int next : list_edge.get(prev)) {
          if (next == x) {
            dp[i][next][1] += dp[i - 1][prev][0];
            dp[i][next][0] += dp[i - 1][prev][1];
          } else {
            dp[i][next][0] += dp[i - 1][prev][0];
            dp[i][next][1] += dp[i - 1][prev][1];
          }
          dp[i][next][0] %= MOD;
          dp[i][next][1] %= MOD;
        }
      }
    }
    System.out.println(dp[k][t][0]);
  }

  // FastScannerライブラリ
  static class FastScanner implements AutoCloseable {

    private final java.io.InputStream in;
    private final byte[] buffer = new byte[1024];
    private int ptr = 0;
    private int buflen = 0;

    public FastScanner(java.io.InputStream input) {
      this.in = input;
    }

    private boolean hasNextByte() {
      if (ptr < buflen) {
        return true;
      } else {
        ptr = 0;
        try {
          buflen = in.read(buffer);
        } catch (java.io.IOException e) {
          e.printStackTrace();
        }
        if (buflen <= 0) {
          return false;
        }
      }
      return true;
    }

    private int readByte() {
      if (hasNextByte()) {
        return buffer[ptr++];
      } else {
        return -1;
      }
    }

    private static boolean isPrintableChar(int c) {
      return 33 <= c && c <= 126;
    }

    public boolean hasNext() {
      while (hasNextByte() && !isPrintableChar(buffer[ptr])) {
        ptr++;
      }
      return hasNextByte();
    }

    public String next() {
      if (!hasNext()) {
        throw new java.util.NoSuchElementException();
      }
      StringBuilder sb = new StringBuilder();
      int b = readByte();
      while (isPrintableChar(b)) {
        sb.appendCodePoint(b);
        b = readByte();
      }
      return sb.toString();
    }

    public long nextLong() {
      if (!hasNext()) {
        throw new java.util.NoSuchElementException();
      }
      long n = 0;
      boolean minus = false;
      int b = readByte();
      if (b == '-') {
        minus = true;
        b = readByte();
      }
      if (b < '0' || '9' < b) {
        throw new NumberFormatException();
      }
      while (true) {
        if ('0' <= b && b <= '9') {
          n *= 10;
          n += b - '0';
        } else if (b == -1 || !isPrintableChar(b)) {
          return minus ? -n : n;
        } else {
          throw new NumberFormatException();
        }
        b = readByte();
      }
    }

    public int nextInt() {
      long nl = nextLong();
      if (nl < Integer.MIN_VALUE || nl > Integer.MAX_VALUE) {
        throw new NumberFormatException();
      }
      return (int) nl;
    }

    public double nextDouble() {
      return Double.parseDouble(next());
    }

    @Override
    public void close() throws Exception {
      in.close();
    }
  }
}
