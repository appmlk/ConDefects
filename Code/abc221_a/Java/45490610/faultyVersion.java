import java.util.*;
public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    double a = sc.nextDouble();
    double b = sc.nextDouble();
    double ans = Math.pow(32.0, a - b);
    System.out.println(ans);
  }
}