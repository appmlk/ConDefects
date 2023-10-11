import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final StringTokenizer st = new StringTokenizer(br.readLine());
    final int n = Integer.parseInt(st.nextToken());
    final int l = Integer.parseInt(st.nextToken());
    final StringTokenizer st_a = new StringTokenizer(br.readLine());
    final int[] array_a = new int[n];
    for (int i = 0; i < n; i++) {
      array_a[i] = Integer.parseInt(st_a.nextToken());
    }
    br.close();
    boolean isOK = true;
    int remain = l;
    for (int i = 0; i < n; i++) {
      if (array_a[i] == 2) {
        if (remain < 2) {
          isOK = false;
          break;
        } else {
          remain = Math.max(remain - 3, 0);
        }
      } else if (array_a[i] == 1) {
        remain = Math.max(remain - 2, 0);
      }
    }
    System.out.println(isOK ? "Yes" : "No");
  }
}
