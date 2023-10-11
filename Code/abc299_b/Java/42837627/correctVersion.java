import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.next());
        int t = Integer.parseInt(sc.next());
        int[] c = new int[n];
        int[] r = new int[n];
        boolean flag = false;

        for (int i = 0; i < n; i++) {
            c[i] = Integer.parseInt(sc.next());
            if (c[i] == t)
                flag = true;
        }
        for (int i = 0; i < n; i++) {
            r[i] = Integer.parseInt(sc.next());
        }
        sc.close();

        if (flag) {
            int max = 0;
            int result = 0;

            for (int i = 0; i < n; i++) {
                if (c[i] == t && max < r[i]) {
                    result = i + 1;
                    max = r[i];
                }
            }
            System.out.println(result);
        } else {
            int false_max = r[0];
            int faluse_result = 1;
            for (int i = 0; i < n; i++) {
                if (c[0] == c[i] && false_max < r[i]) {
                    faluse_result = i + 1;
                    false_max = r[i];
                }
            }
            System.out.println(faluse_result);
        }

    }
}