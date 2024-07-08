import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    static int MOD = 1000000007;
    static int INF = Integer.MAX_VALUE/2;

    static void run (final FastScanner scanner, final PrintWriter out) {
        int N = scanner.nextInt();
        List<Integer>[] g = new List[N];
        for (int i = 0; i < N; i++) {
            g[i]=new ArrayList<>();
        }
        for (int i = 0; i < N; i++) {
            int a = scanner.nextInt()-1;
            g[i].add(a);
        }
        Set<Integer> inLoop = new HashSet<>();
        boolean[] visitedGlobal = new boolean[N];
        for (int i = 0; i < N; i++) {
            if (visitedGlobal[i]) {
                continue;
            }
            visitedGlobal[i]=true;
            var visitedLocal = new HashSet<Integer>();
            visitedLocal.add(i);
            Queue<Integer>q = new LinkedList<>();
            q.add(i);
            while(!q.isEmpty()) {
                int cur = q.poll();
                for (Integer next : g[cur]) {
                    if (visitedLocal.contains(next)) {
                        int start = i;
                        Queue<Integer>q2 = new LinkedList<>();
                        q2.add(start);
                        while(!q2.isEmpty()) {
                            int cur2 = q2.poll();
                            //System.out.println(cur2);
                            if (next==cur2) {
                                break;
                            }
                            visitedLocal.remove(cur2);
                            for (Integer next2 : g[cur2]) {
                                q2.add(next2);
                            }
                        }
                        inLoop.addAll(visitedLocal);
                        break;
                    }
                    if (visitedGlobal[next]) {
                        continue;
                    }
                    visitedGlobal[next]=true;
                    visitedLocal.add(next);
                    q.add(next);
                }
            }

            //System.out.println(visitedLocal);
        }
        System.out.println(inLoop.size());
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
