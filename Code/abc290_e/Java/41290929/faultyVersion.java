import java.io.*;
import java.util.*;

class FastScaner {
	InputStream in;
	
	FastScaner(InputStream in) {
		this.in = in;
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
	
	FastWriter(PrintStream out) {
		this.out = out;
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


public class Main {
	static FastScaner in = new FastScaner(System.in);
	static FastWriter out = new FastWriter(System.out);
	static FastWriter tr = new FastWriter(System.err);

	
	void solve() {
		int N = in.nextInt();
		int[] A = in.nextIntArray(1, N);
		long same = 0;
		Map<Integer,Long> sum = new HashMap<Integer,Long>(N);
		for(int i=1; i<=(N+1)/2; i++) {
			int d = i;  //Math.min(i, N-i+1);
			same += sum.getOrDefault(A[i], 0L);
			sum.put(A[i], sum.getOrDefault(A[i], 0L) + d);
			if(N%2==1 && i==(N+1)/2) {
				break;
			}
			int ri = N-i+1;
			same += sum.getOrDefault(A[ri], 0L);
			sum.put(A[ri], sum.getOrDefault(A[ri], 0L) + d);
		}
		long all = 0;
		for(int i=1; i<N; i++) {
			all += (long)(N-i) * ((i+1) / 2);
		}
		tr.pr(all).pr(same).ln();
		out.pr(all-same).ln();
	}
	

	public static void main(String[] args) {
		try {
			new Main().solve();
		} finally {
			out.flush();
			tr.flush();
		}
	}
}
