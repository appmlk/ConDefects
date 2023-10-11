import java.io.*;
import java.util.*;

class Main{
    final static long INF = Long.MAX_VALUE / 2;
    final static int MOD = 1_000_000_007;
    final static int SIZE = 1_000_000;
    long[] fac = new long[SIZE];
    long[] inv = new long[SIZE];
    long[] finv = new long[SIZE];
    FastScanner sc = new FastScanner();

    public static void main(String[] args) {
        new Main().solve();
    }

    void solve(){

        long X = sc.nextLong();
        long A = sc.nextLong();
        long D = sc.nextLong();
        long N = sc.nextLong();

        long l = A + D * (N - 1);

        long max = Math.max(A, l);
        long min = Math.min(A, l);

        if(X <= min) System.out.println(Math.abs(min - X));
        else if(X >= max) System.out.println(Math.abs(max - X));
        else System.out.println(Math.min(Math.abs((X - A) % D), Math.abs(D - (X - A) % D)));

    }

    long gcd(long a, long b){ // return aとbの最大公約数
        if(b == 0){
          return a;
        }
        return gcd(b, a % b);
    }

    long lcm(long a, long b){ // return aとbの最小公倍数
        return a * b / gcd(a, b);
    }

    long inv(long a){ // return aの逆元 (mod MOD)
        return pow(a, MOD - 2);
    }

    long pow(long a, long r){ // return a^r (mod MOD)
        long sum = 1;
        while(r > 0){
            if((r & 1) == 1){ // 2進数表記で末尾1の時
                sum *= a;
                sum %= MOD;
            }
            a *= a;
            a %= MOD;
            r >>= 1;
        }
        return sum;
    }

    long modFact(long n){ // retur n! (mod MOD)
        if(n == 0){
            return 1;
        }
        return n * modFact(n - 1) % MOD;
    }

    long fact(long n){ // return n!
        if(n == 0){
            return 1;
        }
        return n * fact(n - 1);
    }

    void initCOMB(){
        fac[0] = fac[1] = 1;
        inv[1] = 1;
        finv[0] = finv[1] = 1;
        for(int i = 2; i < SIZE; i++){
            fac[i] = fac[i - 1] * i % MOD;
            inv[i] = MOD - inv[MOD % i] * (MOD / i) % MOD;
            finv[i] = finv[i - 1] * inv[i] % MOD;
        }
    }

    long modComb(int n, int r){ // return nCr (先にinitCOMB()必要)
        if(n < r || n < 0 || r < 0) return 0;
        return fac[n] * finv[r] % MOD * finv[n - r] % MOD;
    }

    long comb(long n, long r){ // return nCr
        long num = 1;
        for(long i = 1; i <= r; i++){
            num = num * (n - i + 1) / i;
        }
        return num;
    }

    boolean isPrime(long a){ // aの素数判定
        if(a <= 1) return false;
        for(int i = 2; i * i <= a; i++){
            if(a % i == 0) return false;
        }
        return true;
    }

    int lowerBound(long[] a, long v){ // return 配列a内のv以上の要素の内最低の要素のイテレータ
        int r = a.length;
        int l = -1;
        while(r - l > 1){
            int mid = (r + l) / 2;
            if(a[mid] >= v){
                r = mid;
            }else{
                l = mid;
            }
        }
        return r;
    }

    int lowerBound(List<Long> a, long v){ // return 配列a内のv以上の要素の内最低の要素のイテレータ
        int r = a.size();
        int l = -1;
        while(r - l > 1){
            int mid = (r + l) / 2;
            if(a.get(mid) >= v){
                r = mid;
            }else{
                l = mid;
            }
        }
        return r;
    }

    int upperBound(long[] a, long v){ // return 配列a内のvより大きい要素の内最低の要素のイテレータ
        int r = a.length;
        int l = -1;
        while(r - l > 1){
            int mid = (r + l) / 2;
            if(a[mid] > v){
                r = mid;
            }else{
                l = mid;
            }
        }
        return r;
    }

    String nextPermutation(String s){ // return sの次の順列
        ArrayList<Character> list = new ArrayList<>();
        for(int i = 0; i < s.length(); i++) list.add(s.charAt(i));

        int pivotPos = -1;
        char pivot = 0;
        for(int i = list.size() - 2; i >= 0; i--){
            if(list.get(i) < list.get(i+1)){
    			pivotPos = i;
    			pivot = list.get(i);
    			break;
    		}
    	}

        if(pivotPos == -1 && pivot == 0) return null;

        int L = pivotPos + 1;
        int R = list.size() - 1;
    	int minPos = -1;
    	char min = Character.MAX_VALUE;
    	for(int i = R; i >= L; i--){
    		if(pivot < list.get(i)){
    			if(list.get(i) < min){
    				min = list.get(i);
    				minPos = i;
    			}
    		}
    	}

    	Collections.swap(list, pivotPos, minPos);
    	Collections.sort(list.subList(L, R + 1));

    	StringBuilder sb = new StringBuilder();
    	for(int i=0; i<list.size(); i++) sb.append(list.get(i));

    	return sb.toString();
    }

