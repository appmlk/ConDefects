import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Main {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "";

	static void solve()
	{
		int n = ni();
		long[] a = new long[n];
		for(int i = 0;i < n;i++)a[i] = nl();

		int argmin = 0;
		for(int i = 0;i < n;i++){
			if(a[i] < a[argmin]){
				argmin = i;
			}
		}
		long ans = go(a, argmin);
		ans += go(rev(a), n-1-argmin);
		ans += a[argmin];

		out.println(ans);
	}

	static long go(long[] a, int argmin)
	{
		long ans = 0;
		int n = a.length;
		int[] stack = new int[n];
		int sp = 0;
		stack[sp++] = argmin;
		for (int i = argmin + 1; i < n; i++) {
			while (sp > 1 && a[stack[sp - 1]] >= a[i]) {
				sp--;
			}

			while (sp - 2 >= 0) {
				int x = stack[sp - 2], y = stack[sp - 1];
				int len = i - x;
				long ey = a[x] + (a[i] - a[x]) / len * (y - x) + ((a[i] - a[x]) % len == 0 ? 0 : Math.max(0, y - x - (len - (a[i] - a[x]) % len)));
				if (a[y] >= ey) {
					sp--;
				} else {
					break;
				}
			}
			stack[sp++] = i;
		}
		for (int i = 1; i < sp; i++) {
			int len = stack[i] - stack[i-1];
			long dv = a[stack[i]] - a[stack[i-1]];
			ans += a[stack[i-1]] * len + dv%len * (dv%len+1) / 2 + (long)len*(len+1)/2 * (dv/len);
		}
		return ans;
	}

	public static long[] rev(long[] a){long[] b = new long[a.length];for(int i = 0;i < a.length;i++)b[a.length-1-i] = a[i];return b;}


	public static void main(String[] args) throws Exception
	{
		long S = System.currentTimeMillis();
		is = INPUT.isEmpty() ? System.in : new ByteArrayInputStream(INPUT.getBytes());
		out = new PrintWriter(System.out);
		
		solve();
		out.flush();
		long G = System.currentTimeMillis();
		tr(G-S+"ms");
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
	
	private static double nd() { return Double.parseDouble(ns()); }
	private static char nc() { return (char)skip(); }
	
	private static String ns()
	{
		int b = skip();
		StringBuilder sb = new StringBuilder();
		while(!(isSpaceChar(b))){
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}
	
	private static char[] ns(int n)
	{
		char[] buf = new char[n];
		int b = skip(), p = 0;
		while(p < n && !(isSpaceChar(b))){
			buf[p++] = (char)b;
			b = readByte();
		}
		return n == p ? buf : Arrays.copyOf(buf, p);
	}
	
	private static char[][] nm(int n, int m)
	{
		char[][] map = new char[n][];
		for(int i = 0;i < n;i++)map[i] = ns(m);
		return map;
	}
	
	private static int[] na(int n)
	{
		int[] a = new int[n];
		for(int i = 0;i < n;i++)a[i] = ni();
		return a;
	}
	
	private static int ni()
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
	
	private static long nl()
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
