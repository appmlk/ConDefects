import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final int n = Integer.parseInt(br.readLine());
    final StringTokenizer st_a = new StringTokenizer(br.readLine());
    br.close();
    final long[] array_a = new long[n];
    for (int i = 0; i < n; i++) {
      array_a[i] = Long.parseLong(st_a.nextToken());
    }
    long g = 0;
    for (int i = 0; i < n - 1; i++) {
      g = gcd(array_a[i + 1] - array_a[i], g);
    }
    System.out.println(g != 1 ? 1 : 2);
  }

  static long gcd(long a, long b) {
    return b == 0 ? a : gcd(b, a % b);
  }
}
