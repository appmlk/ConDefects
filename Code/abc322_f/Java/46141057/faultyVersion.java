
import java.util.*;
import java.io.*;
class BitSegTree {

    static class Node {
        Node left;
        Node right;
        long sum;
        int ls, rs;//debug用
        OP lazy;
        int l0,r0,mx0,l1,r1,mx1;
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
        this.root = create(0, maxN);
    }

    Node create(int ls, int rs) {
        Node n=new Node();
        n.ls=ls;
        n.rs=rs;
        n.l0=n.r0=n.mx0=rs-ls+1;
        n.l1=n.r1=n.mx1=0;
        return n;
    }
    
    void reduce(Node node, Node left, Node right, int ls, int rs) {
        int mid = ls + rs >> 1;
        node.sum = left.sum + right.sum;
        node.mx0 =Math.max(left.r0+right.l0, Math.max(left.mx0, right.mx0));
        node.l0=(left.l0==mid-ls+1)?left.l0+right.l0:left.l0;
        node.r0=(right.r0==rs-mid)?right.r0+left.r0:right.r0;

        node.mx1 =Math.max(left.r1+right.l1, Math.max(left.mx1, right.mx1));
        node.l1=(left.l1==mid-ls+1)?left.l1+right.l1:left.l1;
        node.r1=(right.r1==rs-mid)?right.r1+left.r1:right.r1;
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
        
        reduce(node, node.left, node.right, ls, rs);
    }

    /**
     * O(n)设置初始值，但未必时间更短，因为可能不需要每个节点都创建出来
     */
    void build(int[] vals) {
        build(root, vals, 0, maxN);
    }

    private void build(Node node, int[] vals, int ls, int rs) {
        if (ls==rs) {
            apply(node, ls, rs, vals[ls]==1?OP.SET:OP.UNSET);
            return;
        }
        pushDown(node, ls, rs);
        int mid = ls + rs >> 1;
        build(node.left,vals, ls, mid);
        build(node.right,vals,mid + 1, rs);
        reduce(node, node.left, node.right, ls, rs);
    }

    void pushDown(Node node, int ls, int rs) {
        int mid = ls + rs >> 1;
        if (node.left == null) {
            node.left = create(ls, mid);
        }
        if (node.right == null) {
            node.right = create(mid+1, rs);
        }
        if (node.lazy!=null) {
            apply(node.left, ls, mid, node.lazy);
            apply(node.right, mid + 1, rs, node.lazy);
            node.lazy = null;
        }
    }

    private static final Node EMPTY = new Node();
    private final Node sumAns=new Node();

    public long sum(int l, int r) {
        sumAns.sum=0;
        sum(root, l, r, 0, maxN);
        return sumAns.sum;
    }

    private void sum(Node node, int l, int r, int ls, int rs) {
        if (l < 0 || r > maxN) {
            throw new IllegalArgumentException();
        }
        if (l <= ls && rs <= r) {
            reduce(sumAns, node, EMPTY, ls, rs);
            return;
        }
        pushDown(node, ls, rs);
        int mid = ls + rs >> 1;
        if (l <= mid) {
            sum(node.left, l, r, ls, mid);
        }
        if (r >= mid + 1) {
            sum(node.right, l, r, mid + 1, rs);
        }
    }

    /**
     * sum方法为了减少创建对象，所有[l,r]范围内覆盖节点合计(reduce)到全局对象。
     * 但有些情况下必须每一层左右区间合并，比如求区间内连续1的数量，可以用此方法。
     */
    public Node sumInLevel(int l, int r) {
        return sumInLevel(root, l, r, 0, maxN);
    }

    private Node sumInLevel(Node node, int l, int r, int ls, int rs) {
        if (l < 0 || r > maxN) {
            throw new IllegalArgumentException();
        }
        if (l <= ls && rs <= r) {
            return node;
        }
        pushDown(node, ls, rs);
        int mid = ls + rs >> 1;
        Node res = new Node(), leftRes = EMPTY, rightRes = EMPTY;
        if (l <= mid) {
            leftRes = sumInLevel(node.left, l, r, ls, mid);
        }
        if (r >= mid + 1) {
            rightRes = sumInLevel(node.right, l, r, mid + 1, rs);
        }
        reduce(res, leftRes, rightRes, ls, rs);
        return res;
    }
}

public class Main {
    public void solve() throws Exception {
        int n=nextInt(),q=nextInt();
        String s=next();
        BitSegTree tree=new BitSegTree(n);
        int[] vals=new int[n+1];
        for (int i = 0; i < n; i++) {
            vals[i]=s.charAt(i)-'0';
        }
        tree.build(vals);
        for (int i = 0; i < q; i++) {
            int c=nextInt(),l=nextInt()-1,r=nextInt()-1;
            if (c==1){
                tree.add(l,r, BitSegTree.OP.INV);
            } else {
                out.println(tree.sumInLevel(l,r));
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


