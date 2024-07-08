import java.util.*;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String  s = sc.next();

		String subS = s.substring(3);
		
		int num = Integer.parseInt(subS);

		if (num > 0 && num < 350 && num != 316) {
			System.out.println("Yes");
			System.exit(0);
		}

		System.out.println("No");
	}
}
