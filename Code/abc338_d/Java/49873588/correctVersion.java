import java.io.PrintWriter;
import java.util.Scanner;

class Main {
    static PrintWriter out;
    public static void main(String[] args) {
        out = new PrintWriter(System.out);
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] X = new int[M+1];
        for (int i = 1; i <= M; i++) {
            X[i] = sc.nextInt();
        }

        long[] cost = new long[N+1];
        //累積和準備
        for (int i = 1; i < M; i++) {
            int left;
            int right;
            if (X[i] >= X[i+1]){
                left = X[i] - X[i+1];
                right = N - X[i] + X[i+1];
            }else {
                left = X[i] + N - X[i+1];
                right = X[i+1] - X[i];
            }
            cost[X[i]] += right - left;
            cost[X[i+1]] -= right - left;
        }
        //imos
        for (int i = 1; i < N; i++) {
            cost[i+1] += cost[i];
        }
        cost[1] += cost[N];

        int delete = 1;
        long dc = cost[1];
        for (int i = 2; i <= N; i++) {
            if (dc < cost[i]){
                delete = i;
                dc = cost[i];
            }
        }
        long answer = 0;
        for (int i = 1; i < M; i++) {
            if (X[i] >= X[i+1]){
                if (X[i+1] <= delete && delete < X[i]){
                    answer += N - X[i] + X[i+1];
                }else {
                    answer += X[i] - X[i+1];
                }
            }else {
                if (X[i] <= delete && delete < X[i+1]){
                    answer += X[i] + N - X[i+1];
                }else {
                    answer += X[i+1] - X[i];
                }
            }
        }
        System.out.println(answer);
    }
}