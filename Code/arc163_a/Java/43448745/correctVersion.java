import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

class RUQ {
	int N;
	int[] originalData;
	int[] tree;

	int INT_MIN = Integer.MIN_VALUE / 2;

	RUQ(int[] original) {
		this.originalData = Arrays.copyOf(original, original.length);
		int n = originalData.length;
		this.N = 1;
		while (N < n)
			N *= 2;
		this.tree = new int[N * 2];
		for (int i = 0; i < N * 2; i++) {
			tree[i] = INT_MIN;
		}
		build();
	}

	void build() {
		for (int i = 0; i < originalData.length; i++) {
			update(i, originalData[i]);
		}
	}

	void update(int i, int x) {
		i = N + i - 1;
		tree[i] = x;
		while (i > 0) {
			i = (i - 1) / 2;
			tree[i] = marge(tree[i * 2 + 1], tree[i * 2 + 2]);
		}
	}

	int query(int start, int end) {
		return query(start, end, 0, 0, N);
	}

	private int query(int a, int b, int k, int l, int r) {
		if (r <= a || b <= l)
			return INT_MIN;

		if (a <= l && r <= b) {
			return tree[k];
		} else {
			int lv = query(a, b, 2 * k + 1, l, (l + r) / 2);
			int rb = query(a, b, 2 * k + 2, (l + r) / 2, r);
			return marge(lv, rb);
		}
	}

	private int marge(int l, int r) {
		return Math.max(l, r);
	}
}

class RMQ {
	int N;
	int[] originalData;
	int[] tree;

	int INT_MAX = Integer.MAX_VALUE / 2;

	RMQ(int[] original) {
		this.originalData = Arrays.copyOf(original, original.length);
		int n = originalData.length;
		this.N = 1;
		while (N < n)
			N *= 2;
		this.tree = new int[N * 2];
		for (int i = 0; i < N * 2; i++) {
			tree[i] = INT_MAX;
		}
		build();
	}

	void build() {
		for (int i = 0; i < originalData.length; i++) {
			update(i, originalData[i]);
		}
	}

	void update(int i, int x) {
		i = N + i - 1;
		tree[i] = x;
		while (i > 0) {
			i = (i - 1) / 2;
			tree[i] = marge(tree[i * 2 + 1], tree[i * 2 + 2]);
		}
	}

	int query(int start, int end) {
		return query(start, end, 0, 0, N);
	}

	private int query(int a, int b, int k, int l, int r) {
		if (r <= a || b <= l)
			return INT_MAX;

		if (a <= l && r <= b) {
			return tree[k];
		} else {
			int lv = query(a, b, 2 * k + 1, l, (l + r) / 2);
			int rb = query(a, b, 2 * k + 2, (l + r) / 2, r);
			return marge(lv, rb);
		}
	}

	private int marge(int l, int r) {
		return Math.min(l, r);
	}
}

class BIT {
	long bit[] = new long[0];
	int N = 0;

	BIT(int n) {
		bit = new long[n + 1];
		N = n;
	}

	void add(int a, long w) {
		for (int x = a; x <= N; x += (x & -x))
			bit[x] += w;
	}

	long sum(int a) {
		long ret = 0;
		for (int x = a; x > 0; x -= x & -x)
			ret += bit[x];
		return ret;
	}

	long modsum(int a, int mod) {
		long ret = 0;
		for (int x = a; x > 0; x -= x & -x) {
			ret += bit[x];
			ret %= mod;
		}
		return ret;
	}
}

class MODBIT {
	long bit[] = new long[0];
	int N = 0;
	int mod = 0;

	MODBIT(int n, int m) {
		bit = new long[n + 1];
		N = n;
		mod = m;
	}

	void add(int a, long w) {
		for (int x = a; x < N; x += (x & -x)) {
			bit[x] += w;
			bit[x] %= mod;
		}
	}

	long modsum(int a, long mod2) {
		long ret = 0;
		for (int x = a; x > 0; x -= x & -x) {
			ret += bit[x];
			ret %= mod2;
		}
		return ret;
	}
}

class FastScanner {
	private final InputStream in = System.in;
	private final byte[] buffer = new byte[1024];
	private int ptr = 0;
	private int buflen = 0;

