import java.util.*;

class Main{
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    String s = sc.next();
    
    if (s.indexOf("ABC") > 0) {
      System.out.println(s.indexOf("ABC") + 1);
    } else {
      System.out.println(s.indexOf("ABC"));
    }
  }
}