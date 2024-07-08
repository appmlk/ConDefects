import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    static int MOD = 1000000007;
    static int INF = Integer.MAX_VALUE/2;

    static void run (final FastScanner scanner, final PrintWriter out) {
        int H  = scanner.nextInt();
        int W  = scanner.nextInt();
        int[][] gridA = new int[H][W];
        int[][] gridB = new int[H][W];
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                gridA[i][j]= scanner.nextInt();;
                set.add(gridA[i][j]);
            }
        }
        boolean ok = true;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                gridB[i][j]= scanner.nextInt();;
                if (!set.contains(gridB[i][j])) {
                    ok=false;
                }
            }
        }
        if(!ok) {
            System.out.println(-1);
            return;
        }
        Permutations permH = new Permutations(H);
        Permutations permW = new Permutations(W);

        for (int[] permh : permH.perms) {
            for (int[] permw : permW.perms) {
                int[][] aa = new int[H][W];
                for (int i = 0; i < H; i++) {
                    for (int j = 0; j < W; j++) {
                        aa[i][j]=gridA[permh[i]][permw[j]];
                    }
                }
                if (isSame(aa, gridB)) {
                    System.out.println(inversionNumber(permh) + inversionNumber(permw));
                    return;
                }
            }
        }
        System.out.println(-1);
    }

    static int inversionNumber(int[] a) {
        int ret = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i+1; j < a.length; j++) {
                if (a[i]>a[j]) {
                    ret++;
                }
            }
        }
        return ret;
    }

    static boolean isSame(int[][] a,int[][] b) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if(a[i][j]!=b[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    static class Permutations {
        int[] perm;
        boolean[] used;
        int N;
        ArrayList<int[]> perms = new ArrayList<>();

        public Permutations(int n) {
            this.N=n;
            used=new boolean[N];
            perm=new int[N];
            perm(0);
        }
        void perm(int depth) {
            if (depth == N) {
                int[] idxArr = new int[N];
                for (int i = 0; i < N; i++) {
                    idxArr[i] = perm[i];
                }
                perms.add(idxArr);
                return;
            }
            for (int i = 0; i < N; i++) {
                if (used[i]) {
                    continue;
                }
                used[i] = true;
                perm[depth] = i;
                perm(depth + 1);
                used[i] = false;
            }
        }
    }

    public static void main(final String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        FastScanner scanner = new FastScanner();
        try {
            run(scanner, out);
        } catch (Throwable e) {
            throw e;
        } finally {
            out.flush();
        }
    }

    static class FastScanner {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1024];
        private int ptr = 0;
        private int buflen = 0;
        private boolean hasNextByte() {
            if (ptr < buflen) {
                return true;
            }else{
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
        public boolean hasNext() { while(hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++; return hasNextByte();}
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
            while(true){
                if ('0' <= b && b <= '9') {
                    n *= 10;
                    n += b - '0';
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
        public double nextDouble() { return Double.parseDouble(next());}
    }
}
