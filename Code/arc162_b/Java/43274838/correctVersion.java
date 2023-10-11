import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final int n = Integer.parseInt(br.readLine());
    final StringTokenizer st_p = new StringTokenizer(br.readLine());
    final int[] arr_p = new int[n];
    for (int i = 0; i < n; i++) {
      arr_p[i] = Integer.parseInt(st_p.nextToken()) - 1;
    }
    br.close();
    final PrintWriter pw = new PrintWriter(System.out);
    //転倒数
    long inv = countInversionNumber(n, arr_p);
    if (inv % 2 == 1) {
      pw.println("No");
    } else if (inv == 0) {
      pw.println("Yes");
      pw.println(0);
    } else {
      List<String> comlist = new ArrayList<>();
      LinkedList<Integer> list = new LinkedList<>();
      for (int p : arr_p) {
        list.add(p);
      }
      for (int focus_num = 0; focus_num < n - 1; focus_num++) {
        int idx_focus = list.indexOf(focus_num);
        if (idx_focus == focus_num) {
          continue;
        }

        if (idx_focus == n - 1) {
          //移動したい要素が一番後ろの場合、まず後ろ２つの要素を一つ前に持っていく
          //その後、後ろ２つの要素を目的位置に持っていく
          //ex.3->2->1 ---> 2->1->3
          comlist.add((n - 1) + " " + (n - 3));
          comlist.add((n - 1) + " " + focus_num);
          int num_last_1 = list.pollLast();
          int num_last_2 = list.pollLast();
          int num_last_3 = list.pollLast();
          list.addLast(num_last_2);
          list.add(focus_num, num_last_1);
          list.add(focus_num + 1, num_last_3);
        } else {
          comlist.add((idx_focus + 1) + " " + focus_num);
          int num1 = list.remove(idx_focus);
          int num2 = list.remove(idx_focus);
          list.add(focus_num, num1);
          list.add(focus_num + 1, num2);
        }
      }
      pw.println("Yes");
      pw.println(comlist.size());
      for (String c : comlist) {
        pw.println(c);
      }
    }
    pw.close();
  }

  static long countInversionNumber(int n, int[] array) {
    //転倒数の計算
    long ret = 0;
    FenwickTree ft = new FenwickTree(n);
    for (int i = 0; i < n; i++) {
      ret += ft.sum(array[i], n);
      ft.add(array[i], 1);
    }
    return ret;
  }

  //FenwickTreeライブラリ
  static class FenwickTree {

    final int N;
    long[] data;

    public FenwickTree(int n) {
      N = n;
      this.data = new long[n];
    }

    public FenwickTree(long[] data) {
      N = data.length;
      this.data = new long[this.N];
      System.arraycopy(data, 0, this.data, 0, N);
      for (int i = 1; i <= N; i++) {
        int p = i + (-i & i);
        if (p <= N) {
          this.data[p - 1] += this.data[i - 1];
        }
      }
    }

    public void add(int p, long x) {
      if (!(p >= 0 && p < N)) {
        String errMsg = String.format("Index %d out of bounds for length %d.", p, N);
        throw new ArrayIndexOutOfBoundsException(errMsg);
      }
      p++;
      while (p <= N) {
        data[p - 1] += x;
        p += -p & p;
      }
    }

    public long sum(int l, int r) {
      if (l > r) {
        String errMsg = String.format("Invalid range: [%d, %d).", l, r);
        throw new IllegalArgumentException(errMsg);
      }
      if (!(l >= 0 && l <= N)) {
        String errMsg = String.format("Index %d out of bounds for length %d.", l, N);
        throw new ArrayIndexOutOfBoundsException(errMsg);
      }
      if (!(r >= 0 && r <= N)) {
        String errMsg = String.format("Index %d out of bounds for length %d.", r, N);
        throw new ArrayIndexOutOfBoundsException(errMsg);
      }
      return sum(r) - sum(l);
    }

    private long sum(int r) {
      long s = 0;
      while (r > 0) {
        s += data[r - 1];
        r -= -r & r;
      }
      return s;
    }
  }
}
