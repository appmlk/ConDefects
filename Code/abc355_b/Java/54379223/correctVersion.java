import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        new Main(sc);
    }

    Integer n;
    Integer m;
    Map<Integer, String> p = new TreeMap<Integer, String>();

    public Main(Scanner sc) {
        // 入力
        n = sc.nextInt();
        m = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            p.put(sc.nextInt(), "a");
        }
        sc.nextLine();
        for (int i = 0; i < m; i++) {
            p.put(sc.nextInt(), "b");
        }

        // System.out.println(p);

        String pre = "";
        for (String pi : p.values()) {
            if (pre.equals("a") && pi.equals("a")) {
                System.out.println("Yes");
                return;
            }
            pre = pi;
        }

        System.out.println("No");

    }

}
