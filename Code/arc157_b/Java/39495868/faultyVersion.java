import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final StringTokenizer st = new StringTokenizer(br.readLine());
    final int n = Integer.parseInt(st.nextToken());
    final int k = Integer.parseInt(st.nextToken());
    final String s = br.readLine();
    br.close();
    int cnt_x = 0;
    for (int i = 0; i < n; i++) {
      if (s.charAt(i) == 'X') {
        cnt_x++;
      }
    }
    int ans = 0;
    if (cnt_x == n) {
      //ALLXの場合、できるだけYYを繋げれば良い
      ans = k > 1 ? k - 1 : 0;
    } else if (cnt_x == 0) {
      //ALLYの場合、片側からXに置き換え
      ans = n - 1 - k;
    } else if (cnt_x == k) {
      //Xを全部Yにできる場合は、全部Yにすればよい
      ans = n - 1;
    } else {
      int remain = k;
      char as_y = 'Y';
      if (k > cnt_x) {
        remain = n - k;
        as_y = 'X';
      }
      boolean flg_y = s.charAt(0) == as_y;
      PriorityQueue<Integer> queue = new PriorityQueue<>();
      int cnt_xx = 0;
      for (int i = 1; i < n; i++) {
        if (s.charAt(i) == as_y) {
          flg_y = true;
          //Yの場合、前の文字がYならYYを一個増やす。
          //前の文字がXならこれまでのX個数をキューに
          if (s.charAt(i - 1) == as_y) {
            ans++;
          } else {
            if (cnt_xx > 0) {
              queue.add(cnt_xx);
              cnt_xx = 0;
            }
          }
        } else {
          //Xの場合、以前にYが一つでもあればX連続個数を増やす
          if (flg_y) {
            cnt_xx++;
          }
        }
      }
      while (!queue.isEmpty() && remain > 0) {
        int cnt = queue.poll();
        if (remain >= cnt) {
          remain -= cnt;
          ans += cnt + 1;
        }
      }
      //最後に両サイドのXをできるだけYにする
      ans += remain;
    }
    System.out.println(ans);
  }
}
