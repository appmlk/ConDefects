
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = (int) 1e6;
        long[] mi = new long[n];//mi[i] = 10 ^ i
        int mod = 998244353;
        mi[0] = 1;
        for (int i = 1; i < n; i++) {
            mi[i] = mi[i - 1] * 10;
            mi[i] %= mod;
        }
        long ans = 1;
        Deque<Integer> deque = new LinkedList<>();
        deque.push(1);

        int q = sc.nextInt();
        for (int i = 0; i < q; i++) {
            int t = sc.nextInt();
            if (t == 1) {//末尾添加一个数
                int d = sc.nextInt();
                deque.push(d);
                ans = ans * 10 + d;
                ans %= mod;
            } else if (t == 2) {//开头删除一个数
                int size = deque.size();
                int d = deque.pollLast();
                ans -= d * mi[size - 1];
                ans += mod;
                ans %= mod;
            } else {//输出当前数
                System.out.println((ans + mod) % mod);
            }
        }
    }
}