    boolean nextPermutation(long[] a){
        for(int i = a.length - 1; i > 0; i--){
            if(a[i - 1] < a[i]){
                int swapIndex = find(a[i - 1], a, i, a.length - 1);
                long temp = a[swapIndex];
                a[swapIndex] = a[i - 1];
                a[i - 1] = temp;
                Arrays.sort(a, i, a.length);
                return true;
            }
        }
        return false;
    }

    int find(long dest, long[] a, int s, int e){
        if(s == e){
            return s;
        }
        int m = (s + e + 1) / 2;
        return a[m] <= dest ? find(dest, a, s, m - 1) : find(dest, a, m, e);
    }

    void elimination(int[][] a, int[] b) {
        int n = a.length;
        double f;
        for(int k = 0; k < n - 1; k++){
            for(int i = k + 1; i < n; i++){
                f = - a[i][k] / a[k][k];
                for(int j = k + 1; j < n; j++){
                    a[i][j] += f * a[k][j];
                }
                b[i] += f * b[k];
            }
            for(int i = n - 1; i >= 0; i--){
                for(int j = i + 1; j < n; j++){
                    b[i] -= a[i][j] * b[j];
                }
                b[i] = b[i] / a[i][i];
            }
        }
    }
}

class SegmentTree{

    //------------------------------------------------------------
    // 2 * n - 1 : 木全体のノード数
    // i + n - 1 : 配列のi番目が対応するノードの番号
    // 2 * i + 1, 2 * i + 2 : i番目のノードの子ノードの番号
    // (i - 1) / 2 : i番目のノードの親ノードの番号
    //
    // int n = sc.nextInt();
    // long[] a = new long[n];
    // for(int i = 0; i < n; i++) a[i] = sc.nextLong();
    // SegmentTree st = new SegmentTree(a);
    // int l = sc.nextInt() - 1;
    // int r = sc.nextInt() - 1;
    // System.out.println(st.query(l, r));
    //------------------------------------------------------------

    final static long INF = Long.MAX_VALUE / 2;

    // long e = INF; // 単位元
    long e = 0;
    long func(long a, long b){ // 処理
        // return Math.min(a, b);
        return a + b;
    }

    int n; // 配列の要素数を超える最小の2のべき乗
    long[] node;

    SegmentTree(long[] a){
        init(a);
    }

    void init(long[] a){ // 配列aで初期化
        n = 1;
        while(n < a.length){
            n *= 2;
        }
        node = new long[2 * n - 1];
        Arrays.fill(node, e);
        for(int i = 0; i < a.length; i++){
            node[i + n - 1] = a[i];
        }
        for(int i = n - 2; i >= 0; i--){
            node[i] = func(node[2 * i + 1], node[2 * i + 2]);
        }
    }

    void update(int p, long v){ // 配列のp番目をvに変更し、木全体を更新
        p = p + n - 1;
        node[p] = v;
        while(p > 0){
            p = (p - 1) / 2;
            node[p] = func(node[2 * p + 1], node[2 * p + 2]);
        }
    }

    long query(int a, int b){ // 区間[a, b)についてクエリを処理
        return query(a, b, 0, 0, n);
    }

    long query(int a, int b, int k, int l, int r){
        if(r <= a || b <= l) return e;
        if(a <= l && r <= b) return node[k];
        return func(query(a, b, 2 * k + 1, l, (l + r) / 2), query(a, b, 2 * k + 2, (l + r) / 2, r));
    }

}



class UnionFindTree{

    //------------------------------------------------------------
    // int n = sc.nextInt();
    // int q = sc.nextInt();
    // UnionFindTree uft = new UnionFindTree(n);
    // List<String> ans = new ArrayList<>();
    // for(int i = 0; i < q; i++){
    //     int p = sc.nextInt(); // 0 : union, 1 : same
    //     if(p == 0){
    //         int a = sc.nextInt() - 1;
    //         int b = sc.nextInt() - 1;
    //         uft.union(a, b);
    //     }else if(p == 1){
    //         int a = sc.nextInt() - 1;
    //         int b = sc.nextInt() - 1;
    //         if(uft.same(a, b)) ans.add("Yes");
    //         else ans.add("No");
    //     }
    // }
    // for(String s : ans){
    //     System.out.println(s);
    // }
    //------------------------------------------------------------

    int[] parent; // インデックスにとノードを対応させ、そのルートノードのインデックスを格納
    int[] rank; // parentと同様に、木の高さを格納

    UnionFindTree(int size){
        parent = new int[size];
        rank = new int[size];

        for(int i = 0; i < size; i++){
            makeSet(i);
        }
    }

    void makeSet(int i){
        parent[i] = i;
        rank[i] = 0;
    }

