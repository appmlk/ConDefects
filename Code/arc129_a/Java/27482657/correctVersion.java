import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] input = br.readLine().split(" ");
    br.close();
    long n = Long.parseLong(input[0]);
    long l = Long.parseLong(input[1]);
    long r = Long.parseLong(input[2]);

    long x = 1;
    long ans = 0;
    while (r >= x) {
      if ((n ^ x) < n) {
        long range_min = x;
        long range_max = (x << 1) - 1;
        ans += Math.max(Math.min(range_max, r) - Math.max(range_min, l) + 1, 0);
      }
      x = x << 1;
    }
    System.out.println(ans);
  }
}
