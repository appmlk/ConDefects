import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;


public class Main {

    public static void main(String[] args) throws Exception {
        PrintWriter out = new PrintWriter(System.out);
        InputReader in = new InputReader(System.in);
        Task solver = new Task();
        solver.solve(in, out);
        out.flush();
        out.close();
    }
}

class Task {

    static final long mod = 998244353;

    long powmod(long a, long b) {
        long ans = 1, d = a;
        while (b > 0) {
            if (b % 2 == 1) ans = ans * d % mod;
            b >>= 1;
            d = d * d % mod;
        }
        return ans;
    }

    public void solve(InputReader in, PrintWriter out) throws Exception {
//        int T = in.nextInt();
        int T = 1;
        for (int i = 0; i < T; i++) {
            solveOne(in, out);
        }

    }

    int[] p;
    long[][][] dp;

    private void solveOne(InputReader in, PrintWriter out) {
        int n = in.nextInt();
        p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = in.nextInt();
        }
        dp = new long[2][n + 1][n];
        out.println(dfs(0, 0, n - 1));
    }

    private long dfs(int c, int i, int j) {
        if (dp[c][i][j] != 0) {
            return dp[c][i][j];
        }
        if (i >= j) {
            return dp[c][i][j] = 1;
        }
        if (c == 0) {
            return dp[c][i][j] = dfs(1, i + 1, j);
        }
        long ans = 0;
        for (int k = i; k <= j; k++) {
            if (k == j || p[k + 1] >= p[i]) {
                ans += dfs(0, i, k) * dfs(1, k + 1, j);
                ans %= mod;
            }
        }
        return dp[c][i][j] = ans;
    }

}

class InputReader {
    private InputStream in;
    private byte[] buffer = new byte[1024];
    private int curbuf;
    private int lenbuf;

    public InputReader(InputStream in) {
        this.in = in;
        this.curbuf = this.lenbuf = 0;
    }

    public boolean hasNextByte() {
        if (curbuf >= lenbuf) {
            curbuf = 0;
            try {
                lenbuf = in.read(buffer);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (lenbuf <= 0)
                return false;
        }
        return true;
    }

    private int readByte() {
        if (hasNextByte())
            return buffer[curbuf++];
        else
            return -1;
    }

    private boolean isSpaceChar(int c) {
        return !(c >= 33 && c <= 126);
    }

    private void skip() {
        while (hasNextByte() && isSpaceChar(buffer[curbuf]))
            curbuf++;
    }

    public boolean hasNext() {
        skip();
        return hasNextByte();
    }

    public String next() {
        if (!hasNext())
            throw new NoSuchElementException();
        StringBuilder sb = new StringBuilder();
        int b = readByte();
        while (!isSpaceChar(b)) {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }

    public int nextInt() {
        if (!hasNext())
            throw new NoSuchElementException();
        int c = readByte();
        while (isSpaceChar(c))
            c = readByte();
        boolean minus = false;
        if (c == '-') {
            minus = true;
            c = readByte();
        }
        int res = 0;
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res = res * 10 + c - '0';
            c = readByte();
        } while (!isSpaceChar(c));
        return (minus) ? -res : res;
    }

    public long nextLong() {
        if (!hasNext())
            throw new NoSuchElementException();
        int c = readByte();
        while (isSpaceChar(c))
            c = readByte();
        boolean minus = false;
        if (c == '-') {
            minus = true;
            c = readByte();
        }
        long res = 0;
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res = res * 10 + c - '0';
            c = readByte();
        } while (!isSpaceChar(c));
        return (minus) ? -res : res;
    }

    public double nextDouble() {
        return Double.parseDouble(next());
    }

    public int[] nextIntArray(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = nextInt();
        return a;
    }

    public long[] nextLongArray(int n) {
        long[] a = new long[n];
        for (int i = 0; i < n; i++)
            a[i] = nextLong();
        return a;
    }

    public char[][] nextCharMap(int n, int m) {
        char[][] map = new char[n][m];
        for (int i = 0; i < n; i++)
            map[i] = next().toCharArray();
        return map;
    }
}

