import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);


		int N = sc.nextInt();
		String S = sc.next();

		for (int i=0; i<N-1; i++) {
			if (S.substring(i,i+2).equals("ab")) {
				System.out.println("Yes");
				System.exit(0);
			}
		}
		
		System.out.println("No");
		
		sc.close();
	}
}