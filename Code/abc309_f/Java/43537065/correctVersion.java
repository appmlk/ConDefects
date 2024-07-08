import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        int n = nextInt();
        T[] tt = new T[n];
        for (int i = 0; i < n; i++) {
            int[] a = new int[3];
            for (int j = 0; j < 3; j++) {
                a[j] = nextInt();
            }
            Arrays.sort(a);
            tt[i] = new T(a[0], a[1], a[2]);
        }
        int[] ll = new int[n];
        int[] mm = new int[n];
        int[] hh = new int[n];
        for (int i = 0; i < n; i++) {
            ll[i] = tt[i].l;
            mm[i] = tt[i].m;
            hh[i] = tt[i].h;
        }
        ll = compress(ll);
        mm = compress(mm);
        hh = compress(hh);
        for (int i = 0; i < n; i++) {
            tt[i].l = ll[i];
            tt[i].m = mm[i];
            tt[i].h = hh[i];
        }

        Arrays.sort(tt, Comparator.comparing((T t) -> t.l).thenComparing(t -> -t.m));
        Integer[] array = new Integer[n + 1];
        Arrays.fill(array, Integer.MAX_VALUE);
        SegTree<Integer> segTree = new SegTree<>(array, Math::min, Integer.MAX_VALUE);

        for (T t : tt) {
            if (segTree.query(0, t.m) < t.h) {
                System.out.println("Yes");
                return;
            }
            segTree.updateValue(t.m, Math.min(segTree.query(t.m, t.m+1), t.h));
        }

        System.out.println("No");
        out.flush();
    }

    private static class T {
        int l;
        int m;
        int h;
        public T(int l, int m, int h) {
            this.l = l;
            this.m = m;
            this.h = h;
        }
        @Override
        public String toString() {
            return "T{" +
                "l=" + l +
                ", m=" + m +
                ", h=" + h +
                '}';
        }
    }

    private static int[] compress(int[] array) {
        TreeSet<Integer> sortedElements = Arrays.stream(array).boxed().collect(Collectors.toCollection(TreeSet::new));
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int element: sortedElements) map.put(element, count++);
        int[] res = new int[array.length];
        for (int i = 0; i < array.length; i++) res[i] = map.get(array[i]);
        return res;
    }

    static PrintWriter out = new PrintWriter(System.out);
    static Scanner scanner = new Scanner(System.in);
    static String next() { return scanner.next(); }
    static int nextInt() {
        int res = 0;
        char[] chars = next().toCharArray();
        boolean minus = chars[0] == '-';
        int start = minus?1:0;
        for (int i = start; i < chars.length; i++) {
            res = res*10 + (chars[i]-'0');
        }
        return minus?-res:res;
    }
    static long nextLong() {
        long res = 0;
        char[] chars = next().toCharArray();
        boolean minus = chars[0] == '-';
        int start = minus?1:0;
        for (int i = start; i < chars.length; i++) {
            res = res*10 + (chars[i]-'0');
        }
        return minus?-res:res;
    }
    static double nextDouble() { return Double.parseDouble(next()); }
    static int[] nextIntArray(int n) {
        int[] array = new int[n];
        for (int i = 0; i < n; i++) { array[i] = nextInt(); }
        return array;
    }
    static long[] nextLongArray(int n) {
        long[] array = new long[n];
        for (int i = 0; i < n; i++) { array[i] = nextLong(); }
        return array;
    }

    private static class SegTree<T> {
        int n;
        T[] tree;
        BiFunction<T, T, T> op;
        T unit;

        private SegTree(T[] array, BiFunction<T, T, T> op, T unit) {
            int len = array.length;
            n = getSize(len);
            tree = (T[]) new Object[2 * n - 1];
            this.op = op;
            this.unit = unit;
            initTree(array, len);
        }

        private int getSize(final int len) {
            int exp = 1;
            while (len > exp) {
                exp *= 2;
            }
            return exp;
        }

        private void initTree(final T[] array, final int len) {
            Arrays.fill(tree, unit);
            for (int i = 0; i < len; i++) {
                tree[i + n - 1] = array[i]; // 葉のindexはn-1から2n-2まで
            }
            for (int i = n - 2; i >= 0; i--) {
                updateNode(i);
            }
        }

        private void updateNode(final int i) {
            tree[i] = op.apply(tree[lChildOf(i)], tree[rChildOf(i)]);
        }

        /**
         * 元の配列の値を更新する。
         * 親のnodeに遡っての更新もする。
         *
         * @param i     元の配列のindex
         * @param value 更新後の値
         */
        public void updateValue(int i, T value) {
            int index = i + n - 1;
            tree[index] = value;
            while (index > 0) {
                index = parentOf(index);
                updateNode(index);
            }
        }

        /**
         * 区間の値を求める
         * 実装的には、親から子に下りながら見る。
         *
         *
         * @param l 左端（inclusive）
         * @param r 右端（exclusive）
         */
        public T query(int l, int r) {
            return doQuery(Math.min(l, r), Math.max(l, r), 0, 0, n);
        }

        /**
         * 値の欲しい範囲が、今調べているnodeと被っていなかったらunitを返す。
         * 値の欲しい範囲が広かったら、nodeの値をそのまま返す。
         * 値の欲しい範囲が狭かったら、nodeの子に対して再度このメソッドを実行する。
         *
         * @param l 値の欲しい範囲の左端（inclusive）
         * @param r 値の欲しい範囲の右端（exclusive）
         * @param node 今調べているnode
         * @param lEdge nodeが表す範囲の左端（inclusive）
         * @param rEdge nodeが表す範囲の右端（exclusive）
         */
        private T doQuery(int l, int r, int node, int lEdge, int rEdge) {
            if (rEdge <= l || r <= lEdge) { return unit; }
            if (l <= lEdge && rEdge <= r) { return tree[node]; }
            return op.apply(
                doQuery(l, r, lChildOf(node), lEdge, (lEdge+rEdge)/2),
                doQuery(l, r, rChildOf(node), (lEdge+rEdge)/2, rEdge)
            );
        }

        private int lChildOf(int i) {
            return i * 2 + 1;
        }

        private int rChildOf(int i) {
            return lChildOf(i) + 1;
        }

        private int parentOf(int i) {
            return (i - 1) / 2;
        }
    }

}