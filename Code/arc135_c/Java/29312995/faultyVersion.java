import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class Main {

	static int mod = (int) (Math.pow(10, 9)+7);
	static final int dx[] = { 0, 0, -1, 1 }, dy[] = { -1, 1, 0, 0 };
	static final int[] dx8 = { 1, 1, 0, -1, -1, -1, 0, 1 }, dy8 = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static final int[] dx9 = { -1, -1, -1, 0, 0, 0, 1, 1, 1 }, dy9 = { -1, 0, 1, -1, 0, 1, -1, 0, 1 };
	static final int inf = Integer.MAX_VALUE / 2;
	static final int minf = inf * -1;
	static final long infL = Long.MAX_VALUE / 2;
	static final long minfL = infL * -1;
	static final double infD = Double.MAX_VALUE / 3;
	static final double eps = 1e-10;
	static final double pi = Math.PI;
	static final double pi2 = pi*2;
	static int H, W;
	static StringBuilder out = new StringBuilder();




	public static void main(String[] args) {
		FastScanner sc = new FastScanner(System.in);
		int n = sc.nextInt();
		int[] s = new int[n];
		for(int i = 0; i < n; i++) {
			s[i] = sc.nextInt();
			
		}
		
		
		while(true) {
			int[] bc = new int[31];
			for(int i = 0; i < n; i++) {
				int tmp = s[i];
				for(int j = 0; j < 31; j++) {
					if((tmp & 1) == 1) {
						bc[j]++;
					}
					
					tmp >>= 1;
				}
			}
			
			long max = 0;
			int maxv = -1;
			for(int i = 0; i < n; i++) {
				int tmp = s[i];
				long sum = 0;
				for(int j = 0; j < 31; j++) {
					if((tmp & 1) == 1) {
						sum += (n-bc[j]*2)*(1L<<(j*2));
					}
					tmp >>= 1;
				}
				
				if(sum > max) {
					max = sum;
					maxv = s[i];
				}
			}
			
			if(maxv == -1)
				break;
			
			for(int i = 0; i < n; i++)
				s[i] ^= maxv;
		}
		
		long ans = 0;
		for(int i = 0; i < n; i++) {
			ans += s[i];
		}
		
		out.append(ans);
		System.out.println(out);
	}


	///				///
///	ライブラリ	///
///				///
	public static boolean[][] rotate(int d, boolean[][] arr){
		if(d == 0)
			return arr;

		int h = arr.length;
		int w = arr[0].length;

		boolean[][] ret = new boolean[h][w];
		for(int i = 0; i < h; i++) {
			int y = i;
			if(d == 2 || d == 3)
				y = h-i-1;

			for(int j = 0; j < w; j++) {
				int x = j;
				if(d == 1 || d == 3)
					x = w-j-1;

				ret[i][j] = arr[y][x];
			}
		}

		return ret;
	}

	public static int bitsMsb(int bits) {
		bits = bits | (bits >> 1);
		bits = bits | (bits >> 2);
		bits = bits | (bits >> 4);
		bits = bits | (bits >> 8);
		bits = bits | (bits >> 16);
		return bits ^ (bits >> 1);
	}
	public static long bitsMsb(long bits) {
		for(int i = 1; i <= 32; i *= 2)
			bits |= bits >>i;

		return bits ^ (bits>>1);
	}
	public static double log2(double x) {
	    // 特殊な値
	    if (Double.isNaN(x) || x < 0.0) return Double.NaN;
	    if (x == Double.POSITIVE_INFINITY) return Double.POSITIVE_INFINITY;
	    if (x == 0.0) return Double.NEGATIVE_INFINITY;
	    // ここから
	    int k = Math.getExponent(x);
	    if (k < Double.MIN_EXPONENT) {
	        // 非正規化数は取扱い注意！
	        k = Math.getExponent(x * 0x1.0p52) - 52;
	    }
	    if (k < 0) {
	        k++;
	    }
	    double s = Math.scalb(x, -k);
	    final double LOG2_E = 1.4426950408889634;
	    return k + LOG2_E * Math.log(s);
	}


	// 組み合わせの数を計算する(a個の中からb個を選ぶ）
	//(10*9*8)/(3*2*1);
	//10*9*8 -> ansMul
	//3*2*1 -> ansDiv
	static long calcComb(int a, int b) {
		if (b > a - b) return calcComb(a, a - b);
		long ansMul = 1;
		long ansDiv = 1;
		for (int i = 0; i < b; i++) {
			ansMul *= (a - i);
			ansDiv *= (i + 1);
			ansMul %= mod;
			ansDiv %= mod;
		}
		long ans = ansMul * modpow(ansDiv, mod - 2, mod) % mod;

		return ans;
	}



	static boolean isOutBoard(int y, int x) {
		return y < 0 || y >= H || x < 0 || x >= W;
	}

	static <T> int upperBound(T[] ar, T x) {
        if(ar instanceof Integer[]) {
            return ~Arrays.binarySearch(ar, x, (t1, t2) -> (Integer)t1 - (Integer)t2 > 0 ? 1 : -1);
        }
        else if(ar instanceof Long[]) {
            return ~Arrays.binarySearch(ar, x, (t1, t2) -> (Long)t1 - (Long)t2 > 0 ? 1 : -1);
        }
        else if(ar instanceof Double[]) {
            return ~Arrays.binarySearch(ar, x, (t1, t2) -> (Double)t1 - (Double)t2 > 0 ? 1 : -1);
        }
        else {
            System.err.println(String.format("%s:数値でない配列を二分探索しています。",Thread.currentThread().getStackTrace()[1].getMethodName()));
            throw new RuntimeException();
        }
    }
	static <T> int lowerBound(T[] ar, T x) {
        if(ar instanceof Integer[]) {
            return ~Arrays.binarySearch(ar, x, (t1, t2) -> (Integer)t1 - (Integer)t2 >= 0 ? 1 : -1);
        }
        else if(ar instanceof Long[]) {
            return ~Arrays.binarySearch(ar, x, (t1, t2) -> (Long)t1 - (Long)t2 >= 0 ? 1 : -1);
        }
        else if(ar instanceof Double[]) {
            return ~Arrays.binarySearch(ar, x, (t1, t2) -> (Double)t1 - (Double)t2 >= 0 ? 1 : -1);
        }
        else {
            System.err.println(String.format("%s:数値でない配列を二分探索しています。",Thread.currentThread().getStackTrace()[1].getMethodName()));
            throw new RuntimeException();
        }
    }

	// O(N log log N)
	public static boolean[] sieve(int n) {
		boolean[] isPrime = new boolean[n+1];
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;

		for(int i = 2; i <= n; i++) {
			if(isPrime[i]) {
				for(int j = 2 * i; j <= n; j += i) {
					isPrime[j] = false;
				}
			}
		}

		return isPrime;
	}

	// 与えられた配列の中身を辞書順に、次の順列をセットする（次がなければ　false を返す）
	public static boolean next_permutation(int[] a) {
		for (int i = a.length - 2; i >= 0; i--) {
			if (a[i] < a[i + 1]) {
				for (int j = a.length - 1;; j--) {
					if (a[i] < a[j]) {
						int temp = a[i];
						a[i] = a[j];
						a[j] = temp;
						for (i++, j = a.length - 1; i < j; i++, j--) {
							temp = a[i];
							a[i] = a[j];
							a[j] = temp;
						}
						return true;
					}
				}
			}
		}
		return false;
	}
	public static boolean next_permutation(long[] a) {
		for (int i = a.length - 2; i >= 0; i--) {
			if (a[i] < a[i + 1]) {
				for (int j = a.length - 1;; j--) {
					if (a[i] < a[j]) {
						long temp = a[i];
						a[i] = a[j];
						a[j] = temp;
						for (i++, j = a.length - 1; i < j; i++, j--) {
							temp = a[i];
							a[i] = a[j];
							a[j] = temp;
						}
						return true;
					}
				}
			}
		}
		return false;
	}

	private static int binarySearch2D(long key, long[][] a, int ind) {
		return binarySearch2D(key, a, ind, 0,  a.length);
	}

	private static int binarySearch2D(long key, long[][] ps, int ind, int fromIndex, int toIndex) {

		int low = fromIndex;
		int high = toIndex - 1;

		while (low <= high) {
		int mid = (low + high) >>> 1;
		long midVal = ps[mid][ind];

		if (midVal < key)
			low = mid + 1;
		else if (midVal > key)
			high = mid - 1;
		else
			return mid; // key found
		}
		return -(low + 1);  // key not found.
	}

	private static int binarySearch2D(double key, double[][] a, int ind) {
		return binarySearch2D(key, a, ind, 0,  a.length);
	}
	private static int binarySearch2D(double key, double[][] ps, int ind, int fromIndex, int toIndex) {

		int low = fromIndex;
		int high = toIndex - 1;

		while (low <= high) {
		int mid = (low + high) >>> 1;
		double midVal = ps[mid][ind];

		if (midVal < key)
			low = mid + 1;
		else if (midVal > key)
			high = mid - 1;
		else
			return mid; // key found
		}
		return -(low + 1);  // key not found.
	}


	//b^e mod m
	private static long modpow(long b, long e, int m) {
		long result = 1;
		b %= m;

		while(e > 0) {
			if((e&1) == 1) result = (result * b) % m;
			e >>= 1;
			b = (b*b) % m;
		}

		return result;
	}

	private static int gcd(int a, int b) {
		if(b == 0) return a;
		return gcd(b, a%b);
	}
	private static long gcd(long a, long b) {
		if(b == 0) return a;
		return gcd(b, a%b);
	}

	public static int lcm(int a, int b) {
        return a / gcd(a, b) * b;
	}
	public static long lcm(long a, long b) {
		return a / gcd(a, b) * b;
	}


	/** 拡張ユークリッド互除法
	 * ax + by = gcd(a, b) となる a, b の最大公約数と解 x, y を求める
	 * @param a ： 数値１(>0)
	 * @param b ： 数値２(>0)
	 * @return<int[] ： {[0]:gcd, [1]:x, [2]:y}：最大公約数(なし=1 [互いに素])と解 x, y
	 */
	public static final int[] extgcd(int a, int b) {
	    int x0 = 1, x1 = 0;
	    int y0 = 0, y1 = 1;

	    while (b != 0) {
	        int q = a / b;
	        int r = a % b;
	        int x2 = x0 - q * x1;
	        int y2 = y0 - q * y1;

	        a = b; b = r;
	        x0 = x1; x1 = x2;
	        y0 = y1; y1 = y2;
	    }

	    return new int[]{a, x0, y0};
	}

	static int digits(long x) {
	    int numDigits;
	    long d;
	    if (x < 0) {
	        if (x == Long.MIN_VALUE) return 20;
	        numDigits = 1;
	        d = - x;
	    } else {
	        numDigits = 0;
	        d = x;
	    }
	    if (d >= 1_0000_0000_0000_0000L) {
	        d /= 1_0000_0000_0000_0000L;
	        numDigits += 16;
	    }
	    if (d >= 1_0000_0000L) {
	        d /= 1_0000_0000L;
	        numDigits += 8;
	    }
	    if (d >= 1_0000L) {
	        d /= 1_0000L;
	        numDigits += 4;
	    }
	    if (d >= 1_00L) {
	        d /= 1_00L;
	        numDigits += 2;
	    }
	    return numDigits + (d >= 10L ? 2 : 1);
	}
	static int digits(int y) {
		long x = y;
	    int numDigits;
	    long d;
	    if (x < 0) {
	        if (x == Long.MIN_VALUE) return 20;
	        numDigits = 1;
	        d = - x;
	    } else {
	        numDigits = 0;
	        d = x;
	    }
	    if (d >= 1_0000_0000_0000_0000L) {
	        d /= 1_0000_0000_0000_0000L;
	        numDigits += 16;
	    }
	    if (d >= 1_0000_0000L) {
	        d /= 1_0000_0000L;
	        numDigits += 8;
	    }
	    if (d >= 1_0000L) {
	        d /= 1_0000L;
	        numDigits += 4;
	    }
	    if (d >= 1_00L) {
	        d /= 1_00L;
	        numDigits += 2;
	    }
	    return numDigits + (d >= 10L ? 2 : 1);
	}

}

