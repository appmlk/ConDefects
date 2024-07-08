import java.util.*;
public class Main {
    public static void main(String[] args) {
        // 自分の得意な言語で
        // Let's チャレンジ！！
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(), A = sc.nextInt();
        int[] T = new int[N + 1];
        for (int i = 1; i <= N; i++) {
          T[i] = sc.nextInt();
        }
        for (int i = 1; i <= N; i++) {
          if (T[i] - T[i - 1] >= A) {
            T[i] = T[i] + A;
          } else {
            T[i] = T[i - 1] + A;
          }
          System.out.println(T[i]);
        }
    }
}