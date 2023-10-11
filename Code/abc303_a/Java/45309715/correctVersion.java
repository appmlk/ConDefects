import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		int N = input.nextInt();
		String S = input.next();
		String T = input.next();
		
		String Answer = "Yes";
		for (int i = 0; i < N; i++) {
			if (S.charAt(i) != T.charAt(i)) {
				if ((S.charAt(i) == 'l' && T.charAt(i) == '1') || (S.charAt(i) == '1' && T.charAt(i) == 'l')) {
					continue;
				}
				else if ((S.charAt(i) == '0' && T.charAt(i) == 'o') || (S.charAt(i) == 'o' && T.charAt(i) == '0')) {
					continue;
				} else {
					Answer = "No";
					break;
				}
				
			}
		}
		System.out.println(Answer);
	}
}