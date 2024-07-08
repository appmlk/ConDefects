import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class Main implements Runnable {
	
	public static void main(String[] args) {
		new Thread(null, new Main(), "", Runtime.getRuntime().maxMemory()).start(); 
	}
	
	final long mod = 998244353;
	
	long pow(long a, long n) {
		if (n == 0) return 1;
		return pow(a * a % mod, n / 2) * (n % 2 == 1 ? a : 1) % mod;
	}
	
	long inv(long a) {
		return pow(a, mod - 2);
	}
	
	public void run() {
		FastScanner sc=new FastScanner();
		PrintWriter pw=new PrintWriter(System.out);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		long H = sc.nextLong();
		long[] A = new long[N];
		int[] B = new int[N];
		for (int i = 0; i < N; ++i) {
			A[i] = sc.nextLong();
			B[i] = sc.nextInt() - 1;
		}
		
		long HP = H;
		class State implements Comparable<State> {
			long point;
			int id;
			
			public State(long point, int id) {
				this.point = point;
				this.id = id;
			}

			public int compareTo(State o) {
				if (point != o.point) return Long.compare(point, o.point);
				else return Integer.compare(id, o.id);
			};
		}
		TreeSet<State> ok = new TreeSet<>();
		TreeSet<State> ng = new TreeSet<>();
		long[] point = new long[M];
		int[] ans = new int[N];
		for (int i = 0; i < N; ++i) {
			State state = new State(point[B[i]], B[i]);
			if (ok.contains(state)) {
				ok.remove(state);
				state.point += A[i];
				ok.add(state);
			} else {
				if (ng.contains(state)) {
					ng.remove(state);
				}
				state.point += A[i];
				ng.add(state);
				HP -= A[i];
			}
			point[B[i]] += A[i];
			while (!ng.isEmpty() && !ok.isEmpty() && ng.last().point > ok.first().point) {
				State s0 = ok.pollFirst();
				State s1 = ng.pollLast();
				HP += s1.point - s0.point;
				ok.add(s1);
				ng.add(s0);
			}
			while (!ok.isEmpty() && HP - ok.first().point > 0) {
				HP -= ok.first().point;
				ng.add(ok.pollFirst());
			}
			while (HP <= 0) {
				State ns = ng.pollLast();
				HP += ns.point;
				ok.add(ns);
			}
			ans[i] = ok.size();
		}
		int[] ans2 = new int[M + 1];
		int p = 0;
		for (int i = 0; i <= M; ++i) {
			while (p < N && ans[p] <= i) ++p;
			ans2[i] = p;
		}
		for (int i = 0; i <= M; ++i) {
			pw.print(ans2[i] + (i == M? "\n" : " "));
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
    public int nextInt() {
    	return (int)nextLong();
    }
}