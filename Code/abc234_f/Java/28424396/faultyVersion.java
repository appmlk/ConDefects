import java.io.*;
import java.util.*;

public class Main {
    static MyReader in = new MyReader();
    static MyWriter out = new MyWriter();

    static final int MOD = 998_244_353;

    public static void main(String[] args) {
        char[] S = new char[5000];
        int N = in.read(S);
        
        var cnt = new Counter<Character>();
        for (int i = 0; i < N; i++) {
            cnt.incr(S[i]);
        }

        var c = new Combination(N, MOD);

        long[] dp = new long[N + 1];
        dp[0] = 1;
        long[] dp2 = new long[N + 1];

        for (int v : cnt.values()) {
            Arrays.fill(dp2, 0);
            for (int k = 1; k <= v; k++) {
                for (int n = k; n <= N; n++) {
                    dp2[n] = (dp2[n] + dp[n - k] * c.comb(n, k)) % MOD;
                }
            }
            for (int i = 0; i <= N; i++) {
                dp[i] += dp2[i];
            }
        }

        long ans = 0;
        for (int i = 1; i <= N; i++) {
            ans = (ans + dp[i]) % MOD;
        }
        out.println(ans);
        out.flush();
    }
}

class Combination {
    final int MOD;
    
    long[] fact;
    long[] factInv;
    long[] inv;

    Combination(int n, int mod) {
        MOD = mod;

        fact = new long[n + 1];
        fact[0] = 1;
        for (int i = 1; i <= n; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }
 
        factInv = new long[n + 1];
        factInv[n] = inv((int)fact[n]);
        for (int i = n - 1; i >= 0; i--) {
            factInv[i] = factInv[i + 1] * (i + 1) % MOD;
        }

        inv = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            inv[i] = factInv[i] * fact[i - 1] % MOD;
        }
    }

    int inv(int a) {
        int b = MOD;
        int s = 1;
        int t = 0;
        while (0 < b) {
            int q = a / b;
            int r = a - b * q;
            a = b;
            b = r;
            r = s;
            s = t;
            t = r - t * q;
        }
        return s + (-s + MOD + 1) / MOD * MOD;
    }

    long comb(int n, int r) {
        return n < r || r < 0 ? 0 : fact[n] * factInv[r] % MOD * factInv[n - r] % MOD;
    }
}

class Counter<K> extends HashMap<K, Integer> {
    @Override
    public Integer get(Object key) {
        return super.getOrDefault(key, 0);
    }

    void plus(K key, int n) {
        put(key, get(key) + n);
    }

    void incr(K key) {
        plus(key, 1);
    }

    void decr(K key) {
        plus(key, -1);
    }
}

class MyReader {
    byte[] buf = new byte[1 << 16];
    int head = 0;
    int tail = 0;
    InputStream in = System.in;

    byte read() {
        if (head == tail) {
            try {
                tail = in.read(buf);
            } catch (IOException e) {
                e.printStackTrace();
            }
            head = 0;
        }
        return buf[head++];
    }

    char c() {
        char c = (char) read();
        read();
        return c;
    }

    int i() {
        int i = read() - '0';
        boolean negative = i == -3;
        int n = negative ? 0 : i;
        while (0 <= (i = read() - '0') && i <= 9) {
            n = 10 * n + i;
        }
        return negative ? -n : n;
    }

    void read(int[] a, int from, int to) {
        for (int i = from; i < to; i++) {
            a[i] = i();
        }
    }

    void read(int[] a) {
        read(a, 0, a.length);
    }

    int[] ii(int length, int from, int to) {
        int[] a = new int[length];
        read(a, from, to);
        return a;
    }

    int[] ii(int length) {
        return ii(length, 0, length);
    }

    int[][] ii2(int row, int col) {
        int[][] a = new int[row][col];
        for (int i = 0; i < row; i++) {
            read(a[i]);
        }
        return a;
    }

    long l() {
        int i = read() - '0';
        boolean negative = i == -3;
        long n = negative ? 0 : i;
        while (0 <= (i = read() - '0') && i <= 9) {
            n = 10 * n + i;
        }
        return negative ? -n : n;
    }

