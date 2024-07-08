import java.util.*;
import java.util.stream.*;
import java.io.*;

class Pair implements Comparable<Pair> {
	int p, q;
	@Override
	public int compareTo(Pair other){
		if(this.p > other.p) return 1;
		else if(this.p < other.p) return -1;
		else return 0;
	}
}

public class Main {
	static final int INF = 1<<30;
	static final long INFL = 1L<<60;
	static final int MOD = 1000000007;
	static final int MOD2 = 998244353;
	static List<List<Integer>> g;
	
	public static void main(String[] args) {
		FastScanner fs = new FastScanner();
		PrintWriter pw = new PrintWriter(System.out);
		int n = fs.nextInt();
		int K = fs.nextInt();
		Pair[] s = new Pair[n];
		for(int i = 0; i < n; i++) s[i] = new Pair();
		for(int i = 0; i < n; i++) s[i].p = fs.nextInt();
		for(int i = 0; i < n; i++) s[i].q = fs.nextInt();
		Arrays.sort(s);

		long[][][] dp = new long[n+1][n+1][n+2];
		dp[0][0][n+1] = 1;
		for(int i = 0; i < n; i++){
			int q = s[i].q;
			for(int j = 0; j < n; j++){
				for(int k = 0; k <= n+1; k++){
					// not choose
					dp[i+1][j][Math.min(q, k)] += dp[i][j][k];
					dp[i+1][j][Math.min(q, k)] %= MOD2;
					// choose
					if(k > q) dp[i+1][j+1][k] += dp[i][j][k];
					dp[i+1][j+1][k] %= MOD2;
				}
			}
		}
		long ans = 0;
		for(int i = 1; i <= n+1; i++) ans = (ans + dp[n][K][i]) % MOD2;
		pw.println(ans);
	
		pw.close();
	}
}

class FastScanner {
	private InputStream in = System.in;
	private byte[] buffer = new byte[1024];
	private int length = 0, p = 0;
	private boolean hasNextByte() {
		if (p < length) return true;
		else{
			p = 0;
			try{
				length = in.read(buffer);
			}catch(Exception e){
				e.printStackTrace();
			}
			if(length == 0) return false;
		}
		return true;
	}
	private int readByte() {
		if (hasNextByte() == true) return buffer[p++];
		return -1;
	}
	private static boolean isPrintable(int n) {
		return 33 <= n && n <= 126;
	}
	private void skip() {
		while (hasNextByte() && !isPrintable(buffer[p])) p++;
	}
	public boolean hasNext() {
		skip();
		return hasNextByte();
	}
	public String next() {
		if(!hasNext()) throw new NoSuchElementException();
		StringBuilder sb = new StringBuilder();
		int temp = readByte();
		while (isPrintable(temp)) {
			sb.appendCodePoint(temp);
			temp = readByte();
		}
		return sb.toString();
	}
	public int nextInt() {
		return Math.toIntExact(nextLong());
	}
	public int[] nextInts(int n) {
		int[] ar = new int[n];
		for (int i = 0; i < n; i++) ar[i] = nextInt();
		return ar;
	}
	public long[] nextLongs(int n) {
		long[] ar = new long[n];
		for (int i = 0; i < n; i++) ar[i] = nextLong();
		return ar;
	}
	public long nextLong() {
		if(!hasNext()) throw new NoSuchElementException();
		boolean minus = false;
		int temp = readByte();
		if (temp == '-') {
			minus = true;
			temp = readByte();
		}
		if (temp < '0' || '9' < temp) throw new NumberFormatException();
		long n = 0;
		while (isPrintable(temp)) {
			if ('0' <= temp && temp <= '9') {
				n *= 10;
				n += temp - '0';
			}
			temp = readByte();
		}
		return minus ? -n : n;
	}
	public char[] nextChars() {
		String s = next();
		return s.toCharArray();
	}
	public char[][] nextCharMat(int h, int w) {
		char[][] ar = new char[h][w];
		for(int i = 0; i < h; i++){
			String s = next();
			for(int j = 0; j < w; j++){
				ar[i][j] = s.charAt(j);
			}
		}
		return ar;
	}
}