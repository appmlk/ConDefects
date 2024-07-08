import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class Main implements Runnable {
	public static void main(String[] args) {
		new Thread(null, new Main(), "", Runtime.getRuntime().maxMemory()).start();
	}
	
	public void run() {
		FastScanner sc = new FastScanner();
		PrintWriter pw=new PrintWriter(System.out);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		Map<Integer, Integer> mset = new HashMap<>();
		int base = 0;
		for (int i = 0; i < N; ++i) {
			int a = sc.nextInt();
			int b=  sc.nextInt();
			base += a;
			int add = b - a;
			if (add == 0) continue;
			mset.merge(add, 1, Integer::sum);
		}
		
		int[] dp = new int[M + 1];
		int INF = Integer.MAX_VALUE / 3;
		Arrays.fill(dp, INF);
		dp[base] = 0;
		for (var es : mset.entrySet()) {
			int add = es.getKey();
			int num = es.getValue();
			if (add > 0) {
				solve(dp, add, num, M);
			} else {
				reverse(dp);
				add = -add;
				solve(dp, add, num, M);
				reverse(dp);
			}
		}
		
		for (int i = 0; i <= M; ++i) {
			pw.println(dp[i] == INF ? -1 : dp[i]);
		}
		
		pw.close();
	
	}
	
	void reverse(int[] a) {
		int s = 0;
		int t = a.length - 1;
		while (s < t) {
			a[s]^=a[t];
			a[t]^=a[s];
			a[s]^=a[t];
			++s;--t;
		}
	}
	
	
	
	void solve(int[] dp, int add, int num, int M) {
		for (int src = 0; src < add; ++src) {
			Dq val = new Dq(M / add + 1);
			Dq off = new Dq(M / add + 1);
			for (int i = src, c = 0; i <= M; i += add, ++c) {
				if (!val.isEmpty() && c - off.first() > num) {
					val.popFirst();
					off.popFirst();
				}
				int nv = dp[i];
				if (!val.isEmpty()) {
					nv = Math.min(dp[i], val.last() + (c - off.last()));
				}
				while (!val.isEmpty()  && dp[i] - c <= val.last() - off.last()) {
					val.popLast();
					off.popLast();
				}
				val.addLast(dp[i]);
				off.addLast(c);
				dp[i] = nv;
			}
		}
	}
	
	class Dq {
		int[] a;
		int head = 0;;
		int tail = -1;
		
		public Dq(int n) {
			a = new int[n];
		}
		
		int first()	{
			return a[head];
		}
		
		int last() {
			return a[tail];
		}
		
		void popFirst() {
			++head;
		}
		
		void popLast() {
			--tail;
		}
		
		boolean isEmpty() {
			return !(tail - head >= 0);
		}
		
		void addLast(int v) {
			a[++tail] = v;
		}
		
	}
	

	void tr(Object... objects) {
		System.err.println(Arrays.deepToString(objects));
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

	private void skipUnprintable() {
		while (hasNextByte() && !isPrintableChar(buffer[ptr]))
			ptr++;
	}

	public boolean hasNext() {
		skipUnprintable();
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
		return (int) nextLong();
	}
}