import java.util.*;
import java.io.*;
public class Main {
    static final long INF = 1l << 62;
    static final int inf = 1 << 30;
    static final int MOD = 998244353;
    static final double EPS = 1e-9;
    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        PrintWriter pw = new PrintWriter(System.out);
        solve(sc, pw);
        pw.close();
    }
    static void solve(FastScanner sc, PrintWriter pw) {
        int t = sc.nextInt();
        int[] dat = new int[510];
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i < 510; i++) {
            dat[i] = i*(i+1);
            set.add(dat[i]);
        }
        for (int test = 0; test < t; test++) {
            int n = sc.nextInt();
            if (n == 1) {
                pw.println("Yes");
                pw.println(1); continue;
            }
            if (n == 2) {
                pw.println("No");
                continue;
            }

            pw.println("Yes");
            if (set.contains(n)) {
                for (int i = 1; i < n-1; i++) pw.print(2*dat[i] + " ");
                pw.println(2 + " " + (2*(n-1)));
            } else {
                for (int i = 1; i < n; i++) pw.print(dat[i] + " ");
                pw.println(n);
            }
        }
    }
}
class FastScanner {
    private final InputStream in = System.in;
    private final byte[] buffer = new byte[1024];
    private int ptr = 0;
    private int buflen = 0;
    private boolean hasNextByte() {
        if (ptr < buflen) {
            return true;
        } else {
            ptr = 0;
            try {
                buflen = in.read(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (buflen <= 0) {
                return false;
            }
        }
        return true;
    }
    private int readByte() { if (hasNextByte()) return buffer[ptr++]; else return -1;}
    private static boolean isPrintableChar(int c) { return 33 <= c && c <= 126;}
    public boolean hasNext() { while (hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++; return hasNextByte();}
    public String next() {
        if (!hasNext()) throw new NoSuchElementException();
        StringBuilder sb = new StringBuilder();
        int b = readByte();
        while(isPrintableChar(b)) {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }
    public long nextLong() {
        if (!hasNext()) throw new NoSuchElementException();
        long n = 0;
        boolean minus = false;
        int b = readByte();
        if (b == '-') {
            minus = true;
            b = readByte();
        }
        if (b < '0' || '9' < b) {
            throw new NumberFormatException();
        }
        while (true) {
            if ('0' <= b && b <= '9') {
                n *= 10;
                n += b - '0';
            } else if (b == -1 || !isPrintableChar(b)) {
                return minus ? -n : n;
            } else {
                throw new NumberFormatException();
            }
            b = readByte();
        }
    }
    public int nextInt() {
        long nl = nextLong();
        if (nl < Integer.MIN_VALUE || nl > Integer.MAX_VALUE) throw new NumberFormatException();
        return (int) nl;
    }
    public double nextDouble() { return Double.parseDouble(next());}
}
