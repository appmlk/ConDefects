import java.util.*;

public class Main {
    private static Scanner in;

    public static void solve() {
        // int n = in.nextInt();
        // String s = in.next();
        // long m = in.nextLong();

        int Y = in.nextInt();
        for (int x = Y; x <=3000; x++)
        {
            if (x % 4 == 2)
            {
                System.out.println(x);
                return;
            }
        }
    }

    public static void main(String[] args) {
        in = new Scanner(System.in);
        solve();
        in.close();
    }
}