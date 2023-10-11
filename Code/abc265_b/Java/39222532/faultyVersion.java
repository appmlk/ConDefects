import java.io.*;
import java.util.*;

class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int N, M, T;
	int[] A, X, Y;
	
	static int i(String s) { return Integer.parseInt(s); }
	void calc() throws Exception {
		String[] nmt = br.readLine().split(" ");
		N = i(nmt[0]); M = i(nmt[1]); T = i(nmt[2]);
		String[] as = br.readLine().split(" ");
		A = Arrays.stream(as).mapToInt(s -> i(s)).toArray();
		X = new int[M]; Y = new int[M];
		for (int i = 0; i < M; i++) {
			String[] xy = br.readLine().split(" ");
			X[i] = i(xy[0]) - 1;
			Y[i] = i(xy[1]);
		}
		int m = 0;
		int t = T;
		for (int i = 0; i < N-1; i++) {
			if (t <= A[i]) {
				System.out.println("No");
				return;
			}
			t -= A[i];
			if (m < M && i+1 == X[m]) {
				t += Y[m];
				m++;
			}
		}
		System.out.println("Yes");
	}
	
	public static void main(String[] args) throws Exception {
		new Main().calc();
	}
}
