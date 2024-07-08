import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

class Main {
	
	public static void main(String[] args) {
		new Main().run();
	}
	
	class DSU {
		int[] parent;
		
		public DSU(int n) {
			parent=new int[n];
			Arrays.fill(parent, -1);
		}
		
		int root(int x) {
			return parent[x]<0?x:(parent[x]=root(parent[x]));
		}
		
		void union(int x, int y) {
			x=root(x);
			y=root(y);
			if (x==y) return;
			parent[x]=y;
		}
		
		boolean equiv(int x, int y) {
			return root(x) == root(y);
		}
	}
	
	final long mod=998244353;
	
	void solve() {

		FastScanner sc=new FastScanner();
		PrintWriter pw=new PrintWriter(System.out);
		int N=sc.nextInt();
		int K=sc.nextInt();
		int[][] x=new int[N][2];
		for (int i=0;i<N;++i) {
			x[i][0]=sc.nextInt();
		}
		for (int i=0;i<N;++i) {
			x[i][1]=sc.nextInt();
		}
		Arrays.sort(x, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[1], o2[1]);
			}
		});		
		long[][] dp=new long[K+2][N+2];
		dp[0][N+1]=1;
		for (int i=0;i<N;++i) {
			long[][] ndp=new long[K+2][N+2];
			for (int k=0;k<=K;++k) {
				for (int min=1;min<=N+1;++min) {
					ndp[k][Math.min(x[i][0], min)]+=dp[k][min];
					ndp[k][Math.min(x[i][0], min)]%=mod;
					if (min > x[i][0]) {
						ndp[k+1][min]+=dp[k][min];
						ndp[k+1][min]%=mod;
					}
				}
			}
			dp=ndp;
			
		}
		long ans=0;
		for (int min=1;min<=N;++min) {
			ans=(ans+dp[K][min])%mod;
		}
		pw.println(ans);
		pw.close();
	}
	
	void run() {
		solve();
	}
	
	static void tr(Object...objects) {
		System.out.println(Arrays.deepToString(objects));
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
    public boolean hasNext() { while(hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++; return hasNextByte();}
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
        long nl = nextLong();
        if (nl < Integer.MIN_VALUE || nl > Integer.MAX_VALUE) throw new NumberFormatException();
        return (int) nl;
    }
    public double nextDouble() { return Double.parseDouble(next());}
}