class SegmentTree {
	int inf = 0;
	int[] node, lazy;
	int n;
	int mod = 998244353;

	SegmentTree(int n){
		int x = 1;
		while(n > x) x *= 2;
		this.n = x;

		node = new int[x*2 -1];
		lazy = new int[x*2 -1];

		refresh();
	}

	SegmentTree(int[] arr){
		int n = arr.length;
		int x = 1;
		while(n > x) x *= 2;
		this.n = x;

		node = new int[x*2 -1];
		lazy = new int[x*2 -1];

		reset(arr);
	}

	public int merge(int a, int b) {
		return (a+b)%mod;
	}
	public int setNode(int k) {
		return lazy[k];
	}
	public int empty() {
		return -1;
	}

	public void reset(int[] arr) {
		int ln = arr.length;
		int i;
		for(i = 0; i < ln; i++) {
			node[i+n-1] = arr[i];
		}
		for(i = i+n; i < node.length; i++)
			node[i] = inf;

		for(i = n-2; i >= 0; i--)
			node[i] = merge(node[i*2 +1], node[i*2 +2]);

		Arrays.fill(lazy, inf);
	}
	public void refresh() {
		Arrays.fill(node,inf);
		Arrays.fill(lazy, inf);
	}

	public void update(int a, int b, int x) {
		update(a, b, x, 0, 0, n);
	}
	public void update(int a, int b, int x, int k, int l, int r) {

		if(b <= l || r <= a)
			return;

		if(a <= l && r <= b) {
			lazy[k] = x;
			eval(k);
		}else {
			update(a, b, x, k*2 +1, l, (l+r)/2);
			update(a, b, x, k*2 +2, (l+r)/2, r);
			node[k] = merge(node[k*2 +1], node[k*2 +2]);
		}
	}

