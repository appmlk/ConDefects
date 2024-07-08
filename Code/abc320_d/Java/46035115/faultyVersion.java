import java.io.PrintWriter;
import java.util.*;

public class Main {
    private static final ContestScanner in = new ContestScanner(System.in);
    private static final PrintWriter pw = new PrintWriter(System.out);

    private static int n, m;
    private static long[][] coord;
    private static boolean[] visited;
    private static List<int[]>[] g;

    public static void main(String[] args) {
        n = in.nextInt();
        m = in.nextInt();
        coord = new long[n][2];
        visited = new boolean[n];
        g = new List[n];
        for (int i = 0; i < n;) g[i++] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            int x = in.nextInt();
            int y = in.nextInt();
            g[a].add(new int[]{b, x, y});
            g[b].add(new int[]{a, -x, -y});
        }

        solve();
    }

    private static void solve() {
        List<Integer> queue = new ArrayList<>();
        queue.add(0);
        visited[0] = true;

        for (int p = 0; p < queue.size(); p++) {
            int cur = queue.get(p);
            for (int[] next: g[cur]) {
                if (visited[next[0]]) continue;

                queue.add(next[0]);
                visited[next[0]] = true;
                coord[next[0]][0] = coord[cur][0] + next[1];
                coord[next[0]][1] = coord[cur][1] + next[2];
            }
        }

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                res.append(coord[i][0]).append(" ").append(coord[i][1]).append("\n");
            } else {
                res.append("undecidable");
            }
        }
        write(res.toString());
    }

    private static void write(long num) {
        pw.println(num);
        pw.flush();
    }
    private static void write(String s) {
        pw.println(s);
        pw.flush();
    }
    private static void write(int[] arr, int begin, int end) {
        StringBuilder line = new StringBuilder();
        for (int i = begin; i <= end; i++) {
            line.append(arr[i]).append(" ");
        }
        write(line.toString());
    }
}

/**
 * refercence : https://github.com/NASU41/AtCoderLibraryForJava/blob/master/ContestIO/ContestScanner.java
 */
class ContestScanner {
    private final java.io.InputStream in;
    private final byte[] buffer = new byte[1024];
    private int ptr = 0;
    private int buflen = 0;

    private static final long LONG_MAX_TENTHS = 922337203685477580L;
    private static final int LONG_MAX_LAST_DIGIT = 7;
    private static final int LONG_MIN_LAST_DIGIT = 8;

    public ContestScanner(java.io.InputStream in){
        this.in = in;
    }
    public ContestScanner(java.io.File file) throws java.io.FileNotFoundException {
        this(new java.io.BufferedInputStream(new java.io.FileInputStream(file)));
    }
    public ContestScanner(){
        this(System.in);
    }

    private boolean hasNextByte() {
        if (ptr < buflen) {
            return true;
        }else{
            ptr = 0;
            try {
                buflen = in.read(buffer);
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
            if (buflen <= 0) {
                return false;
            }
        }
        return true;
    }
    private int readByte() {
        if (hasNextByte()) return buffer[ptr++]; else return -1;
    }
    private static boolean isPrintableChar(int c) {
        return 33 <= c && c <= 126;
    }
    public boolean hasNext() {
        while(hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++;
        return hasNextByte();
    }
    public String next() {
        if (!hasNext()) throw new java.util.NoSuchElementException();
        StringBuilder sb = new StringBuilder();
        int b = readByte();
        while(isPrintableChar(b)) {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }

    public long nextLong() {
        if (!hasNext()) throw new java.util.NoSuchElementException();
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
                int digit = b - '0';
                if (n >= LONG_MAX_TENTHS) {
                    if (n == LONG_MAX_TENTHS) {
                        if (minus) {
                            if (digit <= LONG_MIN_LAST_DIGIT) {
                                n = -n * 10 - digit;
                                b = readByte();
                                if (!isPrintableChar(b)) {
                                    return n;
                                } else if (b < '0' || '9' < b) {
                                    throw new NumberFormatException(
                                            String.format("%d%s... is not number", n, Character.toString((char)b))
                                    );
                                }
                            }
                        } else {
                            if (digit <= LONG_MAX_LAST_DIGIT) {
                                n = n * 10 + digit;
                                b = readByte();
                                if (!isPrintableChar(b)) {
                                    return n;
                                } else if (b < '0' || '9' < b) {
                                    throw new NumberFormatException(
                                            String.format("%d%s... is not number", n, Character.toString((char)b))
                                    );
                                }
                            }
                        }
                    }
                    throw new ArithmeticException(
                            String.format("%s%d%d... overflows long.", minus ? "-" : "", n, digit)
                    );
                }
                n = n * 10 + digit;
            }else if(b == -1 || !isPrintableChar(b)){
                return minus ? -n : n;
            }else{
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