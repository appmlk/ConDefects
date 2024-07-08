import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);) {
			int n = Integer.parseInt(sc.next());
			int m = Integer.parseInt(sc.next());
			final long mod = 998244353;
			
			SegTreeLazy segTreeLazy = new SegTreeLazy(n);
			for(int i = 0; i < n; i++) segTreeLazy.set(i, Long.parseLong(sc.next()));
			segTreeLazy.build();
			
			for(int i = 0; i < m; i++) {
				int l = Integer.parseInt(sc.next());
				int r = Integer.parseInt(sc.next());
				long x = Long.parseLong(sc.next()); 
				l--;
				x %= mod;
				
				long p = r - l;
				long invp = inv(p, mod);
				long q = 1 - invp;
				
				q = (q + mod) % mod;
				x = (x * invp) % mod;
				segTreeLazy.update(l, r, new long[] {q, x});
			}
			
			for(int i = 0; i < 4 * n; i++) segTreeLazy.eval(i);
			
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < n; i++) sb.append(segTreeLazy.get(i) + " ");
			System.out.println(sb.toString());
			
		}
	}
	
	static long inv(long x, long mod) {
		long res = 1;
		long y = mod - 2;
		
		while(y > 0) {
			if((y & 1) != 0) {
				res *= x;
				res %= mod;
			}
			y = y >> 1;
			x *= x;
			x %= mod;
		}
		
		return res;
	}
	
}

class SegTreeLazy{
	int n;
	final long ex = 1;
	final long[] em = {1, 0};
	long[] dat;
	long[][] lazy;
	final long mod = 998244353;
	
	private long fx(long a, long b) {
		return Math.min(a, b);
	}
	
	private long fa(long x, long[] f) {
		return (f[0] * x + f[1]) % mod;
	}
	
	private long[] fm(long[] f, long[] g) {
		return new long[] {(f[0] * g[0]) % mod, (g[0] * f[1] + g[1]) % mod};
	}
	
	public SegTreeLazy(int n) {
		int x = 1;
		while(n > x) x *= 2;
		this.n = x;
		dat = new long[4 * n];
		lazy = new long[4 * n][2];
		
		for(int i = 0; i < 4 * n; i++) {
			lazy[i][0] = 1;
			lazy[i][1] = 0;
		}
	}
	
	void set(int i, long x) {
		dat[i + n - 1] = x;
	}
	
	long get(int i) {
		eval(i + n - 1);
		return dat[i + n - 1];
	}
	
	void build() {
		for (int k = n - 2; k >= 0; k--) dat[k] = fx(dat[2 * k + 1], dat[2 * k + 2]);
	}
	
	void eval(int k) {
		if(lazy[k][0] == 1 && lazy[k][1] == 0) return;
		if(k < n - 1) {
			lazy[2 * k + 1] = fm(lazy[2 * k + 1], lazy[k]);
			lazy[2 * k + 2] = fm(lazy[2 * k + 2], lazy[k]);
		}
		
		dat[k] = fa(dat[k], lazy[k]);
		lazy[k][0] = 1;
		lazy[k][1] = 0;
		//lazy[k] = em;
	}
	
	void update(int a, int b, long[] x) { 
		update(a, b, x, 0, 0, n);
	}
	
	void update(int a, int b, long[] x, int k, int l, int r) {
		eval(k);
		if(a <= l && r <= b) {
			lazy[k] = fm(lazy[k], x);
			eval(k);
		} else if(a < r && l < b){
			update(a, b, x, 2 * k + 1, l, (l + r) / 2);
			update(a, b, x, 2 * k + 2, (l + r) / 2, r);
			dat[k] = fx(dat[k * 2 + 1], dat[k * 2 + 2]);
		}
		
	}
	
	long query(int a, int b) {
		return query_sub(a, b, 0, 0, n);
	}
	
	long query_sub(int a, int b, int k, int l, int r) {
		if(r <= a || b <= l) return ex;
		else if(a <= l && r <= b) return dat[k];
		else {
			long vl = query_sub(a, b, 2 * k + 1, l, (l + r) / 2);
			long vr = query_sub(a, b, 2 * k + 2, (l + r) / 2, r);
			return fx(vl, vr);
		}
	}
	
	void show() {
		int x = 0;
		int y = 1;
		
		while(x < n) {
			for(int i = x; i < y; i++) {
				System.out.print(dat[i] + " ");
			}
			System.out.println();
			x = y;
			y = 2 * y + 1;
		}
	}
	
	void showlazy() {
		int x = 0;
		int y = 1;
		
		while(x < n) {
			for(int i = x; i < y; i++) {
				System.out.print(lazy[i][0] + " : " + lazy[i][1] + "   ");
			}
			System.out.println();
			x = y;
			y = 2 * y + 1;
		}
	}
}