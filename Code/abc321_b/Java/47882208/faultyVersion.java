import java.util.*;
import java.util.stream.*;
public class Main {
  public static void main(String[] args) {
    int n, x;
    int[] a;
    try (var s = new Scanner(System.in)) {
      n = s.nextInt();
      x = s.nextInt();
      a = new int[n];
      for (int i = 0; i < n-1; i++) a[i] = s.nextInt();
    }
    int min = Integer.MAX_VALUE;
    for (int i = 0; i <= 100; i++) {
      int[] b = a.clone();
      b[n-1] = i;
      Arrays.sort(b);
      //System.out.println(Arrays.toString(b));//debug
      int sum = 0;
      for (int j = 1; j < n-1; j++) sum += b[j];
      //System.out.println(sum);//debug
      if (sum == x) {
        min = i;
        break;
      }
    }
    System.out.println(min == Integer.MAX_VALUE ? -1 : min);
  }
}