//Utilities
import java.io.*;
import java.util.*;

public class Main {
	static int n, q;
	static char[] ch;
	static int[] a;
	static int cmd, l, r, x, c;
	static RangeBIT[] countBIT;
	static MinSegmentTree minSeg;
	static MaxSegmentTree maxSeg;
	static TreeSet<Integer> tset;
	
	public static void main(String[] args) throws IOException {
		n = in.iscan();
		ch = in.sscan().toCharArray();
		a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = ch[i]-'a';
		}
		tset = new TreeSet<Integer>();
		countBIT = new RangeBIT[26]; 
		minSeg = new MinSegmentTree(n, a); 
		maxSeg = new MaxSegmentTree(n, a);
		for (int i = 0; i < 26; i++) {
			countBIT[i] = new RangeBIT(n);
		}
		for (int i = 0; i < n; i++) {
			if (i == 0 || i > 0 && ch[i] != ch[i-1]) {
				tset.add(i);
			}
			countBIT[ch[i]-'a'].range_add(i+1, i+1, 1);
		}
		q = in.iscan();
		outer : while (q-- > 0) {
			cmd = in.iscan();
			if (cmd == 1) {
				x = in.iscan()-1; c = in.sscan().charAt(0) - 'a';
				int tmp = ch[x] - 'a';
				countBIT[tmp].range_add(x+1, x+1, -1);
				minSeg.update(x, c); maxSeg.update(x, c);
				countBIT[c].range_add(x+1, x+1, 1);
				tset.remove(x); tset.remove(x-1); tset.remove(x+1);
				ch[x] = (char)(c+'a');
				if (x-1 >= 0 && (x-1 == 0 || x-1 > 0 && ch[x-1] != ch[x-2])) {
					tset.add(x-1);
				}
				if (x == 0 || x > 0 && ch[x] != ch[x-1]) {
					tset.add(x);
				}
				if (x+1 < n && ch[x+1] != ch[x]) {
					tset.add(x+1);
				}
			}
			else {
				l = in.iscan()-1; r = in.iscan()-1;
				boolean isSorted = true;
				Integer idx = tset.floor(l);
				char prev = ch[idx];
				while (true) {
					idx = tset.higher(idx);
					if (idx == null || idx > r) {
						break;
					}
					char cur = ch[idx];
					if (cur < prev) {
						isSorted = false;
						break;
					}
					prev = cur;
				}
				if (!isSorted) {
					out.println("No");
					continue outer;
				}
				boolean hasCount = true;
				int min = minSeg.query(l, r), max = maxSeg.query(l, r);
				for (int i = min+1; i <= max-1; i++) {
					long curCount = countBIT[i].range_sum(l+1, r+1);
					long totCount = countBIT[i].range_sum(l+1, r+1);
					if (curCount != totCount) {
						hasCount = false;
						break;
					}
				}
				if (!hasCount) {
					out.println("No");
				}
				else {
					out.println("Yes");
				}
			}
		}
		out.close();
	}
	
	static class RangeBIT {
		int n;
		long[] m, a;
		
		RangeBIT(int n){
			this.n = n;
			m = new long[n+1];
			a = new long[n+1];
		}
		
		RangeBIT(int n, long[] b) {
			this.n = n;
			m = new long[n+1];
			a = new long[n+1];
			for (int i = 1; i <= n; i++) {
				range_add(i, i, b[i]);
			}
		}
		
		void range_add(int l, int r, long v) { // NOTE: l and r cannot be 0, use one-indexed representation of l and r
		    add(m, l, v);
		    add(m, r+1, -v);
		    add(a, l, v*(l-1));
		    add(a, r+1, -v*r);
		}
		
		void add(long[] a, int idx, long v) {
			for (int i = idx; i <= n; i += i&-i) {
				a[i] += v;
			}
		}

		long sum(long[] a, int idx) {
		    long ret = 0;
		    for (int i = idx; i > 0; i -= i&-i) {
		    	ret += a[i];
		    }
		    return ret;
		}
		
		long prefix_sum(int idx) {
			return sum(m, idx)*idx - sum(a, idx);
		}
		
		long range_sum(int l, int r) {
			return prefix_sum(r) - prefix_sum(l-1);
		}
	}
	
	static class MinSegmentTree {
		int n;
		int[] a, st;
		
		MinSegmentTree(int n){
			this.n = n;
			a = new int[n];
			st = new int[4*n+5];
		}
		
		MinSegmentTree(int n, int[] b){
			this.n = n;
			a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = b[i];
			}
			st = new int[4*n+5];
			init(1, 0, n-1);
		}
		
		void init(int i, int l, int r){
		    if (l == r) { 
		    	st[i] = a[l]; 
		    	return; 
		    }
		    int mid = (l+r)/2;
		    init(2*i, l, mid);
		    init(2*i+1, mid + 1, r);
		    st[i] = comb(st[2*i], st[2*i+1]);
		}
		
		void update(int idx, int v) {
			update(1, 0, n-1, idx, v);
		}

		void update(int i, int l, int r, int idx, int v){
		    if (l == r) {
		    	st[i] = v; // NOTE: might want to change this to optimize for speed
		    	return;
		    }
		    int mid = (l+r)/2;
		    if (idx <= mid) {
		    	update(2*i, l, mid, idx, v);
		    }
		    else {
		    	update(2*i+1, mid + 1, r, idx, v);
		    }
		    st[i] = comb(st[2*i], st[2*i+1]);
		}
		
		int query(int ql, int qr) {
			return query(1, 0, n-1, ql, qr);
		}

		int query(int i, int l, int r, int ql, int qr){
		    if (qr < l || ql > r) return Integer.MAX_VALUE; // NOTE: might need to change this depending on the "comb" method
		    if (ql <= l && r <= qr) return st[i];
		    int mid = (l+r) / 2;
		    return comb(query(2*i, l, mid, ql, qr), query(2*i+1, mid + 1, r, ql, qr));
		}

		int comb(int a, int b) {
			return Math.min(a, b);
		}
	}
	
	static class MaxSegmentTree {
		int n;
		int[] a, st;
		
		MaxSegmentTree(int n){
			this.n = n;
			a = new int[n];
			st = new int[4*n+5];
		}
		
		MaxSegmentTree(int n, int[] b){
			this.n = n;
			a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = b[i];
			}
			st = new int[4*n+5];
			init(1, 0, n-1);
		}
		
		void init(int i, int l, int r){
		    if (l == r) { 
		    	st[i] = a[l]; 
		    	return; 
		    }
		    int mid = (l+r)/2;
		    init(2*i, l, mid);
		    init(2*i+1, mid + 1, r);
		    st[i] = comb(st[2*i], st[2*i+1]);
		}
		
		void update(int idx, int v) {
			update(1, 0, n-1, idx, v);
		}

		void update(int i, int l, int r, int idx, int v){
		    if (l == r) {
		    	st[i] = v; // NOTE: might want to change this to optimize for speed
		    	return;
		    }
		    int mid = (l+r)/2;
		    if (idx <= mid) {
		    	update(2*i, l, mid, idx, v);
		    }
		    else {
		    	update(2*i+1, mid + 1, r, idx, v);
		    }
		    st[i] = comb(st[2*i], st[2*i+1]);
		}
		
		int query(int ql, int qr) {
			return query(1, 0, n-1, ql, qr);
		}

		int query(int i, int l, int r, int ql, int qr){
		    if (qr < l || ql > r) return Integer.MIN_VALUE; // NOTE: might need to change this depending on the "comb" method
		    if (ql <= l && r <= qr) return st[i];
		    int mid = (l+r) / 2;
		    return comb(query(2*i, l, mid, ql, qr), query(2*i+1, mid + 1, r, ql, qr));
		}

		int comb(int a, int b) {
			return Math.max(a, b);
		}
	}
	
	static INPUT in = new INPUT(System.in);
	static PrintWriter out = new PrintWriter(System.out);
	private static class INPUT {
 
		private InputStream stream;
		private byte[] buf = new byte[1024];
		private int curChar, numChars;
 
		public INPUT (InputStream stream) {
			this.stream = stream;
		}
 
		public INPUT (String file) throws IOException {
			this.stream = new FileInputStream (file);
		}
 
		public int cscan () throws IOException {
			if (curChar >= numChars) {
				curChar = 0;
				numChars = stream.read (buf);
			}
			
			if (numChars == -1)
				return numChars;
 
			return buf[curChar++];
		}
 
		public int iscan () throws IOException {
			int c = cscan (), sgn = 1;
			
			while (space (c))
				c = cscan ();
 
			if (c == '-') {
				sgn = -1;
				c = cscan ();
			}
 
			int res = 0;
 
			do {
				res = (res << 1) + (res << 3);
				res += c - '0';
				c = cscan ();
			}
			while (!space (c));
 
			return res * sgn;
		}
 
		public String sscan () throws IOException {
			int c = cscan ();
			
			while (space (c))
				c = cscan ();
 
			StringBuilder res = new StringBuilder ();
 
			do {
				res.appendCodePoint (c);
				c = cscan ();
			}
			while (!space (c));
 
			return res.toString ();
		}
 
		public double dscan () throws IOException {
			int c = cscan (), sgn = 1;
			
			while (space (c))
				c = cscan ();
 
			if (c == '-') {
				sgn = -1;
				c = cscan ();
			}
 
			double res = 0;
 
			while (!space (c) && c != '.') {
				if (c == 'e' || c == 'E')
					return res * UTILITIES.fast_pow (10, iscan ());
				
				res *= 10;
				res += c - '0';
				c = cscan ();
			}
 
			if (c == '.') {
				c = cscan ();
				double m = 1;
 
				while (!space (c)) {
					if (c == 'e' || c == 'E')
						return res * UTILITIES.fast_pow (10, iscan ());
 
					m /= 10;
					res += (c - '0') * m;
					c = cscan ();
				}
			}
 
			return res * sgn;
		}
 
		public long lscan () throws IOException {
			int c = cscan (), sgn = 1;
			
			while (space (c))
				c = cscan ();
 
			if (c == '-') {
				sgn = -1;
				c = cscan ();
			}
 
			long res = 0;
 
			do {
				res = (res << 1) + (res << 3);
				res += c - '0';
				c = cscan ();
			}
			while (!space (c));
 
			return res * sgn;
		}
 
		public boolean space (int c) {
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}
	}
 
	public static class UTILITIES {
 
		static final double EPS = 10e-6;
		
		public static void sort(int[] a, boolean increasing) {
			ArrayList<Integer> arr = new ArrayList<Integer>();
			int n = a.length;
			for (int i = 0; i < n; i++) {
				arr.add(a[i]);
			}
			Collections.sort(arr);
			for (int i = 0; i < n; i++) {
				if (increasing) {
					a[i] = arr.get(i);
				}
				else {
					a[i] = arr.get(n-1-i);
				}
			}
		}
		
		public static void sort(long[] a, boolean increasing) {
			ArrayList<Long> arr = new ArrayList<Long>();
			int n = a.length;
			for (int i = 0; i < n; i++) {
				arr.add(a[i]);
			}
			Collections.sort(arr);
			for (int i = 0; i < n; i++) {
				if (increasing) {
					a[i] = arr.get(i);
				}
				else {
					a[i] = arr.get(n-1-i);
				}
			}
		}
		
		public static void sort(double[] a, boolean increasing) {
			ArrayList<Double> arr = new ArrayList<Double>();
			int n = a.length;
			for (int i = 0; i < n; i++) {
				arr.add(a[i]);
			}
			Collections.sort(arr);
			for (int i = 0; i < n; i++) {
				if (increasing) {
					a[i] = arr.get(i);
				}
				else {
					a[i] = arr.get(n-1-i);
				}
			}
		}
 
		public static int lower_bound (int[] arr, int x) {
			int low = 0, high = arr.length, mid = -1;
 
			while (low < high) {
				mid = (low + high) / 2;
 
				if (arr[mid] >= x)
					high = mid;
				else
					low = mid + 1;
			}
 
			return low;
		}
 
		public static int upper_bound (int[] arr, int x) {
			int low = 0, high = arr.length, mid = -1;
 
			while (low < high) {
				mid = (low + high) / 2;
 
				if (arr[mid] > x)
					high = mid;
				else
					low = mid + 1;
			}
 
			return low;
		}
		
		public static void updateMap(HashMap<Integer, Integer> map, int key, int v) {
			if (!map.containsKey(key)) {
				map.put(key, v);
			}
			else {
				map.put(key, map.get(key) + v);
			}
			if (map.get(key) == 0) {
				map.remove(key);
			}
		}
 
		public static long gcd (long a, long b) {
			return b == 0 ? a : gcd (b, a % b);
		}
 
		public static long lcm (long a, long b) {
			return a * b / gcd (a, b);
		}
 
		public static long fast_pow_mod (long b, long x, int mod) {
			if (x == 0) return 1;
			if (x == 1) return b % mod;
			if (x % 2 == 0) return fast_pow_mod (b * b % mod, x / 2, mod) % mod;
 
			return b * fast_pow_mod (b * b % mod, x / 2, mod) % mod;
		}
 
		public static long fast_pow (long b, long x) {
			if (x == 0) return 1;
			if (x == 1) return b;
			if (x % 2 == 0) return fast_pow (b * b, x / 2);
 
			return b * fast_pow (b * b, x / 2);
		}
 
		public static long choose (long n, long k) {
			if (k > n || k < 0) {
				return 0;
			}
			k = Math.min (k, n - k);
			long val = 1;
 
			for (int i = 0; i < k; ++i)
				val = val * (n - i) / (i + 1);
 
			return val;
		}
 
		public static long permute (int n, int k) {
			if (n < k) return 0;
			long val = 1;
 
			for (int i = 0; i < k; ++i)
				val = (val * (n - i));
 
			return val;
		}
		
		// start of permutation and lower/upper bound template
		public static void nextPermutation(int[] nums) {
		    //find first decreasing digit
		    int mark = -1;
		    for (int i = nums.length - 1; i > 0; i--) {
		        if (nums[i] > nums[i - 1]) {
		            mark = i - 1;
		            break;
		        }
		    }
		 
		    if (mark == -1) {
		        reverse(nums, 0, nums.length - 1);
		        return;
		    }
		 
		    int idx = nums.length-1;
		    for (int i = nums.length-1; i >= mark+1; i--) {
		        if (nums[i] > nums[mark]) {
		            idx = i;
		            break;
		        }
		    }
		 
		    swap(nums, mark, idx);
		 
		    reverse(nums, mark + 1, nums.length - 1);
		}
		 
		public static void swap(int[] nums, int i, int j) {
		    int t = nums[i];
		    nums[i] = nums[j];
		    nums[j] = t;
		}
		 
		public static void reverse(int[] nums, int i, int j) {
		    while (i < j) {
		        swap(nums, i, j);
		        i++;
		        j--;
		    }
		}
		
		static int lower_bound (int[] arr, int hi, int cmp) {
			int low = 0, high = hi, mid = -1;
			while (low < high) {
				mid = (low + high) / 2;
				if (arr[mid] >= cmp) high = mid;
				else low = mid + 1;
			}
			return low;
		}
	 
		static int upper_bound (int[] arr, int hi, int cmp) {
			int low = 0, high = hi, mid = -1;
			while (low < high) {
				mid = (low + high) / 2;
				if (arr[mid] > cmp) high = mid;
				else low = mid + 1;
			}
			return low;
		} 
		// end of permutation and lower/upper bound template
	}
}
