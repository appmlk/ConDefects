import java.util.*;

public class Main {
  public static void main (String[] args)
  {
    new Main().run();
  }

  void run ()
  {
    Scanner cin = new Scanner(System.in);
    int N = cin.nextInt();
    int M = cin.nextInt();
    String S = cin.next();
    String T = cin.next();
    cin.close();
    int ans = 3;
    if (T.substring(0, N).equals(S)) {
      ans -= 2;
    }
    if (T.substring(M-N, M).equals(S)) {
      ans -= 1;
    }

    System.out.println(ans);
  }
}