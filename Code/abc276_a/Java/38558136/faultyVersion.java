import java.util.*;
public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String input = sc.next();
    int ans = -1;
    for (int i = 0; i < input.length(); i++) {
        if (input.substring(i,i+1).equals("a")) {
          ans = i;
        }
    }
    System.out.println(ans);
  }
}