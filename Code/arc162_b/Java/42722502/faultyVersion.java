






import java.awt.Point;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.AbstractList;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.RandomAccess;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class Main implements Runnable {

	private void solve(final FastIO io, final String[] args) {
		io.setAutoFlush(false);
		io.setAutoOutFlush(false);
		/*
		 * author: 31536000
		 * AtCoder Regular Contest 162 B問題
		 * 考察メモ
		 * 順番に数字を持ち運ぶとどうだろう？
		 * まず1を先頭に運び、次に2を先頭に運び…とやるの
		 * で、これだと上手くいかないケースがありそう
		 * 1 3 2 → NG
		 * ……作れないケースを考えよう
		 * 長さ4で作れないケースがあるか？
		 * 1 2 4 3は普通に無理そう
		 * 実験でも半分か、なら愚直で無理なら無理かな
		 */
		int N = io.nextInt();
		if (N < 0) {
			TreeSet<Test> test = test(-N);
			io.println(test.size());
			for (Test t : test) {
				if (solver(-N, t.P) == null) {
					io.println("error: " + t);
				}
			}
//			io.println(test, "\n");
			return;
		}
		int[] P = io.nextInt(N);
		ArrayList<Query> ans = solver(N, P);
		if (ans == null) {
			io.println("No");
		} else {
			io.println("Yes");
			io.println(ans.size());
			io.println(ans, "\n");
		}
	}
	
	ArrayList<Query> solver(int N, int[] P) {
		ArrayList<Query> ans = new ArrayList<>();
		for (int i = N;i >= 3;-- i) { // iを目的の位置へ
			for (int j = 0;j < i;++ j) {
				if (P[j] != i) continue;
				if (j == 0) { // 2手
					ans.add(new Query(0, 1, P));
					++ j;
				}
				ans.add(new Query(j - 1, i - 2, P));
			}
		}
		if (P[0] != 1) return null;
		return ans;
	}
	
	class Query {
		int i, j;
		Query(int i, int j, int[] P) {
			this.i = i + 1;
			this.j = j;
			int N = P.length;
			if (!(1 <= this.i && this.i <= N - 1)) throw new AssertionError(this);
			if (!(0 <= this.j && this.j <= N - 2)) throw new AssertionError(this);
			int[] Q = new int[N - 2];
			for (int k = 0;k < i;++ k) Q[k] = P[k];
			for (int k = i + 2;k < N;++ k) Q[k - 2] = P[k];
			int ins1 = P[i], ins2 = P[i + 1];
			for (int k = 0;k < j;++ k) P[k] = Q[k];
			P[j] = ins1;
			P[j + 1] = ins2;
			for (int k = j;k < Q.length;++ k) P[k + 2] = Q[k];
		}
		@Override
		public String toString() {
			return i + " " + j;
		}
	}
	
	TreeSet<Test> test(int N) {
		TreeSet<Test> set = new TreeSet<>();
		Queue<Test> bfs = new ArrayDeque<>();
		set.add(new Test(N));
		bfs.add(new Test(N));
		while(!bfs.isEmpty()) {
			Test t = bfs.poll();
			for (Test next : t.test()) {
				if (set.add(next)) bfs.add(next);
			}
		}
		return set;
	}

	class Test implements Comparable<Test>{
		int[] P;
		Test(int N) {
			P = new int[N];
			for (int i = 0;i < N;++ i) P[i] = i + 1;
		}

		private Test(int[] P) {
			this.P = P;
		}

		ArrayList<Test> test() {
			ArrayList<Test> test = new ArrayList<>();
			int N = P.length;
			for (int i = 1;i < N;++ i) {
				int[] Q = new int[N - 2];
				for (int j = 0;j < i - 1;++ j) Q[j] = P[j];
				for (int j = i + 1;j < N;++ j) Q[j - 2] = P[j];
				for (int j = 0;j <= N - 2;++ j) {
					int[] P = new int[N];
					for (int k = 0;k < j;++ k) P[k] = Q[k];
					P[j] = this.P[i - 1];
					P[j + 1] = this.P[i];
					for (int k = j;k < Q.length;++ k) P[k + 2] = Q[k];
					test.add(new Test(P));
				}
			}
			return test;
		}

		@Override
		public int hashCode() {
			return Arrays.hashCode(P);
		}

		@Override
		public boolean equals(Object o) {
			if (o instanceof Test) {
				return Arrays.equals(P, ((Test)o).P);
			}
			return false;
		}

		@Override
		public int compareTo(Test o) {
			for (int i = 0;i < P.length;++ i) {
				int comp = Integer.compare(P[i], o.P[i]);
				if (comp != 0) return comp;
			}
			return 0;
		}

		@Override
		public String toString() {
			return Arrays.toString(P);
		}
	}

	/** デバッグ用コードのお供に */
	private static boolean DEBUG = false;
	/** 確保するメモリの大きさ(単位: MB) */
	private static final long MEMORY = 64;
	private final FastIO io;
	private final String[] args;

	public static void main(final String[] args) {
		Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
			e.printStackTrace();
			System.exit(1);
		});
		FastIO.setFastStandardOutput(true);
		new Thread(null, new Main(args), "", MEMORY * 1048576L).start();
	}

	public Main(final String[] args) {
		this(new FastIO(), args);
	}

	public Main(final FastIO io, final String... args) {
		this.io = io;
		this.args = args;
		if (DEBUG) io.setAutoFlush(true);
	}

	@Override
	public void run() {
		try {
			solve(io, args);
		} catch (final Throwable e) {
			throw e;
		} finally {
			io.close();
			FastIO.setFastStandardOutput(false);
		}
	}

	// 以下、ライブラリ

	/**
	 * 指数表記の値を整数で返します。
	 *
	 * @param n 仮数部
	 * @param e 指数部
	 * @return n * 10^e
	 */
	public static int exponent10(final int n, final int e) {
		return n * pow(10, e);
	}

	/**
	 * 指数表記の値を整数で返します。
	 *
	 * @param n 仮数部
	 * @param e 指数部
	 * @return n * 10^e
	 */
	public static long exponent10L(final int n, final int e) {
		return n * pow(10L, e);
	}

	/**
	 * aのb乗を返します。
	 *
	 * @param a 整数
	 * @param b 整数
	 * @return aのb乗
	 */
	public static int pow(final int a, int b) {
		int ans = 1;
		for (int mul = a; b > 0; b >>= 1, mul *= mul) if ((b & 1) != 0) ans *= mul;
		return ans;
	}

	/**
	 * aのb乗をmodを法として計算したものを返します。
	 *
	 * @param a   整数
	 * @param b   整数
	 * @param mod 法
	 * @return aのb乗をmodを法として計算したもの
	 */
	public static int pow(int a, int b, final int mod) {
		a %= mod;
		if (a < 0) a += mod;
		if (b < 0) {
			b %= mod - 1;
			b += mod - 1;
		}
		long ans = 1;
		for (long mul = a; b > 0; b >>= 1, mul = mul * mul % mod) if ((b & 1) != 0) ans = ans * mul % mod;
		return (int) ans;
	}

	/**
	 * aのb乗を返します。
	 *
	 * @param a 整数
	 * @param b 整数
	 * @return aのb乗
	 */
	public static long pow(final long a, long b) {
		long ans = 1;
		for (long mul = a; b > 0; b >>= 1, mul *= mul) if ((b & 1) != 0) ans *= mul;
		return ans;
	}

	/**
	 * aのb乗をmodを法として計算したものを返します。
	 *
	 * @param a   整数
	 * @param b   整数
	 * @param mod 法
	 * @return aのb乗をmodを法として計算したもの
	 */
	public static int pow(long a, long b, final int mod) {
		a %= mod;
		if (a < 0) a += mod;
		if (b < 0) {
			b %= mod - 1;
			b += mod - 1;
		}
		long ans = 1;
		for (long mul = a; b > 0; b >>= 1, mul = mul * mul % mod) if ((b & 1) != 0) ans = ans * mul % mod;
		return (int) ans;
	}

	public enum BoundType {
		CLOSED, OPEN;
	}

	public static class Range<C> implements Serializable {

		private static final long serialVersionUID = -4702828934863023392L;
		protected C lower;
		protected C upper;
		protected BoundType lowerType;
		protected BoundType upperType;
		private Comparator<? super C> comparator;

		protected Range(final C lower, final BoundType lowerType, final C upper, final BoundType upperType) {
			this(lower, lowerType, upper, upperType, null);
		}

		protected Range(final C lower, final BoundType lowerType, final C upper, final BoundType upperType,
				final Comparator<? super C> comparator) {
			this.lower = lower;
			this.upper = upper;
			this.lowerType = lowerType;
			this.upperType = upperType;
			this.comparator = comparator;
		}

		public static <C extends Comparable<? super C>> Range<C> range(final C lower, final BoundType lowerType,
				final C upper, final BoundType upperType) {
			if (lower != null && upper != null) {
				final int comp = lower.compareTo(upper);
				if (comp > 0) return new Range<>(null, BoundType.CLOSED, null, BoundType.CLOSED);
				else if (comp == 0 && (lowerType == BoundType.OPEN || upperType == BoundType.OPEN))
					return new Range<>(null, BoundType.CLOSED, null, BoundType.CLOSED);
			}
			return new Range<>(lower, lowerType, upper, upperType);
		}

		public static <C> Range<C> range(final C lower, final BoundType lowerType, final C upper,
				final BoundType upperType, final Comparator<? super C> comparator) {
			if (lower != null && upper != null) {
				final int comp = comparator.compare(lower, upper);
				if (comp > 0) return new Range<>(null, BoundType.CLOSED, null, BoundType.CLOSED, comparator);
				else if (comp == 0 && (lowerType == BoundType.OPEN || upperType == BoundType.OPEN))
					return new Range<>(null, BoundType.CLOSED, null, BoundType.CLOSED, comparator);
			}
			return new Range<>(lower, lowerType, upper, upperType, comparator);
		}

		public static <C extends Comparable<? super C>> Range<C> all() {
			return range((C) null, BoundType.OPEN, null, BoundType.OPEN);
		}

		public static <C> Range<C> all(final Comparator<? super C> comparator) {
			return range((C) null, BoundType.OPEN, null, BoundType.OPEN, comparator);
		}

		public static <C extends Comparable<? super C>> Range<C> atMost(final C upper) {
			return range(null, BoundType.OPEN, upper, BoundType.CLOSED);
		}

		public static <C> Range<C> atMost(final C upper, final Comparator<? super C> comparator) {
			return range(null, BoundType.OPEN, upper, BoundType.CLOSED, comparator);
		}

		public static <C extends Comparable<? super C>> Range<C> lessThan(final C upper) {
			return range(null, BoundType.OPEN, upper, BoundType.OPEN);
		}

		public static <C> Range<C> lessThan(final C upper, final Comparator<? super C> comparator) {
			return range(null, BoundType.OPEN, upper, BoundType.OPEN, comparator);
		}

		public static <C extends Comparable<? super C>> Range<C> downTo(final C upper, final BoundType boundType) {
			return range(null, BoundType.OPEN, upper, boundType);
		}

		public static <C> Range<C> downTo(final C upper, final BoundType boundType,
				final Comparator<? super C> comparator) {
			return range(null, BoundType.OPEN, upper, boundType, comparator);
		}

		public static <C extends Comparable<? super C>> Range<C> atLeast(final C lower) {
			return range(lower, BoundType.CLOSED, null, BoundType.OPEN);
		}

		public static <C> Range<C> atLeast(final C lower, final Comparator<? super C> comparator) {
			return range(lower, BoundType.CLOSED, null, BoundType.OPEN, comparator);
		}

		public static <C extends Comparable<? super C>> Range<C> greaterThan(final C lower) {
			return range(lower, BoundType.OPEN, null, BoundType.OPEN);
		}

		public static <C> Range<C> greaterThan(final C lower, final Comparator<? super C> comparator) {
			return range(lower, BoundType.OPEN, null, BoundType.OPEN, comparator);
		}

		public static <C extends Comparable<? super C>> Range<C> upTo(final C lower, final BoundType boundType) {
			return range(lower, boundType, null, BoundType.OPEN);
		}

		public static <C> Range<C> upTo(final C lower, final BoundType boundType,
				final Comparator<? super C> comparator) {
			return range(lower, boundType, null, BoundType.OPEN, comparator);
		}

		public static <C extends Comparable<? super C>> Range<C> open(final C lower, final C upper) {
			return range(lower, BoundType.OPEN, upper, BoundType.OPEN);
		}

		public static <C> Range<C> open(final C lower, final C upper, final Comparator<? super C> comparator) {
			return range(lower, BoundType.OPEN, upper, BoundType.OPEN, comparator);
		}

		public static <C extends Comparable<? super C>> Range<C> openClosed(final C lower, final C upper) {
			return range(lower, BoundType.OPEN, upper, BoundType.CLOSED);
		}

		public static <C> Range<C> openClosed(final C lower, final C upper, final Comparator<? super C> comparator) {
			return range(lower, BoundType.OPEN, upper, BoundType.CLOSED, comparator);
		}

		public static <C extends Comparable<? super C>> Range<C> closedOpen(final C lower, final C upper) {
			return range(lower, BoundType.CLOSED, upper, BoundType.OPEN);
		}

		public static <C> Range<C> closedOpen(final C lower, final C upper, final Comparator<? super C> comparator) {
			return range(lower, BoundType.CLOSED, upper, BoundType.OPEN, comparator);
		}

		public static <C extends Comparable<? super C>> Range<C> closed(final C lower, final C upper) {
			return range(lower, BoundType.CLOSED, upper, BoundType.CLOSED);
		}

		public static <C> Range<C> closed(final C lower, final C upper, final Comparator<? super C> comparator) {
			return range(lower, BoundType.CLOSED, upper, BoundType.CLOSED, comparator);
		}

		public static <C extends Comparable<? super C>> Range<C> singleton(final C value) {
			return range(value, BoundType.CLOSED, value, BoundType.CLOSED);
		}

		public static <C> Range<C> singleton(final C value, final Comparator<? super C> comparator) {
			return range(value, BoundType.CLOSED, value, BoundType.CLOSED, comparator);
		}

		public static <C extends Comparable<? super C>> Range<C> empty() {
			return range((C) null, BoundType.CLOSED, null, BoundType.CLOSED);
		}

		public static <C> Range<C> empty(final Comparator<? super C> comparator) {
			return range((C) null, BoundType.CLOSED, null, BoundType.CLOSED, comparator);
		}

		public static <C extends Comparable<? super C>> Range<C> encloseAll(final Iterable<C> values) {
			C lower = values.iterator().next();
			C upper = lower;
			for (final C i : values) {
				if (lower.compareTo(i) > 0) lower = i;
				if (upper.compareTo(i) < 0) upper = i;
			}
			return range(lower, BoundType.CLOSED, upper, BoundType.CLOSED);
		}

		public static <C> Range<C> encloseAll(final Iterable<C> values, final Comparator<? super C> comparator) {
			C lower = values.iterator().next();
			C upper = lower;
			for (final C i : values) {
				if (comparator.compare(lower, i) > 0) lower = i;
				if (comparator.compare(upper, i) < 0) upper = i;
			}
			return range(lower, BoundType.CLOSED, upper, BoundType.CLOSED, comparator);
		}

		protected int compareLower(final C value) {
			return compareLower(value, BoundType.CLOSED);
		}

		protected int compareLower(final C value, final BoundType boundType) {
			return compareLower(lower, lowerType, value, boundType);
		}

		protected int compareLower(final C lower, final BoundType lowerType, final C value) {
			return compareLower(lower, lowerType, value, BoundType.CLOSED);
		}

		protected int compareLower(final C lower, final BoundType lowerType, final C value, final BoundType boundType) {
			if (lower == null) return value == null ? 0 : -1;
			else if (value == null) return 1;
			int compare;
			if (comparator == null) {
				@SuppressWarnings("unchecked")
				final Comparable<C> comp = (Comparable<C>) lower;
				compare = comp.compareTo(value);
			} else compare = comparator.compare(lower, value);
			if (compare == 0) {
				if (lowerType == BoundType.CLOSED) --compare;
				if (boundType == BoundType.CLOSED) ++compare;
			}
			return compare;
		}

		protected int compareUpper(final C value) {
			return compareUpper(value, BoundType.CLOSED);
		}

		protected int compareUpper(final C value, final BoundType boundType) {
			return compareUpper(upper, upperType, value, boundType);
		}

		protected int compareUpper(final C upper, final BoundType upperType, final C value) {
			return compareUpper(upper, upperType, value, BoundType.CLOSED);
		}

		protected int compareUpper(final C upper, final BoundType upperType, final C value, final BoundType boundType) {
			if (upper == null) return value == null ? 0 : 1;
			if (value == null) return -1;
			int compare;
			if (comparator == null) {
				@SuppressWarnings("unchecked")
				final Comparable<C> comp = (Comparable<C>) upper;
				compare = comp.compareTo(value);
			} else compare = comparator.compare(upper, value);
			if (compare == 0) {
				if (upperType == BoundType.CLOSED) ++compare;
				if (boundType == BoundType.CLOSED) --compare;
			}
			return compare;
		}

		public boolean hasLowerBound() {
			return lower != null;
		}

		public C lowerEndpoint() {
			if (hasLowerBound()) return lower;
			throw new IllegalStateException();
		}

		public BoundType lowerBoundType() {
			if (hasLowerBound()) return lowerType;
			throw new IllegalStateException();
		}

		public boolean hasUpperBound() {
			return upper != null;
		}

		public C upperEndpoint() {
			if (hasUpperBound()) return upper;
			throw new IllegalStateException();
		}

		public BoundType upperBoundType() {
			if (hasUpperBound()) return upperType;
			throw new IllegalStateException();
		}

		/**
		 * この区間が空集合か判定します。
		 *
		 * @return 空集合ならばtrue
		 */
		public boolean isEmpty() { return lower == null && upper == null && lowerType == BoundType.CLOSED; }

		/**
		 * 与えられた引数が区間の左側に位置するか判定します。<br>
		 * 接する場合は区間の左側ではないと判定します。
		 *
		 * @param value 調べる引数
		 * @return 区間の左側に位置するならtrue
		 */
		public boolean isLess(final C value) {
			return isLess(value, BoundType.CLOSED);
		}

		protected boolean isLess(final C value, final BoundType boundType) {
			return compareLower(value, boundType) > 0;
		}

		/**
		 * 与えられた引数が区間の右側に位置するか判定します。<br>
		 * 接する場合は区間の右側ではないと判定します。
		 *
		 * @param value 調べる引数
		 * @return 区間の右側に位置するならtrue
		 */
		public boolean isGreater(final C value) {
			return isGreater(value, BoundType.CLOSED);
		}

		private boolean isGreater(final C value, final BoundType boundType) {
			return compareUpper(value, boundType) < 0;
		}

		/**
		 * 与えられた引数が区間内に位置するか判定します。<br>
		 * 接する場合も区間内に位置すると判定します。
		 *
		 * @param value 調べる引数
		 * @return 区間内に位置するならtrue
		 */
		public boolean contains(final C value) {
			return !isLess(value) && !isGreater(value) && !isEmpty();
		}

		/**
		 * 与えられた引数すべてが区間内に位置するか判定します。<br>
		 * 接する場合も区間内に位置すると判定します。
		 *
		 * @param value 調べる要素
		 * @return 全ての要素が区間内に位置するならtrue
		 */
		public boolean containsAll(final Iterable<? extends C> values) {
			for (final C i : values) if (!contains(i)) return false;
			return true;
		}

		/**
		 * 与えられた区間がこの区間に内包されるか判定します。<br>
		 *
		 * @param other
		 * @return 与えられた区間がこの区間に内包されるならtrue
		 */
		public boolean encloses(final Range<C> other) {
			return !isLess(other.lower, other.lowerType) && !isGreater(other.upper, other.upperType);
		}

		/**
		 * 与えられた区間がこの区間と公差するか判定します。<br>
		 * 接する場合は公差するものとします。
		 *
		 * @param value 調べる引数
		 * @return 区間が交差するならtrue
		 */
		public boolean isConnected(final Range<C> other) {
			if (this.isEmpty() || other.isEmpty()) return false;
			C lower, upper;
			BoundType lowerType, upperType;
			if (isLess(other.lower, other.lowerType)) {
				lower = other.lower;
				lowerType = other.lowerType;
			} else {
				lower = this.lower;
				lowerType = this.lowerType;
			}
			if (isGreater(other.upper, other.upperType)) {
				upper = other.upper;
				upperType = other.upperType;
			} else {
				upper = this.upper;
				upperType = this.upperType;
			}
			if (lower == null || upper == null) return true;
			final int comp = compareLower(lower, lowerType, upper, upperType);
			return comp <= 0;
		}

		/**
		 * この区間との積集合を返します。
		 *
		 * @param connectedRange 積集合を求める区間
		 * @return 積集合
		 */
		public Range<C> intersection(final Range<C> connectedRange) {
			if (this.isEmpty() || connectedRange.isEmpty()) {
				if (comparator == null) return new Range<>(null, BoundType.CLOSED, null, BoundType.CLOSED);
				return empty(comparator);
			}
			C lower, upper;
			BoundType lowerType, upperType;
			if (isLess(connectedRange.lower, connectedRange.lowerType)) {
				lower = connectedRange.lower;
				lowerType = connectedRange.lowerType;
			} else {
				lower = this.lower;
				lowerType = this.lowerType;
			}
			if (isGreater(connectedRange.upper, connectedRange.upperType)) {
				upper = connectedRange.upper;
				upperType = connectedRange.upperType;
			} else {
				upper = this.upper;
				upperType = this.upperType;
			}
			if (comparator == null) { return new Range<>(lower, lowerType, upper, upperType); }
			return range(lower, lowerType, upper, upperType, comparator);
		}

		/**
		 * この区間との和集合を返します。
		 *
		 * @param other 和集合を求める区間
		 * @return 和集合
		 */
		public Range<C> span(final Range<C> other) {
			if (other.isEmpty()) return new Range<>(lower, lowerType, upper, upperType);
			C lower, upper;
			BoundType lowerType, upperType;
			if (isLess(other.lower, other.lowerType)) {
				lower = this.lower;
				lowerType = this.lowerType;
			} else {
				lower = other.lower;
				lowerType = other.lowerType;
			}
			if (isGreater(other.upper, other.upperType)) {
				upper = this.upper;
				upperType = this.upperType;
			} else {
				upper = other.upper;
				upperType = other.upperType;
			}
			return new Range<>(lower, lowerType, upper, upperType, comparator);
		}

		/**
		 * 区間スケジューリングを行います。<br>
		 * 計算量は要素数Nに対してO(NlogN)です。
		 *
		 * @param ranges 区間の集合
		 * @return 区間スケジューリングを行った際の一つの解
		 */
		public static <C> List<Range<C>> scheduling(final List<Range<C>> ranges) {
			final PriorityQueue<Range<C>> pq = new PriorityQueue<>((l, r) -> l.compareUpper(r.upper, r.upperType));
			final List<Range<C>> ret = new ArrayList<>();
			Range<C> last = pq.poll();
			if (pq.isEmpty()) return ret;
			ret.add(last);
			while (!pq.isEmpty()) {
				final Range<C> tmp = pq.poll();
				if (tmp.compareLower(last.upper, last.upperType) > 0) {
					ret.add(tmp);
					last = tmp;
				}
			}
			return ret;
		}

		@Override
		public boolean equals(final Object object) {
			if (this == object) return true;
			if (object instanceof Range) {
				@SuppressWarnings("unchecked")
				final Range<C> comp = (Range<C>) object;
				return compareLower(comp.lower, comp.lowerType) == 0 && compareUpper(comp.upper, comp.upperType) == 0
						&& lowerType == comp.lowerType && upperType == comp.upperType;
			}
			return false;
		}

		@Override
		public int hashCode() {
			if (lower == null && upper == null) return 0;
			else if (lower == null) return upper.hashCode();
			else if (upper == null) return lower.hashCode();
			return lower.hashCode() ^ upper.hashCode();
		}

		@Override
		public String toString() {
			if (isEmpty()) return "()";
			return (lowerType == BoundType.OPEN ? "(" : "[") + (lower == null ? "" : lower.toString()) + ".."
					+ (upper == null ? "" : upper.toString()) + (upperType == BoundType.OPEN ? ")" : "]");
		}
	}

	public static class IterableRange<C> extends Range<C> implements Iterable<C> {

		private static final long serialVersionUID = 9065915259748260688L;
		protected UnaryOperator<C> func;

		protected IterableRange(final C lower, final BoundType lowerType, final C upper, final BoundType upperType,
				final UnaryOperator<C> func) {
			super(lower, lowerType, upper, upperType);
			this.func = func;
		}

		public static <C extends Comparable<? super C>> IterableRange<C> range(final C lower, final BoundType lowerType,
				final C upper, final BoundType upperType, final UnaryOperator<C> func) {
			if (lower == null || upper == null)
				return new IterableRange<>(null, BoundType.CLOSED, null, BoundType.CLOSED, func);
			final int comp = lower.compareTo(upper);
			if (comp > 0) return new IterableRange<>(null, BoundType.CLOSED, null, BoundType.CLOSED, func);
			else if (comp == 0 && (lowerType == BoundType.OPEN || upperType == BoundType.OPEN))
				return new IterableRange<>(null, BoundType.CLOSED, null, BoundType.CLOSED, func);
			return new IterableRange<>(lower, lowerType, upper, upperType, func);
		}

		public static <C extends Comparable<? super C>> IterableRange<C> open(final C lower, final C upper,
				final UnaryOperator<C> func) {
			if (lower == null) return new IterableRange<>(null, BoundType.CLOSED, null, BoundType.CLOSED, func);
			return range(func.apply(lower), BoundType.CLOSED, upper, BoundType.OPEN, func);
		}

		public static <C extends Comparable<? super C>> IterableRange<C> openClosed(final C lower, final C upper,
				final UnaryOperator<C> func) {
			if (lower == null) return new IterableRange<>(null, BoundType.CLOSED, null, BoundType.CLOSED, func);
			return range(func.apply(lower), BoundType.CLOSED, upper, BoundType.CLOSED, func);
		}

		public static <C extends Comparable<? super C>> IterableRange<C> closedOpen(final C lower, final C upper,
				final UnaryOperator<C> func) {
			return range(lower, BoundType.CLOSED, upper, BoundType.OPEN, func);
		}

		public static <C extends Comparable<? super C>> IterableRange<C> closed(final C lower, final C upper,
				final UnaryOperator<C> func) {
			return range(lower, BoundType.CLOSED, upper, BoundType.CLOSED, func);
		}

		public static <C extends Comparable<? super C>> IterableRange<C> singleton(final C value,
				final UnaryOperator<C> func) {
			return range(value, BoundType.CLOSED, value, BoundType.CLOSED, func);
		}

		protected class Iter implements Iterator<C> {

			C now;

			Iter() {
				now = lower;
			}

			@Override
			public final boolean hasNext() {
				return !isGreater(now);
			}

			@Override
			public final C next() {
				final C ret = now;
				now = func.apply(now);
				return ret;
			}

			@Override
			public final void remove() {
				throw new UnsupportedOperationException();
			}
		}

		protected class EmptyIter implements Iterator<C> {

			@Override
			public boolean hasNext() {
				return false;
			}

			@Override
			public C next() {
				return null;
			}

			@Override
			public final void remove() {
				throw new UnsupportedOperationException();
			}

		}

		@Override
		public Iterator<C> iterator() {
			return lower == null || upper == null ? new EmptyIter() : new Iter();
		}

		public int getDistance() {
			C check = upper;
			int ret = 0;
			while (lower != check) {
				check = func.apply(check);
				++ret;
			}
			return ret;
		}
	}

	public static class IntRange extends IterableRange<Integer> {

		private static final long serialVersionUID = 5623995336491967216L;
		private final boolean useFastIter;

		private static class Next implements UnaryOperator<Integer> {

			@Override
			public Integer apply(final Integer value) {
				return value + 1;
			}
		}

		protected IntRange() {
			super(null, BoundType.CLOSED, null, BoundType.CLOSED, new Next());
			useFastIter = true;
		}

		protected IntRange(final UnaryOperator<Integer> func) {
			super(null, BoundType.CLOSED, null, BoundType.CLOSED, func);
			useFastIter = false;
		}

		protected IntRange(final int lower, final BoundType lowerType, final int upper, final BoundType upperType) {
			super(lower, lowerType, upper, upperType, new Next());
			useFastIter = true;
		}

		protected IntRange(final int lower, final BoundType lowerType, final int upper, final BoundType upperType,
				final UnaryOperator<Integer> func) {
			super(lower, lowerType, upper, upperType, func);
			useFastIter = false;
		}

		public static IntRange range(int lower, final BoundType lowerType, int upper, final BoundType upperType) {
			if (lower > upper) return new IntRange();
			if (lowerType == BoundType.OPEN) ++lower;
			if (upperType == BoundType.OPEN) --upper;
			return new IntRange(lower, BoundType.CLOSED, upper, BoundType.CLOSED);
		}

		public static IntRange range(int lower, final BoundType lowerType, int upper, final BoundType upperType,
				final UnaryOperator<Integer> func) {
			if (lower > upper) return new IntRange(func);
			if (lowerType == BoundType.OPEN) ++lower;
			if (upperType == BoundType.OPEN) --upper;
			return new IntRange(lower, BoundType.CLOSED, upper, BoundType.CLOSED, func);
		}

		public static IntRange open(final int lower, final int upper) {
			return range(lower, BoundType.OPEN, upper, BoundType.OPEN);
		}

		public static IntRange open(final int lower, final int upper, final UnaryOperator<Integer> func) {
			return range(lower, BoundType.OPEN, upper, BoundType.OPEN, func);
		}

		public static IntRange open(final int upper) {
			return range(0, BoundType.CLOSED, upper, BoundType.OPEN);
		}

		public static IntRange open(final int upper, final UnaryOperator<Integer> func) {
			return range(0, BoundType.CLOSED, upper, BoundType.OPEN, func);
		}

		public static IntRange openClosed(final int lower, final int upper) {
			return range(lower, BoundType.OPEN, upper, BoundType.CLOSED);
		}

		public static IntRange openClosed(final int lower, final int upper, final UnaryOperator<Integer> func) {
			return range(lower, BoundType.OPEN, upper, BoundType.CLOSED, func);
		}

		public static IntRange closedOpen(final int lower, final int upper) {
			return range(lower, BoundType.CLOSED, upper, BoundType.OPEN);
		}

		public static IntRange closedOpen(final int lower, final int upper, final UnaryOperator<Integer> func) {
			return range(lower, BoundType.CLOSED, upper, BoundType.OPEN, func);
		}

		public static IntRange closed(final int lower, final int upper) {
			return range(lower, BoundType.CLOSED, upper, BoundType.CLOSED);
		}

		public static IntRange closed(final int lower, final int upper, final UnaryOperator<Integer> func) {
			return range(lower, BoundType.CLOSED, upper, BoundType.CLOSED, func);
		}

		public static IntRange closed(final int upper) {
			return range(0, BoundType.CLOSED, upper, BoundType.CLOSED);
		}

		public static IntRange closed(final int upper, final UnaryOperator<Integer> func) {
			return range(0, BoundType.CLOSED, upper, BoundType.CLOSED, func);
		}

		public static IntRange singleton(final int value) {
			return range(value, BoundType.CLOSED, value, BoundType.CLOSED);
		}

		public static IntRange singleton(final int value, final UnaryOperator<Integer> func) {
			return range(value, BoundType.CLOSED, value, BoundType.CLOSED, func);
		}

		private class FastIter implements Iterator<Integer> {

			int now;

			public FastIter() {
				now = lower;
			}

			@Override
			public final boolean hasNext() {
				return now <= upper;
			}

			@Override
			public final Integer next() {
				return now++;
			}

			@Override
			public final void remove() {
				throw new UnsupportedOperationException();
			}
		}

		private class Iter implements Iterator<Integer> {

			int now;

			public Iter() {
				now = lower;
			}

			@Override
			public final boolean hasNext() {
				return now <= upper;
			}

			@Override
			public final Integer next() {
				final int ret = now;
				now = func.apply(now);
				return ret;
			}

			@Override
			public final void remove() {
				throw new UnsupportedOperationException();
			}
		}

		@Override
		public Iterator<Integer> iterator() {
			return lower == null || upper == null ? new EmptyIter() : useFastIter ? new FastIter() : new Iter();
		}

		@Override
		public int getDistance() {
			int ret = upper - lower;
			if (upperType == BoundType.CLOSED) ++ret;
			return ret;
		}

		public int getClosedLower() { return lower; }

		public int getOpenLower() { return lower - 1; }

		public int getClosedUpper() { return upperType == BoundType.CLOSED ? upper : upper - 1; }

		public int getOpenUpper() { return upperType == BoundType.CLOSED ? upper + 1 : upper; }

		/**
		 * 区間スケジューリングを行います。<br>
		 * 計算量は要素数Nに対してO(NlogN)です。
		 *
		 * @param ranges 区間の集合
		 * @return 区間スケジューリングを行った際の一つの解
		 */
		public static List<IntRange> intScheduling(final List<IntRange> ranges) {
			final PriorityQueue<IntRange> pq = new PriorityQueue<>((l, r) -> l.compareUpper(r.upper, r.upperType));
			pq.addAll(ranges);
			final List<IntRange> ret = new ArrayList<>();
			if (pq.isEmpty()) return ret;
			IntRange last = pq.poll();
			ret.add(last);
			while (!pq.isEmpty()) {
				final IntRange tmp = pq.poll();
				if (tmp.compareLower(last.upper, last.upperType) > 0) {
					ret.add(tmp);
					last = tmp;
				}
			}
			return ret;
		}
	}

	/**
	 * 演算が結合法則を満たすことを示すために使用するマーカー・インターフェースです。
	 *
	 * @author 31536000
	 *
	 * @param <T> 二項演算の型
	 */
	public interface Associative<T> extends BinaryOperator<T> {

		/**
		 * repeat個のelementを順次演算した値を返します。
		 *
		 * @param element 演算する値
		 * @param repeat  繰り返す回数、1以上であること
		 * @return 演算を+として、element + element + ... + elementと演算をrepeat-1回行った値
		 */
		public default T hyper(final T element, int repeat) {
			if (repeat < 1) throw new IllegalArgumentException("undefined operation");
			T ret = element;
			--repeat;
			for (T mul = element; repeat > 0; repeat >>= 1, mul = apply(mul, mul))
				if ((repeat & 1) != 0) ret = apply(ret, mul);
			return ret;
		}
	}

	/**
	 * この演算が逆元を持つことを示すために使用するマーカー・インターフェースです。
	 *
	 * @author 31536000
	 *
	 * @param <T> 二項演算の型
	 */
	public interface Inverse<T> extends BinaryOperator<T> {

		public T inverse(T element);
	}

	/**
	 * 演算が交換法則を満たすことを示すために使用するマーカー・インターフェースです。
	 *
	 * @author 31536000
	 *
	 * @param <T> 二項演算の型
	 */
	public interface Commutative<T> extends BinaryOperator<T> {

	}

	/**
	 * 演算が単位元を持つことを示すために使用するマーカー・インターフェースです。
	 *
	 * @author 31536000
	 *
	 * @param <T> 二項演算の型
	 */
	public interface Identity<T> extends BinaryOperator<T> {

		/**
		 * 単位元を返します。
		 *
		 * @return 単位元
		 */
		public T identity();
	}

	/**
	 * 演算が群であることを示すために使用するマーカー・インターフェースです。
	 *
	 * @author 31536000
	 *
	 * @param <T> 二項演算の型
	 */
	public interface Group<T> extends Monoid<T>, Inverse<T> {

		/**
		 * repeat個のelementを順次演算した値を返します。
		 *
		 * @param element 演算する値
		 * @param repeat  繰り返す回数
		 * @return 演算を+として、element + element + ... + elementと演算をrepeat-1回行った値
		 */
		@Override
		public default T hyper(final T element, int repeat) {
			T ret = identity();
			if (repeat < 0) {
				repeat = -repeat;
				for (T mul = element; repeat > 0; repeat >>= 1, mul = apply(mul, mul))
					if ((repeat & 1) != 0) ret = apply(ret, mul);
				return inverse(ret);
			}
			for (T mul = element; repeat > 0; repeat >>= 1, mul = apply(mul, mul))
				if ((repeat & 1) != 0) ret = apply(ret, mul);
			return ret;
		}
	}

	/**
	 * 演算がモノイドであることを示すために使用するマーカー・インターフェースです。
	 *
	 * @author 31536000
	 *
	 * @param <T> 二項演算の型
	 */
	public interface Monoid<T> extends Associative<T>, Identity<T> {

		/**
		 * repeat個のelementを順次演算した値を返します。
		 *
		 * @param element 演算する値
		 * @param repeat  繰り返す回数、0以上であること
		 * @return 演算を+として、element + element + ... + elementと演算をrepeat-1回行った値
		 */
		@Override
		public default T hyper(final T element, int repeat) {
			if (repeat < 0) throw new IllegalArgumentException("undefined operation");
			T ret = identity();
			for (T mul = element; repeat > 0; repeat >>= 1, mul = apply(mul, mul))
				if ((repeat & 1) != 0) ret = apply(ret, mul);
			return ret;
		}
	}

	/**
	 * 演算が可換モノイドであることを示すために使用するマーカー・インターフェースです。
	 *
	 * @author 31536000
	 *
	 * @param <T> 二項演算の型
	 */
	public interface CommutativeMonoid<T> extends Monoid<T>, Commutative<T> {

	}

	/**
	 * 演算がアーベル群(可換群)であることを示すために使用するマーカー・インターフェースです。
	 *
	 * @author 31536000
	 *
	 * @param <T> 二項演算の型
	 */
	public interface Abelian<T> extends Group<T>, CommutativeMonoid<T> {

	}

	/**
	 * 演算が半環であることを示すために使用するマーカー・インターフェースです。
	 *
	 * @author 31536000
	 *
	 * @param <T> 二項演算の型
	 * @param <A> 和に関する演算
	 * @param <M> 積に関する演算
	 */
	public interface Semiring<T, A extends CommutativeMonoid<T>, M extends Monoid<T>> {

		public A getAddition();

		public M getMultiplication();

		public default T add(final T left, final T right) {
			return getAddition().apply(left, right);
		}

		public default T multiply(final T left, final T right) {
			return getMultiplication().apply(left, right);
		}

		public default T additiveIdentity() {
			return getAddition().identity();
		}

		public default T multipleIdentity() {
			return getMultiplication().identity();
		}

		public default int characteristic() {
			return 0;
		}
	}

	/**
	 * 演算が環であることを示すために使用するマーカー・インターフェースです。
	 *
	 * @author 31536000
	 *
	 * @param <T> 二項演算の型
	 * @param <A> 和に関する演算
	 * @param <M> 積に関する演算
	 */
	public interface Ring<T, A extends Abelian<T>, M extends Monoid<T>> extends Semiring<T, A, M> {

	}

	/**
	 * 演算が可換環に属することを示すために使用するマーカー・インターフェースです。
	 *
	 * @author 31536000
	 *
	 * @param <T> 二項演算の型
	 * @param <A> 和に関する演算
	 * @param <M> 積に関する演算
	 */
	public interface CommutativeRing<T, A extends Abelian<T>, M extends CommutativeMonoid<T>> extends Ring<T, A, M> {

	}

	/**
	 * 演算が整域であることを示すために使用するマーカー・インターフェースです。
	 *
	 * @author 31536000
	 *
	 * @param <T> 二項演算の型
	 * @param <A> 和に関する演算
	 * @param <M> 積に関する演算
	 */
	public interface IntegralDomain<T, A extends Abelian<T>, M extends CommutativeMonoid<T>>
			extends CommutativeRing<T, A, M> {

		public boolean isDivisible(T left, T right);

		public T divide(T left, T right);
	}

	/**
	 * 演算が整閉整域であることを示すために使用するマーカー・インターフェースです。
	 *
	 * @author 31536000
	 *
	 * @param <T> 二項演算の型
	 * @param <A> 和に関する演算
	 * @param <M> 積に関する演算
	 */
	public interface IntegrallyClosedDomain<T, A extends Abelian<T>, M extends CommutativeMonoid<T>>
			extends IntegralDomain<T, A, M> {

	}

	/**
	 * 演算がGCD整域であることを示すために使用するマーカー・インターフェースです。
	 *
	 * @author 31536000
	 *
	 * @param <T> 二項演算の型
	 * @param <A> 和に関する演算
	 * @param <M> 積に関する演算
	 */
	public interface GCDDomain<T, A extends Abelian<T>, M extends CommutativeMonoid<T>>
			extends IntegrallyClosedDomain<T, A, M> {

		public T gcd(T left, T right);

		public T lcm(T left, T right);
	}

	/**
	 * 素元を提供します。
	 *
	 * @author 31536000
	 *
	 * @param <T> 演算の型
	 */
	public static class PrimeElement<T> {

		public final T element;

		public PrimeElement(final T element) {
			this.element = element;
		}
	}

	public interface MultiSet<E> extends Collection<E> {

		public int add(E element, int occurrences);

		public int count(Object element);

		public Set<E> elementSet();

		public boolean remove(Object element, int occurrences);

		public int setCount(E element, int count);

		public boolean setCount(E element, int oldCount, int newCount);
	}

	/**
	 * 演算が一意分解整域であることを示すために使用するマーカー・インターフェースです。
	 *
	 * @author 31536000
	 *
	 * @param <T> 二項演算の型
	 * @param <A> 和に関する演算
	 * @param <M> 積に関する演算
	 */
	public interface UniqueFactorizationDomain<T, A extends Abelian<T>, M extends CommutativeMonoid<T>>
			extends GCDDomain<T, A, M> {

		public MultiSet<PrimeElement<T>> PrimeFactorization(T x);
	}

	/**
	 * 演算が主イデアル整域であることを示すために使用するマーカー・インターフェースです。
	 *
	 * @author 31536000
	 *
	 * @param <T> 二項演算の型
	 * @param <A> 和に関する演算
	 * @param <M> 積に関する演算
	 */
	public interface PrincipalIdealDomain<T, A extends Abelian<T>, M extends CommutativeMonoid<T>>
			extends UniqueFactorizationDomain<T, A, M> {

	}

	/**
	 * 演算がユークリッド整域であることを示すために使用するマーカー・インターフェースです。
	 *
	 * @author 31536000
	 *
	 * @param <T> 二項演算の型
	 * @param <A> 和に関する演算
	 * @param <M> 積に関する演算
	 */
	public interface EuclideanDomain<T, A extends Abelian<T>, M extends CommutativeMonoid<T>>
			extends PrincipalIdealDomain<T, A, M> {

		public T reminder(T left, T right);
	}

	/**
	 * 演算が体であることを示すために使用するマーカー・インターフェースです。
	 *
	 * @author 31536000
	 *
	 * @param <T> 二項演算の型
	 * @param <A> 和に関する演算
	 * @param <M> 積に関する演算
	 */
	public interface Field<T, A extends Abelian<T>, M extends Abelian<T>> extends EuclideanDomain<T, A, M> {

		@Override
		public default boolean isDivisible(final T left, final T right) {
			return !right.equals(additiveIdentity());
		}

		@Override
		public default T divide(final T left, final T right) {
			if (isDivisible(left, right)) throw new ArithmeticException("divide by Additive Identify");
			return multiply(left, getMultiplication().inverse(right));
		}

		@Override
		public default T reminder(final T left, final T right) {
			if (isDivisible(left, right)) throw new ArithmeticException("divide by Additive Identify");
			return additiveIdentity();
		}

		@Override
		public default T gcd(final T left, final T right) {
			return multipleIdentity();
		}

		@Override
		public default T lcm(final T left, final T right) {
			return multipleIdentity();
		}

		@Override
		public default MultiSet<PrimeElement<T>> PrimeFactorization(final T x) {
			final HashMultiSet<PrimeElement<T>> ret = HashMultiSet.create(1);
			ret.add(new PrimeElement<>(x));
			return ret;
		}
	}

	public static class HashMultiSet<E> implements MultiSet<E>, Serializable {

		private static final long serialVersionUID = -8378919645386251159L;
		private final transient HashMap<E, Integer> map;
		private transient int size;

		private HashMultiSet() {
			map = new HashMap<>();
			size = 0;
		}

		private HashMultiSet(final int distinctElements) {
			map = new HashMap<>(distinctElements);
			size = 0;
		}

		public static <E> HashMultiSet<E> create() {
			return new HashMultiSet<>();
		}

		public static <E> HashMultiSet<E> create(final int distinctElements) {
			return new HashMultiSet<>(distinctElements);
		}

		public static <E> HashMultiSet<E> create(final Iterable<? extends E> elements) {
			final HashMultiSet<E> ret = new HashMultiSet<>();
			for (final E i : elements) ret.map.compute(i, (v, e) -> e == null ? 1 : ++e);
			return ret;
		}

		@Override
		public int size() {
			return size;
		}

		@Override
		public boolean isEmpty() { return size == 0; }

		@Override
		public boolean contains(final Object o) {
			return map.containsKey(o);
		}

		private class Iter implements Iterator<E> {

			private final Iterator<Entry<E, Integer>> iter = map.entrySet().iterator();
			private E value;
			private int count = 0;

			@Override
			public boolean hasNext() {
				if (count > 0) return true;
				if (iter.hasNext()) {
					final Entry<E, Integer> entry = iter.next();
					value = entry.getKey();
					count = entry.getValue();
					return true;
				}
				return false;
			}

			@Override
			public E next() {
				--count;
				return value;
			}

		}

		@Override
		public Iterator<E> iterator() {
			return new Iter();
		}

		@Override
		public Object[] toArray() {
			final Object[] ret = new Object[size];
			int read = 0;
			for (final Entry<E, Integer> i : map.entrySet()) Arrays.fill(ret, read, read += i.getValue(), i.getKey());
			return ret;
		}

		@Override
		public <T> T[] toArray(final T[] a) {
			final Object[] src = toArray();
			if (a.length < src.length) {
				@SuppressWarnings("unchecked")
				final T[] ret = (T[]) Arrays.copyOfRange(src, 0, src.length, a.getClass());
				return ret;
			}
			System.arraycopy(src, 0, a, 0, src.length);
			return a;
		}

		@Override
		public boolean add(final E e) {
			add(e, 1);
			return true;
		}

		@Override
		public boolean remove(final Object o) {
			return remove(o, 1);
		}

		@Override
		public boolean containsAll(final Collection<?> c) {
			boolean ret = true;
			for (final Object i : c) ret |= contains(i);
			return ret;
		}

		@Override
		public boolean addAll(final Collection<? extends E> c) {
			boolean ret = false;
			for (final E i : c) ret |= add(i);
			return ret;
		}

		@Override
		public boolean removeAll(final Collection<?> c) {
			boolean ret = false;
			for (final Object i : c) ret |= remove(i);
			return ret;
		}

		@Override
		public boolean retainAll(final Collection<?> c) {
			return removeAll(c);
		}

		@Override
		public void clear() {
			map.clear();
			size = 0;
		}

		@Override
		public int add(final E element, final int occurrences) {
			size += occurrences;
			return map.compute(element, (k, v) -> v == null ? occurrences : v + occurrences) - occurrences;
		}

		@Override
		public int count(final Object element) {
			return map.getOrDefault(element, 0);
		}

		@Override
		public Set<E> elementSet() {
			return map.keySet();
		}

		public Set<Entry<E, Integer>> entrySet() {
			return map.entrySet();
		}

		@Override
		public boolean remove(final Object element, final int occurrences) {
			try {
				@SuppressWarnings("unchecked")
				final E put = (E) element;
				return map.compute(put, (k, v) -> {
					if (v == null) return null;
					if (v < occurrences) {
						size -= v;
						return null;
					}
					size -= occurrences;
					return v - occurrences;
				}) != null;
			} catch (final ClassCastException E) {
				return false;
			}
		}

		@Override
		public int setCount(final E element, final int count) {
			final Integer ret = map.put(element, count);
			final int ret2 = ret == null ? 0 : ret;
			size += count - ret2;
			return ret2;
		}

		@Override
		public boolean setCount(final E element, final int oldCount, final int newCount) {
			final boolean ret = map.replace(element, oldCount, newCount);
			if (ret) size += newCount - oldCount;
			return ret;
		}

	}

	public static class ModInteger extends Number
			implements Field<ModInteger, Abelian<ModInteger>, Abelian<ModInteger>> {

		private static final long serialVersionUID = -8595710127161317579L;
		private final int mod;
		private int num;

		private final Addition add;
		private final Multiplication mul;

		private class Addition implements Abelian<ModInteger> {

			@Override
			public ModInteger identity() {
				return new ModInteger(mod, 0);
			}

			@Override
			public ModInteger inverse(final ModInteger element) {
				return new ModInteger(element, element.mod - element.num);
			}

			@Override
			public ModInteger apply(final ModInteger left, final ModInteger right) {
				return new ModInteger(left).addEqual(right);
			}
		}

		private class Multiplication implements Abelian<ModInteger> {

			@Override
			public ModInteger identity() {
				return new ModInteger(mod, 1);
			}

			@Override
			public ModInteger apply(final ModInteger left, final ModInteger right) {
				return new ModInteger(left).multiplyEqual(right);
			}

			@Override
			public ModInteger inverse(final ModInteger element) {
				return new ModInteger(element, element.inverse(element.num));
			}

		}

		@Override
		public int characteristic() {
			return mod;
		}

		public ModInteger(final int mod) {
			this.mod = mod;
			num = 0;
			add = new Addition();
			mul = new Multiplication();
		}

		public ModInteger(final int mod, final int num) {
			this.mod = mod;
			this.num = validNum(num);
			add = new Addition();
			mul = new Multiplication();
		}

		public ModInteger(final ModInteger n) {
			mod = n.mod;
			num = n.num;
			add = n.add;
			mul = n.mul;
		}

		private ModInteger(final ModInteger n, final int num) {
			mod = n.mod;
			this.num = num;
			add = n.add;
			mul = n.mul;
		}

		private int validNum(int n) {
			n %= mod;
			if (n < 0) n += mod;
			return n;
		}

		private int validNum(long n) {
			n %= mod;
			if (n < 0) n += mod;
			return (int) n;
		}

		protected int inverse(int n) {
			int m = mod, u = 0, v = 1, t;
			while (n != 0) {
				t = m / n;
				m -= t * n;
				u -= t * v;
				if (m != 0) {
					t = n / m;
					n -= t * m;
					v -= t * u;
				} else {
					v %= mod;
					if (v < 0) v += mod;
					return v;
				}
			}
			u %= mod;
			if (u < 0) u += mod;
			return u;
		}

		public boolean isPrime(final int n) {
			if ((n & 1) == 0) return false; // 偶数
			for (int i = 3, j = 8, k = 9; k <= n; i += 2, k += j += 8) if (n % i == 0) return false;
			return true;
		}

		@Override
		public int intValue() {
			return num;
		}

		@Override
		public long longValue() {
			return num;
		}

		@Override
		public float floatValue() {
			return num;
		}

		@Override
		public double doubleValue() {
			return num;
		}

		protected ModInteger getNewInstance(final ModInteger mod) {
			return new ModInteger(mod);
		}

		public ModInteger add(final int n) {
			return getNewInstance(this).addEqual(n);
		}

		public ModInteger add(final long n) {
			return getNewInstance(this).addEqual(n);
		}

		public ModInteger add(final ModInteger n) {
			return getNewInstance(this).addEqual(n);
		}

		public ModInteger addEqual(final int n) {
			num = validNum(num + n);
			return this;
		}

		public ModInteger addEqual(final long n) {
			num = validNum(num + n);
			return this;
		}

		public ModInteger addEqual(final ModInteger n) {
			if ((num += n.num) >= mod) num -= mod;
			return this;
		}

		public ModInteger subtract(final int n) {
			return getNewInstance(this).subtractEqual(n);
		}

		public ModInteger subtract(final long n) {
			return getNewInstance(this).subtractEqual(n);
		}

		public ModInteger subtract(final ModInteger n) {
			return getNewInstance(this).subtractEqual(n);
		}

		public ModInteger subtractEqual(final int n) {
			num = validNum(num - n);
			return this;
		}

		public ModInteger subtractEqual(final long n) {
			num = validNum(num - n);
			return this;
		}

		public ModInteger subtractEqual(final ModInteger n) {
			if ((num -= n.num) < 0) num += mod;
			return this;
		}

		public ModInteger multiply(final int n) {
			return getNewInstance(this).multiplyEqual(n);
		}

		public ModInteger multiply(final long n) {
			return getNewInstance(this).multiplyEqual(n);
		}

		public ModInteger multiply(final ModInteger n) {
			return getNewInstance(this).multiplyEqual(n);
		}

		public ModInteger multiplyEqual(final int n) {
			num = (int) ((long) num * n % mod);
			if (num < 0) num += mod;
			return this;
		}

		public ModInteger multiplyEqual(final long n) {
			return multiplyEqual((int) (n % mod));
		}

		public ModInteger multiplyEqual(final ModInteger n) {
			num = (int) ((long) num * n.num % mod);
			return this;
		}

		public ModInteger divide(final int n) {
			return getNewInstance(this).divideEqual(n);
		}

		public ModInteger divide(final long n) {
			return getNewInstance(this).divideEqual(n);
		}

		public ModInteger divide(final ModInteger n) {
			return getNewInstance(this).divideEqual(n);
		}

		public ModInteger divideEqual(final int n) {
			num = (int) ((long) num * inverse(validNum(n)) % mod);
			return this;
		}

		public ModInteger divideEqual(final long n) {
			return divideEqual((int) (n % mod));
		}

		public ModInteger divideEqual(final ModInteger n) {
			num = (int) ((long) num * n.inverse(n.num) % mod);
			return this;
		}

		public ModInteger pow(final int n) {
			return getNewInstance(this).powEqual(n);
		}

		public ModInteger pow(final long n) {
			return getNewInstance(this).powEqual(n);
		}

		public ModInteger pow(final ModInteger n) {
			return getNewInstance(this).powEqual(n);
		}

		public ModInteger powEqual(int n) {
			long ans = 1, num = this.num;
			if (n < 0) {
				n = -n;
				while (n != 0) {
					if ((n & 1) != 0) ans = ans * num % mod;
					n >>>= 1;
					num = num * num % mod;
				}
				this.num = inverse((int) ans);
				return this;
			}
			while (n != 0) {
				if ((n & 1) != 0) ans = ans * num % mod;
				n >>>= 1;
				num = num * num % mod;
			}
			this.num = (int) ans;
			return this;
		}

		public ModInteger powEqual(final long n) {
			return powEqual((int) (n % (mod - 1)));
		}

		public ModInteger powEqual(final ModInteger n) {
			long num = this.num;
			this.num = 1;
			int mul = n.num;
			while (mul != 0) {
				if ((mul & 1) != 0) this.num *= num;
				mul >>>= 1;
				num *= num;
				num %= mod;
			}
			return this;
		}

		public ModInteger equal(final int n) {
			num = validNum(n);
			return this;
		}

		public ModInteger equal(final long n) {
			num = validNum(n);
			return this;
		}

		public ModInteger equal(final ModInteger n) {
			num = n.num;
			return this;
		}

		public int toInt() {
			return num;
		}

		public int getMod() { return mod; }

		@Override
		public boolean equals(final Object x) {
			if (x instanceof ModInteger) return ((ModInteger) x).num == num && ((ModInteger) x).mod == mod;
			return false;
		}

		@Override
		public int hashCode() {
			return num ^ mod;
		}

		@Override
		public String toString() {
			return String.valueOf(num);
		}

		@Deprecated
		public String debug() {
			int min = num, ans = 1;
			for (int i = 2; i < min; ++i) {
				final int tmp = multiply(i).num;
				if (min > tmp) {
					min = tmp;
					ans = i;
				}
			}
			return min + "/" + ans;
		}

		@Override
		public Addition getAddition() { return add; }

		@Override
		public Multiplication getMultiplication() { return mul; }
	}

	/**
	 * 素数を法とする演算上で、組み合わせの計算を高速に行います。
	 *
	 * @author 31536000
	 *
	 */
	public static class ModUtility {

		private final int mod;
		private int[] fact, inv, invfact;

		/**
		 * modを法として、演算を行います。
		 *
		 * @param mod 法とする素数
		 */
		public ModUtility(final Prime mod) {
			this(mod, 2);
		}

		/**
		 * modを法として、演算を行います。
		 *
		 * @param mod  法とする素数
		 * @param calc 予め前計算しておく大きさ
		 */
		public ModUtility(final Prime mod, final int calc) {
			this.mod = mod.prime;
			precalc(calc);
		}

		/**
		 * calcの大きさだけ、前計算を行います。
		 *
		 * @param calc 前計算をする大きさ
		 */
		public void precalc(int calc) {
			++calc;
			if (calc < 2) calc = 2;
			if (calc > mod) calc = mod;
			fact = new int[calc];
			inv = new int[calc];
			invfact = new int[calc];
			fact[0] = invfact[0] = fact[1] = invfact[1] = inv[1] = 1;
			for (int i = 2; i < calc; ++i) {
				fact[i] = (int) ((long) fact[i - 1] * i % mod);
				inv[i] = (int) (mod - (long) inv[mod % i] * (mod / i) % mod);
				invfact[i] = (int) ((long) invfact[i - 1] * inv[i] % mod);
			}
		}

		/**
		 * modを法とする剰余環上で振舞う整数を返します。
		 *
		 * @return modを法とする整数、初期値は0
		 */
		public ModInteger create() {
			return new ModInt();
		}

		/**
		 * modを法とする剰余環上で振舞う整数を返します。
		 *
		 * @param n 初期値
		 * @return modを法とする整数
		 */
		public ModInteger create(final int n) {
			return new ModInt(n);
		}

		private class ModInt extends ModInteger {

			private static final long serialVersionUID = -2435281861935422575L;

			public ModInt() {
				super(mod);
			}

			public ModInt(final int n) {
				super(mod, n);
			}

			public ModInt(final ModInteger mod) {
				super(mod);
			}

			@Override
			protected ModInteger getNewInstance(final ModInteger mod) {
				return new ModInt(mod);
			}

			@Override
			protected int inverse(final int n) {
				return ModUtility.this.inverse(n);
			}
		}

		/**
		 * modを法として、nの逆元を返します。<br>
		 * 計算量はO(log n)です。
		 *
		 * @param n 逆元を求めたい値
		 * @return 逆元
		 */
		public int inverse(int n) {
			try {
				if (inv.length > n) return inv[n];
				int m = mod, u = 0, v = 1, t;
				while (n != 0) {
					t = m / n;
					m -= t * n;
					u -= t * v;
					if (m != 0) {
						t = n / m;
						n -= t * m;
						v -= t * u;
					} else {
						v %= mod;
						if (v < 0) v += mod;
						return v;
					}
				}
				u %= mod;
				if (u < 0) u += mod;
				return u;
			} catch (final ArrayIndexOutOfBoundsException e) {
				throw new IllegalArgumentException();
			}
		}

		/**
		 * n!を、modを法として求めた値を返します。<br>
		 * 計算量はO(n)です。
		 *
		 * @param n 階乗を求めたい値
		 * @return nの階乗をmodで割った余り
		 */
		public int factorial(final int n) {
			try {
				if (fact.length > n) return fact[n];
				long ret = fact[fact.length - 1];
				for (int i = fact.length; i <= n; ++i) ret = ret * i % mod;
				return (int) ret;
			} catch (final ArrayIndexOutOfBoundsException e) {
				throw new IllegalArgumentException();
			}
		}

		/**
		 * nPkをmodで割った余りを求めます。<br>
		 * 計算量はO(n-k)です。
		 *
		 * @param n 左辺
		 * @param k 右辺
		 * @return nPkをmodで割った余り
		 */
		public int permutation(final int n, final int k) {
			if (n < 0) throw new IllegalArgumentException();
			if (n < k) return 0;
			if (fact.length > n) return (int) ((long) fact[n] * invfact[n - k] % mod);
			long ret = 1;
			for (int i = n - k + 1; i <= n; ++i) ret = ret * i % mod;
			return (int) ret;
		}

		/**
		 * nCkをmodで割った余りを求めます。<br>
		 * 計算量はO(min(plogn, n-k))です。
		 *
		 * @param n 左辺
		 * @param k 右辺
		 * @return nCkをmodで割った余り
		 */
		public int combination(int n, int k) {
			if (n < 0) throw new IllegalArgumentException();
			if (n < k) return 0;
			if (fact.length > n) return (int) ((long) fact[n] * invfact[k] % mod * invfact[n - k] % mod);
			long ret = 1;
			if (n >= mod) {
				if (mod == 2) return (~n & k) == 0 ? 1 : 0;
				while (n > 0) {
					ret = ret * combination(n % mod, k % mod) % mod;
					n /= mod;
					k /= mod;
				}
				return (int) ret;
			}
			if (n < 2 * k) k = n - k;
			ret = invfact.length > k ? invfact[k] : inverse(factorial(k));
			for (int i = n - k + 1; i <= n; ++i) ret = ret * i % mod;
			return (int) ret;
		}

		/**
		 * 他項係数をmodで割った余りを求めます。<br>
		 * ] 計算量はO(n)です。
		 *
		 * @param n 左辺
		 * @param k 右辺、合計がn以下である必要がある
		 * @return 他項係数
		 */
		public int multinomial(final int n, final int... k) {
			int sum = 0;
			long ret = factorial(n);
			if (fact.length > n) {
				for (final int i : k) {
					if (i < 0) throw new IllegalArgumentException();
					ret = ret * invfact[i] % mod;
					sum += i;
				}
				if (sum > n) return 0;
				ret = ret * invfact[n - sum] % mod;
			} else {
				for (final int i : k) {
					if (i < 0) throw new IllegalArgumentException();
					if (invfact.length > i) ret = ret * invfact[i] % mod;
					else ret = ret * inverse(factorial(i)) % mod;
					sum += i;
				}
				if (sum > n) return 0;
				if (invfact.length > n - sum) ret = ret * invfact[n - sum] % mod;
				else ret = ret * inverse(factorial(n - sum)) % mod;
			}
			return (int) ret;
		}

		/**
		 * n個からk個を選ぶ重複組み合わせnHkをmodで割った余りを求めます。<br>
		 * 計算量はO(min(n, k))です。
		 *
		 * @param n 左辺
		 * @param k 右辺
		 * @return nHkをmodで割った余り
		 */
		public int multichoose(final int n, final int k) {
			return combination(mod(n + k - 1), k);
		}

		/**
		 * カタラン数C(n)をmodで割った余りを求めます。<br>
		 * 計算量はO(n)です。
		 *
		 * @param n 求めたいカタラン数の番号
		 * @return カタラン数
		 */
		public int catalan(final int n) {
			return divide(combination(mod(2 * n), n), mod(n + 1));
		}

		/**
		 * 第一種スターリング数S(n, k)をmodで割った余りを求めます。<br>
		 * 計算量はO(nk)です。 // TODO NTTを使うとO(n log n)、未実装
		 *
		 * @param n 左辺
		 * @param k 右辺
		 * @return S(n, k)をmodで割った余り
		 */
		public int firstStirling(final int n, final int k) {
			final int[] stirling = new int[(n + 1) * (k + 1)];
			stirling[0] = 1;
			final int h = k + 1;
			for (int i = 0; i < n; ++i) {
				for (int j = 0; j < k; ++j) {
					final int tmp = stirling[i * h + j] + (int) ((long) i * stirling[i * h + j + 1] % mod);
					stirling[(i + 1) * h + j + 1] = tmp >= mod ? tmp - mod : tmp;
				}
			}
			return stirling[stirling.length - 1];
		}

		/**
		 * 第二種スターリング数S(n, k)をmodで割った余りを求めます。<br>
		 * 計算量はO(k)です。
		 *
		 * @param n 左辺
		 * @param k 右辺
		 * @return S(n, k)をmodで割った余り
		 */
		public int secondStirling(final int n, final int k) {
			if (k == 0) return n == 0 ? 1 : 0;
			final int[] sieve = new int[k + 1], prime = new int[k + 1];
			int size = 0;
			sieve[1] = 1;
			for (int i = 2; i <= k; ++i) {
				if (sieve[i] == 0) prime[size++] = sieve[i] = i;
				for (int j = 0, s; j < size && prime[j] <= sieve[i] && (s = i * prime[j]) <= k; ++j)
					sieve[s] = prime[j];
			}
			long ans = 0;
			for (int i = 1, s; i <= k; ++i) {
				final long tmp = (long) combination(k, i)
						* (prime[i] = (s = sieve[i]) == i ? pow(i, n) : (int) ((long) prime[s] * prime[i / s] % mod))
						% mod;
				ans += (k - i & 1) != 0 ? -tmp : tmp;
			}
			return (int) ((long) mod(ans) * invfact[k] % mod);
		}

		/**
		 * ベル数B(n, k)をmodで割った余りを求めます。<br>
		 * 計算量はO(k)です。
		 *
		 * @param n 左辺
		 * @param k 右辺
		 * @return B(n, k)をmodで割った余り
		 */
		public int bell(final int n, final int k) {
			if (k == 0) return n == 0 ? 1 : 0;
			final int[] sieve = new int[k + 1], prime = new int[k + 1];
			int size = 0;
			sieve[1] = 1;
			long sum = 0;
			for (int i = 2; i <= k; ++i) {
				if (sieve[i] == 0) prime[size++] = sieve[i] = i;
				for (int j = 0, s; j < size && prime[j] <= sieve[i] && (s = i * prime[j]) <= k; ++j)
					sieve[s] = prime[j];
				sum += (i & 1) != 0 ? -invfact[i] : invfact[i];
			}
			sum = mod(sum);
			long ans = 0;
			for (int i = 0, s; i <= k; ++i) {
				final long tmp = (long) (prime[i] = (s = sieve[i]) == i ? pow(i, n)
						: (int) ((long) prime[s] * prime[i / s] % mod)) * invfact[i] % mod;
				ans += tmp * sum % mod;
				if ((sum -= (k - i & 1) != 0 ? -invfact[k - i] : invfact[k - i]) < 0) sum += mod;
			}
			return mod(ans);
		}

		/**
		 * ベル数B(n)をmodで割った余りを求めます。<br>
		 * 計算量はO(n)です。
		 *
		 * @param n 求めたいベル数の番号
		 * @return B(n)
		 */
		public int bell(final int n) {
			return bell(n, n);
		}

		/**
		 * 分割数P(n, k)をmodで割った余りを求めます。<br>
		 * 計算量はO(nk)です。 // TODO NTTを使うとO(n log n)、未実装
		 *
		 * @param n 左辺
		 * @param k 右辺
		 * @return P(n, k)をmodで割った余り
		 */
		public int pertition(final int n, final int k) {
			final int[] pertition = new int[(n + 1) * (k + 1)];
			pertition[0] = 1;
			final int h = k + 1;
			for (int i = 0; i <= n; ++i) {
				for (int j = 1, l = Math.min(i, k); j <= l; ++j)
					pertition[i * h + j] = pertition[i * h + j - 1] + pertition[(i - j) * h + j];
				for (int j = i; j < k; ++j) pertition[i * h + j + 1] = pertition[i * h + j];
			}
			return pertition[n * h + k];
		}

		/**
		 * 分割数P(n)をmodで割った余りを求めます。<br>
		 * 計算量はO(n sqrt(n))です。 // TODO NTTを使うとO(n log n)、未実装
		 *
		 * @param n 求めたい分割数の番号
		 * @return P(n)
		 */
		public int pertition(final int n) {
			final long[] pertition = new long[n + 1];
			pertition[0] = 1;
			for (int i = 1; i <= n; ++i) {
				for (int j = 1, t; (t = i - (j * (3 * j - 1) >> 1)) >= 0; ++j) {
					pertition[i] += (j & 1) != 0 ? pertition[t] : -pertition[t];
				}
				for (int j = 1, t; (t = i - (j * (3 * j + 1) >> 1)) >= 0; ++j) {
					pertition[i] += (j & 1) != 0 ? pertition[t] : -pertition[t];
				}
				pertition[i] %= mod;
			}
			return (int) pertition[n];
		}

		/**
		 * nのm乗をmodで割った余りを求めます。<br>
		 * 計算量はO(log m)です。
		 *
		 * @param n 床
		 * @param m 冪指数
		 * @return n^mをmodで割った余り
		 */
		public int pow(final int n, int m) {
			long ans = 1, num = n;
			if (m < 0) {
				m = -m;
				while (m != 0) {
					if ((m & 1) != 0) ans = ans * num % mod;
					m >>>= 1;
					num = num * num % mod;
				}
				return inverse((int) ans);
			}
			while (m != 0) {
				if ((m & 1) != 0) ans = ans * num % mod;
				m >>>= 1;
				num = num * num % mod;
			}
			return (int) ans;
		}

		/**
		 * nのm乗をmodで割った余りを求めます。<br>
		 * 計算量はO(log m)です。
		 *
		 * @param n 床
		 * @param m 冪指数
		 * @return n^mをmodで割った余り
		 */
		public int pow(final long n, final long m) {
			return pow((int) (n % mod), (int) (m % (mod - 1)));
		}

		/**
		 * 現在のmod値のトーシェント数を返します。<br>
		 * なお、これはmod-1に等しいです。
		 *
		 * @return トーシェント数
		 */
		public int totient() {
			return mod - 1;
		}

		/**
		 * nのトーシェント数を返します。<br>
		 * 計算量はO(sqrt n)です。
		 *
		 * @param n トーシェント数を求めたい値
		 * @return nのトーシェント数
		 */
		public static int totient(int n) {
			int totient = n;
			for (int i = 2; i * i <= n; ++i) {
				if (n % i == 0) {
					totient = totient / i * (i - 1);
					while ((n %= i) % i == 0);
				}
			}
			if (n != 1) totient = totient / n * (n - 1);
			return totient;
		}

		/**
		 * nをmodで割った余りを返します。
		 *
		 * @param n 演算する値
		 * @return nをmodで割った余り
		 */
		public int mod(int n) {
			return (n %= mod) < 0 ? n + mod : n;
		}

		/**
		 * nをmodで割った余りを返します。
		 *
		 * @param n 演算する値
		 * @return nをmodで割った余り
		 */
		public int mod(long n) {
			return (int) ((n %= mod) < 0 ? n + mod : n);
		}

		/**
		 * nをmodで割った余りを返します。
		 *
		 * @param n 演算する値
		 * @return nをmodで割った余り
		 */
		public int mod(final PrimeFactor n) {
			int ret = 1;
			for (final Entry<Prime, Integer> i : n.primeFactor.entrySet())
				ret = multiply(ret, pow(i.getKey().prime, i.getValue()));
			return ret;
		}

		/**
		 * n+mをmodで割った余りを返します。
		 *
		 * @param n 足される値
		 * @param m 足す値
		 * @return n+mをmodで割った余り
		 */
		public int add(final int n, final int m) {
			return mod(n + m);
		}

		/**
		 * n-mをmodで割った余りを返します。
		 *
		 * @param n 引かれる値
		 * @param m 引く値
		 * @return n-mをmodで割った余り
		 */
		public int subtract(final int n, final int m) {
			return mod(n - m);
		}

		/**
		 * n*mをmodで割った余りを返します。
		 *
		 * @param n 掛けられる値
		 * @param m 掛ける値
		 * @return n*mをmodで割った余り
		 */
		public int multiply(final int n, final int m) {
			final int ans = (int) ((long) n * m % mod);
			return ans < 0 ? ans + mod : ans;
		}

		/**
		 * n/mをmodで割った余りを返します。
		 *
		 * @param n 割られる値
		 * @param m 割る値
		 * @return n/mをmodで割った余り
		 */
		public int divide(final int n, final int m) {
			return multiply(n, inverse(m));
		}

		/**
		 * fを通ることが分かっているfの要素数-1次の関数について、xの位置における値をmodで割った余りを返します。<br>
		 * 計算量はO(f)です。
		 *
		 * @param f 関数の形
		 * @param x 求める位置
		 * @return 求めたい値をmodで割った余り
		 */
		public ModInteger lagrangePolynomial(final ModInteger[] f, final int x) {
			if (f.length > x) return f[x];
			if (x > fact.length) precalc(x);
			final ModInteger ret = create(0);
			final ModInteger[] dp = new ModInteger[f.length], dp2 = new ModInteger[f.length];
			dp[0] = create(1);
			dp2[f.length - 1] = create(1);
			for (int i = 1; i < f.length; ++i) {
				dp[i] = dp[i - 1].multiply(x - i - 1);
				dp2[f.length - i - 1] = dp2[f.length - i].multiply(x - f.length + i);
			}
			for (int i = 0; i < f.length; ++i) {
				final ModInteger tmp = f[i].multiply(dp[i]).multiplyEqual(dp2[i]).multiplyEqual(inv[i])
						.multiplyEqual(inv[f.length - 1 - i]);
				if ((f.length - i & 1) == 0) ret.addEqual(tmp);
				else ret.subtractEqual(tmp);
			}
			return ret;
		}

		/**
		 * 与えられた配列に対し、その配列を並び替えることで構成できる配列の集合をSとします。
		 * このとき、arrayがSを辞書順に並べると何番目かを求めます。
		 * @complexity N=array.length として O(N log N)
		 * @param array 辞書順で何番目か求めたい配列
		 * @return arrayが辞書順で何番目か
		 */
		public ModInteger permutationNumber(int[] array) {
			int[] compress = ArrayUtility.compress(array);
			int[] bucket = new int[array.length];
			for (int i : compress) ++bucket[i];
			int sum = multinomial(array.length, bucket);
			int[] bit = new int[array.length + 1];
			for (int i = 0; i < array.length; ++i)
				for (int j = i + 1, add = bucket[i]; j < bit.length; j += j & -j) bit[j] += add;
			int ans = 1;
			for (int i = 0; i < array.length; ++i) {
				sum = divide(sum, array.length - i);
				int comp = compress[i];
				int min = 0;
				for (int j = comp; j != 0; j -= j & -j) min += bit[j];
				ans = add(ans, multiply(sum, min));
				sum = multiply(sum, bucket[comp]--);
				for (int j = comp + 1; j < bit.length; j += j & -j) --bit[j];
			}
			return create(ans);
		}
	}

	/**
	 * 区間における素数を保持する関数です。
	 *
	 * @author 31536000
	 *
	 */
	public static class SegmentPrime {

		private final Prime[] divisor;
		private final int offset;

		private SegmentPrime(final Prime[] divisor, final int offset) {
			this.divisor = divisor;
			this.offset = offset;
		}

		/**
		 * このクラスが持つ区間の範囲を返します。
		 *
		 * @return 素数を保持している区間
		 */
		public IntRange getRange() { return IntRange.closedOpen(offset, offset + divisor.length); }

		/**
		 * 素数かどうかを判定します。
		 *
		 * @param n 素数かどうか判定したい数
		 * @return 素数ならばtrue
		 */
		public boolean isPrime(final int n) {
			return n <= 1 ? false : divisor[n - offset].prime == n;
		}

		/**
		 * 与えられた数を素因数分解します。<br>
		 * 計算量はO(log n)です。
		 *
		 * @param n 素因数分解したい数
		 * @return 素因数分解した結果
		 */
		public PrimeFactor getPrimeFactor(int n) {
			if (n < 1) throw new IllegalArgumentException("not positive number");
			final Map<Prime, Integer> map = new HashMap<>();
			while (n > 1) {
				final Prime d = divisor[n - offset];
				map.compute(d, (k, v) -> v == null ? 1 : v + 1);
				n /= d.prime;
			}
			return new PrimeFactor(map);
		}

		@Override
		public String toString() {
			return "SegmentPrime: [" + offset + ", " + (offset + divisor.length) + ")";
		}
	}

	/**
	 * 整数の素因数分解表現を保持します。
	 *
	 * @author 31536000
	 *
	 */
	public static class PrimeFactor extends Number {

		private static final long serialVersionUID = 1363575672283884773L;
		public Map<Prime, Integer> primeFactor;

		private PrimeFactor(final Map<Prime, Integer> n) {
			primeFactor = n;
		}

		/**
		 * 素因数分解のリスト表現を返します。
		 *
		 * @return 素因数分解のリスト
		 */
		public List<Integer> getFactorizationList() {
			final List<Integer> ret = new ArrayList<>();
			for (final Entry<Prime, Integer> i : primeFactor.entrySet()) {
				final int p = i.getKey().prime, n = i.getValue();
				for (int j = 0; j < n; ++j) ret.add(p);
			}
			return ret;
		}

		/**
		 * nとgcdを取った値を保持します。
		 *
		 * @param n gcdを取りたい値
		 */
		public void gcd(final PrimeFactor n) {
			for (final Entry<Prime, Integer> i : n.primeFactor.entrySet())
				primeFactor.computeIfPresent(i.getKey(), (k, v) -> Math.min(v, i.getValue()));
		}

		/**
		 * gcd(n, m)を返します。
		 *
		 * @param n gcdを取りたい値
		 * @param m gcdを取りたい値
		 * @return gcd(n, m)
		 */
		public static PrimeFactor gcd(final PrimeFactor n, final PrimeFactor m) {
			final Map<Prime, Integer> ret = new HashMap<>(n.primeFactor);
			for (final Entry<Prime, Integer> i : m.primeFactor.entrySet())
				ret.computeIfPresent(i.getKey(), (k, v) -> Math.min(v, i.getValue()));
			return new PrimeFactor(ret);
		}

		/**
		 * nとlcmを取った値を保持します。
		 *
		 * @param n lcmを取りたい値
		 */
		public void lcm(final PrimeFactor n) {
			for (final Entry<Prime, Integer> i : n.primeFactor.entrySet())
				primeFactor.merge(i.getKey(), i.getValue(), (v1, v2) -> Math.max(v1, v2));
		}

		/**
		 * lcm(n, m)を返します。
		 *
		 * @param n lcmを取りたい値
		 * @param m lcmを取りたい値
		 * @return lcm(n, m)
		 */
		public static PrimeFactor lcm(final PrimeFactor n, final PrimeFactor m) {
			final Map<Prime, Integer> ret = new HashMap<>(n.primeFactor);
			for (final Entry<Prime, Integer> i : m.primeFactor.entrySet())
				ret.merge(i.getKey(), i.getValue(), (v1, v2) -> Math.max(v1, v2));
			return new PrimeFactor(ret);
		}

		private static int pow(final int p, int n) {
			int ans = 1;
			for (int mul = p; n > 0; n >>= 1, mul *= mul) if ((n & 1) != 0) ans *= mul;
			return ans;
		}

		private static long pow(final long p, long n) {
			long ans = 1;
			for (long mul = p; n > 0; n >>= 1, mul *= mul) if ((n & 1) != 0) ans *= mul;
			return ans;
		}

		public BigInteger getValue() {
			BigInteger ret = BigInteger.ONE;
			for (final Entry<Prime, Integer> i : primeFactor.entrySet())
				ret = ret.multiply(new BigInteger(i.getKey().toString()).pow(i.getValue()));
			return ret;
		}

		@Override
		public int intValue() {
			int ret = 1;
			for (final Entry<Prime, Integer> i : primeFactor.entrySet()) ret *= pow(i.getKey().prime, i.getValue());
			return ret;
		}

		@Override
		public long longValue() {
			long ret = 1;
			for (final Entry<Prime, Integer> i : primeFactor.entrySet())
				ret *= pow((long) i.getKey().prime, i.getValue());
			return ret;
		}

		@Override
		public float floatValue() {
			float ret = 1;
			for (final Entry<Prime, Integer> i : primeFactor.entrySet())
				ret *= Math.pow(i.getKey().prime, i.getValue());
			return ret;
		}

		@Override
		public double doubleValue() {
			long ret = 1;
			for (final Entry<Prime, Integer> i : primeFactor.entrySet())
				ret *= Math.pow(i.getKey().prime, i.getValue());
			return ret;
		}

		@Override
		public boolean equals(final Object o) {
			return o instanceof PrimeFactor ? ((PrimeFactor) o).primeFactor.equals(primeFactor) : false;
		}

		@Override
		public int hashCode() {
			return primeFactor.hashCode();
		}

		@Override
		public String toString() {
			return primeFactor.toString();
		}
	}

	/**
	 * 素数を渡すためのクラスです。<br>
	 * 中身が確実に素数であることを保証するときに使ってください。
	 *
	 * @author 31536000
	 *
	 */
	public static class Prime extends Number {

		private static final long serialVersionUID = 8216169308184181643L;
		public final int prime;

		/**
		 * 素数を設定します。
		 *
		 * @param prime 素数
		 * @throws IllegalArgumentException 素数以外を渡した時
		 */
		public Prime(final int prime) {
			if (!isPrime(prime)) throw new IllegalArgumentException(prime + " is not prime");
			this.prime = prime;
		}

		private Prime(final int prime, final boolean none) {
			this.prime = prime;
		}

		private static final int bases[] = { 15591, 2018, 166, 7429, 8064, 16045, 10503, 4399, 1949, 1295, 2776, 3620,
				560, 3128, 5212, 2657, 2300, 2021, 4652, 1471, 9336, 4018, 2398, 20462, 10277, 8028, 2213, 6219, 620,
				3763, 4852, 5012, 3185, 1333, 6227, 5298, 1074, 2391, 5113, 7061, 803, 1269, 3875, 422, 751, 580, 4729,
				10239, 746, 2951, 556, 2206, 3778, 481, 1522, 3476, 481, 2487, 3266, 5633, 488, 3373, 6441, 3344, 17,
				15105, 1490, 4154, 2036, 1882, 1813, 467, 3307, 14042, 6371, 658, 1005, 903, 737, 1887, 7447, 1888,
				2848, 1784, 7559, 3400, 951, 13969, 4304, 177, 41, 19875, 3110, 13221, 8726, 571, 7043, 6943, 1199, 352,
				6435, 165, 1169, 3315, 978, 233, 3003, 2562, 2994, 10587, 10030, 2377, 1902, 5354, 4447, 1555, 263,
				27027, 2283, 305, 669, 1912, 601, 6186, 429, 1930, 14873, 1784, 1661, 524, 3577, 236, 2360, 6146, 2850,
				55637, 1753, 4178, 8466, 222, 2579, 2743, 2031, 2226, 2276, 374, 2132, 813, 23788, 1610, 4422, 5159,
				1725, 3597, 3366, 14336, 579, 165, 1375, 10018, 12616, 9816, 1371, 536, 1867, 10864, 857, 2206, 5788,
				434, 8085, 17618, 727, 3639, 1595, 4944, 2129, 2029, 8195, 8344, 6232, 9183, 8126, 1870, 3296, 7455,
				8947, 25017, 541, 19115, 368, 566, 5674, 411, 522, 1027, 8215, 2050, 6544, 10049, 614, 774, 2333, 3007,
				35201, 4706, 1152, 1785, 1028, 1540, 3743, 493, 4474, 2521, 26845, 8354, 864, 18915, 5465, 2447, 42,
				4511, 1660, 166, 1249, 6259, 2553, 304, 272, 7286, 73, 6554, 899, 2816, 5197, 13330, 7054, 2818, 3199,
				811, 922, 350, 7514, 4452, 3449, 2663, 4708, 418, 1621, 1171, 3471, 88, 11345, 412, 1559, 194 };
		private static final byte wheel[] = { 10, 2, 4, 2, 4, 6, 2, 6, 4, 2, 4, 6, 6, 2, 6, 4, 2, 6, 4, 6, 8, 4, 2, 4,
				2, 4, 8, 6, 4, 6, 2, 4, 6, 2, 6, 6, 4, 2, 4, 6, 2, 6, 4, 2, 4, 2, 10, 2 };

		private static boolean isSPRP(final int n, long a) {
			int d = n - 1, s = 0;
			while ((d & 1) == 0) {
				++s;
				d >>= 1;
			}
			long cur = 1, pw = d;
			do {
				if ((pw & 1) != 0) cur = cur * a % n;
				a = a * a % n;
				pw >>= 1;
			} while (pw != 0);
			if (cur == 1) return true;
			for (int r = 0; r < s; ++r) {
				if (cur == n - 1) return true;
				cur = cur * cur % n;
			}
			return false;
		}

		/**
		 * 与えられた値が素数か否かを判定します。<br>
		 * この実装はhttp://ceur-ws.org/Vol-1326/020-Forisek.pdfに基づきます。
		 *
		 * @param x 判定したい値
		 * @return xが素数ならtrue
		 */
		public static boolean isPrime(final int x) {
			if (x == 2 || x == 3 || x == 5 || x == 7) return true;
			if ((x & 1) == 0 || x % 3 == 0 || x % 5 == 0 || x % 7 == 0) return false;
			return checkPrime(x);
		}

		private static boolean checkPrime(final int x) {
			if (x < 121) return x > 1;
			long h = x;
			h = (h >> 16 ^ h) * 0x45d9f3b;
			h = (h >> 16 ^ h) * 0x45d9f3b;
			h = (h >> 16 ^ h) & 0xFF;
			return isSPRP(x, bases[(int) h]);
		}

		/**
		 * 区間における素数を列挙します。<br>
		 * この実装はエラトステネスの篩に基づきます。
		 *
		 * @param n 素数を求める範囲
		 * @return 1以上n以下の素数を保持する区間素数
		 */
		public static SegmentPrime getSegmentPrime(final int n) {
			final Prime[] divisor = new Prime[n - 1];
			final int sqrt = (int) Math.sqrt(n) + 1;
			for (int i = 0; i < sqrt; ++i) {
				if (divisor[i] != null) continue;
				final int p = i + 2;
				divisor[i] = new Prime(p, true);
				for (int j = p * p - 2; j < divisor.length; j += p) divisor[j] = divisor[i];
			}
			for (int i = sqrt; i < divisor.length; ++i) if (divisor[i] == null) divisor[i] = new Prime(i + 2, true);
			return new SegmentPrime(divisor, 2);
		}

		/**
		 * 与えられた値を素因数分解した結果を返します。
		 *
		 * @param x 素因数分解する値
		 * @return 素因数分解した結果
		 */
		public static PrimeFactor getPrimeFactor(int x) {
			if (x <= 0) throw new IllegalArgumentException("non positive number: " + x);
			final Map<Prime, Integer> ret = new TreeMap<>((l, r) -> Integer.compare(l.prime, r.prime));
			int c;
			if ((x & 1) == 0) {
				c = 1;
				for (x >>= 1; (x & 1) == 0; x >>= 1) ++c;
				ret.put(new Prime(2, false), c);
			}
			if (x % 3 == 0) {
				c = 1;
				for (x /= 3; x % 3 == 0; x /= 3) ++c;
				ret.put(new Prime(3, false), c);
			}
			if (x % 5 == 0) {
				c = 1;
				for (x /= 5; x % 5 == 0; x /= 5) ++c;
				ret.put(new Prime(5, false), c);
			}
			if (x % 7 == 0) {
				c = 1;
				for (x /= 7; x % 7 == 0; x /= 7) ++c;
				ret.put(new Prime(7, false), c);
			}
			if (x < 100000000) { // Wheel Factorization
				for (int i = 11, j = 0; i * i <= x; i += wheel[++j % wheel.length]) {
					while (x % i == 0) {
						x /= i;
						ret.compute(new Prime(i, false), (k, v) -> v == null ? 1 : v + 1);
					}
				}
				if (x != 1) ret.put(new Prime(x, false), 1);
			} else {
				int p, count;
				while (x != 1) { // 素因数分解が終わってる
					for (p = x; !checkPrime(p); p = pollardRho(p, 1));
					final Prime prime = new Prime(p, false);
					count = 1;
					for (x /= p; x % p == 0; x /= p) ++count;
					ret.put(prime, count);
				}
			}
			return new PrimeFactor(ret);
		}

		private static int gcd(int n, int m) {
			while (n != 0) if ((m %= n) != 0) n %= m;
			else return n;
			return m;
		}

		private static int pollardRho(final int x, int c) {
			int n = 2, m = 2, d = 1, next = 4, i = 1;
			do {
				if (++i == next) {
					m = n;
					next <<= 1;
				}
				if ((n = (int) (((long) n * n + c) % x)) == m) return pollardRho(x, ++c); // 失敗したので
			} while ((d = gcd(Math.abs(n - m), x)) == 1);// dは約数の一つ
			return d;
		}

		@Override
		public int intValue() {
			return prime;
		}

		@Override
		public long longValue() {
			return prime;
		}

		@Override
		public float floatValue() {
			return prime;
		}

		@Override
		public double doubleValue() {
			return prime;
		}

		@Override
		public boolean equals(final Object o) {
			return o instanceof Prime ? ((Prime) o).prime == prime : false;
		}

		@Override
		public int hashCode() {
			return prime;
		}

		@Override
		public String toString() {
			return String.valueOf(prime);
		}
	}

	public static class AbstractArray<T> extends AbstractList<T> implements RandomAccess {

		private final Object[] array;

		public AbstractArray(final int size) {
			array = new Object[size];
		}

		public AbstractArray(final T[] array) {
			this(array.length);
			System.arraycopy(array, 0, this.array, 0, array.length);
		}

		@Override
		public T set(final int index, final T element) {
			final T ret = get(index);
			array[index] = element;
			return ret;
		}

		@Override
		public T get(final int index) {
			@SuppressWarnings("unchecked")
			final T ret = (T) array[index];
			return ret;
		}

		public Object[] get() {
			return array;
		}

		public T[] get(final T[] array) {
			if (array.length < this.array.length) {
				@SuppressWarnings("unchecked")
				final T[] ret = (T[]) Arrays.copyOfRange(this.array, 0, this.array.length, array.getClass());
				return ret;
			}
			System.arraycopy(this.array, 0, array, 0, this.array.length);
			return array;
		}

		@Override
		public int size() {
			return array.length;
		}

		public int length() {
			return size();
		}

		@Override
		public int hashCode() {
			return Arrays.hashCode(array);
		}

		private class Iter implements Iterator<T> {

			private int index;

			private Iter() {
				index = 0;
			}

			@Override
			public boolean hasNext() {
				return index < array.length;
			}

			@Override
			public T next() {
				return get(index++);
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		}

		@Override
		public Iterator<T> iterator() {
			return new Iter();
		}
	}

	public static class Array<T> extends AbstractArray<T> implements Serializable {

		private static final long serialVersionUID = 2749604433067098063L;

		public Array(final int size) {
			super(size);
		}

		public Array(final T[] array) {
			super(array);
		}

		public T front() {
			return get(0);
		}

		public T back() {
			return get(size() - 1);
		}
	}

	/**
	 * 要素とそのindexを管理するクラスです。
	 *
	 * @author 31536000
	 *
	 * @param <E> 保持する要素
	 */
	public static class Enumerate<E> {

		public final E value;
		public final int index;

		/**
		 * 要素とそのindexを渡します。<br>
		 * indexは必ずしも元の配列またはコレクションのindexと一致する必要はありませんが、一致する値を返すことが推奨されます。
		 *
		 * @param value
		 * @param index
		 */
		public Enumerate(final E value, final int index) {
			this.value = value;
			this.index = index;
		}

		/**
		 * 要素を返します。
		 *
		 * @return 要素
		 */
		public E getValue() { return value; }

		/**
		 * indexを返します。
		 *
		 * @return index
		 */
		public int getIndex() { return index; }

		@Override
		public boolean equals(final Object o) {
			if (o instanceof Enumerate)
				return ((Enumerate<?>) o).getValue().equals(value) && ((Enumerate<?>) o).getIndex() == index;
			return false;
		}

		@Override
		public int hashCode() {
			return value.hashCode() ^ index;
		}

		@Override
		public String toString() {
			return "{" + value.toString() + ", " + index + "}";
		}
	}

	/**
	 * 要素とそのindexを効率的に取得する関数を提供します。
	 *
	 * @author 31536000
	 *
	 */
	public static class Enumeration {

		private static class IteratorArray<E> implements Iterator<Enumerate<E>> {

			private final E[] array;
			private final int start;
			private int index;

			public IteratorArray(final E[] array, final int index) {
				this.array = array;
				this.start = index;
				this.index = 0;
			}

			@Override
			public boolean hasNext() {
				return index < array.length;
			}

			@Override
			public Enumerate<E> next() {
				final Enumerate<E> ret = new Enumerate<>(array[index], index++ + start);
				return ret;
			}
		}

		private static class IteratorCollection<E> implements Iterator<Enumerate<E>> {

			private final Iterator<E> iter;
			private int start;

			public IteratorCollection(final Iterator<E> iter, final int index) {
				this.iter = iter;
				this.start = index;
			}

			@Override
			public boolean hasNext() {
				return iter.hasNext();
			}

			@Override
			public Enumerate<E> next() {
				final Enumerate<E> ret = new Enumerate<>(iter.next(), start++);
				return ret;
			}
		}

		/**
		 * 配列の各要素とそのindexを順に返すIteratorを生成します。
		 *
		 * @param       <E> 配列の型
		 * @param array 配列
		 * @return Enumerate&lt;E&gt;のIterator
		 */
		public static <E> Iterator<Enumerate<E>> enumerate(final E[] array) {
			return enumerate(array, 0);
		}

		/**
		 * 配列の各要素とそのindexを順に返すIteratorを生成します。
		 *
		 * @param       <E> 配列の型
		 * @param array 配列
		 * @param start 添字の初期値、この値だけindexが足されたものが返る
		 * @return Enumerate&lt;E&gt;のIterator
		 */
		public static <E> Iterator<Enumerate<E>> enumerate(final E[] array, final int start) {
			if (array == null) throw new NullPointerException("array is null");
			return new IteratorArray<>(array, start);
		}

		/**
		 * Iteratorの各要素とそのindexを順に返すIteratorを生成します。
		 *
		 * @param      <E> Iteratorの型
		 * @param iter Iterator
		 * @return Enumerate&lt;E&gt;のIterator
		 */
		public static <E> Iterator<Enumerate<E>> enumerate(final Iterator<E> iter) {
			return enumerate(iter, 0);
		}

		/**
		 * Iteratorの各要素とそのindexを順に返すIteratorを生成します。
		 *
		 * @param       <E> Iteratorの型
		 * @param iter  Iterator
		 * @param start 添字の初期値、この値だけindexが足されたものが返る
		 * @return Enumerate&lt;E&gt;のIterator
		 */
		public static <E> Iterator<Enumerate<E>> enumerate(final Iterator<E> iter, final int start) {
			if (iter == null) throw new NullPointerException("iterator is null");
			return new IteratorCollection<>(iter, start);
		}
	}

	/**
	 * このクラスは配列に対する様々な操作を提供します。
	 * @author 31536000
	 *
	 */
	public static class ArrayUtility {

		private ArrayUtility() {
			throw new AssertionError();
		}

		/**
		 * initを用いて配列を生成します。配列のi番目の要素はinit.applyAsInt(i)になります。
		 * @complexity O(length)
		 * @param length 配列の長さ
		 * @param init 配列の初期値を決める関数
		 * @return 配列
		 */
		public static int[] create(int length, java.util.function.IntUnaryOperator init) {
			int[] ret = new int[length];
			for (int i = 0; i < length; ++i) ret[i] = init.applyAsInt(i);
			return ret;
		}

		/**
		 * initを用いて配列を生成します。配列のi番目の要素はinit.applyAsInt(i)になります。
		 * @complexity O(length)
		 * @param length 配列の長さ
		 * @param init 配列の初期値を決める関数
		 * @return 配列
		 */
		public static long[] create(int length, java.util.function.LongUnaryOperator init) {
			long[] ret = new long[length];
			for (int i = 0; i < length; ++i) ret[i] = init.applyAsLong(i);
			return ret;
		}

		/**
		 * initを用いて配列を生成します。配列のi番目の要素はinit.applyAsInt(i)になります。
		 * @complexity O(length)
		 * @param length 配列の長さ
		 * @param init 配列の初期値を決める関数
		 * @return 配列
		 */
		public static double[] create(int length, java.util.function.DoubleUnaryOperator init) {
			double[] ret = new double[length];
			for (int i = 0; i < length; ++i) ret[i] = init.applyAsDouble(i);
			return ret;
		}

		/**
		 * 配列の最後に要素を一つ増やした新しい配列を返します。
		 * @complexity O(array.length)
		 * @param array 元の配列
		 * @param element 加えたい要素
		 * @return 配列の後ろに要素を加えた配列
		 */
		public static boolean[] add(boolean[] array, boolean element) {
			if (array == null) {
				boolean[] ret = { element };
				return ret;
			}
			boolean[] ret = new boolean[array.length + 1];
			System.arraycopy(array, 0, ret, 0, array.length);
			ret[array.length] = element;
			return ret;
		}

		/**
		 * 配列の最後に要素を一つ増やした新しい配列を返します。
		 * @complexity O(array.length)
		 * @param array 元の配列
		 * @param element 加えたい要素
		 * @return 配列の後ろに要素を加えた配列
		 */
		public static byte[] add(byte[] array, byte element) {
			if (array == null) {
				byte[] ret = { element };
				return ret;
			}
			byte[] ret = new byte[array.length + 1];
			System.arraycopy(array, 0, ret, 0, array.length);
			ret[array.length] = element;
			return ret;
		}

		/**
		 * 配列の最後に要素を一つ増やした新しい配列を返します。
		 * @complexity O(array.length)
		 * @param array 元の配列
		 * @param element 加えたい要素
		 * @return 配列の後ろに要素を加えた配列
		 */
		public static short[] add(short[] array, short element) {
			if (array == null) {
				short[] ret = { element };
				return ret;
			}
			short[] ret = new short[array.length + 1];
			System.arraycopy(array, 0, ret, 0, array.length);
			ret[array.length] = element;
			return ret;
		}

		/**
		 * 配列の最後に要素を一つ増やした新しい配列を返します。
		 * @complexity O(array.length)
		 * @param array 元の配列
		 * @param element 加えたい要素
		 * @return 配列の後ろに要素を加えた配列
		 */
		public static int[] add(int[] array, int element) {
			if (array == null) {
				int[] ret = { element };
				return ret;
			}
			int[] ret = new int[array.length + 1];
			System.arraycopy(array, 0, ret, 0, array.length);
			ret[array.length] = element;
			return ret;
		}

		/**
		 * 配列の最後に要素を一つ増やした新しい配列を返します。
		 * @complexity O(array.length)
		 * @param array 元の配列
		 * @param element 加えたい要素
		 * @return 配列の後ろに要素を加えた配列
		 */
		public static long[] add(long[] array, long element) {
			if (array == null) {
				long[] ret = { element };
				return ret;
			}
			long[] ret = new long[array.length + 1];
			System.arraycopy(array, 0, ret, 0, array.length);
			ret[array.length] = element;
			return ret;
		}

		/**
		 * 配列の最後に要素を一つ増やした新しい配列を返します。
		 * @complexity O(array.length)
		 * @param array 元の配列
		 * @param element 加えたい要素
		 * @return 配列の後ろに要素を加えた配列
		 */
		public static float[] add(float[] array, float element) {
			if (array == null) {
				float[] ret = { element };
				return ret;
			}
			float[] ret = new float[array.length + 1];
			System.arraycopy(array, 0, ret, 0, array.length);
			ret[array.length] = element;
			return ret;
		}

		/**
		 * 配列の最後に要素を一つ増やした新しい配列を返します。
		 * @complexity O(array.length)
		 * @param array 元の配列
		 * @param element 加えたい要素
		 * @return 配列の後ろに要素を加えた配列
		 */
		public static double[] add(double[] array, double element) {
			if (array == null) {
				double[] ret = { element };
				return ret;
			}
			double[] ret = new double[array.length + 1];
			System.arraycopy(array, 0, ret, 0, array.length);
			ret[array.length] = element;
			return ret;
		}

		/**
		 * 配列の最後に要素を一つ増やした新しい配列を返します。
		 * @complexity O(array.length)
		 * @param array 元の配列
		 * @param element 加えたい要素
		 * @return 配列の後ろに要素を加えた配列
		 */
		public static char[] add(char[] array, char element) {
			if (array == null) {
				char[] ret = { element };
				return ret;
			}
			char[] ret = new char[array.length + 1];
			System.arraycopy(array, 0, ret, 0, array.length);
			ret[array.length] = element;
			return ret;
		}

		/**
		 * 配列の最後に要素を一つ増やした新しい配列を返します。
		 * @complexity O(array.length)
		 * @param array 元の配列
		 * @param element 加えたい要素
		 * @return 配列の後ろに要素を加えた配列
		 */
		public static <T> T[] add(T[] array, T element) {
			if (array == null) { return addAll(array, element); }
			@SuppressWarnings("unchecked")
			T[] ret = (T[]) java.util.Arrays.copyOfRange(array, 0, array.length + 1, array.getClass());
			ret[array.length] = element;
			return ret;
		}

		/**
		 * 2つの配列を結合した新しい配列を返します。
		 * @complexity O(array.length + array2.length)
		 * @param array 左側の配列
		 * @param array2 右側の配列
		 * @return 2つの配列を結合した配列
		 */
		public static boolean[] addAll(boolean[] array, boolean... array2) {
			if (array == null) return array2 == null ? null : array2.clone();
			if (array2 == null) return array.clone();
			boolean[] ret = new boolean[array.length + array2.length];
			System.arraycopy(array, 0, ret, 0, array.length);
			System.arraycopy(array2, 0, ret, array.length, array2.length);
			return ret;
		}

		/**
		 * 2つの配列を結合した新しい配列を返します。
		 * @complexity O(array.length + array2.length)
		 * @param array 左側の配列
		 * @param array2 右側の配列
		 * @return 2つの配列を結合した配列
		 */
		public static byte[] addAll(byte[] array, byte... array2) {
			if (array == null) return array2 == null ? null : array2.clone();
			if (array2 == null) return array.clone();
			byte[] ret = new byte[array.length + array2.length];
			System.arraycopy(array, 0, ret, 0, array.length);
			System.arraycopy(array2, 0, ret, array.length, array2.length);
			return ret;
		}

		/**
		 * 2つの配列を結合した新しい配列を返します。
		 * @complexity O(array.length + array2.length)
		 * @param array 左側の配列
		 * @param array2 右側の配列
		 * @return 2つの配列を結合した配列
		 */
		public static short[] addAll(short[] array, short... array2) {
			if (array == null) return array2 == null ? null : array2.clone();
			if (array2 == null) return array.clone();
			short[] ret = new short[array.length + array2.length];
			System.arraycopy(array, 0, ret, 0, array.length);
			System.arraycopy(array2, 0, ret, array.length, array2.length);
			return ret;
		}

		/**
		 * 2つの配列を結合した新しい配列を返します。
		 * @complexity O(array.length + array2.length)
		 * @param array 左側の配列
		 * @param array2 右側の配列
		 * @return 2つの配列を結合した配列
		 */
		public static int[] addAll(int[] array, int... array2) {
			if (array == null) return array2 == null ? null : array2.clone();
			if (array2 == null) return array.clone();
			int[] ret = new int[array.length + array2.length];
			System.arraycopy(array, 0, ret, 0, array.length);
			System.arraycopy(array2, 0, ret, array.length, array2.length);
			return ret;
		}

		/**
		 * 2つの配列を結合した新しい配列を返します。
		 * @complexity O(array.length + array2.length)
		 * @param array 左側の配列
		 * @param array2 右側の配列
		 * @return 2つの配列を結合した配列
		 */
		public static long[] addAll(long[] array, long... array2) {
			if (array == null) return array2 == null ? null : array2.clone();
			if (array2 == null) return array.clone();
			long[] ret = new long[array.length + array2.length];
			System.arraycopy(array, 0, ret, 0, array.length);
			System.arraycopy(array2, 0, ret, array.length, array2.length);
			return ret;
		}

		/**
		 * 2つの配列を結合した新しい配列を返します。
		 * @complexity O(array.length + array2.length)
		 * @param array 左側の配列
		 * @param array2 右側の配列
		 * @return 2つの配列を結合した配列
		 */
		public static float[] addAll(float[] array, float... array2) {
			if (array == null) return array2 == null ? null : array2.clone();
			if (array2 == null) return array.clone();
			float[] ret = new float[array.length + array2.length];
			System.arraycopy(array, 0, ret, 0, array.length);
			System.arraycopy(array2, 0, ret, array.length, array2.length);
			return ret;
		}

		/**
		 * 2つの配列を結合した新しい配列を返します。
		 * @complexity O(array.length + array2.length)
		 * @param array 左側の配列
		 * @param array2 右側の配列
		 * @return 2つの配列を結合した配列
		 */
		public static double[] addAll(double[] array, double... array2) {
			if (array == null) return array2 == null ? null : array2.clone();
			if (array2 == null) return array.clone();
			double[] ret = new double[array.length + array2.length];
			System.arraycopy(array, 0, ret, 0, array.length);
			System.arraycopy(array2, 0, ret, array.length, array2.length);
			return ret;
		}

		/**
		 * 2つの配列を結合した新しい配列を返します。
		 * @complexity O(array.length + array2.length)
		 * @param array 左側の配列
		 * @param array2 右側の配列
		 * @return 2つの配列を結合した配列
		 */
		public static char[] addAll(char[] array, char... array2) {
			if (array == null) return array2 == null ? null : array2.clone();
			if (array2 == null) return array.clone();
			char[] ret = new char[array.length + array2.length];
			System.arraycopy(array, 0, ret, 0, array.length);
			System.arraycopy(array2, 0, ret, array.length, array2.length);
			return ret;
		}

		/**
		 * 2つの配列を結合した新しい配列を返します。
		 * @complexity O(array.length + array2.length)
		 * @param array 左側の配列
		 * @param array2 右側の配列
		 * @return 2つの配列を結合した配列
		 */
		@SafeVarargs
		public static <T> T[] addAll(T[] array, T... array2) {
			if (array == null) return array2 == null ? null : array2.clone();
			if (array2 == null) return array.clone();
			@SuppressWarnings("unchecked")
			T[] ret = (T[]) java.util.Arrays.copyOfRange(array, 0, array.length + array2.length, array.getClass());
			System.arraycopy(array2, 0, ret, array.length, array2.length);
			return ret;
		}

		/**
		 * 配列を逆順にします。
		 * @complexity O(array.length)
		 * @param array 元の配列
		 */
		public static void reverse(boolean[] array) {
			if (array != null)
				for (int i = 0, l = array.length + 1 >> 1; i < l; ++i) swap(array, i, array.length - 1 - i);
		}

		/**
		 * 配列を逆順にします。
		 * @complexity O(toIndex-fromIndex)
		 * @param array 元の配列
		 * @param fromIndex 逆順にする左閉区間
		 * @param toIndex 逆順にする右開区間
		 */
		public static void reverse(boolean[] array, int fromIndex, int toIndex) {
			for (--toIndex; fromIndex < toIndex; ++fromIndex, --toIndex) swap(array, fromIndex, toIndex);
		}

		/**
		 * 配列を逆順にします。
		 * @complexity O(range.getDistance())
		 * @param array 元の配列
		 * @param range 逆順にする区間
		 */
		public static void reverse(boolean[] array, IntRange range) {
			reverse(array, range.getClosedLower(), range.getOpenUpper());
		}

		/**
		 * 配列を逆順にします。
		 * @complexity O(array.length)
		 * @param array 元の配列
		 */
		public static void reverse(byte[] array) {
			if (array != null)
				for (int i = 0, l = array.length + 1 >> 1; i < l; ++i) swap(array, i, array.length - 1 - i);
		}

		/**
		 * 配列を逆順にします。
		 * @complexity O(toIndex-fromIndex)
		 * @param array 元の配列
		 * @param fromIndex 逆順にする左閉区間
		 * @param toIndex 逆順にする右開区間
		 */
		public static void reverse(byte[] array, int fromIndex, int toIndex) {
			for (--toIndex; fromIndex < toIndex; ++fromIndex, --toIndex) swap(array, fromIndex, toIndex);
		}

		/**
		 * 配列を逆順にします。
		 * @complexity O(range.getDistance())
		 * @param array 元の配列
		 * @param range 逆順にする区間
		 */
		public static void reverse(byte[] array, IntRange range) {
			reverse(array, range.getClosedLower(), range.getOpenUpper());
		}

		/**
		 * 配列を逆順にします。
		 * @complexity O(array.length)
		 * @param array 元の配列
		 */
		public static void reverse(short[] array) {
			if (array != null)
				for (int i = 0, l = array.length + 1 >> 1; i < l; ++i) swap(array, i, array.length - 1 - i);
		}

		/**
		 * 配列を逆順にします。
		 * @complexity O(toIndex-fromIndex)
		 * @param array 元の配列
		 * @param fromIndex 逆順にする左閉区間
		 * @param toIndex 逆順にする右開区間
		 */
		public static void reverse(short[] array, int fromIndex, int toIndex) {
			for (--toIndex; fromIndex < toIndex; ++fromIndex, --toIndex) swap(array, fromIndex, toIndex);
		}

		/**
		 * 配列を逆順にします。
		 * @complexity O(range.getDistance())
		 * @param array 元の配列
		 * @param range 逆順にする区間
		 */
		public static void reverse(short[] array, IntRange range) {
			reverse(array, range.getClosedLower(), range.getOpenUpper());
		}

		/**
		 * 配列を逆順にします。
		 * @complexity O(array.length)
		 * @param array 元の配列
		 */
		public static void reverse(int[] array) {
			if (array != null)
				for (int i = 0, l = array.length + 1 >> 1; i < l; ++i) swap(array, i, array.length - 1 - i);
		}

		/**
		 * 配列を逆順にします。
		 * @complexity O(toIndex-fromIndex)
		 * @param array 元の配列
		 * @param fromIndex 逆順にする左閉区間
		 * @param toIndex 逆順にする右開区間
		 */
		public static void reverse(int[] array, int fromIndex, int toIndex) {
			for (--toIndex; fromIndex < toIndex; ++fromIndex, --toIndex) swap(array, fromIndex, toIndex);
		}

		/**
		 * 配列を逆順にします。
		 * @complexity O(range.getDistance())
		 * @param array 元の配列
		 * @param range 逆順にする区間
		 */
		public static void reverse(int[] array, IntRange range) {
			reverse(array, range.getClosedLower(), range.getOpenUpper());
		}

		/**
		 * 配列を逆順にします。
		 * @complexity O(array.length)
		 * @param array 元の配列
		 */
		public static void reverse(long[] array) {
			if (array != null)
				for (int i = 0, l = array.length + 1 >> 1; i < l; ++i) swap(array, i, array.length - 1 - i);
		}

		/**
		 * 配列を逆順にします。
		 * @complexity O(toIndex-fromIndex)
		 * @param array 元の配列
		 * @param fromIndex 逆順にする左閉区間
		 * @param toIndex 逆順にする右開区間
		 */
		public static void reverse(long[] array, int fromIndex, int toIndex) {
			for (--toIndex; fromIndex < toIndex; ++fromIndex, --toIndex) swap(array, fromIndex, toIndex);
		}

		/**
		 * 配列を逆順にします。
		 * @complexity O(range.getDistance())
		 * @param array 元の配列
		 * @param range 逆順にする区間
		 */
		public static void reverse(long[] array, IntRange range) {
			reverse(array, range.getClosedLower(), range.getOpenUpper());
		}

		/**
		 * 配列を逆順にします。
		 * @complexity O(array.length)
		 * @param array 元の配列
		 */
		public static void reverse(float[] array) {
			if (array != null)
				for (int i = 0, l = array.length + 1 >> 1; i < l; ++i) swap(array, i, array.length - 1 - i);
		}

		/**
		 * 配列を逆順にします。
		 * @complexity O(toIndex-fromIndex)
		 * @param array 元の配列
		 * @param fromIndex 逆順にする左閉区間
		 * @param toIndex 逆順にする右開区間
		 */
		public static void reverse(float[] array, int fromIndex, int toIndex) {
			for (--toIndex; fromIndex < toIndex; ++fromIndex, --toIndex) swap(array, fromIndex, toIndex);
		}

		/**
		 * 配列を逆順にします。
		 * @complexity O(range.getDistance())
		 * @param array 元の配列
		 * @param range 逆順にする区間
		 */
		public static void reverse(float[] array, IntRange range) {
			reverse(array, range.getClosedLower(), range.getOpenUpper());
		}

		/**
		 * 配列を逆順にします。
		 * @complexity O(array.length)
		 * @param array 元の配列
		 */
		public static void reverse(double[] array) {
			if (array != null)
				for (int i = 0, l = array.length + 1 >> 1; i < l; ++i) swap(array, i, array.length - 1 - i);
		}

		/**
		 * 配列を逆順にします。
		 * @complexity O(toIndex-fromIndex)
		 * @param array 元の配列
		 * @param fromIndex 逆順にする左閉区間
		 * @param toIndex 逆順にする右開区間
		 */
		public static void reverse(double[] array, int fromIndex, int toIndex) {
			for (--toIndex; fromIndex < toIndex; ++fromIndex, --toIndex) swap(array, fromIndex, toIndex);
		}

		/**
		 * 配列を逆順にします。
		 * @complexity O(range.getDistance())
		 * @param array 元の配列
		 * @param range 逆順にする区間
		 */
		public static void reverse(double[] array, IntRange range) {
			reverse(array, range.getClosedLower(), range.getOpenUpper());
		}

		/**
		 * 配列を逆順にします。
		 * @complexity O(array.length)
		 * @param array 元の配列
		 */
		public static void reverse(char[] array) {
			if (array != null)
				for (int i = 0, l = array.length + 1 >> 1; i < l; ++i) swap(array, i, array.length - 1 - i);
		}

		/**
		 * 配列を逆順にします。
		 * @complexity O(toIndex-fromIndex)
		 * @param array 元の配列
		 * @param fromIndex 逆順にする左閉区間
		 * @param toIndex 逆順にする右開区間
		 */
		public static void reverse(char[] array, int fromIndex, int toIndex) {
			for (--toIndex; fromIndex < toIndex; ++fromIndex, --toIndex) swap(array, fromIndex, toIndex);
		}

		/**
		 * 配列を逆順にします。
		 * @complexity O(range.getDistance())
		 * @param array 元の配列
		 * @param range 逆順にする区間
		 */
		public static void reverse(char[] array, IntRange range) {
			reverse(array, range.getClosedLower(), range.getOpenUpper());
		}

		/**
		 * 配列を逆順にします。
		 * @complexity O(array.length)
		 * @param array 元の配列
		 */
		public static void reverse(Object[] array) {
			if (array != null)
				for (int i = 0, l = array.length + 1 >> 1; i < l; ++i) swap(array, i, array.length - 1 - i);
		}

		/**
		 * 配列を逆順にします。
		 * @complexity O(toIndex-fromIndex)
		 * @param array 元の配列
		 * @param fromIndex 逆順にする左閉区間
		 * @param toIndex 逆順にする右開区間
		 */
		public static void reverse(Object[] array, int fromIndex, int toIndex) {
			for (--toIndex; fromIndex < toIndex; ++fromIndex, --toIndex) swap(array, fromIndex, toIndex);
		}

		/**
		 * 配列を逆順にします。
		 * @complexity O(range.getDistance())
		 * @param array 元の配列
		 * @param range 逆順にする区間
		 */
		public static void reverse(Object[] array, IntRange range) {
			reverse(array, range.getClosedLower(), range.getOpenUpper());
		}

		private static java.util.Random rnd;

		/**
		 * 配列をシャッフルします。
		 * @complexity O(array.length)
		 * @param array 元の配列
		 */
		public static void shuffle(boolean[] array) {
			shuffle(array, rnd == null ? rnd = new java.util.Random() : rnd);
		}

		/**
		 * 配列をシャッフルします。
		 * @complexity O(toIndex-fromIndex)
		 * @param array 元の配列
		 * @param fromIndex シャッフルする左閉区間
		 * @param toIndex シャッフルする右開区間
		 */
		public static void shuffle(boolean[] array, int fromIndex, int toIndex) {
			shuffle(array, fromIndex, toIndex, rnd == null ? rnd = new java.util.Random() : rnd);
		}

		/**
		 * 配列をシャッフルします。
		 * @complexity O(range.getDistance())
		 * @param array 元の配列
		 * @param range シャッフルする区間
		 */
		public static void shuffle(boolean[] array, IntRange range) {
			shuffle(array, range.getClosedLower(), range.getOpenUpper(),
					rnd == null ? rnd = new java.util.Random() : rnd);
		}

		/**
		 * 配列をシャッフルします。
		 * @complexity O(array.length)
		 * @param array 元の配列
		 * @param random 乱数
		 */
		public static void shuffle(boolean[] array, java.util.Random random) {
			if (array != null) for (int i = array.length - 1; i > 0; --i) swap(array, i, random.nextInt(i + 1));
		}

		/**
		 * 配列をシャッフルします。
		 * @complexity O(toIndex-fromIndex)
		 * @param array 元の配列
		 * @param fromIndex シャッフルする左閉区間
		 * @param toIndex シャッフルする右開区間
		 * @param random 乱数
		 */
		public static void shuffle(boolean[] array, int fromIndex, int toIndex, java.util.Random random) {
			if (array != null)
				for (int i = toIndex - 1; i > fromIndex; --i) swap(array, i, random.nextInt(i - fromIndex) + fromIndex);
		}

		/**
		 * 配列をシャッフルします。
		 * @complexity O(range.getDistance())
		 * @param array 元の配列
		 * @param range シャッフルする区間
		 * @param random 乱数
		 */
		public static void shuffle(boolean[] array, IntRange range, java.util.Random random) {
			shuffle(array, range.getClosedLower(), range.getOpenUpper(), random);
		}

		/**
		 * 配列をシャッフルします。
		 * @complexity O(array.length)
		 * @param array 元の配列
		 */
		public static void shuffle(byte[] array) {
			shuffle(array, rnd == null ? rnd = new java.util.Random() : rnd);
		}

		/**
		 * 配列をシャッフルします。
		 * @complexity O(toIndex-fromIndex)
		 * @param array 元の配列
		 * @param fromIndex シャッフルする左閉区間
		 * @param toIndex シャッフルする右開区間
		 */
		public static void shuffle(byte[] array, int fromIndex, int toIndex) {
			shuffle(array, fromIndex, toIndex, rnd == null ? rnd = new java.util.Random() : rnd);
		}

		/**
		 * 配列をシャッフルします。
		 * @complexity O(range.getDistance())
		 * @param array 元の配列
		 * @param range シャッフルする区間
		 */
		public static void shuffle(byte[] array, IntRange range) {
			shuffle(array, range.getClosedLower(), range.getOpenUpper(),
					rnd == null ? rnd = new java.util.Random() : rnd);
		}

		/**
		 * 配列をシャッフルします。
		 * @complexity O(array.length)
		 * @param array 元の配列
		 * @param random 乱数
		 */
		public static void shuffle(byte[] array, java.util.Random random) {
			if (array != null) for (int i = array.length - 1; i > 0; --i) swap(array, i, random.nextInt(i + 1));
		}

		/**
		 * 配列をシャッフルします。
		 * @complexity O(toIndex-fromIndex)
		 * @param array 元の配列
		 * @param fromIndex シャッフルする左閉区間
		 * @param toIndex シャッフルする右開区間
		 * @param random 乱数
		 */
		public static void shuffle(byte[] array, int fromIndex, int toIndex, java.util.Random random) {
			if (array != null)
				for (int i = toIndex - 1; i > fromIndex; --i) swap(array, i, random.nextInt(i - fromIndex) + fromIndex);
		}

		/**
		 * 配列をシャッフルします。
		 * @complexity O(range.getDistance())
		 * @param array 元の配列
		 * @param range シャッフルする区間
		 * @param random 乱数
		 */
		public static void shuffle(byte[] array, IntRange range, java.util.Random random) {
			shuffle(array, range.getClosedLower(), range.getOpenUpper(), random);
		}

		/**
		* 配列をシャッフルします。
		* @complexity O(array.length)
		* @param array 元の配列
		*/
		public static void shuffle(short[] array) {
			shuffle(array, rnd == null ? rnd = new java.util.Random() : rnd);
		}

		/**
		 * 配列をシャッフルします。
		 * @complexity O(toIndex-fromIndex)
		 * @param array 元の配列
		 * @param fromIndex シャッフルする左閉区間
		 * @param toIndex シャッフルする右開区間
		 */
		public static void shuffle(short[] array, int fromIndex, int toIndex) {
			shuffle(array, fromIndex, toIndex, rnd == null ? rnd = new java.util.Random() : rnd);
		}

		/**
		 * 配列をシャッフルします。
		 * @complexity O(range.getDistance())
		 * @param array 元の配列
		 * @param range シャッフルする区間
		 */
		public static void shuffle(short[] array, IntRange range) {
			shuffle(array, range.getClosedLower(), range.getOpenUpper(),
					rnd == null ? rnd = new java.util.Random() : rnd);
		}

		/**
		 * 配列をシャッフルします。
		 * @complexity O(array.length)
		 * @param array 元の配列
		 * @param random 乱数
		 */
		public static void shuffle(short[] array, java.util.Random random) {
			if (array != null) for (int i = array.length - 1; i > 0; --i) swap(array, i, random.nextInt(i + 1));
		}

		/**
		 * 配列をシャッフルします。
		 * @complexity O(toIndex-fromIndex)
		 * @param array 元の配列
		 * @param fromIndex シャッフルする左閉区間
		 * @param toIndex シャッフルする右開区間
		 * @param random 乱数
		 */
		public static void shuffle(short[] array, int fromIndex, int toIndex, java.util.Random random) {
			if (array != null)
				for (int i = toIndex - 1; i > fromIndex; --i) swap(array, i, random.nextInt(i - fromIndex) + fromIndex);
		}

		/**
		 * 配列をシャッフルします。
		 * @complexity O(range.getDistance())
		 * @param array 元の配列
		 * @param range シャッフルする区間
		 * @param random 乱数
		 */
		public static void shuffle(short[] array, IntRange range, java.util.Random random) {
			shuffle(array, range.getClosedLower(), range.getOpenUpper(), random);
		}

		/**
		* 配列をシャッフルします。
		* @complexity O(array.length)
		* @param array 元の配列
		*/
		public static void shuffle(int[] array) {
			shuffle(array, rnd == null ? rnd = new java.util.Random() : rnd);
		}

		/**
		 * 配列をシャッフルします。
		 * @complexity O(toIndex-fromIndex)
		 * @param array 元の配列
		 * @param fromIndex シャッフルする左閉区間
		 * @param toIndex シャッフルする右開区間
		 */
		public static void shuffle(int[] array, int fromIndex, int toIndex) {
			shuffle(array, fromIndex, toIndex, rnd == null ? rnd = new java.util.Random() : rnd);
		}

		/**
		 * 配列をシャッフルします。
		 * @complexity O(range.getDistance())
		 * @param array 元の配列
		 * @param range シャッフルする区間
		 */
		public static void shuffle(int[] array, IntRange range) {
			shuffle(array, range.getClosedLower(), range.getOpenUpper(),
					rnd == null ? rnd = new java.util.Random() : rnd);
		}

		/**
		 * 配列をシャッフルします。
		 * @complexity O(array.length)
		 * @param array 元の配列
		 * @param random 乱数
		 */
		public static void shuffle(int[] array, java.util.Random random) {
			if (array != null) for (int i = array.length - 1; i > 0; --i) swap(array, i, random.nextInt(i + 1));
		}

		/**
		 * 配列をシャッフルします。
		 * @complexity O(toIndex-fromIndex)
		 * @param array 元の配列
		 * @param fromIndex シャッフルする左閉区間
		 * @param toIndex シャッフルする右開区間
		 * @param random 乱数
		 */
		public static void shuffle(int[] array, int fromIndex, int toIndex, java.util.Random random) {
			if (array != null)
				for (int i = toIndex - 1; i > fromIndex; --i) swap(array, i, random.nextInt(i - fromIndex) + fromIndex);
		}

		/**
		 * 配列をシャッフルします。
		 * @complexity O(range.getDistance())
		 * @param array 元の配列
		 * @param range シャッフルする区間
		 * @param random 乱数
		 */
		public static void shuffle(int[] array, IntRange range, java.util.Random random) {
			shuffle(array, range.getClosedLower(), range.getOpenUpper(), random);
		}

		/**
		* 配列をシャッフルします。
		* @complexity O(array.length)
		* @param array 元の配列
		*/
		public static void shuffle(long[] array) {
			shuffle(array, rnd == null ? rnd = new java.util.Random() : rnd);
		}

		/**
		 * 配列をシャッフルします。
		 * @complexity O(toIndex-fromIndex)
		 * @param array 元の配列
		 * @param fromIndex シャッフルする左閉区間
		 * @param toIndex シャッフルする右開区間
		 */
		public static void shuffle(long[] array, int fromIndex, int toIndex) {
			shuffle(array, fromIndex, toIndex, rnd == null ? rnd = new java.util.Random() : rnd);
		}

		/**
		 * 配列をシャッフルします。
		 * @complexity O(range.getDistance())
		 * @param array 元の配列
		 * @param range シャッフルする区間
		 */
		public static void shuffle(long[] array, IntRange range) {
			shuffle(array, range.getClosedLower(), range.getOpenUpper(),
					rnd == null ? rnd = new java.util.Random() : rnd);
		}

		/**
		 * 配列をシャッフルします。
		 * @complexity O(array.length)
		 * @param array 元の配列
		 * @param random 乱数
		 */
		public static void shuffle(long[] array, java.util.Random random) {
			if (array != null) for (int i = array.length - 1; i > 0; --i) swap(array, i, random.nextInt(i + 1));
		}

		/**
		 * 配列をシャッフルします。
		 * @complexity O(toIndex-fromIndex)
		 * @param array 元の配列
		 * @param fromIndex シャッフルする左閉区間
		 * @param toIndex シャッフルする右開区間
		 * @param random 乱数
		 */
		public static void shuffle(long[] array, int fromIndex, int toIndex, java.util.Random random) {
			if (array != null)
				for (int i = toIndex - 1; i > fromIndex; --i) swap(array, i, random.nextInt(i - fromIndex) + fromIndex);
		}

		/**
		 * 配列をシャッフルします。
		 * @complexity O(range.getDistance())
		 * @param array 元の配列
		 * @param range シャッフルする区間
		 * @param random 乱数
		 */
		public static void shuffle(long[] array, IntRange range, java.util.Random random) {
			shuffle(array, range.getClosedLower(), range.getOpenUpper(), random);
		}

		/**
		* 配列をシャッフルします。
		* @complexity O(array.length)
		* @param array 元の配列
		*/
		public static void shuffle(float[] array) {
			shuffle(array, rnd == null ? rnd = new java.util.Random() : rnd);
		}

		/**
		 * 配列をシャッフルします。
		 * @complexity O(toIndex-fromIndex)
		 * @param array 元の配列
		 * @param fromIndex シャッフルする左閉区間
		 * @param toIndex シャッフルする右開区間
		 */
		public static void shuffle(float[] array, int fromIndex, int toIndex) {
			shuffle(array, fromIndex, toIndex, rnd == null ? rnd = new java.util.Random() : rnd);
		}

		/**
		 * 配列をシャッフルします。
		 * @complexity O(range.getDistance())
		 * @param array 元の配列
		 * @param range シャッフルする区間
		 */
		public static void shuffle(float[] array, IntRange range) {
			shuffle(array, range.getClosedLower(), range.getOpenUpper(),
					rnd == null ? rnd = new java.util.Random() : rnd);
		}

		/**
		 * 配列をシャッフルします。
		 * @complexity O(array.length)
		 * @param array 元の配列
		 * @param random 乱数
		 */
		public static void shuffle(float[] array, java.util.Random random) {
			if (array != null) for (int i = array.length - 1; i > 0; --i) swap(array, i, random.nextInt(i + 1));
		}

		/**
		 * 配列をシャッフルします。
		 * @complexity O(toIndex-fromIndex)
		 * @param array 元の配列
		 * @param fromIndex シャッフルする左閉区間
		 * @param toIndex シャッフルする右開区間
		 * @param random 乱数
		 */
		public static void shuffle(float[] array, int fromIndex, int toIndex, java.util.Random random) {
			if (array != null)
				for (int i = toIndex - 1; i > fromIndex; --i) swap(array, i, random.nextInt(i - fromIndex) + fromIndex);
		}

		/**
		 * 配列をシャッフルします。
		 * @complexity O(range.getDistance())
		 * @param array 元の配列
		 * @param range シャッフルする区間
		 * @param random 乱数
		 */
		public static void shuffle(float[] array, IntRange range, java.util.Random random) {
			shuffle(array, range.getClosedLower(), range.getOpenUpper(), random);
		}

		/**
		* 配列をシャッフルします。
		* @complexity O(array.length)
		* @param array 元の配列
		*/
		public static void shuffle(double[] array) {
			shuffle(array, rnd == null ? rnd = new java.util.Random() : rnd);
		}

		/**
		 * 配列をシャッフルします。
		 * @complexity O(toIndex-fromIndex)
		 * @param array 元の配列
		 * @param fromIndex シャッフルする左閉区間
		 * @param toIndex シャッフルする右開区間
		 */
		public static void shuffle(double[] array, int fromIndex, int toIndex) {
			shuffle(array, fromIndex, toIndex, rnd == null ? rnd = new java.util.Random() : rnd);
		}

		/**
		 * 配列をシャッフルします。
		 * @complexity O(range.getDistance())
		 * @param array 元の配列
		 * @param range シャッフルする区間
		 */
		public static void shuffle(double[] array, IntRange range) {
			shuffle(array, range.getClosedLower(), range.getOpenUpper(),
					rnd == null ? rnd = new java.util.Random() : rnd);
		}

		/**
		 * 配列をシャッフルします。
		 * @complexity O(array.length)
		 * @param array 元の配列
		 * @param random 乱数
		 */
		public static void shuffle(double[] array, java.util.Random random) {
			if (array != null) for (int i = array.length - 1; i > 0; --i) swap(array, i, random.nextInt(i + 1));
		}

		/**
		 * 配列をシャッフルします。
		 * @complexity O(toIndex-fromIndex)
		 * @param array 元の配列
		 * @param fromIndex シャッフルする左閉区間
		 * @param toIndex シャッフルする右開区間
		 * @param random 乱数
		 */
		public static void shuffle(double[] array, int fromIndex, int toIndex, java.util.Random random) {
			if (array != null)
				for (int i = toIndex - 1; i > fromIndex; --i) swap(array, i, random.nextInt(i - fromIndex) + fromIndex);
		}

		/**
		 * 配列をシャッフルします。
		 * @complexity O(range.getDistance())
		 * @param array 元の配列
		 * @param range シャッフルする区間
		 * @param random 乱数
		 */
		public static void shuffle(double[] array, IntRange range, java.util.Random random) {
			shuffle(array, range.getClosedLower(), range.getOpenUpper(), random);
		}

		/**
		* 配列をシャッフルします。
		* @complexity O(array.length)
		* @param array 元の配列
		*/
		public static void shuffle(char[] array) {
			shuffle(array, rnd == null ? rnd = new java.util.Random() : rnd);
		}

		/**
		 * 配列をシャッフルします。
		 * @complexity O(toIndex-fromIndex)
		 * @param array 元の配列
		 * @param fromIndex シャッフルする左閉区間
		 * @param toIndex シャッフルする右開区間
		 */
		public static void shuffle(char[] array, int fromIndex, int toIndex) {
			shuffle(array, fromIndex, toIndex, rnd == null ? rnd = new java.util.Random() : rnd);
		}

		/**
		 * 配列をシャッフルします。
		 * @complexity O(range.getDistance())
		 * @param array 元の配列
		 * @param range シャッフルする区間
		 */
		public static void shuffle(char[] array, IntRange range) {
			shuffle(array, range.getClosedLower(), range.getOpenUpper(),
					rnd == null ? rnd = new java.util.Random() : rnd);
		}

		/**
		 * 配列をシャッフルします。
		 * @complexity O(array.length)
		 * @param array 元の配列
		 * @param random 乱数
		 */
		public static void shuffle(char[] array, java.util.Random random) {
			if (array != null) for (int i = array.length - 1; i > 0; --i) swap(array, i, random.nextInt(i + 1));
		}

		/**
		 * 配列をシャッフルします。
		 * @complexity O(toIndex-fromIndex)
		 * @param array 元の配列
		 * @param fromIndex シャッフルする左閉区間
		 * @param toIndex シャッフルする右開区間
		 * @param random 乱数
		 */
		public static void shuffle(char[] array, int fromIndex, int toIndex, java.util.Random random) {
			if (array != null)
				for (int i = toIndex - 1; i > fromIndex; --i) swap(array, i, random.nextInt(i - fromIndex) + fromIndex);
		}

		/**
		 * 配列をシャッフルします。
		 * @complexity O(range.getDistance())
		 * @param array 元の配列
		 * @param range シャッフルする区間
		 * @param random 乱数
		 */
		public static void shuffle(char[] array, IntRange range, java.util.Random random) {
			shuffle(array, range.getClosedLower(), range.getOpenUpper(), random);
		}

		/**
		* 配列をシャッフルします。
		* @complexity O(array.length)
		* @param array 元の配列
		*/
		public static void shuffle(Object[] array) {
			shuffle(array, rnd == null ? rnd = new java.util.Random() : rnd);
		}

		/**
		 * 配列をシャッフルします。
		 * @complexity O(toIndex-fromIndex)
		 * @param array 元の配列
		 * @param fromIndex シャッフルする左閉区間
		 * @param toIndex シャッフルする右開区間
		 */
		public static void shuffle(Object[] array, int fromIndex, int toIndex) {
			shuffle(array, fromIndex, toIndex, rnd == null ? rnd = new java.util.Random() : rnd);
		}

		/**
		 * 配列をシャッフルします。
		 * @complexity O(range.getDistance())
		 * @param array 元の配列
		 * @param range シャッフルする区間
		 */
		public static void shuffle(Object[] array, IntRange range) {
			shuffle(array, range.getClosedLower(), range.getOpenUpper(),
					rnd == null ? rnd = new java.util.Random() : rnd);
		}

		/**
		 * 配列をシャッフルします。
		 * @complexity O(array.length)
		 * @param array 元の配列
		 * @param random 乱数
		 */
		public static void shuffle(Object[] array, java.util.Random random) {
			if (array != null) for (int i = array.length - 1; i > 0; --i) swap(array, i, random.nextInt(i + 1));
		}

		/**
		 * 配列をシャッフルします。
		 * @complexity O(toIndex-fromIndex)
		 * @param array 元の配列
		 * @param fromIndex シャッフルする左閉区間
		 * @param toIndex シャッフルする右開区間
		 * @param random 乱数
		 */
		public static void shuffle(Object[] array, int fromIndex, int toIndex, java.util.Random random) {
			if (array != null)
				for (int i = toIndex - 1; i > fromIndex; --i) swap(array, i, random.nextInt(i - fromIndex) + fromIndex);
		}

		/**
		 * 配列をシャッフルします。
		 * @complexity O(range.getDistance())
		 * @param array 元の配列
		 * @param range シャッフルする区間
		 * @param random 乱数
		 */
		public static void shuffle(Object[] array, IntRange range, java.util.Random random) {
			shuffle(array, range.getClosedLower(), range.getOpenUpper(), random);
		}

		/**
		 * 指定した長さと初期値を持つ配列を生成します。
		 * @complexity O(size)
		 * @param size 配列の長さ
		 * @param value 配列の初期値
		 * @return 生成された配列
		 */
		public static boolean[] getArray(int size, boolean value) {
			boolean[] ret = new boolean[size];
			java.util.Arrays.fill(ret, value);
			return ret;
		}

		/**
		 * 指定した長さと初期値を持つ配列を生成します。
		 * @complexity O(size)
		 * @param size 配列の長さ
		 * @param value 配列の初期値
		 * @return 生成された配列
		 */
		public static byte[] getArray(int size, byte value) {
			byte[] ret = new byte[size];
			java.util.Arrays.fill(ret, value);
			return ret;
		}

		/**
		 * 指定した長さと初期値を持つ配列を生成します。
		 * @complexity O(size)
		 * @param size 配列の長さ
		 * @param value 配列の初期値
		 * @return 生成された配列
		 */
		public static short[] getArray(int size, short value) {
			short[] ret = new short[size];
			java.util.Arrays.fill(ret, value);
			return ret;
		}

		/**
		 * 指定した長さと初期値を持つ配列を生成します。
		 * @complexity O(size)
		 * @param size 配列の長さ
		 * @param value 配列の初期値
		 * @return 生成された配列
		 */
		public static int[] getArray(int size, int value) {
			int[] ret = new int[size];
			java.util.Arrays.fill(ret, value);
			return ret;
		}

		/**
		 * 指定した長さと初期値を持つ配列を生成します。
		 * @complexity O(size)
		 * @param size 配列の長さ
		 * @param value 配列の初期値
		 * @return 生成された配列
		 */
		public static long[] getArray(int size, long value) {
			long[] ret = new long[size];
			java.util.Arrays.fill(ret, value);
			return ret;
		}

		/**
		 * 指定した長さと初期値を持つ配列を生成します。
		 * @complexity O(size)
		 * @param size 配列の長さ
		 * @param value 配列の初期値
		 * @return 生成された配列
		 */
		public static float[] getArray(int size, float value) {
			float[] ret = new float[size];
			java.util.Arrays.fill(ret, value);
			return ret;
		}

		/**
		 * 指定した長さと初期値を持つ配列を生成します。
		 * @complexity O(size)
		 * @param size 配列の長さ
		 * @param value 配列の初期値
		 * @return 生成された配列
		 */
		public static double[] getArray(int size, double value) {
			double[] ret = new double[size];
			java.util.Arrays.fill(ret, value);
			return ret;
		}

		/**
		 * 指定した長さと初期値を持つ配列を生成します。
		 * @complexity O(size)
		 * @param size 配列の長さ
		 * @param value 配列の初期値
		 * @return 生成された配列
		 */
		public static char[] getArray(int size, char value) {
			char[] ret = new char[size];
			java.util.Arrays.fill(ret, value);
			return ret;
		}

		/**
		 * プリミティブ型の配列と中身が対応するオブジェクト型の配列を生成します。
		 * @complexity O(array.length)
		 * @param array プリミティブ型の配列
		 * @return オブジェクト型の配列
		 */
		public static Boolean[] toObject(boolean[] array) {
			if (array == null) return null;
			Boolean[] ret = new Boolean[array.length];
			for (int i = 0; i < ret.length; ++i) ret[i] = array[i];
			return ret;
		}

		/**
		 * プリミティブ型の配列と中身が対応するオブジェクト型の配列を生成します。
		 * @complexity O(array.length)
		 * @param array プリミティブ型の配列
		 * @return オブジェクト型の配列
		 */
		public static Byte[] toObject(byte[] array) {
			if (array == null) return null;
			Byte[] ret = new Byte[array.length];
			for (int i = 0; i < ret.length; ++i) ret[i] = array[i];
			return ret;
		}

		/**
		 * プリミティブ型の配列と中身が対応するオブジェクト型の配列を生成します。
		 * @complexity O(array.length)
		 * @param array プリミティブ型の配列
		 * @return オブジェクト型の配列
		 */
		public static Short[] toObject(short[] array) {
			if (array == null) return null;
			Short[] ret = new Short[array.length];
			for (int i = 0; i < ret.length; ++i) ret[i] = array[i];
			return ret;
		}

		/**
		 * プリミティブ型の配列と中身が対応するオブジェクト型の配列を生成します。
		 * @complexity O(array.length)
		 * @param array プリミティブ型の配列
		 * @return オブジェクト型の配列
		 */
		public static Integer[] toObject(int[] array) {
			if (array == null) return null;
			Integer[] ret = new Integer[array.length];
			for (int i = 0; i < ret.length; ++i) ret[i] = array[i];
			return ret;
		}

		/**
		 * プリミティブ型の配列と中身が対応するオブジェクト型の配列を生成します。
		 * @complexity O(array.length)
		 * @param array プリミティブ型の配列
		 * @return オブジェクト型の配列
		 */
		public static Long[] toObject(long[] array) {
			if (array == null) return null;
			Long[] ret = new Long[array.length];
			for (int i = 0; i < ret.length; ++i) ret[i] = array[i];
			return ret;
		}

		/**
		 * プリミティブ型の配列と中身が対応するオブジェクト型の配列を生成します。
		 * @complexity O(array.length)
		 * @param array プリミティブ型の配列
		 * @return オブジェクト型の配列
		 */
		public static Float[] toObject(float[] array) {
			if (array == null) return null;
			Float[] ret = new Float[array.length];
			for (int i = 0; i < ret.length; ++i) ret[i] = array[i];
			return ret;
		}

		/**
		 * プリミティブ型の配列と中身が対応するオブジェクト型の配列を生成します。
		 * @complexity O(array.length)
		 * @param array プリミティブ型の配列
		 * @return オブジェクト型の配列
		 */
		public static Double[] toObject(double[] array) {
			if (array == null) return null;
			Double[] ret = new Double[array.length];
			for (int i = 0; i < ret.length; ++i) ret[i] = array[i];
			return ret;
		}

		/**
		 * プリミティブ型の配列と中身が対応するオブジェクト型の配列を生成します。
		 * @complexity O(array.length)
		 * @param array プリミティブ型の配列
		 * @return オブジェクト型の配列
		 */
		public static Character[] toObject(char[] array) {
			if (array == null) return null;
			Character[] ret = new Character[array.length];
			for (int i = 0; i < ret.length; ++i) ret[i] = array[i];
			return ret;
		}

		/**
		 * オブジェクト型の配列と中身が対応するプリミティブ型の配列を生成します。
		 * @complexity O(array.length)
		 * @param array オブジェクト型の配列
		 * @return プリミティブ型の配列
		 * @throws NullPointerException 配列の要素にnullが含まれていた場合
		 */
		public static boolean[] toPrimitive(Boolean[] array) {
			if (array == null) return null;
			boolean[] ret = new boolean[array.length];
			for (int i = 0; i < ret.length; ++i) ret[i] = array[i];
			return ret;
		}

		/**
		 * オブジェクト型の配列と中身が対応するプリミティブ型の配列を生成します。
		 * @complexity O(array.length)
		 * @param array オブジェクト型の配列
		 * @param valueForNull nullの値に対応させる値
		 * @return プリミティブ型の配列
		 */
		public static boolean[] toPrimitive(Boolean[] array, boolean valueForNull) {
			if (array == null) return null;
			boolean[] ret = new boolean[array.length];
			for (int i = 0; i < ret.length; ++i) ret[i] = array[i] == null ? valueForNull : array[i];
			return ret;
		}

		/**
		 * オブジェクト型の配列と中身が対応するプリミティブ型の配列を生成します。
		 * @complexity O(array.length)
		 * @param array オブジェクト型の配列
		 * @return プリミティブ型の配列
		 * @throws NullPointerException 配列の要素にnullが含まれていた場合
		 */
		public static byte[] toPrimitive(Byte[] array) {
			if (array == null) return null;
			byte[] ret = new byte[array.length];
			for (int i = 0; i < ret.length; ++i) ret[i] = array[i];
			return ret;
		}

		/**
		 * オブジェクト型の配列と中身が対応するプリミティブ型の配列を生成します。
		 * @complexity O(array.length)
		 * @param array オブジェクト型の配列
		 * @param valueForNull nullの値に対応させる値
		 * @return プリミティブ型の配列
		 */
		public static byte[] toPrimitive(Byte[] array, byte valueForNull) {
			if (array == null) return null;
			byte[] ret = new byte[array.length];
			for (int i = 0; i < ret.length; ++i) ret[i] = array[i] == null ? valueForNull : array[i];
			return ret;
		}

		/**
		 * オブジェクト型の配列と中身が対応するプリミティブ型の配列を生成します。
		 * @complexity O(array.length)
		 * @param array オブジェクト型の配列
		 * @return プリミティブ型の配列
		 * @throws NullPointerException 配列の要素にnullが含まれていた場合
		 */
		public static short[] toPrimitive(Short[] array) {
			if (array == null) return null;
			short[] ret = new short[array.length];
			for (int i = 0; i < ret.length; ++i) ret[i] = array[i];
			return ret;
		}

		/**
		 * オブジェクト型の配列と中身が対応するプリミティブ型の配列を生成します。
		 * @complexity O(array.length)
		 * @param array オブジェクト型の配列
		 * @param valueForNull nullの値に対応させる値
		 * @return プリミティブ型の配列
		 */
		public static short[] toPrimitive(Short[] array, short valueForNull) {
			if (array == null) return null;
			short[] ret = new short[array.length];
			for (int i = 0; i < ret.length; ++i) ret[i] = array[i] == null ? valueForNull : array[i];
			return ret;
		}

		/**
		 * オブジェクト型の配列と中身が対応するプリミティブ型の配列を生成します。
		 * @complexity O(array.length)
		 * @param array オブジェクト型の配列
		 * @return プリミティブ型の配列
		 * @throws NullPointerException 配列の要素にnullが含まれていた場合
		 */
		public static int[] toPrimitive(Integer[] array) {
			if (array == null) return null;
			int[] ret = new int[array.length];
			for (int i = 0; i < ret.length; ++i) ret[i] = array[i];
			return ret;
		}

		/**
		 * オブジェクト型の配列と中身が対応するプリミティブ型の配列を生成します。
		 * @complexity O(array.length)
		 * @param array オブジェクト型の配列
		 * @param valueForNull nullの値に対応させる値
		 * @return プリミティブ型の配列
		 */
		public static int[] toPrimitive(Integer[] array, int valueForNull) {
			if (array == null) return null;
			int[] ret = new int[array.length];
			for (int i = 0; i < ret.length; ++i) ret[i] = array[i] == null ? valueForNull : array[i];
			return ret;
		}

		/**
		 * オブジェクト型の配列と中身が対応するプリミティブ型の配列を生成します。
		 * @complexity O(array.length)
		 * @param array オブジェクト型の配列
		 * @return プリミティブ型の配列
		 * @throws NullPointerException 配列の要素にnullが含まれていた場合
		 */
		public static long[] toPrimitive(Long[] array) {
			if (array == null) return null;
			long[] ret = new long[array.length];
			for (int i = 0; i < ret.length; ++i) ret[i] = array[i];
			return ret;
		}

		/**
		 * オブジェクト型の配列と中身が対応するプリミティブ型の配列を生成します。
		 * @complexity O(array.length)
		 * @param array オブジェクト型の配列
		 * @param valueForNull nullの値に対応させる値
		 * @return プリミティブ型の配列
		 */
		public static long[] toPrimitive(Long[] array, long valueForNull) {
			if (array == null) return null;
			long[] ret = new long[array.length];
			for (int i = 0; i < ret.length; ++i) ret[i] = array[i] == null ? valueForNull : array[i];
			return ret;
		}

		/**
		 * オブジェクト型の配列と中身が対応するプリミティブ型の配列を生成します。
		 * @complexity O(array.length)
		 * @param array オブジェクト型の配列
		 * @return プリミティブ型の配列
		 * @throws NullPointerException 配列の要素にnullが含まれていた場合
		 */
		public static float[] toPrimitive(Float[] array) {
			if (array == null) return null;
			float[] ret = new float[array.length];
			for (int i = 0; i < ret.length; ++i) ret[i] = array[i];
			return ret;
		}

		/**
		 * オブジェクト型の配列と中身が対応するプリミティブ型の配列を生成します。
		 * @complexity O(array.length)
		 * @param array オブジェクト型の配列
		 * @param valueForNull nullの値に対応させる値
		 * @return プリミティブ型の配列
		 */
		public static float[] toPrimitive(Float[] array, float valueForNull) {
			if (array == null) return null;
			float[] ret = new float[array.length];
			for (int i = 0; i < ret.length; ++i) ret[i] = array[i] == null ? valueForNull : array[i];
			return ret;
		}

		/**
		 * オブジェクト型の配列と中身が対応するプリミティブ型の配列を生成します。
		 * @complexity O(array.length)
		 * @param array オブジェクト型の配列
		 * @return プリミティブ型の配列
		 * @throws NullPointerException 配列の要素にnullが含まれていた場合
		 */
		public static double[] toPrimitive(Double[] array) {
			if (array == null) return null;
			double[] ret = new double[array.length];
			for (int i = 0; i < ret.length; ++i) ret[i] = array[i];
			return ret;
		}

		/**
		 * オブジェクト型の配列と中身が対応するプリミティブ型の配列を生成します。
		 * @complexity O(array.length)
		 * @param array オブジェクト型の配列
		 * @param valueForNull nullの値に対応させる値
		 * @return プリミティブ型の配列
		 */
		public static double[] toPrimitive(Double[] array, double valueForNull) {
			if (array == null) return null;
			double[] ret = new double[array.length];
			for (int i = 0; i < ret.length; ++i) ret[i] = array[i] == null ? valueForNull : array[i];
			return ret;
		}

		/**
		 * オブジェクト型の配列と中身が対応するプリミティブ型の配列を生成します。
		 * @complexity O(array.length)
		 * @param array オブジェクト型の配列
		 * @return プリミティブ型の配列
		 * @throws NullPointerException 配列の要素にnullが含まれていた場合
		 */
		public static char[] toPrimitive(Character[] array) {
			if (array == null) return null;
			char[] ret = new char[array.length];
			for (int i = 0; i < ret.length; ++i) ret[i] = array[i];
			return ret;
		}

		/**
		 * オブジェクト型の配列と中身が対応するプリミティブ型の配列を生成します。
		 * @complexity O(array.length)
		 * @param array オブジェクト型の配列
		 * @param valueForNull nullの値に対応させる値
		 * @return プリミティブ型の配列
		 */
		public static char[] toPrimitive(Character[] array, char valueForNull) {
			if (array == null) return null;
			char[] ret = new char[array.length];
			for (int i = 0; i < ret.length; ++i) ret[i] = array[i] == null ? valueForNull : array[i];
			return ret;
		}

		/**
		 * 配列の最小要素を返します。
		 * @complexity O(array.length)
		 * @param array 配列
		 * @param comparator 比較関数
		 * @return 配列がnullか要素数が0の場合はnull、それ以外の場合は配列の最小値
		 * @throws NullPointerException comparatorがnullの場合
		 */
		public static <T> T min(T[] array, java.util.Comparator<T> comparator) {
			if (array == null || array.length == 0) return null;
			T min = array[0];
			for (int i = 1; i < array.length; ++i) if (comparator.compare(min, array[i]) > 0) min = array[i];
			return min;
		}

		/**
		 * 配列の最小要素を返します。
		 * @complexity O(array.length)
		 * @param array 配列
		 * @param comparator 比較関数
		 */
		public static <T extends Comparable<T>> T min(T[] array) {
			return min(array, java.util.Comparator.naturalOrder());
		}

		/**
		 * 配列の最小要素を返します。
		 * @complexity O(array.length)
		 * @param array 配列
		 * @param comparator 比較関数
		 */
		public static byte min(byte[] array) {
			byte min = array[0];
			for (int i = 1; i < array.length; ++i) if (min > array[i]) min = array[i];
			return min;
		}

		/**
		 * 配列の最小要素を返します。
		 * @complexity O(array.length)
		 * @param array 配列
		 * @param comparator 比較関数
		 */
		public static short min(short[] array) {
			short min = array[0];
			for (int i = 1; i < array.length; ++i) if (min > array[i]) min = array[i];
			return min;
		}

		/**
		 * 配列の最小要素を返します。
		 * @complexity O(array.length)
		 * @param array 配列
		 * @param comparator 比較関数
		 */
		public static int min(int[] array) {
			int min = array[0];
			for (int i = 1; i < array.length; ++i) if (min > array[i]) min = array[i];
			return min;
		}

		/**
		 * 配列の最小要素を返します。
		 * @complexity O(array.length)
		 * @param array 配列
		 * @param comparator 比較関数
		 */
		public static long min(long[] array) {
			long min = array[0];
			for (int i = 1; i < array.length; ++i) if (min > array[i]) min = array[i];
			return min;
		}

		/**
		 * 配列の最小要素を返します。
		 * @complexity O(array.length)
		 * @param array 配列
		 * @param comparator 比較関数
		 */
		public static float min(float[] array) {
			float min = array[0];
			for (int i = 1; i < array.length; ++i) if (min > array[i]) min = array[i];
			return min;
		}

		/**
		 * 配列の最小要素を返します。
		 * @complexity O(array.length)
		 * @param array 配列
		 * @param comparator 比較関数
		 */
		public static double min(double[] array) {
			double min = array[0];
			for (int i = 1; i < array.length; ++i) if (min > array[i]) min = array[i];
			return min;
		}

		/**
		 * 配列の最小要素を返します。
		 * @complexity O(array.length)
		 * @param array 配列
		 * @param comparator 比較関数
		 * @return 配列がnullか要素数が0の場合はnull、それ以外の場合は配列の最小値
		 * @throws NullPointerException comparatorがnullの場合
		 */
		public static <T> T max(T[] array, java.util.Comparator<T> comparator) {
			if (array == null || array.length == 0) return null;
			T max = array[0];
			for (int i = 1; i < array.length; ++i) if (comparator.compare(max, array[i]) < 0) max = array[i];
			return max;
		}

		/**
		 * 配列の最大要素を返します。
		 * @complexity O(array.length)
		 * @param array 配列
		 * @return 配列がnullか要素数が0の場合はnull、それ以外の場合は配列の最大値
		 */
		public static <T extends Comparable<T>> T max(T[] array) {
			return max(array, java.util.Comparator.naturalOrder());
		}

		/**
		 * 配列の最大要素を返します。
		 * @complexity O(array.length)
		 * @param array 配列
		 * @return 配列がnullか要素数が0の場合はnull、それ以外の場合は配列の最大値
		 * @throws NullPointerException arrayがnullの場合
		 */
		public static byte max(byte[] array) {
			byte max = array[0];
			for (int i = 1; i < array.length; ++i) if (max < array[i]) max = array[i];
			return max;
		}

		/**
		 * 配列の最大要素を返します。
		 * @complexity O(array.length)
		 * @param array 配列
		 * @return 配列がnullか要素数が0の場合はnull、それ以外の場合は配列の最大値
		 * @throws NullPointerException arrayがnullの場合
		 */
		public static short max(short[] array) {
			short max = array[0];
			for (int i = 1; i < array.length; ++i) if (max < array[i]) max = array[i];
			return max;
		}

		/**
		 * 配列の最大要素を返します。
		 * @complexity O(array.length)
		 * @param array 配列
		 * @return 配列がnullか要素数が0の場合はnull、それ以外の場合は配列の最大値
		 * @throws NullPointerException arrayがnullの場合
		 */
		public static int max(int[] array) {
			int max = array[0];
			for (int i = 1; i < array.length; ++i) if (max < array[i]) max = array[i];
			return max;
		}

		/**
		 * 配列の最大要素を返します。
		 * @complexity O(array.length)
		 * @param array 配列
		 * @return 配列がnullか要素数が0の場合はnull、それ以外の場合は配列の最大値
		 * @throws NullPointerException arrayがnullの場合
		 */
		public static long max(long[] array) {
			long max = array[0];
			for (int i = 1; i < array.length; ++i) if (max < array[i]) max = array[i];
			return max;
		}

		/**
		 * 配列の最大要素を返します。
		 * @complexity O(array.length)
		 * @param array 配列
		 * @return 配列がnullか要素数が0の場合はnull、それ以外の場合は配列の最大値
		 * @throws NullPointerException arrayがnullの場合
		 */
		public static float max(float[] array) {
			float max = array[0];
			for (int i = 1; i < array.length; ++i) if (max < array[i]) max = array[i];
			return max;
		}

		/**
		 * 配列の最大要素を返します。
		 * @complexity O(array.length)
		 * @param array 配列
		 * @return 配列がnullか要素数が0の場合はnull、それ以外の場合は配列の最大値
		 * @throws NullPointerException arrayがnullの場合
		 */
		public static double max(double[] array) {
			double max = array[0];
			for (int i = 1; i < array.length; ++i) if (max < array[i]) max = array[i];
			return max;
		}

		/**
		 * 配列のn番目とm番目を入れ替えます。
		 * @complexity O(1)
		 * @param array 配列
		 * @param n 中身をswapするindex
		 * @param m 中身をswapするindex
		 * @throws ArrayIndexOutOfBoundsException n, m < 0 || array.length <= n, mのとき
		 * @throws NullPointerException arrayがnullの場合
		 */
		public static void swap(boolean[] array, int n, int m) {
			boolean swap = array[n];
			array[n] = array[m];
			array[m] = swap;
		}

		/**
		 * 配列のn番目とm番目を入れ替えます。
		 * @complexity O(1)
		 * @param array 配列
		 * @param n 中身をswapするindex
		 * @param m 中身をswapするindex
		 * @throws ArrayIndexOutOfBoundsException n, m < 0 || array.length <= n, mのとき
		 * @throws NullPointerException arrayがnullの場合
		 */
		public static void swap(byte[] array, int n, int m) {
			byte swap = array[n];
			array[n] = array[m];
			array[m] = swap;
		}

		/**
		 * 配列のn番目とm番目を入れ替えます。
		 * @complexity O(1)
		 * @param array 配列
		 * @param n 中身をswapするindex
		 * @param m 中身をswapするindex
		 * @throws ArrayIndexOutOfBoundsException n, m < 0 || array.length <= n, mのとき
		 * @throws NullPointerException arrayがnullの場合
		 */
		public static void swap(short[] array, int n, int m) {
			short swap = array[n];
			array[n] = array[m];
			array[m] = swap;
		}

		/**
		 * 配列のn番目とm番目を入れ替えます。
		 * @complexity O(1)
		 * @param array 配列
		 * @param n 中身をswapするindex
		 * @param m 中身をswapするindex
		 * @throws ArrayIndexOutOfBoundsException n, m < 0 || array.length <= n, mのとき
		 * @throws NullPointerException arrayがnullの場合
		 */
		public static void swap(int[] array, int n, int m) {
			int swap = array[n];
			array[n] = array[m];
			array[m] = swap;
		}

		/**
		 * 配列のn番目とm番目を入れ替えます。
		 * @complexity O(1)
		 * @param array 配列
		 * @param n 中身をswapするindex
		 * @param m 中身をswapするindex
		 * @throws ArrayIndexOutOfBoundsException n, m < 0 || array.length <= n, mのとき
		 * @throws NullPointerException arrayがnullの場合
		 */
		public static void swap(long[] array, int n, int m) {
			long swap = array[n];
			array[n] = array[m];
			array[m] = swap;
		}

		/**
		 * 配列のn番目とm番目を入れ替えます。
		 * @complexity O(1)
		 * @param array 配列
		 * @param n 中身をswapするindex
		 * @param m 中身をswapするindex
		 * @throws ArrayIndexOutOfBoundsException n, m < 0 || array.length <= n, mのとき
		 * @throws NullPointerException arrayがnullの場合
		 */
		public static void swap(float[] array, int n, int m) {
			float swap = array[n];
			array[n] = array[m];
			array[m] = swap;
		}

		/**
		 * 配列のn番目とm番目を入れ替えます。
		 * @complexity O(1)
		 * @param array 配列
		 * @param n 中身をswapするindex
		 * @param m 中身をswapするindex
		 * @throws ArrayIndexOutOfBoundsException n, m < 0 || array.length <= n, mのとき
		 * @throws NullPointerException arrayがnullの場合
		 */
		public static void swap(double[] array, int n, int m) {
			double swap = array[n];
			array[n] = array[m];
			array[m] = swap;
		}

		/**
		 * 配列のn番目とm番目を入れ替えます。
		 * @complexity O(1)
		 * @param array 配列
		 * @param n 中身をswapするindex
		 * @param m 中身をswapするindex
		 * @throws ArrayIndexOutOfBoundsException n, m < 0 || array.length <= n, mのとき
		 * @throws NullPointerException arrayがnullの場合
		 */
		public static void swap(char[] array, int n, int m) {
			char swap = array[n];
			array[n] = array[m];
			array[m] = swap;
		}

		/**
		 * 配列のn番目とm番目を入れ替えます。
		 * @complexity O(1)
		 * @param array 配列
		 * @param n 中身をswapするindex
		 * @param m 中身をswapするindex
		 * @throws ArrayIndexOutOfBoundsException n, m < 0 || array.length <= n, mのとき
		 * @throws NullPointerException arrayがnullの場合
		 */
		public static void swap(Object[] array, int n, int m) {
			Object swap = array[n];
			array[n] = array[m];
			array[m] = swap;
		}

		/**
		 * 配列を辞書式順序で次の配列に書き換えます。そのような配列が無い場合、何もしません。
		 * @complexity O(array.length)
		 * @param array 配列
		 * @return 配列を書き換えたならばtrue
		 */
		public static <T extends Comparable<T>> boolean nextPermutation(T[] array) {
			return nextPermutation(array, java.util.Comparator.naturalOrder());
		}

		/**
		 * 配列を辞書式順序で次の配列に書き換えます。そのような配列が無い場合、何もしません。
		 * @complexity O(array.length)
		 * @param array 配列
		 * @param comparator 比較関数
		 * @return 配列を書き換えたならばtrue
		 * @throws NullPointerException comparatorがnullの場合
		 */
		public static <T> boolean nextPermutation(T[] array, java.util.Comparator<T> comparator) {
			if (array == null) return false;
			for (int change = array.length - 2; change >= 0; --change) {
				if (comparator.compare(array[change], array[change + 1]) < 0) {
					int min = change, max = array.length, halfDiff, mid;
					while ((halfDiff = max - min >> 1) != 0)
						if (comparator.compare(array[change], array[mid = min + halfDiff]) < 0) min = mid;
						else max = mid;
					swap(array, change, min);
					for (min = change + 1, max = array.length - 1; min < max; ++min, --max) swap(array, min, max);
					return true;
				}
			}
			return false;
		}

		/**
		 * 配列を辞書式順序で次の配列に書き換えます。そのような配列が無い場合、何もしません。
		 * @complexity O(array.length)
		 * @param array 配列
		 * @return 配列を書き換えたならばtrue
		 */
		public static boolean nextPermutation(byte[] array) {
			if (array == null) return false;
			for (int change = array.length - 2; change >= 0; --change) {
				if (array[change] < array[change + 1]) {
					int min = change, max = array.length, halfDiff, mid;
					while ((halfDiff = max - min >> 1) != 0) if (array[change] < array[mid = min + halfDiff]) min = mid;
					else max = mid;
					swap(array, change, min);
					for (min = change + 1, max = array.length - 1; min < max; ++min, --max) swap(array, min, max);
					return true;
				}
			}
			return false;
		}

		/**
		 * 配列を辞書式順序で次の配列に書き換えます。そのような配列が無い場合、何もしません。
		 * @complexity O(array.length)
		 * @param array 配列
		 * @return 配列を書き換えたならばtrue
		 */
		public static boolean nextPermutation(short[] array) {
			if (array == null) return false;
			for (int change = array.length - 2; change >= 0; --change) {
				if (array[change] < array[change + 1]) {
					int min = change, max = array.length, halfDiff, mid;
					while ((halfDiff = max - min >> 1) != 0) if (array[change] < array[mid = min + halfDiff]) min = mid;
					else max = mid;
					swap(array, change, min);
					for (min = change + 1, max = array.length - 1; min < max; ++min, --max) swap(array, min, max);
					return true;
				}
			}
			return false;
		}

		/**
		 * 配列を辞書式順序で次の配列に書き換えます。そのような配列が無い場合、何もしません。
		 * @complexity O(array.length)
		 * @param array 配列
		 * @return 配列を書き換えたならばtrue
		 */
		public static boolean nextPermutation(int[] array) {
			if (array == null) return false;
			for (int change = array.length - 2; change >= 0; --change) {
				if (array[change] < array[change + 1]) {
					int min = change, max = array.length, halfDiff, mid;
					while ((halfDiff = max - min >> 1) != 0) if (array[change] < array[mid = min + halfDiff]) min = mid;
					else max = mid;
					swap(array, change, min);
					for (min = change + 1, max = array.length - 1; min < max; ++min, --max) swap(array, min, max);
					return true;
				}
			}
			return false;
		}

		/**
		 * 配列を辞書式順序で次の配列に書き換えます。そのような配列が無い場合、何もしません。
		 * @complexity O(array.length)
		 * @param array 配列
		 * @return 配列を書き換えたならばtrue
		 */
		public static boolean nextPermutation(long[] array) {
			if (array == null) return false;
			for (int change = array.length - 2; change >= 0; --change) {
				if (array[change] < array[change + 1]) {
					int min = change, max = array.length, halfDiff, mid;
					while ((halfDiff = max - min >> 1) != 0) if (array[change] < array[mid = min + halfDiff]) min = mid;
					else max = mid;
					swap(array, change, min);
					for (min = change + 1, max = array.length - 1; min < max; ++min, --max) swap(array, min, max);
					return true;
				}
			}
			return false;
		}

		/**
		 * 配列を辞書式順序で次の配列に書き換えます。そのような配列が無い場合、何もしません。
		 * @complexity O(array.length)
		 * @param array 配列
		 * @return 配列を書き換えたならばtrue
		 */
		public static boolean nextPermutation(float[] array) {
			if (array == null) return false;
			for (int change = array.length - 2; change >= 0; --change) {
				if (array[change] < array[change + 1]) {
					int min = change, max = array.length, halfDiff, mid;
					while ((halfDiff = max - min >> 1) != 0) if (array[change] < array[mid = min + halfDiff]) min = mid;
					else max = mid;
					swap(array, change, min);
					for (min = change + 1, max = array.length - 1; min < max; ++min, --max) swap(array, min, max);
					return true;
				}
			}
			return false;
		}

		/**
		 * 配列を辞書式順序で次の配列に書き換えます。そのような配列が無い場合、何もしません。
		 * @complexity O(array.length)
		 * @param array 配列
		 * @return 配列を書き換えたならばtrue
		 */
		public static boolean nextPermutation(double[] array) {
			if (array == null) return false;
			for (int change = array.length - 2; change >= 0; --change) {
				if (array[change] < array[change + 1]) {
					int min = change, max = array.length, halfDiff, mid;
					while ((halfDiff = max - min >> 1) != 0) if (array[change] < array[mid = min + halfDiff]) min = mid;
					else max = mid;
					swap(array, change, min);
					for (min = change + 1, max = array.length - 1; min < max; ++min, --max) swap(array, min, max);
					return true;
				}
			}
			return false;
		}

		/**
		 * 配列を辞書式順序で次の配列に書き換えます。そのような配列が無い場合、何もしません。
		 * @complexity O(array.length)
		 * @param array 配列
		 * @return 配列を書き換えたならばtrue
		 */
		public static boolean nextPermutation(char[] array) {
			if (array == null) return false;
			for (int change = array.length - 2; change >= 0; --change) {
				if (array[change] < array[change + 1]) {
					int min = change, max = array.length, halfDiff, mid;
					while ((halfDiff = max - min >> 1) != 0) if (array[change] < array[mid = min + halfDiff]) min = mid;
					else max = mid;
					swap(array, change, min);
					for (min = change + 1, max = array.length - 1; min < max; ++min, --max) swap(array, min, max);
					return true;
				}
			}
			return false;
		}

		/**
		 * 配列を辞書式順序で前の配列に書き換えます。そのような配列が無い場合、何もしません。
		 * @complexity O(array.length)
		 * @param array 配列
		 * @return 配列を書き換えたならばtrue
		 */
		public static <T extends Comparable<T>> boolean prevPermutation(T[] array) {
			return prevPermutation(array, java.util.Comparator.naturalOrder());
		}

		/**
		 * 配列を辞書式順序で前の配列に書き換えます。そのような配列が無い場合、何もしません。
		 * @complexity O(array.length)
		 * @param array 配列
		 * @param comparator 比較関数
		 * @return 配列を書き換えたならばtrue
		 * @throws NullPointerException comparatorがnullの場合
		 */
		public static <T> boolean prevPermutation(T[] array, java.util.Comparator<T> comparator) {
			if (array == null) return false;
			for (int change = array.length - 2; change >= 0; --change) {
				if (comparator.compare(array[change], array[change + 1]) > 0) {
					int min = change, max = array.length, halfDiff, mid;
					while ((halfDiff = max - min >> 1) != 0)
						if (comparator.compare(array[change], array[mid = min + halfDiff]) > 0) min = mid;
						else max = mid;
					swap(array, change, min);
					for (min = change + 1, max = array.length - 1; min < max; ++min, --max) swap(array, min, max);
					return true;
				}
			}
			return false;
		}

		/**
		 * 配列を辞書式順序で前の配列に書き換えます。そのような配列が無い場合、何もしません。
		 * @complexity O(array.length)
		 * @param array 配列
		 * @return 配列を書き換えたならばtrue
		 */
		public static boolean prevPermutation(byte[] array) {
			if (array == null) return false;
			for (int change = array.length - 2; change >= 0; --change) {
				if (array[change] > array[change + 1]) {
					int min = change, max = array.length, halfDiff, mid;
					while ((halfDiff = max - min >> 1) != 0) if (array[change] > array[mid = min + halfDiff]) min = mid;
					else max = mid;
					swap(array, change, min);
					for (min = change + 1, max = array.length - 1; min < max; ++min, --max) swap(array, min, max);
					return true;
				}
			}
			return false;
		}

		/**
		 * 配列を辞書式順序で前の配列に書き換えます。そのような配列が無い場合、何もしません。
		 * @complexity O(array.length)
		 * @param array 配列
		 * @return 配列を書き換えたならばtrue
		 */
		public static boolean prevPermutation(short[] array) {
			if (array == null) return false;
			for (int change = array.length - 2; change >= 0; --change) {
				if (array[change] > array[change + 1]) {
					int min = change, max = array.length, halfDiff, mid;
					while ((halfDiff = max - min >> 1) != 0) if (array[change] > array[mid = min + halfDiff]) min = mid;
					else max = mid;
					swap(array, change, min);
					for (min = change + 1, max = array.length - 1; min < max; ++min, --max) swap(array, min, max);
					return true;
				}
			}
			return false;
		}

		/**
		 * 配列を辞書式順序で前の配列に書き換えます。そのような配列が無い場合、何もしません。
		 * @complexity O(array.length)
		 * @param array 配列
		 * @return 配列を書き換えたならばtrue
		 */
		public static boolean prevPermutation(int[] array) {
			if (array == null) return false;
			for (int change = array.length - 2; change >= 0; --change) {
				if (array[change] > array[change + 1]) {
					int min = change, max = array.length, halfDiff, mid;
					while ((halfDiff = max - min >> 1) != 0) if (array[change] > array[mid = min + halfDiff]) min = mid;
					else max = mid;
					swap(array, change, min);
					for (min = change + 1, max = array.length - 1; min < max; ++min, --max) swap(array, min, max);
					return true;
				}
			}
			return false;
		}

		/**
		 * 配列を辞書式順序で前の配列に書き換えます。そのような配列が無い場合、何もしません。
		 * @complexity O(array.length)
		 * @param array 配列
		 * @return 配列を書き換えたならばtrue
		 */
		public static boolean prevPermutation(long[] array) {
			if (array == null) return false;
			for (int change = array.length - 2; change >= 0; --change) {
				if (array[change] > array[change + 1]) {
					int min = change, max = array.length, halfDiff, mid;
					while ((halfDiff = max - min >> 1) != 0) if (array[change] > array[mid = min + halfDiff]) min = mid;
					else max = mid;
					swap(array, change, min);
					for (min = change + 1, max = array.length - 1; min < max; ++min, --max) swap(array, min, max);
					return true;
				}
			}
			return false;
		}

		/**
		 * 配列を辞書式順序で前の配列に書き換えます。そのような配列が無い場合、何もしません。
		 * @complexity O(array.length)
		 * @param array 配列
		 * @return 配列を書き換えたならばtrue
		 */
		public static boolean prevPermutation(float[] array) {
			if (array == null) return false;
			for (int change = array.length - 2; change >= 0; --change) {
				if (array[change] > array[change + 1]) {
					int min = change, max = array.length, halfDiff, mid;
					while ((halfDiff = max - min >> 1) != 0) if (array[change] > array[mid = min + halfDiff]) min = mid;
					else max = mid;
					swap(array, change, min);
					for (min = change + 1, max = array.length - 1; min < max; ++min, --max) swap(array, min, max);
					return true;
				}
			}
			return false;
		}

		/**
		 * 配列を辞書式順序で前の配列に書き換えます。そのような配列が無い場合、何もしません。
		 * @complexity O(array.length)
		 * @param array 配列
		 * @return 配列を書き換えたならばtrue
		 */
		public static boolean prevPermutation(double[] array) {
			if (array == null) return false;
			for (int change = array.length - 2; change >= 0; --change) {
				if (array[change] > array[change + 1]) {
					int min = change, max = array.length, halfDiff, mid;
					while ((halfDiff = max - min >> 1) != 0) if (array[change] > array[mid = min + halfDiff]) min = mid;
					else max = mid;
					swap(array, change, min);
					for (min = change + 1, max = array.length - 1; min < max; ++min, --max) swap(array, min, max);
					return true;
				}
			}
			return false;
		}

		/**
		 * 配列を辞書式順序で前の配列に書き換えます。そのような配列が無い場合、何もしません。
		 * @complexity O(array.length)
		 * @param array 配列
		 * @return 配列を書き換えたならばtrue
		 */
		public static boolean prevPermutation(char[] array) {
			if (array == null) return false;
			for (int change = array.length - 2; change >= 0; --change) {
				if (array[change] > array[change + 1]) {
					int min = change, max = array.length, halfDiff, mid;
					while ((halfDiff = max - min >> 1) != 0) if (array[change] > array[mid = min + halfDiff]) min = mid;
					else max = mid;
					swap(array, change, min);
					for (min = change + 1, max = array.length - 1; min < max; ++min, --max) swap(array, min, max);
					return true;
				}
			}
			return false;
		}

		/**
		 * 配列の各要素を与えられた関数に適用した配列を生成します。
		 * @complexity O(array.length)
		 * @param array 配列
		 * @param map 各要素に適用する関数
		 * @return 配列の各要素にmapを適用した配列
		 */
		public static <T> T[] map(T[] array, java.util.function.UnaryOperator<T> map) {
			T[] ret = java.util.Arrays.copyOf(array, array.length);
			for (int i = 0; i < ret.length; ++i) ret[i] = map.apply(ret[i]);
			return ret;
		}

		/**
		 * 配列の各要素を与えられた関数に適用した配列を生成します。
		 * @complexity O(array.length)
		 * @param array 配列
		 * @param map 各要素に適用する関数
		 * @return 配列の各要素にmapを適用した配列
		 */
		public static int[] map(int[] array, java.util.function.IntUnaryOperator map) {
			int[] ret = java.util.Arrays.copyOf(array, array.length);
			for (int i = 0; i < ret.length; ++i) ret[i] = map.applyAsInt(ret[i]);
			return ret;
		}

		/**
		 * 配列の各要素を与えられた関数に適用した配列を生成します。
		 * @complexity O(array.length)
		 * @param array 配列
		 * @param map 各要素に適用する関数
		 * @return 配列の各要素にmapを適用した配列
		 */
		public static long[] map(long[] array, java.util.function.LongUnaryOperator map) {
			long[] ret = java.util.Arrays.copyOf(array, array.length);
			for (int i = 0; i < ret.length; ++i) ret[i] = map.applyAsLong(ret[i]);
			return ret;
		}

		/**
		 * 配列の各要素を与えられた関数に適用した配列を生成します。
		 * @complexity O(array.length)
		 * @param array 配列
		 * @param map 各要素に適用する関数
		 * @return 配列の各要素にmapを適用した配列
		 */
		public static double[] map(double[] array, java.util.function.DoubleUnaryOperator map) {
			double[] ret = java.util.Arrays.copyOf(array, array.length);
			for (int i = 0; i < ret.length; ++i) ret[i] = map.applyAsDouble(ret[i]);
			return ret;
		}

		/**
		 * 配列の各要素を与えられた関数に適用した配列を生成します。
		 * @complexity O(array.length)
		 * @param array 配列
		 * @param map 各要素に適用する関数
		 * @param generator 新しい配列を生成するための関数、U::newを引数に取る
		 * @return 配列の各要素にmapを適用した配列
		 */
		public static <T, U> U[] map(T[] array, java.util.function.Function<T, U> map,
				java.util.function.IntFunction<U[]> generator) {
			U[] ret = generator.apply(array.length);
			for (int i = 0; i < ret.length; ++i) ret[i] = map.apply(array[i]);
			return ret;
		}

		/**
		 * 配列を昇順にソートします。
		 * @complexity O(array.length)
		 * @param array 配列
		 */
		public static void sort(final byte[] array) {
			if (array.length < 128) {
				for (int i = 0, j; i < array.length; ++i) {
					byte tmp = array[i], tmp2;
					for (j = i; j > 0 && (tmp2 = array[j - 1]) > tmp; --j) array[j] = tmp2;
					array[j] = tmp;
				}
				return;
			}
			int[] count = new int[256];
			for (byte i : array) ++count[i & 0xff];
			for (int i = 0, j = 0; j < count.length; ++j) java.util.Arrays.fill(array, i, i += count[j], (byte) j);
		}

		/**
		 * 配列を昇順にソートします。
		 * @complexity O(toIndex-fromIndex)
		 * @param array 配列
		 */
		public static void sort(final byte[] array, int fromIndex, int toIndex) {
			if (toIndex - fromIndex < 128) {
				for (int i = fromIndex, j; i < toIndex; ++i) {
					byte tmp = array[i], tmp2;
					for (j = i; j > fromIndex && (tmp2 = array[j - 1]) > tmp; --j) array[j] = tmp2;
					array[j] = tmp;
				}
				return;
			}
			int[] count = new int[256];
			for (int i = fromIndex; i < toIndex; ++i) ++count[array[i] & 0xff];
			for (int i = fromIndex, j = 0; j < count.length; ++j)
				java.util.Arrays.fill(array, i, i += count[j], (byte) j);
		}

		/**
		 * 配列を昇順にソートします。
		 * @complexity O(range.getDistance())
		 * @param array 配列
		 */
		public static void sort(final byte[] array, IntRange range) {
			sort(array, range.getClosedLower(), range.getOpenUpper());
		}

		/**
		 * 配列を昇順にソートします。
		 * @complexity Nを配列長として O(N log N)
		 * @param array 配列
		 */
		public static void sort(final short[] array) {
			if (array.length < 1024) java.util.Arrays.sort(array);
			else sort(array, 0, array.length, 0, new short[array.length]);
		}

		/**
		 * 配列を昇順にソートします。
		 * @complexity N=toIndex-fromIndex として O(N log N)
		 * @param array 元の配列
		 * @param fromIndex ソートする左閉区間
		 * @param toIndex ソートする右開区間
		 */
		public static void sort(final short[] array, int fromIndex, int toIndex) {
			if (toIndex - fromIndex < 1024) java.util.Arrays.sort(array, fromIndex, toIndex);
			else sort(array, fromIndex, toIndex, 0, new short[array.length]);
		}

		/**
		 * 配列を昇順にソートします。
		 * @complexity N=range.getDistance() として O(N log N)
		 * @param array 元の配列
		 * @param fromIndex ソートする左閉区間
		 * @param toIndex ソートする右開区間
		 */
		public static void sort(final short[] array, IntRange range) {
			sort(array, range.getClosedLower(), range.getOpenUpper());
		}

		private static final void sort(short[] a, final int from, final int to, final int l, final short[] bucket) {
			final int BUCKET_SIZE = 256;
			final int SHORT_RECURSION = 2;
			final int MASK = 0xff;
			final int shift = l << 3;
			final int[] cnt = new int[BUCKET_SIZE + 1];
			final int[] put = new int[BUCKET_SIZE];
			for (int i = from; i < to; i++) ++cnt[(a[i] >>> shift & MASK) + 1];
			for (int i = 0; i < BUCKET_SIZE; i++) cnt[i + 1] += cnt[i];
			for (int i = from; i < to; i++) {
				int bi = a[i] >>> shift & MASK;
				bucket[cnt[bi] + put[bi]++] = a[i];
			}
			for (int i = BUCKET_SIZE - 1, idx = from; i >= 0; i--) {
				int begin = cnt[i];
				int len = cnt[i + 1] - begin;
				System.arraycopy(bucket, begin, a, idx, len);
				idx += len;
			}
			final int nxtL = l + 1;
			if (nxtL < SHORT_RECURSION) {
				sort(a, from, to, nxtL, bucket);
				if (l == 0) {
					int lft, rgt;
					lft = from - 1;
					rgt = to;
					while (rgt - lft > 1) {
						int mid = lft + rgt >> 1;
						if (a[mid] < 0) lft = mid;
						else rgt = mid;
					}
					reverse(a, from, rgt);
					reverse(a, rgt, to);
				}
			}
		}

		/**
		 * 配列を昇順にソートします。
		 * @complexity Nを配列長として O(N log N)
		 * @param array 配列
		 */
		public static void sort(final int[] array) {
			if (array.length < 1024) java.util.Arrays.sort(array);
			else sort(array, 0, array.length, 0, new int[array.length]);
		}

		/**
		 * 配列を昇順にソートします。
		 * @complexity N=toIndex-fromIndex として O(N log N)
		 * @param array 元の配列
		 * @param fromIndex ソートする左閉区間
		 * @param toIndex ソートする右開区間
		 */
		public static void sort(final int[] array, int fromIndex, int toIndex) {
			if (toIndex - fromIndex < 1024) java.util.Arrays.sort(array, fromIndex, toIndex);
			else sort(array, fromIndex, toIndex, 0, new int[array.length]);
		}

		/**
		 * 配列を昇順にソートします。
		 * @complexity N=range.getDistance() として O(N log N)
		 * @param array 元の配列
		 * @param fromIndex ソートする左閉区間
		 * @param toIndex ソートする右開区間
		 */
		public static void sort(final int[] array, IntRange range) {
			sort(array, range.getClosedLower(), range.getOpenUpper());
		}

		private static final void sort(int[] a, final int from, final int to, final int l, final int[] bucket) {
			final int BUCKET_SIZE = 256;
			final int INT_RECURSION = 4;
			final int MASK = 0xff;
			final int shift = l << 3;
			final int[] cnt = new int[BUCKET_SIZE + 1];
			final int[] put = new int[BUCKET_SIZE];
			for (int i = from; i < to; i++) ++cnt[(a[i] >>> shift & MASK) + 1];
			for (int i = 0; i < BUCKET_SIZE; i++) cnt[i + 1] += cnt[i];
			for (int i = from; i < to; i++) {
				int bi = a[i] >>> shift & MASK;
				bucket[cnt[bi] + put[bi]++] = a[i];
			}
			for (int i = BUCKET_SIZE - 1, idx = from; i >= 0; i--) {
				int begin = cnt[i];
				int len = cnt[i + 1] - begin;
				System.arraycopy(bucket, begin, a, idx, len);
				idx += len;
			}
			final int nxtL = l + 1;
			if (nxtL < INT_RECURSION) {
				sort(a, from, to, nxtL, bucket);
				if (l == 0) {
					int lft, rgt;
					lft = from - 1;
					rgt = to;
					while (rgt - lft > 1) {
						int mid = lft + rgt >> 1;
						if (a[mid] < 0) lft = mid;
						else rgt = mid;
					}
					reverse(a, from, rgt);
					reverse(a, rgt, to);
				}
			}
		}

		/**
		 * 配列を昇順にソートします。
		 * @complexity Nを配列長として O(N log N)
		 * @param array 配列
		 */
		public static void sort(final long[] array) {
			if (array.length < 1024) java.util.Arrays.sort(array);
			else sort(array, 0, array.length, 0, new long[array.length]);
		}

		/**
		 * 配列を昇順にソートします。
		 * @complexity N=toIndex-fromIndex として O(N log N)
		 * @param array 元の配列
		 * @param fromIndex ソートする左閉区間
		 * @param toIndex ソートする右開区間
		 */
		public static void sort(final long[] array, int fromIndex, int toIndex) {
			if (toIndex - fromIndex < 1024) java.util.Arrays.sort(array, fromIndex, toIndex);
			else sort(array, fromIndex, toIndex, 0, new long[array.length]);
		}

		/**
		 * 配列を昇順にソートします。
		 * @complexity N=range.getDistance() として O(N log N)
		 * @param array 元の配列
		 * @param fromIndex ソートする左閉区間
		 * @param toIndex ソートする右開区間
		 */
		public static void sort(final long[] array, IntRange range) {
			sort(array, range.getClosedLower(), range.getOpenUpper());
		}

		private static final void sort(long[] a, final int from, final int to, final int l, final long[] bucket) {
			final int BUCKET_SIZE = 256;
			final int LONG_RECURSION = 8;
			final int MASK = 0xff;
			final int shift = l << 3;
			final int[] cnt = new int[BUCKET_SIZE + 1];
			final int[] put = new int[BUCKET_SIZE];
			for (int i = from; i < to; i++) ++cnt[(int) ((a[i] >>> shift & MASK) + 1)];
			for (int i = 0; i < BUCKET_SIZE; i++) cnt[i + 1] += cnt[i];
			for (int i = from; i < to; i++) {
				int bi = (int) (a[i] >>> shift & MASK);
				bucket[cnt[bi] + put[bi]++] = a[i];
			}
			for (int i = BUCKET_SIZE - 1, idx = from; i >= 0; i--) {
				int begin = cnt[i];
				int len = cnt[i + 1] - begin;
				System.arraycopy(bucket, begin, a, idx, len);
				idx += len;
			}
			final int nxtL = l + 1;
			if (nxtL < LONG_RECURSION) {
				sort(a, from, to, nxtL, bucket);
				if (l == 0) {
					int lft, rgt;
					lft = from - 1;
					rgt = to;
					while (rgt - lft > 1) {
						int mid = lft + rgt >> 1;
						if (a[mid] < 0) lft = mid;
						else rgt = mid;
					}
					reverse(a, from, rgt);
					reverse(a, rgt, to);
				}
			}
		}

		/**
		 * 座標圧縮した配列を返します。
		 * この関数によって返される配列をretとしたとき、retは次の条件を満たします。
		 * <ul>
		 * <li>任意の正整数nに対し、contains(ret, n)がtrueならcontains(ret, n-1)もtrue</li>
		 * <li>0≦i, j&lt;nを満たすi, jに対し、array[i]&lt;array[j]ならret[i]&lt;ret[j]</li>
		 * <li>0≦i, j&lt;nを満たすi, jに対し、array[i]==array[j]ならret[i]==ret[j]</li>
		 * </ul>
		 * @complexity Nを配列長として O(N log N)
		 * @param array 座標圧縮を行う配列
		 * @return arrayを座標圧縮した配列
		 */
		public static int[] compress(int[] array) {
			int[] ret = new int[array.length];
			int[] copy = java.util.Arrays.copyOf(array, array.length);
			sort(copy);
			int len = 1;
			for (int j = 1; j < array.length; ++j) {
				if (copy[len - 1] != copy[j]) copy[len++] = copy[j];
			}
			for (int i = 0; i < array.length; ++i) {
				int min = 0, max = len;
				int comp = array[i];
				while (max - min > 1) {
					int mid = min + max >> 1;
					if (copy[mid] <= comp) min = mid;
					else max = mid;
				}
				ret[i] = min;
			}
			return ret;
		}

		/**
		 * 座標圧縮した配列を返します。
		 * この関数によって返される配列をretとしたとき、retは次の条件を満たします。
		 * <ul>
		 * <li>任意の正整数nに対し、contains(ret, n)がtrueならcontains(ret, n-1)もtrue</li>
		 * <li>0≦i, j&lt;nを満たすi, jに対し、array[i]&lt;array[j]ならret[i]&lt;ret[j]</li>
		 * <li>0≦i, j&lt;nを満たすi, jに対し、array[i]==array[j]ならret[i]==ret[j]</li>
		 * </ul>
		 * @complexity Nを配列長として O(N log N)
		 * @param array 座標圧縮を行う配列
		 * @return arrayを座標圧縮した配列
		 */
		public static int[] compress(long[] array) {
			int[] ret = new int[array.length];
			long[] copy = java.util.Arrays.copyOf(array, array.length);
			sort(copy);
			int len = 1;
			for (int j = 1; j < array.length; ++j) {
				if (copy[len - 1] != copy[j]) copy[len++] = copy[j];
			}
			for (int i = 0; i < array.length; ++i) {
				int min = 0, max = len;
				long comp = array[i];
				while (max - min > 1) {
					int mid = min + max >> 1;
					if (copy[mid] <= comp) min = mid;
					else max = mid;
				}
				ret[i] = min;
			}
			return ret;
		}

		/**
		 * 座標圧縮した配列を返します。
		 * この関数によって返される配列をretとしたとき、retは次の条件を満たします。
		 * <ul>
		 * <li>任意の正整数nに対し、contains(ret, n)がtrueならcontains(ret, n-1)もtrue</li>
		 * <li>0≦i, j&lt;nを満たすi, jに対し、array[i]&lt;array[j]ならret[i]&lt;ret[j]</li>
		 * <li>0≦i, j&lt;nを満たすi, jに対し、array[i]==array[j]ならret[i]==ret[j]</li>
		 * </ul>
		 * @complexity Nを配列長として O(N log N)
		 * @param array 座標圧縮を行う配列
		 * @return arrayを座標圧縮した配列
		 */
		public static <T extends Comparable<T>> int[] compress(T[] array) {
			int[] ret = new int[array.length];
			T[] copy = java.util.Arrays.copyOf(array, array.length);
			java.util.Arrays.sort(copy);
			int len = 1;
			for (int j = 1; j < array.length; ++j) {
				if (copy[len - 1] != copy[j]) copy[len++] = copy[j];
			}
			for (int i = 0; i < array.length; ++i) {
				int min = 0, max = len;
				T comp = array[i];
				while (max - min > 1) {
					int mid = min + max >> 1;
					if (copy[mid].compareTo(comp) <= 0) min = mid;
					else max = mid;
				}
				ret[i] = min;
			}
			return ret;
		}

		/**
		 * 座標圧縮した配列を返します。
		 * この関数によって返される配列をretとしたとき、retは次の条件を満たします。
		 * <ul>
		 * <li>任意の正整数nに対し、contains(ret, n)がtrueならcontains(ret, n-1)もtrue</li>
		 * <li>0≦i, j&lt;nを満たすi, jに対し、array[i]&lt;array[j]ならret[i]&lt;ret[j]</li>
		 * <li>0≦i, j&lt;nを満たすi, jに対し、array[i]==array[j]ならret[i]==ret[j]</li>
		 * </ul>
		 * @complexity Nを配列長として O(N log N)
		 * @param array 座標圧縮を行う配列
		 * @param comparator 比較関数
		 * @return arrayを座標圧縮した配列
		 */
		public static <T> int[] compress(T[] array, java.util.Comparator<T> comparator) {
			int[] ret = new int[array.length];
			T[] copy = java.util.Arrays.copyOf(array, array.length);
			java.util.Arrays.sort(copy, comparator);
			int len = 1;
			for (int j = 1; j < array.length; ++j) {
				if (!copy[len - 1].equals(copy[j])) copy[len++] = copy[j];
			}
			for (int i = 0; i < array.length; ++i) {
				int min = 0, max = len;
				T comp = array[i];
				while (max - min > 1) {
					int mid = min + max >> 1;
					if (comparator.compare(copy[mid], comp) <= 0) min = mid;
					else max = mid;
				}
				ret[i] = min;
			}
			return ret;
		}

		/**
		 * 座標圧縮した配列を返します。
		 * この関数によって返される配列をretとしたとき、retは次の条件を満たします。
		 * <ul>
		 * <li>任意の正整数nに対し、contains(ret, n)がtrueならcontains(ret, n-1)もtrue</li>
		 * <li>0≦i, j&lt;nを満たすi, jに対し、list[i]&lt;list[j]ならret[i]&lt;ret[j]</li>
		 * <li>0≦i, j&lt;nを満たすi, jに対し、list[i]==list[j]ならret[i]==ret[j]</li>
		 * </ul>
		 * @complexity Nをリスト長として O(N log N)
		 * @param list 座標圧縮を行うリスト
		 * @return listを座標圧縮した配列
		 * @throws NullPointerException listがnullの場合
		 */
		public static <T extends Comparable<T>> int[] compress(java.util.List<T> list) {
			int size = list.size();
			int[] ret = new int[size];
			java.util.ArrayList<T> copy = new java.util.ArrayList<>(list);
			copy.sort(java.util.Comparator.naturalOrder());
			int len = 1;
			for (int j = 1; j < size; ++j) {
				if (!copy.get(len - 1).equals(copy.get(j))) copy.set(len++, copy.get(j));
			}
			java.util.Iterator<T> iter = list.iterator();
			for (int i = 0; i < size; ++i) {
				int min = 0, max = len;
				T comp = iter.next();
				while (max - min > 1) {
					int mid = min + max >> 1;
					if (copy.get(mid).compareTo(comp) <= 0) min = mid;
					else max = mid;
				}
				ret[i] = min;
			}
			return ret;
		}

		/**
		 * 座標圧縮した配列を返します。
		 * この関数によって返される配列をretとしたとき、retは次の条件を満たします。
		 * <ul>
		 * <li>任意の正整数nに対し、contains(ret, n)がtrueならcontains(ret, n-1)もtrue</li>
		 * <li>0≦i, j&lt;nを満たすi, jに対し、list[i]&lt;list[j]ならret[i]&lt;ret[j]</li>
		 * <li>0≦i, j&lt;nを満たすi, jに対し、list[i]==list[j]ならret[i]==ret[j]</li>
		 * </ul>
		 * @complexity Nをリスト長として O(N log N)
		 * @param list 座標圧縮を行うリスト
		 * @param comparator 比較関数
		 * @return listを座標圧縮した配列
		 */
		public static <T> int[] compress(java.util.List<T> list, java.util.Comparator<T> comparator) {
			int[] ret = new int[list.size()];
			java.util.ArrayList<T> copy = new java.util.ArrayList<>(list);
			copy.sort(comparator);
			int[] bit = new int[list.size() + 1];
			java.util.Iterator<T> iter = list.iterator();
			for (int i = 0; i < list.size(); ++i) {
				int min = 0, max = list.size();
				T comp = iter.next();
				while (max - min > 1) {
					int mid = min + max >> 1;
					if (comparator.compare(copy.get(mid), comp) <= 0) min = mid;
					else max = mid;
				}
				for (int j = max; j != 0; j -= j & -j) ret[i] += bit[j];
				for (int j = max; j < bit.length; j += j & -j) ++bit[j];
			}
			return ret;
		}

		/**
		 * 配列の転倒数を求めます。すなわち、i<jかつarray[i]>array[j]となる(i, j)の個数を求めます。
		 * @complexity Nを配列長として O(N log N)
		 * @param array 配列
		 * @return 転倒数
		 */
		public static long inversionNumber(int[] array) {
			if (array == null) return 0;
			int[] copy = java.util.Arrays.copyOf(array, array.length);
			sort(copy);
			int[] bit = new int[array.length + 1];
			long ans = (long) array.length * (array.length - 1) >> 1;
			for (int i = 0; i < array.length; ++i) {
				int min = 0, max = array.length;
				int comp = array[i];
				while (max - min > 1) {
					int mid = min + max >> 1;
					if (copy[mid] <= comp) min = mid;
					else max = mid;
				}
				for (int j = max; j != 0; j -= j & -j) ans -= bit[j];
				for (int j = max; j < bit.length; j += j & -j) ++bit[j];
			}
			return ans;
		}

		/**
		 * 配列の転倒数を求めます。すなわち、i<jかつarray[i]>array[j]となる(i, j)の個数を求めます。
		 * @complexity Nを配列長として O(N log N)
		 * @param array 配列
		 * @return 転倒数
		 */
		public static long inversionNumber(long[] array) {
			if (array == null) return 0;
			long[] copy = java.util.Arrays.copyOf(array, array.length);
			sort(copy);
			int[] bit = new int[array.length + 1];
			long ans = (long) array.length * (array.length - 1) >> 1;
			for (int i = 0; i < array.length; ++i) {
				int min = 0, max = array.length;
				long comp = array[i];
				while (max - min > 1) {
					int mid = min + max >> 1;
					if (copy[mid] <= comp) min = mid;
					else max = mid;
				}
				for (int j = max; j != 0; j -= j & -j) ans -= bit[j];
				for (int j = max; j < bit.length; j += j & -j) ++bit[j];
			}
			return ans;
		}

		/**
		 * 配列の転倒数を求めます。すなわち、i<jかつarray[i]>array[j]となる(i, j)の個数を求めます。
		 * @complexity Nを配列長として O(N log N)
		 * @param array 配列
		 * @return 転倒数
		 */
		public static long inversionNumber(char[] array) {
			if (array == null) return 0;
			int[] a = new int[array.length];
			for (int i = 0;i < array.length;++ i) a[i] = array[i];
			return inversionNumber(a);
		}

		/**
		 * 配列の転倒数を求めます。すなわち、i<jかつarray[i]>array[j]となる(i, j)の個数を求めます。
		 * @complexity Nを配列長として O(N log N)
		 * @param array 配列
		 * @return 転倒数
		 */
		public static long inversionNumber(String array) {
			if (array == null) return 0;
			return inversionNumber(array.toCharArray());
		}

		/**
		 * 2つの配列の転倒距離を求めます。つまり、配列srcの隣接する2要素をswapして配列destと一致させるまでのswap回数の最小値を求めます。
		 * @complexity N=src.length, M=dest.lengthとしてO((N+M)log(N+M))
		 * @param src 配列
		 * @param dest 配列
		 * @return srcとdestの転倒距離、ただしsrcを隣接swapすることでdestが構築できない場合は-1
		 */
		public static long inversionDistance(int[] src, int[] dest) {
			if (src == null || dest == null) return src == null && dest == null ? 0 : -1;
			int[] copySrc = java.util.Arrays.copyOf(src, src.length),
					copyDest = java.util.Arrays.copyOf(dest, dest.length);
			sort(copySrc);
			sort(copyDest);
			if (!java.util.Arrays.equals(copySrc, copyDest)) return -1;
			int[] key = new int[dest.length];
			for (int i = 0; i < dest.length; ++i) {
				int min = -1, max = dest.length;
				int comp = dest[i];
				while (max - min > 1) {
					int mid = min + max >> 1;
					if (copyDest[mid] < comp) min = mid;
					else max = mid;
				}
				key[max] = i;
				copyDest[max] = max == 0 ? Integer.MIN_VALUE : copyDest[max - 1];
			}
			int[] bit = new int[src.length + 1];
			long ans = (long) src.length * (src.length - 1) >> 1;
			for (int i = 0; i < src.length; ++i) {
				int min = -1, max = src.length;
				int comp = src[i];
				while (max - min > 1) {
					int mid = min + max >> 1;
					if (copySrc[mid] < comp) min = mid;
					else max = mid;
				}
				copySrc[max] = max == 0 ? Integer.MIN_VALUE : copySrc[max - 1];
				max = key[max] + 1;
				for (int j = max; j != 0; j -= j & -j) ans -= bit[j];
				for (int j = max; j < bit.length; j += j & -j) ++bit[j];
			}
			return ans;
		}

		/**
		 * 2つの配列の転倒距離を求めます。つまり、配列srcの隣接する2要素をswapして配列destと一致させるまでのswap回数の最小値を求めます。
		 * @complexity N=src.length, M=dest.lengthとしてO((N+M)log(N+M))
		 * @param src 配列
		 * @param dest 配列
		 * @return srcとdestの転倒距離、ただしsrcを隣接swapすることでdestが構築できない場合は-1
		 */
		public static long inversionDistance(long[] src, long[] dest) {
			if (src == null || dest == null) return src == null && dest == null ? 0 : -1;
			long[] copySrc = java.util.Arrays.copyOf(src, src.length),
					copyDest = java.util.Arrays.copyOf(dest, dest.length);
			sort(copySrc);
			sort(copyDest);
			if (!java.util.Arrays.equals(copySrc, copyDest)) return -1;
			int[] key = new int[dest.length];
			for (int i = 0; i < dest.length; ++i) {
				int min = -1, max = dest.length;
				long comp = dest[i];
				while (max - min > 1) {
					int mid = min + max >> 1;
					if (copyDest[mid] < comp) min = mid;
					else max = mid;
				}
				key[max] = i;
				copyDest[max] = max == 0 ? Integer.MIN_VALUE : copyDest[max - 1];
			}
			int[] bit = new int[src.length + 1];
			long ans = (long) src.length * (src.length - 1) >> 1;
			for (int i = 0; i < src.length; ++i) {
				int min = -1, max = src.length;
				long comp = src[i];
				while (max - min > 1) {
					int mid = min + max >> 1;
					if (copySrc[mid] < comp) min = mid;
					else max = mid;
				}
				copySrc[max] = max == 0 ? Integer.MIN_VALUE : copySrc[max - 1];
				max = key[max] + 1;
				for (int j = max; j != 0; j -= j & -j) ans -= bit[j];
				for (int j = max; j < bit.length; j += j & -j) ++bit[j];
			}
			return ans;
		}

		/**
		 * 2つの配列の転倒距離を求めます。つまり、配列srcの隣接する2要素をswapして配列destと一致させるまでのswap回数の最小値を求めます。
		 * @complexity N=src.length, M=dest.lengthとしてO((N+M)log(N+M))
		 * @param src 配列
		 * @param dest 配列
		 * @return srcとdestの転倒距離、ただしsrcを隣接swapすることでdestが構築できない場合は-1
		 */
		public static long inversionDistance(char[] src, char[] dest) {
			if (src == null || dest == null) return src == null && dest == null ? 0 : -1;
			int[] a = new int[src.length];
			for (int i = 0;i < src.length;++ i) a[i] = src[i];
			int[] b = new int[dest.length];
			for (int i = 0;i < dest.length;++ i) b[i] = dest[i];
			return inversionDistance(a, b);
		}

		/**
		 * 2つの配列の転倒距離を求めます。つまり、配列srcの隣接する2要素をswapして配列destと一致させるまでのswap回数の最小値を求めます。
		 * @complexity N=src.length, M=dest.lengthとしてO((N+M)log(N+M))
		 * @param src 配列
		 * @param dest 配列
		 * @return srcとdestの転倒距離、ただしsrcを隣接swapすることでdestが構築できない場合は-1
		 */
		public static long inversionDistance(String src, String dest) {
			if (src == null || dest == null) return src == null && dest == null ? 0 : -1;
			return inversionDistance(src.toCharArray(), dest.toCharArray());
		}
	}

}

class ACL {

	public static final class DisjointSetUnion {

		private final int[] parent;

		private DisjointSetUnion(final int n) {
			parent = new int[n];
			java.util.Arrays.fill(parent, -1);
		}

		public static DisjointSetUnion create(final int n) {
			return new DisjointSetUnion(n);
		}

		public int getLeader(int a) {
			int p1, p2;
			while ((p1 = parent[a]) >= 0) {
				if ((p2 = parent[p1]) >= 0) a = parent[a] = p2;
				else return p1;
			}
			return a;
		}

		public int merge(int a, int b) {
			a = getLeader(a);
			b = getLeader(b);
			if (a == b) return a;
			if (parent[a] < parent[b]) {
				parent[b] += parent[a];
				parent[a] = b;
				return b;
			}
			parent[a] += parent[b];
			parent[b] = a;
			return a;
		}

		public boolean isSame(final int a, final int b) {
			return getLeader(a) == getLeader(b);
		}

		public int getSize(final int a) {
			return -parent[getLeader(a)];
		}

		public java.util.ArrayList<java.util.ArrayList<Integer>> getGroups() {
			final Object[] group = new Object[parent.length];
			final java.util.ArrayList<java.util.ArrayList<Integer>> ret = new java.util.ArrayList<>();
			for (int i = 0; i < parent.length; ++i) {
				final int leader = getLeader(i);
				final Object put = group[leader];
				if (put == null) {
					final java.util.ArrayList<Integer> list = new java.util.ArrayList<>();
					list.add(i);
					ret.add(list);
					group[leader] = list;
				} else {
					@SuppressWarnings("unchecked")
					final java.util.ArrayList<Integer> list = (java.util.ArrayList<Integer>) put;
					list.add(i);
				}
			}
			return ret;
		}

		@Override
		public String toString() {
			return getGroups().toString();
		}
	}

	public static final class IntFenwickTree {

		private final int[] array;

		private IntFenwickTree(final int n) {
			array = new int[n + 1];
		}

		private IntFenwickTree(final int[] array) {
			this(array.length);
			System.arraycopy(array, 0, this.array, 1, array.length);
			for (int i = 1; i < this.array.length; ++i)
				if (i + (i & -i) < this.array.length) this.array[i + (i & -i)] += this.array[i];
		}

		public static IntFenwickTree create(final int n) {
			return new IntFenwickTree(n);
		}

		public static IntFenwickTree create(final int[] array) {
			return new IntFenwickTree(array);
		}

		public void add(int index, final int add) {
			++index;
			while (index < array.length) {
				array[index] += add;
				index += index & -index;
			}
		}

		private int sum(int index) {
			int sum = 0;
			while (index > 0) {
				sum += array[index];
				index -= index & -index;
			}
			return sum;
		}

		public int sum(final int l, final int r) {
			return sum(r) - sum(l);
		}

		@Override
		public String toString() {
			return java.util.stream.IntStream.range(0, array.length - 1)
					.mapToObj(i -> String.valueOf(sum(i + 1) - sum(i)))
					.collect(java.util.stream.Collectors.joining(", ", "[", "]"));
		}
	}

	public static final class LongFenwickTree {

		private final long[] array;

		private LongFenwickTree(final int n) {
			array = new long[n + 1];
		}

		private LongFenwickTree(final long[] array) {
			this(array.length);
			System.arraycopy(array, 0, this.array, 1, array.length);
			for (int i = 1; i < this.array.length; ++i)
				if (i + (i & -i) < this.array.length) this.array[i + (i & -i)] += this.array[i];
		}

		public static LongFenwickTree create(final int n) {
			return new LongFenwickTree(n);
		}

		public static LongFenwickTree create(final long[] array) {
			return new LongFenwickTree(array);
		}

		public void add(int index, final long add) {
			++index;
			while (index < array.length) {
				array[index] += add;
				index += index & -index;
			}
		}

		private long sum(int index) {
			long sum = 0;
			while (index > 0) {
				sum += array[index];
				index -= index & -index;
			}
			return sum;
		}

		public long sum(final int l, final int r) {
			return sum(r) - sum(l);
		}

		@Override
		public String toString() {
			return java.util.stream.IntStream.range(0, array.length - 1)
					.mapToObj(i -> String.valueOf(sum(i + 1) - sum(i)))
					.collect(java.util.stream.Collectors.joining(", ", "[", "]"));
		}
	}

	public static final class MathLib {

		public static class Barrett {
			private final int mod;
			private final long h, l;
			private final long MAX = 1L << 62;
			private final int MASK = (1 << 31) - 1;

			Barrett(final int mod) {
				this.mod = mod;
				final long t = MAX / mod;
				h = t >>> 31;
				l = t & MASK;
			}

			int reduce(final long x) {
				final long xh = x >>> 31, xl = x & MASK;
				long z = xl * l;
				z = xl * h + xh * l + (z >>> 31);
				z = xh * h + (z >>> 31);
				final int ret = (int) (x - z * mod);
				return ret >= mod ? ret - mod : ret;
			}
		}

		public static class BarrettSmall {
			private final int mod;
			final long t;

			BarrettSmall(final int mod) {
				this.mod = mod;
				t = (1L << 42) / mod;
			}

			int reduce(long x) {
				long q = x * t >> 42;
				x -= q * mod;
				return (int) (x >= mod ? x - mod : x);
			}
		}

		private static long safe_mod(long x, final long m) {
			x %= m;
			if (x < 0) x += m;
			return x;
		}

		private static long[] inv_gcd(long a, final long b) {
			a = safe_mod(a, b);
			if (a == 0) return new long[] { b, 0 };

			long s = b, t = a;
			long m0 = 0, m1 = 1;
			while (t > 0) {
				final long u = s / t;
				s -= t * u;
				m0 -= m1 * u;
				long tmp = s;
				s = t;
				t = tmp;
				tmp = m0;
				m0 = m1;
				m1 = tmp;
			}
			if (m0 < 0) m0 += b / s;
			return new long[] { s, m0 };
		}

		public static int pow(long n, long m, final int mod) {
			assert m >= 0 && mod >= 1;
			if (mod == 1) return 0;
			return pow(n, m, new Barrett(mod));
		}

		public static int pow(long n, long m, Barrett mod) {
			assert m >= 0;
			long ans = 1, num = n % mod.mod;
			while (m != 0) {
				if ((m & 1) != 0) ans = mod.reduce(ans * num);
				m >>>= 1;
				num = mod.reduce(num * num);
			}
			return (int) ans;
		}

		public static int pow998_244_353(long n, long m) {
			assert m >= 0;
			long ans = 1, num = n % 998_244_353;
			while (m != 0) {
				if ((m & 1) != 0) ans = ans * num % 998_244_353;
				m >>>= 1;
				num = num * num % 998_244_353;
			}
			return (int) ans;
		}

		public static int pow167_772_161(long n, long m) {
			assert m >= 0;
			long ans = 1, num = n % 167_772_161;
			while (m != 0) {
				if ((m & 1) != 0) ans = ans * num % 167_772_161;
				m >>>= 1;
				num = num * num % 167_772_161;
			}
			return (int) ans;
		}

		public static int pow469_762_049(long n, long m) {
			assert m >= 0;
			long ans = 1, num = n % 469_762_049;
			while (m != 0) {
				if ((m & 1) != 0) ans = ans * num % 469_762_049;
				m >>>= 1;
				num = num * num % 469_762_049;
			}
			return (int) ans;
		}

		public static int pow1_000_000_007(long n, long m) {
			assert m >= 0;
			long ans = 1, num = n % 1_000_000_007;
			while (m != 0) {
				if ((m & 1) != 0) ans = ans * num % 1_000_000_007;
				m >>>= 1;
				num = num * num % 1_000_000_007;
			}
			return (int) ans;
		}

		public static int pow(long n, long m, BarrettSmall mod) {
			assert m >= 0;
			long ans = 1, num = n % mod.mod;
			while (m != 0) {
				if ((m & 1) != 0) ans = mod.reduce(ans * num);
				m >>>= 1;
				num = mod.reduce(num * num);
			}
			return (int) ans;
		}

		public static long[] crt(final long[] r, final long[] m) {
			assert r.length == m.length;
			final int n = r.length;

			long r0 = 0, m0 = 1;
			for (int i = 0; i < n; i++) {
				assert 1 <= m[i];
				long r1 = safe_mod(r[i], m[i]), m1 = m[i];
				if (m0 < m1) {
					long tmp = r0;
					r0 = r1;
					r1 = tmp;
					tmp = m0;
					m0 = m1;
					m1 = tmp;
				}
				if (m0 % m1 == 0) {
					if (r0 % m1 != r1) return new long[] { 0, 0 };
					continue;
				}

				final long[] ig = inv_gcd(m0, m1);
				final long g = ig[0], im = ig[1];

				final long u1 = m1 / g;
				if ((r1 - r0) % g != 0) return new long[] { 0, 0 };

				final long x = (r1 - r0) / g % u1 * im % u1;

				r0 += x * m0;
				m0 *= u1;
				if (r0 < 0) r0 += m0;
				// System.err.printf("%d %d\n", r0, m0);
			}
			return new long[] { r0, m0 };
		}

		public static long floor_sum(final long n, final long m, long a, long b) {
			long ans = 0;
			if (a >= m) {
				ans += (n - 1) * n * (a / m) / 2;
				a %= m;
			}
			if (b >= m) {
				ans += n * (b / m);
				b %= m;
			}

			final long y_max = (a * n + b) / m;
			final long x_max = y_max * m - b;
			if (y_max == 0) return ans;
			ans += (n - (x_max + a - 1) / a) * y_max;
			ans += floor_sum(y_max, a, m, (a - x_max % a) % a);
			return ans;
		}

		/**
		 * aとbの最大公約数を返します。
		 * @param a 整数
		 * @param b 整数
		 * @return 最大公約数
		 */
		public static int gcd(int a, int b) {
			while (a != 0) if ((b %= a) != 0) a %= b;
			else return a;
			return b;
		}

		/**
		 * 配列全ての値の最大公約数を返します。
		 * @param array 配列
		 * @return 最大公約数
		 */
		public static int gcd(int... array) {
			int ret = array[0];
			for (int i = 1; i < array.length; ++i) ret = gcd(ret, array[i]);
			return ret;
		}

		/**
		 * aとbの最大公約数を返します。
		 * @param a 整数
		 * @param b 整数
		 * @return 最大公約数
		 */
		public static long gcd(long a, long b) {
			while (a != 0) if ((b %= a) != 0) a %= b;
			else return a;
			return b;
		}

		/**
		 * 配列全ての値の最大公約数を返します。
		 * @param array 配列
		 * @return 最大公約数
		 */
		public static long gcd(long... array) {
			long ret = array[0];
			for (int i = 1; i < array.length; ++i) ret = gcd(ret, array[i]);
			return ret;
		}

		/**
		 * 配列全ての値の最小公倍数を返します。
		 * @param a 整数
		 * @param b 整数
		 * @return 最小公倍数
		 */
		public static long lcm(int a, int b) {
			return a / gcd(a, b) * (long) b;
		}

		/**
		 * 配列全ての値の最小公倍数を返します。
		 * @param a 整数
		 * @param b 整数
		 * @return 最小公倍数
		 */
		public static long lcm(long a, long b) {
			return a / gcd(a, b) * b;
		}

		/**
		 * 配列全ての値の最小公倍数を返します。
		 * @param array 配列
		 * @return 最小公倍数
		 */
		public static long lcm(int... array) {
			long ret = array[0];
			for (int i = 1; i < array.length; ++i) ret = lcm(ret, array[i]);
			return ret;
		}

		/**
		 * aとbのうち、小さい方を返します。
		 * @param a 整数
		 * @param b 整数
		 * @return aとbのうち小さい方の値
		 */
		public static int min(int a, int b) {
			return a < b ? a : b;
		}

		/**
		 * 配列の中で最小の値を返します。
		 * @param array 配列
		 * @return 配列の中で最小の値
		 */
		public static int min(int... array) {
			int ret = array[0];
			for (int i = 1; i < array.length; ++i) ret = min(ret, array[i]);
			return ret;
		}

		/**
		 * aとbのうち、小さい方を返します。
		 * @param a 整数
		 * @param b 整数
		 * @return aとbのうち小さい方の値
		 */
		public static long min(long a, long b) {
			return a < b ? a : b;
		}

		/**
		 * 配列の中で最小の値を返します。
		 * @param array 配列
		 * @return 配列の中で最小の値
		 */
		public static long min(long... array) {
			long ret = array[0];
			for (int i = 1; i < array.length; ++i) ret = min(ret, array[i]);
			return ret;
		}

		/**
		 * aとbのうち、大きい方を返します。
		 * @param a 整数
		 * @param b 整数
		 * @return aとbのうち大きい方の値
		 */
		public static int max(int a, int b) {
			return a > b ? a : b;
		}

		/**
		 * 配列の中で最大の値を返します。
		 * @param array 配列
		 * @return 配列の中で最大の値
		 */
		public static int max(int... array) {
			int ret = array[0];
			for (int i = 1; i < array.length; ++i) ret = max(ret, array[i]);
			return ret;
		}

		/**
		 * aとbのうち、大きい方を返します。
		 * @param a 整数
		 * @param b 整数
		 * @return aとbのうち大きい方の値
		 */
		public static long max(long a, long b) {
			return a > b ? a : b;
		}

		/**
		 * 配列の中で最大の値を返します。
		 * @param array 配列
		 * @return 配列の中で最大の値
		 */
		public static long max(long... array) {
			long ret = array[0];
			for (int i = 1; i < array.length; ++i) ret = max(ret, array[i]);
			return ret;
		}

		/**
		 * 配列の値の合計を返します。
		 * @param array 配列
		 * @return 配列の値の総和
		 */
		public static long sum(int... array) {
			long ret = 0;
			for (int i : array) ret += i;
			return ret;
		}

		/**
		 * 配列の値の合計を返します。
		 * @param array 配列
		 * @return 配列の値の総和
		 */
		public static long sum(long... array) {
			long ret = 0;
			for (long i : array) ret += i;
			return ret;
		}

		/**
		 * 二項係数を列挙した配列を返します。
		 * @param l 左辺
		 * @param r 右辺
		 * @return 0≦i≦l及び0≦j≦rを満たす全てのi, jに対してi choose jを求めた配列
		 */
		public static long[][] combination(int l, int r) {
			long[][] pascal = new long[l + 1][r + 1];
			pascal[0][0] = 1;
			for (int i = 1; i <= l; ++i) {
				pascal[i][0] = 1;
				for (int j = 1; j <= r; ++j) {
					pascal[i][j] = pascal[i - 1][j - 1] + pascal[i - 1][j];
				}
			}
			return pascal;
		}

		/**
		 * 二分探索を行い、func(x) != func(x+1)となるような数xを発見します。
		 * funcが単調な関数であるとき、発見されるxは一意に定まります。
		 * @param isTrue func(isTrue)=trueとなるような値
		 * @param isFalse func(isFalse)=falseとなるような値
		 * @param func 関数
		 * @complexity O(log(max(isTrue, isFalse) - min(isTrue, isFalse)))
		 * @return func(x) != func(x+1)となるような数x
		 */
		public static int binarySearch(int isTrue, int isFalse, java.util.function.IntPredicate func) {
			if (isTrue <= isFalse) {
				int halfDiff = isFalse - isTrue >> 1, mid = isTrue + halfDiff;
				while(halfDiff != 0) {
					if (func.test(mid)) isTrue = mid;
					else isFalse = mid;
					halfDiff = isFalse - isTrue >> 1;
					mid = isTrue + halfDiff;
				}
				return isTrue;
			} else {
				int halfDiff = isTrue - isFalse >> 1, mid = isFalse + halfDiff;
				while(halfDiff != 0) {
					if (func.test(mid)) isTrue = mid;
					else isFalse = mid;
					halfDiff = isTrue - isFalse >> 1;
					mid = isFalse + halfDiff;
				}
				return isFalse;
			}
		}

		/**
		 * 二分探索を行い、func(x) != func(x+1)となるような数xを発見します。
		 * funcが単調な関数であるとき、発見されるxは一意に定まります。
		 * @param isTrue func(isTrue)=trueとなるような値
		 * @param isFalse func(isFalse)=falseとなるような値
		 * @param func 関数
		 * @complexity O(log(max(isTrue, isFalse) - min(isTrue, isFalse)))
		 * @return func(x) != func(x+1)となるような数x
		 */
		public static long binarySearch(long isTrue, long isFalse, java.util.function.LongPredicate func) {
			if (isTrue <= isFalse) {
				long halfDiff = isFalse - isTrue >> 1, mid = isTrue + halfDiff;
				while(halfDiff != 0) {
					if (func.test(mid)) isTrue = mid;
					else isFalse = mid;
					halfDiff = isFalse - isTrue >> 1;
					mid = isTrue + halfDiff;
				}
				return isTrue;
			} else {
				long halfDiff = isTrue - isFalse >> 1, mid = isFalse + halfDiff;
				while(halfDiff != 0) {
					if (func.test(mid)) isTrue = mid;
					else isFalse = mid;
					halfDiff = isTrue - isFalse >> 1;
					mid = isFalse + halfDiff;
				}
				return isFalse;
			}
		}

		/**
		 * 二分探索を行い、func(x) != func(x+Math.nextUp(x))となるような数xを発見します。
		 * funcが単調な関数であるとき、発見されるxは一意に定まります。
		 * @param isTrue func(isTrue)=trueとなるような値
		 * @param isFalse func(isFalse)=falseとなるような値
		 * @param func 関数
		 * @complexity O(log(max(isTrue, isFalse) - min(isTrue, isFalse)))
		 * @return func(x) != func(x+Math.nextUp(x))となるような数x
		 */
		public static double binarySearch(double isTrue, double isFalse, java.util.function.DoublePredicate func) {
			return Double.longBitsToDouble(binarySearch(Double.doubleToRawLongBits(isTrue), Double.doubleToRawLongBits(isFalse), (long i) -> func.test(Double.longBitsToDouble(i))));
		}

		/**
		 * 下に凸な関数の極小値を発見します。
		 * @param <T> 関数の終域
		 * @param min 関数の定義域の下界
		 * @param max 関数の定義域の上界
		 * @param loop 探索回数
		 * @param func 関数
		 * @return 極小値
		 */
		public static <T extends Comparable<T>> double find_minimal(double min, double max, int loop, java.util.function.DoubleFunction<T> func) {
			return find_minimal(min, max, loop, func, java.util.Comparator.naturalOrder());
		}

		/**
		 * 下に凸な関数の極小値を発見します。
		 * @param <T> 関数の終域
		 * @param min 関数の定義域の下界
		 * @param max 関数の定義域の上界
		 * @param loop 探索回数
		 * @param func 関数
		 * @param comparator 比較関数
		 * @return 極小値
		 */
		public static <T> double find_minimal(double min, double max, int loop, java.util.function.DoubleFunction<T> func, java.util.Comparator<T> comparator) {
			double phi = (1 + Math.sqrt(5)) / 2;
			for (int i = 0;i < loop;++ i) {
				double mid_min = (min * phi + max) / (1 + phi), mid_max = (min + max * phi) / (1 + phi);
				T mid_min_calc = func.apply(mid_min), mid_max_calc = func.apply(mid_max);
				if (comparator.compare(mid_min_calc, mid_max_calc) <= 0) max = mid_max;
				else min = mid_min;
			}
			return min;
		}

		/**
		 * 上に凸な関数の極大値を発見します。
		 * @param <T> 関数の終域
		 * @param min 関数の定義域の下界
		 * @param max 関数の定義域の上界
		 * @param loop 探索回数
		 * @param func 関数
		 * @return 極大値
		 */
		public static <T extends Comparable<T>> double find_maximal(double min, double max, int loop, java.util.function.DoubleFunction<T> func) {
			return find_maximal(min, max, loop, func, java.util.Comparator.naturalOrder());
		}

		/**
		 * 上に凸な関数の極大値を発見します。
		 * @param <T> 関数の終域
		 * @param min 関数の定義域の下界
		 * @param max 関数の定義域の上界
		 * @param loop 探索回数
		 * @param func 関数
		 * @param comparator 比較関数
		 * @return 極大値
		 */
		public static <T> double find_maximal(double min, double max, int loop, java.util.function.DoubleFunction<T> func, java.util.Comparator<T> comparator) {
			if (max <= min) throw new IllegalArgumentException("empty range");
			double phi = (1 + Math.sqrt(5)) / 2;
			for (int i = 0;i < loop;++ i) {
				double mid_min = (min * phi + max) / (1 + phi), mid_max = (min + max * phi) / (1 + phi);
				T mid_min_calc = func.apply(mid_min), mid_max_calc = func.apply(mid_max);
				if (comparator.compare(mid_min_calc, mid_max_calc) >= 0) max = mid_max;
				else min = mid_min;
			}
			return min;
		}

		/**
		 * 下に凸な関数の極小値を発見します。
		 * @param <T> 関数の終域
		 * @param min 関数の定義域の下界
		 * @param max 関数の定義域の上界
		 * @param func 関数
		 * @return 極小値
		 */
		public static <T extends Comparable<T>> int find_minimal(int min, int max, java.util.function.IntFunction<T> func) {
			return find_minimal(min, max, func, java.util.Comparator.naturalOrder());
		}

		/**
		 * 下に凸な関数の極小値を発見します。
		 * @param <T> 関数の終域
		 * @param min 関数の定義域の下界
		 * @param max 関数の定義域の上界
		 * @param func 関数
		 * @param comparator 比較関数
		 * @return 極小値
		 */
		public static <T> int find_minimal(int min, int max, java.util.function.IntFunction<T> func, java.util.Comparator<T> comparator) {
			-- min;
			int range = max - min;
			if (range <= 1) throw new IllegalArgumentException("empty range");
			int fib_small = 1, fib_large = 1;
			while(fib_large < range) {
				fib_large += fib_small;
				fib_small = fib_large - fib_small;
			}
			T mid_min_calc = null, mid_max_calc = null;
			int last_calc = -1;
			final int LAST_CALC_IS_MIN = 0, LAST_CALC_IS_MAX = 1;
			while(max - min > 2) {
				fib_small = fib_large - fib_small;
				fib_large -= fib_small;
				int mid_min = min + fib_small, mid_max = min + fib_large;
				if (mid_max >= max) {
					mid_max_calc = mid_min_calc;
					last_calc = LAST_CALC_IS_MAX;
					continue;
				}
				if (last_calc != LAST_CALC_IS_MIN) mid_min_calc = func.apply(mid_min);
				if (last_calc != LAST_CALC_IS_MAX) mid_max_calc = func.apply(mid_max);
				if (comparator.compare(mid_min_calc, mid_max_calc) <= 0) {
					max = mid_max;
					mid_max_calc = mid_min_calc;
					last_calc = LAST_CALC_IS_MAX;
				} else {
					min = mid_min;
					mid_min_calc = mid_max_calc;
					last_calc = LAST_CALC_IS_MIN;
				}
			}
			return min + 1;
		}

		/**
		 * 上に凸な関数の極大値を発見します。
		 * @param <T> 関数の終域
		 * @param min 関数の定義域の下界
		 * @param max 関数の定義域の上界
		 * @param func 関数
		 * @return 極大値
		 */
		public static <T extends Comparable<T>> int find_maximal(int min, int max, java.util.function.IntFunction<T> func) {
			return find_maximal(min, max, func, java.util.Comparator.naturalOrder());
		}

		/**
		 * 上に凸な関数の極大値を発見します。
		 * @param <T> 関数の終域
		 * @param min 関数の定義域の下界
		 * @param max 関数の定義域の上界
		 * @param func 関数
		 * @param comparator 比較関数
		 * @return 極大値
		 */
		public static <T> int find_maximal(int min, int max, java.util.function.IntFunction<T> func, java.util.Comparator<T> comparator) {
			-- min;
			int range = max - min;
			if (range <= 1) throw new IllegalArgumentException("empty range");
			int fib_small = 1, fib_large = 1;
			while(fib_large < range) {
				fib_large += fib_small;
				fib_small = fib_large - fib_small;
			}
			T mid_min_calc = null, mid_max_calc = null;
			int last_calc = -1;
			final int LAST_CALC_IS_MIN = 0, LAST_CALC_IS_MAX = 1;
			while(max - min > 2) {
				fib_small = fib_large - fib_small;
				fib_large -= fib_small;
				int mid_min = min + fib_small, mid_max = min + fib_large;
				if (mid_max >= max) {
					mid_max_calc = mid_min_calc;
					last_calc = LAST_CALC_IS_MAX;
					continue;
				}
				if (last_calc != LAST_CALC_IS_MIN) mid_min_calc = func.apply(mid_min);
				if (last_calc != LAST_CALC_IS_MAX) mid_max_calc = func.apply(mid_max);
				if (comparator.compare(mid_min_calc, mid_max_calc) >= 0) {
					max = mid_max;
					mid_max_calc = mid_min_calc;
					last_calc = LAST_CALC_IS_MAX;
				} else {
					min = mid_min;
					mid_min_calc = mid_max_calc;
					last_calc = LAST_CALC_IS_MIN;
				}
			}
			return min + 1;
		}

		/**
		 * 下に凸な関数の極小値を発見します。
		 * @param <T> 関数の終域
		 * @param min 関数の定義域の下界
		 * @param max 関数の定義域の上界
		 * @param func 関数
		 * @return 極小値
		 */
		public static <T extends Comparable<T>> long find_minimal(long min, long max, java.util.function.LongFunction<T> func) {
			return find_minimal(min, max, func, java.util.Comparator.naturalOrder());
		}

		/**
		 * 下に凸な関数の極小値を発見します。
		 * @param <T> 関数の終域
		 * @param min 関数の定義域の下界
		 * @param max 関数の定義域の上界
		 * @param func 関数
		 * @param comparator 比較関数
		 * @return 極小値
		 */
		public static <T> long find_minimal(long min, long max, java.util.function.LongFunction<T> func, java.util.Comparator<T> comparator) {
			-- min;
			long range = max - min;
			if (range <= 1) throw new IllegalArgumentException("empty range");
			long fib_small = 1, fib_large = 1;
			while(fib_large < range) {
				fib_large += fib_small;
				fib_small = fib_large - fib_small;
			}
			T mid_min_calc = null, mid_max_calc = null;
			int last_calc = -1;
			final int LAST_CALC_IS_MIN = 0, LAST_CALC_IS_MAX = 1;
			while(max - min > 2) {
				fib_small = fib_large - fib_small;
				fib_large -= fib_small;
				long mid_min = min + fib_small, mid_max = min + fib_large;
				if (mid_max >= max) {
					mid_max_calc = mid_min_calc;
					last_calc = LAST_CALC_IS_MAX;
					continue;
				}
				if (last_calc != LAST_CALC_IS_MIN) mid_min_calc = func.apply(mid_min);
				if (last_calc != LAST_CALC_IS_MAX) mid_max_calc = func.apply(mid_max);
				if (comparator.compare(mid_min_calc, mid_max_calc) <= 0) {
					max = mid_max;
					mid_max_calc = mid_min_calc;
					last_calc = LAST_CALC_IS_MAX;
				} else {
					min = mid_min;
					mid_min_calc = mid_max_calc;
					last_calc = LAST_CALC_IS_MIN;
				}
			}
			return min + 1;
		}

		/**
		 * 上に凸な関数の極大値を発見します。
		 * @param <T> 関数の終域
		 * @param min 関数の定義域の下界
		 * @param max 関数の定義域の上界
		 * @param func 関数
		 * @return 極大値
		 */
		public static <T extends Comparable<T>> long find_maximal(long min, long max, java.util.function.LongFunction<T> func) {
			return find_maximal(min, max, func, java.util.Comparator.naturalOrder());
		}

		/**
		 * 上に凸な関数の極大値を発見します。
		 * @param <T> 関数の終域
		 * @param min 関数の定義域の下界
		 * @param max 関数の定義域の上界
		 * @param func 関数
		 * @param comparator 比較関数
		 * @return 極大値
		 */
		public static <T> long find_maximal(long min, long max, java.util.function.LongFunction<T> func, java.util.Comparator<T> comparator) {
			-- min;
			long range = max - min;
			if (range <= 1) throw new IllegalArgumentException("empty range");
			long fib_small = 1, fib_large = 1;
			while(fib_large < range) {
				fib_large += fib_small;
				fib_small = fib_large - fib_small;
			}
			T mid_min_calc = null, mid_max_calc = null;
			int last_calc = -1;
			final int LAST_CALC_IS_MIN = 0, LAST_CALC_IS_MAX = 1;
			while(max - min > 2) {
				fib_small = fib_large - fib_small;
				fib_large -= fib_small;
				long mid_min = min + fib_small, mid_max = min + fib_large;
				if (mid_max >= max) {
					mid_max_calc = mid_min_calc;
					last_calc = LAST_CALC_IS_MAX;
					continue;
				}
				if (last_calc != LAST_CALC_IS_MIN) mid_min_calc = func.apply(mid_min);
				if (last_calc != LAST_CALC_IS_MAX) mid_max_calc = func.apply(mid_max);
				if (comparator.compare(mid_min_calc, mid_max_calc) >= 0) {
					max = mid_max;
					mid_max_calc = mid_min_calc;
					last_calc = LAST_CALC_IS_MAX;
				} else {
					min = mid_min;
					mid_min_calc = mid_max_calc;
					last_calc = LAST_CALC_IS_MIN;
				}
			}
			return min + 1;
		}
	}

	/**
	 * @verified https://atcoder.jp/contests/practice2/tasks/practice2_d
	 */
	public static final class MaxFlow {
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
			pos = new java.util.ArrayList<>();
			g = new java.util.ArrayList[n];
			for (int i = 0; i < n; i++) {
				g[i] = new java.util.ArrayList<>();
			}
		}

		public int addEdge(int from, int to, long cap) {
			rangeCheck(from, 0, n);
			rangeCheck(to, 0, n);
			nonNegativeCheck(cap, "Capacity");
			int m = pos.size();
			pos.add(new IntPair(from, g[from].size()));
			int fromId = g[from].size();
			int toId = g[to].size();
			if (from == to) toId++;
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
				if (level[t] < 0) break;
				java.util.Arrays.fill(iter, 0);
				while (flow < flowLimit) {
					long d = dfs(t, s, flowLimit - flow, iter, level);
					if (d == 0) break;
					flow += d;
				}
			}
			return flow;
		}

		private void bfs(int s, int t, int[] level, int[] que) {
			java.util.Arrays.fill(level, -1);
			int hd = 0, tl = 0;
			que[tl++] = s;
			level[s] = 0;
			while (hd < tl) {
				int u = que[hd++];
				for (InternalCapEdge e : g[u]) {
					int v = e.to;
					if (e.cap == 0 || level[v] >= 0) continue;
					level[v] = level[u] + 1;
					if (v == t) return;
					que[tl++] = v;
				}
			}
		}

		private long dfs(int cur, int s, long flowLimit, int[] iter, int[] level) {
			if (cur == s) return flowLimit;
			long res = 0;
			int curLevel = level[cur];
			for (int itMax = g[cur].size(); iter[cur] < itMax; iter[cur]++) {
				int i = iter[cur];
				InternalCapEdge e = g[cur].get(i);
				InternalCapEdge re = getInternalEdgeReversed(e);
				if (curLevel <= level[e.to] || re.cap == 0) continue;
				long d = dfs(e.to, s, Math.min(flowLimit - res, re.cap), iter, level);
				if (d <= 0) continue;
				e.cap += d;
				re.cap -= d;
				res += d;
				if (res == flowLimit) break;
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
			if (cap < 0) { throw new IllegalArgumentException(String.format("%s %d is negative.", attribute, cap)); }
		}
	}

	/**
	 * @verified
	 * - https://atcoder.jp/contests/practice2/tasks/practice2_e
	 * - http://judge.u-aizu.ac.jp/onlinejudge/description.jsp?id=GRL_6_B
	 */
	public static final class MinCostFlow {
		private static final class InternalWeightedCapEdge {
			final int to, rev;
			long cap;
			final long cost;

			InternalWeightedCapEdge(int to, int rev, long cap, long cost) {
				this.to = to;
				this.rev = rev;
				this.cap = cap;
				this.cost = cost;
			}
		}

		public static final class WeightedCapEdge {
			public final int from, to;
			public final long cap, flow, cost;

			WeightedCapEdge(int from, int to, long cap, long flow, long cost) {
				this.from = from;
				this.to = to;
				this.cap = cap;
				this.flow = flow;
				this.cost = cost;
			}

			@Override
			public boolean equals(Object o) {
				if (o instanceof WeightedCapEdge) {
					WeightedCapEdge e = (WeightedCapEdge) o;
					return from == e.from && to == e.to && cap == e.cap && flow == e.flow && cost == e.cost;
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

		public static final class FlowAndCost {
			public final long flow, cost;

			FlowAndCost(long flow, long cost) {
				this.flow = flow;
				this.cost = cost;
			}

			@Override
			public boolean equals(Object o) {
				if (o instanceof FlowAndCost) {
					FlowAndCost c = (FlowAndCost) o;
					return flow == c.flow && cost == c.cost;
				}
				return false;
			}
		}

		static final long INF = Long.MAX_VALUE;

		private final int n;
		private final java.util.ArrayList<IntPair> pos;
		private final java.util.ArrayList<InternalWeightedCapEdge>[] g;

		@SuppressWarnings("unchecked")
		public MinCostFlow(int n) {
			this.n = n;
			pos = new java.util.ArrayList<>();
			g = new java.util.ArrayList[n];
			for (int i = 0; i < n; i++) {
				g[i] = new java.util.ArrayList<>();
			}
		}

		public int addEdge(int from, int to, long cap, long cost) {
			rangeCheck(from, 0, n);
			rangeCheck(to, 0, n);
			nonNegativeCheck(cap, "Capacity");
			nonNegativeCheck(cost, "Cost");
			int m = pos.size();
			pos.add(new IntPair(from, g[from].size()));
			int fromId = g[from].size();
			int toId = g[to].size();
			if (from == to) toId++;
			g[from].add(new InternalWeightedCapEdge(to, toId, cap, cost));
			g[to].add(new InternalWeightedCapEdge(from, fromId, 0L, -cost));
			return m;
		}

		private InternalWeightedCapEdge getInternalEdge(int i) {
			return g[pos.get(i).first].get(pos.get(i).second);
		}

		private InternalWeightedCapEdge getInternalEdgeReversed(InternalWeightedCapEdge e) {
			return g[e.to].get(e.rev);
		}

		public WeightedCapEdge getEdge(int i) {
			int m = pos.size();
			rangeCheck(i, 0, m);
			InternalWeightedCapEdge e = getInternalEdge(i);
			InternalWeightedCapEdge re = getInternalEdgeReversed(e);
			return new WeightedCapEdge(re.to, e.to, e.cap + re.cap, re.cap, e.cost);
		}

		public WeightedCapEdge[] getEdges() {
			WeightedCapEdge[] res = new WeightedCapEdge[pos.size()];
			java.util.Arrays.setAll(res, this::getEdge);
			return res;
		}

		public FlowAndCost minCostMaxFlow(int s, int t) {
			return minCostFlow(s, t, INF);
		}

		public FlowAndCost minCostFlow(int s, int t, long flowLimit) {
			return minCostSlope(s, t, flowLimit).getLast();
		}

		public java.util.ArrayList<Long> minCostList(int s, int t) {
			return minCostList(s, t, INF);
		}

		public java.util.ArrayList<Long> minCostList(int s, int t, long flowLimit) {
			java.util.LinkedList<FlowAndCost> list = minCostSlope(s, t, flowLimit);
			FlowAndCost last = list.pollFirst();
			java.util.ArrayList<Long> ret = new java.util.ArrayList<>();
			ret.add(0L);
			while(!list.isEmpty()) {
				FlowAndCost now = list.pollFirst();
				for (long i = last.flow + 1;i <= now.flow;++ i) {
					ret.add(last.cost + (i - last.flow) * (now.cost - last.cost) / (now.flow - last.flow));
				}
				last = now;
			}
			return ret;
		}

		java.util.LinkedList<FlowAndCost> minCostSlope(int s, int t) {
			return minCostSlope(s, t, INF);
		}

		public java.util.LinkedList<FlowAndCost> minCostSlope(int s, int t, long flowLimit) {
			rangeCheck(s, 0, n);
			rangeCheck(t, 0, n);
			if (s == t) { throw new IllegalArgumentException(String.format("%d and %d is the same vertex.", s, t)); }
			long[] dual = new long[n];
			long[] dist = new long[n];
			int[] pv = new int[n];
			int[] pe = new int[n];
			boolean[] vis = new boolean[n];
			long flow = 0;
			long cost = 0, prev_cost = -1;
			java.util.LinkedList<FlowAndCost> result = new java.util.LinkedList<>();
			result.addLast(new FlowAndCost(flow, cost));
			while (flow < flowLimit) {
				if (!dualRef(s, t, dual, dist, pv, pe, vis)) break;
				long c = flowLimit - flow;
				for (int v = t; v != s; v = pv[v]) {
					c = Math.min(c, g[pv[v]].get(pe[v]).cap);
				}
				for (int v = t; v != s; v = pv[v]) {
					InternalWeightedCapEdge e = g[pv[v]].get(pe[v]);
					e.cap -= c;
					g[v].get(e.rev).cap += c;
				}
				long d = -dual[s];
				flow += c;
				cost += c * d;
				if (prev_cost == d) {
					result.removeLast();
				}
				result.addLast(new FlowAndCost(flow, cost));
				prev_cost = cost;
			}
			return result;
		}

		private boolean dualRef(int s, int t, long[] dual, long[] dist, int[] pv, int[] pe, boolean[] vis) {
			java.util.Arrays.fill(dist, INF);
			java.util.Arrays.fill(pv, -1);
			java.util.Arrays.fill(pe, -1);
			java.util.Arrays.fill(vis, false);
			class State implements Comparable<State> {
				final long key;
				final int to;

				State(long key, int to) {
					this.key = key;
					this.to = to;
				}

				@Override
				public int compareTo(State q) {
					return key > q.key ? 1 : -1;
				}
			};
			java.util.PriorityQueue<State> pq = new java.util.PriorityQueue<>();
			dist[s] = 0;
			pq.add(new State(0L, s));
			while (pq.size() > 0) {
				int v = pq.poll().to;
				if (vis[v]) continue;
				vis[v] = true;
				if (v == t) break;
				for (int i = 0, deg = g[v].size(); i < deg; i++) {
					InternalWeightedCapEdge e = g[v].get(i);
					if (vis[e.to] || e.cap == 0) continue;
					long cost = e.cost - dual[e.to] + dual[v];
					if (dist[e.to] - dist[v] > cost) {
						dist[e.to] = dist[v] + cost;
						pv[e.to] = v;
						pe[e.to] = i;
						pq.add(new State(dist[e.to], e.to));
					}
				}
			}
			if (!vis[t]) { return false; }

			for (int v = 0; v < n; v++) {
				if (!vis[v]) continue;
				dual[v] -= dist[t] - dist[v];
			}
			return true;
		}

		private void rangeCheck(int i, int minInlusive, int maxExclusive) {
			if (i < 0 || i >= maxExclusive) {
				throw new IndexOutOfBoundsException(
						String.format("Index %d out of bounds for length %d", i, maxExclusive));
			}
		}

		private void nonNegativeCheck(long cap, java.lang.String attribute) {
			if (cap < 0) { throw new IllegalArgumentException(String.format("%s %d is negative.", attribute, cap)); }
		}
	}

	/**
	 * @verified
	 *           <ul>
	 *           <li>https://atcoder.jp/contests/arc050/tasks/arc050_c
	 *           <li>https://atcoder.jp/contests/abc129/tasks/abc129_f
	 *           </ul>
	 */
	public static final class ModIntFactory {

		private final ModArithmetic ma;
		private final int mod;

		public ModIntFactory(final int mod) {
			ma = ModArithmetic.of(mod);
			this.mod = mod;
		}

		public ModInt create(long value) {
			if ((value %= mod) < 0) value += mod;
			if (ma instanceof ModArithmetic.ModArithmeticMontgomery) {
				return new ModInt(((ModArithmetic.ModArithmeticMontgomery) ma).generate(value));
			}
			return new ModInt((int) value);
		}

		class ModInt {

			private int value;

			private ModInt(final int value) {
				this.value = value;
			}

			public int mod() {
				return mod;
			}

			public int value() {
				if (ma instanceof ModArithmetic.ModArithmeticMontgomery) {
					return ((ModArithmetic.ModArithmeticMontgomery) ma).reduce(value);
				}
				return value;
			}

			public ModInt add(final ModInt mi) {
				return new ModInt(ma.add(value, mi.value));
			}

			public ModInt add(final ModInt mi1, final ModInt mi2) {
				return new ModInt(ma.add(value, mi1.value)).addAsg(mi2);
			}

			public ModInt add(final ModInt mi1, final ModInt mi2, final ModInt mi3) {
				return new ModInt(ma.add(value, mi1.value)).addAsg(mi2).addAsg(mi3);
			}

			public ModInt add(final ModInt mi1, final ModInt mi2, final ModInt mi3, final ModInt mi4) {
				return new ModInt(ma.add(value, mi1.value)).addAsg(mi2).addAsg(mi3).addAsg(mi4);
			}

			public ModInt add(final ModInt mi1, final ModInt... mis) {
				final ModInt mi = add(mi1);
				for (final ModInt m : mis) mi.addAsg(m);
				return mi;
			}

			public ModInt add(final long mi) {
				return new ModInt(ma.add(value, ma.remainder(mi)));
			}

			public ModInt sub(final ModInt mi) {
				return new ModInt(ma.sub(value, mi.value));
			}

			public ModInt sub(final long mi) {
				return new ModInt(ma.sub(value, ma.remainder(mi)));
			}

			public ModInt mul(final ModInt mi) {
				return new ModInt(ma.mul(value, mi.value));
			}

			public ModInt mul(final ModInt mi1, final ModInt mi2) {
				return new ModInt(ma.mul(value, mi1.value)).mulAsg(mi2);
			}

			public ModInt mul(final ModInt mi1, final ModInt mi2, final ModInt mi3) {
				return new ModInt(ma.mul(value, mi1.value)).mulAsg(mi2).mulAsg(mi3);
			}

			public ModInt mul(final ModInt mi1, final ModInt mi2, final ModInt mi3, final ModInt mi4) {
				return new ModInt(ma.mul(value, mi1.value)).mulAsg(mi2).mulAsg(mi3).mulAsg(mi4);
			}

			public ModInt mul(final ModInt mi1, final ModInt... mis) {
				final ModInt mi = mul(mi1);
				for (final ModInt m : mis) mi.mulAsg(m);
				return mi;
			}

			public ModInt mul(final long mi) {
				return new ModInt(ma.mul(value, ma.remainder(mi)));
			}

			public ModInt div(final ModInt mi) {
				return new ModInt(ma.div(value, mi.value));
			}

			public ModInt div(final long mi) {
				return new ModInt(ma.div(value, ma.remainder(mi)));
			}

			public ModInt inv() {
				return new ModInt(ma.inv(value));
			}

			public ModInt pow(final long b) {
				return new ModInt(ma.pow(value, b));
			}

			public ModInt addAsg(final ModInt mi) {
				value = ma.add(value, mi.value);
				return this;
			}

			public ModInt addAsg(final ModInt mi1, final ModInt mi2) {
				return addAsg(mi1).addAsg(mi2);
			}

			public ModInt addAsg(final ModInt mi1, final ModInt mi2, final ModInt mi3) {
				return addAsg(mi1).addAsg(mi2).addAsg(mi3);
			}

			public ModInt addAsg(final ModInt mi1, final ModInt mi2, final ModInt mi3, final ModInt mi4) {
				return addAsg(mi1).addAsg(mi2).addAsg(mi3).addAsg(mi4);
			}

			public ModInt addAsg(final ModInt... mis) {
				for (final ModInt m : mis) addAsg(m);
				return this;
			}

			public ModInt addAsg(final long mi) {
				value = ma.add(value, ma.remainder(mi));
				return this;
			}

			public ModInt subAsg(final ModInt mi) {
				value = ma.sub(value, mi.value);
				return this;
			}

			public ModInt subAsg(final long mi) {
				value = ma.sub(value, ma.remainder(mi));
				return this;
			}

			public ModInt mulAsg(final ModInt mi) {
				value = ma.mul(value, mi.value);
				return this;
			}

			public ModInt mulAsg(final ModInt mi1, final ModInt mi2) {
				return mulAsg(mi1).mulAsg(mi2);
			}

			public ModInt mulAsg(final ModInt mi1, final ModInt mi2, final ModInt mi3) {
				return mulAsg(mi1).mulAsg(mi2).mulAsg(mi3);
			}

			public ModInt mulAsg(final ModInt mi1, final ModInt mi2, final ModInt mi3, final ModInt mi4) {
				return mulAsg(mi1).mulAsg(mi2).mulAsg(mi3).mulAsg(mi4);
			}

			public ModInt mulAsg(final ModInt... mis) {
				for (final ModInt m : mis) mulAsg(m);
				return this;
			}

			public ModInt mulAsg(final long mi) {
				value = ma.mul(value, ma.remainder(mi));
				return this;
			}

			public ModInt divAsg(final ModInt mi) {
				value = ma.div(value, mi.value);
				return this;
			}

			public ModInt divAsg(final long mi) {
				value = ma.div(value, ma.remainder(mi));
				return this;
			}

			@Override
			public String toString() {
				return String.valueOf(value());
			}

			@Override
			public boolean equals(final Object o) {
				if (o instanceof ModInt) {
					final ModInt mi = (ModInt) o;
					return mod() == mi.mod() && value() == mi.value();
				}
				return false;
			}

			@Override
			public int hashCode() {
				return (1 * 37 + mod()) * 37 + value();
			}
		}

		private interface ModArithmetic {

			public int mod();

			public int remainder(long value);

			public int add(int a, int b);

			public int sub(int a, int b);

			public int mul(int a, int b);

			public default int div(final int a, final int b) {
				return mul(a, inv(b));
			}

			public int inv(int a);

			public int pow(int a, long b);

			public static ModArithmetic of(final int mod) {
				if (mod <= 0) {
					throw new IllegalArgumentException();
				} else if (mod == 1) {
					return new ModArithmetic1();
				} else if (mod == 2) {
					return new ModArithmetic2();
				} else if (mod == 998244353) {
					return new ModArithmetic998244353();
				} else if (mod == 1000000007) {
					return new ModArithmetic1000000007();
				} else if ((mod & 1) == 1) {
					return new ModArithmeticMontgomery(mod);
				} else {
					return new ModArithmeticBarrett(mod);
				}
			}

			static final class ModArithmetic1 implements ModArithmetic {

				@Override
				public int mod() {
					return 1;
				}

				@Override
				public int remainder(final long value) {
					return 0;
				}

				@Override
				public int add(final int a, final int b) {
					return 0;
				}

				@Override
				public int sub(final int a, final int b) {
					return 0;
				}

				@Override
				public int mul(final int a, final int b) {
					return 0;
				}

				@Override
				public int inv(final int a) {
					throw new ArithmeticException("divide by zero");
				}

				@Override
				public int pow(final int a, final long b) {
					return 0;
				}
			}

			static final class ModArithmetic2 implements ModArithmetic {

				@Override
				public int mod() {
					return 2;
				}

				@Override
				public int remainder(final long value) {
					return (int) (value & 1);
				}

				@Override
				public int add(final int a, final int b) {
					return a ^ b;
				}

				@Override
				public int sub(final int a, final int b) {
					return a ^ b;
				}

				@Override
				public int mul(final int a, final int b) {
					return a & b;
				}

				@Override
				public int inv(final int a) {
					if (a == 0) throw new ArithmeticException("divide by zero");
					return a;
				}

				@Override
				public int pow(final int a, final long b) {
					if (b == 0) return 1;
					return a;
				}
			}

			static final class ModArithmetic998244353 implements ModArithmetic {

				private final int mod = 998244353;

				@Override
				public int mod() {
					return mod;
				}

				@Override
				public int remainder(long value) {
					return (int) ((value %= mod) < 0 ? value + mod : value);
				}

				@Override
				public int add(final int a, final int b) {
					final int res = a + b;
					return res >= mod ? res - mod : res;
				}

				@Override
				public int sub(final int a, final int b) {
					final int res = a - b;
					return res < 0 ? res + mod : res;
				}

				@Override
				public int mul(final int a, final int b) {
					return (int) ((long) a * b % mod);
				}

				@Override
				public int inv(int a) {
					int b = mod;
					long u = 1, v = 0;
					while (b >= 1) {
						final long t = a / b;
						a -= t * b;
						final int tmp1 = a;
						a = b;
						b = tmp1;
						u -= t * v;
						final long tmp2 = u;
						u = v;
						v = tmp2;
					}
					u %= mod;
					if (a != 1) { throw new ArithmeticException("divide by zero"); }
					return (int) (u < 0 ? u + mod : u);
				}

				@Override
				public int pow(final int a, long b) {
					if (b < 0) throw new ArithmeticException("negative power");
					long res = 1;
					long pow2 = a;
					long idx = 1;
					while (b > 0) {
						final long lsb = b & -b;
						for (; lsb != idx; idx <<= 1) {
							pow2 = pow2 * pow2 % mod;
						}
						res = res * pow2 % mod;
						b ^= lsb;
					}
					return (int) res;
				}
			}

			static final class ModArithmetic1000000007 implements ModArithmetic {

				private final int mod = 1000000007;

				@Override
				public int mod() {
					return mod;
				}

				@Override
				public int remainder(long value) {
					return (int) ((value %= mod) < 0 ? value + mod : value);
				}

				@Override
				public int add(final int a, final int b) {
					final int res = a + b;
					return res >= mod ? res - mod : res;
				}

				@Override
				public int sub(final int a, final int b) {
					final int res = a - b;
					return res < 0 ? res + mod : res;
				}

				@Override
				public int mul(final int a, final int b) {
					return (int) ((long) a * b % mod);
				}

				@Override
				public int div(final int a, final int b) {
					return mul(a, inv(b));
				}

				@Override
				public int inv(int a) {
					int b = mod;
					long u = 1, v = 0;
					while (b >= 1) {
						final long t = a / b;
						a -= t * b;
						final int tmp1 = a;
						a = b;
						b = tmp1;
						u -= t * v;
						final long tmp2 = u;
						u = v;
						v = tmp2;
					}
					u %= mod;
					if (a != 1) { throw new ArithmeticException("divide by zero"); }
					return (int) (u < 0 ? u + mod : u);
				}

				@Override
				public int pow(final int a, long b) {
					if (b < 0) throw new ArithmeticException("negative power");
					long res = 1;
					long pow2 = a;
					long idx = 1;
					while (b > 0) {
						final long lsb = b & -b;
						for (; lsb != idx; idx <<= 1) {
							pow2 = pow2 * pow2 % mod;
						}
						res = res * pow2 % mod;
						b ^= lsb;
					}
					return (int) res;
				}
			}

			static final class ModArithmeticMontgomery extends ModArithmeticDynamic {

				private final long negInv;
				private final long r2, r3;

				private ModArithmeticMontgomery(final int mod) {
					super(mod);
					long inv = 0;
					long s = 1, t = 0;
					for (int i = 0; i < 32; i++) {
						if ((t & 1) == 0) {
							t += mod;
							inv += s;
						}
						t >>= 1;
						s <<= 1;
					}
					final long r = (1l << 32) % mod;
					negInv = inv;
					r2 = r * r % mod;
					r3 = r2 * r % mod;
				}

				private int generate(final long x) {
					return reduce(x * r2);
				}

				private int reduce(long x) {
					x = x + (x * negInv & 0xffff_ffffl) * mod >>> 32;
					return (int) (x < mod ? x : x - mod);
				}

				@Override
				public int remainder(long value) {
					return generate((value %= mod) < 0 ? value + mod : value);
				}

				@Override
				public int mul(final int a, final int b) {
					return reduce((long) a * b);
				}

				@Override
				public int inv(int a) {
					a = super.inv(a);
					return reduce(a * r3);
				}

				@Override
				public int pow(final int a, final long b) {
					return generate(super.pow(a, b));
				}
			}

			static final class ModArithmeticBarrett extends ModArithmeticDynamic {

				private static final long mask = 0xffff_ffffl;
				private final long mh;
				private final long ml;

				private ModArithmeticBarrett(final int mod) {
					super(mod);
					/**
					 * m = floor(2^64/mod) 2^64 = p*mod + q, 2^32 = a*mod + b => (a*mod + b)^2 =
					 * p*mod + q => p = mod*a^2 + 2ab + floor(b^2/mod)
					 */
					final long a = (1l << 32) / mod;
					final long b = (1l << 32) % mod;
					final long m = a * a * mod + 2 * a * b + b * b / mod;
					mh = m >>> 32;
					ml = m & mask;
				}

				private int reduce(long x) {
					long z = (x & mask) * ml;
					z = (x & mask) * mh + (x >>> 32) * ml + (z >>> 32);
					z = (x >>> 32) * mh + (z >>> 32);
					x -= z * mod;
					return (int) (x < mod ? x : x - mod);
				}

				@Override
				public int remainder(long value) {
					return (int) ((value %= mod) < 0 ? value + mod : value);
				}

				@Override
				public int mul(final int a, final int b) {
					return reduce((long) a * b);
				}
			}

			static class ModArithmeticDynamic implements ModArithmetic {

				final int mod;

				public ModArithmeticDynamic(final int mod) {
					this.mod = mod;
				}

				@Override
				public int mod() {
					return mod;
				}

				@Override
				public int remainder(long value) {
					return (int) ((value %= mod) < 0 ? value + mod : value);
				}

				@Override
				public int add(final int a, final int b) {
					final int sum = a + b;
					return sum >= mod ? sum - mod : sum;
				}

				@Override
				public int sub(final int a, final int b) {
					final int sum = a - b;
					return sum < 0 ? sum + mod : sum;
				}

				@Override
				public int mul(final int a, final int b) {
					return (int) ((long) a * b % mod);
				}

				@Override
				public int inv(int a) {
					int b = mod;
					long u = 1, v = 0;
					while (b >= 1) {
						final long t = a / b;
						a -= t * b;
						final int tmp1 = a;
						a = b;
						b = tmp1;
						u -= t * v;
						final long tmp2 = u;
						u = v;
						v = tmp2;
					}
					u %= mod;
					if (a != 1) { throw new ArithmeticException("divide by zero"); }
					return (int) (u < 0 ? u + mod : u);
				}

				@Override
				public int pow(final int a, long b) {
					if (b < 0) throw new ArithmeticException("negative power");
					int res = 1;
					int pow2 = a;
					long idx = 1;
					while (b > 0) {
						final long lsb = b & -b;
						for (; lsb != idx; idx <<= 1) {
							pow2 = mul(pow2, pow2);
						}
						res = mul(res, pow2);
						b ^= lsb;
					}
					return res;
				}
			}
		}
	}

	/**
	 * Convolution.
	 *
	 * @verified https://atcoder.jp/contests/practice2/tasks/practice2_f
	 * @verified https://judge.yosupo.jp/problem/convolution_mod_1000000007
	 */
	public static final class Convolution {
		/**
		 * writer: amotama 勝手に借りてます、問題あったらごめんね
		 */
		private static void fft(double[] a, double[] b, boolean invert) {
			int count = a.length;
			for (int i = 1, j = 0; i < count; i++) {
				int bit = count >> 1;
				for (; j >= bit; bit >>= 1) {
					j -= bit;
				}
				j += bit;
				if (i < j) {
					double temp = a[i];
					a[i] = a[j];
					a[j] = temp;
					temp = b[i];
					b[i] = b[j];
					b[j] = temp;
				}
			}
			for (int len = 2; len <= count; len <<= 1) {
				int halfLen = len >> 1;
				double angle = 2 * Math.PI / len;
				if (invert) {
					angle = -angle;
				}
				double wLenA = Math.cos(angle);
				double wLenB = Math.sin(angle);
				for (int i = 0; i < count; i += len) {
					double wA = 1;
					double wB = 0;
					for (int j = 0; j < halfLen; j++) {
						double uA = a[i + j];
						double uB = b[i + j];
						double vA = a[i + j + halfLen] * wA - b[i + j + halfLen] * wB;
						double vB = a[i + j + halfLen] * wB + b[i + j + halfLen] * wA;
						a[i + j] = uA + vA;
						b[i + j] = uB + vB;
						a[i + j + halfLen] = uA - vA;
						b[i + j + halfLen] = uB - vB;
						double nextWA = wA * wLenA - wB * wLenB;
						wB = wA * wLenB + wB * wLenA;
						wA = nextWA;
					}
				}
			}
			if (invert) {
				for (int i = 0; i < count; i++) {
					a[i] /= count;
					b[i] /= count;
				}
			}
		}

		/**
		 * writer: amotama 勝手に借りてます、問題あったらごめんね
		 */
		public static long[] convolution(long[] a, long[] b) {
			int resultSize = Integer.highestOneBit(Math.max(a.length, b.length) - 1) << 2;
			resultSize = Math.max(resultSize, 1);
			double[] aReal = new double[resultSize];
			double[] aImaginary = new double[resultSize];
			double[] bReal = new double[resultSize];
			double[] bImaginary = new double[resultSize];
			for (int i = 0; i < a.length; i++) aReal[i] = a[i];
			for (int i = 0; i < b.length; i++) bReal[i] = b[i];

			fft(aReal, aImaginary, false);
			if (a == b) {
				System.arraycopy(aReal, 0, bReal, 0, aReal.length);
				System.arraycopy(aImaginary, 0, bImaginary, 0, aImaginary.length);
			} else {
				fft(bReal, bImaginary, false);
			}
			for (int i = 0; i < resultSize; i++) {
				double real = aReal[i] * bReal[i] - aImaginary[i] * bImaginary[i];
				aImaginary[i] = aImaginary[i] * bReal[i] + bImaginary[i] * aReal[i];
				aReal[i] = real;
			}
			fft(aReal, aImaginary, true);
			long[] result = new long[a.length + b.length - 1];
			for (int i = 0; i < result.length; i++) result[i] = Math.round(aReal[i]);
			return result;
		}

		/**
		 * writer: amotama 勝手に借りてます、問題あったらごめんね
		 */
		public static int[] convolution(int[] a, int[] b) {
			int resultSize = Integer.highestOneBit(Math.max(a.length, b.length) - 1) << 2;
			resultSize = Math.max(resultSize, 1);
			double[] aReal = new double[resultSize];
			double[] aImaginary = new double[resultSize];
			double[] bReal = new double[resultSize];
			double[] bImaginary = new double[resultSize];
			for (int i = 0; i < a.length; i++) aReal[i] = a[i];
			for (int i = 0; i < b.length; i++) bReal[i] = b[i];
			fft(aReal, aImaginary, false);
			if (a == b) {
				System.arraycopy(aReal, 0, bReal, 0, aReal.length);
				System.arraycopy(aImaginary, 0, bImaginary, 0, aImaginary.length);
			} else {
				fft(bReal, bImaginary, false);
			}
			for (int i = 0; i < resultSize; i++) {
				double real = aReal[i] * bReal[i] - aImaginary[i] * bImaginary[i];
				aImaginary[i] = aImaginary[i] * bReal[i] + bImaginary[i] * aReal[i];
				aReal[i] = real;
			}
			fft(aReal, aImaginary, true);
			int[] result = new int[a.length + b.length - 1];
			for (int i = 0; i < result.length; i++) result[i] = (int) Math.round(aReal[i]);
			return result;
		}

		public static double[] convolution(double[] a, double[] b) {
			int resultSize = Integer.highestOneBit(Math.max(a.length, b.length) - 1) << 2;
			resultSize = Math.max(resultSize, 1);
			double[] aReal = Arrays.copyOf(a, resultSize);
			double[] aImaginary = new double[resultSize];
			double[] bReal = Arrays.copyOf(b, resultSize);
			double[] bImaginary = new double[resultSize];
			fft(aReal, aImaginary, false);
			if (a == b) {
				System.arraycopy(aReal, 0, bReal, 0, aReal.length);
				System.arraycopy(aImaginary, 0, bImaginary, 0, aImaginary.length);
			} else {
				fft(bReal, bImaginary, false);
			}
			for (int i = 0; i < resultSize; i++) {
				double real = aReal[i] * bReal[i] - aImaginary[i] * bImaginary[i];
				aImaginary[i] = aImaginary[i] * bReal[i] + bImaginary[i] * aReal[i];
				aReal[i] = real;
			}
			fft(aReal, aImaginary, true);
			return Arrays.copyOf(aReal, a.length + b.length - 1);
		}

		/**
		 * Find a primitive root.
		 *
		 * @param m A prime number.
		 * @return Primitive root.
		 */
		private static int primitiveRoot(final int m) {
			if (m == 2) return 1;
			if (m == 167772161) return 3;
			if (m == 469762049) return 3;
			if (m == 754974721) return 11;
			if (m == 998244353) return 3;

			final int[] divs = new int[20];
			divs[0] = 2;
			int cnt = 1;
			int x = (m - 1) / 2;
			while (x % 2 == 0) x /= 2;
			for (int i = 3; (long) i * i <= x; i += 2) {
				if (x % i == 0) {
					divs[cnt++] = i;
					while (x % i == 0) {
						x /= i;
					}
				}
			}
			if (x > 1) {
				divs[cnt++] = x;
			}
			for (int g = 2;; g++) {
				boolean ok = true;
				for (int i = 0; i < cnt; i++) {
					if (MathLib.pow(g, (m - 1) / divs[i], m) == 1) {
						ok = false;
						break;
					}
				}
				if (ok) return g;
			}
		}

		/**
		 * Ceil of power 2.
		 *
		 * @param n Value.
		 * @return Ceil of power 2.
		 */
		private static int ceilPow2(final int n) {
			int x = 0;
			while (1L << x < n) x++;
			return x;
		}

		/**
		 * Garner's algorithm.
		 *
		 * @param c    Mod convolution results.
		 * @param mods Mods.
		 * @return Result.
		 */
		private static long garner(final long[] c, final int[] mods) {
			final int n = c.length + 1;
			final long[] cnst = new long[n];
			final long[] coef = new long[n];
			java.util.Arrays.fill(coef, 1);
			for (int i = 0; i < n - 1; i++) {
				final int m1 = mods[i];
				long v = (c[i] - cnst[i] + m1) % m1;
				v = v * MathLib.pow(coef[i], m1 - 2, m1) % m1;

				for (int j = i + 1; j < n; j++) {
					final long m2 = mods[j];
					cnst[j] = (cnst[j] + coef[j] * v) % m2;
					coef[j] = coef[j] * m1 % m2;
				}
			}
			return cnst[n - 1];
		}

		/**
		 * Garner's algorithm.
		 *
		 * @param c    Mod convolution results.
		 * @param mods Mods.
		 * @return Result.
		 */
		private static int garner(int c0, int c1, int c2, final MathLib.Barrett[] mods) {
			final long[] cnst = new long[4];
			final long[] coef = new long[4];
			java.util.Arrays.fill(coef, 1);
			MathLib.Barrett m1 = mods[0];
			long v = m1.reduce(c0 - cnst[0] + m1.mod);
			v = m1.reduce(v * MathLib.pow(coef[0], m1.mod - 2, m1));
			{
				MathLib.Barrett m2 = mods[1];
				cnst[1] = m2.reduce(cnst[1] + coef[1] * v);
				coef[1] = m2.reduce(coef[1] * m1.mod);
				m2 = mods[2];
				cnst[2] = m2.reduce(cnst[2] + coef[2] * v);
				coef[2] = m2.reduce(coef[2] * m1.mod);
				m2 = mods[3];
				cnst[3] = m2.reduce(cnst[3] + coef[3] * v);
				coef[3] = m2.reduce(coef[3] * m1.mod);
			}
			m1 = mods[1];
			v = m1.reduce(c1 - cnst[1] + m1.mod);
			v = m1.reduce(v * MathLib.pow(coef[1], m1.mod - 2, m1));
			{
				MathLib.Barrett m2 = mods[2];
				cnst[2] = m2.reduce(cnst[2] + coef[2] * v);
				coef[2] = m2.reduce(coef[2] * m1.mod);
				m2 = mods[3];
				cnst[3] = m2.reduce(cnst[3] + coef[3] * v);
				coef[3] = m2.reduce(coef[3] * m1.mod);
			}
			m1 = mods[2];
			v = m1.reduce(c2 - cnst[2] + m1.mod);
			v = m1.reduce(v * MathLib.pow(coef[2], m1.mod - 2, m1));
			{
				MathLib.Barrett m2 = mods[3];
				cnst[3] = m2.reduce(cnst[3] + coef[3] * v);
				coef[3] = m2.reduce(coef[3] * m1.mod);
			}
			return (int) cnst[3];
		}

		/**
		 * Garner's algorithm.
		 *
		 * @param c    Mod convolution results.
		 * @param mods Mods.
		 * @return Result.
		 */
		private static int garner1_000_000_007(int c0, int c1, int c2) {
			final long[] cnst = new long[4];
			final long[] coef = new long[4];
			java.util.Arrays.fill(coef, 1);
			long v = (c0 - cnst[0] + 998_244_353) % 998_244_353;
			v = v * MathLib.pow998_244_353(coef[0], 998_244_353 - 2) % 998_244_353;
			{
				cnst[1] = (cnst[1] + coef[1] * v) % 167_772_161;
				coef[1] = coef[1] * 998_244_353 % 167_772_161;
				cnst[2] = (cnst[2] + coef[2] * v) % 469_762_049;
				coef[2] = coef[2] * 998_244_353 % 469_762_049;
				cnst[3] = (cnst[3] + coef[3] * v) % 1_000_000_007;
				coef[3] = coef[3] * 998_244_353 % 1_000_000_007;
			}
			v = (c1 - cnst[1] + 167_772_161) % 167_772_161;
			v = v * MathLib.pow167_772_161(coef[1], 167_772_161 - 2) % 167_772_161;
			{
				cnst[2] = (cnst[2] + coef[2] * v) % 469_762_049;
				coef[2] = coef[2] * 167_772_161 % 469_762_049;
				cnst[3] = (cnst[3] + coef[3] * v) % 1_000_000_007;
				coef[3] = coef[3] * 167_772_161 % 1_000_000_007;
			}
			v = (c2 - cnst[2] + 469_762_049) % 469_762_049;
			v = v * MathLib.pow469_762_049(coef[2], 469_762_049 - 2) % 469_762_049;
			{
				cnst[3] = (cnst[3] + coef[3] * v) % 1_000_000_007;
				coef[3] = coef[3] * 469_762_049 % 1_000_000_007;
			}
			return (int) cnst[3];
		}

		/**
		 * Pre-calculation for NTT.
		 *
		 * @param mod NTT Prime.
		 * @param g   Primitive root of mod.
		 * @return Pre-calculation table.
		 */
		private static long[] sumE(final int mod, final int g) {
			final long[] sum_e = new long[30];
			final long[] es = new long[30];
			final long[] ies = new long[30];
			final int cnt2 = Integer.numberOfTrailingZeros(mod - 1);
			long e = MathLib.pow(g, mod - 1 >> cnt2, mod);
			long ie = MathLib.pow(e, mod - 2, mod);
			for (int i = cnt2; i >= 2; i--) {
				es[i - 2] = e;
				ies[i - 2] = ie;
				e = e * e % mod;
				ie = ie * ie % mod;
			}
			long now = 1;
			for (int i = 0; i < cnt2 - 2; i++) {
				sum_e[i] = es[i] * now % mod;
				now = now * ies[i] % mod;
			}
			return sum_e;
		}

		/**
		 * Pre-calculation for inverse NTT.
		 *
		 * @param mod Mod.
		 * @param g   Primitive root of mod.
		 * @return Pre-calculation table.
		 */
		private static long[] sumIE(final int mod, final int g) {
			final long[] sum_ie = new long[30];
			final long[] es = new long[30];
			final long[] ies = new long[30];

			final int cnt2 = Integer.numberOfTrailingZeros(mod - 1);
			long e = MathLib.pow(g, mod - 1 >> cnt2, mod);
			long ie = MathLib.pow(e, mod - 2, mod);
			for (int i = cnt2; i >= 2; i--) {
				es[i - 2] = e;
				ies[i - 2] = ie;
				e = e * e % mod;
				ie = ie * ie % mod;
			}
			long now = 1;
			for (int i = 0; i < cnt2 - 2; i++) {
				sum_ie[i] = ies[i] * now % mod;
				now = now * es[i] % mod;
			}
			return sum_ie;
		}

		/**
		 * Inverse NTT.
		 *
		 * @param a     Target array.
		 * @param sumIE Pre-calculation table.
		 * @param mod   NTT Prime.
		 */
		private static void butterflyInv(final long[] a, final long[] sumIE, final int mod) {
			final int n = a.length;
			final int h = ceilPow2(n);

			for (int ph = h; ph >= 1; ph--) {
				final int w = 1 << ph - 1, p = 1 << h - ph;
				long inow = 1;
				for (int s = 0; s < w; s++) {
					final int offset = s << h - ph + 1;
					for (int i = 0; i < p; i++) {
						final long l = a[i + offset];
						final long r = a[i + offset + p];
						a[i + offset] = (l + r) % mod;
						a[i + offset + p] = (mod + l - r) * inow % mod;
					}
					final int x = Integer.numberOfTrailingZeros(~s);
					inow = inow * sumIE[x] % mod;
				}
			}
		}

		/**
		 * Inverse NTT.
		 *
		 * @param a    Target array.
		 * @param sumE Pre-calculation table.
		 * @param mod  NTT Prime.
		 */
		private static void butterfly(final long[] a, final long[] sumE, final int mod) {
			final int n = a.length;
			final int h = ceilPow2(n);

			for (int ph = 1; ph <= h; ph++) {
				final int w = 1 << ph - 1, p = 1 << h - ph;
				long now = 1;
				for (int s = 0; s < w; s++) {
					final int offset = s << h - ph + 1;
					for (int i = 0; i < p; i++) {
						final long l = a[i + offset];
						final long r = a[i + offset + p] * now % mod;
						a[i + offset] = (l + r) % mod;
						a[i + offset + p] = (l - r + mod) % mod;
					}
					final int x = Integer.numberOfTrailingZeros(~s);
					now = now * sumE[x] % mod;
				}
			}
		}

		/**
		 * Inverse NTT used mod 998_244_353.
		 *
		 * @param a     Target array.
		 * @param sumIE Pre-calculation table.
		 */
		private static void butterflyInv998_244_353(final int[] a, final int[] sumIE) {
			final int n = a.length;
			final int h = ceilPow2(n);

			for (int ph = h; ph >= 1; ph--) {
				final int w = 1 << ph - 1, p = 1 << h - ph;
				long inow = 1;
				for (int s = 0; s < w; s++) {
					final int offset = s << h - ph + 1;
					for (int i = 0; i < p; i++) {
						final long l = a[i + offset];
						final long r = a[i + offset + p];
						a[i + offset] = (int) ((l + r) % 998_244_353);
						a[i + offset + p] = (int) ((998_244_353 + l - r) * inow % 998_244_353);
					}
					final int x = Integer.numberOfTrailingZeros(~s);
					inow = inow * sumIE[x] % 998_244_353;
				}
			}
		}

		/**
		 * Inverse NTT used mod 167_772_161.
		 *
		 * @param a     Target array.
		 * @param sumIE Pre-calculation table.
		 */
		private static void butterflyInv167_772_161(final int[] a, final int[] sumIE) {
			final int n = a.length;
			final int h = ceilPow2(n);

			for (int ph = h; ph >= 1; ph--) {
				final int w = 1 << ph - 1, p = 1 << h - ph;
				long inow = 1;
				for (int s = 0; s < w; s++) {
					final int offset = s << h - ph + 1;
					for (int i = 0; i < p; i++) {
						final long l = a[i + offset];
						final long r = a[i + offset + p];
						a[i + offset] = (int) ((l + r) % 167_772_161);
						a[i + offset + p] = (int) ((167_772_161 + l - r) * inow % 167_772_161);
					}
					final int x = Integer.numberOfTrailingZeros(~s);
					inow = inow * sumIE[x] % 167_772_161;
				}
			}
		}

		/**
		 * Inverse NTT used mod 469_762_049.
		 *
		 * @param a     Target array.
		 * @param sumIE Pre-calculation table.
		 */
		private static void butterflyInv469_762_049(final int[] a, final int[] sumIE) {
			final int n = a.length;
			final int h = ceilPow2(n);

			for (int ph = h; ph >= 1; ph--) {
				final int w = 1 << ph - 1, p = 1 << h - ph;
				long inow = 1;
				for (int s = 0; s < w; s++) {
					final int offset = s << h - ph + 1;
					for (int i = 0; i < p; i++) {
						final long l = a[i + offset];
						final long r = a[i + offset + p];
						a[i + offset] = (int) ((l + r) % 469_762_049);
						a[i + offset + p] = (int) ((469_762_049 + l - r) * inow % 469_762_049);
					}
					final int x = Integer.numberOfTrailingZeros(~s);
					inow = inow * sumIE[x] % 469_762_049;
				}
			}
		}

		/**
		 * Inverse NTT.
		 *
		 * @param a     Target array.
		 * @param sumIE Pre-calculation table.
		 * @param mod   NTT Prime.
		 */
		private static void butterflyInv(final int[] a, final int[] sumIE, final MathLib.Barrett mod) {
			final int n = a.length;
			final int h = ceilPow2(n);

			for (int ph = h; ph >= 1; ph--) {
				final int w = 1 << ph - 1, p = 1 << h - ph;
				long inow = 1;
				for (int s = 0; s < w; s++) {
					final int offset = s << h - ph + 1;
					for (int i = 0; i < p; i++) {
						final long l = a[i + offset];
						final long r = a[i + offset + p];
						long sum = l + r;
						if (sum >= mod.mod) sum -= mod.mod;
						a[i + offset] = (int) sum;
						a[i + offset + p] = mod.reduce((mod.mod + l - r) * inow);
					}
					final int x = Integer.numberOfTrailingZeros(~s);
					inow = mod.reduce(inow * sumIE[x]);
				}
			}
		}

		/**
		 * Inverse NTT used mod 998_244_353.
		 *
		 * @param a    Target array.
		 * @param sumE Pre-calculation table.
		 * @param mod  NTT Prime.
		 */
		private static void butterfly998_244_353(final int[] a, final int[] sumE) {
			final int n = a.length;
			final int h = ceilPow2(n);
			final long ADD = (long) (998_244_353 - 2) * 998_244_353;

			for (int ph = 1; ph <= h; ph++) {
				final int w = 1 << ph - 1, p = 1 << h - ph;
				long now = 1;
				for (int s = 0; s < w; s++) {
					final int offset = s << h - ph + 1;
					for (int i = 0; i < p; i++) {
						final long l = a[i + offset];
						final long r = a[i + offset + p] * now;
						a[i + offset] = (int) ((l + r) % 998_244_353);
						a[i + offset + p] = (int) ((l - r + ADD) % 998_244_353);
					}
					final int x = Integer.numberOfTrailingZeros(~s);
					now = now * sumE[x] % 998_244_353;
				}
			}
		}

		/**
		 * Inverse NTT used mod 167_772_161.
		 *
		 * @param a    Target array.
		 * @param sumE Pre-calculation table.
		 * @param mod  NTT Prime.
		 */
		private static void butterfly167_772_161(final int[] a, final int[] sumE) {
			final int n = a.length;
			final int h = ceilPow2(n);
			final long ADD = (long) (167_772_161 - 2) * 167_772_161;

			for (int ph = 1; ph <= h; ph++) {
				final int w = 1 << ph - 1, p = 1 << h - ph;
				long now = 1;
				for (int s = 0; s < w; s++) {
					final int offset = s << h - ph + 1;
					for (int i = 0; i < p; i++) {
						final long l = a[i + offset];
						final long r = a[i + offset + p] * now;
						a[i + offset] = (int) ((l + r) % 167_772_161);
						a[i + offset + p] = (int) ((l - r + ADD) % 167_772_161);
					}
					final int x = Integer.numberOfTrailingZeros(~s);
					now = now * sumE[x] % 167_772_161;
				}
			}
		}

		/**
		 * Inverse NTT used mod 469_762_049.
		 *
		 * @param a    Target array.
		 * @param sumE Pre-calculation table.
		 * @param mod  NTT Prime.
		 */
		private static void butterfly469_762_049(final int[] a, final int[] sumE) {
			final int n = a.length;
			final int h = ceilPow2(n);
			final long ADD = (long) (469_762_049 - 2) * 469_762_049;

			for (int ph = 1; ph <= h; ph++) {
				final int w = 1 << ph - 1, p = 1 << h - ph;
				long now = 1;
				for (int s = 0; s < w; s++) {
					final int offset = s << h - ph + 1;
					for (int i = 0; i < p; i++) {
						final long l = a[i + offset];
						final long r = a[i + offset + p] * now;
						a[i + offset] = (int) ((l + r) % 469_762_049);
						a[i + offset + p] = (int) ((l - r + ADD) % 469_762_049);
					}
					final int x = Integer.numberOfTrailingZeros(~s);
					now = now * sumE[x] % 469_762_049;
				}
			}
		}

		/**
		 * Inverse NTT.
		 *
		 * @param a    Target array.
		 * @param sumE Pre-calculation table.
		 * @param mod  NTT Prime.
		 */
		private static void butterfly(final int[] a, final int[] sumE, final MathLib.Barrett mod) {
			final int n = a.length;
			final int h = ceilPow2(n);
			final long ADD = (long) (mod.mod - 2) * mod.mod;

			for (int ph = 1; ph <= h; ph++) {
				final int w = 1 << ph - 1, p = 1 << h - ph;
				long now = 1;
				for (int s = 0; s < w; s++) {
					final int offset = s << h - ph + 1;
					for (int i = 0; i < p; i++) {
						final long l = a[i + offset];
						final long r = a[i + offset + p] * now;
						a[i + offset] = mod.reduce(l + r);
						a[i + offset + p] = mod.reduce(l - r + ADD);
					}
					final int x = Integer.numberOfTrailingZeros(~s);
					now = mod.reduce(now * sumE[x]);
				}
			}
		}

		/**
		 * Convolution used mod 998_244_353.
		 *
		 * @param a   Target array 1.
		 * @param b   Target array 2.
		 * @return Answer.
		 */
		private static int[] convolution998_244_353(int[] a, int[] b) {
			final int n = a.length;
			final int m = b.length;
			if (n == 0 || m == 0) return new int[0];

			final int z = 1 << ceilPow2(n + m - 1);
			{
				final int[] na = new int[z];
				final int[] nb = new int[z];
				System.arraycopy(a, 0, na, 0, n);
				System.arraycopy(b, 0, nb, 0, m);
				a = na;
				b = nb;
			}

			final int g = primitiveRoot(998_244_353);
			final int[] sume;
			{
				long[] s = sumE(998_244_353, g);
				sume = new int[s.length];
				for (int i = 0; i < s.length; ++i) sume[i] = (int) s[i];
			}
			final int[] sumie;
			{
				long[] s = sumIE(998_244_353, g);
				sumie = new int[s.length];
				for (int i = 0; i < s.length; ++i) sumie[i] = (int) s[i];
			}

			butterfly998_244_353(a, sume);
			butterfly998_244_353(b, sume);
			for (int i = 0; i < z; i++) a[i] = (int) ((long) a[i] * b[i] % 998_244_353);
			butterflyInv998_244_353(a, sumie);
			a = java.util.Arrays.copyOf(a, n + m - 1);

			final long iz = MathLib.pow998_244_353(z, 998_244_353 - 2);
			for (int i = 0; i < n + m - 1; i++) a[i] = (int) (a[i] * iz % 998_244_353);
			return a;
		}

		/**
		 * Convolution used mod 167_772_161.
		 *
		 * @param a   Target array 1.
		 * @param b   Target array 2.
		 * @return Answer.
		 */
		private static int[] convolution167_772_161(int[] a, int[] b) {
			final int n = a.length;
			final int m = b.length;
			if (n == 0 || m == 0) return new int[0];

			final int z = 1 << ceilPow2(n + m - 1);
			{
				final int[] na = new int[z];
				final int[] nb = new int[z];
				System.arraycopy(a, 0, na, 0, n);
				System.arraycopy(b, 0, nb, 0, m);
				a = na;
				b = nb;
			}

			final int g = primitiveRoot(167_772_161);
			final int[] sume;
			{
				long[] s = sumE(167_772_161, g);
				sume = new int[s.length];
				for (int i = 0; i < s.length; ++i) sume[i] = (int) s[i];
			}
			final int[] sumie;
			{
				long[] s = sumIE(167_772_161, g);
				sumie = new int[s.length];
				for (int i = 0; i < s.length; ++i) sumie[i] = (int) s[i];
			}

			butterfly167_772_161(a, sume);
			butterfly167_772_161(b, sume);
			for (int i = 0; i < z; i++) a[i] = (int) ((long) a[i] * b[i] % 167_772_161);
			butterflyInv167_772_161(a, sumie);
			a = java.util.Arrays.copyOf(a, n + m - 1);

			final long iz = MathLib.pow167_772_161(z, 167_772_161 - 2);
			for (int i = 0; i < n + m - 1; i++) a[i] = (int) (a[i] * iz % 167_772_161);
			return a;
		}

		/**
		 * Convolution used mod 469_762_049.
		 *
		 * @param a   Target array 1.
		 * @param b   Target array 2.
		 * @return Answer.
		 */
		private static int[] convolution469_762_049(int[] a, int[] b) {
			final int n = a.length;
			final int m = b.length;
			if (n == 0 || m == 0) return new int[0];

			final int z = 1 << ceilPow2(n + m - 1);
			{
				final int[] na = new int[z];
				final int[] nb = new int[z];
				System.arraycopy(a, 0, na, 0, n);
				System.arraycopy(b, 0, nb, 0, m);
				a = na;
				b = nb;
			}

			final int g = primitiveRoot(469_762_049);
			final int[] sume;
			{
				long[] s = sumE(469_762_049, g);
				sume = new int[s.length];
				for (int i = 0; i < s.length; ++i) sume[i] = (int) s[i];
			}
			final int[] sumie;
			{
				long[] s = sumIE(469_762_049, g);
				sumie = new int[s.length];
				for (int i = 0; i < s.length; ++i) sumie[i] = (int) s[i];
			}

			butterfly469_762_049(a, sume);
			butterfly469_762_049(b, sume);
			for (int i = 0; i < z; i++) a[i] = (int) ((long) a[i] * b[i] % 469_762_049);
			butterflyInv469_762_049(a, sumie);
			a = java.util.Arrays.copyOf(a, n + m - 1);

			final long iz = MathLib.pow469_762_049(z, 469_762_049 - 2);
			for (int i = 0; i < n + m - 1; i++) a[i] = (int) (a[i] * iz % 469_762_049);
			return a;
		}

		/**
		 * Convolution.
		 *
		 * @param a   Target array 1.
		 * @param b   Target array 2.
		 * @param mod NTT Prime.
		 * @return Answer.
		 */
		private static int[] convolutionNTT(int[] a, int[] b, final int mod) {
			MathLib.Barrett barrett = new MathLib.Barrett(mod);
			final int n = a.length;
			final int m = b.length;
			if (n == 0 || m == 0) return new int[0];

			final int z = 1 << ceilPow2(n + m - 1);
			{
				final int[] na = new int[z];
				final int[] nb = new int[z];
				System.arraycopy(a, 0, na, 0, n);
				System.arraycopy(b, 0, nb, 0, m);
				a = na;
				b = nb;
			}

			final int g = primitiveRoot(mod);
			final int[] sume;
			{
				long[] s = sumE(mod, g);
				sume = new int[s.length];
				for (int i = 0; i < s.length; ++i) sume[i] = (int) s[i];
			}
			final int[] sumie;
			{
				long[] s = sumIE(mod, g);
				sumie = new int[s.length];
				for (int i = 0; i < s.length; ++i) sumie[i] = (int) s[i];
			}

			butterfly(a, sume, barrett);
			butterfly(b, sume, barrett);
			for (int i = 0; i < z; i++) a[i] = barrett.reduce((long) a[i] * b[i]);
			butterflyInv(a, sumie, barrett);
			a = java.util.Arrays.copyOf(a, n + m - 1);

			final long iz = MathLib.pow(z, mod - 2, mod);
			for (int i = 0; i < n + m - 1; i++) a[i] = barrett.reduce(a[i] * iz);
			return a;
		}

		/**
		 * Convolution.
		 *
		 * @param a   Target array 1.
		 * @param b   Target array 2.
		 * @param mod NTT Prime.
		 * @return Answer.
		 */
		private static long[] convolutionNTT(long[] a, long[] b, final int mod) {
			final int n = a.length;
			final int m = b.length;
			if (n == 0 || m == 0) return new long[0];

			final int z = 1 << ceilPow2(n + m - 1);
			{
				final long[] na = new long[z];
				final long[] nb = new long[z];
				System.arraycopy(a, 0, na, 0, n);
				System.arraycopy(b, 0, nb, 0, m);
				a = na;
				b = nb;
			}

			final int g = primitiveRoot(mod);
			final long[] sume = sumE(mod, g);
			final long[] sumie = sumIE(mod, g);

			butterfly(a, sume, mod);
			butterfly(b, sume, mod);
			for (int i = 0; i < z; i++) {
				a[i] = a[i] * b[i] % mod;
			}
			butterflyInv(a, sumie, mod);
			a = java.util.Arrays.copyOf(a, n + m - 1);

			final long iz = MathLib.pow(z, mod - 2, mod);
			for (int i = 0; i < n + m - 1; i++) a[i] = a[i] * iz % mod;
			return a;
		}

		/**
		 * Convolution.
		 *
		 * @param a   Target array 1.
		 * @param b   Target array 2.
		 * @param mod Any mod.
		 * @return Answer.
		 */
		public static long[] convolution(final long[] a, final long[] b, final int mod) {
			final int n = a.length;
			final int m = b.length;
			if (n == 0 || m == 0) return new long[0];

			final int mod1 = 998_244_353;
			final int mod2 = 167_772_161;
			final int mod3 = 469_762_049;

			final long[] c1 = convolutionNTT(a, b, mod1);
			final long[] c2 = convolutionNTT(a, b, mod2);
			final long[] c3 = convolutionNTT(a, b, mod3);

			final int retSize = c1.length;
			final long[] ret = new long[retSize];
			final int[] mods = { mod1, mod2, mod3, mod };
			for (int i = 0; i < retSize; ++i) {
				ret[i] = garner(new long[] { c1[i], c2[i], c3[i] }, mods);
			}
			return ret;
		}

		/**
		 * Convolution.
		 *
		 * @param a   Target array 1.
		 * @param b   Target array 2.
		 * @param mod Any mod.
		 * @return Answer.
		 */
		public static int[] convolution(final int[] a, final int[] b, final int mod) {
			final int n = a.length;
			final int m = b.length;
			if (n == 0 || m == 0) return new int[0];
			if (mod == 1_000_000_007) return convolution1_000_000_007(a, b);
			if (mod == 998_244_353) return convolution998_244_353(a, b);
			int ntt = Integer.lowestOneBit(mod - 1) >> 1;
			if (n + m <= ntt) return convolutionNTT(a, b, mod);

			final int[] c1 = convolution998_244_353(a, b);
			final int[] c2 = convolution167_772_161(a, b);
			final int[] c3 = convolution469_762_049(a, b);

			final int retSize = c1.length;
			final int[] ret = new int[retSize];
			final MathLib.Barrett[] mods = { new MathLib.Barrett(998_244_353), new MathLib.Barrett(167_772_161),
					new MathLib.Barrett(469_762_049), new MathLib.Barrett(mod) };
			for (int i = 0; i < retSize; ++i) ret[i] = garner(c1[i], c2[i], c3[i], mods);
			return ret;
		}

		/**
		 * Convolution used mod 1_000_000_007.
		 *
		 * @param a   Target array 1.
		 * @param b   Target array 2.
		 * @return Answer.
		 */
		private static int[] convolution1_000_000_007(final int[] a, final int[] b) {

			final int[] c1 = convolution998_244_353(a, b);
			final int[] c2 = convolution167_772_161(a, b);
			final int[] c3 = convolution469_762_049(a, b);

			final int retSize = c1.length;
			final int[] ret = new int[retSize];
			for (int i = 0; i < retSize; ++i) ret[i] = garner1_000_000_007(c1[i], c2[i], c3[i]);
			return ret;
		}

		/**
		 * Convolution. need: length < 2000
		 *
		 * @param a   Target array 1.
		 * @param b   Target array 2.
		 * @param mod Any mod.
		 * @return Answer.
		 */
		public static int[] convolution2(final int[] a, final int[] b, final int mod) {
			if (Math.max(a.length, b.length) < 4000) {
				long[] la = new long[a.length], ha = new long[a.length], ma = new long[a.length],
						lb = new long[b.length], hb = new long[b.length], mb = new long[b.length];
				MathLib.Barrett barrett = new MathLib.Barrett(mod);
				for (int i = 0; i < a.length; ++i) {
					ha[i] = a[i] >> 15;
					la[i] = a[i] & 0x7FFF;
					ma[i] = la[i] + ha[i];
				}
				for (int i = 0; i < b.length; ++i) {
					hb[i] = b[i] >> 15;
					lb[i] = b[i] & 0x7FFF;
					mb[i] = lb[i] + hb[i];
				}
				long[] l = convolution(la, lb), h = convolution(ha, hb), m = convolution(ma, mb);
				int[] ret = new int[m.length];
				for (int i = 0; i < m.length; ++i) {
					h[i] = barrett.reduce(h[i]);
					m[i] = barrett.reduce(m[i] - l[i] - h[i] + (long) m.length * mod);
					ret[i] = barrett.reduce((h[i] << 30) + (m[i] << 15) + l[i]);
				}
				return ret;
			}
			return convolution(a, b, mod);
		}

		/**
		 * Naive convolution. (Complexity is O(N^2)!!)
		 *
		 * @param a   Target array 1.
		 * @param b   Target array 2.
		 * @param mod Mod.
		 * @return Answer.
		 */
		public static long[] convolutionNaive(final long[] a, final long[] b, final int mod) {
			final int n = a.length;
			final int m = b.length;
			final int k = n + m - 1;
			final long[] ret = new long[k];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					ret[i + j] += a[i] * b[j] % mod;
					ret[i + j] %= mod;
				}
			}
			return ret;
		}
	}

	/**
	 * @verified https://atcoder.jp/contests/practice2/tasks/practice2_g
	 */
	public static final class SCC {

		static class Edge {

			int from, to;

			public Edge(final int from, final int to) {
				this.from = from;
				this.to = to;
			}
		}

		final int n;
		int m;
		final java.util.ArrayList<Edge> unorderedEdges;
		final int[] start;
		final int[] ids;
		boolean hasBuilt = false;

		public SCC(final int n) {
			this.n = n;
			unorderedEdges = new java.util.ArrayList<>();
			start = new int[n + 1];
			ids = new int[n];
		}

		public void addEdge(final int from, final int to) {
			rangeCheck(from);
			rangeCheck(to);
			unorderedEdges.add(new Edge(from, to));
			start[from + 1]++;
			m++;
		}

		public int id(final int i) {
			if (!hasBuilt) { throw new UnsupportedOperationException("Graph hasn't been built."); }
			rangeCheck(i);
			return ids[i];
		}

		public int[][] build() {
			for (int i = 1; i <= n; i++) {
				start[i] += start[i - 1];
			}
			final Edge[] orderedEdges = new Edge[m];
			final int[] count = new int[n + 1];
			System.arraycopy(start, 0, count, 0, n + 1);
			for (final Edge e : unorderedEdges) {
				orderedEdges[count[e.from]++] = e;
			}
			int nowOrd = 0;
			int groupNum = 0;
			int k = 0;
			// parent
			final int[] par = new int[n];
			final int[] vis = new int[n];
			final int[] low = new int[n];
			final int[] ord = new int[n];
			java.util.Arrays.fill(ord, -1);
			// u = lower32(stack[i]) : visiting vertex
			// j = upper32(stack[i]) : jth child
			final long[] stack = new long[n];
			// size of stack
			int ptr = 0;
			// non-recursional DFS
			for (int i = 0; i < n; i++) {
				if (ord[i] >= 0) continue;
				par[i] = -1;
				// vertex i, 0th child.
				stack[ptr++] = 0l << 32 | i;
				// stack is not empty
				while (ptr > 0) {
					// last element
					final long p = stack[--ptr];
					// vertex
					final int u = (int) (p & 0xffff_ffffl);
					// jth child
					int j = (int) (p >>> 32);
					if (j == 0) { // first visit
						low[u] = ord[u] = nowOrd++;
						vis[k++] = u;
					}
					if (start[u] + j < count[u]) { // there are more children
						// jth child
						final int to = orderedEdges[start[u] + j].to;
						// incr children counter
						stack[ptr++] += 1l << 32;
						if (ord[to] == -1) { // new vertex
							stack[ptr++] = 0l << 32 | to;
							par[to] = u;
						} else { // backward edge
							low[u] = Math.min(low[u], ord[to]);
						}
					} else { // no more children (leaving)
						while (j-- > 0) {
							final int to = orderedEdges[start[u] + j].to;
							// update lowlink
							if (par[to] == u) low[u] = Math.min(low[u], low[to]);
						}
						if (low[u] == ord[u]) { // root of a component
							while (true) { // gathering verticies
								final int v = vis[--k];
								ord[v] = n;
								ids[v] = groupNum;
								if (v == u) break;
							}
							groupNum++; // incr the number of components
						}
					}
				}
			}
			for (int i = 0; i < n; i++) {
				ids[i] = groupNum - 1 - ids[i];
			}

			final int[] counts = new int[groupNum];
			for (final int x : ids) counts[x]++;
			final int[][] groups = new int[groupNum][];
			for (int i = 0; i < groupNum; i++) {
				groups[i] = new int[counts[i]];
			}
			for (int i = 0; i < n; i++) {
				final int cmp = ids[i];
				groups[cmp][--counts[cmp]] = i;
			}
			hasBuilt = true;
			return groups;
		}

		private void rangeCheck(final int i) {
			if (i < 0 || i >= n) {
				throw new IndexOutOfBoundsException(String.format("Index %d out of bounds for length %d", i, n));
			}
		}
	}

	/**
	 * @verified https://atcoder.jp/contests/practice2/submissions/16647102
	 */
	public static final class TwoSAT {
		private final int n;
		private final InternalSCC scc;
		private final boolean[] answer;

		private boolean hasCalledSatisfiable = false;
		private boolean existsAnswer = false;

		public TwoSAT(int n) {
			this.n = n;
			scc = new InternalSCC(2 * n);
			answer = new boolean[n];
		}

		public void addClause(int x, boolean f, int y, boolean g) {
			rangeCheck(x);
			rangeCheck(y);
			scc.addEdge(x << 1 | (f ? 0 : 1), y << 1 | (g ? 1 : 0));
			scc.addEdge(y << 1 | (g ? 0 : 1), x << 1 | (f ? 1 : 0));
		}

		public void addImplication(int x, boolean f, int y, boolean g) {
			addClause(x, !f, y, g);
		}

		public void addNand(int x, boolean f, int y, boolean g) {
			addClause(x, !f, y, !g);
		}

		public void set(int x, boolean f) {
			addClause(x, f, x, f);
		}

		public boolean satisfiable() {
			hasCalledSatisfiable = true;
			int[] ids = scc.ids();
			for (int i = 0; i < n; i++) {
				if (ids[i << 1 | 0] == ids[i << 1 | 1]) return existsAnswer = false;
				answer[i] = ids[i << 1 | 0] < ids[i << 1 | 1];
			}
			return existsAnswer = true;
		}

		public boolean[] answer() {
			if (!hasCalledSatisfiable) {
				throw new UnsupportedOperationException("Call TwoSAT#satisfiable at least once before TwoSAT#answer.");
			}
			if (existsAnswer) return answer;
			return null;
		}

		private void rangeCheck(int x) {
			if (x < 0 || x >= n) {
				throw new IndexOutOfBoundsException(String.format("Index %d out of bounds for length %d", x, n));
			}
		}

		private static final class EdgeList {
			long[] a;
			int ptr = 0;

			EdgeList(int cap) {
				a = new long[cap];
			}

			void add(int upper, int lower) {
				if (ptr == a.length) grow();
				a[ptr++] = (long) upper << 32 | lower;
			}

			void grow() {
				long[] b = new long[a.length << 1];
				System.arraycopy(a, 0, b, 0, a.length);
				a = b;
			}
		}

		private static final class InternalSCC {
			final int n;
			int m;
			final EdgeList unorderedEdges;
			final int[] start;

			InternalSCC(int n) {
				this.n = n;
				unorderedEdges = new EdgeList(n);
				start = new int[n + 1];
			}

			void addEdge(int from, int to) {
				unorderedEdges.add(from, to);
				start[from + 1]++;
				m++;
			}

			static final long mask = 0xffff_ffffl;

			int[] ids() {
				for (int i = 1; i <= n; i++) {
					start[i] += start[i - 1];
				}
				int[] orderedEdges = new int[m];
				int[] count = new int[n + 1];
				System.arraycopy(start, 0, count, 0, n + 1);
				for (int i = 0; i < m; i++) {
					long e = unorderedEdges.a[i];
					orderedEdges[count[(int) (e >>> 32)]++] = (int) (e & mask);
				}
				int nowOrd = 0;
				int groupNum = 0;
				int k = 0;
				int[] par = new int[n];
				int[] vis = new int[n];
				int[] low = new int[n];
				int[] ord = new int[n];
				java.util.Arrays.fill(ord, -1);
				int[] ids = new int[n];
				long[] stack = new long[n];
				int ptr = 0;

				for (int i = 0; i < n; i++) {
					if (ord[i] >= 0) continue;
					par[i] = -1;
					stack[ptr++] = i;
					while (ptr > 0) {
						long p = stack[--ptr];
						int u = (int) (p & mask);
						int j = (int) (p >>> 32);
						if (j == 0) {
							low[u] = ord[u] = nowOrd++;
							vis[k++] = u;
						}
						if (start[u] + j < count[u]) {
							int to = orderedEdges[start[u] + j];
							stack[ptr++] += 1l << 32;
							if (ord[to] == -1) {
								stack[ptr++] = to;
								par[to] = u;
							} else {
								low[u] = Math.min(low[u], ord[to]);
							}
						} else {
							while (j-- > 0) {
								int to = orderedEdges[start[u] + j];
								if (par[to] == u) low[u] = Math.min(low[u], low[to]);
							}
							if (low[u] == ord[u]) {
								while (true) {
									int v = vis[--k];
									ord[v] = n;
									ids[v] = groupNum;
									if (v == u) break;
								}
								groupNum++;
							}
						}
					}
				}
				for (int i = 0; i < n; i++) {
					ids[i] = groupNum - 1 - ids[i];
				}
				return ids;
			}
		}
	}

	public static final class StringAlgorithm {

		private static int[] saNaive(final int[] s) {
			final int n = s.length;
			final Integer[] _sa = new Integer[n];
			for (int i = 0; i < n; i++) {
				_sa[i] = i;
			}
			java.util.Arrays.sort(_sa, (l, r) -> {
				while (l < n && r < n) {
					if (s[l] != s[r]) return s[l] - s[r];
					l++;
					r++;
				}
				return -(l - r);
			});
			final int[] sa = new int[n];
			for (int i = 0; i < n; i++) {
				sa[i] = _sa[i];
			}
			return sa;
		}

		private static int[] saDoubling(final int[] s) {
			final int n = s.length;
			final Integer[] _sa = new Integer[n];
			for (int i = 0; i < n; i++) {
				_sa[i] = i;
			}
			int[] rnk = s;
			int[] tmp = new int[n];

			for (int k = 1; k < n; k *= 2) {
				final int _k = k;
				final int[] _rnk = rnk;
				final java.util.Comparator<Integer> cmp = (x, y) -> {
					if (_rnk[x] != _rnk[y]) return _rnk[x] - _rnk[y];
					final int rx = x + _k < n ? _rnk[x + _k] : -1;
					final int ry = y + _k < n ? _rnk[y + _k] : -1;
					return rx - ry;
				};
				java.util.Arrays.sort(_sa, cmp);
				tmp[_sa[0]] = 0;
				for (int i = 1; i < n; i++) {
					tmp[_sa[i]] = tmp[_sa[i - 1]] + (cmp.compare(_sa[i - 1], _sa[i]) < 0 ? 1 : 0);
				}
				final int[] buf = tmp;
				tmp = rnk;
				rnk = buf;
			}

			final int[] sa = new int[n];
			for (int i = 0; i < n; i++) {
				sa[i] = _sa[i];
			}
			return sa;
		}

		private static final int THRESHOLD_NAIVE = 10;
		private static final int THRESHOLD_DOUBLING = 40;

		private static int[] sais(final int[] s, final int upper) {
			final int n = s.length;
			if (n == 0) return new int[0];
			if (n == 1) return new int[] { 0 };
			if (n == 2) { return s[0] < s[1] ? new int[] { 0, 1 } : new int[] { 1, 0 }; }
			if (n < THRESHOLD_NAIVE) { return saNaive(s); }
			if (n < THRESHOLD_DOUBLING) { return saDoubling(s); }

			final int[] sa = new int[n];
			final boolean[] ls = new boolean[n];
			for (int i = n - 2; i >= 0; i--) {
				ls[i] = s[i] == s[i + 1] ? ls[i + 1] : s[i] < s[i + 1];
			}

			final int[] sumL = new int[upper + 1];
			final int[] sumS = new int[upper + 1];

			for (int i = 0; i < n; i++) {
				if (ls[i]) {
					sumL[s[i] + 1]++;
				} else {
					sumS[s[i]]++;
				}
			}

			for (int i = 0; i <= upper; i++) {
				sumS[i] += sumL[i];
				if (i < upper) sumL[i + 1] += sumS[i];
			}

			final java.util.function.Consumer<int[]> induce = lms -> {
				java.util.Arrays.fill(sa, -1);
				final int[] buf = new int[upper + 1];
				System.arraycopy(sumS, 0, buf, 0, upper + 1);
				for (final int d : lms) {
					if (d == n) continue;
					sa[buf[s[d]]++] = d;
				}
				System.arraycopy(sumL, 0, buf, 0, upper + 1);
				sa[buf[s[n - 1]]++] = n - 1;
				for (int i = 0; i < n; i++) {
					final int v = sa[i];
					if (v >= 1 && !ls[v - 1]) {
						sa[buf[s[v - 1]]++] = v - 1;
					}
				}
				System.arraycopy(sumL, 0, buf, 0, upper + 1);
				for (int i = n - 1; i >= 0; i--) {
					final int v = sa[i];
					if (v >= 1 && ls[v - 1]) {
						sa[--buf[s[v - 1] + 1]] = v - 1;
					}
				}
			};

			final int[] lmsMap = new int[n + 1];
			java.util.Arrays.fill(lmsMap, -1);
			int m = 0;
			for (int i = 1; i < n; i++) {
				if (!ls[i - 1] && ls[i]) {
					lmsMap[i] = m++;
				}
			}

			final int[] lms = new int[m];
			{
				int p = 0;
				for (int i = 1; i < n; i++) {
					if (!ls[i - 1] && ls[i]) {
						lms[p++] = i;
					}
				}
			}

			induce.accept(lms);

			if (m > 0) {
				final int[] sortedLms = new int[m];
				{
					int p = 0;
					for (final int v : sa) {
						if (lmsMap[v] != -1) {
							sortedLms[p++] = v;
						}
					}
				}
				final int[] recS = new int[m];
				int recUpper = 0;
				recS[lmsMap[sortedLms[0]]] = 0;
				for (int i = 1; i < m; i++) {
					int l = sortedLms[i - 1], r = sortedLms[i];
					final int endL = lmsMap[l] + 1 < m ? lms[lmsMap[l] + 1] : n;
					final int endR = lmsMap[r] + 1 < m ? lms[lmsMap[r] + 1] : n;
					boolean same = true;
					if (endL - l != endR - r) {
						same = false;
					} else {
						while (l < endL && s[l] == s[r]) {
							l++;
							r++;
						}
						if (l == n || s[l] != s[r]) same = false;
					}
					if (!same) {
						recUpper++;
					}
					recS[lmsMap[sortedLms[i]]] = recUpper;
				}

				final int[] recSA = sais(recS, recUpper);

				for (int i = 0; i < m; i++) {
					sortedLms[i] = lms[recSA[i]];
				}
				induce.accept(sortedLms);
			}
			return sa;
		}

		public static int[] suffixArray(final int[] s, final int upper) {
			assert 0 <= upper;
			for (final int d : s) {
				assert 0 <= d && d <= upper;
			}
			return sais(s, upper);
		}

		public static int[] suffixArray(final int[] s) {
			final int n = s.length;
			final Integer[] idx = new Integer[n];
			for (int i = 0; i < n; i++) {
				idx[i] = i;
			}
			java.util.Arrays.sort(idx, (l, r) -> s[l] - s[r]);
			final int[] s2 = new int[n];
			int now = 0;
			for (int i = 0; i < n; i++) {
				if (i > 0 && s[idx[i - 1]] != s[idx[i]]) {
					now++;
				}
				s2[idx[i]] = now;
			}
			return sais(s2, now);
		}

		public static int[] suffixArray(final char[] s) {
			final int n = s.length;
			final int[] s2 = new int[n];
			for (int i = 0; i < n; i++) {
				s2[i] = s[i];
			}
			return sais(s2, 255);
		}

		public static int[] suffixArray(final java.lang.String s) {
			return suffixArray(s.toCharArray());
		}

		public static int[] lcpArray(final int[] s, final int[] sa) {
			final int n = s.length;
			assert n >= 1;
			final int[] rnk = new int[n];
			for (int i = 0; i < n; i++) {
				rnk[sa[i]] = i;
			}
			final int[] lcp = new int[n - 1];
			int h = 0;
			for (int i = 0; i < n; i++) {
				if (h > 0) h--;
				if (rnk[i] == 0) {
					continue;
				}
				final int j = sa[rnk[i] - 1];
				for (; j + h < n && i + h < n; h++) {
					if (s[j + h] != s[i + h]) break;
				}
				lcp[rnk[i] - 1] = h;
			}
			return lcp;
		}

		public static int[] lcpArray(final char[] s, final int[] sa) {
			final int n = s.length;
			final int[] s2 = new int[n];
			for (int i = 0; i < n; i++) {
				s2[i] = s[i];
			}
			return lcpArray(s2, sa);
		}

		public static int[] lcpArray(final java.lang.String s, final int[] sa) {
			return lcpArray(s.toCharArray(), sa);
		}

		public static int[] zAlgorithm(final int[] s) {
			final int n = s.length;
			if (n == 0) return new int[0];
			final int[] z = new int[n];
			for (int i = 1, j = 0; i < n; i++) {
				int k = j + z[j] <= i ? 0 : Math.min(j + z[j] - i, z[i - j]);
				while (i + k < n && s[k] == s[i + k]) k++;
				z[i] = k;
				if (j + z[j] < i + z[i]) j = i;
			}
			z[0] = n;
			return z;
		}

		public static int[] zAlgorithm(final char[] s) {
			final int n = s.length;
			if (n == 0) return new int[0];
			final int[] z = new int[n];
			for (int i = 1, j = 0; i < n; i++) {
				int k = j + z[j] <= i ? 0 : Math.min(j + z[j] - i, z[i - j]);
				while (i + k < n && s[k] == s[i + k]) k++;
				z[i] = k;
				if (j + z[j] < i + z[i]) j = i;
			}
			z[0] = n;
			return z;
		}

		public static int[] zAlgorithm(final String s) {
			return zAlgorithm(s.toCharArray());
		}
	}

	/**
	 * @verified https://atcoder.jp/contests/practice2/tasks/practice2_j
	 */
	public static final class SegTree<S> {

		final int MAX;

		final int N;
		final java.util.function.BinaryOperator<S> op;
		final S E;

		final S[] data;

		@SuppressWarnings("unchecked")
		public SegTree(final int n, final java.util.function.BinaryOperator<S> op, final S e) {
			this.MAX = n;
			int k = 1;
			while (k < n) k <<= 1;
			this.N = k;
			this.E = e;
			this.op = op;
			this.data = (S[]) new Object[N << 1];
			java.util.Arrays.fill(data, E);
		}

		public SegTree(final S[] dat, final java.util.function.BinaryOperator<S> op, final S e) {
			this(dat.length, op, e);
			build(dat);
		}

		private void build(final S[] dat) {
			final int l = dat.length;
			System.arraycopy(dat, 0, data, N, l);
			for (int i = N - 1; i > 0; i--) {
				data[i] = op.apply(data[i << 1 | 0], data[i << 1 | 1]);
			}
		}

		public void set(int p, final S x) {
			exclusiveRangeCheck(p);
			data[p += N] = x;
			p >>= 1;
			while (p > 0) {
				data[p] = op.apply(data[p << 1 | 0], data[p << 1 | 1]);
				p >>= 1;
			}
		}

		public void set(int p, java.util.function.UnaryOperator<S> f) {
			exclusiveRangeCheck(p);
			data[p += N] = f.apply(data[p]);
			p >>= 1;
			while (p > 0) {
				data[p] = op.apply(data[p << 1 | 0], data[p << 1 | 1]);
				p >>= 1;
			}
		}

		public S get(final int p) {
			exclusiveRangeCheck(p);
			return data[p + N];
		}

		public S prod(int l, int r) {
			if (l > r) { throw new IllegalArgumentException(String.format("Invalid range: [%d, %d)", l, r)); }
			inclusiveRangeCheck(l);
			inclusiveRangeCheck(r);
			S sumLeft = E;
			S sumRight = E;
			l += N;
			r += N;
			while (l < r) {
				if ((l & 1) == 1) sumLeft = op.apply(sumLeft, data[l++]);
				if ((r & 1) == 1) sumRight = op.apply(data[--r], sumRight);
				l >>= 1;
				r >>= 1;
			}
			return op.apply(sumLeft, sumRight);
		}

		public S allProd() {
			return data[1];
		}

		public int maxRight(int l, final java.util.function.Predicate<S> f) {
			inclusiveRangeCheck(l);
			if (!f.test(E)) { throw new IllegalArgumentException("Identity element must satisfy the condition."); }
			if (l == MAX) return MAX;
			l += N;
			S sum = E;
			do {
				l >>= Integer.numberOfTrailingZeros(l);
				if (!f.test(op.apply(sum, data[l]))) {
					while (l < N) {
						l = l << 1;
						if (f.test(op.apply(sum, data[l]))) {
							sum = op.apply(sum, data[l]);
							l++;
						}
					}
					return l - N;
				}
				sum = op.apply(sum, data[l]);
				l++;
			} while ((l & -l) != l);
			return MAX;
		}

		public int minLeft(int r, final java.util.function.Predicate<S> f) {
			inclusiveRangeCheck(r);
			if (!f.test(E)) { throw new IllegalArgumentException("Identity element must satisfy the condition."); }
			if (r == 0) return 0;
			r += N;
			S sum = E;
			do {
				r--;
				while (r > 1 && (r & 1) == 1) r >>= 1;
				if (!f.test(op.apply(data[r], sum))) {
					while (r < N) {
						r = r << 1 | 1;
						if (f.test(op.apply(data[r], sum))) {
							sum = op.apply(data[r], sum);
							r--;
						}
					}
					return r + 1 - N;
				}
				sum = op.apply(data[r], sum);
			} while ((r & -r) != r);
			return 0;
		}

		private void exclusiveRangeCheck(final int p) {
			if (p < 0 || p >= MAX) {
				throw new IndexOutOfBoundsException(
						String.format("Index %d out of bounds for the range [%d, %d).", p, 0, MAX));
			}
		}

		private void inclusiveRangeCheck(final int p) {
			if (p < 0 || p > MAX) {
				throw new IndexOutOfBoundsException(
						String.format("Index %d out of bounds for the range [%d, %d].", p, 0, MAX));
			}
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append('[');
			for (int i = 0;i < N;++ i) {
				if (i != 0) sb.append(", ");
				sb.append(data[i + N]);
			}
			sb.append(']');
			return sb.toString();
		}
	}

	/**
	 *
	 * @verified https://atcoder.jp/contests/practice2/tasks/practice2_k
	 */

	public static final class LazySegTree<S, F> {

		final int MAX;

		final int N;
		final int Log;
		final java.util.function.BinaryOperator<S> Op;
		final S E;
		final java.util.function.BiFunction<F, S, S> Mapping;
		final java.util.function.BinaryOperator<F> Composition;
		final F Id;

		final S[] Dat;
		final F[] Laz;

		@SuppressWarnings("unchecked")
		public LazySegTree(final int n, final java.util.function.BinaryOperator<S> op, final S e,
				final java.util.function.BiFunction<F, S, S> mapping,
				final java.util.function.BinaryOperator<F> composition, final F id) {
			this.MAX = n;
			int k = 1;
			while (k < n) k <<= 1;
			this.N = k;
			this.Log = Integer.numberOfTrailingZeros(N);
			this.Op = op;
			this.E = e;
			this.Mapping = mapping;
			this.Composition = composition;
			this.Id = id;
			this.Dat = (S[]) new Object[N << 1];
			this.Laz = (F[]) new Object[N];
			java.util.Arrays.fill(Dat, E);
			java.util.Arrays.fill(Laz, Id);
		}

		public LazySegTree(final S[] dat, final java.util.function.BinaryOperator<S> op, final S e,
				final java.util.function.BiFunction<F, S, S> mapping,
				final java.util.function.BinaryOperator<F> composition, final F id) {
			this(dat.length, op, e, mapping, composition, id);
			build(dat);
		}

		private void build(final S[] dat) {
			final int l = dat.length;
			System.arraycopy(dat, 0, Dat, N, l);
			for (int i = N - 1; i > 0; i--) {
				Dat[i] = Op.apply(Dat[i << 1 | 0], Dat[i << 1 | 1]);
			}
		}

		private void push(final int k) {
			if (Laz[k] == Id) return;
			final int lk = k << 1 | 0, rk = k << 1 | 1;
			Dat[lk] = Mapping.apply(Laz[k], Dat[lk]);
			Dat[rk] = Mapping.apply(Laz[k], Dat[rk]);
			if (lk < N) Laz[lk] = Composition.apply(Laz[k], Laz[lk]);
			if (rk < N) Laz[rk] = Composition.apply(Laz[k], Laz[rk]);
			Laz[k] = Id;
		}

		private void pushTo(final int k) {
			for (int i = Log; i > 0; i--) push(k >> i);
		}

		private void pushTo(final int lk, final int rk) {
			for (int i = Log; i > 0; i--) {
				if (lk >> i << i != lk) push(lk >> i);
				if (rk >> i << i != rk) push(rk >> i);
			}
		}

		private void updateFrom(int k) {
			k >>= 1;
			while (k > 0) {
				Dat[k] = Op.apply(Dat[k << 1 | 0], Dat[k << 1 | 1]);
				k >>= 1;
			}
		}

		private void updateFrom(final int lk, final int rk) {
			for (int i = 1; i <= Log; i++) {
				if (lk >> i << i != lk) {
					final int lki = lk >> i;
					Dat[lki] = Op.apply(Dat[lki << 1 | 0], Dat[lki << 1 | 1]);
				}
				if (rk >> i << i != rk) {
					final int rki = rk - 1 >> i;
					Dat[rki] = Op.apply(Dat[rki << 1 | 0], Dat[rki << 1 | 1]);
				}
			}
		}

		public void set(int p, final S x) {
			exclusiveRangeCheck(p);
			p += N;
			pushTo(p);
			Dat[p] = x;
			updateFrom(p);
		}

		public S get(int p) {
			exclusiveRangeCheck(p);
			p += N;
			pushTo(p);
			return Dat[p];
		}

		public S prod(int l, int r) {
			if (l > r) { throw new IllegalArgumentException(String.format("Invalid range: [%d, %d)", l, r)); }
			inclusiveRangeCheck(l);
			inclusiveRangeCheck(r);
			if (l == r) return E;
			l += N;
			r += N;
			pushTo(l, r);
			S sumLeft = E, sumRight = E;
			while (l < r) {
				if ((l & 1) == 1) sumLeft = Op.apply(sumLeft, Dat[l++]);
				if ((r & 1) == 1) sumRight = Op.apply(Dat[--r], sumRight);
				l >>= 1;
				r >>= 1;
			}
			return Op.apply(sumLeft, sumRight);
		}

		public S allProd() {
			return Dat[1];
		}

		public void apply(int p, final F f) {
			exclusiveRangeCheck(p);
			p += N;
			pushTo(p);
			Dat[p] = Mapping.apply(f, Dat[p]);
			updateFrom(p);
		}

		public void apply(int l, int r, final F f) {
			if (l > r) { throw new IllegalArgumentException(String.format("Invalid range: [%d, %d)", l, r)); }
			inclusiveRangeCheck(l);
			inclusiveRangeCheck(r);
			if (l == r) return;
			l += N;
			r += N;
			pushTo(l, r);
			for (int l2 = l, r2 = r; l2 < r2;) {
				if ((l2 & 1) == 1) {
					Dat[l2] = Mapping.apply(f, Dat[l2]);
					if (l2 < N) Laz[l2] = Composition.apply(f, Laz[l2]);
					l2++;
				}
				if ((r2 & 1) == 1) {
					r2--;
					Dat[r2] = Mapping.apply(f, Dat[r2]);
					if (r2 < N) Laz[r2] = Composition.apply(f, Laz[r2]);
				}
				l2 >>= 1;
				r2 >>= 1;
			}
			updateFrom(l, r);
		}

		public int maxRight(int l, final java.util.function.Predicate<S> g) {
			inclusiveRangeCheck(l);
			if (!g.test(E)) { throw new IllegalArgumentException("Identity element must satisfy the condition."); }
			if (l == MAX) return MAX;
			l += N;
			pushTo(l);
			S sum = E;
			do {
				l >>= Integer.numberOfTrailingZeros(l);
				if (!g.test(Op.apply(sum, Dat[l]))) {
					while (l < N) {
						push(l);
						l = l << 1;
						if (g.test(Op.apply(sum, Dat[l]))) {
							sum = Op.apply(sum, Dat[l]);
							l++;
						}
					}
					return l - N;
				}
				sum = Op.apply(sum, Dat[l]);
				l++;
			} while ((l & -l) != l);
			return MAX;
		}

		public int minLeft(int r, final java.util.function.Predicate<S> g) {
			inclusiveRangeCheck(r);
			if (!g.test(E)) { throw new IllegalArgumentException("Identity element must satisfy the condition."); }
			if (r == 0) return 0;
			r += N;
			pushTo(r - 1);
			S sum = E;
			do {
				r--;
				while (r > 1 && (r & 1) == 1) r >>= 1;
				if (!g.test(Op.apply(Dat[r], sum))) {
					while (r < N) {
						push(r);
						r = r << 1 | 1;
						if (g.test(Op.apply(Dat[r], sum))) {
							sum = Op.apply(Dat[r], sum);
							r--;
						}
					}
					return r + 1 - N;
				}
				sum = Op.apply(Dat[r], sum);
			} while ((r & -r) != r);
			return 0;
		}

		private void exclusiveRangeCheck(final int p) {
			if (p < 0 || p >= MAX) {
				throw new IndexOutOfBoundsException(String.format("Index %d is not in [%d, %d).", p, 0, MAX));
			}
		}

		private void inclusiveRangeCheck(final int p) {
			if (p < 0 || p > MAX) {
				throw new IndexOutOfBoundsException(String.format("Index %d is not in [%d, %d].", p, 0, MAX));
			}
		}

		// **************** DEBUG **************** //

		private int indent = 6;

		public void setIndent(final int newIndent) { this.indent = newIndent; }

		@Override
		public String toString() {
			return toString(1, 0);
		}

		private String toString(final int k, final int sp) {
			if (k >= N) return indent(sp) + Dat[k];
			String s = "";
			s += toString(k << 1 | 1, sp + indent);
			s += "\n";
			s += indent(sp) + Dat[k] + "/" + Laz[k];
			s += "\n";
			s += toString(k << 1 | 0, sp + indent);
			return s;
		}

		private static String indent(int n) {
			final StringBuilder sb = new StringBuilder();
			while (n-- > 0) sb.append(' ');
			return sb.toString();
		}
	}

	public static final class MultiSet<T> extends java.util.TreeMap<T, Long> {

		private static final long serialVersionUID = 1L;

		public MultiSet() {
			super();
		}

		public MultiSet(final java.util.List<T> list) {
			super();
			for (final T e : list) this.addOne(e);
		}

		public long count(final Object elm) {
			return getOrDefault(elm, 0L);
		}

		public void add(final T elm, final long amount) {
			if (!containsKey(elm)) put(elm, amount);
			else replace(elm, get(elm) + amount);
			if (this.count(elm) == 0) this.remove(elm);
		}

		public void addOne(final T elm) {
			this.add(elm, 1);
		}

		public void removeOne(final T elm) {
			this.add(elm, -1);
		}

		public void removeAll(final T elm) {
			this.add(elm, -this.count(elm));
		}

		public static <T> MultiSet<T> merge(final MultiSet<T> a, final MultiSet<T> b) {
			final MultiSet<T> c = new MultiSet<>();
			for (final T x : a.keySet()) c.add(x, a.count(x));
			for (final T y : b.keySet()) c.add(y, b.count(y));
			return c;
		}
	}
}

/**
 * 高速な入出力を提供します。
 *
 * @author 31536000
 *
 */
final class FastIO implements AutoCloseable {

	private Input in;
	private Output out;
	private Output err;
	private boolean outFlush = false;
	private boolean autoOutFlush = true;
	public static final java.io.PrintStream DUMMY_OUT = new DummyOut();

	public FastIO() {
		this(System.in, System.out, System.err);
	}

	public FastIO(final java.io.InputStream in, final java.io.PrintStream out, final java.io.PrintStream err) {
		this.in = in instanceof Input ? (Input) in : new Input(in);
		if (out instanceof Output) {
			this.out = (Output) out;
		} else {
			this.out = new Output(out);
			this.out.setAutoFlush(false);
		}
		if (err instanceof Output) {
			this.err = (Output) err;
		} else {
			this.err = new Output(err);
			this.err.setAutoFlush(false);
		}
	}

	public static void setFastStandardOutput(final boolean set) {
		final java.io.FileOutputStream fdOut = new java.io.FileOutputStream(java.io.FileDescriptor.out);
		final java.io.FileOutputStream fdErr = new java.io.FileOutputStream(java.io.FileDescriptor.err);
		if (set) {
			System.out.flush();
			final Output out = new Output(fdOut);
			out.setAutoFlush(false);
			System.setOut(out);
			System.err.flush();
			final Output err = new Output(fdErr);
			err.setAutoFlush(false);
			System.setErr(err);
		} else {
			System.out.flush();
			final java.io.PrintStream out = new java.io.PrintStream(new java.io.BufferedOutputStream(fdOut, 128), true);
			System.setOut(out);
			System.err.flush();
			final java.io.PrintStream err = new java.io.PrintStream(new java.io.BufferedOutputStream(fdErr, 128), true);
			System.setErr(err);
		}
	}

	public void setInputStream(final java.io.InputStream in) {
		if (this.in == in) return;
		this.in.close();
		this.in = in instanceof Input ? (Input) in : new Input(in);
	}

	public void setInputStream(final java.io.File in) {
		try {
			this.in.close();
			final java.io.InputStream input = new java.io.FileInputStream(in);
			this.in = new Input(input);
		} catch (final java.io.FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Input getInputStream() { return in; }

	public void setOutputStream(final java.io.OutputStream out) {
		if (this.out == out) {
			this.out.flush();
		}
		final boolean flush = this.out.autoFlush;
		this.out.close();
		if (out instanceof Output) {
			this.out = (Output) out;
			this.out.setAutoFlush(flush);
		} else {
			this.out = new Output(out);
			this.out.setAutoFlush(flush);
		}
	}

	public void setOutputStream(final java.io.File out) {
		try {
			setOutputStream(new java.io.FileOutputStream(out));
		} catch (final java.io.FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void setOutputStream(final java.io.FileDescriptor out) {
		setOutputStream(new java.io.FileOutputStream(out));
	}

	public Output getOutputStream() { return out; }

	public void setErrorStream(final java.io.OutputStream err) {
		if (this.err == err) {
			this.err.flush();
		}
		final boolean flush = this.err.autoFlush;
		this.err.close();
		if (err instanceof Output) {
			this.err = (Output) err;
			this.err.setAutoFlush(flush);
		} else {
			this.err = new Output(err);
			this.err.setAutoFlush(flush);
		}
	}

	public void setErrorStream(final java.io.File err) {
		try {
			setErrorStream(new java.io.FileOutputStream(err));
		} catch (final java.io.FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void setErrorStream(final java.io.FileDescriptor err) {
		setErrorStream(new java.io.FileOutputStream(err));
	}

	public Output getErrorStream() { return err; }

	public void setAutoFlush(final boolean flush) {
		out.setAutoFlush(flush);
		err.setAutoFlush(flush);
	}

	public void setAutoOutFlush(final boolean flush) { autoOutFlush = flush; }

	private void autoFlush() {
		if (outFlush) {
			outFlush = false;
			flush();
		}
	}

	public boolean hasNext() {
		autoFlush();
		return in.hasNext();
	}

	public boolean nextBoolean() {
		autoFlush();
		return in.nextBoolean();
	}

	public boolean[] nextBoolean(final char T) {
		final char[] s = nextChars();
		final boolean[] ret = new boolean[s.length];
		for (int i = 0; i < ret.length; ++i) ret[i] = s[i] == T;
		return ret;
	}

	public boolean[][] nextBoolean(final char T, final int height) {
		final boolean[][] ret = new boolean[height][];
		for (int i = 0; i < ret.length; ++i) {
			final char[] s = nextChars();
			ret[i] = new boolean[s.length];
			for (int j = 0; j < ret[i].length; ++j) ret[i][j] = s[j] == T;
		}
		return ret;
	}

	public byte nextByte() {
		autoFlush();
		return in.nextByte();
	}

	public short nextShort() {
		autoFlush();
		return in.nextShort();
	}

	public short[] nextShort(final int width) {
		final short[] ret = new short[width];
		for (int i = 0; i < width; ++i) ret[i] = nextShort();
		return ret;
	}

	public short[][] nextShort(final int width, final int height) {
		final short[][] ret = new short[height][width];
		for (int i = 0, j; i < height; ++i) for (j = 0; j < width; ++j) ret[i][j] = nextShort();
		return ret;
	}

	public int nextInt() {
		autoFlush();
		return in.nextInt();
	}

	public int[] nextInt(final int width) {
		final int[] ret = new int[width];
		for (int i = 0; i < width; ++i) ret[i] = nextInt();
		return ret;
	}

	public int[][] nextInt(final int width, final int height) {
		final int[][] ret = new int[height][width];
		for (int i = 0, j; i < height; ++i) for (j = 0; j < width; ++j) ret[i][j] = nextInt();
		return ret;
	}

	public int[] nextInts() {
		return nextInts(" ");
	}

	public int[] nextInts(final String parse) {
		final String[] get = nextLine().split(parse);
		final int[] ret = new int[get.length];
		for (int i = 0; i < ret.length; ++i) ret[i] = Integer.valueOf(get[i]);
		return ret;
	}

	public long nextLong() {
		autoFlush();
		return in.nextLong();
	}

	public long[] nextLong(final int width) {
		final long[] ret = new long[width];
		for (int i = 0; i < width; ++i) ret[i] = nextLong();
		return ret;
	}

	public long[][] nextLong(final int width, final int height) {
		final long[][] ret = new long[height][width];
		for (int i = 0, j; i < height; ++i) for (j = 0; j < width; ++j) ret[j][i] = nextLong();
		return ret;
	}

	public long[] nextLongs() {
		return nextLongs(" ");
	}

	public long[] nextLongs(final String parse) {
		final String[] get = nextLine().split(parse);
		final long[] ret = new long[get.length];
		for (int i = 0; i < ret.length; ++i) ret[i] = Long.valueOf(get[i]);
		return ret;
	}

	public float nextFloat() {
		autoFlush();
		return in.nextFloat();
	}

	public double nextDouble() {
		autoFlush();
		return in.nextDouble();
	}

	public char nextChar() {
		autoFlush();
		return in.nextChar();
	}

	public char[] nextChars() {
		return next().toCharArray();
	}

	public char[] nextChars(final char around) {
		return (around + next() + around).toCharArray();
	}

	public char[][] nextChars(final int height) {
		final char[][] ret = new char[height][];
		for (int i = 0; i < ret.length; ++i) ret[i] = nextChars();
		return ret;
	}

	public char[][] nextChars(final int height, final char around) {
		final char[][] ret = new char[height + 2][];
		for (int i = 1; i <= height; ++i) ret[i] = nextChars(around);
		java.util.Arrays.fill(ret[0] = new char[ret[1].length], around);
		java.util.Arrays.fill(ret[ret.length - 1] = new char[ret[0].length], around);
		return ret;
	}

	public String next() {
		autoFlush();
		return in.next();
	}

	public String nextLine() {
		autoFlush();
		return in.nextLine();
	}

	public Point nextPoint() {
		return new Point(nextInt(), nextInt());
	}

	public Point[] nextPoint(final int width) {
		final Point[] ret = new Point[width];
		for (int i = 0; i < width; ++i) ret[i] = nextPoint();
		return ret;
	}

	public boolean print(final boolean b) {
		out.print(b);
		outFlush = autoOutFlush;
		return b;
	}

	public byte print(final byte b) {
		out.print(b);
		outFlush = autoOutFlush;
		return b;
	}

	public short print(final short s) {
		out.print(s);
		outFlush = autoOutFlush;
		return s;
	}

	public int print(final int i) {
		out.print(i);
		outFlush = autoOutFlush;
		return i;
	}

	public long print(final long l) {
		out.print(l);
		outFlush = autoOutFlush;
		return l;
	}

	public float print(final float f) {
		out.print(f);
		outFlush = autoOutFlush;
		return f;
	}

	public double print(final double d) {
		out.print(d);
		outFlush = autoOutFlush;
		return d;
	}

	public double print(final double d, final int length) {
		out.print(d, length);
		outFlush = autoOutFlush;
		return d;
	}

	public char print(final char c) {
		out.print(c);
		outFlush = autoOutFlush;
		return c;
	}

	public char[] print(final char[] s) {
		out.print(s);
		outFlush = autoOutFlush;
		return s;
	}

	public String print(final String s) {
		out.print(s);
		outFlush = autoOutFlush;
		return s;
	}

	public Object print(final Object obj) {
		if (obj != null && obj.getClass().isArray()) {
			if (obj instanceof boolean[][]) print(obj, "\n", " ");
			else if (obj instanceof byte[][]) print(obj, "\n", " ");
			else if (obj instanceof short[][]) print(obj, "\n", " ");
			else if (obj instanceof int[][]) print(obj, "\n", " ");
			else if (obj instanceof long[][]) print(obj, "\n", " ");
			else if (obj instanceof float[][]) print(obj, "\n", " ");
			else if (obj instanceof double[][]) print(obj, "\n", " ");
			else if (obj instanceof char[][]) print(obj, "\n", " ");
			else if (obj instanceof Object[][]) print(obj, "\n", " ");
			else print(obj, " ");
		} else {
			out.print(obj);
			outFlush = autoOutFlush;
		}
		return obj;
	}

	public Object print(final Object array, final String... parse) {
		print(array, 0, parse);
		return array;
	}

	private Object print(final Object array, final int check, final String... parse) {
		if (check >= parse.length) {
			if (array != null && array.getClass().isArray()) throw new IllegalArgumentException("not equal dimension");
			print(array);
			return array;
		}
		final String str = parse[check];
		if (array instanceof Object[]) {
			final Object[] obj = (Object[]) array;
			if (obj.length == 0) return array;
			print(obj[0], check + 1, parse);
			for (int i = 1; i < obj.length; ++i) {
				print(str);
				print(obj[i], check + 1, parse);
			}
			return array;
		}
		if (array instanceof java.util.Collection) {
			final java.util.Iterator<?> iter = ((java.util.Collection<?>) array).iterator();
			if (!iter.hasNext()) return array;
			print(iter.next(), check + 1, parse);
			while (iter.hasNext()) {
				print(str);
				print(iter.next(), check + 1, parse);
			}
			return array;
		}
		if (!array.getClass().isArray()) throw new IllegalArgumentException("not equal dimension");
		if (check != parse.length - 1) throw new IllegalArgumentException("not equal dimension");
		if (array instanceof boolean[]) {
			final boolean[] obj = (boolean[]) array;
			if (obj.length == 0) return array;
			print(obj[0]);
			for (int i = 1; i < obj.length; ++i) {
				print(str);
				print(obj[i]);
			}
		} else if (array instanceof byte[]) {
			final byte[] obj = (byte[]) array;
			if (obj.length == 0) return array;
			print(obj[0]);
			for (int i = 1; i < obj.length; ++i) {
				print(str);
				print(obj[i]);
			}
			return array;
		} else if (array instanceof short[]) {
			final short[] obj = (short[]) array;
			if (obj.length == 0) return array;
			print(obj[0]);
			for (int i = 1; i < obj.length; ++i) {
				print(str);
				print(obj[i]);
			}
		} else if (array instanceof int[]) {
			final int[] obj = (int[]) array;
			if (obj.length == 0) return array;
			print(obj[0]);
			for (int i = 1; i < obj.length; ++i) {
				print(str);
				print(obj[i]);
			}
		} else if (array instanceof long[]) {
			final long[] obj = (long[]) array;
			if (obj.length == 0) return array;
			print(obj[0]);
			for (int i = 1; i < obj.length; ++i) {
				print(str);
				print(obj[i]);
			}
		} else if (array instanceof float[]) {
			final float[] obj = (float[]) array;
			if (obj.length == 0) return array;
			print(obj[0]);
			for (int i = 1; i < obj.length; ++i) {
				print(str);
				print(obj[i]);
			}
		} else if (array instanceof double[]) {
			final double[] obj = (double[]) array;
			if (obj.length == 0) return array;
			print(obj[0]);
			for (int i = 1; i < obj.length; ++i) {
				print(str);
				print(obj[i]);
			}
		} else if (array instanceof char[]) {
			final char[] obj = (char[]) array;
			if (obj.length == 0) return array;
			print(obj[0]);
			for (int i = 1; i < obj.length; ++i) {
				print(str);
				print(obj[i]);
			}
		} else throw new AssertionError();
		return array;
	}

	public Object[] print(final String parse, final Object... args) {
		print(args[0]);
		for (int i = 1; i < args.length; ++i) {
			print(parse);
			print(args[i]);
		}
		return args;
	}

	public Object[] printf(final String format, final Object... args) {
		out.printf(format, args);
		outFlush = autoOutFlush;
		return args;
	}

	public Object[] printf(final java.util.Locale l, final String format, final Object... args) {
		out.printf(l, format, args);
		outFlush = autoOutFlush;
		return args;
	}

	public void println() {
		out.println();
		outFlush = autoOutFlush;
	}

	public boolean println(final boolean b) {
		out.println(b);
		outFlush = autoOutFlush;
		return b;
	}

	public byte println(final byte b) {
		out.println(b);
		outFlush = autoOutFlush;
		return b;
	}

	public short println(final short s) {
		out.println(s);
		outFlush = autoOutFlush;
		return s;
	}

	public int println(final int i) {
		out.println(i);
		outFlush = autoOutFlush;
		return i;
	}

	public long println(final long l) {
		out.println(l);
		outFlush = autoOutFlush;
		return l;
	}

	public float println(final float f) {
		out.println(f);
		outFlush = autoOutFlush;
		return f;
	}

	public double println(final double d) {
		out.println(d);
		outFlush = autoOutFlush;
		return d;
	}

	public double println(final double d, final int length) {
		out.println(d, length);
		outFlush = autoOutFlush;
		return d;
	}

	public char println(final char c) {
		out.println(c);
		outFlush = autoOutFlush;
		return c;
	}

	public char[] println(final char[] s) {
		out.println(s);
		outFlush = autoOutFlush;
		return s;
	}

	public String println(final String s) {
		out.println(s);
		return s;
	}

	public Object println(final Object obj) {
		print(obj);
		println();
		return obj;
	}

	public Object println(final Object array, final String... parse) {
		print(array, parse);
		println();
		return array;
	}

	public boolean debug(final boolean b) {
		err.print(b);
		outFlush = autoOutFlush;
		return b;
	}

	public byte debug(final byte b) {
		err.print(b);
		outFlush = autoOutFlush;
		return b;
	}

	public short debug(final short s) {
		err.print(s);
		outFlush = autoOutFlush;
		return s;
	}

	public int debug(final int i) {
		err.print(i);
		outFlush = autoOutFlush;
		return i;
	}

	public long debug(final long l) {
		err.print(l);
		outFlush = autoOutFlush;
		return l;
	}

	public float debug(final float f) {
		err.print(f);
		outFlush = autoOutFlush;
		return f;
	}

	public double debug(final double d) {
		err.print(d);
		outFlush = autoOutFlush;
		return d;
	}

	public double debug(final double d, final int length) {
		err.print(d, length);
		outFlush = autoOutFlush;
		return d;
	}

	public char debug(final char c) {
		err.print(c);
		outFlush = autoOutFlush;
		return c;
	}

	public char[] debug(final char[] s) {
		err.print(s);
		outFlush = autoOutFlush;
		return s;
	}

	public String debug(final String s) {
		err.print(s);
		outFlush = autoOutFlush;
		return s;
	}

	public Object debug(final Object obj) {
		if (obj != null && obj.getClass().isArray()) {
			if (obj instanceof boolean[][]) debug(obj, "\n", " ");
			else if (obj instanceof byte[][]) debug(obj, "\n", " ");
			else if (obj instanceof short[][]) debug(obj, "\n", " ");
			else if (obj instanceof int[][]) debug(obj, "\n", " ");
			else if (obj instanceof long[][]) debug(obj, "\n", " ");
			else if (obj instanceof float[][]) debug(obj, "\n", " ");
			else if (obj instanceof double[][]) debug(obj, "\n", " ");
			else if (obj instanceof char[][]) debug(obj, "\n", " ");
			else if (obj instanceof Object[][]) debug(obj, "\n", " ");
			else debug(obj, " ");
		} else {
			err.print(obj);
			outFlush = autoOutFlush;
		}
		return obj;
	}

	public Object debug(final Object array, final String... parse) {
		debug(array, 0, parse);
		return array;
	}

	private Object debug(final Object array, final int check, final String... parse) {
		if (check >= parse.length) {
			if (array != null && array.getClass().isArray()) throw new IllegalArgumentException("not equal dimension");
			debug(array);
			return array;
		}
		final String str = parse[check];
		if (array instanceof Object[]) {
			final Object[] obj = (Object[]) array;
			if (obj.length == 0) return array;
			debug(obj[0], check + 1, parse);
			for (int i = 1; i < obj.length; ++i) {
				debug(str);
				debug(obj[i], check + 1, parse);
			}
			return array;
		}
		if (array instanceof java.util.Collection) {
			final java.util.Iterator<?> iter = ((java.util.Collection<?>) array).iterator();
			if (!iter.hasNext()) return array;
			debug(iter.next(), check + 1, parse);
			while (iter.hasNext()) {
				debug(str);
				debug(iter.next(), check + 1, parse);
			}
			return array;
		}
		if (!array.getClass().isArray()) throw new IllegalArgumentException("not equal dimension");
		if (check != parse.length - 1) throw new IllegalArgumentException("not equal dimension");
		if (array instanceof boolean[]) {
			final boolean[] obj = (boolean[]) array;
			if (obj.length == 0) return array;
			debug(obj[0]);
			for (int i = 1; i < obj.length; ++i) {
				debug(str);
				debug(obj[i]);
			}
		} else if (array instanceof byte[]) {
			final byte[] obj = (byte[]) array;
			if (obj.length == 0) return array;
			debug(obj[0]);
			for (int i = 1; i < obj.length; ++i) {
				debug(str);
				debug(obj[i]);
			}
			return array;
		} else if (array instanceof short[]) {
			final short[] obj = (short[]) array;
			if (obj.length == 0) return array;
			debug(obj[0]);
			for (int i = 1; i < obj.length; ++i) {
				debug(str);
				debug(obj[i]);
			}
		} else if (array instanceof int[]) {
			final int[] obj = (int[]) array;
			if (obj.length == 0) return array;
			debug(obj[0]);
			for (int i = 1; i < obj.length; ++i) {
				debug(str);
				debug(obj[i]);
			}
		} else if (array instanceof long[]) {
			final long[] obj = (long[]) array;
			if (obj.length == 0) return array;
			debug(obj[0]);
			for (int i = 1; i < obj.length; ++i) {
				debug(str);
				debug(obj[i]);
			}
		} else if (array instanceof float[]) {
			final float[] obj = (float[]) array;
			if (obj.length == 0) return array;
			debug(obj[0]);
			for (int i = 1; i < obj.length; ++i) {
				debug(str);
				debug(obj[i]);
			}
		} else if (array instanceof double[]) {
			final double[] obj = (double[]) array;
			if (obj.length == 0) return array;
			debug(obj[0]);
			for (int i = 1; i < obj.length; ++i) {
				debug(str);
				debug(obj[i]);
			}
		} else if (array instanceof char[]) {
			final char[] obj = (char[]) array;
			if (obj.length == 0) return array;
			debug(obj[0]);
			for (int i = 1; i < obj.length; ++i) {
				debug(str);
				debug(obj[i]);
			}
		} else throw new AssertionError();
		return array;
	}

	public Object[] debug(final String parse, final Object... args) {
		debug(args[0]);
		for (int i = 1; i < args.length; ++i) {
			debug(parse);
			debug(args[i]);
		}
		return args;
	}

	public Object[] debugf(final String format, final Object... args) {
		err.printf(format, args);
		outFlush = autoOutFlush;
		return args;
	}

	public Object[] debugf(final java.util.Locale l, final String format, final Object... args) {
		err.printf(l, format, args);
		outFlush = autoOutFlush;
		return args;
	}

	public void debugln() {
		err.println();
		outFlush = autoOutFlush;
	}

	public boolean debugln(final boolean b) {
		err.println(b);
		outFlush = autoOutFlush;
		return b;
	}

	public byte debugln(final byte b) {
		err.println(b);
		outFlush = autoOutFlush;
		return b;
	}

	public short debugln(final short s) {
		err.println(s);
		outFlush = autoOutFlush;
		return s;
	}

	public int debugln(final int i) {
		err.println(i);
		outFlush = autoOutFlush;
		return i;
	}

	public long debugln(final long l) {
		err.println(l);
		outFlush = autoOutFlush;
		return l;
	}

	public float debugln(final float f) {
		err.println(f);
		outFlush = autoOutFlush;
		return f;
	}

	public double debugln(final double d) {
		err.println(d);
		outFlush = autoOutFlush;
		return d;
	}

	public double debugln(final double d, final int length) {
		err.println(d, length);
		outFlush = autoOutFlush;
		return d;
	}

	public char debugln(final char c) {
		err.println(c);
		outFlush = autoOutFlush;
		return c;
	}

	public char[] debugln(final char[] s) {
		err.println(s);
		outFlush = autoOutFlush;
		return s;
	}

	public String debugln(final String s) {
		err.println(s);
		outFlush = autoOutFlush;
		return s;
	}

	public Object debugln(final Object obj) {
		debug(obj);
		debugln();
		return obj;
	}

	public Object debugln(final Object array, final String... parse) {
		debug(array, parse);
		debugln();
		return array;
	}

	public void flush() {
		out.flush();
		err.flush();
		outFlush = false;
	}

	@Override
	public void close() {
		out.close();
		err.close();
	}

	public static final class Input extends java.io.InputStream {

		private final java.io.InputStream in;
		private final byte[] buffer = new byte[1 << 13];
		private int read = 0;
		private int length = 0;

		public Input(final java.io.InputStream in) {
			this.in = in;
		}

		@Override
		public int available() {
			try {
				return in.available();
			} catch (final java.io.IOException e) {
				e.printStackTrace();
			}
			return 0;
		}

		@Override
		public void close() {
			try {
				in.close();
				read = length = 0;
			} catch (final java.io.IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public int read() {
			if (hasNextByte()) return nextByte();
			return 0;
		}

		private boolean hasNextByte() {
			if (read < length) return true;
			read = 0;
			try {
				length = in.read(buffer);
			} catch (final java.io.IOException e) {
				e.printStackTrace();
			}
			return length > 0;
		}

		private static boolean isPrintableChar(final byte c) {
			return 32 < c || c < 0;
		}

		private static boolean isNumber(final byte c) {
			return '0' <= c && c <= '9';
		}

		private boolean readNewLine() {
			if (hasNextByte()) {
				if (buffer[read] == '\r') {
					++read;
					if (hasNextByte() && buffer[read] == '\n') ++read;
					return true;
				}
				if (buffer[read] == '\n') {
					++read;
					return true;
				}
			}
			return false;
		}

		public boolean hasNext() {
			while (hasNextByte() && !isPrintableChar(buffer[read])) read++;
			return hasNextByte();
		}

		private byte nextTokenByte() {
			while (hasNextByte() && !isPrintableChar(buffer[read])) read++;
			return buffer[read++];
		}

		public boolean nextBoolean() {
			return Boolean.valueOf(next());
		}

		public byte nextByte() {
			if (hasNextByte()) return buffer[read++];
			throw new java.util.NoSuchElementException();
		}

		public short nextShort() {
			byte b = nextTokenByte();
			short n = 0;
			try {
				if (b == '-') {
					while (isNumber(b = nextByte())) n = (short) (n * 10 + '0' - b);
					return n;
				} else if (!isNumber(b)) throw new NumberFormatException();
				do n = (short) (n * 10 + b - '0'); while (isNumber(b = nextByte()));
				return n;
			} catch (final java.util.NoSuchElementException e) {
				return n;
			}
		}

		public int nextInt() {
			byte b = nextTokenByte();
			int n = 0;
			try {
				if (b == '-') {
					while (isNumber(b = nextByte())) n = n * 10 + '0' - b;
					return n;
				} else if (!isNumber(b)) throw new NumberFormatException();
				do n = n * 10 + b - '0'; while (isNumber(b = nextByte()));
				return n;
			} catch (final java.util.NoSuchElementException e) {
				return n;
			}
		}

		public long nextLong() {
			byte b = nextTokenByte();
			long n = 0;
			try {
				if (b == '-') {
					while (isNumber(b = nextByte())) n = n * 10 + '0' - b;
					return n;
				} else if (!isNumber(b)) throw new NumberFormatException();
				do n = n * 10 + b - '0'; while (isNumber(b = nextByte()));
				return n;
			} catch (final java.util.NoSuchElementException e) {
				return n;
			}
		}

		public float nextFloat() {
			return Float.parseFloat(next());
		}

		public double nextDouble() {
			return Double.parseDouble(next());
		}

		public char nextChar() {
			final byte b = nextByte();
			if ((b & 0x80) == 0) return (char) b;
			if ((b & 0x20) == 0) return (char) ((b & 0x1F) << 6 | nextByte() & 0x3F);
			return (char) ((b & 0xF) << 12 | (nextByte() & 0x3F) << 6 | nextByte() & 0x3F);
		}

		public String next() {
			if (!hasNext()) throw new java.util.NoSuchElementException();
			final StringBuilder sb = new StringBuilder();
			do sb.append(nextChar()); while (hasNextByte() && isPrintableChar(buffer[read]));
			return sb.toString();
		}

		public String nextLine() {
			final StringBuilder sb = new StringBuilder();
			while (!readNewLine()) sb.append(nextChar());
			return sb.toString();
		}

	}

	public static final class Output extends java.io.PrintStream {

		private final byte[] buffer = new byte[1 << 13];
		private int read = 0;
		private boolean autoFlush = true;

		public Output(final java.io.OutputStream out) {
			super(out);
		}

		public void setAutoFlush(final boolean autoFlush) { this.autoFlush = autoFlush; }

		@Override
		public void close() {
			if (out == System.out || out == System.err || this == System.out || this == System.err) {
				flush();
				return;
			}
			try {
				flush();
				out.close();
			} catch (final java.io.IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void flush() {
			try {
				write();
				out.flush();
			} catch (final java.io.IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void write(final byte[] b) {
			if (b.length < buffer.length) {
				ensureBuffer(b.length);
				System.arraycopy(b, 0, buffer, read, b.length);
				read += b.length;
			} else {
				write();
				try {
					out.write(b);
				} catch (final java.io.IOException e) {
					e.printStackTrace();
				}
			}
		}

		@Override
		public void write(final byte[] b, final int off, final int len) {
			if (len < buffer.length) {
				ensureBuffer(len);
				System.arraycopy(b, off, buffer, read, len);
				read += len;
			} else {
				write();
				try {
					out.write(b, off, len);
				} catch (final java.io.IOException e) {
					e.printStackTrace();
				}
			}
		}

		@Override
		public void write(final int b) {
			print((byte) b);
		}

		private void write() {
			try {
				out.write(buffer, 0, read);
				read = 0;
			} catch (final java.io.IOException e) {
				e.printStackTrace();
			}
		}

		private void ensureBuffer(final int size) {
			if (read + size > buffer.length) {
				write();
			}
		}

		@Override
		public void print(final boolean b) {
			if (b) {
				ensureBuffer(4);
				buffer[read++] = 't';
				buffer[read++] = 'r';
				buffer[read++] = 'u';
				buffer[read++] = 'e';
			} else {
				ensureBuffer(5);
				buffer[read++] = 'f';
				buffer[read++] = 'a';
				buffer[read++] = 'l';
				buffer[read++] = 's';
				buffer[read++] = 'e';
			}
		}

		public void print(final byte b) {
			ensureBuffer(1);
			buffer[read++] = b;
		}

		private static int digit(final short s) {
			return s >= 100 ? s >= 1000 ? s >= 10000 ? 5 : 4 : 3 : s >= 10 ? 2 : 1;
		}

		public void print(short s) {
			ensureBuffer(6);
			if (s < 0) {
				if (s == -32768) {
					buffer[read++] = '-';
					buffer[read++] = '3';
					buffer[read++] = '2';
					buffer[read++] = '7';
					buffer[read++] = '6';
					buffer[read++] = '8';
					return;
				}
				buffer[read++] = '-';
				s = (short) -s;
			}
			final int digit = digit(s);
			int i = read + digit;
			while (i-- > read) {
				buffer[i] = (byte) (s % 10 + '0');
				s /= 10;
			}
			read += digit;
		}

		private static int digit(final int i) {
			if (i >= 1000000000) return 10;
			if (i >= 100000000) return 9;
			if (i >= 10000000) return 8;
			if (i >= 1000000) return 7;
			if (i >= 100000) return 6;
			if (i >= 10000) return 5;
			if (i >= 1000) return 4;
			if (i >= 100) return 3;
			if (i >= 10) return 2;
			return 1;
		}

		@Override
		public void print(int i) {
			ensureBuffer(11);
			if (i < 0) {
				if (i == -2147483648) {
					buffer[read++] = '-';
					buffer[read++] = '2';
					buffer[read++] = '1';
					buffer[read++] = '4';
					buffer[read++] = '7';
					buffer[read++] = '4';
					buffer[read++] = '8';
					buffer[read++] = '3';
					buffer[read++] = '6';
					buffer[read++] = '4';
					buffer[read++] = '8';
					return;
				}
				buffer[read++] = '-';
				i = -i;
			}
			final int digit = digit(i);
			int j = read + digit;
			while (j-- > read) {
				buffer[j] = (byte) (i % 10 + '0');
				i /= 10;
			}
			read += digit;
		}

		private static int digit(final long l) {
			if (l >= 1000000000000000000L) return 19;
			if (l >= 100000000000000000L) return 18;
			if (l >= 10000000000000000L) return 17;
			if (l >= 1000000000000000L) return 16;
			if (l >= 100000000000000L) return 15;
			if (l >= 10000000000000L) return 14;
			if (l >= 1000000000000L) return 13;
			if (l >= 100000000000L) return 12;
			if (l >= 10000000000L) return 11;
			if (l >= 1000000000L) return 10;
			if (l >= 100000000L) return 9;
			if (l >= 10000000L) return 8;
			if (l >= 1000000L) return 7;
			if (l >= 100000L) return 6;
			if (l >= 10000L) return 5;
			if (l >= 1000L) return 4;
			if (l >= 100L) return 3;
			if (l >= 10L) return 2;
			return 1;
		}

		@Override
		public void print(long l) {
			ensureBuffer(20);
			if (l < 0) {
				if (l == -9223372036854775808L) {
					buffer[read++] = '-';
					buffer[read++] = '9';
					buffer[read++] = '2';
					buffer[read++] = '2';
					buffer[read++] = '3';
					buffer[read++] = '3';
					buffer[read++] = '7';
					buffer[read++] = '2';
					buffer[read++] = '0';
					buffer[read++] = '3';
					buffer[read++] = '6';
					buffer[read++] = '8';
					buffer[read++] = '5';
					buffer[read++] = '4';
					buffer[read++] = '7';
					buffer[read++] = '7';
					buffer[read++] = '5';
					buffer[read++] = '8';
					buffer[read++] = '0';
					buffer[read++] = '8';
					return;
				}
				buffer[read++] = '-';
				l = -l;
			}
			final int digit = digit(l);
			int i = read + digit;
			while (i-- > read) {
				buffer[i] = (byte) (l % 10 + '0');
				l /= 10;
			}
			read += digit;
		}

		@Override
		public void print(final float f) {
			print(Float.toString(f));
		}

		@Override
		public void print(final double d) {
			print(Double.toString(d));
		}

		public void print(double d, final int n) {
			if (d < 0) {
				ensureBuffer(1);
				buffer[read++] = '-';
				d = -d;
			}
			d += Math.pow(10, -n) / 2;
			final long l = (long) d;
			print(l);
			ensureBuffer(n + 1);
			buffer[read++] = '.';
			d -= l;
			for (int i = 0; i < n; i++) {
				d *= 10;
				final int in = (int) d;
				buffer[read++] = (byte) (in + '0');
				d -= in;
			}
		}

		@Override
		public void print(final char c) {
			if (c < 0x80) {
				ensureBuffer(1);
				buffer[read++] = (byte) c;
			} else if (c < 0x07FF) {
				ensureBuffer(2);
				buffer[read++] = (byte) (c >> 6 & 0x3F | 0x80);
				buffer[read++] = (byte) (c & 0x3F | 0x80);
			} else {
				ensureBuffer(3);
				buffer[read++] = (byte) (c >> 12 & 0xF | 0xE0);
				buffer[read++] = (byte) (c >> 6 & 0x3F | 0x80);
				buffer[read++] = (byte) (c & 0x3F | 0x80);
			}
		}

		@Override
		public void print(final char[] s) {
			for (final char i : s) print(i);
		}

		@Override
		public void print(final String s) {
			print(s.toCharArray());
		}

		@Override
		public void print(final Object o) {
			print(o.toString());
		}

		@Override
		public Output printf(final java.util.Locale l, final String format, final Object... args) {
			print(String.format(l, format, args));
			return this;
		}

		@Override
		public Output printf(final String format, final Object... args) {
			print(String.format(format, args));
			return this;
		}

		@Override
		public void println() {
			ensureBuffer(1);
			buffer[read++] = '\n';
			if (autoFlush) flush();
		}

		@Override
		public void println(final boolean b) {
			print(b);
			println();
		}

		public void println(final byte b) {
			print(b);
			println();
		}

		public void println(final short s) {
			print(s);
			println();
		}

		@Override
		public void println(final int i) {
			print(i);
			println();
		}

		@Override
		public void println(final long l) {
			print(l);
			println();
		}

		@Override
		public void println(final float f) {
			print(f);
			println();
		}

		@Override
		public void println(final double d) {
			print(d);
			println();
		}

		public void println(final double d, final int n) {
			print(d, n);
			println();
		}

		@Override
		public void println(final char c) {
			print(c);
			println();
		}

		@Override
		public void println(final char[] s) {
			print(s);
			println();
		}

		@Override
		public void println(final String s) {
			print(s);
			println();
		}

		@Override
		public void println(final Object o) {
			print(o);
			println();
		}

		@Override
		public Output append(final char c) {
			print(c);
			return this;
		}

		@Override
		public Output append(CharSequence csq) {
			if (csq == null) csq = "null";
			print(csq.toString());
			return this;
		}

		@Override
		public Output append(CharSequence csq, final int start, final int end) {
			if (csq == null) csq = "null";
			print(csq.subSequence(start, end).toString());
			return this;
		}
	}

	public static final class DummyOut extends java.io.PrintStream {

		public DummyOut() {
			super(new Dummy());
		}

		private static class Dummy extends java.io.OutputStream {

			@Override
			public void close() {

			}

			@Override
			public void flush() {

			}

			@Override
			public void write(final byte[] b) {

			}

			@Override
			public void write(final byte[] b, final int off, final int len) {

			}

			@Override
			public void write(final int b) {

			}
		}
	}
}
