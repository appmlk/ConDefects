import java.util.*;

class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String n = sc.next();
    int i=1;
    for(; i<n.length();i++) if((int)n.charAt(i-1) <= n.charAt(i)) break;
    System.out.print(i == n.length() ? "Yes" : "No");
  }
}