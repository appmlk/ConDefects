
import java.util.*;
import java.io.*;
class BitSegTree {
    static NodePool TMP_POOL = new NodePool();
    static class NodePool {
        int i = 1;
        //数量有限，只能用作一次性临时变量，递归时要后序获取
        Node[] pool = new Node[100];

        public NodePool() {
            for (int j = 0; j < pool.length; j++) {
                pool[j] = new Node();
            }
            pool[0].init(0, -1);
        }

        Node immutableEmpty() {
            return pool[0];
        }

        Node next(int ls, int rs) {
            Node ret = pool[i++];
            ret.init(ls, rs);
            if (i == pool.length) i = 1;
            return ret;
        }
    }

    static class Node {
        Node left;
        Node right;
        long sum;
        int ls, rs;//debug用
        OP lazy;
        int l0,r0,mx0;//左端连续0，右端连续0，区间连续0
        int l1,r1,mx1;//左端连续1，右端连续1，区间连续1

        void init(int ls, int rs) {
            this.ls = ls;
            this.rs = rs;
            this.l0=this.r0=this.mx0=rs-ls+1;
            this.l1=this.r1=this.mx1=0;
        }

        int len() {
            return rs-ls+1;
        }
    }

    enum OP {
        SET,
        UNSET,
        INV
    }

    int maxN;
    Node root;

    public BitSegTree(int maxN) {
        this.maxN = maxN;
        this.root = new Node();
        this.root.init(0, maxN);
    }

    void reduce(Node node, Node left, Node right) {
        node.sum = left.sum + right.sum;
        node.mx0 =Math.max(left.r0+right.l0, Math.max(left.mx0, right.mx0));
        node.l0=(left.l0==left.len())?left.l0+right.l0:left.l0;
        node.r0=(right.r0==right.len())?right.r0+left.r0:right.r0;

        node.mx1 =Math.max(left.r1+right.l1, Math.max(left.mx1, right.mx1));
        node.l1=(left.l1==left.len())?left.l1+right.l1:left.l1;
        node.r1=(right.r1==right.len())?right.r1+left.r1:right.r1;
    }

    void apply(Node node, int ls, int rs, OP op) {
        if (op == OP.INV) {
            if (node.lazy == OP.SET) {
                op = OP.UNSET;
            } else if (node.lazy == OP.UNSET) {
                op = OP.SET;
            }
        }
        node.lazy = (node.lazy == OP.INV && op == OP.INV) ? null : op;
        if (op == OP.SET) {
            node.l0=node.r0=node.mx0 =0;
            node.l1=node.r1=node.mx1 =rs-ls+1;
            node.sum = (rs - ls + 1);
        } else if (op == OP.UNSET) {
            node.l0=node.r0=node.mx0 =rs-ls+1;
            node.l1=node.r1=node.mx1 =0;
            node.sum = 0;
        } else if (op == OP.INV) {
            int lt=node.l0,rt=node.r0,mt=node.mx0;
            node.l0=node.l1;node.r0=node.r1;node.mx0 =node.mx1;
            node.l1=lt;node.r1=rt;node.mx1 =mt;
            node.sum = (rs - ls + 1) - node.sum;
        }
    }

    public void add(int l, int r, OP op) {
        add(root, l, r, 0, maxN, op);
    }

    private void add(Node node, int l, int r, int ls, int rs, OP op) {
        if (l < 0 || r > maxN) {
            throw new IllegalArgumentException();
        }
        if (l <= ls && rs <= r) {
            apply(node, ls, rs, op);
            return;
        }

        pushDown(node, ls, rs);
        int mid = ls + rs >> 1;
        if (l <= mid) {
            add(node.left, l, r, ls, mid, op);
        }
        if (r >= mid + 1) {
            add(node.right, l, r, mid + 1, rs, op);
        }

        reduce(node, node.left, node.right);
    }

    void pushDown(Node node, int ls, int rs) {
        int mid = ls + rs >> 1;
        if (node.left == null) {
            node.left = new Node();
            node.left.init(ls, mid);
        }
        if (node.right == null) {
            node.right = new Node();
            node.right.init(mid+1, rs);
        }
        if (node.lazy!=null) {
            // 1 如果有多种懒操作变量，注意下传顺序，以及下传后的重置
            // 2 lazyVal会累积，即使每次add都是val==1，下传的时候lazyVal也会>1
            apply(node.left, ls, mid, node.lazy);
            apply(node.right, mid + 1, rs, node.lazy);
            node.lazy = null;
        }
    }

    public Node query(int l, int r) {
        return query(root, l, r, 0, maxN);
    }

    private static final Node EMPTY = new Node();
    static {
        EMPTY.init(0, -1);
    }

    private Node query(Node node, int l, int r, int ls, int rs) {
        if (l < 0 || r > maxN) {
            throw new IllegalArgumentException();
        }
        if (l <= ls && rs <= r) {
            return node;
        }
        pushDown(node, ls, rs);
        int mid = ls + rs >> 1;
        Node left, right;
        left = right = EMPTY;
        if (l <= mid) {
            left = query(node.left, l, r, ls, mid);
        }
        if (r >= mid + 1) {
            right = query(node.right, l, r, mid + 1, rs);
        }
        Node ret = TMP_POOL.next(Math.max(ls, l), Math.min(rs, r));
        reduce(ret, left, right);
        return ret;
    }

}

public class Main {
    public void solve() throws Exception {
        int n=nextInt(),q=nextInt();
        String s=next();
        BitSegTree tree=new BitSegTree(n);
        for (int i = 0; i < n; i++) {
            if (s.charAt(i)=='1'){
                tree.add(i,i, BitSegTree.OP.SET);
            }
            //vals[i]=s.charAt(i)-'0';
        }
        for (int i = 0; i < q; i++) {
            int c=nextInt(),l=nextInt()-1,r=nextInt()-1;
            if (c==1){
                tree.add(l,r, BitSegTree.OP.INV);
            } else {
                out.println(tree.query(l,r).mx1);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
        out.flush();
    }

    static PrintWriter out = new PrintWriter(System.out, false);
    static InputReader in = new InputReader(System.in);
    static String next() { return in.next(); }
    static int nextInt() { return Integer.parseInt(in.next()); }
    static long nextLong() { return Long.parseLong(in.next()); }
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }
    }
}


