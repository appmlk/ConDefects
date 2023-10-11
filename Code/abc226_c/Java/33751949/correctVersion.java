import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {

  public static void main(String[] args) throws Exception {
    final NextScanner sc = new NextScanner(System.in);
    final int n = Integer.parseInt(sc.next());
    final List<List<Integer>> skill_graph = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      skill_graph.add(new ArrayList<>());
    }
    final long[] times_to_learn = new long[n];
    for (int i = 0; i < n; i++) {
      times_to_learn[i] = Long.parseLong(sc.next());
      int k = Integer.parseInt(sc.next());
      for (int j = 0; j < k; j++) {
        int a = Integer.parseInt(sc.next()) - 1;
        skill_graph.get(i).add(a);
      }
    }
    sc.close();
    boolean[] learned = new boolean[n];
    long ans = 0;
    //要求されるスキルをキューで管理する
    Queue<Integer> skill_to_learn = new ArrayDeque<>();
    skill_to_learn.add(n - 1);
    while (!skill_to_learn.isEmpty()) {
      int skill = skill_to_learn.poll();
      if (learned[skill]) {
        // do nothing
      } else {
        ans += times_to_learn[skill];
        learned[skill] = true;
      }
      for (int next_skill : skill_graph.get(skill)) {
        if (!learned[next_skill]) {
          skill_to_learn.add(next_skill);
        }
      }
    }
    System.out.println(ans);
  }

  //NextScannerライブラリ
  static class NextScanner implements AutoCloseable {

    private final java.io.BufferedReader br;

    private java.util.StringTokenizer st;

    private static final int BUF_SIZE = 1 << 16;

    private static final char[] c_buf = new char[BUF_SIZE];

    public NextScanner(java.io.InputStream input) {
      this.br = new java.io.BufferedReader(new java.io.InputStreamReader(input));
    }

    private java.util.StringTokenizer readInput() {
      java.util.StringTokenizer st;
      try {
        int b = br.read(c_buf);
        if (b == BUF_SIZE) {
          StringBuilder sb = new StringBuilder();
          sb.append(c_buf);
          sb.append(br.readLine());
          st = new java.util.StringTokenizer(sb.toString());
        } else if (b < 0) {
          throw new java.util.NoSuchElementException();
        } else {
          st = new java.util.StringTokenizer(new String(c_buf, 0, b));
        }
      } catch (java.io.IOException e) {
        throw new java.util.InputMismatchException(e.getMessage());
      }
      return st;
    }

    public String next() throws java.io.IOException {
      if (st == null || !st.hasMoreElements()) {
        st = readInput();
      }
      return st.nextToken();
    }

    @Override
    public void close() throws Exception {
      br.close();
    }
  }
}
