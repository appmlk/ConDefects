//Click on the SUBMIT button to make a submission to this problem.
import java.util.*;
import java.io.*;
public class Main {

	public static int[] takeInput(int n) {
		int arr[] = new int[n];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = in.nextInt();
		}
		return arr;
	}
	static InputStream inputStream = System.in;
	static InputReader in = new InputReader(inputStream);
	public static void main(String[] args) {
		// System.in and System.out are input and output streams, respectively.

		// Taking input for Integer
//		int t = in.nextInt();
//		while (t-- > 0) {
//
//		}
		
		//int n = in.nextInt();
//		int a = in.nextInt();
//		int b = in.nextInt();
		
		
//		int arr[] = takeInput(n);
		int arr[] = new int[8];
		for(int i = 0; i < arr.length; i++) {
			arr[i] = in.nextInt();
		}
		
		if((arr[0] < 100 && arr[0] > 675) || arr[0] % 25 != 0) {
			System.out.println("No");
			return;
		}
		for(int i = 1; i < arr.length; i++) {
			if((arr[i] < 100 || arr[i] > 675) || arr[i] % 25 != 0 || (arr[i - 1] > arr[i])) {
				System.out.println("No");
				return;
			}
		}
		
		System.out.println("Yes");
	}

	static class InputReader {
		public BufferedReader reader;
		public StringTokenizer tokenizer;

		public InputReader(InputStream stream) {
			reader = new BufferedReader(new InputStreamReader(stream), 32768);
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
	}
}
