import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class Main implements Runnable { //Runnableを実装する
    public static void main(String[] args) {
        new Thread(null, new Main(), "", Runtime.getRuntime().maxMemory()).start(); //16MBスタックを確保して実行
    }	
	final long mod = 998244353;
	
	void dfs(int v, int[] parent, int[] depth, ArrayList<Integer>[] g) {
		for (int u : g[v]) {
			if (u == parent[v]) continue;
			parent[u] = v;
			depth[u] = depth[v] + 1;
			dfs(u, parent, depth, g);
		}
	}
	
	long dfs2(int v, int p, int d, int diameter, ArrayList<Integer>[] g) {
		if (2 * d == diameter) return 1;
		long ret = 0;
		for (int u : g[v]) {
			if (u == p) continue;
			ret += dfs2(u, v, d+1, diameter, g);
		}
		return ret;
	}
	
	public void run() {
		FastScanner sc=new FastScanner();
		PrintWriter pw=new PrintWriter(System.out);
        
		
		int N=sc.nextInt();
		ArrayList<Integer>[] g=new ArrayList[N+N-1];
		for (int i=0;i<g.length;++i) g[i]=new ArrayList<>();
		for (int i=0;i<N-1;++i) {
			int u=sc.nextInt()-1;
			int v=sc.nextInt()-1;
			int m=N+i;
			g[u].add(m);
			g[m].add(u);
			g[m].add(v);
			g[v].add(m);
		}
		
		N+= N-1;
		
		int leaf = 0;
		for (int i=0;i<N;++i) if (g[i].size() == 1) ++leaf;
		if (leaf == 2) {
			System.out.println(1);
			return;
		}
		
		int[] depth = new int[N];
		int[] parent = new int[N];
		Arrays.fill(parent, -1);
		dfs(0, parent, depth, g);
		int nxt = 0;
		for (int i=0;i<N;++i) if (depth[i] > depth[nxt]) nxt = i;
		Arrays.fill(depth, 0);
		Arrays.fill(parent, -1);
		dfs(nxt, parent, depth, g);
		
		int nxt2 = 0;
		for (int i=0;i<N;++i) if (depth[i] > depth[nxt2]) nxt2 = i;
		int diameter = depth[nxt2];
		while (2 * depth[nxt2] != diameter) {
			nxt2 = parent[nxt2];
		}
		
		long ans=1;
		long sub=1;
		for (int u : g[nxt2]) {
			long c = dfs2(u, nxt2, 1, diameter, g);
			ans *= 1 + c;
			sub += c;
			ans %= mod;
			sub %= mod;
		}
		
		ans = (ans + mod - sub) % mod;
		System.out.println(ans);
		pw.close();
	}
	
	
    void tr(Object...o) {
    	System.out.println(Arrays.deepToString(o));
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