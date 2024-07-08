

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		String[] S = sc.nextLine().split("");

		sc.close();

		if (S[0].equals(S[1])) {
			for (int i = 2; i < S.length; i++) {
				if (!S[0].equals(S[i])) {
					System.out.println(i + 1);
				}
			}
		}else if(S[1].equals(S[2])) {
			System.out.println(1);
		}else {
			System.out.println(2);
		}
		
	}
}
