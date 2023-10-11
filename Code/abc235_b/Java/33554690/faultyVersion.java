import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int N = sc.nextInt();
        int[] H = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            H[i] = sc.nextInt();
        }
        int now_idx = 1;
        int max_height=0;
      	int next_idx=0;
        while (now_idx!=N) {
            next_idx = now_idx + 1;
            if (H[now_idx] >= H[next_idx]) {
                break;
            } else {
                max_height = H[next_idx];
            }
            now_idx = next_idx;
        }
        System.out.println(max_height);
    }
}