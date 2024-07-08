import java.io.*;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        int n = rd.nextInt();
        int q = rd.nextInt();

        a = new long[n];
        b = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = rd.nextLong();
        }

        for (int i = 0; i < n; i++) {
            b[i] = rd.nextLong();
        }

        seg = build(0, n - 1);

        LinkedList<Long> ans = new LinkedList<>();
        for (int i = 0; i < q; i++) {
            int op = rd.nextInt();
            int l = rd.nextInt();
            int r = rd.nextInt();
            long x = 0;
            if (op != 3) {
                x = rd.nextLong();
            }
            l = l - 1;
            r = r - 1;
            if (op == 1) {
                seg.add(l, r, x, 0);
            }
            if (op == 2) {
                seg.add(l, r, 0, x);
            }
            if (op == 3) {
                ans.add(seg.query(l, r));
            }
        }

        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        for (long val : ans) {
            pw.println(val);
        }
        pw.flush();
    }

    static Seg seg;
    static long[] a;
    static long[] b;


    static class Seg {
        int l, r;
        Seg left, right;
        long sum;
        long aSum;
        long bSum;
        long pushx;
        long pushy;

        public Seg(int l, int r) {
            this.l = l;
            this.r = r;
            sum = 0;
            aSum = 0;
            bSum = 0;
            pushx = 0;
            pushy = 0;
        }

        public void add(int l, int r, long x, long y) {
            if (this.l > r || this.r < l) {
                return;
            }
            if (l <= this.l && this.r <= r) {
                update(x, y);
                return;
            }
            pushdown();
            // add sum
            this.left.add(l, r, x, y);
            this.right.add(l, r, x, y);
            pushup();
        }

        public void pushdown() {
            left.update(pushx, pushy);
            right.update(pushx, pushy);
        }

        public void pushup() {
            this.sum = this.left.sum + this.right.sum;
            this.sum %= mod;
            this.aSum = this.left.aSum + this.right.aSum;
            this.aSum %= mod;
            this.bSum = this.left.bSum + this.right.bSum;
            this.bSum %= mod;
        }

        public void update(long valx, long valy) {
            valx %= mod;
            valy %= mod;
            // 更新sum
            this.sum += (valx * bSum) % mod + (valy * aSum) % mod + (((valx * valy) % mod) * (r - l + 1)) % mod;
            this.sum %= mod;
            /// 再更新
            this.aSum += (valx * (this.r - this.l + 1)) % mod;
            this.bSum += (valy * (this.r - this.l + 1)) % mod;
            this.aSum %= mod;
            this.bSum %= mod;

            this.pushx += valx;
            this.pushy += valy;

            this.pushx %= mod;
            this.pushy %= mod;
        }

        public long query(int l, int r) {
            if (this.l > r || this.r < l) {
                return 0;
            }
            if (l <= this.l && this.r <= r) {
                return sum;
            }
            pushdown();
            long sum = left.query(l, r) + right.query(l, r);
            pushup();
            return sum % mod;
        }
    }

    static long mod = 998244353L;

    public static Seg build(int l, int r) {
        if (l == r) {
            Seg seg = new Seg(l, r);
            seg.sum = (a[l] % mod) * (b[l] % mod) % mod;
            seg.sum %= mod;
            seg.aSum = (a[l] % mod);
            seg.bSum = (b[l] % mod);
            return seg;
        }
        Seg left = build(l, (l + r) / 2);
        Seg right = build((l + r) / 2 + 1, r);

        Seg seg = new Seg(l, r);
        seg.left = left;
        seg.right = right;
        seg.pushup();
        return seg;
    }

    static class rd {
        static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        static StringTokenizer tokenizer = new StringTokenizer("");

        // nextLine()读取字符串
        static String nextLine() throws IOException {
            return reader.readLine();
        }

        // next()读取字符串
        static String next() throws IOException {
            while (!tokenizer.hasMoreTokens()) tokenizer = new StringTokenizer(reader.readLine());
            return tokenizer.nextToken();
        }

        // 读取一个int型数值
        static int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        // 读取一个double型数值
        static double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }

        // 读取一个long型数值
        static long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        // 读取一个BigInteger
        static BigInteger nextBigInteger() throws IOException {
            BigInteger d = new BigInteger(rd.nextLine());
            return d;
        }
    }
}
