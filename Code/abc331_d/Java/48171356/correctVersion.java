import java.util.Scanner;

public class Main implements Runnable {

    public static void main(String[] args) {
        new Thread(null, new Main(), "", Runtime.getRuntime().maxMemory()).start();
    }

    public long count(int r, int c) {
        r--;
        c--;
        long ret = (long) (r / N) * (c / N) * sums[N][N];
        ret += (long) (r / N) * sums[N][c % N + 1];
        ret += (long) (c / N) * sums[r % N + 1][N];
        ret += sums[r % N + 1][c % N + 1];
        return ret;
    }

    static int[][] sums;
    static int N;

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int Q = sc.nextInt();
        char[][] table = new char[N][N];
        for (int i = 0; i < N; i++) {
            String S = sc.next();
            table[i] = S.toCharArray();
        }

        sums = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                sums[i][j] = sums[i][j - 1] + (table[i - 1][j - 1] == 'B' ? 1 : 0);
            }
        }
        for (int j = 1; j <= N; j++) {
            for (int i = 1; i <= N; i++) {
                sums[i][j] += sums[i - 1][j];
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            int A = sc.nextInt();
            int B = sc.nextInt();
            int C = sc.nextInt();
            int D = sc.nextInt();
            long ans = count(C + 1, D + 1);
            ans -= count(A, D + 1);
            ans -= count(C + 1, B);
            ans += count(A, B);
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
}
