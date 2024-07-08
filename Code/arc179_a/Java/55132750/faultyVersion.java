import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] s) {
		try {
			InputStream inputStream = System.in;
			InputReader in = new InputReader(inputStream);
			PrintWriter writer = new PrintWriter(System.out);

			Task solution = new Task();
			solution.solve(in,writer);

			writer.close();
			inputStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

class Task {

	public void solve(InputReader in, PrintWriter out) throws IOException {

		int N = in.nextInt();
		int K = in.nextInt();

		int[] ar = new int[N];
		for (int i = 0; i < N; i++) {
			ar[i] = in.nextInt();
		}

		// total sum
		int total = 0;
		for (int i = 0; i < N; i++) {
			total = total + ar[i];
		}

		if (K > 0) {
			out.println("Yes");
			Arrays.sort(ar);
			printArray(ar, out);
			return;
		}
		if (total < K) {
			out.println("No");
			return;
		}

		out.println("Yes");
		Arrays.sort(ar);
		int s = 0;
		int e = ar.length-1;
		while (s < e) {
			int temp = ar[s];
			ar[s] = ar[e];
			ar[e] = temp;
			s++;
			e--;
		}

		printArray(ar, out);

	}

	private void printArray(int[] ar, PrintWriter out) {
		for (int i = 0; i < ar.length; i++) {
			out.printf("%d ", ar[i]);
		}
		out.println("");
	}


}

class InputReader {
	public BufferedReader reader;
	public StringTokenizer tokenizer;

	public InputReader(InputStream stream) {
		reader = new BufferedReader(new InputStreamReader(stream));
		tokenizer = null;
	}

	public String next() {
		while (tokenizer == null || !tokenizer.hasMoreTokens()) {
			try {
				tokenizer = new StringTokenizer(reader.readLine());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return tokenizer.nextToken();
	}

	public int nextInt() {
		return Integer.parseInt(next());
	}

	public long nextLong() {
		return Long.parseLong(next());
	}

	public double nextDouble() {
		return Double.parseDouble(next());
	}

}
