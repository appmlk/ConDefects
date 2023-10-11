import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] sa = br.readLine().split(" ");
		int n = Integer.parseInt(sa[0]);
		sa = br.readLine().split(" ");
		int[] a = new int [n];
		for (int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(sa[i]);
		}
		br.close();

		PriorityQueue<Obj> que = new PriorityQueue<>((o1, o2) -> o2.a - o1.a);
		for (int i = 0; i < n; i++) {
			Obj o = new Obj();
			o.i = i + 1;
			o.a = a[i];
			que.add(o);
		}

		int n1 = n / 2;
		int n2 = n * 2;
		Integer[] b = new Integer[n2 + 1];
		Arrays.fill(b, 0);
		long s = 0;
		for (int i = 0; i < n1; i++) {
			Obj o = que.poll();
			b[o.i]++;
			b[o.i + n]++;
			s += o.a;
		}

		for (int i = 1; i <= n2; i++) {
			b[i] += b[i - 1];
		}
		int[] c = new int[n2 + 1];
		for (int i = 1; i <= n2; i++) {
			c[i] = b[i];
			b[i] -= (i + 1) / 2;
		}
		SegTree<Integer> st = new SegTree<>(b, 0, (v1, v2) -> Math.max(v1, v2));

		int d1 = 0;
		int d2 = 0;
		for (int i = 1; i < n; i += 2) {
			d1 = c[i - 1];
			if (st.prod(i, i + n) - d1 + d2 <= 0) {
				System.out.println((i - 1) + " " + s);
				return;
			}
			d2++;
		}
		throw new RuntimeException();
	}

	static class Obj {
		int i, a;
	}
}

class SegTree<S> {
	private final int n; // 要素数
	private final int size; // 葉の数（n以上の最小の2べき）
	private final BinaryOperator<S> op; // 二項演算
	private final S e; // 単位元
	private final S[] data;

	/**
	 * 長さnの配列aを作る。初期値は全て単位元。<br>
	 * O(n)
	 * 
	 * @param n 要素数
	 * @param e 単位元
	 * @param op 二項演算
	 */
	@SuppressWarnings("unchecked")
	public SegTree(int n, S e, BinaryOperator<S> op) {
		this.n = n;
		int k = 1;
		while (k < n) k <<= 1;
		this.size = k;
		this.e = e;
		this.op = op;
		this.data = (S[]) new Object[size << 1];
		Arrays.fill(data, e);
	}

	/**
	 * 長さdat.lengthの配列aをdatを元に作る。<br>
	 * O(n)
	 * 
	 * @param dat 初期データ
	 * @param e 単位元
	 * @param op 二項演算
	 */
	public SegTree(S[] dat, S e, BinaryOperator<S> op) {
		this(dat.length, e, op);
		build(dat);
	}

	private void build(S[] dat) {
		int l = dat.length;
		System.arraycopy(dat, 0, data, size, l);
		for (int i = size - 1; i > 0; i--) {
			data[i] = op.apply(data[i << 1 | 0], data[i << 1 | 1]);
		}
	}

	/**
	 * a[p] = xとする。<br>
	 * O(log n)
	 * 
	 * @param p 設定位置（0≦p＜n）
	 * @param x 設定値
	 */
	void set(int p, S x) {
		assert 0 <= p && p < n : "p=" + p;

		data[p += size] = x;
		p >>= 1;
		while (p > 0) {
			data[p] = op.apply(data[p << 1 | 0], data[p << 1 | 1]);
			p >>= 1;
		}
	}

	/**
	 * a[p]を取得する。<br>
	 * O(1)
	 * 
	 * @param p 取得位置（0≦p＜n）
	 */
	S get(int p) {
		assert 0 <= p && p < n : "p=" + p;

		return data[p + size];
	}

