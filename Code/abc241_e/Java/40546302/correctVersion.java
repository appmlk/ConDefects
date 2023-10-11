import java.io.*;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.*;


public class Main {
	
	static int maxInt = Integer.MAX_VALUE/3;
	static long maxLong = Long.MAX_VALUE/3;
	
	public static void main(String[] args) throws IOException {
		new Thread(new Task()).start();		
	}

	static class Task implements Runnable {		
		
		public void run()  {
			InputStream inputStream = System.in;
			OutputStream outputStream = System.out;
			PrintWriter out = new PrintWriter(outputStream);
			InputReader in = new InputReader(inputStream);
			


//			for(int i=4;i<=4;i++) {
//				InputStream uinputStream = new FileInputStream("timeline.in");
			// String f = i+".in";
			// InputStream uinputStream = new FileInputStream(f);
//				InputReader in = new InputReader(uinputStream);
//				PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("timeline.out")));
//			}
			//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("timeline.out")));

			try {
				solve(in, out);
			} catch (IOException e) {
				e.printStackTrace();
			}
			out.close();
			
		}	
		

		public void solve(InputReader in, PrintWriter out) throws IOException {
			//System.out.println(Long.MAX_VALUE);
			int n = in.nextInt();
			long k = in.nextLong();
			int[] arr = in.readIntArray(n);
			long s[] = new long[n+1];
			long sum = 0;
			int cur = 0;
			int step[] = new int[n];
			int cnt = 0;
			while(step[cur]==0) {
				cnt++;
				step[cur] = cnt;
				sum+=arr[cur];				
				cur = (int) (sum%n);	
				s[cnt] = sum;
			}
			int start = step[cur];
			int end = cnt;
			long cycle_sum = s[end] - s[start-1];
			int sz = end-start+1;
			
			long ret = 0;
			
			if(end<=k) {
				ret = s[start-1];
				k-=(start-1);
				ret += cycle_sum*(k/sz);
				k%=sz;
				ret += s[(int)k+start-1]-s[start-1];
			}else {
				ret = s[(int)k];
			}				
			out.println(ret);
		}
		
	    public static long modPow(long X, long K) {
	        long ret = 1;
	        while (K > 0) {
	            if ((K & 1) != 0) ret = (ret * X) % 998244353;
	            X = (X * X) % 998244353;
	            K = K >> 1;
	        }
	        return ret;
	    }
	    public static long inverse(long X) {
	        return modPow(X, 998244353 - 2);
	    }		
				
	    

	 
		public long GCD(long a, long b) {
			if (b == 0)
				return a;
			return GCD(b, a % b);
		}
		
		
		class pair{
			int f,t; long len;
			public pair(int a, int b, long c) {
				f = a;t=b;len=c;
			}
		}
  
		public ArrayList<edge>[] getG(int n){
			ArrayList<edge>[] g = new ArrayList[n];
			for(int i=0;i<n;i++) g[i] = new ArrayList<edge>();
			return g;
		}

		public class edge implements Comparable<edge> {
			int f, t;
			int len;
			int id;

			public edge(int a, int b, int c, int d) {
				f = a;
				t = b;
				len = c;
				id = d;
			}

