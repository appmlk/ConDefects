import java.util.*;

public class Main {
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    String s = sc.next();
    int cnt = 0;
    String result = "out";

    for(int i = 0; i < n; i++) {
      if(s.charAt(i) == '|') {
        cnt++;
      }
      if(cnt == 1 && s.charAt(i) == '*') {
        result = "in";
      }
    }
    System.out.println(result);
  }
}