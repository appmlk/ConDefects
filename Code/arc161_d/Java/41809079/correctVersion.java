import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] sa = br.readLine().split(" ");
		int n = Integer.parseInt(sa[0]);
		int d = Integer.parseInt(sa[1]);
		br.close();

		if (n * d > (long) n * (n - 1) / 2) {
			System.out.println("No");
			return;
		}

		PrintWriter pw = new PrintWriter(System.out);
		pw.println("Yes");
		for (int i = 1; i <= d; i++) {
			for (int j = 0; j < n; j++) {
				int a = j + 1;
				int b = (j + i) % n + 1;
				pw.println(a + " " + b);
			}
		}
		pw.flush();
	}
}
