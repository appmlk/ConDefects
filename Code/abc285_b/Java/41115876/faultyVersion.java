//Utilities
import java.io.*;
import java.util.*;
 
public class Main {
	static int n;
	static char[] ch;
	static int[] res;
	
	public static void main(String[] args) throws IOException {
		n = in.iscan();
		ch = in.sscan().toCharArray();
		res = new int[n+1];
		for (int i = 1; i <= n; i++) {
			res[i] = n-i;
		}
		for (int i = 0; i < n; i++) {
			for (int j = i+1; j < n; j++) {
				if (ch[i] == ch[j]) {
					res[j-i] = Math.min(res[j-i], i);
				}
			}
		}
		for (int i = 1; i <= n; i++) {
			out.println(res[i]);
		}
		out.close();
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
