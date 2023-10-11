
import java.util.*;

/**
 * @author zjz
 * 2023/8/23
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long[] A = new long[n];
        long[] B = new long[n];
        long sum = 0;
        Arrays.sort(A);
        for (int i = 0; i < n; i++) {
            A[i] = in.nextInt();
            sum += A[i];
        }
        Arrays.fill(B, sum / n);
        for (int i = 0; i < sum % n; i++) {
            B[n - 1 - i]++;
        }
        long res = 0;
        for (int i = 0; i < n; i++) {
            res += Math.abs(A[i] - B[i]);
        }
        System.out.println(res / 2);
    }

}

class Pair {
    public int first;
    public char second;

    public Pair() {
    }

    public Pair(int first, char second) {
        this.first = first;
        this.second = second;
    }
}