    void read(long[] a, int from, int to) {
        for (int i = from; i < to; i++) {
            a[i] = l();
        }
    }

    void read(long[] a) {
        read(a, 0, a.length);
    }

    long[] ll(int length, int from, int to) {
        long[] a = new long[length];
        read(a, from, to);
        return a;
    }

    long[] ll(int length) {
        return ll(length, 0, length);
    }

    int read(char[] buf, int off) {
        int i;
        char c;
        for (i = off; (c = (char) read()) != ' ' && c != '\n'; i++) {
            buf[i] = c;
        }
        return i;
    }

    int read(char[] buf) {
        return read(buf, 0);
    }

    char[] s(int length) {
        char[] s = new char[length];
        for (int i = 0; i < length; i++) {
            s[i] = (char) read();
        }
        read();
        return s;
    }

    char[][] ss(int row, int col) {
        char[][] s = new char[row][col];
        for (int i = 0; i < row; i++) {
            read(s[i]);
        }
        return s;
    }
}

class MyWriter {
    OutputStream out = System.out;

    byte[] buf = new byte[1 << 16];
    byte[] ibuf = new byte[20];

    int tail = 0;

    void flush() {
        try {
            out.write(buf, 0, tail);
            tail = 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void write(byte b) {
        buf[tail++] = b;
        if (tail == buf.length) {
            flush();
        }
    }

    void write(byte[] b, int off, int len) {
        for (int i = off; i < off + len; i++) {
            write(b[i]);
        }
    }

    void space() {
        write((byte) ' ');
    }

    void println() {
        write((byte) '\n');
    }

    void print(char c) {
        write((byte) c);
    }

    void println(char c) {
        print(c);
        println();
    }

    void printsp(char c) {
        print(c);
        space();
    }

    void print(int n) {
        if (n < 0) {
            n = -n;
            write((byte) '-');
        }

        int i = ibuf.length;
        do {
            ibuf[--i] = (byte) (n % 10 + '0');
            n /= 10;
        } while (n > 0);

        write(ibuf, i, ibuf.length - i);
    }

    void println(int n) {
        print(n);
        println();
    }

    void printsp(int n) {
        print(n);
        space();
    }

    void print(long n) {
        if (n < 0) {
            n = -n;
            write((byte) '-');
        }

        int i = ibuf.length;
        do {
            ibuf[--i] = (byte) (n % 10 + '0');
            n /= 10;
        } while (n > 0);

        write(ibuf, i, ibuf.length - i);
    }

    void println(long n) {
        print(n);
        println();
    }

    void printsp(long n) {
        print(n);
        space();
    }

    void print(String s) {
        byte[] b = s.getBytes();
        write(b, 0, b.length);
    }

    void println(String s) {
        print(s);
        println();
    }

    void printsp(String s) {
        print(s);
        space();
    }

    void print(int[] a, int from, int to, char sep) {
        for (int i = from; i < to; i++) {
            print(a[i]);
            print(sep);
        }
    }

    void print(int[] a, char sep) {
        print(a, 0, a.length, sep);
    }

    void print(int[] a, int from, int to) {
        print(a, from, to, ' ');
    }

    void print(int[] a) {
        print(a, ' ');
    }

    void println(int[] a) {
        print(a);
        println();
    }

    void println(int[] a, char sep) {
        print(a, sep);
        println();
    }

    void println(int[] a, int from, int to) {
        print(a, from, to);
        println();
    }

    void print(char[] s, int from, int to) {
        for (int i = from; i < to && s[i] != '\0'; i++) {
            print(s[i]);
        }
    }

    void print(char[] s) {
        print(s, 0, s.length);
    }

    void println(char[] s, int from, int to) {
        print(s, from, to);
        println();
    }

    void println(char[] s) {
        println(s, 0, s.length);
    }

    void print(Object o) {
        print(o.toString());
    }

    void println(Object o) {
        print(o);
    }
}
