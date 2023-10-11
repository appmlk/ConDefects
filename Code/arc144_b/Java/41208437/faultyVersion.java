import java.io.*;
import java.util.*;

public class Main {
	static FastScaner in = new FastScaner();
	static FastWriter out = new FastWriter();

	int N = in.nextInt();
	long a = in.nextLong();
	long b = in.nextLong();
	long[] A = in.nextLongArray(0, N-1);

	void solve() {
		Arrays.sort(A);

		long s = A[0];
		long e = A[N-1];
		long mid = e;
		while(true) {
			long m = (s + e) / 2;
			if(m == mid) {
				break;
			}
			mid = m;
			if(check(mid)) {
				s = mid;
			} else {
				e = mid;
			}
		}
		out.println(mid);
	}
	
	boolean check(long m) {
		int up = 0, down = 0;
		for(int i=0; i<N; i++) {
			if(A[i] < m) {
				up += (m - A[i]) / a + ((m - A[i])%a>0 ? 1 : 0);
			} else if(A[i] > m) {
				down += (A[i] - m) / b;
			}
		}
		return up <= down;
	}
			
	

	public static void main(String[] args) {
		try {
			new Main().solve();
		} finally {
			out.flush();
		}
	}
}

class Ai implements Comparable<Ai>{
	int id;
	long a;
	
	public Ai(int id, long a) {
		super();
		this.id = id;
		this.a = a;
	}

	@Override
	public int compareTo(Ai o) {
		if(a == o.a) {
			return id-o.id;
		}
		return Long.compare(a, o.a);
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
