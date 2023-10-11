import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class ArrayUtils {
	static int[] toIntArray(Collection<Integer> l) {
		return l.stream().mapToInt(Integer::intValue).toArray();
	}

	static long[] toLongArray(Collection<Long> l) {
		return l.stream().mapToLong(Long::longValue).toArray();
	}

	static double[] toDoubleArray(Collection<Double> l) {
		return l.stream().mapToDouble(Double::doubleValue).toArray();
	}

	static List<Integer> toList(int[] a) {
		return Arrays.stream(a).mapToObj(Integer::valueOf).collect(Collectors.toList());
	}

	static List<Long> toList(long[] a) {
		return Arrays.stream(a).mapToObj(Long::valueOf).collect(Collectors.toList());
	}

	static List<Double> toList(double[] a) {
		return Arrays.stream(a).mapToObj(Double::valueOf).collect(Collectors.toList());
	}

	static void fills(int[][] a, int value) {
		for(int[] a2 : a) {
			Arrays.fill(a2, value);
		}
	}

	static void fills(long[][] a, long value) {
		for(long[] a2 : a) {
			Arrays.fill(a2, value);
		}
	}

	static char[] reverse(char[] a) {
		for (int i = 0, j = a.length - 1; j > i; i++, j--) {
			char temp = a[j];
			a[j] = a[i];
			a[i] = temp;
		}
		return a;
	}
	
	static void reverse(int[] a) {
		for (int i = 0, j = a.length - 1; j > i; i++, j--) {
			int temp = a[j];
			a[j] = a[i];
			a[i] = temp;
		}
	}

	static void reverse(long[] a) {
		for (int i = 0, j = a.length - 1; j > i; i++, j--) {
			long temp = a[j];
			a[j] = a[i];
			a[i] = temp;
		}
	}
}

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
		out.print(buf);
	}
}


public class Main {
	static FastScaner in = new FastScaner(System.in);
	static FastWriter out = new FastWriter(System.out);
	static FastWriter tr = new FastWriter(System.err);

	
	void solve() {
		int T = in.nextInt();
		for(int i=1; i<=T; i++) {
			out.pr(doTestCase()?"Yes":"No").ln();
		}
	}
	
	boolean doTestCase() {
		int N = in.nextInt();
		long K = in.nextLong();
		var S = in.next();
		var Sr = rev(S);
		
		K %= 2*N;
		String Sdash;
		if(K <= N) {
			Sdash = Sr.substring(0,(int)K);
		} else {
			int l = (int)(2*N - K);
			Sdash = Sr.substring(0,(int)(N-l)) + Sr;
		}
		var s1 = S+Sdash;
		if(!s1.equals(rev(s1))) {
			return false;
		}
		var s2 = Sdash+S;
		if(!s2.equals(rev(s2))) {
			return false;
		}
		return true;
	}
	
	String rev(String s) {
		return new StringBuilder(s).reverse().toString();
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
