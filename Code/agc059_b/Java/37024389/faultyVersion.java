import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author lucasr
 */
public class Main {
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		MyScanner in = new MyScanner(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		BArrangeYourBalls solver = new BArrangeYourBalls();
		int testCount = Integer.parseInt(in.next());
		for (int i = 1; i <= testCount; i++)
			solver.solve(i, in, out);
		out.close();
	}

	static class BArrangeYourBalls {
		public static MyScanner sc;
		public static PrintWriter out;

		public void solve(int testNumber, MyScanner sc, PrintWriter out) {
			BArrangeYourBalls.sc = sc;
			BArrangeYourBalls.out = out;

			int N = sc.nextInt();
			int[] count = new int[N];
			for (int i = 0; i < N; i++) {
				count[sc.nextInt() - 1]++;
			}
			IntArray inner = new IntArray(), leaves = new IntArray();
			for (int i = 0; i < N; i++) {
				if (count[i] == 1) {
					leaves.add(i);
				} else if (count[i] > 1) {
					inner.add(i);
				}
			}
			IntArray[] adj = new IntArray[N];
			for (int i = 0; i < N; i++) {
				adj[i] = new IntArray();
			}
			int[] rem = count.clone();
			boolean can = true;
			int innerIdx = 0;
			for (int i = 0; i < leaves.size(); i++) {
				int cur = leaves.get(i);
				if (innerIdx < inner.size()) {
					int other = inner.get(innerIdx);
					adj[cur].add(other);
					adj[other].add(cur);
					rem[other]--;
					rem[cur]--;
					if (rem[other] == 0) {
						if (i + 1 < leaves.size()) can = false;
						break;
					}
					while (rem[other] == 1 && innerIdx + 1 < inner.size()) {
						int tmp = inner.get(innerIdx + 1);
						adj[tmp].add(other);
						adj[other].add(tmp);
						rem[tmp]--;
						rem[other]--;
						other = tmp;
						innerIdx++;
					}
				} else {
					can = false;
				}
			}
			if (can) {
				for (int i = innerIdx; i + 1 < inner.size(); i++) {
					int a = inner.get(i);
					int b = inner.get(i + 1);
					adj[a].add(b);
					adj[b].add(a);
					rem[a]--;
					rem[b]--;
				}
				IntArray order = new IntArray();
				int first = !leaves.isEmpty() ? leaves.first() : inner.first();
				dfs(first, -1, adj, order);
				for (int i = 0; i < order.size(); i++) {
					int cur = order.get(i);
					out.print(" " + (cur + 1));
					while (rem[cur] > 0) {
						out.print(" " + (cur + 1));
						rem[cur]--;
					}
				}
				out.println();
			} else {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < count[i]; j++) {
						out.print(" " + (i + 1));
					}
				}
				out.println();
			}
		}

		private void dfs(int node, int parent, IntArray[] adj, IntArray order) {
			order.add(node);
			boolean first = true;
			for (int i = 0; i < adj[node].size(); i++) {
				int next = adj[node].get(i);
				if (next != parent) {
					dfs(next, node, adj, order);
					if (first && parent == -1) first = false;
					else order.add(node);
				}
			}
		}

	}

	static class IntArray {
		int[] arr;
		int size;

		public IntArray() {
			arr = new int[4];
		}

		public void add(int val) {
			if (size == arr.length) {
				arr = Arrays.copyOf(arr, 2 * arr.length);
			}
			arr[size++] = val;
		}

		public int get(int pos) {
			return arr[pos];
		}

		public int first() {
			return arr[0];
		}

		public int size() {
			return size;
		}

		public boolean isEmpty() {
			return size() == 0;
		}

		public int[] toArray() {
			return Arrays.copyOf(arr, size);
		}

		public String toString() {
			return "IntArray " + Arrays.toString(toArray());
		}

	}

	static class MyScanner {
		private BufferedReader br;
		private StringTokenizer tokenizer;

		public MyScanner(InputStream is) {
			br = new BufferedReader(new InputStreamReader(is));
		}

		public String next() {
			while (tokenizer == null || !tokenizer.hasMoreTokens()) {
				try {
					tokenizer = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
			return tokenizer.nextToken();
		}

		public int nextInt() {
			return Integer.parseInt(next());
		}

	}
}

