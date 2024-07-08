import java.util.*;

public class Main {
    private static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        solve();
    }
    private static TreeMap<Integer, Integer> map;
    private static void solve() {
        int n = in.nextInt(), m = in.nextInt();
        map = new TreeMap<>();
        for(int i = 0; i < n; i++) {
            int num = in.nextInt();
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int res = 0;
        int[] b = new int[m];
        for(int i = 0; i < m; i++) b[i] = in.nextInt();
        Arrays.sort(b);
        for(int i = 0; i < m; i++) {
            int need = b[i];
            Integer num = map.ceilingKey(need);
            if(num == null) {
                System.out.println(-1);
                return;
            }
            res += num;
            if(map.get(num) == 1) map.remove(num);
            else map.put(num, map.get(num) - 1);
        }
        System.out.println(res);
    }
}