    void union(int x, int y){
        int xRoot = find(x);
        int yRoot = find(y);

        if(rank[xRoot] > rank[yRoot]){ // xが属する木の方が大きい場合
            parent[yRoot] = xRoot;
        }else if(rank[xRoot] < rank[yRoot]){
            parent[xRoot] = yRoot; // yの親をxに更新
        }else{
            parent[yRoot] = xRoot;
            rank[xRoot]++; // 同じ高さの木がルートの子として着くから大きさ++;
        }
    }

    int find(int i){ // iの属するルートを返す
        if(i != parent[i]){
            parent[i] = find(parent[i]);
        }
        return parent[i];
    }

    boolean same(int x, int y){ // xとyが同じ木に属しているかを返す
        return find(x) == find(y);
    }

}

class FordFulkerson {
    //------------------------------------------------------------
    // int n = sc.nextInt();
    // int m = sc.nextInt();
    // FordFulkerson ff = new FordFulkerson(n);
    //
    // for(int i = 0; i < m; i++){
    //     int a = sc.nextInt() - 1;
    //     int b = sc.nextInt() - 1;
    //     long c = sc.nextLong();
    //     ff.addEdge(a, b, c);
    // }
    //
    // System.out.println(ff.solve(0, n - 1));
    //------------------------------------------------------------

    final static long INF = Long.MAX_VALUE / 2;

    List<HashMap<Integer, Long>> l;
    boolean[] seen;

    FordFulkerson(int n) {
        l = new ArrayList<>();
        for (int i = 0; i < n; i++) l.add(new HashMap<>());
        seen = new boolean[n];
    }

    void addEdge(int from, int to, long cap) {
        l.get(from).put(to, cap);
        l.get(to).put(from, 0L);
    }

    void runFlow(int from, int to, long flow) {
        l.get(from).put(to, l.get(from).get(to) - flow);
        l.get(to).put(from, l.get(to).get(from) + flow);
    }

    long ffdfs(int v, int t, long f) {
        if (v == t) return f;
        seen[v] = true;

        for (int next: l.get(v).keySet()) {
            if (seen[next]) continue;
            if (l.get(v).get(next) == 0) continue;

            long flow = ffdfs(next, t, Math.min(f, l.get(v).get(next)));

            if (flow == 0) continue;

            runFlow(v, next, flow);
            return flow;
        }

        return 0;
    }

    int solve(int s, int t) {
        int res = 0;

        while (true) {
            Arrays.fill(seen, false);
            long flow = ffdfs(s, t, INF);

            if(flow == 0) return res;

            res += flow;
        }
    }
}



class Pair implements Comparable<Pair>{
    long a, b;
    public Pair(long a, long b){
        this.a = a;
        this.b = b;
    }

    @Override
    public int compareTo(Pair p){
        if(this.b < p.b) return -1;
        else if(this.b > p.b) return 1;
        else return 0;
    }

    @Override
    public String toString(){
        return a + " " + b;
    }
}



class Triple implements Comparable<Triple>{
    long a, b, c;
    public Triple(long a, long b, long c){
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public int compareTo(Triple q){
        if(this.c < q.c) return -1;
        else if(this.c > q.c) return 1;
        else return 0;
    }

    @Override
    public String toString(){
        return a + " " + b + " " + c;
    }
}



class Quadruple implements Comparable<Quadruple>{
    long a, b, c, d;
    public Quadruple(long a, long b, long c, long d){
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    @Override
    public int compareTo(Quadruple q){
        if(this.d < q.d) return -1;
        else if(this.d > q.d) return 1;
        else return 0;
    }

    @Override
    public String toString(){
        return a + " " + b + " " + c + " " + d;
    }
}




class FastScanner {
    private final InputStream in = System.in;
    private final byte[] buffer = new byte[1024];
    private int ptr = 0;
    private int buflen = 0;
    private boolean hasNextByte(){
        if(ptr < buflen){
            return true;
        }else{
            ptr = 0;
            try{
                buflen = in.read(buffer);
            }catch(IOException e){
                e.printStackTrace();
            }
            if(buflen <= 0){
                return false;
            }
        }
        return true;
    }
    private int readByte(){
        if(hasNextByte()) return buffer[ptr++];
        else return -1;
    }
    private static boolean isPrintableChar(int c){
        return 33 <= c && c <= 126;
    }
    public boolean hasNext(){
        while(hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++;
        return hasNextByte();
    }
    public String next(){
        if(!hasNext()) throw new NoSuchElementException();
        StringBuilder sb = new StringBuilder();
        int b = readByte();
        while(isPrintableChar(b)){
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }
    public long nextLong(){
        if(!hasNext()) throw new NoSuchElementException();
        long n = 0;
        boolean minus = false;
        int b = readByte();
        if(b == '-'){
            minus = true;
            b = readByte();
        }
        if(b < '0' || '9' < b){
            throw new NumberFormatException();
        }
        while(true){
            if('0' <= b && b <= '9'){
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
    public int nextInt(){
        long nl = nextLong();
        if (nl < Integer.MIN_VALUE || nl > Integer.MAX_VALUE) throw new NumberFormatException();
        return (int) nl;
    }
    public double nextDouble(){
        return Double.parseDouble(next());
    }
}
