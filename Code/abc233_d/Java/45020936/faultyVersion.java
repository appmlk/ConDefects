import java.io.*;
import java.math.BigDecimal;
import java.util.*;


public class Main {


    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int MOD = 998244353;

    static int res,q,n;
    static int t;
    static Boolean[][] memo;
    static int[] a,s,f;
    static List<Integer>[] graph;
   static double ans = 0.00;
    /**
     * 2  5 10  12 15 20
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        Fastinanner3 in = new Fastinanner3(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int n = in.nextInt();
        long k = in.nextLong();
        long count = 0;
        int res = 0;
        Map<Long,Integer> maps = new HashMap<>();
        maps.put(0L,1);
        for(int i = 0 ; i < n;i++){
            count+=in.nextLong();
            res+=maps.getOrDefault(count-k,0);
            maps.put(count,maps.getOrDefault(count,0)+1);
        }
        out.println(res);
        in.close();
        out.close();
    }
    static long modMinus(long n) {
        return n < 0 ? n + MOD : n;
    }
    static long modPow(long base, long exponent) {
        long result = 1;
        long current = base;

        while (exponent > 0) {
            if ((exponent & 1) == 1) {
                result = (result * current) % MOD;
            }
            current = (current * current) % MOD;
            exponent >>= 1;
        }

        return result;
    }

    static long power(long a,long b,long m){
        long p=a,ans=1;
        for(int i=0;i<30;i++){
            int wari=(1<<i);
            if((b/wari)%2==1){
                ans=(ans*p)%m;
            }
            p=(p*p)%m;
        }
        return ans;
    }

    /**
     *    5
     *    5 2 1 2 2
     *    0 1
     *    2
     *    2
     *    2
     * @param key
     * @return
     */
    private static int length(Integer key) {
        int ans = 0;
        while(key > 0){
            key/=10;
            ans++;
        }
        return ans;
    }

    private static int calc(final int n, final long[] ns) {
        Trie root = new Trie();
        final int h = 29;
        for (long a : ns){
            int d = 1<<h;
            Trie tr = root;
            while (d > 0){
                if ((a & d) == 0){
                    if (tr.next[0]==null)tr.next[0]=new Trie();
                    tr = tr.next[0];
                }else {
                    if (tr.next[1]==null)tr.next[1]=new Trie();
                    tr = tr.next[1];
                }
                d >>= 1;
            }
        }
        return todo(root, 1<<h);
    }

    private static int todo(Trie tr, int h){
        int res = 0;
        if(tr.next[0] != null){
            int l = todo(tr.next[0], h>>1);
            if (tr.next[1] != null){
                int r = todo(tr.next[1], h>>1);
                res = Math.min(l, r) + h;
            }else {
                res = l;
            }
        }else if (tr.next[1] != null){
            res = todo(tr.next[1], h>>1);
        }
        return res;
    }
    private static long help(long[] dict, int pos, int left, int right) {
        if(pos==-1){
            return 0L;
        }
        int oneStart=right+1;
        for(int i=left;i<=right;i++){
            if(((dict[i]>>pos)&1)==1){
                oneStart=i;
                break;
            }
        }
        if(oneStart==left||oneStart==right+1){
            return help(dict,pos-1,left,right);
        }else {
            return (1<<pos)+Math.min(help(dict,pos-1,left,oneStart-1),help(dict,pos-1,oneStart,right));
        }
    }

    private static void connect(int i, int j,int[] p,int[] cnt) {
        int fi = father(i,p);
        int fj = father(j,p);
        if(fi != fj) {
            p[fi] = fj;
            cnt[fj] += cnt[fi];
        }
    }

    static int father(int i,int[] p) {
        if(p[i] == i) return i;
        return p[i] = father(p[i],p);
    }

    private static long rationalMod(long p, long q,int MOD) {

        return (p % MOD * pow(q, MOD - 2,MOD) % MOD) % MOD;
    }
    public static long pow (long x, long exp,int MOD){
        if (exp==0) return 1;
        long t = pow(x, exp/2,MOD);
        t = t*t % MOD;
        if (exp%2 == 0) return t;
        return t*x % MOD;
    }

