import java.io.*;
import java.util.*;

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
	
	FastWriter() {
		this.out = System.out;
		this.buf = new StringBuilder();
	}

	FastWriter pr(char ch) {
		buf.append(ch);
		return this;
	}

	FastWriter pr(int o) {
		buf.append(o);
		buf.append(' ');
		return this;
	}
	
	FastWriter pr(long o) {
		buf.append(o);
		buf.append(' ');
		return this;
	}
	
	FastWriter pr(double o) {
		buf.append(o);
		buf.append(' ');
		return this;
	}
	
	FastWriter pr(Object o) {
		buf.append(o);
		buf.append(' ');
		return this;
	}

	FastWriter pr(Iterable<?> o) {
		for(var e : o) {
			buf.append(e);
			buf.append(' ');
		}
		return this;
	}

	
	FastWriter ln() {
		int l = buf.length();
		if(l >= 1) {
			if(buf.charAt(l-1)==' ') {
				buf.deleteCharAt(l-1);
			}
		}
		buf.append(LINE_SEPARATOR);
		return this;
	}
	
	void flush() {
		System.out.print(buf);
	}
}


//非連結無向グラフ
class DisjointSetUnion {
	int rank[];
	int next[];
	
	DisjointSetUnion(int nodeSize) {
		rank = new int[nodeSize+1];
		next = new int[nodeSize+1];
		Arrays.fill(rank, 1);
	}
	
	void addNode(int a, int b) {
		a = getRoot(a);
		b = getRoot(b);
		if(a==b) {
			return;
		}
		if(rank[a] < rank[b]) {
			next[a] = b;
			rank[b] += rank[a];
		} else {
			next[b] = a;
			rank[a] += rank[b];
		}
	}
	
	int size(int v) {
		return rank[getRoot(v)];
	}

	boolean isJoint(int a, int b) {
		return getRoot(a) == getRoot(b);
	}
	
	int getRoot(int v) {
		if(next[v] == 0) {
			return v;
		}
		return next[v] = getRoot(next[v]);
	}

	Set<Integer> getRootSet() {
		Set<Integer> result = new HashSet<>();
		for(int i=1; i<rank.length; i++) {
			result.add( getRoot(i) );
		}
		return result;
	}
}

public class Main {
	static FastScaner in = new FastScaner();
	static FastWriter out = new FastWriter();

	
	int N = in.nextInt();
	int K = in.nextInt();
	XY[] xys = new XY[N];
	TreeSet<XY> tree = new TreeSet<XY>();
	
	void solve() {
		var chars = in.nextCharArray(0, N-1);
		for(int i=0; i<chars.length; i++) {
			xys[i] = new XY(i,chars[i]);
		}
		
		int preY = -1;
		for(int i=0; i<N; i++) {
			if(xys[i].ch=='Y') {
				if(preY == -1) {
				} else {
					for(int j=preY+1; j<i; j++) {
						xys[j].groupSize = i - preY - 1;
					}
				}
				preY = i;
			}
		}
		for(var xy : xys) {
			xy.update();
			tree.add(xy);
		}
		boolean xtoy = true;
		while(!tree.isEmpty() && K>=1) {
			var t = tree.pollFirst();
			if(xtoy && t.ch == 'Y') {
				xtoy = false;
				var dsu = new DisjointSetUnion(N);
				boolean pre = true;
				for(int i=0; i<N; i++) {
					if(!xys[i].changed && !pre) {
						dsu.addNode(i, i+1);
					}
					pre = xys[i].changed;
				}
				tree = new TreeSet<XY>();
				for(int i=0; i<N; i++) {
					if(!xys[i].changed) {
						xys[i].groupSize = dsu.size(i+1);
						xys[i].update();
						tree.add(xys[i]);
					}
				}
				continue;
			}
			t.change();
			K --;
			if(t.index >= 1 && !xys[t.index-1].changed) {
				tree.remove(xys[t.index-1]);
				xys[t.index-1].update();
				tree.add(xys[t.index-1]);
			}
			if(t.index < N-1 && !xys[t.index+1].changed) {
				tree.remove(xys[t.index+1]);
				xys[t.index+1].update();
				tree.add(xys[t.index+1]);
			}
		}

		int ans = 0;
		boolean pre = false;
		for(int i=0; i<N; i++) {
			if(xys[i].ch == 'Y' && pre) {
				ans ++;
			}
			pre = xys[i].ch == 'Y';
		}
		out.pr(ans).ln();
	}
	
	class XY implements Comparable<XY>{
		int index;
		char ch;
		int priority;
		boolean changed;
		int groupSize;
		
		public XY(int index, char ch) {
			this.index = index;
			this.ch = ch;
			changed = false;
		}
		
		void change() {
			changed = true;
			ch = ch=='X' ? 'Y' : 'X';
		}
		
		void update() {
			if(ch=='X') {
				int c = 1;
				if(index >= 1 && xys[index-1].ch == 'Y') {
					c ++;
				}
				if(index < N-1 && xys[index+1].ch == 'Y') {
					c ++;
				}
				c *= 1000000;
				if(groupSize >= 1) {
					c += 1000000 - groupSize;
				}
				priority = c;
			} else {
				int c = -1;
				if(index >= 1 && xys[index-1].ch == 'Y') {
					c --;
				}
				if(index < N-1 && xys[index+1].ch == 'Y') {
					c --;
				}
				c *= 1000000;
				c += groupSize;
				priority = c;
			}
		}
		
		@Override
		public int compareTo(Main.XY o) {
			if(priority == o.priority) {
				return index - o.index;
			}
			return o.priority - priority;
		}
		
		
		
	}
	

	public static void main(String[] args) {
		try {
			new Main().solve();
		} finally {
			out.flush();
		}
	}
}
