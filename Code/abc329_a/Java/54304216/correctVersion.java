import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		sc.close();
		String[] S = s.split("");
		StringBuilder sb = new StringBuilder(S[0]);
		for (int i = 1; i < S.length; i++) {
			sb.append(" " + S[i]);
		}
		System.out.println(sb);
	}
}