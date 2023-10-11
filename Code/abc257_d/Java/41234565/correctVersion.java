import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
	static FastScaner in = new FastScaner();
	static FastWriter out = new FastWriter();

	int N = in.nextInt();
	long[][] P = in.nextLongMatrix(1, N, 0, 2);
	
	void solve() {
		long s = findLowestBound(0,4000000000L,(bottomS)-> {
			var scc = new StronglyConnectedComponent( getPaths(bottomS), N);
			boolean[] m = new boolean[N+1];
			var start = scc.search().get(0).get(0);
			mark( scc.graph, start, m);
			for(int i=1; i<=N; i++) {
				if(!m[i]) {
					return false;
				}
			}
			return true;
		});
		out.println(s);
	}
	
	void mark(Graph g, int v, boolean[] m) {
		if(m[v]) {
			return;
		}
		m[v] = true;
		for(var n : g.getNext(v)) {
			mark(g,n,m);
		}
	}

	List<Path> getPaths(long bottomS) {
		List<Path> result = new ArrayList<>();
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(i==j) {
					continue;
				}
				long p = P[i][2];
				long x1 = P[i][0];
				long y1 = P[i][1];
				long x2 = P[j][0];
				long y2 = P[j][1];
				if(p*bottomS >= (Math.abs( x1 - x2 ) + Math.abs( y1 - y2 ))) {
					result.add(new Path(i,j));
				}
			}
		}
		return result;
	}

	long findLowestBound(long ng, long ok, Bounder f) {
		while(ng != ok) {
			long m = (ng+ok)/2;
			if(f.check(m)) {
				ok = m;
			} else {
				ng = m+1;
			}
		}
		return ok;
	}
	
	interface Bounder {
		boolean check(long i);
	}

	public static void main(String[] args) {
		try {
			new Main().solve();
		} finally {
			out.flush();
		}
	}
}

class Graph {
	List<Integer>[] next;

	Graph(List<Path> graph, int nodeSize) {
		next = new List[nodeSize+1];
		for(int i=1; i<=nodeSize; i++) {
			next[i] = new ArrayList<Integer>();
		}
		for(Path p : graph) {
			next[p.src].add(p.dest);
		}
	}
	
	List<Integer> getNext(int n) {
		return next[n];
	}
	
}

class StronglyConnectedComponent {
	List<Path> paths;
	int nodeSize;
	int[] level;
	int[] levelToNode;
	int currentLevel;
	Graph graph;
	Graph revGraph;
	boolean closed[];
	
	StronglyConnectedComponent(List<Path>  paths, int nodeSize) {
		this.paths = paths;
		this.nodeSize = nodeSize;
	}
	
	// 戻り値: 相互に行き来可能な頂点集合のリスト
	List<List<Integer>> search() {
		level = new int[nodeSize+1];
		levelToNode = new int[nodeSize+1];
		graph = new Graph(paths,nodeSize);
		closed = new boolean[nodeSize+1];
		currentLevel = 1;
		for(int i=1; i<=nodeSize; i++) {
			setLevelDfs(i);
		}
		revGraph = new Graph(reversePaths(),nodeSize);
		List<List<Integer>> scc = new ArrayList<>();
		Arrays.fill(closed, false);
		for(int i=currentLevel-1; i>=1; i--) {
			List<Integer> path = new ArrayList<Integer>();
			setSccDfs(path,levelToNode[i]);
			if(path.size()>=1) {
				scc.add(path);
			}
		}
		return scc;
	}
	
	List<Path> reversePaths() {
		List<Path> result = new ArrayList<>();
		for(var p : paths) {
			result.add(new Path(p.dest,p.src));
		}
		return result;
	}
	
	void setLevelDfs(int v) {
		if(closed[v]) {
			return;
		}
		closed[v] = true;
		for(var next : graph.getNext(v)) {
			setLevelDfs(next);
		}
		level[v] = currentLevel;
		levelToNode[currentLevel] = v;
		currentLevel ++;
	}
	
	void setSccDfs(List<Integer> path, int v) {
		if(closed[v]) {
			return;
		}
		closed[v] = true;
		path.add(v);
		for(var next : revGraph.getNext(v)) {
			setSccDfs(path,next);
		}
	}
}

class Path {
	int src;
	int dest;
	
	public Path(int src, int dest) {
		this.src = src;
		this.dest = dest;
	}
}

class CostPath extends Path implements Comparable<CostPath> {
	int cost;
	
	public CostPath(int src, int dest, int cost) {
		super(src,dest);
		this.cost = cost;
	}

