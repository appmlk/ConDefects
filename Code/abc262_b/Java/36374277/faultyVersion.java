import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        sc.nextLine();

        boolean[][] data = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int k = 0; k < N; k++) {
                data[i][k] = false;
            }
        }

        for (int i = 0; i < M; i++) {
            int U = sc.nextInt();
            int V = sc.nextInt();
            data[U - 1][V - 1] = true;
            data[V - 1][U - 1] = true;
            sc.nextLine();
        }
        sc.close();

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int k = i; k < N; k++) {
                if (data[i][k]) {
                    for (int n = k; n < N; n++) {
                        if (data[k][n]) {
                            count += 1;
                        }
                    }
                }
            }
        }
        System.out.println(count);
    }
}