			@Override
			public int compareTo(Main.Task.edge o) {
				if (this.len - o.len < 0)
					return -1;
				else if (this.len == o.len)
					return 0;
				else
					return 1;
			}
		}

		
		List<List<Integer>> convert(int arr[][]) {
			int n = arr.length;
			List<List<Integer>> ret = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				ArrayList<Integer> tmp = new ArrayList<Integer>();
				for (int j = 0; j < arr[i].length; j++)
					tmp.add(arr[i][j]);
				ret.add(tmp);
			}
			return ret;
		}
	}

	static class ArrayUtils {
		static final long seed = System.nanoTime();
		static final Random rand = new Random(seed);

		public static void sort(int[] a) {
			shuffle(a);
			Arrays.sort(a);
		}

		public static void shuffle(int[] a) {
			for (int i = 0; i < a.length; i++) {
				int j = rand.nextInt(i + 1);
				int t = a[i];
				a[i] = a[j];
				a[j] = t;
			}
		}

		public static void sort(long[] a) {
			shuffle(a);
			Arrays.sort(a);
		}

		public static void shuffle(long[] a) {
			for (int i = 0; i < a.length; i++) {
				int j = rand.nextInt(i + 1);
				long t = a[i];
				a[i] = a[j];
				a[j] = t;
			}
		}
	}

	static class BIT {
		long arr[];
		int n;

		public BIT(int a) {
			n = a;
			arr = new long[n];
		}

		long sum(int p) {
			long s = 0;
			while (p > 0) {
				s += arr[p];
				p -= p & (-p);
			}
			return s;
		}

		void add(int p, long v) {
			while (p < n) {
				arr[p] += v;
				p += p & (-p);
			}
		}
	}

	static class DSU {
		int[] arr;
		int[] sz;

		public DSU(int n) {
			arr = new int[n];
			sz = new int[n];
			for (int i = 0; i < n; i++)
				arr[i] = i;
			Arrays.fill(sz, 1);
		}

		public int find(int a) {
			if (arr[a] != a)
				arr[a] = find(arr[a]);
			return arr[a];
		}

		public void union(int a, int b) {
			int x = find(a);
			int y = find(b);
			if (x == y)
				return;
			arr[y] = x;
			sz[x] += sz[y];
		}

		public int size(int x) {
			return sz[find(x)];
		}
	}

	private static class InputReader {
		private InputStream stream;
		private byte[] buf = new byte[1024];
		private int zcurChar;
		private int znumChars;
		private SpaceCharFilter filter;

		public InputReader(InputStream stream) {
			this.stream = stream;
		}

		public int read() {
			if (znumChars == -1)
				throw new InputMismatchException();
			if (zcurChar >= znumChars) {
				zcurChar = 0;
				try {
					znumChars = stream.read(buf);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (znumChars <= 0)
					return -1;
			}
			return buf[zcurChar++];
		}

		public int nextInt() {
			int c = read();
			while (isSpaceChar(c))
				c = read();
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			int res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = read();
			} while (!isSpaceChar(c));
			return res * sgn;
		}

		public String nextString() {
			int c = read();
			while (isSpaceChar(c))
				c = read();
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = read();
			} while (!isSpaceChar(c));
			return res.toString();
		}

		public double nextDouble() {
			int c = read();
			while (isSpaceChar(c))
				c = read();
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			double res = 0;
			while (!isSpaceChar(c) && c != '.') {
				if (c == 'e' || c == 'E')
					return res * Math.pow(10, nextInt());
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = read();
			}
			if (c == '.') {
				c = read();
				double m = 1;
				while (!isSpaceChar(c)) {
					if (c == 'e' || c == 'E')
						return res * Math.pow(10, nextInt());
					if (c < '0' || c > '9')
						throw new InputMismatchException();
					m /= 10;
					res += (c - '0') * m;
					c = read();
				}
			}
			return res * sgn;
		}

		public long nextLong() {
			int c = read();
			while (isSpaceChar(c))
				c = read();
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			long res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = read();
			} while (!isSpaceChar(c));
			return res * sgn;
		}

		public boolean isSpaceChar(int c) {
			if (filter != null)
				return filter.isSpaceChar(c);
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}

		public String next() {
			return nextString();
		}

		public interface SpaceCharFilter {
			public boolean isSpaceChar(int ch);
		}

		public int[] readIntArray(int n) {
			int[] ret = new int[n];
			for (int i = 0; i < n; i++) {
				ret[i] = nextInt();
			}
			return ret;
		}
	}

	static class Dumper {
		static void print_int_arr(int[] arr) {
			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
			System.out.println("---------------------");
		}

		static void print_char_arr(char[] arr) {
			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
			System.out.println("---------------------");
		}

		static void print_double_arr(double[] arr) {
			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
			System.out.println("---------------------");
		}

		static void print_2d_arr(int[][] arr, int x, int y) {
			for (int i = 0; i < x; i++) {
				for (int j = 0; j < y; j++) {
					System.out.print(arr[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println();
			System.out.println("---------------------");
		}

		static void print_2d_arr(boolean[][] arr, int x, int y) {
			for (int i = 0; i < x; i++) {
				for (int j = 0; j < y; j++) {
					System.out.print(arr[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println();
			System.out.println("---------------------");
		}

		static void print(Object o) {
			System.out.println(o.toString());
		}

		static void getc() {
			System.out.println("here");
		}
	}
}