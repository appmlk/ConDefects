import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		int N = input.nextInt();
		String S = input.next();
		
		boolean check1 = false;
		boolean check2 = true;

		for (int i = 0; i < N; i++) {
			if (S.charAt(i) == 'o') {
				check1 = true;
			}
			if (S.charAt(i) == 'x') {
				check2 = false;
			}
		}
		
		if (check1 && check2) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
	}
}