    /**
     *   2     1
     *   22    2      290+116   406   12      23 2*3
     *   222   4
     *   2222 8
     * @param index
     * @param max
     * @param curr
     * @param n
     * @param m
     */

    private static void dfs(int index,int max,double curr,int n,int m){
        if(index > max){
            return;
        }
        if(n == 0){
            return;
        }

        if(m == 0){
            ans+=curr;
            return;
        }
        ans += (n/(double)(n+m))*curr;
        if(m >= 3) {
           dfs(index + 1, max, (m/(double)(n+m))*curr*((m-1)/(double)(n+m-1))*((m-2)/(double)(n+m-2))
                   //new BigDecimal(m).divide(new BigDecimal(n + m),
//                    8,BigDecimal.ROUND_CEILING).multiply(curr).multiply
//                    (new BigDecimal(m - 1)
//                            .divide(new BigDecimal(n+m-1),8,BigDecimal.ROUND_CEILING)).multiply(new BigDecimal(m-2).divide(new BigDecimal(n+m-2),8,
//                                    BigDecimal.ROUND_CEILING))
            , n, m - 3);
        }
        if(m >= 2)
        dfs(index+1,max,(m/(double)(n+m))*curr*((m-1)/(double)(n+m-1))*((n-1)/(double)(n+m-2)),n-1,m-2);
    }

    private static void dfs3(int u, int p, int l) {
        t = Math.max(t, a[u] + l);
        for(int v : graph[u]) {
            if(v == p) continue;
            dfs3(v, u, Math.min(2, l + 1));
        }
    }

    private static void dfs2(int u, int p, int w) {
        for(int v : graph[u]) {
            if(v == p || f[v] == -1) continue;
            if(f[v] + 1 == f[u]) {
                int w2 = -1;
                if(w != -1 || s[u] != -1) w2 = Math.max(w, s[u]) + 1;
                if(w2 != -1) res = Math.max(res, f[v] + w2);
                dfs2(v, u, w2);
            } else {
                int w2 = Math.max(w, f[u]) + 1;
                res = Math.max(res, f[v] + w2);
                dfs2(v, u, w2);
            }
        }
    }

    private static void dfs1(int u, int p) {
        if(a[u] == t) {
            f[u] = 0;
            q = u;
        }
        for(int v : graph[u]) {
            if(v == p) continue;
            dfs1(v, u);
            if(f[v] == -1) continue;
            if(f[v] + 1 > f[u]) {
                s[u] = f[u];
                f[u] = f[v] + 1;
            } else {
                s[u] = Math.max(s[u], f[v] + 1);
            }
        }
    }
    private static boolean helper1(int index, int k, long target) {
        if (k > n - index) {
            return false;
        }
        if (index == n) {
            return true;
        }
        if (k == 0) {
            return false;
        }
        if (memo[index][k] != null) {
            return memo[index][k];
        }
        long sum = 0;
        for (int i = index; i < n; i++) {
            sum += a[i];
            if ((sum & target) != target) {
                continue;
            }
            if (helper1(i + 1, k - 1, target)) {
                return memo[index][k] = true;
            }
        }
        return memo[index][k] = false;
    }

    private static boolean helper(int idx, int k, long target) {
        if (k > n - idx) return false;
        if (idx == n) return true;
        if (k == 0) return false;
        if (memo[idx][k] != null) return memo[idx][k];
        long sum = 0;
        for (int i = idx; i < n; i++) {
            sum += a[i];
            if ((sum & target) != target) continue;
            if (helper(i + 1, k - 1, target)) return memo[idx][k] = true;
        }
        return memo[idx][k] = false;
    }

    static class Node1 {

        Node1[] next = new Node1[2];
    }

    static Node1 root = new Node1();

    private static void add(long x) {
        Node1 curr = root;
        for (int i = 39; i >= 0; i--) {
            int a = (int) ((x >> i) & 1);
            if (curr.next[a] == null) {
                curr.next[a] = new Node1();
            }
            curr = curr.next[a];
        }
    }

