import java.util.*;
import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        //Scanner sc = new Scanner(System.in);
        Main o = new Main();
        o.solve();
    }

    List<List<int[]>> G;
    int[] d;
    long[][] dp;
    long inf = (long)1e18;
    public void solve(){
        FastScanner sc = new FastScanner(System.in);
        int n = sc.nextInt();
        G = new ArrayList<>();
        d = new int[n + 1];
        dp = new long[n + 1][2];
        for(int i = 1; i <= n; ++i){
            d[i] = sc.nextInt();
            G.add(new ArrayList<>());
        }
        G.add(new ArrayList<>());
        for(int i = 1; i < n; ++i){
            int u = sc.nextInt(), v = sc.nextInt(), w = sc.nextInt();
            G.get(u).add(new int[]{v, w});
            G.get(v).add(new int[]{u, w});
        }
        dfs(1, -1);
        long ans = dp[1][1];
        System.out.println(ans);
    }
    public void dfs(int v, int p){
        List<Long> l = new ArrayList<>();
        for(int[] nxt : G.get(v)){
            int u = nxt[0], w = nxt[1];
            if(u == p) continue;
            dfs(u, v);
            l.add(dp[u][0] + (long)w - dp[u][1]);
            dp[v][0] += dp[u][1];
            dp[v][1] += dp[u][1];
        }
        Collections.sort(l, Collections.reverseOrder());
        for(int i = 0; i < l.size(); ++i){
            if(l.get(i) <= 0) break;
            if(i < d[v] - 1) dp[v][0] += l.get(i);
            if(i < d[v]) dp[v][1] += l.get(i);
        }
        if(d[v] == 0) dp[v][0] = -inf;
    }


    class FastScanner {
        private final InputStream in;
        private final byte[] buf = new byte[1024];
        private int ptr = 0;
        private int buflen = 0;
        FastScanner( InputStream source ) { this.in = source; }
        private boolean hasNextByte() {
            if ( ptr < buflen ) return true;
            else {
                ptr = 0;
                try { buflen = in.read(buf); } catch (IOException e) { e.printStackTrace(); }
                if ( buflen <= 0 ) return false;
            }
            return true;
        }
        private int readByte() { if ( hasNextByte() ) return buf[ptr++]; else return -1; }
        private boolean isPrintableChar( int c ) { return 33 <= c && c <= 126; }
        private boolean isNumeric( int c ) { return '0' <= c && c <= '9'; }
        private void skipToNextPrintableChar() { while ( hasNextByte() && !isPrintableChar(buf[ptr]) ) ptr++; }
        public boolean hasNext() { skipToNextPrintableChar(); return hasNextByte(); }
        public String next() {
            if ( !hasNext() ) throw new NoSuchElementException();
            StringBuilder ret = new StringBuilder();
            int b = readByte();
            while ( isPrintableChar(b) ) { ret.appendCodePoint(b); b = readByte(); }
            return ret.toString();
        }
        public long nextLong() {
            if ( !hasNext() ) throw new NoSuchElementException();
            long ret = 0;
            int b = readByte();
            boolean negative = false;
            if ( b == '-' ) { negative = true; if ( hasNextByte() ) b = readByte(); }
            if ( !isNumeric(b) ) throw new NumberFormatException();
            while ( true ) {
                if ( isNumeric(b) ) ret = ret * 10 + b - '0';
                else if ( b == -1 || !isPrintableChar(b) ) return negative ? -ret : ret;
                else throw new NumberFormatException();
                b = readByte();
            }
        }
        public int nextInt() { return (int)nextLong(); }
        public double nextDouble() { return Double.parseDouble(next()); }
    }
}