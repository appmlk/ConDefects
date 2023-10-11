import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final int n = Integer.parseInt(br.readLine());
    final int[] array_p = new int[2 * n];
    final StringTokenizer st_p = new StringTokenizer(br.readLine());
    for(int i = 0; i < 2 * n; i++) {
      array_p[i] = Integer.parseInt(st_p.nextToken());
    }
    br.close();
    List<Integer> ans = new ArrayList<>();
    for(int i = 1; i < n - 1; i++) {
      int a = (i * 2) - 1, b = i * 2;
      if(array_p[a] < array_p[b]) {
        int tmp = array_p[a];
        array_p[a] = array_p[b];
        array_p[b] = tmp;
        ans.add(b);
      }
    }
    for(int i = 0; i < n; i++) {
      int a = i * 2, b = (i * 2) + 1;
      if(array_p[a] > array_p[b]) {
        int tmp = array_p[a];
        array_p[a] = array_p[b];
        array_p[b] = tmp;
        ans.add(b);
      }
    }
    PrintWriter pw = new PrintWriter(System.out);
    pw.println(ans.size());
    if(ans.size() > 0) {
      StringBuilder sb = new StringBuilder();
      for(int a : ans) {
        sb.append(a).append(' ');
      }
      pw.println(sb.deleteCharAt(sb.length() - 1));
    }
    pw.close();
  }
}
