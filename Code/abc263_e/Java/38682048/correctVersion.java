import java.io.*;
import java.util.*;
import java.util.stream.*;

class Main {
	static final int MOD = 998244353;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int N;
	int[] A;
	long[] es;
	
	static int i(String s) { return Integer.parseInt(s); }
	void calc() throws Exception {
		N = i(br.readLine());
		String[] as = br.readLine().split(" ");
		A = Arrays.stream(as).mapToInt(a -> i(a)).toArray();

		es = new long[N+1];
		for (int i = N-2; i >= 0; i--) {
			long p = inv(A[i] + 1);
			long e = inv( (1-p+MOD) %MOD );
			e = e * p %MOD;
			e = e * ((es[i+1] - es[i+A[i]+1] + MOD) %MOD + A[i] + 1) %MOD;
			es[i] = (es[i+1] + e) %MOD;
		}
		System.out.println( (es[0] - es[1] + MOD) %MOD);
	}
	
	public static void main(String[] args) throws Exception {
		new Main().calc();
	}

	static final long inv(long a) {
		long b = MOD;
		long aa = a, bb = b;
		long x0 = 1, x1 = 0;
		long y0 = 0, y1 = 1;
	
		while (b != 0) {
			long q = a / b;
			long r = a % b;
			long x2 = x0 - q * x1;
			long y2 = y0 - q * y1;
	
			a = b; b = r;
			x0 = x1; x1 = x2;
			y0 = y1; y1 = y2;
		}
		if (bb > 0 && x0 < 0) {
			long n = -x0/bb;
			if (-x0%bb != 0) n++;
			x0 += n*bb; y0 -= n*aa;
		}
		return x0;
	}
}
