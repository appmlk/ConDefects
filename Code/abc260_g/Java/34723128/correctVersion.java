import java.io.*;
import java.util.*;
import java.util.stream.*;

class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int N, M, Q;
	byte[][] S;
	Query[] q;
	int[][] V, D;
	
	static class Query {
		int i, x, y, ans;
	}

	static int i(String s) { return Integer.parseInt(s); }

	void prepare() throws Exception {
		String[] nm = br.readLine().split(" ");
		N = i(nm[0]); M = i(nm[1]);
		S = new byte[N][N];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) if (s.charAt(j) == 'O') S[i][j] = 1;
		}
		Q = i(br.readLine());
		q = new Query[Q];
		for (int i = 0; i < Q; i++) {
			q[i] = new Query();
			q[i].i = i;
			String[] xy = br.readLine().split(" ");
			q[i].x = i(xy[0]); q[i].y = i(xy[1]);
		}

		V = new int[N+1][N+1];
		for (int i = 1; i <= N; i++)
			for (int j = 1; j <= N; j++)
				V[j][i] = V[j][i-1] + S[i-1][j-1];
		
		D = new int[Math.max(0, 3*N-2*M-1)][(N+1)/2+1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				int s = 2*i+j-3;
				if (s >= D.length) break;
				int r = (N-j+2)/2;
				D[s][r] = D[s][r-1] + S[i-1][j-1];
			}
		}
	}

	void calc() throws Exception {
		prepare();
		Arrays.sort(q, Comparator.comparingInt((Query q) -> q.x).thenComparingInt(q -> q.y));
		int toBeSolved = 0;

	lp:
		for (int i = 1; i <= N; i++) {
			int a = 0;
			for (int j = 1; j <= N; j++) {
				a += (V[j][i] - V[j][Math.max(0, i-M)]);
				int s = 2*(i-M)+j-3;
				if (s >= 0) {
					int r0 = (N-j+2)/2;
					int r1 = Math.min(r0 + M, (N+1)/2-(N%2)*(s%2));
					a -= (D[s][r1] - D[s][r0]);
				}
				while (i == q[toBeSolved].x && j == q[toBeSolved].y) {
					q[toBeSolved++].ans = a;
					if (toBeSolved == Q) break lp;
				}
			}
		}

		Arrays.sort(q, Comparator.comparingInt(q -> q.i));
		System.out.write(Arrays.stream(q).map(q -> String.valueOf(q.ans)).collect(Collectors.joining("\n")).getBytes());
		System.out.flush();
	}

	public static void main(String[] args) throws Exception {
		new Main().calc();
	}
}
