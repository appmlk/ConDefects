import java.io.*;
import java.util.*;

class Main {
	int N, M;
	int[] A, B, C;
	
	void calc() {
		int[] ns = nextInts();
		N = ns[0]; M = ns[1];
		A = nextInts(); B = nextInts();
		Arrays.sort(A); Arrays.sort(B);
		C = Arrays.copyOf(A, N+M);
		for (int i = 0; i < M; i++) C[i+N] = B[i] + 1;
		Arrays.sort(C);
		System.out.println(solve());
	}

	int solve() {
		int ai = 0, bi = 0;
		for (int i = 0; i < N+M; i++) {
			while (i < N+M-1 && C[i] == C[i+1]) i++;
			int x = C[i];
			while (ai < N && A[ai] <= x) ai++;
			while (bi < M && B[bi] < x) bi++;
			if (ai >= M-bi) return x;
		}
		return -1;
	}

	// ---
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String next() { try { return br.readLine(); } catch (Exception e) { return null; } }
	String[] nexts() { return next().split(" "); }

	static int i(String s) { return Integer.parseInt(s); }
	int nextInt() { return i(next()); }
	int[] nextInts() { return Arrays.stream(nexts()).mapToInt(Main::i).toArray(); }

	public static void main(String[] args) {
		new Main().calc();
	}
}
