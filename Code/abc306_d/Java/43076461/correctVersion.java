public class Main {

  public static void main(String[] args) throws Exception {
    final FastScanner sc = new FastScanner(System.in);
    final int n = sc.nextInt();
    final int[] arr_x = new int[n];
    final long[] arr_y = new long[n];
    for (int i = 0; i < n; i++) {
      arr_x[i] = sc.nextInt();
      arr_y[i] = sc.nextLong();
    }
    sc.close();
    //dp[i][j]:=j個目まで食べるかどうか決めて、状態i(0:お腹を壊してない 1:お腹を壊している)の時の最大スコア
    final long[][] dp = new long[2][n + 1];
    dp[0][0] = 0;
    dp[1][0] = Long.MIN_VALUE / 2;
    for (int i = 1; i <= n; i++) {
      if (arr_x[i - 1] == 0) {
        dp[0][i] = Math.max(dp[0][i - 1] + arr_y[i - 1], dp[0][i - 1]);
        dp[0][i] = Math.max(dp[1][i - 1] + arr_y[i - 1], dp[0][i]);
        dp[1][i] = dp[1][i - 1];
      } else {
        dp[0][i] = dp[0][i - 1];
        dp[1][i] = Math.max(dp[0][i - 1] + arr_y[i - 1], dp[1][i - 1]);
      }
    }
    System.out.println(Math.max(dp[0][n], dp[1][n]));
  }

  // FastScannerライブラリ
  static class FastScanner implements AutoCloseable {

    private final java.io.InputStream in;
    private final byte[] buf = new byte[1024];
    private int ptr = 0;
    private int buflen = 0;

    FastScanner(java.io.InputStream source) {
      this.in = source;
    }

    private boolean hasNextByte() {
      if (ptr < buflen) {
        return true;
      } else {
        ptr = 0;
        try {
          buflen = in.read(buf);
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
        return buf[ptr++];
      } else {
        return -1;
      }
    }

    private boolean isPrintableChar(final int c) {
      return 33 <= c && c <= 126;
    }

    private boolean isNumeric(final int c) {
      return '0' <= c && c <= '9';
    }

    private void skipToNextPrintableChar() {
      while (hasNextByte() && !isPrintableChar(buf[ptr])) {
        ptr++;
      }
    }

    public boolean hasNext() {
      skipToNextPrintableChar();
      return hasNextByte();
    }

    public String next() {
      if (!hasNext()) {
        throw new java.util.NoSuchElementException();
      }
      StringBuilder ret = new StringBuilder();
      int b = readByte();
      while (isPrintableChar(b)) {
        ret.appendCodePoint(b);
        b = readByte();
      }
      return ret.toString();
    }

    public long nextLong() {
      if (!hasNext()) {
        throw new java.util.NoSuchElementException();
      }
      long ret = 0;
      int b = readByte();
      boolean negative = false;
      if (b == '-') {
        negative = true;
        if (hasNextByte()) {
          b = readByte();
        }
      }
      if (!isNumeric(b)) {
        throw new NumberFormatException();
      }
      while (true) {
        if (isNumeric(b)) {
          ret = ret * 10 + b - '0';
        } else if (b == -1 || !isPrintableChar(b)) {
          return negative ? -ret : ret;
        } else {
          throw new NumberFormatException();
        }
        b = readByte();
      }
    }

    public int nextInt() {
      return (int) nextLong();
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
