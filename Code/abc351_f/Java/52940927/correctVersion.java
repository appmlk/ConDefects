/**
 * F - Double Sum
 */

import java.util.*;

class Main {
    public static void main(String[] arg) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        AVLTree tree = new AVLTree();

        long s = 0;
        for (int i = n - 1; i >= 0; i--) {
            tree.add(a[i]);
            int  ti = tree.total_right(a[i]); // a[i]より大きい要素の個数
            long si = tree.sum_right(a[i]);   // a[i]より大きい要素の総和
            s += si - (long)a[i] * ti;
        }
        System.out.println(s);
    }
}

class AVLTree {
    class Node {
        Node left  = null;
        Node right = null;
        long key;
        int height = 1;
        int count = 1; // # of elements with the same key
        int total = 1; // # of elements in the subtree
        long sum;      // sum of elements in the subtree

        Node(int x) {
            this.key = x;
            this.sum = x;
        }
    }

    static int height(Node node) { return node == null ? 0 : node.height; }
    static int total(Node node)  { return node == null ? 0 : node.total; }
    static long sum(Node node)   { return node == null ? 0 : node.sum; }
    static int max(int a, int b) { return a > b ? a : b; }

    Node root = null;

    void add(int x) {
        root = insert(root, new Node(x));
    }
    private Node insert(Node pos, Node node) {
        if (pos == null) {
            return node;
        }
        pos.total += node.total;
        pos.sum   += node.sum;
        if (node.key == pos.key) {
            pos.count += node.count;
            return pos;
        } else if (node.key < pos.key) {
            pos.left  = insert(pos.left, node);
        } else {
            pos.right = insert(pos.right, node);
        }

        int lh = height(pos.left);
        int rh = height(pos.right);
        pos.height = 1 + max(lh, rh);

        int balance = lh - rh;
        if (balance > 1) {
            if (node.key > pos.left.key) {
                pos.left = left_rotate(pos.left);
            }
            return right_rotate(pos);
        } else if (balance < -1) {
            if (node.key < pos.right.key) {
                pos.right = right_rotate(pos.right);
            }
            return left_rotate(pos);
        } else {
            return pos;
        }
    }

    private Node left_rotate(Node pos) {
        Node y = pos.right;
        pos.right = y.left;
        y.left = pos;
        pos.height = 1 + max(height(pos.left), height(pos.right));
        y.height   = 1 + max(height(pos), height(y.right));
        pos.total  = pos.count + total(pos.left) + total(pos.right);
        y.total    = y.count   + total(pos) + total(y.right);
        pos.sum    = pos.key * pos.count + sum(pos.left) + sum(pos.right);
        y.sum      = y.key   * y.count   + sum(pos) + sum(y.right);
        return y;
    }
    private Node right_rotate(Node pos) {
        Node x = pos.left;
        pos.left = x.right;
        x.right = pos;
        pos.height = 1 + max(height(pos.left), height(pos.right));
        x.height   = 1 + max(height(pos), height(x.left));
        pos.total  = pos.count + total(pos.left) + total(pos.right);
        x.total    = x.count   + total(pos) + total(x.left);
        pos.sum    = pos.key * pos.count + sum(pos.left) + sum(pos.right);
        x.sum      = x.key   * x.count   + sum(pos) + sum(x.left);
        return x;
    }

    /** Return the number of elements > x. */
    int total_right(int x) {
        return total_right(root, x);
    }
    private int total_right(Node pos, int x) {
        if (pos == null) {
            return 0;
        } else if (pos.key > x) {
            int t = total_right(pos.left, x);
            t += pos.count + total(pos.right);
            return t;
        } else {
            return total_right(pos.right, x);
        }
    }

    /** Return the sum of elements > x. */
    long sum_right(int x) {
        return sum_right(root, x);
    }
    private long sum_right(Node pos, int x) {
        if (pos == null) {
            return 0;
        } else if (pos.key > x) {
            long s = sum_right(pos.left, x);
            s += pos.count * pos.key + sum(pos.right);
            return s;
        } else {
            return sum_right(pos.right, x);
        }
    }
}
