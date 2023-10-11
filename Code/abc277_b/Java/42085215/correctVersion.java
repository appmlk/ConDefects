import java.util.*;
import java.util.regex.*;

class Main {
  static int n;
  public static int sum (int x) {
    if (x == 0) {
      return x;
    }
    return x - 1 + sum(x - 1);
  }
  
  public static void main (String[] args) {
    Scanner sc = new Scanner(System.in);
    n = Integer.parseInt(sc.next());
    List<String> s = new ArrayList<>();
    String ans = "No";
    int cnt = 0;
    for (int i = 0; i < n; i++) { 
      s.add(sc.next());
    }
    
    if (n == 1) {
      if (Pattern.matches("[SDCH][ATJQK2-9]", s.get(0))) {
        ans = "Yes";
      }
    } else {
      for (int i = 0; i < n - 1; i++) {
        for (int j = i + 1; j < n; j++) {
          if (Pattern.matches("[SDCH][ATJQK2-9]", s.get(i)) && Pattern.matches("[SDCH][ATJQK2-9]", s.get(j))) {
            if (s.get(i).equals(s.get(j))) {
              continue;
            } else {
              cnt++;
              // System.out.println(cnt);
            }
          }
        }
      }
    }
    if (cnt == sum(n) && n != 1) {
      ans = "Yes";
    }
    System.out.println(ans);
    //System.out.println(sum(2));
    //System.out.println(cnt);
  }
}
