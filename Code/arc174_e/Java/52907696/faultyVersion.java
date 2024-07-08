import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    int mod = 998244353;
    long[] fac;
    public long[] existenceCounting(int n, int k, List<Integer> sequence) {
        fac = new long[n + 1];
        fac[0] = fac[1] = 1L;
        for (int i = 1; i < fac.length; i++) {
            fac[i] = fac[i - 1] * i % mod;
        }
        long[] ans = new long[n];

        List<Long> l1 = new ArrayList<>();

        long[] b1 = new long[n + 1];
        long[] b2 = new long[n + 2];
        boolean[] visited = new boolean[n];
        for (int i = 0; i < sequence.size(); i++) {
            int sup = sequence.get(i);
            visited[sup - 1] = true;
            l1.add((sup - 1) * a(n - i - 1, k - i - 1));
            long cnt = sup - 1 - get(b1, sup - 1);
            long v1 = a(n - i - 1, k - i - 1) + ((cnt - 1) * (k - i - 1) % mod) * a(n - i - 2, k - i - 2);
            v1 %= mod;
            set(b2, 1, v1);
            set(b2, sup, -v1);
            long v2 = ((k - i - 1) * cnt % mod) * a(n - i - 2, k - i - 2);
            v2 %= mod;
            set(b2, sup, v2);
            set(b2, n + 1, -v2);
            ans[sup - 1] = (ans[sup - 1] + get(b2, sup)) % mod;
            set(b1, sup, 1);
        }
        long c = 1L;
        for (int i = l1.size() - 1; i >= 0; --i) {
            ans[sequence.get(i) - 1]= (ans[sequence.get(i) - 1] + c) % mod;
            c =  (c + l1.get(i)) % mod;
        }

        for (int i = 0; i < n; ++i) {
            if (!visited[i]) ans[i] = get(b2, i + 1);
        }

        return ans;
    }

    public void set(long[] bit, int index, long v) {
        while (index < bit.length) {
            bit[index]+=v;
            index += low(index);
        }
    }
    public long get(long[] bit, int index) {
        long ans = 0L;
        while (index > 0) {
            ans += bit[index];
            ans %= mod;
            index -= low(index);
        }
        return ans;
    }

    public int low(int x) {
        return x & (-x);
    }


    public long a(int m, int n) {
        if (n < 0) return 0;
        if (n == 0) return 1;
        if (n == 1) return m;
        long ans = fac[m];
        long a = fac[m - n];
        long b = mod - 2;
        while (b > 0) {
            if (b % 2 == 1) {
                ans *= a;
                ans %= mod;
            }
            b /= 2;
            a *= a;
            a %= mod;
        }
        return ans;
    }



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int K = scanner.nextInt();
        List<Integer> seq = new ArrayList<>();
        for (int i = 0; i < K; i++) seq.add(scanner.nextInt());
        scanner.close();
        for (long v : new Main().existenceCounting(N, K, seq)) {
            System.out.println(v);
        }
    }
}
