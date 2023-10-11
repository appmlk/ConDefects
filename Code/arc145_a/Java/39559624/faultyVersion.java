import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  public static void main(String[] args) throws IOException {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final int n = Integer.parseInt(br.readLine());
    final String s = br.readLine();
    br.close();
    if (s.charAt(0) == 'A' && s.charAt(n - 1) == 'B') {
      System.out.println("No");
    } else {
      System.out.println("Yes");
    }
  }
}
