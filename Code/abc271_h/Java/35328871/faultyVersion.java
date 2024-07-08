

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
		for(int T = ni();T > 0;T--)go();
	}
	
	static void go()
	{
		long x = ni(), y = ni();
		char[] s = ns(8);
		if(x < 0){
			x = -x;
			{char d = s[0]; s[0] = s[4]; s[4] = d;}
			{char d = s[1]; s[1] = s[3]; s[3] = d;}
			{char d = s[5]; s[5] = s[7]; s[7] = d;}
		}
		if(y < 0){
			y = -y;
			{char d = s[1]; s[1] = s[7]; s[7] = d;}
			{char d = s[2]; s[2] = s[6]; s[6] = d;}
			{char d = s[3]; s[3] = s[5]; s[5] = d;}
		}
		if(x < y){
			long z = x; x = y; y = z;
			{char d = s[0]; s[0] = s[2]; s[2] = d;}
			{char d = s[3]; s[3] = s[7]; s[7] = d;}
			{char d = s[4]; s[4] = s[6]; s[6] = d;}
		}
		// x >= y >= 0
		if(x == 0 && y == 0){
			out.println(0);
			return;
		}

		long ret = Long.MAX_VALUE;

		if(s[0] == '1' && y == 0){
			ret = Math.min(ret, x);
		}
		if(s[1] == '1' && y == x){
			ret = Math.min(ret, x);
		}
		if(s[0] == '1' && s[2] == '1'){
			ret = Math.min(ret, x+y);
		}
		if(s[0] == '1' && s[1] == '1'){
			ret = Math.min(ret, x);
		}
		if(s[0] == '1' && s[3] == '1'){
			ret = Math.min(ret, x+y+y);
		}
		if(s[6] == '1' && s[1] == '1'){
			ret = Math.min(ret, 2*x-y);
		}
		if(s[7] == '1' && s[1] == '1'){
			if((x+y) % 2 == 0) {
				ret = Math.min(ret, x);
			}
		}
		if(s[7] == '1' && s[1] == '1' && s[0] == '1'){
			ret = Math.min(ret, x);
		}
		if(s[7] == '1' && s[1] == '1' && s[2] == '1'){
			ret = Math.min(ret, x+1);
		}
		if(s[7] == '1' && s[1] == '1' && s[5] == '1'){
			ret = Math.min(ret, x+2);
		}
		if(s[7] == '1' && s[1] == '1' && s[6] == '1'){
			ret = Math.min(ret, x+1);
		}
		if(s[2] == '1' &&  s[7] == '1'){
			ret = Math.min(ret, 2*x+y);
		}

		out.println(ret == Long.MAX_VALUE ? -1 : ret);
	}
	
	public static void main(String[] args) throws Exception
	{
		long S = System.currentTimeMillis();
		is = INPUT.isEmpty() ? System.in : new ByteArrayInputStream(INPUT.getBytes());
		out = new PrintWriter(System.out);
		
		solve();
		out.flush();
		long G = System.currentTimeMillis();

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

	private static int[][] nmi(int n, int m)
	{
		int[][] map = new int[n][];
		for(int i = 0;i < n;i++)map[i] = na(m);
		return map;
	}

	private static int[] na(int n)
	{
		int[] a = new int[n];
		for(int i = 0;i < n;i++)a[i] = ni();
		return a;
	}

	private static long[] nal(int n)
	{
		long[] a = new long[n];
		for(int i = 0;i < n;i++)a[i] = nl();
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
