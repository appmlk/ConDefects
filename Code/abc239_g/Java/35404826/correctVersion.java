import java.util.*;
import java.io.*;

class Main {
    private static final void solve() throws IOException {
        final int n = ni(), nn = n << 1, m = ni();
        final long inf = (long) 1e13;
        var flow = new MaxFlow(nn);
        var uu = new int[m];
        var vv = new int[m];
        for (int i = 0; i < m; i++) {
            uu[i] = ni() - 1;
            vv[i] = ni() - 1;
            flow.addEdge(uu[i] + n, vv[i], inf);
            flow.addEdge(vv[i] + n, uu[i], inf);
        }
        var ty_cnt = new int[n];
        for (int i = 0; i < m; i++) {
            ty_cnt[uu[i]]++;
            ty_cnt[vv[i]]++;
        }
        var g = new int[n][];
        for (int i = 0; i < n; i++)
            g[i] = new int[ty_cnt[i]];
        Arrays.fill(ty_cnt, 0);
        for (int i = 0; i < m; i++) {
            g[uu[i]][ty_cnt[uu[i]]++] = vv[i];
            g[vv[i]][ty_cnt[vv[i]]++] = uu[i];
        }
        for (int i = 0; i < n; i++)
            flow.addEdge(i, i + n, nl());
        final long ans = flow.maxFlow(n, n - 1);
        var t = flow.minCut(n);
        var q = new ArrayDeque<Integer>();
        var u = new boolean[n];
        q.add(0);
        u[0] = true;
        var y = new boolean[n];
        l: while (!q.isEmpty()) {
            int x = q.remove();
            for (int i : g[x]) {
                if (u[i])
                    continue;
                if (!t[i]) {
                    y[x] = true;
                    continue l;
                }
            }
            for (int i : g[x]) {
                if (!u[i])
                    q.add(i);
                u[i] = true;
            }
        }
        var s = new ArrayList<Integer>();
        for (int i = 0; i < n; i++)
            if (y[i])
                s.add(i + 1);
        ou.println(ans).println(s.size()).print(s);
    }

    public static void main(String[] args) throws IOException {
        solve();
        ou.flush();
    }

    private static final int ni() throws IOException {
        return sc.nextInt();
    }

    private static final int[] ni(int n) throws IOException {
        return sc.nextIntArray(n);
    }

    private static final long nl() throws IOException {
        return sc.nextLong();
    }

    private static final long[] nl(int n) throws IOException {
        return sc.nextLongArray(n);
    }

    private static final String ns() throws IOException {
        return sc.next();
    }

    private static final ContestInputStream sc = new ContestInputStream();
    private static final ContestOutputStream ou = new ContestOutputStream();
}

class MaxFlow {
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
        for (int i = 0; i < n; i++)
            this.g[i] = new java.util.ArrayList<>();
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
            Arrays.fill(iter, 0);
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
        Arrays.fill(level, -1);
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
            throw new IllegalArgumentException(
                    String.format("%s %d is negative.", attribute, cap));
        }
    }
}

final class ContestInputStream extends FilterInputStream {
    protected final byte[] buf;
    protected int pos = 0;
    protected int lim = 0;
    private final char[] cbuf;

    public ContestInputStream() {
        super(System.in);
        this.buf = new byte[1 << 13];
        this.cbuf = new char[1 << 20];
    }

    boolean hasRemaining() throws IOException {
        if (pos < lim)
            return true;
        lim = in.read(buf);
        pos = 0;
        return lim > 0;
    }

    final int remaining() throws IOException {
        if (pos >= lim) {
            lim = in.read(buf);
            pos = 0;
        }
        return lim - pos;
    }

    @Override
    public final int available() throws IOException {
        if (pos < lim)
            return lim - pos + in.available();
        return in.available();
    }

    @Override
    public final long skip(long n) throws IOException {
        if (pos < lim) {
            int rem = lim - pos;
            if (n < rem) {
                pos += n;
                return n;
            }
            pos = lim;
            return rem;
        }
        return in.skip(n);
    }

