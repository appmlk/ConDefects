import java.util.*;
import static java.lang.Character.*;
public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String s = sc.next();
    int slen = s.length();
    int index = 6;
    if(slen != 8) {
      System.out.println("No");
    } else if (isUpperCase(s.charAt(0)) && isUpperCase(s.charAt(7))) {
      String x = "";
      for(int i = 1; i < 7; i ++) {
        if(isDigit(s.charAt(i))) {
          x = x.concat(String.valueOf(s.charAt(i)));
          index --;
        }
      }
      if(index == 0) {
        int ii = Integer.parseInt(x);
        if(ii > 99999 && ii < 1000000) {
          System.out.println("Yes");
        } else {
          System.out.println("No");
        }
      } else {
        System.out.println("No");
      }
    } else {
      System.out.println("No");
    }
  }
}