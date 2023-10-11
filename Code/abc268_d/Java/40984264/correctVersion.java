import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Main {
	static int len = 0;
	static int N;
	static HashSet<String> T;

	public static void main(String[] args) {
		FastScanner sc = new FastScanner(System.in);
		N = sc.nextInt();
		int M = sc.nextInt();
		String[] S = new String[N+1];
		for(int i=1; i<=N; i++ ) {
			S[i] = sc.next();
			len += S[i].length();
		}
		T = new HashSet<>(M);
		for(int i=1; i<=M; i++ ) {
			T.add(sc.next());
		}
		Stack<Select> queue = new Stack<>();
		var select = new Select();
		for(int i=1; i<=N; i++) {
			select.unused.add(S[i]);
		}
		queue.add(select);
		while(!queue.isEmpty()) {
			var c = queue.pop();
			if(c.unused.size()==0) {
				String r = create(c);
				if(r != null) {
					System.out.println(r);
					return;
				}
				continue;
			}
			for(var s : c.unused) {
				queue.add(new Select(c,s));
			}
		}
		System.out.println(-1);
	}
	
	static String create(Select s) {
		int delimitorLen = 16 - len;
		int maxAdditional = delimitorLen - (N-1);
		for(int i=0; i<=maxAdditional; i++) {
			String r = create(s,new int[N-1],i);
			if(r != null) {
				return r;
			}
		}
		return null;
	}
	
	static String create(Select select, int[] dels, int additional) {
		if(additional==0) {
			int i=0;
			StringBuilder buf = new StringBuilder();
			while(select.s != null) {
				buf.append(select.s);
				if(i < N-1) {
					buf.append("_".repeat(dels[i++]+1));
				}
				select = select.par;
			}
			String r = buf.toString();
			if(T.contains(r) || r.length()>16 || r.length()<3) {
				return null;
			}
			return r;
		}
		for(int i=0; i<N-1; i++) {
			int[] ndels = dels.clone();
			ndels[i]++;
			String r = create(select,ndels,additional-1);
			if(r!=null) {
				return r;
			}
		}
		return null;
	}

}

class Select {
	Set<String> unused;
	String s;
	Select par;
	
	Select() {
		unused = new HashSet<>();
	}
	
	Select(Select par, String s) {
		this.par = par;
		unused = new HashSet<>(par.unused);
		unused.remove(s);
		this.s = s;
	}
}

class FastScanner {
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");
	InputStream in;
	
	FastScanner(InputStream in) {
		this.in = in;
	}
	
	String next() {
		try {
			char ch;
			do {
				ch = (char)in.read();
			} while(isDelimiter(ch));
			StringBuilder buf = new StringBuilder();
			do {
				buf.append(ch);
				ch = (char)in.read();
			} while(!isDelimiter(ch));
			return buf.toString();
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
		
	}
	
	boolean isDelimiter(char ch) {
		return ch==' ' || ch=='\r' || ch=='\n';
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
}
