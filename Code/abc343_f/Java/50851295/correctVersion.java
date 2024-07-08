import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.TreeMap;

public class Main{
	
	public static void main(String[] args) {
		new Main().run();
	}
	
	long powmod(long a, long n, long mod) {
		if (n == 0) return 1;
		return powmod(a*a%mod, n/2, mod) * (n % 2 == 1 ? a : 1) % mod;
	}
	
	int INF = Integer.MAX_VALUE / 3;
	
	class V {
		int fv;
		int sv;
		int fn;
		int sn;
		
		public V() {
			fv = -INF+1;
			sv = -INF;
			fn = 0;
			sn = 0;
		}
		
		public V(int val) {
			this();
			fv = val;
			fn = 1;
		}
		
		V copy() {
			V ret = new V();
			ret.fv = fv;
			ret.sv = sv;
			ret.fn = fn;
			ret.sn = sn;
			return ret;
		}
		
		void add(int val, int num) {
			if (val > fv) {
				sv = fv;
				sn = fn;
				fv = val;
				fn = num;
			} else if (val == fv) {
				fn += num;
			} else if (fv > val && val > sv) {
				sv = val;
				sn = num;
			} else if (val == sv) {
				sn += num;
			}
		}
	}
	
	class Tree {
		int n;
		V[] v;
		
		public Tree(int n_) {
			n = 2 * Integer.highestOneBit(n_);
			v = new V[2 * n];
			for (int i = 0; i < 2 * n; ++i) {
				v[i] = new V();
			}
		}
		
		void set(int k, int val) {
			k = id(k, k + 1);
			v[k] = new V(val);
			while (k != id(0, n)) {
				k /= 2;
				v[k] = merge(v[2 * k], v[2 * k + 1]);
			}
		}
		
		V query(int a, int b) {
			if (b - a <= 0) return null;
			int ma = a + Integer.lowestOneBit(a);
			int mb = b - Integer.lowestOneBit(b);
			if (a < ma && ma <= b) {
				return merge(v[id(a, ma)], query(ma, b));
			} else {
				return merge(query(a, mb), v[id(mb, b)]);
			}
		}
		
		int id(int a, int b) {
			int w = Integer.lowestOneBit(a ^ b);
			return a / w + n / w;
		}
		
		V merge(V a, V b) {
			if (a == null) return b.copy();
			if (b == null) return a.copy();
			V c = new V();
			c.add(a.fv, a.fn);
			c.add(a.sv, a.sn);
			c.add(b.fv, b.fn);
			c.add(b.sv, b.sn);
			return c;
		}
	}
	
	
	void run() {
		FastScanner sc=new FastScanner();
		PrintWriter pw=new PrintWriter(System.out);
		int N = sc.nextInt();
		int Q = sc.nextInt();
		int[] A = new int[N];
		Tree tree = new Tree(N);
		for (int i = 0; i < N; ++i) {
			A[i] = sc.nextInt();
			tree.set(i, A[i]);
		}
		for (int i = 0; i < Q; ++i) {
			int type = sc.nextInt();
			if (type == 1) {
				int p = sc.nextInt() - 1;
				int x = sc.nextInt();
				tree.set(p, x);
			} else {
				int l = sc.nextInt() - 1;
				int r = sc.nextInt() - 1;
				var v = tree.query(l, r + 1);
				System.out.println(v.sn);
			}
		}
	}	
	
	void tr(Object...objects) {System.out.println(Arrays.deepToString(objects));}
}




class FastScanner {
    private final InputStream in = System.in;
    private final byte[] buffer = new byte[1024];
    private int ptr = 0;
    private int buflen = 0;
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
    private void skipUnprintable() { while(hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++;}
    public boolean hasNext() { skipUnprintable(); return hasNextByte();}
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
    	return (int)nextLong();
    }
}