	public int query(int a, int b) {
		int re = query(a, b, 0, 0, n);

		if(re == inf)
			re = empty();
		return re;
	}
	public int query(int a, int b, int k, int l, int r) {
		if(b <= l || r <= a)
			return inf;

		eval(k);
		if(a <= l && r <= b)
			return node[k];
		else
			return merge(query(a, b, k*2 +1, l, (l+r)/2), query(a, b, k*2 +2, (l+r)/2, r));
	}

	public void eval(int k) {
		if(lazy[k] != inf) {
			node[k] = setNode(k);

			if(k*2 +2 < node.length) {
				lazy[k*2 +1] = lazy[k];
				lazy[k*2 +2] = lazy[k];
			}

			lazy[k] = inf;
		}
	}
	public void evalAll() {
		for(int j = 0; j < lazy.length; j++) {
			eval(j);
		}
	}

	public void getArr(int[] arr) {
		int ln = arr.length;

		evalAll();

		for(int i = 0; i < ln; i++) {
			int re = node[i+n-1];
			if(re == inf)
				re = empty();
			arr[i] = re;
		}
	}
}


class SegmentTreeL {
	long inf = Long.MIN_VALUE/2;
	long[] node, lazy;
	int n;

	SegmentTreeL(int n){
		int x = 1;
		while(n > x) x *= 2;
		this.n = x;

		node = new long[x*2 -1];
		lazy = new long[x*2 -1];

		refresh();
	}