    private static long get(long x) {
        long ans = 0;
        Node1 curr = root;
        for (int i = 39; i >= 0; i--) {
            if (((x >> i) & 1) == 0) {
                if (curr.next[1] != null) {
                    ans = ans * 2 + 1;
                    curr = curr.next[1];
                } else {
                    ans = ans * 2;
                    curr = curr.next[0];
                }
            } else {
                if (curr.next[0] != null) {
                    ans = ans * 2 + 1;
                    curr = curr.next[0];
                } else {
                    ans = ans * 2;
                    curr = curr.next[1];
                }
            }
        }
        return ans;
    }


    public static boolean isPrime(int x) {
        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }

    public void solve(int n, int[] c, int[] s) {

    }

    static class Node {
        Node left;
        Node right;
        int v;
        int l;
        int r;

        public Node(int x, int y) {
            l = x;
            r = y;
            v = Integer.MAX_VALUE;
        }

        public int find(int x, int y) {
            if (x > y) return Integer.MAX_VALUE;
            if (l == x && r == y) return v;
            int m = (l + r) >> 1;
            if (left == null) left = new Node(l, m);
            if (right == null) right = new Node(m + 1, r);
            if (m >= y) {
                return left.find(x, y);
            } else if (m < x) {
                return right.find(x, y);
            } else {
                return Math.min(left.find(x, m), right.find(m + 1, y));
            }
        }

        public void update(int x, int y) {
            v = Math.min(v, y);
            if (l == r) return;
            int m = (l + r) >> 1;
            if (left == null) left = new Node(l, m);
            if (right == null) right = new Node(m + 1, r);
            if (m >= x) {
                left.update(x, y);
            } else {
                right.update(x, y);
            }
        }
    }


    private static void delete(long a, int m, Trie1 trie) {
        if (m == -1) return;
        int bit = (a & (1L << m)) == 0 ? 0 : 1;
        if (bit == 0) {
            trie.left.flow--;
            if (trie.left.flow == 0) {
                trie.left = null;
            } else {
                delete(a, m - 1, trie.left);
            }
        } else {
            trie.right.flow--;
            if (trie.right.flow == 0) {
                trie.right = null;
            } else {
                delete(a, m - 1, trie.right);
            }
        }
    }

    private static long query(long a, int m, Trie1 trie) {
        if (trie == null || m == -1) return 0;
        int bit = (a & (1L << m)) == 0 ? 0 : 1;
        long res = 0;
        if (bit == 0 && trie.right != null || bit == 1 && trie.left != null) {
            res += 1L << m;
            Trie1 child = bit == 0 ? trie.right : trie.left;
            return res + query(a, m - 1, child);
        }
        Trie1 child = bit == 0 ? trie.left : trie.right;
        return res + query(a, m - 1, child);
    }

    private static void add(long a, int m, Trie1 trie) {
        if (m == -1) return;
        int bit = (a & (1L << m)) == 0 ? 0 : 1;
        Trie1 child = bit == 0 ? trie.left : trie.right;
        if (bit == 0) {
            if (trie.left == null) trie.left = new Trie1();
            trie.left.flow++;
            add(a, m - 1, trie.left);
        } else {
            if (trie.right == null) trie.right = new Trie1();
            trie.right.flow++;
            add(a, m - 1, trie.right);
        }
    }

    static private class Trie1 {
        Trie1 left, right;
        int flow;
    }
    static class Trie{
        Trie[] next;
        public Trie(){
            next = new Trie[2];
        }
    }

}


class Fastinanner3 {
    private StringTokenizer tokenizer;
    private BufferedReader reader;

    public Fastinanner3(InputStream inputStream) {
        reader = new BufferedReader(new InputStreamReader(inputStream));
    }

    public String next() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            String line;
            try {
                line = reader.readLine();
            } catch (IOException e) {
                return null;
            }
            if (line == null) {
                return null;
            }
            tokenizer = new StringTokenizer(line);
        }
        return tokenizer.nextToken();
    }

    public String nextLine() {
        String line;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            return null;
        }
        return line;
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }

    public double nextDouble() {
        return Double.parseDouble(next());
    }

    public void close() {
        try {
            reader.close();
        } catch (IOException e) {

        }
    }
}

