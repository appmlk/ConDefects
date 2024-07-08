import java.util.*;

public class Main {
    private static Scanner in;

    public static void solve() {
        // int n = in.nextInt();
        // String s = in.next();
        // long m = in.nextLong();

        int H = in.nextInt();
        int height = 1;
        int growth = 2;

        for (int i = 0; i < H+1; i++)
        {
            
            if (height > H)
            {
                System.out.print(i+1);
                return;
            }
            height = height + growth;
            growth = growth*2;
            
            
        }
       
    }

    public static void main(String[] args) {
        in = new Scanner(System.in);
        solve();
        in.close();
    }
}