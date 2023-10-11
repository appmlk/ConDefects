import java.util.*;

public class Main {
  public static void main(String... args) {
    Scanner s = new Scanner(System.in);
    int n = s.nextInt();
    System.out.println((n / 100 + (n % 100) / 10 + n % 100) * 111);
  }
}