    @Override
    public final int read() throws IOException {
        if (hasRemaining())
            return buf[pos++];
        return -1;
    }

    @Override
    public final int read(byte[] b, int off, int len) throws IOException {
        if (pos < lim) {
            int rem = Math.min(lim - pos, len);
            for (int i = 0; i < rem; i++)
                b[off + i] = buf[pos + i];
            pos += rem;
            return rem;
        }
        return in.read(b, off, len);
    }

    public final char[] readToken() throws IOException {
        int cpos = 0;
        int rem;
        byte b;
        l: while ((rem = remaining()) > 0) {
            for (int i = 0; i < rem; i++) {
                b = buf[pos + i];
                if (b <= 0x20) {
                    pos += i + 1;
                    cpos += i;
                    if (b == 0x0d && hasRemaining() && buf[pos] == 0x0a)
                        pos++;
                    break l;
                }
                cbuf[cpos + i] = (char) b;
            }
            pos += rem;
            cpos += rem;
        }
        char[] arr = new char[cpos];
        for (int i = 0; i < cpos; i++)
            arr[i] = cbuf[i];
        return arr;
    }

    public final int readToken(char[] cbuf, int off) throws IOException {
        int cpos = off;
        int rem;
        byte b;
        l: while ((rem = remaining()) > 0) {
            for (int i = 0; i < rem; i++) {
                b = buf[pos + i];
                if (b <= 0x20) {
                    pos += i + 1;
                    cpos += i;
                    if (b == 0x0d && hasRemaining() && buf[pos] == 0x0a)
                        pos++;
                    break l;
                }
                cbuf[cpos + i] = (char) b;
            }
            pos += rem;
            cpos += rem;
        }
        return cpos - off;
    }

    public final int readToken(char[] cbuf) throws IOException {
        return readToken(cbuf, 0);
    }

    public final String next() throws IOException {
        int cpos = 0;
        int rem;
        byte b;
        l: while ((rem = remaining()) > 0) {
            for (int i = 0; i < rem; i++) {
                b = buf[pos + i];
                if (b <= 0x20) {
                    pos += i + 1;
                    cpos += i;
                    if (b == 0x0d && hasRemaining() && buf[pos] == 0x0a)
                        pos++;
                    break l;
                }
                cbuf[cpos + i] = (char) b;
            }
            pos += rem;
            cpos += rem;
        }
        return String.valueOf(cbuf, 0, cpos);
    }

    public final char[] nextCharArray() throws IOException {
        return readToken();
    }

    public final int nextInt() throws IOException {
        if (!hasRemaining())
            return 0;
        int value = 0;
        byte b = buf[pos++];
        if (b == 0x2d) {
            while (hasRemaining() && (b = buf[pos++]) > 0x20)
                value = value * 10 - b + 0x30;
        } else {
            do {
                value = value * 10 + b - 0x30;
            } while (hasRemaining() && (b = buf[pos++]) > 0x20);
        }
        if (b == 0x0d && hasRemaining() && buf[pos] == 0x0a)
            pos++;
        return value;
    }

    public final long nextLong() throws IOException {
        if (!hasRemaining())
            return 0L;
        long value = 0L;
        byte b = buf[pos++];
        if (b == 0x2d) {
            while (hasRemaining() && (b = buf[pos++]) > 0x20)
                value = value * 10 - b + 0x30;
        } else {
            do {
                value = value * 10 + b - 0x30;
            } while (hasRemaining() && (b = buf[pos++]) > 0x20);
        }
        if (b == 0x0d && hasRemaining() && buf[pos] == 0x0a)
            pos++;
        return value;
    }

    public final char nextChar() throws IOException {
        if (!hasRemaining())
            throw new EOFException();
        final char c = (char) buf[pos++];
        if (hasRemaining() && buf[pos++] == 0x0d && hasRemaining() && buf[pos] == 0x0a)
            pos++;
        return c;
    }

    public final float nextFloat() throws IOException {
        return Float.parseFloat(next());
    }