	SegmentTreeL(long[] arr){
		int n = arr.length;
		int x = 1;
		while(n > x) x *= 2;
		this.n = x;

		node = new long[x*2 -1];
		lazy = new long[x*2 -1];

		reset(arr);
	}

	public long merge(long a, long b) {
		return Math.max(a, b);
	}
	public long setNode(int k) {
		return lazy[k];
	}
	public long empty() {
		return -1;
	}

	public void reset(long[] arr) {
		int ln = arr.length;
		int i;
		for(i = 0; i < ln; i++) {
			node[i+n-1] = arr[i];
		}
		for(i = i+n; i < node.length; i++)
			node[i] = inf;

		for(i = n-2; i >= 0; i--)
			node[i] = merge(node[i*2 +1], node[i*2 +2]);

		Arrays.fill(lazy, inf);
	}
	public void refresh() {
		Arrays.fill(node,inf);
		Arrays.fill(lazy, inf);
	}

	public void update(int a, int b, long x) {
		update(a, b, x, 0, 0, n);
	}
	public void update(int a, int b, long x, int k, int l, int r) {

		if(b <= l || r <= a)
			return;

		if(a <= l && r <= b) {
			lazy[k] = x;
			eval(k);
		}else {
			update(a, b, x, k*2 +1, l, (l+r)/2);
			update(a, b, x, k*2 +2, (l+r)/2, r);
			node[k] = merge(node[k*2 +1], node[k*2 +2]);
		}
	}

	public long query(int a, int b) {
		long re = query(a, b, 0, 0, n);

		if(re == inf)
			re = empty();
		return re;
	}
	public long query(int a, int b, int k, int l, int r) {
		if(b <= l || r <= a)
			return inf;

		eval(k);
		if(a <= l && r <= b)
			return node[k];
		else
			return merge(query(a, b, k*2 +1, l, (l+r)/2), query(a, b, k*2 +2, (l+r)/2, r));
	}

