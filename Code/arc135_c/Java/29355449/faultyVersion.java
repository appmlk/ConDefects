import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    long[] array_a = new long[n];
    String[] input_a = br.readLine().split(" ");
    long sum = 0;
    int[] binary_bit_cnt = new int[30];
    for (int i = 0; i < n; i++) {
      array_a[i] = Long.parseLong(input_a[i]);
      sum += array_a[i];
      for (int k = 0; array_a[i] >> k > 0; k++) {
        if ((array_a[i] >> k) % 2 == 1) {
          binary_bit_cnt[k] += 1;
        }
      }
    }
    br.close();

    long score = 0;
    for (int i = 0; i < n; i++) {
      long tmp_score = 0;
      for (int k = 0; array_a[i] >> k > 0; k++) {
        if ((array_a[i] >> k) % 2 == 1) {
          tmp_score += (n - (2 * binary_bit_cnt[k])) * (1 << k);
        }
      }
      score = Math.max(tmp_score, score);
    }
    System.out.println(sum + score);
  }
}