    public final double nextDouble() throws IOException {
        return Double.parseDouble(next());
    }

    public final boolean[] nextBooleanArray(char ok) throws IOException {
        char[] s = readToken();
        int n = s.length;
        boolean[] t = new boolean[n];
        for (int i = 0; i < n; i++)
            t[i] = s[i] == ok;
        return t;
    }

    public final boolean[][] nextBooleanMatrix(int h, int w, char ok) throws IOException {
        boolean[][] s = new boolean[h][];
        for (int i = 0; i < h; i++) {
            char[] t = readToken();
            int n = t.length;
            s[i] = new boolean[n];
            for (int j = 0; j < n; j++)
                s[i][j] = t[j] == ok;
        }
        return s;
    }

    public final String[] nextStringArray(int len) throws IOException {
        String[] arr = new String[len];
        for (int i = 0; i < len; i++)
            arr[i] = next();
        return arr;
    }

    public final int[] nextIntArray(int len) throws IOException {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++)
            arr[i] = nextInt();
        return arr;
    }

    public final int[] nextIntArray(int len, java.util.function.IntUnaryOperator map) throws IOException {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++)
            arr[i] = map.applyAsInt(nextInt());
        return arr;
    }

    public final long[] nextLongArray(int len, java.util.function.LongUnaryOperator map) throws IOException {
        long[] arr = new long[len];
        for (int i = 0; i < len; i++)
            arr[i] = map.applyAsLong(nextLong());
        return arr;
    }

    public final int[][] nextIntMatrix(int h, int w) throws IOException {
        int[][] arr = new int[h][w];
        for (int i = 0; i < h; i++)
            for (int j = 0; j < w; j++)
                arr[i][j] = nextInt();
        return arr;
    }

    public final int[][] nextIntMatrix(int h, int w, java.util.function.IntUnaryOperator map) throws IOException {
        int[][] arr = new int[h][w];
        for (int i = 0; i < h; i++)
            for (int j = 0; j < w; j++)
                arr[i][j] = map.applyAsInt(nextInt());
        return arr;
    }

    public final long[] nextLongArray(int len) throws IOException {
        long[] arr = new long[len];
        for (int i = 0; i < len; i++)
            arr[i] = nextLong();
        return arr;
    }

    public final long[][] nextLongMatrix(int h, int w) throws IOException {
        long[][] arr = new long[h][w];
        for (int i = 0; i < h; i++)
            for (int j = 0; j < w; j++)
                arr[i][j] = nextLong();
        return arr;
    }

    public final float[] nextFloatArray(int len) throws IOException {
        float[] arr = new float[len];
        for (int i = 0; i < len; i++)
            arr[i] = nextFloat();
        return arr;
    }

    public final double[] nextDoubleArray(int len) throws IOException {
        double[] arr = new double[len];
        for (int i = 0; i < len; i++)
            arr[i] = nextDouble();
        return arr;
    }

    public final char[][] nextCharMatrix(int h, int w) throws IOException {
        char[][] arr = new char[h][];
        for (int i = 0; i < h; i++)
            arr[i] = readToken();
        return arr;
    }

    public final void nextThrow() throws IOException {
        next();
        return;
    }

    public final void nextThrow(int n) throws IOException {
        for (int i = 0; i < n; i++)
            nextThrow();
        return;
    }
}

final class ContestOutputStream extends FilterOutputStream implements Appendable {
    protected final byte[] buf;
    protected int pos = 0;

    public ContestOutputStream() {
        super(System.out);
        this.buf = new byte[1 << 13];
    }

    @Override
    public void flush() throws IOException {
        out.write(buf, 0, pos);
        pos = 0;
        out.flush();
    }

    void put(byte b) throws IOException {
        if (pos >= buf.length)
            flush();
        buf[pos++] = b;
    }

    int remaining() throws IOException {
        if (pos >= buf.length)
            flush();
        return buf.length - pos;
    }

