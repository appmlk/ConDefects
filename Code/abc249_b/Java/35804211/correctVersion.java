import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String S = scan.next();
		String result = "Yes";

		if (S.equals(S.toLowerCase())||S.equals(S.toUpperCase())) {
			result = "No";
		} else {
			for (int i = 0; i < S.length(); i++) {
				if (S.indexOf(S.charAt(i)) != S.lastIndexOf(S.charAt(i))) {
					result = "No";
				}
			}
		}
		System.out.println(result);
	}
}
