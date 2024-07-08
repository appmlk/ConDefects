import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Long> A = new ArrayList<>();
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            A.add(sc.nextLong());
        }
        sc.close();

        List<Long> up = new ArrayList<>();
        List<Long> down = new ArrayList<>();

        Long pre = 0L;
        for (int i = 0; i < n; i++) {
            if (A.get(i) > pre) {
                ++pre;
                up.add(pre);
            } else {
                pre = A.get(i);
                up.add(pre);
            }
        }
        pre = 0L;
        for (int i = n - 1; i >= 0; i--) {
            if (A.get(i) > pre) {
                ++pre;
                down.add(pre);
            } else {
                pre = A.get(i);
                down.add(pre);
            }
        }
        long ans = 0L;
        for (int i = 0; i < n; i++) {
            long min = (up.get(i) - down.get(n - i - 1)) >= 0 ? down.get(n - i - 1) : up.get(i);
            if (ans < min) {
                ans = min;
            }
        }
        System.out.print(ans);
    }
}