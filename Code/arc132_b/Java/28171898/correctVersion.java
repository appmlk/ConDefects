import java.util.*;

public class Main {

    public static final int MOD998 = 998244353;
    public static final int MOD100 = 1000000007;

    public static void main(String[] args) throws Exception {
        ContestScanner sc = new ContestScanner();
        ContestPrinter cp = new ContestPrinter();
        int N = sc.nextInt();
        int[] P = sc.nextIntArray(N, i -> i - 1);
        if (N == 2) {
            if (P[0] == 0) {
                cp.println(0);
            } else {
                cp.println(1);
            }
        } else {
            if (P[0] + 1 == P[1] || P[1] + 1 == P[2]) {
                cp.println(Math.min(N - (P[N - 1] + 1), P[N - 1] + 3));
            } else {
                cp.println(Math.min(N - (P[0] + 1) + 1, P[0] + 2));
            }
        }
        cp.close();
    }

    //////////////////
    // My Library //
    //////////////////

    public static class SlopeTrick {

        private PriorityQueue<Long> lq = new PriorityQueue<>(Comparator.reverseOrder());
        private PriorityQueue<Long> rq = new PriorityQueue<>();
        private long lshift = 0;
        private long rshift = 0;
        private long min = 0;

        public long getMin() {
            return min;
        }

        public long get(long x) {
            long val = min;
            for (long l : lq) {
                if (l - x > 0) {
                    val += l - x;
                }
            }
            for (long r : rq) {
                if (x - r > 0) {
                    val += x - r;
                }
            }
            return val;
        }

        public long getMinPosLeft() {
            return lq.isEmpty() ? Long.MIN_VALUE : lq.peek() + lshift;
        }

        public long getMinPosRight() {
            return rq.isEmpty() ? Long.MAX_VALUE : rq.peek() + rshift;
        }

        public void addConst(long a) {
            min += a;
        }

        public void addSlopeRight(long a) {
            if (!lq.isEmpty() && lq.peek() + lshift > a) {
                min += lq.peek() + lshift - a;
                lq.add(a - lshift);
                rq.add(lq.poll() + lshift - rshift);
            } else {
                rq.add(a - rshift);
            }
        }

        public void addSlopeLeft(long a) {
            if (!rq.isEmpty() && rq.peek() < a) {
                min += a - rq.peek() - rshift;
                rq.add(a - rshift);
                lq.add(rq.poll() + rshift - lshift);
            } else {
                lq.add(a - lshift);
            }
        }

        public void addAbs(long a) {
            addSlopeLeft(a);
            addSlopeRight(a);
        }

        public void shift(long a) {
            lshift += a;
            rshift += a;
        }

        public void slideLeft(long a) {
            lshift += a;
        }

        public void slideRight(long a) {
            rshift += a;
        }

        public void clearLeft() {
            lq.clear();
        }

        public void clearRight() {
            rq.clear();
        }

        public void clearMin() {
            min = 0;
        }
    }

