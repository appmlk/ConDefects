import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int N = sc.nextInt();
            int[] NBox = new int[N];

            for (int i = 0; i < N; i++) {
                NBox[i] = sc.nextInt();
            }

            int H1 = NBox[0];
            int H = -1;
            for (int i = 1; i < N; i++) {
                if (H1 > NBox[i]) {
                    H = i + 1;
                    break;
                }
            }
            System.out.println(H);
        }
    }
}