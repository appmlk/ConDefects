import java.io.PrintWriter;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

  private static final int INF = Integer.MAX_VALUE / 2;

  public static void main(String[] args) throws Exception {
    final FastScanner sc = new FastScanner(System.in);
    final int n = sc.nextInt();
    final long k = sc.nextLong();
    final int[][] dist = new int[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        dist[i][j] = sc.nextInt();
      }
    }
    final PrintWriter pw = new PrintWriter(System.out);
    final int q = sc.nextInt();
    for (int cnt = 0; cnt < q; cnt++) {
      long s = sc.nextLong() - 1;
      long t = sc.nextLong() - 1;
      int s_modn = (int) (s % (long) n);
      int t_modn = (int) (t % (long) n);
      int[] temp = new int[n];
      Arrays.fill(temp, INF);
      PriorityQueue<IntPair> queue = new PriorityQueue<>();
      queue.add(new IntPair(s_modn, 0));
      while (!queue.isEmpty()) {
        IntPair p = queue.poll();
        for (int j = 0; j < n; j++) {
          if (dist[p.second][j] == 1 && temp[j] > p.first + 1) {
            temp[j] = p.first + 1;
            queue.add(new IntPair(temp[j], j));
          }
        }
      }
      int ans = temp[t_modn];
      pw.println(ans == INF ? -1 : ans);
    }
    pw.close();
    sc.close();
  }

  static class IntPair implements Comparable<IntPair> {

    int first, second;

    public IntPair(int first, int second) {
      this.first = first;
      this.second = second;
    }

    @Override
    public int compareTo(IntPair o) {
      if (first != o.first) {
        return Integer.compare(first, o.first);
      } else {
        return Integer.compare(second, o.second);
      }
    }
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

    private boolean isPrintableChar(int c) {
      return 33 <= c && c <= 126;
    }

    private boolean isNumeric(int c) {
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
