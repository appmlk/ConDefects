import java.util.*;
public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String[] s = sc.next().split("");
    String[] t = sc.next().split("");
    for(int i = 0; i < s.length; i ++) {
      if(!t[i].equals(s[i])) {
        System.out.println(i + 1);
        break;
      } else if (i == s.length - 1) {
        System.out.println(s.length + 1);
      }
    }
  }
}