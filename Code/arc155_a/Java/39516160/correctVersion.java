import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final int t = Integer.parseInt(br.readLine());
    final PrintWriter pw = new PrintWriter(System.out);
    for (int i = 0; i < t; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      long n = Long.parseLong(st.nextToken());
      long k = Long.parseLong(st.nextToken());
      String s = br.readLine();
      pw.println(solve(n, k, s) ? "Yes" : "No");
    }
    pw.close();
    br.close();
  }

  private static boolean solve(long n, long k, String s) {
    long x = k % (2 * n);
    StringBuilder rev_s = new StringBuilder(s).reverse();
    StringBuilder sd = new StringBuilder();
    if (x > n) {
      sd.append(rev_s.substring(0, (int) (x - n)));
    }
    sd.append(rev_s.substring(Math.max((int) (n - x), 0)));
    return isPalindrome(s + sd) && isPalindrome(sd + s);
  }

  private static boolean isPalindrome(String s) {
    boolean isOK = true;
    int l = 0, r = s.length() - 1;
    while (l < r) {
      if (s.charAt(l) == s.charAt(r)) {
        l++;
        r--;
      } else {
        isOK = false;
        break;
      }
    }
    return isOK;
  }
}