    public static int zeroOneBFS(int[][][] weighted_graph, int start, int goal) {
        int[] dist = new int[weighted_graph.length];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            int now = queue.poll();
            if (now == goal) {
                return dist[goal];
            }
            for (int[] info : weighted_graph[now]) {
                if (dist[info[0]] > dist[now] + info[1]) {
                    dist[info[0]] = dist[now] + info[1];
                    if (info[1] == 0) {
                        queue.addFirst(info[0]);
                    } else {
                        queue.addLast(info[0]);
                    }
                }
            }
        }
        return -1;
    }

    public static int[] zeroOneBFSAll(int[][][] weighted_graph, int start) {
        int[] dist = new int[weighted_graph.length];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int[] info : weighted_graph[now]) {
                if (dist[info[0]] > dist[now] + info[1]) {
                    dist[info[0]] = dist[now] + info[1];
                    if (info[1] == 0) {
                        queue.addFirst(info[0]);
                    } else {
                        queue.addLast(info[0]);
                    }
                }
            }
        }
        return dist;
    }

    public static long dijkstra(int[][][] weighted_graph, int start, int goal) {
        long[] dist = new long[weighted_graph.length];
        Arrays.fill(dist, 0, dist.length, Long.MAX_VALUE);
        dist[start] = 0;
        PriorityQueue<Pair<Integer, Long>> unsettled = new PriorityQueue<>((u, v) -> (int) (u.cdr - v.cdr));
        unsettled.offer(new Pair<Integer, Long>(start, 0L));
        while (!unsettled.isEmpty()) {
            Pair<Integer, Long> pair = unsettled.poll();
            int now = pair.car;
            if (now == goal) {
                return dist[goal];
            }
            if (dist[now] < pair.cdr) {
                continue;
            }
            for (int[] info : weighted_graph[now]) {
                if (dist[info[0]] > dist[now] + info[1]) {
                    dist[info[0]] = dist[now] + info[1];
                    unsettled.offer(new Pair<Integer, Long>(info[0], dist[info[0]]));
                }
            }
        }
        return -1;
    }

    public static long[] dijkstraAll(int[][][] weighted_graph, int start) {
        long[] dist = new long[weighted_graph.length];
        Arrays.fill(dist, 0, dist.length, Long.MAX_VALUE);
        dist[start] = 0;
        PriorityQueue<Pair<Integer, Long>> unsettled = new PriorityQueue<>((u, v) -> (int) (u.cdr - v.cdr));
        unsettled.offer(new Pair<Integer, Long>(start, 0L));
        while (!unsettled.isEmpty()) {
            Pair<Integer, Long> pair = unsettled.poll();
            int now = pair.car;
            if (dist[now] < pair.cdr) {
                continue;
            }
            for (int[] info : weighted_graph[now]) {
                if (dist[info[0]] > dist[now] + info[1]) {
                    dist[info[0]] = dist[now] + info[1];
                    unsettled.offer(new Pair<Integer, Long>(info[0], dist[info[0]]));
                }
            }
        }
        return dist;
    }

    public static long countLatticePoint(int[] p1, int[] p2, boolean include_end) {
        long difx = p2[0] - p1[0];
        long dify = p2[1] - p1[1];
        if (difx == 0 && dify == 0) {
            return include_end ? 1 : 0;
        }
        if (difx == 0 || dify == 0) {
            return Math.abs(difx + dify) + (include_end ? 1 : -1);
        }
        return MathLib.gcd(difx, dify) + (include_end ? 1 : -1);
    }

    public static long countLatticePoint(long[] p1, long[] p2, boolean include_end) {
        long difx = p2[0] - p1[0];
        long dify = p2[1] - p1[1];
        if (difx == 0 && dify == 0) {
            return include_end ? 1 : 0;
        }
        if (difx == 0 || dify == 0) {
            return Math.abs(difx + dify) + (include_end ? 1 : -1);
        }
        return MathLib.gcd(difx, dify) + (include_end ? 1 : -1);
    }

    // Don't contain same points!
    public static long countLatticePoint(int[] p1, int[] p2, int[] p3, boolean include_edge) {
        int[][] arr = new int[][] { p1, p2, p3 };
        Arrays.sort(arr, Comparator.comparingInt(p -> ((int[]) p)[0]).thenComparingInt(p -> ((int[]) p)[1]));
        if ((p2[0] - p1[0]) * (long) (p3[1] - p2[1]) == (p2[1] - p1[1]) * (long) (p3[0] - p2[0])) {
            return countLatticePoint(arr[0], arr[2], true) - (include_edge ? 0 : 3);
        }
        long b = countLatticePoint(p1, p2, true) + countLatticePoint(p2, p3, true) + countLatticePoint(p3, p1, true)
                - 3;
        long i = (getAreaTriangle(p1, p2, p3) - b) / 2 + 1;
        return include_edge ? i + b : i;

    }

    public static long getAreaTriangle(int[] p1, int[] p2, int[] p3) {
        int x1 = p2[0] - p1[0];
        int x2 = p3[0] - p2[0];
        int y1 = p2[1] - p1[1];
        int y2 = p3[1] - p2[1];
        return Math.abs((long) x1 * y2 - (long) x2 * y1);
    }

    // Don't contain same points!
    public static long countLatticePointConvex(int[][] points, boolean include_edge) {
        if (points.length == 1) {
            return include_edge ? 1 : 0;
        }
        if (points.length == 2) {
            return countLatticePoint(points[0], points[1], include_edge);
        }
        long s = 0;
        for (int n = 1; n < points.length - 1; n++) {
            s += getAreaTriangle(points[0], points[n], points[n + 1]);
        }
        long b = countLatticePoint(points[points.length - 1], points[0], true) - points.length;
        for (int n = 0; n < points.length - 1; n++) {
            b += countLatticePoint(points[n], points[n + 1], true);
        }
        long i = (s - b) / 2 + 1;
        return include_edge ? i + b : i;
    }

    public static class RationalAngle implements Comparable<RationalAngle> {
        public long x;
        public long y;

        public static boolean include_pi_to_minus = true;

        public RationalAngle(long x, long y) {
            if (x == 0) {
                this.x = x;
                if (y == 0) {
                    throw new UnsupportedOperationException("Angle to (0, 0) is invalid.");
                } else {
                    this.y = y > 0 ? 1 : -1;
                }
            } else if (y == 0) {
                this.x = x > 0 ? 1 : -1;
                this.y = 0;
            } else {
                long gcd = MathLib.gcd(x, y);
                this.x = x / gcd;
                this.y = y / gcd;
            }
        }

        public RationalAngle copy() {
            return new RationalAngle(x, y);
        }

        public RationalAngle add(RationalAngle a) {
            RationalAngle res = copy();
            res.addArg(a);
            return res;
        }

        public void addArg(RationalAngle a) {
            long nx = x * a.x - y * a.y;
            long ny = y * a.x + x * a.y;
            x = nx;
            y = ny;
        }

        public RationalAngle sub(RationalAngle a) {
            RationalAngle res = copy();
            res.subArg(a);
            return res;
        }

        public void subArg(RationalAngle a) {
            long nx = x * a.x + y * a.y;
            long ny = y * a.x - x * a.y;
            x = nx;
            y = ny;
        }

        public boolean equals(RationalAngle a) {
            return x == a.x && y == a.y;
        }

        public boolean parallel(RationalAngle a) {
            return x == a.x && y == a.y || x == -a.x && y == -a.y;
        }

        public int rotDirection(RationalAngle trg) {
            if (parallel(trg)) {
                return 0;
            } else if (trg.sub(this).y > 0) {
                return 1;
            } else {
                return -1;
            }
        }

        public RationalAngle minus() {
            return new RationalAngle(x, -y);
        }

        public RationalAngle rev() {
            return new RationalAngle(-x, -y);
        }

        public double toRadian() {
            return Math.atan2(y, x);
        }

        private int toQuad() {
            if (x == 0) {
                if (y > 0) {
                    return 2;
                } else {
                    return -2;
                }
            } else if (x > 0) {
                if (y == 0) {
                    return 0;
                } else if (y > 0) {
                    return 1;
                } else {
                    return -1;
                }
            } else {
                if (y == 0) {
                    return include_pi_to_minus ? -4 : 4;
                } else if (y > 0) {
                    return 3;
                } else {
                    return -3;
                }
            }
        }

        @Override
        public int compareTo(RationalAngle ra) {
            if (ra == null) {
                throw new NullPointerException();
            }
            int me = toQuad();
            int you = ra.toQuad();
            if (me > you) {
                return 1;
            } else if (me < you) {
                return -1;
            }
            long sub = sub(ra).y;
            if (sub == 0) {
                return 0;
            } else if (sub > 0) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    public static class Pair<A, B> {
        public final A car;
        public final B cdr;

        public Pair(A car_, B cdr_) {
            car = car_;
            cdr = cdr_;
        }

        private static boolean eq(Object o1, Object o2) {
            return o1 == null ? o2 == null : o1.equals(o2);
        }

        private static int hc(Object o) {
            return o == null ? 0 : o.hashCode();
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Pair))
                return false;
            Pair<?, ?> rhs = (Pair<?, ?>) o;
            return eq(car, rhs.car) && eq(cdr, rhs.cdr);
        }

        @Override
        public int hashCode() {
            return hc(car) ^ hc(cdr);
        }
    }

    public static class Tuple1<A> extends Pair<A, Object> {
        public Tuple1(A a) {
            super(a, null);
        }
    }

    public static class Tuple2<A, B> extends Pair<A, Tuple1<B>> {
        public Tuple2(A a, B b) {
            super(a, new Tuple1<>(b));
        }
    }

    public static class Tuple3<A, B, C> extends Pair<A, Tuple2<B, C>> {
        public Tuple3(A a, B b, C c) {
            super(a, new Tuple2<>(b, c));
        }
    }

    public static class Tuple4<A, B, C, D> extends Pair<A, Tuple3<B, C, D>> {
        public Tuple4(A a, B b, C c, D d) {
            super(a, new Tuple3<>(b, c, d));
        }
    }

    public static class Tuple5<A, B, C, D, E> extends Pair<A, Tuple4<B, C, D, E>> {
        public Tuple5(A a, B b, C c, D d, E e) {
            super(a, new Tuple4<>(b, c, d, e));
        }
    }

    public static class PriorityQueueLogTime<T> {
        private PriorityQueue<T> queue;
        private Multiset<T> total;
        private int size = 0;

        public PriorityQueueLogTime() {
            queue = new PriorityQueue<>();
            total = new Multiset<>();
        }

        public PriorityQueueLogTime(Comparator<T> c) {
            queue = new PriorityQueue<>(c);
            total = new Multiset<>();
        }

        public void clear() {
            queue.clear();
            total.clear();
            size = 0;
        }

        public boolean contains(T e) {
            return total.count(e) > 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean offer(T e) {
            total.addOne(e);
            size++;
            return queue.offer(e);
        }

        public T peek() {
            if (total.isEmpty()) {
                return null;
            }
            simplify();
            return queue.peek();
        }

        public T poll() {
            if (total.isEmpty()) {
                return null;
            }
            simplify();
            size--;
            T res = queue.poll();
            total.removeOne(res);
            return res;
        }

        public void remove(T e) {
            total.removeOne(e);
            size--;
        }

        public int size() {
            return size;
        }

        private void simplify() {
            while (total.count(queue.peek()) == 0) {
                queue.poll();
            }
        }

    }

    static int[][] scanGraphOneIndexed(ContestScanner sc, int node, int edge, boolean undirected) {
        int[][] arr = sc.nextIntArrayMulti(edge, 2);
        for (int n = 0; n < edge; n++) {
            arr[0][n]--;
            arr[1][n]--;
        }
        return GraphBuilder.makeGraph(node, edge, arr[0], arr[1], undirected);
    }

    static int[][][] scanWeightedGraphOneIndexed(ContestScanner sc, int node, int edge, boolean undirected) {
        int[][] arr = sc.nextIntArrayMulti(edge, 3);
        for (int n = 0; n < edge; n++) {
            arr[0][n]--;
            arr[1][n]--;
        }
        return GraphBuilder.makeGraphWithWeight(node, edge, arr[0], arr[1], arr[2], undirected);
    }

    static class EdgeData {
        private int capacity;
        private int[] from, to, weight;
        private int p = 0;
        private boolean weighted;

        public EdgeData(boolean weighted) {
            this(weighted, 500000);
        }

        public EdgeData(boolean weighted, int initial_capacity) {
            capacity = initial_capacity;
            from = new int[capacity];
            to = new int[capacity];
            weight = new int[capacity];
            this.weighted = weighted;
        }

        public void addEdge(int u, int v) {
            if (weighted) {
                System.err.println("The graph is weighted!");
                return;
            }
            if (p == capacity) {
                int[] newfrom = new int[capacity * 2];
                int[] newto = new int[capacity * 2];
                System.arraycopy(from, 0, newfrom, 0, capacity);
                System.arraycopy(to, 0, newto, 0, capacity);
                capacity *= 2;
                from = newfrom;
                to = newto;
            }
            from[p] = u;
            to[p] = v;
            p++;
        }

        public void addEdge(int u, int v, int w) {
            if (!weighted) {
                System.err.println("The graph is NOT weighted!");
                return;
            }
            if (p == capacity) {
                int[] newfrom = new int[capacity * 2];
                int[] newto = new int[capacity * 2];
                int[] newweight = new int[capacity * 2];
                System.arraycopy(from, 0, newfrom, 0, capacity);
                System.arraycopy(to, 0, newto, 0, capacity);
                System.arraycopy(weight, 0, newweight, 0, capacity);
                capacity *= 2;
                from = newfrom;
                to = newto;
                weight = newweight;
            }
            from[p] = u;
            to[p] = v;
            weight[p] = w;
            p++;
        }

        public int[] getFrom() {
            int[] result = new int[p];
            System.arraycopy(from, 0, result, 0, p);
            return result;
        }

        public int[] getTo() {
            int[] result = new int[p];
            System.arraycopy(to, 0, result, 0, p);
            return result;
        }

        public int[] getWeight() {
            int[] result = new int[p];
            System.arraycopy(weight, 0, result, 0, p);
            return result;
        }

        public int size() {
            return p;
        }
    }

    ////////////////////////////////
    // Atcoder Library for Java //
    ////////////////////////////////

    static class MathLib {
        private static long safe_mod(long x, long m) {
            x %= m;
            if (x < 0)
                x += m;
            return x;
        }

        private static long[] inv_gcd(long a, long b) {
            a = safe_mod(a, b);
            if (a == 0)
                return new long[] { b, 0 };
            long s = b, t = a;
            long m0 = 0, m1 = 1;
            while (t > 0) {
                long u = s / t;
                s -= t * u;
                m0 -= m1 * u;
                long tmp = s;
                s = t;
                t = tmp;
                tmp = m0;
                m0 = m1;
                m1 = tmp;
            }
            if (m0 < 0)
                m0 += b / s;
            return new long[] { s, m0 };
        }

        public static long gcd(long a, long b) {
            a = java.lang.Math.abs(a);
            b = java.lang.Math.abs(b);
            return inv_gcd(a, b)[0];
        }

        public static long lcm(long a, long b) {
            a = java.lang.Math.abs(a);
            b = java.lang.Math.abs(b);
            return a / gcd(a, b) * b;
        }

        public static long pow_mod(long x, long n, int m) {
            assert n >= 0;
            assert m >= 1;
            if (m == 1)
                return 0L;
            x = safe_mod(x, m);
            long ans = 1L;
            while (n > 0) {
                if ((n & 1) == 1)
                    ans = (ans * x) % m;
                x = (x * x) % m;
                n >>>= 1;
            }
            return ans;
        }

        public static long[] crt(long[] r, long[] m) {
            assert (r.length == m.length);
            int n = r.length;

            long r0 = 0, m0 = 1;
            for (int i = 0; i < n; i++) {
                assert (1 <= m[i]);
                long r1 = safe_mod(r[i], m[i]), m1 = m[i];
                if (m0 < m1) {
                    long tmp = r0;
                    r0 = r1;
                    r1 = tmp;
                    tmp = m0;
                    m0 = m1;
                    m1 = tmp;
                }
                if (m0 % m1 == 0) {
                    if (r0 % m1 != r1)
                        return new long[] { 0, 0 };
                    continue;
                }

                long[] ig = inv_gcd(m0, m1);
                long g = ig[0], im = ig[1];

                long u1 = m1 / g;
                if ((r1 - r0) % g != 0)
                    return new long[] { 0, 0 };

                long x = (r1 - r0) / g % u1 * im % u1;

                r0 += x * m0;
                m0 *= u1;
                if (r0 < 0)
                    r0 += m0;
                // System.err.printf("%d %d\n", r0, m0);
            }
            return new long[] { r0, m0 };
        }

        public static long floor_sum(long n, long m, long a, long b) {
            long ans = 0;
            if (a >= m) {
                ans += (n - 1) * n * (a / m) / 2;
                a %= m;
            }
            if (b >= m) {
                ans += n * (b / m);
                b %= m;
            }

            long y_max = (a * n + b) / m;
            long x_max = y_max * m - b;
            if (y_max == 0)
                return ans;
            ans += (n - (x_max + a - 1) / a) * y_max;
            ans += floor_sum(y_max, a, m, (a - x_max % a) % a);
            return ans;
        }

        public static java.util.ArrayList<Long> divisors(long n) {
            java.util.ArrayList<Long> divisors = new ArrayList<>();
            java.util.ArrayList<Long> large = new ArrayList<>();

            for (long i = 1; i * i <= n; i++)
                if (n % i == 0) {
                    divisors.add(i);
                    if (i * i < n)
                        large.add(n / i);
                }
            for (int p = large.size() - 1; p >= 0; p--) {
                divisors.add(large.get(p));
            }
            return divisors;
        }
    }

    static class Multiset<T> extends java.util.TreeMap<T, Long> {
        public Multiset() {
            super();
        }

        public Multiset(java.util.List<T> list) {
            super();
            for (T e : list)
                this.addOne(e);
        }

        public long count(Object elm) {
            return getOrDefault(elm, 0L);
        }

        public void add(T elm, long amount) {
            if (!this.containsKey(elm))
                put(elm, amount);
            else
                replace(elm, get(elm) + amount);
            if (this.count(elm) == 0)
                this.remove(elm);
        }

        public void addOne(T elm) {
            this.add(elm, 1);
        }

        public void removeOne(T elm) {
            this.add(elm, -1);
        }

        public void removeAll(T elm) {
            this.add(elm, -this.count(elm));
        }

        public static <T> Multiset<T> merge(Multiset<T> a, Multiset<T> b) {
            Multiset<T> c = new Multiset<>();
            for (T x : a.keySet())
                c.add(x, a.count(x));
            for (T y : b.keySet())
                c.add(y, b.count(y));
            return c;
        }
    }

    static class GraphBuilder {
        public static int[][] makeGraph(int NumberOfNodes, int NumberOfEdges, int[] from, int[] to,
                boolean undirected) {
            int[][] graph = new int[NumberOfNodes][];
            int[] outdegree = new int[NumberOfNodes];
            for (int i = 0; i < NumberOfEdges; i++) {
                outdegree[from[i]]++;
                if (undirected)
                    outdegree[to[i]]++;
            }
            for (int i = 0; i < NumberOfNodes; i++)
                graph[i] = new int[outdegree[i]];
            for (int i = 0; i < NumberOfEdges; i++) {
                graph[from[i]][--outdegree[from[i]]] = to[i];
                if (undirected)
                    graph[to[i]][--outdegree[to[i]]] = from[i];
            }
            return graph;
        }

        public static int[][][] makeGraphWithWeight(int NumberOfNodes, int NumberOfEdges, int[] from, int[] to,
                int[] weight, boolean undirected) {
            int[][][] graph = new int[NumberOfNodes][][];
            int[] outdegree = new int[NumberOfNodes];
            for (int i = 0; i < NumberOfEdges; i++) {
                outdegree[from[i]]++;
                if (undirected)
                    outdegree[to[i]]++;
            }
            for (int i = 0; i < NumberOfNodes; i++)
                graph[i] = new int[outdegree[i]][];
            for (int i = 0; i < NumberOfEdges; i++) {
                graph[from[i]][--outdegree[from[i]]] = new int[] { to[i], weight[i] };
                if (undirected)
                    graph[to[i]][--outdegree[to[i]]] = new int[] { from[i], weight[i] };
            }
            return graph;
        }

        public static int[][][] makeGraphWithEdgeInfo(int NumberOfNodes, int NumberOfEdges, int[] from, int[] to,
                boolean undirected) {
            int[][][] graph = new int[NumberOfNodes][][];
            int[] outdegree = new int[NumberOfNodes];
            for (int i = 0; i < NumberOfEdges; i++) {
                outdegree[from[i]]++;
                if (undirected)
                    outdegree[to[i]]++;
            }
            for (int i = 0; i < NumberOfNodes; i++)
                graph[i] = new int[outdegree[i]][];
            for (int i = 0; i < NumberOfEdges; i++) {
                graph[from[i]][--outdegree[from[i]]] = new int[] { to[i], i, 0 };
                if (undirected)
                    graph[to[i]][--outdegree[to[i]]] = new int[] { from[i], i, 1 };
            }
            return graph;
        }

        public static int[][][] makeGraphWithWeightAndEdgeInfo(int NumberOfNodes, int NumberOfEdges, int[] from,
                int[] to, int[] weight, boolean undirected) {
            int[][][] graph = new int[NumberOfNodes][][];
            int[] outdegree = new int[NumberOfNodes];
            for (int i = 0; i < NumberOfEdges; i++) {
                outdegree[from[i]]++;
                if (undirected)
                    outdegree[to[i]]++;
            }
            for (int i = 0; i < NumberOfNodes; i++)
                graph[i] = new int[outdegree[i]][];
            for (int i = 0; i < NumberOfEdges; i++) {
                graph[from[i]][--outdegree[from[i]]] = new int[] { to[i], weight[i], i, 0 };
                if (undirected)
                    graph[to[i]][--outdegree[to[i]]] = new int[] { from[i], weight[i], i, 1 };
            }
            return graph;
        }
    }

    static class DSU {
        private int n;
        private int[] parentOrSize;

        public DSU(int n) {
            this.n = n;
            this.parentOrSize = new int[n];
            java.util.Arrays.fill(parentOrSize, -1);
        }

        int merge(int a, int b) {
            if (!(0 <= a && a < n))
                throw new IndexOutOfBoundsException("a=" + a);
            if (!(0 <= b && b < n))
                throw new IndexOutOfBoundsException("b=" + b);

            int x = leader(a);
            int y = leader(b);
            if (x == y)
                return x;
            if (-parentOrSize[x] < -parentOrSize[y]) {
                int tmp = x;
                x = y;
                y = tmp;
            }
            parentOrSize[x] += parentOrSize[y];
            parentOrSize[y] = x;
            return x;
        }

        boolean same(int a, int b) {
            if (!(0 <= a && a < n))
                throw new IndexOutOfBoundsException("a=" + a);
            if (!(0 <= b && b < n))
                throw new IndexOutOfBoundsException("b=" + b);
            return leader(a) == leader(b);
        }

        int leader(int a) {
            if (parentOrSize[a] < 0) {
                return a;
            } else {
                parentOrSize[a] = leader(parentOrSize[a]);
                return parentOrSize[a];
            }
        }

        int size(int a) {
            if (!(0 <= a && a < n))
                throw new IndexOutOfBoundsException("" + a);
            return -parentOrSize[leader(a)];
        }

        java.util.ArrayList<java.util.ArrayList<Integer>> groups() {
            int[] leaderBuf = new int[n];
            int[] groupSize = new int[n];
            for (int i = 0; i < n; i++) {
                leaderBuf[i] = leader(i);
                groupSize[leaderBuf[i]]++;
            }
            java.util.ArrayList<java.util.ArrayList<Integer>> result = new java.util.ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                result.add(new java.util.ArrayList<>(groupSize[i]));
            }
            for (int i = 0; i < n; i++) {
                result.get(leaderBuf[i]).add(i);
            }
            result.removeIf(java.util.ArrayList::isEmpty);
            return result;
        }
    }

    static class ModIntFactory {
        private final ModArithmetic ma;
        private final int mod;

        private final boolean usesMontgomery;
        private final ModArithmetic.ModArithmeticMontgomery maMontgomery;

        private ArrayList<Integer> factorial;
        private ArrayList<Integer> factorial_inversion;

        public ModIntFactory(int mod) {
            this.ma = ModArithmetic.of(mod);
            this.usesMontgomery = ma instanceof ModArithmetic.ModArithmeticMontgomery;
            this.maMontgomery = usesMontgomery ? (ModArithmetic.ModArithmeticMontgomery) ma : null;
            this.mod = mod;

            this.factorial = new ArrayList<>();
            this.factorial_inversion = new ArrayList<>();
        }

        public ModInt create(long value) {
            if ((value %= mod) < 0)
                value += mod;
            if (usesMontgomery) {
                return new ModInt(maMontgomery.generate(value));
            } else {
                return new ModInt((int) value);
            }
        }

        private void prepareFactorial(int max) {
            factorial.ensureCapacity(max + 1);
            if (factorial.size() == 0)
                factorial.add(1);
            for (int i = factorial.size(); i <= max; i++) {
                factorial.add(ma.mul(factorial.get(i - 1), i));
            }
        }

        public ModInt factorial(int i) {
            prepareFactorial(i);
            return create(factorial.get(i));
        }

        public ModInt permutation(int n, int r) {
            if (n < 0 || r < 0 || n < r)
                return create(0);
            prepareFactorial(n);
            if (factorial_inversion.size() > n) {
                return create(ma.mul(factorial.get(n), factorial_inversion.get(n - r)));
            }
            return create(ma.div(factorial.get(n), factorial.get(n - r)));
        }

        public ModInt combination(int n, int r) {
            if (n < 0 || r < 0 || n < r)
                return create(0);
            prepareFactorial(n);
            if (factorial_inversion.size() > n) {
                return create(
                        ma.mul(factorial.get(n), ma.mul(factorial_inversion.get(n - r), factorial_inversion.get(r))));
            }
            return create(ma.div(factorial.get(n), ma.mul(factorial.get(r), factorial.get(n - r))));
        }

        public void prepareFactorialInv(int max) {
            prepareFactorial(max);
            factorial_inversion.ensureCapacity(max + 1);
            for (int i = factorial_inversion.size(); i <= max; i++) {
                factorial_inversion.add(ma.inv(factorial.get(i)));
            }
        }

        public int getMod() {
            return mod;
        }

        public class ModInt {
            private int value;

            private ModInt(int value) {
                this.value = value;
            }

            public int mod() {
                return mod;
            }

            public int value() {
                if (ma instanceof ModArithmetic.ModArithmeticMontgomery) {
                    return ((ModArithmetic.ModArithmeticMontgomery) ma).reduce(value);
                }
                return value;
            }

            public ModInt add(ModInt mi) {
                return new ModInt(ma.add(value, mi.value));
            }

            public ModInt add(ModInt mi1, ModInt mi2) {
                return new ModInt(ma.add(value, mi1.value)).addAsg(mi2);
            }

            public ModInt add(ModInt mi1, ModInt mi2, ModInt mi3) {
                return new ModInt(ma.add(value, mi1.value)).addAsg(mi2).addAsg(mi3);
            }

            public ModInt add(ModInt mi1, ModInt mi2, ModInt mi3, ModInt mi4) {
                return new ModInt(ma.add(value, mi1.value)).addAsg(mi2).addAsg(mi3).addAsg(mi4);
            }

            public ModInt add(ModInt mi1, ModInt... mis) {
                ModInt mi = add(mi1);
                for (ModInt m : mis)
                    mi.addAsg(m);
                return mi;
            }

            public ModInt add(long mi) {
                return new ModInt(ma.add(value, ma.remainder(mi)));
            }

            public ModInt sub(ModInt mi) {
                return new ModInt(ma.sub(value, mi.value));
            }

            public ModInt sub(long mi) {
                return new ModInt(ma.sub(value, ma.remainder(mi)));
            }

            public ModInt mul(ModInt mi) {
                return new ModInt(ma.mul(value, mi.value));
            }

            public ModInt mul(ModInt mi1, ModInt mi2) {
                return new ModInt(ma.mul(value, mi1.value)).mulAsg(mi2);
            }

            public ModInt mul(ModInt mi1, ModInt mi2, ModInt mi3) {
                return new ModInt(ma.mul(value, mi1.value)).mulAsg(mi2).mulAsg(mi3);
            }

            public ModInt mul(ModInt mi1, ModInt mi2, ModInt mi3, ModInt mi4) {
                return new ModInt(ma.mul(value, mi1.value)).mulAsg(mi2).mulAsg(mi3).mulAsg(mi4);
            }

            public ModInt mul(ModInt mi1, ModInt... mis) {
                ModInt mi = mul(mi1);
                for (ModInt m : mis)
                    mi.mulAsg(m);
                return mi;
            }

            public ModInt mul(long mi) {
                return new ModInt(ma.mul(value, ma.remainder(mi)));
            }

            public ModInt div(ModInt mi) {
                return new ModInt(ma.div(value, mi.value));
            }

            public ModInt div(long mi) {
                return new ModInt(ma.div(value, ma.remainder(mi)));
            }

            public ModInt inv() {
                return new ModInt(ma.inv(value));
            }

            public ModInt pow(long b) {
                return new ModInt(ma.pow(value, b));
            }

            public ModInt addAsg(ModInt mi) {
                this.value = ma.add(value, mi.value);
                return this;
            }

            public ModInt addAsg(ModInt mi1, ModInt mi2) {
                return addAsg(mi1).addAsg(mi2);
            }

            public ModInt addAsg(ModInt mi1, ModInt mi2, ModInt mi3) {
                return addAsg(mi1).addAsg(mi2).addAsg(mi3);
            }

            public ModInt addAsg(ModInt mi1, ModInt mi2, ModInt mi3, ModInt mi4) {
                return addAsg(mi1).addAsg(mi2).addAsg(mi3).addAsg(mi4);
            }

            public ModInt addAsg(ModInt... mis) {
                for (ModInt m : mis)
                    addAsg(m);
                return this;
            }

            public ModInt addAsg(long mi) {
                this.value = ma.add(value, ma.remainder(mi));
                return this;
            }

            public ModInt subAsg(ModInt mi) {
                this.value = ma.sub(value, mi.value);
                return this;
            }

            public ModInt subAsg(long mi) {
                this.value = ma.sub(value, ma.remainder(mi));
                return this;
            }

            public ModInt mulAsg(ModInt mi) {
                this.value = ma.mul(value, mi.value);
                return this;
            }

            public ModInt mulAsg(ModInt mi1, ModInt mi2) {
                return mulAsg(mi1).mulAsg(mi2);
            }

            public ModInt mulAsg(ModInt mi1, ModInt mi2, ModInt mi3) {
                return mulAsg(mi1).mulAsg(mi2).mulAsg(mi3);
            }

            public ModInt mulAsg(ModInt mi1, ModInt mi2, ModInt mi3, ModInt mi4) {
                return mulAsg(mi1).mulAsg(mi2).mulAsg(mi3).mulAsg(mi4);
            }

            public ModInt mulAsg(ModInt... mis) {
                for (ModInt m : mis)
                    mulAsg(m);
                return this;
            }

            public ModInt mulAsg(long mi) {
                this.value = ma.mul(value, ma.remainder(mi));
                return this;
            }

            public ModInt divAsg(ModInt mi) {
                this.value = ma.div(value, mi.value);
                return this;
            }

            public ModInt divAsg(long mi) {
                this.value = ma.div(value, ma.remainder(mi));
                return this;
            }

            @Override
            public String toString() {
                return String.valueOf(value());
            }

            @Override
            public boolean equals(Object o) {
                if (o instanceof ModInt) {
                    ModInt mi = (ModInt) o;
                    return mod() == mi.mod() && value() == mi.value();
                }
                return false;
            }

            @Override
            public int hashCode() {
                return (1 * 37 + mod()) * 37 + value();
            }
        }

        private static abstract class ModArithmetic {
            abstract int mod();

            abstract int remainder(long value);

            abstract int add(int a, int b);

            abstract int sub(int a, int b);

            abstract int mul(int a, int b);

            int div(int a, int b) {
                return mul(a, inv(b));
            }

            int inv(int a) {
                int b = mod();
                if (b == 1)
                    return 0;
                long u = 1, v = 0;
                while (b >= 1) {
                    int t = a / b;
                    a -= t * b;
                    int tmp1 = a;
                    a = b;
                    b = tmp1;
                    u -= t * v;
                    long tmp2 = u;
                    u = v;
                    v = tmp2;
                }
                if (a != 1) {
                    throw new ArithmeticException("divide by zero");
                }
                return remainder(u);
            }

            int pow(int a, long b) {
                if (b < 0)
                    throw new ArithmeticException("negative power");
                int r = 1;
                int x = a;
                while (b > 0) {
                    if ((b & 1) == 1)
                        r = mul(r, x);
                    x = mul(x, x);
                    b >>= 1;
                }
                return r;
            }

            static ModArithmetic of(int mod) {
                if (mod <= 0) {
                    throw new IllegalArgumentException();
                } else if (mod == 1) {
                    return new ModArithmetic1();
                } else if (mod == 2) {
                    return new ModArithmetic2();
                } else if (mod == 998244353) {
                    return new ModArithmetic998244353();
                } else if (mod == 1000000007) {
                    return new ModArithmetic1000000007();
                } else if ((mod & 1) == 1) {
                    return new ModArithmeticMontgomery(mod);
                } else {
                    return new ModArithmeticBarrett(mod);
                }
            }

            private static final class ModArithmetic1 extends ModArithmetic {
                int mod() {
                    return 1;
                }

                int remainder(long value) {
                    return 0;
                }

                int add(int a, int b) {
                    return 0;
                }

                int sub(int a, int b) {
                    return 0;
                }

                int mul(int a, int b) {
                    return 0;
                }

                int pow(int a, long b) {
                    return 0;
                }
            }

            private static final class ModArithmetic2 extends ModArithmetic {
                int mod() {
                    return 2;
                }

                int remainder(long value) {
                    return (int) (value & 1);
                }

                int add(int a, int b) {
                    return a ^ b;
                }

                int sub(int a, int b) {
                    return a ^ b;
                }

                int mul(int a, int b) {
                    return a & b;
                }
            }

            private static final class ModArithmetic998244353 extends ModArithmetic {
                private final int mod = 998244353;

                int mod() {
                    return mod;
                }

                int remainder(long value) {
                    return (int) ((value %= mod) < 0 ? value + mod : value);
                }

                int add(int a, int b) {
                    int res = a + b;
                    return res >= mod ? res - mod : res;
                }

                int sub(int a, int b) {
                    int res = a - b;
                    return res < 0 ? res + mod : res;
                }

                int mul(int a, int b) {
                    return (int) (((long) a * b) % mod);
                }
            }

            private static final class ModArithmetic1000000007 extends ModArithmetic {
                private final int mod = 1000000007;

                int mod() {
                    return mod;
                }

                int remainder(long value) {
                    return (int) ((value %= mod) < 0 ? value + mod : value);
                }

                int add(int a, int b) {
                    int res = a + b;
                    return res >= mod ? res - mod : res;
                }

                int sub(int a, int b) {
                    int res = a - b;
                    return res < 0 ? res + mod : res;
                }

                int mul(int a, int b) {
                    return (int) (((long) a * b) % mod);
                }
            }

            private static final class ModArithmeticMontgomery extends ModArithmeticDynamic {
                private final long negInv;
                private final long r2;

                private ModArithmeticMontgomery(int mod) {
                    super(mod);
                    long inv = 0;
                    long s = 1, t = 0;
                    for (int i = 0; i < 32; i++) {
                        if ((t & 1) == 0) {
                            t += mod;
                            inv += s;
                        }
                        t >>= 1;
                        s <<= 1;
                    }
                    long r = (1l << 32) % mod;
                    this.negInv = inv;
                    this.r2 = (r * r) % mod;
                }

                private int generate(long x) {
                    return reduce(x * r2);
                }

                private int reduce(long x) {
                    x = (x + ((x * negInv) & 0xffff_ffffl) * mod) >>> 32;
                    return (int) (x < mod ? x : x - mod);
                }

                @Override
                int remainder(long value) {
                    return generate((value %= mod) < 0 ? value + mod : value);
                }

                @Override
                int mul(int a, int b) {
                    return reduce((long) a * b);
                }

                @Override
                int inv(int a) {
                    return super.inv(reduce(a));
                }

                @Override
                int pow(int a, long b) {
                    return generate(super.pow(a, b));
                }
            }

            private static final class ModArithmeticBarrett extends ModArithmeticDynamic {
                private static final long mask = 0xffff_ffffl;
                private final long mh;
                private final long ml;

                private ModArithmeticBarrett(int mod) {
                    super(mod);
                    /**
                     * m = floor(2^64/mod) 2^64 = p*mod + q, 2^32 = a*mod + b => (a*mod + b)^2 =
                     * p*mod + q => p = mod*a^2 + 2ab + floor(b^2/mod)
                     */
                    long a = (1l << 32) / mod;
                    long b = (1l << 32) % mod;
                    long m = a * a * mod + 2 * a * b + (b * b) / mod;
                    mh = m >>> 32;
                    ml = m & mask;
                }

                private int reduce(long x) {
                    long z = (x & mask) * ml;
                    z = (x & mask) * mh + (x >>> 32) * ml + (z >>> 32);
                    z = (x >>> 32) * mh + (z >>> 32);
                    x -= z * mod;
                    return (int) (x < mod ? x : x - mod);
                }

                @Override
                int remainder(long value) {
                    return (int) ((value %= mod) < 0 ? value + mod : value);
                }

                @Override
                int mul(int a, int b) {
                    return reduce((long) a * b);
                }
            }

            private static class ModArithmeticDynamic extends ModArithmetic {
                final int mod;

                ModArithmeticDynamic(int mod) {
                    this.mod = mod;
                }

                int mod() {
                    return mod;
                }

                int remainder(long value) {
                    return (int) ((value %= mod) < 0 ? value + mod : value);
                }

                int add(int a, int b) {
                    int sum = a + b;
                    return sum >= mod ? sum - mod : sum;
                }

                int sub(int a, int b) {
                    int sum = a - b;
                    return sum < 0 ? sum + mod : sum;
                }

                int mul(int a, int b) {
                    return (int) (((long) a * b) % mod);
                }
            }
        }
    }

    static class Convolution {
        /**
         * Find a primitive root.
         *
         * @param m A prime number.
         * @return Primitive root.
         */
        private static int primitiveRoot(int m) {
            if (m == 2)
                return 1;
            if (m == 167772161)
                return 3;
            if (m == 469762049)
                return 3;
            if (m == 754974721)
                return 11;
            if (m == 998244353)
                return 3;

            int[] divs = new int[20];
            divs[0] = 2;
            int cnt = 1;
            int x = (m - 1) / 2;
            while (x % 2 == 0)
                x /= 2;
            for (int i = 3; (long) (i) * i <= x; i += 2) {
                if (x % i == 0) {
                    divs[cnt++] = i;
                    while (x % i == 0) {
                        x /= i;
                    }
                }
            }
            if (x > 1) {
                divs[cnt++] = x;
            }
            for (int g = 2;; g++) {
                boolean ok = true;
                for (int i = 0; i < cnt; i++) {
                    if (pow(g, (m - 1) / divs[i], m) == 1) {
                        ok = false;
                        break;
                    }
                }
                if (ok)
                    return g;
            }
        }

        /**
         * Power.
         *
         * @param x Parameter x.
         * @param n Parameter n.
         * @param m Mod.
         * @return n-th power of x mod m.
         */
        private static long pow(long x, long n, int m) {
            if (m == 1)
                return 0;
            long r = 1;
            long y = x % m;
            while (n > 0) {
                if ((n & 1) != 0)
                    r = (r * y) % m;
                y = (y * y) % m;
                n >>= 1;
            }
            return r;
        }

        /**
         * Ceil of power 2.
         *
         * @param n Value.
         * @return Ceil of power 2.
         */
        private static int ceilPow2(int n) {
            int x = 0;
            while ((1L << x) < n)
                x++;
            return x;
        }

        /**
         * Garner's algorithm.
         *
         * @param c    Mod convolution results.
         * @param mods Mods.
         * @return Result.
         */
        private static long garner(long[] c, int[] mods) {
            int n = c.length + 1;
            long[] cnst = new long[n];
            long[] coef = new long[n];
            java.util.Arrays.fill(coef, 1);
            for (int i = 0; i < n - 1; i++) {
                int m1 = mods[i];
                long v = (c[i] - cnst[i] + m1) % m1;
                v = v * pow(coef[i], m1 - 2, m1) % m1;

                for (int j = i + 1; j < n; j++) {
                    long m2 = mods[j];
                    cnst[j] = (cnst[j] + coef[j] * v) % m2;
                    coef[j] = (coef[j] * m1) % m2;
                }
            }
            return cnst[n - 1];
        }

        /**
         * Pre-calculation for NTT.
         *
         * @param mod NTT Prime.
         * @param g   Primitive root of mod.
         * @return Pre-calculation table.
         */
        private static long[] sumE(int mod, int g) {
            long[] sum_e = new long[30];
            long[] es = new long[30];
            long[] ies = new long[30];
            int cnt2 = Integer.numberOfTrailingZeros(mod - 1);
            long e = pow(g, (mod - 1) >> cnt2, mod);
            long ie = pow(e, mod - 2, mod);
            for (int i = cnt2; i >= 2; i--) {
                es[i - 2] = e;
                ies[i - 2] = ie;
                e = e * e % mod;
                ie = ie * ie % mod;
            }
            long now = 1;
            for (int i = 0; i < cnt2 - 2; i++) {
                sum_e[i] = es[i] * now % mod;
                now = now * ies[i] % mod;
            }
            return sum_e;
        }

        /**
         * Pre-calculation for inverse NTT.
         *
         * @param mod Mod.
         * @param g   Primitive root of mod.
         * @return Pre-calculation table.
         */
        private static long[] sumIE(int mod, int g) {
            long[] sum_ie = new long[30];
            long[] es = new long[30];
            long[] ies = new long[30];

            int cnt2 = Integer.numberOfTrailingZeros(mod - 1);
            long e = pow(g, (mod - 1) >> cnt2, mod);
            long ie = pow(e, mod - 2, mod);
            for (int i = cnt2; i >= 2; i--) {
                es[i - 2] = e;
                ies[i - 2] = ie;
                e = e * e % mod;
                ie = ie * ie % mod;
            }
            long now = 1;
            for (int i = 0; i < cnt2 - 2; i++) {
                sum_ie[i] = ies[i] * now % mod;
                now = now * es[i] % mod;
            }
            return sum_ie;
        }

        /**
         * Inverse NTT.
         *
         * @param a     Target array.
         * @param sumIE Pre-calculation table.
         * @param mod   NTT Prime.
         */
        private static void butterflyInv(long[] a, long[] sumIE, int mod) {
            int n = a.length;
            int h = ceilPow2(n);

            for (int ph = h; ph >= 1; ph--) {
                int w = 1 << (ph - 1), p = 1 << (h - ph);
                long inow = 1;
                for (int s = 0; s < w; s++) {
                    int offset = s << (h - ph + 1);
                    for (int i = 0; i < p; i++) {
                        long l = a[i + offset];
                        long r = a[i + offset + p];
                        a[i + offset] = (l + r) % mod;
                        a[i + offset + p] = (mod + l - r) * inow % mod;
                    }
                    int x = Integer.numberOfTrailingZeros(~s);
                    inow = inow * sumIE[x] % mod;
                }
            }
        }

        /**
         * Inverse NTT.
         *
         * @param a    Target array.
         * @param sumE Pre-calculation table.
         * @param mod  NTT Prime.
         */
        private static void butterfly(long[] a, long[] sumE, int mod) {
            int n = a.length;
            int h = ceilPow2(n);

            for (int ph = 1; ph <= h; ph++) {
                int w = 1 << (ph - 1), p = 1 << (h - ph);
                long now = 1;
                for (int s = 0; s < w; s++) {
                    int offset = s << (h - ph + 1);
                    for (int i = 0; i < p; i++) {
                        long l = a[i + offset];
                        long r = a[i + offset + p] * now % mod;
                        a[i + offset] = (l + r) % mod;
                        a[i + offset + p] = (l - r + mod) % mod;
                    }
                    int x = Integer.numberOfTrailingZeros(~s);
                    now = now * sumE[x] % mod;
                }
            }
        }

        /**
         * Convolution.
         *
         * @param a   Target array 1.
         * @param b   Target array 2.
         * @param mod NTT Prime.
         * @return Answer.
         */
        public static long[] convolution(long[] a, long[] b, int mod) {
            int n = a.length;
            int m = b.length;
            if (n == 0 || m == 0)
                return new long[0];

            int z = 1 << ceilPow2(n + m - 1);
            {
                long[] na = new long[z];
                long[] nb = new long[z];
                System.arraycopy(a, 0, na, 0, n);
                System.arraycopy(b, 0, nb, 0, m);
                a = na;
                b = nb;
            }

            int g = primitiveRoot(mod);
            long[] sume = sumE(mod, g);
            long[] sumie = sumIE(mod, g);

            butterfly(a, sume, mod);
            butterfly(b, sume, mod);
            for (int i = 0; i < z; i++) {
                a[i] = a[i] * b[i] % mod;
            }
            butterflyInv(a, sumie, mod);
            a = java.util.Arrays.copyOf(a, n + m - 1);

            long iz = pow(z, mod - 2, mod);
            for (int i = 0; i < n + m - 1; i++)
                a[i] = a[i] * iz % mod;
            return a;
        }

        /**
         * Convolution.
         *
         * @param a   Target array 1.
         * @param b   Target array 2.
         * @param mod Any mod.
         * @return Answer.
         */
        public static long[] convolutionLL(long[] a, long[] b, int mod) {
            int n = a.length;
            int m = b.length;
            if (n == 0 || m == 0)
                return new long[0];

            int mod1 = 754974721;
            int mod2 = 167772161;
            int mod3 = 469762049;

            long[] c1 = convolution(a, b, mod1);
            long[] c2 = convolution(a, b, mod2);
            long[] c3 = convolution(a, b, mod3);

            int retSize = c1.length;
            long[] ret = new long[retSize];
            int[] mods = { mod1, mod2, mod3, mod };
            for (int i = 0; i < retSize; ++i) {
                ret[i] = garner(new long[] { c1[i], c2[i], c3[i] }, mods);
            }
            return ret;
        }

        /**
         * Convolution by ModInt.
         *
         * @param a Target array 1.
         * @param b Target array 2.
         * @return Answer.
         */
        public static java.util.List<ModIntFactory.ModInt> convolution(java.util.List<ModIntFactory.ModInt> a,
                java.util.List<ModIntFactory.ModInt> b) {
            int mod = a.get(0).mod();
            long[] va = a.stream().mapToLong(ModIntFactory.ModInt::value).toArray();
            long[] vb = b.stream().mapToLong(ModIntFactory.ModInt::value).toArray();
            long[] c = convolutionLL(va, vb, mod);

            ModIntFactory factory = new ModIntFactory(mod);
            return java.util.Arrays.stream(c).mapToObj(factory::create).collect(java.util.stream.Collectors.toList());
        }

        /**
         * Naive convolution. (Complexity is O(N^2)!!)
         *
         * @param a   Target array 1.
         * @param b   Target array 2.
         * @param mod Mod.
         * @return Answer.
         */
        public static long[] convolutionNaive(long[] a, long[] b, int mod) {
            int n = a.length;
            int m = b.length;
            int k = n + m - 1;
            long[] ret = new long[k];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    ret[i + j] += a[i] * b[j] % mod;
                    ret[i + j] %= mod;
                }
            }
            return ret;
        }
    }

    static class SCC {

        static class Edge {
            int from, to;

            public Edge(int from, int to) {
                this.from = from;
                this.to = to;
            }
        }

        final int n;
        int m;
        final java.util.ArrayList<Edge> unorderedEdges;
        final int[] start;
        final int[] ids;
        boolean hasBuilt = false;

        public SCC(int n) {
            this.n = n;
            this.unorderedEdges = new java.util.ArrayList<>();
            this.start = new int[n + 1];
            this.ids = new int[n];
        }

        public void addEdge(int from, int to) {
            rangeCheck(from);
            rangeCheck(to);
            unorderedEdges.add(new Edge(from, to));
            start[from + 1]++;
            this.m++;
        }

        public int id(int i) {
            if (!hasBuilt) {
                throw new UnsupportedOperationException("Graph hasn't been built.");
            }
            rangeCheck(i);
            return ids[i];
        }

        public int[][] build() {
            for (int i = 1; i <= n; i++) {
                start[i] += start[i - 1];
            }
            Edge[] orderedEdges = new Edge[m];
            int[] count = new int[n + 1];
            System.arraycopy(start, 0, count, 0, n + 1);
            for (Edge e : unorderedEdges) {
                orderedEdges[count[e.from]++] = e;
            }
            int nowOrd = 0;
            int groupNum = 0;
            int k = 0;
            // parent
            int[] par = new int[n];
            int[] vis = new int[n];
            int[] low = new int[n];
            int[] ord = new int[n];
            java.util.Arrays.fill(ord, -1);
            // u = lower32(stack[i]) : visiting vertex
            // j = upper32(stack[i]) : jth child
            long[] stack = new long[n];
            // size of stack
            int ptr = 0;
            // non-recursional DFS
            for (int i = 0; i < n; i++) {
                if (ord[i] >= 0)
                    continue;
                par[i] = -1;
                // vertex i, 0th child.
                stack[ptr++] = 0l << 32 | i;
                // stack is not empty
                while (ptr > 0) {
                    // last element
                    long p = stack[--ptr];
                    // vertex
                    int u = (int) (p & 0xffff_ffffl);
                    // jth child
                    int j = (int) (p >>> 32);
                    if (j == 0) { // first visit
                        low[u] = ord[u] = nowOrd++;
                        vis[k++] = u;
                    }
                    if (start[u] + j < count[u]) { // there are more children
                        // jth child
                        int to = orderedEdges[start[u] + j].to;
                        // incr children counter
                        stack[ptr++] += 1l << 32;
                        if (ord[to] == -1) { // new vertex
                            stack[ptr++] = 0l << 32 | to;
                            par[to] = u;
                        } else { // backward edge
                            low[u] = Math.min(low[u], ord[to]);
                        }
                    } else { // no more children (leaving)
                        while (j-- > 0) {
                            int to = orderedEdges[start[u] + j].to;
                            // update lowlink
                            if (par[to] == u)
                                low[u] = Math.min(low[u], low[to]);
                        }
                        if (low[u] == ord[u]) { // root of a component
                            while (true) { // gathering verticies
                                int v = vis[--k];
                                ord[v] = n;
                                ids[v] = groupNum;
                                if (v == u)
                                    break;
                            }
                            groupNum++; // incr the number of components
                        }
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                ids[i] = groupNum - 1 - ids[i];
            }

            int[] counts = new int[groupNum];
            for (int x : ids)
                counts[x]++;
            int[][] groups = new int[groupNum][];
            for (int i = 0; i < groupNum; i++) {
                groups[i] = new int[counts[i]];
            }
            for (int i = 0; i < n; i++) {
                int cmp = ids[i];
                groups[cmp][--counts[cmp]] = i;
            }
            hasBuilt = true;
            return groups;
        }

        private void rangeCheck(int i) {
            if (i < 0 || i >= n) {
                throw new IndexOutOfBoundsException(String.format("Index %d out of bounds for length %d", i, n));
            }
        }
    }

    static class ContestScanner {
        private final java.io.InputStream in;
        private final byte[] buffer = new byte[1024];
        private int ptr = 0;
        private int buflen = 0;

        private static final long LONG_MAX_TENTHS = 922337203685477580L;
        private static final int LONG_MAX_LAST_DIGIT = 7;
        private static final int LONG_MIN_LAST_DIGIT = 8;

        public ContestScanner(java.io.InputStream in) {
            this.in = in;
        }

        public ContestScanner() {
            this(System.in);
        }

        private boolean hasNextByte() {
            if (ptr < buflen) {
                return true;
            } else {
                ptr = 0;
                try {
                    buflen = in.read(buffer);
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }
                if (buflen <= 0) {
                    return false;
                }
            }
            return true;
        }

        private int readByte() {
            if (hasNextByte())
                return buffer[ptr++];
            else
                return -1;
        }

        private static boolean isPrintableChar(int c) {
            return 33 <= c && c <= 126;
        }

        public boolean hasNext() {
            while (hasNextByte() && !isPrintableChar(buffer[ptr]))
                ptr++;
            return hasNextByte();
        }

        public String next() {
            if (!hasNext())
                throw new java.util.NoSuchElementException();
            StringBuilder sb = new StringBuilder();
            int b = readByte();
            while (isPrintableChar(b)) {
                sb.appendCodePoint(b);
                b = readByte();
            }
            return sb.toString();
        }

        public long nextLong() {
            if (!hasNext())
                throw new java.util.NoSuchElementException();
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
            while (true) {
                if ('0' <= b && b <= '9') {
                    int digit = b - '0';
                    if (n >= LONG_MAX_TENTHS) {
                        if (n == LONG_MAX_TENTHS) {
                            if (minus) {
                                if (digit <= LONG_MIN_LAST_DIGIT) {
                                    n = -n * 10 - digit;
                                    b = readByte();
                                    if (!isPrintableChar(b)) {
                                        return n;
                                    } else if (b < '0' || '9' < b) {
                                        throw new NumberFormatException(
                                                String.format("%d%s... is not number", n, Character.toString(b)));
                                    }
                                }
                            } else {
                                if (digit <= LONG_MAX_LAST_DIGIT) {
                                    n = n * 10 + digit;
                                    b = readByte();
                                    if (!isPrintableChar(b)) {
                                        return n;
                                    } else if (b < '0' || '9' < b) {
                                        throw new NumberFormatException(
                                                String.format("%d%s... is not number", n, Character.toString(b)));
                                    }
                                }
                            }
                        }
                        throw new ArithmeticException(
                                String.format("%s%d%d... overflows long.", minus ? "-" : "", n, digit));
                    }
                    n = n * 10 + digit;
                } else if (b == -1 || !isPrintableChar(b)) {
                    return minus ? -n : n;
                } else {
                    throw new NumberFormatException();
                }
                b = readByte();
            }
        }

        public int nextInt() {
            long nl = nextLong();
            if (nl < Integer.MIN_VALUE || nl > Integer.MAX_VALUE)
                throw new NumberFormatException();
            return (int) nl;
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public long[] nextLongArray(int length) {
            long[] array = new long[length];
            for (int i = 0; i < length; i++)
                array[i] = this.nextLong();
            return array;
        }

        public long[] nextLongArray(int length, java.util.function.LongUnaryOperator map) {
            long[] array = new long[length];
            for (int i = 0; i < length; i++)
                array[i] = map.applyAsLong(this.nextLong());
            return array;
        }

        public int[] nextIntArray(int length) {
            int[] array = new int[length];
            for (int i = 0; i < length; i++)
                array[i] = this.nextInt();
            return array;
        }

        public int[][] nextIntArrayMulti(int length, int width) {
            int[][] arrays = new int[width][length];
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < width; j++)
                    arrays[j][i] = this.nextInt();
            }
            return arrays;
        }

        public int[] nextIntArray(int length, java.util.function.IntUnaryOperator map) {
            int[] array = new int[length];
            for (int i = 0; i < length; i++)
                array[i] = map.applyAsInt(this.nextInt());
            return array;
        }

        public double[] nextDoubleArray(int length) {
            double[] array = new double[length];
            for (int i = 0; i < length; i++)
                array[i] = this.nextDouble();
            return array;
        }

        public double[] nextDoubleArray(int length, java.util.function.DoubleUnaryOperator map) {
            double[] array = new double[length];
            for (int i = 0; i < length; i++)
                array[i] = map.applyAsDouble(this.nextDouble());
            return array;
        }

        public long[][] nextLongMatrix(int height, int width) {
            long[][] mat = new long[height][width];
            for (int h = 0; h < height; h++)
                for (int w = 0; w < width; w++) {
                    mat[h][w] = this.nextLong();
                }
            return mat;
        }

        public int[][] nextIntMatrix(int height, int width) {
            int[][] mat = new int[height][width];
            for (int h = 0; h < height; h++)
                for (int w = 0; w < width; w++) {
                    mat[h][w] = this.nextInt();
                }
            return mat;
        }

        public double[][] nextDoubleMatrix(int height, int width) {
            double[][] mat = new double[height][width];
            for (int h = 0; h < height; h++)
                for (int w = 0; w < width; w++) {
                    mat[h][w] = this.nextDouble();
                }
            return mat;
        }

        public char[][] nextCharMatrix(int height, int width) {
            char[][] mat = new char[height][width];
            for (int h = 0; h < height; h++) {
                String s = this.next();
                for (int w = 0; w < width; w++) {
                    mat[h][w] = s.charAt(w);
                }
            }
            return mat;
        }
    }

    static class ContestPrinter extends java.io.PrintWriter {
        public ContestPrinter(java.io.PrintStream stream) {
            super(stream);
        }

        public ContestPrinter() {
            super(System.out);
        }

        private static String dtos(double x, int n) {
            StringBuilder sb = new StringBuilder();
            if (x < 0) {
                sb.append('-');
                x = -x;
            }
            x += Math.pow(10, -n) / 2;
            sb.append((long) x);
            sb.append(".");
            x -= (long) x;
            for (int i = 0; i < n; i++) {
                x *= 10;
                sb.append((int) x);
                x -= (int) x;
            }
            return sb.toString();
        }

        @Override
        public void print(float f) {
            super.print(dtos(f, 20));
        }

        @Override
        public void println(float f) {
            super.println(dtos(f, 20));
        }

        @Override
        public void print(double d) {
            super.print(dtos(d, 20));
        }

        @Override
        public void println(double d) {
            super.println(dtos(d, 20));
        }

        public void printArray(int[] array, String separator) {
            int n = array.length;
            for (int i = 0; i < n - 1; i++) {
                super.print(array[i]);
                super.print(separator);
            }
            super.println(array[n - 1]);
        }

        public void printArray(int[] array) {
            this.printArray(array, " ");
        }

        public void printArray(int[] array, String separator, java.util.function.IntUnaryOperator map) {
            int n = array.length;
            for (int i = 0; i < n - 1; i++) {
                super.print(map.applyAsInt(array[i]));
                super.print(separator);
            }
            super.println(map.applyAsInt(array[n - 1]));
        }

        public void printArray(int[] array, java.util.function.IntUnaryOperator map) {
            this.printArray(array, " ", map);
        }

        public void printArray(long[] array, String separator) {
            int n = array.length;
            for (int i = 0; i < n - 1; i++) {
                super.print(array[i]);
                super.print(separator);
            }
            super.println(array[n - 1]);
        }

        public void printArray(long[] array) {
            this.printArray(array, " ");
        }

        public void printArray(long[] array, String separator, java.util.function.LongUnaryOperator map) {
            int n = array.length;
            for (int i = 0; i < n - 1; i++) {
                super.print(map.applyAsLong(array[i]));
                super.print(separator);
            }
            super.println(map.applyAsLong(array[n - 1]));
        }

        public void printArray(long[] array, java.util.function.LongUnaryOperator map) {
            this.printArray(array, " ", map);
        }

    }

    static class Permutation implements java.util.Iterator<int[]>, Iterable<int[]> {
        private int[] next;

        public Permutation(int n) {
            next = java.util.stream.IntStream.range(0, n).toArray();
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public int[] next() {
            int[] r = next.clone();
            next = nextPermutation(next);
            return r;
        }

        @Override
        public java.util.Iterator<int[]> iterator() {
            return this;
        }

        public static int[] nextPermutation(int[] a) {
            if (a == null || a.length < 2)
                return null;
            int p = 0;
            for (int i = a.length - 2; i >= 0; i--) {
                if (a[i] >= a[i + 1])
                    continue;
                p = i;
                break;
            }
            int q = 0;
            for (int i = a.length - 1; i > p; i--) {
                if (a[i] <= a[p])
                    continue;
                q = i;
                break;
            }
            if (p == 0 && q == 0)
                return null;
            int temp = a[p];
            a[p] = a[q];
            a[q] = temp;
            int l = p, r = a.length;
            while (++l < --r) {
                temp = a[l];
                a[l] = a[r];
                a[r] = temp;
            }
            return a;
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
            while (k < n)
                k <<= 1;
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
                throw new IllegalArgumentException(String.format("Invalid range: [%d, %d)", l, r));
            }
            inclusiveRangeCheck(l);
            inclusiveRangeCheck(r);
            S sumLeft = E;
            S sumRight = E;
            l += N;
            r += N;
            while (l < r) {
                if ((l & 1) == 1)
                    sumLeft = op.apply(sumLeft, data[l++]);
                if ((r & 1) == 1)
                    sumRight = op.apply(data[--r], sumRight);
                l >>= 1;
                r >>= 1;
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
            if (l == MAX)
                return MAX;
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
            if (r == 0)
                return 0;
            r += N;
            S sum = E;
            do {
                r--;
                while (r > 1 && (r & 1) == 1)
                    r >>= 1;
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
                        String.format("Index %d out of bounds for the range [%d, %d).", p, 0, MAX));
            }
        }

        private void inclusiveRangeCheck(int p) {
            if (p < 0 || p > MAX) {
                throw new IndexOutOfBoundsException(
                        String.format("Index %d out of bounds for the range [%d, %d].", p, 0, MAX));
            }
        }

        // **************** DEBUG **************** //

        private int indent = 6;

        public void setIndent(int newIndent) {
            this.indent = newIndent;
        }

        @Override
        public String toString() {
            return toSimpleString();
        }

        public String toDetailedString() {
            return toDetailedString(1, 0);
        }

        private String toDetailedString(int k, int sp) {
            if (k >= N)
                return indent(sp) + data[k];
            String s = "";
            s += toDetailedString(k << 1 | 1, sp + indent);
            s += "\n";
            s += indent(sp) + data[k];
            s += "\n";
            s += toDetailedString(k << 1 | 0, sp + indent);
            return s;
        }

        private static String indent(int n) {
            StringBuilder sb = new StringBuilder();
            while (n-- > 0)
                sb.append(' ');
            return sb.toString();
        }

        public String toSimpleString() {
            StringBuilder sb = new StringBuilder();
            sb.append('[');
            for (int i = 0; i < N; i++) {
                sb.append(data[i + N]);
                if (i < N - 1)
                    sb.append(',').append(' ');
            }
            sb.append(']');
            return sb.toString();
        }
    }

    static class LazySegTree<S, F> {
        final int MAX;

        final int N;
        final int Log;
        final java.util.function.BinaryOperator<S> Op;
        final S E;
        final java.util.function.BiFunction<F, S, S> Mapping;
        final java.util.function.BinaryOperator<F> Composition;
        final F Id;

        final S[] Dat;
        final F[] Laz;

        @SuppressWarnings("unchecked")
        public LazySegTree(int n, java.util.function.BinaryOperator<S> op, S e,
                java.util.function.BiFunction<F, S, S> mapping, java.util.function.BinaryOperator<F> composition,
                F id) {
            this.MAX = n;
            int k = 1;
            while (k < n)
                k <<= 1;
            this.N = k;
            this.Log = Integer.numberOfTrailingZeros(N);
            this.Op = op;
            this.E = e;
            this.Mapping = mapping;
            this.Composition = composition;
            this.Id = id;
            this.Dat = (S[]) new Object[N << 1];
            this.Laz = (F[]) new Object[N];
            java.util.Arrays.fill(Dat, E);
            java.util.Arrays.fill(Laz, Id);
        }

        public LazySegTree(S[] dat, java.util.function.BinaryOperator<S> op, S e,
                java.util.function.BiFunction<F, S, S> mapping, java.util.function.BinaryOperator<F> composition,
                F id) {
            this(dat.length, op, e, mapping, composition, id);
            build(dat);
        }

        private void build(S[] dat) {
            int l = dat.length;
            System.arraycopy(dat, 0, Dat, N, l);
            for (int i = N - 1; i > 0; i--) {
                Dat[i] = Op.apply(Dat[i << 1 | 0], Dat[i << 1 | 1]);
            }
        }

        private void push(int k) {
            if (Laz[k] == Id)
                return;
            int lk = k << 1 | 0, rk = k << 1 | 1;
            Dat[lk] = Mapping.apply(Laz[k], Dat[lk]);
            Dat[rk] = Mapping.apply(Laz[k], Dat[rk]);
            if (lk < N)
                Laz[lk] = Composition.apply(Laz[k], Laz[lk]);
            if (rk < N)
                Laz[rk] = Composition.apply(Laz[k], Laz[rk]);
            Laz[k] = Id;
        }

        private void pushTo(int k) {
            for (int i = Log; i > 0; i--)
                push(k >> i);
        }

        private void pushTo(int lk, int rk) {
            for (int i = Log; i > 0; i--) {
                if (((lk >> i) << i) != lk)
                    push(lk >> i);
                if (((rk >> i) << i) != rk)
                    push(rk >> i);
            }
        }

        private void updateFrom(int k) {
            k >>= 1;
            while (k > 0) {
                Dat[k] = Op.apply(Dat[k << 1 | 0], Dat[k << 1 | 1]);
                k >>= 1;
            }
        }

        private void updateFrom(int lk, int rk) {
            for (int i = 1; i <= Log; i++) {
                if (((lk >> i) << i) != lk) {
                    int lki = lk >> i;
                    Dat[lki] = Op.apply(Dat[lki << 1 | 0], Dat[lki << 1 | 1]);
                }
                if (((rk >> i) << i) != rk) {
                    int rki = (rk - 1) >> i;
                    Dat[rki] = Op.apply(Dat[rki << 1 | 0], Dat[rki << 1 | 1]);
                }
            }
        }

        public void set(int p, S x) {
            exclusiveRangeCheck(p);
            p += N;
            pushTo(p);
            Dat[p] = x;
            updateFrom(p);
        }

        public S get(int p) {
            exclusiveRangeCheck(p);
            p += N;
            pushTo(p);
            return Dat[p];
        }

        public S prod(int l, int r) {
            if (l > r) {
                throw new IllegalArgumentException(String.format("Invalid range: [%d, %d)", l, r));
            }
            inclusiveRangeCheck(l);
            inclusiveRangeCheck(r);
            if (l == r)
                return E;
            l += N;
            r += N;
            pushTo(l, r);
            S sumLeft = E, sumRight = E;
            while (l < r) {
                if ((l & 1) == 1)
                    sumLeft = Op.apply(sumLeft, Dat[l++]);
                if ((r & 1) == 1)
                    sumRight = Op.apply(Dat[--r], sumRight);
                l >>= 1;
                r >>= 1;
            }
            return Op.apply(sumLeft, sumRight);
        }

        public S allProd() {
            return Dat[1];
        }

        public void apply(int p, F f) {
            exclusiveRangeCheck(p);
            p += N;
            pushTo(p);
            Dat[p] = Mapping.apply(f, Dat[p]);
            updateFrom(p);
        }

        public void apply(int l, int r, F f) {
            if (l > r) {
                throw new IllegalArgumentException(String.format("Invalid range: [%d, %d)", l, r));
            }
            inclusiveRangeCheck(l);
            inclusiveRangeCheck(r);
            if (l == r)
                return;
            l += N;
            r += N;
            pushTo(l, r);
            for (int l2 = l, r2 = r; l2 < r2;) {
                if ((l2 & 1) == 1) {
                    Dat[l2] = Mapping.apply(f, Dat[l2]);
                    if (l2 < N)
                        Laz[l2] = Composition.apply(f, Laz[l2]);
                    l2++;
                }
                if ((r2 & 1) == 1) {
                    r2--;
                    Dat[r2] = Mapping.apply(f, Dat[r2]);
                    if (r2 < N)
                        Laz[r2] = Composition.apply(f, Laz[r2]);
                }
                l2 >>= 1;
                r2 >>= 1;
            }
            updateFrom(l, r);
        }

        public int maxRight(int l, java.util.function.Predicate<S> g) {
            inclusiveRangeCheck(l);
            if (!g.test(E)) {
                throw new IllegalArgumentException("Identity element must satisfy the condition.");
            }
            if (l == MAX)
                return MAX;
            l += N;
            pushTo(l);
            S sum = E;
            do {
                l >>= Integer.numberOfTrailingZeros(l);
                if (!g.test(Op.apply(sum, Dat[l]))) {
                    while (l < N) {
                        push(l);
                        l = l << 1;
                        if (g.test(Op.apply(sum, Dat[l]))) {
                            sum = Op.apply(sum, Dat[l]);
                            l++;
                        }
                    }
                    return l - N;
                }
                sum = Op.apply(sum, Dat[l]);
                l++;
            } while ((l & -l) != l);
            return MAX;
        }

        public int minLeft(int r, java.util.function.Predicate<S> g) {
            inclusiveRangeCheck(r);
            if (!g.test(E)) {
                throw new IllegalArgumentException("Identity element must satisfy the condition.");
            }
            if (r == 0)
                return 0;
            r += N;
            pushTo(r - 1);
            S sum = E;
            do {
                r--;
                while (r > 1 && (r & 1) == 1)
                    r >>= 1;
                if (!g.test(Op.apply(Dat[r], sum))) {
                    while (r < N) {
                        push(r);
                        r = r << 1 | 1;
                        if (g.test(Op.apply(Dat[r], sum))) {
                            sum = Op.apply(Dat[r], sum);
                            r--;
                        }
                    }
                    return r + 1 - N;
                }
                sum = Op.apply(Dat[r], sum);
            } while ((r & -r) != r);
            return 0;
        }

        private void exclusiveRangeCheck(int p) {
            if (p < 0 || p >= MAX) {
                throw new IndexOutOfBoundsException(String.format("Index %d is not in [%d, %d).", p, 0, MAX));
            }
        }

        private void inclusiveRangeCheck(int p) {
            if (p < 0 || p > MAX) {
                throw new IndexOutOfBoundsException(String.format("Index %d is not in [%d, %d].", p, 0, MAX));
            }
        }

        // **************** DEBUG **************** //

        private int indent = 6;

        public void setIndent(int newIndent) {
            this.indent = newIndent;
        }

        @Override
        public String toString() {
            return toSimpleString();
        }

        private S[] simulatePushAll() {
            S[] simDat = java.util.Arrays.copyOf(Dat, 2 * N);
            F[] simLaz = java.util.Arrays.copyOf(Laz, 2 * N);
            for (int k = 1; k < N; k++) {
                if (simLaz[k] == Id)
                    continue;
                int lk = k << 1 | 0, rk = k << 1 | 1;
                simDat[lk] = Mapping.apply(simLaz[k], simDat[lk]);
                simDat[rk] = Mapping.apply(simLaz[k], simDat[rk]);
                if (lk < N)
                    simLaz[lk] = Composition.apply(simLaz[k], simLaz[lk]);
                if (rk < N)
                    simLaz[rk] = Composition.apply(simLaz[k], simLaz[rk]);
                simLaz[k] = Id;
            }
            return simDat;
        }

        public String toDetailedString() {
            return toDetailedString(1, 0, simulatePushAll());
        }

        private String toDetailedString(int k, int sp, S[] dat) {
            if (k >= N)
                return indent(sp) + dat[k];
            String s = "";
            s += toDetailedString(k << 1 | 1, sp + indent, dat);
            s += "\n";
            s += indent(sp) + dat[k];
            s += "\n";
            s += toDetailedString(k << 1 | 0, sp + indent, dat);
            return s;
        }

        private static String indent(int n) {
            StringBuilder sb = new StringBuilder();
            while (n-- > 0)
                sb.append(' ');
            return sb.toString();
        }

        public String toSimpleString() {
            S[] dat = simulatePushAll();
            StringBuilder sb = new StringBuilder();
            sb.append('[');
            for (int i = 0; i < N; i++) {
                sb.append(dat[i + N]);
                if (i < N - 1)
                    sb.append(',').append(' ');
            }
            sb.append(']');
            return sb.toString();
        }
    }

    static class MaxFlow {
        private static final class InternalCapEdge {
            final int to;
            final int rev;
            long cap;

            InternalCapEdge(int to, int rev, long cap) {
                this.to = to;
                this.rev = rev;
                this.cap = cap;
            }
        }

        public static final class CapEdge {
            public final int from, to;
            public final long cap, flow;

            CapEdge(int from, int to, long cap, long flow) {
                this.from = from;
                this.to = to;
                this.cap = cap;
                this.flow = flow;
            }

            @Override
            public boolean equals(Object o) {
                if (o instanceof CapEdge) {
                    CapEdge e = (CapEdge) o;
                    return from == e.from && to == e.to && cap == e.cap && flow == e.flow;
                }
                return false;
            }
        }

        private static final class IntPair {
            final int first, second;

            IntPair(int first, int second) {
                this.first = first;
                this.second = second;
            }
        }

        static final long INF = Long.MAX_VALUE;

        private final int n;
        private final java.util.ArrayList<IntPair> pos;
        private final java.util.ArrayList<InternalCapEdge>[] g;

        @SuppressWarnings("unchecked")
        public MaxFlow(int n) {
            this.n = n;
            this.pos = new java.util.ArrayList<>();
            this.g = new java.util.ArrayList[n];
            for (int i = 0; i < n; i++) {
                this.g[i] = new java.util.ArrayList<>();
            }
        }

        public int addEdge(int from, int to, long cap) {
            rangeCheck(from, 0, n);
            rangeCheck(to, 0, n);
            nonNegativeCheck(cap, "Capacity");
            int m = pos.size();
            pos.add(new IntPair(from, g[from].size()));
            int fromId = g[from].size();
            int toId = g[to].size();
            if (from == to)
                toId++;
            g[from].add(new InternalCapEdge(to, toId, cap));
            g[to].add(new InternalCapEdge(from, fromId, 0L));
            return m;
        }

        private InternalCapEdge getInternalEdge(int i) {
            return g[pos.get(i).first].get(pos.get(i).second);
        }

        private InternalCapEdge getInternalEdgeReversed(InternalCapEdge e) {
            return g[e.to].get(e.rev);
        }

        public CapEdge getEdge(int i) {
            int m = pos.size();
            rangeCheck(i, 0, m);
            InternalCapEdge e = getInternalEdge(i);
            InternalCapEdge re = getInternalEdgeReversed(e);
            return new CapEdge(re.to, e.to, e.cap + re.cap, re.cap);
        }

        public CapEdge[] getEdges() {
            CapEdge[] res = new CapEdge[pos.size()];
            java.util.Arrays.setAll(res, this::getEdge);
            return res;
        }

        public void changeEdge(int i, long newCap, long newFlow) {
            int m = pos.size();
            rangeCheck(i, 0, m);
            nonNegativeCheck(newCap, "Capacity");
            if (newFlow > newCap) {
                throw new IllegalArgumentException(
                        String.format("Flow %d is greater than the capacity %d.", newCap, newFlow));
            }
            InternalCapEdge e = getInternalEdge(i);
            InternalCapEdge re = getInternalEdgeReversed(e);
            e.cap = newCap - newFlow;
            re.cap = newFlow;
        }

        public long maxFlow(int s, int t) {
            return flow(s, t, INF);
        }

        public long flow(int s, int t, long flowLimit) {
            rangeCheck(s, 0, n);
            rangeCheck(t, 0, n);
            long flow = 0L;
            int[] level = new int[n];
            int[] que = new int[n];
            int[] iter = new int[n];
            while (flow < flowLimit) {
                bfs(s, t, level, que);
                if (level[t] < 0)
                    break;
                java.util.Arrays.fill(iter, 0);
                while (flow < flowLimit) {
                    long d = dfs(t, s, flowLimit - flow, iter, level);
                    if (d == 0)
                        break;
                    flow += d;
                }
            }
            return flow;
        }

        private void bfs(int s, int t, int[] level, int[] que) {
            java.util.Arrays.fill(level, -1);
            int hd = 0, tl = 0;
            que[tl++] = s;
            level[s] = 0;
            while (hd < tl) {
                int u = que[hd++];
                for (InternalCapEdge e : g[u]) {
                    int v = e.to;
                    if (e.cap == 0 || level[v] >= 0)
                        continue;
                    level[v] = level[u] + 1;
                    if (v == t)
                        return;
                    que[tl++] = v;
                }
            }
        }

        private long dfs(int cur, int s, long flowLimit, int[] iter, int[] level) {
            if (cur == s)
                return flowLimit;
            long res = 0;
            int curLevel = level[cur];
            for (int itMax = g[cur].size(); iter[cur] < itMax; iter[cur]++) {
                int i = iter[cur];
                InternalCapEdge e = g[cur].get(i);
                InternalCapEdge re = getInternalEdgeReversed(e);
                if (curLevel <= level[e.to] || re.cap == 0)
                    continue;
                long d = dfs(e.to, s, Math.min(flowLimit - res, re.cap), iter, level);
                if (d <= 0)
                    continue;
                e.cap += d;
                re.cap -= d;
                res += d;
                if (res == flowLimit)
                    break;
            }
            return res;
        }

        public boolean[] minCut(int s) {
            rangeCheck(s, 0, n);
            boolean[] visited = new boolean[n];
            int[] stack = new int[n];
            int ptr = 0;
            stack[ptr++] = s;
            visited[s] = true;
            while (ptr > 0) {
                int u = stack[--ptr];
                for (InternalCapEdge e : g[u]) {
                    int v = e.to;
                    if (e.cap > 0 && !visited[v]) {
                        visited[v] = true;
                        stack[ptr++] = v;
                    }
                }
            }
            return visited;
        }

        private void rangeCheck(int i, int minInclusive, int maxExclusive) {
            if (i < 0 || i >= maxExclusive) {
                throw new IndexOutOfBoundsException(
                        String.format("Index %d out of bounds for length %d", i, maxExclusive));
            }
        }

        private void nonNegativeCheck(long cap, String attribute) {
            if (cap < 0) {
                throw new IllegalArgumentException(String.format("%s %d is negative.", attribute, cap));
            }
        }
    }

    static class StringAlgorithm {
        private static int[] saNaive(int[] s) {
            int n = s.length;
            int[] sa = new int[n];
            for (int i = 0; i < n; i++) {
                sa[i] = i;
            }
            insertionsortUsingComparator(sa, (l, r) -> {
                while (l < n && r < n) {
                    if (s[l] != s[r])
                        return s[l] - s[r];
                    l++;
                    r++;
                }
                return -(l - r);
            });
            return sa;
        }

        public static int[] saDoubling(int[] s) {
            int n = s.length;
            int[] sa = new int[n];
            for (int i = 0; i < n; i++) {
                sa[i] = i;
            }
            int[] rnk = java.util.Arrays.copyOf(s, n);
            int[] tmp = new int[n];

            for (int k = 1; k < n; k *= 2) {
                final int _k = k;
                final int[] _rnk = rnk;
                java.util.function.IntBinaryOperator cmp = (x, y) -> {
                    if (_rnk[x] != _rnk[y])
                        return _rnk[x] - _rnk[y];
                    int rx = x + _k < n ? _rnk[x + _k] : -1;
                    int ry = y + _k < n ? _rnk[y + _k] : -1;
                    return rx - ry;
                };
                mergesortUsingComparator(sa, cmp);
                tmp[sa[0]] = 0;
                for (int i = 1; i < n; i++) {
                    tmp[sa[i]] = tmp[sa[i - 1]] + (cmp.applyAsInt(sa[i - 1], sa[i]) < 0 ? 1 : 0);
                }
                int[] buf = tmp;
                tmp = rnk;
                rnk = buf;
            }
            return sa;
        }

        private static void insertionsortUsingComparator(int[] a, java.util.function.IntBinaryOperator comparator) {
            final int n = a.length;
            for (int i = 1; i < n; i++) {
                final int tmp = a[i];
                if (comparator.applyAsInt(a[i - 1], tmp) > 0) {
                    int j = i;
                    do {
                        a[j] = a[j - 1];
                        j--;
                    } while (j > 0 && comparator.applyAsInt(a[j - 1], tmp) > 0);
                    a[j] = tmp;
                }
            }
        }

        private static void mergesortUsingComparator(int[] a, java.util.function.IntBinaryOperator comparator) {
            final int n = a.length;
            final int[] work = new int[n];
            for (int block = 1; block <= n; block <<= 1) {
                final int block2 = block << 1;
                for (int l = 0, max = n - block; l < max; l += block2) {
                    int m = l + block;
                    int r = Math.min(l + block2, n);
                    System.arraycopy(a, l, work, 0, block);
                    for (int i = l, wi = 0, ti = m;; i++) {
                        if (ti == r) {
                            System.arraycopy(work, wi, a, i, block - wi);
                            break;
                        }
                        if (comparator.applyAsInt(work[wi], a[ti]) > 0) {
                            a[i] = a[ti++];
                        } else {
                            a[i] = work[wi++];
                            if (wi == block)
                                break;
                        }
                    }
                }
            }
        }

        private static final int THRESHOLD_NAIVE = 50;
        // private static final int THRESHOLD_DOUBLING = 0;

        private static int[] sais(int[] s, int upper) {
            int n = s.length;
            if (n == 0)
                return new int[0];
            if (n == 1)
                return new int[] { 0 };
            if (n == 2) {
                if (s[0] < s[1]) {
                    return new int[] { 0, 1 };
                } else {
                    return new int[] { 1, 0 };
                }
            }
            if (n < THRESHOLD_NAIVE) {
                return saNaive(s);
            }
            // if (n < THRESHOLD_DOUBLING) {
            // return saDoubling(s);
            // }

            int[] sa = new int[n];
            boolean[] ls = new boolean[n];
            for (int i = n - 2; i >= 0; i--) {
                ls[i] = s[i] == s[i + 1] ? ls[i + 1] : s[i] < s[i + 1];
            }

            int[] sumL = new int[upper + 1];
            int[] sumS = new int[upper + 1];

            for (int i = 0; i < n; i++) {
                if (ls[i]) {
                    sumL[s[i] + 1]++;
                } else {
                    sumS[s[i]]++;
                }
            }

            for (int i = 0; i <= upper; i++) {
                sumS[i] += sumL[i];
                if (i < upper)
                    sumL[i + 1] += sumS[i];
            }

            java.util.function.Consumer<int[]> induce = lms -> {
                java.util.Arrays.fill(sa, -1);
                int[] buf = new int[upper + 1];
                System.arraycopy(sumS, 0, buf, 0, upper + 1);
                for (int d : lms) {
                    if (d == n)
                        continue;
                    sa[buf[s[d]]++] = d;
                }
                System.arraycopy(sumL, 0, buf, 0, upper + 1);
                sa[buf[s[n - 1]]++] = n - 1;
                for (int i = 0; i < n; i++) {
                    int v = sa[i];
                    if (v >= 1 && !ls[v - 1]) {
                        sa[buf[s[v - 1]]++] = v - 1;
                    }
                }
                System.arraycopy(sumL, 0, buf, 0, upper + 1);
                for (int i = n - 1; i >= 0; i--) {
                    int v = sa[i];
                    if (v >= 1 && ls[v - 1]) {
                        sa[--buf[s[v - 1] + 1]] = v - 1;
                    }
                }
            };

            int[] lmsMap = new int[n + 1];
            java.util.Arrays.fill(lmsMap, -1);
            int m = 0;
            for (int i = 1; i < n; i++) {
                if (!ls[i - 1] && ls[i]) {
                    lmsMap[i] = m++;
                }
            }

            int[] lms = new int[m];
            {
                int p = 0;
                for (int i = 1; i < n; i++) {
                    if (!ls[i - 1] && ls[i]) {
                        lms[p++] = i;
                    }
                }
            }

            induce.accept(lms);

            if (m > 0) {
                int[] sortedLms = new int[m];
                {
                    int p = 0;
                    for (int v : sa) {
                        if (lmsMap[v] != -1) {
                            sortedLms[p++] = v;
                        }
                    }
                }
                int[] recS = new int[m];
                int recUpper = 0;
                recS[lmsMap[sortedLms[0]]] = 0;
                for (int i = 1; i < m; i++) {
                    int l = sortedLms[i - 1], r = sortedLms[i];
                    int endL = (lmsMap[l] + 1 < m) ? lms[lmsMap[l] + 1] : n;
                    int endR = (lmsMap[r] + 1 < m) ? lms[lmsMap[r] + 1] : n;
                    boolean same = true;
                    if (endL - l != endR - r) {
                        same = false;
                    } else {
                        while (l < endL && s[l] == s[r]) {
                            l++;
                            r++;
                        }
                        if (l == n || s[l] != s[r])
                            same = false;
                    }
                    if (!same) {
                        recUpper++;
                    }
                    recS[lmsMap[sortedLms[i]]] = recUpper;
                }

                int[] recSA = sais(recS, recUpper);

                for (int i = 0; i < m; i++) {
                    sortedLms[i] = lms[recSA[i]];
                }
                induce.accept(sortedLms);
            }
            return sa;
        }

        public static int[] suffixArray(int[] s, int upper) {
            assert (0 <= upper);
            for (int d : s) {
                assert (0 <= d && d <= upper);
            }
            return sais(s, upper);
        }

        public static int[] suffixArray(int[] s) {
            int n = s.length;
            int[] vals = Arrays.copyOf(s, n);
            java.util.Arrays.sort(vals);
            int p = 1;
            for (int i = 1; i < n; i++) {
                if (vals[i] != vals[i - 1]) {
                    vals[p++] = vals[i];
                }
            }
            int[] s2 = new int[n];
            for (int i = 0; i < n; i++) {
                s2[i] = java.util.Arrays.binarySearch(vals, 0, p, s[i]);
            }
            return sais(s2, p);
        }

        public static int[] suffixArray(char[] s) {
            int n = s.length;
            int[] s2 = new int[n];
            for (int i = 0; i < n; i++) {
                s2[i] = s[i];
            }
            return sais(s2, 255);
        }

        public static int[] suffixArray(java.lang.String s) {
            return suffixArray(s.toCharArray());
        }

        public static int[] lcpArray(int[] s, int[] sa) {
            int n = s.length;
            assert (n >= 1);
            int[] rnk = new int[n];
            for (int i = 0; i < n; i++) {
                rnk[sa[i]] = i;
            }
            int[] lcp = new int[n - 1];
            int h = 0;
            for (int i = 0; i < n; i++) {
                if (h > 0)
                    h--;
                if (rnk[i] == 0) {
                    continue;
                }
                int j = sa[rnk[i] - 1];
                for (; j + h < n && i + h < n; h++) {
                    if (s[j + h] != s[i + h])
                        break;
                }
                lcp[rnk[i] - 1] = h;
            }
            return lcp;
        }

        public static int[] lcpArray(char[] s, int[] sa) {
            int n = s.length;
            int[] s2 = new int[n];
            for (int i = 0; i < n; i++) {
                s2[i] = s[i];
            }
            return lcpArray(s2, sa);
        }

        public static int[] lcpArray(java.lang.String s, int[] sa) {
            return lcpArray(s.toCharArray(), sa);
        }

        public static int[] zAlgorithm(int[] s) {
            int n = s.length;
            if (n == 0)
                return new int[0];
            int[] z = new int[n];
            for (int i = 1, j = 0; i < n; i++) {
                int k = j + z[j] <= i ? 0 : Math.min(j + z[j] - i, z[i - j]);
                while (i + k < n && s[k] == s[i + k])
                    k++;
                z[i] = k;
                if (j + z[j] < i + z[i])
                    j = i;
            }
            z[0] = n;
            return z;
        }

        public static int[] zAlgorithm(char[] s) {
            int n = s.length;
            if (n == 0)
                return new int[0];
            int[] z = new int[n];
            for (int i = 1, j = 0; i < n; i++) {
                int k = j + z[j] <= i ? 0 : Math.min(j + z[j] - i, z[i - j]);
                while (i + k < n && s[k] == s[i + k])
                    k++;
                z[i] = k;
                if (j + z[j] < i + z[i])
                    j = i;
            }
            z[0] = n;
            return z;
        }

        public static int[] zAlgorithm(String s) {
            return zAlgorithm(s.toCharArray());
        }
    }
}
