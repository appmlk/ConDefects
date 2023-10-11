import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class Main {
    static Main2 admin = new Main2();

    public static void main(String[] args) {
        admin.start();
    }
}

class Main2 {
    //---------------------------------INPUT READER-----------------------------------------//
    public BufferedReader br;
    StringTokenizer st = new StringTokenizer("");
    String next() {
        while (!st.hasMoreTokens()) {
            try { st = new StringTokenizer(br.readLine());} catch (IOException e) { e.printStackTrace(); }
        }
        return st.nextToken();
    }

    int ni() { return Integer.parseInt(next()); }
    long nl() { return Long.parseLong(next()); }
    double nd() { return Double.parseDouble(next()); }
    String ns() { return next(); }

    int[] na(long n) {int[]ret=new int[(int)n]; for(int i=0;i<n;i++) ret[i]=ni(); return ret;}
    long[] nal(long n) {long[]ret=new long[(int)n]; for(int i=0;i<n;i++) ret[i]=nl(); return ret;}
    Integer[] nA(long n) {Integer[]ret=new Integer[(int)n]; for(int i=0;i<n;i++) ret[i]=ni(); return ret;}
    Long[] nAl(long n) {Long[]ret=new Long[(int)n]; for(int i=0;i<n;i++) ret[i]=nl(); return ret;}

    //--------------------------------------PRINTER------------------------------------------//
    PrintWriter w;
    void p(int i) {w.println(i);} void p(long l) {w.println(l);}
    void p(double d) {w.println(d);} void p(String s) { w.println(s);}
    void pr(int i) {w.print(i);} void pr(long l) {w.print(l);}
    void pr(double d) {w.print(d);} void pr(String s) { w.print(s);}
    void pl() {w.println();}

    //--------------------------------------VARIABLES-----------------------------------------//
    long lma = Long.MAX_VALUE, lmi = Long.MIN_VALUE;
    int ima = Integer.MAX_VALUE, imi = Integer.MIN_VALUE;
//    long mod = 1000000007;
    long mod = 998244353;
    {
        w = new PrintWriter(System.out);
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    //----------------------START---------------------//
    void start() {
        //int t = ni(); while(t-- > 0)
            solve();

        w.close();
    }

    void solve() {
        int n = ni(), m = ni();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) arr[i] = ni() - 1;
        uf uf = new uf(n);
        long ans = 0;
        long inv2 = mp(2, mod - 2);
        for(int i = 0; i < n; i++) {
            if(uf.con(i, arr[i])) continue;
            long curr = mp(m, uf.comps - 2);
            long mult = ((m * (m-1L) % mod) * inv2) % mod;
            curr = (curr * mult) % mod;
            uf.unify(i, arr[i]);
            ans += curr;
            ans %= mod;
        }
        p(ans);
    }

    static class uf {
        int[] id, sz; int comps;

        // 0 to n
        public uf(int n) {
            n--;
            id = new int[n+1]; sz = new int[n+1];
            comps = n+1;
            for (int i = 0; i < n + 1; i++) {
                id[i] = i;
            }
            Arrays.fill(sz, 1);
        }

        public int find(int a) {
            return id[a]=(id[a]==a?a:find(id[a]));
        }

        public boolean con(int a, int b) {return find(a)==find(b);}

        public int size(int a) {return sz[find(a)];}

        public int comps() {return comps;}

        public void unify(int a, int b) {
            a = find(a); b = find(b);
            if(a==b)return;
            if(sz[a] >= sz[b]) {
                id[b] = a;
                sz[a] += sz[b];
            } else {
                id[a] = b;
                sz[b] += sz[a];
            }
            comps--;
        }
    }

    long mp (long b, long x) {
        if (x == 0) return 1;
        if (x == 1) return b;
        if (x % 2 == 0) return mp (b * b % mod, x / 2) % mod;

        return b * mp (b * b % mod, x / 2) % mod;
    }
}