	@Override
	public int compareTo(CostPath o) {
		if(cost == o.cost) {
			if(src == o.src) {
				return dest - o.dest;
			} else {
				return src - o.src;
			}
		} else {
			return cost - o.cost;
		}
	}
}

class FastScaner {
	InputStream in;
	
	FastScaner() {
		this.in = System.in;
	}

	char nextChar() {
		try {
			char ch;
			do {
				ch = (char)in.read();
			} while(ch == '\r' || ch=='\n');
			return ch;
		} catch(IOException e) {
			throw new IllegalStateException(e);
		}
	}
	
	int nextInt() {
		return (int) nextLong();
	}
	
	long nextLong() {
		try {
			long result = 0;
			int flag = 1;
			int ch;
			do {
				ch = in.read();
				if(ch=='-') {
					flag = -1;
				}
			} while(!Character.isDigit(ch));
			do {
				result *= 10;
				result += ch - '0';
				ch = in.read();
			} while(Character.isDigit(ch));
			return result * flag;
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}


	char[] nextCharArray(int start, int end) {
		char[] result = new char[end+1];
		for(int i=start; i<=end; i++) {
			result[i] = nextChar();
		}
		return result;
	}	
	
	int[] nextIntArray(int start, int end) {
		int[] result = new int[end+1];
		for(int i=start; i<=end; i++) {
			result[i] = nextInt();
		}
		return result;
	}
	
	long[] nextLongArray(int start, int end) {
		long[] result = new long[end+1];
		for(int i=start; i<=end; i++) {
			result[i] = nextLong();
		}
		return result;
	}

	char[][] nextCharMatrix(int s1, int e1, int s2, int e2) {
		char[][] result = new char[e1+1][e2+1];
		for(int i=s1; i<=e1; i++) {
			for(int j=s2; j<=e2; j++) {
				result[i][j] = nextChar();
			}
		}
		return result;
	}

	int[][] nextIntMatrix(int s1, int e1, int s2, int e2) {
		int[][] result = new int[e1+1][e2+1];
		for(int i=s1; i<=e1; i++) {
			for(int j=s2; j<=e2; j++) {
				result[i][j] = nextInt();
			}
		}
		return result;
	}

	long[][] nextLongMatrix(int s1, int e1, int s2, int e2) {
		long[][] result = new long[e1+1][e2+1];
		for(int i=s1; i<=e1; i++) {
			for(int j=s2; j<=e2; j++) {
				result[i][j] = nextLong();
			}
		}
		return result;
	}


	String next() {
		return next(' ');
	}

	String nextLine() {
		return next((char)-1);
	}

	String next(char a) {
		try {
			char ch;
			do {
				ch = (char)in.read();
			} while(ch == '\r' || ch=='\n' || ch==a);
			StringBuilder buf = new StringBuilder();
			do {
				buf.append(ch);
				ch = (char)in.read();
			} while(ch != '\r' && ch != '\n' && ch != a);
			return buf.toString();
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}
	
}

class FastWriter {
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");
	PrintStream out;
	StringBuilder buf;
	boolean newLine;
	
	FastWriter() {
		this.out = System.out;
		this.buf = new StringBuilder();
		this.newLine = true;
	}

	void print(char ch) {
		newLine = false;
		buf.append(ch);
	}
	
	void print(Object o) {
		if(!newLine) {
			buf.append(" ");
		} else {
			newLine = false;
		}
		buf.append(o);
	}

	void println(int o) {
		buf.append(o);
		println();
	}
	
	void println(long o) {
		buf.append(o);
		println();
	}
	
	void println(double o) {
		buf.append(o);
		println();
	}

	void println(String o) {
		buf.append(o);
		println();
	}

	void println(int[] o) {
		println(Arrays.stream(o).mapToObj((n)->(Integer)n).collect(Collectors.toList()));
	}

	void println(long[] o) {
		println(Arrays.stream(o).mapToObj((n)->(Long)n).collect(Collectors.toList()));
	}
	
	void println(double[] o) {
		println(Arrays.stream(o).mapToObj((n)->(Double)n).collect(Collectors.toList()));
	}

	void println(Collection<?> o) {
		for(var e : o) {
			buf.append(e);
			buf.append(" ");
		}
		if(o.size()>=1) {
			buf.deleteCharAt(buf.length()-1);
		}
		println();
	}

	
	void println() {
		buf.append(LINE_SEPARATOR);
		newLine = true;
	}
	
	void flush() {
		System.out.print(buf);
	}
}
