import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        int T = sc.nextInt();

        sc.nextLine();

        int[] A = new int[N];
        for (int i = 1; i < N; i++) {
            A[i] = sc.nextInt();
        }

        sc.nextLine();
        Map bonusRoomMap = new HashMap();
        for (int i = 0; i < M; i++) {
            bonusRoomMap.put(sc.nextInt(), sc.nextInt());
            sc.nextLine();
        }

        for (int i = 1; i < N; i++) {
            T -= A[i];
            if (T <= 0) {
                System.out.println("No");
                return;
            }
            T += (int) bonusRoomMap.getOrDefault(i+1, 0);
        }
        System.out.println("Yes");
    }
}
