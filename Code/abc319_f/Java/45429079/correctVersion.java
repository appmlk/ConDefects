import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main implements Runnable {
	
	public static void main(String[] args) {
		new Thread(null, new Main(), "", Runtime.getRuntime().maxMemory()).start(); //16MBスタックを確保して実行
	}
	
	long gcd(long a, long b) {
		if (a == 0) return b;
		return gcd(b % a, a);
	}
	
	long lcm(long a, long b) {
		return a * b / gcd(a, b);
	}
	
	long extend(boolean[] vis, long height) {
		boolean[] nvis = Arrays.copyOf(vis, vis.length);
		PriorityQueue<State> pq = new PriorityQueue<>();
		nvis[0] = true;
		for (int i = 0; i < N; ++i) {
			if (nvis[i]) for (int u : g[i]) {
				if (!nvis[u] && type[u] == addType) {
					pq.add(new State(heights[u], gains[u], u));
				}
			}
		}
		while (!pq.isEmpty()) {
			State state = pq.poll();
			if (state.height <= height) {
				height = Math.min(height + state.gain, INF);
				nvis[state.id] = true;
				for (int next : g[state.id]) if (!nvis[next] && type[next] == addType){
					pq.add(new State(heights[next], gains[next], next));
				}	
			}
		}
		return height;
	}
	
	int N;
	ArrayList<Integer>[] g;
	long[] heights;
	int[] type;
	long[] gains;
	ArrayList<Integer> list;
	int[] scaleId;
	long INF = (long) 1234567890;
	final int addType = 1;
	final int scaleType = 2;
	
	class State implements Comparable<State> {
		long height;
		long gain;
		int id;
		public State(long height, long gain, int id) {
			this.height = height;
			this.gain = gain;
			this.id = id;
		}
		public int compareTo(State o) {
			return Long.compare(height, o.height);
		};
	}
	
	public void run() {
		FastScanner sc=new FastScanner();
		PrintWriter pw=new PrintWriter(System.out);
		
		N = sc.nextInt();
		g = new ArrayList[N];
		for (int i = 0; i < N; ++i) {
			g[i] = new ArrayList<>();
		}
		heights = new long[N];
		type = new int[N];
		gains = new long[N];
		list = new ArrayList<>();
		for (int i = 1; i < N; ++i) {
			int p = sc.nextInt() - 1;
			g[i].add(p);
			g[p].add(i);
			type[i] = sc.nextInt();
			heights[i] = sc.nextInt();
			gains[i] = sc.nextInt();
			if (type[i] == scaleType) list.add(i);
		}
		scaleId = new int[N];
		Arrays.fill(scaleId, -1);
		for (int i = 0; i < list.size(); ++i) scaleId[list.get(i)] = i;
		long[] dp = new long[1 << list.size()];
		Arrays.fill(dp, -1);
		dp[0] = extend(new boolean[N], 1);
		for (int set = 0; set < 1 << list.size(); ++set) {
			if (dp[set] == -1) continue;
			Queue<Integer> que = new ArrayDeque<>();
			que.add(0);
			boolean[] vis = new boolean[N];
			ArrayList<Integer> next = new ArrayList<>();
			vis[0] = true;
			while (!que.isEmpty()) {
				int v = que.poll();
				for (int u : g[v]) {
					if (vis[u]) continue;
					if (type[u] == addType && heights[u] > dp[set]) continue;
					if (type[u] == scaleType && (set >> scaleId[u]) % 2 == 0) continue;
					que.add(u);
					vis[u] = true;
				}
			}
			for (int i = 0; i < N; ++i) {
				if (vis[i]) {
					for (int u : g[i]) {
						if (type[u] == scaleType && (set >> scaleId[u]) % 2 == 0) next.add(u);
					}
				}
			}
			for (int add : next) {
				int nset = set | (1 << scaleId[add]);
				vis[add] = true;
				long height = dp[set];
				height = Math.min(height * gains[add], INF);
				dp[nset] = Math.max(dp[nset], extend(vis, height));
				vis[add] = false;
			}
		}
		long need = 0;
		for (int i = 0; i < N; ++i) if (type[i] == addType) need = Math.max(need, heights[i]);
		pw.println(Arrays.stream(dp).max().getAsLong() >= need ? "Yes" : "No");
		pw.close();
	
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