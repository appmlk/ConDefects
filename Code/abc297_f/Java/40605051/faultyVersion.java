
import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
class Comb {

    long[] p;
    long[] pi;
    long mod;

    public Comb(int n, long mod) {
        this.mod=mod;
        p=new long[n+1];
        pi=new long[n+1];
        p[0] = 1;
        pi[0] = 1;
        for (int i = 1; i <= n; i++) {
            p[i] = p[i - 1] * i % mod;
        }
        pi[n] = modinv(p[n], (int) mod);
        for (int i = n; i > 1; i--) {
            pi[i-1] = pi[i] * (long) i % mod;
        }
    }

    public long comb(int n, int r) {
        if (n < r) return 0;
        return p[n] * pi[r] % mod * pi[n - r] % mod;
    }

    public long perm(int n, int r) {
        if (n < r) return 0;
        return p[n] * pi[n - r] % mod;
    }

    long modinv(long a, long m) {
        long b = m;
        long u = 1;
        long v = 0;
        long tmp = 0;

        while (b > 0) {
            long t = a / b;
            a -= t * b;
            tmp = a;
            a = b;
            b = tmp;

            u -= t * v;
            tmp = u;
            u = v;
            v = tmp;
        }

        u %= m;
        if (u < 0) u += m;
        return u;
    }
}

public class Main {

    static long M=998244353;

    public void solve() throws Exception {
        int h=nextInt(),w=nextInt(),k=nextInt();
        long res=0;

        int hw = h * w;
        Comb c=new Comb(hw, M);

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                res+=c.comb(h*w,k);
                res-=c.comb(j*h,k);
                res-=c.comb((w-j-1)*h,k);
                res-=c.comb(i*w,k);
                res-=c.comb((h-i-1)*w,k);
                res+=c.comb(i*j,k);
                res+=c.comb(i*(w-j-1),k);
                res+=c.comb((h-i-1)*(w-j-1),k);
                res+=c.comb((h-i-1)*j,k);
//                res += M;
                res%=M;
            }
        }
        long modinv = c.modinv(c.comb(h*w,k), M);
        out.println(((res*modinv+M)%M));
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }

    static PrintWriter out = new PrintWriter(System.out, true);
    static InputReader in = new InputReader(System.in);
    static String next() { return in.next(); }
    static int nextInt() { return Integer.parseInt(in.next()); }
    static long nextLong() { return Long.parseLong(in.next()); }
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }
    }
}