	public void eval(int k) {
		if(lazy[k] != inf) {
			node[k] = setNode(k);

			if(k*2 +2 < node.length) {
				lazy[k*2 +1] = lazy[k];
				lazy[k*2 +2] = lazy[k];
			}

			lazy[k] = inf;
		}
	}
	public void evalAll() {
		for(int j = 0; j < lazy.length; j++) {
			eval(j);
		}
	}

	public void getArr(long[] arr) {
		int ln = arr.length;

		evalAll();

		for(int i = 0; i < ln; i++) {
			long re = node[i+n-1];
			if(re == inf)
				re = empty();
			arr[i] = re;
		}
	}
}

class a {
	public List<Integer> l = new ArrayList<>();

	public a(){
	}

	public void add(int n){
		l.add(n);
	}
	public int gf() {
		return l.get(0);
	}
	public int get(int n) {
		return l.get(n);
	}
	public int size() {
		return l.size();
	}
}


class cmp implements Comparator<a> {

	@Override
	public int compare(a p1, a p2) {
		int ind = 0;
		while(ind < p1.size()) {
			if(p1.get(ind) != p2.get(ind))
				return p1.get(ind) - p2.get(ind);

			ind++;
		}


		return 0;
	}
}

//class Pair{
//	int a;
//	int b;
//
//	public Pair(int a, int b) {
//		this.a = a;
//		this.b = b;
//	}
//}
class Pair{
	int a;
	Integer b;

	public Pair(int a, int b) {
		this.a = a;
		this.b = b;
	}

	public boolean equals(Object o) {
		Pair p = (Pair)o;
		return (this.a == p.a && this.b == p.b);
	}
	public int hashCode() {
		return ("" + a + " " + b).hashCode();
	}
}
class PairIL{
	int a;
	Long b;

	public PairIL(int a, long b) {
		this.a = a;
		this.b = b;
	}

	public boolean equals(Object o) {
		PairIL p = (PairIL)o;
		return (this.a == p.a && this.b == p.b);
	}
	public int hashCode() {
		return ("" + a + " " + b).hashCode();
	}
}
class PairID{
	int a;
	Double b;

	public PairID(int a, double b) {
		this.a = a;
		this.b = b;
	}
}

class PairLL{
	long a;
	long b;

	public PairLL(long a, long b) {
		this.a = a;
		this.b = b;
	}
	public boolean equals(Object o) {
		PairLL p = (PairLL)o;
		return (this.a == p.a && this.b == p.b);
	}
	public int hashCode() {
		return ("" + a + " " + b).hashCode();
	}
}

class Trio{
	int a;
	int b;
	int c;

	public Trio(int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
}
class TrioILI{
	int a;
	long b;
	int c;

	public TrioILI(int a, long b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
}
class TrioILL{
	int a;
	Long b;
	Long c;

	public TrioILL(int a, long b, long c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
}
class TrioIIL{
	int a;
	int b;
	Long c;

	public TrioIIL(int a, int b, long c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public boolean equals(Object o) {
		TrioIIL t = (TrioIIL)o;
		return (this.a == t.a && this.b == t.b && this.c == t.c);
	}
	public int hashCode() {
		return ("" + a + " " + b + " " + c).hashCode();
	}
}

class Quar{
	int a;
	int b;
	int c;
	int d;

