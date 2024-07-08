import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		MyScanner sc = new MyScanner();
		
		int N = sc.nextInt();
		String S[] = new String[N];
		for(int i=0; i<N; i++) S[i] = sc.next();
		
		int win[] = new int[N];
		Integer place[] = new Integer[N];
		for(int i=0; i<N; i++) place[i] = i+1;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) if(S[i].charAt(j)=='o') win[i]++;
		}
		Arrays.sort(place, new Comparator<Integer>() {
			@Override
			public int compare(Integer x, Integer y) {
				return win[x-1] > win[y-1] ? -1 : 1;
			}
		});
		
		for(int v : place) System.out.print(v + " ");
		System.out.println();
	}
}

class MyScanner {
	public Scanner sc = new Scanner(System.in);
	public int nextInt() {
		return Integer.parseInt(sc.next());
	}
	public long nextLong() {
		return Long.parseLong(sc.next());
	}
	public double nextDouble() {
		return Double.parseDouble(sc.next());
	}
	public String next() {
		return sc.next();
	}
}