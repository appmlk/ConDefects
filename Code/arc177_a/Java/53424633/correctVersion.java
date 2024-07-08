import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		FastScanner sc = new FastScanner();
		
		HashMap<Integer, Integer> coins = new HashMap<>();
		int sum_coins = 0;
		int x = Integer.parseInt(sc.next());
		coins.put(1, x);
		sum_coins += 1 * x;
		x = Integer.parseInt(sc.next());
		coins.put(5, x);
		sum_coins += 5 * x;
		x = Integer.parseInt(sc.next());
		coins.put(10, x);
		sum_coins += 10 * x;
		x = Integer.parseInt(sc.next());
		coins.put(50, x);
		sum_coins += 50 * x;
		x = Integer.parseInt(sc.next());
		coins.put(100, x);
		sum_coins += 100 * x;
		x = Integer.parseInt(sc.next());
		coins.put(500, x);
		sum_coins += 500 * x;
		
		int n = Integer.parseInt(sc.next());
		int[] a = new int[n];
		int one = 0;
		int five = 0;
		int ten = 0;
		int fifty = 0;
		int hundred = 0;
		int fivehundred = 0;
		int sum_all = 0;
		for (int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(sc.next());
			sum_all += a[i];
			one += a[i] % 5;
			five += (a[i] % 10 - a[i] % 5) / 5;
			ten += (a[i] % 50 - a[i] % 10) / 10;
			fifty += (a[i] % 100 - a[i] % 50) / 50;
			hundred += (a[i] % 500 - a[i] % 100) / 100;
			fivehundred += a[i] / 500;
		}
		int add = 0;
		
		if (one <= coins.get(1) && (coins.get(1) - one) / 5 > 0) {
			add = coins.get(1) - one;
			coins.put(5, coins.get(5) + add / 5);
			coins.put(1, coins.get(1) - add % 5);
		} else if (one > coins.get(1)) {
			System.out.println("No");
			return;
		}
		
		if (five <= coins.get(5) && (coins.get(5) - five) * 5 / 10 > 0) {
			add = coins.get(5) - five;
			coins.put(10, coins.get(10) + add * 5 / 10);
			coins.put(5, coins.get(5) - add * 5 % 10);
		} else if (five > coins.get(5)) {
			System.out.println("No");
			return;
		}
		
		if (ten <= coins.get(10) && (coins.get(10) - ten) * 10 / 50 > 0) {
			add = coins.get(10) - ten;
			coins.put(50, coins.get(50) + add * 10 / 50);
			coins.put(10, coins.get(10) - add * 10 % 50);
		} else if (ten > coins.get(10)) {
			System.out.println("No");
			return;
		}
		
		if (fifty <= coins.get(50) && (coins.get(50) - fifty) * 50 / 100 > 0) {
			add = coins.get(50) - fifty;
			coins.put(100, coins.get(100) + add * 50 / 100);
			coins.put(50, coins.get(50) - add * 50 % 100);
		} else if (fifty > coins.get(50)) {
			System.out.println("No");
			return;
		}
		
		if (hundred <= coins.get(100) && (coins.get(100) - hundred) * 100 / 500 > 0) {
			add = coins.get(100) - hundred;
			coins.put(500, coins.get(500) + add * 100 / 500);
			coins.put(100, coins.get(100) - add * 100 % 500);
		} else if (hundred > coins.get(100)) {
			System.out.println("No");
			return;
		}
		
		if (fivehundred <= coins.get(500)) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
		
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
