import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] buill = new int[N];

        for (int i = 0; i < N; i++) {
            buill[i] = sc.nextInt();
        }
        sc.close();

        int standard = buill[0];
        boolean found = false;

        for (int i = 1; i < buill.length; i++) {
            if (standard < buill[i]) {
                System.out.println(i + 1);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println(-1);
        }
    }
}