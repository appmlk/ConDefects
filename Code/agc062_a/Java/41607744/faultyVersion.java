import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		System.out.println(t);
		for (int i = 0; i < t; i++) {
			System.out.println(i);
			int n = sc.nextInt();
			String s = sc.next();
			String newS = s.replaceAll("A", "");
			if (newS.length() == 0) {
				System.out.println("A");
				continue;
			}

			newS = s.replace("BA", "");
			if (newS.length() != s.length()) {
				System.out.println("A");
				continue;
			}
			System.out.println("B");
		}
	}
}