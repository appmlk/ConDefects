import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int S = sc.nextInt();
    int M = sc.nextInt();
    int L = sc.nextInt();
    int ans = Integer.MAX_VALUE;

    for (int i=0; i<=18; i++) {
      for (int j=0; j<=18; j++) {
        for (int k=0; k<=18; k++) {
          if (6*i + 8*j + 12*k >= N) {
            ans = Math.min(ans, S*i + M*j + L*k);
          }
        }
      }
    }
    System.out.println(ans);
  }
}