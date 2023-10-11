import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);
        long d = Long.parseLong(str[2]);
        long[] a = new long[n];
        long[] b = new long[m];
        str = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            a[i] = Long.parseLong(str[i]);
        }
        str = br.readLine().split(" ");
        for (int i = 0; i < m; i++) {
            b[i] = Long.parseLong(str[i]);
        }
        Arrays.sort(a);
        long ans = 0l;
        for (int i = 0; i < m; i++) {
            int aindex = Arrays.binarySearch(a, b[i] + d);
            if (aindex >= 0) {
                ans = Math.max(ans, a[aindex] + b[i]);
            } else if (aindex < -1) {
                if (Math.abs(b[i] - a[aindex * (-1) - 2]) <= d) {
                    ans = Math.max(ans, a[aindex * (-1) - 2] + b[i]);
                }
            } else {
            }
        }
        if (ans == 0) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }
    }
}
