import java.io.*;
import java.util.*;

import static java.lang.String.*;


public class Main {
    static int mod = (int) 1e9 + 7;
    static int INF = (int) 2e9;
    static int base = 131;

    public static void main(String[] args) throws IOException {
        FastScanner f = new FastScanner();
        PrintWriter w = new PrintWriter(System.out);
        int n = f.nextInt();
        int q = f.nextInt();
        a = new int[n+1];
        for (int i = 1; i <= n; i++) {
            a[i] = f.nextInt();
        }
        segTree seg = new segTree();
        seg.build(1, 1, n);
        for (int i = 0; i < q; i++) {
            int op = f.nextInt();
            int l = f.nextInt();
            int r = f.nextInt();
            if (op == 1){
                seg.change(1, l, r);

            }else {
                Node node = seg.sum(1, l, r);
                w.println(node.secCnt);
            }
        }
        w.flush();
        w.close();

    }

    static int MAXN = 200005;
    static int[] a;
    public static class segTree {
        Node[] t = new Node[4 * MAXN];

        public segTree() {
            for (int i = 0; i < 4 * MAXN; i++) {
                t[i] = new Node();
            }
        }


        public void build(int root, int l, int r){
            t[root].l = l;
            t[root].r = r;
//            t[root].lazy[1] = 0; // 因为默认赋值为1， 这步初始化可以省略
            if (l == r){
                t[root].max = a[l];
                t[root].maxCnt = 1;
                t[root].sec = -1;
                t[root].secCnt = 0;
            }else {
                int mid = (l + r) >> 1;
                int ch = root << 1;
                build(ch, l, mid);
                build(ch+1, mid+1, r);
                add(t[root], t[ch], t[ch+1]);
            }
        }

        public void change(int root, int l, int val){
            if (l == t[root].l && l == t[root].r){
                t[root].max = val;
                t[root].maxCnt = 1;
                t[root].sec = -1;
                t[root].secCnt = 0;
                return;
            }
            int mid = (t[root].l + t[root].r) >> 1;
            int ch = root << 1;
            if (l <= mid) change(ch, l, val);
            else change(ch + 1, l, val);
            add(t[root], t[ch], t[ch+1]); // 儿子可能发生了修改，所以需要进行答案更新
        }

        public Node sum(int root, int l, int r){
            if (l == t[root].l && r == t[root].r){
                return t[root];
            }
            int mid = (t[root].l + t[root].r) >> 1;
            int ch = root << 1;
            long ans = 0;
            if (r <= mid)return sum(ch, l, r);
            else if (l > mid)return sum(ch + 1, l, r);
            else {
                Node n1 = sum(ch, l, mid);
                Node n2 = sum(ch + 1, mid + 1, r);
                Node node = new Node();
                add(node, n1, n2);
                return node;
            }
        }
    }
    public static void add(Node t, Node l, Node r){
        t.maxCnt = 0;
        t.secCnt = 0;

        if (l.max == r.max){
            t.max = l.max;
            t.maxCnt = l.maxCnt + r.maxCnt;
            t.sec = Math.max(l.sec, r.sec);
            if (t.sec == l.sec) t.secCnt += l.secCnt;
            if (t.sec == r.sec) t.secCnt += r.secCnt;
        } else if (l.max > r.max) {
            t.max = l.max;
            t.maxCnt = l.maxCnt;
            if (l.sec == r.max){
                t.sec = l.sec;
                t.secCnt = l.secCnt + r.maxCnt;
            } else if (l.sec > r.max) {
                t.sec = l.sec;
                t.secCnt = l.secCnt;
            }else {
                t.sec = r.max;
                t.secCnt = t.maxCnt;
            }
        }else {
            t.max = r.max;
            t.maxCnt = r.maxCnt;
            if (l.max == r.sec){
                t.sec = l.max;
                t.secCnt = l.maxCnt + r.secCnt;
            } else if (l.max > r.sec) {
                t.sec = l.max;
                t.secCnt = l.maxCnt;
            }else {
                t.sec = r.sec;
                t.secCnt = r.secCnt;
            }
        }
    }
    public static class Node{
        int l, r;
        int max;
        int maxCnt;
        int sec;
        int secCnt;

    }

    private static class FastScanner {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        private FastScanner() throws IOException {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        private short nextShort() throws IOException {
            short ret = 0;
            byte c = read();
            while (c <= ' ') c = read();
            boolean neg = (c == '-');
            if (neg) c = read();
            do ret = (short) (ret * 10 + c - '0');
            while ((c = read()) >= '0' && c <= '9');
            if (neg) return (short) -ret;
            return ret;
        }

        private int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') c = read();
            boolean neg = (c == '-');
            if (neg) c = read();
            do ret = ret * 10 + c - '0';
            while ((c = read()) >= '0' && c <= '9');
            if (neg) return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ') c = read();
            boolean neg = (c == '-');
            if (neg) c = read();
            do ret = ret * 10 + c - '0';
            while ((c = read()) >= '0' && c <= '9');
            if (neg) return -ret;
            return ret;
        }

        private char nextChar() throws IOException {
            byte c = read();
            while (c <= ' ') c = read();
            return (char) c;
        }

        private String nextString() throws IOException {
            StringBuilder ret = new StringBuilder();
            byte c = read();
            while (c <= ' ') c = read();
            do {
                ret.append((char) c);
            } while ((c = read()) > ' ');
            return ret.toString();
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1) buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead) fillBuffer();
            return buffer[bufferPointer++];
        }
    }


}




