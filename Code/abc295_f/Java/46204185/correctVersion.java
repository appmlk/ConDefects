//Utilities
import java.io.*;
import java.util.*;

public class Main {
	static int t;
	static String s, l, r;
	
	public static void main(String[] args) throws IOException {
		t = in.iscan();
		while (t-- > 0) {
			s = in.sscan(); l = in.sscan(); r = in.sscan();
			out.println(calc(r) - calc(""+(Long.parseLong(l)-1)));
		}
		out.close();
	}
	
	static long calc(String x) {
		long ret = 0;
		boolean zeroPrefix = s.charAt(0) == '0';
		for (int i = 0; i + s.length() - 1 < x.length(); i++) {
			int l = i, r = i + s.length() - 1;
			long xx = Long.parseLong(x.substring(l, r+1));
			long ss = Long.parseLong(s);
			String lstr = x.substring(0, l), rstr = x.substring(r+1);
			long[] dpl = computeDp(lstr), dpr = computeDp(rstr);
			if (ss < xx) {
				if (!zeroPrefix) {
					ret += dpl[0] * (dpr[0] + dpr[1] + dpr[2]) + dpl[1] * (dpr[0] + dpr[1] + dpr[2]);
				}
				else {
					if (i != 0) {
						ret += (dpl[0]-1) * (dpr[0] + dpr[1] + dpr[2]) + dpl[1] * (dpr[0] + dpr[1] + dpr[2]);
					}
					else {
						ret += dpl[0] * (dpr[0] + dpr[1] + dpr[2]) + (dpl[1]-1) * (dpr[0] + dpr[1] + dpr[2]);
					}
				}
			}
			else if (ss == xx) {
				if (!zeroPrefix) {
					ret += dpl[0] * (dpr[0] + dpr[1] + dpr[2]) + dpl[1] * (dpr[0] + dpr[1]);
				}
				else {
					if (i != 0) {
						ret += (dpl[0]-1) * (dpr[0] + dpr[1] + dpr[2]) + dpl[1] * (dpr[0] + dpr[1]);
					}
					else {
						ret += dpl[0] * (dpr[0] + dpr[1] + dpr[2]) + (dpl[1]-1) * (dpr[0] + dpr[1]);
					}
				}
			}
			else { // ss > xx
				if (!zeroPrefix) {
					ret += dpl[0] * (dpr[0] + dpr[1] + dpr[2]);
				}
				else {
					if (i != 0) {
						ret += (dpl[0]-1) * (dpr[0] + dpr[1] + dpr[2]);
					}
					else {
						ret += dpl[0] * (dpr[0] + dpr[1] + dpr[2]);
					}
				}
			}
		}
		return ret;
	}
	
	static long[] digitDp; // [0 - less, 1 - equal, 2 - more]
	// digitDp[i] =
	// # of non-negative integers with up to num.length() digits that are less than num, if i = 0
	// # of non-negative integers with up to num.length() digits that are equal to num, if i = 1
	// # of non-negative integers with up to num.length() digits that are greater than num, if i = 2
	static long[] computeDp(String num){
		int len = num.length();
		long[] digitDp = new long[3]; digitDp[1] = 1;
		for (int i = len-1; i >= 0; i--) {
			int curDigit = Integer.parseInt(""+num.charAt(i));
			long[] newDigitDp = new long[3];
			for (int d = 0; d <= 9; d++) {
				if (d < curDigit) {
					newDigitDp[0] = combCnt(newDigitDp[0], combCnt(combCnt(digitDp[0], digitDp[1]), digitDp[2]));
				}
				else if (d == curDigit) {
					newDigitDp[0] = combCnt(newDigitDp[0], digitDp[0]);
					newDigitDp[1] = combCnt(newDigitDp[1], digitDp[1]);
					newDigitDp[2] = combCnt(newDigitDp[2], digitDp[2]);
				}
				else { // d > curDigit
					newDigitDp[2] = combCnt(newDigitDp[2], combCnt(combCnt(digitDp[0], digitDp[1]), digitDp[2]));
				}
			}
			digitDp = newDigitDp;
		}
		return digitDp;
	}
	
	static long combCnt(long a, long b) {
		return a + b; // if MOD is needed, change to (a + b) % MOD
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
