import java.util.*;

class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String s = sc.next();
    int i = 1;
    while(s.charAt(i) == '0' && i != 15) i += 2;
    System.out.print(s.charAt(i) == '0' && i == 15 ? "Yes" : "No");
  }
}