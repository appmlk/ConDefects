import java.util.HashSet;
import java.util.Scanner;

public class Main{


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] fireworks = new int[M];
        for (int i = 0; i < M; i++) {
            fireworks[i] = sc.nextInt();
        }
        HashSet<Integer> set = new HashSet<>();
        for (int num : fireworks) {
            set.add(num);
        }


        int[] result = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            if (set.contains(i))
                result[i] = i;
        }

        int store = N - 1;
        for (int i = N - 1; i >= 1; i--) {
            if (result[i] == 0) result[i] = store;
            else store = result[i];
        }

        for (int i = 1; i <= N; i++) {
            System.out.println(result[i] - i);
        }
    }
}
