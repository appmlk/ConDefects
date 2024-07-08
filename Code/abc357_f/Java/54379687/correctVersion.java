import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] sa = br.readLine().split(" ");
		int n = Integer.parseInt(sa[0]);
		int q = Integer.parseInt(sa[1]);
		Obj[] arr = new Obj[n];
		sa = br.readLine().split(" ");
		for (int i = 0; i < n; i++) {
			Obj o = new Obj();
			o.a = Integer.parseInt(sa[i]);
			o.size = 1;
			arr[i] = o;
		}
		sa = br.readLine().split(" ");
		for (int i = 0; i < n; i++) {
			Obj o = arr[i];
			o.b = Integer.parseInt(sa[i]);
			o.v = o.a * o.b;
		}

		int mod = 998244353;
		LazySegTree<Obj, Obj> st = new LazySegTree<>(
				arr,
				new Obj(),
				(s1, s2) -> {
					Obj ret = new Obj();
					ret.a = (s1.a + s2.a) % mod;
					ret.b = (s1.b + s2.b) % mod;
					ret.v = (s1.v + s2.v) % mod;
					ret.size = s1.size + s2.size;
					return ret;
				},
				(f, s) -> {
					Obj ret = new Obj();
					ret.a = (s.a + f.a * s.size) % mod;
					ret.b = (s.b + f.b * s.size) % mod;
					ret.v = (s.v + s.a * f.b % mod + s.b * f.a % mod
							+ f.a * f.b % mod * s.size % mod) % mod;
					ret.size = s.size;
					return ret;
				},
				(f1, f2) -> {
					Obj ret = new Obj();
					ret.a = (f1.a + f2.a) % mod;
					ret.b = (f1.b + f2.b) % mod;
					return ret;
				},
				new Obj());

		PrintWriter pw = new PrintWriter(System.out);
		for (int i = 0; i < q; i++) {
			sa = br.readLine().split(" ");
			int t = Integer.parseInt(sa[0]);
			int l = Integer.parseInt(sa[1]) - 1;
			int r = Integer.parseInt(sa[2]);
			if (t == 1) {
				Obj f = new Obj();
				f.a = Integer.parseInt(sa[3]);
				st.apply(l, r, f);

			} else if (t == 2) {
				Obj f = new Obj();
				f.b = Integer.parseInt(sa[3]);
				st.apply(l, r, f);

			} else {
				Obj o = st.prod(l, r);
				pw.println(o.v);
			}
		}
		pw.flush();
		br.close();
	}

	static class Obj {
		long a, b, v;
		int size;
	}
}

class LazySegTree<S, F> {
	private final int n; // 要素数
	private final int size; // 葉の数（n以上の最小の2べき）
	private final int log; // 2^log = size
	private final BinaryOperator<S> op; // 二項演算
	private final S e; // 単位元
	private final BiFunction<F, S, S> mapping; // f(x)
	private final BinaryOperator<F> composition; // 合成関数
	private final F id; // 恒等写像
	private final S[] data;
	private final F[] lazy;

	/**
	 * 長さnの配列aを作る。初期値は全て単位元。<br>
	 * O(n)<br>
	 * S：モノイドの型<br>
	 * F：写像の型
	 * 
	 * @param n 要素数
	 * @param e 単位元
	 * @param op 二項演算
	 * @param mapping f(x)を返す関数
	 * @param composition fとgの合成関数
	 * @param id 恒等写像
	 */
	@SuppressWarnings("unchecked")
	public LazySegTree(int n, S e, BinaryOperator<S> op,
			BiFunction<F, S, S> mapping, BinaryOperator<F> composition, F id) {
		this.n = n;
		int k = 1;
		while (k < n) k <<= 1;
		this.size = k;
		this.log = Integer.numberOfTrailingZeros(size);
		this.op = op;
		this.e = e;
		this.mapping = mapping;
		this.composition = composition;
		this.id = id;
		this.data = (S[]) new Object[size << 1];
		this.lazy = (F[]) new Object[size];
		Arrays.fill(data, e);
		Arrays.fill(lazy, id);
	}

	/**
	 * 長さdat.lengthの配列aをdatを元に作る。<br>
	 * O(n)<br>
	 * S：モノイドの型<br>
	 * F：写像の型
	 * 
	 * @param dat 初期データ
	 * @param e 単位元
	 * @param op 二項演算
	 * @param mapping f(x)を返す関数
	 * @param composition fとgの合成関数
	 * @param id 恒等写像
	 */
	public LazySegTree(S[] dat, S e, BinaryOperator<S> op,
			BiFunction<F, S, S> mapping, BinaryOperator<F> composition, F id) {
		this(dat.length, e, op, mapping, composition, id);
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

		p += size;
		pushTo(p);
		data[p] = x;
		updateFrom(p);
	}

