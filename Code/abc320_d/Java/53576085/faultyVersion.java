import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    static int MOD = 1000000007;
    static long INF = Long.MAX_VALUE/2;

    static void run (final FastScanner scanner, final PrintWriter out) {
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        List<int[]>[] graph = new List[N];
        for (int i = 0; i < N; i++) {
            graph[i]=new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            int a = scanner.nextInt()-1;
            int b = scanner.nextInt()-1;
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            graph[a].add(new int[]{b,x,y});
            graph[b].add(new int[]{a,-x,-y});
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        long[][] cood = new long[N][2];
        for (int i = 0; i < N; i++) {
            cood[i][0]=cood[i][1]=INF;
        }
        cood[0][0]=cood[0][1]=0;
        while(!q.isEmpty()) {
            Integer current = q.poll();
            for (int[] nexts : graph[current]) {
                int next = nexts[0];
                int y = nexts[1];
                int x = nexts[2];
                if (cood[next][0]!=INF) {
                    continue;
                }
                cood[next][0]=cood[current][0]+y;
                cood[next][1]=cood[current][1]+x;
                q.add(next);
            }
        }
        for (int i = 0; i < N; i++) {
            if (cood[i][0]==INF) {
                out.println("0 0");
            } else {
                out.println(cood[i][0]+" "+cood[i][1]);
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
