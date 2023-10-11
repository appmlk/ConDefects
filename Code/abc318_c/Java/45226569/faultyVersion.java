import java.util.*;

public class Main {
    public static void main(String[] args) {

        IOHandler io = new IOHandler();
        int n = io.nextInt();
        int d = io.nextInt();
        int p = io.nextInt();
        int[] f = io.nextIntArray(n);
        io.close();

        /*
         * 方針
         * (1)パスを購入しない場合
         *    ⌈N/D⌉*P (⌈x⌉は x以上の最小の整数)
         * (2)パスを購入する場合
         *   パスを何回買うか試す、パスは運賃の高い日順に割り当てる
         */

        // (1)
        int k = (n+d-1)/d;
        long result = p*k;

        // (2)
        Arrays.sort(f);
        // 事前に累積和を求める(O(n))
        long[] cumulativeSumOfF = new long[n+1];
        for (int i = 0; i < n; i++) cumulativeSumOfF[i+1] = cumulativeSumOfF[i] + f[i];

        // 適当な回数ループして、運賃の高いr日間にパスを割り当てる
        for (int i = 0; i < k; i++)  {
            int r = Math.max(0, n-i*d);

            result = Math.min(result, cumulativeSumOfF[r] + (long)p*i);
            if (r == 0) break;
        }

        io.output(result);
    }

    private static class IOHandler {
        private Scanner sc = new Scanner(System.in);
        private void close() {this.sc.close();}
        private int nextInt() {return this.sc.nextInt();}
        private int[] nextIntArray(int size) {
            int[] array = new int[size];
            for (int i = 0; i < size; i++) array[i] = this.sc.nextInt();
            return array;
        }
        private void output(long result) {System.out.println(result);}
    }
}