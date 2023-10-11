import java.util.*;
public class Main{
    static void bfs(long a, long b) {
        Deque<long[]> dq = new ArrayDeque<>();
        dq.add(new long[]{a, b});
        while (!dq.isEmpty()) {
            int size = dq.size();
            while (size-- > 0) {
                long[] poll = dq.poll();
                if (gcd(poll[0], poll[1]) == 1) {
                    System.out.println(Math.abs(poll[1] - poll[0]));
                    return;
                }
                dq.add(new long[]{poll[0] + 1, poll[1]});
                dq.add(new long[]{poll[0], poll[1] - 1});
            }
        }
    }
    static long gcd(long a, long b) {
        return a == 0 ? b : gcd(b % a, a);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong(), b = sc.nextLong();
        bfs(a, b);
    }
}