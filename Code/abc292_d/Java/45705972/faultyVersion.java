import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static FastIO io = new FastIO();
    static int n, m;
    static int[] p;
    static long[] cnt, group;
    public static void main(String[] args) {
        n = io.nextInt();
        m = io.nextInt();
        p = new int[n + 1];
        group = new long[n + 1];
        cnt = new long[n + 1];
        Arrays.fill(group, 1);
        Arrays.fill(p, -1);
        for (int i = 0; i < m; i++) {
            int s = io.nextInt();
            int e = io.nextInt();
            cnt[e]++;
            union(s, e);
        }
        boolean ok = true;
        for (int i = 1; i <= n; i++) {
            if (p[i] == -1) {
                if (findCnt(i) != findGroup(i)) {
                    ok = false;
                    break;
                }
            }
        }
        if (!ok) System.out.println("No");
        else System.out.println("Yes");
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return;
        p[a] = b;
        cnt[b] += cnt[a];
        cnt[a] = 0;
        group[b] += group[a];
        group[a] = 0;
    }
    static long findGroup(int x) {
        return group[find(x)];
    }
    static long findCnt(int x) {
        return cnt[find(x)];
    }
    static int find(int x) {
        if (p[x] < 0) return x;
        return p[x] = find(p[x]);
    }
}
class FastIO {
    public BufferedReader br;
    public StringTokenizer st;
    FastIO() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }
    public String next() {
        while (st == null || !st.hasMoreTokens()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }
    public int nextInt() {
        return Integer.parseInt(next());
    }
    public long nextLong() {
        return Long.parseLong(next());
    }
}