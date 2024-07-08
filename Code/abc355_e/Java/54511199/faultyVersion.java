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
        int l = sc.nextInt();
        int r = sc.nextInt();
        int N = (1 << n) + 1;
        graph G = new graph(N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < n; j++) {
                int s = 1 << j;
                if (i % s == 0 && i + s < N) {
                    G.addUndirectedEdge(i, i + s, 1);
                }
            }
        }
        int[] par = new int[N];
        int[] L = G.bfs(l, par);
        ArrayDeque<Integer> q = new ArrayDeque<>();
        int now = r + 1;
        while (now != l) {
            q.addFirst(now);
            now = par[now];
        }
        long ans = 0;
        UnionFindTree ut = new UnionFindTree(N);
        while (!q.isEmpty()) {
            int v = q.poll();
            int x = Integer.toBinaryString(Math.abs(v - now)).length() - 1;
            int y = Math.min(now, v) / (1 << x);
            System.out.println("? " + x + " " + y);
            System.out.flush();
            int z = sc.nextInt();
            if (v - now < 0) {
                ans -= z;
            }else {
                ans += z;
            }
            now = v;
        }
        System.out.println("!" + " " + ((ans % 100) + 100) % 100);
    }
    static class Edge implements Comparable<Edge>{
        int to;
        long v;
        int from;
        public Edge(int to,long v,int from) {
            this.to=to;
            this.v=v;
            this.from=from;
        }
        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Edge) {
                Edge other = (Edge) obj;
                return other.to==this.to && other.v==this.v&&other.from==this.from;
            }
            return false;
        }//同値の定義
        @Override
        public int hashCode() {
            return Objects.hash(this.to,this.v,this.from);
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
            list[from].add(new Edge(to, w, from));
        }
        void addUndirectedEdge(int from,int to,long w) {
            addEdge(from, to, w);
            addEdge(to, from, w);
        }
        int[] bfs(int s, int[] par) {
            int[] l=new int[size];
            for (int i = 0; i < l.length; i++) {
                l[i]=-1;
            }
            l[s] = 0;
            par[s] = -1;
            ArrayDeque<Integer> qArrayDeque=new ArrayDeque<Integer>();
            qArrayDeque.add(s);
            while (!qArrayDeque.isEmpty()) {
                int v=qArrayDeque.poll();
                for (Edge nv: list[v]) {
                    if (l[nv.to]==-1) {
                        par[nv.to]=v;
                        l[nv.to]=l[v]+1;
                        qArrayDeque.add(nv.to);
                    }
                }
            }
            return l;
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