	private boolean hasNextByte() {
		if (ptr < buflen) {
			return true;
		} else {
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

	private int readByte() {
		if (hasNextByte())
			return buffer[ptr++];
		else
			return -1;
	}

	private static boolean isPrintableChar(int c) {
		return 33 <= c && c <= 126;
	}

	public boolean hasNext() {
		while (hasNextByte() && !isPrintableChar(buffer[ptr]))
			ptr++;
		return hasNextByte();
	}

	public String next() {
		if (!hasNext())
			throw new NoSuchElementException();
		StringBuilder sb = new StringBuilder();
		int b = readByte();
		while (isPrintableChar(b)) {
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}

	public long nextLong() {
		if (!hasNext())
			throw new NoSuchElementException();
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
		while (true) {
			if ('0' <= b && b <= '9') {
				n *= 10;
				n += b - '0';
			} else if (b == -1 || !isPrintableChar(b)) {
				return minus ? -n : n;
			} else {
				throw new NumberFormatException();
			}
			b = readByte();
		}
	}

	public int nextInt() {
		long nl = nextLong();
		if (nl < Integer.MIN_VALUE || nl > Integer.MAX_VALUE)
			throw new NumberFormatException();
		return (int) nl;
	}

	public double nextDouble() {
		return Double.parseDouble(next());
	}

	public char nextchar() {
		return next().charAt(0);
	}
}

class Unionfind {
	private int n;
	private int[] parentOrSize;

	public Unionfind(int n) {
		this.n = n;
		this.parentOrSize = new int[n];
		java.util.Arrays.fill(parentOrSize, -1);
	}

	int merge(int a, int b) {
		if (!(0 <= a && a < n))
			throw new IndexOutOfBoundsException("a=" + a);
		if (!(0 <= b && b < n))
			throw new IndexOutOfBoundsException("b=" + b);

		int x = leader(a);
		int y = leader(b);
		if (x == y) return x;
		if (-parentOrSize[x] < -parentOrSize[y]) {
			int tmp = x;
			x = y;
			y = tmp;
		}
		parentOrSize[x] += parentOrSize[y];
		parentOrSize[y] = x;
		return x;
	}

	boolean same(int a, int b) {
		if (!(0 <= a && a < n))
			throw new IndexOutOfBoundsException("a=" + a);
		if (!(0 <= b && b < n))
			throw new IndexOutOfBoundsException("b=" + b);
		return leader(a) == leader(b);
	}

	int leader(int a) {
		if (parentOrSize[a] < 0) {
			return a;
		} else {
			parentOrSize[a] = leader(parentOrSize[a]);
			return parentOrSize[a];
		}
	}

	int size(int a) {
		if (!(0 <= a && a < n))
			throw new IndexOutOfBoundsException("" + a);
		return -parentOrSize[leader(a)];
	}

	java.util.ArrayList<java.util.ArrayList<Integer>> groups() {
		int[] leaderBuf = new int[n];
		int[] groupSize = new int[n];
		for (int i = 0; i < n; i++) {
			leaderBuf[i] = leader(i);
			groupSize[leaderBuf[i]]++;
		}
		java.util.ArrayList<java.util.ArrayList<Integer>> result = new java.util.ArrayList<>(n);
		for (int i = 0; i < n; i++) {
			result.add(new java.util.ArrayList<>(groupSize[i]));
		}
		for (int i = 0; i < n; i++) {
			result.get(leaderBuf[i]).add(i);
		}
		result.removeIf(java.util.ArrayList::isEmpty);
		return result;
	}
}
class Comb {
	int N = 0;
	int mod = 0;
	long fact[], ifact[], inv[];

	Comb(int n, int m) {
		N = n;
		mod = m;
		fact = new long[N + 10];
		ifact = new long[N + 10];
		inv = new long[N + 10];
		inv[1] = 1;
		for (int i = 2; i < N; i++)
			inv[i] = 1l * (mod - mod / i) * inv[mod % i] % mod;
		fact[0] = ifact[0] = 1;
		for (int i = 1; i < N; i++) {
			fact[i] = 1l * fact[i - 1] * i % mod;
			ifact[i] = 1l * ifact[i - 1] * inv[i] % mod;
		}
	}

	public long C(int x, int y) {
		if (x < 0 || y < 0 || x < y)
			return 0;
		return 1l * fact[x] * ifact[y] % mod * ifact[x - y] % mod;
	}
}

public class Main {

	static class Pair<T, E> {
		public T first;
		public E second;

		public Pair(T x, E y) {
			first = x;
			second = y;
		}

		void set(T x, E y) {
			first = x;
			second = y;
		}
		@Override
		public int hashCode() {
			return Objects.hash(this.first,this.second);
		}
		@Override
		public boolean equals(Object obj) {
			if(obj instanceof Pair) {
				Pair p=(Pair)obj;
				return Objects.equals(first, p.first)&&Objects.equals(second, p.second);
			}
			else {
				return false;
			}
		}
	}

	static class Tpair {
		public int first;
		public int second;
		public long third;

		Tpair(int x, int y, long z) {
			first = x;
			second = y;
			third = z;
		}

		void set(int x, int y, long z) {
			first = x;
			second = y;
			third = z;
		}
		@Override
		public boolean equals(Object obj) {
			if(obj instanceof Tpair) {
				Tpair other=(Tpair)obj;
				return other.first==this.first&&other.second==this.second&&other.third==this.third;
			}
			return false;
		}
		@Override
		public int hashCode() {
			return Objects.hash(this.first,this.second,this.third);
		}
	}

	static class Pint {
		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Pint) {
				Pint other = (Pint) obj;
				return other.first == this.first && other.second == this.second;
			}
			return false;
		}

		@Override
		public int hashCode() {
			return Objects.hash(this.first, this.second);
		}

		public int first;
		public int second;

		Pint(int x, int y) {
			first = x;
			second = y;
		}

		void set(int x, int y) {
			first = x;
			second = y;
		}
	}

	static FastScanner scan = new FastScanner();
	static Scanner scanner = new Scanner(System.in);
	static Random rand = new Random();
	static int mod = 1000000007;
	static double eps = 1.0E-14;
	static int big = Integer.MAX_VALUE / 2;
	static double PI = 3.141592653589793;

	static long modlcm(long a, long b) {
		return a * b * modinv(GCD(a, b), mod);
	}

	static long GCD(long a, long b) {
		return b > 0 ? GCD(b, a % b) : a;
	}

	static long lcm(long a, long b) {
		return a * b / GCD(a, b);
	}

	static int min(int a, int b) {
		return a < b ? a : b;
	}

	static long factorial(int i) {
		return i == 1 ? 1 : i * factorial(i - 1);
	}

	static int max(int... i) {
		int x = i[0];
		for (int e : i)
			x = Math.max(x, e);
		return x;
	}

	static int min(int... i) {
		int x = i[0];
		for (int e : i)
			x = Math.min(x, e);
		return x;
	}

	static long gcd(long... i) {
		long x = i[0];
		for (long e : i)
			x = GCD(x, e);
		return x;
	}

	static long lmax(long... i) {
		long x = i[0];
		for (long e : i)
			x = Math.max(x, e);
		return x;
	}

	static long lmin(long... i) {
		long x = i[0];
		for (long e : i)
			x = Math.min(x, e);
		return x;
	}

	static long nCr(long n, long r, long m) {
		long ans = 1;
		for (long i = 0; i < r; i++) {
			ans *= (n - i);
			ans %= m;
		}
		for (long i = 1; i <= r; i++) {
			ans *= modinv(i, m);
			ans %= m;
		}
		return ans;
	}

	static int lower_bound(long[] b, long cost) {
		int ok = b.length;
		int ng = -1;
		while (Math.abs(ok - ng) > 1) {
			int mid = (ok + ng) / 2;
			if (b[mid] >= cost)
				ok = mid;
			else
				ng = mid;
		}
		return ok;
	}

	static int upper_bound(int[] b, int cost) {
		int ok = b.length;
		int ng = -1;
		while (Math.abs(ok - ng) > 1) {
			int mid = (ok + ng) / 2;
			if (b[mid] > cost)
				ok = mid;
			else
				ng = mid;
		}
		return ok;
	}

	static boolean isPrime(long n) {
		if (n == 2)
			return true;
		if (n < 2 || n % 2 == 0)
			return false;
		double d = Math.sqrt(n);
		for (int i = 3; i <= d; i += 2)
			if (n % i == 0) {
				return false;
			}
		return true;
	}

	static int upper_division(int a, int b) {
		if (a % b == 0) {
			return a / b;
		} else {
			return a / b + 1;
		}
	}

	static long lupper_division(long a, long b) {
		if (a % b == 0) {
			return a / b;
		} else {
			return a / b + 1;
		}
	}

	static int[] setArray(int a) {
		int b[] = new int[a];
		for (int i = 0; i < a; i++) {
			b[i] = scan.nextInt();
		}
		return b;
	}

	static long[] lsetArray(int a) {
		long b[] = new long[a];
		for (int i = 0; i < a; i++) {
			b[i] = scan.nextLong();
		}
		return b;
	}

	static String reverse(String str) {
		char ch[] = new char[str.length()];
		char chch[] = str.toCharArray();
		int a = str.length();
		for (int i = 0; i < upper_division(a, 2); i++) {
			ch[i] = chch[ch.length - i - 1];
			ch[ch.length - 1 - i] = chch[i];
		}
		return String.valueOf(ch);
	}

	public static void printArray(int[] que) {
		for (int i = 0; i < que.length - 1; i++) {
			System.out.print(que[i] + " ");
		}
		System.out.println(que[que.length - 1]);
	}

	public static void lprintArray(long[] que) {
		for (int i = 0; i < que.length - 1; i++) {
			System.out.print(que[i] + " ");
		}
		System.out.println(que[que.length - 1]);
	}


	static long modpow(long x, long n, long mo) {
		long sum = 1;
		while (n > 0) {
			if ((n & 1) == 1) {
				sum = sum * x % mo;
			}
			x = x * x % mo;
			n >>= 1;
		}
		return sum;
	}

	static long pow(long x, long n) {
		long sum = 1;
		while (n > 0) {
			if ((n & 1) == 1) {
				sum = sum * x;
			}
			x = x * x;
			n >>= 1;
		}
		return sum;
	}

	public static char[] revch(char ch[]) {
		char ret[] = new char[ch.length];
		for (int i = ch.length - 1, j = 0; i >= 0; i--, j++) {
			ret[j] = ch[i];
		}
		return ret;
	}

	public static int[] revint(int ch[]) {
		int ret[] = new int[ch.length];
		for (int i = ch.length - 1, j = 0; i >= 0; i--, j++) {
			ret[j] = ch[i];
		}
		return ret;
	}

	public static void warshall_floyd(long v[][]) {
		int n = v[0].length;
		for (int k = 0; k < n; k++)
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					v[i][j] = lmin(v[i][j], v[i][k] + v[k][j]);
	}

	public static long modinv(long a, long m) {
		long b = m, u = 1, v = 0;
		while (b != 0) {
			long t = a / b;
			a -= t * b;
			long x = a;
			a = b;
			b = x;
			u -= t * v;
			x = u;
			u = v;
			v = x;
		}
		u %= m;
		if (u < 0)
			u += m;
		return u;
	}

	public static long lmod(long i, long j) {
		return (i % j) < 0 ? (i % j) + 0 + (j < 0 ? -j : j) : (i % j + 0);
	}

	public static int next_combination(int sub) {
		int x = sub & -sub, y = sub + x;
		return (((sub & ~y) / x) >> 1) | y;
	}

	static long sum(long... a) {
		long cnt = 0;
		for (int j = 0; j < a.length; j++) {
			cnt += a[j];
		}
		return cnt;
	}

	static class Dijkstra {
		public int node;
		public long cost;

		Dijkstra(int x, long y) {
			node = x;
			cost = y;
		}
	}
	static int sum(int... a) {
		int x=0;
		for(int e:a)x+=e;
		return x;
	}
	static boolean isrevstr(char ch[]) {
		boolean ret=true;
			for(int i=0;i<ch.length;i++) {
				if(ch[i]!=ch[ch.length-1-i]) {
					ret=false;
					break;
				}
			}
		return ret;
	}
	public static void doublesort(int a[][]){
		  merge(0,a.length-1,a);
		  return;
		 }
		 public static boolean compare(int a,int b,int[][]origin) {
		  for(int i=0;i<origin[a].length;i++) {
		   if(origin[a][i]>origin[b][i]) {
		    return false;
		   }
		   else if(origin[a][i]<origin[b][i]) {
		    return true;
		   }
		  }
		  return true;
		 }
		 public static void merge(int left,int right,int[][]origin) {
		  if(left==right) {
		   return;
		  }
		  else {
		   int mid=(left+right)/2;
		   merge(left,mid,origin);
		   merge(mid+1,right,origin);
		   int hoge2[][]=new int[right-left+1][origin[0].length];
		   int itr=0;
		   int leftcount=0;
		   int rightcount=0;
		   while(leftcount<=(mid-left)||rightcount<=(right-(mid+1))) {
		    if(leftcount==mid-left+1) {
		     for(int i=0;i<origin[0].length;i++) {
		      hoge2[itr][i]=origin[mid+rightcount+1][i];
		     }
		     rightcount++;
		    }
		    else if(rightcount==right-(mid+1)+1) {
		     for(int i=0;i<origin[0].length;i++) {
		      hoge2[itr][i]=origin[left+leftcount][i];
		     }
		     leftcount++;
		    }
		    else {
		     if(compare(left+leftcount,mid+rightcount+1,origin)) {
		      for(int i=0;i<origin[0].length;i++) {
		       hoge2[itr][i]=origin[left+leftcount][i];
		      }
		      leftcount++;
		     }
		     else {
		      for(int i=0;i<origin[0].length;i++) {
		       hoge2[itr][i]=origin[mid+rightcount+1][i];
		      }
		      rightcount++;
		     }
		    }
		    itr++;
		   }
		   for(int i=0;i<(right-left+1);i++) {
		    for(int j=0;j<origin[0].length;j++) {
		     origin[left+i][j]=hoge2[i][j];
		    }
		   }
		  }
		 }
		 public static void ldoublesort(long a[][]){
			  lmerge(0,a.length-1,a);
			  return;
			 }
			 public static boolean lcompare(int a,int b,long[][]origin) {
			  for(int i=0;i<origin[a].length;i++) {
			   if(origin[a][i]>origin[b][i]) {
			    return false;
			   }
			   else if(origin[a][i]<origin[b][i]) {
			    return true;
			   }
			  }
			  return true;
			 }
			 public static void lmerge(int left,int right,long[][]origin) {
			  if(left==right) {
			   return;
			  }
			  else {
			   int mid=(left+right)/2;
			   lmerge(left,mid,origin);
			   lmerge(mid+1,right,origin);
			   long hoge2[][]=new long[right-left+1][origin[0].length];
			   int itr=0;
			   int leftcount=0;
			   int rightcount=0;
			   while(leftcount<=(mid-left)||rightcount<=(right-(mid+1))) {
			    if(leftcount==mid-left+1) {
			     for(int i=0;i<origin[0].length;i++) {
			      hoge2[itr][i]=origin[mid+rightcount+1][i];
			     }
			     rightcount++;
			    }
			    else if(rightcount==right-(mid+1)+1) {
			     for(int i=0;i<origin[0].length;i++) {
			      hoge2[itr][i]=origin[left+leftcount][i];
			     }
			     leftcount++;
			    }
			    else {
			     if(lcompare(left+leftcount,mid+rightcount+1,origin)) {
			      for(int i=0;i<origin[0].length;i++) {
			       hoge2[itr][i]=origin[left+leftcount][i];
			      }
			      leftcount++;
			     }
			     else {
			      for(int i=0;i<origin[0].length;i++) {
			       hoge2[itr][i]=origin[mid+rightcount+1][i];
			      }
			      rightcount++;
			     }
			    }
			    itr++;
			   }
			   for(int i=0;i<(right-left+1);i++) {
			    for(int j=0;j<origin[0].length;j++) {
			     origin[left+i][j]=hoge2[i][j];
			    }
			   }
			  }
			 }
	public static void main(String[] $) throws IOException {
		int t=scan.nextInt();
		while(t>0) {
			t--;
			int n=scan.nextInt();
			boolean bool=false;
			char ch[]=scan.next().toCharArray();
			loop:for(int i=0;i<n-1;i++) {//[0,i+1],[i+2,n-1]
				char c1[]=new char[i+1];
				char c2[]=new char[n-(i+2)+1];
				for(int j=0;j<i+1;j++) {
					c1[j]=ch[j];
				}
				for(int j=0;j<n-(i+2)+1;j++) {
					c2[j]=ch[j+i+1];
				}
				int minlen=min(c1.length,c2.length);
				for(int j=0;j<minlen;j++) {
					if(c1[j]<c2[j]) {
						bool=true;
						break loop;
					}
					else if(c1[j]>c2[j]) {
						continue loop;
					}
				}
				if(minlen==c1.length&&minlen!=c2.length) {
					bool=true;
					break loop;
				}
			}
			System.out.println(bool?"Yes":"No");
		}
	}
}