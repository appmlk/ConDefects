import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    int[] array_p = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    br.close();
    System.out.println(solve(n, array_p));
    return;
  }

  static int solve(int n, int[] array_p) {
    int ans = 0;
    int idx_1 = 0;
    for(int i = 0; i < n; i++){
      if(array_p[i] == 1) {
        idx_1 = i;
      }
    }
    if(array_p[(idx_1 + 1) % n] == 2) {
      //昇順の場合
      ans = idx_1;
      ans = Math.min((( n - idx_1) % n) + 2, ans);
    } else {
      //降順の場合
      ans = ((idx_1 + 1) % n) + 1;
      ans = Math.min((n - idx_1 - 1) + 1, ans);
    }

    return ans;
  }
}
