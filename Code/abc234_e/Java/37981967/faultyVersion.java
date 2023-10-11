import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.lang.Math;

public class Main {
    static long X;

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        X = sc.nextLong();

        String s = String.valueOf(X);
        int len = s.length();

        if (len > 10) {
            char f = s.charAt(0);
            if (s.charAt(0) >= s.charAt(1)) {
                long cur = 0L;
                for (int i = 0; i < len; i++) {
                    cur = cur * 10 + (long) (f - '0');
                }
            } else {
                long cur = 0L;
                if (f == '9') {
                    for (int i = 0; i < len + 1; i++) {
                        cur = cur * 10 + 1L;
                    }
                } else {
                    for (int i = 0; i < len; i++) {
                        cur = cur * 10 + (long) (f - '0') + 1L;
                    }
                }
            }
        }

        long ans = 11111111111111111L;
        for (int d = -9; d < 10; d++) {
            for (int f = 1; f < 10; f++) {
                long cur = f;
                if (cur >= X) {
                    ans = Math.min(ans, cur);
                }
                for (int i = 0; i < len + 1; i++) {
                    long next = cur % 10 + d;
                    if (next < 0 || next > 9)
                        break;
                    cur = cur * 10 + next;
                    if (cur >= X) {
                        ans = Math.min(ans, cur);
                    }
                }
            }
        }
        System.out.println(ans);
    }
}

// System.out.println();

// for(int i=0; i<N; i++)

// for(int i=0; i<N; i++){
// for(int j=0; j<M; j++){
// // 添字に注意
// }
// }

// G = new char[N][N];
// for(int i=0; i<N; i++) G[i] = sc.next().toCharArray();

// for(Map.Entry<String, String> entry : m.entrySet()) {
// System.out.println(entry.getKey());
// System.out.println(entry.getValue());
// }

// TreeMap<Integer, Integer> map = new TreeMap<>();

// static char[] T;
// static ArrayList<Integer> l = new ArrayList<>();
// static ArrayList<ArrayList<Integer>> l = new ArrayList<>();
// static LinkedList<Integer> l = new LinkedList<>();
// static TreeSet<Integer> set = new TreeSet<>();
// static HashMap<Integer, Integer> map = new HashMap<>();
// static TreeMap<Integer, Integer> map = new TreeMap<>();
// static char[][] G;

// class Pair implements Comparable<Pair>{
// int x;
// int y;
// public Pair(int a, int b) {
// this.x = a ;
// this.y = b;
// }
// public boolean equals(Object o) {
// if (this == o) return true;
// if (o == null || getClass() != o.getClass()) return false;
// Pair fraction = (Pair) o;
// return x == fraction.x && y == fraction.y;
// }

// @Override
// public int hashCode() {
// return Objects.hash(x, y);
// }

// @Override
// public int compareTo(Pair o) {
// if (this.equals(o)) {
// return 0;
// }
// if (this.x == 0) {
// return this.y == -1 ? 1 : -1;
// }
// if (0 <= this.x * o.x) {
// return 0 <= this.x * o.y - this.y * o.x ? 1 : -1;
// }
// return 0 <= this.x * o.y - this.y * o.x ? -1 : 1;
// }
// }

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

    private int readByte() {
        if (hasNextByte())
            return buffer[ptr++];
        else
            return -1;
    }

    private static boolean isPrintableChar(int c) {
        return 33 <= c && c <= 126;
    }

    public boolean hasNext() {
        while (hasNextByte() && !isPrintableChar(buffer[ptr]))
            ptr++;
        return hasNextByte();
    }

    public String next() {
        if (!hasNext())
            throw new NoSuchElementException();
        StringBuilder sb = new StringBuilder();
        int b = readByte();
        while (isPrintableChar(b)) {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }

    public long nextLong() {
        if (!hasNext())
            throw new NoSuchElementException();
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
        if (nl < Integer.MIN_VALUE || nl > Integer.MAX_VALUE)
            throw new NumberFormatException();
        return (int) nl;
    }

    public double nextDouble() {
        return Double.parseDouble(next());
    }
}
