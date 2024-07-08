import java.util.Scanner;
 
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int N = in.nextInt();
            int M = in.nextInt();
            int[] nums = new int[M];
            int[] A = new int[M];
            for (int i = 0; i < M; i++) {
                A[i] = in.nextInt();
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    nums[j] += in.nextInt();
                }
            }
            for (int i = 0; i < M; i++) {
                if (nums[i] < A[i]) {
                    System.out.println("No");
                    return;
                }
            }
            System.out.println("Yes");
        }
        // System.out.println("Hello LeetCoder");
        
    }
}