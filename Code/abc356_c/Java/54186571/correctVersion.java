import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		FastScanner sc = new FastScanner();
		int n = Integer.parseInt(sc.next());
		int m = Integer.parseInt(sc.next());
		int k = Integer.parseInt(sc.next());
		HashMap<Integer, Character> keySet = new HashMap<>();
		for (int i = 0; i < m; i++) {
			int C = Integer.parseInt(sc.next());
			int keys = 0;
			for (int j = 0; j < C; j++) {
				int A = Integer.parseInt(sc.next());
				keys |= 1 << (A - 1);
			}
			char r = sc.next().charAt(0);
			if (keySet.containsKey(keys)) {
				if (keySet.get(keys) != r) {
					System.out.println(0);
					return;
				}
			}
			keySet.put(keys, r);
		}
		System.out.println(test(k, n, keySet));
	}
	
	private static int test(int k, int n, HashMap<Integer, Character> keySets) {
		int count = 0;
		for (int keySet = 0; keySet < 1 << n; keySet++) {
			boolean flag = true;
			for (int keys: keySets.keySet()) {
				char r = keySets.get(keys);
				if ((r == 'o' && Integer.bitCount(keys & keySet) < k) || (r == 'x' && Integer.bitCount(keys & keySet) >= k)) {
					flag = false;
					break;
				}
			}
			if (flag) {
				count++;
			}
		}
		return count;
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
