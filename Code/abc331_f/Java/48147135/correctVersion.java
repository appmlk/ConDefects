import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Arrays;
import java.util.function.BiFunction;

public class Main {

    private static void solve() {
        int n = nextInt();
        int q = nextInt();
        String s = next();
        char[] chars = s.toCharArray();
        P[] posBase = new P[n];
        for (int i = 0; i < n; i++) {
            posBase[i] = new P(1, chars[i]-'a');
        }
        P[] negBase = new P[n];
        for (int i = 0; i < n; i++) {
            negBase[i] = new P(1, chars[i]-'a');
        }
        long[] mul = new long[n+6];
        mul[0] = 1;
        mul[1] = 2;
        for (int i = 0; i < n + 5; i++) {
            mul[i+1] = mul[i]*3;
        }

        SegTree<P> pos = new SegTree<>(
            posBase,
            (a, b) -> new P(a.len + b.len, a.hash * (mul[b.len]) + b.hash),
            new P(0, 0)
        );
        SegTree<P> neg = new SegTree<>(
            negBase,
            (a, b) -> new P(a.len + b.len, b.hash * (mul[a.len]) + a.hash),
            new P(0, 0)
        );

        while (q-- > 0) {
            int t = nextInt();
            if (t == 1) {
                int x = nextInt() - 1;
                char c = next().charAt(0);
                pos.updateValue(x, new P(1, c-'a'));
                neg.updateValue(x, new P(1, c-'a'));
            } else {
                int l = nextInt()-1;
                int r = nextInt()-1;
                if (pos.query(l, r+1).hash == neg.query(l, r+1).hash) {
                    out.println("Yes");
                } else {
                    out.println("No");
                }
            }
        }
        out.flush();
    }

    private static class P {
        int len;
        long hash;
        public P(int len, long hash) {
            this.len = len;
            this.hash = hash;
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(null, () -> solve(), "", 64L * 1024 * 1024);
        thread.setUncaughtExceptionHandler((t, e) -> {
            e.printStackTrace();
            System.exit(1);
        });
        thread.start();
    }

    static PrintWriter out = new PrintWriter(System.out);
    static Scanner scanner = new Scanner(System.in);
    static String next() {
        return scanner.next();
    }
    static int nextInt() {
        int res = 0;
        char[] chars = next().toCharArray();
        boolean minus = chars[0] == '-';
        int start = minus ? 1 : 0;
        for (int i = start; i < chars.length; i++) {
            res = res * 10 + (chars[i] - '0');
        }
        return minus ? -res : res;
    }
    static long nextLong() {
        long res = 0;
        char[] chars = next().toCharArray();
        boolean minus = chars[0] == '-';
        int start = minus ? 1 : 0;
        for (int i = start; i < chars.length; i++) {
            res = res * 10 + (chars[i] - '0');
        }
        return minus ? -res : res;
    }
    static double nextDouble() {
        return Double.parseDouble(next());
    }
    static int[] nextIntArray(int n) {
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = nextInt();
        }
        return array;
    }
    static long[] nextLongArray(int n) {
        long[] array = new long[n];
        for (int i = 0; i < n; i++) {
            array[i] = nextLong();
        }
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
         * @param l     値の欲しい範囲の左端（inclusive）
         * @param r     値の欲しい範囲の右端（exclusive）
         * @param node  今調べているnode
         * @param lEdge nodeが表す範囲の左端（inclusive）
         * @param rEdge nodeが表す範囲の右端（exclusive）
         */
        private T doQuery(int l, int r, int node, int lEdge, int rEdge) {
            if (rEdge <= l || r <= lEdge) {
                return unit;
            }
            if (l <= lEdge && rEdge <= r) {
                return tree[node];
            }
            return op.apply(
                doQuery(l, r, lChildOf(node), lEdge, (lEdge + rEdge) / 2),
                doQuery(l, r, rChildOf(node), (lEdge + rEdge) / 2, rEdge)
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