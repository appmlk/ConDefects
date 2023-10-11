import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;


public class Main {

    public static void main(String[] args) {
    	new Main().run();
    }	
    
    ArrayList<int[]> list = new ArrayList<>();
    
    // A[s] += 1
    // A[t] -= 1
    void g(int s, int t, long[] A) {
    	int[] P = new int[A.length];
    	int[] Q = new int[A.length];
    	int cnt = 2;
    	for (int i = 0; i < A.length; ++i) {
    		if (i == s) {
    			P[i] = 2;
    			Q[i] = A.length;
    		} else if (i == t) {
    			P[i] = 1;
    			Q[i] = A.length - 1;
    		} else {
    			P[i] = cnt;
    			Q[i] = A.length - cnt + 1;
    			++cnt;
    		}
    	}
    	for (int i = 0; i < A.length; ++i) {
    		A[i] += P[i] + Q[i];
    	}
    	list.add(P);
    	list.add(Q);
    }
    
    void swap(int a, int b, int[] A) {
    	if (a == b) throw new AssertionError();
    	A[a] ^= A[b];
    	A[b] ^= A[a];
    	A[a] ^= A[b];
    }
	
    void run() {
    	FastScanner sc = new FastScanner();
    	int N = sc.nextInt();
    	long[] A = new long[N];
    	for (int i = 0; i < N; ++i) {
    		A[i] = sc.nextInt();
    	}
    	long sum = 0;
    	for (long a : A) sum += a;
    	if (sum % N != 0) {
    		int[] P = new int[N];
    		for (int i = 0; i < N; ++i) {
    			P[i] = i + 1;
    			A[i] += P[i];
    		}
    		list.add(P);
    	}
    	while (Arrays.stream(A).max().getAsLong() > Arrays.stream(A).min().getAsLong() + 1) {
    		long min = Arrays.stream(A).min().getAsLong();
    		long max = Arrays.stream(A).max().getAsLong();
    		int s = 0;
    		int t = 0;
    		while (min != A[s]) ++s;
    		while (max != A[t]) ++t;
    		g(s, t, A);
    	}
    	PrintWriter pw = new PrintWriter(System.out);
    	if (Arrays.stream(A).max().getAsLong() != Arrays.stream(A).min().getAsLong()) {
    		pw.println("No");
    	} else {
    		pw.println("Yes");
    		pw.println(list.size());
    		for (int[] P : list) {
    			for (int i = 0; i < N; ++i) {
    				pw.print(P[i] + (i == N - 1 ? "\n" : " "));
    			}
    		}
    	}
    	pw.close();
    }
    
    
    void tr(Object...objects) {System.out.println(Arrays.deepToString(objects));}
    
}


class FastScanner {
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
    
    int nextInt() {
    	return (int) nextLong();
    }
}


