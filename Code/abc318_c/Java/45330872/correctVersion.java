import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TrainTravel trainTravel = new TrainTravel(sc);
        sc.close();
        System.out.println(trainTravel.getMinTotalCost());
    }
}

class TrainTravel {
    private int N, D, P;
    private int[] F;

    TrainTravel(Scanner sc) {
        N = Integer.parseInt(sc.next());
        D = Integer.parseInt(sc.next());
        P = Integer.parseInt(sc.next());
        F = new int[N];
        for (int i = 0; i < N; i++)
            F[i] = Integer.parseInt(sc.next());
    }

    long getMinTotalCost() {
        Arrays.sort(F);
        long min = Long.MAX_VALUE;
        long[] sum = new long[N + 1];
        sum[0] = 0;
        for (int i = 0; i < N; i++) {
            sum[i + 1] = sum[i] + F[i];
        }
        for (int k = 0; k * D <= N; k++) {
            long tmp = (long) k * P + sum[N - k * D];
            min = (min > tmp) ? tmp : min;
        }
        min = (min < (long) (N / D + 1) * P) ? min : ((long) (N / D + 1) * P);

        return min;
    }
}