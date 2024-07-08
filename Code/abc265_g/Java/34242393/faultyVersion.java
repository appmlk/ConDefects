import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.logging.Logger;



public class Main {
	static final long MOD1=1000000007;
	static final long MOD=998244353;
	static final int NTT_MOD1 = 998244353;
	static final int NTT_MOD2 = 1053818881;
	static final int NTT_MOD3 = 1004535809;
	static long MAX = 1000000000000000000l;
	public static void main(String[] args){
		PrintWriter out = new PrintWriter(System.out);
		InputReader sc=new InputReader(System.in);
		int n = sc.nextInt();
		int q = sc.nextInt();
		int[] a = sc.nextIntArray(n);
		int[] FI = {0, 1, 2};
		F I = new F(FI);
		long[] dat = {0, 0, 0};
		long[] pair = new long[9];
		S E = new S(dat, pair, 0);
		LazySegTree<S, F> lazySegTree=new LazySegTree<S, F>(n, S::op, E, F::map, F::composite, I);
		for (int i = 0; i < n; i++) {
			long[] d = new long[3];
			d[a[i]] = 1;
			lazySegTree.set(i, new S(d, pair, 0));
		}
		for (int j = 0; j < q; j++) {
			int t = sc.nextInt();
			int l = sc.nextInt()-1;
			int r = sc.nextInt();
			if (t==1) {
				out.println(lazySegTree.prod(l, r).sum);
			}else {
				int[] STU = sc.nextIntArray(3);
				lazySegTree.apply(l, r, new F(STU));
			}
		}
		out.flush();
   	}
	static class S{
		long[] dat;
		long[] pair;
		long sum;
		public S(long[] dat, long[] pair, long sum){
			this.dat = Arrays.copyOf(dat, 3);
			this.sum = sum;
			this.pair = Arrays.copyOf(pair, 9);
		}
		static S op(S a,S b){
			long[] dat2 = new long[3];
			for (int i = 0; i < dat2.length; i++) {
				dat2[i] = a.dat[i] + b.dat[i];
			}
			long[] pair = new long[9];
			long sum = 0;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					pair[3*i + j] = a.pair[3*i + j] + b.pair[3*i + j] + (a.dat[i] * b.dat[j]);
					if(i > j) sum += pair[3*i + j];
				}
			}
			return new S(dat2, pair, a.sum+b.sum+(a.dat[2]+a.dat[1])*b.dat[0] + a.dat[2]*b.dat[1]);
			//return new S(dat2, pair, sum);
		}
	}
	static class F{
		int[] STU;
		public F(int[] STU) {
			this.STU = Arrays.copyOf(STU, 3);
 		}
		static S map(F f,S s) {
			long[] n_dat = new long[3];
			for (int i = 0; i < n_dat.length; i++) {
				n_dat[i] += s.dat[f.STU[i]];
			}
			long[] pair = new long[9];
			long sum = 0;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					pair[3*f.STU[i] + f.STU[j]] += s.pair[3*i + j];
				}
			}
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if(i > j) sum += pair[3*i + j];
				}
			}
			return new S(n_dat, pair, sum);
		}
		static F composite(F f,F g){
			int[] STU = new int[3];
			for (int i = 0; i < 3; i++) {
				STU[i] = f.STU[g.STU[i]];
			}
			return new F(STU);
		}
	}
	static class LazySegTree<S, F> {
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
	    public LazySegTree(int n, java.util.function.BinaryOperator<S> op, S e, java.util.function.BiFunction<F, S, S> mapping, java.util.function.BinaryOperator<F> composition, F id) {
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
 
	    public LazySegTree(S[] dat, java.util.function.BinaryOperator<S> op, S e, java.util.function.BiFunction<F, S, S> mapping, java.util.function.BinaryOperator<F> composition, F id) {
	        this(dat.length, op, e, mapping, composition, id);
	        build(dat);
	    }
 
	    private void build(S[] dat) {
	        int l = dat.length;
	        System.arraycopy(dat, 0, Dat, N, l);
	        for (int i = N - 1; i > 0; i--) {
	            Dat[i] = Op.apply(Dat[i << 1 | 0], Dat[i << 1 | 1]);
	        }
	    }
 
	    private void push(int k) {
	        if (Laz[k] == Id) return;
	        int lk = k << 1 | 0, rk = k << 1 | 1;
	        Dat[lk] = Mapping.apply(Laz[k], Dat[lk]);
	        Dat[rk] = Mapping.apply(Laz[k], Dat[rk]);
	        if (lk < N) Laz[lk] = Composition.apply(Laz[k], Laz[lk]);
	        if (rk < N) Laz[rk] = Composition.apply(Laz[k], Laz[rk]);
	        Laz[k] = Id;
	    }
 
	    private void pushTo(int k) {
	        for (int i = Log; i > 0; i--) push(k >> i);
	    }
 
	    private void pushTo(int lk, int rk) {
	        for (int i = Log; i > 0; i--) {
	            if (((lk >> i) << i) != lk) push(lk >> i);
	            if (((rk >> i) << i) != rk) push(rk >> i);
	        }
	    }
 
	    private void updateFrom(int k) {
	        k >>= 1;
	        while (k > 0) {
	            Dat[k] = Op.apply(Dat[k << 1 | 0], Dat[k << 1 | 1]);
	            k >>= 1;
	        }
	    }
 
	    private void updateFrom(int lk, int rk) {
	        for (int i = 1; i <= Log; i++) {
	            if (((lk >> i) << i) != lk) {
	                int lki = lk >> i;
	                Dat[lki] = Op.apply(Dat[lki << 1 | 0], Dat[lki << 1 | 1]);
	            }
	            if (((rk >> i) << i) != rk) {
	                int rki = (rk - 1) >> i;
	                Dat[rki] = Op.apply(Dat[rki << 1 | 0], Dat[rki << 1 | 1]);
	            }
	        }
	    }
 
	    public void set(int p, S x) {
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
	        if (l > r) {
	            throw new IllegalArgumentException(
	                String.format("Invalid range: [%d, %d)", l, r)
	            );
	        }
	        inclusiveRangeCheck(l);
	        inclusiveRangeCheck(r);
	        if (l == r) return E;
	        l += N; r += N;
	        pushTo(l, r);
	        S sumLeft = E, sumRight = E;
	        while (l < r) {
	            if ((l & 1) == 1) sumLeft = Op.apply(sumLeft, Dat[l++]);
	            if ((r & 1) == 1) sumRight = Op.apply(Dat[--r], sumRight);
	            l >>= 1; r >>= 1;
	        }
	        return Op.apply(sumLeft, sumRight);
	    }
 
	    public S allProd() {
	        return Dat[1];
	    }
 
	    public void apply(int p, F f) {
	        exclusiveRangeCheck(p);
	        p += N;
	        pushTo(p);
	        Dat[p] = Mapping.apply(f, Dat[p]);
	        updateFrom(p);
	    }
 
	    public void apply(int l, int r, F f) {
	        if (l > r) {
	            throw new IllegalArgumentException(
	                String.format("Invalid range: [%d, %d)", l, r)
	            );
	        }
	        inclusiveRangeCheck(l);
	        inclusiveRangeCheck(r);
	        if (l == r) return;
	        l += N; r += N;
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
	            l2 >>= 1; r2 >>= 1;
	        }
	        updateFrom(l, r);
	    }
 
	    public int maxRight(int l, java.util.function.Predicate<S> g) {
	        inclusiveRangeCheck(l);
	        if (!g.test(E)) {
	            throw new IllegalArgumentException("Identity element must satisfy the condition.");
	        }
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
 
	    public int minLeft(int r, java.util.function.Predicate<S> g) {
	        inclusiveRangeCheck(r);
	        if (!g.test(E)) {
	            throw new IllegalArgumentException("Identity element must satisfy the condition.");
	        }
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
 
	    private void exclusiveRangeCheck(int p) {
	        if (p < 0 || p >= MAX) {
	            throw new IndexOutOfBoundsException(
	                String.format("Index %d is not in [%d, %d).", p, 0, MAX)
	            );
	        }
	    }
 
	    private void inclusiveRangeCheck(int p) {
	        if (p < 0 || p > MAX) {
	            throw new IndexOutOfBoundsException(
	                String.format("Index %d is not in [%d, %d].", p, 0, MAX)
	            );
	        }
	    }
 
	    // **************** DEBUG **************** //
 
	    private int indent = 6;
 
	    public void setIndent(int newIndent) {
	        this.indent = newIndent;
	    }
 
	    @Override
	    public String toString() {
	        return toString(1, 0);
	    }
 
	    private String toString(int k, int sp) {
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
	        StringBuilder sb = new StringBuilder();
	        while (n --> 0) sb.append(' ');
	        return sb.toString();
	    }
	}
	static class InputReader { 
		private InputStream in;
		private byte[] buffer = new byte[1024];
		private int curbuf;
		private int lenbuf;
		public InputReader(InputStream in) {
			this.in = in;
			this.curbuf = this.lenbuf = 0;
		}
		public boolean hasNextByte() {
			if (curbuf >= lenbuf) {
				curbuf = 0;
				try {
					lenbuf = in.read(buffer);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (lenbuf <= 0)
					return false;
			}
			return true;
		}
 
		private int readByte() {
			if (hasNextByte())
				return buffer[curbuf++];
			else
				return -1;
		}
 
		private boolean isSpaceChar(int c) {
			return !(c >= 33 && c <= 126);
		}
 
		private void skip() {
			while (hasNextByte() && isSpaceChar(buffer[curbuf]))
				curbuf++;
		}
 
		public boolean hasNext() {
			skip();
			return hasNextByte();
		}
 
		public String next() {
			if (!hasNext())
				throw new NoSuchElementException();
			StringBuilder sb = new StringBuilder();
			int b = readByte();
			while (!isSpaceChar(b)) {
				sb.appendCodePoint(b);
				b = readByte();
			}
			return sb.toString();
		}
 
		public int nextInt() {
			if (!hasNext())
				throw new NoSuchElementException();
			int c = readByte();
			while (isSpaceChar(c))
				c = readByte();
			boolean minus = false;
			if (c == '-') {
				minus = true;
				c = readByte();
			}
			int res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res = res * 10 + c - '0';
				c = readByte();
			} while (!isSpaceChar(c));
			return (minus) ? -res : res;
		}
 
		public long nextLong() {
			if (!hasNext())
				throw new NoSuchElementException();
			int c = readByte();
			while (isSpaceChar(c))
				c = readByte();
			boolean minus = false;
			if (c == '-') {
				minus = true;
				c = readByte();
			}
			long res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res = res * 10 + c - '0';
				c = readByte();
			} while (!isSpaceChar(c));
			return (minus) ? -res : res;
		}
 
		public double nextDouble() {
			return Double.parseDouble(next());
		}
 
		public int[] nextIntArray(int n) {
			int[] a = new int[n];
			for (int i = 0; i < n; i++)
				a[i] = nextInt();
			return a;
		}
		public double[] nextDoubleArray(int n) {
			double[] a = new double[n];
			for (int i = 0; i < n; i++)
				a[i] = nextDouble();
			return a;
		}
		public long[] nextLongArray(int n) {
			long[] a = new long[n];
			for (int i = 0; i < n; i++)
				a[i] = nextLong();
			return a;
		}
 
		public char[][] nextCharMap(int n, int m) {
			char[][] map = new char[n][m];
			for (int i = 0; i < n; i++)
				map[i] = next().toCharArray();
			return map;
		}
	}
}