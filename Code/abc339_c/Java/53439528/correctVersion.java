import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author YC
 * @version 1.0
 */
public class Main {
    static int N = (int) (2e5 + 10);
    static long[] sum = new long[N];
    static long u = 0;
    static int n;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        String[] strs = br.readLine().split(" ");
        for(int i = 1; i <= n ; i ++) {
            sum[i] = sum[i - 1] + Integer.parseInt(strs[i - 1]);
            if(sum[i] < 0) {
                u = Math.max(u, -sum[i]);
            }
        }

        System.out.println(u + sum[n]);
    }
}
