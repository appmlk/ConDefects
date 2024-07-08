import java.util.*;
import java.io.*;

public class Main {
    private static final int MOD = 998244353;
    public static void main(String[] args) throws IOException{
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(r.readLine());
        int q = Integer.parseInt(st.nextToken()); int k = Integer.parseInt(st.nextToken());
        int[] sums = new int[k + 1]; sums[0] = 1;
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(r.readLine());
            char c = st.nextToken().charAt(0); int change = Integer.parseInt(st.nextToken());
            if(c == '-'){
                for (int j = change; j <= k; j++) {
                    if(sums[j - change] > 0){
                        sums[j] = (sums[j] - sums[j - change])%MOD;
                    }
                }
            }
            else{
                for (int j = k; j >= change; j--) {
                    sums[j] = (sums[j] + sums[j - change])%MOD;
                }
            }
            pw.println(sums[k]);
        }
        pw.close();
    }
}
