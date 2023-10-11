
import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {


    public static Reader in;
    public static PrintWriter out;

    public static void main(String[] args) {
        out = new PrintWriter(new BufferedOutputStream(System.out));
        in = new Reader();
        fk();
        out.close();
    }

    static class Reader {
        private BufferedReader br;
        private StringTokenizer st;

        Reader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            try {
                while (st == null || !st.hasMoreTokens()) {
                    st = new StringTokenizer(br.readLine());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        int[] nextIntArray(int n) {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextInt();
            }
            return arr;
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        String nextLine() {
            String s = "";
            try {
                s = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return s;
        }
    }

    static int mod = 998244353;

    static void fk() {
        int q = in.nextInt();
        long cur = 1;
        LinkedList<Long> dq = new LinkedList<>();
        dq.add(1L);
        for (int i = 0; i < q; i++) {
            int c = in.nextInt();
            if (c == 1) {
                long cv = in.nextLong();
                cur = (cur * 10 + cv) % mod;
                dq.add(cv);
            } else if (c == 2) {
                long fv = dq.pollFirst();
                cur = (cur - (fv * powWithMod(10, dq.size(), mod)) % mod + mod) % mod;
            } else {
                out.println(cur);
            }
        }


    }

    /**
     * 快速幂
     *
     * @param a
     * @param b
     * @param mod
     * @return
     */
    static long powWithMod(long a, long b, int mod) {
        if (b == 0) return 1;
        long res = powWithMod(a, b / 2, mod);
        if ((b & 1) == 0) {
            res = (res * res);
        } else {
            res = ((res * res) % mod) * a;
        }
        return res % mod;
    }

}
