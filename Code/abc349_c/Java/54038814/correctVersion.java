import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String S = sc.next();
		String T = sc.next();
		String ans = "No";
		int n = 0;
		int limit = 3;

		if (T.charAt(2) == 'X') {
			limit = 2;
		}

		for (int i = 0; i < S.length(); i++) {
			char chS = S.charAt(i); //文字をchar型で抜き出し
			int numS = chS - 'a'; // 'a'が0に対応するように調整

			char chT = T.charAt(n); //文字をchar型で抜き出し
			int numT = chT - 'A'; // 'a'が0に対応するように調整

			if (numS == numT) {
				n++;
			}
			if (n >= limit) {
				ans = "Yes";
				break;
			}
		}

		System.out.println(ans);
		sc.close();
	}
}