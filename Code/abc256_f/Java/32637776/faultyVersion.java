import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.stream.IntStream;
import java.io.ByteArrayOutputStream;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.io.FileNotFoundException;
import java.io.Writer;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * @author tauros
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        RealFastReader in = new RealFastReader(inputStream);
        RealFastWriter out = new RealFastWriter(outputStream);
        ABC256F solver = new ABC256F();
        solver.solve(1, in, out);
        out.close();
    }

    static class ABC256F {
        private final int MOD = 998244353;

        private int lowBit(int x) {
            return x & -x;
        }

        private void update(long[] bit, int index, long val) {
            val = (val % MOD + MOD) % MOD;
            for (; index < bit.length; index += lowBit(index)) {
                bit[index] += val;
                if (bit[index] >= MOD) {
                    bit[index] -= MOD;
                }
            }
        }

        private long query(long[] bit, int index) {
            long ans = 0;
            for (; index > 0; index -= lowBit(index)) {
                ans += bit[index];
                if (ans >= MOD) {
                    ans -= MOD;
                }
            }
            return ans;
        }

        private void update(long[] sum1, long[] sum2, long[] sum3, final int pos, final long val) {
            long oriVal1 = query(sum1, pos) - query(sum1, pos - 1);
            update(sum1, pos, val - oriVal1);
            long oriVal2 = query(sum2, pos) - query(sum2, pos - 1);
            long curVal2 = (pos * val) % MOD;
            update(sum2, pos, curVal2 - oriVal2);
            long oriVal3 = query(sum3, pos) - query(sum3, pos - 1);
            long curVal3 = (val * pos % MOD * pos) % MOD;
            update(sum3, pos, curVal3 - oriVal3);
        }

        private long query(long[] sum1, long[] sum2, long[] sum3, int pos) {
            long fact1 = (long) (pos + 1) * (pos + 2) % MOD;
            long part1 = query(sum1, pos) * fact1 % MOD;
            long fact2 = -(3 + 2L * pos) % MOD;
            long part2 = query(sum2, pos) * fact2 % MOD;
            long part3 = query(sum3, pos);
            long ans = (part3 + part2 + part1) % MOD;
            ans = ans * Common.invExgcdLong(2, MOD) % MOD;
            return ans;
        }

        public void solve(int testNumber, RealFastReader in, RealFastWriter out) {
            int n = in.ni(), q = in.ni();
            long[] sum1 = new long[n + 1];
            long[] sum2 = new long[n + 1];
            long[] sum3 = new long[n + 1];
            for (int i = 1; i <= n; i++) {
                int a = in.ni();
                update(sum1, sum2, sum3, i, a);
            }
            while (q-- > 0) {
                int op = in.ni();
                if (op == 1) {
                    int x = in.ni(), v = in.ni();
                    update(sum1, sum2, sum3, x, v);
                } else if (op == 2) {
                    int x = in.ni();
                    long ans = query(sum1, sum2, sum3, x);
                    out.println(ans);
                }
            }
            out.flush();
        }

    }

    static class RealFastWriter {
        private static final int BUF_SIZE = 1 << 13;
        private final byte[] buf = new byte[BUF_SIZE];
        private OutputStream out;
        private Writer writer;
        private int ptr = 0;

        private RealFastWriter() {
            out = null;
        }

        public RealFastWriter(Writer writer) {
            this.writer = new BufferedWriter(writer);
            out = new ByteArrayOutputStream();
        }

        public RealFastWriter(OutputStream os) {
            this.out = os;
        }

        public RealFastWriter(String path) {
            try {
                this.out = new FileOutputStream(path);
            } catch (FileNotFoundException e) {
                throw new RuntimeException("FastWriter");
            }
        }

        public RealFastWriter write(byte b) {
            buf[ptr++] = b;
            if (ptr == BUF_SIZE) {
                innerflush();
            }
            return this;
        }

        public RealFastWriter write(String s) {
            s.chars().forEach(c -> {
                buf[ptr++] = (byte) c;
                if (ptr == BUF_SIZE) {
                    innerflush();
                }
            });
            return this;
        }

        private static int countDigits(long l) {
            if (l >= 1000000000000000000L) {
                return 19;
            }
            if (l >= 100000000000000000L) {
                return 18;
            }
            if (l >= 10000000000000000L) {
                return 17;
            }
            if (l >= 1000000000000000L) {
                return 16;
            }
            if (l >= 100000000000000L) {
                return 15;
            }
            if (l >= 10000000000000L) {
                return 14;
            }
            if (l >= 1000000000000L) {
                return 13;
            }
            if (l >= 100000000000L) {
                return 12;
            }
            if (l >= 10000000000L) {
                return 11;
            }
            if (l >= 1000000000L) {
                return 10;
            }
            if (l >= 100000000L) {
                return 9;
            }
            if (l >= 10000000L) {
                return 8;
            }
            if (l >= 1000000L) {
                return 7;
            }
            if (l >= 100000L) {
                return 6;
            }
            if (l >= 10000L) {
                return 5;
            }
            if (l >= 1000L) {
                return 4;
            }
            if (l >= 100L) {
                return 3;
            }
            if (l >= 10L) {
                return 2;
            }
            return 1;
        }

        public RealFastWriter write(long x) {
            if (x == Long.MIN_VALUE) {
                return write("" + x);
            }
            if (ptr + 21 >= BUF_SIZE) {
                innerflush();
            }
            if (x < 0) {
                write((byte) '-');
                x = -x;
            }
            int d = countDigits(x);
            for (int i = ptr + d - 1; i >= ptr; i--) {
                buf[i] = (byte) ('0' + x % 10);
                x /= 10;
            }
            ptr += d;
            return this;
        }

        public RealFastWriter writeln(long x) {
            return write(x).writeln();
        }

        public RealFastWriter writeln() {
            return write((byte) '\n');
        }

        private void innerflush() {
            try {
                out.write(buf, 0, ptr);
                ptr = 0;
            } catch (IOException e) {
                throw new RuntimeException("innerflush");
            }
        }

        public void flush() {
            innerflush();
            try {
                if (writer != null) {
                    writer.write(((ByteArrayOutputStream) out).toString());
                    out = new ByteArrayOutputStream();
                    writer.flush();
                } else {
                    out.flush();
                }
            } catch (IOException e) {
                throw new RuntimeException("flush");
            }
        }

        public RealFastWriter println(long x) {
            return writeln(x);
        }

        public void close() {
            flush();
            try {
                out.close();
            } catch (Exception e) {
            }
        }

    }

    static final class Common {
        public static long[] exgcdLong(long a, long b) {
            // calc ax + by = gcd(a, b)
            if (b == 0) {
                // gcd, x, y
                return new long[]{ a, 1, 0 };
            }
            long[] res = exgcdLong(b, a % b);
            // x = y0; y = x0 - (a / b) * y0;
            long gcd = res[0], x0 = res[1], y0 = res[2];
            long x = y0, y = x0 - (a / b) * y0;
            return new long[]{ gcd, x, y };
        }

        public static long invExgcdLong(long a, long p) {
            long[] exgcd = exgcdLong(a, p);
            if (exgcd[0] != 1L) {
                return -1L;
            }
            long inv = exgcd[1];
            return (inv % p + p) % p;
        }

    }

    static class RealFastReader {
        InputStream is;
        private byte[] inbuf = new byte[1024];
        public int lenbuf = 0;
        public int ptrbuf = 0;

        public RealFastReader(final InputStream is) {
            this.is = is;
        }

        public int readByte() {
            if (lenbuf == -1) {
                throw new InputMismatchException();
            }
            if (ptrbuf >= lenbuf) {
                ptrbuf = 0;
                try {
                    lenbuf = is.read(inbuf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (lenbuf <= 0) {
                    return -1;
                }
            }
            return inbuf[ptrbuf++];
        }

        public int ni() {
            return (int) nl();
        }

        public long nl() {
            long num = 0;
            int b;
            boolean minus = false;
            while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
                ;
            if (b == '-') {
                minus = true;
                b = readByte();
            }

            while (true) {
                if (b >= '0' && b <= '9') {
                    num = num * 10 + (b - '0');
                } else {
                    return minus ? -num : num;
                }
                b = readByte();
            }
        }

    }
}