	public Quar(int a, int b, int c, int d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}
}



class UnionFind{
    int[] parent;
    int[] rank;
    int[] size;
    public UnionFind(int n){
        this.parent = new int[n];
        this.rank = new int[n];
        this.size = new int[n];
        for(int i = 0; i < n; i++) set(i);
    }
    public void set(int i) {
        parent[i] = i;
        rank[i] = 0;
        size[i] = 1;
    }
    public int find(int i) {
        if (i == parent[i])
            return parent[i];
        else
            return parent[i] = find(parent[i]);
    }
    public boolean same(int x, int y){
        return find(x) == find(y);
    }
    public void unite(int x, int y) {
        int xroot = find(x);
        int yroot = find(y);
        if(xroot == yroot)
            return;
        if(rank[xroot] > rank[yroot]) {
            parent[yroot] = xroot;
            size[xroot] += size[yroot];
        }
        else if(rank[xroot] < rank[yroot]) {
            parent[xroot] = yroot;
            size[yroot] += size[xroot];
        }
        else {
            parent[yroot] = xroot;
            size[xroot] += size[yroot];
            rank[xroot]++;
        }
    }
    public int get_size(int i) {
        return size[find(i)];
    }
    public int group_num() {
        Set<Integer> parents = new HashSet<>();
        for(int i = 1; i < this.parent.length; i++)
            parents.add(find(i));
        return parents.size();
    }
}

class FastScanner {
    private final InputStream in = System.in;
    private final byte[] buffer = new byte[1024];
    private int ptr = 0;
    private int buflen = 0;
    public FastScanner(InputStream in2) {
	}
	private boolean hasNextByte() {
        if (ptr < buflen) {
            return true;
        }else{
            ptr = 0;
            try {
                buflen = in.read(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (buflen <= 0) {
                return false;
            }
        }
        return true;
    }
    private int readByte() { if (hasNextByte()) return buffer[ptr++]; else return -1;}
    private static boolean isPrintableChar(int c) { return 33 <= c && c <= 126;}
    private static boolean isNewLineChar(int c) {return 10 <= c && c <= 13;}
    public boolean hasNext() { while(hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++; return hasNextByte();}
    public String next() {
        if (!hasNext()) throw new NoSuchElementException();
        StringBuilder sb = new StringBuilder();
        int b = readByte();
        while(isPrintableChar(b)) {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }
    public String nextLine() {
    	if (!hasNext()) throw new NoSuchElementException();
        StringBuilder sb = new StringBuilder();
        int b = readByte();
        while(!isNewLineChar(b)) {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }
    public long nextLong() {
        if (!hasNext()) throw new NoSuchElementException();
        long n = 0;
        boolean minus = false;
        int b = readByte();
        if (b == '-') {
            minus = true;
            b = readByte();
        }
        if (b < '0' || '9' < b) {
            throw new NumberFormatException();
        }
        while(true){
            if ('0' <= b && b <= '9') {
                n *= 10;
                n += b - '0';
            }else if(b == -1 || !isPrintableChar(b)){
                return minus ? -n : n;
            }else{
                throw new NumberFormatException();
            }
            b = readByte();
        }
    }
    public int nextInt() {
        long nl = nextLong();
        if (nl < Integer.MIN_VALUE || nl > Integer.MAX_VALUE) throw new NumberFormatException();
        return (int) nl;
    }
    public double nextDouble() { return Double.parseDouble(next());}
}

/*
 * AVL木
 *
 * add				O(logN)
 * remove			O(logN)
 * find			O(logN)	return true | false
 * get				O(logN)	存在しなかったらnullを返す
 * get_upper		O(logN)	存在しなかったらnullを返す
 * get_lower		O(logN)	存在しなかったらnullを返す
 * get_greater		O(logN)	存在しなかったらnullを返す
 * get_less_than	O(logN)	存在しなかったらnullを返す
 * get_list			O(N)
 *
 * getSize			O(1)
 * getArray			O(N)
 *
 */
class AVLTree<K extends Comparable<? super K>> { // K:キーの型, V:値の型
    ///////////////////////////////////////////////////////////////////////////
    // 共通定義
    ///////////////////////////////////////////////////////////////////////////

    private class Node { // ノードの型
        int height;      // そのノードを根とする部分木の高さ
        K key;           // そのノードのキー
        Node lst = null; // 左部分木
        Node rst = null; // 右部分木

        Node(int height, K key) {
            this.height = height;
            this.key = key;
        }
    }
    private Node root = null; // AVL木の根
    private boolean change;   // 修正が必要かを示すフラグ(true:必要, false:不要)
    private K lmax;           // 左部分木のキーの最大値
    private int size = 0;

    // 部分木 t の高さを返す
    private int height(Node t) { return t == null ? 0 : t.height; }

    // 左右の部分木の高さの差を返す。左が高いと正、右が高いと負
    private int bias(Node t) { return height(t.lst) - height(t.rst); }

    // 左右の部分木の高さから、その木の高さを計算して修正する
    private void modHeight(Node t) {
        t.height = 1 + Math.max(height(t.lst), height(t.rst));
    }

    // ２分探索木 v の左回転。回転した木を返す
    private Node rotateL(Node v) {
        Node u = v.rst, t2 = u.lst;
        u.lst = v; v.rst = t2;
        modHeight(u.lst);
        modHeight(u);
        return u;
    }

    // ２分探索木 u の右回転。回転した木を返す
    private Node rotateR(Node u) {
        Node v = u.lst, t2 = v.rst;
        v.rst = u; u.lst = t2;
        modHeight(v.rst);
        modHeight(v);
        return v;
    }

    // ２分探索木 t の二重回転(左回転 -> 右回転)。回転した木を返す
    private Node rotateLR(Node t) {
        t.lst = rotateL(t.lst);
        return rotateR(t);
    }

    // ２分探索木 t の二重回転(右回転 -> 左回転)。回転した木を返す
    private Node rotateRL(Node t) {
        t.rst = rotateR(t.rst);
        return rotateL(t);
    }

    ///////////////////////////////////////////////////////////////////////////
    // バランス回復
    ///////////////////////////////////////////////////////////////////////////

    // 挿入時の修正(balanceLi:左部分木への挿入, balanceRi:右部分木への挿入)
    private Node balanceLi(Node t) { return balanceL(t); }
    private Node balanceRi(Node t) { return balanceR(t); }

    // 削除時の修正(balanceLd:左部分木での削除, balanceRd:右部分木での削除)
    private Node balanceLd(Node t) { return balanceR(t); }
    private Node balanceRd(Node t) { return balanceL(t); }

    // 部分木 t のバランスを回復して戻り値で返す
    // 左部分木への挿入に伴うAVL木の修正
    // 右部分木での削除に伴うAVL木の修正
    private Node balanceL(Node t) {
        if (!change) return t;
        int h = height(t);
        if (bias(t) == 2) {
            if (bias(t.lst) >= 0)
                t = rotateR(t);
            else
                t = rotateLR(t);
        }
        else modHeight(t);
        change = (h != height(t));
        return t;
    }

    // 部分木 t のバランスを回復して戻り値で返す
    // 右部分木への挿入に伴うAVL木の修正
    // 左部分木での削除に伴うAVL木の修正
    private Node balanceR(Node t) {
        if (!change) return t;
        int h = height(t);
        if (bias(t) == -2) {
            if (bias(t.rst) <= 0)
                t = rotateL(t);
            else
                t = rotateRL(t);
        }
        else modHeight(t);
        change = (h != height(t));
        return t;
    }

    ///////////////////////////////////////////////////////////////////////////
    // insert(挿入)
    ///////////////////////////////////////////////////////////////////////////

    // エントリー(key, x のペア)を挿入する
    public void add(K key) {

    	root = add(root, key);
    	size++;
    }

    private Node add(Node t, K key) {
        if (t == null) {
            change = true;
            return new Node(1, key);
        }
        else if (key.compareTo(t.key) < 0) {
            t.lst = add(t.lst, key);
            return balanceLi(t);
        }
        else if (key.compareTo(t.key) > 0) {
            t.rst = add(t.rst, key);
            return balanceRi(t);
        }
        else {
            change = false;
            size--;
            return t;
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // delete(削除)
    ///////////////////////////////////////////////////////////////////////////

    // key で指すエントリー(ノード)を削除する
    public void remove(K key) {
    	root = remove(root, key);
    	size--;
    }

    private Node remove(Node t, K key) {
        if (t == null) {
            change = false;
            size++;
            return null;
        }
        else if (key.compareTo(t.key) < 0) {
            t.lst = remove(t.lst, key);
            return balanceLd(t);
        }
        else if (key.compareTo(t.key) > 0) {
            t.rst = remove(t.rst, key);
            return balanceRd(t);
        }
        else {
            if (t.lst == null) {
                change = true;
                return t.rst; // 右部分木を昇格させる
            }
            else {
                t.lst = deleteMax(t.lst); // 左部分木の最大値を削除する
                t.key = lmax; // 左部分木の削除した最大値で置き換える
                return balanceLd(t);
            }
        }
    }

    // 部分木 t の最大値のノードを削除する
    // 戻り値は削除により修正された部分木
    // 削除した最大値を lmax に保存する
    private Node deleteMax(Node t) {
        if (t.rst != null) {
            t.rst = deleteMax(t.rst);
            return balanceRd(t);
        }
        else {
            change = true;
            lmax = t.key; // 部分木のキーの最大値を保存
            return t.lst; // 左部分木を昇格させる
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // 検索等
    ///////////////////////////////////////////////////////////////////////////

    // キーの検索。ヒットすれば true、しなければ false
    public boolean find(K key) {
        Node t = root;
        while (t != null) {
            if      (key.compareTo(t.key) < 0) t = t.lst;
            else if (key.compareTo(t.key) > 0) t = t.rst;
            else return true;
        }
        return false;
    }

    // キーから値を得る。キーがヒットしない場合は null を返す
    public K get(K key) {
        Node t = root;
        while (t != null) {
            if      (key.compareTo(t.key) < 0)
            	t = t.lst;
            else if (key.compareTo(t.key) > 0)
            	t = t.rst;
            else return t.key;
        }
        return null;
    }

    public K get_upper(K key) {
    	K up = null;
    	Node t = root;
    	while (t != null) {
    		if(t.key.compareTo(key) >= 0)
    			if(up == null)
    				up = t.key;
    			else if(t.key.compareTo(up) < 0)
    				up = t.key;

    		if		(key.compareTo(t.key) < 0)
    			t = t.lst;
    		else if	(key.compareTo(t.key) > 0)
    			t = t.rst;
    		else
    			return t.key;
    	}

    	return up;
    }
    public K get_lower(K key) {
    	K lw = null;
    	Node t = root;
    	while (t != null) {
    		if(t.key.compareTo(key) <= 0)
    			if(lw == null)
    				lw = t.key;
    			else if(t.key.compareTo(lw) > 0)
    				lw = t.key;

    		if		(key.compareTo(t.key) < 0)
    			t = t.lst;
    		else if	(key.compareTo(t.key) > 0)
    			t = t.rst;
    		else
    			return t.key;
    	}

    	return lw;
    }


    public K get_greater(K key) {
    	K gt = null;
    	Node t = root;
    	while (t != null) {
    		if(t.key.compareTo(key) > 0)
    			if(gt == null)
    				gt = t.key;
    			else if(t.key.compareTo(gt) < 0)
    				gt = t.key;

    		if(key.compareTo(t.key) < 0)
    			t = t.lst;
    		else
    			t = t.rst;
    	}

    	return gt;
    }
    public K get_less_than(K key) {
    	K lt = null;
    	Node t = root;
    	while (t != null) {
    		if(t.key.compareTo(key) < 0)
    			if(lt == null)
    				lt = t.key;
    			else if(t.key.compareTo(lt) > 0)
    				lt = t.key;

    		if(key.compareTo(t.key) <= 0)
    			t = t.lst;
    		else
    			t = t.rst;
    	}

    	return lt;
    }

    // マップが空なら true、空でないなら false
    public boolean isEmpty() { return root == null; }

    // マップを空にする
    public void clear() { root = null; }

    // キーのリスト
    public ArrayList<K> keys() {
        ArrayList<K> al = new ArrayList<K>();
        keys(root, al);
        return al;
    }

    public int getSize() {
    	return size;
    }

    // キーの最小値
    public K min() {
        Node t = root;
        if (t == null) return null;
        while (t.lst != null) t = t.lst;
        return t.key;
    }

    // キーの最大値
    public K max() {
        Node t = root;
        if (t == null) return null;
        while (t.rst != null) t = t.rst;
        return t.key;
    }

    private void keys(Node t, ArrayList<K> al) {
        if (t != null) {
            keys(t.lst, al);
            al.add(t.key);
            keys(t.rst, al);
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // debug 用ルーチン
    ///////////////////////////////////////////////////////////////////////////

    // AVL木をグラフ文字列に変換する
    public String toString() {
        return toGraph("", "", root).replaceAll("\\s+$", "");
    }

    // AVL木のバランスが取れているか確認する
    public boolean balanced() { return balanced(root); }

    // ２分探索木になっているか確認する
    public boolean bstValid() { return bstValid(root); }

    private String toGraph(String head, String bar, Node t) {
        String graph = "";
        if (t != null) {
            graph += toGraph(head + "　　", "／", t.rst);
            String node = "" + t.height;
            node += ":" + t.key;
            graph += String.format("%s%s%s%n", head, bar, node);
            graph += toGraph(head + "　　", "＼", t.lst);
        }
        return graph;
    }

    private boolean balanced(Node t) {
        if (t == null) return true;
        return Math.abs(bias(t)) <= 1 && balanced(t.lst) && balanced(t.rst);
    }

    private boolean bstValid(Node t) {
        if (t == null) return true;
        boolean flag = small(t.key, t.lst) && large(t.key, t.rst);
        return flag && bstValid(t.lst) && bstValid(t.rst);
    }

    private boolean small(K key, Node t) {
        if (t == null) return true;
        boolean flag = key.compareTo(t.key) > 0;
        return flag && small(key, t.lst) && small(key, t.rst);
    }

    private boolean large(K key, Node t) {
        if (t == null) return true;
        boolean flag = key.compareTo(t.key) < 0;
        return flag && large(key, t.lst) && large(key, t.rst);
    }
}