    @Override
    public void write(int b) throws IOException {
        put((byte) b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        int o = off;
        int l = len;
        while (l > 0) {
            int rem = Math.min(remaining(), l);
            for (int i = 0; i < rem; i++)
                buf[pos + i] = b[o + i];
            pos += rem;
            o += rem;
            l -= rem;
        }
    }

    @Override
    public ContestOutputStream append(char c) throws IOException {
        put((byte) c);
        return this;
    }

    @Override
    public ContestOutputStream append(CharSequence csq, int start, int end) throws IOException {
        int off = start;
        int len = end - start;
        while (len > 0) {
            int rem = Math.min(remaining(), len);
            for (int i = 0; i < rem; i++)
                buf[pos + i] = (byte) csq.charAt(off + i);
            pos += rem;
            off += rem;
            len -= rem;
        }
        return this;
    }

    @Override
    public ContestOutputStream append(CharSequence csq) throws IOException {
        return append(csq, 0, csq.length());
    }

    public ContestOutputStream append(char[] arr, int off, int len) throws IOException {
        int o = off;
        int l = len;
        while (l > 0) {
            int rem = Math.min(remaining(), l);
            for (int i = 0; i < rem; i++)
                buf[pos + i] = (byte) arr[o + i];
            pos += rem;
            o += rem;
            l -= rem;
        }
        return this;
    }

    public ContestOutputStream print(char[] arr) throws IOException {
        return append(arr, 0, arr.length).newLine();
    }

    public ContestOutputStream print(boolean value) throws IOException {
        if (value)
            return append("o");
        return append("x");
    }

    public ContestOutputStream println(boolean value) throws IOException {
        if (value)
            return append("o\n");
        return append("x\n");
    }

    public ContestOutputStream print(boolean[][] value) throws IOException {
        final int n = value.length, m = value[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++)
                print(value[i][j]);
            newLine();
        }
        return this;
    }

    public ContestOutputStream print(int value) throws IOException {
        return append(String.valueOf(value));
    }

    public ContestOutputStream println(int value) throws IOException {
        return append(String.valueOf(value)).newLine();
    }

    public ContestOutputStream print(long value) throws IOException {
        return append(String.valueOf(value));
    }

    public ContestOutputStream println(long value) throws IOException {
        return append(String.valueOf(value)).newLine();
    }

    public ContestOutputStream print(float value) throws IOException {
        return append(String.valueOf(value));
    }

    public ContestOutputStream println(float value) throws IOException {
        return append(String.valueOf(value)).newLine();
    }

    public ContestOutputStream print(double value) throws IOException {
        return append(String.valueOf(value));
    }

    public ContestOutputStream println(double value) throws IOException {
        return append(String.valueOf(value)).newLine();
    }

    public ContestOutputStream print(char value) throws IOException {
        return append(value);
    }

    public ContestOutputStream println(char value) throws IOException {
        return append(value).newLine();
    }

    public ContestOutputStream print(String value) throws IOException {
        return append(value);
    }

    public ContestOutputStream println(String value) throws IOException {
        return append(String.valueOf(value)).newLine();
    }

    public ContestOutputStream print(Object value) throws IOException {
        return append(String.valueOf(value));
    }

    public ContestOutputStream println(Object value) throws IOException {
        return append(String.valueOf(value)).newLine();
    }

    public ContestOutputStream printYN(boolean yes) throws IOException {
        if (yes)
            return println("Yes");
        return println("No");
    }

    public ContestOutputStream printAB(boolean yes) throws IOException {
        if (yes)
            return println("Alice");
        return println("Bob");
    }

    public ContestOutputStream print(CharSequence[] arr) throws IOException {
        if (arr.length > 0) {
            append(arr[0]);
            for (int i = 1; i < arr.length; i++)
                append('\u0020').append(arr[i]);
        }
        return this;
    }

    public ContestOutputStream print(int[] arr) throws IOException {
        if (arr.length > 0) {
            print(arr[0]);
            for (int i = 1; i < arr.length; i++)
                append('\u0020').print(arr[i]);
        }
        newLine();
        return this;
    }

