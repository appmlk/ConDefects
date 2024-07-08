import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

	public static void main(String[] args) {
		
		FastScanner sc = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		
		int n = Integer.parseInt(sc.next());
		int m = Integer.parseInt(sc.next());
		long[] a = new long[n];
		for (int i = 0; i < n; i++) {
			a[i] = Long.parseLong(sc.next());
		}
		Arrays.sort(a);
		
		long[] b = new long[m];
		for (int i = 0; i < m; i++) {
			b[i] = Long.parseLong(sc.next());
		}
		Arrays.sort(b);
		
		int j = 0;
		long ans = 0L;
		for (int i = 0; i < m; i++) {
			for (; j < n; j++) {
				if (a[j] >= b[i]) {
					ans += a[j];
					j++;
					break;
				}
				if (j == n - 1) {
					out.println(-1);
					out.flush();
					return;
				}
			}
			if (j == n && i < m) {
				out.println(-1);
				out.flush();
				return;
			}
		}
		out.println(ans);
		
		out.flush();

	}

	private static long modPow(long a, long b, long mod) {
		long ans = 1L;
		b %= mod - 1L;
		while (b > 0) {
			if ((b & 1) == 1) {
				ans = ans * a % mod;
			}
			a = a * a % mod;
			b >>= 1;
		}
		return ans;
	}

	private static long pow(long a, long b) {
		long ans = 1L;
		while (b > 0) {
			if ((b & 1) == 1) {
				ans *= a;
			}
			a *= a;
			b >>= 1;
		}
		return ans;
	}

	private static class FastScanner {
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
	}

}