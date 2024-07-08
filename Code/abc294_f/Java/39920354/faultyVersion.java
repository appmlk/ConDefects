import java.io.*;
import java.util.*;

public class Main {
    void go() {
        int m1 = nextInt();
        int m2 = nextInt();
        long K = nextLong();
        long[][] c1 = new long[m1][2];
        c2 = new long[m2][2];
        for (int i = 0; i < m1; ++i) {
            c1[i][0] = nextLong();
            c1[i][1] = nextLong() + c1[i][0];
        }
        for (int i = 0; i < m2; ++i) {
            c2[i][0] = nextLong();
            c2[i][1] = nextLong() + c2[i][0];
        }
        double l = 0, r = 1, eps = 1e-10;
        while (l + eps < r) {
            double mid = (l + r) / 2;
            //ruffle(c2);
            Arrays.sort(c2, (x, y) -> Double.compare(x[0] - mid * x[1], y[0] - mid * y[1]));
            long ge = 0;
            for (int i = 0; i < m1; ++i) {
                ge += more(mid, c1[i][0], c1[i][1]);
            }
            if (ge < K) r = mid;
            else l = mid;
        }
        sl(l * 100);
    }

    long[][] c2;

    long more(double tar, long x, long y) {
        int l = 0, r = c2.length - 1;
        int m2 = c2.length;
        if ((x + c2[0][0]) * 1.0 / (y + c2[0][1]) >= tar) return c2.length;
        if ((x + c2[m2 - 1][0]) * 1.0 / (y + c2[m2 - 1][1]) < tar) return 0;
        while (l + 1 < r) {
            int mid = (l + r) / 2;
            if ((x + c2[mid][0]) * 1.0 / (y + c2[mid][1]) < tar) l = mid;
            else r = mid;
        }
        return m2 - r;
    }

    /**
     * NOTE: Ignore following codes.
     */
    void goMultiple() {
        int T = nextInt();
        for (int i = 0; i < T; ++i) {
            go();
        }
    }

    /**
     * Toolkit.
     */
    void ruffleSort(int[] a) {
        Random random = new Random();
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int x = random.nextInt(n);
            int temp = a[x];
            a[x] = a[i];
            a[i] = temp;
        }
        Arrays.sort(a);
    }

    void ruffle(long[][] a) {
        Random random = new Random();
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int x = random.nextInt(n);
            long[] temp = a[x];
            a[x] = a[i];
            a[i] = temp;
        }
    }

    /**
     * Input.
     */
    InputStream inStream;
    byte[] inBuff = new byte[1024];
    int inBuffCursor = 0;
    int inBuffLen = 0;

    boolean isVisibleChar(int c) {
        return 33 <= c && c <= 126;
    }

    boolean hasNextByte() {
        if (inBuffCursor < inBuffLen) return true;
        inBuffCursor = 0;
        try {
            inBuffLen = inStream.read(inBuff);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inBuffLen > 0;
    }

    boolean hasNext() {
        while (hasNextByte() && !isVisibleChar(inBuff[inBuffCursor])) inBuffCursor++;
        return hasNextByte();
    }

    int nextByte() {
        return hasNextByte() ? inBuff[inBuffCursor++] : -1;
    }

    String next() {
        if (!hasNext()) throw new RuntimeException("no next.");
        StringBuilder sb = new StringBuilder();
        int b = nextByte();
        while (isVisibleChar(b)) {
            sb.appendCodePoint(b);
            b = nextByte();
        }
        return sb.toString();
    }

    long nextLong() {
        if (!hasNext()) throw new RuntimeException("no next.");
        long result = 0;
        boolean negative = false;
        int b = nextByte();
        if (b < '0') {
            if (b == '-') negative = true;
            else if (b != '+') throw new RuntimeException("long number must start with +/-.");
            b = nextByte();
        }
        while (isVisibleChar(b)) {
            if (b < '0' || b > '9') throw new RuntimeException("wrong digit in long:" + (char) b);
            // TODO: may overflow here, even if it is a valid Long, eg.-(1<<63).
            result = result * 10 + (b - '0');
            b = nextByte();
        }
        return negative ? -result : result;
    }

    int nextInt() {
        long x = nextLong();
        if (x < Integer.MIN_VALUE || x > Integer.MAX_VALUE)
            throw new RuntimeException("int overflow:" + x);
        return (int) x;
    }

    double nextDouble() {
        return Double.parseDouble(next());
    }


    /**
     * Output.
     */
    PrintWriter printOut = new PrintWriter(System.out);

    void so(Object obj) {
        printOut.print(obj);
    }

    void sl(Object obj) {
        printOut.println(obj);
    }

    void sl() {
        printOut.println();
    }

    /**
     * Main.
     */
    void mainGo(boolean isMultiple) {
        try {
            inStream = new FileInputStream("src/main.in");
        } catch (Exception e) {
            inStream = System.in;
        }
        while (hasNext()) {
            if (isMultiple)
                goMultiple();
            else
                go();
        }
        printOut.flush();
    }

    public static void main(String[] args) throws Exception {
        new Main().mainGo(false);
    }
}