	/**
	 * 区間l～(r-1)の計算を行う。<br>
	 * l＝rのときは単位元を返す。<br>
	 * O(log n)
	 * 
	 * @param l 開始位置（含む）    （0≦l≦r≦n）
	 * @param r 終了位置（含まない）（0≦l≦r≦n）
	 */
	S prod(int l, int r) {
		assert 0 <= l && l <= r && r <= n : "l=" + l + ", r=" + r;

		S sumLeft = e;
		S sumRight = e;
		l += size;
		r += size;
		while (l < r) {
			if ((l & 1) == 1) sumLeft = op.apply(sumLeft, data[l++]);
			if ((r & 1) == 1) sumRight = op.apply(data[--r], sumRight);
			l >>= 1;
			r >>= 1;
		}
		return op.apply(sumLeft, sumRight);
	}

	/**
	 * 全区間の計算を行う。<br>
	 * O(1)
	 */
	S allProd() {
		return data[1];
	}

	/**
	 * f(op(l～(r-1)))＝trueとなる最大のrを返す。<br>
	 * （計算区間にrを追加するとfalseになる）<br>
	 * O(log n)
	 * 
	 * @param l 左端（含む）（0≦l≦n）
	 * @param f 判定関数（f(e)＝true）
	 * @return 条件を満たす最大のr
	 */
	int maxRight(int l, Predicate<S> f) {
		assert 0 <= l && l <= n : "l=" + l;
		assert f.test(e);

		if (l == n) return n;
		l += size;
		S sum = e;
		do {
			l >>= Integer.numberOfTrailingZeros(l);
			if (!f.test(op.apply(sum, data[l]))) {
				while (l < size) {
					l = l << 1;
					if (f.test(op.apply(sum, data[l]))) {
						sum = op.apply(sum, data[l]);
						l++;
					}
				}
				return l - size;
			}
			sum = op.apply(sum, data[l]);
			l++;
		} while ((l & -l) != l);
		return n;
	}

	/**
	 * f(op(l～(r-1)))＝trueとなる最小のlを返す。<br>
	 * （計算区間に(l-1)を追加するとfalseになる）<br>
	 * O(log n)
	 * 
	 * @param r 右端（含まない）（0≦r≦n）
	 * @param f 判定関数（f(e)＝true）
	 * @return 条件を満たす最小のl
	 */
	int minLeft(int r, Predicate<S> f) {
		assert 0 <= r && r <= n : "r=" + r;
		assert f.test(e);

		if (r == 0) return 0;
		r += size;
		S sum = e;
		do {
			r--;
			while (r > 1 && (r & 1) == 1) r >>= 1;
			if (!f.test(op.apply(data[r], sum))) {
				while (r < size) {
					r = r << 1 | 1;
					if (f.test(op.apply(data[r], sum))) {
						sum = op.apply(data[r], sum);
						r--;
					}
				}
				return r + 1 - size;
			}
			sum = op.apply(data[r], sum);
		} while ((r & -r) != r);
		return 0;
	}

	// **************** DEBUG **************** //

	private int indent = 6;

	void setIndent(int newIndent) {
		this.indent = newIndent;
	}

	@Override
	public String toString() {
		return toSimpleString();
	}

	/**
	 * セグメント木全体の要素をツリー状に出力。
	 */
	String toDetailedString() {
		return toDetailedString(1, 0);
	}

	private String toDetailedString(int k, int sp) {
		if (k >= size) return indent(sp) + data[k];
		StringBuilder sb = new StringBuilder();
		sb.append(toDetailedString(k << 1 | 1, sp + indent));
		sb.append("\n");
		sb.append(indent(sp) + data[k]);
		sb.append("\n");
		sb.append(toDetailedString(k << 1 | 0, sp + indent));
		return sb.toString();
	}

	private String indent(int n) {
		StringBuilder sb = new StringBuilder();
		while (n-- > 0) sb.append(' ');
		return sb.toString();
	}

	/**
	 * n件の要素のみを、配列形式で出力。
	 */
	String toSimpleString() {
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (int i = 0; i < size; i++) {
			sb.append(data[i + size]);
			if (i < size - 1) sb.append(',').append(' ');
		}
		sb.append(']');
		return sb.toString();
	}
}
