
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

@SuppressWarnings("unused")
public class Main {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "";
	
	//global
	private final static int BASE = 998244353;
	private static long[] BIT;

	private static void update(int i, long k) {
		while (i<BIT.length) {
			BIT[i] = (BIT[i] + k)%BASE;
			i = i + (i&(-i));
		}
	}

	private static long get(int i) {
		long tmp=0;
		while (i>0) {
			tmp = (tmp+BIT[i]) % BASE;
			i = i - (i&(-i));
		}
		return tmp;
	}

	static void solve() {
		int N = readInt();
		int[] A = readIntArray(N);
		
		BIT = new long[N+1];
		long[] S = new long[N+1];
		S[0] = 1l*A[0];
		for (int i=1;i<N;i++) S[i] = S[i-1] + 1L*A[i];
		Set<Long> used = new HashSet<>();
		for (int i=0;i<N;i++) {
			if (used.contains(S[i])) continue;
			used.add(S[i]);
			update(i+1, 1);
		}
		
		Map<Long, Integer> last = new HashMap<>();

		for (int i=0;i<N-1;i++) {
			//withou suffix = 0
			long F;
			if (!last.containsKey(S[i])) {
				F = get(i);
			} else {
				F = (get(i) - get(last.get(S[i])) + BASE) % BASE;
			}
			last.put(S[i], i);
			update(i+1, F);
		}

		out.println((get(N) - get(0) + 1 + BASE)%BASE);
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
		out = new PrintWriter(System.out);
		
		solve();
		out.flush();
		long G = System.currentTimeMillis();

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