    public ContestOutputStream print(int[] arr, int length) throws IOException {
        if (length > 0)
            print(arr[0]);
        for (int i = 1; i < length; i++)
            append('\u0020').print(arr[i]);
        newLine();
        return this;
    }

    public ContestOutputStream println(int[] arr) throws IOException {
        for (int i : arr)
            print(i).newLine();
        return this;
    }

    public ContestOutputStream println(int[] arr, int length) throws IOException {
        for (int i = 0; i < length; i++)
            println(arr[i]);
        return this;
    }

    public ContestOutputStream print(boolean[] arr) throws IOException {
        if (arr.length > 0) {
            print(arr[0]);
            for (int i = 1; i < arr.length; i++)
                append('\u0020').print(arr[i]);
        }
        newLine();
        return this;
    }

    public ContestOutputStream print(long[] arr) throws IOException {
        if (arr.length > 0) {
            print(arr[0]);
            for (int i = 1; i < arr.length; i++)
                append('\u0020').print(arr[i]);
        }
        newLine();
        return this;
    }

    public ContestOutputStream print(long[] arr, int length) throws IOException {
        if (length > 0)
            print(arr[0]);
        for (int i = 1; i < length; i++)
            append('\u0020').print(arr[i]);
        newLine();
        return this;
    }

    public ContestOutputStream println(long[] arr, int length) throws IOException {
        for (int i = 0; i < length; i++)
            println(arr[i]);
        return this;
    }

    public ContestOutputStream println(long[] arr) throws IOException {
        for (long i : arr)
            print(i).newLine();
        return this;
    }

    public ContestOutputStream print(float[] arr) throws IOException {
        if (arr.length > 0) {
            print(arr[0]);
            for (int i = 1; i < arr.length; i++)
                append('\u0020').print(arr[i]);
        }
        return this;
    }

    public ContestOutputStream println(float[] arr) throws IOException {
        for (float i : arr)
            print(i).newLine();
        return this;
    }

    public ContestOutputStream print(double[] arr) throws IOException {
        if (arr.length > 0) {
            print(arr[0]);
            for (int i = 1; i < arr.length; i++)
                append('\u0020').print(arr[i]);
        }
        return newLine();
    }

    public ContestOutputStream println(double[] arr) throws IOException {
        for (double i : arr)
            print(i).newLine();
        return this;
    }

    public ContestOutputStream print(Object[] arr) throws IOException {
        if (arr.length > 0) {
            print(arr[0]);
            for (int i = 1; i < arr.length; i++)
                append('\u0020').print(arr[i]);
        }
        return newLine();
    }

    public ContestOutputStream print(java.util.ArrayList<?> arr) throws IOException {
        if (!arr.isEmpty()) {
            final int n = arr.size();
            print(arr.get(0));
            for (int i = 1; i < n; i++)
                print(" ").print(arr.get(i));
        }
        return newLine();
    }

    public ContestOutputStream println(java.util.ArrayList<?> arr) throws IOException {
        final int n = arr.size();
        for (int i = 0; i < n; i++)
            println(arr.get(i));
        return this;
    }

    public ContestOutputStream println(Object[] arr) throws IOException {
        for (Object i : arr)
            print(i).newLine();
        return this;
    }

    public ContestOutputStream newLine() throws IOException {
        return append(System.lineSeparator());
    }

    public ContestOutputStream endl() throws IOException {
        newLine().flush();
        return this;
    }

    public ContestOutputStream print(int[][] arr) throws IOException {
        for (int[] i : arr)
            print(i);
        return this;
    }

    public ContestOutputStream print(long[][] arr) throws IOException {
        for (long[] i : arr)
            print(i);
        return this;
    }

    public ContestOutputStream print(char[][] arr) throws IOException {
        for (char[] i : arr)
            print(i);
        return this;
    }

    public ContestOutputStream print(Object[][] arr) throws IOException {
        for (Object[] i : arr)
            print(i);
        return this;
    }

    public ContestOutputStream println() throws IOException {
        return newLine();
    }
}