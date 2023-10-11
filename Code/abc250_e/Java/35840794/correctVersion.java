import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        var map = new HashMap<Integer, Integer>();
        int nowmax = 0;
        int[] pre = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int now = in.nextInt();
            if (!map.containsKey(now)){
                nowmax++;
                map.put(now, nowmax);
                pre[i] = nowmax;
            } else {
                pre[i] = pre[i - 1];
            }
        }
        int[] bpre = new int[n + 1];
        int mx = 0, now = 0;
        var set = new HashSet<Integer>();
        for (int i = 1; i <= n; i++){
            int btemp = in.nextInt();
            if (!map.containsKey(btemp)){
                mx = (int) (2e9);
            } else {
                now = map.get(btemp);
                mx = (now > mx) ? now : mx;
                set.add(now);
                if (mx == set.size()) bpre[i] = mx;
            }
        }
        int k = in.nextInt();
        for (int i = 0; i < k; i++){
            int x = in.nextInt();
            int y = in.nextInt();
            System.out.println(pre[x] == bpre[y] ? "Yes" : "No");
        }
    }
}
