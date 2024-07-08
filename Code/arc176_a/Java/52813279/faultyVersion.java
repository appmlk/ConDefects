import java.util.*;
public class Main {
   public static void main(String[] args) throws Exception {
        // 看过答案
        // https://atcoder.jp/contests/arc176/submissions/52668226
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] a = new int[m];
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
            a[i] = scanner.nextInt() - 1;
            b[i] = scanner.nextInt() - 1;
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            set.add((b[i] - a[i] + n) % n);
        }
        for (int i = n; i >= 0; i--) {
            if (set.size() == m) {
                break;
            }
            if (!set.contains(i)) {
                set.add(i);
            }
        }

        System.out.println(n * m);
        for (int p : set) {
            for (int i = 0; i < n; i++) {
                System.out.println((i + 1) + " " + ((i + p) % n + 1));
            }
        }
    }

}