import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.security.cert.X509CRL;
import java.util.*;
import java.lang.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class Main {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "";
	static String OUTPUT = "";
	
	//global
	private final static long BASE = 998244353L;
	private final static int INF_I = 1001001001;
	private final static long INF_L = 1001001001001001001L;
	private final static int MAXN = 100100;
		

	static void solve() {
		int N = readInt();
		long L = readLong(), W = readLong();
		Deque<List<Long>> coord = new LinkedList<>();
		coord.add(Arrays.asList(0l,0l));

		for (int i=0;i<N;i++) {
			long A = readLong();
			long B = A+W;
			
			List<Long> last = coord.pollLast();

			if (last.get(1) >= A) {
				coord.add(Arrays.asList(last.get(0), B));
			} else {
				coord.add(last);
				coord.add(Arrays.asList(A,B));
			}
		}

		coord.add(Arrays.asList(L,L));

		int ans=0;
		while (coord.size()>1) {
			List<Long> curr = coord.pollFirst();
			long diff = coord.peekFirst().get(0) - curr.get(1);
			ans += (diff+W-1L)/W;
		}

		out.println(ans);
	}

	public static void main(String[] args) throws Exception
	{
		long S = System.currentTimeMillis();
		if (INPUT=="") {
			is = System.in;
		} else { 
			File file = new File(INPUT);

			is = new FileInputStream(file);
		}
		if (OUTPUT == "") out = new PrintWriter(System.out);
		else out = new PrintWriter(OUTPUT);
		
		solve();
		out.flush();
		long G = System.currentTimeMillis();

	}

	private static class Point<T extends Number & Comparable<T>> implements Comparable<Point<T>> {

		private T x;
		private T y;

		public Point(T x, T y) {
			this.x = x;
			this.y = y;
		}

		public T getX() {return x;}

		public T getY() {return y;}

		@Override
		public int compareTo(Point<T> o) {
			int cmp = x.compareTo(o.getX());
			if (cmp==0) return y.compareTo(o.getY());
			return cmp;
		}
	}

	private static class ClassComparator<T extends Comparable<T>> implements Comparator<T> {
		public ClassComparator() {}

		@Override
		public int compare(T a, T b) {
			return a.compareTo(b);
		}
	}
	
	private static class ListComparator<T extends Comparable<T>> implements Comparator<List<T>> {
        public ListComparator() {}
 
        @Override
        public int compare(List<T> o1, List<T> o2) {
            for (int i = 0; i < Math.min(o1.size(), o2.size()); i++) {
                int c = o1.get(i).compareTo(o2.get(i));
                if (c != 0) {
                  return c;
                }
              }
            return Integer.compare(o1.size(), o2.size());
        }
    }
	
	private static boolean eof()
	{
		if(lenbuf == -1)return true;
		int lptr = ptrbuf;
		while(lptr < lenbuf)if(!isSpaceChar(inbuf[lptr++]))return false;
		
		try {
			is.mark(1000);
			while(true){
				int b = is.read();
				if(b == -1){
					is.reset();
					return true;
				}else if(!isSpaceChar(b)){
					is.reset();
					return false;
				}
			}
		} catch (IOException e) {
			return true;
		}
	}
	
	private static byte[] inbuf = new byte[1024];
	static int lenbuf = 0, ptrbuf = 0;
	
	private static int readByte()
	{
		if(lenbuf == -1)throw new InputMismatchException();
		if(ptrbuf >= lenbuf){
			ptrbuf = 0;
			try { lenbuf = is.read(inbuf); } catch (IOException e) { throw new InputMismatchException(); }
			if(lenbuf <= 0)return -1;
		}
		return inbuf[ptrbuf++];
	}
	
	private static boolean isSpaceChar(int c) { return !(c >= 33 && c <= 126); }
//	private static boolean isSpaceChar(int c) { return !(c >= 32 && c <= 126); }
	private static int skip() { int b; while((b = readByte()) != -1 && isSpaceChar(b)); return b; }
	
	private static double readDouble() { return Double.parseDouble(readString()); }
	private static char readChar() { return (char)skip(); }
	
	private static String readString()
	{
		int b = skip();
		StringBuilder sb = new StringBuilder();
		while(!(isSpaceChar(b))){
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}
	
	private static char[] readChar(int n)
	{
		char[] buf = new char[n];
		int b = skip(), p = 0;
		while(p < n && !(isSpaceChar(b))){
			buf[p++] = (char)b;
			b = readByte();
		}
		return n == p ? buf : Arrays.copyOf(buf, p);
	}
	
	private static char[][] readTable(int n, int m)
	{
		char[][] map = new char[n][];
		for(int i = 0;i < n;i++)map[i] = readChar(m);
		return map;
	}
	
	private static int[] readIntArray(int n)
	{
		int[] a = new int[n];
		for(int i = 0;i < n;i++)a[i] = readInt();
		return a;
	}

	private static long[] readLongArray(int n) {
		long[] a = new long[n];
		for (int i=0;i<n;i++) a[i] = readLong();
		return a;
	}
	
	private static int readInt()
	{
		int num = 0, b;
		boolean minus = false;
		while((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
		if(b == '-'){
			minus = true;
			b = readByte();
		}
		
		while(true){
			if(b >= '0' && b <= '9'){
				num = num * 10 + (b - '0');
			}else{
				return minus ? -num : num;
			}
			b = readByte();
		}
	}
	
	private static long readLong()
	{
		long num = 0;
		int b;
		boolean minus = false;
		while((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
		if(b == '-'){
			minus = true;
			b = readByte();
		}
		
		while(true){
			if(b >= '0' && b <= '9'){
				num = num * 10 + (b - '0');
			}else{
				return minus ? -num : num;
			}
			b = readByte();
		}
	}
	
	private static void tr(Object... o) { if(INPUT.length() != 0)System.out.println(Arrays.deepToString(o)); }
}
