import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;
import java.util.InputMismatchException;

public class Main {
    static RealFastReader sc = new RealFastReader(System.in);
    static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) {
        int t = 1;
        while (t-- > 0) {
            solve();
        }
        out.close();
    }

    static int s;
    public static void solve() {
        int[][] g = sc.nmi(3,3);
        int[][] h = new int[3][3];
        for(int[] w: h){
            Arrays.fill(w,-1);
        }
        s = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                s += g[i][j];
            }
        }
        if(dfs(g, 0, 0, h)){
            out.println("Takahashi");
        }else {
            out.println("Aoki");
        }

    }
    private static boolean dfs(int[][] g, int now, int f, int[][] h){
        if(now == ((1<<9)-1)){
            long a = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if(h[i][j] == f){
                        a += g[i][j];
                    }
                }
            }
            return a > s - a;
        }
        boolean r = false;
        for (int i = 0; i < 9; i++) {
            if(((1<<i)&now) == (1<<i)){
                continue;
            }
            h[i/3][i%3] = f;
            if(check(h)){
                h[i/3][i%3] = -1;
                r = true;
                break;
            }
            r |= !(dfs(g, (1<<i)| now, f^1,  h));
            h[i/3][i%3] = -1;
            if(r){
                break;
            }
        }
        return r;

    }

    private static boolean check(int[][] h){
        if( h[0][0] == h[0][1] && h[0][2] == h[0][1] && h[0][0]!=-1){
            return true;
        }

        if(h[1][1] == h[1][0] && h[1][2] == h[1][1] && h[1][0]!=-1){
            return true;
        }
        if(h[2][1] == h[2][0] && h[2][2] == h[2][1] && h[2][0]!=-1){
            return true;
        }

        if(h[0][0] == h[1][0] && h[1][0] == h[2][0] && h[0][0]!=-1){
            return true;
        }
        if(h[0][1] == h[1][1] && h[1][1] == h[2][1] && h[0][1]!=-1){
            return true;
        }

        if(h[0][2] == h[1][2] && h[1][2] == h[2][2] && h[0][2]!=-1){
            return true;
        }

        if(h[0][0] == h[1][1] && h[1][1] == h[2][2] && h[0][0]!=-1){
            return true;
        }

        if(h[0][2] == h[1][1] && h[1][1] == h[2][0] && h[0][2]!=-1){
            return true;
        }


        return false;
    }

    public static class RealFastReader {
        InputStream is;

        public RealFastReader(final InputStream is) {
            this.is = is;
        }

        private byte[] inbuf = new byte[8192];
        public int lenbuf = 0, ptrbuf = 0;

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

        private boolean isSpaceChar(int c) {
            return !(c >= 33 && c <= 126);
        }

        private int skip() {
            int b;
            while ((b = readByte()) != -1 && isSpaceChar(b))
                ;
            return b;
        }

        public double nd() {
            return Double.parseDouble(ns());
        }

        public char nc() {
            return (char) skip();
        }

        public String ns() {
            int b = skip();
            StringBuilder sb = new StringBuilder();
            while (!(isSpaceChar(b))) { // when nextLine, (isSpaceChar(b) && b != ' ')
                sb.appendCodePoint(b);
                b = readByte();
            }
            return sb.toString();
        }

        public char[] ns(int n) {
            char[] buf = new char[n];
            int b = skip(), p = 0;
            while (p < n && !(isSpaceChar(b))) {
                buf[p++] = (char) b;
                b = readByte();
            }
            return n == p ? buf : Arrays.copyOf(buf, p);
        }

        public int[] na(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = ni();
            }
            return a;
        }

        public long[] nal(int n) {
            long[] a = new long[n];
            for (int i = 0; i < n; i++) {
                a[i] = nl();
            }
            return a;
        }

        public char[][] nm(int n, int m) {
            char[][] map = new char[n][];
            for (int i = 0; i < n; i++) {
                map[i] = ns(m);
            }
            return map;
        }

        public int[][] nmi(int n, int m) {
            int[][] map = new int[n][];
            for (int i = 0; i < n; i++) {
                map[i] = na(m);
            }
            return map;
        }

        public int ni() {
            int num = 0;
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
