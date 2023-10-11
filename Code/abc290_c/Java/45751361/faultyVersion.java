import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(sc.nextInt());
        }int ans = k;
        for (int j = 0; j < n; j++) {
            if (!set.contains(j)) {
                ans = j;
                break;
            }
        }
            System.out.println(ans);
    }
}