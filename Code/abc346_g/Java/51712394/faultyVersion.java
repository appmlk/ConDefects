
import java.io.*;
import java.util.*;

class MinValSegTree {
    static final long INIT = 0;
    static final int OP_ADD = 1;
    static final int OP_SET = 2;

    static class Node {
        Node left;
        Node right;
        int ls, rs;//debug用

        long minVal;
        long minFreq;
        int lazyType;
        long lazyVal;
    }

    int maxN;
    Node root;

    public MinValSegTree(int maxN) {
        this.maxN = maxN;
        this.root = createNode(0, maxN);
        root.ls = 0;
        root.rs = maxN;
    }

    Node createNode(int ls, int rs) {
        Node r = new Node();
        r.minVal = INIT;
        r.minFreq = rs-ls+1;
        return r;
    }

    void apply(Node node, int ls, int rs, int type, long val) {
        node.lazyType = type;
        node.lazyVal += val;
        if (type == OP_ADD) {
            node.minVal += val;
        } else if (type==OP_SET) {
            throw new RuntimeException();
        }
    }

    void reduce(Node node, Node left, Node right, int ls, int rs) {
        long m = Math.min(left.minVal,right.minVal);
        node.minVal = Math.min(left.minVal,right.minVal);
        node.minFreq = (left.minVal==m? left.minFreq : 0) +
                (right.minVal==m? right.minFreq : 0);
    }

    public void add(int l, int r, long val) {
        update(root, l, r, 0, maxN, OP_ADD, val);
    }

    public void set(int l, int r, long val) {
        update(root, l, r, 0, maxN, OP_SET, val);
    }

    void build(int[] vals) {
        build(root, vals, 0, maxN);
    }

    private void build(Node node, int[] vals, int ls, int rs) {
        if (ls==rs) {
            apply(node, ls, rs, OP_SET, ls>=vals.length ? INIT : vals[ls]);
            return;
        }
        pushDown(node, ls, rs);
        int mid = ls + rs >> 1;
        build(node.left,vals, ls, mid);
        build(node.right,vals,mid + 1, rs);
        reduce(node, node.left, node.right, ls, rs);
    }

    /**
     * 当前Node的范围: [ls,rs]
     */
    private void update(Node node, int l, int r, int ls, int rs, int type, long val) {
        if (l < 0 || r > maxN) {
            throw new IllegalArgumentException();
        }
        if (l <= ls && rs <= r) {
            apply(node, ls, rs, type, val);
            return;
        }

        pushDown(node, ls, rs);
        int mid = ls + rs >> 1;
        //左子树[ls,mid]
        //右子树[mid+1,rs]
        if (l <= mid) {
            update(node.left, l, r, ls, mid, type, val);
        }
        if (r >= mid + 1) {
            update(node.right, l, r, mid + 1, rs, type, val);
        }
        reduce(node, node.left, node.right, ls, rs);
    }

    //下发lazy值
    void pushDown(Node node, int ls, int rs) {
        int mid = ls + rs >> 1;
        if (node.left == null) {
            node.left = createNode(ls, mid);
            node.left.ls = ls;
            node.left.rs = mid;
        }
        if (node.right == null) {
            node.right = createNode(mid + 1, rs);
            node.right.ls = mid + 1;
            node.right.rs = rs;
        }
        if (node.lazyType != 0) {
            apply(node.left, ls, mid, node.lazyType, node.lazyVal);
            apply(node.right, mid + 1, rs, node.lazyType, node.lazyVal);
            node.lazyType = 0;
            node.lazyVal = 0;
        }
    }

    public Node queryMinVal(int l, int r) {
        queryNode.minVal = Long.MAX_VALUE;
        queryNode.minFreq = 0;
        query(root, l, r, 0, maxN);
        return queryNode;
    }

    private final Node queryNode = new Node();

    private void query(Node node, int l, int r, int ls, int rs) {
        if (l < 0 || r > maxN) {
            throw new IllegalArgumentException();
        }
        if (l <= ls && rs <= r) {
            // reduce是从left和right重新计算，原root的值不保留
            reduce(queryNode, node, queryNode, ls, rs);
            return;
        }
        pushDown(node, ls, rs);
        int mid = ls + rs >> 1;
        if (l <= mid) {
            query(node.left, l, r, ls, mid);
        }
        if (r >= mid + 1) {
            query(node.right, l, r, mid + 1, rs);
        }
    }

}
public class Main implements Runnable {

    void solve() {
        int n=nextInt();
        int[] a=new int[n];
        for (int i = 0; i < n; i++) {
            a[i]=nextInt();
            --a[i];
        }
        long res=0;
        List<List<Integer>> occ = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            List<Integer> innerList = new ArrayList<>();
            innerList.add(-1); // 将-1加入到每个内部列表中
            occ.add(innerList);
        }
        MinValSegTree tree = new MinValSegTree(n);
        for (int i = 0; i < n; i++) {
            List<Integer> l1 = occ.get(a[i]);
            int p1=l1.get(l1.size()-1);
            if (l1.size()>=2){
                int p2=l1.get(l1.size()-2);
                tree.add(p2+1,p1,-1);
            }
            l1.add(i);
            tree.add(p1+1,i,1);
            MinValSegTree.Node node = tree.queryMinVal(0, i);
            res+=i+1-(node.minVal==0?node.minFreq:0);
        }
        System.out.println(res);
    }

    public static void main(String[] args) throws Exception {
        new Thread(null, new Main(), "CustomThread", 1024 * 1024 * 100).start();
    }

    @Override
    public void run() {
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

