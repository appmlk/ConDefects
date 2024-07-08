// import static org.junit.jupiter.api.Assertions.assertEquals;

// import org.junit.jupiter.api.Test;
import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int M = sc.nextInt();
    int[] A = new int[N];
    int[] B = new int[M];
    for (int i = 0; i < N; i++) {
      A[i] = sc.nextInt();
    }
    for (int i = 0; i < M; i++) {
      B[i] = sc.nextInt();
    }
    int j = 0;
    long sum = 0;
    Arrays.sort(A);
    Arrays.sort(B);
    for (int i = 0; i < N; i++) {
      if (A[i] >= B[j]) {
        j++;
        sum += A[i];
      }
      if (j >= M) {
        System.out.println(sum);
        return;
      }
    }
    System.out.println(-1);
    return;
  }

  // @Test
  // void addition() {
  // assertEquals(2, 1 + 1);
  // }
}