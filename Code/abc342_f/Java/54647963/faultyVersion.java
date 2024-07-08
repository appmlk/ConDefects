import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.*;


public class Main {
    static final long MOD1=1000000007;
    static final long MOD=998244353;
    static final int NTT_MOD1 = 998244353;
    static final int NTT_MOD2 = 1053818881;
    static final int NTT_MOD3 = 1004535809;
    static long MAX = 1000000000000000010L;//10^18
    public static void main(String[] args){
        //PrintWriter out = new PrintWriter(System.out);
        InputReader sc=new InputReader(System.in);
        int n = sc.nextInt();
        int L = sc.nextInt();
        int D = sc.nextInt();
        double[] dp = new double[Math.max(n + 1, L - 1 + D)];
        int s = dp.length;
        SegTree<Double> seg = new SegTree<>(s, (a,b)->a+b, 0.0);
        seg.set(0, 1.0);
        double d = (double) D;
        for (int i = 1; i < s; i++) {
            int l = Math.max(0, i - D);
            int r = Math.min(L, i);
            dp[i] = l <= r ? seg.prod(l, r) / d : 0.0;
            seg.set(i, dp[i]);
        }
        double[] dp2 = new double[n + 1];
        double[] sum = new double[n + 2];
        for (int i = n; i >= 0; i--) {
            double cand1 = (i >= L ? seg.prod(L, i) : 0.0) + seg.prod(n + 1, s);
            double cand2 = (- sum[Math.min(n + 1, i + D + 1)] + sum[i + 1]) / d;
            dp2[i] = Math.max(cand1, cand2);
            sum[i] = sum[i + 1] + dp2[i];
        }
        System.out.println(dp2[0]);
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
    static long ceil(long a,long b){
        return a%b==0?a/b:a/b+1;
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