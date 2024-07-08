import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.NoSuchElementException;

class Main {
    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        FastScanner sc = new FastScanner();

        int N = sc.nextInt();
        int A = sc.nextInt();
        int B = sc.nextInt();
        String S = sc.next();

        int L = 0;
        int R = 0;

        for (int i = 0; i < 2*N; i++) {
            if (S.charAt(i) == '('){
                L++;
            }else {
                if (L > 0){
                    L--;
                }else R++;
            }
        }

        if (A <= 2*B){
            long answer = 0;
            while (L > 0 && R > 0){
                if (L >= 2 && R >= 2){
                    answer += A;
                    L -= 2;
                    R -= 2;
                }else if (A <= 2*B){
                    answer += A;
                    L --;
                    R --;
                }else {
                    break;
                }
            }
            System.out.println(answer + ((long) (L+R)/2*B));
        }else {
            long answer = Math.min(L/2,R/2);
            answer += (long) (L / 2) *B;
            answer += (long) (R / 2) *B;
            L %= 2;
            R %= 2;
            System.out.println(answer + (long) (L + R) *B);
        }

        out.flush();
    }

    private static class FastScanner {
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
                return buflen > 0;
            }
        }

        private int readByte() {
            if (hasNextByte()) return buffer[ptr++];
            else return -1;
        }

        private static boolean isPrintableChar(int c) {
            return 33 <= c && c <= 126;
        }

        public boolean hasNext() {
            while (hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++;
            return !hasNextByte();
        }

        public String next() {
            if (hasNext()) throw new NoSuchElementException();
            StringBuilder sb = new StringBuilder();
            int b = readByte();
            while (isPrintableChar(b)) {
                sb.appendCodePoint(b);
                b = readByte();
            }
            return sb.toString();
        }

        public long nextLong() {
            if (hasNext()) throw new NoSuchElementException();
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

        public double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}