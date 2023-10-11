import java.io.*;
import java.util.*;

public class Main {

    static Scanner sc;
    static PrintWriter out;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        out = new PrintWriter(System.out);
        new Main().solve();
        out.flush();
    }

    public void solve() {
        int n = sc.nextInt();
        int[][] d = new int[n][2];
        boolean notd1 = false;
        for(int i=2; i<n; i++) {
            for(int j=0; j<2; j++) {
                out.printf("? %d %d\n", j + 1, i + 1);
                out.flush();
                d[i][j] = sc.nextInt();
            }
            if(Math.abs(d[i][0]-d[i][1]) != 1) notd1 = true;
        }
        if(!notd1) {
            if(n == 4) {
                out.println("? 3 4");
                out.flush();
                int d34 = sc.nextInt();
                if(d34 == 1) {
                    out.println("! 3");
                    return;
                }
            }
            out.println("! 1");
            return;
        }
        int res = n-1;
        for(int i=2; i<n; i++) {
            res = Math.min(res, d[i][0]+d[i][1]);
        }
        out.println("! " + res);

    }

}
