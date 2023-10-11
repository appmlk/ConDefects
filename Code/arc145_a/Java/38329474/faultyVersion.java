import java.io.*;
import java.util.*;
import java.math.*;

public class Main{
	static Kattio k = new Kattio();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = k.nextInt();
		StringBuilder ss = new StringBuilder(k.next());
		if(ss.length() <= 1) {
			k.println("Yes");k.close();
			System.exit(0);
		}
		int q = 0,w = ss.length()-1;
		boolean bo = true;
		while(q < w) {
			if(ss.charAt(q) != ss.charAt(w)) {
				bo = false;
				break;
			}
			q++;w--;
		}
		if(bo) {
			k.println("Yes");k.close();
			System.exit(0);
		}
		if(ss.charAt(0) == 'B' || (ss.charAt(0) == 'A' && ss.charAt(ss.length()-1) == 'A')) {
			k.println("Yes");k.close();
			System.exit(0);
		}
		k.println("No");
		k.close();
	}
}
class Kattio extends PrintWriter {
    private BufferedReader r;
    private StringTokenizer st;
    public Kattio() { this(System.in, System.out); }
    public Kattio(InputStream i, OutputStream o) {
        super(o);
        r = new BufferedReader(new InputStreamReader(i));
    }
    public Kattio(String intput, String output) throws IOException {
        super(output);
        r = new BufferedReader(new FileReader(intput));
    }
    public String next() {
        try {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(r.readLine());
            return st.nextToken();
        } catch (Exception e) {}
        return null;
    }
    public int nextInt() { return Integer.parseInt(next()); }
    public double nextDouble() { return Double.parseDouble(next()); }
    public long nextLong() { return Long.parseLong(next()); }
}