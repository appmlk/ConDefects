import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final int n = Integer.parseInt(br.readLine());
    final StringTokenizer st_a = new StringTokenizer(br.readLine());
    final int[] array_a = new int[n];
    for (int i = 0; i < n; i++) {
      array_a[i] = Integer.parseInt(st_a.nextToken());
    }
    br.close();
    final PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
    int min = Integer.MAX_VALUE;
    for (int a : array_a) {
      min = Math.min(min, a);
      queue.add(a);
    }
    int ans = 0;
    while (queue.size() > 1) {
      int m = queue.poll() % min;
      ans++;
      if (m != 0) {
        queue.add(m);
        min = m;
      }
    }
    System.out.println(ans);
  }
}