	/**
	 * a[p]を取得する。<br>
	 * O(log n)
	 * 
	 * @param p 取得位置（0≦p＜n）
	 */
	S get(int p) {
		assert 0 <= p && p < n : "p=" + p;

		p += size;
		pushTo(p);
		return data[p];
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
		if (l == r) return e;

		l += size;
		r += size;
		pushTo(l, r);
		S sumLeft = e, sumRight = e;
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
	 * a[p]に作用素fを作用させる。<br>
	 * O(log n)
	 * 
	 * @param p 取得位置（0≦p＜n）
	 * @param f 作用素
	 */
	void apply(int p, F f) {
		assert 0 <= p && p < n : "p=" + p;

		p += size;
		pushTo(p);
		data[p] = mapping.apply(f, data[p]);
		updateFrom(p);
	}

	/**
	 * a[l]～a[r-1]に作用素fを作用させる。<br>
	 * O(log n)
	 * 
	 * @param l 開始位置（含む）    （0≦l≦r≦n）
	 * @param r 終了位置（含まない）（0≦l≦r≦n）
	 * @param f 作用素
	 */
	void apply(int l, int r, F f) {
		assert 0 <= l && l <= r && r <= n : "l=" + l + ", r=" + r;
		if (l == r) return;

		l += size;
		r += size;
		pushTo(l, r);
		for (int l2 = l, r2 = r; l2 < r2;) {
			if ((l2 & 1) == 1) {
				data[l2] = mapping.apply(f, data[l2]);
				if (l2 < size) lazy[l2] = composition.apply(f, lazy[l2]);
				l2++;
			}
			if ((r2 & 1) == 1) {
				r2--;
				data[r2] = mapping.apply(f, data[r2]);
				if (r2 < size) lazy[r2] = composition.apply(f, lazy[r2]);
			}
			l2 >>= 1;
			r2 >>= 1;
		}
		updateFrom(l, r);
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
		pushTo(l);
		S sum = e;
		do {
			l >>= Integer.numberOfTrailingZeros(l);
			if (!f.test(op.apply(sum, data[l]))) {
				while (l < size) {
					push(l);
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
		pushTo(r - 1);
		S sum = e;
		do {
			r--;
			while (r > 1 && (r & 1) == 1) r >>= 1;
			if (!f.test(op.apply(data[r], sum))) {
				while (r < size) {
					push(r);
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

	private void pushTo(int k) {
		for (int i = log; i > 0; i--) push(k >> i);
	}

	private void pushTo(int lk, int rk) {
		for (int i = log; i > 0; i--) {
			if (((lk >> i) << i) != lk) push(lk >> i);
			if (((rk >> i) << i) != rk) push(rk >> i);
		}
	}

	private void push(int k) {
		if (lazy[k] == id) return;
		int lk = k << 1 | 0, rk = k << 1 | 1;
		data[lk] = mapping.apply(lazy[k], data[lk]);
		data[rk] = mapping.apply(lazy[k], data[rk]);
		if (lk < size) lazy[lk] = composition.apply(lazy[k], lazy[lk]);
		if (rk < size) lazy[rk] = composition.apply(lazy[k], lazy[rk]);
		lazy[k] = id;
	}

	private void updateFrom(int k) {
		k >>= 1;
		while (k > 0) {
			data[k] = op.apply(data[k << 1 | 0], data[k << 1 | 1]);
			k >>= 1;
		}
	}

	private void updateFrom(int lk, int rk) {
		for (int i = 1; i <= log; i++) {
			if (((lk >> i) << i) != lk) {
				int lki = lk >> i;
				data[lki] = op.apply(data[lki << 1 | 0], data[lki << 1 | 1]);
			}
			if (((rk >> i) << i) != rk) {
				int rki = (rk - 1) >> i;
				data[rki] = op.apply(data[rki << 1 | 0], data[rki << 1 | 1]);
			}
		}
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
		return toDetailedString(1, 0, simulatePushAll());
	}

	private String toDetailedString(int k, int sp, S[] dat) {
		if (k >= size) return indent(sp) + dat[k];
		StringBuilder sb = new StringBuilder();
		sb.append(toDetailedString(k << 1 | 1, sp + indent, dat));
		sb.append("\n");
		sb.append(indent(sp) + dat[k]);
		sb.append("\n");
		sb.append(toDetailedString(k << 1 | 0, sp + indent, dat));
		return sb.toString();
	}

	private static String indent(int n) {
		StringBuilder sb = new StringBuilder();
		while (n-- > 0) sb.append(' ');
		return sb.toString();
	}

	/**
	 * n件の要素のみを、配列形式で出力。
	 */
	String toSimpleString() {
		S[] dat = simulatePushAll();
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (int i = 0; i < size; i++) {
			sb.append(dat[i + size]);
			if (i < size - 1) sb.append(',').append(' ');
		}
		sb.append(']');
		return sb.toString();
	}

	private S[] simulatePushAll() {
		S[] simDat = Arrays.copyOf(data, 2 * size);
		F[] simLaz = Arrays.copyOf(lazy, 2 * size);
		for (int k = 1; k < size; k++) {
			if (simLaz[k] == id) continue;
			int lk = k << 1 | 0, rk = k << 1 | 1;
			simDat[lk] = mapping.apply(simLaz[k], simDat[lk]);
			simDat[rk] = mapping.apply(simLaz[k], simDat[rk]);
			if (lk < size) simLaz[lk] = composition.apply(simLaz[k], simLaz[lk]);
			if (rk < size) simLaz[rk] = composition.apply(simLaz[k], simLaz[rk]);
			simLaz[k] = id;
		}
		return simDat;
	}
}
