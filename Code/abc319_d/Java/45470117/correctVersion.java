
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static FastReader sc;

    public static void main(String[] args) throws IOException {

        sc = new FastReader();
        int n = sc.nextInt();
        int m = sc.nextInt();
        long[] L = new long[n];
        long total = 0;
        for (int i=0;i<n;i++) {
            L[i] = sc.nextLong();
            total += L[i];
        }
        long ans = 0;
        long ng = 0; // ありうる値-1
        long ok = total + n; // ありうる値+1
        while (Math.abs(ok - ng) > 1) {
            long mid = (ok + ng) / 2;
            if (check(L, mid, m)) {
                ok = mid;
            } else {
                ng = mid;
            }
        }
        System.out.println(ok);

    }
    static boolean check(long[] a, long mid, long m) {

        int cnt = 0;
        long tmp = 0;
        int i = 0;
        while (true) {
            if (a[i] > mid) {
                return false;
            }
            if (i > 0 && tmp + a[i] >= mid) {
                if (tmp + a[i] == mid) {
                    i++;
                    if (i > a.length -1) {
                        cnt++;
                        break;
                    }
                }
                tmp = a[i] + 1;
                cnt++;
            } else {
                    tmp += a[i] + 1;
            }
            i++;
            if (i > a.length -1) {
                cnt++;
                break;
            }
        }
        return cnt <= m;

    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        int[] readIntArray(int n) {
            int[] res = new int[n];
            for (int i = 0; i < n; i++)
                res[i] = nextInt();
            return res;
        }

        long[] readLongArray(int n) {
            long[] res = new long[n];
            for (int i = 0; i < n; i++)
                res[i] = nextLong();
            return res;
        }
    }

}
