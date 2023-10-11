import java.util.*;
import java.io.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.util.Collections.*;
import static java.util.stream.Collectors.toList;
public class Main {
    static final long INF = 1l << 60;
    static final int inf = 1 << 30;
    static final int MOD = 998244353;
    //static final int MOD = 1000000007;
    static final double EPS = 1e-9;
    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        PrintWriter pw = new PrintWriter(System.out);
        int n = sc.nextInt();
        int _a = sc.nextInt();
        int _b = sc.nextInt();
        int[] a = new int[n];
        int l = inf, r = 0;
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            l = min(l, a[i]);
            r = max(r, a[i]);
        }
        while (r-l > 1) {
            int mid = (l+r)/2;
            int cntMinus = 0, cntPlus = 0;
            for (int i = 0; i < n; i++) {
                if (a[i] >= mid) cntPlus += (a[i]-mid)/_b;
                else cntMinus += ceilDivide(mid-a[i], _a);
            }
            if (cntPlus >= cntMinus) l = mid;
            else r = mid;
        }
        pw.println(l);
        pw.close();
    }
    static int ceilDivide(int a, int b) { return a <= 0 ? 0 : (a-1)/b + 1; }
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
