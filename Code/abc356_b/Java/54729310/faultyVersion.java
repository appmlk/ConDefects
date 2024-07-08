import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        int[] A = new int[M];
        int[][] X = new int[N][M];

        for (int i = 0; i < M; i++) {
            A[i] = sc.nextInt();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                X[i][j] = sc.nextInt();
            }
        }
        sc.close();

        int[] total = new int[M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                X[i][j] += total[j];
            }
        }

        boolean result = true;

        for (int i = 0; i < M; i++){
            if(A[i] > total[i]){
                result = false;
                break;
            }
        }
        System.out.println(result ? "Yes" : "No");
    }
}
