import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		sc.close();
		String[] S = s.split("");
		
		String ini = S[0];
		if (!ini.equals(S[1])) {
			System.out.println(ini.equals(S[2]) ? 2 : 1);
		} else {
			for (int i = 3; i < S.length; i++) {
				if (!ini.equals(S[i])) {
					System.out.println(i + 1);
				}
			}
		}
	}
}
