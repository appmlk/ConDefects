
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);

		int n = sc.nextInt();
		String[] s = sc.next().split("");

		int taka = 0;
		int ao = 0;
		int winner = 0;
		if (s.length % 1 == 1) {
			winner = s.length / 2 + 1;
		} else {
			winner = s.length / 2;
		}

		for (String str : s) {
			if (str.equals("T")) {
				taka++;
			} else {
				ao++;
			}

			if (taka == winner) {
				pw.println("T");
				break;
			} else if (ao == winner) {
				pw.println("A");
				break;
			}
		}

		pw.flush();

	}
}
