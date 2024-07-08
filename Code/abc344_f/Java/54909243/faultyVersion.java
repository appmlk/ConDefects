import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.*;

//ctrl + alt + s でスニペット編集
//iter itm if nibutan sout
public class Main {
    static final long MOD1=1000000007;
    static final long MOD=998244353;
    static final int NTT_MOD1 = 998244353;
    static final int NTT_MOD2 = 1053818881;
    static final int NTT_MOD3 = 1004535809;
    static final int[] dx = {0, 0, 1, 1};
    static final int[] dy = {0, 1, 0, 1};
    static int ans = 50;
    static long MAX = 1000000000000000010L;//10^18
    public static void main(String[] args){
        PrintWriter out = new PrintWriter(System.out);
        InputReader sc=new InputReader(System.in);
        int n = sc.nextInt();
        long[][] p = new long[n][n];
        long[][] r = new long[n][n - 1];
        long[][] d = new long[n - 1][n];
        for (int i = 0; i < n; i++) {
            p[i] = sc.nextLongArray(n);
        }
        for (int i = 0; i < n; i++) {
            r[i] = sc.nextLongArray(n - 1);
        }
        for (int i = 0; i < n - 1; i++) {
            d[i] = sc.nextLongArray(n);
        }
        PairLong[][] dp = new PairLong[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], new PairLong(MAX, MAX));
        }
        dp[0][0] = new PairLong(0, 0);
        long ans = MAX;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == n - 1 && j == n - 1) {
                  continue;
                }
                if (dp[i][j].x == MAX) {
                    continue;
                }
                long[][] dist = new long[n][n];
                for (int k = 0; k < n; k++) {
                    Arrays.fill(dist[k], MAX);
                }
                dist[i][j] = 0;
                for (int k = i; k < n; k++) {
                    for (int l = j; l < n; l++) {
                        if (k + 1 < n) {
                            dist[k + 1][l] = Math.min(dist[k + 1][l], dist[k][l] + d[k][l]);
                        }
                        if (l + 1 < n) {
                            dist[k][l + 1] = Math.min(dist[k][l + 1], dist[k][l] + r[k][l]);
                        }
                    }
                }
                long x = dp[i][j].x;
                long y = dp[i][j].y;
                ans = Math.min(ans, x + ceil(Math.max(dist[n - 1][n - 1] - y, 0), p[i][j]));
                //System.out.println(ans + " " + i + " " + j + " " + x + " " + y + " " + dist[n - 1][n - 1] + " " + p[i][j]);
                for (int k = i; k < n; k++) {
                    for (int l = j; l < n; l++) {
                        if (k == i && l == j) {
                            continue;
                        }
                        if (p[i][j] >= p[k][l]) {
                            continue;
                        }
                        long nx = x + ceil(Math.max(dist[k][l] - y, 0), p[i][j]);
                        long ny = y +  (ceil(Math.max(dist[k][l] - y, 0), p[i][j]) * p[i][j]) - dist[k][l];
                        if (nx < dp[k][l].x || (nx == dp[k][l].x && ny < dp[k][l].y)) {
                            dp[k][l] = new PairLong(nx, ny);
                        }
                    }
                }
            }
        }
        out.println(ans + 2 * n - 2);
        out.flush();
    }

    static class Edge implements Comparable<Edge>{
        int to;
        long v;
        public Edge(int to,long v) {
            this.to=to;
            this.v=v;
        }
        @Override
        public boolean equals(Object obj){
            if(obj instanceof Edge) {
                Edge other = (Edge) obj;
                return other.to==this.to && other.v==this.v;
            }
            return false;
        }//同値の定義
        @Override
        public int hashCode() {
            return Objects.hash(this.to,this.v);
        }
        @Override
        public int compareTo( Edge p2 ){
            if (this.v>p2.v) {
                return 1;
            }
            else if (this.v<p2.v) {
                return -1;
            }
            else {
                return 0;
            }
        }
    }
    static class graph{
        ArrayList<Edge>[] list;
        int size;
        long INF=Long.MAX_VALUE/2;
        int[] color;
        @SuppressWarnings("unchecked")
        public graph(int n) {
            size = n;
            list = new ArrayList[n];
            color =new int[n];
            for(int i=0;i<n;i++){
                list[i] = new ArrayList<Edge>();
            }
        }
        void addEdge(int from,int to,long w) {
            list[from].add(new Edge(to,w));
        }
        void addUndirectedEdge(int from,int to,long w) {
            addEdge(from, to, w);
            addEdge(to, from, w);
        }
        boolean nibugraph(int s,int colors) {
            color[s]=colors;
            for (Edge nv: list[s]) {
                if (color[nv.to]==colors) {
                    return false;
                }
                if (color[nv.to]==0&&!nibugraph(nv.to, -colors)) {
                    return false;
                }
            }
            return true;
        }
    }
    static class BinaryTrie {
        private final static int K = 31;
        int[] l;
        int[] r;
        int[] count;
        int size;
        int node_sum = 0;
        public BinaryTrie(int size) {
            this.l = new int[(K + 1) * (size + 1)];
            this.r = new int[(K + 1) * (size + 1)];
            this.count = new int[(K + 1) * (size + 1)];
            this.size = size;
            Arrays.fill(l, -1);
            Arrays.fill(r, -1);
        }
        public int size() {
            return count[0];
        }

        public boolean isEmpty() {
            return size() == 0;
        }

        public int count(int x) {
            int node_id = 0;
            for (int i = K; i >= 0; i--) {
                if (((x >> i) & 1) == 0) {
                    if (l[node_id] == -1) {
                        return 0;
                    }
                    node_id = l[node_id];
                } else {
                    if (r[node_id] == -1) {
                        return 0;
                    }
                    node_id = r[node_id];
                }
            }
            return count[node_id];
        }

        public void add(int x) {
            add(x, 1);
        }

        public int get(int i) {
            if (i < 0 || size() <= i) {
                throw new IndexOutOfBoundsException();
            }
            int node_id = 0;
            int val = 0;
            for (int j = 0; j <= K; j++) {
                val *= 2;
                int lc = l[node_id] == -1 ? 0 : count[l[node_id]];
                if (i < lc) {
                    node_id = l[node_id];
                } else {
                    val++;
                    i -= lc;
                    node_id = r[node_id];
                }
            }
            return val;
        }

        public void add(int x, int count) {
            if (x < 0 || count < 0) {
                throw new IllegalArgumentException();
            }
            int node_id = 0;
            this.count[node_id] += count;
            for (int i = K; i >= 0; i--) {
                if (((x >> i) & 1) == 0) {
                    if (l[node_id] == -1) {
                        node_sum++;
                        l[node_id] = node_sum;
                    }
                    node_id = l[node_id];
                    this.count[node_id] += count;
                } else {
                    if (r[node_id] == -1) {
                        node_sum++;
                        r[node_id] = node_sum;
                    }
                    node_id = r[node_id];
                    this.count[node_id] += count;
                }
            }
        }

        public boolean remove(int x) {
            return remove(x, 1);
        }

        public boolean remove(int x, int count) {
            if (x < 0 || count < 0) {
                throw new IllegalArgumentException();
            }
            count = Math.min(count, count(x));
            if (count == 0) {
                return false;
            }
            int node_id = 0;
            this.count[node_id] -= count;
            for (int i = K; i >= 0; i--) {
                if (((x >> i) & 1) == 0) {
                    this.count[l[node_id]] -= count;
                    if (this.count[l[node_id]] == 0) {
                        l[node_id] = -1;
                        return true;
                    }
                    node_id = l[node_id];
                } else {
                    this.count[r[node_id]] -= count;
                    if (this.count[r[node_id]] == 0) {
                        r[node_id] = -1;
                        return true;
                    }
                    node_id = r[node_id];
                }
            }
            return true;
        }

        public long min() {
            if(isEmpty()) throw new ArrayIndexOutOfBoundsException();
            int node_id = 0;
            long val = 0;
            for (int j = 0; j <= K; j++) {
                val *= 2;
                if (l[node_id] != -1) {
                    node_id = l[node_id];
                } else {
                    node_id = r[node_id];
                    val++;
                }
            }
            return val;
        }

        public int xormin(int x) {
            if(isEmpty()) throw new ArrayIndexOutOfBoundsException();
            int node_id = 0;
            int val = 0;
            for (int i = K; i >= 0; i--) {
                val *= 2;
                if (((x >> i) & 1) == 1) {
                    if (r[node_id] == -1) {
                        node_id = l[node_id];
                    } else {
                        node_id = r[node_id];
                        val++;
                    }
                }else {
                    if (l[node_id] != -1) {
                        node_id = l[node_id];
                    } else {
                        node_id = r[node_id];
                        val++;
                    }
                }
            }
            return val;
        }

        public int max() {
            if(isEmpty()) throw new ArrayIndexOutOfBoundsException();
            int node_id = 0;
            int val = 0;
            for (int j = 0; j <= K; j++) {
                val *= 2;
                if (r[node_id] == -1) {
                    node_id = l[node_id];
                } else {
                    node_id = r[node_id];
                    val++;
                }
            }
            return val;
        }

        public int xormax(long x) {
            if(isEmpty()) throw new ArrayIndexOutOfBoundsException();
            int node_id = 0;
            int val = 0;
            for (int i = K; i >= 0; i--) {
                val *= 2;
                if (((x >> i) & 1) == 0) {
                    if (r[node_id] == -1) {
                        node_id = l[node_id];
                    } else {
                        node_id = r[node_id];
                        val++;
                    }
                }else {
                    if (l[node_id] != -1) {
                        node_id = l[node_id];
                    } else {
                        node_id = r[node_id];
                        val++;
                    }
                }
            }
            return val;
        }//未verify

        public int lowerBound(int x) {
            if (isEmpty()) {
                return 0;
            }
            int node_id = 0;
            int i = 0;
            for (int j = K; j >= 0; j--) {
                if (((x >> j) & 1) == 0) {
                    node_id = l[node_id];
                } else {
                    i += l[node_id] == -1 ? 0 : count[l[node_id]];
                    node_id = r[node_id];
                }
                if (node_id == -1) {
                    return i;
                }
            }
            return i;
        }

        public int xorlowerBound(int x, int xor) {
            if (isEmpty()) {
                return 0;
            }
            int node_id = 0;
            int i = 0;
            for (int j = K; j >= 0; j--) {
                if (((xor >> j) & 1) == 0) {
                    if (((x >> j) & 1) == 0) {
                        node_id = l[node_id];
                    } else {
                        i += l[node_id] == -1 ? 0 : count[l[node_id]];
                        node_id = r[node_id];
                    }
                }else {
                    if (((x >> j) & 1) == 0) {
                        node_id = r[node_id];
                    } else {
                        i += r[node_id] == -1 ? 0 : count[r[node_id]];
                        node_id = l[node_id];
                    }
                }
                if (node_id == -1) {
                    return i;
                }
            }
            return i;
        }
    }
    static class SegTree<S> {
        final int MAX;

        final int N;
        final java.util.function.BinaryOperator<S> op;
        final S E;

        final S[] data;

        @SuppressWarnings("unchecked")
        public SegTree(int n, java.util.function.BinaryOperator<S> op, S e) {
            this.MAX = n;
            int k = 1;
            while (k < n) k <<= 1;
            this.N = k;
            this.E = e;
            this.op = op;
            this.data = (S[]) new Object[N << 1];
            java.util.Arrays.fill(data, E);
        }

        public SegTree(S[] dat, java.util.function.BinaryOperator<S> op, S e) {
            this(dat.length, op, e);
            build(dat);
        }

        private void build(S[] dat) {
            int l = dat.length;
            System.arraycopy(dat, 0, data, N, l);
            for (int i = N - 1; i > 0; i--) {
                data[i] = op.apply(data[i << 1 | 0], data[i << 1 | 1]);
            }
        }

        public void set(int p, S x) {
            exclusiveRangeCheck(p);
            data[p += N] = x;
            p >>= 1;
            while (p > 0) {
                data[p] = op.apply(data[p << 1 | 0], data[p << 1 | 1]);
                p >>= 1;
            }
        }

        public S get(int p) {
            exclusiveRangeCheck(p);
            return data[p + N];
        }

        public S prod(int l, int r) {
            if (l > r) {
                throw new IllegalArgumentException(
                        String.format("Invalid range: [%d, %d)", l, r)
                );
            }
            inclusiveRangeCheck(l);
            inclusiveRangeCheck(r);
            S sumLeft = E;
            S sumRight = E;
            l += N; r += N;
            while (l < r) {
                if ((l & 1) == 1) sumLeft = op.apply(sumLeft, data[l++]);
                if ((r & 1) == 1) sumRight = op.apply(data[--r], sumRight);
                l >>= 1; r >>= 1;
            }
            return op.apply(sumLeft, sumRight);
        }

        public S allProd() {
            return data[1];
        }

        public int maxRight(int l, java.util.function.Predicate<S> f) {
            inclusiveRangeCheck(l);
            if (!f.test(E)) {
                throw new IllegalArgumentException("Identity element must satisfy the condition.");
            }
            if (l == MAX) return MAX;
            l += N;
            S sum = E;
            do {
                l >>= Integer.numberOfTrailingZeros(l);
                if (!f.test(op.apply(sum, data[l]))) {
                    while (l < N) {
                        l = l << 1;
                        if (f.test(op.apply(sum, data[l]))) {
                            sum = op.apply(sum, data[l]);
                            l++;
                        }
                    }
                    return l - N;
                }
                sum = op.apply(sum, data[l]);
                l++;
            } while ((l & -l) != l);
            return MAX;
        }

        public int minLeft(int r, java.util.function.Predicate<S> f) {
            inclusiveRangeCheck(r);
            if (!f.test(E)) {
                throw new IllegalArgumentException("Identity element must satisfy the condition.");
            }
            if (r == 0) return 0;
            r += N;
            S sum = E;
            do {
                r--;
                while (r > 1 && (r & 1) == 1) r >>= 1;
                if (!f.test(op.apply(data[r], sum))) {
                    while (r < N) {
                        r = r << 1 | 1;
                        if (f.test(op.apply(data[r], sum))) {
                            sum = op.apply(data[r], sum);
                            r--;
                        }
                    }
                    return r + 1 - N;
                }
                sum = op.apply(data[r], sum);
            } while ((r & -r) != r);
            return 0;
        }

        private void exclusiveRangeCheck(int p) {
            if (p < 0 || p >= MAX) {
                throw new IndexOutOfBoundsException(
                        String.format("Index %d out of bounds for the range [%d, %d).", p, 0, MAX)
                );
            }
        }

        private void inclusiveRangeCheck(int p) {
            if (p < 0 || p > MAX) {
                throw new IndexOutOfBoundsException(
                        String.format("Index %d out of bounds for the range [%d, %d].", p, 0, MAX)
                );
            }
        }
    }
    static int ceil(int a,int b) {
        if (a%b==0) {
            return a/b;
        }else {
            return a/b+1;
        }
    }
    static int lower_bound(int[] a, int fromIndex, int toIndex, int val) {
        if (val > a[toIndex])
            return toIndex + 1;
        if (val <= a[fromIndex])
            return fromIndex;
        int lb = fromIndex - 1, ub = toIndex;
        while (ub - lb > 1) {
            int mid = (ub - lb)/2+lb;
            if (a[mid] >= val) {
                ub = mid;
            } else {
                lb = mid;
            }
        }
        return ub;
    }
    static int lower_bound(long[] a, int fromIndex, int toIndex, long val) {
        if (val > a[toIndex])
            return toIndex + 1;
        if (val <= a[fromIndex])
            return fromIndex;
        int lb = fromIndex - 1, ub = toIndex;
        while (ub - lb > 1) {
            int mid = (ub - lb)/2+lb;
            if (a[mid] >= val) {
                ub = mid;
            } else {
                lb = mid;
            }
        }
        return ub;
    }
    static class UnionFindTree{
        int[] par;
        int[] rank;
        int[] size;
        long[] diff_weight;
        int arraysize;
        public UnionFindTree(int n) {
            this.par=new int[n];
            this.rank=new int[n];
            this.size=new int[n];
            this.diff_weight=new long[n];
            arraysize=n;
            for (int i = 0; i < arraysize; i++) {
                set(i);
            }
        }
        public void set(int i) {
            par[i]=i;
            rank[i]=0;
            size[i]=1;
            diff_weight[i]=0;
        }
        public void union(int x,int y,long w) {
            w += weight(x);
            w -= weight(y);
            int rootx=find(x);
            int rooty=find(y);
            if (rootx==rooty) {
                return;
            }
            if (rank[rootx]>rank[rooty]) {
                par[rooty]=rootx;
                diff_weight[rooty] = w;
                size[rootx]+=size[rooty];
            }
            else if (rank[rootx]<rank[rooty]) {
                par[rootx]=rooty;
                w=-w;
                diff_weight[rootx] = w;
                size[rooty]+=size[rootx];
            }
            else {
                par[rooty]=rootx;
                diff_weight[rooty] = w;
                rank[rootx]++;
                size[rootx]+=size[rooty];
            }
        }
        public int find(int x) {
            if(par[x] == x) return x;
            int r = find(par[x]);
            diff_weight[x] += diff_weight[par[x]];
            return par[x] = r;
        }
        public long weight(int x) {
            find(x);
            return diff_weight[x];
        }
        public int size(int i) {
            return size[find(i)];
        }
        public long diff(int x, int y) {
            return weight(y) - weight(x);
        }
        public boolean same(int x, int y) {
            return find(x) == find(y);
        }
        public ArrayList<Integer>[] group() {
            ArrayList<Integer>[] group = new ArrayList[arraysize];
            for (int i = 0; i < group.length; i++) {
                group[i] = new ArrayList<>();
            }
            for (int i = 0; i < arraysize; i++) {
                group[find(i)].add(i);
            }
            return group;
        }
    }
    static class PairLong implements Comparable<PairLong>{
        public long x;
        public long y;
        public PairLong(long x,long y) {
            this.x=x;
            this.y=y;
        }
        @Override
        public boolean equals(Object obj) {
            if(obj instanceof PairInt) {
                PairLong other = (PairLong) obj;
                return other.x==this.x && other.y==this.y;
            }
            return false;
        }//同値の定義
        @Override
        public int hashCode() {
            return Objects.hash(this.x,this.y);
        }//これ書かないと正しく動作しない（要　勉強）
        @Override
        public int compareTo( PairLong p){
            if (this.x > p.x) {
                return 1;
            }
            else if (this.x < p.x) {
                return -1;
            }
            else {
                return 0;
            }
        }
    }
    static class PairInt implements Comparable<PairInt>{
        public int x;
        public int y;
        public PairInt(int x,int y) {
            this.x=x;
            this.y=y;
        }
        @Override
        public boolean equals(Object obj) {
            if(obj instanceof PairInt) {
                PairInt other = (PairInt) obj;
                return other.x==this.x && other.y==this.y;
            }
            return false;
        }//同値の定義
        @Override
        public int hashCode() {
            return Objects.hash(this.x,this.y);
        }//これ書かないと正しく動作しない（要　勉強）
        @Override
        public int compareTo( PairInt p){
            if (this.x > p.x) {
                return 1;
            }
            else if (this.x < p.x) {
                return -1;
            }
            else {
                return 0;
            }
        }
    }
    static class FenwickTree{
        private int _n;
        private long[] data;

        public FenwickTree(int n){
            this._n = n;
            data = new long[n];
        }

        /**
         * @verified https://atcoder.jp/contests/practice2/tasks/practice2_b
         * @submission https://atcoder.jp/contests/practice2/submissions/16580495
         */
        public FenwickTree(long[] data) {
            this(data.length);
            build(data);
        }

        public void set(int p, long x){
            add(p, x - get(p));
        }

        public void add(int p, long x){
            assert(0<=p && p<_n);
            p++;
            while(p<=_n){
                data[p-1] += x;
                p += p&-p;
            }
        }
        public long sum(int l, int r){
            assert(0<=l && l<=r && r<=_n);
            return sum(r)-sum(l);
        }

        public long get(int p){
            return sum(p, p+1);
        }

        private long sum(int r){
            long s = 0;
            while(r>0){
                s += data[r-1];
                r -= r&-r;
            }
            return s;
        }

        private void build(long[] dat) {
            System.arraycopy(dat, 0, data, 0, _n);
            for (int i=1; i<=_n; i++) {
                int p = i+(i&-i);
                if(p<=_n){
                    data[p-1] += data[i-1];
                }
            }
        }
    }
    static HashMap<Long, Integer> compress(long[] A,boolean is_duplication){
        HashMap<Long, Integer> hashMap = new HashMap<>();
        long[] tmp = Arrays.copyOf(A, A.length);
        Arrays.sort(tmp);
        int now = 0;
        for (int i = 0; i < A.length; i++) {
            if (!is_duplication && i > 0 && tmp[i] == tmp[i-1]) {
                continue;
            }
            hashMap.put(tmp[i], now++);
        }
        return hashMap;
    }
    static HashMap<Long, Integer> compress(long[] A, long[] B, boolean is_duplication){
        HashMap<Long, Integer> hashMap = new HashMap<>();
        long[] tmp = new long[A.length+B.length];
        for (int i = 0; i < A.length; i++) {
            tmp[i] = A[i];
        }
        for (int i = 0; i < B.length; i++) {
            tmp[i + A.length] = B[i];
        }
        Arrays.sort(tmp);
        int now = 0;
        for (int i = 0; i < tmp.length; i++) {
            if (!is_duplication && i > 0 && tmp[i] == tmp[i-1]) {
                continue;
            }
            hashMap.put(tmp[i], now++);
        }
        return hashMap;
    }
    static int sign(long x) {
        if (x > 0) {
            return 1;
        }else if (x < 0) {
            return -1;
        }else {
            return 0;
        }
    }
    static int sign(int x) {
        if (x > 0) {
            return 1;
        }else if (x < 0) {
            return -1;
        }else {
            return 0;
        }
    }
    static int gcd(int a,int b) {
        if (b==0) {
            return a;
        }
        return gcd(b,a%b);
    }
    static long gcd(long a,long b) {
        if (b==0) {
            return a;
        }
        return gcd(b,a%b);
    }
    static long lcm(long a,long b) {
        long g=gcd(a, b);
        return a/g*b;
    }
    static int lcm(int a,int b) {
        int g=gcd(a, b);
        return a/g*b;
    }
    static long ceil(long a,long b){
        return a % b == 0 ? a / b : a / b + 1;
    }
    static class Permutation {
        public static boolean next(int[] a) {
            int n = a.length;
            int i = n - 1;
            while (i > 0 && a[i - 1] >= a[i]) i--;
            if (i <= 0) return false;

            int j = n - 1;
            while (a[j] <= a[i - 1]) j--;
            swap(a, i - 1, j);

            int k = n - 1;
            while (i < k) swap(a, i++, k--);

            return true;
        }

        private static void swap(int[] a, int i, int j) {
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }
    }
    static  long min(long[] a) {
        long min = Long.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            min = Math.min(min, a[i]);
        }
        return min;
    }
    static  int min(int[] a) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            min = Math.min(min, a[i]);
        }
        return min;
    }
    static class InputReader {
        private InputStream in;
        private byte[] buffer = new byte[1024];
        private int curbuf;
        private int lenbuf;
        public InputReader(InputStream in) {
            this.in = in;
            this.curbuf = this.lenbuf = 0;
        }
        public boolean hasNextByte() {
            if (curbuf >= lenbuf) {
                curbuf = 0;
                try {
                    lenbuf = in.read(buffer);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (lenbuf <= 0)
                    return false;
            }
            return true;
        }

        private int readByte() {
            if (hasNextByte())
                return buffer[curbuf++];
            else
                return -1;
        }

        private boolean isSpaceChar(int c) {
            return !(c >= 33 && c <= 126);
        }

        private void skip() {
            while (hasNextByte() && isSpaceChar(buffer[curbuf]))
                curbuf++;
        }

        public boolean hasNext() {
            skip();
            return hasNextByte();
        }

        public String next() {
            if (!hasNext())
                throw new NoSuchElementException();
            StringBuilder sb = new StringBuilder();
            int b = readByte();
            while (!isSpaceChar(b)) {
                sb.appendCodePoint(b);
                b = readByte();
            }
            return sb.toString();
        }

        public int nextInt() {
            if (!hasNext())
                throw new NoSuchElementException();
            int c = readByte();
            while (isSpaceChar(c))
                c = readByte();
            boolean minus = false;
            if (c == '-') {
                minus = true;
                c = readByte();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res = res * 10 + c - '0';
                c = readByte();
            } while (!isSpaceChar(c));
            return (minus) ? -res : res;
        }

        public long nextLong() {
            if (!hasNext())
                throw new NoSuchElementException();
            int c = readByte();
            while (isSpaceChar(c))
                c = readByte();
            boolean minus = false;
            if (c == '-') {
                minus = true;
                c = readByte();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res = res * 10 + c - '0';
                c = readByte();
            } while (!isSpaceChar(c));
            return (minus) ? -res : res;
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public int[] nextIntArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            return a;
        }
        public double[] nextDoubleArray(int n) {
            double[] a = new double[n];
            for (int i = 0; i < n; i++)
                a[i] = nextDouble();
            return a;
        }
        public long[] nextLongArray(int n) {
            long[] a = new long[n];
            for (int i = 0; i < n; i++)
                a[i] = nextLong();
            return a;
        }

        public char[][] nextCharMap(int n, int m) {
            char[][] map = new char[n][m];
            for (int i = 0; i < n; i++)
                map[i] = next().toCharArray();
            return map;
        }
    }
}