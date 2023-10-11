import java.util.Scanner;
public class Main {
    static int N, M;
    static long[] L;
    static boolean isPossible(long W) {
        int lines = 1;
        long line_width = 0;

        for (int i = 0; i < N; i++) {
            if (L[i] > W) {
                return false;
            }

            if (line_width + L[i] <= W) {
                line_width += L[i] + 1; 
            } else {
                lines++;
                line_width = L[i] + 1;
            }
        }

        return lines <= M;
    }
    static long binarySearch(long left, long right) {
        long result = -1;

        while (left <= right) {
            long mid = (left + right) / 2;

            if (isPossible(mid)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        M = scanner.nextInt();
        L = new long[N];
        for (int i = 0; i < N; i++) {
            L[i] = scanner.nextLong();
        }
        
    }
}
